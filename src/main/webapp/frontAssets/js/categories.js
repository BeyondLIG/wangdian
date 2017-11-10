/**
 * Created by dushang on 2016/9/13.
 */
$(function () {

    //"styleNum":分类，"modelNum":型号

    /*获取商品分类列表*/
    /*$.ajax({
        url:'',
        type:'POST',
        cache:false,
        success:function (data) {
            setList(data,"styleNum");
        }
    });*/
    // var data=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22];
    //前端传回的数组
    setList(styleNum,"styleNum");
    setSlider();

    /*绑定商品分类列表元素点击事件*/
    $('.styleNum').on('click',function () {
        var styleNum=$(this).text();
        /*将当前选择的商品分类存储到本地*/
        sessionStorage.setItem('styleNum',styleNum);
        // console.log(styleNum);
        $.ajax({
            url:contextPath+'/specification',
            type:'POST',
            data:{
                styleNum:styleNum
            },
            cache:false,
            success:function (data) {
                clearSlider();
                setList(data,"modelNum");
                setSlider();

                /*绑定商品型号列表元素点击事件*/
                $('.modelNum').on('click',function () {
                    var modelNum=$(this).text();
                    console.log(modelNum);
                    var url= contextPath+"/commodityList";
                    /*跳转到商品列表页面，携带参数：商品分类、商品型号*/
                    window.location.href=url+"?shopType="+sessionStorage.getItem('styleNum')+"&shopModel="+modelNum;
                });
            }

        });
    });


    function setList(data,type) {
        //data,形如：data=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22]

        var listNum = parseInt(data.length/10)+1;

        for(var i=1;i<=listNum;i++){
            $('#sliderList').append('<li><div class="ui-grid-d"></div></li>');
        }

        $('.ui-grid-d').each(function (i) {
            for(var j=1;j<=data.length;j++){
                if((j%5==1)&&(parseInt(j/10)==i)){
                    $(this).append("<div class='ui-block-a "+type+"'><span>"+data[j-1]+"</span></div>");
                }else if((j%5==2)&&(parseInt(j/11)==i)){
                    $(this).append("<div class='ui-block-b "+type+"'><span>"+data[j-1]+"</span></div>");
                }else if((j%5==3)&&(parseInt(j/11)==i)){
                    $(this).append("<div class='ui-block-c "+type+"'><span>"+data[j-1]+"</span></div>");
                }else if((j%5==4)&&(parseInt(j/11)==i)){
                    $(this).append("<div class='ui-block-d "+type+"'><span>"+data[j-1]+"</span></div>");
                }else if((j%5==0)&&(parseInt(j/11)==i)){
                    $(this).append("<div class='ui-block-e "+type+"'><span>"+data[j-1]+"</span></div>");
                }
            }
        });
    }


    function clearSlider() {
        var sliderNode="<div class='my-slider'><ul id='sliderList'></ul></div>";
        $('.unslider').remove();
        $('.recommendation').before(sliderNode);
    }

    function setSlider() {

        var unslider=$('.my-slider').unslider({
            speed: 1000,               //  The speed to animate each slide (in milliseconds)
            delay: 3000,              //  The delay between slide animations (in milliseconds)
            fluid: false,             //  Support responsive design. May break non-responsive designs
            arrows:false,
            infinite:true
        });
        /*手机端触屏*/
        $('.my-slider').on('swipeleft', function(e) {
            unslider.stop().prev();
        }).on('swiperight', function(e) {
            unslider.stop().next();
        });
    }

    /**/
    var page=1;
    var noMore=false;

    /*页面滚动时计算距离页面底部距离*/
    $(window).scroll(function () {
        var pageHeight=document.body.scrollHeight;   //窗口文档body的高度
        var scrollTop=document.body.scrollTop;        //滚动条top
        var viewHeight=$(window).height();          //视口高度

        var toBoottom=pageHeight-scrollTop-viewHeight;      //距离页面底部的距离

        /*当距离页面底部距离很小时，ajax加载更多内容*/
        if(toBoottom<1){
            ajaxGetData();
        }
    });


    /*通过ajax，传递当前页面参数，获取页面数据*/
    function ajaxGetData() {
        $.ajax({
            url:contextPath+'/isRecommendPage',
            type:'POST',
            data:{
                page:page
            },
            cache:false,
            success:function (data) {
                if(data['shopList'].length!=0){
                    var shopList=data['shopList'];
                    for(var i=0;i<shopList.length;i++){
                        var appendContent="<a class='ui-grid-solo' href='"+contextPath+"/commodityDetail?id="+shopList[i].id+"&type=shop' data-ajax='false'>"+
                            "<div class='ui-block-a'>"+
                            "<img src='"+contextPath+"/"+shopList[i].firstPhoto+"'>" +
                            "<div class='commodity-info'>" +
                            "<div>"+shopList[i].name+"</div>" +
                            "<div class='price'><span>"+shopList[i].secondCost+"</span></div>" +
                            "</div></div></a>";

                        $('.commodities').append(appendContent);

                    }
                    console.log(shopList);
                }else{
                    if(!noMore){
                        $('.commodities').append("<div class='no-more'>----没有更多内容！----</div>");
                        noMore=true;
                    }

                }
                page++;
            }
        })
    }
});

