import cn.wangdian.utils.StringUtil;
import cn.wangdian.utils.validate.SMSValidService;

public class SMS {
    public static void main(String[] args){
        SMSValidService.sendSMSValidCode("15871489335", StringUtil.validCode6());
    }
}
