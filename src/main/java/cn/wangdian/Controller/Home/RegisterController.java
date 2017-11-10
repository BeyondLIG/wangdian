package cn.wangdian.Controller.Home;

import cn.wangdian.Model.ShopKeeper;
import cn.wangdian.Model.User;
import cn.wangdian.Service.ShopKeeperService;
import cn.wangdian.Service.UserService;
import cn.wangdian.utils.encryption.PBKDF2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 注册控制器
 */
@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @Autowired
    private ShopKeeperService shopKeeperService;

    @RequestMapping("/register")
    public String getRegister() {
        return "frontPage/pages/register";
    }

    /**
     * 注册已经通过测试
     * @param username 用户名
     * @param telephone 手机号码
     * @param validCode 验证码
     * @param password 密码
     * @param referee 推荐人的手机号码
     * @param userType 用户类型 0==>普通用户 1==>vip用户 2==>店主
     */
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Map<String, String> register(@RequestParam("username") String username,
                                        @RequestParam("telephone") String telephone,
                                        @RequestParam("validCode") String validCode,
                                        @RequestParam("password") String password,
                                        @RequestParam(value = "referee") String referee, /*可空*/
                                        @RequestParam("userType") Integer userType,
                                        HttpSession session) {
        Map<String, String> map = new HashMap<>(); //用于最后返回 json 给前段
        String smsValidCode = (String) session.getAttribute("smsValidCode");
        //申请验证码的手机号
        String registerTelephone = (String) session.getAttribute("telephone");
        session.removeAttribute("smsValidCode");
        // 正则表达式判断是否是手机号码
        String regex_mobile = "^1[34578]\\d{9}$";
        if (!Pattern.matches(regex_mobile, telephone)) {
            map.put("message", "手机号码格式不正确，请再次确认");
        } else if(!registerTelephone.equals(telephone)) {
            map.put("message", "请输入你接收验证码的手机号码");
        } else if (!smsValidCode.equals(validCode)) {
            //判断用户输入的验证码是否正确
            map.put("message", "验证码错误");
        } else if (userType != 0 && userType != 1 && userType != 2) {
            map.put("message", "用户注册类型错误");
        } else if (userType != 0 && userType != 2 && (referee == null || referee.equals(""))) {
                //店主的推荐人可以为空
            map.put("message", "注册VIP的推荐人不能够为空");
        } else if (userService.checkUsername(username) != null) {
            map.put("message", "用户名" + username + "已经被注册了，请换一个试试");
        } else if (userService.checkTelePhone(telephone) != null) {
            map.put("message", "手机:" + telephone + "已经被注册了，请换一个试试");
        }else if(shopKeeperService.findByTelephone(referee) == null){
            map.put("message", "推荐人不存在");
        } else {
            //所有信息验证通过
            User user = new User();
            PBKDF2 pbkdf2 = PBKDF2.getInstance();
            String encrypt = pbkdf2.encrypt(password);
            user.setPassword(encrypt);
            user.setUsername(username);
            user.setTelephone(telephone);
            user.setVip(userType);
            user.setIsDel(0);
            //店主用户首先锁定，知道交付年费以后
            if (userType == 2) {
                user.setStatus(1);
            } else {
                user.setStatus(0);
            }
            user.setStatus(0);
            user.setPay(0);
            //存在推荐人
            ShopKeeper shopKeeperReferee = null;
            if (referee != null && !referee.equals("")) {
                shopKeeperReferee = shopKeeperService.findByTelephone(referee);
            }
            if (userType == 1) {
                //vip 用户需要填写店主
                shopKeeperReferee.addUserList(user);
                //更新数据库 ==> 已经把 vip user 保存在 wd_user 中
                shopKeeperService.update(shopKeeperReferee);
                shopKeeperService.updateNumberByAddUser(shopKeeperReferee.getId());
            }
            //店主注册，需要将另外一份数据保存在 ShopKeeper 数据库表中
//            else if (userType == 2) {
//                ShopKeeper shopKeeper = new ShopKeeper();
//                shopKeeper.setUsername(username);
//                shopKeeper.setIsDel(0);
//                shopKeeper.setPassword(encrypt);
//                shopKeeper.setStatus(1); //店主刚刚注册，还没有提交年费，让店主处于锁定状态
//                shopKeeper.setTelephone(telephone);
//                shopKeeper.setAllProfit(0);
//                shopKeeper.setYiTiXian(0);
//                shopKeeper.setAllUserNumber(0);
//                shopKeeper.setDirectUserNumber(0);
//                shopKeeper.setLevel(0);
//                shopKeeper.setAllShopkeeperNumber(0);
//                shopKeeper.setDirectShopkeeperNumber(0);
//                shopKeeper.setRegisterTime(new Date());
//                shopKeeper.setDeathTime(new Date());
//                shopKeeper.setIsNew(1); //该店主是新招的
//                if (shopKeeperReferee != null) {
//                    //有推荐人
//                    shopKeeper.setBelong(shopKeeperReferee.getId());
//                } else {
//                    //该店主没有上一级店主
//                    shopKeeper.setBelong(0);
//                }
//                shopKeeperService.save(shopKeeper);
//                if (shopKeeperReferee != null) {
//                    //更新上一级店主的直招店主数目和间接招店主的数目
//                    shopKeeperService.updateNumberByAddShopkeeper(shopKeeperReferee.getId());
//                    map.put("url", "/annualFee/submitAnnualFee");
//                }
//            }
            //用户登录页面
            map.put("url", "/login");
//            if (userType != 1) {
//                //如果是 vip 用户在上面已经持久化了，不需要再执行持久化
//                userService.save(user);
//            }
            map.put("username", username); //返回用户名
            map.put("telephone", telephone); //返回手机号码，方便店主交年费
            map.put("message", "注册成功");
        }
        return map;

    }
}
