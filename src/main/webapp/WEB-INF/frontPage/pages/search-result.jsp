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
            <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/search-result.css">
            <script src="${pageContext.request.contextPath}/frontAssets/js/iconfont.js"></script>
            <title>搜索</title>
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
                            <!--搜索商品名称-->
                            ${shopName}
                        </span>
                    </div>
                </div>
                <!--内容区-->
                <div data-role="main" class="ui-content">
                    <!--未找到搜索商品-->
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
                                                        <div class="price">
                                                            <div class="current-price">
                                                                <span> 
                                                                    ￥<c:choose><c:when test="${vip==2}">${list.shopkeeperPrice}</c:when><c:when test="${vip==1}">${list.vipPrice}</c:when><c:when test="${vip==0}">${list.secondCost}</c:when>
                                                                    </c:choose>
                                                                </span>
                                                            </div>
                                                            <div class="past-price">
                                                                <span class="line"></span>
                                                                <span>￥${list.pastPrice}</span>
                                                            </div>
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
            </div>
            <script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.min.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.event.move.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.event.swipe.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/jquery.mobile/jquery.mobile-1.4.5.min.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/common.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/search-result.js" rel="script"></script>
            <script>
                var contextPath = "${pageContext.request.contextPath}";
                //TODO 添加搜索商品的异步加载
                var keyword = "${shopName}";
            </script>
        </body>

        </html>