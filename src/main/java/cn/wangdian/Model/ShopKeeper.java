package cn.wangdian.Model;

import sun.misc.BASE64Decoder;

import javax.persistence.*;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 25065 on 2016/9/14.
 */
@Entity
@Table(name = "wd_shopkeeper")
public class ShopKeeper {

    @Id
    @GeneratedValue
    private Integer id;

    private String nickname;//真实姓名，由于历史原因，这里使用的是 nickname，后期需要改正为 realName

    private String username;//真实的用户名

    private String email;//电子邮件

    private String telephone;//手机号

    private String password;//密码

    private String zhifubao;//支付宝

    private String webUrl;//网址

    private float allProfit;//截止目前为止的总收益

    private float yiTiXian;//已提现

    private String message;//提现消息

    //或者是超级管理员因为某种原因手动锁定该用户
    private Integer status;//状态 ，0正常，1锁定 店主欠交年费的时候会被处于锁定状态

    private Integer isDel;//0存在，1删除

    private Integer level;//店主等级 0-店主 1-主管 2-经理

    private Integer belong;//店主受谁来管 belong=对方id号

    //===========NEW===========//
    private Integer directUserNumber;//记录店主的直接用户数量
    private Integer allUserNumber;//记录店主的所有用户数量
    private Integer directShopkeeperNumber;//记录店主的直接店主数量
    private Integer allShopkeeperNumber;//记录店主所有店主数量
    private Integer isNew; //判断当前店主是否是新招的，在店主第一次交年费之后不会再更新该字段 0==>不是新招的  1==>新招的

    @Column(name="registerTime")
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date registerTime;//店主注册时间

    @Column(name="deathTime")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date deathTime;//过期时间

//    //商品收藏
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "shopkeeper")
//    private List<Shop> shopList=new ArrayList<Shop>();

    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL},mappedBy = "shopkeeper")
    private List<User> userList=new ArrayList<User>();



    public ShopKeeper() {
    }

    public ShopKeeper(String nickname, String username, String email, String telephone, String password, String zhifubao, Integer status, Integer isDel,List<User> userList,Integer level,Integer belong) {
        this.nickname = nickname;
        this.username = username;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
        this.zhifubao = zhifubao;
        this.status = status;
        this.isDel = isDel;
        this.userList=userList;
        this.level=level;
        this.belong=belong;
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

    public String decoderPassword() throws Exception{
        return new String(new BASE64Decoder().decodeBuffer(password));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getZhifubao() {
        return zhifubao;
    }

    public void setZhifubao(String zhifubao) {
        this.zhifubao = zhifubao;
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

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

//    public String webUrl(){
//        return webUrl.substring(0,webUrl.length()-1);
//    }

    public float getAllProfit() {
        return allProfit;
    }

    public void setAllProfit(float allProfit) {
        this.allProfit = allProfit;
    }

    public float getYiTiXian() {
        return yiTiXian;
    }

    public void setYiTiXian(float yiTiXian) {
        this.yiTiXian = yiTiXian;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public void addUserList(User user){
        user.setShopkeeper(this);
        this.userList.add(user);
    }

    public Integer getBelong() {
        return belong;
    }

    public void setBelong(Integer belong) {
        this.belong = belong;
    }

    //=========NEW===========//


//    public List<Shop> getShopList() {
//        return shopList;
//    }
//
//    public void setShopList(List<Shop> shopList) {
//        this.shopList = shopList;
//    }

    public Integer getDirectUserNumber() {
        return directUserNumber;
    }

    public void setDirectUserNumber(Integer directUserNumber) {
        this.directUserNumber = directUserNumber;
    }

    public Integer getAllUserNumber() {
        return allUserNumber;
    }

    public void setAllUserNumber(Integer allUserNumber) {
        this.allUserNumber = allUserNumber;
    }

    public Integer getDirectShopkeeperNumber() {
        return directShopkeeperNumber;
    }

    public void setDirectShopkeeperNumber(Integer directShopkeeperNumber) {
        this.directShopkeeperNumber = directShopkeeperNumber;
    }

    public Integer getAllShopkeeperNumber() {
        return allShopkeeperNumber;
    }

    public void setAllShopkeeperNumber(Integer allShopkeeperNumber) {
        this.allShopkeeperNumber = allShopkeeperNumber;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getDeathTime() {
        return deathTime;
    }

    public void setDeathTime(Date deathTime) {
        this.deathTime = deathTime;
    }

    //==================================//

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }
}


