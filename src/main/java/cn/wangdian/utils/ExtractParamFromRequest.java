package cn.wangdian.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于从 request 中提取参数
 */
public class ExtractParamFromRequest {
    /**
     * 用于查看字符串的编码格式
     */
    private Encode encodeUtil = new Encode();

    /**
     * 从 HttpServletRequest 中提取参数
     * @return 把需要参数提取出来后的 Map<String, String>
     */
    public Map<String, String> extractParamsFromRequest(HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<>();
        Map requestParams = request.getParameterMap(); //将 request 所有参数加入到 Map 中
        for (Object o : requestParams.keySet()) {
            String name = (String) o; //首先把参数的键值对的 键 key 取出来
            String[] values = (String[]) requestParams.get(name); //把参数中键 key 对应的值 value 取出来
            //String[] 将数组中的内容转化为一个字符串 String
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? (valueStr + values[i]) : (valueStr + values[i] + ",");
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            String encode = encodeUtil.getEncoding(valueStr);
            if (!encode.equalsIgnoreCase("GB2312")) {
                // 如果字符串的编码不是 GB2312 就转化为 utf-8
                valueStr = new String(valueStr.getBytes(encode), "UTF-8");
            }
            params.put(name, valueStr);
        }
        return params;
    }
}
