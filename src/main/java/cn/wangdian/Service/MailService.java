package cn.wangdian.Service;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;

/**
 * 提供 Administrator 使用的邮箱服务
 * 这里使用的是腾讯的企业邮箱
 * @author Administrator
 */
@Service
public class MailService {

    private static Log logger = LogFactory.getLog(MailService.class);

    private String host;
    private String port;
    //   private String from;
    private String user;
    private String password;


    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(String port) {
        this.port = port;
    }

//    public void setFrom(String from) {
//        this.from = from;
//    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void sendMessage(String to, String subject, String messageText)
            throws MessagingException, UnsupportedEncodingException {

        // Step 1: Configure the mail session
        logger.debug("Configuring mail session for:" + host);
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.auth", "true");// 指定是否需要SMTP验证
        props.put("mail.smtp.host", host);// 指定SMTP服务器
        props.put("mail.smtp.port", port);
        props.put("mail.transport.protocol", "smtp");
        Session mailSession = Session.getDefaultInstance(props);
        mailSession.setDebug(false);// 是否在控制台显示debug信息
        // Step 2: Construct the message
        logger.debug("Constructing message -  from=" + user + "  to=" + to);
        InternetAddress fromAddress = new InternetAddress(user);
        InternetAddress toAddress = new InternetAddress(to);
        MimeMessage testMessage = new MimeMessage(mailSession);
        testMessage.setFrom(fromAddress);
        testMessage.addRecipient(javax.mail.Message.RecipientType.TO, toAddress);

        testMessage.setSentDate(new java.util.Date());
        testMessage.setSubject(MimeUtility.encodeText(subject, "gb2312", "B"));
        testMessage.setContent(messageText, "text/html;charset=gb2312");
        logger.debug("Message constructed");
        // Step 3: Now send the message
        Transport transport = mailSession.getTransport("smtp");
        transport.connect(host, user, password);
        transport.sendMessage(testMessage, testMessage.getAllRecipients());
        transport.close();
        logger.debug("Message sent!");
    }

    //-----发送计算正确或错误的报告---------------------------------------------------------------------------------------
    public void sendNotify(String to,String code) throws Exception {


        String subject = "网店订单系统";

        StringBuffer theMessage = new StringBuffer();
        theMessage.append("<br><br>");
        theMessage.append("尊敬的用户，您好！<br><br>");
        theMessage.append("请查看下面的系统自动生成的重置密码<br>");
        theMessage.append("------------------------------------------------------------------------------------------------------------------<br><br>");
        theMessage.append("      重置密码为： "+code+"<br>");
        theMessage.append("首次使用系统的重置密码，请尽快修改，以免被不法份子盗用！！！<br>");
        theMessage.append("<br><br>");
        theMessage.append("此邮件由系统发出，请勿直接回复！<br>");
        theMessage.append("------------------------------------------------------------------------------------------------------------------<br>");
        theMessage.append("<br>");

        try {
            sendMessage(to, subject, theMessage.toString());
        } catch (Exception exc) {
//            exc.printStackTrace();
//            throw new Exception("验证邮件发送失败");
            throw exc;
        }

    }

}
