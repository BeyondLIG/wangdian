package cn.wangdian.Controller.Home;

import cn.wangdian.Model.*;
import cn.wangdian.Service.*;
import cn.wangdian.utils.StringUtil;
import cn.wangdian.utils.encryption.PBKDF2;
import com.alibaba.druid.pool.DruidDataSource;
import net.sf.json.JSONArray;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * 用户个人中心控制器
 * 用于设置、查看相应用户信息的控制器
 * TODO 后期需要加一个 filter
 */
@Controller
@RequestMapping("/user")
public class UserCenterController {
    @Autowired
    private UserService userService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private YunFeiService yunFeiService;

    @Autowired
    private ShopCartService shopCartService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private BenefitService benefitService;

    @Autowired
    private ShunShopService shunShopService;

    @Autowired
    private ShopKeeperService shopKeeperService;

    @Autowired
    private DruidDataSource dataSource;

    private Log logger = LogFactory.getLog(UserCenterController.class);

    /*
    ====================== 更新用户个人信息 start ==========================
     */

    /**
     * 普通用户 vip用户 店主使用同样的修改密码逻辑
     * 提供手机号码 ==>　验证码通过验证后再更改
     *
     * @param userId 需要修改密码的用户 id
     */
    @RequestMapping("/changePassword")
    public String changePassword(Integer userId, Model model) {
        model.addAttribute("userId", userId);
        return "frontPage/pages/change-password";
    }

    /**
     *
     * @param oldPassword 旧密码 ==> 应该对应数据库中的密码
     * @param newPassword 新密码
     */
    @ResponseBody
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public Map<String, String> changePassword(Integer userId,@RequestParam("oldPassword") String oldPassword,
                                              @RequestParam("newPassword") String newPassword,
                                              HttpSession session) {
        User user = (User) session.getAttribute("ordinaryUser");
        userId = user.getId();
        Map<String, String> map = new HashMap<>();
        PBKDF2 pbkdf2 = new PBKDF2();
        try {
            // 从数据库加载用户旧密码，判断用户输入的旧密码是否是正确的
            String passwordFromData = userService.selectPasswordById(userId).trim();

            if (pbkdf2.validate(oldPassword, passwordFromData)) {
                //输入的旧密码与数据库的旧密码相符
                //可以修改密码
                String newPasswordEncrypt = pbkdf2.encrypt(newPassword);
                userService.updatePasswordById(newPasswordEncrypt, userId);
                //判断用户是否是店主
                if (user.getVip() == 2) {
                    //判断当前用户是否是店主，如果是店主，还需要去更新店主的密码信息
                    //因为店主在 wd_shopkeeper 和 wd_user 表中的 id，不一定一致，所以这里不能够使用 id 来更新店主的 password
                    //这里选择的方案是通过用户的手机号码来实现
                    shopKeeperService.updatePasswordByTelephone(newPasswordEncrypt, user.getTelephone());
                }
                map.put("message", "修改成功");
            } else {
                //不符
                map.put("message", "原始密码错误，请重新填写！");
            }
        } catch (Exception e) {
            map.put("message", "修改失败！请稍后再试。");
        }
        return map;
    }

//    @ResponseBody
//    @RequestMapping("/updateEmail")
//    public Map<String, String> updateEmail(Integer userId, String email, HttpSession session) {
//        Map<String, String> map = new HashMap<>();
//        try {
//            if (email != null && !email.equals("")) {
//                // TODO: email 应该是唯一的
//                userService.updateEmailById(email, userId);
//                map.put("message", "修改成功！");
//                map.put("email", email);
//                User user = (User) session.getAttribute("ordinaryUser");
//                user.setEmail(email);
//                session.setAttribute("ordinaryUser", user);
//            } else {
//                map.put("message", "你所填邮箱为空！");
//            }
//        } catch (Exception e) {
//            map.put("message", "修改失败！请稍后再试。");
//        }
//        return map;
//    }

    @ResponseBody
    @RequestMapping("/updateTelephone")
    public Map<String, String> updateTelephone(Integer userId, @RequestParam("telephone") String telephone,
                                               @RequestParam("validCode") String validCode,
                                               HttpSession session) {
        //收取验证码的手机号码
        String validTelephone = (String) session.getAttribute("telephone");
        String smsValidCode = (String) session.getAttribute("smsValidCode");
        session.removeAttribute("smsValidCode");
        Map<String, String> map = new HashMap<>();
        try {
            if (telephone != null && !telephone.equals("")) {
                //检验手机号码为唯一性
                if (userService.checkTelePhone(telephone) != null) {
                    map.put("message", "该手机号码已注册，换一个试试");
                } else if(!telephone.equals(validTelephone)){
                    map.put("message", "请输入你接收验证码的手机号码");
                } else if(!validCode.equals(smsValidCode)) {
                    map.put("message", "验证码错误");
                }else {
                    User user = (User) session.getAttribute("ordinaryUser");
                    user.setTelephone(telephone);
                    userService.updateTelephoneById(telephone, user.getId());
                    if (user.getVip() != null && user.getVip() == 2) {
                        //该用户是店主，需要同时更新店主的密码信息
                        shopKeeperService.updateTelephoneByUsername(user.getUsername(), telephone);
                    }
                    session.setAttribute("ordinaryUser", user);
                    map.put("message", "修改成功！");
                    map.put("telephone", telephone);
                }
            } else {
                map.put("message", "你所填手机号为空！");
            }
        } catch (Exception e) {
            map.put("message", "修改失败！请稍后再试。");
        }
        return map;
    }

    /*
    =================== 更新用户个人信息 end =====================
     */

    /*
    =================== 用户收藏商品功能 start ===================
     */

//    /**
//     * 用户查看所有收藏商品的信息
//     */
//    @RequestMapping("/my-collection")
//    public String myCollection(HttpSession session, Model model) {
//        User user = (User) session.getAttribute("ordinaryUser");
//        model.addAttribute("vip", user.getVip());
//        if (user != null) {
//            //TODO 这会把用户所有的收藏商品一次查询出来，我个人觉得效率很低，应该换成每一次加载多少收藏商品之类的
//            //TODO 如果查询出来收藏栏是空的 list.size() == 0需要给予用户相应提示
////            List<Shop> shopList = user.getShopList();
//            //提取用户所有的收藏商品
////            model.addAttribute("shopList", shopList);
//            //TODO 常 需要写一个展示用户所有收藏商品的页面
//            return "frontPage/pages/my_collect";
//        }
//        return "redirect: /login";
//    }


//    @ResponseBody
//    public int addCollection(@RequestParam("shopId") Integer shopId,
//            HttpSession session) {
//        User user = (User) session.getAttribute("ordinaryUser");
//        Shop shop = shopService.findById(shopId);
//        if (shop != null) {
//            //如果商品存在就添加进去用户的收藏列表中
////            user.addShopList(shop);
//
//            //用户收藏商品
////            user.getShopList().add(shop);
////            user.getShopList().add(shop);
//            try {
//                userService.save(user);
//                //用户添加收藏成功
//                return 1;
//            } catch (Exception e) {
//                //因为 wd_shop_wd_user 数据库表中的 primary key(user_id, shop_id)
//                //如果重复添加会抛出错误，避免用户重复添加收藏
//                e.printStackTrace();
//                //用户添加收藏失败 ==> 很大原因是因为用户重复添加了收藏
//                return 0;
//            }
//        }
//        //正常情况下，不会返回 2
//        return 2;
//    }

//    /**
//     * 测试用，测试完成后需要删除
//     */
//    @RequestMapping("/test")
//    @ResponseBody
//    public int testPersist() {
//        User user = new User();
//        Shop shop = new Shop();
////        shop.setShopTypeName(new ShopType());
//        shop.setPastPrice(10.2f);
//        shop.setFirstCost(100f);
//        shop.setSecondCost(200f);
//        shop.setShopkeeperPrice(300f);
//        shop.setVipPrice(1000f);
//        shop.setShopTypeName("自行车");
//        user.getShopList().add(shop);
//        try {
//            userService.save(user);
//            return 1;
//        } catch (Exception e) {
//            return 0;
//        }
//    }
//    /**
//     * 测试用，测试完成后需要删除
//     */
//    @RequestMapping("/test")
//    @ResponseBody
//    public int testPersist() {
//        User user = new User();
//        Shop shop = new Shop();
////        shop.setShopTypeName(new ShopType());
//        shop.setPastPrice(10.2f);
//        shop.setFirstCost(100f);
//        shop.setSecondCost(200f);
//        shop.setShopkeeperPrice(300f);
//        shop.setVipPrice(1000f);
//        shop.setShopTypeName("自行车");
//        user.getShopList().add(shop);
//        try {
//            userService.save(user);
//            return 1;
//        } catch (Exception e) {
//            return 0;
//        }
//    }


    /*
    ================== 用户收藏商品功能 end ======================
     */


    @RequestMapping("/settings")
    public String settings(HttpSession session) {
        if (session.getAttribute("ordinaryUser") != null) {
            return "frontPage/pages/setting";
        }
        return "redirect: /login";
    }

//    /**
//     * 查询所有待收货商品 ==> 已付款未发货 +　已付款已发货
//     */
//    @RequestMapping("/receivingGoods")
//    public String receivingGoods(HttpSession session, Model model) {
//        //如果用户还没有登录，则重定向到登录页面
//        User user = (User) session.getAttribute("ordinaryUser");
//        if (user != null) {
//            //去查询数据库 Orders 订单的信息，查看所有属于
//            List<Orders> ordersList = ordersService.findAllByUserAndReceiving(user.getId());
//            model.addAttribute("ordersList", ordersList);
//            return "frontPage/pages/receiving-goods";
//        }
//        return "redirect: /login";
//    }

    @RequestMapping("/userInfo")
    public String userInfo(HttpSession session) {
        if (session.getAttribute("ordinaryUser") != null) {
            return "frontPage/pages/user-info";
        }
        return "redirect: /login";
    }

    /**
     * 用户中心页面
     * 加载这个页面的时候有点慢
     */
    @RequestMapping("/userCenter")
    public String userCenter(HttpSession session,
                             Model model){
        User user=(User)session.getAttribute("ordinaryUser");
        if (user!=null){
            model.addAttribute("ordinaryUser",user);
            return "frontPage/pages/user-center";
        }else {
            //用户还没登录，重定向到登录页面
            return "redirect:/login";
        }

    }

    /*
      ================ 用户管理收货地址 start ===============
     */
    @RequestMapping("/addressManagement")
    public String addressManagement(Integer userId,Model model){
        model.addAttribute("userId",userId);
        List<UserAddress> userAddressList= null;
        try {
            userAddressList = userAddressService.findAllByIsDel0(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("userAddressList",userAddressList);
        return "frontPage/pages/address-management";
    }

    @RequestMapping(value = "/addAddress",method = RequestMethod.GET)
    public String addAddress(Integer userId,Model model){
        model.addAttribute("userId",userId);
        return "frontPage/pages/add-address";
    }

    @ResponseBody
    @RequestMapping(value = "/addAddress",method = RequestMethod.POST)
    public Map<String,String> addAddress(UserAddress userAddress){
        Map<String,String> map= new HashMap<>();
        try {
            userAddressService.save(userAddress);
            map.put("message","操作成功");
        } catch (Exception e) {
            map.put("message","添加失败！请稍后再试。");
        }
        return map;
    }


    @RequestMapping(value = "/addAddressEdit",method = RequestMethod.GET)
    public String editAddress(Integer id,Model model){
        UserAddress userAddress=null;
        try {
            userAddress=userAddressService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("userAddress",userAddress);
        model.addAttribute("userId",userAddress.getUserId());
        return "frontPage/pages/add-address";
    }

    @RequestMapping(value = "/addAddressDelete",method = RequestMethod.GET)
    public String deleteAddress(Integer id){
        Integer userId= null;
        try {
            userAddressService.deleteByPrimaryKey(id);
            userId = userAddressService.findUserIdById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/user/addressManagement?userId="+userId;
    }
    /*
      ================ 用户管理收货地址 end ===============
     */

    /*
    ================= 用户管理订单信息 start ===============
     */
    /**
     * 通过时间来排序，把所有的该用户还没有删除的订单查询出来，无论是已经付款，没发货，已经确认收货什么的订单都加载出来
     */
    @RequestMapping("/myOrder")
    public String myOrder(Integer userId,Model model, HttpSession session){
        User user = (User) session.getAttribute("ordinaryUser");
        model.addAttribute("vip", user.getVip());
        List<Orders> ordersList=null;
        try {
            String username=userService.findUsernameById(userId);
            // 通过时间来排序，把所有的该用户还没有删除的订单查询出来，无论是已经付款，没发货，已经确认收货什么的订单都加载出来
            ordersList=ordersService.findAllByIsDel0AndIsDelFromUser0AndUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("ordersList",ordersList);
        model.addAttribute("userId",userId);
        return "frontPage/pages/my-order";
    }

    /**
     * 通过订单 id 来查询出订单的详细内容
     * @param id 订单的 id
     * @param userId 用户 id
     * @param shopId 商品 id
     * @param type
     */
    @RequestMapping(value = "/orderDetail",method = RequestMethod.GET)
    public String orderDetail(Integer id,Integer userId,Integer shopId,String type,Model model, HttpSession session){
        User user = (User) session.getAttribute("ordinaryUser");
        model.addAttribute("vip", user.getVip());
        Orders orders= null;
        try {
            orders = ordersService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("shopId",shopId);
        model.addAttribute("type",type);
        model.addAttribute("orders",orders);
        model.addAttribute("userId",userId);
        return "frontPage/pages/order-detail";
    }

    /**
     * 退款。提供联系方式给用户，让用户直接联系管理员进行退款
     * TODO 需要变成管理审核是否经过退款，然后再退款给用户
     * @param shopOrderId 订单号
     * @param userId 用户 id
     */
    @RequestMapping(value = "/ordersToRefund",method = RequestMethod.GET)
    public String ordersToRefund(String shopOrderId,Integer userId,Model model, HttpSession session){
        User user = (User) session.getAttribute("ordinaryUser");
        model.addAttribute("vip", user.getVip());
        Orders orders= null;
        Contact contact=null;   // 商家的联系方式
        try {
            //申请退款中
            int status=ordersService.findStatusByByShopOrderId(shopOrderId);
            if (status==1){
                // 订单状态变为退款中
                ordersService.updateStatusByShopOrderId(4,shopOrderId);
            }
            orders=ordersService.findByShopOrderId(shopOrderId);
            contact=contactService.onlyOne();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("orders",orders);
        model.addAttribute("contact",contact);
        return "frontPage/alipay/ordersToRefund";
    }

    /**
     * 用户确认收货
     * @param shopOrderId 订单 id
     * @param userId 用户 id
     */
    @RequestMapping(value = "/ordersToConfirmReceipt",method = RequestMethod.GET)
    public String ordersToConfirmReceipt(String shopOrderId, Integer userId,Model model, HttpSession session){
        User user = (User) session.getAttribute("ordinaryUser");
        model.addAttribute("vip", user.getVip());
        Orders orders= ordersService.findByShopOrderId(shopOrderId);
        try {
            //确认收货
            int status=ordersService.findStatusByByShopOrderId(shopOrderId);
            if (status==2){ //商品现在的状态为已付款已发货
                // 把货物状态变成已确认收货
                ordersService.updateStatusByShopOrderId(3,shopOrderId);

                //更新花费金额
                float ordersCost=ordersService.getOrdersCostByShopOrderId(shopOrderId);
                //更新用户的总消费额
                float pay=userService.getPayById(userId);
                float newPay=pay+ordersCost;
                userService.updatePayById(newPay,userId);

                //TODO 在用户确认收货以后， 这里需要实现给每一级店主返利分润
                List<ShopCart> shopCartList = orders.getShopCartList();
                //遍历购物车中所有的商品，逐渐进行返润操作
                for (ShopCart shopCart : shopCartList) {
                    //只有普通商品才进行分润
                    if ("shop".equals(shopCart.getType())) {
                        benefitService.shareGoodsBenefit(shopCart.getShopId(), user.getId(), shopCart.getCount(), shopCart.getId());
                    }
                }
            }
//            orders=ordersService.findByShopOrderId(shopOrderId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("orders",orders);
        model.addAttribute("message","收货成功");
        return "frontPage/include/success";
    }

    /**
     * 用户主动撤销退款申请
     * @param shopOrderId 订单 id
     * @param userId 用户 id
     */
    @RequestMapping(value = "/ordersToWithdraw",method = RequestMethod.GET)
    public String ordersToWithdraw(String shopOrderId,Integer userId,Model model, HttpSession session){
        User user = (User) session.getAttribute("ordinaryUser");
        model.addAttribute("vip", user.getVip());
        Orders orders= null;
        try {
            //成功撤销退款申请
            int status=ordersService.findStatusByByShopOrderId(shopOrderId);
            if (status==4){
                ordersService.updateStatusByShopOrderId(1,shopOrderId);
            }
            orders=ordersService.findByShopOrderId(shopOrderId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("orders",orders);
        model.addAttribute("message","成功撤销退款申请");
        return "frontPage/include/success";
    }

    /**
     * 联系商家页面
     */
    @RequestMapping(value = "/ordersContact",method = RequestMethod.GET)
    public String ordersContact(Model model){
        Contact contact=null;
        try {
            //联系卖家
            contact=contactService.onlyOne();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("contact",contact);
        return "frontPage/alipay/ordersContact";
    }

    /**
     * 取消订单（需要在还未发货之前申请）
     * @param shopOrderId 订单 id
     * @param userId 用户 id
     */
    @RequestMapping(value = "/ordersCancel",method = RequestMethod.GET)
    public String ordersCancel(String shopOrderId,Integer userId, HttpSession session){
        User user = (User) session.getAttribute("ordinaryUser");
        try {
            int status=ordersService.findStatusByByShopOrderId(shopOrderId);
            // 只有在商品还没发货的时候取消订单
            if (status==0){
                ordersService.updateStatusByShopOrderId(6,shopOrderId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/user/myOrder?userId="+userId;
    }

    /**
     * 删除订单
     * @param id 订单 id
     */
    @RequestMapping(value = "/ordersDelete",method = RequestMethod.GET)
    public String ordersDelete(Integer id,Integer userId){
        try {
            ordersService.deleteIsDelFromUser1ByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/user/myOrder?userId="+userId;
    }

    @RequestMapping(value = "/submitOrder",method = RequestMethod.GET)
    public String submitOrder(Integer shopCartId,String shopCartIdListToString,
                              Model model,HttpSession session){
        User user=(User)session.getAttribute("ordinaryUser");
        model.addAttribute("vip", user.getVip());
        List<ShopCart> shopCartList= new ArrayList<>();

        List<UserAddress> userAddressList= null;
        ShopCart shopCart;
        YunFei yunFei=null;
        try {
            // 寻找该用户的所有收货地址
            userAddressList = userAddressService.findAllByIsDel0AndUserId(user.getId());
            // 查询运费 购物价格满 100 免运费
            yunFei=yunFeiService.onlyOne();

            if (shopCartId!=null){
                shopCart=shopCartService.findById(shopCartId);
                model.addAttribute("shopId",shopCart.getShopId());
                model.addAttribute("type",shopCart.getType());
                model.addAttribute("direction","submitOrder");
                model.addAttribute("shopCart",shopCart);
            }

            if (shopCartIdListToString!=null && !shopCartIdListToString.equals("")){

                String[] shopCartIdList=shopCartIdListToString.split(",");

                if (shopCartIdList.length!=0){
                    for (int i=0;i<shopCartIdList.length;i++){
                        logger.info(shopCartIdList[i]+"------------------------------------------");
                        ShopCart shopC=shopCartService.findById(Integer.parseInt(shopCartIdList[i]));
                        shopCartList.add(shopC);
                    }
                }
                model.addAttribute("direction","addToShopCart");
                model.addAttribute("shopCartList",shopCartList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("userAddressList",userAddressList);
        model.addAttribute("user",user);
        model.addAttribute("yunFei",yunFei);
        return "frontPage/pages/submit-order";
    }


    /**
     * 提交订单
     */
    @RequestMapping(value = "/submitOrder",method = RequestMethod.POST)
    public String submitOrder(Integer shopCartId,Integer count,Integer addressId,String liuYan,ShopCartArrays shopCartArrays,String direction,Integer shopId,String type,
                              HttpSession session){
        //如果用户提交数据什么都没有，就重新定位到首页
        //这个存在的原因是避免用户购买的时候。重复按下提交订单，出现 400 Bad Request 错误
        if (shopCartId == null && shopCartArrays.getCountList().size() == 0 && shopCartArrays.getShopCartIdList().size() == 0) {
            return "redirect:/index";
        }

        User user=(User)session.getAttribute("ordinaryUser");
        Integer orderId=null;

        if (user==null){
            return "redirect:/login";
        }

        try {
                /*
                 * 通过生成一个长度为 4 随机字母数字串
                  * 而且通过今天的年月日
                  * 以及今天的第几单来作为 orderId 的唯一标示
                 */
            long no;
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
            SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
            Date today = new Date();
            String nowDate=sdf.format(today);
            // 使用时间作为唯一识别
            no=Long.parseLong(nowDate)*1000;
            // 查找今天的一共下了多少单
            int orderCount=ordersService.countAllByIsDel0AndToday(s.format(today));
            System.out.println(orderCount+"------------------------------------------");
            orderCount=orderCount+1;
            // 保证生成唯一的 orderId
            String id= StringUtil.ziMuRandom()+no+orderCount;

            Orders orders=new Orders();
            orders.setShopOrderId(id);
            orders.setShopOrderTime(new Date());

            orders.setShopOrderMan(user.getUsername());
            orders.setTelephone(user.getTelephone());

            UserAddress userAddress=userAddressService.findById(addressId);
            orders.setReceiver(userAddress.getName());
            orders.setReceiverPhone(userAddress.getPhone());
            orders.setZipCode(userAddress.getZipcode());
            orders.setAddress(userAddress.getProvince()+userAddress.getCity()+userAddress.getArea()+userAddress.getTown()+userAddress.getAdddetail());

            orders.setStatus(0);
            orders.setIsDel(0);
            orders.setIsDelFromUser(0);
//                orders.setShopKeeper(shopKeeper);
            orders.setLiuYan(liuYan);   //保存买家留言。但是最多只能够输入85个汉字

            YunFei yunFei=yunFeiService.onlyOne();

            float ordersFirstCost=0; // 成本价
            float ordersCost=0; // 销售价 ==> 这里需要根据 消费者的身份去实现定价不一样
            float ordersProfits=0; //利润 == ordersCost - ordersFirstCost

            //单种商品购买，计算总价以及利润价 ==> 单间商品购买的情况，传递到服务器，需要购买商品的 id，以及需要购买的的数量 count
            if (shopCartId!=null||count!=null){
                //用户提交订单的时候，判断当前商品是否已经下架了，如果当前商品已经下架了就重定向到主页 /index
                ShopCart shopCart=shopCartService.findById(shopCartId);
//                ordersFirstCost=shopCart.getFirstCost()*count;
                //通过查询购物车的类型去判断到底是什么类型的商品
                //购买瞬时秒杀商品
                if (shopCart.getType().equals("shunShop")) {
                    ShunShop shunShop = shunShopService.findById(shopCart.getShopId());
                    if (shunShop.getIsDel() == 1 || shunShop.getStatus() == 1) {
                        return "/index";
                    }
                    ordersFirstCost = shunShopService.findFirstCostById(shopId) * count;
                    ordersCost = shunShopService.findThirdById(shopId) * count; //计算用户购买的秒杀商品总价格=单价 * 数量
                }
                //购买普通商品
                if (shopCart.getType().equals("shop")){
                    Shop shop = shopService.findById(shopCart.getShopId());
                    if (shop.getIsDel() == 1 || shop.getStatus() == 1) {
                        return "redirect:/index";
                    }
                    //普通商品
                    ordersFirstCost = shopService.findFirstCostById(shopId) * count;    //查找商品的成本价
                    ordersCost = shopCartService.getCostByShopCartId(shopCart, user.getVip()) * count; //根据不同用户的身份去设置不同的订单价格
                }
//                ordersCost=shopCart.getSecondCost()*count;
//                ordersProfits=shopCart.getProfits()*count;
                //从数据库中移除购物车的信息
                shopCartService.deleteRealByPrimaryKey(shopCart);
                // 这里通过 setter 方法是否会自动同步数据库
                shopCart.setAllFirstCost(ordersFirstCost); //设置成本价
                shopCart.setAllSecondCost(ordersCost); //设置售价
                shopCart.setCount(count); //记录用户到底购买了多少件
//                shopCart.setAllProfits(ordersProfits);
                //g10guang 修改了这里的 利润=售价-成本
                shopCart.setAllProfits(ordersCost - ordersFirstCost);
                shopCart.setId(null);
                shopCart.setStatus(2);//已下订
                orders.addShopCartList(shopCart);
            }else {
                //多种商品购买，购物车形式
                List<Integer> shopCartIdList = shopCartArrays.getShopCartIdList(); //得到每一个购物车的信息
                List<Integer> countList = shopCartArrays.getCountList(); //对应每一个商品的购买数量
                float tmpFirstCost = 0f;
                float tmpOrderSCost = 0f;
                float tmpProfit = 0f;
                if (shopCartIdList.size() > 0 && countList.size() > 0) {
                    for (int i = 0; i < shopCartIdList.size(); ++i) {
                        ShopCart shopCart = shopCartService.findById(shopCartIdList.get(i));
                        //瞬杀商品
                        if("shunShop".equals(shopCart.getType())){
                            ShunShop shunShop = shunShopService.findById(shopCart.getShopId());
                            if (shunShop.getIsDel() == 1 || shunShop.getStatus() == 1){
                                shopCartIdList.remove(shopCart);
                                i--;
                                shopCartService.deleteRealByPrimaryKey(shopCart); //从数据库中删除该购物车的信息
                                continue;
                            }
                            //售价
                            tmpOrderSCost = shunShopService.findThirdById(shopCart.getShopId()) * countList.get(i);
                            //成本
                            tmpFirstCost = shunShopService.findFirstCostById(shopCart.getShopId()) * countList.get(i);
                            //利润
                            tmpProfit = tmpOrderSCost - tmpFirstCost;
                        }
                        //普通商品
                        if ("shop".equals(shopCart.getType())){
                            Shop shop = shopService.findById(shopCart.getShopId());
                            if (shop.getIsDel() == 1 || shop.getStatus() == 1){
                                shopCartIdList.remove(shopCart);
                                i--;
                                shopCartService.deleteRealByPrimaryKey(shopCart); //从数据库中删除该购物车的信息
                                continue;
                            }
                            //售价
                            tmpOrderSCost = shopCartService.getCostByShopCartId(shopCart, user.getVip()) * countList.get(i);
                            //成本
                            tmpFirstCost = shopService.findFirstCostById(shopCart.getShopId()) * countList.get(i);
                            tmpProfit = tmpOrderSCost - tmpFirstCost;
                        }
                        ordersFirstCost += tmpFirstCost; //计算总成本
                        ordersCost+=tmpOrderSCost; //计算总售价
//                        ordersProfits += tmpProfit; //计算总利润
//                        ordersCost += shopCart.getSecondCost() * countList.get(i);
                        // 从数据库中移除
                        shopCartService.deleteRealByPrimaryKey(shopCart);
                        shopCart.setAllFirstCost(ordersFirstCost); //设置购物车成本
                        shopCart.setAllSecondCost(ordersCost); //设置购物车售价
                        shopCart.setAllProfits(tmpProfit); //设置购物车利润
                        shopCart.setCount(countList.get(i));
                        shopCart.setId(null);
                        shopCart.setStatus(2);//已下订
                        orders.addShopCartList(shopCart); //通过级联会再次保存到数据库
                    }
                }
            }
            if (orders.getShopCartList().size() == 0){
                //购物车中所有商品都已经过期，不能够进行任何购买
                return "redirect:/index";
            }
            //总利润 = 总售价 - 总成本
            ordersProfits = ordersCost - ordersFirstCost;
            orders.setFirstCost(ordersFirstCost);
            orders.setProfits(ordersProfits);
            // 判断是否达到免运费条件 如果没有达到免运费条件就需要把运费加总价
            if (yunFei!=null){
                if (ordersCost>=yunFei.getMianYunFei()){
                    //免运费
                    orders.setOrdersCost(ordersCost);
                    orders.setYunFei(0);
                }else {
                    orders.setOrdersCost(ordersCost+yunFei.getYunFei());
                    orders.setYunFei(yunFei.getYunFei());
                }
            }else {
                orders.setOrdersCost(ordersCost);
                orders.setYunFei(0);
            }
            //这里持久化 orders 的时候，会把上面对应购物车的信息也重新存储到数据库中
            orders.setShopKeeper(user.getShopkeeper().getUsername());
            orderId=ordersService.saveAndReturnId(orders);
            logger.info("--------------------------------------------orderId为"+orderId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 是否是把某件商品添加到购物车
        if (direction.equals("addToShopCart")){
            return "redirect:/user/orderDetail?id="+orderId+"&userId="+user.getId();
        }else {
            return "redirect:/user/orderDetail?id="+orderId+"&userId="+user.getId()+"&direction="+direction+"&shopId="+shopId+"&type="+type;
        }
    }


    /*
    ================= 用户管理订单信息 end ===============
     */


    /*
    ================= 用户管理购物车 start ===============
     */

    @ResponseBody
    @RequestMapping("/deleteFromShopCart")
    public Map<String,String> deleteFromShopCart(String shopCartIdList){

        Map<String ,String> map=new HashMap<>();

        try{
            JSONArray idList=new JSONArray().fromObject(shopCartIdList);

            for(int i=0;i<idList.size();i++){
                logger.info(idList.getString(i));
                shopCartService.deleteByPrimaryKey(Integer.parseInt(idList.getString(i)));
            }
            map.put("message","success");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("message","fail");
        }
        return map;
    }

    @RequestMapping("/myShopCart")
    public String myShopCart(Model model,HttpSession session){

        User user=(User)session.getAttribute("ordinaryUser");
        model.addAttribute("user",user);

        if (user==null){
            return "redirect:/login";
        }

        List<ShopCart> shopCartsList=null;
        try {
            //这里需要更改，把店主虽然加入了购物车，但是普通商品下架了或者是瞬杀商品过时的过滤掉
            shopCartsList=shopCartService.findAllByIsDel0AndUserIdAndStatus1(user.getId());
            for (int i = 0; i < shopCartsList.size(); i++) {
                ShopCart shopCart = shopCartsList.get(i);
                if ("shunShop".equals(shopCart.getType())) {
                    //秒杀商品
                    //判断秒杀商品是否已经删除了或者是已经过期了
                    if (shunShopService.findByIsDel0AndNoEndAndId(shopCart.getShopId()) == null) {
                        shopCartsList.remove(shopCart);
                        i--;
                    }
                } else if ("shop".equals(shopCart.getType())) {
                    //普通商品已经删除了
                    if (shopService.findIsDelById(shopCart.getShopId()) == null) {
                        shopCartsList.remove(shopCart);
                        i--;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("shopCartsList",shopCartsList);
        model.addAttribute("vip", user.getVip());
        return "frontPage/pages/my-shopCart";
    }

    //我的购物车选择商品去提交订单
    @RequestMapping(value = "/myShopCartToSubmitOrder")
    public String myShopCartToSubmitOrder(int[] shopCartIdList,
                                          HttpSession session) {
        User user=(User)session.getAttribute("ordinaryUser");

        String shopCartIdListToString="";
        //将所有的 shopCart id 取出来，用 , 连接起来，再重定向到提交订单
        if (shopCartIdList.length!=0){
            for (int i=0;i<shopCartIdList.length;i++){
                shopCartIdListToString+=shopCartIdList[i]+",";
            }
            shopCartIdListToString=shopCartIdListToString.substring(0,shopCartIdListToString.length()-1);
        }
        return "redirect:/user/submitOrder?shopCartIdListToString="+shopCartIdListToString;
    }


    /*
    ================ 用户管理购物车 end ===================
     */

    @ResponseBody
    @RequestMapping("/toBeShopkeeper")
    public Map<String, String> vipUpdateToShopkeeper(@RequestParam("realName") String realName,
            @RequestParam("alipay") String alipay,
            HttpSession session) throws SQLException {
        Map<String, String> map = new HashMap<>();
        User user = (User) session.getAttribute("ordinaryUser");
        if (user.getVip() == 1) {
            Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO wd_shopkeeper (isDel, password, status, username, allProfit, yiTiXian, telephone, allUserNumber, belong, directUserNumber, level, allShopkeeperNumber, directShopkeeperNumber, registerTime, deathTime, isNew, nickname, zhifubao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, 0); //isDel
            preparedStatement.setString(2, user.getPassword()); //password
            preparedStatement.setInt(3, 1); //status 没缴费前，默认锁定
            preparedStatement.setString(4, user.getUsername()); //username
            preparedStatement.setFloat(5, 0f); //allProfit
            preparedStatement.setFloat(6, 0f); //yiTiXian
            preparedStatement.setString(7, user.getTelephone()); //telephone
            preparedStatement.setInt(8, 0); //allUserNumber
            preparedStatement.setInt(9, user.getShopkeeper().getId()); //belong
            preparedStatement.setInt(10, 0); //directUserNumber
            preparedStatement.setInt(11, 0); //level
            preparedStatement.setInt(12, 0); //allShopkeeperNumber
            preparedStatement.setInt(13, 0); //directUserNumber
            long now = new Date().getTime();
            preparedStatement.setTimestamp(14, new Timestamp(now)); //registerTime
            preparedStatement.setTimestamp(15, new Timestamp(now)); //deathTime
            preparedStatement.setInt(16, 1); //isNew
            preparedStatement.setString(17, realName); //set realName
            preparedStatement.setString(18, alipay);
            preparedStatement.executeUpdate();
            user.setVip(2);
            //更新用户的状态为 1 锁定
//        userService.suoDingById(user.getId());
            user.setStatus(1);
            userService.update(user);
            //更新店主数目
            shopKeeperService.decreaseDirectUserNumberAndAllUserNumber(user.getShopkeeper().getId());
            shopKeeperService.updateNumberByAddShopkeeper(user.getShopkeeper().getId());
            //如何使得响应的店主数目全部减少 1

            map.put("username", user.getUsername());
            map.put("telephone", user.getTelephone());
            map.put("url", "/annualFee/submitAnnualFee");
        }
        //取消该用户的登录状态
        session.removeAttribute("ordinaryUser");
        return map;
    }
}
