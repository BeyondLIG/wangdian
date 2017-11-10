<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--[if lte IE 7]>
    <div id="errorie"><div>您还在使用老掉牙的IE，正常使用系统前请升级您的浏览器到 IE8以上版本 <a target="_blank" href="http://windows.microsoft.com/zh-cn/internet-explorer/ie-8-worldwide-languages">点击升级</a>&nbsp;&nbsp;强烈建议您更改换浏览器：<a href="http://down.tech.sina.com.cn/content/40975.html" target="_blank">谷歌 Chrome</a></div></div>
<![endif]-->
<header id="bjui-header">
    <div class="bjui-navbar-header">
        <button type="button" class="bjui-navbar-toggle btn-default" data-toggle="collapse" data-target="#bjui-navbar-collapse">
            <i class="fa fa-bars"></i>
        </button>
        <a class="bjui-navbar-logo" href="<%=request.getContextPath()%>"><img src="<%=request.getContextPath()%>/backAsset/Static/Public/Img/logo.png" style="height: 2.5em; margin-left: 1em;"></a>
    </div>
    <%
        String headerType=(String) request.getAttribute("peopleType");
    %>
    <nav id="bjui-navbar-collapse">
        <ul class="bjui-navbar-right">
            <li class="datetime" style="padding-top: 7px;">
                <div><span id="bjui-date"></span>&nbsp;<i class="fa fa-clock-o"></i> <span id="bjui-clock"></span></div><br>
            </li>
            <!--<li><a href="#">消息 <span class="badge">0</span></a></li>-->
            <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">当前用户：${user.username}(<%if (headerType.equals("shopKeeper")){%>店主<%}%><%if (headerType.equals("admin")){%>管理员<%}%>)<span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <%--<li><a href="{:U('Admin/User/modifyPwd')}" data-toggle="dialog" data-id="changepwd_page" data-mask="true" data-width="400" data-height="260">&nbsp;<span class="fa fa-lock"></span> 修改密码</a></li>--%>
                        <%
                            if (headerType.equals("admin")){
                        %>
                        <li><a href="${pageContext.request.contextPath}/admin/admin/add?id=${user.id}&type=admin&update=head" data-toggle="dialog" data-id="changepwd_page" data-mask="true" data-width="880" data-height="280">&nbsp;<span class="fa fa-user"></span> 我的资料</a></li>
                        <%
                            }
                        %>

                    <!--li><a href="{:U('Admin/Index/cache')}" data-toggle="navtab">&nbsp;<span class="fa fa-trash"></span> 清理缓存</a></li-->
                    <li class="divider"></li>
                    <li><a href="${pageContext.request.contextPath}/admin/logout" class="red">&nbsp;<span class="fa fa-power-off"></span> 注销登陆</a></li>
                </ul>
            </li>
            <li class="dropdown"><a href="#" class="dropdown-toggle theme purple" data-toggle="dropdown"><i class="fa fa-tree"></i></a>
                <ul class="dropdown-menu" role="menu" id="bjui-themes">
                    <li><a href="javascript:;" class="theme_default" data-toggle="theme" data-theme="default">&nbsp;<i class="fa fa-tree"></i> 黑白分明&nbsp;&nbsp;</a></li>
                    <li><a href="javascript:;" class="theme_orange" data-toggle="theme" data-theme="orange">&nbsp;<i class="fa fa-tree"></i> 橘子红了</a></li>
                    <li class="active"><a href="javascript:;" class="theme_purple" data-toggle="theme" data-theme="purple">&nbsp;<i class="fa fa-tree"></i> 紫罗兰</a></li>
                    <li><a href="javascript:;" class="theme_blue" data-toggle="theme" data-theme="blue">&nbsp;<i class="fa fa-tree"></i> 青出于蓝</a></li>
                    <li><a href="javascript:;" class="theme_red" data-toggle="theme" data-theme="red">&nbsp;<i class="fa fa-tree"></i> 红红火火</a></li>
                    <li><a href="javascript:;" class="theme_green" data-toggle="theme" data-theme="green">&nbsp;<i class="fa fa-tree"></i> 绿草如茵</a></li>
                </ul>
            </li>
        </ul>
    </nav>
</header>