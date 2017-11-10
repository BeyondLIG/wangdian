<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/frontAssets/images/logo.jpg" type="image/x-icon" />
    <!--
    <link rel="stylesheet" href="http://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.css">
    -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/jquery.mobile/jquery.mobile-1.4.5.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/form-validate.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/user-center.css">
    <script src="${pageContext.request.contextPath}/frontAssets/js/iconfont.js"></script>
    <title>个人中心</title>
</head>

<body>
<div data-role="page" id="page">
    <div data-role="header" class="fixed-header">
        <div class="header-title">
                        <span>
                        个人中心
                    </span>
        </div>
    </div>

    <div data-role="main" class="ui-content">
        <div class="user-info">
            <div class="img">
                <img src="http://img.my.csdn.net/uploads/201703/26/1490497985_3287.jpg" alt="图片">
            </div>
            <c:if test="${ordinaryUser.vip==1}">
                <div class="username">
                    ${ordinaryUser.username}
                    <div class="user-type">VIP用户</div>
                </div>
                <div class="user-level">
                    <button id="becomeShopkeeper">成为店主</button>
                </div>
            </c:if>
            <c:if test="${ordinaryUser.vip==2}">
                <div class="username">
                        ${ordinaryUser.username}
                    <div class="user-type">店主</div>
                </div>
                <!--
                <div class="user-level">
                    <button id="upgrade">升级</button>
                </div>
                -->
            </c:if>
        </div>
        <div class="user-items">
            <ul>
                <li>
                    <a data-ajax="false" href="${pageContext.request.contextPath}/user/myOrder?userId=${ordinaryUser.id}" class="ui-btn">
                        <div class="item-icon">
                            <svg class="icon" aria-hidden="true">
                                <use xlink:href="#icon-order"></use>
                            </svg>
                        </div>
                        <div class="item-title">
                            我的订单
                        </div>
                    </a>
                </li>
                <li>
                    <a href="#phone" data-rel="popup" class="ui-btn" data-position-to="window">
                        <div class="item-icon">
                            <svg class="icon" aria-hidden="true">
                                <use xlink:href="#icon-shouji"></use>
                            </svg>
                        </div>
                        <div class="item-title">
                            修改手机号
                        </div>
                    </a>
                </li>
                <li>
                    <a data-ajax="false" href="${pageContext.request.contextPath}/user/changePassword?userId=${ordinaryUser.id}" class="ui-btn">
                        <div class="item-icon">
                            <svg class="icon" aria-hidden="true">
                                <use xlink:href="#icon-mima"></use>
                            </svg>
                        </div>
                        <div class="item-title">
                            修改密码
                        </div>
                    </a>
                </li>
                <li>
                    <a data-ajax="false" href="${pageContext.request.contextPath}/user/addressManagement?userId=${ordinaryUser.id}" class="ui-btn">
                        <div class="item-icon">
                            <svg class="icon" aria-hidden="true">
                                <use xlink:href="#icon-dizhi"></use>
                            </svg>
                        </div>
                        <div class="item-title">
                            管理收货地址
                        </div>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/user/ordersContact" data-ajax="false" class="ui-btn">
                        <div class="item-icon">
                            <svg class="icon" aria-hidden="true">
                                <use xlink:href="#icon-yijian"></use>
                            </svg>
                        </div>
                        <div class="item-title">
                            联系我们
                        </div>
                    </a>

                </li>
            </ul>
        </div>
        <a href="#alertInfo" data-rel="popup" id="alertInfoBtn" data-position-to="window"></a>
        <div data-role="popup" id="alertInfo" class="ui-content" data-overlay-theme="b">
            <div data-role="header">
                <span>提示信息</span>
            </div>
            <div class="ui-field-contain">
                <div class="alert-info"></div>
                <input type="text" name="realName" id="realName" placeholder="请输入真实姓名">
                <input type="text" name="alipay" id="alipay" placeholder="请输入支付宝账号">
            </div>
            <a href="#" class="ui-btn ui-corner-all ui-mini" data-rel="back" id="cancle">取消</a>
            <a href="#" class="ui-btn ui-corner-all ui-mini" id="sure">确定</a>
        </div>

        <div data-role="popup" id="email" class="ui-content" data-overlay-theme="b">
            <div data-role="header">
                <span>修改邮箱</span>
            </div>
            <div class="origin">
                原邮箱
                <span id="emailShow">${ordinaryUser.email}</span>
            </div>
            <div class="ui-field-contain">
                <input type="email" name="email" placeholder="请输入新邮箱：">
            </div>
            <div class="alertInfo"></div>
            <button class="ui-btn ui-corner-all" id="changeEmailBtn">确认修改</button>
        </div>

        <div data-role="popup" id="phone" class="ui-content" data-overlay-theme="b">
            <div data-role="header">
                <span>修改手机号</span>
            </div>
            <div class="origin">
                原号码
                <span id="phoneShow">${ordinaryUser.telephone}</span>
            </div>
            <div class="ui-field-contain">
                <form method="post" action="${pageContext.request.contextPath}/forgetPassword" data-ajax="false" id="forgetPwdForm">
                    <div class="ui-field-contain">
                        <input type="tel" name="telephone" placeholder="新的手机号" id="telephone">
                        <input type="text" name="validCode" placeholder="验证码" id="validCode">
                        <button id="send-code">发送验证码</button>
                        <input type="password" name="password" placeholder="新密码" id="password">
                    </div>
                </form>
            </div>
            <button class="ui-btn ui-corner-all" id="changePhoneBtn">确认修改</button>
        </div>
        <a data-ajax="false" href="${pageContext.request.contextPath}/logout" style="text-decoration: none;"><button id="logoutBtn" class="ui-btn">退出登录</button></a>

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
        var userId = ${ordinaryUser.id};
    </script>
    <script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.min.js" rel="script"></script>
    <script src="${pageContext.request.contextPath}/frontAssets/jquery.mobile/jquery.mobile-1.4.5.min.js" rel="script"></script>
    <script src="${pageContext.request.contextPath}/frontAssets/js/common.js" rel="script"></script>
    <script src="${pageContext.request.contextPath}/frontAssets/js/user-center.js" rel="script"></script>
</body>

</html>