<%@ page import="cn.wangdian.Model.ShopkeeperProfitTrack" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<ShopkeeperProfitTrack> shopkeeperProfitTrackList = (List<ShopkeeperProfitTrack>) request.getAttribute("shopkeeperProfitTrackList");
    int counter = 0;
%>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/admin/shopKeeper/goodsIncome" method="post">
        <input type="hidden" name="page" value="${currentPage}">
        <input type="hidden" name="size" value="${pageSize}">
    </form>
</div>

<div class="bjui-pageContent">
    <table class="table table-bordered table-hover table-striped table-top" data-selected-multi="true">
        <thead>
        <tr>
            <th>序号</th>
            <th>商品名称</th>
            <th>商品类型</th>
            <th>成本价</th>
            <th>销售价</th>
            <th>销售数量</th>
            <th>总利润</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="list" items="${shopCartList}" varStatus="i">
            <tr>
                <td>${i.index+1}</td>
                <td>${list.shopName}</td>
                <td>${list.shopType}</td>
                <td>${list.firstCost}</td>
                <td>${list.secondCost}元</td>
                <td>${list.count}元</td>
                <td><%=shopkeeperProfitTrackList.get(counter++).getProfit()%>元</td>
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
    <div class="pagination-box" data-toggle="pagination" data-total="${recordNumber}" data-page-size="${pageSize}" data-page-current="${currentPage}">
    </div>
</div>
