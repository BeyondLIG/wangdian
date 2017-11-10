package cn.wangdian.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 25065 on 2016/9/21.
 */
@Entity
@Table(name = "wd_shopkeeperprofit")
public class ShopKeeperProfit {

    @Id
    @GeneratedValue
    private Integer id;

    private String shopKeeper;//提现人

    private float money;//提现金额

    private Date tiXianTime;//提现时间

    private Integer status;//0未批准，1批准,，2拒绝

    private Integer isDel;//0存在，1删除

    private String alipay;//提现转到的支付宝账号

    public ShopKeeperProfit() {
    }

    public ShopKeeperProfit(String shopKeeper, float money, Date tiXianTime, Integer status, Integer isDel, String alipay) {
        this.shopKeeper = shopKeeper;
        this.money = money;
        this.tiXianTime = tiXianTime;
        this.status = status;
        this.isDel = isDel;
        this.alipay = alipay;
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

    public void setShopKeeper(String shopKeeper) {
        this.shopKeeper = shopKeeper;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public Date getTiXianTime() {
        return tiXianTime;
    }

    public void setTiXianTime(Date tiXianTime) {
        this.tiXianTime = tiXianTime;
    }

    public String tiXianTimeToString(){
        if (tiXianTime!=null){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.format(tiXianTime);
        }else {
            return null;
        }

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

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }
}
