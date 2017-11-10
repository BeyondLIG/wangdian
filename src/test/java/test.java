import cn.wangdian.Service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by dushang on 2016/10/2.
 */
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class test {

    @Autowired
    private MailService mailService;

    @Test
    public void hello() throws Exception{
           mailService.sendNotify("2506597416@qq.com","12322");
    }

}