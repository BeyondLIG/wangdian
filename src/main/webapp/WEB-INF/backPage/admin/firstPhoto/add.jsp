<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String hello=sdf1.format(new Date());
%>
<div class="bjui-pageContent">
     <form action="${pageContext.request.contextPath}/admin/firstPhoto/add" data-toggle="validate" <c:if test='${!type.equals("admin")}'>data-reload-navtab="true"</c:if>  method="post">
         <input type="hidden" name="isDel" value="0">
         <input type="hidden" name="id" value="${firstPhoto.id}">
         <div class="pageFormContent" data-layout-h="0">

             <table class="table table-condensed table-hover" width="100%">
                 <thead>
                 <label style="line-height: 28px;" align="center">首页</label>
                 </thead>
                 <tbody>
                       <tr>
                           <td colspan="2">
                               <label class="control-label x80">图片信息：</label>
                               <input type="text" data-rule="required" name="name" value="${firstPhoto.name}" size="60">
                           </td>
                       </tr>
                       <tr>
                           <td colspan="2">
                               <label class="control-label x80">记录时间：</label>
                               <input type="text" data-rule="required" name="advertInTime" <c:if test="${firstPhoto.inTime!=null}">value="${firstPhoto.inTimeToString()}"</c:if> <c:if test="${firstPhoto.inTime==null}">value="<%=hello%>"</c:if> size="20">
                           </td>
                       </tr>
                 </tbody>
             </table>

             <div class="alert alert-warning " style="font-size: 14px;margin-top: 16px;margin-bottom: 0px;">注意：下述添加图片时，需要点击右下角的保存按钮，确定保存图片数据；否则不会保存数据！！！</div>
             <table class="table table-bordered table-hover table-striped table-top" width="100%">
                 <thead>
                 <label style="line-height: 28px;" align="center">图片</label>
                 </thead>
                 <tbody>
                 <tr>
                     <td>
                         <div  id="fileInput-custom"
                               data-toggle="upload"
                               data-uploader="${pageContext.request.contextPath}/admin/firstPhoto/upload"
                               data-button-text="选择上传图片"
                               data-file-size-limit="1024000000"
                               data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
                               data-multi="false"
                               data-on-upload-success="doc_upload_success"
                               data-icon="cloud-upload">
                         </div>
                           <span id="doc_span_pic" style="margin-top: 10px;">
                               <c:if test="${firstPhoto.photo!=null||firstPhoto.photo.equals('')}">
                                   <img src="${pageContext.request.contextPath}/${firstPhoto.photo}" style="width: 80%;">
                                   <input type="hidden" name="photo" value="${firstPhoto.photo}">
                               </c:if>
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
           <li><button type="submit" class="btn-default" data-icon="save">保存</button></li>
       </ul>
</div>
<script type="text/javascript">
    function doc_upload_success(file, data) {
        var json = $.parseJSON(data);
        $(this).bjuiajax('ajaxDone', json);
        if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
            $('#doc_span_pic').html('<img src="${pageContext.request.contextPath}/'+ json.filepath +
                    '"  style="width: 80%;"> ' +
                    '<input type="hidden" name="photo" value="'+json.filepath+'">');
        }
    }

</script>

