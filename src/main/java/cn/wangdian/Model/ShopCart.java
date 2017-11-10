package cn.wangdian.Model;

import javax.persistence.*;

/**
 * 购买单间商品，可以购买多件
 */
@Entity
@Table(name = "wd_shopcart")
public class ShopCart {

    @Id
    @GeneratedValue
    private Integer id;

    private String shopName;//商品名称

    private String shopType;//商品类型

    private String shopModel;//商品型号

    private String shopNumber;//商品编号

    private float firstCost;//单件成本价

    private float allFirstCost;//总成本价

    private float secondCost;//单件销售价

    private float allSecondCost;//总销售价

    private float profits;//单件利润价

    private float allProfits;//总利润价

    private Integer count;//购买数量

    private String detail;//详细信息

    private String photo;//订单图片

    private Integer isDel;//0存在，1删除

    private String type;//shop为正常商品，shunShop为瞬杀商品

    private Integer shopId;//商品id

    private Integer userId;//普通用户id

    private Integer status;//0未放进购物车，1放进购物车，2已被下订

//    private Integer orderId=0;//0正常，其他已下订

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    @JoinColumn(name = "order_id")
    private Orders orders;

    public ShopCart() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public String getShopModel() {
        return shopModel;
    }

    public void setShopModel(String shopModel) {
        this.shopModel = shopModel;
    }

    public String getShopNumber() {
        return shopNumber;
    }

    public void setShopNumber(String shopNumber) {
        this.shopNumber = shopNumber;
    }

    public float getFirstCost() {
        return firstCost;
    }

    public void setFirstCost(float firstCost) {
        this.firstCost = firstCost;
    }

    public float getAllFirstCost() {
        return allFirstCost;
    }

    public void setAllFirstCost(float allFirstCost) {
        this.allFirstCost = allFirstCost;
    }

    public float getSecondCost() {
        return secondCost;
    }

    public void setSecondCost(float secondCost) {
        this.secondCost = secondCost;
    }

    public float getAllSecondCost() {
        return allSecondCost;
    }

    public void setAllSecondCost(float allSecondCost) {
        this.allSecondCost = allSecondCost;
    }

    public float getProfits() {
        return profits;
    }

    public void setProfits(float profits) {
        this.profits = profits;
    }

    public float getAllProfits() {
        return allProfits;
    }

    public void setAllProfits(float allProfits) {
        this.allProfits = allProfits;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

//    public Integer getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(Integer orderId) {
//        this.orderId = orderId;
//    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
}
