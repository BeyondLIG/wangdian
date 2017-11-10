<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <div class="bjui-pageContent">
            <form action="${pageContext.request.contextPath}/admin/shopKeeper/add" data-toggle="validate" <c:if test="${type.equals('shopKeeper')}">data-callback="mycallback"</c:if>
                <c:if test="${!type.equals('shopKeeper')}">data-reload-navtab="true"</c:if> method="post">
                <input type="hidden" name="isDel" value="0">
                <input type="hidden" name="id" value="${shopKeeper.id}">
                <input type="hidden" name="type" value="${type}">

                <c:if test="${shopKeeper!=null}">
                    <input type="hidden" name="allProfit" value="${shopKeeper.allProfit}">
                    <input type="hidden" name="yiTiXian" value="${shopKeeper.yiTiXian}">
                </c:if>

                <input type="hidden" name="message" value="${shopKeeper.message}">
                <c:if test="${type.equals('shopKeeper')}">
                    <input type="hidden" name="username" value="${shopKeeper.username}">
                    <input type="hidden" name="status" value="${shopKeeper.status}">
                </c:if>
                <div class="pageFormContent" data-layout-h="0">
                    <div style="padding: 5px;"></div>
                    <table class="table table-condensed table-hover" width="100%">
                        <thead>
                            <label>店主信息</label>
                        </thead>
                        <tbody>
                            <tr>
                                <td colspan="2" style="width: 50%;">
                                    <label class="control-label x100">用户名：</label>
                                    <input type="text" data-rule="required" id="username" <c:if test="${type.equals('shopKeeper')}">disabled</c:if>
                                    name="username" value="${shopKeeper.username}" size="20">
                                    <c:if test="${!type.equals('shopKeeper')}"><a href="#" id="checkUsername">检测用户名是否已存在</a></c:if>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" style="width: 50%;">
                                    <label class="control-label x100">密码：</label>
                                    <input type="password" data-rule="密码:required" name="password" value="${shopKeeper.decoderPassword()}" size="20">
                                </td>
                                <td colspan="2" style="width: 50%;">
                                    <label class="control-label x100">确认密码：</label>
                                    <input type="password" data-rule="确认密码:required;match(password)" value="${shopKeeper.decoderPassword()}" size="20">
                                </td>
                            </tr>

                            <tr>
                                <td colspan="2">
                                    <label class="control-label x100">手机号：</label>
                                    <input type="text" data-rule="mobile" name="telephone" value="${shopKeeper.telephone}" size="20">
                                </td>
                                <td colspan="2">
                                    <label class="control-label x100">支付宝：</label>
                                    <input type="text" data-rule="required" name="zhifubao" value="${shopKeeper.zhifubao}" size="20">
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <label class="control-label x100">店主级别：</label>
                                    <input type="text" data-rule="required" name="level" value="${shopKeeper.level}" size="20">
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4">
                                    <label class="control-label x100">状态：</label>
                                    <input type="radio" <c:if test="${type.equals('shopKeeper')}">disabled</c:if> name="status"
                                    data-toggle="icheck" data-label="正常" value="0"
                                    <c:if test="${shopKeeper.status==0}">checked</c:if>
                                    <c:if test="${shopKeeper.status==null}">checked</c:if>/>
                                    <input type="radio" <c:if test="${type.equals('shopKeeper')}">disabled</c:if> name="status"
                                    data-toggle="icheck" data-label="锁定" value="1"
                                    <c:if test="${shopKeeper.status==1}">checked</c:if>/>
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
            $("#checkUsername").on("click", function () {
                //        alert("hello");
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}" + "/admin/shopKeeper/checkUsername",
                    dataType: "json",
                    data: {
                        username: $("#username").val()
                    },

                    success: function (data) {
                        //                alert(data.message);
                        $(this).bjuiajax('ajaxDone', data);
                    },
                    error: function (data) {
                        alert("函数访问失败");
                    }
                })
            });

            function mycallback(json) {
                $(this).bjuiajax('ajaxDone', json); // 信息提示
                setTimeout('refresh()', 1500);
                //        console.info("hello");
            }

            function refresh() {
                window.location.reload();
            }
        </script>