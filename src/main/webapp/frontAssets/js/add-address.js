/**
 * Created by dushang on 2016/9/16.
 */
$(function () {

    if(oldProvince!=null&&oldProvince!=""){
        $('#city').citys({
            province:oldProvince,
            city:oldCity,
            area:oldArea,
            onChange:function(info){
                townFormat(info);
            }
        },function(api){
            var info = api.getInfo();
            townFormat(info);
        });

        setTimeout(function () {
            $('#city select[name=town] option:selected').text(oldTown);
            $('.current-address').html("当前选择地址："+oldProvince+oldCity+oldArea+oldTown);

        },3000)
    }else{
        $('#city').citys({
            onChange:function(info){
                townFormat(info);
            }
        },function(api){
            var info = api.getInfo();
            townFormat(info);
        });
    }


    var $town = $('#city select[name=town]');
    var townFormat = function(info){
        $town.hide().empty();
        if(info['code']%1e4&&info['code']<7e6){	//是否为“区”且不是港澳台地区
            $.ajax({
                url:'http://passer-by.com/data_location/town/'+info['code']+'.json',
                dataType:'json',
                success:function(town){
                    $town.show();
                    for(i in town){
                        $town.append('<option value="'+i+'">'+town[i]+'</option>');
                    }
                    showCurrentAddress();
                }
            });
        }
    };

    // $('.current-address').html("当前选择地址："+oldProvince+oldCity+oldArea+oldTown);


    $('#submit').click(function () {


        var url=$('#addAddressForm').attr('action');
        var param={};


        param['isDel']=$('input[name=isDel]').val();
        param['id']=$('input[name=id]').val();
        param['status']=$('input[name=status]').val();
        param['userId']=$('input[name=userId]').val();
        param['name']=$('input[name=name]').val();
        param['phone']=$('input[name=phone]').val();
        // param['zipcode']=$('input[name=zipcode]').val();
        param['province']=$('#city select[name=province] option:selected').text();
        param['city']=$('#city select[name=city] option:selected').text();
        param['area']=$('#city select[name=area] option:selected').text();
        param['town']=$('#city select[name=town] option:selected').text();
        param['adddetail']=$('#addDetail').val();

        console.log(param);

        var alertInfo="";

        if(param['name']==''){
            alertInfo="收货人姓名不能为空！";
            showAlertInfo(alertInfo);
        }else if(param['phone']==''){
            alertInfo="手机号码不能为空！";
            showAlertInfo(alertInfo);
        }else if(!/^1[34578]\d{9}$/.test(param['phone'])){
            alertInfo="手机号码格式不正确！";
            showAlertInfo(alertInfo);
        }else if(param['adddetail']==''){
            alertInfo="详细地址不能为空！";
            showAlertInfo(alertInfo);
        }else{
            /*向后台传递表单数据*/
            /*禁用按钮*/
            $(this).attr('disabled',"true");
            alertInfo="正在处理，请稍候...";
            showAlertInfo(alertInfo);
            $.post(url,param,function (msg) {

                $('#submit').removeAttr("disabled");

                if(msg["message"]=="操作成功"){
                    alertInfo="操作成功！";
                    showAlertInfo(alertInfo);
                    setTimeout(function () {
                        alertInfo="正在进行页面跳转！";
                        showAlertInfo(alertInfo);
                    },1000);
                    setTimeout(function () {
                        // window.location.href=contextPath+"/addressManagement?userId="+userId;
                        // history.go(-1);
                        // window.location.href="javascript:history.back()";

                        /*不知道为什么需要调用两次才能实现返回上一页，目前没找到原因*/
                        /*奇怪的问题*/
                        // history.back();
                        // history.back();
                        window.location.href=document.referrer;

                        // console.log('hello1');
                        // $('#returnBtn').click();
                        // window.location.href="http://www.baidu.com";
                        // console.log('hello2');
                    },2000);
                }else{
                    alertInfo=msg["message"];
                    showAlertInfo(alertInfo);
                    return false;
                }
            })
        }

        return false;
    });

    function showCurrentAddress() {

        var $province=$('#city select[name=province] option:selected').text();
        var $city=$('#city select[name=city] option:selected').text();
        var $area=$('#city select[name=area] option:selected').text();
        var $town=$('#city select[name=town] option:selected').text();

        $('.current-address').html("当前选择地址："+$province+$city+$area+$town);
    }

    $('#city').on("change",function () {
        showCurrentAddress();
    });

    function showAlertInfo(alertInfo) {
        $('.alert-info').html(alertInfo);
        $('#alertInfoBtn').click();
    }
});