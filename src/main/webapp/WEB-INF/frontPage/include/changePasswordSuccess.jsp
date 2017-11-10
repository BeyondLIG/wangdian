<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--<link rel="shortcut icon" href="../images/logo.png" type="image/x-icon"/>-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/jquery.mobile/jquery.mobile-1.4.5.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/frontAssets/css/login.css">
    <title>成功</title>
</head>
<body>
<div data-role="page">

    <!--公共头部-->
    <div data-role="header" class="fixed-header">
        <a href="${pageContext.request.contextPath}/userCenter" class="ui-btn" >返回</a>
        <h1>成功</h1>
    </div>


    <div data-role="main" class="ui-content" style="margin-top: 10px;">
       ${message}
    </div>

    <!--公共底部-->

</div>


<script src="${pageContext.request.contextPath}/frontAssets/js/common/jquery.min.js" rel="script"></script>
<script src="${pageContext.request.contextPath}/frontAssets/jquery.mobile/jquery.mobile-1.4.5.min.js" rel="script"></script>

</body>
</html>