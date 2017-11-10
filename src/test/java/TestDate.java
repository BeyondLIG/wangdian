import cn.wangdian.utils.encryption.PBKDF2;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {
    public static void main(String[] args){
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(sdf.format(date));
        PBKDF2 pbkdf2 = PBKDF2.getInstance();
        System.out.println(pbkdf2.encrypt("20170407"));
//        boolean valid = pbkdf2.validate("123456", "1000:ca187cee4440d5231287c1824008f762:b25ed5d1fee2b0cf41942c1dd81aeab7019e85d723bd784ce755f87fe4e40f29ecd38f8c4ededf3118eb4743c58aeb02abed8c5a3866838de8dfdc25cc0a30dc");
//        System.out.println(valid);
//        System.out.println(Integer.MAX_VALUE);
    }
}
