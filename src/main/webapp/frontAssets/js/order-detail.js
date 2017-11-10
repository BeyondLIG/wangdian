/**
 * Created by dushang on 2016/9/28.
 */
$(function () {


    $('#delOrderBtn').click(function (e) {
        e.preventDefault();
        showAlertInfo("确定要删除该订单吗？");
        var href=$(this).parent().attr('href');
        console.log(href);
        $('.confirmBtn').on('click',function () {
            window.location.href=href;
            return false;
        });
    });
    // $('#payBtn').click(function (e) {
    //     e.preventDefault();
    //     showAlertInfo("暂未开通此功能！");
    // });

    function showAlertInfo(alertInfo) {
        $('.alert-info').html(alertInfo);
        $('#alertInfoBtn').click();
    }
});