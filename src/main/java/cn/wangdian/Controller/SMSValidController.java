package cn.wangdian.Controller;

import cn.wangdian.Service.UserService;
import cn.wangdian.utils.StringUtil;
import cn.wangdian.utils.validate.SMSValidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 短信验证
 * 邮箱验证
 */
@Controller
@RequestMapping("/smsvalid")
public class SMSValidController {
    @Autowired
    private UserService userService;

    /**
     * 前段异步请求发送手机验证码到特定的设备
     * @param telephone 发验证码到该 session
     * @return 0==>该手机号码已经被注册 1==>发送成功
     */
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    @ResponseBody
    public int sendSMSValidCode(@RequestParam(value = "type", required = false, defaultValue = "") String type, @RequestParam("telephone") String telephone,
                                HttpSession session) {
        //生成 6 位随机数字
        String random6NumStr = StringUtil.validCode6();
        //判断该手机号码是否已经存在数据库中
        String isExist = userService.checkTelePhone(telephone);
        //新注册用户和修改手机号码不能够与原来用户的手机号码冲突的
        if ("register".equals(type) || "changeTelephone".equals(type)) {
            if (isExist != null) {
                return 0;
            }
        } else if ("changePassword".equals(type)) {
            //手机号码不存在用户数据库，可以判断该用户操作错误
            if (isExist == null) {
                return 0;
            }
        }
        session.setAttribute("telephone", telephone);
        SMSValidService.sendSMSValidCode(telephone, random6NumStr);
        //把验证码加入 session
        session.setAttribute("smsValidCode", random6NumStr);
        return 1;
    }

    /**
     * 验证前段发来的注册信息，如果验证
     * @param validCode 前段发来的手机验证码 not null
     * @return 0==>手机验证码出错 1==>邮箱已注册 2==>手机号码已注册 3==>邀请人不存在 4==>注册成功
     */
    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    @ResponseBody
    public int register(@RequestParam("validCode") String validCode, HttpSession session) {
        String codeInSession = (String) session.getAttribute("smsValidCode");
        //验证手机验证码
        if (validCode.equals(codeInSession)) {
            return 1;
        }
        return 0;
    }
}
