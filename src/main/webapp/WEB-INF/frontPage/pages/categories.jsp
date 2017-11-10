<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/jquery.mobile/jquery.mobile-1.4.5.min.css">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/common.css">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/categories.css">
            <script src="${pageContext.request.contextPath}/frontAssets/js/iconfont.js"></script>
            <title>购物车</title>
            <style>
                .fixed-header{
                    height: 50px;
                }
            </style>
        </head>

        <body>
            <div data-role="page">
                <!--公共头部-->
                <div data-role="header" class="fixed-header">
                    <div class="shop-cart">
                        <span>
                            我的购物车
                        </span>
                    </div>
                </div>
                <div data-role="main" class="ui-content">
                    <!--购物车列表-->
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
                            <!--此处链接需要修改-->
                            <li>
                                <a data-ajax="false" href="${pageContext.request.contextPath}/categories" class="ui-btn" data-inline="true">
                                    <div class="icon-container">
                                        <svg class="icon" aria-hidden="true">
                                            <use xlink:href="#icon-gouwuchekong"></use>
                                        </svg>
                                    </div>
                                    <span class="title">购物车</span>
                                </a>
                            </li>
                            <li>
                                <a data-ajax="false" href="${pageContext.request.contextPath}/userCenter" class="ui-btn" data-inline="true">
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

            <script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.min.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.event.move.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.event.swipe.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/jquery.mobile/jquery.mobile-1.4.5.min.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/common.js" rel="script"></script>
            <script src="${pageContext.request.contextPath}/frontAssets/js/categories.js" rel="script"></script>

        </body>

        </html>