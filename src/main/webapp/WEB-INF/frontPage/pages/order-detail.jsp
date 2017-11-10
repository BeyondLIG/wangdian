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
            <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/order-detail.css">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/form-validate.css">
            <script src="${pageContext.request.contextPath}/frontAssets/js/iconfont.js"></script>
            <title>订单详情</title>
        </head>

        <body>
            <div data-role="page">

                <!--公共头部-->
                <div data-role="header" class="fixed-header">
                    <!--
        <c:if test="${shopId!=null}">
            <a class="ui-btn" data-ajax="false" href="${pageContext.request.contextPath}/commodityDetail?id=${shopId}&type=${type}">返回</a>
        </c:if>
        <c:if test="${shopId==null}">
            <a class="ui-btn" data-ajax="false" href="${pageContext.request.contextPath}/myShopCart">购物车</a>
        </c:if>

        <h1>订单详情</h1>
        <a data-ajax="false" href="${pageContext.request.contextPath}/myOrder?userId=${ordinaryUser.id}" class="ui-btn">订单列表</a>
        -->
                    <div class="icon-container">
                        <svg class="icon" aria-hidden="true">
                            <use xlink:href="#icon-fanhui"></use>
                        </svg>
                    </div>
                    <div class="header-title">
                        <span>
                           订单详情
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
                        <div class="ui-block-b"><a href="#" class="ui-btn ui-corner-all ui-mini confirmBtn" data-rel="back" style="background-color: #FF463C;color: #fff">确定</a></div>
                    </div>
                </div>

                <div data-role="main" class="ui-content">
                    <!--订单详情-->
                    <div class="order-detail">
                        <div class="order-status">订单状态：
                            <c:if test="${orders.status==0}">订单已提交</c:if>
                            <c:if test="${orders.status==1}">商品待发货</c:if>
                            <c:if test="${orders.status==2}">商品运输中</c:if>
                            <c:if test="${orders.status==3}">已确认收货</c:if>
                            <c:if test="${orders.status==4}">申请退款中</c:if>
                            <c:if test="${orders.status==5}">已完成退款</c:if>
                            <c:if test="${orders.status==6}">已取消订单</c:if>
                        </div>

                        <c:if test="${orders.kuaiDiDanHao!=null}">
                            <div class="delivery-num">快递单号：<span>${orders.kuaiDiDanHao}</span></div>
                        </c:if>
                        <div class="order-address">
                            <div class="name-phone">
                                <div class="order-name">收货人：<span>${orders.receiver}</span></div>
                                <div class="order-phone">联系电话：${orders.receiverPhone}</div>
                            </div>
                            <div class="address-info">
                                收货地址：<span>${orders.address}</span>
                            </div>
                        </div>

                        <c:forEach items="${orders.shopCartList}" var="list">
                            <div class="order-info">
                                <img src="${pageContext.request.contextPath}/${list.photo}">
                                <div class="order-desc">
                                    <div class="name">${list.shopName}</div>
                                    <div class="parameter">
                                        <div class="product-detail">
                                            商品规格：${list.detail}
                                        </div>
                                        <div class="order-num">
                                            <span>数量：${list.count}</span>
                                        </div>
                                        <div class="order-price">
                                            <span>商品单价：￥${list.secondCost}</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        <div class="order-more">
                            <div>
                                订单号：<span>${orders.shopOrderId}</span>
                            </div>
                            <div>
                                下单时间：<span>${orders.shopOrderTimeToString()}</span>
                            </div>
                            <div class="text-area">
                                买家留言：
                                <span>${orders.liuYan}</span>
                            </div>
                        </div>

                        <div class="price">
                            <div class="price-inner">
                                <div class="post-fee">
                                    运费：<span>${orders.yunFei}</span>
                                </div>
                                <div class="total-fee">
                                    订单总价：<span>${orders.ordersCost}</span>
                                </div>
                            </div>
                        </div>
                        <c:if test="${orders.status==0}">
                            <img src="${pageContext.request.contextPath}/frontAssets/images/alipay.png" style="width: 100%;">
                        </c:if>
                    </div>
                </div>

                <div class="order-options">
                    <%--已提交订单，可以付款和取消订单--%>
                        <c:if test="${orders.status==0}">
                            <a data-ajax="false" href="${pageContext.request.contextPath}/toPay?id=${orders.id}&userId=${userId}"><button data-role="none" id="payBtn">付款</button></a>
                            <a data-ajax="false" href="${pageContext.request.contextPath}/user/ordersCancel?shopOrderId=${orders.shopOrderId}&userId=${userId}"><button data-role="none" id="delOrderBtn">取消订单</button></a>
                        </c:if>
                        <%--已取消订单，可以删除订单--%>
                            <c:if test="${orders.status==6}">
                                <a data-ajax="false" href="${pageContext.request.contextPath}/user/ordersDelete?id=${orders.id}&userId=${userId}"><button data-role="none" id="delOrderBtn">删除</button></a>
                            </c:if>
                            <%--已付款，卖家尚未发货，可以申请退款--%>
                                <c:if test="${orders.status==1}">
                                    <a data-ajax="false" href="${pageContext.request.contextPath}/user/ordersToRefund?shopOrderId=${orders.shopOrderId}&userId=${userId}"><button data-role="none" id="refundsBtn">申请退款</button></a>
                                    <a data-ajax="false" href="${pageContext.request.contextPath}/user/ordersContact"><button data-role="none" id="contactBtn">联系卖家</button></a>
                                </c:if>
                                <%--已发货，买家可以确认收货--%>
                                    <c:if test="${orders.status==2}">
                                        <a data-ajax="false" href="${pageContext.request.contextPath}/user/ordersToConfirmReceipt?shopOrderId=${orders.shopOrderId}&userId=${userId}"><button data-role="none" id="confirmBtn">确认收货</button></a>
                                        <a data-ajax="false" href="${pageContext.request.contextPath}/user/ordersContact"><button data-role="none" id="contactBtn">联系卖家</button></a>
                                    </c:if>
                                    <%--已确认收货，交易完成，可以删除订单--%>
                                        <c:if test="${orders.status==3}">
                                            <a data-ajax="false" href="${pageContext.request.contextPath}/user/ordersDelete?id=${orders.id}&userId=${userId}"><button data-role="none" id="delOrderBtn">删除订单</button></a>
                                        </c:if>
                                        <%--申请退款中，可以撤销申请--%>
                                            <c:if test="${orders.status==4}">
                                                <a data-ajax="false" href="${pageContext.request.contextPath}/user/ordersToWithdraw?shopOrderId=${orders.shopOrderId}&userId=${userId}"><button data-role="none" id="recallBtn">撤销申请</button></a>
                                                <a data-ajax="false" href="${pageContext.request.contextPath}/user/ordersContact"><button data-role="none" id="contactBtn">联系卖家</button></a>
                                            </c:if>
                                            <%--已退款，可以删除订单--%>
                                                <c:if test="${orders.status==5}">
                                                    <a data-ajax="false" href="${pageContext.request.contextPath}/user/ordersDelete?id=${orders.id}&userId=${userId}"><button data-role="none" id="delOrderBtn">删除订单</button></a>
                                                </c:if>
                </div>

            </div>
            <script>
                sessionStorage.setItem('freshFlag', "1");
            </script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.min.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/jquery.mobile/jquery.mobile-1.4.5.min.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/common.js"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/order-detail.js" rel="script"></script>

        </body>

        </html>