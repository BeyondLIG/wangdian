/**
 * Created by dushang on 2016/9/17.
 */
$(function () {
    //修改样式
    $("#validCode").parent().addClass("validCode-parent");
    $("#realName").hide();
    $("#alipay").hide();

    var alertInfo = "";

    /*发送验证码*/
    $("#send-code").click(function (e) {
        e.preventDefault();
        e.stopPropagation();
        var $telephoneN = $("#telephone").val();
        var result = /^1[34578]\d{9}$/.test($telephoneN);
        if (result) {
            $.get(contextPath + "/smsvalid/send?telephone=" + $telephoneN + "&type=changeTelephone").success(function (data) {
                if (data == 1) {
                    $("#send-code").html("验证码已发送");
                } else {
                    $("#phoneShow").html("手机号已被占用");
                }
            }).error(function () {
                $("#phoneshow").html("请稍后重试").css("color", "#fe4070");
            });
        } else {
            $("#phoneshow").html("手机号格式不正确").css("color", "#fe4070");
        }
        return false;
    });

    /*修改手机号*/
    $("#changePhoneBtn").on('click', function (e) {
        e.preventDefault();
        e.stopPropagation();
        var param = {};
        param['validCode'] = $('input[name=validCode]').val();
        param['newPassword'] = $('input[name=password]').val();
        param['telephone'] = $('input[name=telephone]').val();

        if (param['telephone'] == '') {
            alertInfo = "手机号码不能为空！";
            $("#phoneShow").html(alertInfo).css("color", "#fe4070");
        } else if (!/^1[34578]\d{9}$/.test(param['telephone'])) {
            alertInfo = "手机号码格式不正确！";
            $("#phoneShow").html(alertInfo).css("color", "#fe4070");

        } else if (param['validCode'] == '') {
            alertInfo = "验证码不能为空！";
            $("#phoneShow").html(alertInfo).css("color", "#fe4070");

        } else if (param['password'] == '') {
            alertInfo = "密码不能为空！";
            $("#phoneShow").html(alertInfo).css("color", "#fe4070");

        } else {
            $('#changePhoneBtn').attr('disabled', 'true');
            alertInfo = "正在处理...";
            $("#phoneShow").html(alertInfo).css("color", "#fe4070");
            $.ajax({
                url: contextPath + '/user/updateTelephone',
                type: 'POST',
                data: param,
                cache: false,
                success: function (data) {
                    $('#changePhoneBtn').removeAttr("disabled");
                    $("#phoneShow").html(data['message']);
                }
            });
        }
    });

    /*
     * 升级成为店主
     */

    var $container = $("#alertInfo").find(".ui-field-contain");
    $("#becomeShopkeeper").on("click", function () {
        var alertInfo = "点击确认并支付年费365元，你将自动升级为店主！";
        showWaringInfo(alertInfo);
        $("#sure").on("click",function () {
            //取消此页面显示
            $container.find(".alert-info").hide();
            $("#realName").show();
            $("#alipay").show();
            $("#alertInfo").find("#sure").attr("id","submitInfo");
            $("#submitInfo").on("click", function () {
                //禁用按钮
                $("this").attr("disabled","true");
                //检查参数
                var param = {};
                param['realName'] = $("#realName").val();
                param['alipay'] = $("#alipay").val();
                var $showArea =  $("#alertInfo").find("span");
                if(param['realName']==""){
                    $showArea.html("真实姓名不能为空").css("color","#fe4070");
                    $("this").attr("disabled","false");
                }else if(param['alipay']==""){
                    $showArea.html("支付宝不能为空").css("color","#fe4070");
                    $("this").attr("disabled","false");
                }else{
                    $.ajax({
                            url: "/user/toBeShopkeeper",
                            method: "GET",
                            data: param,
                            success: function (data) {
                                var args = {};
                                args['username'] = data['username'];
                                args['telephone'] = data['telephone'];
                                $.StandardPost(data['url'], args);
                            }
                        });
                    //显示处理
                    $("#realName").hide();
                    $("#alipay").hide();
                    $container.find(".alert-info").show();
                    $("#alertInfo").find("#submitInfo").attr("id","sure");
                }
            });

        })
    });

    //处理在提示信息弹出后又点击取消的情况
    $("#cancle").on("click",function () {
        $("#realName").hide();
        $("#alipay").hide();
        $container.find(".alert-info").show();
    });

    function showAlertInfo($selector, alertInfo) {
        $selector.parent().find('.alertInfo').html(alertInfo);
        $selector.on('blur', function () {
            $selector.parent().find('.alertInfo').html("");
        })
    }

    function showWaringInfo(message) {
        $("#alertInfo").find(".alert-info").html(message);
        $("#alertInfoBtn").click()
    }

    $.extend({
        StandardPost: function (url, args) {
            var body = $(document.body),
                form = $("<form method='post'></form>"),
                input;
            form.attr({
                "action": url
            });
            $.each(args, function (key, value) {
                input = $("<input type='hidden'>");
                input.attr({
                    "name": key
                });
                input.val(value);
                form.append(input);
            });

            form.appendTo(document.body);
            form.submit();
            document.body.removeChild(form[0]);
        }
    });
});