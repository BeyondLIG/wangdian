/**
 * Created by dushang on 2016/9/16.
 */
$(function () {
    $(".fixed-header").find(".icon-container").on("click", function () {
        //console.log("click");
        window.history.back();
    });

    /*根据当前页面的title值来确定激活的导航菜单*/
    var title = $('title').text();
    var $classify = $(".classify");
    if (title.indexOf('店小二手机版') != -1) {
        $('.ui-footer-fixed ul').find('a').eq(0).addClass('btn-choose').parent().siblings().removeClass('btn-choose');
    } else if (title.indexOf('限时秒杀') != -1) {
        $('.ui-footer-fixed ul').find('a').eq(1).addClass('btn-choose').parent().siblings().removeClass('btn-choose');
    } else if (title.indexOf('购物车') != -1) {
        $('.ui-footer-fixed ul').find('a').eq(2).addClass('btn-choose').parent().siblings().removeClass('btn-choose');
    } else if (title.indexOf('个人中心') != -1) {
        $('.ui-footer-fixed ul').find('a').eq(3).addClass('btn-choose').parent().siblings().removeClass('btn-choose');
    }

    /*根据选择的分类选择显示样式条*/
    $classify.on("click", function (e) {
        $(".choose-line").hide();
        $(this).find(".choose-line").show();

    });
});
/*
$(function () {
    var startX = 0;
    var currentX = 0
    var moveX = 0;

    var classifyBar = document.getElementsByClassName("classify-bar")[0];

    var i = 0,
        r = $(window).width();
    $(".classify-bar ul").children().each(function () {
        i += $(this).width() + parseInt($(this).css("padding-left")) * 2 + 4; //每个li之间有空隙
    });
    i = i < r ? r : i;

    $(classifyBar).find(".move-bar").width(i);

    hiddenWidth = i - $(window).width();

    function move() {
        currentX += moveX;
        if (currentX >= 0) {
            if (0 === currentX)
                return null;
            currentX = 0
        } else if (-currentX >= hiddenWidth) {
            if (-currentX === hiddenWidth)
                return null;
            currentX = -(hiddenWidth);
        }

        $(".classify-bar .move-bar").css("transform", "translateX(" + currentX + "px)");
    }
    if (classifyBar) {
        classifyBar.addEventListener('touchstart', function (e) {
            startX = e.touches[0].clientX;
        });

        //classifyBar.addEventListener("touchstart", function (e) {
        //}, false);

        classifyBar.addEventListener("touchmove", function (e) {
            var e = event || window.event;
            e.preventDefault();
            moveX = e.touches[0].pageX - startX;
            startX = e.touches[0].pageX;
            move();
        }, false);
    }
});
*/