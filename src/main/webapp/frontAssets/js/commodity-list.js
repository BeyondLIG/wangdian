/**
 * Created by dushang on 2016/10/2.
 */
$(function () {
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

        var url="";
        var data={};
        if(shopName!=""&&shopType==""&&shopModel==""){
            url=contextPath+'/searchPage';
            data={
                shopName:shopName,
                page:page
            }
        }else{
            url=contextPath+'/commodityListPage';
            data={
                shopType:shopType,
                shopModel:shopModel,
                page:page
            }
        }

        $.ajax({
            url:url,
            type:'POST',
            data:data,
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