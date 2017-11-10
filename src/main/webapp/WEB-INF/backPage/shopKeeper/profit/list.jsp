<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="bjui-pageContent">
    <div class="alert alert-warning " style="font-size: 14px;margin-top: 10px;">注意：以下的收益信息都是来自已经确认收货的订单，不包括其他状态的订单！！！</div>
    <div class="pageFormContent" data-layout-h="0">
        <table id="tabledit1" class="table table-bordered table-hover table-striped table-top" width="100%">
            <thead>
            <label style="line-height: 28px;">收益信息（信息来自已经确认收货的订单）</label>
            <tr>
                <th align="center">店主</th>
                <th align="center">截止目前总收益（元）</th>
                <th align="center">已提现（元）</th>
                <th align="center">未提现（元）</th>
                <th align="center">消息</th>
                <th align="center">操作</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${shopKeeper}</td>
                <td>${allProfit}</td>
                <td>${yiTiXian}</td>
                <td>
                    <c:if test="${allProfit!=0}">
                        <fmt:formatNumber type="number" pattern="#.#" value="${allProfit-yiTiXian}" />元</c:if>
                    <c:if test="${allProfit==0}">0元</c:if>
                </td>
                <td>${message}</td>
                <td data-noedit="true">
                    <a href="${pageContext.request.contextPath}/admin/shopKeeper/profit/tiXian?shopKeeper=${shopKeeper}" class="btn btn-default"
                       data-toggle="dialog" data-width="400" data-height="280" data-id="dialog-mask" data-mask="true">提现</a>
                </td>
            </tr>
            </tbody>
        </table>
        <c:if test="${errorMessage!=null&&!errorMessage.equals('')}">
            <div class="alert alert-warning " style="font-size: 14px;margin-top: 16px;margin-bottom: 0px;">${errorMessage}</div>
        </c:if>
    </div>
    <div style="font-size: 14px; margin-top: 10px; padding-left: 10px">
    </div>

    <div style="margin-top: 20px">
        <ul id="layout-tree" class="ztree" data-toggle="ztree" data-expand-all="false" data-on-click="do_open_layout">
            <li data-id="1" data-pid="0">收益详情</li>
            <li data-id="10" data-pid="1" data-url="${pageContext.request.contextPath}/admin/shopKeeper/annualFeeIncome" data-divid="#layout">年费详情</li>
            <li data-id="11" data-pid="1" data-url="${pageContext.request.contextPath}/admin/shopKeeper/goodsIncome" data-divid="#layout">商品销售</li>
        </ul>
    </div>
    <div style="margin-top: 10px; height:99.9%; overflow:hidden;">
        <div style="height:50%; overflow:hidden;">
            <fieldset style="height:100%;">
                <legend>详情区</legend>
                <div id="layout" style="height:94%; overflow:hidden;">
                </div>
            </fieldset>
        </div>
    </div>
</div>

<script type="text/javascript">
    function do_open_layout(event, treeId, treeNode) {
        if (treeNode.isParent) {
            var zTree = $.fn.zTree.getZTreeObj(treeId)

            zTree.expandNode(treeNode)
            return
        }
        $(event.target).bjuiajax('doLoad', {
            url: treeNode.url,
            target: treeNode.divid
        })

        event.preventDefault()
    }
</script>