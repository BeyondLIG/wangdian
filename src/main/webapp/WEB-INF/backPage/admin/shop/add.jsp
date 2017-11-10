<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Calendar c= Calendar.getInstance();
    SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String hello=sdf1.format(new Date());
%>
<style>
    .upload-photo{
        padding: 10px;
        border: 1px solid #ccc;
        width: 80%;
    }
    .upload-photo a{
        position: relative;
        display: block;
        width: 100%;
    }
    .upload-photo img{
        width: 100%;
    }
</style>
<div class="bjui-pageContent">
     <form action="${pageContext.request.contextPath}/admin/shop/add" data-toggle="validate" data-reload-navtab="true" method="post">
         <input type="hidden" name="isDel" value="0">
         <input type="hidden" name="id" value="${shop.id}">
         <div class="pageFormContent" data-layout-h="0">
             <div style="padding: 5px;"></div>
             <table class="table table-condensed table-hover" width="100%" >
                 <thead>
                 <label>商品信息</label>
                 </thead>
                 <tbody>
                       <tr >
                           <td colspan="2" style="width: 50%;">
                               <label class="control-label x100">编号：</label>
                               <input type="text" data-rule="required" id="number" name="number" value="${shop.number}" size="20">
                               <a href="#" id="checkNumber" >检测编号的唯一性</a>
                           </td>
                           <td colspan="2" >
                               <label class="control-label x100">商品名称：</label>
                               <input type="text" data-rule="required" name="name" value="${shop.name}" size="20">
                           </td>
                       </tr>
                       <tr >
                           <td colspan="2" style="width: 50%;">
                               <label class="control-label x100">商品类型：</label>
                               <input type="text" data-rule="required" name="shopTypeName" value="${shop.shopTypeName}" size="20">
                           </td>
                           <td colspan="2">
                               <label class="control-label x100">VIP价：</label>
                               <input type="text" data-rule="required" name="vipPrice" value="${shop.vipPrice}" size="20" id="vipPrice">元
                           </td>
                       </tr>

                       <tr>
                           <td colspan="2" style="width: 50%;">
                               <label class="control-label x100">成本价：</label>
                               <input type="text" data-rule="required" name="firstCost" value="${shop.firstCost}" size="20" id="firstCost">元
                           </td>
                           <td colspan="2">
                               <label class="control-label x100">店主价：</label>
                               <input type="text" name="shopkeeperPrice" value="${shop.shopkeeperPrice}" size="20" id="shopkeeperPrice">元
                               <a href="#" id="calculateShopkeeperPrice" >计算店主价</a>
                           </td>
                       </tr>
                       <tr>
                           <td colspan="2" style="width: 50%;">
                               <label class="control-label x100">销售价：</label>
                               <input type="text" data-rule="required" name="secondCost" value="${shop.secondCost}" size="20">元
                           </td>
                           <td colspan="2">
                               <label class="control-label x100">市场参考价：</label>
                               <input type="text" data-rule="required" name="pastPrice" value="${shop.pastPrice}" size="20">元
                           </td>
                       </tr>
                       <tr>
                           <td colspan="2">
                               <label class="control-label x100">入库时间：</label>
                               <input type="text" data-rule="required" name="shopInTime" <c:if test="${shop.inTime!=null}">value="${shop.inTimeToString()}"</c:if> <c:if test="${shop.inTime==null}">value="<%=hello%>"</c:if> size="20">

                           </td>
                       </tr>
                       <tr>
                           <td colspan="2">
                               <label class="control-label x100">是否推荐商品：</label>
                               <input type="radio" name="isRecommend" data-toggle="icheck" data-label="否" value="0" <c:if test="${shop.isRecommend==0}">checked</c:if><c:if test="${shop.isRecommend==null}">checked</c:if>/>
                               <input type="radio" name="isRecommend" data-toggle="icheck" data-label="是" value="1" <c:if test="${shop.isRecommend==1}">checked</c:if>/>
                           </td>
                       </tr>
                       <tr>
                           <td colspan="4">
                               <label class="control-label x100">商品描述：</label>
                               <textarea name="shopDescribe" style="width:644px;height:50px" data-toggle="autoheight" >${shop.shopDescribe}</textarea>
                           </td>
                       </tr>
                 </tbody>
             </table>

             <div style="padding: 10px;"></div>
             <table id="tabledit1" class="table table-bordered table-hover table-striped table-top" data-toggle="tabledit" data-initnum="0" data-single-noindex="true" width="100%">
                 <thead>
                 <label style="line-height: 28px;">商品属性信息</label>
                 <tr >
                     <th style="width: 4%;" align="center" title="No."><input type="text" readonly <%--name="mutantPhenotypeList[#index#].smpId"--%> class="no" data-rule="required" value="1" size="2"/></th>
                     <th style="width: 14%;" align="center" title="商品属性">
                         <input type="text" name="shopAttributesList[#index#].name" data-rule="required" value="" size="5"/>
                     </th>

                     <th style="width: 14%" align="center" title="属性值">
                         <input type="text" name="shopAttributesValueList[#index#].attributesValue" data-rule="required" value="" size="1"/>
                     </th>
                     <th style="width: 12%" align="center" title="" data-addtool="true">
                         <a href="javascript:;" class="btn btn-green" data-toggle="dosave">完成</a>
                         <a href="${pageContext.request.contextPath}/admin/shop/ajaxDone" class="btn btn-red row-del" data-confirm-msg="确定要删除该行信息吗？">删除</a>
                     </th>
                 </tr>
                 </thead>
                 <tbody>
                 <c:if test="${shopAttributesList!=null&&shopAttributesList.size()>0}">
                     <c:forEach items="${shopAttributesList}" var="list" varStatus="i">
                         <c:forEach items="${list.shopAttributesValueList}" var="value">
                             <tr data-id="1">
                                 <input type="hidden" name="shopAttributesList[${i.index}].id" value="${list.id}"/>
                                 <input type="hidden" name="shopAttributesValueList[${i.index}].id" value="${value.id}"/>
                                 <td data-id-val="${i.index+1}">${i.index+1}</td>
                                 <td>${list.name}</td>
                                 <td>${value.attributesValue}</td>
                                 <td data-noedit="true">
                                     <button type="button" class="btn-green" data-toggle="doedit">编辑</button>
                                     <a href="${pageContext.request.contextPath}/admin/shop/ajaxDone?id=${list.id}" class="btn btn-red row-del" data-confirm-msg="确定要删除该行信息吗？">删除</a>
                                 </td>
                             </tr>
                         </c:forEach>
                     </c:forEach>
                 </c:if>
                 </tbody>
             </table>
             <div class="alert alert-warning " style="font-size: 14px;margin-top: 10px;">注意：上述添加&编辑商品信息时，需要点击右下角的保存按钮，确定保存数据；否则不会保存数据！！！</div>


             <div class="alert alert-warning " style="font-size: 14px;margin-top: 16px;margin-bottom: 0px;">注意：下述添加图片时，需要点击右下角的保存按钮，确定保存图片数据；否则不会保存数据！！！（为了显示上的美观，建议图片长宽比接近为2:1）</div>
             <table class="table table-bordered table-hover table-striped table-top" width="100%">
                 <thead>
                 <label style="line-height: 28px;" align="center">商品首页图片</label>
                 </thead>
                 <tbody>
                 <tr>
                     <td>
                         <div  id="fileInput-custom"
                               data-toggle="upload"
                               data-uploader="${pageContext.request.contextPath}/admin/shop/upload"
                               data-button-text="选择上传图片"
                               data-file-size-limit="1024000000"
                               data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                               data-multi="false"
                               data-on-upload-success="doc_upload_success"
                               data-icon="cloud-upload">
                         </div>
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

             <div class="alert alert-warning " style="font-size: 14px;margin-top: 16px;margin-bottom: 0px;">注意：下述添加图片时，需要点击右下角的保存按钮，确定保存图片数据；否则不会保存数据！！！（为了显示上的美观，建议图片长宽比接近为2:1）</div>
             <table class="table table-bordered table-hover table-striped table-top" width="100%">
                 <thead>
                 <label style="line-height: 28px;" align="center">商品轮播图片展示</label>
                 </thead>
                 <tbody>
                 <tr>
                     <td>
                         <div  id="fileInput2"
                               data-toggle="upload"
                               data-uploader="${pageContext.request.contextPath}/admin/shop/upload"
                               data-button-text="选择上传图片"
                               data-file-size-limit="1024000000"
                               data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                               data-multi="false"
                               data-on-upload-success="show_upload_success"
                               data-icon="cloud-upload">
                         </div>
                           <span id="doc_show_pic" style="margin-top: 10px;">
                               <c:forEach items="${shopShowsList}" var="list" varStatus="x">
                                   <div class="shopShowsList${x.index} upload-photo">
                                        <a href="${pageContext.request.contextPath}/admin/shop/photo/delete?id=${list.id}&index=${x.index}&type=shopShowsList&urlPath=${list.urlPath}" data-callback="mycallback" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除吗？">删除</a>
                                        <img src="${pageContext.request.contextPath}/${list.urlPath}">
                                        <input type="hidden" name="shopShowsList[${x.index}].id" value="${list.id}">
                                        <input type="hidden" name="shopShowsList[${x.index}].urlPath" value="${list.urlPath}">
                                   </div>
                               </c:forEach>
                           </span>
                     </td>
                 </tr>
                 </tbody>
             </table>

             <div class="alert alert-warning " style="font-size: 14px;margin-top: 16px;margin-bottom: 0px;">注意：下述添加图片时，需要点击右下角的保存按钮，确定保存图片数据；否则不会保存数据！！！</div>
             <table class="table table-bordered table-hover table-striped table-top" width="100%">
                 <thead>
                 <label style="line-height: 28px;" align="center">商品详情图片</label>
                 </thead>
                 <tbody>
                 <tr>
                     <td>
                         <div  id="fileInput"
                               data-toggle="upload"
                               data-uploader="${pageContext.request.contextPath}/admin/shop/upload"
                               data-button-text="选择上传图片"
                               data-file-size-limit="1024000000"
                               data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                               data-multi="false"
                               data-on-upload-success="detail_upload_success"
                               data-icon="cloud-upload">
                         </div>
                           <span id="doc_detail_pic" style="margin-top: 10px;">
                               <c:forEach items="${shopPhotosList}" var="list" varStatus="y">
                                   <div class="shopPhotosList${y.index} upload-photo">
                                            <a href="${pageContext.request.contextPath}/admin/shop/photo/delete?id=${list.id}&index=${y.index}&type=shopPhotosList&urlPath=${list.urlPath}" data-callback="mycallback" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除吗？">删除</a>
                                            <img src="${pageContext.request.contextPath}/${list.urlPath}">
                                            <input type="hidden" name="shopPhotosList[${y.index}].id" value="${list.id}">
                                            <input type="hidden" name="shopPhotosList[${y.index}].urlPath" value="${list.urlPath}">
                                   </div>
                               </c:forEach>

                           </span>
                     </td>

                 </tr>
                 </tbody>
             </table>

             <div style="padding: 10px;"></div>
             <table class="table table-bordered table-hover table-striped table-top" width="100%">
                 <tbody>
                 <tr>
                     <td colspan="4">
                         <label class="control-label x100">状态：</label>
                         <input type="radio" name="status" data-toggle="icheck" data-label="上架" value="0" <c:if test="${shop.status==0}">checked</c:if><c:if test="${shop.status==null}">checked</c:if>/>
                         <input type="radio" name="status" data-toggle="icheck" data-label="下架" value="1" <c:if test="${shop.status==1}">checked</c:if>/>
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
        $.ajax({
            type:"POST",
            url:"${pageContext.request.contextPath}"+"/admin/shop/checkNumber",
            dataType:"json",
            data:{
                number:$("#number").val()
            },
            success:function (data) {
                $(this).bjuiajax('ajaxDone',data);
            },
            error:function (data) {
                alert("函数访问失败");
            }
        })
    });

    //发送异步请求，计算店主价
    $("#calculateShopkeeperPrice").on("click",function (e) {
        e.preventDefault();
        var $vipPrice = $("#vipPrice").val();
        var $firstCost = $("#firstCost").val();
        if($vipPrice==""){
            BJUI.alertmsg("error","请输入VIP价")
        }else if($firstCost == ""){
            BJUI.alertmsg("error","请输入成本价")
        }else{
            $.ajax({
                url: "/admin/shop/calculate/calculateShopkeeperPrice",
                method: "GET",
                data: {
                    vipPrice : $vipPrice,
                    firstCost: $firstCost
                },
                success: function (result) {
                    $("#shopkeeperPrice").val(result);
                }
            });
        }
    });


    //商品首页展示，一张
    function doc_upload_success(file, data) {
        var json = $.parseJSON(data);
        $(this).bjuiajax('ajaxDone', json);
        if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
            $('#doc_span_pic').html('<img src="${pageContext.request.contextPath}/'+ json.filepath +
                    '"  style="width: 80%;"> ' +
                    '<input type="hidden" name="firstPhoto" value="'+json.filepath+'">');
        }
    }
    if (${shopShowsList.size()==0||shopShowsList.size()==null}){
        x=0;
    }else {
        x="${shopShowsList.size()}";
    }
    //商品轮播展示，多张
    function show_upload_success(file, data) {
        var json = $.parseJSON(data);
        $(this).bjuiajax('ajaxDone', json);
        if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
            $('#doc_show_pic').append(
                    '<div class="shopShowsList'+x+' upload-photo">'+
                    "<a href='${pageContext.request.contextPath}/admin/shop/photo/delete?index="+x+"&type=shopShowsList&urlPath="+json.filepath+"' class='btn btn-red' data-callback='mycallback' data-toggle='doajax' data-confirm-msg='确定要删除吗？'>删除</a>"+
                    '<img src="${pageContext.request.contextPath}/'+ json.filepath + '">' +
                    '<input type="hidden" name="shopShowsList['+x+'].urlPath" value="'+json.filepath+'"><br>'+
                    '</div>');
            x++;
        }
    }
    if(${shopPhotosList.size()==0||shopPhotosList.size()==null}){
        y=0;
    }else {
        y="${shopPhotosList.size()}";
    }

    //商品详情展示，多张
    function detail_upload_success(file, data) {
        var json = $.parseJSON(data);
        $(this).bjuiajax('ajaxDone', json);
        if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
            $('#doc_detail_pic').append(
                    '<div class="shopPhotosList'+y+' upload-photo">'+
                    "<a href='${pageContext.request.contextPath}/admin/shop/photo/delete?index="+y+"&type=shopPhotosList&urlPath="+json.filepath+"' class='btn btn-red' data-callback='mycallback' data-toggle='doajax' data-confirm-msg='确定要删除吗？'>删除</a>"+
                    '<img src="${pageContext.request.contextPath}/'+ json.filepath + '">' +
                    '<input type="hidden" name="shopPhotosList['+y+'].urlPath" value="'+json.filepath+'"><br>' +
                    '</div>');
        y++;
        }
    }
    function mycallback(json) {
        $(this).bjuiajax('ajaxDone', json);// 信息提示
        $('.'+json.type+json.index).remove();
        console.log(json.type+json.index);
    }
</script>