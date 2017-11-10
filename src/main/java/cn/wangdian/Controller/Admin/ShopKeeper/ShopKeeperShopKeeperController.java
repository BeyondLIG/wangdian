package cn.wangdian.Controller.Admin.ShopKeeper;

import cn.wangdian.Model.ShopKeeper;
import cn.wangdian.Model.User;
import cn.wangdian.Service.ShopKeeperService;
import cn.wangdian.Service.UserService;
import cn.wangdian.utils.AddAttributeToModel;
import cn.wangdian.utils.ExecuteResult;
import org.apache.commons.fileupload.util.LimitedInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 店主查看自己所有店主的功能
 */
@Controller
@RequestMapping("/admin/shopKeeper")
public class ShopKeeperShopKeeperController {
    //用来返回 json 结果
    private ExecuteResult executeResult = new ExecuteResult();

    @Autowired
    private ShopKeeperService shopKeeperService;

    @Autowired
    private UserService userService;

    //用来添加参数到 Model 中
    private AddAttributeToModel addAttributeToModel = new AddAttributeToModel();

    private static int shopkeeper_parameterCountBefore=0;
    private static int user_parameterCountBefore = 0;
    /**
     * 店主查看自己手下的店主
     */
    @RequestMapping(value = "/shopkeeperList")
    public String shopKeepersList(Model model,
                                  String orderField, String orderDirection, Integer pageSize, Integer pageCurrent,
                                  String username, String nickname, Integer status,Integer belong) {
        Page<ShopKeeper> shopKeeperList = null;
        try {
            int parameterCountNow = 0;
            if (username != null && !username.equals("")) {
                model.addAttribute("username", username);
                parameterCountNow++;
            }
            if (nickname != null && !nickname.equals("")) {
                model.addAttribute("nickname", nickname);
                parameterCountNow++;
            }
            if (status != null && !status.equals("")) {
                model.addAttribute("status", status);
                parameterCountNow++;
            }


            if (pageSize == null || pageSize.equals("")) {
                pageSize = 5;
            }
            //有多少页
            int count = shopKeeperService.countAllByIsDel0(username, nickname, status);
            int pageNumbers = 0;
            if (count % pageSize == 0) {
                //整除
                pageNumbers = count / pageSize;
            } else {
                //有余数
                pageNumbers = count / pageSize + 1;
            }

            if (pageCurrent == null || pageCurrent.equals("")) {
                pageCurrent = 0;
            } else if (parameterCountNow != shopkeeper_parameterCountBefore) {
                pageCurrent = 0;
                shopkeeper_parameterCountBefore = parameterCountNow;
            } else if (pageCurrent > pageNumbers) {
                pageCurrent = 0;
            } else {
                pageCurrent = pageCurrent - 1;
            }
            PageRequest pageRequest = new PageRequest(pageCurrent, pageSize);
            //把对应的页数的数据查询出来
            //TODO 未完成显示店主 等待前端传递店主的id号调用shopKeeperService.findAllByIsDel0AndBelong查看店主的直接下属店主
            //需要传递店主的id号
            shopKeeperList= shopKeeperService.findAllByIsDel0AndBelong(belong,status,orderField,orderDirection,pageRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("shopKeeperList",shopKeeperList);
        return "backPage/shopKeeper/shopkeeper/shopKeeperList";
    }

    @RequestMapping(value = "/userList")
    public String userList(Model model, String orderField, String orderDirection,
                           Integer pageSize, Integer pageCurrent, String username, String nickname, Integer status,Integer shopKeeperId) {
        Page<User> userList = null;
        try {
            int parameterCountNow = 0;
            if (username != null && !username.equals("")) {
                model.addAttribute("username", username);
                parameterCountNow++;
            }
            if (nickname != null && !nickname.equals("")) {
                model.addAttribute("nickname", nickname);
                parameterCountNow++;
            }
            if (status != null && !status.equals("")) {
                model.addAttribute("status", status);
                parameterCountNow++;
            }


            if (pageSize == null || pageSize.equals("")) {
                pageSize = 5;
            }
            //有多少页
            int count = userService.countAllByIsDel0(username, nickname, status);
            int pageNumbers = 0;
            if (count % pageSize == 0) {
                //整除
                pageNumbers = count / pageSize;
            } else {
                //有余数
                pageNumbers = count / pageSize + 1;
            }

            if (pageCurrent == null || pageCurrent.equals("")) {
                pageCurrent = 0;
            } else if (parameterCountNow != user_parameterCountBefore) {
                pageCurrent = 0;
                user_parameterCountBefore = parameterCountNow;
            } else if (pageCurrent > pageNumbers) {
                pageCurrent = 0;
            } else {
                pageCurrent = pageCurrent - 1;
            }

            PageRequest pageRequest = new PageRequest(pageCurrent, pageSize);
            //把对应的页数的数据查询出来
            //TODO 未完成用户显示  等待前端传递店主的id号再调用userService.findAllUserByShopKeeperId查看店主的用户
            userList = userService.findAllUserByShopKeeperId(shopKeeperId, status,orderField, orderDirection, pageRequest);
        }catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("userList",userList);
        return "backPage/shopKeeper/user/userList";
    }
}
