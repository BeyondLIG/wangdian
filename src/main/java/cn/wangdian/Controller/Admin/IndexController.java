package cn.wangdian.Controller.Admin;

import cn.wangdian.Model.Admin;
import cn.wangdian.Model.AdminLogin;
import cn.wangdian.Model.ShopKeeper;
import cn.wangdian.Service.AdminLoginService;
import cn.wangdian.Service.AdminService;
import cn.wangdian.Service.ShopKeeperService;
import cn.wangdian.utils.encryption.PBKDF2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Calendar;

/**
 * 管理后台登录处理
 */
@Controller
@RequestMapping("/admin")
public class IndexController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminLoginService adminLoginService;
    @Autowired
    private ShopKeeperService shopKeeperService;

    private Log logger= LogFactory.getLog(IndexController.class);

    @RequestMapping("")
    public String toIndex(){
        return "redirect:/admin/index";
    }

    /**
     * 店主使用手机号码登录, 管理员通过用户名登录
     * @param username 店主登录 ==> 手机号码  管理员登录 ==> 用户名
     * @param password 密码
     * @param authCode 验证码
     * @param peopleType 登录类型
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam(value = "username")String username,
                        @RequestParam(value = "password")String password,
                        @RequestParam(value = "authCode")String authCode,
                        @RequestParam(value = "peopleType")String peopleType,
                        HttpSession session, Model model){
        try {
            // 用户登录的验证码是添加到 session 中
            String validateCode=(String) session.getAttribute("validateCode");

            String loginPassword="";
            Integer status=0;
            Integer isDel = 0;

            ShopKeeper shopKeeper=new ShopKeeper();
            Admin admin=new Admin();

            if (peopleType.equals("shopKeeper")){

//                shopKeeper=shopKeeperService.selectByUsername(username);
                //用户使用手机号码登录
                shopKeeper = shopKeeperService.findByTelephone(username);
                //判断是否有该手机号码
                if (shopKeeper!=null){
                    loginPassword=shopKeeper.getPassword();
                    status=shopKeeper.getStatus();
                    isDel = shopKeeper.getIsDel();
                }else {
                    model.addAttribute("message","该用户名不存在");
                    return "backPage/fail";
                }

            }else if (peopleType.equals("admin")){
                admin=adminService.selectByUsername(username);
                if (admin!=null){
                    loginPassword=admin.getPassword();
                    status=admin.getStatus();
                    isDel = admin.getIsDel();
                } else {
                    model.addAttribute("message","该用户名不存在");
                    return "backPage/fail";
                }

            }
//            将用户输入的密码进行 PBKDF2 加密
            PBKDF2 pbkdf2 = new PBKDF2();

            if (!pbkdf2.validate(password, loginPassword)){
                model.addAttribute("message","用户名或密码错误");
                return "backPage/fail";
            }else if (!authCode.equals(validateCode)){
                model.addAttribute("message","验证码错误");
                return "backPage/fail";
            }else if (status==1){
                // status == 1 账号处于锁定状态   店主的没有提交年费，就会处于该状态
                model.addAttribute("message","该用户已被锁定，请联系管理员解锁");
                return "backPage/fail";
            } else if(isDel == 1){
                model.addAttribute("message","该用户已被删除，请联系管理员解锁");
                return "backPage/fail";
            } else {
                // 无论是 shopKeeper 还是 admin 保存在 session 中的都的属性名都是 user
                if (peopleType.equals("shopKeeper")){
                    //店主
                    session.setMaxInactiveInterval(30*60);  // 设置登录有效时长最大为 30min
                    session.setAttribute("peopleType","shopKeeper");
                    session.setAttribute("user",shopKeeper);
                }else if (peopleType.equals("admin")){
                    //管理员
                    //保存上一次的登录时间以及登录次数
                    admin.setLoginTime(adminLoginService.findLastLoginTimeByAdminId(admin.getId()));
                    admin.setLoginCount(adminLoginService.findCountByAdminId(admin.getId())+1);
                    adminService.update(admin);
//                //保存这次登录时间
                    Calendar calendar=Calendar.getInstance();
                    AdminLogin adminLogin=new AdminLogin(calendar.getTime(),admin.getId());
                    adminLoginService.save(adminLogin);
                    session.setMaxInactiveInterval(30*60);
                    session.setAttribute("peopleType","admin");
                    session.setAttribute("user",admin);
                }
                //设置登录的类型 shopKeeper==>店主 admin==>管理员
                session.setAttribute("peopleType", peopleType);
                session.setAttribute("login","success");
                return "redirect:/admin/index";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpSession session){
        // 用户退出，移除 session 中有关登录的信息
        session.removeAttribute("login");
        session.removeAttribute("user");
        session.removeAttribute("peopleType");
        return "backPage/login";
    }

    @RequestMapping("/validatecode")
    public String validatecode(){
        return "backPage/validatecode";
    }

    @RequestMapping("/index")
    public String index(HttpSession session, Model model){
        /*
        用户的登录信息会被加入到 Session 中
        login 属性判断用户是否成功登录
        peopleType 用户登录的身份 ==> 有可能是店主 或者 管理员
         */
        logger.info("主页");
//        暂时注释
        try {
            String login=(String) session.getAttribute("login");
            if (login.equals("success")){
                //登录成功
                String peopleType=(String) session.getAttribute("peopleType");  //获得登录用户类型
                System.out.println(peopleType+"--==-=-=-=-");

                if (peopleType.equals("shopKeeper")){
                    //店主
                    ShopKeeper shopKeeper=(ShopKeeper)session.getAttribute("user");
                    model.addAttribute("user",shopKeeper);
                }else if (peopleType.equals("admin")){
                    //管理员
                    Admin admin=(Admin)session.getAttribute("user");
                    model.addAttribute("user",admin);
                }
                model.addAttribute("peopleType",peopleType);
                return "backPage/backIndex";
            }else {
//                如果用户尚未登录，就要求用户重新登录
                session.removeAttribute("login");
                return "backPage/login";
            }
        } catch (Exception e) {
            //session login不存在，报错
            return "backPage/login";
        }
//        return "backPage/backIndex";
    }
}
