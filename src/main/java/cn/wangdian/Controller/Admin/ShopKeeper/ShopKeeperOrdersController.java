package cn.wangdian.Controller.Admin.ShopKeeper;

import cn.wangdian.Model.Orders;
import cn.wangdian.Model.ShopKeeper;
import cn.wangdian.Service.OrdersService;
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

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;

/**
 * Created by 25065 on 2016/9/16.
 */
@Controller
@RequestMapping("/admin/shopKeeper")
public class ShopKeeperOrdersController {

    private ExecuteResult executeResult=new ExecuteResult();

    private Log logger= LogFactory.getLog(ShopKeeperOrdersController.class);


    @Autowired
    private OrdersService ordersService;

    private static int parameterCountBefore=0;

    @RequestMapping("/orders/list")
    public String ordersList(Model model,HttpSession session,
                           String orderField,String orderDirection,Integer pageSize,Integer pageCurrent,
                           String shopOrderId,String shopName,String shopOrderMan,String shopOrderTime,Integer status){
        Page<Orders> ordersList= null;
        try {
            ShopKeeper shopKeeper123=(ShopKeeper) session.getAttribute("user");

            if (shopKeeper123==null){
                //session失效
                model.addAttribute("message","当前会话已失效，请重新登录");
                return "backPage/fail";
            }
            String shopKeeper=shopKeeper123.getUsername();

            int parameterCountNow=0;
            if (shopOrderId!=null&&!shopOrderId.equals("")){
                model.addAttribute("shopOrderId",shopOrderId);
                parameterCountNow++;
            }
            if (shopName!=null&&!shopName.equals("")){
                model.addAttribute("shopName",shopName);
                parameterCountNow++;
            }
            if (shopOrderMan!=null&&!shopOrderMan.equals("")){
                model.addAttribute("shopOrderMan",shopOrderMan);
                parameterCountNow++;
            }
            if (shopOrderTime!=null&&!shopOrderTime.equals("")){
                model.addAttribute("shopOrderTime",shopOrderTime);
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
            int count=ordersService.countAllByIsDel0(shopOrderId,shopName,shopKeeper,shopOrderMan,shopOrderTime,status);
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
            ordersList = ordersService.findAllByIsDel0(shopOrderId,shopName,shopKeeper,shopOrderMan,shopOrderTime,status,orderField,orderDirection,pageRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("ordersList",ordersList);
        return "backPage/shopKeeper/orders/ordersList";
    }

    @RequestMapping(value = "/orders/add",method = RequestMethod.GET)
    public String ordersAdd(Integer id,Model model){
        if (id!=null){
            Orders orders=null;
            try {
                orders=ordersService.findById(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            model.addAttribute("orders",orders);
        }
        return "backPage/shopKeeper/orders/add";
    }

}
