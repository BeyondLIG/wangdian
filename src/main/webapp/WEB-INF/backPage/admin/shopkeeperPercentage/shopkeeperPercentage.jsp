<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="bjui-pageContent">
    <form action="${pageContext.request.contextPath}/admin/shareBenefit/annualFeeShareBenefit/update" data-toggle="validate" method="post">
        <div class="alert alert-warning " style="font-size: 14px;margin-top: 16px;margin-bottom: 0px;">
            注意：下述添加数据时，需要点击右下角的保存按钮，确定保存数据；否则不会保存数据！！！
            <br>
            本模块设置不同级别的店主招收下级店主时的提成金额
        </div>
        <div style="padding: 10px;"></div>
        <input type="hidden" name="id" value="${annualFeeParam.id}">
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover" width="100%" >
                <thead>
                <label>店主提成信息</label>
                </thead>
                <tbody>
                <tr>
                    <td colspan="2" style="width: 50%;">
                        <label class="control-label x150">店主（a）：</label>
                        <input type="text"  name="a" value="${annualFeeParam.a}" size="50">元
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="width: 50%;">
                        <label class="control-label x150">经理（c）：</label>
                        <input type="text"  name="c" value="${annualFeeParam.c}" size="50">元
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="width: 50%;">
                        <label class="control-label x150">主管(b)：</label>
                        <input type="text"  name="b" value="${annualFeeParam.b}" size="50">元
                    </td>
                </tr>
                </tbody>
            </table>
            <div style="padding: 10px;"></div>
        </div>
    </form>
</div>

<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
        <li><button type="submit" class="btn-default" data-icon="save">保存</button></li>
    </ul>
</div>

