package cn.wangdian.utils;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by 25065 on 2016/5/14.
 * 用于生成 JSON, RESTful 多用于异步请求
 * 回调函数参数
 *statusCode	int	必选。状态码(ok = 200, error = 300, timeout = 301)，可以在BJUI.init时配置三个参数的默认值。
 *message	string	可选。信息内容。
 *tabid	string	可选。待刷新navtab id，多个id以英文逗号分隔开，当前的navtab id不需要填写，填写后可能会导致当前navtab重复刷新。
 *dialogid	string	可选。待刷新dialog id，多个id以英文逗号分隔开，请不要填写当前的dialog id，要控制刷新当前dialog，请设置dialog中表单的reload参数。
 *divid	string	可选。待刷新div id，多个id以英文逗号分隔开，请不要填写当前的div id，要控制刷新当前div，请设置该div中表单的reload参数。
 *closeCurrent	boolean	可选。是否关闭当前窗口(navtab或dialog)。
 *forward	string	可选。跳转到某个url。
 *forwardConfirm string	可选。跳转url前的确认提示信息。
 */
public class ExecuteResult {

    public Map<String,Object> jsonReturnForHead(int statusCode){
        Map<String,Object> jsonObj=new HashMap<String,Object>();

        if (statusCode==200){
            jsonObj.put("statusCode","200");
            jsonObj.put("message","操作成功");
        }else if (statusCode==300){
            jsonObj.put("statusCode","300");
            jsonObj.put("message","操作失败，请重试");
        }
        jsonObj.put("tabid","table, table-fixed");
        jsonObj.put("closeCurrent",true);
        jsonObj.put("forward","");
        jsonObj.put("forwardConfirm","");
        return jsonObj;
    }

    /**
     * closeCurrent=True.
     */
    public Map<String,Object> jsonReturn(int statusCode){
        Map<String,Object> jsonObj=new HashMap<String,Object>();

        if (statusCode==200){
            jsonObj.put("statusCode","200");
            jsonObj.put("message","操作成功");
        }else if (statusCode==300){
            jsonObj.put("statusCode","300");
            jsonObj.put("message","操作失败，请重试");
        }
        jsonObj.put("closeCurrent",true);
        return jsonObj;
    }

    /**
     * closeCurrent=True. And refresh the specified dialog by dialogid.
     * @param dialogid which dialog should be refresh.
     */
    public Map<String,Object> jsonReturnAndRefreshDialogId(int statusCode,String dialogid){
        Map<String,Object> jsonObj=new HashMap<String,Object>();

        if (statusCode==200){
            jsonObj.put("statusCode","200");
            jsonObj.put("message","操作成功");
            jsonObj.put("dialogid",dialogid);
        }else if (statusCode==300){
            jsonObj.put("statusCode","300");
            jsonObj.put("message","操作失败，请重试");
        }
        jsonObj.put("closeCurrent",true);
        return jsonObj;
    }

    /**
     *
     * @param closeCurrent 决定是否要 closeCurrent
     */
    public Map<String,Object> jsonReturn(int statusCode,boolean closeCurrent){
        Map<String,Object> jsonObj=new HashMap<String,Object>();

        if (statusCode==200){
            jsonObj.put("statusCode","200");
            jsonObj.put("message","操作成功");
        }else if (statusCode==300){
            jsonObj.put("statusCode","300");
            jsonObj.put("message","操作失败，请重试");
        }
        jsonObj.put("closeCurrent",closeCurrent);
        return jsonObj;
    }

    public Map<String,Object> jsonReturnIndexAndType(int statusCode,Integer index,String type,boolean closeCurrent){
        Map<String,Object> jsonObj=new HashMap<String,Object>();

        if (statusCode==200){
            jsonObj.put("statusCode","200");
            jsonObj.put("index",index);
            jsonObj.put("type",type);
            jsonObj.put("message","操作成功");
        }else if (statusCode==300){
            jsonObj.put("statusCode","300");
            jsonObj.put("message","操作失败，请重试");
        }
        jsonObj.put("closeCurrent",closeCurrent);
        return jsonObj;
    }

    /**
     *
     * @param msg 返回特定的信息，添加到 Json 返回给前端
     */
    public Map<String,String> jsonReturn(int statusCode,String msg){
        Map<String,String> jsonObj=new HashMap<String,String>();

        if (statusCode==200){
            jsonObj.put("statusCode","200");
            jsonObj.put("message","操作成功"+msg);
        }else if (statusCode==300){
            jsonObj.put("statusCode","300");
            jsonObj.put("message","操作失败，请重试"+msg);
        }
        return jsonObj;
    }

    /**
     * 上传文件 图片后，返回数据给前端，判断是否上传成功，以及文件 图片的保存位置
     * @param fileNme 被上传文件的名称
     * @param path 上传的文件保存到哪一个路径下
     */
    public Map<String,Object> jsonReturnFile(int statusCode,String fileNme,String path){
        Map<String,Object> jsonObj=new HashMap<String,Object>();

        if (statusCode==200){
            jsonObj.put("statusCode","200");
            jsonObj.put("filename",fileNme);
            jsonObj.put("filepath",path);
            jsonObj.put("message","上传成功");
        }else if (statusCode==300){
            jsonObj.put("statusCode","300");
            jsonObj.put("filename",fileNme+"上传失败，请重试");
            jsonObj.put("message","上传失败，请重试");
        }
        jsonObj.put("closeCurrent",true);
        return jsonObj;
    }

    /**
     *
     * @param msg 返回给前端的信息
     * @param closeCurrent 添加是否要关闭前端的某个页面
     */
    public Map<String,Object> jsonReturn(int statusCode,String msg,boolean closeCurrent){
        Map<String,Object> jsonObj=new HashMap<String,Object>();

        if (statusCode==200){
            jsonObj.put("statusCode","200");
            jsonObj.put("message",msg);
        }else if (statusCode==300){
            jsonObj.put("statusCode","300");
            jsonObj.put("message",msg);
        }
        jsonObj.put("closeCurrent",closeCurrent);
        return jsonObj;
    }

    public Map<String,Object> ajaxDonejsonReturn(int statusCode,boolean closeCurrent){
        Map<String,Object> jsonObj=new HashMap<String,Object>();

        if (statusCode==200){
            jsonObj.put("statusCode","200");
            jsonObj.put("message","\u64cd\u4f5c\u6210\u529f"); // '操作成功' utf-8 编码
            jsonObj.put("tabid","");
            jsonObj.put("forward","");
            jsonObj.put("forwardConfirm","");
        }else if (statusCode==300){
            jsonObj.put("statusCode","300");
            jsonObj.put("message","操作失败");
            jsonObj.put("tabid","");
            jsonObj.put("forward","");
            jsonObj.put("forwardConfirm","");
        }
        jsonObj.put("closeCurrent",closeCurrent);
        return jsonObj;
    }

}
