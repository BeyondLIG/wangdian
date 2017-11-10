package cn.wangdian.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.crypto.Data;
import java.util.Date;

/**
 * Created by 25065 on 2016/9/15.
 */
@Entity
@Table(name = "wd_adminlogin")
public class AdminLogin {

    @Id
    @GeneratedValue
    private Integer id;

    private Date loginTime;

    private Integer adminId;

    public AdminLogin() {
    }

    public AdminLogin(Date loginTime, Integer adminId) {
        this.loginTime = loginTime;
        this.adminId = adminId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }
}
