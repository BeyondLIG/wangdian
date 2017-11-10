package cn.wangdian.Service;

import cn.wangdian.Model.ShopPhotos;
import cn.wangdian.Repository.ShopPhotosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 25065 on 2016/9/18.
 */
@Service
public class ShopPhotosService {

    @Autowired
    private ShopPhotosRepository shopPhotosRepository;

    public void save(ShopPhotos shopPhotos){
        shopPhotosRepository.save(shopPhotos);
    }

    public void update(ShopPhotos shopPhotos){
        shopPhotosRepository.saveAndFlush(shopPhotos);
    }

    public List<ShopPhotos> findAllByIsDel0AndShopId(Integer shopId,Integer photoType){
        return shopPhotosRepository.findAllByIsDel0AndShopId(shopId,photoType);
    }

    public ShopPhotos findById(Integer id){
        return shopPhotosRepository.findById(id);
    }

    public String findUrlPathById(Integer id){
        return shopPhotosRepository.findUrlPathById(id);
    }

    @Transactional
    public void deleteByPrimaryKey(Integer id){
        shopPhotosRepository.deleteByPrimaryKey(id);
    }

}
