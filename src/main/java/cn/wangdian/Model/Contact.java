package cn.wangdian.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商家的联系方式
 */
@Entity
@Table(name = "wd_contact")
public class Contact {

    @Id
    @GeneratedValue
    private Integer id;
    private String weiXin;//微信号
    private String qq;//qq号
    private String telePhone;//电话

    public Contact() {
    }

    public Contact(String weiXin, String qq, String telePhone) {
        this.weiXin = weiXin;
        this.qq = qq;
        this.telePhone = telePhone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWeiXin() {
        return weiXin;
    }

    public void setWeiXin(String weiXin) {
        this.weiXin = weiXin;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone;
    }
}
