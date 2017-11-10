<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/jquery.mobile/jquery.mobile-1.4.5.min.css">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/common.css">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/setting.css">
            <script src="${pageContext.request.contextPath}/frontAssets/js/iconfont.js"></script>
            <title>设置</title>
        </head>

        <body>
            <div data-role="page">
                <!--公共头部-->
                <div data-role="header" class="fixed-header">
                    <!--
                    <a data-ajax="false" href="${pageContext.request.contextPath}/userCenter" class="">
                    </a>
                    -->
                    <div class="icon-container">
                        <svg class="icon" aria-hidden="true">
                            <use xlink:href="#icon-fanhui"></use>
                        </svg>
                    </div>
                    <div class="header-title">
                        <span>
                            设置
                        </span>
                    </div>
                </div>
                <div data-role="main" class="ui-content">
                    <div class="user-items">
                        <ul>
                            <li>
                                <a data-ajax="false" href="${pageContext.request.contextPath}/user/userInfo" class="ui-btn">
                                    <div class="item-icon">
                                        <svg class="icon" aria-hidden="true">
                                            <use xlink:href="#icon-iconfuzhi01"></use>
                                        </svg>
                                    </div>
                                    <div class="item-title">
                                        账户安全
                                    </div>
                                </a>

                            </li>
                        
                            
                        </ul>
                    </div>
                    
                </div>
            </div>
            <script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.min.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/jquery.mobile/jquery.mobile-1.4.5.min.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/common.js"></script>
        </body>

        </html>