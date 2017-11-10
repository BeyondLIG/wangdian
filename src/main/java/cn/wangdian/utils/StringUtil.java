package cn.wangdian.utils;

import java.util.Random;

/**
 * 生成随机的字母串，主要用于动态生成验证码
 */
public class StringUtil {

    /**
     * 生成 6 位由英文字母、数字组成的随机字符串
     */
    public static String random(){
        String base="abcdefghijklmnopqrstuvwxyz0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for (int i=0;i<6;i++){
            int number=random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 生成 4 位由纯英文字母组成的随机字符串
     */
    public static String ziMuRandom(){
        String base="abcdefghijklmnopqrstuvwxyz";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for (int i=0;i<4;i++){
            int number=random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 生成 6 位由纯数字组成的随机字符串
     * @return 6位数字验证码
     */
    public static String validCode6() {
        String base = "0123456789";
        StringBuilder builder = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 6; i++) {
            builder.append(base.charAt(rand.nextInt(10)));
        }
        return builder.toString();
    }
}
