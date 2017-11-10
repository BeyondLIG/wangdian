<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/frontAssets/images/logo.jpg" type="image/x-icon" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/jquery.mobile/jquery.mobile-1.4.5.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/common.css">
    <script src="${pageContext.request.contextPath}/frontAssets/js/iconfont.js"></script>
    <title>限时秒杀</title>
    <style>
        .fixed-header {
            height: 50px;
        }

        .ui-content {
            top: 0.5em;
        }
    </style>
</head>

<body>
<div data-role="page">
    <div data-role="main" class="ui-content">
        <c:if test="${shunShopList.size()==0}">
            <!--没有商品-->
            <div class="not-found">
                <div class="not-icon">
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-meiyousousuodaojieguo"></use>
                    </svg>
                </div>
                <div class="not-title">
                    未找到商品
                </div>
            </div>
        </c:if>
        <c:if test="${shunShopList.size()>0}">
            <!--有商品-->
            <div class="product-items">
                <ul class="items-inner">
                    <c:forEach items="${shunShopList}" var="list">
                        <li>
                            <a href="${pageContext.request.contextPath}/commodityDetail?id=${list.id}&type=shunShop" data-ajax="false" class="link">
                                <div class="product">
                                    <div class="product-img">
                                        <img src="${pageContext.request.contextPath}/${list.firstPhoto}" alt="商品图片">
                                    </div>
                                    <div class="product-desc">
                                        <div class="name">
                                                ${list.name}
                                        </div>
                                        <div class="product-price">
                                            <div class="current-price">
                                                <div class="price">
                                                    <span>￥${list.thirdCost}</span>
                                                </div>
                                            </div>
                                            <div class="past-price">
                                                <span class="line"></span>
                                                <span>￥${list.secondCost}</span>
                                            </div>
                                        </div>
                                        <div class="down-count">
                                            <div class="down-count-title">距活动结束还剩：</div>
                                            <ul class="timer" data-endTime="${list.endTimeToString()}">
                                                <li><span class="days">00</span></li>
                                                <li class="seperator">天</li>
                                                <li><span class="hours">00</span></li>
                                                <li class="seperator">时</li>
                                                <li><span class="minutes">00</span></li>
                                                <li class="seperator">分</li>
                                                <li><span class="seconds">00</span></li>
                                                <li class="seperator">秒</li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
    </div>

    <!--公共底部-->
    <div data-role="footer" class="ui-footer-fixed">
        <div data-role="navbar">
            <ul>
                <li>
                    <a data-ajax="false" href="${pageContext.request.contextPath}/index" class="ui-btn" data-inline="true">
                        <div class="icon-container">
                            <svg class="icon" aria-hidden="true">
                                <use xlink:href="#icon-icontitlehuishouye"></use>
                            </svg>
                        </div>
                        <span class="title">首页</span>
                    </a>
                </li>
                <li>
                    <a data-ajax="false" href="${pageContext.request.contextPath}/flashSale" class="ui-btn" data-inline="true">
                        <div class="icon-container">
                            <svg class="icon" aria-hidden="true">
                                <use xlink:href="#icon-miaosha"></use>
                            </svg>
                        </div>
                        <span class="title">限时秒杀</span>
                    </a>
                </li>
                <li>
                    <a data-ajax="false" href="${pageContext.request.contextPath}/user/myShopCart" class="ui-btn" data-inline="true">
                        <div class="icon-container">
                            <svg class="icon" aria-hidden="true">
                                <use xlink:href="#icon-gouwuche"></use>
                            </svg>
                        </div>
                        <span class="title">购物车</span>
                    </a>
                </li>
                <li>
                    <a data-ajax="false" href="${pageContext.request.contextPath}/user/userCenter" class="ui-btn" data-inline="true">
                        <div class="icon-container">
                            <svg class="icon" aria-hidden="true">
                                <use xlink:href="#icon-gerenzhongxin"></use>
                            </svg>
                        </div>
                        <span class="title">个人中心</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>

</div>
<script>
    var contextPath = "${pageContext.request.contextPath}";
</script>

<script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.min.js" rel="script"></script>
<script src="${pageContext.request.contextPath}/frontAssets/jquery.mobile/jquery.mobile-1.4.5.min.js" rel="script"></script>
<script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.downCount.js" rel="script"></script>
<script src="${pageContext.request.contextPath}/frontAssets/js/common.js" rel="script"></script>
<script src="${pageContext.request.contextPath}/frontAssets/js/flash-sale.js" rel="script"></script>
</body>

</html>