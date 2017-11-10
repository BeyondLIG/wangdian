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
        <title>登录</title>
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
                        登录
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
                <a href="#" class="ui-btn ui-corner-all ui-mini" data-rel="back">确定</a>
            </div>


            <div data-role="main" class="ui-content">
                <div class="logo">
                    <img src="${pageContext.request.contextPath}/frontAssets/images/logo.jpg" alt="logo">
                </div>
                <form method="post" action="${pageContext.request.contextPath}/login" data-ajax="false" id="loginForm">
                    <div class="ui-field-contain">
                        <input type="text" name="username" id="username" placeholder="手机号：">
                        <input type="password" name="password" id="password" placeholder="密码：">
                    </div>
                    <button id="login-btn">登录</button>
                </form>

                <div class="register-forgetpwd">
                    <a href="${pageContext.request.contextPath}/register" data-ajax="false">免费注册</a>
                    <a href="${pageContext.request.contextPath}/forgetPassword" data-ajax="false">忘记密码</a>
                </div>
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
        </script>

        <script src="${pageContext.request.contextPath}/frontAssets/js/login.js" rel="script"></script>
    </body>

    </html>