<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/admin/profit/list"
          method="post">
        <div class="bjui-searchBar">
            <label>店主：</label><input type="text" name="shopKeeper" value="${shopKeeper}" class="form-control"
                                     size="10"/>
            <label>起止时间：</label><input type="text" id="startTime" name="startTime" data-toggle="datepicker"
                                       value="${startTime}" class="form-control" size="12"/>
            <label>结束时间：</label><input type="text" id="endTime" name="endTime" data-toggle="datepicker"
                                       value="${endTime}" class="form-control" size="12"/>
            注意：起止时间与结束同时填，才能准确搜索！！！
            <button type="submit" class="btn-default" data-icon="search">查询</button>
            <a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true"
               data-icon="undo">清空查询</a>
        </div>
    </form>
</div>

<div class="bjui-pageContent">
    <div class="alert alert-warning " style="font-size: 14px;margin-top: 10px;">注意：以下的商品信息都是来自已经确认收货的订单，不包括其他状态的订单！！！
    </div>

    <div class="pageFormContent" data-layout-h="0">
        <table class="table table-bordered table-hover" style="margin: 1em 0">
            <thead>
            </thead>
            销售额信息
            <tbody>
            <tr>
                <td colspan="2" style="width: 50%;">
                    <label class="control-label x150">总销售额：</label>${allCost}
                </td>
                <td colspan="2">
                    <label class="control-label x150">总利润：</label>${allProfit}
                </td>
            </tr>
            <tr>
                <td colspan="2" style="width: 50%;">
                    <label class="control-label x150">年费总收入：</label>${allAnnualFeeProfit}
                </td>
            </tr>
            </tbody>
        </table>

        <table class="table table-bordered table-hover" width="100%">
            <thead>
            <label>订单统计信息（以个数为单位）</label>
            </thead>
            <tbody>
            <tr>
                <td colspan="2" style="width: 50%;">
                    <label class="control-label x150">已提交订单的订单数：</label>${tiJiaDingDanCount}
                </td>
                <td colspan="2">
                    <label class="control-label x150">已付款未发货的订单数：</label>${yiFuKuanWeiFaHuoCount}
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <label class="control-label x150">已付款已发货的订单数：</label>${yiFuKuanYiFaHuoCount}
                </td>
                <td colspan="2">
                    <label class="control-label x150">已确认收货的订单数：</label>${yiQueShouHuoCount}
                </td>

            </tr>
            <tr>
                <td colspan="2">
                    <label class="control-label x150">申请退款中的订单数：</label>${shenQingTuiHuoZhongCount}
                </td>
                <td colspan="2">
                    <label class="control-label x150">已退款的订单数：</label>${yiKuanCount}
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <label class="control-label x150">已缴纳年费订单数：</label>${annualFeeOrderCountWhichHasBeenPaid}
                </td>
                <td colspan="2">
                    <label class="control-label x150">未缴纳年费订单数：</label>${annaulFeeOrdersCountWhichNotPaid}
                </td>
            </tr>
            </tbody>
        </table>
        <div style="padding: 10px;"></div>
    </div>
</div>

