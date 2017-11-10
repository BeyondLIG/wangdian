$(function () {

    var buyCount=$('form input[name=count]').val();

    $('#minusBtn').click(function () {
        if(buyCount>1){
            buyCount--;
            $('form input[name=count]').val(buyCount);
            $('.buy-count span').text(buyCount);
        }
    });

    $('#plusBtn').click(function () {
        buyCount++;
        $('form input[name=count]').val(buyCount);
        $('.buy-count span').text(buyCount);

    });

    if(submitAvailableTest()){
        $('#confirmBtn').addClass('available');
    }


    $('input[type=radio]').change(function () {
        var $this=$(this);
        var appendFlag=true;
        var inputName=$(this).attr('name');
        if($("input[name="+inputName+"]").length>1){
            $('.chosen-parameter span').each(function (i) {
                if($(this).attr('data-type')==$this.attr('name')){
                    // val.html($this.val());
                    appendFlag=false;
                }
            });

            if(appendFlag){
                $('.chosen-parameter').append("<span data-type='"+$this.attr('name')+"'>"+$this.attr('name')+"："+$this.val()+"</span>")
            }else{
                $(".chosen-parameter span[data-type="+$this.attr('name')+"]").html($this.attr('name')+"："+$this.val());
            }
        }
        if(submitAvailableTest()){
            $('#confirmBtn').addClass('available');
        }
    });


    /*根据参数是否选择完判断提交按钮是否可用*/
    function submitAvailableTest() {
        // console.log(($('.chosen-parameter span').length));
        // console.log($('fieldset').length);
        return (($('.chosen-parameter span').length)==$('fieldset').length);
    }


    /*确定按钮点击事件*/
    $('#confirmBtn').click(function (e) {

        if(submitAvailableTest()){
            var chosenParameter="";
            $('.chosen-parameter span').each(function () {
                chosenParameter+=$(this).text()+"、";
            });
            chosenParameter=chosenParameter.substring(0,chosenParameter.length-1);

            $('input[name=shopDetail]').val(chosenParameter);
        }else{
            return false;
        }
    });

});

window.onload=function(){
    // console.log(sessionStorage.getItem('freshFlag'));
    if (sessionStorage.getItem('freshFlag')=="1")
    {
        window.location.reload();
        sessionStorage.setItem('freshFlag',"0");
    }
};


