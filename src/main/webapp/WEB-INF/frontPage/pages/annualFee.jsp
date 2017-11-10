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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/login.css">
    <script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.min.js" rel="script"></script>
    <script src="${pageContext.request.contextPath}/frontAssets/jquery.mobile/jquery.mobile-1.4.5.min.js" rel="script"></script>
    <script src="${pageContext.request.contextPath}/frontAssets/js/iconfont.js"></script>
    <title>缴纳年费</title>
</head>

<body>
<div data-role="page">
    <!--公共头部-->
    <div data-role="header" class="fixed-header">
        <!--
                <a data-ajax="false" href="${pageContext.request.contextPath}/index" class="ui-btn">首页</a>
                -->
        <div class="header-title">
            <span>
                缴纳年费
            </span>
        </div>
    </div>

    <!--出错信息提示-->
    <a href="#alertInfo" data-rel="popup" class="ui-btn" id="alertInfoBtn" data-position-to="window"></a>
    <div data-role="popup" id="alertInfo" class="ui-content" data-overlay-theme="b">
        <div data-role="header">
            <span>提示信息</span>
        </div>
        <div class="ui-field-contain">
            <div class="alert-info"></div>
        </div>
        <a href="" class="ui-btn ui-corner-all ui-mini" data-rel="back">确定</a>
    </div>


    <div data-role="main" class="ui-content">
        <form method="post" action="${pageContext.request.contextPath}/annualFee/submitAnnualFee" data-ajax="false" id="submitForm">
            <div class="ui-field-contain">
                <input type="text" name="username" id="username" placeholder="用户名：">
                <input type="text" name="telephone" id="telephone" placeholder="手机号：">
            </div>
            <button id="submit-btn">缴费</button>
        </form>
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
    var isSuccess = ${isMatch};
</script>

<script src="${pageContext.request.contextPath}/frontAssets/js/annualFee.js" rel="script"></script>
</body>

</html>