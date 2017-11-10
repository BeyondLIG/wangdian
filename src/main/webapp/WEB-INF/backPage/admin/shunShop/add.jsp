<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
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
     <form action="${pageContext.request.contextPath}/admin/shunShop/add" data-toggle="validate" data-reload-navtab="true" method="post">
         <input type="hidden" name="isDel" value="0">
         <input type="hidden" name="id" value="${shunShop.id}">
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
                               <input type="text" data-rule="required" id="number" name="number" value="${shunShop.number}" size="20">
                               <a href="#" id="checkNumber" >检测编号的唯一性</a>
                           </td>
                           <td colspan="2" >
                               <label class="control-label x100">商品名称：</label>
                               <input type="text" data-rule="required" name="name" value="${shunShop.name}" size="20">
                           </td>
                       </tr>
                       <tr >
                           <td colspan="2" style="width: 50%;">
                               <label class="control-label x100">商品类型：</label>
                               <input type="text" data-rule="required" name="shopType" value="${shunShop.shopType}" size="20">
                           </td>
                           <td colspan="2">
                               <label class="control-label x100">入库时间：</label>
                               <input type="text" data-rule="required" name="shunShopInTime" <c:if test="${shunShop.inTime!=null}">value="${shunShop.inTimeToString()}"</c:if> <c:if test="${shunShop.inTime==null}">value="<%=hello%>"</c:if> size="20">

                           </td>
                       </tr>

                       <tr>
                           <td colspan="2" style="width: 50%;">
                               <label class="control-label x100">成本价：</label>
                               <input type="text" data-rule="required" name="firstCost" value="${shunShop.firstCost}" size="20">元
                           </td>
                           <td colspan="2">
                               <label class="control-label x100">销售价：</label>
                               <input type="text" data-rule="required" name="secondCost" value="${shunShop.secondCost}" size="20">元
                           </td>
                       </tr>
                       <tr>
                           <td colspan="4">
                               <label class="control-label x100">商品描述：</label>
                               <textarea name="shopDescribe" style="width:644px;height:50px" data-toggle="autoheight" >${shunShop.shopDescribe}</textarea>
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
                         <label class="control-label x100">开始时间：</label>
                         <input type="text" name="shunShopStartTime" id="shunShopStartTime" data-toggle="datepicker" value="${shunShop.startTimeToString()}" class="form-control" size="20" />
                     </td>
                 </tr>
                 <tr>
                     <td colspan="2">
                         <label class="control-label x100">活动时间：</label>
                         <select name='day' class="selectpicker" data-toggle='selectpicker' data-width="80" data-rule="required">
                             <option value="" <c:if test="${shunShop.day==null}">selected</c:if> >--请选择--</option>
                             <c:forEach begin="0" end="31" varStatus="i">
                                 <option value="${i.index}" <c:if test="${shunShop.day==i.index}">selected</c:if>>${i.index}</option>
                             </c:forEach>
                         </select>天
                         <select name='hours' class="selectpicker" data-toggle='selectpicker' data-width="80" data-rule="required">
                             <option value="" <c:if test="${shunShop.hours==null}">selected</c:if> >--请选择--</option>
                             <c:forEach begin="0" end="24" varStatus="i">
                                 <option value="${i.index}" <c:if test="${shunShop.hours==i.index}">selected</c:if>>${i.index}</option>
                             </c:forEach>
                         </select>小时
                         <select name='minutes' class="selectpicker" data-toggle='selectpicker' data-width="80" data-rule="required">
                             <option value="" <c:if test="${shunShop.minutes==null}">selected</c:if> >--请选择--</option>
                             <c:forEach begin="0" end="60" varStatus="i">
                                 <option value="${i.index}" <c:if test="${shunShop.minutes==i.index}">selected</c:if>>${i.index}</option>
                             </c:forEach>
                         </select>分
                         <select name='seconds' class="selectpicker" data-toggle='selectpicker' data-width="80" data-rule="required">
                             <option value="" <c:if test="${shunShop.seconds==null}">selected</c:if> >--请选择--</option>
                             <c:forEach begin="0" end="60" varStatus="i">
                                 <option value="${i.index}" <c:if test="${shunShop.seconds==i.index}">selected</c:if>>${i.index}</option>
                             </c:forEach>
                         </select>秒
                     </td>
                 </tr>
                 <tr>
                     <td colspan="2" style="width: 50%;">
                         <label class="control-label x100">瞬杀价：</label>
                         <input type="text" data-rule="required" name="thirdCost" value="${shunShop.thirdCost}" size="20">元
                     </td>
                 </tr>
                 </tbody>
             </table>

             <div style="padding: 10px;"></div>
             <table id="tabledit1" class="table table-bordered table-hover table-striped table-top" data-toggle="tabledit" data-initnum="0" data-single-noindex="true" width="100%">
                 <thead>
                 <label style="line-height: 28px;">商品属性信息</label>
                 <tr data-idname="shunShopAttributesList[#index#].id">
                     <th style="width: 4%;" align="center" title="No."><input type="text" readonly <%--name="mutantPhenotypeList[#index#].smpId"--%> class="no" data-rule="required" value="1" size="2"/></th>
                     <th style="width: 14%;" align="center" title="商品属性">
                         <input type="text" name="shunShopAttributesList[#index#].name" data-rule="required" value="" size="5"/>
                     </th>

                     <th style="width: 14%" align="center" title="属性值">
                         <input type="text" name="shunShopAttributesValueList[#index#].attributesValue" data-rule="required" value="" size="1"/>
                     </th>
                     <th style="width: 12%" align="center" title="" data-addtool="true">
                         <a href="javascript:;" class="btn btn-green" data-toggle="dosave">完成</a>
                         <a href="${pageContext.request.contextPath}/admin/shunShop/ajaxDone" class="btn btn-red row-del" data-confirm-msg="确定要删除该行信息吗？">删</a>
                     </th>
                 </tr>
                 </thead>
                 <tbody>
                 <c:if test="${shunShopAttributesList!=null&&shunShopAttributesList.size()>0}">
                     <c:forEach items="${shunShopAttributesList}" var="list" varStatus="i">
                         <c:forEach items="${list.shunShopAttributesValueList}" var="value">
                             <tr data-id="1">
                                 <input type="hidden" name="shunShopAttributesList[${i.index}].id" value="${list.id}"/>
                                 <input type="hidden" name="shunShopAttributesValueList[${i.index}].id" value="${value.id}"/>
                                 <td data-id-val="${i.index+1}">${i.index+1}</td>
                                 <td>${list.name}</td>
                                 <td>${value.attributesValue}</td>
                                 <td data-noedit="true">
                                     <button type="button" class="btn-green" data-toggle="doedit">编辑</button>
                                     <a href="${pageContext.request.contextPath}/admin/shunShop/ajaxDone?id=${list.id}" class="btn btn-red row-del" data-confirm-msg="确定要删除该行信息吗？">删</a>
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
                 <label style="line-height: 28px;" align="center">瞬时秒杀首页图片</label>
                 </thead>
                 <tbody>
                 <tr>
                     <td>
                         <div  id="fileInput-custom"
                               data-toggle="upload"
                               data-uploader="${pageContext.request.contextPath}/admin/shunShop/upload"
                               data-button-text="选择上传图片"
                               data-file-size-limit="1024000000"
                               data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                               data-multi="false"
                               data-on-upload-success="doc_upload_success"
                               data-icon="cloud-upload">
                         </div>
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

             <div class="alert alert-warning " style="font-size: 14px;margin-top: 16px;margin-bottom: 0px;">注意：下述添加图片时，需要点击右下角的保存按钮，确定保存图片数据；否则不会保存数据！！！（为了显示上的美观，建议图片长宽比接近为2:1）</div>
             <table class="table table-bordered table-hover table-striped table-top" width="100%">
                 <thead>
                 <label style="line-height: 28px;" align="center">瞬杀商品轮播图片展示</label>
                 </thead>
                 <tbody>
                 <tr>
                     <td>
                         <div  id="fileInput2"
                               data-toggle="upload"
                               data-uploader="${pageContext.request.contextPath}/admin/shunShop/upload"
                               data-button-text="选择上传图片"
                               data-file-size-limit="1024000000"
                               data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                               data-multi="false"
                               data-on-upload-success="show_upload_success"
                               data-icon="cloud-upload">
                         </div>
                           <span id="doc_show_pic" style="margin-top: 10px;">
                               <c:forEach items="${shunShopShowsList}" var="list" varStatus="x">
                                   <div class="shunShopShowsList${x.index} upload-photo">
                                       <a href="${pageContext.request.contextPath}/admin/shunShop/photo/delete?id=${list.id}&index=${x.index}&type=shunShopShowsList&urlPath=${list.urlPath}" data-callback="mycallback" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除吗？">删除</a>
                                        <img src="${pageContext.request.contextPath}/${list.urlPath}">
                                        <input type="hidden" name="shunShopShowsList[${x.index}].id" value="${list.id}">
                                        <input type="hidden" name="shunShopShowsList[${x.index}].urlPath" value="${list.urlPath}">
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
                               data-uploader="${pageContext.request.contextPath}/admin/shunShop/upload"
                               data-button-text="选择上传图片"
                               data-file-size-limit="1024000000"
                               data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                               data-multi="false"
                               data-on-upload-success="detail_upload_success"
                               data-icon="cloud-upload">
                         </div>
                           <span id="doc_detail_pic" style="margin-top: 10px;">
                               <c:forEach items="${shunShopPhotosList}" var="list" varStatus="y">
                                   <div class="shunShopPhotosList${y.index} upload-photo">
                                       <a href="${pageContext.request.contextPath}/admin/shunShop/photo/delete?id=${list.id}&index=${y.index}&type=shunShopPhotosList&urlPath=${list.urlPath}" data-callback="mycallback" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除吗？">删除</a>
                                        <img src="${pageContext.request.contextPath}/${list.urlPath}">
                                        <input type="hidden" name="shunShopPhotosList[${y.index}].id" value="${list.id}">
                                        <input type="hidden" name="shunShopPhotosList[${y.index}].urlPath" value="${list.urlPath}">
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
                         <input type="radio" name="status" data-toggle="icheck" data-label="上架" value="0" <c:if test="${shunShop.status==0}">checked</c:if><c:if test="${shunShop.status==null}">checked</c:if>/>
                         <input type="radio" name="status" data-toggle="icheck" data-label="下架" value="1" <c:if test="${shunShop.status==1}">checked</c:if>/>
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
            url:"${pageContext.request.contextPath}"+"/admin/shunShop/checkNumber",
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
    });


    function doc_upload_success(file, data) {
        var json = $.parseJSON(data);
        $(this).bjuiajax('ajaxDone', json);
        if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
            $('#doc_span_pic').html('<img src="${pageContext.request.contextPath}/'+ json.filepath +
                    '"  style="width: 80%;"> ' +
                    '<input type="hidden" name="firstPhoto" value="'+json.filepath+'">');
        }
    }

    //自动有效日期
    $(document).on('afterchange.bjui.datepicker', '#shunShopStartTime', function(e, data) {
        var pattern = 'yyyy-MM-dd HH:mm:ss';
        end = data.value;
        var $end    = $('#shunShopStartTime');

        if ($end.length) {
            $end.val(end.formatDate(pattern));
        }
    });

    if (${shunShopShowsList.size()==0||shunShopShowsList.size()==null}){
        x=0;
    }else {
        x="${shunShopShowsList.size()}";
    }


    function show_upload_success(file, data) {
        var json = $.parseJSON(data);
        $(this).bjuiajax('ajaxDone', json);
        if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
            $('#doc_show_pic').append(
                    '<div class="shunShopShowsList'+x+' upload-photo">'+
                    "<a href='${pageContext.request.contextPath}/admin/shunShop/photo/delete?index="+x+"&type=shunShopShowsList&urlPath="+json.filepath+"' class='btn btn-red' data-callback='mycallback' data-toggle='doajax' data-confirm-msg='确定要删除吗？'>删除</a>"+
                    '<img src="${pageContext.request.contextPath}/'+ json.filepath + '">' +
                    '<input type="hidden" name="shunShopShowsList['+x+'].urlPath" value="'+json.filepath+'"><br>'+
                    '</div>');
            x++;
        }
    }


    if (${shunShopPhotosList.size()==0||shunShopPhotosList.size()==null}){
        y=0;
    }else {
        y="${shunShopPhotosList.size()}";
    }


    function detail_upload_success(file, data) {
        var json = $.parseJSON(data);
        $(this).bjuiajax('ajaxDone', json);
        if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
            $('#doc_detail_pic').append(
                    '<div class="shunShopPhotosList'+y+' upload-photo">'+
                    "<a href='${pageContext.request.contextPath}/admin/shunShop/photo/delete?index="+y+"&type=shunShopPhotosList&urlPath="+json.filepath+"' class='btn btn-red' data-callback='mycallback' data-toggle='doajax' data-confirm-msg='确定要删除吗？'>删除</a>"+
                    '<img src="${pageContext.request.contextPath}/'+ json.filepath + '"> ' +
                    '<input type="hidden" name="shunShopPhotosList['+y+'].urlPath" value="'+json.filepath+'"><br>'+
                    '</div>');
            y++;
        }
    }


    function mycallback(json) {
        $(this).bjuiajax('ajaxDone', json);       // 信息提示
        $('.'+json.type+json.index).remove();
        console.log(json.type+json.index);
    }
</script>

