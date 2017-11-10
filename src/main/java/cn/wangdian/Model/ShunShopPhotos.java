package cn.wangdian.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by 25065 on 2016/9/16.
 */
@Entity
@Table(name = "wd_shunshopphotos")
public class ShunShopPhotos {

    @Id
    @GeneratedValue
    private Integer id;

    private String urlPath;

    private Integer shopId;

    private Integer isDel;

    private Integer photoType;//0商品图片展示，1商品详情

    public ShunShopPhotos() {
    }

    public ShunShopPhotos(String urlPath, Integer shopId) {
        this.urlPath = urlPath;
        this.shopId = shopId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
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

    public Integer getPhotoType() {
        return photoType;
    }

    public void setPhotoType(Integer photoType) {
        this.photoType = photoType;
    }
}
