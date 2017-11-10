/**
 * Created by dushang on 2016/9/23.
 */
$(function () {

    var alertInfo="";
    $('.delBtn').on('click',function (e) {

        /*阻止链接跳转*/
        e.preventDefault();

        var href=$(this).attr('href');
        console.log(href);
        alertInfo="确定要删除该条地址记录吗？";
        showAlertInfo(alertInfo);

        $('.confirmBtn').on('click',function () {
            window.location.href = href;
            return false;
        });
    });

    function showAlertInfo(alertInfo) {
        $('.alert-info').html(alertInfo);
        $('#alertInfoBtn').click();
    }

});

