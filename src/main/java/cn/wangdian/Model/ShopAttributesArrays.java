package cn.wangdian.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 25065 on 2016/9/20.
 */
public class ShopAttributesArrays {

    private List<ShopAttributes> shopAttributesList=new ArrayList<ShopAttributes>();

    private List<ShopAttributesValue> shopAttributesValueList=new ArrayList<ShopAttributesValue>();

    private List<ShopPhotos> shopPhotosList=new ArrayList<ShopPhotos>();

    private List<ShopPhotos> shopShowsList=new ArrayList<ShopPhotos>();

    public List<ShopAttributes> getShopAttributesList() {
        return shopAttributesList;
    }

    public void setShopAttributesList(List<ShopAttributes> shopAttributesList) {
        this.shopAttributesList = shopAttributesList;
    }

    public List<ShopPhotos> getShopPhotosList() {
        return shopPhotosList;
    }

    public void setShopPhotosList(List<ShopPhotos> shopPhotosList) {
        this.shopPhotosList = shopPhotosList;
    }

    public List<ShopPhotos> getShopShowsList() {
        return shopShowsList;
    }

    public void setShopShowsList(List<ShopPhotos> shopShowsList) {
        this.shopShowsList = shopShowsList;
    }

    public List<ShopAttributesValue> getShopAttributesValueList() {
        return shopAttributesValueList;
    }

    public void setShopAttributesValueList(List<ShopAttributesValue> shopAttributesValueList) {
        this.shopAttributesValueList = shopAttributesValueList;
    }
}
