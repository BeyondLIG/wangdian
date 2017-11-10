package cn.wangdian.Controller.Admin.Admin;

import cn.wangdian.Model.*;
import cn.wangdian.Service.ShopService;
import cn.wangdian.Service.ShunShopAttributesService;
import cn.wangdian.Service.ShunShopPhotosService;
import cn.wangdian.Service.ShunShopService;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 25065 on 2016/9/16.
 */
@Controller
@RequestMapping("/admin")
public class ShunShopController {

    private ExecuteResult executeResult=new ExecuteResult();

    private Log logger= LogFactory.getLog(ShunShopController.class);


    @Autowired
    private ShunShopService shunShopService;
    @Autowired
    private ShunShopAttributesService shunShopAttributesService;
    @Autowired
    private ShunShopPhotosService shunShopPhotosService;

    private static int parameterCountBefore=0;

    @RequestMapping("/shunShop/list")
    public String shopList(Model model,
                           String orderField,String orderDirection,Integer pageSize,Integer pageCurrent,
                           String number,String name,String shopType,String shopModel,Integer status){
        Page<ShunShop> shunShopList= null;
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


            if (pageSize==null||pageSize.equals("")){
                pageSize=5;
            }
            //有多少页
            int count=shunShopService.countAllByIsDel0(number,name,shopType,shopModel,status);
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
            }else {
                pageCurrent=pageCurrent-1;
            }
            PageRequest pageRequest=new PageRequest(pageCurrent,pageSize);
            shunShopList = shunShopService.findAllByIsDel0(number,name,shopType,shopModel,status,orderField,orderDirection,pageRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("shunShopList",shunShopList);
        return "backPage/admin/shunShop/shunShopList";
    }

    @RequestMapping(value = "/shunShop/add",method = RequestMethod.GET)
    public String userAdd(Integer id,Model model){
        List<ShunShopAttributes> shunShopAttributesList=null;
        List<ShunShopPhotos> shunShopPhotosList=null;
        List<ShunShopPhotos> shunShopShowsList=null;
        if (id!=null){
            ShunShop shunShop=null;

            try {
                shunShop=shunShopService.findById(id);

                shunShopAttributesList=shunShopAttributesService.findAllByIsDel0AndShopId(id);
                shunShopPhotosList=shunShopPhotosService.findAllByIsDel0AndShopId(id,1);
                shunShopShowsList=shunShopPhotosService.findAllByIsDel0AndShopId(id,0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            model.addAttribute("shunShop",shunShop);
            model.addAttribute("shunShopAttributesList",shunShopAttributesList);
            model.addAttribute("shunShopPhotosList",shunShopPhotosList);
            model.addAttribute("shunShopShowsList",shunShopShowsList);
        }
        return "backPage/admin/shunShop/add";
    }

    @ResponseBody
    @RequestMapping(value = "/shunShop/add",method = RequestMethod.POST)
    public Object shopAdd(ShunShop shunShop,String shunShopStartTime,ShunShopAttributesArrays shunShopAttributesArrays, Model model,String shunShopInTime){

        List<ShunShopAttributes> shunShopAttributesList=shunShopAttributesArrays.getShunShopAttributesList();
        List<ShunShopAttributesValue> shunShopAttributesValueList=shunShopAttributesArrays.getShunShopAttributesValueList();
        List<ShunShopPhotos> shunShopPhotosList=shunShopAttributesArrays.getShunShopPhotosList();
        List<ShunShopPhotos> shunShopShowsList=shunShopAttributesArrays.getShunShopShowsList();
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            shunShop.setInTime(sdf.parse(shunShopInTime));

            Date startTime=sdf.parse(shunShopStartTime);

            shunShop.setStartTime(startTime);
            int day=shunShop.getDay();int hours=shunShop.getHours();int minutes=shunShop.getMinutes();int seconds=shunShop.getSeconds();

            long time=(startTime.getTime()/1000+day*24*3600+hours*3600+minutes*60+seconds);

            GregorianCalendar gc=new GregorianCalendar();
            gc.setTimeInMillis(time*1000);

            Date endTime=sdf.parse(sdf.format(gc.getTime()));
            shunShop.setEndTime(endTime);

            Integer shunShopId=null;

            if (shunShop.getId()!=null){
                //编辑
                String numberFromDatebase=shunShopService.findNumberById(shunShop.getId());
                //编辑过程中所填的编号与本来数据库的编号不一样
                if (!numberFromDatebase.equals(shunShop.getNumber())){
                    String number=shunShopService.checkNumber(shunShop.getNumber());
                    if (number!=null){
                        return executeResult.jsonReturn(300,"该编号已被注册",false);
                    }
                }
                shunShopId=shunShop.getId();
                shunShopService.update(shunShop);
            }else {
                //添加
                String number=shunShopService.checkNumber(shunShop.getNumber());
                if (number!=null){
                    return executeResult.jsonReturn(300,"该编号已被注册",false);
                }else {
                    shunShopId=shunShopService.save(shunShop);
                }
            }

            for (int i=0;i<shunShopAttributesList.size();i++){
                shunShopAttributesList.get(i).setIsDel(0);
                shunShopAttributesList.get(i).setShopId(shunShopId);
                String name=shunShopAttributesService.findNameByNameAndShopId(shunShopAttributesList.get(i).getName(),shunShopId);

                if (name!=null){
                    //属性名存在，则不需要重新插入,更新名字
                    ShunShopAttributes shunShopAttributes=shunShopAttributesService.findByNameAndShopId(name,shunShopId);
                    ShunShopAttributesValue shunShopAttributesValue=shunShopAttributesValueList.get(i);
                    shunShopAttributesValue.setIsDel(0);
                    shunShopAttributes.addShunShopAttributesValueList(shunShopAttributesValue);
                    shunShopAttributesService.save(shunShopAttributes);
                }else {
                    //属性名不存在,创建
                    ShunShopAttributesValue shunShopAttributesValue=shunShopAttributesValueList.get(i);
                    shunShopAttributesValue.setIsDel(0);
                    shunShopAttributesList.get(i).addShunShopAttributesValueList(shunShopAttributesValue);
                    shunShopAttributesService.save(shunShopAttributesList.get(i));
                }
            }

            //商品轮播图片展示
            for (ShunShopPhotos shunShopPhotos:shunShopShowsList){
                shunShopPhotos.setIsDel(0);
                shunShopPhotos.setShopId(shunShopId);
                shunShopPhotos.setPhotoType(0);
                shunShopPhotosService.save(shunShopPhotos);
            }

            //图片详情
            for (ShunShopPhotos shunShopPhotos:shunShopPhotosList){
                shunShopPhotos.setIsDel(0);
                shunShopPhotos.setShopId(shunShopId);
                shunShopPhotos.setPhotoType(1);
                shunShopPhotosService.save(shunShopPhotos);
            }
            return executeResult.jsonReturn(200);
        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage());
        }
    }

    /**
     * 商品编号检测是否存在
     * @return
     */
    @ResponseBody
    @RequestMapping("/shunShop/checkNumber")
    public Object checkNumber(String number){
        try {
            String name=shunShopService.checkNumber(number);
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
    @RequestMapping("/shunShop/shangJia")
    public Object shangJia(Integer id){
        try {
            shunShopService.shangJiaById(id);
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
    @RequestMapping("/shunShop/xiaJia")
    public Object xiaJia(Integer id){
        try {
            shunShopService.xiaJiaById(id);
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
    @RequestMapping("/shunShop/delete")
    public Object delete(Integer id){
        try {
            shunShopService.deleteByPrimaryKey(id);
            return executeResult.jsonReturn(200,false);
        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage(),false);
        }
    }

    /**
     * 图片上传
     */
    @ResponseBody
    @RequestMapping("/shunShop/upload")
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
    @RequestMapping("/shunShop/photo/delete")
    public Object deletePhoto(HttpServletRequest request, HttpServletResponse response,Integer id,Integer index,String type,String urlPath) throws Exception{

        try {
            String basePath=request.getSession().getServletContext().getRealPath("/");
            File file=new File(basePath+urlPath);
            if (file.exists()){
                file.delete();
            }
            if (id!=null){
                shunShopPhotosService.deleteByPrimaryKey(id);
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
    @RequestMapping("/shunShop/ajaxDone")
    public Object ajaxDone(Integer id){
        try {
            shunShopAttributesService.deleteByPrimaryKey(id);
            return executeResult.jsonReturn(200,false);
        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage(),false);
        }
    }
}
