package cn.wangdian.Controller.Admin.Admin;

import cn.wangdian.Model.ShopKeeper;
import cn.wangdian.Model.User;
import cn.wangdian.Model.UserAddress;
import cn.wangdian.Service.ShopKeeperService;
import cn.wangdian.Service.UserAddressService;
import cn.wangdian.Service.UserService;
import cn.wangdian.utils.ExecuteResult;
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
import sun.misc.BASE64Encoder;

import java.util.List;

/**
 * Created by 25065 on 2016/9/11.
 */
@Controller
@RequestMapping("/admin")
public class UserController {

    private ExecuteResult executeResult=new ExecuteResult();

    private Log logger= LogFactory.getLog(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private ShopKeeperService shopKeeperService;

    private static int parameterCountBefore=0;

    @RequestMapping("/user/list")
    public String userList(Model model,
                            String orderField,String orderDirection,Integer pageSize,Integer pageCurrent,
                            String username,String nickname,Integer status,Integer vip){
        Page<User> userList= null;
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
            int count=userService.countAllByIsDel0(username,nickname,status);
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
            }else if (parameterCountNow!=parameterCountBefore){
                pageCurrent=0;
                parameterCountBefore=parameterCountNow;
            }else if (pageCurrent>pageNumbers){
                pageCurrent=0;
            }else {
                pageCurrent=pageCurrent-1;
            }
            PageRequest pageRequest=new PageRequest(pageCurrent,pageSize);
            userList = userService.findAllByIsDel0AndVip(vip,status,orderField,orderDirection,pageRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("userList",userList);
        model.addAttribute("vip",vip);
        return "backPage/admin/user/userList";
    }

    @RequestMapping(value = "/user/add",method = RequestMethod.GET)
    public String userAdd(Integer id,Model model){
        if (id!=null){
            User user=null;
            List<UserAddress> userAddressList= null;
            try {
                user=userService.findById(id);
                userAddressList = userAddressService.findAllByIsDel0(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            model.addAttribute("user",user);
            model.addAttribute("userAddressList",userAddressList);
        }
        return "backPage/admin/user/add";
    }

    /**
     * 管理员已经不再拥有更改用户信息的权限
     */
    @ResponseBody
    @RequestMapping(value = "/user/add",method = RequestMethod.POST)
    public Object userAdd(User user, Model model){
        try {
            //base64加密
            user.setPassword(new BASE64Encoder().encodeBuffer(user.getPassword().getBytes("utf-8")));
            if (user.getId()!=null){
                //编辑
//                String usernameFromDatebase=userService.findUsernameById(user.getId());
//                //编辑过程中所填的用户名与本来数据库的用户名不一样
//                if (!usernameFromDatebase.equals(user.getUsername())){
//                    String name=userService.checkUsername(user.getUsername());
//                    if (name!=null){
//                        return executeResult.jsonReturn(300,"该用户名已被注册",false);
//                    }
//                }
                userService.update(user);
            }else {
                //添加
//                String name=userService.checkUsername(user.getUsername());
//                if (name!=null){
//                    return executeResult.jsonReturn(300,"该用户名已被注册",false);
//                }else {
//                    userService.save(user);
//                }
                userService.save(user);
            }
            return executeResult.jsonReturn(200);
        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage());
        }
    }


    /**
     * 普通用户名检测是否存在
     * @return
     */
    @ResponseBody
    @RequestMapping("/user/checkUsername")
    public Object checkUsername(String username){
        try {
            String name=userService.checkUsername(username);
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
     * 普通用户锁定
     * @return
     */
    @ResponseBody
    @RequestMapping("/user/suoDing")
    public Object suoDing(Integer id){
        try {
            userService.suoDingById(id);
            return executeResult.jsonReturn(200,false);
        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage(),false);
        }
    }

    /**
     * 店主解锁
     * @return
     */
    @ResponseBody
    @RequestMapping("/user/jieDing")
    public Object jieDing(Integer id){
        try {
            userService.jieDingById(id);
            return executeResult.jsonReturn(200,false);
        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage(),false);
        }
    }


    /**
     * 普通用户删除
     * @param id 需要被删除的用户的 id
     */
    @ResponseBody
    @RequestMapping("/user/delete")
    public Object delete(Integer id){
        try {
            User user = userService.findById(id);
            //得到 vip 对应的店主信息
            ShopKeeper shopKeeper = user.getShopkeeper();
            //因为每一个用户都是有推荐人的，所以不需要判断 shopkeeper 是否是 null
            shopKeeperService.decreaseDirectUserNumberAndAllUserNumber(shopKeeper.getId());
            //置空店主 shopkeeper_id，防止级联把店主和店主相应的用户的信息全都删除了
            user.setShopkeeper(null);
            user.setTelephone(null);
            user.setUsername(null);
            user.setIsDel(1);
            user.setStatus(1);
            userService.update(user);
            //从数据库中真是删除用户的信息
            userService.realDeleteById(id);
            return executeResult.jsonReturn(200,false);
        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage(),false);
        }
    }
}
