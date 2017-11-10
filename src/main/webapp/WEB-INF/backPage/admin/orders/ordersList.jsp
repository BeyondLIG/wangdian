<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/admin/orders/list" method="post">
        <input type="hidden" name="pageSize" value="${ordersList.size}">
        <input type="hidden" name="pageCurrent" value="${ordersList.number+1}">
        <input type="hidden" name="orderField" value="${param.orderField}">
        <input type="hidden" name="orderDirection" value="${param.orderDirection}">
        <div class="bjui-searchBar">
            <label>订单号：</label><input type="text" name="shopOrderId" value="${shopOrderId}" class="form-control" size="9" />
            <label>店主：</label><input type="text" name="shopKeeper" value="${shopKeeper}" class="form-control" size="9" />
            <label>下订人：</label><input type="text" name="shopOrderMan" value="${shopOrderMan}" class="form-control" size="9" />
            <label>下订时间：</label><input type="text" name="shopOrderTime" data-toggle="datepicker" value="${shopOrderTime}" class="form-control" size="9" />
            <label>状态：</label>
            <select name='status' class="selectpicker" data-toggle='selectpicker' data-width="80">
                <option value="" <c:if test="${status==null}">selected</c:if> >--请选择--</option>
                <option value="0" <c:if test="${status==0}">selected</c:if> >提交订单</option>
                <option value="1" <c:if test="${status==1}">selected</c:if> >已付款未发货</option>
                <option value="2" <c:if test="${status==2}">selected</c:if> >已付款已发货</option>
                <option value="3" <c:if test="${status==3}">selected</c:if> >已确认收货</option>
                <option value="4" <c:if test="${status==4}">selected</c:if> >申请退款中</option>
                <option value="5" <c:if test="${status==5}">selected</c:if> >已退款</option>
                <option value="6" <c:if test="${status==6}">selected</c:if> >已取消订单</option>
            </select>
            <button type="submit" class="btn-default" data-icon="search">查询</button>
            <a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
            <div class="pull-right">
                <div class="btn-group">
                     <button type="button" class="btn-default" ><a href="${pageContext.request.contextPath}/admin/orders/add" data-toggle="dialog" data-width="900" data-height="600" data-id="dialog-mask" data-mask="true" style="text-decoration: none;"><i class="fa fa-plus"></i> 添加订单</a></button>
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
            <th data-order-field="shopOrderId" >订单号</th>
            <th data-order-field="shopKeeper" >店主</th>
            <th data-order-field="shopOrderMan">下订人</th>
            <th data-order-field="telephone" >下订人电话</th>
            <th data-order-field="shopOrderTime" data-order-direction="desc">下订时间</th>
            <th data-order-field="status">状态</th>
            <th width="140">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="list" items="${ordersList.content}" varStatus="i">
            <tr>
                <td>${i.index+1}</td>
                <td>${list.shopOrderId}</td>
                <td>${list.shopKeeper}</td>
                <td>${list.shopOrderMan}</td>
                <td>${list.telephone}</td>
                <td>${list.shopOrderTimeToString()}</td>
                <td><c:if test="${list.status==0}">提交订单</c:if><c:if test="${list.status==1}">已付款未发货</c:if><c:if test="${list.status==2}">已付款已发货</c:if><c:if test="${list.status==3}">已确认收货</c:if><c:if test="${list.status==4}">申请退款中</c:if><c:if test="${list.status==5}">已退款</c:if><c:if test="${list.status==6}">已取消订单</c:if></td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/orders/add?id=${list.id}" class="btn btn-default" data-toggle="dialog" data-width="900" data-height="600" data-id="dialog-mask" data-mask="true">编辑</a>
                    <a href="${pageContext.request.contextPath}/admin/orders/delete?id=${list.id}" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除吗？">删除</a>
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
                <option value="5" <c:if test="${ordersList.size==5}">selected</c:if>>5</option>
                <option value="10" <c:if test="${ordersList.size==10}">selected</c:if>>10</option>
                <option value="20" <c:if test="${ordersList.size==20}">selected</c:if>>20</option>
                <option value="50" <c:if test="${ordersList.size==50}">selected</c:if>>50</option>

            </select>
        </div>
        <span>&nbsp;条，共 ${ordersList.totalElements} 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination" data-total="${ordersList.totalElements}" data-page-size="${ordersList.size}" data-page-current="${ordersList.number+1}">
    </div>
</div>
