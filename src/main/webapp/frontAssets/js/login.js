/**
 * Created by dushang on 2016/9/21.
 */
$(function () {
    $('#login-btn').click(function () {

        var $url = $('#loginForm').attr('action');
        var param = {};

        param['username'] = $('input[name=username]').val();
        param['password'] = $('input[name=password]').val();

        var alertInfo = "";
        if (param['username'] == '') {
            alertInfo = "用户名不能为空！";
            showAlertInfo(alertInfo);
        } else if (!(/^1[34578]\d{9}$/.test(param['username']) || (/\w@\w*\.\w/.test(param['username'])))) {
            alertInfo = "用户名格式不正确！";
            showAlertInfo(alertInfo);
        } else if (param['password'] == '') {
            alertInfo = "密码不能为空!";
            showAlertInfo(alertInfo);
        } else {
            /*向后台传递表单数据*/
            /*禁用按钮*/
            $(this).attr('disabled', "true");

            $.post($url, param, function (msg) {
                $('#login-btn').removeAttr("disabled");

                if (msg["message"] == "登录成功") {
                    if (document.referrer.indexOf("register") > 0 || document.referrer.indexOf("forgetPassword") > 0) {
                        window.location.href = contextPath + "/index";
                    } else {
                        console.log("back");
                        window.history.back();
                    }
                } else if(msg["url"]){
                    alertInfo = "你的权限已过期，正在前往年费支付界面";
                    showAlertInfo(alertInfo);
                    setTimeout(function(){
                        window.location.href = contextPath + "/annualFee/submitAnnualFee";
                    },1500);
                }else{
                    alertInfo = msg["message"];
                    showAlertInfo(alertInfo);
                    return false;
                }
            })
        }
        return false;
    });

    function showAlertInfo(alertInfo) {
        $('.alert-info').html(alertInfo);
        $('#alertInfoBtn').click();
    }

    console.log("hello" + document.referrer);
});