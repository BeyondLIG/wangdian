package cn.wangdian.utils.validate;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * SMS 阿里云提供的短信服务
 */
public class SMSValidService {
    /**
     * 发送短信验证码 validCode 到 recNum
     *
     * @param recNum    手机号码
     * @param validCode 短信验证码
     */
    public static void sendSMSValidCode(String recNum, String validCode) {
        Map<String, String> headers = new HashMap<>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + SMSConfig.APP_CODE);
        Map<String, String> queries = new HashMap<>();
        queries.put("ParamString", "{\"validCode\":\"" + validCode + "\"}");
        queries.put("RecNum", recNum);
        queries.put("SignName", SMSConfig.SIGN_NAME);
        queries.put("TemplateCode", SMSConfig.TEMPLATE_CODE);

        try{
            HttpUtils.doGet(SMSConfig.HOST, SMSConfig.PATH, SMSConfig.METHOD, headers, queries);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
