package cn.wangdian.Controller.Admin.ShopKeeper;

import cn.wangdian.Model.ShunShop;
import cn.wangdian.Model.ShunShopAttributes;
import cn.wangdian.Model.ShunShopPhotos;
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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

/**
 * 店主只有查看 ShunShop 商品的功能
 */
@Controller
@RequestMapping("/admin/shopKeeper")
public class ShopKeeperShunShopController {

    private ExecuteResult executeResult=new ExecuteResult();

    private Log logger= LogFactory.getLog(ShopKeeperShunShopController.class);


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
        return "backPage/shopKeeper/shunShop/shunShopList";
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
        return "backPage/shopKeeper/shunShop/add";
    }

}
