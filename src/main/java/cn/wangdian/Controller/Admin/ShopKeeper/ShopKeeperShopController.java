package cn.wangdian.Controller.Admin.ShopKeeper;

import cn.wangdian.Controller.Admin.Admin.ShopController;
import cn.wangdian.Model.Shop;
import cn.wangdian.Model.ShopAttributes;
import cn.wangdian.Model.ShopPhotos;
import cn.wangdian.Service.ShopAttributesService;
import cn.wangdian.Service.ShopPhotosService;
import cn.wangdian.Service.ShopService;
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

import java.util.List;

/**
 * 店主只有查看普通商品的功能
 */
@Controller
@RequestMapping("/admin/shopKeeper")
public class ShopKeeperShopController {

    private ExecuteResult executeResult=new ExecuteResult();

    private Log logger= LogFactory.getLog(ShopKeeperShopController.class);

    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopAttributesService shopAttributesService;
    @Autowired
    private ShopPhotosService shopPhotosService;

    private static int parameterCountBefore=0;

    @RequestMapping("/shop/list")
    public String shopList(Model model,
                           String orderField,String orderDirection,Integer pageSize,Integer pageCurrent,
                           String number,String name,String shopType,String shopModel,Integer isRecommend){
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
            if (isRecommend!=null&&!isRecommend.equals("")){
                model.addAttribute("isRecommend",isRecommend);
                parameterCountNow++;
            }


            if (pageSize==null||pageSize.equals("")){
                pageSize=5;
            }
            //有多少页
            int count=shopService.countAllByIsDel0(number,name,shopType,shopModel,0,isRecommend);
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
            shopList = shopService.findAllByIsDel0(number,name,shopType,shopModel,0,isRecommend,orderField,orderDirection,pageRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("shopList",shopList);
        return "backPage/shopKeeper/shop/shopList";
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
        return "backPage/shopKeeper/shop/add";
    }
}
