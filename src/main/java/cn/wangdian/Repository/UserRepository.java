package cn.wangdian.Repository;

import cn.wangdian.Model.ShopKeeper;
import cn.wangdian.Model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * TODO 去掉不部分没用的 lower()
 */
public interface UserRepository extends JpaRepository<User,Integer>,JpaSpecificationExecutor<User> {

    @Query("select o.username from User o where o.username=:username")
    public String checkUsername(@Param("username") String username);

    @Query("select distinct (o.telephone) from User o where o.telephone=:telephone")
    public String checkTelePhone(@Param("telephone") String telephone);

    @Query("select distinct (o.email) from User o where o.email=:email")
    public String checkEmail(@Param("email") String email);

    @Query("select o.username from User o where o.id=:id")
    public String findUsernameById(@Param("id") Integer id);

    @Query("select o from User o where o.username=:username ")
    public User selectByUsername(@Param("username") String username);

    @Query("select o from User o where o.email=:email")
    public User selectByEmail(@Param("email") String email);

    @Query("select o from User o where o.telephone=:telephone ")
    public User selectByTelephone(@Param("telephone") String telephone);

    @Query("select o.password from User o where o.email=:email")
    public String selectPasswordByEmail(@Param("email") String email);

    @Query("select o from User o where o.id=:id")
    public User findById(@Param("id") Integer id);

    @Query("select o.password from User o where o.id=:id ")
    public String selectPasswordById(@Param("id") Integer id);

    @Modifying
    @Query("update User o set o.password=:password where o.email=:email ")
    public void updatePasswordByEmail(@Param("email") String email, @Param("password") String password);

    @Modifying
    @Query("update User o set o.password=:password where o.id=:id ")
    public void updatePasswordById(@Param("password") String password, @Param("id") Integer id);

    @Modifying
    @Query("update User o set o.nickname=:nickname where o.id=:id ")
    public void updateNicknameById(@Param("nickname") String nickname, @Param("id") Integer id);

    @Modifying
    @Query("update User o set o.email=:email where o.id=:id ")
    public void updateEmailById(@Param("email") String email, @Param("id") Integer id);

    @Modifying
    @Query("update User o set o.telephone=:telephone where o.id=:id")
    public void updateTelephoneById(@Param("telephone") String telephone, @Param("id") Integer id);

    @Modifying
    @Query("update User o set o.status=:status where o.id=:id ")
    public void suoJieDing(@Param("id") Integer id, @Param("status") Integer status);

    @Modifying
    @Query("update User o set o.isDel=1 where o.id=:id")
    public void deleteByPrimaryKey(@Param("id") Integer id);

    //=================NEW=============//
    @Modifying
    @Query("update  User o set o.vip=0 where o.id=:id")
    public void updateUserVipById(@Param("id")Integer id);

    @Query("select o.pay from User o where o.id=:id")
    public float getPayById(@Param("id")Integer id);

    @Modifying
    @Query("update User o set o.pay=:pay where o.id=:id")
    public void updatePayById(@Param("pay")float pay,@Param("id")Integer id);

    @Query("select o from User o where o.isDel=0 and o.shopkeeper.id=:id")
    public List<User> getAllUserByShopkeeperId(@Param("id")Integer id);

    @Modifying
    @Query("update User o set o.password=:password where o.telephone=:telephone")
    public void updatePasswordByByTelephone(@Param("password") String password, @Param("telephone") String telephone);

    @Modifying
    @Query("update User o set o.status=:status where o.username=:username")
    public void updateStatusByUsername(@Param("username") String username, @Param("status") Integer status);

    @Modifying
    @Query("update User o set o.isDel=1 where o.username=:username")
    public void deleteByUsername(@Param("username") String username);

    @Query("select o from User o where o.telephone=:telephone")
    public User findByTelephone(@Param("telephone") String telephone);
}
