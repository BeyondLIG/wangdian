package cn.wangdian.Service;

import cn.wangdian.Model.*;
import cn.wangdian.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 利润服务类
 */
@Service
public class BenefitService {
    @Autowired
    private BenefitRepository benefitRepository;

    @Autowired
    private ShopKeeperRepository shopKeeperRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ShopkeeperProfitTrackRepository shopkeeperProfitTrackRepository;

    @Autowired
    private OrdersService ordersService;
    /**
     * 店主用户购买商品，店主、主管、经理进行分润的逻辑
     * @param shopId 用户购买的商品的 id
     * @param userId 购买商品的用户的 id
     * @param count 用户购买同一款商品的数量
     * @param shopCartId 用户购物订单号
     * @return 分润操作执行成功返回 1
     * 分润操作执行失败返回 0
     */
    @Transactional
    public int shareGoodsBenefit(Integer shopId, Integer userId, Integer count, Integer shopCartId) {
        //查询用户
        User user = userRepository.findById(userId);
        //查询商品
        Shop shop = shopRepository.findById(shopId);
        Date now = new Date();
        //判断是否符合分润的条件
        //(user.getVip() == 1 || user.getVip() == 2) 普通用户购买商品不进行分润
        if (user != null && shop != null && (user.getVip() == 1 || user.getVip() == 2)) {
            ShopKeeper shopKeeper;
            if (user.getVip() == 1) {//vip=1，vip用户
                //查询用户直接店主
                shopKeeper = user.getShopkeeper();
                //理论上 shopKeeper 不会是 NULL, 这个判断是为了防止后期数据库出错  且 店主不是因为欠年费就可以享受分润的权利
                if (shopKeeper != null && shopKeeper.getStatus() != 1) {
                    //对店主等级分润的，只有 vip 购买才有。店主购买商品，没有这一等级的分润
                    //开始执行分润逻辑
                    //计算直接店主应该分到多少利润
                    float shopKeeperIncome = (shop.getVipPrice() - shop.getShopkeeperPrice()) * count; //40
                    //更新直接店主的总利润
                    shopKeeperRepository.addAllProfitById(shopKeeperIncome, shopKeeper.getId()); //140
                    //记录跟踪店主的收益
                    //直接下属购买东西，店主需要获得分润
                    ShopkeeperProfitTrack shopkeeperProfitTrack = new ShopkeeperProfitTrack(shopKeeper.getId(), shopKeeper.getLevel(), shopKeeperIncome, 0, user.getUsername(), user.getTelephone(), now, shopCartId);
                    shopkeeperProfitTrackRepository.save(shopkeeperProfitTrack);
                }
            } else {//vip=2,店主
                shopKeeper = shopKeeperRepository.findByUsername(user.getUsername());
            }
            //只有该店主的 status != 1，该店主的上一级才有权利分润
            assert shopKeeper != null;
            if (shopKeeper.getStatus() != 1) {
                //查询主管分润百分比
                float b = benefitRepository.findBById(1);  //0.3
                //查询径流分润百分比
                float c = benefitRepository.findCById(1);  //0.6
                //店主价到成本这一段利润，用来给主管和经理分润 ==> (店主价 - 成本价) * 购买数量
                float profitForHigherLevel = (shop.getShopkeeperPrice() - shop.getFirstCost()) * count; //20
                //查询直接店主等级
                int level = shopKeeper.getLevel();  //0
                //查询直接店主的上一级店主
                int belong = shopKeeper.getBelong();//6
                //临时变量
                int tmpLevel;
                //对应等级的店主能够分到百分之几的利润
                float percent;
                while (level < 2 && belong != 0 && shopKeeperRepository.findStatusById(belong) != 1) {
                    //level < 2 当前店主还不是经理
                    //belong != 0 目前店主还有上一级
                    //shopKeeperRepository.findStatusById(belong) != 1 上一级店主不是因为欠交年费而可以享受分润的权利
                    tmpLevel = shopKeeperRepository.findLevelById(belong); //1
                    if (tmpLevel > level) { //1>0
                        //上一级店主能够进行分润
                        percent = tmpLevel == 2 ? c : b;  //0.3   0.6
                        //计算能够分润的上一级对应的新总利润
                        float profitDelta = profitForHigherLevel * percent; //6  12
                        //更新数据库
                        shopKeeperRepository.addAllProfitById(profitDelta, belong); //106  112
                        //跟踪店主收益
                        //间接下属买东西
                        ShopkeeperProfitTrack shopkeeperProfitTrack = new ShopkeeperProfitTrack(belong, tmpLevel,
                                profitDelta, 1, user.getUsername(), user.getTelephone(), now, shopCartId);
                        shopkeeperProfitTrackRepository.save(shopkeeperProfitTrack);
                        //更新 level 等级
                        level = tmpLevel;
                    }
                    //查询下一级店主
                    belong = shopKeeperRepository.findBelongById(belong);
                }
            }
            //成功进行了分润逻辑
            return 1;
        }
        //没有进行分润逻辑
        return 0;
    }

    /**
     * 新招店主的分润操作实现
     * 该方法里面加上了判断：店主是否是新招的，第一次提交年费
     * Warning: 该函数只能够被用户提交年费后执行，其他时候不能够执行该函数
     * @param newShopKeeperId 新注册用户在数据库表 wd_shopkeeper 中的 id
     * @return 有进行分润操作，返回 1
     * 没有进行分润操作，返回 0
     */

    @Transactional  //5->6->4
    public int shareAnnualFeeBenefit(Integer newShopKeeperId, Integer annualFeeOrderId) {
        Date now = new Date();
        ShopKeeper shopKeeper = shopKeeperRepository.findById(newShopKeeperId);
        //判断该用户是否存在，为了保险起见
        if (shopKeeper != null && shopKeeper.getIsNew() == 1) {
            //设置该店主的状态为不是新招的  set isNew == 0 ==> 未来这个店主再次提交年费的时候不会再进入这个页面
            shopKeeperRepository.updateIsNewById(newShopKeeperId);
            //新注册的店主 level 肯定是 0
            int belong = shopKeeper.getBelong(); //查询新店主的直接店主（推荐人）  //6
            if (belong != 0) {
                //推荐人存在
                //查找上一级直接店主分润
                //查找上一级管理员分润
                //查找上一级经理分润
                float directShopKeeperReward = benefitRepository.findAById(2); //直接店主分润数额  //110
                float firstSupervisorReward = benefitRepository.findBById(2); //直接主管分润数额   //40
                float firstMangerReward = benefitRepository.findCById(2); //直接经理分润数额       //60
                int belongLevel = shopKeeperRepository.findLevelById(belong); //查找店主（推荐人）的 level 等级  //1
                float profitDelta = directShopKeeperReward;  //110
                //判断给予直接上级店主多少奖励
               if (belongLevel == 1){
                    //直接店主是主管，享受两重奖励
                    profitDelta += firstSupervisorReward;  //110+40=150
                } else if (belongLevel == 2) {
                   //直接店主是经理，享受两重奖励
                    profitDelta += firstMangerReward;
                }
                //更新直接上级的总利润
                shopKeeperRepository.addAllProfitById(profitDelta, belong); //+150 6
                //招到直接店主，追踪店主分润
                ShopkeeperProfitTrack shopkeeperProfitTrack = new ShopkeeperProfitTrack(belong, belongLevel,
                        profitDelta, 2, shopKeeper.getNickname(), shopKeeper.getTelephone(), now, annualFeeOrderId);
                shopkeeperProfitTrackRepository.save(shopkeeperProfitTrack);
                int level = belongLevel;  //1
                int tmpLevel;
                int nextBelong = shopKeeperRepository.findBelongById(belong);  //4
                while (level < 2 && nextBelong != 0) {
                    //level >= 2 则，下一级不会再有机会分润
                    //nextBelong == 0 没有上一级
                    tmpLevel = shopKeeperRepository.findLevelById(nextBelong); //下一级等级 level  //2
                    if (tmpLevel > level) {  //2>1
                        //等级更高可以分润
                        if (tmpLevel == 1) {
                            //主管加上主管应有的奖励
                            profitDelta = firstSupervisorReward;
                        } else if (tmpLevel == 2) {
                            //经理加上经理应有的奖励
                            profitDelta = firstMangerReward;  //60
                        }else {
                            profitDelta = 0;
                        }
                        //更新上一级，符合分润条件的主管或者经理总利润
                        shopKeeperRepository.addAllProfitById(profitDelta, nextBelong); //+60
                        //追踪经理或者主管享受新招店主分润
                        shopkeeperProfitTrack = new ShopkeeperProfitTrack(nextBelong, tmpLevel, profitDelta, 3,
                                shopKeeper.getNickname(), shopKeeper.getTelephone(), now, annualFeeOrderId);
                        shopkeeperProfitTrackRepository.save(shopkeeperProfitTrack);
                        level = tmpLevel;
                    }
                    //寻找上一级店主
                    nextBelong = shopKeeperRepository.findBelongById(nextBelong);
                }
                return 1;   //有进行分润操作
            }
        }
        return 0;   //没有进行分润操作
    }


    /**
     * 查看每卖掉一件商品的上级店主，主管，经理的分润系数
     */
    public Benefit findAnnualFeeShareBenefitParam() {
        return benefitRepository.findAnnualFeeBenefitParam();
    }

    /**
     * 查看年费分润系数
     */
    public Benefit findGoodsShareBenefitParam() {
        return benefitRepository.findGoodsShareBenefitParam();
    }

    /**
     * 更新店主年年费分润系数
     * @param benefit 新的店主年费分润系数
     */
    @Transactional
    public void updateAnnualFeeShareBenefitParam(Benefit benefit) {
        //id==2 为年费分润系数
        benefit.setId(2);
        benefitRepository.saveAndFlush(benefit);
    }

    /**
     * 更新商品分润系数
     * @param benefit 新的商品分润系数
     */
    @Transactional
    public void updateGoodsShareBenefitParam(Benefit benefit) {
        //id==1 为商品分润系数
        benefit.setId(1);
        benefitRepository.saveAndFlush(benefit);
    }
}
