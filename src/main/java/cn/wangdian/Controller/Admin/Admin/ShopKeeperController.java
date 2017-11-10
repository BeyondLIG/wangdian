package cn.wangdian.Controller.Admin.Admin;

import cn.wangdian.Model.ShopKeeper;
import cn.wangdian.Model.User;
import cn.wangdian.Service.OrdersService;
import cn.wangdian.Service.ShopKeeperService;
import cn.wangdian.Service.UserService;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 25065 on 2016/9/11.
 */
@Controller
@RequestMapping("/admin")
public class ShopKeeperController {

    private ExecuteResult executeResult=new ExecuteResult();

    private Log logger= LogFactory.getLog(ShopKeeperController.class);

    @Autowired
    private ShopKeeperService shopKeeperService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private UserService userService;

    private static int parameterCountBefore=0;

    @RequestMapping(" /shopKeeper/list")
    public String shopKeeperList(Model model,
                            String orderField,String orderDirection,Integer pageSize,Integer pageCurrent,
                            String username,String nickname,Integer status,Integer level){
        Page<ShopKeeper> shopKeeperList= null;
        List<String> shopKeeperNameList=new ArrayList<>();
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

            //pageSize
            if (pageSize==null||pageSize.equals("")){
                pageSize=5;
            }
            //有多少页
            int count=shopKeeperService.countAllByIsDel0(username,nickname,status);
            int pageNumbers=0;
            if (count%pageSize==0){
                //整除
                pageNumbers=count/pageSize;
            }else {
                //有余数
                pageNumbers=count/pageSize+1;
            }

            //起始页码
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

            //pageRequest 页面请求
            PageRequest pageRequest=new PageRequest(pageCurrent,pageSize);
//            shopKeeperList = shopKeeperService.findAllByIsDel0(username,nickname,status,orderField,orderDirection,pageRequest);
            //查看不同等级的店主
            shopKeeperList=shopKeeperService.findAllByIsDel0AndLevel(level,status,orderField,orderDirection,pageRequest);


            for(ShopKeeper shopKeeper:shopKeeperList){
                String shopKeeperName=shopKeeperService.findShopKeeperNameByBelong(shopKeeper.getBelong());
                shopKeeperNameList.add(shopKeeperName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //计算每个店主的盈利额
//        if (shopKeeperList!=null){
//            for (ShopKeeper shopKeeper:shopKeeperList){
//                float allProfit=0;
//
//                List<Float> profitList=ordersService.findAllByShopKeeperAndStatus3(shopKeeper.getUsername());
//                for (Float profit:profitList){
//                    allProfit+=profit;
//                }
//                shopKeeper.setAllProfit(allProfit);
//                shopKeeperService.updateAllProfitById(allProfit,shopKeeper.getId());
//            }
//        }
        model.addAttribute("shopKeeperList",shopKeeperList);//店主
        model.addAttribute("level",level);
        model.addAttribute("shopKeeperNameList",shopKeeperNameList);//店主对应的推荐人
        return "backPage/admin/shopkeeper/shopKeeperList";
    }

    @RequestMapping(value = "/shopKeeper/add",method = RequestMethod.GET)
    public String shopKeeperAdd(Integer id,String type,Model model){
        if (id!=null){
            ShopKeeper shopKeeper=null;

            try {
                shopKeeper=shopKeeperService.findById(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            model.addAttribute("shopKeeper",shopKeeper);
        }

        if (type!=null&&!type.equals("")&&type.equals("shopKeeper")){
            model.addAttribute("type","shopKeeper");
        }
        return "backPage/admin/shopkeeper/add";
    }

    /**
     * 现在管理员不再拥有修改店主和用户的信息的权限
     */
    @ResponseBody
    @RequestMapping(value = "/shopKeeper/add",method = RequestMethod.POST)
    public Object shopKeeperAdd(ShopKeeper shopKeeper, String type, Model model, HttpSession session){
        try {
            shopKeeper.setPassword(new PBKDF2().encrypt(shopKeeper.getPassword()));
//            shopKeeper.setWebUrl(shopKeeper.getUsername());
            if (shopKeeper.getId()!=null){
                //编辑
                String usernameFromDatebase=shopKeeperService.findUsernameById(shopKeeper.getId());
                //编辑过程中所填的用户名与本来数据库的用户名不一样
                if (!usernameFromDatebase.equals(shopKeeper.getUsername())){
                    String name=shopKeeperService.checkUsername(shopKeeper.getUsername());
                    if (name!=null){
                        return executeResult.jsonReturn(300,"该用户名已被注册",false);
                    }
                }
                shopKeeperService.update(shopKeeper);
                //TODO 需要找出相应的 user 取出来，然后再更新一遍
                session.setMaxInactiveInterval(30*60);
                session.setAttribute("user",shopKeeper);
//                ShopKeeper shopKeeper1=(ShopKeeper)session.getAttribute("user");
//                System.out.println(shopKeeper1.getNickname()+"昵称");

            }else {
                //添加
                String name=shopKeeperService.checkUsername(shopKeeper.getUsername());
                if (name!=null){
                    return executeResult.jsonReturn(300,"该用户名已被注册",false);
                }else {
                    shopKeeperService.save(shopKeeper);
                }
            }

            if (type!=null&&!type.equals("")&&type.equals("shopKeeper")){
                return executeResult.jsonReturnForHead(200);
            }else {
                return executeResult.jsonReturn(200);
            }

        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage());
        }
//        return "backPage/Common/ajaxDone";
    }


    /**
     * 店主用户名检测是否存在
     * @return
     */
    @ResponseBody
    @RequestMapping("/shopKeeper/checkUsername")
    public Object checkUsername(String username){
        try {
            //Shopkeeper 信息也保存在 User 数据库表中
            //所以只需要保证在用户表中唯一
            String name = userService.checkUsername(username);
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
     * 店主锁定
     * @return
     */
    @ResponseBody
    @RequestMapping("/shopKeeper/suoDing")
    public Object suoDing(Integer id){
        try {
            //同时更新店主和用户信息的状态
            shopKeeperService.suoDingById(id);
            userService.suoDingByUsername(shopKeeperService.findUsernameById(id));
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
    @RequestMapping("/shopKeeper/jieDing")
    public Object jieDing(Integer id){
        try {
            //同时更新店主和用户信息的状态
            shopKeeperService.jieDingById(id);
            userService.jieSuoByUsername(shopKeeperService.findUsernameById(id));
            return executeResult.jsonReturn(200,false);
        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage(),false);
        }
    }


    /**
     * 店主删除
     * @param id 需要被删除的店主的 id
     */
    @ResponseBody
    @RequestMapping("/shopKeeper/delete")
    public Object delete(Integer id){
        try {
            //同时从 ShopKeeper 和 User 删除
            ShopKeeper shopKeeper = shopKeeperService.findById(id);
            //通过手机号码，找到相应的店主
            User user = userService.findByTelephone(shopKeeper.getTelephone());
            //释放当前店主的用户名和手机号码，以便后来者可以重复使用
            //转移相应店主和vip用户到 id 的上一级店主
            shopKeeperService.deleteShopKeeperById(id);
            //置空店主的选项
            user.setUsername(null);
            user.setTelephone(null);
            user.setShopkeeper(null);
            user.setStatus(1);
            user.setIsDel(1);
            userService.update(user);
            //从数据库 user 表中删除店主的数据
            userService.realDeleteById(user.getId());
            //置空店主的用户名和手机号码信息
            shopKeeper.setUsername(null);
            shopKeeper.setTelephone(null);
            shopKeeper.setBelong(0);
            shopKeeper.setIsNew(1);
            shopKeeper.setStatus(1);
            shopKeeperService.update(shopKeeper);
            //从数据库 shopkeeper 表中删除店主的信息
            shopKeeperService.realDeleteById(shopKeeper.getId());
//            shopKeeperService.deleteByPrimaryKey(id);
//            userService.deleteByPrimaryKey(id);
//            userService.deleteByUsername(shopKeeperService.findUsernameById(id));
            return executeResult.jsonReturn(200,false);
        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage(),false);
        }
    }
}
