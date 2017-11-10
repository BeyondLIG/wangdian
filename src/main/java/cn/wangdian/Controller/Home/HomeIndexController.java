package cn.wangdian.Controller.Home;

import cn.wangdian.Model.*;
import cn.wangdian.Service.*;
import cn.wangdian.utils.Encode;
import cn.wangdian.utils.encryption.PBKDF2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 店家的推广商品
 */
@Controller
public class HomeIndexController {

    private Log logger= LogFactory.getLog(HomeIndexController.class);

    private Encode encodeUtil = new Encode();

    @Autowired
    private FirstPhotoService firstPhotoService;
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private ShunShopService shunShopService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopPhotosService shopPhotosService;
    @Autowired
    private ShopAttributesService shopAttributesService;
    @Autowired
    private ShunShopPhotosService shunShopPhotosService;
    @Autowired
    private ShunShopAttributesService shunShopAttributesService;
    @Autowired
    private YunFeiService yunFeiService;
    @Autowired
    private ShopCartService shopCartService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private ShopTypeService shopTypeService;

    /**
     * 首页 start
     */
    @RequestMapping({"/index", ""})
    public String index(Model model,HttpSession session){
        // 普通用户所用的注册账号 普通用户需要凭借注册账号才能够进行购买
        User user=(User)session.getAttribute("ordinaryUser");

        if (user!=null){
            model.addAttribute("ordinaryUser",user);
            model.addAttribute("vip", user.getVip());
        }else {
            model.addAttribute("vip", 0);
        }

        List<Shop> shopList= null;  // 前 10 条推荐商品
        List<String> shopTypeNameList= null; // 所有的商品类别
        try {
            Integer page=0;

            // 把头 10 条推荐商品查询出来
            shopList = shopService.findAllByIsDel0AndIsRecommend1Limit(page, 10);
            // 查询所有的商品类型
//            shopTypeNameList = shopTypeService.findAllName();
            shopTypeNameList = shopService.findAllShopTypeByIsDel0();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("shopList",shopList);
        model.addAttribute("shopListAll",shopTypeNameList);
        return "frontPage/pages/home-page";
    }

    @RequestMapping(value = "/allShopType", method = RequestMethod.GET)
    @ResponseBody
    public List<String> allShopType(){
        return shopTypeService.findAllName();
    }


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "frontPage/pages/login";
    }

    /**
     * 限时抢购页面
     * @param size 前端请求一页数据最多返回多少条
     */
    @RequestMapping("/flashSale")
    public String flashSale(@RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            Model model){
        //防止用户一次访问过多数据
        if (size > 20) {
            size = 10;
        }
        List<ShunShop> shunShopList=null;
        try {
            Integer page=0;
            //记载前20条抢购信息
            shunShopList=shunShopService.findAllByIsDel0Limit(page, size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("shunShopList",shunShopList);
        return "frontPage/pages/flash-sale";
    }

    /**
     * 用户滑动屏幕，一直加载抢购商品信息
     * @param page 需要显示第几页。默认从第0页开始
     * @param size 一页数据访问多少条
     */
    @ResponseBody
    @RequestMapping("/flashSalePage")
    public Map<String,Object> flashSalePage(@RequestParam("page") Integer page,
                                            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size){
        if (size > 20) {
            size = 10;
        }
        Map<String,Object> map= new HashMap<>();
        List<ShunShop> shunShopList=null;
        try {
            shunShopList=shunShopService.findAllByIsDel0Limit(page, size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("shunShopList",shunShopList);
        return map;
    }


    /**
     * 首页 end
     */


    /**
     * 登录 start
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map<String,String> login(@RequestParam("username")String username,
                                    @RequestParam("password")String password,
                                    HttpSession session){
        Map<String,String> map= new HashMap<>();
        try {
            User user = new User();
            // 正则表达式判断是否是手机号码
            String regex_mobile = "^1[34578]\\d{9}$";

            if (!Pattern.matches(regex_mobile, username)) {
                map.put("message", "手机号码格式不正确，请重新确认");
            }else {
                user = userService.selectByTelephone(username);
                PBKDF2 pbkdf2 = PBKDF2.getInstance();
                if (user == null) {
                    map.put("message", "该用户不存在，请先注册！");
                } else if(user.getIsDel() == 1){
                    map.put("message", "该用户已经被删除，请联系管理员");
                } else if (!pbkdf2.validate(password, user.getPassword())) {
                    map.put("message", "用户名或密码错误！");
                } else if (user.getStatus() == 1) {
                    //TODO 应该提醒用户欠交年费，然后重定向到支付年费的页面
                    map.put("message", "当前用户已被锁定，请联系管理员或店主解锁！");
                    map.put("url", "/annualFee/submitAnnualFee");
                } else {
                    //成功登录
                    session.setMaxInactiveInterval(30 * 60);
                    //无论是普通用户，vip用户还是店主在 session 中的状态都是 ordinaryUser。
                    //是通过 vip 属性来判断用户的类型，然后在用户查询商品时候实现差异化定价
                    session.setAttribute("ordinaryUser", user);
                    map.put("message", "登录成功");
                }
            }
        }catch (Exception e) {
            map.put("message","登录失败，请稍后再试！");
        }
        return map;
    }



    /**
     * 登录 end
     * @param shopKeeper
     * @return
     */

    /**
     * specification start
     */

//    /**
//     * 显示前20条推荐商品和所有的商品类别
//     */
//    @RequestMapping("/categories")
//    public String categories(Model model){
//        List<Shop> shopList= null;  // 前 20 条推荐商品
//        List<String> shopListAll= null; // 所有的商品类别
//        try {
//            Integer page=0;
//            // 把头 20 条推荐商品查询出来
//            shopList = shopService.findAllByIsDel0AndIsRecommend1Limit(page);
//            // 查询所有的商品类型
//            shopListAll = shopService.findAllShopTypeByIsDel0();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        model.addAttribute("shopList",shopList);
//        model.addAttribute("shopListAll",shopListAll);
//        //TODO 这里前段显示有 bug
//        return "frontPage/pages/categories";
//    }

    /**
     * 前段一部请求制定页数的推荐商品，每次加载 20 条数据
     * @param page 指定加载的页数，默认从第0页开始
     * @param size 每一页返回多少条数据
     */
    @ResponseBody
    @RequestMapping("/isRecommendPage")
    public Map<String,Object> isRecommendPage(@RequestParam("page") Integer page,
                                              @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                              HttpSession session){
        if (size > 20) {
            size = 10;
        }
        Map<String,Object> map= new HashMap<>();
        User user = (User) session.getAttribute("ordinaryUser");
        if (user != null) {
            map.put("vip", user.getVip());
        }else{
            map.put("vip", 0);
        }
        List<Shop> shopList= null;
        try {
            shopList = shopService.findAllByIsDel0AndIsRecommend1Limit(page, size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("shopList",shopList);
        //返回 json
        return map;
    }

    /**
     * 查询同一个商品类型的所有商品型号 shopModel
     * @param styleNum 商品类型
     */
    @ResponseBody
    @RequestMapping(value = "/specification",method = RequestMethod.POST)
    public List<String> specification(String styleNum){
        List<String> shopList= null;
        try {
            shopList= shopService.findAllShopModelByIsDel0AndType(styleNum);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return shopList;
    }

    /**
     * search 用户从搜索框输入关键字，进行商品的搜索
     * 查询的只是普通商品而不是秒杀商品
     * @param keyword 用户输入的关键字
     */
    @RequestMapping("/search")
    public String search(@RequestParam("keyword") String keyword,
                                @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                Model model, HttpSession session){
        User user = (User) session.getAttribute("ordinaryUser");
        if (user != null) {
            model.addAttribute("vip", user.getVip());
        }else {
            model.addAttribute("vip", 0);
        }
        List<Shop> shopList= null;
        if (size > 20) {
            size = 10;
        }
        try {
            Integer page=0;
            shopList = shopService.findAllByIsDel0OrShopNameOrShopTypeOrShopModel(keyword, page, size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("shopList",shopList);
        model.addAttribute("shopName", keyword);
        return "frontPage/pages/search-result";
    }

    /**
     * 用户通过 search 搜索框进入展示相应商品，后续用户需要查看更多，前端 js 通过异步请求数据来展示新的商品，不在重新加载整个网页
     * 查询的只是普通商品而不是秒杀商品
     * @param keyword 搜索的商品类型
     * @param page 搜索第几页内容。从第 0 页开始计数
     * @param size 前段发送来查询的数量
     */
    @ResponseBody
    @RequestMapping(value = "/searchPage")
    public Map<String,Object>  searchAdd(@RequestParam("keyword") String keyword,
                                         @RequestParam("page") Integer page,
                                         @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                         HttpSession session){
        Map<String,Object> map= new HashMap<>();
        User user = (User) session.getAttribute("ordinaryUser");
        if (user != null) {
            map.put("vip", user.getVip());
        }else {
            map.put("vip", 0);
        }
        List<Shop> shopList= null;
        if (size > 20) {
            size = 10;
        }
        try {
            shopList = shopService.findAllByIsDel0OrShopNameOrShopTypeOrShopModel(keyword, page, size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("shopList",shopList);
        return map;
    }


    /**
     * 商品列表
     * @param shopType 商品类型
     * @param shopModel 商品分类
     */
    @RequestMapping("/commodityList")
    public String commodityList(@RequestParam(value = "shopType", required = false, defaultValue = "") String shopType,
                                @RequestParam(value = "shopModel", required = false, defaultValue = "") String shopModel,
                                @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                Model model)throws Exception{
        if (size > 20) {
            size = 10;
        }
        List<Shop> shopList= null;

        String encode=encodeUtil.getEncoding(shopType);
        logger.info("编码格式为"+encode);
        //我不明白为什么 postman 传输过来的字符串的格式是 GB2312
        //GB2312是服务器上的编码格式
        if(!encode.equals("GB2312")){
            shopType=new String(shopType.getBytes(encode),"UTF-8");
            shopModel=new String(shopModel.getBytes(encode),"UTF-8");
        }
        try {
            Integer page=0;
//            shopList = shopService.findAllByIsDel0AndShopTypeAndShopModel(shopType, page, size);
            shopList = shopService.findAllByIsDel0AndShopTypeAndShopModel(shopType, page ,size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("shopList",shopList);
        model.addAttribute("shopType",shopType);
        model.addAttribute("shopModel",shopModel);
        return "frontPage/pages/commodity-list";
    }

    /**
     * 异步请求特定类型的商品
     * @param page 第几页数据，从第0页开始
     * @return
     */
    @RequestMapping("/commodityListPage")
    @ResponseBody
    public Map<String,Object> commodityListPage(@RequestParam(value = "shopType") String shopType,
                                                @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                                HttpSession session)  {
        User user = (User) session.getAttribute("ordinaryUser");
        int vip = user != null ? user.getVip() : 0;
        if (size > 20){
            size = 10;
        }
        Map<String,Object> map= new HashMap<>();
        List<Shop> shopList= null;
        try {
            //encode == 'GB2312'
            String encode = encodeUtil.getEncoding(shopType);
            if(!encode.equals("GB2312")){
                shopType=new String(shopType.getBytes(encode),"UTF-8");
            }
            //query the DB
            shopList = shopService.findAllByIsDel0AndShopTypeAndShopModel(shopType ,page,size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("vip", vip);
        map.put("shopList",shopList);
        //Throw Stackoverflow here
        return map;
    }

    /**
     * 用于在支付宝支付页面展示上秒杀商品的信息
     * 展示商品详情也是使用这个网页
     * @param type 判断是普通商品还是秒杀商品
     * @param id 对应商品的 id
     */
    @RequestMapping("/commodityDetail")
    public String commodityDetail(Integer id,String type,Model model, HttpSession session){

        User user = (User) session.getAttribute("ordinaryUser");
        if (user != null) {
            model.addAttribute("vip", user.getVip());
        }else {
            model.addAttribute("vip", 0);
        }
        if (type.equals("shunShop")){
            // 秒杀商品类型
            ShunShop shunShop=null;
            List<ShunShopPhotos> shunShopPhotosList= null;  //商品详情图片
            List<ShunShopPhotos> shunShopShowsList= null;   //商品展示图片
            List<ShunShopAttributes> shunShopAttributesList= null;  //商品的所有可选属性

            try {
                shunShop=shunShopService.findById(id);
                if (shunShop.getIsDel() == 1 || shunShop.getStatus() == 1) {
                    //商品已经下架或者已经删除了，不能再查看商品详情，直接重定向到主页
                    return "redirect:/index";
                }
                // photoType 0 ==> 商品图片展示 1 ==> 商品详情
                shunShopPhotosList=shunShopPhotosService.findAllByIsDel0AndShopId(id,1);
                shunShopShowsList=shunShopPhotosService.findAllByIsDel0AndShopId(id,0);
                shunShopAttributesList=shunShopAttributesService.findAllByIsDel0AndShopId(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            model.addAttribute("type","shunShop");
            model.addAttribute("shopPhotosList",shunShopPhotosList);
            model.addAttribute("shopShowsList",shunShopShowsList);
            model.addAttribute("shopAttributesList",shunShopAttributesList);
            model.addAttribute("shop",shunShop);

        }else {
            // 普通商品类型
            Shop shop= null;
            List<ShopPhotos> shopPhotosList= null; //商品详情图片
            List<ShopPhotos> shopShowsList= null;   //商品展示图片
            List<ShopAttributes> shopAttributesList= null;  //商品属性
            try {
                shop = shopService.findById(id);
                if (shop.getIsDel() == 1 || shop.getStatus() == 1){
                    return "redirect:/index";
                }
                //商品详情
                shopPhotosList = shopPhotosService.findAllByIsDel0AndShopId(id,1);
                //商品轮播
                shopShowsList = shopPhotosService.findAllByIsDel0AndShopId(id,0);
                //商品属性
                shopAttributesList = shopAttributesService.findAllByIsDel0AndShopId(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            model.addAttribute("type","shop");
            model.addAttribute("shopPhotosList",shopPhotosList);
            model.addAttribute("shopShowsList",shopShowsList);
            model.addAttribute("shopAttributesList",shopAttributesList);
            model.addAttribute("shop",shop);
        }

        YunFei yunFei=yunFeiService.onlyOne();
        model.addAttribute("yunFei",yunFei);
        /*logger.info(promptInfo+"---------------------------=====");
        if(promptInfo!=null){
            if (promptInfo==0){
                logger.info(promptInfo+"---------------------------=====");
                model.addAttribute("promptInfo","加入购物车失败，请稍后再试！");
            }else if(promptInfo==1){
                logger.info(promptInfo+"---------------------------=====");
                model.addAttribute("promptInfo","成功加入购物车！");
            }
        }*/
        return "frontPage/pages/commodity-detail";
    }

    /**
     * user center start
     */

    /**
     * 用户登出
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("ordinaryUser");
        return "redirect:/index";
    }

    /**
     * 选择商品的参数
     * 比如什么颜色，什么型号之类的
     */
    //选择参数页面
    @RequestMapping("/chooseParam")
    public String chooseParam(Integer shopId,String type,String direction,
                              Model model,HttpSession session){

        User user=(User)session.getAttribute("ordinaryUser");
        if (user != null) {
            model.addAttribute("vip", user.getVip());
        }else {
            model.addAttribute("vip", 0);
        }
        model.addAttribute("user",user);

        if (user==null){
            return "redirect:/login";
        }
        List<ShopAttributes> shopAttributesList;
        List<ShunShopAttributes> shunShopAttributesList;
        try {

            if (type.equals("shop")){
                Shop shop = shopService.findById(shopId);
                //当商品已经下降或者已经被删除的时候，用户不能够查看
                if (shop.getIsDel() == 1 || shop.getStatus() == 1){
                    return "redirect:/index";
                }
                shopAttributesList=shopAttributesService.findAllByIsDel0AndShopId(shopId);
                model.addAttribute("shop",shop);
                model.addAttribute("type","shop");
                // 可选择的属性列表
                model.addAttribute("shopAttributesList",shopAttributesList);
            }
            if (type.equals("shunShop")){
                ShunShop shunShop=shunShopService.findById(shopId);
                if (shunShop.getIsDel() == 1 || shunShop.getStatus() == 1){
                    return "redirect:/index";
                }
                shunShopAttributesList=shunShopAttributesService.findAllByIsDel0AndShopId(shopId);
                model.addAttribute("shop",shunShop);
                model.addAttribute("type","shunShop");
                // 可选择的属性列表
                model.addAttribute("shunShopAttributesList",shunShopAttributesList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("direction",direction);
        logger.info(direction+"----------------------");
        return "frontPage/pages/choose-param";
    }

    /**
     * 用户选择参数后，提交订单
     * @param shopId
     * @param type
     * @param shopDetail
     * @param count
     * @param direction
     * @param session
     * @return
     */
    @RequestMapping(value = "/submitParam",method = RequestMethod.POST)
    public String submitParam(Integer shopId, String type, String shopDetail, Integer count, String direction,
                              HttpSession session){
        logger.info(direction+"=============================");

        User user=(User)session.getAttribute("ordinaryUser");
        Integer vip = user.getVip();
        Integer shopCartId=null;
        ShopCart shopCart=new ShopCart();

        String promptInfo;      //操作提示信息

        //保存用户所选的商品信息到数据库，即添加到购物车
        try {
            shopCart.setDetail(shopDetail);
            shopCart.setCount(count);
            shopCart.setIsDel(0);
            shopCart.setUserId(user.getId());
            shopCart.setStatus(0);

            if (type.equals("shop")){
                //这一步加入购物车的操作需要通过不同的用户 vip 等级来设置不同的价格
                Shop shop = shopService.findById(shopId);
                shopCart.setShopId(shop.getId());
                shopCart.setShopNumber(shop.getNumber());
                shopCart.setShopModel(shop.getShopModel());
                shopCart.setShopName(shop.getName());
//                shopCart.setShopType(shop.getShopType().getName()); // 抛出了空指针异常
                shopCart.setShopType(shop.getShopTypeName());
                shopCart.setFirstCost(shop.getFirstCost());
                shopCart.setAllFirstCost(shop.getFirstCost()*count);
//                shopCart.setSecondCost(shop.getSecondCost());
                //计算该用户对应的价格用该是多少 设置secondCost
                //商品的真实售价
                float sellingPrice;
                switch (vip){
                    case 0: //普通用户价格
                        sellingPrice = shop.getSecondCost();
                        break;
                    case 1: //vip 用户价格
                        sellingPrice = shop.getVipPrice();
                        break;
                    case 2: //店主售价
                        sellingPrice = shop.getShopkeeperPrice();
                        break;
                    default: //出现异常，以普通用户价格出售
                        sellingPrice = shop.getSecondCost();
                }
                shopCart.setSecondCost(sellingPrice);
                shopCart.setAllSecondCost(sellingPrice * count);
                //单间利润 = 真实售价 - 成本
                float profits= sellingPrice - shop.getFirstCost();
                shopCart.setProfits(profits);
                shopCart.setAllProfits(profits * count);
                shopCart.setPhoto(shop.getFirstPhoto());
                shopCart.setType("shop");
            }
            if (type.equals("shunShop")){
                ShunShop shunShop=shunShopService.findById(shopId);
                shopCart.setShopId(shunShop.getId());
                shopCart.setShopNumber(shunShop.getNumber());
                shopCart.setShopModel(shunShop.getShopModel());
                shopCart.setShopName(shunShop.getName());
                shopCart.setShopType(shunShop.getShopType());
                shopCart.setFirstCost(shunShop.getFirstCost());
                shopCart.setAllFirstCost(shunShop.getFirstCost()*count);
                shopCart.setSecondCost(shunShop.getThirdCost());
                shopCart.setAllSecondCost(shunShop.getThirdCost()*count);
                float profits=shunShop.getThirdCost()-shunShop.getFirstCost();
                shopCart.setProfits(profits);
                shopCart.setAllProfits(profits*count);
                shopCart.setPhoto(shunShop.getFirstPhoto());
                shopCart.setType("shunShop");
            }

            promptInfo="成功加入购物车！";

            shopCartId=shopCartService.save(shopCart);
        } catch (Exception e) {
            promptInfo="加入购物车失败，请稍后再试！";
            e.printStackTrace();
        }

        //转到参数选择前的商品详情页面
        if (direction.equals("addToShopCart")){
            shopCartService.updateStatus1ById(shopCartId);
            session.setAttribute("promptInfo",promptInfo);
//            return "redirect:/commodityDetail?id="+shopId+"&type="+type;
            return "redirect:/user/myShopCart";
        } else if (direction.equals("submitOrder")){
            //直接购买，跳转到提交订单页面
            return "redirect:/user/submitOrder?shopCartId="+shopCartId;
        }
        return null;
    }


}
