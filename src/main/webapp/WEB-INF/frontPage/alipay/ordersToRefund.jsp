<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/frontAssets/images/logo.jpg" type="image/x-icon"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/jquery.mobile/jquery.mobile-1.4.5.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/common.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/user-center.css">
	<script src="${pageContext.request.contextPath}/frontAssets/js/iconfont.js"></script>
	<title>申请退款</title>
</head>
<body>
<div data-role="page">

	<!--公共头部-->
	<div data-role="header" class="fixed-header">
		<div class="icon-container">
			<svg class="icon" aria-hidden="true">
				<use xlink:href="#icon-fanhui"></use>
			</svg>
		</div>
		<div class="header-title">
			<span>
				申请退款
			</span>
		</div>
	</div>


	<div data-role="main" class="ui-content">
		<div style="padding: 5px 10px">
			<p>该订单于${orders.shopOrderTimeToString()}完成下单，订单号为${orders.shopOrderId}，正在申请退款。<br><br>退款期间请<span style="color: red">主动联系商家</span>完成退款，你也可以<span style="color: red">撤销退款申请</span>继续等待卖家发货！</p>
			<c:if test="${contact!=null}">
				<p>商家的联系方式为</p>
				<p>微信号：${contact.weiXin}</p>
				<p>QQ号：${contact.qq}</p>
				<p>电话：${contact.telePhone}</p>
			</c:if>
		</div>
	</div>
</div>

<script>
	var contextPath="${pageContext.request.contextPath}";
</script>


<script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.min.js" rel="script"></script>
<script src="${pageContext.request.contextPath}/frontAssets/jquery.mobile/jquery.mobile-1.4.5.min.js" rel="script"></script>
<script src="${pageContext.request.contextPath}/frontAssets/js/login.js" rel="script"></script>
<script src="${pageContext.request.contextPath}/frontAssets/js/common.js" rel="script"></script>
</body>
</html>
