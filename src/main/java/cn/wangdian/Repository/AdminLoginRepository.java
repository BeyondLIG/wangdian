package cn.wangdian.Repository;

import cn.wangdian.Model.AdminLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

/**
 * Created by 25065 on 2016/9/15.
 */
public interface AdminLoginRepository extends JpaRepository<AdminLogin,String>,JpaSpecificationExecutor<AdminLogin> {

    @Query("select max(o.loginTime) from AdminLogin o where o.adminId=:adminId ")
    public Date findLastLoginTimeByAdminId(@Param("adminId")Integer adminId);

    @Query("select count(o.id) from AdminLogin o where o.adminId=:adminId")
    public Integer findCountByAdminId(@Param("adminId")Integer adminId);
}
