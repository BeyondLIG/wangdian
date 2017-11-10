package cn.wangdian.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * 存储多个购物车信息
 * 分别存储的是购物车的 id
 * 以及每一个购物车对应有多少件商品
 */
public class ShopCartArrays {

    private List<Integer> shopCartIdList=new ArrayList<>();
    private List<Integer> countList=new ArrayList<>();

    public List<Integer> getShopCartIdList() {
        return shopCartIdList;
    }

    public void setShopCartIdList(List<Integer> shopCartIdList) {
        this.shopCartIdList = shopCartIdList;
    }

    public List<Integer> getCountList() {
        return countList;
    }

    public void setCountList(List<Integer> countList) {
        this.countList = countList;
    }
}
