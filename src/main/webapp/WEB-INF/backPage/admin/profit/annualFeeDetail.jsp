<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch"
          action="${pageContext.request.contextPath}/admin/annualFeeOrder/list" method="post">
        <input type="hidden" name="page" value="${annualFeeOrdersList.number+1}">
        <input type="hidden" name="size" value="${annualFeeOrdersList.size}">
    </form>
</div>

<div class="bjui-pageContent">
    <table class="table table-bordered table-hover table-striped table-top" data-selected-multi="true">
        <thead>
        <tr>
            <th>序号</th>
            <th>店主姓名</th>
            <th>状态</th>
            <th>缴纳年费</th>
            <th>缴纳时间</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="list" items="${annualFeeOrdersList.content}" varStatus="i">
            <tr>
                <td>${i.index+1}</td>
                <td>${list.shopKeeper}</td>
                <td><c:if test="${list.status==0}">未付款</c:if><c:if test="${list.status==1}">已付款</c:if></td>
                <td>${list.fee}</td>
                <td>${list.submitTime}</td>
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
                <option value="5" <c:if test="${annualFeeOrdersList.size==5}">selected</c:if>>5</option>
                <option value="10" <c:if test="${annualFeeOrdersList.size==10}">selected</c:if>>10</option>
                <option value="20" <c:if test="${annualFeeOrdersList.size==20}">selected</c:if>>20</option>
                <option value="50" <c:if test="${annualFeeOrdersList.size==50}">selected</c:if>>50</option>

            </select>
        </div>
        <span>&nbsp;条，共 ${annualFeeOrdersList.totalElements} 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination" data-total="${annualFeeOrdersList.totalElements}" data-page-size="${annualFeeOrdersList.size}" data-page-current="${annualFeeOrdersList.number+1}">
    </div>
</div>
