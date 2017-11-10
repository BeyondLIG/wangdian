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
            <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/my-shopCart.css">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/form-validate.css">
            <script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.min.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/jquery.mobile/jquery.mobile-1.4.5.min.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/iconfont.js"></script>
            <title>购物车</title>
        </head>

        <body>
            <div data-role="page">
                <!--公共头部-->
                <div data-role="header" class="fixed-header">
                    <div class="header-title">
                        <span>
                        购物车
                        </span>
                    </div>
                </div>
                <!--出错信息提示-->
                <a href="#alertInfo" data-rel="popup" id="alertInfoBtn" data-position-to="window"></a>
                <div data-role="popup" id="alertInfo" class="ui-content" data-overlay-theme="b">
                    <div data-role="header">
                        <span>提示信息</span>
                    </div>
                    <div class="ui-field-contain">
                        <div class="alert-info"></div>
                    </div>
                    <div class="ui-grid-a">
                        <div class="ui-block-a"><a href="#" class="ui-btn ui-corner-all ui-mini" data-rel="back">取消</a></div>
                        <div class="ui-block-b"><a href="#" class="ui-btn ui-corner-all ui-mini confirmBtn" data-rel="back">确定</a></div>
                    </div>
                </div>

                <div data-role="main" class="ui-content">
                    <c:if test="${shopCartsList.size()==0}">
                        <div class="not-found">
                            <div class="icon-container">
                                <svg class="icon" aria-hidden="true">
                                    <use xlink:href="#icon-gouwuchekong"></use>
                                </svg>
                            </div>
                            <div class="not-title">
                                购物车睡着了，快去叫醒它！
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${shopCartsList.size()>0}">
                        <form method="post" action="${pageContext.request.contextPath}/user/myShopCartToSubmitOrder" data-ajax="false">
                            <%int showNum=0;%>
                                <c:forEach items="${shopCartsList}" var="shopCart" varStatus="i">
                                    <c:if test="${shopCart.isDel==0}">
                                        <%showNum++;%>
                                            <fieldset data-role="controlgroup">
                                                <label for="shopCartIdList${i.index}">
                                   <div class="shop-cart">
                                       <img src="${pageContext.request.contextPath}/${shopCart.photo}">
                                       <div>
                                           <div>${shopCart.shopName}</div>
                                           <div>${shopCart.detail}</div>
                                           <div class="productPrice">商品单价：￥<span>${shopCart.secondCost}</span></div>
                                           <div class="productNum">数量：<span>${shopCart.count}</span></div>
                                       </div>
                                   </div>
                               </label>
                                                <input type="checkbox" name="shopCartIdList" id="shopCartIdList${i.index}" value="${shopCart.id}">
                                                <div class="del-icon">
                                                    <svg class="icon" aria-hidden="true">
                                                        <use xlink:href="#icon-shanchu"></use>
                                                    </svg>
                                                </div>
                                            </fieldset>
                                    </c:if>
                                </c:forEach>

                                <%if(showNum==0){%>
                                    <div class="not-found">
                                        <div class="icon-container">
                                            <svg class="icon" aria-hidden="true">
                                                <use xlink:href="#icon-gouwuchekong"></use>
                                            </svg>
                                        </div>
                                        <span>购物车睡着了，快去叫醒它！</span>
                                    </div>
                                    <%}%>
                                        <%if(showNum>0){%>
                                            <div class="options">
                                                <input type="checkbox" name="chooseAll" id="chooseAll">
                                                <label for="chooseAll"></label>
                                                <span class="chooseAll-title">全选</span>
                                                <div class="shopCartInfo">
                                                    <div class="price">
                                                        合计：<span class="total-price">0</span>
                                                    </div>
                                                    <div class="num">
                                                        共&nbsp<span class="total-num">0</span>&nbsp件
                                                    </div>
                                                </div>
                                                <button type="submit" data-inline="true" value="结算" id="submitBtn">结算</button>
                                            </div>
                                            <%}%>
                        </form>
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
                console.log("${shopCartsList[0]}");
                var contextPath = "${pageContext.request.contextPath}";
            </script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/common.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/my-shopCart.js" rel="script"></script>
        </body>

        </html>