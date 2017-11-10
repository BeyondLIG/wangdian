<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<!DOCTYPE html>
		<html lang="en">

		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<meta name="viewport" content="width=device-width, initial-scale=1">
			<link rel="shortcut icon" href="${pageContext.request.contextPath}/frontAssets/images/logo.jpg" type="image/x-icon" />
			<link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/jquery.mobile/jquery.mobile-1.4.5.min.css">
			<link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/common.css">
			<link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/user-center.css">
			<script src="${pageContext.request.contextPath}/frontAssets/js/iconfont.js"></script>
			<title>联系卖家</title>
			<style>
				.contact-inner {
					width: 80%;
					text-align: left;
					margin: 2em auto;
				}

				.contact-inner span {
					display: block;
					margin: 1em 0;
				}
			</style>
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
                            联系我们
                        </span>
					</div>
				</div>

				<div data-role="main" class="ui-content">
					<div class="contact-inner">
						你好，有什么疑问？请联系我们！
						<c:if test="${contact!=null}">
							<span>联系方式如下</span>
						    <c:if test="${contact.weiXin!= ''}">
								<span>微信号：${contact.weiXin}</span>
							</c:if>
							<c:if test="${contact.telePhone!= ''}">
								<span>电话：${contact.telePhone}</span>
							</c:if>
							<c:if test="${contact.qq!= ''}">
								<span>QQ号：${contact.qq}</span>
							</c:if>
						</c:if>
					</div>
				</div>
			</div>
			<script>
				var contextPath = "${pageContext.request.contextPath}";
			</script>
			<script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.min.js" rel="script"></script>
			<script src="${pageContext.request.contextPath}/frontAssets/jquery.mobile/jquery.mobile-1.4.5.min.js" rel="script"></script>
			<script src="${pageContext.request.contextPath}/frontAssets/js/common.js"></script>
			<script src="${pageContext.request.contextPath}/frontAssets/js/login.js" rel="script"></script>
		</body>

		</html>