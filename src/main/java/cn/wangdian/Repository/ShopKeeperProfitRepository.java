package cn.wangdian.Repository;

import cn.wangdian.Model.ShopKeeperProfit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by 25065 on 2016/9/18.
 */
public interface ShopKeeperProfitRepository extends JpaRepository<ShopKeeperProfit,String>,JpaSpecificationExecutor<ShopKeeperProfit> {


    @Modifying
    @Query("update ShopKeeperProfit o set o.isDel=1 where o.id=:id ")
    public void deleteByPrimaryKey(@Param("id") Integer id);

    @Modifying
    @Query("update ShopKeeperProfit o set o.status=1 where o.id=:id ")
    public void updateStatusOnOkByPrimaryKey(@Param("id") Integer id);

    @Modifying
    @Query("update ShopKeeperProfit o set o.status=2 where o.id=:id ")
    public void updateStatusOnRejectByPrimaryKey(@Param("id") Integer id);

    @Query("select o from ShopKeeperProfit o where o.id=:id")
    public ShopKeeperProfit findById(@Param("id") Integer id);
}
