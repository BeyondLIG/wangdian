package cn.wangdian.Controller.Admin.Admin;

import cn.wangdian.Model.Orders;
import cn.wangdian.Model.Shop;
import cn.wangdian.Model.ShopCart;
import cn.wangdian.Service.BenefitService;
import cn.wangdian.Service.OrdersService;
import cn.wangdian.Service.ShopCartService;
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
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by 25065 on 2016/9/16.
 */
@Controller
@RequestMapping("/admin")
public class OrdersController {

    private ExecuteResult executeResult=new ExecuteResult();

    private Log logger= LogFactory.getLog(OrdersController.class);

    private static int parameterCountBefore=0;

    @Autowired
    private BenefitService benefitService;

    @Autowired
    private OrdersService ordersService;
    @Autowired
    private ShopCartService shopCartService;

    @RequestMapping("/orders/list")
    public String ordersList(Model model,
                           String orderField,String orderDirection,Integer pageSize,Integer pageCurrent,
                           String shopOrderId,String shopName,String shopKeeper,String shopOrderMan,String shopOrderTime,Integer status){
        Page<Orders> ordersList= null;
        try {
            int parameterCountNow=0;
            if (shopOrderId!=null&&!shopOrderId.equals("")){
                model.addAttribute("shopOrderId",shopOrderId);
                parameterCountNow++;
            }
            if (shopName!=null&&!shopName.equals("")){
                model.addAttribute("shopName",shopName);
                parameterCountNow++;
            }
            if (shopKeeper!=null&&!shopKeeper.equals("")){
                model.addAttribute("shopKeeper",shopKeeper);
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
        return "backPage/admin/orders/ordersList";
    }

    @RequestMapping(value = "/orders/add",method = RequestMethod.GET)
    public String ordersAdd(Integer id,Model model){
        if (id!=null){
            Orders orders=null;
            List<ShopCart> shopCartList=null;

            try {
                orders=ordersService.findById(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            model.addAttribute("orders",orders);
        }
        return "backPage/admin/orders/add";
    }

    @ResponseBody
    @RequestMapping(value = "/orders/add",method = RequestMethod.POST)
    public Object shopAdd(Orders orders, String orderTime,Model model){
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            orders.setShopOrderTime(sdf.parse(orderTime));
            if (orders.getId()!=null){
                //编辑
                Orders o = ordersService.findById(orders.getId());
                //已确认收货，不能够再回到已发货状态
                if (orders.getStatus() == 2 && o.getStatus() == 3){
                    return executeResult.jsonReturn(300, "已确认收货后，不能够回到已发货状态");
                }else if (orders.getStatus() == 3) {
                    //管理员修改订单状态为已确认收货，也需要给店主分润
                    if (o.getStatus() == 2) {
                        for (ShopCart shopCart : o.getShopCartList()){
                            if ("shop".equals(shopCart.getType())) {
                                benefitService.shareGoodsBenefit(shopCart.getShopId(), shopCart.getUserId(), shopCart.getCount(), shopCart.getId());
                            }
                        }
                    }
                }
                ordersService.update(orders);
            }else {
                //添加
                ordersService.save(orders);
            }
            return executeResult.jsonReturn(200);
        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage());
        }
    }

    /**
     * 订单删除
     * @return
     */
    @ResponseBody
    @RequestMapping("/orders/delete")
    public Object delete(Integer id){
        try {
            ordersService.deleteByPrimaryKey(id);
            return executeResult.jsonReturn(200,false);
        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage(),false);
        }
    }
}
