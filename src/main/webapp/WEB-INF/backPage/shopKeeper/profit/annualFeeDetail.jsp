<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch"
          action="${pageContext.request.contextPath}/admin/shopKeeper/annualFeeIncome" method="post">
        <input type="hidden" name="page" value="${currentPage}">
        <input type="hidden" name="size" value="${pageSize}">
    </form>
</div>

<div class="bjui-pageContent">
    <table class="table table-bordered table-hover table-striped table-top" data-selected-multi="true">
        <thead>
        <tr>
            <th>序号</th>
            <th>店主姓名</th>
            <th>电话</th>
            <th>类型</th>
            <th>分润</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="list" items="${shopkeeperProfitTrackList}" varStatus="i">
            <tr>
                <td>${i.index+1}</td>
                <td>${list.username}</td>
                <td>${list.telephone}</td>
                <td><c:if test="${list.type==2}">直接招聘</c:if><c:if test="${list.type==3}">间接招聘</c:if></td>
                <td>${list.profit}</td>
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
                <option value="5" <c:if test="${pageSize==5}">selected</c:if>>5</option>
                <option value="10" <c:if test="${pageSize==10}">selected</c:if>>10</option>
                <option value="20" <c:if test="${pageSize==20}">selected</c:if>>20</option>
                <option value="50" <c:if test="${pageSize==50}">selected</c:if>>50</option>
            </select>
        </div>
        <span>&nbsp;条，共 ${recordNumber} 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination" data-total="${recordNumber}"
         data-page-size="${pageSize}" data-page-current="${currentPage}">
    </div>
</div>
