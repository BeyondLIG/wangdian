package cn.wangdian.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 25065 on 2016/9/20.
 */
@Entity
@Table(name = "wd_shopattributes")
public class ShopAttributes {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

//    private String value;

    private Integer shopId;

    private Integer isDel;//0存在，1删除

    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE},mappedBy = "shopAttributes")
    private List<ShopAttributesValue> shopAttributesValueList=new ArrayList<ShopAttributesValue>();

    public ShopAttributes() {
    }

    public ShopAttributes(String name,  Integer shopId) {
        this.name = name;
        this.shopId = shopId;
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

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public List<ShopAttributesValue> getShopAttributesValueList() {
        return shopAttributesValueList;
    }

    public void setShopAttributesValueList(List<ShopAttributesValue> shopAttributesValueList) {
        this.shopAttributesValueList = shopAttributesValueList;
    }

    public void addShopAttributesValueList(ShopAttributesValue shopAttributesValue){
        shopAttributesValue.setShopAttributes(this);
        this.shopAttributesValueList.add(shopAttributesValue);
    }

}
