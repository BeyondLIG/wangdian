<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="bjui-pageContent">
     <form action="${pageContext.request.contextPath}/admin/user/add" data-toggle="validate" data-reload-navtab="true" method="post">
         <input type="hidden" name="isDel" value="0">
         <input type="hidden" name="id" value="${user.id}">
         <div class="pageFormContent" data-layout-h="0">
             <div style="padding: 5px;"></div>
             <table class="table table-condensed table-hover" width="100%" >
                 <thead>
                 <label>普通用户信息</label>
                 </thead>
                 <tbody>
                       <tr >
                           <td colspan="2" style="width: 50%;">
                               <label class="control-label x100">用户名：</label>
                               <input type="text" data-rule="required" id="username" name="username" value="${user.username}" disabled size="20">
                               <%--<a href="#" id="checkUsername" >检测用户名是否已存在</a>--%>
                           </td>
                       </tr>
                       <tr >
                           <td colspan="2" style="width: 50%;">
                               <label class="control-label x100">密码：</label>
                               <input type="password" data-rule="密码:required" name="password" value="${user.decoderPassword()}" disabled size="20">
                           </td>
                           <td colspan="2" style="width: 50%;">
                               <label class="control-label x100">确认密码：</label>
                               <input type="password" data-rule="确认密码:required;match(password)" value="${user.decoderPassword()}" disabled size="20">
                           </td>
                       </tr>
                       <tr>
                        
                       </tr>
                 </tbody>
             </table>

             <div style="padding: 10px;"></div>
             <table class="table table-bordered table-hover table-striped table-top" width="100%" >
                 <thead>
                 <label>地址信息</label>
                 <th align="center">序号</th>
                 <th align="center">收货人</th>
                 <th align="center">手机号</th>
                 <th align="center">邮政编码</th>
                 <th align="center">省份</th>
                 <th align="center">城市</th>
                 <th align="center">区域</th>
                 <th align="center">街道</th>
                 <th align="center">详细地址</th>
                 <th align="center">状态</th>
                 </thead>
                 <tbody>
                 <c:forEach items="${userAddressList}" var="list" varStatus="i">
                     <tr >
                         <td>${i.index+1}</td>
                         <td>${list.name}</td>
                         <td>${list.phone}</td>
                         <td>${list.zipcode}</td>
                         <td>${list.province}</td>
                         <td>${list.city}</td>
                         <td>${list.area}</td>
                         <td>${list.town}</td>
                         <td>${list.adddetail}</td>
                         <td><c:if test="${list.status==0}">默认</c:if><c:if test="${list.status!=0}">普通</c:if></td>
                     </tr>
                 </c:forEach>

                 </tbody>
             </table>

             <div style="padding: 10px;"></div>
             <table class="table table-bordered table-hover table-striped table-top" width="100%">
                 <tbody>
                 <tr>
                     <td colspan="4">
                         <label class="control-label x100">状态：</label>
                         <input type="radio" name="status" data-toggle="icheck" data-label="正常" value="0" <c:if test="${user.status==0}">checked</c:if><c:if test="${user.status==null}">checked</c:if>/>
                         <input type="radio" name="status" data-toggle="icheck" data-label="锁定" value="1" <c:if test="${user.status==1}">checked</c:if>/>
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
           <!--
           <li><button type="submit" class="btn-default" data-icon="save">保存</button></li>
           -->
       </ul>
</div>
<script>
    $("#checkUsername").on("click",function () {
//        alert("hello");
        $.ajax({
            type:"POST",
            url:"${pageContext.request.contextPath}"+"/admin/user/checkUsername",
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
    })
</script>

