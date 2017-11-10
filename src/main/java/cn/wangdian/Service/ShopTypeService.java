package cn.wangdian.Service;

import cn.wangdian.Model.ShopType;
import cn.wangdian.Repository.ShopTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品分类的服务类
 */
@Service
public class ShopTypeService {
    @Autowired
    private ShopTypeRepository shopTypeRepository;

    /**
     * 查询所有的商品分类
     */
    public List<String> findAllName() {
       return shopTypeRepository.findAllName();
    }

    /**
     * 通过商品类型的名字去找相应的商品
     * @param name 商品类型的名字
     * @return 如果找不到响应的商品类型的名字，返回 null
     */
    public ShopType findByShopTypeName(String name) {
        return shopTypeRepository.findByShopTypeName(name);
    }

    /**
     * 更新 ShopType 信息
     */
    @Transactional
    public void update(ShopType shopType) {
        shopTypeRepository.saveAndFlush(shopType);
    }

    @Transactional
    public void save(ShopType shopType) {
        shopTypeRepository.save(shopType);
    }
}
