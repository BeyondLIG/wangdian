package cn.wangdian.Repository;

import cn.wangdian.Model.Orders;
import cn.wangdian.Model.ShopCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by 25065 on 2016/9/15.
 */
public interface ShopCartRepository extends JpaRepository<ShopCart,String>,JpaSpecificationExecutor<ShopCart> {

//    @Query("select distinct(o.shopType) from Orders o ")
//    public List<String> countShopType(Specification<Orders> ordersSpecification);
//
//    @Query("select o.number from Orders o where lower(o.id)=lower(:id)")
//    public String findNumberById(@Param("id") Integer id);

//    @Query("select o from User o where lower(o.username)=lower(:username) ")
//    public User selectByUsername(@Param("username") String username);

    @Query("select o from ShopCart o where o.id=:id ")
    public ShopCart findById(@Param("id") Integer id);

    @Query("select o.allSecondCost from ShopCart o where o.id=:id ")
    public float findAllSecondCostById(@Param("id") Integer id);

    @Query("select o.allProfits from ShopCart o where o.id=:id ")
    public float findAllProfitsById(@Param("id") Integer id);

    @Modifying
    @Query("update ShopCart o set o.isDel=1 where o.id=:id ")
    public void deleteByPrimaryKey(@Param("id") Integer id);

//    @Modifying
//    @Query("update ShopCart o set o.orderId=:orderId where lower(o.id)=lower(:shopCartId) ")
//    public void updateOrderIdById(@Param("orderId") Integer orderId,@Param("shopCartId") Integer shopCartId);

    @Modifying
    @Query("update ShopCart o set o.status=:status where o.id=:shopCartId ")
    public void updateStatusById(@Param("status") Integer status,@Param("shopCartId") Integer shopCartId);

    @Query("select o from ShopCart o where o.userId=:userId and o.status=1")
    public List<ShopCart> findAllByIsDel0AndUserIdAndStatus1(@Param("userId") Integer userId);

//    @Query("select o from ShopCart o where o.orderId=:orderId")
//    public List<ShopCart> findAllByIsDel0AndOrderId(@Param("orderId") Integer orderId);

    @Query("select o.userId from ShopCart o where o.id=:id")
    public Integer getUserIdByShopCartId(@Param("id")Integer id);
}
