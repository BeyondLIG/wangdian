package cn.wangdian.Service;

import cn.wangdian.Model.Orders;
import cn.wangdian.Model.ShopkeeperProfitTrack;
import cn.wangdian.Repository.ShopkeeperProfitTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 追踪店主收益来源的 Service
 */
@Service
public class ShopkeeperProfitTrackService {
    @Autowired
    private ShopkeeperProfitTrackRepository shopkeeperProfitTrackRepository;

    /**
     * 持久化一个 ShopkeeperProfitTrack
     * @param shopkeeperProfitTrack
     */
    @Transactional
    public void save(ShopkeeperProfitTrack shopkeeperProfitTrack) {
        shopkeeperProfitTrackRepository.save(shopkeeperProfitTrack);
    }

    /**
     * 通过店主的 id 来搜索该店主的所有收益来源
     * @param shopkeeperId
     * @return
     */
    public List<ShopkeeperProfitTrack> findByShopkeeperId(Integer shopkeeperId, int page, int size) {
        Sort sort = new Sort(Sort.Direction.DESC, "time");
        Pageable pageable = new PageRequest(page, size, sort);
        return shopkeeperProfitTrackRepository.findByShopkeeperId(shopkeeperId, pageable);
    }

    /**
     * 查找店主的下属购买商品的分润
     * @param shopkeeperId
     * @param page
     * @param size
     * @return
     */
    public List<ShopkeeperProfitTrack> findByShopkeeperIdWithGoods(Integer shopkeeperId, int page, int size) {
        Sort sort = new Sort(Sort.Direction.DESC, "time");
        Pageable pageable = new PageRequest(page, size, sort);
        return shopkeeperProfitTrackRepository.findByShopkeeperIdWithGoods(shopkeeperId, pageable);
    }

    /**
     * 查找店主的年费分润收益来源
     * @param shopkeeperId
     * @param page
     * @param size
     * @return
     */
    public List<ShopkeeperProfitTrack> findByShopkeeperIdWithAnnualFee(Integer shopkeeperId, int page, int size) {
        Sort sort = new Sort(Sort.Direction.DESC, "time");
        Pageable pageable = new PageRequest(page, size, sort);
        return shopkeeperProfitTrackRepository.findByShopkeeperIdWithAnnualFee(shopkeeperId, pageable);
    }

    /**
     * 通过订单 id 和店主 id寻找订单详情
     */
    public boolean isShopkeeperOwnsOrdersId(Integer shopkeeperId, Integer ordersId) {
        return shopkeeperProfitTrackRepository.isShopkeeperOwnsOrdersId(shopkeeperId, ordersId).size() > 0;
    }

    /**
     * 查看对应id 的店主的记录总数目
     * @param shopkeeperId
     * @return
     */
    public int countAllType0Or1(Integer shopkeeperId) {
        return shopkeeperProfitTrackRepository.countAllType0Or1(shopkeeperId);
    }

    /**
     * 查看对应id 的店主的记录总数目
     * @param shopkeeperId
     * @return
     */
    public int countAllType2Or3(Integer shopkeeperId) {
        return shopkeeperProfitTrackRepository.countAllType2Or3(shopkeeperId);
    }
}
