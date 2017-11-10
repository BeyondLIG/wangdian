<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    long no=0;
    SimpleDateFormat sdf=new SimpleDateFormat("yyyymmdd");
    String nowDate=sdf.format(new Date());
    no=Long.parseLong(nowDate)*1000;
    no+=001;
    String userId="123";
    String orderId=userId+no;

    Calendar c=Calendar.getInstance();
    SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String hello=sdf1.format(new Date());
%>

<div class="bjui-pageContent">
     <form action="${pageContext.request.contextPath}/admin/orders/add" data-toggle="validate" data-reload-navtab="true" method="post">
         <input type="hidden" name="isDel" value="0">
         <input type="hidden" name="id" value="${orders.id}">
         <input type="hidden" name="isDelFromUser" value="${orders.isDelFromUser}">
         <%--<input type="hidden" name="photo" value="${orders.photo}">--%>
         <div class="pageFormContent" data-layout-h="0">
             <div style="padding: 5px;"></div>
             <table class="table table-condensed table-hover" width="100%" >
                 <thead>
                 <label>订单信息</label>
                 </thead>
                 <tbody>
                       <tr >
                           <td colspan="2" style="width: 50%;">
                               <label class="control-label x100">订单号：</label>
                               <input type="text" data-rule="required" id="shopOrderId" name="shopOrderId" <c:if test="${orders.shopOrderId!=null}">value="${orders.shopOrderId}"</c:if> <c:if test="${orders.shopOrderId==null}">value="<%=orderId%>"</c:if>  size="20">
                               <%--<a href="#" id="checkNumber" >检测编号的唯一性</a>--%>
                           </td>
                           <td colspan="2">
                               <label class="control-label x100">下订时间：</label>
                               <input type="text" data-rule="required" name="orderTime" <c:if test="${orders.shopOrderTime!=null}">value="${orders.shopOrderTimeToString()}"</c:if> <c:if test="${orders.shopOrderTime==null}">value="<%=hello%>"</c:if> size="20">
                           </td>
                       </tr>

                       <tr>
                           <td colspan="2">
                               <label class="control-label x100">下订人：</label>
                               <input type="text" data-rule="required" name="shopOrderMan" value="${orders.shopOrderMan}" size="20">
                           </td>
                           <td colspan="2">
                               <label class="control-label x100">下订人电话：</label>
                               <input type="text" data-rule="required" name="telephone" value="${orders.telephone}" size="20">
                           </td>

                       </tr>
                       <tr>
                            <td colspan="4">
                                <label class="control-label x100">订单总价：</label>
                                <input type="text" data-rule="required" name="ordersCost" value="${orders.ordersCost}" size="20">元
                            </td>
                       </tr>
                       <tr>
                           <td colspan="2">
                               <label class="control-label x100">收货人：</label>
                               <input type="text" data-rule="required" name="receiver" value="${orders.receiver}" size="20">
                           </td>
                           <td colspan="2">
                               <label class="control-label x100">收货人电话：</label>
                               <input type="text" data-rule="required" name="receiverPhone" value="${orders.receiverPhone}" size="20">
                           </td>
                       </tr>
                       <tr>
                           <td colspan="4">
                               <label class="control-label x100">收货人地址：</label>
                               <input type="text" data-rule="required" name="address" value="${orders.address}" size="63">
                           </td>
                       </tr>
                       <tr>
                           <td colspan="4" >
                               <label class="control-label x100">买家留言：</label>
                               <input type="text" name="liuYan" value="${orders.liuYan}" size="63">
                           </td>
                       </tr>
                 </tbody>
             </table>

             <div style="padding: 10px;"></div>

             <label>商品信息</label>
             <c:forEach items="${orders.shopCartList}" var="list">
                 <table class="table table-condensed table-hover" width="100%" >
                     <tbody>
                     <tr>
                         <td colspan="2" style="width: 50%;">
                             <label class="control-label x100">商品编号：</label>
                                 <input type="text" disabled value="${list.shopNumber}" size="20">
                         </td>
                         <td colspan="2" >
                             <label class="control-label x100">商品名称：</label>
                                 <input type="text" disabled value="${list.shopName}" size="20">
                         </td>
                     </tr>
                     <tr >
                         <td colspan="2" style="width: 50%;">
                             <label class="control-label x100">商品类型：</label>
                                 <input type="text" disabled value="${list.shopType}" size="20">
                         </td>
                         <td colspan="2">
                             <label class="control-label x100">商品型号：</label>
                                 <input type="text" disabled value="${list.shopModel}" size="20">
                         </td>
                     </tr>
                     <tr>
                         <td colspan="2" style="width: 50%;">
                             <label class="control-label x100">购买数量：</label>
                                 <input type="text" disabled value="${list.count}" size="20">件
                         </td>
                         <td colspan="2" style="width: 50%;">
                             <label class="control-label x100">是否瞬杀商品：</label>
                             <c:if test="${list.type.equals('shunShop')}">
                                 <input type="text" disabled value="是" size="20">
                             </c:if>
                             <c:if test="${list.type.equals('shop')}">
                                 <input type="text" disabled value="否" size="20">
                             </c:if>

                         </td>
                     </tr>
                     <tr>
                         <td colspan="2" style="width: 50%;">
                             <label class="control-label x100">单价成本价：</label>
                             <input type="text" disabled value="${list.firstCost}" size="20">元
                         </td>
                         <td colspan="2">
                             <label class="control-label x100">总成本价：</label>
                             <input type="text" disabled value="${list.allFirstCost}" size="20">元
                         </td>
                     </tr>
                     <tr>
                         <td colspan="2" style="width: 50%;">
                             <label class="control-label x100">单价销售价：</label>
                             <input type="text" disabled value="${list.secondCost}" size="20">元
                         </td>
                         <td colspan="2">
                             <label class="control-label x100">总销售价：</label>
                             <input type="text" disabled value="${list.allSecondCost}" size="20">元
                         </td>
                     </tr>
                     <tr>
                         <td colspan="2" style="width: 50%;">
                             <label class="control-label x100">单价利润价：</label>
                             <input type="text" disabled value="${list.profits}" size="20">元
                         </td>
                         <td colspan="2">
                             <label class="control-label x100">总利润价：</label>
                             <input type="text" disabled value="${list.allProfits}" size="20">元
                         </td>
                     </tr>
                     <tr >
                         <td colspan="4" style="width: 50%;">
                             <label class="control-label x100">商品详情：</label>
                                 <input type="text" name="detail" value="${list.detail}" size="63">
                         </td>
                     </tr>
                     <tr>
                         <td colspan="4" >
                             <hr>
                         </td>
                     </tr>
                     </tbody>
                 </table>
             </c:forEach>

             <div style="padding: 10px;"></div>

             <table class="table table-condensed table-hover" width="100%" >
                 <thead>
                 <label>其他信息</label>
                 </thead>
                 <tbody>
                 <tr >
                     <td colspan="2" style="width: 50%;">
                         <label class="control-label x100">总成本价：</label>
                         <input type="text" data-rule="required" name="firstCost" value="${orders.firstCost}" size="20">元
                     </td>
                     <td colspan="2" >
                         <label class="control-label x100">总销售价：</label>
                         <input type="text" data-rule="required" name="secondCost" value="${orders.ordersCost}" size="20">元
                     </td>
                 </tr>
                 <tr>
                     <td colspan="2" style="width: 50%;">
                         <label class="control-label x100">利润：</label>
                         <input type="text" data-rule="required" name="profits" value="${orders.profits}" size="20">元
                     </td>
                     <td colspan="2">
                         <label class="control-label x100">店主：</label>
                         <c:if test="${orders.shopKeeper!=null}">
                            <input type="text" data-rule="required" name="shopKeeper" value="${orders.shopKeeper}" size="20">
                         </c:if>
                        <c:if test="${orders.shopKeeper==null}">
                            <input type="text" data-rule="required" name="shopKeeper" value="无"  size="20">
                         </c:if>
                     </td>
                 </tr>
                 <tr>
                     <td colspan="4" >
                     </td>
                 </tr>
                 </tbody>
             </table>

             <div style="padding: 10px;"></div>
             <table class="table table-bordered table-hover" width="100%">
                 <thead>
                 <label>快递信息</label>
                 </thead>
                 <tbody>
                 <tr>
                     <td colspan="2">
                         <label class="control-label x100">快递单号：</label>
                         <input type="text" name="kuaiDiDanHao" value="${orders.kuaiDiDanHao}" size="20">
                     </td>
                     <td colspan="2">
                         <label class="control-label x100">运费：</label>
                         <input type="text" data-rule="required" name="yunFei" value="${orders.yunFei}" size="20">元
                     </td>
                 </tr>
                 </tbody>
             </table>

             <div style="padding: 10px;"></div>
             <table class="table table-bordered table-hover table-striped table-top" width="100%">
                 <tbody>
                 <tr>
                     <td colspan="2">
                         <label class="control-label x100">状态：</label>
                         <input type="radio" name="status" data-toggle="icheck" data-label="提交订单" value="0" <c:if test="${orders.status==0}">checked</c:if><c:if test="${orders.status==null}">checked</c:if>/>　　
                         <input type="radio" name="status" data-toggle="icheck" data-label="已付款未发货" value="1" <c:if test="${orders.status==1}">checked</c:if>/>　　
                         <input type="radio" name="status" data-toggle="icheck" data-label="已付款已发货" value="2" <c:if test="${orders.status==2}">checked</c:if>/>　　
                         <input type="radio" name="status" data-toggle="icheck" data-label="已确认收货" value="3" <c:if test="${orders.status==3}">checked</c:if>/>　　
                         <input type="radio" name="status" data-toggle="icheck" data-label="申请退款中" value="4" <c:if test="${orders.status==4}">checked</c:if>/>　　
                         <input type="radio" name="status" data-toggle="icheck" data-label="已退款" value="5" <c:if test="${orders.status==5}">checked</c:if>/>　　
                         <input type="radio" name="status" data-toggle="icheck" data-label="已取消订单" value="6" <c:if test="${orders.status==6}">checked</c:if>/>　　
                     </td>
                 </tr>
                 </tbody>
             </table>

         </div>
     </form>
</div>
<div class="bjui-pageFooter">
       <ul>
           <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
           <li><button type="submit" class="btn-default" data-icon="save">保存</button></li>
       </ul>
</div>
<script>
    $("#checkNumber").on("click",function () {
//        alert("hello");
        $.ajax({
            type:"POST",
            url:"${pageContext.request.contextPath}"+"/admin/shop/checkNumber",
            dataType:"json",
            data:{
                number:$("#number").val()
            },

            success:function (data) {
//                alert(data.message);
                $(this).bjuiajax('ajaxDone',data);
            },
            error:function (data) {
                alert("函数访问失败");
            }
        })
    })
</script>

