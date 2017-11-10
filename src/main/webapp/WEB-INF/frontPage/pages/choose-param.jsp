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
            <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/choose-param.css">
            <script src="${pageContext.request.contextPath}/frontAssets/js/iconfont.js"></script>
            <title>参数选择</title>
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
                            参数选择
                        </span>
                    </div>
                </div>
                <div data-role="main" class="ui-content">
                    <div class="chosen-info">
                        <div class="name">
                            商品名称：<span>${shop.name}
                                </span>
                        </div>
                        <div class="price">商品单价：<span> <c:if test="${type.equals('shop')}">
                                               ￥<c:choose><c:when test="${vip==2}">${shop.shopkeeperPrice}</c:when><c:when test="${vip==1}">${shop.vipPrice}</c:when><c:when test="${vip==0}">${shop.secondCost}</c:when>
                                               </c:choose>
                                            </c:if>
                                            <c:if test="${type.equals('shunShop')}">
                                                ￥${shop.thirdCost}
                                            </c:if></span></div>
                        <div class="parameter">
                            <span>已选参数：</span>
                            <div class="chosen-parameter">
                                <!--普通商品-->
                                <c:if test="${type.equals('shop')}">
                                    <c:forEach items="${shopAttributesList}" var="list">
                                        <c:if test="${list.shopAttributesValueList.size()==1}">
                                            <span data-type="${list.name}">${list.name}：${list.shopAttributesValueList.get(0).attributesValue}</span>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <!--秒杀商品-->
                                <c:if test="${type.equals('shunShop')}">
                                    <c:forEach items="${shunShopAttributesList}" var="list">
                                        <c:if test="${list.shunShopAttributesValueList.size()==1}">
                                            <span data-type="${list.name}">${list.name}：${list.shunShopAttributesValueList.get(0).attributesValue}</span>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </div>
                        </div>
                    </div>

                    <form method="post" action="${pageContext.request.contextPath}/submitParam" data-ajax="false">
                        <c:if test="${type.equals('shop')}"> <input type="hidden" name="type" value="shop"></c:if>
                        <c:if test="${type.equals('shunShop')}"> <input type="hidden" name="type" value="shunShop"></c:if>
                        <input type="hidden" name="shopId" value="${shop.id}">
                        <input type="hidden" name="shopDetail" value="">
                        <input type="hidden" name="direction" value="${direction}">

                        <c:if test="${type.equals('shop')}">
                            <c:forEach items="${shopAttributesList}" var="list">
                                <fieldset data-role="controlgroup">
                                    <legend>${list.name}:</legend>
                                    <c:if test="${list.shopAttributesValueList.size()==1}">
                                        <c:forEach items="${list.shopAttributesValueList}" var="value">
                                            <label for="${value.id}">${value.attributesValue}</label>
                                            <input type="radio" name="${list.name}" id="${value.id}" value="${value.attributesValue}" checked="checked">
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${list.shopAttributesValueList.size()>1}">
                                        <c:forEach items="${list.shopAttributesValueList}" var="value">
                                            <label for="${value.id}">${value.attributesValue}</label>
                                            <input type="radio" name="${list.name}" id="${value.id}" value="${value.attributesValue}">
                                        </c:forEach>
                                    </c:if>
                                </fieldset>
                            </c:forEach>
                        </c:if>
                        <c:if test="${type.equals('shunShop')}">
                            <c:forEach items="${shunShopAttributesList}" var="list">
                                <fieldset data-role="controlgroup">
                                    <legend>${list.name}:</legend>
                                    <c:if test="${list.shunShopAttributesValueList.size()==1}">
                                        <c:forEach items="${list.shunShopAttributesValueList}" var="value">
                                            <label for="${value.id}">${value.attributesValue}</label>
                                            <input type="radio" name="${list.name}" id="${value.id}" value="${value.attributesValue}" checked="checked">
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${list.shunShopAttributesValueList.size()>1}">
                                        <c:forEach items="${list.shunShopAttributesValueList}" var="value">
                                            <label for="${value.id}">${value.attributesValue}</label>
                                            <input type="radio" name="${list.name}" id="${value.id}" value="${value.attributesValue}">
                                        </c:forEach>
                                    </c:if>
                                </fieldset>
                            </c:forEach>
                        </c:if>

                        <div class="buy-count">
                            <label for="count">数量：</label>
                            <button id="minusBtn" type="button">-</button>
                            <input type="hidden" name="count" id="count" value="1">
                            <span>1</span>
                            <button id="plusBtn" type="button">+</button>
                        </div>
                        <button type="submit" data-ajax="false" class="ui-btn" id="confirmBtn">确认</button>
                    </form>
                </div>
            </div>


            <script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.min.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/jquery.mobile/jquery.mobile-1.4.5.min.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/common.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/choose-param.js" rel="script"></script>
        </body>

        </html>