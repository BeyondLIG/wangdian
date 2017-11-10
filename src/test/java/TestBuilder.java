import cn.wangdian.utils.StringUtil;
import cn.wangdian.utils.alipay.config.AlipayConfig;
import cn.wangdian.utils.encryption.PBKDF2;

/**
 * 测试随机验证码生成
 */
public class TestBuilder {
    public static void main(String[] args){
//        System.out.println(StringUtil.validCode6());
        PBKDF2 pbkdf2 = new PBKDF2();
//        System.out.println(pbkdf2.encrypt("123456"));
//        System.out.println(pbkdf2.encrypt("workhard"));
//        String storedPassword = pbkdf2.encrypt("workhard");
//        System.out.println(pbkdf2.validate("workhard", storedPassword));
        System.out.println(pbkdf2.encrypt("123456"));
    }
}
