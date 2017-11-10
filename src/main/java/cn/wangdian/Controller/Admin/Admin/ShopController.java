package cn.wangdian.Controller.Admin.Admin;

import cn.wangdian.Model.*;
import cn.wangdian.Service.*;
import cn.wangdian.utils.ExecuteResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by 25065 on 2016/9/16.
 */
@Controller
@RequestMapping("/admin")
public class ShopController {

    private ExecuteResult executeResult=new ExecuteResult();

    private Log logger= LogFactory.getLog(ShopController.class);

    private static int parameterCountBefore=0;

    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopAttributesService shopAttributesService;
    @Autowired
    private ShopPhotosService shopPhotosService;

    @Autowired
    private BenefitService benefitService;

    @RequestMapping("/shop/list")
    public String shopList(Model model,
                           String orderField,String orderDirection,Integer pageSize,Integer pageCurrent,
                           String number,String name,String shopType,String shopModel,Integer status,Integer isRecommend){
        Page<Shop> shopList= null;
        try {
            int parameterCountNow=0;
            if (number!=null&&!number.equals("")){
                model.addAttribute("number",number);
                parameterCountNow++;
            }
            if (name!=null&&!name.equals("")){
                model.addAttribute("name",name);
                parameterCountNow++;
            }
            if (shopType!=null&&!shopType.equals("")){
                model.addAttribute("shopType",shopType);
                parameterCountNow++;
            }
            if (shopModel!=null&&!shopModel.equals("")){
                model.addAttribute("shopModel",shopModel);
                parameterCountNow++;
            }
            if (status!=null&&!status.equals("")){
                model.addAttribute("status",status);
                parameterCountNow++;
            }
            if (isRecommend!=null&&!isRecommend.equals("")){
                model.addAttribute("isRecommend",isRecommend);
                parameterCountNow++;
            }

            if (pageSize==null||pageSize.equals("")){
                pageSize=5;
            }

            //有多少页
            int count=shopService.countAllByIsDel0(number,name,shopType,shopModel,status,isRecommend);
            int pageNumbers=0;
            if (count%pageSize==0){
                //整除
                pageNumbers=count/pageSize;
            }else {
                //有余数
                pageNumbers=count/pageSize+1;
            }

            if (pageCurrent==null||pageCurrent.equals("")){
                pageCurrent=0;
            }else if (parameterCountNow!=parameterCountBefore){
                pageCurrent=0;
                parameterCountBefore=parameterCountNow;
            }else if (pageCurrent>pageNumbers){
                pageCurrent=0;
            }
            else {
                pageCurrent=pageCurrent-1;
            }

            logger.info("pageCurrent-----------------------------"+pageCurrent);
            logger.info("parameterCountNow-----------------------------"+parameterCountNow);
            logger.info("parameterCountBefore-----------------------------"+parameterCountBefore);
            logger.info("pageNumbers-----------------------------"+pageNumbers);

            PageRequest pageRequest=new PageRequest(pageCurrent,pageSize);
            shopList = shopService.findAllByIsDel0(number,name,shopType,shopModel,status,isRecommend,orderField,orderDirection,pageRequest);

        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("shopList",shopList);
        return "backPage/admin/shop/shopList";
    }

    @RequestMapping(value = "/shop/add",method = RequestMethod.GET)
    public String userAdd(Integer id,Model model){
        List<ShopAttributes> shopAttributesList=null;
        List<ShopPhotos> shopPhotosList=null;
        List<ShopPhotos> shopShowsList=null;
        if (id!=null){
            Shop shop=null;
            try {
                shop=shopService.findById(id);
                shopAttributesList=shopAttributesService.findAllByIsDel0AndShopId(id);


                shopPhotosList=shopPhotosService.findAllByIsDel0AndShopId(id,1);
                shopShowsList=shopPhotosService.findAllByIsDel0AndShopId(id,0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            model.addAttribute("shop",shop);
            model.addAttribute("shopAttributesList",shopAttributesList);
            model.addAttribute("shopPhotosList",shopPhotosList);
            model.addAttribute("shopShowsList",shopShowsList);
        }
        return "backPage/admin/shop/add";
    }

    @ResponseBody
    @RequestMapping(value = "/shop/add",method = RequestMethod.POST)
    public Object shopAdd(Shop shop,String shopInTime, ShopAttributesArrays shopAttributesArrays, Model model){

        List<ShopAttributes> shopAttributesList=shopAttributesArrays.getShopAttributesList();
        List<ShopAttributesValue> shopAttributesValueList=shopAttributesArrays.getShopAttributesValueList();
        List<ShopPhotos> shopPhotosList=shopAttributesArrays.getShopPhotosList();
        List<ShopPhotos> shopShowsList=shopAttributesArrays.getShopShowsList();
        try {
            Integer shopId=null;

            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            shop.setInTime(sdf.parse(shopInTime));
            if (shop.getId()!=null){
                //编辑
                String numberFromDatebase=shopService.findNumberById(shop.getId()); //商品的编号（唯一的）
                //编辑过程中所填的编号与本来数据库的编号不一样
                if (!numberFromDatebase.equals(shop.getNumber())){
                    String number=shopService.checkNumber(shop.getNumber());
                    if (number!=null){
                        return executeResult.jsonReturn(300,"该编号已被注册",false);
                    }
                }
                //现在情况是店主价格由前端传送到后来，不再需要后台计算
                //需要根据 成本价(firstCost) vip价(vipPrice) a, b, c 来计算出店主价(shopkeeperPrice)
//                calculateShopkeeperPrice(shop);
                shopId=shop.getId();
                //更新数据库对应的 shop
                shopService.update(shop);
            }else {
                //添加
                String number=shopService.checkNumber(shop.getNumber());
                if (number!=null){
                    return executeResult.jsonReturn(300,"该编号已被注册",false);
                }
                else{
                    //现在情况是店主价格由前端传送到后来，不再需要后台计算
                    //需要根据 成本价(firstCost) vip价(vipPrice) a, b, c 来计算出店主价(shopkeeperPrice)
//                    calculateShopkeeperPrice(shop);
                    shopId = shopService.save(shop);
                    System.out.println("========="+shopId);
                }
            }
            //把商品的属性存储进数据库
            for (int i=0;i<shopAttributesList.size();i++){
                shopAttributesList.get(i).setIsDel(0); //设置该
                shopAttributesList.get(i).setShopId(shopId);
                String name=shopAttributesService.findNameByNameAndShopId(shopAttributesList.get(i).getName(),shopId);
                //使用级联进行插入或者更新
                if (name!=null){
                    //属性名存在，则不需要重新插入,更新名字
                    ShopAttributes shopAttributes=shopAttributesService.findByNameAndShopId(name,shopId);
                    ShopAttributesValue shopAttributesValue=shopAttributesValueList.get(i);
                    shopAttributesValue.setIsDel(0);
                    shopAttributes.addShopAttributesValueList(shopAttributesValue);
                    shopAttributesService.save(shopAttributes);
                }else {
                    //属性名不存在,创建
                    ShopAttributesValue shopAttributesValue=shopAttributesValueList.get(i);
                    shopAttributesValue.setIsDel(0);
                    shopAttributesList.get(i).addShopAttributesValueList(shopAttributesValue);
                    shopAttributesService.save(shopAttributesList.get(i));
                }

            }

            /*
            以下保存图片的操作，不会出现图片重复添加的问题，因为在旧的模式下，旧的图片会附带 id 回传，id 相同会自动覆盖原来 id 的图片的信息
             */
            //商品轮播图片展示
            for (ShopPhotos shopPhotos:shopShowsList){
                if (shopPhotos.getUrlPath() != null && !shopPhotos.getUrlPath().equals("")) {
                    shopPhotos.setIsDel(0);
                    shopPhotos.setShopId(shopId);
                    shopPhotos.setPhotoType(0);
                    shopPhotosService.save(shopPhotos);
                }
            }

            //图片详情
            for (ShopPhotos shopPhotos:shopPhotosList){
                if (shopPhotos.getUrlPath() != null && !shopPhotos.getUrlPath().equals("")) {
                    shopPhotos.setIsDel(0);
                    shopPhotos.setShopId(shopId);
                    shopPhotos.setPhotoType(1);
                    shopPhotosService.save(shopPhotos);

                }
            }

            return executeResult.jsonReturn(200);
        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage());
        }
    }

    /**
     * 根据 成本价(firstCost) vip价(vipPrice) a, b, c 来计算出店主价(shopkeeperPrice)
     * @param shop 需要计算店主价的商品
     */
    private void calculateShopkeeperPrice(Shop shop) {
        Benefit benefit = benefitService.findGoodsShareBenefitParam();
        float shopkeeperPrice =
                ((benefit.getA() + benefit.getB() + benefit.getC()) * shop.getVipPrice() + shop.getFirstCost()) / (1 + benefit.getA() + benefit.getB() + benefit.getC());
        shop.setShopkeeperPrice(shopkeeperPrice);
    }

    @ResponseBody
    @RequestMapping("/shop/calculate/calculateShopkeeperPrice")
    public Integer calculateShopkeeperPrice(@RequestParam("firstCost") float firstCost, @RequestParam("vipPrice") float vipPrice) {
        Benefit benefit = benefitService.findGoodsShareBenefitParam();
        float shopkeeperPrice =
                ((benefit.getA() + benefit.getB() + benefit.getC()) * vipPrice + firstCost) / (1 + benefit.getA() + benefit.getB() + benefit.getC());
        int realPrice = (int) shopkeeperPrice;
        if (realPrice < shopkeeperPrice) {
            realPrice ++;
        }
        return realPrice;
    }

    /**
     * 商品编号检测是否存在
     * @return
     */
    @ResponseBody
    @RequestMapping("/shop/checkNumber")
    public Object checkNumber(String number){
        try {
            String name=shopService.checkNumber(number);
            if (name==null){
                return executeResult.jsonReturn(200,"该编号可以使用",false);
            }else {
                return executeResult.jsonReturn(300,"该编号已被注册",false);
            }
        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage(),false);
        }
    }

    /**
     * 商品上架
     * @return
     */
    @ResponseBody
    @RequestMapping("/shop/shangJia")
    public Object shangJia(Integer id){
        try {
            shopService.shangJiaById(id);
            return executeResult.jsonReturn(200,false);
        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage(),false);
        }
    }

    /**
     * 商品下架
     * @return
     */
    @ResponseBody
    @RequestMapping("/shop/xiaJia")
    public Object xiaJia(Integer id){
        try {
            shopService.xiaJiaById(id);
            return executeResult.jsonReturn(200,false);
        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage(),false);
        }
    }



    /**
     * 商品删除
     * @return
     */
    @ResponseBody
    @RequestMapping("/shop/delete")
    public Object delete(Integer id){
        try {
            shopService.deleteByPrimaryKey(id);
            return executeResult.jsonReturn(200,false);
        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage(),false);
        }
    }

    /**
     * 图片上传
     */
    @ResponseBody
    @RequestMapping("/shop/upload")
    public Object uploadPhoto(HttpServletRequest request, HttpServletResponse response) throws Exception{

        String basePath=request.getSession().getServletContext().getRealPath("/");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHH");
        String ymd="uploads"+"/"+sdf.format(new Date());
        String parentPath=basePath+ymd+"/";

        String readFilePath=null;

        String path=null;

        try {
            //解析器解析request的上下文
            CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());

            String fileName=null;
            //先判断request中是否包含multipart类型的数据，
            if (multipartResolver.isMultipart(request)){
                //再将request中的数据转化成multipart类型的数据
                MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest) request;
                Iterator iterator=multiRequest.getFileNames();
                while (iterator.hasNext()){
                    MultipartFile file=multiRequest.getFile((String)iterator.next());
                    if (file!=null){
                        fileName=file.getOriginalFilename();

                        String imageType=fileName.substring(fileName.lastIndexOf(".")).trim().toLowerCase();
                        String uuid= UUID.randomUUID().toString().replace("-","");//返回一个随机UUID
                        String newFileName=uuid+imageType;
//                        String fileNameFirst=fileName.substring(0,fileName.lastIndexOf(".")).trim().toLowerCase();
//                        String hashStr=Integer.toHexString(fileNameFirst.hashCode());
//                        String newFileName=hashStr+imageType;

                        path=parentPath+newFileName;
                        readFilePath=ymd+"/"+newFileName;
                        File localFile=new File(path);
                        if (!localFile.getParentFile().exists()){
                            localFile.getParentFile().mkdirs();
                        }
                        file.transferTo(localFile);
                        System.out.println(readFilePath);
                    }
                }
            }
            return executeResult.jsonReturnFile(200,fileName,readFilePath);
        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage(),false);
        }
    }

    /**
     * 图片删除
     */
    @ResponseBody
    @RequestMapping("/shop/photo/delete")
    public Object deletePhoto(HttpServletRequest request, HttpServletResponse response,Integer id,Integer index,String type,String urlPath) throws Exception{

        try {
            String basePath=request.getSession().getServletContext().getRealPath("/");
            File file=new File(basePath+urlPath);
            if (file.exists()){
                //删除磁盘上的图片
                file.delete();
            }
            if (id!=null){
                shopPhotosService.deleteByPrimaryKey(id);
                int i = 1;
                for (int j = 0; j < 10; j++) {
                    System.out.println(i);
                }
            }
            return executeResult.jsonReturnIndexAndType(200,index,type,false);
        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage(),false);
        }
    }

    /**
     * 突变体诱变类型删除
     * @return
     */
    @ResponseBody
    @RequestMapping("/shop/ajaxDone")
    public Object ajaxDone(Integer id){
        try {
            shopAttributesService.deleteByPrimaryKey(id);
            return executeResult.jsonReturn(200,false);
        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage(),false);
        }
    }
}
