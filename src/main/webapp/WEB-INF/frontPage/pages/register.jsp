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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/register.css">
        <script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.min.js" rel="script"></script>
        <script src="${pageContext.request.contextPath}/frontAssets/jquery.mobile/jquery.mobile-1.4.5.min.js" rel="script"></script>
        <script src="${pageContext.request.contextPath}/frontAssets/js/iconfont.js"></script>
        <title>免费注册</title>
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
                        免费注册
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
                <!--
                <div class="type-area">
                    <div id="type-title">
                        <span>选择注册类型</span>
                    </div>
                    <div id="type-btn">
                        <button id="type-shopKeeper">店主</button>
                        <button id="type-vip">VIP会员</button>
                        <!--
                        <button id="type-normal">普通会员</button>
                    </div>
                </div>
                -->
                <form method="post" action="${pageContext.request.contextPath}/register" data-ajax="false" id="registerForm">
                    <div class="ui-field-contain">
                        <!--
                        <input type="text" name="username" placeholder="真实姓名">
                        -->
                        <!--
                        <input type="text" name="username" placeholder="用户名">
                        -->
                        <input type="tel" name="telephone" placeholder="手机号" id="telephone">
                        <input type="text" name="validCode" placeholder="验证码" id="validCode">
                        <button id="send-code">发送验证码</button>
                        <input type="password" name="password" placeholder="密码">
                        <!--
                        <input type="password" id="password" placeholder="确认密码">
                        -->
                        <input type="text" name="referee" id="referee" placeholder="推荐人手机号">
                        <!--
                        <input type="text" name="alipay" id="alipay" placeholder="支付宝账号">
                        -->
                    </div>
                    <button type="submit" id="register">注&nbsp&nbsp&nbsp册</button>
                </form>
            </div>
        </div>

        <script>
            var contextPath = "${pageContext.request.contextPath}";
        </script>
        <script src="${pageContext.request.contextPath}/frontAssets/js/common.js"></script>
        <script src="${pageContext.request.contextPath}/frontAssets/js/register.js" rel="script"></script>

    </body>

    </html>