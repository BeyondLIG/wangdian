package cn.wangdian.Repository;

import cn.wangdian.Model.Shop;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by 25065 on 2016/9/15.
 */
public interface ShopRepository extends JpaRepository<Shop,String>,JpaSpecificationExecutor<Shop> {

    @Query("select o from Shop o where o.isDel=0 and o.status=0")
    public List<Shop> findAllByIsDel0();

    @Query("select o from Shop o where o.isDel=0 and o.status=0 and lower(o.name) like CONCAT('%',:shopName,'%') ")
    public List<Shop> findAllByIsDel0AndShopName(@Param("shopName") String shopName,Pageable pageable);

    @Query("select o from Shop o where o.isDel=0 and o.status=0 and o.isRecommend=1")
    public List<Shop> findAllByIsDel0AndIsRecommend1Limit(Pageable pageable);

    @Query("select distinct (o.shopTypeName) from Shop o where o.isDel=0 and o.status=0")
    public List<String> findAllShopTypeByIsDel0();

    @Query("select distinct (o.shopModel) from Shop o where o.isDel=0 and o.status=0 and o.shopTypeName=:shopType")
    public List<String> findAllShopModelByIsDel0AndType(@Param("shopType") String shopType);

    @Query("select o from Shop o where o.isDel=0 and o.status=0 and o.shopTypeName=:shopType")
    public List<Shop> findAllByIsDel0AndShopTypeAndShopModel(@Param("shopType") String shopType, Pageable pageable);

    @Query("select o.number from Shop o where o.number=:number")
    public String checkNumber(@Param("number") String number);

    @Query("select o.number from Shop o where o.id=:id")
    public String findNumberById(@Param("id") Integer id);

//    @Query("select o from User o where lower(o.username)=lower(:username) ")
//    public User selectByUsername(@Param("username") String username);

    @Query("select o from Shop o where o.id=:id ")
    public Shop findById(@Param("id") Integer id);

    @Modifying
    @Query("update Shop o set o.status=:status where o.id=:id ")
    public void shangXiaJia(@Param("id") Integer id, @Param("status") Integer status);

    @Modifying
    @Query("update Shop o set o.isDel=1 where o.id=:id ")
    public void deleteByPrimaryKey(@Param("id") Integer id);


    //===========NEW===========//
    //查找普通用户价格
    @Query("select o.secondCost from Shop o where o.id=:id ")
    public float findSecondCostById(@Param("id")Integer id);

    //查找vip用户价格
    @Query("select o.vipPrice from Shop o where o.id=:id ")
    public float findVipPriceById(@Param("id")Integer id);

    //查找店主价格
    @Query("select o.shopkeeperPrice from Shop o where o.id=:id ")
    public float findShopkeeperPriceById(@Param("id")Integer id);

    @Query("select o from Shop o where o.status=0 and o.isDel=0 and (lower(o.name) like concat('%',lower(:keyWord),'%') or  lower(o.shopModel) like concat('%',lower(:keyWord),'%') or lower(o.shopTypeName) like CONCAT('%',lower(:keyWord),'%')) ")
    public List<Shop> findAllByIsDel0OrShopNameOrShopTypeOrShopModel(@Param("keyWord")String  keyWord,Pageable pageable);

//    查询用户的所有藏品
//    @Query("select  o from Shop o  left join fetch o.userList u where u.id=:id")
//    public List<Shop> findAllShopByUserId(@Param("id")Integer id);

    //删除用户的商品
//    @Modifying
//    @Query("delete o from Shop o ")

    @Query("select o.firstCost from Shop o where o.id=:id")
    public float findFirstCostById(@Param("id") Integer id);

    @Query("select o.isDel from Shop o where o.id=:id and o.isDel = 0 and o.status = 0")
    public Shop findByIsDel0AndStatus0AndId(@Param("id") Integer id);
}
