<%
/* *
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
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="cn.wangdian.utils.alipay.config.*"%>
<%@ page import="cn.wangdian.utils.alipay.util.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="cn.wangdian.Model.User" %>
<%@ page import="cn.wangdian.Model.Orders" %>
<%@ page import="cn.wangdian.Model.ShopCart" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="cn.wangdian.Service.OrdersService" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>支付宝手机网站支付接口</title>
	</head>
    <%

//		User user=(User)session.getAttribute("ordinaryUser");

		Orders orders= null;
		try {
			int id=Integer.parseInt(request.getAttribute("id").toString());
			int userId=Integer.parseInt(request.getAttribute("userId").toString());

			ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
			OrdersService ordersService=(OrdersService) ac.getBean("ordersService");
			orders = ordersService.findById(id);

			List<ShopCart> shopCartList=orders.getShopCartList();

			if (shopCartList!=null&&shopCartList.size()!=0){


				//商户订单号，商户网站订单系统中唯一订单号，必填
				String out_trade_no = orders.getShopOrderId();

				//付款金额，必填
            	String total_fee =String.valueOf(orders.getOrdersCost());
//				String total_fee ="0.01";

				//收银台页面上，商品展示的超链接，必填
				String show_url="";

				//订单名称，必填
				String subject = "";

				//商品描述，可空
				String body = "";

				if (shopCartList.size()==1){
					//购物车只有一件商品或者直接购买
					ShopCart shopCart=orders.getShopCartList().get(0);

					//订单名称，必填
					subject = shopCart.getShopName()+" "+orders.getShopOrderId()+"订单";
					//如果是 80 端口默认不显示，否则显示非 80 端口，提供更加准确的信息
					if (request.getServerPort()==80){
						show_url =request.getServerName().toLowerCase()+request.getContextPath()+"/commodityDetail?id="+shopCart.getId()+"&type="+shopCart.getType();
					}else {
						show_url =request.getServerName().toLowerCase()+":"+request.getServerPort()+request.getContextPath()+"/commodityDetail?id="+shopCart.getId()+"&type="+shopCart.getType();
					}
					System.out.println("show_url----------------------------"+show_url);

					//商品描述，可空
					body = new String(shopCart.getDetail());
				}else {
					subject= "购物车 "+orders.getShopOrderId()+"订单";

					if (request.getServerPort()==80){
						show_url =request.getServerName().toLowerCase()+request.getContextPath()+"/orderDetail?id="+orders.getId()+"&userId="+userId;
					}else {
						show_url =request.getServerName().toLowerCase()+":"+request.getServerPort()+request.getContextPath()+"/orderDetail?id="+orders.getId()+"&userId="+userId;
					}
					System.out.println("show_url----------------------------"+show_url);

					StringBuffer stringBuffer=new StringBuffer();
					for (ShopCart shopCart:shopCartList){
						stringBuffer.append(shopCart.getShopName()+",");
					}
					body=stringBuffer.toString();
					body=body.substring(0,body.length()-1);
				}

				//把请求参数打包成数组
				Map<String, String> sParaTemp = new HashMap<String, String>();
				sParaTemp.put("service", AlipayConfig.service);
				sParaTemp.put("partner", AlipayConfig.partner);
				sParaTemp.put("seller_id", AlipayConfig.seller_id);
				sParaTemp.put("_input_charset", AlipayConfig.input_charset);
				sParaTemp.put("payment_type", AlipayConfig.payment_type);
				sParaTemp.put("notify_url", AlipayConfig.notify_url);
				sParaTemp.put("return_url", AlipayConfig.return_url);
				sParaTemp.put("out_trade_no", out_trade_no);
				sParaTemp.put("subject", subject);
				sParaTemp.put("total_fee", total_fee);
				sParaTemp.put("show_url", show_url);
				sParaTemp.put("app_pay","Y");//启用此参数可唤起钱包APP支付。
                //本地可以正常执行，如果部署在服务器中 subject 和 body 中文不一样，可能出现 ILLEGAL_SIGN
//				sParaTemp.put("body", subject);
				//其他业务参数根据在线开发文档，添加参数.文档地址:https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.2Z6TSk&treeId=60&articleId=103693&docType=1
				//如sParaTemp.put("参数名","参数值");

				//建立请求
				String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
				System.out.println("建立请求"+sHtmlText);

				// 此处一定要out.println写出来，写出来的是支付宝的支付页面代码，所以不要讲数据返回到前端页面，将无法处理。
				// 直接在java代码中print出就可以实现跳转。
				out.println(sHtmlText);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    %>
	<body>
	</body>
</html>
