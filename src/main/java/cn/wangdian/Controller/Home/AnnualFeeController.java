package cn.wangdian.Controller.Home;

import cn.wangdian.Model.AnnualFee;
import cn.wangdian.Model.AnnualFeeOrder;
import cn.wangdian.Model.ShopKeeper;
import cn.wangdian.Service.*;
import cn.wangdian.utils.Encode;
import cn.wangdian.utils.ExtractParamFromRequest;
import cn.wangdian.utils.StringUtil;
import cn.wangdian.utils.alipay.util.AlipayNotify;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * 店主年费的控制器
 * 1.店主提交年费
 */
@Controller
@RequestMapping("/annualFee")
public class  AnnualFeeController {
    private Log logger = LogFactory.getLog(AnnualFeeController.class);

    @Autowired
    private ShopKeeperService shopKeeperService;

    @Autowired
    private AnnualFeeService annualFeeService;

    @Autowired
    private AnnualFeeOrderService annualFeeOrderService;

    @Autowired
    private BenefitService benefitService;

    @Autowired
    private UserService userService;

    private ExtractParamFromRequest extractParamFromRequest = new ExtractParamFromRequest();

    private Encode encode = new Encode();

    /**
     * 跳转到提交年费的页面
     */
    @RequestMapping("/submitAnnualFee")
    public String submitAnnualFeeGet(Model model) {
        model.addAttribute("isMatch", true);
        return "frontPage/pages/annualFee";
    }

    /**
     * 店主申请提交年费
     * 为某个店主提交年费，不需要先登录再交年费
     *
     * @param username  店主的用户名(唯一) NOT NULL
     * @param telephone 为手机号码是 telephone 的店主提价年费 NOT NULL
     * @return 返回提价年费的页面给店主
     */
    @RequestMapping(value = "/submitAnnualFee", method = RequestMethod.POST)
    public String submitAnnualFee(@RequestParam("username") String username,
                                  @RequestParam("telephone") String telephone,
                                  Model model) {
        ShopKeeper shopKeeper = shopKeeperService.selectByUsername(username);
        //判断用户名和手机号码是否匹配，避免店主操作失误
        if (shopKeeper != null && shopKeeper.getTelephone().equals(telephone)) {
            //用户名和手机号码匹配成功
            //取出第一条年费记录
            AnnualFee annualFee = annualFeeService.onlyOne();
            AnnualFeeOrder annualFeeOrder = new AnnualFeeOrder();
            annualFeeOrder.setFee(annualFee.getFee());
            annualFeeOrder.setStatus(0); //设置新建的年费订单为未付款
            annualFeeOrder.setShopKeeper(username);
            Date now = new Date();
            annualFeeOrder.setSubmitTime(now);
            /*
             * 通过生成一个长度为 4 随机字母数字串
             * 而且通过今天的年月日时分秒 ==> 尽量病避免订单重复
             * 以及今天的第几单来作为 orderId 的唯一标示
             */
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
            String today = sdf.format(now);
            //生成一个唯一 id 4位随机字母串+今天年月日yyyyMMdd+今天的第几单
            String annualFeeOrderId = StringUtil.ziMuRandom() + today + String.valueOf(annualFeeOrderService.countToday(s.format(now)));
            annualFeeOrder.setOrderId(annualFeeOrderId);
            //将新生成的记录存储到数据库中
            annualFeeOrderService.save(annualFeeOrder);
            //下列对象加入到 Model 后可以通过 request.getAttribute("name"); 取出来
            model.addAttribute("username", username);
            model.addAttribute("telephone", telephone);
//            model.addAttribute("annualFee", annualFee);
            model.addAttribute("annualFeeOrder", annualFeeOrder);
            // 跳转到支付页面
            return "frontPage/alipay/alipayAnnualFee";
        }
        model.addAttribute("isMatch", false);
        return "frontPage/pages/annualFee";
    }

    /**
     * 店家支付完年费支付宝通知服务器，执行相应的更新操作
     */
    @RequestMapping(value = "/notify_url", method = RequestMethod.POST)
    @ResponseBody
    public String notify_url(HttpServletRequest request) throws UnsupportedEncodingException {
        //用于验证消息是否来自支付宝
        Map<String, String> params = extractParamFromRequest.extractParamsFromRequest(request);
        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号 该订单号是我们发起的支付请求的时候发送给支付宝平台的，当支付完成支付宝返回相同的订单号来标示
        String out_trade_no = request.getParameter("out_trade_no");
        String encodeOut_trade_no = encode.getEncoding(out_trade_no);
        //GB2312是服务器上的编码格式
        if (!encodeOut_trade_no.equalsIgnoreCase("GB2312")) {
            out_trade_no = new String(out_trade_no.getBytes(encodeOut_trade_no), "UTF-8");
        }

        //支付宝交易号
        String trade_no = request.getParameter("trade_no");
        String encodeTrade_no = encode.getEncoding(trade_no);
        //GB2312是服务器上的编码格式
        if (!encodeTrade_no.equalsIgnoreCase("GB2312")) {
            trade_no = new String(trade_no.getBytes(encodeTrade_no), "UTF-8");
        }

        //交易状态
        String trade_status = request.getParameter("trade_status");
        String encodeTrade_status = encode.getEncoding(trade_status);
        if (!encodeTrade_status.equalsIgnoreCase("GB2312")) {
            trade_status = new String(trade_status.getBytes(encodeTrade_status), "UTF-8");
        }

        //计算出验证结果，判断消息是否来自支付宝和是否已经支付成功
        boolean verify_result = AlipayNotify.verify(params);
        if (verify_result) {
            //验证成功
            if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
                //该交易成功执行了
                //首先判断，业务逻辑是否已经在 return_url 中已经执行了
                //如果没有就执行更新的业务逻辑
                updateAnnualFeeOrderStatusAndShopKepperDeadLine(out_trade_no);
                //更新成功之后返回 success 给支付宝
                //否则支付宝系统将会在48小时内持续发送消息给我们服务器
                return "success";
            }
        }
        //验证失败，不进行任何操作
        return null;
    }

    /**
     * 店家支付完年费支付宝跳转到 return_url 需要返回一个页面提示店主年费提交成功
     */
    @RequestMapping(value = "return_url", method = RequestMethod.GET)
    public String return_url(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        //params 用于验证支付宝信息是否正确
        Map<String, String> params = extractParamFromRequest.extractParamsFromRequest(request);
        //店主提交年费的 id
        //postman 传过来的参数都是 GB2312 编码的 ==> 奇怪
        String out_trade_no = request.getParameter("out_trade_no");
        String encodeOut_trade_no = encode.getEncoding(out_trade_no);
        if (!encodeOut_trade_no.equalsIgnoreCase("GB2312")) {
            out_trade_no = new String(out_trade_no.getBytes(encodeOut_trade_no), "UTF-8");
        }

        //支付宝交易号
        String trade_no = request.getParameter("trade_no");
        String encodeTrade_no = encode.getEncoding(trade_no);
        if (!encodeTrade_no.equalsIgnoreCase("GB2312")) {
            trade_no = new String(trade_no.getBytes(encodeTrade_no), "UTF-8");
        }

        //交易状态
        String trade_status = request.getParameter("trade_status");
        String encodeTrade_status = encode.getEncoding(trade_status);
        if (!encodeTrade_status.equalsIgnoreCase("GB2312")) {
            trade_status = new String(trade_status.getBytes(encodeTrade_status), "UTF-8");
        }
        //验证消息是否来自支付宝服务器
        boolean verify_result = AlipayNotify.verify(params);
        if (verify_result) {
            model.addAttribute("msg1", "是支付宝发来的消息");
            //通过验证
            if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
                model.addAttribute("msg2", "支付失败");
                //该笔交易成功执行
                //判断数据是否已经由 notify_url 处理过，如果没有处理过，就更新
                updateAnnualFeeOrderStatusAndShopKepperDeadLine(out_trade_no);
                return "redirect:/index";
            }
        }
        return "frontPage/alipay/verifyError";
    }

    @RequestMapping(value = "/show_url", method = RequestMethod.GET)
    public String StringShowURL(@RequestParam(value = "username", required = false) String username,
                              @RequestParam(value = "telephone", required = false) String telephone,
                              @RequestParam(value = "fee", required = false) String fee,
                              Model model) throws IOException {
        model.addAttribute("username", username);
        model.addAttribute("telephone", telephone);
        model.addAttribute("fee", fee);
        return "frontPage/alipay/annualfee_show_url";
    }

    /**
     * 更新年费订单信息以及更新店主的最后到期期限
     * 线程安全的
     * @return 由进行更新操作返回 true; 已经更新了，没有进行任何更新返回 false
     */
//    @ResponseBody
//    @RequestMapping("/test/annualFee/profit/track")
    private synchronized boolean updateAnnualFeeOrderStatusAndShopKepperDeadLine(String out_trade_no) {
        AnnualFeeOrder annualFeeOrder = annualFeeOrderService.findByOrderId(out_trade_no);
        //获取订单状态，避免重复更新
        if (annualFeeOrder != null && annualFeeOrder.getStatus() == 0) {
            //尚未进行更新，进行逻辑更新
            annualFeeOrderService.updateAlreadyPaidByOrderId(out_trade_no);
           /* 更新店主的到期期限
            需要分两种情况:
            1.店主已经处于过期状态 status==1 ==> 更新店主的最后期限改为从今天后的一年
            2.店主状态还未被锁定 ==> 读取店主的最后期限，然后在最后加一年*/
            String username = annualFeeOrder.getShopKeeper();
            ShopKeeper shopKeeper = shopKeeperService.selectByUsername(username);
            Date oldDateTime;
            if (shopKeeper.getStatus() == 1) {
                //店主因为没有交年费处于锁定状态，需要为店主解锁
                shopKeeperService.jieDingById(shopKeeper.getId());
                //店主已经处于欠缺年费而被锁定，所以店主的最后期限应该设置为从今天往后的一年
                //用户的 status 也需要解锁
                userService.jieSuoByUsername(shopKeeper.getUsername());
                oldDateTime = new Date();
            }else{
                //店主没有处于锁定状态，店主提前交年费，所以店主的下一次到期期限应该是从店主最后到期期限往后推一年
                oldDateTime = shopKeeper.getDeathTime();
            }
            Calendar newDeadTime = Calendar.getInstance();
            newDeadTime.setTime(oldDateTime);
            //最后期限往后推延一年
            newDeadTime.add(Calendar.YEAR, 1);
            shopKeeperService.updateDeathTimeById(newDeadTime.getTime(), shopKeeper.getId());
            //进行分润操作 ==> 给予招到新店主的相应上级相应奖励
            benefitService.shareAnnualFeeBenefit(shopKeeperService.findIdByUsername(username), annualFeeOrder.getId());
            return true;
        }
        return false;
    }

}