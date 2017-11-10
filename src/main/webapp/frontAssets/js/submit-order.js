/**
 * Created by dushang on 2016/9/27.
 */
$(function () {

    /*更改商品数量*/
    $('.minus-btn').click(function () {
        var buyCount = $(this).parent().find('input').val();
        if (buyCount > 1) {
            buyCount--;
            $(this).parent().find('input').val(buyCount);
            $(this).parent().find('span').text(buyCount);
        }
        calcTotalPrice();
    });

    $('.plus-btn').click(function () {
        var buyCount = $(this).parent().find('input').val();
        buyCount++;
        $(this).parent().find('input').val(buyCount);
        $(this).parent().find('span').text(buyCount);
        calcTotalPrice();
    });

    calcTotalPrice();

    if (submitAvailableTest()) {
        $('#submitOrderBtn').addClass('available');
    }


    //计算订单总价
    function calcTotalPrice() {
        var totalPrice = 0;
        $('.price').each(function () {
            var $price = $(this).attr('data-price');  
            var $amount = $(this).parent().next().find("input").val();
            //console.log($price);
            //console.log($amount);
            totalPrice += $price * $amount;
        });

        //mianyunfei:JSP文件定义
        if (totalPrice >= mianyunfei) {
            $('.postage span').text(0);
            $('.total-price span').text(totalPrice);
        } else {
            $('.postage span').text(yunfei);
            $('.total-price span').text(parseFloat(totalPrice) + parseFloat(yunfei));
        }

    }

    /*根据参数是否选择完判断提交按钮是否可用*/
    function submitAvailableTest() {
        return $('select[name=addressId]').val();
    }

    $('#submitOrderBtn').click(function (e) {

        if (submitAvailableTest()) {
            /*var chosenParameter="";
            $('.chosen-parameter span').each(function () {
            chosenParameter+=$(this).text()+"、";
            });
            chosenParameter+="数量："+$('input[name=count]').val();

            $('input[name=shopDetail]').val(chosenParameter);*/
        } else {
            return false;
        }
    })
});