$(function () {
    /**
     * 页面异步加载
     */
    var page = 1; //加载商品的页面
    var lastScrollY = 0;
    var currentScrollY = 0;

    $(window).on("scroll", function () {
        var url = contextPath + "/searchPage?keyword="+keyword;
        windowScroll(page, url);
    });

    function windowScroll(page, url, shopType) {
        var scrollHeight = $(window).height() + window.scrollY;
        var documentHeight = $(document).height(); //文档的高度
        var productItemHeight = $(".product").height(); //商品条目的高度

        //如果顾客浏览商品滚动的距离比页面高度小两个商品条目的高度，则异步加载5个商品
        var load = scrollHeight + productItemHeight  - documentHeight;
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
    function loadProduct(page, url,shopType) {
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
                    windowScroll(page + 1, url , shopType);
                });
                if (result['shopList'].length > 0) {
                    var shopList = result['shopList'];
                    var vipLevel = result['vip'];
                    for (var i = 0; i < shopList.length; i++) {
                        //普通用户
                        var appendContent = "";
                        if (vipLevel == 0) {
                            appendContent = '<li><a href="' + contextPath + '/commodityDetail?id=' + shopList[i].id + '&type=shop" data-ajax="false">' + '<div class="product"><div class="product-img"><img src="' + contextPath + '/' + shopList[i].firstPhoto + '" alt="商品图片"></div><div class="product-desc"><div class="name">' + shopList[i].name + '</div><div class="product-price"><div class="current-price"><span>￥' + shopList[i].secondCost + '</span></div><div class="past-price"><span class="line"></span><span>￥' + shopList[i].pastPrice + '</span></div></div></div></div>' + '</a></li>';
                        }
                        //VIP用户
                        if (vipLevel == 1) {
                            appendContent = '<li><a href="' + contextPath + '/commodityDetail?id=' + shopList[i].id + '&type=shop" data-ajax="false">' + '<div class="product"><div class="product-img"><img src="' + contextPath + '/' + shopList[i].firstPhoto + '" alt="商品图片"></div><div class="product-desc"><div class="name">' + shopList[i].name + '</div><div class="product-price"><div class="current-price"><span>￥' + shopList[i].vipPrice + '</span></div><div class="past-price"><span class="line"></span><span>￥' + shopList[i].pastPrice + '</span></div></div></div></div>' + '</a></li>';
                        }
                        if (vipLevel == 2) {
                            appendContent = '<li><a href="' + contextPath + '/commodityDetail?id=' + shopList[i].id + '&type=shop" data-ajax="false">' + '<div class="product"><div class="product-img"><img src="' + contextPath + '/' + shopList[i].firstPhoto + '" alt="商品图片"></div><div class="product-desc"><div class="name">' + shopList[i].name + '</div><div class="product-price"><div class="current-price"><span>￥' + shopList[i].shopkeeperPrice + '</span></div><div class="past-price"><span class="line"></span><span>￥' + shopList[i].pastPrice + '</span></div></div></div></div>' + '</a></li>';
                        }
                        $(".product-items ul").append(appendContent);
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
    //jQuery function end 
});