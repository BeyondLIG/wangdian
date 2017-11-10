<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/admin/shopKeeper/userList
" method="post">
        <input type="hidden" name="pageSize" value="${userList.size}">
        <input type="hidden" name="pageCurrent" value="${userList.number+1}">
        <input type="hidden" name="orderField" value="${param.orderField}">
        <input type="hidden" name="orderDirection" value="${param.orderDirection}">
        <div class="bjui-searchBar">
            <label>用户名：</label><input type="text" name="username" value="${username}" class="form-control" size="10" />
            <label>状态：</label>
            <select name='status' class="selectpicker" data-toggle='selectpicker' data-width="100">
                <option value="" <c:if test="${status==null}">selected</c:if> >--请选择--</option>
                <option value="0" <c:if test="${status==0}">selected</c:if> >正常</option>
                <option value="1" <c:if test="${status==1}">selected</c:if> >锁定</option>
            </select>
            <button type="submit" class="btn-default" data-icon="search">查询</button>
            <a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>

            <!--
            <div class="pull-right">
                <div class="btn-group">
                     <button type="button" class="btn-default" ><a href="${pageContext.request.contextPath}/admin/user/add" data-toggle="dialog" data-width="900" data-height="500" data-id="dialog-mask" data-mask="true" style="text-decoration: none;"><i class="fa fa-plus"></i> 添加用户</a></button>
                </div>
            </div>
            -->
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
            <th data-order-field="vip">级别</th>
            <th data-order-field="telephone">手机号</th>
            <th data-order-field="pay">消费金额</th>
            <th data-order-field="shopkeeper">推荐人</th>
            <th data-order-field="status">状态</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="list" items="${userList.content}" varStatus="i">
            <c:if test="${list.vip==1}">
                <tr>
                    <td>${i.index+1}</td>
                    <td>${list.username}</td>
                    <td>VIP用户</td>
                    <td>${list.telephone}</td>
                    <td>${list.pay}</td>
                    <td><c:if test="${list.shopkeeper==null}">无</c:if><c:if test="${list.shopkeeper!=null}">${list.shopkeeper.username}</c:if></td>
                    <td><c:if test="${list.status==0}">正常</c:if><c:if test="${list.status==1}">锁定</c:if></td>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="bjui-pageFooter">
    <div class="pages">
        <span>每页&nbsp;</span>
        <div class="selectPagesize">
            <select data-toggle="selectpicker" data-toggle-change="changepagesize">
                <option value="5" <c:if test="${userList.size==5}">selected</c:if>>5</option>
                <option value="10" <c:if test="${userList.size==10}">selected</c:if>>10</option>
                <option value="20" <c:if test="${userList.size==20}">selected</c:if>>20</option>
                <option value="50" <c:if test="${userList.size==50}">selected</c:if>>50</option>

            </select>
        </div>
        <span>&nbsp;条，共 ${userList.totalElements} 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination" data-total="${userList.totalElements}" data-page-size="${userList.size}" data-page-current="${userList.number+1}">
    </div>
</div>
<script>
    var $url = "${pageContext.request.contextPath}/admin/shopKeeper/userList"+"?shopKeeperId=${user.id}"
    $('form').attr("action",$url);
    console.log($url);
</script>
