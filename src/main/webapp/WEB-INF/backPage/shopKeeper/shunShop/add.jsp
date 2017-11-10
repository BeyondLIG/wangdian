<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="bjui-pageContent">
         <div class="pageFormContent" data-layout-h="0">
             <div style="padding: 5px;"></div>
             <table class="table table-condensed table-hover" width="100%" >
                 <thead>
                 <label>商品信息</label>
                 </thead>
                 <tbody>
                       <tr >
                           <td colspan="2" style="width: 50%;">
                               <label class="control-label x100">编号：</label>${shunShop.number}
                           </td>
                           <td colspan="2" >
                               <label class="control-label x100">商品名称：</label>${shunShop.name}
                           </td>
                       </tr>
                       <tr >
                           <td colspan="2" style="width: 50%;">
                               <label class="control-label x100">商品类型：</label>${shunShop.shopType}
                           </td>
                           <td colspan="2">
                               <label class="control-label x100">商品型号：</label>${shunShop.shopModel}
                           </td>
                       </tr>

                       <tr>
                           <td colspan="2" style="width: 50%;">
                               <label class="control-label x100">成本价：</label>${shunShop.firstCost}元
                           </td>
                           <td colspan="2">
                               <label class="control-label x100">销售价：</label>${shunShop.secondCost}元
                           </td>
                       </tr>

                       <tr>
                           <td colspan="4">
                               <label class="control-label x100">商品描述：</label>${shunShop.shopDescribe}
                           </td>
                       </tr>

                 </tbody>
             </table>

             <div style="padding: 10px;"></div>
             <table class="table table-hover table-striped table-top" width="100%">
                 <thead>
                 <label style="line-height: 28px;" align="center">瞬时秒杀时间设置</label>
                 </thead>
                 <tbody>
                 <tr>
                     <td colspan="2" style="width: 50%;">
                         <label class="control-label x100">开始时间：</label>${shunShop.startTimeToString()}
                     </td>
                 </tr>
                 <tr>
                     <td colspan="2">
                         <label class="control-label x100">活动时间：</label>${shunShop.day}天${shunShop.hours}小时${shunShop.minutes}分${shunShop.seconds}秒
                     </td>
                 </tr>
                 <tr>
                     <td colspan="2" style="width: 50%;">
                         <label class="control-label x100">瞬杀价：</label>${shunShop.thirdCost}元
                     </td>
                 </tr>
                 </tbody>
             </table>

             <div style="padding: 10px;"></div>
             <table id="tabledit1" class="table table-bordered table-hover table-striped table-top" data-toggle="tabledit" data-initnum="0" data-single-noindex="true" width="100%">
                 <thead>
                 <label style="line-height: 28px;">商品属性信息</label>
                 <tr>
                     <th style="width: 4%;" align="center" >No.</th>
                     <th style="width: 14%;" align="center">商品属性</th>
                     <th style="width: 14%" align="center">属性值</th>
                 </tr>
                 </thead>
                 <tbody>
                 <c:if test="${shunShopAttributesList!=null&&shunShopAttributesList.size()>0}">
                     <c:forEach items="${shunShopAttributesList}" var="list" varStatus="i">
                         <c:forEach items="${list.shunShopAttributesValueList}" var="value">
                             <tr data-id="1">
                                 <td data-id-val="${i.index+1}">${i.index+1}</td>
                                 <td>${list.name}</td>
                                 <td>${value.attributesValue}</td>
                                 <%--<td data-noedit="true">
                                     <button type="button" class="btn-green" data-toggle="doedit">编辑</button>
                                     <a href="${pageContext.request.contextPath}/admin/shunShop/ajaxDone?id=${list.id}" class="btn btn-red row-del" data-confirm-msg="确定要删除该行信息吗？">删</a>
                                 </td>--%>
                             </tr>
                         </c:forEach>
                     </c:forEach>
                 </c:if>
                 </tbody>
             </table>

             <div style="padding: 10px;"></div>
             <table class="table table-bordered table-hover table-striped table-top" width="100%">
                 <thead>
                 <label style="line-height: 28px;" align="center">瞬时秒杀首页图片</label>
                 </thead>
                 <tbody>
                 <tr>
                     <td>
                           <span id="doc_span_pic" style="margin-top: 10px;">
                               <c:if test="${shunShop.firstPhoto!=null&&!shunShop.firstPhoto.equals('')}">
                                   <img src="${pageContext.request.contextPath}/${shunShop.firstPhoto}" style="width: 80%;">
                                   <input type="hidden" name="firstPhoto" value="${shunShop.firstPhoto}">
                               </c:if>
                           </span>
                     </td>
                 </tr>
                 </tbody>
             </table>

             <div style="padding: 10px;"></div>
             <table class="table table-bordered table-hover table-striped table-top" width="100%">
                 <thead>
                 <label style="line-height: 28px;" align="center">瞬杀商品轮播图片展示</label>
                 </thead>
                 <tbody>
                 <tr>
                     <td>
                           <span id="doc_show_pic" style="margin-top: 10px;">
                               <c:forEach items="${shunShopShowsList}" var="list" varStatus="x">
                                   <img src="${pageContext.request.contextPath}/${list.urlPath}" style="width: 80%;">
                                   <input type="hidden" name="shunShopShowsList[${x.index}].id" value="${list.id}">
                                   <input type="hidden" name="shunShopShowsList[${x.index}].urlPath" value="${list.urlPath}">
                               </c:forEach>
                           </span>
                     </td>
                 </tr>
                 </tbody>
             </table>

             <div style="padding: 10px;"></div>
             <table class="table table-bordered table-hover table-striped table-top" width="100%">
                 <thead>
                 <label style="line-height: 28px;" align="center">商品详情图片</label>
                 </thead>
                 <tbody>
                 <tr>
                     <td>
                           <span id="doc_detail_pic" style="margin-top: 10px;">
                               <c:forEach items="${shunShopPhotosList}" var="list" varStatus="y">
                                   <img src="${pageContext.request.contextPath}/${list.urlPath}" style="width: 80%;">
                                   <input type="hidden" name="shunShopPhotosList[${y.index}].id" value="${list.id}">
                                   <input type="hidden" name="shunShopPhotosList[${y.index}].urlPath" value="${list.urlPath}">
                               </c:forEach>

                           </span>
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
