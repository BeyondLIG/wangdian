package cn.wangdian.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 顺时秒杀商品
 */
@Entity
@Table(name = "wd_shunshop")
public class ShunShop {

    @Id
    @GeneratedValue
    private Integer id;

    private String number;//编号

    private String name;//商品名称

    private String shopType;//商品类型

    private String shopModel;//商品型号

    private float firstCost;//成本价

    private float secondCost;//销售价

    private Integer status;//0上架，1下架

    private Integer isDel;//0存在，1删除

    private String shopDescribe;//描述

    private float thirdCost;//第三次价，瞬杀价

    private Date inTime;//入库时间

    private Integer day;

    private Integer hours;

    private Integer minutes;

    private Integer seconds;

    private Date startTime;

    private Date endTime;

    private String firstPhoto;

    public ShunShop() {
    }

    public ShunShop(String number, String name, String shopType, String shopModel, Integer status, Integer isDel, String shopDescribe) {
        this.number = number;
        this.name = name;
        this.shopType = shopType;
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

    public float getFirstCost() {
        return firstCost;
    }

    public void setFirstCost(float firstCost) {
        this.firstCost = firstCost;
    }

    public float getSecondCost() {
        return secondCost;
    }

    public void setSecondCost(float secondCost) {
        this.secondCost = secondCost;
    }

    public float getThirdCost() {
        return thirdCost;
    }

    public void setThirdCost(float thirdCost) {
        this.thirdCost = thirdCost;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Integer getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getFirstPhoto() {
        return firstPhoto;
    }

    public void setFirstPhoto(String firstPhoto) {
        this.firstPhoto = firstPhoto;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String startTimeToString(){
        if (startTime!=null){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.format(startTime);
        }else {
            return null;
        }

    }

    public String endTimeToString(){
        if (endTime!=null){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.format(endTime);
        }else {
            return null;
        }

    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public String inTimeToString(){
        if (inTime!=null){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.format(inTime);
        }else {
            return null;
        }

    }
}
