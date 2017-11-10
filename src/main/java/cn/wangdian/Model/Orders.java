package cn.wangdian.Model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 这是总的订单，一个订单包含多个 ShopCart 购物车属性的内容
 */
@Entity
@Table(name = "wd_orders")
public class Orders {

    @Id
    @GeneratedValue
    private Integer id;

    // 订单号应该是唯一的但是这里应该怎么生成唯一的订单号
    private String shopOrderId;//订单号

    private String shopKeeper;//店主 ==> 用于后期返现

    private Date shopOrderTime;//订单时间

    private String shopOrderMan;//下订单人

    private String telephone;//订单人电话

    private String receiver;//收货人姓名

    private String receiverPhone;//收货人电话

    private String address;//收货人地址

    private String zipCode;//邮政编码

    private float firstCost;//订单成本价

    private float ordersCost;//订单总价(可能包含运费) 达到免运费条件时候布包好运费

    private float profits;//订单利润价(纯销售价-纯成本价)

    private Integer status;//订单状态,0提交订单，1已付款未发货，2已付款已发货，3已确认收货，4申请退款中，5已退款，6取消订单

    private String kuaiDiDanHao;//快递单号

    private float yunFei;//运费

    private String liuYan;//留言

    private Integer isDelFromUser;//用户是否删除，0存在，1删除

    private Integer isDel;//0存在，1删除

    private Integer user; //下该订单的用户

    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE},mappedBy = "orders")
    private List<ShopCart> shopCartList=new ArrayList<ShopCart>();

    public Orders() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShopOrderId() {
        return shopOrderId;
    }

    public void setShopOrderId(String shopOrderId) {
        this.shopOrderId = shopOrderId;
    }

    public Date getShopOrderTime() {
        return shopOrderTime;
    }

    public void setShopOrderTime(Date shopOrderTime) {
        this.shopOrderTime = shopOrderTime;
    }

    public String shopOrderTimeToString(){
        if (shopOrderTime!=null){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.format(shopOrderTime);
        }else {
            return null;
        }

    }

    public String getShopOrderMan() {
        return shopOrderMan;
    }

    public void setShopOrderMan(String shopOrderMan) {
        this.shopOrderMan = shopOrderMan;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getProfits() {
        return profits;
    }

    public void setProfits(float profits) {
        this.profits = profits;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getShopKeeper() {
        return shopKeeper;
    }

    public void setShopKeeper(String shopKeeper) {
        this.shopKeeper = shopKeeper;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getKuaiDiDanHao() {
        return kuaiDiDanHao;
    }

    public void setKuaiDiDanHao(String kuaiDiDanHao) {
        this.kuaiDiDanHao = kuaiDiDanHao;
    }

    public float getYunFei() {
        return yunFei;
    }

    public void setYunFei(float yunFei) {
        this.yunFei = yunFei;
    }

    public float getFirstCost() {
        return firstCost;
    }

    public void setFirstCost(float firstCost) {
        this.firstCost = firstCost;
    }

    public float getOrdersCost() {
        return ordersCost;
    }

    public void setOrdersCost(float ordersCost) {
        this.ordersCost = ordersCost;
    }

    public String getLiuYan() {
        return liuYan;
    }

    public void setLiuYan(String liuYan) {
        this.liuYan = liuYan;
    }

    public List<ShopCart> getShopCartList() {
        return shopCartList;
    }

    public void setShopCartList(List<ShopCart> shopCartList) {
        this.shopCartList = shopCartList;
    }

    public void addShopCartList(ShopCart shopCart){
        shopCart.setOrders(this);
        this.shopCartList.add(shopCart);
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getIsDelFromUser() {
        return isDelFromUser;
    }

    public void setIsDelFromUser(Integer isDelFromUser) {
        this.isDelFromUser = isDelFromUser;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }
}
