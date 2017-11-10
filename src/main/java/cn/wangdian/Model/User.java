package cn.wangdian.Model;

import sun.misc.BASE64Decoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 为了实现店主也能够买东西的功能，我们把店主　ShopKeeper 的信息保存一份到　User　中
 * 通过　vip 字段来标示　User 是普通用户，还是vip 用户，还是店主
 * vip==0 普通用户
 * vip==1 vip 用户（隶属于某个店主）
 * vip==2 店主
 */
@Entity
@Table(name = "wd_user")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    private String nickname;//昵称

    private String username;//真实的用户名

    private String email;//邮箱

    private String telephone;//手机号

    private String password;//密码

    private Integer status;//状态 ，0正常，1锁定

    private Integer isDel;//0存在，1删除

    private Integer vip;//0普通用户，1vip用户，2店主

    private float pay;//花费金额

    @ManyToOne(fetch=FetchType.LAZY,cascade = {CascadeType.ALL})
    @JoinColumn(name="shopkeeper_id")         //name=关联表的名称+“_”+ 关联表主键的字段名
    private ShopKeeper shopkeeper; //用户关联的店主


    //--------------------------NEW--------------------//
    //商品收藏

//    @ManyToMany(fetch =FetchType.LAZY,cascade = CascadeType.ALL)
//    @JoinTable(name="wd_shop_wd_user", joinColumns = @JoinColumn(name="user_id",referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "shop_id",referencedColumnName = "id"))
//    private List<Shop> shopList=new ArrayList<Shop>();
   // 地址

    public User() {
    }

    public User(String nickname, String username, String email, String telephone, String password, Integer status, Integer isDel,ShopKeeper shopkeeper,float pay) {
        this.nickname = nickname;
        this.username = username;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
        this.status = status;
        this.isDel = isDel;
        this.pay=pay;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public ShopKeeper getShopkeeper() {
        return shopkeeper;
    }

    public void setShopkeeper(ShopKeeper shopKeeper) {
        this.shopkeeper = shopKeeper;
    }

    public Integer getVip() {
        return vip;
    }

    public void setVip(Integer vip) {
        this.vip = vip;
    }


    public float getPay() {
        return pay;
    }

    public void setPay(float pay) {
        this.pay = pay;
    }
}