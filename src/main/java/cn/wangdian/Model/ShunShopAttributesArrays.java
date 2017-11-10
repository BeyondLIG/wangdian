package cn.wangdian.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 25065 on 2016/9/20.
 */
public class ShunShopAttributesArrays {

    private List<ShunShopAttributes> shunShopAttributesList=new ArrayList<ShunShopAttributes>();

    private List<ShunShopPhotos> shunShopPhotosList=new ArrayList<ShunShopPhotos>();

    private List<ShunShopPhotos> shunShopShowsList=new ArrayList<ShunShopPhotos>();

    private List<ShunShopAttributesValue> shunShopAttributesValueList=new ArrayList<ShunShopAttributesValue>();

    public List<ShunShopAttributes> getShunShopAttributesList() {
        return shunShopAttributesList;
    }

    public void setShunShopAttributesList(List<ShunShopAttributes> shunShopAttributesList) {
        this.shunShopAttributesList = shunShopAttributesList;
    }

    public List<ShunShopPhotos> getShunShopPhotosList() {
        return shunShopPhotosList;
    }

    public void setShunShopPhotosList(List<ShunShopPhotos> shunShopPhotosList) {
        this.shunShopPhotosList = shunShopPhotosList;
    }

    public List<ShunShopPhotos> getShunShopShowsList() {
        return shunShopShowsList;
    }

    public void setShunShopShowsList(List<ShunShopPhotos> shunShopShowsList) {
        this.shunShopShowsList = shunShopShowsList;
    }

    public List<ShunShopAttributesValue> getShunShopAttributesValueList() {
        return shunShopAttributesValueList;
    }

    public void setShunShopAttributesValueList(List<ShunShopAttributesValue> shunShopAttributesValueList) {
        this.shunShopAttributesValueList = shunShopAttributesValueList;
    }
}
