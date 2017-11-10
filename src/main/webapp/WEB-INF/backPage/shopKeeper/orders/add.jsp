<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="bjui-pageContent">
             <div style="padding: 5px;"></div>
             <table class="table table-condensed table-hover" width="100%" >
                 <thead>
                 <label>订单信息</label>
                 </thead>
                 <tbody>
                       <tr >
                           <td colspan="2" style="width: 50%;">
                               <label class="control-label x100">订单号：</label>${orders.shopOrderId}
                           </td>
                           <td colspan="2">
                               <label class="control-label x100">下订时间：</label>${orders.shopOrderTime}
                           </td>
                       </tr>

                       <tr>
                           <td colspan="2">
                               <label class="control-label x100">下订人：</label>${orders.shopOrderMan}
                           </td>
                           <td colspan="2">
                               <label class="control-label x100">下订人电话：</label>${orders.telephone}
                           </td>

                       </tr>
                       <tr>
                           <td colspan="4">
                               <label class="control-label x100">订单总价：</label>${orders.ordersCost}
                           </td>
                       </tr>
                       <tr>
                           <td colspan="2">
                               <label class="control-label x100">收货人：</label>${orders.receiver}
                           </td>
                           <td colspan="2">
                               <label class="control-label x100">收货人电话：</label>${orders.receiverPhone}
                           </td>
                       </tr>
                       <tr>
                           <td colspan="4">
                               <label class="control-label x100">下订人地址：</label>${orders.address}
                           </td>
                       </tr>
                       <tr>
                           <td colspan="4" >
                               <label class="control-label x100">买家留言：</label>${orders.liuYan}
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
                    <label class="control-label x100">商品编号：</label>${list.shopNumber}
                </td>
                <td colspan="2" >
                    <label class="control-label x100">商品名称：</label>${list.shopName}
                </td>
            </tr>
            <tr >
                <td colspan="2" style="width: 50%;">
                    <label class="control-label x100">商品类型：</label>${list.shopType}
                </td>
                <td colspan="2">
                    <label class="control-label x100">商品型号：</label>${list.shopModel}
                </td>
            </tr>
            <tr>
                <td colspan="2" style="width: 50%;">
                    <label class="control-label x100">购买数量：</label>${list.count}件
                </td>
                <td colspan="2" style="width: 50%;">
                    <label class="control-label x100">是否瞬杀商品：</label>
                    <c:if test="${list.type.equals('shunShop')}">
                        <span>是</span>
                    </c:if>
                    <c:if test="${list.type.equals('shop')}">
                        <span>否</span>
                    </c:if>

                </td>
            </tr>

            <!--
            <tr>
                <td colspan="2" style="width: 50%;">
                    <label class="control-label x100">单价成本价：</label>${list.firstCost}元
                </td>
                <td colspan="2">
                    <label class="control-label x100">总成本价：</label>${list.allFirstCost}元
                </td>
            </tr>
            <tr>
                <td colspan="2" style="width: 50%;">
                    <label class="control-label x100">单价销售价：</label>${list.secondCost}元
                </td>
                <td colspan="2">
                    <label class="control-label x100">总销售价：</label>${list.allSecondCost}元
                </td>
            </tr>
            <tr>
                <td colspan="2" style="width: 50%;">
                    <label class="control-label x100">单价利润价：</label>${list.profits}元
                </td>
                <td colspan="2">
                    <label class="control-label x100">总利润价：</label>${list.allProfits}元
                </td>
            </tr>
            <tr >
                <td colspan="4" style="width: 50%;">
                    <label class="control-label x100">商品详情：</label>${list.detail}
                </td>
            </tr>
            <tr>
                <td colspan="4" >
                    <hr>
                </td>
            </tr>
            -->

            </tbody>
        </table>
    </c:forEach>

             <div style="padding: 10px;"></div>
    <!--
    <table class="table table-condensed table-hover" width="100%" >
        <thead>
        <label>其他信息</label>
        </thead>
        <tbody>
        <tr >
            <td colspan="2" style="width: 50%;">
                <label class="control-label x100">总成本价：</label>${orders.firstCost}元
            </td>
            <td colspan="2" >
                <label class="control-label x100">总销售价：</label>${orders.ordersCost}元
            </td>
        </tr>
        <tr >
            <td colspan="2" style="width: 50%;">
                <label class="control-label x100">利润：</label>${orders.profits}元
            </td>
            <td colspan="2">
                <label class="control-label x100">店主：</label>${orders.shopKeeper}
            </td>
        </tr>
        <tr>
            <td colspan="4" >
            </td>
        </tr>
        </tbody>
    </table>
    -->

             <div style="padding: 10px;"></div>
             <table class="table table-condensed table-hover" width="100%">
                 <thead>
                 <label>快递信息</label>
                 </thead>
                 <tbody>
                 <tr>
                     <td colspan="2">
                         <label class="control-label x100">快递单号：</label>${orders.kuaiDiDanHao}
                     </td>
                     <td colspan="2">
                         <label class="control-label x100">运费：</label>${orders.yunFei}元
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
       </ul>
</div>


