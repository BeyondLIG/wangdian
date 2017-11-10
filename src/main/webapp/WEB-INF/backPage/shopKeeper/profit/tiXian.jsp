<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="bjui-pageContent">
     <form action="${pageContext.request.contextPath}/admin/shopKeeper/profit/tiXian" data-toggle="validate" data-reload-navtab="true" method="post">
         <div class="pageFormContent" data-layout-h="0">
             <div style="padding: 5px;"></div>
             <table class="table table-condensed table-hover" width="100%" >
                 <thead>
                 <label>提现</label>
                 </thead>
                 <tbody>
                       <tr>
                           <td colspan="2" style="width: 50%;">
                               <label class="control-label x100">金额：</label>
                               <input type="text" data-rule="required" name="money" size="20">元
                           </td>
                       </tr>
                       <tr>
                           <td colspan="2" style="width: 50%;">
                               <label class="control-label x100">支付宝账号：</label>
                               <input type="text" data-rule="required" name="alipay" size="20" value="${shopKeeper.zhifubao}">
                           </td>
                       </tr>
                       <div class="alert alert-warning " style="font-size: 14px;margin-top: 10px;">注意：支付宝账号涉及收益发放，一定要填写正确！因账号填写错误导致收益发放不到账由店主自身承担！</div>
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

