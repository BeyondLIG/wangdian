/**
 * Created by dushang on 2016/9/21.
 */
/**
 * Created by dushang on 2016/9/21.
 */
$(function () {
    /**
     * 检测注册类型
     */
    var userType = 1;
    var $formReferee = $("#referee");
    var $header = $(".fixed-header");
    var $url = $('#registerForm').attr('action');
    var getUrl = $url.slice(0, -9);
    var $alipay = $("#alipay");
    var $iconContainer = $(".icon-container"); //选取返回icon

    /*
    $("#type-btn").children().each(function () {
        $(this).on("click", function () {
            $iconContainer.addClass("hasChoose"); //为返回按钮添加选择属性，区分返回上一页

            var $thisId = $(this).attr("id");
            var $headerTitle = $header.find(".header-title").find("span");
            var $typeArea = $(".type-area");
            var $registerForm = $("#registerForm");
            var iconHasChoose = $(".hasChoose");


            //当点击其他有更多信息的注册按钮后，可能会选择其他注册类型，致使更多信息选择被设置出现，所以每次点击都要进行隐藏
            if ($thisId === "type-shopKeeper") {
                $alipay.show();
                $headerTitle.html("店主注册");
                $formReferee.show();
                userType = 2;
            } else if ($thisId === "type-vip") {
                $headerTitle.html("VIP注册");
                $alipay.hide();
                $formReferee.show();
                userType = 1;
            } else if ($thisId === "type-normal") {
                $headerTitle.html("普通用户注册");
                $formReferee.hide();
                userType = 0;
                $alipay.hide();

            } else {
                alert("非法信息");
            }

            //每次选择注册类型，取消绑定的返回事件，并绑定新的点击事件
            $header.find(".hasChoose").off().one("click", function (e) {
                console.log("xuanze");
                $typeArea.show();
                $registerForm.hide();
                $header.find(".icon-container").on("click", function () {
                    //重新绑定返回事件
                    window.history.back();
                });
            });
            $(".type-area").hide();
            $("#registerForm").show();
        });


    });
    */

    /**
     * 设置验证码输入框父元素大小
     */
    $("#validCode").parent().addClass("validCode-parent");

    /**
     * 发送验证码
     */
    $("#send-code").click(function (e) {
        e.preventDefault();
        e.stopPropagation();
        $("#send-code").attr("disabled","disabled");
        var $telephoneN = $("#telephone").val();
        var result = /^1[34578]\d{9}$/.test($telephoneN);
        if (result) {
            $("#send-code").removeAttr("disabled");
            $.get(getUrl + "/smsvalid/send?telephone=" + $telephoneN+"&type=register").success(function () {
                $("#send-code").html("验证码已发送");
            }).error(function () {
                $("#send-code").removeAttr("disabled");
                $("#send-code").html("请稍后重试");
            });
        } else {
            $("#send-code").removeAttr("disabled");
            alertInfo = "手机号码格式不正确！";
            showAlertInfo(alertInfo);
            $("#telephone").val("");
        }
        return false;
    });

    //注册信息验证
    $('#register').click(function () {
        /*禁用按钮*/
        $("#register").attr("disabled","disabled");
        var param = {};
        param['username'] = "D"+$('input[name=telephone]').val();
        param['telephone'] = $('input[name=telephone]').val();
        param['password'] = $('input[name=password]').val();
        param['validCode'] = $('input[name=validCode]').val();
        param['userType'] = userType;
        param['alipay'] = $alipay.val();
        param['referee'] = $("#referee").val();

        var password = $('#password').val();
        var alertInfo = "";
        if (param['username'] == '') {
            $("#register").removeAttr("disabled");
            alertInfo = "错误，请重新填写手机号码！";
            showAlertInfo(alertInfo);
        } else if (param['telephone'] == '') {
            $("#register").removeAttr("disabled");
            alertInfo = "手机号码不能为空！";
            showAlertInfo(alertInfo);
        } else if (!/^1[34578]\d{9}$/.test(param['telephone'])) {
            $("#register").removeAttr("disabled");
            alertInfo = "手机号码格式不正确！";
            showAlertInfo(alertInfo);
        } else if (param['validCode'] == '') {
            $("#register").removeAttr("disabled");
            alertInfo = "验证码不能为空！";
            showAlertInfo(alertInfo);
        } else if (param['password'] == '') {
            $("#register").removeAttr("disabled");
            alertInfo = "密码不能为空！";
            showAlertInfo(alertInfo);
        } 
        /*else if (param['password'] != password) {
            alertInfo = "两次输入的密码不一致！";
            showAlertInfo(alertInfo);
        }*/ else {
            /*向后台传递表单数据*/
            alertInfo = "正在处理，请稍候...";
            showAlertInfo(alertInfo);

            $.post($url, param, function (msg) {
                $('#register').removeAttr("disabled");
                if (msg["message"] == "注册成功") {
                    alertInfo = "注册成功！";
                    showAlertInfo(alertInfo);
                    if (userType == 2) {
                        setTimeout(function () {
                            alertInfo = "正在跳转到支付页面！";
                            showAlertInfo(alertInfo);
                        }, 2000);
                        setTimeout(function () {
                            //传递参数并进行页面跳转
                            var args = {
                                username: msg['username'],
                                telephone: msg['telephone']
                            };
                            $.StandardPost(msg['url'], args);
                        }, 2000);
                    } else if (userType == 1 || userType == 0) {
                        setTimeout(function () {
                            alertInfo = "正在跳转到登录界面页面！";
                            showAlertInfo(alertInfo);
                        }, 2000);
                        setTimeout(function () {
                            window.location.href = contextPath + "/login";
                        }, 2000);
                    } else {
                        alertInfo = "非法登录";
                        showAlertInfo(alertInfo);
                    }

                } else {
                    alertInfo = msg["message"];
                    showAlertInfo(alertInfo);
                    return false;
                }
            });


        }
        return false;
    });

    function showAlertInfo(alertInfo) {
        $('.alert-info').html(alertInfo);
        $('#alertInfoBtn').click();
    }

    //传递POST请求并跳转页面
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