package cn.wangdian.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 店主提交年费的订单
 */
@Entity
@Table(name = "wd_annualfee_order")
public class AnnualFeeOrder {
    @Id
    @GeneratedValue
    private Integer id;

    //交年费的店主
    private String shopKeeper;

    //订单id
    private String orderId;

    //订单状态 0==>为付款   1==>已付款
    private Integer status;

    private Date submitTime;

    private Float fee;

    public Float getFee() {
        return fee;
    }

    public void setFee(Float fee) {
        this.fee = fee;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShopKeeper() {
        return shopKeeper;
    }

    public void setShopKeeper(String shopKeeer) {
        this.shopKeeper = shopKeeer;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
