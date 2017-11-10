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
            <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/form-validate.css">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/address-management.css">
            <script src="${pageContext.request.contextPath}/frontAssets/js/iconfont.js"></script>
            <title>管理收货地址</title>
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
                           管理收货地址
                        </span>
                    </div>
                </div>

                <a href="#alertInfo" data-rel="popup" id="alertInfoBtn" data-position-to="window"></a>
                <div data-role="popup" id="alertInfo" class="ui-content" data-overlay-theme="b">
                    <div data-role="header">
                        <span>提示信息</span>
                    </div>
                    <div class="ui-field-contain">
                        <div class="alert-info"></div>
                    </div>
                    <div class="ui-grid-a">
                        <div class="ui-block-a">
                            <a href="#" class="ui-btn ui-corner-all ui-mini" data-rel="back">取消</a>
                        </div>
                        <div class="ui-block-b">
                            <a href="#" class="ui-btn ui-corner-all ui-mini confirmBtn" data-rel="back">确定</a>
                        </div>
                    </div>
                </div>
                <div data-role="main" class="ui-content">
                    <a data-ajax="false" href="${pageContext.request.contextPath}/user/addAddress?userId=${userId}" id="addAddressBtn">新增收货地址</a>

                    <c:if test="${userAddressList.size()==0}">
                        <div class="not-found">
                            <div class="icon-container">
                                <svg class="icon" aria-hidden="true">
                                    <use xlink:href="#icon-notfind01"></use>
                                </svg>
                            </div>
                            <div class="not-title">
                                <span>
                                    收货地址跑没了，快去把他找回来！
                                </span>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${userAddressList.size()!=0}">
                        <c:forEach items="${userAddressList}" var="list">
                            <div class="address">
                                <div class="address-info">
                                    <div class="name-phone"><span>${list.name}</span><span>${list.phone}</span></div>
                                    <span class="address-detail">${list.province}${list.city}${list.area}${list.town}&nbsp${list.adddetail}</span>
                                </div>
                                <div class="address-options ui-grid-b">
                                    <div class="ui-block-a"></div>
                                    <div class="ui-block-b"><a href="${pageContext.request.contextPath}/user/addAddressEdit?id=${list.id}" class="ui-btn"
                                            data-ajax="false">编辑</a></div>
                                    <div class="ui-block-c"><a href="${pageContext.request.contextPath}/user/addAddressDelete?id=${list.id}" class="ui-btn delBtn"
                                            data-ajax="false">删除</a></div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                    <!--
                    <div class="address">
                        <div class="address-info">
                            <div class="name-phone"><span>张三</span><span>13012341234</span></div>
                            <p>湖北省武汉市洪山区珞南街道</p>
                        </div>
                        <div class="address-options ui-grid-b">
                            <div class="ui-block-b">
                                <a href="#" class="ui-btn"></a>
                            </div>
                            <div class="ui-block-b"><a href="#" class="ui-btn">编辑</a></div>
                            <div class="ui-block-c"><a href="http://www.baidu.com" class="ui-btn delBtn">删除</a></div>
                        </div>
                    </div>
                    -->
                </div>

            </div>
            <script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.min.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/jquery.mobile/jquery.mobile-1.4.5.min.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/common.js"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/address-management.js" rel="script"></script>

        </body>

        </html>