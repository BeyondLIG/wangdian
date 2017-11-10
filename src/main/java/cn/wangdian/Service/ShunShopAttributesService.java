package cn.wangdian.Service;

import cn.wangdian.Model.ShopAttributes;
import cn.wangdian.Model.ShunShopAttributes;
import cn.wangdian.Repository.ShopAttributesRepository;
import cn.wangdian.Repository.ShunShopAttributesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 25065 on 2016/9/18.
 */
@Service
public class ShunShopAttributesService {

    @Autowired
    private ShunShopAttributesRepository shunShopAttributesRepository;

    public void save(ShunShopAttributes shunShopAttributes){
        shunShopAttributesRepository.save(shunShopAttributes);
    }

    public void update(ShunShopAttributes shunShopAttributes){
        shunShopAttributesRepository.saveAndFlush(shunShopAttributes);
    }

    public ShunShopAttributes findByNameAndShopId(String name,Integer shopId){
        return shunShopAttributesRepository.findByNameAndShopId(name,shopId);
    }

    public String findNameByNameAndShopId(String name,Integer shopId){
        return shunShopAttributesRepository.findNameByNameAndShopId(name,shopId);
    }

    public List<ShunShopAttributes> findAllByIsDel0AndShopId(Integer shopId){
        return shunShopAttributesRepository.findAllByIsDel0AndShopId(shopId);
    }

    @Transactional
    public void deleteByPrimaryKey(Integer id){
        shunShopAttributesRepository.deleteByPrimaryKey(id);
    }
}
