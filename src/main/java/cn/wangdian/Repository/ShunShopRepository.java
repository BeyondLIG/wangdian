package cn.wangdian.Repository;

import cn.wangdian.Model.Shop;
import cn.wangdian.Model.ShunShop;
import cn.wangdian.Model.UserAddress;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by 25065 on 2016/9/15.
 */
public interface ShunShopRepository extends JpaRepository<ShunShop,String>,JpaSpecificationExecutor<ShunShop> {

    @Query("select o from ShunShop o where o.isDel=0 and o.status=0")
    public List<ShunShop> findAllByIsDel0Limit(Pageable pageable);

//    @Query("select o from ShunShop o where o.isDel=0 and o.status=0 and o.endTime > :now")
//    public List<ShunShop> findAllByIsDel0Limit(@Param("now") Date now, Pageable pageable);

    @Query("select o.number from ShunShop o where o.number=:number")
    public String checkNumber(@Param("number") String number);

    @Query("select o.number from ShunShop o where o.id=:id")
    public String findNumberById(@Param("id") Integer id);

//    @Query("select o from User o where lower(o.username)=lower(:username) ")
//    public User selectByUsername(@Param("username") String username);

    @Query("select o from ShunShop o where o.id=:id ")
    public ShunShop findById(@Param("id") Integer id);

    @Modifying
    @Query("update ShunShop o set o.status=:status where o.id=:id ")
    public void shangXiaJia(@Param("id") Integer id, @Param("status") Integer status);

    @Modifying
    @Query("update ShunShop o set o.isDel=1 where o.id=:id ")
    public void deleteByPrimaryKey(@Param("id") Integer id);

    @Query("select o.thirdCost from ShunShop o where o.id=:id")
    public Float findThirdCostById(@Param("id") Integer id);

    @Query("select o.firstCost from ShunShop o where o.id=:id")
    public Float findFirstCostById(@Param("id") Integer id);

    @Query("select o from ShunShop o where o.id = :id and o.isDel=0 and o.status = 0 and o.endTime > :now")
    public ShunShop findByIsDel0AndStatus0AndNoEndAndId(@Param("now") Date now, @Param("id") Integer id);
}
