package cn.wangdian.Controller.Home;

import cn.wangdian.Model.Shop;
import cn.wangdian.Model.YunFei;
import cn.wangdian.Service.*;
import cn.wangdian.Model.ShopKeeper;
import cn.wangdian.Model.User;
import cn.wangdian.Service.BenefitService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 测试分润
 * 年费分润 + 购买商品分润
 */
@Controller
public class TestBenefit {
//    @Autowired
//    private YunFeiService yunFeiService;
//    @Autowired
//    private BenefitService benefitService;
//
//    @RequestMapping(value = "/test/shareGoodsProfit", method = RequestMethod.GET)
//    @ResponseBody //1,3，1
//    public String shareGoodsProfit(Integer shopId, Integer userId, Integer count) {
//        benefitService.shareGoodsBenefit(shopId, userId, count);
//        return "ok";
//    }
//
//    @RequestMapping("/test/shareAnnualFeeProfit")
//    @ResponseBody
//    public String shareAnnualFeeProfit(int newShopKeeperId) {
//        benefitService.shareAnnualFeeBenefit(newShopKeeperId);
//        return "ok";
//    }

    //已经测试，调用shopKeeperService.findAllByIsDel0AndLevel可以查看不同等级的店主
//    @Autowired
//    private ShopKeeperService shopKeeperService;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private ShopService shopService;

//    @RequestMapping(value = "/test/shareGoodsProfit", method = RequestMethod.GET)
//    @ResponseBody //1,3，1
//    public String shareGoodsProfit(Integer shopId, Integer userId, Integer count) {
//        return benefitService.shareGoodsBenefit(shopId, userId, count);
//    }

//    @ResponseBody
//    @RequestMapping(value = "/findAllByIsDel0AndLevel")
//    public String findAllByIsDel0AndLevel(){
//        PageRequest pageRequest=new PageRequest(0,5);
//        try {
//            Page<ShopKeeper> shopKeeperPage=shopKeeperService.findAllByIsDel0AndLevel(1,"1","",pageRequest);
//            for (ShopKeeper shopKeeper:shopKeeperPage){
//                System.out.println(shopKeeper.getIsDel());
//                System.out.println(shopKeeper.getUsername());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "ok";
//    }

    //已经过测试，可以调用userService.findAllByIsDel0AndVip查看不同等级的用户
//    @ResponseBody
//    @RequestMapping(value = "/findAllByIsDel0AndVip")
//    public String findAllByIsDel0AndVip(){
//        PageRequest pageRequest=new PageRequest(0,5);
//        Page<User> userPage= null;
//        try {
//            userPage = userService.findAllByIsDel0AndVip(0,"1","",pageRequest);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        for (User user:userPage){
//            System.out.println(user.getIsDel());
//            System.out.println(user.getUsername());
//        }
//
//        return "ok";
//    }

//    @RequestMapping("/test/allShop")
//    @ResponseBody
//    public List<Shop> testAllShop() {
//        return shopService.findAllByIsDel0();
//    }

    //已经过测试，可以调用shopKeeperService.findAllByIsDel0AndBelong查看店主的直接下属店主
//    @ResponseBody
//    @RequestMapping(value = "/findAllByIsDel0AndBelong")
//    public String findAllByIsDel0AndBelong(){
//        PageRequest pageRequest=new PageRequest(0,5);
//        try {
//            Page<ShopKeeper> shopKeeperPage=shopKeeperService.findAllByIsDel0AndBelong(6,"1","",pageRequest);
//            for (ShopKeeper shopKeeper:shopKeeperPage){
//                System.out.println(shopKeeper.getUsername());
//                System.out.println(shopKeeper.getBelong());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "ok";
//    }


    //已经过测试，可以调用shopKeeperService.findById(id).getUserList()查看店主的用户
//    @RequestMapping(value = "/findAllByIsDel0AndShopkeeperId")
//    @ResponseBody
//    public String findAllByIsDel0AndShopkeeperId(){
//        try {
//            ShopKeeper shopKeeper=shopKeeperService.findById(11);
//            List<User> userList=shopKeeper.getUserList();
//            if (userList==null){
//                System.out.println("----------------");
//            }else {
//
//                for (User user:userList){
//                    System.out.println(user.getUsername());
//                    System.out.println(user.getIsDel());
//                }
//                System.out.println("========================");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "ok";
//    }


    //已经过测试，可以调用shopKeeperService.deleteShopKeeperById(id)删除店主的下属店主
//    @ResponseBody
//    @RequestMapping(value = "/deleteShopKeeperById")
//    public String deleteShopKeeperById(){
//        shopKeeperService.deleteShopKeeperById(5);
//
//        return "delete";
//    }

//        @RequestMapping("/testIntegerNull")
//    public void testIntegerNull() {
//        YunFei yunFei=yunFeiService.onlyOne();
//    }

//    @Autowired
//    private BenefitService benefitService;
//
//    @ResponseBody
//    @RequestMapping("/test/goodsShareBenefitTrack")
//    public int shareGoodsBenefit(Integer shopId, Integer userId, Integer count, Integer shopCartId) {
//        return benefitService.shareGoodsBenefit(shopId, userId, count, shopCartId);
//    }
}
