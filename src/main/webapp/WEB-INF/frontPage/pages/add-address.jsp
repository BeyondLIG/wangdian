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
            <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/add-address.css">
            <script src="${pageContext.request.contextPath}/frontAssets/js/iconfont.js"></script>
            <title>新增收货地址</title>
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
                           新增收货地址
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
                    <a href="#" class="ui-btn ui-corner-all ui-mini" data-rel="back">确定</a>
                </div>

                <div data-role="main" class="ui-content">
                    <form method="post" action="${pageContext.request.contextPath}/user/addAddress" id="addAddressForm">
                        <div class="ui-field-contain">
                            <input type="hidden" name="isDel" value="0">
                            <input type="hidden" name="id" value="${userAddress.id}">
                            <input type="hidden" name="status" <c:if test="${userAddress.status!=null}">value="${userAddress.status}"</c:if>
                            <c:if test="${userAddress.status==null}">value="1"</c:if> >
                            <input type="hidden" name="userId" value="${userId}">

                            <input type="text" name="name" id="name" placeholder="收货人姓名：" value="${userAddress.name}">
                            <input type="tel" name="phone" id="phone" placeholder="手机号码：" value="${userAddress.phone}">
                            <%--<input type="text" name="zipcode" id="zipcode" placeholder="邮政编码：" value="${userAddress.zipcode}">--%>

                                <div id="city">
                                    <select class="ui-btn" data-role="none" name="province"><c:if test="${userAddress.province!=null}"><option value="${userAddress.province}" selected>${userAddress.province}</option></c:if> </select>
                                    <select class="ui-btn" data-role="none" name="city"><c:if test="${userAddress.city!=null}"><option value="${userAddress.city}" selected>${userAddress.city}</option></c:if> </select>
                                    <select class="ui-btn" data-role="none" name="area"><c:if test="${userAddress.area!=null}"><option value="${userAddress.area}" selected>${userAddress.area}</option></c:if> </select>
                                    <select class="ui-btn" data-role="none" name="town"><c:if test="${userAddress.town!=null}"><option value="${userAddress.town}" selected>${userAddress.town}</option></c:if> </select>
                                </div>
                                <div class="current-address">当前选择地址：${userAddress.province}${userAddress.city}${userAddress.area}${userAddress.town}</div>
                                <textarea placeholder="详细地址" name="adddetail" id="addDetail">${userAddress.adddetail}</textarea>
                        </div>
                        <button type="submit" id="submit">保存</button>
                    </form>
                </div>
            </div>
            <script>
                var contextPath = "${pageContext.request.contextPath}";
                var userId = "${userId}";
                var addressId = "${userAddress.id}";
                var oldProvince = "${userAddress.province}";
                var oldCity = "${userAddress.city}";
                var oldArea = "${userAddress.area}";
                var oldTown = "${userAddress.town}";
            </script>

            <script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.min.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/jquery.mobile/jquery.mobile-1.4.5.min.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.citys.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/common.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/add-address.js" rel="script"></script>

        </body>

        </html>