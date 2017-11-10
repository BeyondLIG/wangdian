<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="bjui-pageContent">
     <form action="${pageContext.request.contextPath}/admin/admin/add" data-toggle="validate" <c:if test='${!type.equals("admin")}'>data-reload-navtab="true"</c:if> <c:if test='${update.equals("head")}'>data-callback="mycallback"</c:if> method="post">
         <input type="hidden" name="isDel" value="0">
         <input type="hidden" name="id" value="${admin.id}">
         <input type="hidden" name="type" value="${type}">
         <input type="hidden" name="loginCount" value="${admin.loginCount}">
         <input type="hidden" name="loginInTime" value="${admin.loginTimeToString()}">
         <div class="pageFormContent" data-layout-h="0">
             <table class="table table-condensed table-hover" width="100%">
                 <tbody>
                       <tr>
                           <td colspan="2">
                               <label class="control-label x100">用户名：</label>
                               <input type="text" data-rule="required" id="username" name="username" value="${admin.username}" size="60">
                               <a href="#" id="checkUsername" >检测用户名是否已存在</a>
                           </td>
                       </tr>
                       <!--
                       <tr>
                           <td colspan="2">
                               <label class="control-label x100">昵称：</label>
                               <input type="text" data-rule="required" name="nickname" value="${admin.nickname}" size="60">
                           </td>
                       </tr>
                       -->
                       <tr>
                           <td colspan="2">
                               <label class="control-label x100">密码：</label>
                               <input type="password" data-rule="密码:required" name="password" value="${admin.decoderPassword()}" size="60">
                           </td>
                       </tr>
                       <tr>
                           <td colspan="2">
                               <label class="control-label x100">确认密码：</label>
                               <input type="password" data-rule="确认密码:required;match(password)" value="${admin.decoderPassword()}" size="60">
                           </td>
                       </tr>
                       <tr>
                           <td colspan="2">
                               <label class="control-label x100">状态：</label>
                               <input type="radio" name="status" data-toggle="icheck" data-label="正常" value="0" <c:if test="${admin.status==0}">checked</c:if><c:if test="${admin.status==null}">checked</c:if>/>
                               <input type="radio" name="status" data-toggle="icheck" data-label="锁定" value="1" <c:if test="${admin.status==1}">checked</c:if>/>
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
    $("#checkUsername").on("click",function () {
//        alert("hello");
        $.ajax({
            type:"POST",
            url:"${pageContext.request.contextPath}"+"/admin/admin/checkUsername",
            dataType:"json",
            data:{
                username:$("#username").val()
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

    function mycallback(json) {
        $(this).bjuiajax('ajaxDone', json);      // 信息提示
        setTimeout('refresh()', 1500);
//        console.info("hello");
    }

    function refresh(){
        window.location.reload();
    }

</script>

