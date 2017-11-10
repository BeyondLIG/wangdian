package cn.wangdian.Controller.Home;

import cn.wangdian.Service.MailService;
import cn.wangdian.Service.ShopKeeperService;
import cn.wangdian.Service.UserService;
import cn.wangdian.utils.StringUtil;
import cn.wangdian.utils.encryption.PBKDF2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户和店主修改密码的控制器
 */
@Controller
public class UserShopKeeperChangePasswordController {
    @Autowired
    private UserService userService;

    @Autowired
    private ShopKeeperService shopKeeperService;

    @Autowired
    private MailService mailService;

    private PBKDF2 pbkdf2 = PBKDF2.getInstance();
    @RequestMapping("/forgetPassword")
    public String forgetPassword(){
        return "frontPage/pages/forget-password";
    }

    /**
     * 用户通过手机号码的验证码来修改密码
     */
    @ResponseBody
    @RequestMapping(value = "/forgetPassword",method = RequestMethod.POST)
    public Map<String,String> forgetPassword(@RequestParam("validCode") String validCode,
                                             @RequestParam("newPassword") String newPassword,
                                             @RequestParam("telephone") String telephone,
                                             HttpSession session){
        Map<String,String> map= new HashMap<>();
        //接受验证码的手机号码
        String validTelephone = (String) session.getAttribute("telephone");
        /*if (validCode == null || "".equals(validCode)) {
            map.put("message", "请输入验证码");
        } else if (!telephone.equals(validTelephone)) {
            map.put("message", "请输入你接收验证码的手机号码");
        } else */{
            String smsCode = (String) session.getAttribute("smsValidCode");
            session.removeAttribute("smsValidCode");
//            if (validCode.equals(smsCode)) {
                //通过验证
                String newPasswordEncrypt = pbkdf2.encrypt(newPassword);
                userService.updatePasswordByTelephone(newPasswordEncrypt, telephone);
                //如果是店主修改密码，需要把店主的密码也更新一遍
                Integer vip = userService.findByTelephone(telephone).getVip();
                if (vip != null && vip ==2){
                    shopKeeperService.updatePasswordByTelephone(newPasswordEncrypt, telephone);
                }
                map.put("message", "修改密码成功");
//            }
        }
        return map;
    }
}
