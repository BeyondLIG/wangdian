/**
 * Created by dushang on 2016/9/12.
 */
/*
 $(function () {
 var unslider=$('.my-slider').unslider({
 speed: 1000,               //  The speed to animate each slide (in milliseconds)
 delay: 3000,              //  The delay between slide animations (in milliseconds)
 fluid: false,             //  Support responsive design. May break non-responsive designs
 autoplay:true,
 arrows:false,
 infinite:true
 });
 /*手机端触屏*/
/*
 $('.my-slider').on('swipeleft', function(e) {
 unslider.stop().prev();
 }).on('swiperight', function(e) {
 unslider.stop().next();
 });

 });*/

/* Date: 2017-04-07T17:46:51Z Path: js/touch/index/index_nav.js */
/*
 !function() {
 var n = {
 outer: null,
 $inner: null,
 hidden_width: 0,
 current_x: 0,
 start_x: 0,
 scale: 1,
 distance: 0,
 init: function(n, e) {
 var t = this;
 if (t.outer = $(n)[0],
 t.$inner = $(e),
 t.outer && t.$inner.length > 0) {
 var i = 0
 , r = $(window).width();
 t.$inner.children().each(function() {
 i += $(this).width()
 }),
 i = i < r ? r : i,
 t.hidden_width = i - $(t.outer).width(),
 t.$inner.width(i + 5),
 t.outer.addEventListener("touchstart", t.eventlistener, !1)
 }
 t.scrollEvent()
 },
 scrollEvent: function() {
 var n = $("#nav-outer")
 , e = $("#hearder_nav");
 $(window).on("scroll", function() {
 $(window).scrollTop() > e.offset().top ? n.addClass("fix-menu") : n.removeClass("fix-menu")
 })
 },
 eventlistener: {
 handleEvent: function(n) {
 switch (n.type) {
 case "touchstart":
 this.start(n);
 break;
 case "touchmove":
 this.move(n);
 break;
 case "touchend":
 this.end(n)
 }
 },
 start: function() {
 n.start_x = event.touches[0].pageX,
 n.outer.addEventListener("touchmove", this, !1)
 },
 move: function() {
 var e = event || window.event;
 e.preventDefault(),
 e.stopPropagation(),
 n.move_x = event.touches[0].pageX,
 n.current_x = n.current_x + n.move_x - n.start_x,
 n.start_x = n.move_x,
 n.transform()
 },
 end: function() {
 n.outer.removeEventListener("touchmove", this, !1),
 n.outer.removeEventListener("touchend", this, !1)
 }
 },
 transform: function(e) {
 var t = this;
 if (-n.current_x >= t.hidden_width) {
 if (-n.current_x === t.hidden_width)
 return null;
 n.current_x = -t.hidden_width
 } else if (n.current_x >= 0) {
 if (0 === n.current_x)
 return null;
 n.current_x = 0
 }
 $(t.outer).css("-webkit-transform", "translateX(" + n.current_x + "px)")
 }
 };

 $("#hearder_nav").length > 0 && n.init("#nav-outer", "#nav-inner"),
 {
 init: function() {
 this.eventBind();
 var n = "";
 location.pathname.indexOf("/muandbaby/index") > -1 && (n = "/muandbaby/index"),
 location.pathname.indexOf("/luxury/index") > -1 && (n = "/luxury/index"),
 n && $('#nav-inner a[href="' + n + '"]').parent().addClass("nav_select")
 },
 eventBind: function() {
 $(".navTitle:not(.nt_mall)").on("click", function() {
 $(this).addClass("nav_select").siblings().removeClass("nav_select")
 }),
 $(".nt_mall").on("click", function() {
 $(this).toggleClass("nav_select")
 })
 }
 }.init()
 }();
 */
$(function () {
    /**
     * 搜索跳转
     */
    $(".search-box").find(".icon").click(function () {
        //除去字符串两边的空格
        var keyword = $("#search-input").val().replace(/(^\s+)|(\s+$)/g, "");
        if ("" === keyword)
            return null;
        window.location.href = contextPath + "/search?keyword=" + keyword;
    });
    $("#search-input").on("keydown", function (e) {
        //如果按下回车键，则以输入的值进行搜索
        if (e.keyCode === 13) {
            //除去字符串两边的空格
            var keyword = $("#search-input").val().replace(/(^\s+)|(\s+$)/g, "");
            if ("" === keyword)
                return null;
            window.location.href = contextPath + "/search?keyword=" + keyword;
        }
    });
    /**
     * 上划时搜索按钮实现
     */

    /**
     * 加入购物车按钮实现
     */
    $(".product").find(".button").on("click", function (e) {
        e.preventDefault();
        e.stopPropagation();
        var shopId = $(this).attr("id");
        window.location.href = contextPath + "/chooseParam?shopId=" + shopId + "&type=shop&direction=addToShopCart"
    });

    /**
     * 动态生成商品分类信息
     */
    setList(data);
    //更新分类列表
    function setList(data) {
        //data,形如：data=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22]
        //script内嵌传值
        var $classifyUl = $(".classify-items").find("ul");
        for (var i = 0; i < data.length; i++) {
            $classifyUl.append('<li class="classify"><span class="classify-title">' + data[i] + '</span><span class="choose-line"></span></li>');
        }
    }

    /**
     *根据选择的分类选择显示样式条
     */
    var $classify = $(".classify");
    $classify.on("click", function (e) {
        //原display属性为block，JQuery自带的show方法会设置display为inline，造成样式异常
        $(".choose-line").css("display", "none");
        $(this).find(".choose-line").css("display", "block");
    });

    /**
     * 页面异步加载
     */
    var page = 1; //加载商品的页面
    var lastScrollY = 0;
    var currentScrollY = 0;
    $(window).on("scroll", function () {
        var url = contextPath + "/isRecommendPage";
        windowScroll(page, url);
    });

    /**
     * 分类栏选择分类异步加载商品
     */
    /*绑定商品分类列表li元素点击事件*/
    $classify.on('click', function () {
        var styleNum = $(this).find(".classify-title").text(); //商品分类
        //重置page参数
        page = 1;
        lastScrollY = 0;
        currentScrollY = 0;
        //取消默认的窗口滚动事件，绑定新的窗口滚动事件
        $(window).off("scroll");
        //对推荐进行特殊处理
        if (styleNum == "推荐") {
            var url = contextPath + "/isRecommendPage";
            $(window).on("scroll", function () {
                windowScroll(page, url);
            });
            loadClassifyProduct({
                page: 0
            }, url);
        } else {
            /*将当前选择的商品分类存储到本地*/
            //sessionStorage.setItem('styleNum', styleNum);
            //发送异步请求
            var url = contextPath + "/commodityListPage";
            loadClassifyProduct({
                shopType: styleNum,
                page: 0
            }, url);
            $(window).on("scroll", function () {
                windowScroll(page, url, styleNum);
            });
        }
    });


    //窗口滚动事件
    function windowScroll(page, url, shopType) {
        if (window.scrollY > 36) {
            $(".fixed-header").css("position", "fixed").css("top", "-36px");
        } else {
            $(".fixed-header").css("position", "absolute").css("top", "0");
        }

        var scrollHeight = $(window).height() + window.scrollY;
        var documentHeight = $(document).height(); //文档的高度
        var productItemHeight = $(".product").height(); //商品条目的高度

        //如果顾客浏览商品滚动的距离比页面高度小两个商品条目的高度，则异步加载5个商品
        var load = scrollHeight + productItemHeight * 2 - documentHeight;
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


    /**
     * 异步加载分类商品
     * @param {any} data ajax异步发送的数据
     * @param {any} url 
     */
    function loadClassifyProduct(data, url) {
        $.ajax({
            url: url,
            method: "POST",
            data: data,
            cache: false,
            success: function (result) {
                $(".product-items ul li").remove();
                if (result['shopList'].length > 0) {
                    var shopList = result['shopList'];
                    var vipLevel = result['vip'];
                    for (var i = 0; i < shopList.length; i++) {
                        //普通用户
                        var apendContent = "";
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
    }
    //jQuery function end 
});


/**
 * 根据分类的条目数，自动设置分类栏宽度，响应分类栏滑动
 */
$(function () {
    var startX = 0;
    var currentX = 0
    var moveX = 0;

    var classifyBar = document.getElementsByClassName("classify-bar")[0];

    var i = 0,
        r = $(window).width();
    $(".classify-bar ul").children().each(function () {
        i += $(this).width() + parseInt($(this).css("margin-left")) * 2 + 4; //每个li之间有空隙
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