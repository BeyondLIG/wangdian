$(function () {
    if (isSuccess == false) {
        alertInfo = "用户名和手机号不匹配！";
        showAlertInfo(alertInfo);
        //isSuccess = true;
    }

    $('#submit-btn').click(function () {
        var param = {};

        param['username'] = $('input[name=username]').val();
        param['telephone'] = $('input[name=telephone]').val();

        var alertInfo = "";
        if (param['username'] == '') {
            alertInfo = "用户名不能为空！";
            showAlertInfo(alertInfo);
        } else if (param['telephone'] == "") {
            alertInfo = "手机号不能为空！";
            showAlertInfo(alertInfo);
        } else if (!(/^1[34578]\d{9}$/.test(param['telephone']) || (/\w@\w*\.\w/.test(param['telephone'])))) {
            alertInfo = "手机格式不正确！";
            showAlertInfo(alertInfo);
        } else {
            /*向后台传递表单数据*/
            /*禁用按钮*/
            $(this).attr('disabled', "true");
            $("#submitForm").submit();
        }
        return false;
    });

    function showAlertInfo(alertInfo) {
        $('.alert-info').html(alertInfo);
        $('#alertInfoBtn').click();
    }
});