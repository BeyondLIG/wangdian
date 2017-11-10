<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/frontAssets/images/logo.jpg"
          type="image/x-icon"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/frontAssets/jquery.mobile/jquery.mobile-1.4.5.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/unslider/css/unslider.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/unslider/css/unslider-dots.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/commodity-detail.css">
    <script src="${pageContext.request.contextPath}/frontAssets/js/iconfont.js"></script>
    <title>${shop.name}</title>
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
                            商品详情
                        </span>
        </div>
    </div>
    <div data-role="main" class="ui-content">
        <!--商品轮播图-->
        <div class="my-slider">
            <ul>
                <c:forEach items="${shopShowsList}" var="list">
                    <li>
                        <a><img src="${pageContext.request.contextPath}/${list.urlPath}"></a>
                    </li>
                </c:forEach>
            </ul>
        </div>

        <div class="commodity-info">
            <div class="commodity-inner">
                <div class="commodity-name">
                    <span>${shop.name}</span>
                </div>
                <div class="price">
                    <div class="current-price">
                                            <span>
                                            <c:if test="${type.equals('shop')}">
                                                ￥<c:choose><c:when
                                                    test="${vip==2}">${shop.shopkeeperPrice}</c:when><c:when
                                                    test="${vip==1}">${shop.vipPrice}</c:when><c:when
                                                    test="${vip==0}">${shop.secondCost}</c:when>
                                            </c:choose>
                                            </c:if>
                                            <c:if test="${type.equals('shunShop')}">
                                                ￥${shop.thirdCost}
                                            </c:if>
                                            </span>
                    </div>
                    <div class="past-price">
                        <span class="line"></span>
                        <span>
                                                <c:if test="${type.equals('shop')}">
                                                    ￥${shop.pastPrice}
                                                </c:if>
                                                <c:if test="${type.equals('shunShop')}">
                                                    ￥${shop.secondCost}
                                                </c:if>
                                                </span>
                    </div>
                </div>
                <div class="shopDesc"><span>${shop.shopDescribe}</span></div>
            </div>

        </div>

        <c:if test="${type.equals('shop')}">
            <div class="user-options">
                <a data-ajax="false"
                   href="${pageContext.request.contextPath}/chooseParam?shopId=${shop.id}&type=shop&direction=addToShopCart"
                   class="ui-btn" id="cartBtn">加入购物车</a>
                <a data-ajax="false"
                   href="${pageContext.request.contextPath}/chooseParam?shopId=${shop.id}&type=shop&direction=submitOrder"
                   class="ui-btn" id="buyBtn">立即购买</a>
            </div>
        </c:if>
        <c:if test="${type.equals('shunShop')}">
            <div class="user-options">
                <a data-ajax="false"
                   href="${pageContext.request.contextPath}/chooseParam?shopId=${shop.id}&type=shunShop&direction=addToShopCart"
                   class="ui-btn" id="cartBtn">加入购物车</a>
                <a data-ajax="false"
                   href="${pageContext.request.contextPath}/chooseParam?shopId=${shop.id}&type=shunShop&direction=submitOrder"
                   class="ui-btn" id="buyBtn">立即购买</a>
            </div>
            <div class="countdown-outer">
                <div class="countdown">
                    <p>距活动结束还剩：</p>
                    <ul class="timer" data-endTime="${shop.endTimeToString()}">
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
        </c:if>

        <!--图文详情-->
        <div class="pic-detail">
            <div class="title">图文详情</div>
            <c:forEach items="${shopPhotosList}" var="list">
                <img src="${pageContext.request.contextPath}/${list.urlPath}">
            </c:forEach>
        </div>

    </div>
</div>

<script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.min.js" rel="script"></script>
<script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.event.move.js" rel="script"></script>
<script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.event.swipe.js" rel="script"></script>
<script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.downCount.js" rel="script"></script>
<script src="${pageContext.request.contextPath}/frontAssets/unslider/js/unslider-min.js" rel="script"></script>
<script src="${pageContext.request.contextPath}/frontAssets/jquery.mobile/jquery.mobile-1.4.5.min.js"
        rel="script"></script>
<script src="${pageContext.request.contextPath}/frontAssets/js/common.js" rel="script"></script>
<script src="${pageContext.request.contextPath}/frontAssets/js/commodity-detail.js" rel="script"></script>

</body>

</html>