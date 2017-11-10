package cn.wangdian.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 追踪店主的收益记录
 */
@Entity
@Table(name = "wd_ShopkeeperProfitTrack")
public class ShopkeeperProfitTrack {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer shopkeeperId; //获得利润的店主 id

    private Integer level; //当前店主的等级

    private Float profit; //店主获得多少利润

    private Integer type; //店主所获得利润的来源类型 (0-直接下属用户购物 1-间接下属用户购物 2-招到直接店主 3-招到间接店主)

    private String username; //下属用户的用户名或者下属店主的真实姓名

    private String telephone; //下属用户或者下属店主的手机号码

    private Date time; //记录获得利润的时间

    private Integer orderId; //用户购买商品的购物车 id或者是年费订单

    public ShopkeeperProfitTrack(Integer shopkeeperId, Integer level, Float profit, Integer type, String username, String telephone, Date time, Integer orderId) {
        this.profit = profit;
        this.type = type;
        this.username = username;
        this.telephone = telephone;
        this.time = time;
        this.shopkeeperId = shopkeeperId;
        this.level = level;
        this.orderId = orderId;
    }

    public ShopkeeperProfitTrack() {}

    public Integer getShopkeeperId() {
        return shopkeeperId;
    }

    public void setShopkeeperId(Integer shopkeeperId) {
        this.shopkeeperId = shopkeeperId;
    }

    public Float getProfit() {
        return profit;
    }

    public void setProfit(Float profit) {
        this.profit = profit;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getTime() {
        return time;
    }

    public Integer getId() {
        return id;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
