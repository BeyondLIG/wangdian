<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/admin/shop/list" method="post">
        <input type="hidden" name="pageSize" value="${shopList.size}">
        <input type="hidden" name="pageCurrent" value="${shopList.number+1}">
        <input type="hidden" name="orderField" value="${param.orderField}">
        <input type="hidden" name="orderDirection" value="${param.orderDirection}">
        <div class="bjui-searchBar">
            <label>商品编号：</label><input type="text" name="number" value="${number}" class="form-control" size="9" />
            <label>商品名称：</label><input type="text" name="name" value="${name}" class="form-control" size="9" />
            <label>商品类型：</label><input type="text" name="shopType" value="${shopType}" class="form-control" size="9" />
            <label>状态：</label>
            <select name='status' class="selectpicker" data-toggle='selectpicker' data-width="70">
                <option value="" <c:if test="${status==null}">selected</c:if> >--请选择--</option>
                <option value="0" <c:if test="${status==0}">selected</c:if> >上架</option>
                <option value="1" <c:if test="${status==1}">selected</c:if> >下架</option>
            </select>
            <label>是否推荐：</label>
            <select name='isRecommend' class="selectpicker" data-toggle='selectpicker' data-width="70">
                <option value="" <c:if test="${isRecommend==null}">selected</c:if> >--请选择--</option>
                <option value="0" <c:if test="${isRecommend==0}">selected</c:if> >否</option>
                <option value="1" <c:if test="${isRecommend==1}">selected</c:if> >是</option>
            </select>
            <button type="submit" class="btn-default" data-icon="search">查询</button>
            <a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
            <div class="pull-right">
                <div class="btn-group">
                     <button type="button" class="btn-default" ><a href="${pageContext.request.contextPath}/admin/shop/add" data-toggle="dialog" data-width="900" data-height="600" data-id="dialog-mask" data-mask="true" style="text-decoration: none;"><i class="fa fa-plus"></i> 添加商品</a></button>
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
            <th data-order-field="number" >编号</th>
            <th data-order-field="name" >商品名称</th>
            <th data-order-field="shopTypeName">商品类型</th>
            <th data-order-field="firstCost">成本价</th>
            <th data-order-field="shopkeeperPrice">店主价</th>
            <th data-order-field="vipPrice">VIP价</th>
            <th data-order-field="inTime">入库时间</th>
            <th data-order-field="isRecommend">是否推荐</th>
            <th data-order-field="status">上下架</th>
            <th width="240">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="list" items="${shopList.content}" varStatus="i">
            <tr>
                <td>${i.index+1}</td>
                <td>${list.number}</td>
                <td>${list.name}</td>
                <td>${list.shopTypeName}</td>
                <td>${list.firstCost}</td>
                <td>${list.shopkeeperPrice}</td>
                <td>${list.vipPrice}</td>
                <td>${list.inTimeToString()}</td>
                <td><c:if test="${list.isRecommend==0}">否</c:if><c:if test="${list.isRecommend==1}">是</c:if></td>
                <td><c:if test="${list.status==0}">上架</c:if><c:if test="${list.status==1}">下架</c:if></td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/shop/add?id=${list.id}" class="btn btn-default" data-toggle="dialog" data-width="900" data-height="600" data-id="dialog-mask" data-mask="true">编辑</a>
                    <a href="${pageContext.request.contextPath}/admin/shop/shangJia?id=${list.id}" class="btn btn-blue" data-toggle="doajax" data-confirm-msg="确定要上架吗？">上架</a>
                    <a href="${pageContext.request.contextPath}/admin/shop/xiaJia?id=${list.id}" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要下架吗？">下架</a>
                    <a href="${pageContext.request.contextPath}/admin/shop/delete?id=${list.id}" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除吗？">删除</a>
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
                <option value="5" <c:if test="${shopList.size==5}">selected</c:if>>5</option>
                <option value="10" <c:if test="${shopList.size==10}">selected</c:if>>10</option>
                <option value="20" <c:if test="${shopList.size==20}">selected</c:if>>20</option>
                <option value="50" <c:if test="${shopList.size==50}">selected</c:if>>50</option>

            </select>
        </div>
        <span>&nbsp;条，共 ${shopList.totalElements} 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination" data-total="${shopList.totalElements}" data-page-size="${shopList.size}" data-page-current="${shopList.number+1}">
    </div>
</div>
