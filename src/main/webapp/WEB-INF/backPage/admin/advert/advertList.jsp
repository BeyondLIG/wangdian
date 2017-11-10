<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/admin/advert/list" method="post">
        <input type="hidden" name="pageSize" value="${advertList.size}">
        <input type="hidden" name="pageCurrent" value="${advertList.number+1}">
        <input type="hidden" name="orderField" value="${param.orderField}">
        <input type="hidden" name="orderDirection" value="${param.orderDirection}">
        <div class="bjui-searchBar">
            <label>广告信息：</label><input type="text" name="name" value="${name}" class="form-control" size="10" />
            <label>记录时间：</label><input type="text" name="inTime" data-toggle="datepicker" value="${inTime}" class="form-control" size="9" />
            <button type="submit" class="btn-default" data-icon="search">查询</button>
            <a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
            <div class="pull-right">
                <div class="btn-group">
                     <button type="button" class="btn-default" ><a href="${pageContext.request.contextPath}/admin/advert/add" data-toggle="dialog" data-width="1100" data-height="600" data-id="dialog-mask" data-mask="true" style="text-decoration: none;"><i class="fa fa-plus"></i> 添加广告</a></button>
                </div>
            </div>
        </div>
    </form>
</div>

<div class="bjui-pageContent">
    <table class="table table-bordered table-hover table-striped table-top" data-selected-multi="true">
        <thead>
        <tr>
            <th >序号</th>
            <th data-order-field="name">广告信息</th>
            <th data-order-field="url">url</th>
            <th data-order-direction="desc" data-order-field="inTime">记录时间</th>
            <th width="240">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="list" items="${advertList.content}" varStatus="i">
            <tr>
                <td>${i.index+1}</td>
                <td>${list.name}</td>
                <td>${list.url}</td>
                <td>${list.inTimeToString()}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/advert/add?id=${list.id}" class="btn btn-default" data-toggle="dialog" data-width="1100" data-height="600" data-id="dialog-mask" data-mask="true">编辑</a>
                    <a href="${pageContext.request.contextPath}/admin/advert/delete?id=${list.id}" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除吗？">删除</a>
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
                <option value="5" <c:if test="${advertList.size==5}">selected</c:if>>5</option>
                <option value="10" <c:if test="${advertList.size==10}">selected</c:if>>10</option>
                <option value="20" <c:if test="${advertList.size==20}">selected</c:if>>20</option>
                <option value="50" <c:if test="${advertList.size==50}">selected</c:if>>50</option>

            </select>
        </div>
        <span>&nbsp;条，共 ${advertList.totalElements} 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination" data-total="${advertList.totalElements}" data-page-size="${advertList.size}" data-page-current="${advertList.number+1}">
    </div>
</div>
