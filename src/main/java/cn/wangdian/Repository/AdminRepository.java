package cn.wangdian.Repository;

import cn.wangdian.Model.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by 25065 on 2016/9/15.
 */
public interface AdminRepository extends JpaRepository<Admin,String>,JpaSpecificationExecutor<Admin>{

    @Query("select o from Admin o where o.isDel=0")
    public List<Admin> findAllByIsDel0();

    @Query("select o from Admin o where o.id=:id ")
    public Admin findById(@Param("id")Integer id);

    @Query("select o.username from Admin o where o.username=:username")
    public String checkUsername(@Param("username")String username);

    @Query("select o.username from Admin o where o.id=:id")
    public String findUsernameById(@Param("id")Integer id);

    @Query("select o from Admin o where o.username=:username ")
    public Admin selectByUsername(@Param("username")String username);

    @Modifying
    @Query("update Admin o set o.status=:status where o.id=:id ")
    public void suoJieDing(@Param("id")Integer id,@Param("status")Integer status);

    @Modifying
    @Query("update Admin o set o.isDel=1 where o.id=:id ")
    public void deleteByPrimaryKey(@Param("id")Integer id);

}
