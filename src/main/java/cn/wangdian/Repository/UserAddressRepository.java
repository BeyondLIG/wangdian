package cn.wangdian.Repository;

import cn.wangdian.Model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by 25065 on 2016/9/18.
 */
public interface UserAddressRepository extends JpaRepository<UserAddress,String>,JpaSpecificationExecutor<UserAddress> {

    @Query("select o from UserAddress o where o.isDel=0 and o.userId=:userId")
    public List<UserAddress> findAllByIsDel0(@Param("userId")Integer userId);

    @Query("select o from UserAddress o where o.isDel=0 and o.userId=:userId")
    public List<UserAddress> findAllByIsDel0AndUserId(@Param("userId")Integer userId);

    @Query("select o.userId from UserAddress o where o.id=:id")
    public Integer findUserIdById(@Param("id")Integer id);

    @Query("select o from UserAddress o where o.id=:id")
    public UserAddress findById(@Param("id")Integer id);

    @Modifying
    @Query("update UserAddress o set o.isDel=1 where o.id=:id ")
    public void deleteByPrimaryKey(@Param("id")Integer id);
}
