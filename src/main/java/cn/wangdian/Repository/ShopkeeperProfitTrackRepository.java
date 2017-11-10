package cn.wangdian.Repository;

import cn.wangdian.Model.ShopkeeperProfitTrack;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 追踪店主收益来源的 repository
 */
public interface ShopkeeperProfitTrackRepository extends JpaRepository<ShopkeeperProfitTrack, Integer>, JpaSpecificationExecutor<ShopkeeperProfitTrack> {

    @Query("select o from ShopkeeperProfitTrack o where o.shopkeeperId=:shopkeeperId")
    List<ShopkeeperProfitTrack> findByShopkeeperId(@Param("shopkeeperId") Integer shopkeeperId, Pageable pageable);

    /**
     * 查找店主的商品分润追踪
     * @param shopkeeperId
     * @param pageable
     * @return
     */
    @Query("select o from ShopkeeperProfitTrack o where o.shopkeeperId=:shopkeeperId and o.type in (0, 1)")
    List<ShopkeeperProfitTrack> findByShopkeeperIdWithGoods(@Param("shopkeeperId") Integer shopkeeperId, Pageable pageable);

    /**
     * 查找店主的年费分润追踪
     * @param shopkeeperId
     * @param pageable
     * @return
     */
    @Query("select o from ShopkeeperProfitTrack o where o.shopkeeperId=:shopkeeperId and o.type in (2, 3)")
    List<ShopkeeperProfitTrack> findByShopkeeperIdWithAnnualFee(@Param("shopkeeperId") Integer shopkeeperId, Pageable pageable);

    /**
     * 判断该订单号是否是属于该店主的，防止店主撞库
     * @param shopkeeperId
     * @param ordersId
     * @return
     */
    @Query("select o from ShopkeeperProfitTrack o where o.shopkeeperId=:shopkeeperId and o.orderId=:ordersId")
    List<ShopkeeperProfitTrack> isShopkeeperOwnsOrdersId(@Param("shopkeeperId") Integer shopkeeperId, @Param("ordersId") Integer ordersId);

    @Query("select count(o) from ShopkeeperProfitTrack o where o.type in (0, 1) and o.shopkeeperId=:shopkeeperId")
    int countAllType0Or1(@Param("shopkeeperId") Integer shopkeeperId);

    @Query("select count(o) from ShopkeeperProfitTrack o where o.type in (2, 3) and o.shopkeeperId=:shopkeeperId")
    int countAllType2Or3(@Param("shopkeeperId") Integer shopkeeperId);
}
