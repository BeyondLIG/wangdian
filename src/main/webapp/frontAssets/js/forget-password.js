/**
 * Created by dushang on 2016/9/22.
 */

$(function () {
    //修改样式
    $("#validCode").parent().addClass("validCode-parent");
    $("#send-code").click(function () {
        var $telephoneN = $("#telephone").val();
        var result = /^1[34578]\d{9}$/.test($telephoneN);
        if (result) {
            $.get(contextPath + "/smsvalid/send?telephone=" + $telephoneN+"&type=forgetPassword").success(function () {
                $("#send-code").html("验证码已发送");
            }).error(function () {
                $("#send-code").html("请稍后重试");
            });
        } else {
            alertInfo = "手机号码格式不正确！";
            showAlertInfo(alertInfo);
            $("#telephone").val("");
        }
        return false;
    });
    //注册信息验证
    $('#changePass').click(function () {
        var param = {};
        param['validCode'] = $('input[name=validCode]').val();
        param['newPassword'] = $('input[name=password]').val();
        param['telephone'] = $('input[name=telephone]').val();


        var password = $('#password').val();
        var alertInfo = "";

        if (param['telephone'] == '') {
            alertInfo = "手机号码不能为空！";
            showAlertInfo(alertInfo);
        } else if (!/^1[34578]\d{9}$/.test(param['telephone'])) {
            alertInfo = "手机号码格式不正确！";
            showAlertInfo(alertInfo);
        } else if (param['validCode'] == '') {
            alertInfo = "验证码不能为空！";
            showAlertInfo(alertInfo);
        } else if (param['password'] == '') {
            alertInfo = "密码不能为空！";
            showAlertInfo(alertInfo);
        } else {
            /*向后台传递表单数据*/
            /*禁用按钮*/
            var url = contextPath + "/forgetPassword";
            $(this).attr('disabled', "true");
            alertInfo = "正在处理，请稍候...";
            showAlertInfo(alertInfo);
            $.ajax({
                url: url,
                data: param,
                method:"POST",
                success: function (msg) {
                    $('#changePass').removeAttr("disabled");
                    console.log(msg["message"]);
                    if (msg["message"] == "修改密码成功") {
                        alertInfo = "密码修改成功！";
                        showAlertInfo(alertInfo);
                        window.location.href = contextPath+ "/login";
                    } else if(msg["message"]!=undefined){
                        alertInfo = msg["message"];
                        showAlertInfo(alertInfo);
                        return false;
                    }else{
                        alertInfo = "验证码错误！";
                        showAlertInfo(alertInfo);
                    }
                },
                error: function(){
                    alertInfo = "网络错误！";
                    showAlertInfo(alertInfo);
                }
            });
        }
        return false;
    });
    /*
    $('input[type=submit]').click(function () {

        var url=$('#forgetPwdForm').attr('action');
        var param={};

        param['email']=$('input[name=email]').val();

        var alertInfo="";
        if(param['email']==''){
            alertInfo="请先输入邮箱！";
            showAlertInfo(alertInfo);
        }else if(!( /\w@\w*\.\w/.test(param['email']))){
            alertInfo="邮箱格式不正确！";
            showAlertInfo(alertInfo);
        }else{
            $(this).attr('disabled',"true");
            alertInfo="正在处理，请稍候...";
            showAlertInfo(alertInfo);
            $.post(url,param,function (msg) {

                $('input[type=submit]').removeAttr("disabled");
                if(msg["message"]=="发送成功"){
                    alertInfo="系统已发送重置密码到您的邮箱，请注意查收，如果未收到提醒，可能被过滤到垃圾箱！";
                    showAlertInfo(alertInfo);
                    setTimeout(function () {
                        alertInfo="正在跳转到登录页面!";
                        showAlertInfo(alertInfo);
                    },2000);
                    setTimeout(function () {
                        window.location.href=contextPath+"/login";
                    },3000);
                }else{
                    alertInfo=msg["message"];
                    showAlertInfo(alertInfo);
                    return false;
                }
            })
        }

        return false;
    });
    */

    function showAlertInfo(alertInfo) {
        $('.alert-info').html(alertInfo);
        $('#alertInfoBtn').click();
    }
});