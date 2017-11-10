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
            <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/home-page.css">
            <script src="${pageContext.request.contextPath}/frontAssets/js/iconfont.js"></script>
            <title>店小二手机版</title>
        </head>

        <body>
            <div data-role="page">
                <!--公共头部-->
                <div data-role="header" class="fixed-header" style="height:88px;">
                    <div id="logo">店小二</div>
                    <div class="search-box">
                        <svg class="icon" aria-hidden="true">
                            <use xlink:href="#icon-sou"></use>
                        </svg>
                        <input type="text" placeholder="搜索商品" id="search-input">
                    </div>
                    <div class="classify-bar">
                        <div class="move-bar">
                            <div class="classify-items">
                                <ul>
                                    <li class="classify">
                                        <span class="classify-title">推荐</span>
                                        <span class="choose-line" id="first"></span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                <!--内容区-->
                <div data-role="main" class="ui-content">
                    <c:if test="${shopList.size()==0}">
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
                    <c:if test="${shopList.size()>0}">
                        <div class="product-items">
                            <ul>
                                <c:forEach items="${shopList}" var="list">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/commodityDetail?id=${list.id}&type=shop" data-ajax="false">
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
                                                                <span>
                                                                        ￥<c:choose><c:when test="${vip==2}">${list.shopkeeperPrice}</c:when><c:when test="${vip==1}">${list.vipPrice}</c:when><c:when test="${vip==0}">${list.secondCost}</c:when>
                                                                        </c:choose>
                                                                    </span>
                                                            </div>
                                                        </div>
                                                        <div class="past-price">
                                                            <span class="line"></span>
                                                            <span>￥${list.pastPrice}</span>
                                                        </div>
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
                            <!--此处需要修改-->
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
                var data = [];
                //JSP格式
                <c:forEach items = "${shopListAll}" var = "list" varStatus = "i" >data[${i.index}] = "${list}"; </c:forEach>
            </script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.min.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.event.move.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.event.swipe.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/jquery.mobile/jquery.mobile-1.4.5.min.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/common.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/home-page.js" rel="script"></script>

        </body>

        </html>