/**
 * Created by dushang on 2016/9/23.
 */
$(function () {
    $('input[type=submit]').click(function () {

        var url=$('#changePwdForm').attr('action');
        var param={};

        param['userId']=userId;
        param['oldPassword']=$('input[name=password1]').val();
        param['newPassword']=$('input[name=password2]').val();
        var password3=$('input[name=password3]').val();

        var alertInfo="";
        if(param['oldPassword']==''){
            alertInfo="原始密码输入错误！";
            showAlertInfo(alertInfo);
        }else if(param['newPassword']==''){
            alertInfo="新密码不能为空！";
            showAlertInfo(alertInfo);
        }else if(param['newPassword']!=password3){
            alertInfo="两次输入的新密码不一致！";
            showAlertInfo(alertInfo);
        }else if(param['oldPassword']==param['newPassword']){
            alertInfo="新密码不能与原始密码相同！";
            showAlertInfo(alertInfo);
        }else{
            /*向后台传递表单数据*/
            /*禁用按钮*/
            $(this).attr('disabled',"true");

            $.post(url,param,function (msg) {

                $('input[type=submit]').removeAttr("disabled");

                if(msg["message"]=="修改成功"){
                    alertInfo="密码修改成功！";
                    showAlertInfo(alertInfo);
                    setTimeout(function () {
                        alertInfo="正在跳转到个人中心！";
                        showAlertInfo(alertInfo);
                    },2000);
                    setTimeout(function () {
                        window.location.href=contextPath+"/user/userCenter";
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

    function showAlertInfo(alertInfo) {
        $('.alert-info').html(alertInfo);
        $('#alertInfoBtn').click();
    }
});


