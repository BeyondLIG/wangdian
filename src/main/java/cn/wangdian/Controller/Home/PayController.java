package cn.wangdian.Controller.Home;

import cn.wangdian.Model.User;
import cn.wangdian.Service.OrdersService;
import cn.wangdian.Service.UserService;
import cn.wangdian.utils.Encode;
import cn.wangdian.utils.ExtractParamFromRequest;
import cn.wangdian.utils.alipay.util.AlipayNotify;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * PayController 是用来提供用户支付页面的
 */
@Controller
public class PayController {

    private Log logger = LogFactory.getLog(HomeIndexController.class);

    /**
     * 用于查看字符串的编码格式
     */
    private Encode encodeUtil = new Encode();

    private ExtractParamFromRequest extractParamFromRequest = new ExtractParamFromRequest();

//    private HttpServletRequest httpServletRequest;
//    private HttpServletResponse httpServletResponse;
//    private PrintWriter out;
//
//    private void OutWrite() {
//        try {
//            httpServletResponse.setContentType("text/html;charset=UTF-8");
//            out = httpServletResponse.getWriter();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 用于查询订单
     */
    @Autowired
    private OrdersService ordersService;

    /**
     * 用于查询用户
     */
    @Autowired
    private UserService userService;

    /**
     * 跳转到支付页面
     */
    @RequestMapping("/toPay")
    public String toPay(Integer id, Integer userId, Model model) {
        // id 是 Orders 的 id 属性值
        model.addAttribute("id", id);
        model.addAttribute("userId", userId);
        return "frontPage/alipay/alipayapi";
    }

//    @RequestMapping("/{shopKeeper}/toPay")
//    public String toPay(@PathVariable("shopKeeper")String shopKeeper, Integer id,Integer userId, Model model, HttpSession session, HttpServletRequest request){
//
//        User user=userService.findById(userId);
//
//        Orders orders= null;
//        try {
//            orders = ordersService.findById(id);
//
//        List<ShopCart> shopCartList=orders.getShopCartList();
//
//        if (shopCartList!=null&&shopCartList.size()!=0){
//
//
//            //商户订单号，商户网站订单系统中唯一订单号，必填
//            String out_trade_no = orders.getShopOrderId();
//
//            //付款金额，必填
////            String total_fee =String.valueOf(orders.getOrdersCost());
//            String total_fee ="0.01";
//
//            //收银台页面上，商品展示的超链接，必填
//            String show_url="";
//
//            //订单名称，必填
//            String subject = "";
//
//            //商品描述，可空
//            String body = "";
//
//              if (shopCartList.size()==1){
//                //购物车只有一件商品或者直接购买
//                ShopCart shopCart=orders.getShopCartList().get(0);
//
//                //订单名称，必填
//                subject = shopCart.getShopName()+" "+orders.getShopOrderId()+"订单";
//
//                if (request.getServerPort()==80){
//                    show_url =request.getServerName().toLowerCase()+request.getContextPath()+"/commodityDetail?id="+shopCart.getId()+"&type="+shopCart.getType();
//                }else {
//                    show_url =request.getServerName().toLowerCase()+":"+request.getServerPort()+request.getContextPath()+"/commodityDetail?id="+shopCart.getId()+"&type="+shopCart.getType();
//                }
//                logger.info("show_url----------------------------"+show_url);
//
//                //商品描述，可空
//                body = new String(shopCart.getDetail());
//              }else {
//                subject= "购物车 "+orders.getShopOrderId()+"订单";
//
//                if (request.getServerPort()==80){
//                    show_url =request.getServerName().toLowerCase()+request.getContextPath()+"/orderDetail?id="+orders.getId()+"&userId="+user.getId();
//                }else {
//                    show_url =request.getServerName().toLowerCase()+":"+request.getServerPort()+request.getContextPath()+"/orderDetail?id="+orders.getId()+"&userId="+user.getId();
//                }
//                logger.info("show_url----------------------------"+show_url);
//
//                StringBuffer stringBuffer=new StringBuffer();
//                for (ShopCart shopCart:shopCartList){
//                    stringBuffer.append(shopCart.getShopName()+",");
//                }
//                body=stringBuffer.toString();
//                body=body.substring(0,body.length()-1);
//              }
//
//              //把请求参数打包成数组
//              Map<String, String> sParaTemp = new HashMap<String, String>();
//              sParaTemp.put("service", AlipayConfig.service);
//              sParaTemp.put("partner", AlipayConfig.partner);
//              sParaTemp.put("seller_id", AlipayConfig.seller_id);
//              sParaTemp.put("_input_charset", AlipayConfig.input_charset);
//              sParaTemp.put("payment_type", AlipayConfig.payment_type);
//              sParaTemp.put("notify_url", AlipayConfig.notify_url);
//              sParaTemp.put("return_url", AlipayConfig.return_url);
//              sParaTemp.put("out_trade_no", out_trade_no);
//              sParaTemp.put("subject", subject);
//              sParaTemp.put("total_fee", total_fee);
//              sParaTemp.put("show_url", show_url);
//              sParaTemp.put("app_pay","Y");//启用此参数可唤起钱包APP支付。
//              sParaTemp.put("body", body);
//              //其他业务参数根据在线开发文档，添加参数.文档地址:https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.2Z6TSk&treeId=60&articleId=103693&docType=1
//              //如sParaTemp.put("参数名","参数值");
//
//
//              //建立请求
//              String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
//              System.out.println("建立请求"+sHtmlText);
//              OutWrite();
//              // 此处一定要out.println写出来，写出来的是支付宝的支付页面代码，所以不要讲数据返回到前端页面，将无法处理。
//              // 直接在java代码中print出就可以实现跳转。
//              out.println(sHtmlText);
//          }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        model.addAttribute("orders",orders);
//        model.addAttribute("userId",userId);
//        return "frontPage/pages/order-detail";
//    }

    /**
     * 从 HttpServletRequest 中提取参数
     * @return 把需要参数提取出来后的 Map<String, String>
     */
    private Map<String, String> extractParamsFromRequest(HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<>();
        Map requestParams = request.getParameterMap(); //将 request 所有参数加入到 Map 中
        for (Object o : requestParams.keySet()) {
            String name = (String) o; //首先把参数的键值对的 键 key 取出来
            String[] values = (String[]) requestParams.get(name); //把参数中键 key 对应的值 value 取出来
            //String[] 将数组中的内容转化为一个字符串 String 使用 , 连接
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? (valueStr + values[i]) : (valueStr + values[i] + ",");
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            String encode = encodeUtil.getEncoding(valueStr);
            logger.info("valueStr为" + valueStr);
            logger.info("valueStr编码格式为" + encode);
            //GB2312是服务器上的编码格式
//            if (!encode.equals("GB2312")) {
                // 如果字符串的编码不是 GB2312 就转化为 utf-8
            // 转化 utf-8 模块
            if (!encode.equalsIgnoreCase("GB2312")) {
                valueStr = new String(valueStr.getBytes(encode), "UTF-8");
            }
//            }
            params.put(name, valueStr);
        }
        return params;
    }

    /**
     * notify_url 支付宝是发送一个 post 请求
     * TODO /notify_url /return_url 未来支付宝账号申请下来后还需要测试一次
     */
    @RequestMapping(value = "/notify_url", method = RequestMethod.POST)
    @ResponseBody
    public String notify_url(HttpServletRequest request, Model model) throws Exception {
        /*
        这里应该添加一个判断是否是支付宝发送来的 POST 请求
         */
        logger.info("hello,notify");

        //获取支付宝POST过来反馈信息
//        Map<String, String> params = extractParamsFromRequest(request);
        Map<String, String> params = extractParamFromRequest.extractParamsFromRequest(request);

                //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号 该订单号是我们发起的支付请求的时候发送给支付宝平台的，当支付完成支付宝返回相同的订单号来标示
        String out_trade_no = request.getParameter("out_trade_no");
        String encodeOut_trade_no = encodeUtil.getEncoding(out_trade_no);
        logger.info("out_trade_no为" + out_trade_no);
        logger.info("encodeOut_trade_no编码格式为" + encodeOut_trade_no);

        //GB2312是服务器上的编码格式
//        if (!encodeOut_trade_no.equals("GB2312")) {
        if (!encodeOut_trade_no.equalsIgnoreCase("GB2312")) {
            out_trade_no = new String(out_trade_no.getBytes(encodeOut_trade_no), "UTF-8");
        }
//        }

        //支付宝交易号
        String trade_no = request.getParameter("trade_no");
        String encodeTrade_no = encodeUtil.getEncoding(trade_no);
        logger.info("trade_no为" + trade_no);
        logger.info("encodeTrade_no 编码格式为" + encodeTrade_no);

        //GB2312是服务器上的编码格式
//        if (!encodeTrade_no.equals("GB2312")) {
        if (!encodeTrade_no.equalsIgnoreCase("GB2312")) {
            trade_no = new String(trade_no.getBytes(encodeTrade_no), "UTF-8");
        }
//        }

        //交易状态
        String trade_status = request.getParameter("trade_status");
        String encodeTrade_status = encodeUtil.getEncoding(trade_status);
        logger.info("trade_status为" + trade_status);
        logger.info("encodeTrade_status 编码格式为" + encodeTrade_status);

        //GB2312是服务器上的编码格式
//        if (!encodeTrade_status.equals("GB2312")) {
        if (!encodeTrade_status.equalsIgnoreCase("GB2312")) {
            trade_status = new String(trade_status.getBytes(encodeTrade_status), "UTF-8");
        }
//        }
        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//


        //计算得出通知验证结果
        boolean verify_result = AlipayNotify.verify(params);

        if (verify_result) {//验证成功
            //////////////////////////////////////////////////////////////////////////////////////////
            //请在这里加上商户的业务逻辑程序代码

            //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
            if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                int status = ordersService.findStatusByByShopOrderId(out_trade_no);
                if (status == 0) {
                    ordersService.updateStatusByShopOrderId(1, out_trade_no);
                }
            }

            //该页面可做页面美工编辑
//            out.println("付款成功<br />");
            model.addAttribute("message", "付款成功<br />");
            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
            return "success"; // 只有在消息是在验证过后的才会返回 "success" 给支付宝，告诉支付宝我已经完成了对数据的处理
            //////////////////////////////////////////////////////////////////////////////////////////
        } else {
            //该页面可做页面美工编辑
//            out.println("交易失败，请重新付款");
            model.addAttribute("message", "验证失败");
        }
        /*
        程序执行完后必须打印输出“success”（不包含引号）。如果商户反馈给支付宝的字符不是success这7个字符，支付宝服务器会不断重发通知，直到超过24小时22分钟。
在25小时内完成6~10次通知（通知频率：5s,2m,10m,15m,1h,2h,6h,15h）
         */
//        return "frontPage/alipay/notify_url";
        return null;
    }

    /**
     * return_url 支付宝是发送一个 GET 请求
     * return_url 是通过支付宝在支付页面写了一个 js 脚本，来实现客户端 GET 方法访问
     * return_url 执行的逻辑操作与 notify_url 几乎是相同的, 但是并不能保证 notify_url 先到达还是 return_url 先到达
     * 具体区别参考：http://m.studyofnet.com/news/1135.html
     * 这里需要判断该订单是否已经是处理过的，避免 return_url 和 notify_url 重复处理
     */
    @RequestMapping(value = "/return_url", method = RequestMethod.GET)
    public String return_url(HttpServletRequest request, Model model, HttpSession session) throws Exception {
        // 这里购物只能够提供给普通用户购买？ 还没有提供借口给店主购买？
        User user = (User) session.getAttribute("ordinaryUser");
        model.addAttribute("ordinaryUser", user);
        logger.info("hello,return_url");

        //获取支付宝GET过来反馈信息 ==> 用于验证是否是支付宝发过来的消息
        Map<String, String> params = extractParamsFromRequest(request);

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号
        String out_trade_no = request.getParameter("out_trade_no");
        String encodeOut_trade_no = encodeUtil.getEncoding(out_trade_no);
        logger.info("out_trade_no为" + out_trade_no);
        logger.info("encodeOut_trade_no编码格式为" + encodeOut_trade_no);

        //GB2312是服务器上的编码格式
//        if (!encodeOut_trade_no.equals("GB2312")) {
        if (!encodeOut_trade_no.equalsIgnoreCase("GB2312")) {
            out_trade_no = new String(out_trade_no.getBytes(encodeOut_trade_no), "UTF-8");
        }
//        }

        //支付宝交易号
        String trade_no = request.getParameter("trade_no");
        String encodeTrade_no = encodeUtil.getEncoding(trade_no);
        logger.info("trade_no为" + trade_no);
        logger.info("encodeTrade_no 编码格式为" + encodeTrade_no);

        //GB2312是服务器上的编码格式
//        if (!encodeTrade_no.equals("GB2312")) {
        if (!encodeTrade_no.equalsIgnoreCase("GB2312")) {
            trade_no = new String(trade_no.getBytes(encodeTrade_no), "UTF-8");
        }
//        }

        //交易状态
        String trade_status = request.getParameter("trade_status");
        String encodeTrade_status = encodeUtil.getEncoding(trade_status);
        logger.info("trade_status为" + trade_status);
        logger.info("encodeTrade_status 编码格式为" + encodeTrade_status);

        //GB2312是服务器上的编码格式
//        if (!encodeTrade_status.equals("GB2312")) {
        if (!encodeTrade_status.equalsIgnoreCase("GB2312")) {
            trade_status = new String(trade_status.getBytes(encodeTrade_status), "UTF-8");
        }
//        }
        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//


        //计算得出通知验证结果
        boolean verify_result = AlipayNotify.verify(params);

        if (verify_result) {//验证成功
            //////////////////////////////////////////////////////////////////////////////////////////
            //请在这里加上商户的业务逻辑程序代码

            //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
            if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                int status = ordersService.findStatusByByShopOrderId(out_trade_no);
                if (status == 0) {
                    ordersService.updateStatusByShopOrderId(1, out_trade_no);
                }
            }

            //该页面可做页面美工编辑
//            out.println("付款成功<br />");
            model.addAttribute("message", "付款成功<br />");
            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
            return "frontPage/alipay/return_url";
            //////////////////////////////////////////////////////////////////////////////////////////
        } else {
            //该页面可做页面美工编辑
//            out.println("交易失败，请重新付款");
            model.addAttribute("message", "验证失败");
        }
        return null;
    }
}
