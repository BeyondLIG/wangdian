<%@ page import="cn.wangdian.Model.AnnualFeeOrder" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="cn.wangdian.utils.alipay.config.AlipayConfig" %>
<%@ page import="cn.wangdian.utils.alipay.util.AlipaySubmit" %><%--/* *
 *功能：手机网站支付接口接入页
 *版本：3.4
 *修改日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 *************************注意*****************
 *如果您在接口集成过程中遇到问题，可以按照下面的途径来解决
 *1、开发文档中心（https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.2Z6TSk&treeId=60&articleId=103693&docType=1）
 *2、商户帮助中心（https://cshall.alipay.com/enterprise/help_detail.htm?help_id=473888）
 *3、支持中心（https://support.open.alipay.com/alipay/support/index.htm）
 *如果不想使用扩展功能请把扩展功能参数赋空值。
 **********************************************
 */--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>支付宝手机网站支付接口</title>
</head>
<body>
<%
    //提取添加进 Model 中的属性
    String username = (String) request.getAttribute("username");
    String telephone = (String) request.getAttribute("telephone");
    AnnualFeeOrder annualFeeOrder = (AnnualFeeOrder) request.getAttribute("annualFeeOrder");

    //商户订单号，商户网站订单系统中唯一订单号，必填
    String out_trade_no = annualFeeOrder.getOrderId();

    //付款金额，必填
    String total_fee = String.valueOf(annualFeeOrder.getFee());

    //收银台页面上，商品展示的超链接，必填
//    String show_url = "http://www.dianxiaoer365.com/annualFee/show_url?username=" + username + "&telephone=" + telephone + "&fee=" + total_fee;
    String show_url = request.getServerName().toLowerCase()+":"+request.getServerPort()+request.getContextPath() + "/annualFee/show_url?username=" + username + "&telephone=" + telephone + "&fee=" + annualFeeOrder.getFee();

    //订单名称，必填
    String subject = "店主支付年费";

    //商品描述，可空
    String body = "店主:" + username + "支付年费:" + total_fee+"元";

    //把请求参数打包成数组
    Map<String, String> sParaTemp = new HashMap<>();
    sParaTemp.put("service", AlipayConfig.service);
    sParaTemp.put("partner", AlipayConfig.partner);
    sParaTemp.put("seller_id", AlipayConfig.seller_id);
    sParaTemp.put("_input_charset", AlipayConfig.input_charset);
    sParaTemp.put("payment_type", AlipayConfig.payment_type);
    sParaTemp.put("notify_url", AlipayConfig.annualfee_notify_url);
    sParaTemp.put("return_url", AlipayConfig.annualfee_return_url);
    sParaTemp.put("out_trade_no", out_trade_no);
    sParaTemp.put("subject", subject);
    sParaTemp.put("total_fee", total_fee);
//    sParaTemp.put("show_url", show_url);
    sParaTemp.put("app_pay", "Y");
    //本地可以正常执行，如果部署在服务器中 subject 和 body 中文不一样，可能出现 ILLEGAL_SIGN
//    sParaTemp.put("body", subject);

    //建立请求
    String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
    System.out.println("建立请求"+sHtmlText);

    //在这里直接打印出来就可以实现支付宝页面的跳转
    out.println(sHtmlText);
%>
</body>
</html>