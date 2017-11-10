<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/admin/profit/tiXian" method="post">
        <input type="hidden" name="pageSize" value="${shopKeeperProfitsList.size}">
        <input type="hidden" name="pageCurrent" value="${shopKeeperProfitsList.number+1}">
        <input type="hidden" name="orderField" value="${param.orderField}">
        <input type="hidden" name="orderDirection" value="${param.orderDirection}">
        <div class="bjui-searchBar">
            <label>提现店主：</label><input type="text" name="shopKeeper" value="${shopKeeper}" class="form-control" size="9" />
            <label>提现时间：</label><input type="text" name="tiXianTime" data-toggle="datepicker" value="${tiXianTime}" class="form-control" size="9" />
            <label>状态：</label>
            <select name='status' class="selectpicker" data-toggle='selectpicker' data-width="70">
                <option value="" <c:if test="${status==null}">selected</c:if> >--请选择--</option>
                <option value="0" <c:if test="${status==0}">selected</c:if> >未批准</option>
                <option value="1" <c:if test="${status==1}">selected</c:if> >已批准</option>
                <option value="2" <c:if test="${status==2}">selected</c:if> >已拒绝</option>
            </select>
            <button type="submit" class="btn-default" data-icon="search">查询</button>
            <a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>

        </div>
    </form>
</div>

<div class="bjui-pageContent">



    <%--<table data-toggle="tablefixed" data-width="100%" data-nowrap="true">--%>
    <table class="table table-bordered table-hover table-striped table-top" data-selected-multi="true">
        <thead>
        <tr>
            <th >序号</th>
            <th data-order-field="shopKeeper" >提现店主</th>
            <th data-order-field="money">提现金额</th>
            <th data-order-field="alipay">店主支付宝</th>
            <th data-order-field="tiXianTime">提现时间</th>
            <th data-order-field="status">状态</th>
            <th width="240">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="list" items="${shopKeeperProfitsList.content}" varStatus="i">
            <tr>
                <td>${i.index+1}</td>
                <td>${list.shopKeeper}</td>
                <td>${list.money}</td>
                <td>${list.alipay}</td>
                <td>${list.tiXianTimeToString()}</td>
                <td><c:if test="${list.status==0}">未批准</c:if><c:if test="${list.status==1}">已批准</c:if><c:if test="${list.status==2}">已拒绝</c:if></td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/profit/ok?id=${list.id}&money=${list.money}" class="btn btn-info" data-toggle="doajax" data-confirm-msg="确定要批准吗？">批准</a>
                    <a href="${pageContext.request.contextPath}/admin/profit/reject?id=${list.id}&money=${list.money}" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要拒绝吗？">拒绝</a>
                    <a href="${pageContext.request.contextPath}/admin/profit/delete?id=${list.id}" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除吗？">删除</a>
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
                <option value="5" <c:if test="${shopKeeperProfitsList.size==5}">selected</c:if>>5</option>
                <option value="10" <c:if test="${shopKeeperProfitsList.size==10}">selected</c:if>>10</option>
                <option value="20" <c:if test="${shopKeeperProfitsList.size==20}">selected</c:if>>20</option>
                <option value="50" <c:if test="${shopKeeperProfitsList.size==50}">selected</c:if>>50</option>

            </select>
        </div>
        <span>&nbsp;条，共 ${shopKeeperProfitsList.totalElements} 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination" data-total="${shopKeeperProfitsList.totalElements}" data-page-size="${shopKeeperProfitsList.size}" data-page-current="${shopKeeperProfitsList.number+1}">
    </div>
</div>
