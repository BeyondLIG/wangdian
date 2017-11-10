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
                               <label class="control-label x100">编号：</label>${shop.number}
                           </td>
                           <td colspan="2" >
                               <label class="control-label x100">商品名称：</label>${shop.name}
                           </td>
                       </tr>
                       <tr >
                           <td colspan="2" style="width: 50%;">
                               <label class="control-label x100">商品类型：</label>${shop.shopTypeName}
                           </td>
                           <td colspan="2">
                               <label class="control-label x100">商品型号：</label>${shop.shopModel}
                           </td>
                       </tr>

                       <tr>
                           <td colspan="2" style="width: 50%;">
                               <label class="control-label x100">成本价：</label>${shop.firstCost}
                           </td>
                           <td colspan="2">
                               <label class="control-label x100">销售价：</label>${shop.secondCost}
                           </td>
                       </tr>

                       <tr>
                           <td colspan="4">
                               <label class="control-label x100">商品描述：</label>${shop.shopDescribe}
                           </td>
                       </tr>
                       <tr>
                           <td colspan="4">
                               <label class="control-label x100">是否推荐商品：</label><c:if test="${shop.isRecommend==0}">否</c:if><c:if test="${shop.isRecommend==1}">是</c:if>
                           </td>
                       </tr>
                 </tbody>
             </table>

             <div style="padding: 10px;"></div>
             <table id="tabledit1" class="table table-bordered table-hover table-striped table-top" data-toggle="tabledit" data-initnum="0" data-single-noindex="true" width="100%">
                 <thead>
                 <label style="line-height: 28px;">商品属性信息</label>
                 <tr data-idname="shopAttributesList[#index#].id">
                     <th style="width: 4%;" align="center" >No.</th>
                     <th style="width: 14%;" align="center">商品属性</th>
                     <th style="width: 14%" align="center">属性值</th>
                 </tr>
                 </thead>
                 <tbody>
                 <c:if test="${shopAttributesList!=null&&shopAttributesList.size()>0}">
                     <c:forEach items="${shopAttributesList}" var="list" varStatus="i">
                         <c:forEach items="${list.shopAttributesValueList}" var="value">
                             <tr data-id="1">
                                 <td data-id-val="${i.index+1}">${i.index+1}</td>
                                 <td>${list.name}</td>
                                 <td>${value.attributesValue}</td>
                                <%-- <td data-noedit="true">
                                     <button type="button" class="btn-green" data-toggle="doedit">编辑</button>
                                     <a href="${pageContext.request.contextPath}/admin/shop/ajaxDone?id=${list.id}" class="btn btn-red row-del" data-confirm-msg="确定要删除该行信息吗？">删</a>
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
                 <label style="line-height: 28px;" align="center">商品首页图片</label>
                 </thead>
                 <tbody>
                 <tr>
                     <td>
                           <span id="doc_span_pic" style="margin-top: 10px;">
                               <c:if test="${shop.firstPhoto!=null&&!shop.firstPhoto.equals('')}">
                                   <img src="${pageContext.request.contextPath}/${shop.firstPhoto}" style="width: 80%;">
                                   <input type="hidden" name="firstPhoto" value="${shop.firstPhoto}">
                               </c:if>
                           </span>
                     </td>
                 </tr>
                 </tbody>
             </table>

             <div style="padding: 10px;"></div>
             <table class="table table-bordered table-hover table-striped table-top" width="100%">
                 <thead>
                 <label style="line-height: 28px;" align="center">商品轮播图片展示</label>
                 </thead>
                 <tbody>
                 <tr>
                     <td>
                           <span id="doc_show_pic" style="margin-top: 10px;">
                               <c:forEach items="${shopShowsList}" var="list" varStatus="x">
                                   <img src="${pageContext.request.contextPath}/${list.urlPath}" style="width: 80%;">
                                   <input type="hidden" name="shopShowsList[${x.index}].id" value="${list.id}">
                                   <input type="hidden" name="shopShowsList[${x.index}].urlPath" value="${list.urlPath}">
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
                               <c:forEach items="${shopPhotosList}" var="list" varStatus="y">
                                   <img src="${pageContext.request.contextPath}/${list.urlPath}" style="width: 80%;">
                                   <input type="hidden" name="shopPhotosList[${y.index}].id" value="${list.id}">
                                   <input type="hidden" name="shopPhotosList[${y.index}].urlPath" value="${list.urlPath}">
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


