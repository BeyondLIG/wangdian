package cn.wangdian.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by 25065 on 2016/9/20.
 */
@Entity
@Table(name = "wd_useraddress")
public class UserAddress {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;//收货人

    private String phone;//手机号

    private String zipcode;//邮政编码

    private String province;//省份

    private String city;//城市

    private String area;//地区

    private String town;//街道

    private String adddetail;//详细地址

    private Integer userId;//用户名userId

    private Integer isDel;//0存在，1删除

    private Integer status;//0 默认，1不默认

    public UserAddress() {
    }

    public UserAddress(String name, String phone, String zipcode, String province, String city, String area, String town, String adddetail) {
        this.name = name;
        this.phone = phone;
        this.zipcode = zipcode;
        this.province = province;
        this.city = city;
        this.area = area;
        this.town = town;
        this.adddetail = adddetail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getAdddetail() {
        return adddetail;
    }

    public void setAdddetail(String adddetail) {
        this.adddetail = adddetail;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
