/**
 * Created by dushang on 2016/9/14.
 */
$(function () {
    /**
     * 页面异步加载
     */
    var page = 1; //加载商品的页面
    var lastScrollY = 0;
    var currentScrollY = 0;
    $(window).on("scroll", function () {
        var url = contextPath + "/flashSalePage";
        windowScroll(page, url);
    });

    //窗口滚动事件
    function windowScroll(page, url, shopType) {
        var scrollHeight = $(window).height() + window.scrollY;
        var documentHeight = $(document).height(); //文档的高度
        var productItemHeight = $(".product").height(); //商品条目的高度

        //如果顾客浏览商品滚动的距离比页面高度小一个商品条目的高度，则异步加载5个商品
        var load = scrollHeight + productItemHeight - documentHeight;
        var scrollDistance = 0;

        currentScrollY = window.scrollY;
        scrollDistance = currentScrollY - lastScrollY;
        if (scrollDistance < 0) {
            scrollDistance = 0;
        }

        //开始加载数据
        if (load >= 0 && scrollDistance > productItemHeight) {
            loadProduct(page++, url, shopType);
            lastScrollY = currentScrollY;
            $(window).off("scroll");
        }
    }

    //异步加载更多商品
    function loadProduct(page, url, shopType) {
        //发送异步加载信息
        $.ajax({
            url: url,
            method: "POST",
            data: {
                shopType: shopType,
                page: page
            },
            //处理返回的数据
            success: function (result) {
                //加载数据成功后再次绑定窗口滚动事件
                $(window).on("scroll", function () {
                    windowScroll(page + 1, url, shopType);
                });

                if (result['shunShopList'].length > 0) {
                    var shunShopList = result['shunShopList'];
                    for (var i = 0; i < shunShopList.length; i++) {
                        var appendContent = '<li><a href="' + contextPath + '/commodityDetail?id=' + shunShopList[i].id + '&type=shunShop" data-ajax="false" class="link"><div class="product"><div class="product-img"><img src="' + contextPath + '/' + shunShopList[i].firstPhoto + '" alt="商品图片"></div><div class="product-desc"><div class="name">' + shunShopList[i].name + '</div><div class="product-price"><div class="current-price"><div class="price"><span>￥' + shunShopList[i].thirdCost + '</span></div></div><div class="past-price"><span class="line"></span><span>￥' + shunShopList[i].secondCost + '</span></div></div><div class="down-count"><div class="down-count-title">距活动结束还剩：</div><ul class="timer" data-endTime="' + shunShopList[i].endTime + '"><li><span class="days">00</span></li><li class="seperator">天</li><li><span class="hours">00</span></li><li class="seperator">时</li><li><span class="minutes">00</span></li><li class="seperator">分</li><li><span class="seconds">00</span></li><li class="seperator">秒</li></ul></div></div></div></a></li>';
                        $(".product-items").find(".items-inner").append(appendContent);

                        var endTime = $(".product-items .down-count").last().find(".timer").attr('data-endTime');
                        var newEndTime = new Date(parseInt(endTime)).pattern('yyyy-MM-dd hh:mm:ss');
                        if (Date.parse(newEndTime) < Date.now()) {
                            //console.log("true");
                            $(".product-items .down-count").last().html("活动已结束").css("color","#fe4070");
                            $(".link").last().attr("href","").css("color","#fe4070");
                        } else {
                            //console.log("false");

                            $(".product-items .down-count").last().find(".timer").downCount({
                                date: newEndTime,
                                offset: +8
                            }, function () {
                                $(this).parent().html("活动已结束").css("color","#fe4070");
                            });
                        }
                    }
                } else {
                    //没有更多商品
                    var notFoundContent = '<div class="not-found" id="no-more"><div class="not-icon"><svg class="icon" aria-hidden="true"><use xlink:href="#icon-meiyougengduo"></use></svg></div><div class="not-title">没有更多内容</div></div>';
                    var $notFound = $(".not-found");

                    if ($notFound.length == 0) {
                        $(".ui-content").append(notFoundContent);
                        //2s后移除添加的内容
                        /*
                         setTimeout(function () {
                         $("#no-more").remove();
                         }, 2000);
                         */
                    }
                }
            }
        });
    };

    //时间倒计时
    var count = 0;
    $('.timer').each(function (i, value) {
        console.log(count);
        var endTime = $(this).attr('data-endTime');
        if (Date.parse(endTime) < Date.now()) {
            $(this).parent().html("活动已结束").css("color","#fe4070");
            $(".link").eq(count).attr("href","");
        } else {
            $(this).downCount({
                date: endTime,
                offset: +8
            }, function () {
                $(this).parent().html("活动已结束").css("color","#fe4070");
            });
        }
        count++;
    });

    /*对时间戳进行格式化*/
    Date.prototype.pattern=function(fmt) {
        var o = {
            "M+" : this.getMonth()+1, //月份
            "d+" : this.getDate(), //日
            "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时
            "H+" : this.getHours(), //小时
            "m+" : this.getMinutes(), //分
            "s+" : this.getSeconds(), //秒
            "q+" : Math.floor((this.getMonth()+3)/3), //季度
            "S" : this.getMilliseconds() //毫秒
        };
        var week = {
            "0" : "/u65e5",
            "1" : "/u4e00",
            "2" : "/u4e8c",
            "3" : "/u4e09",
            "4" : "/u56db",
            "5" : "/u4e94",
            "6" : "/u516d"
        };
        if(/(y+)/.test(fmt)){
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
        }
        if(/(E+)/.test(fmt)){
            fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);
        }
        for(var k in o){
            if(new RegExp("("+ k +")").test(fmt)){
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
            }
        }
        return fmt;
    }
});