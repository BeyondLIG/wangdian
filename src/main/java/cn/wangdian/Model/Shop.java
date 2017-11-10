package cn.wangdian.Model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商品信息模型类
 */
@Entity
@Table(name = "wd_shop")
public class Shop {

    @Id
    @GeneratedValue
    private Integer id;

    private String number;//编号

    private String name;//商品名称

    //因为删除一条商品或者删除一条商品的分类信息时候，不需要进行任何的级联，所以这里不需要 cascade
    //商品的分类信息
//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
//    @JoinColumn(name = "shopType_id")
//    private ShopType shopType;

    private String shopModel;//商品型号

    private Float firstCost;//成本价

    private Float secondCost;//销售价  普通用户价格

    private Date inTime;//入库时间

    private Integer status;//0上架，1下架

    private Integer isDel;//0存在，1删除

    private String shopDescribe;//描述

    private String firstPhoto;//首页图片

    private Integer isRecommend;//是否为推荐商品 0为否，1为是

    @Column(name = "shopType") //历史遗留原因
    private String shopTypeName;

    //============NEW============//
    private Float vipPrice;//vip用户价格

    private Float shopkeeperPrice;//店主价格


//    @NotFound(action = NotFoundAction.IGNORE)
//    @ManyToMany(fetch=FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
//    private List<User> userList=new ArrayList<User>();

    private Float pastPrice; //过去的价格，用于显示一个横线表示删除的东西，装饰用

//    @NotFound(action = NotFoundAction.IGNORE)
//    @ManyToMany(fetch=FetchType.LAZY,cascade = {CascadeType.ALL}, mappedBy = "shopList")
//    private List<User> userList = new ArrayList<>();

    public Shop() {
    }

    public Shop(String number, String name, String shopType, String shopModel, Integer status, Integer isDel, String shopDescribe) {
        this.number = number;
        this.name = name;
        this.shopTypeName = shopType;
        this.shopModel = shopModel;
        this.status = status;
        this.isDel = isDel;
        this.shopDescribe = shopDescribe;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopModel() {
        return shopModel;
    }

    public void setShopModel(String shopModel) {
        this.shopModel = shopModel;
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

    public String getShopDescribe() {
        return shopDescribe;
    }

    public void setShopDescribe(String shopDescribe) {
        this.shopDescribe = shopDescribe;
    }

    public Float getFirstCost() {
        return firstCost;
    }

    public void setFirstCost(Float firstCost) {
        this.firstCost = firstCost;
    }

    public Float getSecondCost() {
        return secondCost;
    }

    public void setSecondCost(Float secondCost) {
        this.secondCost = secondCost;
    }

    public String getFirstPhoto() {
        return firstPhoto;
    }

    public void setFirstPhoto(String firstPhoto) {
        this.firstPhoto = firstPhoto;
    }

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }


    //=================NEW=======================//

    public Float getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(Float vipPrice) {
        this.vipPrice = vipPrice;
    }

    public Float getShopkeeperPrice() {
        return shopkeeperPrice;
    }

    public void setShopkeeperPrice(Float shopkeeperPrice) {
        this.shopkeeperPrice = shopkeeperPrice;
    }



    //=============================//

    public String inTimeToString(){
        if (inTime!=null){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.format(inTime);
        }else {
            return null;
        }

    }


    public Float getPastPrice() {
        return pastPrice;
    }

    public void setPastPrice(Float passPrice) {
        this.pastPrice = passPrice;
    }

    public String getShopTypeName() {
        return shopTypeName;
    }

    public void setShopTypeName(String shopTypeName) {
        this.shopTypeName = shopTypeName;
    }
}
