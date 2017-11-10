<%--
  Created by IntelliJ IDEA.
  User: dushang
  Date: 2016/7/15
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
    /*图片裁剪部分*/
    .img-cropper{
        margin-top: 10px;
        margin-left: 5px;
        padding-right: 5px;
        display: flex;
        display: -webkit-flex;
        justify-content: space-between;
    }
    .img-container,
    .img-preview {
        background-color: #f7f7f7;
        text-align: center;
        overflow: hidden;
    }
    .img-container{
        width: 600px;
        height: 400px;
    }
    .preview-container{
        width: 202px;
        height: 400px;
    }
    .preview-container>p{
        width: 200px;
        font-size: 14px;
        color: #999;
        text-align: center;
        padding: 10px 0;
        margin-bottom: 60px;
    }
    .img-preview{
        width: 202px !important;
        height: 202px !important;
        border: 1px solid #ccc;
        overflow: hidden;
    }
    .img-container > img ,.img-preview > img {
        max-width: 100%;
    }
    .preview-container>.btn{
        height: 40px;
        line-height: 34px;
    }
    .preview-container>.btn:nth-of-type(1){
        margin-bottom: 20px;
    }

</style>

<div class="img-cropper">
    <div class="img-container">
        <img id="image" src="${pageContext.request.contextPath}/backAsset/Static/Public/Img/default-img.jpg" alt="default-img">
    </div>
    <div class="preview-container">
        <div class="img-preview"></div><p>200px*200px</p>
        <label class="btn btn-primary btn-block" for="importImg" title="Upload image file">
            <input type="file" class="sr-only" id="importImg" name="file" accept="image/*">
            选择图片
        </label>
        <%--<button id="chooseImg" class="btn btn-primary btn-block">选择图片</button>--%>
        <button id="uploadBtn" class="btn btn-success btn-block">确认添加</button>
    </div>
</div>
<script>
    var $img= $('#image');
    var options = {
        aspectRatio: 1,
        preview: '.img-preview'
    };

    $img.cropper(options);

    // Import image
    var $importImg = $('#importImg');
    var URL = window.URL || window.webkitURL;
    var blobURL;

    if (URL) {
        $importImg.change(function () {
            var files = this.files;
            var file;

            if (!$img.data('cropper')) {
                return;
            }

            if (files && files.length) {
                file = files[0];

                if (/^image\/\w+$/.test(file.type)) {
                    blobURL = URL.createObjectURL(file);
                    $img.one('built.cropper', function () {

                        // Revoke when load complete
                        URL.revokeObjectURL(blobURL);
                    }).cropper('reset').cropper('replace', blobURL);
                    $importImg.val('');
                } else {
                    window.alert('Please choose an image file.');
                }
            }
        });
    } else {
        $importImg.prop('disabled', true).parent().addClass('disabled');
    }

    // Upload cropped image to server if the browser supports `HTMLCanvasElement.toBlob`
    $('#uploadBtn').on('click',function () {
        $.ajax({
            url:url,
            type: "POST",
            enctype:"multipart/form-data",
            data:{
                id:id,
                imgId:imgId,
                imgContent:$img.cropper('getCroppedCanvas',{width:200,height:200}).toDataURL()//表示把base64的图片字符格式提交到后台
            },
            success: function (data) {
                console.log('Upload success');
                $.CurrentDialog.find('.close').click();
                $('.images-upload .img').find('a').each(function () {
                    if ($(this).attr('data-imgId')==data.id){
                        console.info(data.id+" "+$(this).attr('data-imgId'));
                        $(this).parent().find('img').attr("src",webRoot+"/"+data.imageSrc);
                    }
                })
            },
            error: function () {
                console.log('Upload error');
            }
        })
    })

//    function getRootPath_web() {
//        //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
//        var curWwwPath=window.document.location.href;
//        //获取主机地址之后的目录，如： /uimcardprj/share/meun.jsp
//        var pathName=window.document.location.pathname;
//
//        var pos=curWwwPath.indexOf(pathName);
//        //获取主机地址，如： http://localhost:8083
//        var localhostPaht=curWwwPath.substring(0,pos);
//        //获取带"/"的项目名，如：/uimcardprj
//        var projectName=pathName.substring(0,pathName.substr(1).indexOf("/")+1);
//        if (projectName=="Admin"){
//            return localhostPaht;
//        }else {
//            return localhostPaht+projectName;
//        }
//    }
</script>
