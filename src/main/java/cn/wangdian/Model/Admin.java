package cn.wangdian.Model;

import sun.misc.BASE64Decoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 25065 on 2016/9/14.
 */
@Entity
@Table(name = "wd_admin")
public class Admin {

    @Id
    @GeneratedValue
    private Integer id;

    private String nickname;//昵称

    private String username;//用户名

    private String password;//密码

    private Integer status;//状态 0正常，1锁定

    private Integer isDel;//0存在，1删除

    private Date loginTime;

    private Integer loginCount;

    public Admin() {
    }

    public Admin(String nickname, String username, String password, Integer status, Integer isDel) {
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.status = status;
        this.isDel = isDel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String decoderPassword() throws Exception{
        return new String(new BASE64Decoder().decodeBuffer(password));
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String loginTimeToString(){
        if (loginTime!=null){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.format(loginTime);
        }else {
            return null;
        }

    }

    public Integer getLoginCount() {
        if (loginCount==null){
            return 0;
        }else {
            return loginCount;
        }
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }
}
