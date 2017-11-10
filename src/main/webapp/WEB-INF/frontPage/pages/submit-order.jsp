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
            <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/submit-order.css">
            <script src="${pageContext.request.contextPath}/frontAssets/js/iconfont.js"></script>
            <title>提交订单</title>
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
                            我的订单
                        </span>
                    </div>
                </div>


                <div data-role="main" class="ui-content">

                    <form method="post" action="${pageContext.request.contextPath}/user/submitOrder" data-ajax="false">
                        <input type="hidden" name="direction" value="${direction}">
                        <c:if test="${direction.equals('submitOrder')}">
                            <input type="hidden" name="shopId" value="${shopId}">
                            <input type="hidden" name="type" value="${type}">
                        </c:if>
                        <fieldset class="ui-field-contain choose-address">

                            <c:if test="${userAddressList.size()<=0}">
                                <div class="no-address">您当前还没有收货地址，赶紧去<a href="${pageContext.request.contextPath}/user/addAddress?userId=${user.id}"
                                        data-ajax="false">添加</a>吧！</div>
                            </c:if>

                            <c:if test="${userAddressList.size()>0}">
                                <label for="address" class="address-name">选择配送地址：</label>
                                <select name="addressId" id="address">
                        <c:forEach items="${userAddressList}" var="list">
                            <option value="${list.id}">收货人:${list.name}，收货人电话:${list.phone}，收货地址:${list.province}${list.city}${list.area}${list.town}${list.adddetail}，邮编:${list.zipcode}</option>
                        </c:forEach>
                    </select>
                            </c:if>
                        </fieldset>

                        <%--遍历显示订单中的所有商品--%>
                            <div class="shopCartList">
                                <c:if test="${direction.equals('submitOrder')}">
                                    <input type="hidden" name="shopCartId" value="${shopCart.id}">
                                    <div class="order-info">
                                        <div class="product-info">
                                            <img src="${pageContext.request.contextPath}/${shopCart.photo}">
                                            <div class="name">${shopCart.shopName}</div>
                                            <div class="parameter">${shopCart.detail}</div>
                                            <div class="price" data-price="${shopCart.secondCost}">商品单价：￥${shopCart.secondCost}</div>
                                        </div>
                                        <div class="buy-count">
                                            <label for="count${shopCart.id}">数量：</label>
                                            <button class="minus-btn" type="button">-</button>
                                            <input type="hidden" name="count" id="count${shopCart.id}" value="${shopCart.count}">
                                            <span>${shopCart.count}</span>
                                            <button class="plus-btn" type="button">+</button>
                                        </div>
                                    </div>
                                </c:if>
                                <!--${direction}-->
                                <c:if test="${direction.equals('addToShopCart')}">
                                    <c:forEach items="${shopCartList}" var="list" varStatus="i">
                                        <input type="hidden" name="shopCartIdList[${i.index}]" value="${list.id}">
                                        <div class="order-info">
                                            <div class="product-info">
                                                <img src="${pageContext.request.contextPath}/${list.photo}">
                                                <div class="name">${list.shopName}</div>
                                                <div class="parameter">${list.detail}</div>
                                                <div class="price" data-price="${list.secondCost}">商品单价：￥${list.secondCost}</div>
                                            </div>
                                            <div class="buy-count">
                                                <label for="count">数量：</label>
                                                <button class="minus-btn" type="button">-</button>
                                                <input type="hidden" name="countList[${i.index}]" id="count" value="${list.count}">
                                                <span>${list.count}</span>
                                                <button class="plus-btn" type="button">+</button>
                                            </div>
                                        </div>

                                    </c:forEach>
                                </c:if>
                            </div>

                            <div class="remark">
                                <textarea name="liuYan" placeholder="买家留言"></textarea>
                            </div>

                            <button type="submit" data-ajax="false" class="ui-btn" id="submitOrderBtn">提交订单</button>
                    </form>

                    <div class="order-price">
                        <div class="order-price-inner">
                            <div class="postage">邮费：￥<span></span></div>
                            <div class="total-price">订单总价：￥<span></span></div>
                        </div>
                    </div>
                </div>
            </div>

            <script>
                var mianyunfei = "${yunFei.mianYunFei}";
                var yunfei = "${yunFei.yunFei}";
                console.info(window.location.href);
            </script>
            <script>
                sessionStorage.setItem('freshFlag', "1");
            </script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.min.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/jquery.mobile/jquery.mobile-1.4.5.min.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/common.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/submit-order.js" rel="script"></script>
        </body>

        </html>