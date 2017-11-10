<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/jquery.mobile/jquery.mobile-1.4.5.min.css">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/common.css">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/my-order.css">
            <script src="${pageContext.request.contextPath}/frontAssets/js/iconfont.js"></script>
            <title>待收货 </title>
        </head>

        <body>
            <div data-role="page">
                <!--公共头部-->
                <div data-role="header" class="fixed-header">
                    <!--
                    <a data-ajax="false" href="${pageContext.request.contextPath}/userCenter" class="">
                    </a>
                    -->
                    <div class="icon-container">
                        <svg class="icon" aria-hidden="true">
                            <use xlink:href="#icon-fanhui"></use>
                        </svg>
                    </div>

                    <div class="header-title">
                        <span>
                            待收货
                        </span>
                    </div>
                </div>
                <div data-role="main" class="ui-content">

                    <c:if test="${ordersList.size()==0}">
                        <div class="not-found">
                            <img src="${pageContext.request.contextPath}/frontAssets/images/no-order.png" style="width: 100%">
                        </div>
                    </c:if>
                    <c:if test="${ordersList.size()>0}">
                        <div class="my-order">
                            <div class="orders">
                                <c:forEach items="${ordersList}" var="list">
                                    <a class="ui-grid-solo" href="${pageContext.request.contextPath}/orderDetail?id=${list.id}&userId=${userId}" data-ajax="false">
                                        <div class="ui-block-a">
                                            <div class="order-status">订单状态：<span>
                                <c:if test="${list.status==0}">订单已提交</c:if>
                                <c:if test="${list.status==1}">待发货</c:if>
                                <c:if test="${list.status==2}">运输中</c:if>
                                <c:if test="${list.status==3}">已确认收货</c:if>
                                <c:if test="${list.status==4}">申请退款中</c:if>
                                <c:if test="${list.status==5}">已退款</c:if>
                                <c:if test="${list.status==6}">已取消订单</c:if></span>
                                            </div>
                                            <%--<div class="order-num">订单号：${list.shopOrderId}</div>--%>
                                                <div class="order-time">下订时间：${list.shopOrderTimeToString()}</div>
                                                <div class="order-items">
                                                    <c:forEach items="${list.shopCartList}" var="shopCart">
                                                        <div class="order-info">
                                                            <img src="${pageContext.request.contextPath}/${shopCart.photo}">
                                                            <div>
                                                                <div class="name">${shopCart.shopName}</div>
                                                                <div class="parameter">${shopCart.detail} 、数量：${shopCart.count} 、商品单价：${shopCart.secondCost}
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <%--<div class="price"></div>共${shopCart.count}件商品--%>
                                                    </c:forEach>
                                                </div>
                                                <div class="total-price">订单总价：<span>${list.ordersCost}</span></div>
                                        </div>
                                    </a>
                                </c:forEach>

                            </div>
                    </c:if>

                    </div>
                </div>
            </div>
            <script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.min.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/jquery.mobile/jquery.mobile-1.4.5.min.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/common.js"></script>
        </body>

        </html>