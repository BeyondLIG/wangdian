package cn.wangdian.Controller.Admin.Admin;

import cn.wangdian.Model.Admin;
import cn.wangdian.Service.AdminService;
import cn.wangdian.utils.ExecuteResult;
import cn.wangdian.utils.encryption.PBKDF2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 25065 on 2016/9/15.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private Log logger= LogFactory.getLog(AdminController.class);

    private ExecuteResult executeResult=new ExecuteResult();

    @Autowired
    private AdminService adminService;

    private static int parameterCountBefore=0;

    @RequestMapping("/admin/list")
    public String adminList(Model model,
                            String orderField,String orderDirection,Integer pageSize,Integer pageCurrent,
                            String username,String nickname,Integer status){
        Page<Admin> adminList= null;
        try {

            int parameterCountNow=0;
            if (username!=null&&!username.equals("")){
                model.addAttribute("username",username);
                parameterCountNow++;
            }
            if (nickname!=null&&!nickname.equals("")){
                model.addAttribute("nickname",nickname);
                parameterCountNow++;
            }
            if (status!=null&&!status.equals("")){
                model.addAttribute("status",status);
                parameterCountNow++;
            }


            if (pageSize==null||pageSize.equals("")){
                pageSize=5;
            }

            //有多少页
            int count=adminService.countAllByIsDel0(username,nickname,status);
            int pageNumbers=0;
            if (count%pageSize==0){
                //整除
                pageNumbers=count/pageSize;
            }else {
                //有余数
                pageNumbers=count/pageSize+1;
            }

            if (pageCurrent==null||pageCurrent.equals("")){
                pageCurrent=0;
            }else if(parameterCountNow!=parameterCountBefore){
                pageCurrent=0;
                parameterCountBefore=parameterCountNow;
            }else if (pageCurrent>pageNumbers){
                pageCurrent=0;
            }else {
                pageCurrent=pageCurrent-1;
            }


            PageRequest pageRequest=new PageRequest(pageCurrent,pageSize);
            adminList = adminService.findAllByIsDel0(username,nickname,status,orderField,orderDirection,pageRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("adminList",adminList);
        return "backPage/admin/admin/adminList";
     }

    @RequestMapping(value = "/admin/add",method = RequestMethod.GET)
    public String adminAdd(Integer id,String type,String update,Model model,HttpSession session){
        if (id!=null){
            Admin admin=null;

            try {
                admin=adminService.findById(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            model.addAttribute("admin",admin);
        }
        if (type!=null&&!type.equals("")&&type.equals("admin")){
            System.out.println(type+"-=-=-=-=-------------");
            model.addAttribute("type","admin");
        }
        if (update!=null&&!update.equals("")){
            model.addAttribute("update",update);
        }

//        Admin admin123=(Admin)session.getAttribute("user");
//        if (admin123!=null){
//            if (admin123.getId()==id){
//                model.addAttribute("update","head");
//            }
//        }
        return "backPage/admin/admin/add";
    }

    /**
     * 添加超级管理员
     */
    @ResponseBody
    @RequestMapping(value = "/admin/add",method = RequestMethod.POST)
    public Object adminAdd(Admin admin, String loginInTime, String type, Model model, HttpSession session){
        try {
                admin.setPassword(new PBKDF2().encrypt(admin.getPassword()));
            if (loginInTime!=null&&!loginInTime.equals("")){
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date=sdf.parse(loginInTime);
                admin.setLoginTime(date);
            }

            if (admin.getId()!=null){
                //编辑
                String usernameFromDatebase=adminService.findUsernameById(admin.getId());
                //编辑过程中所填的用户名与本来数据库的用户名不一样
                if (!usernameFromDatebase.equals(admin.getUsername())){
                    String name=adminService.checkUsername(admin.getUsername());
                    if (name!=null){
                        return executeResult.jsonReturn(300,"该用户名已被注册",false);
                    }
                }
                PBKDF2 pbkdf2 = new PBKDF2();
                //重新加密管理员所设置的密码
                admin.setPassword(pbkdf2.encrypt(admin.getPassword()));
                adminService.update(admin);

                Admin admin123=(Admin)session.getAttribute("user");
                if (admin123.getId()==admin.getId()){
                    session.setMaxInactiveInterval(30*60);
                    session.setAttribute("user",admin);
                }

            }else {
                //添加
                String name=adminService.checkUsername(admin.getUsername());
                if (name!=null){
                    return executeResult.jsonReturn(300,"该用户名已被注册",false);
                }else {
                    PBKDF2 pbkdf2 = new PBKDF2();
                    //重新设置管理员密码
                    admin.setPassword(pbkdf2.encrypt(admin.getPassword()));
                    adminService.save(admin);
                }
            }
            if (type!=null&&!type.equals("")&&type.equals("admin")){
                return executeResult.jsonReturnForHead(200);
            }else {
                return executeResult.jsonReturn(200);
            }
        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage());
        }
    }

    /**
     * 管理员用户名检测是否存在
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/checkUsername")
    public Object checkUsername(String username){
        try {
            String name=adminService.checkUsername(username);
            if (name==null){
                return executeResult.jsonReturn(200,"该用户名可以使用",false);
            }else {
                return executeResult.jsonReturn(300,"该用户名已被注册",false);
            }
        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage(),false);
        }
    }

    /**
     * 管理员锁定
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/suoDing")
    public Object suoDing(Integer id){
        try {
            adminService.suoDingById(id);
            return executeResult.jsonReturn(200,false);
        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage(),false);
        }
    }

    /**
     * 管理员解锁
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/jieDing")
    public Object jieDing(Integer id){
        try {
            adminService.jieDingById(id);
            return executeResult.jsonReturn(200,false);
        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage(),false);
        }
    }


    /**
     * 管理员删除
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/delete")
    public Object delete(Integer id){
        try {
            adminService.deleteByPrimaryKey(id);
            return executeResult.jsonReturn(200,false);
        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage(),false);
        }
    }
}
