<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    List<String> shopKeeperNameList = (List<String>) request.getAttribute("shopKeeperNameList");
    int counter = 0;
%>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/admin/shopKeeper/list" method="post">
    
        <input type="hidden" name="pageSize" value="${shopKeeperList.size}">
        <input type="hidden" name="pageCurrent" value="${shopKeeperList.number+1}">
        <input type="hidden" name="orderField" value="${param.orderField}">
        <input type="hidden" name="orderDirection" value="${param.orderDirection}">
        <div class="bjui-searchBar">
            <label>用户名：</label><input type="text" name="username" value="${username}" class="form-control" size="10" />
            <label>状态：</label>
            <select name='status' class="selectpicker" data-toggle='selectpicker' data-width="100">
                <option value="" <c:if test="${status==null}">selected</c:if>>--请选择--</option>
                <option value="0" <c:if test="${status==0}">selected</c:if>>正常</option>
                <option value="1" <c:if test="${status==1}">selected</c:if>>锁定</option>
            </select>
            <!--
            <label>店主级别：</label>            
            <select name='level' class="selectpicker" data-toggle='selectpicker' data-width="100">
                <option value="" <c:if test="${level==null}">selected</c:if>>--请选择--</option>
                <option value="0" <c:if test="${level==0}">selected</c:if>>LV0</option>
                <option value="1" <c:if test="${level==1}">selected</c:if>>LV1</option>
                <option value="2" <c:if test="${level==2}">selected</c:if>>LV2</option>
            </select>
            -->
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
            <th data-order-field="realName" >真实姓名</th>
            <th data-order-field="level">店主级别</th>
            <th data-order-field="telephone">手机号</th>
            <th data-order-field="shopKeeper">推荐人</th>
            <th data-order-field="zhifubao">支付宝</th>
            <th data-order-field="allShopkeeperNumber">团队人数</th>
            <th data-order-field="directUserNumber">直接用户</th>
            <th data-order-field="allProfit">目前总收益</th>
            <th data-order-field="yiTiXian">已提现</th>
            <th>未提现</th>
            <th data-order-field="status">状态</th>
            <th data-order-field="status">注册时间</th>
            <th width="240">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="list" items="${shopKeeperList.content}" varStatus="i">
            <tr>
                <td>${i.index+1}</td>
                <td>${list.nickname}</td>
                <td>${list.level}</td>
                <td>${list.telephone}</td>
                <%--<!----%>
                <%--<td>${shopKeeperNameList.get(i)}</td>--%>
                <%---->--%>
                <%
                    String tmpShopkeeperName = shopKeeperNameList.get(counter++);
                %>
                <td><%= tmpShopkeeperName == null ? "无" : tmpShopkeeperName%></td>
                <td>${list.zhifubao}</td>
                <td>${list.allShopkeeperNumber}</td>
                <td>${list.directUserNumber}</td>
                <td>${list.allProfit}元</td>
                <td>${list.yiTiXian}元</td>
                <td><c:if test="${list.allProfit!=0}"> <fmt:formatNumber type="number" pattern="#.#" value="${list.allProfit-list.yiTiXian}"/>元</c:if><c:if test="${list.allProfit==0}">0元</c:if> </td>
                <td><c:if test="${list.status==0}">正常</c:if><c:if test="${list.status==1}">锁定</c:if></td>
                <td>${list.registerTime.toString()}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/shopKeeper/add?id=${list.id}" class="btn btn-default" data-toggle="dialog" data-width="900" data-height="300" data-id="dialog-mask" data-mask="true">查看</a>
                    <a href="${pageContext.request.contextPath}/admin/shopKeeper/jieDing?id=${list.id}" class="btn btn-blue" data-toggle="doajax" data-confirm-msg="确定要解锁吗？">解锁</a>
                    <a href="${pageContext.request.contextPath}/admin/shopKeeper/suoDing?id=${list.id}" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要锁定吗？">锁定</a>
                    <a href="${pageContext.request.contextPath}/admin/shopKeeper/delete?id=${list.id}" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除吗？">删除</a>
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
                <option value="5" <c:if test="${shopKeeperList.size==5}">selected</c:if>>5</option>
                <option value="10" <c:if test="${shopKeeperList.size==10}">selected</c:if>>10</option>
                <option value="20" <c:if test="${shopKeeperList.size==20}">selected</c:if>>20</option>
                <option value="50" <c:if test="${shopKeeperList.size==50}">selected</c:if>>50</option>

            </select>
        </div>
        <span>&nbsp;条，共 ${shopKeeperList.totalElements} 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination" data-total="${shopKeeperList.totalElements}" data-page-size="${shopKeeperList.size}" data-page-current="${shopKeeperList.number+1}">
    </div>
</div>
<script>
    //修改-->
    var formUrl = "${pageContext.request.contextPath}/admin/shopKeeper/list" + "?level=${level}";
    $("form").attr("action",formUrl);
</script>