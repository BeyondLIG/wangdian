<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/frontAssets/images/logo.jpg" type="image/x-icon" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/jquery.mobile/jquery.mobile-1.4.5.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/common.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/login.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/form-validate.css">
        <script src="${pageContext.request.contextPath}/frontAssets/js/iconfont.js"></script>
        <title>修改密码</title>
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
                        修改密码
                    </span>
                </div>
            </div>

            <!--出错信息提示-->
            <a href="#alertInfo" data-rel="popup" class="ui-btn" id="alertInfoBtn" data-position-to="window"></a>
            <div data-role="popup" id="alertInfo" class="ui-content" data-overlay-theme="b">
                <div data-role="header">
                    <sapn>提示信息</sapn>
                </div>
                <div class="ui-field-contain">
                    <div class="alert-info"></div>
                </div>
                <a href="#" class="ui-btn ui-corner-all ui-mini" data-rel="back">确定</a>
            </div>


            <div data-role="main" class="ui-content">
                <form method="post" action="${pageContext.request.contextPath}/user/changePassword" id="changePwdForm">
                    <div class="ui-field-contain">
                        <input type="password" name="password1" id="password1" placeholder="原密码：">
                        <input type="password" name="password2" id="password2" placeholder="新密码：">
                        <input type="password" name="password3" id="password3" placeholder="重复输入新密码：">
                        <input type="hidden" name="userId" value="${userId}">
                    </div>
                    <input type="submit" value="确认修改">
                </form>
            </div>

            <!--公共底部-->

        </div>

        <script>
            var contextPath = "${pageContext.request.contextPath}";
            var userId = "${ordinaryUser.id}";
        </script>

        <script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.min.js" rel="script"></script>
        <script src="${pageContext.request.contextPath}/frontAssets/jquery.mobile/jquery.mobile-1.4.5.min.js" rel="script"></script>
        <script src="${pageContext.request.contextPath}/frontAssets/js/change-password.js" rel="script"></script>
        <script src="${pageContext.request.contextPath}/frontAssets/js/common.js"></script>
    </body>

    </html>