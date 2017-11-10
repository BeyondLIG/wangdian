package cn.wangdian.Repository;

import cn.wangdian.Model.ShopKeeper;
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
public interface ShopKeeperRepository extends JpaRepository<ShopKeeper, Integer>, JpaSpecificationExecutor<ShopKeeper> {

    @Query("select o.username from ShopKeeper o where o.username=:username")
    public String checkUsername(@Param("username") String username);

    @Query("select o.username from ShopKeeper o where o.id=:id")
    public String findUsernameById(@Param("id") Integer id);

    @Query("select o from ShopKeeper o where o.username=:username ")
    public ShopKeeper selectByUsername(@Param("username") String username);

    @Query("select o from ShopKeeper o where o.id=:id ")
    public ShopKeeper findById(@Param("id") Integer id);

    @Query("select o.webUrl from ShopKeeper o where o.webUrl=:secondDomain ")
    public String findWebUrlBySecondDomain(@Param("secondDomain") String secondDomain);

    @Modifying
    @Query("update ShopKeeper o set o.status=:status where o.id=:id ")
    public void suoJieDing(@Param("id") Integer id, @Param("status") Integer status);

    @Modifying
    @Query("update ShopKeeper o set o.allProfit=:allProfit where o.id=:id ")
    public void updateAllProfitById(@Param("allProfit") float allProfit, @Param("id") Integer id);

    @Modifying
    @Query("update ShopKeeper o set o.isDel=1 where o.id=:id ")
    public void deleteByPrimaryKey(@Param("id") Integer id);

    @Modifying
    @Query("update ShopKeeper o set o.message=:message where o.id=:id ")
    public void updateMessageByPrimaryKey(@Param("message") String message, @Param("id") Integer id);

    @Modifying
    @Query("update ShopKeeper o set o.message=:message,o.yiTiXian=:money where o.id=:id ")
    public void updateMessageAndYiTiXianById(@Param("message") String message, @Param("money") float money, @Param("id") Integer id);

    @Modifying
    @Query("update ShopKeeper o set o.message=:message where o.id=:id ")
    public void updateMessageById(@Param("message") String message, @Param("id") Integer id);


    //-----------------NEW-----------------//

    //删除店主时，更改belong属性=0
    @Modifying
    @Query("update ShopKeeper o set o.belong=0 where o.id=:id")
    public void updateBelongById(@Param("id") Integer id);

    //查询店主的直接用户数量
    @Query("select o.directUserNumber from ShopKeeper o where o.id=:id ")
    public Integer findDirectUserNumberById(@Param("id") Integer id);

    //查询店主的所有用户数量
    @Query("select o.allUserNumber from ShopKeeper o where o.id=:id ")
    public Integer findAllUserNumberById(@Param("id") Integer id);

    //查询店主的直接店主数量
    @Query("select o.directShopkeeperNumber from ShopKeeper o where  o.id=:id")
    public Integer findDirectShopkeeperNumberById(@Param("id") Integer id);

    //查询店主的所有店主数量
    @Query("select o.allShopkeeperNumber from ShopKeeper o where o.id=:id")
    public Integer findAllShopkeeperNumberById(@Param("id") Integer id);

    //更改店主的直接店主数量
    @Modifying
    @Query("update ShopKeeper o set o.directShopkeeperNumber=:directShopkeeperNumber where o.id=:id")
    public void updateDirectShopkeeperNumberById(@Param("directShopkeeperNumber") Integer directShopkeeperNumber, @Param("id") Integer id);

    //更改店主的所有店主数量
    @Modifying
    @Query("update ShopKeeper o set o.allShopkeeperNumber=:allShopkeeperNumber where o.id=:id")
    public void updateAllShopkeeperNumberById(@Param("allShopkeeperNumber") Integer allShopkeeperNumber, @Param("id") Integer id);

    //当数量满足条件时升级店主
    @Modifying
    @Query("update ShopKeeper o set o.level=:level where o.id=:id ")
    public void updateLevelById(@Param("level") Integer level, @Param("id") Integer id);

    //每次店主直接招到用户时，调用更改店主直接用户数量
    @Modifying
    @Query("update ShopKeeper o set o.directUserNumber=:direct where o.id=:id ")
    public void updateDirectUserNumberById(@Param("direct") Integer direct, @Param("id") Integer id);

    //每次店主用户数量改变时，调用
    @Modifying
    @Query("update ShopKeeper o set o.allUserNumber=:allUserNumber where o.id=:id ")
    public void updateAllUserNumberById(@Param("allUserNumber") Integer allNumber, @Param("id") Integer id);

    //通过店主的id查找店主的belong
    @Query("select o.belong from ShopKeeper o where o.id=:id ")
    public Integer findBelongById(@Param("id") Integer id);

    //查询店主的id号查找店主的等级
    @Query("select o.level from ShopKeeper o where o.id=:id ")
    public Integer findLevelById(@Param("id") Integer id);

    //通过 username 查询 id
    @Query("select o.id from ShopKeeper o where o.username=:username")
    public Integer findIdByUsername(@Param("username") String username);

    //通过店主id号查询店主的注册时间
    @Query("select o.registerTime from ShopKeeper o where o.id=:id")
    public Date findRegisterTimeById(@Param("id") Integer id);

    //过期时间
    @Query("select  o.deathTime from ShopKeeper o where o.id=:id")
    public Date findDeathTimeById(@Param("id") Integer id);

    //更改到期时间
    @Modifying
    @Query("update ShopKeeper o set o.deathTime=:deathTime where o.id=:id")
    public void updateDeathTimeById(@Param("deathTime") Date deathTime, @Param("id") Integer id);

    //获取店主的状态
    @Query("select o.status from ShopKeeper o where o.id=:id")
    public Integer findStatusById(@Param("id") Integer id);

    @Query("select o.allProfit from ShopKeeper o where o.id=:id")
    public float findAllProfitById(@Param("id") Integer id);

    @Query("select o from ShopKeeper o where o.belong=:belong")
    public List<ShopKeeper> findAllDirectShopKeeperById(@Param("belong") Integer belong);

    @Modifying
    @Query("update ShopKeeper o set o.directShopkeeperNumber=0, o.allShopkeeperNumber=0, o.directUserNumber=0, o.allUserNumber=0 where o.id=:id")
    public void updateNumber0ById(@Param("id") Integer id);

    @Query("select o from ShopKeeper o where o.username=:username")
    public ShopKeeper findByUsername(@Param("username") String username);

    @Modifying
    @Query("update ShopKeeper o set o.isNew=0 where o.id=:id ")
    public void updateIsNewById(@Param("id") Integer id);

    @Modifying
    @Query("update ShopKeeper o set o.allProfit=o.allProfit + :delta where o.id=:id")
    public void addAllProfitById(@Param("delta") Float delta, @Param("id") Integer id);

    @Query("select o from ShopKeeper o where o.telephone=:telephone")
    public ShopKeeper findByTelephone(@Param("telephone") String telephone);

    @Modifying
    @Query("update ShopKeeper o set o.password=:password where o.telephone=:telephone")
    public void updatePasswordByTelephone(@Param("password") String password, @Param("telephone") String telephone);

    @Query("select o from ShopKeeper o where o.level=:level")
    public List<ShopKeeper> findAllByLevel(@Param("level") Integer level, Pageable pageable);

    @Query("select o.level from ShopKeeper o where o.telephone=:telephone")
    public Integer findLevelByTelephone(@Param("telephone") String telephone);

    @Modifying
    @Query("update ShopKeeper o set o.telephone=:newTelephone where o.username=:username")
    public void updateTelephoneUsername(@Param("username") String username, @Param("newTelephone") String newTelephone);

    @Query("select o.allProfit from ShopKeeper o where o.username=:shopKeeper")
    public float findAllProfitByShopKeeper(@Param("shopKeeper") String shopKeeper);

    @Query("select o.username from ShopKeeper o where o.id=:belong")
    public String findShopKeeperNameByBelong(@Param("belong") Integer belong);

    @Modifying
    @Query("update ShopKeeper o set o.directUserNumber=o.directUserNumber-1 where o.id=:id")
    public void decreaseDirectUserNumberById(@Param("id") Integer id);

    @Modifying
    @Query("update ShopKeeper o set o.allUserNumber=o.allUserNumber-1 where o.id=:id")
    public void decreaseAllUserNumberById(@Param("id") Integer id);
}


