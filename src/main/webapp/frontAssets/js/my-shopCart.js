/**
 * Created by dushang on 16/10/14.
 */
$(function () {

    /*检查按钮是否可用*/
    function checkBtn() {
        //console.log($('input[name=shopCartIdList]:checked').length);
        if ($('input[name=shopCartIdList]:checked').length > 0) {
            return true;
        } else {
            return false;
        }
    }

    var $chooseAll = $(".options").find("#chooseAll");
    $("input[name=shopCartIdList]").click(function () {
        var inputLength = $("input[name=shopCartIdList]").length;
        var checkedLength = $("inputp[name=shopCartIdList]").length;
        if (checkedLength < inputLength) {
            $chooseAll.prop("checked", false);
            $(".options").find(".ui-btn").removeClass("ui-checkbox-on").addClass("ui-checkbox-off");
        }
        updateOrderInfo();
    });

    //全选按钮
    $chooseAll.on("click", function () {
        if ($chooseAll.prop("checked")) {
            $("form").find("fieldset").find(".ui-btn").addClass("ui-checkbox-on").removeClass("ui-checkbox-off");
            $("input[name=shopCartIdList]").prop("checked", true);
            updateOrderInfo();

        } else {
            $("form").find("fieldset").find(".ui-btn").addClass("ui-checkbox-off").removeClass("ui-checkbox-on");
            $("input[name=shopCartIdList]").prop("checked", false);
            updateOrderInfo();
        }
    });

    //删除事件
    $(".ui-controlgroup").on("click", function (e) {
        if (e.target == $(this).find(".icon").get(0)||e.target==$(this).find("use").get(0)) {
            var alertInfo = "确定要删除该商品吗？";
            var $input = $(this).find("input[name=shopCartIdList]");
            showAlertInfo(alertInfo);
            $('.confirmBtn').on('click', function () {
                $input.prop("checked",true);
                var list = [];
                list.push($input.val());
                $.ajax({
                    url: contextPath + '/user/deleteFromShopCart',
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        shopCartIdList: JSON.stringify(list)
                    },
                    cache: false,
                    /*数据传送成功*/
                    success: function (data) {
                        if (data.message == "success") {
                            location.reload();
                        }
                    }
                });
            });
        }
    });

    $('#submitBtn').click(function (e) {
        if (!checkBtn()) {
            e.preventDefault();
            return false;
        }
    });


    //计算价格和数量
    function updateOrderInfo() {
        var totalPrice = 0;
        var totalNum = 0;
        $("fieldset").each(function () {
            var $checkStauts = $(this).find("input[name=shopCartIdList]").prop("checked");
            if ($checkStauts) {
                var $price = parseFloat($(this).find(".productPrice").find("span").html());
                var $amount = parseFloat($(this).find(".productNum").find("span").html());
                totalPrice += $price * $amount;
                totalNum += $amount;
            }
        });
        $(".shopCartInfo").find(".total-price").html("￥" + totalPrice);
        $(".shopCartInfo").find(".total-num").html(totalNum);
    }

    function showAlertInfo(alertInfo) {
        $('.alert-info').html(alertInfo);
        $('#alertInfoBtn').click();
    }
});