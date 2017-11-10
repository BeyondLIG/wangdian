package cn.wangdian.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类信息
 */
@Entity
@Table(name = "wd_shopType")
public class ShopType {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

//    @OneToMany(mappedBy = "shopType", cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
//    private List<Shop> shopList = new ArrayList<>();

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


    /**
     * @return 返回商品类型的名字
     */
    @Override
    public String toString() {
        return name;
    }
}
