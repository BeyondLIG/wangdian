package cn.wangdian.Service;

import cn.wangdian.Model.ShopAttributes;
import cn.wangdian.Repository.ShopAttributesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 25065 on 2016/9/18.
 */
@Service
public class ShopAttributesService {

    @Autowired
    private ShopAttributesRepository shopAttributesRepository;

    public Integer save(ShopAttributes shopAttributes){
        return shopAttributesRepository.save(shopAttributes).getId();
    }

    public void update(ShopAttributes shopAttributes){
        shopAttributesRepository.saveAndFlush(shopAttributes);
    }

    public String findNameByNameAndShopId(String name,Integer shopId){
         return shopAttributesRepository.findNameByNameAndShopId(name,shopId);
    }

    public ShopAttributes findByNameAndShopId(String name,Integer shopId){
        Pageable pageable = new PageRequest(0, 1);
        return shopAttributesRepository.findByNameAndShopId(name,shopId, pageable).get(0);
    }

    @Transactional
    public void updateNameById(String name,Integer id){
         shopAttributesRepository.updateNameById(name,id);
    }

    public List<ShopAttributes> findAllByIsDel0AndShopId(Integer shopId){
        return shopAttributesRepository.findAllByIsDel0AndShopId(shopId);
    }

    @Transactional
    public void deleteByPrimaryKey(Integer id){
        shopAttributesRepository.deleteByPrimaryKey(id);
    }
}
