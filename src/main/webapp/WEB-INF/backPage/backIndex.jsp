<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>网店订单管理系统</title>
    <meta name="Keywords" content=""/>
    <%--<link rel="shortcut icon" href="${pageContext.request.contextPath}/frontAsset/images/icon.png">--%>
    <%@ include file="Common/intoHead.jsp" %>
</head>

<body>
    <%@ include file="Common/header.jsp" %>

    <div id="bjui-container" class="clearfix">
        <%@ include file="Common/leftMenu.jsp" %>

        <div id="bjui-navtab" class="tabsPage">
            <div class="tabsPageHeader">
                <div class="tabsPageHeaderContent">
                    <ul class="navtab-tab nav nav-tabs">
                        <li data-url=""><a href="javascript:;"><span><i class="fa fa-home"></i> #maintab#</span></a></li>
                    </ul>
                </div>
                <div class="tabsLeft"><i class="fa fa-angle-double-left"></i></div>
                <div class="tabsRight"><i class="fa fa-angle-double-right"></i></div>
                <div class="tabsMore"><i class="fa fa-angle-double-down"></i></div>
            </div>
            <ul class="tabsMoreList">
                <li><a href="javascript:;">#maintab#</a></li>
            </ul>

            <div class="navtab-panel tabsPageContent">
                <div class="navtabPage unitBox">
                    <h2 style="background:#FFF;text-align: center;padding-top: 10%;">欢迎来到网店订单管理系统</h2>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="Common/footer.jsp" %>
</body>
</html>