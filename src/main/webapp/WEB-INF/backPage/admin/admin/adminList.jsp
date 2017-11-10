<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/admin/admin/list" method="post">
        <input type="hidden" name="pageSize" value="${adminList.size}">
        <input type="hidden" name="pageCurrent" value="${adminList.number+1}">
        <input type="hidden" name="orderField" value="${param.orderField}">
        <input type="hidden" name="orderDirection" value="${param.orderDirection}">
        <div class="bjui-searchBar">
            <label>用户名：</label><input type="text" name="username" value="${username}" class="form-control" size="10" />
            <label>昵称：</label><input type="text" name="nickname" value="${nickname}" class="form-control" size="10" />
            <label>状态：</label>
            <select name='status' class="selectpicker" data-toggle='selectpicker' data-width="100">
                <option value="" <c:if test="${status==null}">selected</c:if> >--请选择--</option>
                <option value="0" <c:if test="${status==0}">selected</c:if> >正常</option>
                <option value="1" <c:if test="${status==1}">selected</c:if> >锁定</option>
            </select>
            <button type="submit" class="btn-default" data-icon="search">查询</button>
            <a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
            <div class="pull-right">
                <div class="btn-group">
                     <button type="button" class="btn-default" ><a href="${pageContext.request.contextPath}/admin/admin/add" data-toggle="dialog" data-width="880" data-height="280" data-id="dialog-mask" data-mask="true" style="text-decoration: none;"><i class="fa fa-plus"></i> 添加管理员</a></button>
                </div>
            </div>
        </div>
    </form>
</div>

<div class="bjui-pageContent">
    <%--<table data-toggle="tablefixed" data-width="100%" data-nowrap="true">--%>
    <table class="table table-bordered table-hover table-striped table-top" data-selected-multi="true">
        <thead>
        <tr>
            <th >序号</th>
            <th data-order-field="username" >用户名</th>
            <th data-order-field="nickname" >昵称</th>
            <th data-order-direction="asc" data-order-field="loginTime">上次登录</th>
            <th data-order-field="loginCount">登录次数</th>
            <th data-order-field="status">状态</th>
            <th width="240">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="list" items="${adminList.content}" varStatus="i">
            <tr>
                <td>${i.index+1}</td>
                <td>${list.username}</td>
                <td>${list.nickname}</td>
                <td>${list.loginTimeToString()}</td>
                <td>${list.loginCount}</td>
                <td><c:if test="${list.status==0}">正常</c:if><c:if test="${list.status==1}">锁定</c:if></td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/admin/add?id=${list.id}" class="btn btn-default" data-toggle="dialog" data-width="880" data-height="270" data-id="dialog-mask" data-mask="true">编辑</a>
                    <a href="${pageContext.request.contextPath}/admin/admin/jieDing?id=${list.id}" class="btn btn-blue" data-toggle="doajax" data-confirm-msg="确定要解锁吗？">解锁</a>
                    <a href="${pageContext.request.contextPath}/admin/admin/suoDing?id=${list.id}" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要锁定吗？">锁定</a>
                    <a href="${pageContext.request.contextPath}/admin/admin/delete?id=${list.id}" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除吗？">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="bjui-pageFooter">
    <div class="pages">
        <span>每页&nbsp;</span>
        <div class="selectPagesize">
            <select data-toggle="selectpicker" data-toggle-change="changepagesize">
                <option value="5" <c:if test="${adminList.size==5}">selected</c:if>>5</option>
                <option value="10" <c:if test="${adminList.size==10}">selected</c:if>>10</option>
                <option value="20" <c:if test="${adminList.size==20}">selected</c:if>>20</option>
                <option value="50" <c:if test="${adminList.size==50}">selected</c:if>>50</option>

            </select>
        </div>
        <span>&nbsp;条，共 ${adminList.totalElements} 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination" data-total="${adminList.totalElements}" data-page-size="${adminList.size}" data-page-current="${adminList.number+1}">
    </div>
</div>
