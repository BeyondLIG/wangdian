package cn.wangdian.Controller.Admin.ShopKeeper;

import cn.wangdian.Model.*;
import cn.wangdian.Service.*;
import cn.wangdian.utils.ExecuteResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 25065 on 2016/9/18.
 */
@Controller
@RequestMapping("/admin/shopKeeper")
public class ShopKeeperProfitController {

    private ExecuteResult executeResult=new ExecuteResult();

    private Log logger= LogFactory.getLog(ShopKeeperProfitController.class);

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private ShopKeeperService shopKeeperService;

    @Autowired
    private ShopKeeperProfitService shopKeeperProfitService;

    @Autowired
    private ShopkeeperProfitTrackService shopkeeperProfitTrackService;

    @Autowired
    private ShopCartService shopCartService;


    @RequestMapping("/profit/list")
    public String ordersList(Model model,HttpSession session,
                             String shopType,String shopName,String shopModel,String shopNumber,
                             String startTime,String endTime){
        try {
            ShopKeeper shopkeeper=(ShopKeeper) session.getAttribute("user");

            if (shopkeeper==null){
                //session失效
                model.addAttribute("message","当前会话已失效，请重新登录");
                return "backPage/fail";
            }
//            String shopKeeper=shopKeeper123.getUsername();

//            if (shopType!=null&&!shopType.equals("")){
//                model.addAttribute("shopType",shopType);
//            }
//            if (shopName!=null&&!shopName.equals("")){
//                model.addAttribute("shopName",shopName);
//            }
//
//            if (shopModel!=null&&!shopModel.equals("")){
//                model.addAttribute("shopModel",shopModel);
//            }
//            if (shopNumber!=null&&!shopNumber.equals("")){
//                model.addAttribute("shopNumber",shopNumber);
//            }
//            if (startTime!=null&&!startTime.equals("")){
//                model.addAttribute("startTime",startTime);
//            }
//
//            if (endTime!=null&&!endTime.equals("")){
//                model.addAttribute("endTime",endTime);
//            }

            //提交订单
//            long tiJiaDingDanCount = ordersService.countAllByIsDel0(shopType,shopName,shopModel,shopNumber,shopKeeper,startTime,endTime,0);
//            model.addAttribute("tiJiaDingDanCount",tiJiaDingDanCount);

            //已付款未发货
//            long yiFuKuanWeiFaHuoCount=ordersService.countAllByIsDel0(shopType,shopName,shopModel,shopNumber,shopKeeper,startTime,endTime,1);
//            model.addAttribute("yiFuKuanWeiFaHuoCount",yiFuKuanWeiFaHuoCount);

            //已付款已发货
//            long yiFuKuanYiFaHuoCount=ordersService.countAllByIsDel0(shopType,shopName,shopModel,shopNumber,shopKeeper,startTime,endTime,2);
//            model.addAttribute("yiFuKuanYiFaHuoCount",yiFuKuanYiFaHuoCount);

            //确认收货
//            long yiQueShouHuoCount=ordersService.countAllByIsDel0(shopType,shopName,shopModel,shopNumber,shopKeeper,startTime,endTime,3);
//            model.addAttribute("yiQueShouHuoCount",yiQueShouHuoCount);

            //申请退款
//            long shenQingTuiHuoZhongCount=ordersService.countAllByIsDel0(shopType,shopName,shopModel,shopNumber,shopKeeper,startTime,endTime,4);
//            model.addAttribute("shenQingTuiHuoZhongCount",shenQingTuiHuoZhongCount);

            //已退款
//            long yiKuanCount=ordersService.countAllByIsDel0(shopType,shopName,shopModel,shopNumber,shopKeeper,startTime,endTime,5);
//            model.addAttribute("yiKuanCount",yiKuanCount);

//            model.addAttribute("shopKeeper",shopKeeper);

//            float allProfit=0;
            /*List<Orders> profitList=ordersService.findAllByShopKeeperAndStatus3(shopKeeper);
            for (Orders orders:profitList){
//                allProfit+=orders.getProfits()*orders.getCount();
            }*/
            //现在新的方法是，每一个用户提交完订单后和新招店主提交年费后，会自动更新店主的 allProfit 字段
//            List<Float> profitList=ordersService.findAllByShopKeeperAndStatus3(shopKeeper);
//            for(float profit:profitList){
//                allProfit+=profit;
//            }

//            shopKeeperService.updateAllProfitById(allProfit,shopKeeper123.getId());

//            if (shopKeeper123.getAllProfit()!=allProfit){
//                model.addAttribute("errorMessage","你目前的总收益可能有误，请与管理员联系！！！");
//            }

//            ShopKeeper shopKeeper1=shopKeeperService.selectByUsername(shopKeeper);

            //这里需要显示店主的历史收益来源


            model.addAttribute("allProfit", shopkeeper.getAllProfit());
            model.addAttribute("yiTiXian",shopkeeper.getYiTiXian());
            model.addAttribute("message",shopkeeper.getMessage());
//            model.addAttribute("allProfit",allProfit);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "backPage/shopKeeper/profit/list";
    }

    @RequestMapping(value = "/profit/tiXian",method = RequestMethod.GET)
    public String tiXian(Model model, HttpSession session){
        ShopKeeper shopKeeper = (ShopKeeper) session.getAttribute("user");
        model.addAttribute("shopKeeper",shopKeeper);
         return "backPage/shopKeeper/profit/tiXian";
    }

    /**
     * 店主提交提现申请
     * @param money 提现数额
     * @param alipay 提现到哪一个支付宝账号
     */
    @ResponseBody
    @RequestMapping(value = "/profit/tiXian",method = RequestMethod.POST)
    public Object tiXian(@RequestParam("money") float money,
                         @RequestParam("alipay") String alipay,
                         HttpSession session){
        try {
            ShopKeeper shopKeeper1 = (ShopKeeper) session.getAttribute("user");
            String shopKeeper = shopKeeper1.getUsername();
            // 通过历史总收益 - 历史总提现 = 总未提现金额
            float weiTiXian=shopKeeper1.getAllProfit()-shopKeeper1.getYiTiXian();
            logger.info("没有提现为-------"+weiTiXian);
            logger.info("提现为-------"+money);
            if (money>weiTiXian){
                //不能提现
                return executeResult.jsonReturn(300,"，sorry，你所提现的金额超出了你的未提现金额！！！");
            }else {
                //可以提现
                //保存提现记录
                ShopKeeperProfit shopKeeperProfit=new ShopKeeperProfit(shopKeeper,money,new Date(),0,0, alipay);
                shopKeeperProfitService.save(shopKeeperProfit);

                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String nowDate=sdf.format(new Date());
                String message=(shopKeeper+"于"+nowDate+"申请提现"+money+"元");
                //更新店主的最新消息 ==> 此处为店主的体现信息
                //在申请提现的时候就把该店主的已提现金额 += money, 防止用户重复多次申请
                shopKeeperService.updateMessageAndYiTiXianById(message, shopKeeper1.getYiTiXian() + money, shopKeeper1.getId());
                //更新 session 中 Shopkeeper 的已提现数额
                shopKeeper1.setYiTiXian(shopKeeper1.getYiTiXian() + money);
//                shopKeeperService.updateMessageByPrimaryKey(message,shopKeeper1.getId());
                //返回前段操作成功
                return executeResult.jsonReturn(200);
            }
        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage());
        }
    }

    /**
     * 店主查看收益来源 ==> 下属用户或者店主商品收益来源
     * @param page
     * @return
     */
    @RequestMapping("/goodsIncome")
    public String goodsIncome(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                              @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                              HttpSession session,
                              Model model){
        ShopKeeper shopKeeper = (ShopKeeper) session.getAttribute("user");
        List<ShopkeeperProfitTrack> shopkeeperProfitTrackList = shopkeeperProfitTrackService.findByShopkeeperIdWithGoods(shopKeeper.getId(), page, size);
        List<ShopCart> shopCartList = new ArrayList<>();
        for (ShopkeeperProfitTrack shopkeeperProfitTrack : shopkeeperProfitTrackList) {
            ShopCart shopCart = shopCartService.findById(shopkeeperProfitTrack.getOrderId());
            shopCartList.add(shopCart);
        }
        model.addAttribute("shopCartList", shopCartList);
        model.addAttribute("shopkeeperProfitTrackList", shopkeeperProfitTrackList); //店主收益追踪链表
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("recordNumber", shopkeeperProfitTrackService.countAllType0Or1(shopKeeper.getId()));
        return "backPage/shopKeeper/profit/shopDetail";
    }

    /**
     * 店主查看收益来源 ==> 新招店主年费收益
     *
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/annualFeeIncome")
    public String annualFeeIncome(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                  @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                  HttpSession session,
                                  Model model) {
        ShopKeeper shopKeeper = (ShopKeeper) session.getAttribute("user");
        List<ShopkeeperProfitTrack> shopkeeperProfitTrackList = shopkeeperProfitTrackService.findByShopkeeperIdWithAnnualFee(shopKeeper.getId(), page, size);
        model.addAttribute("shopkeeperProfitTrackList", shopkeeperProfitTrackList);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("recordNumber", shopkeeperProfitTrackService.countAllType2Or3(shopKeeper.getId()));
        return "backPage/shopKeeper/profit/annualFeeDetail";
    }


}
