package cn.wangdian.Service;

import cn.wangdian.Model.ShopPhotos;
import cn.wangdian.Model.ShunShopPhotos;
import cn.wangdian.Repository.ShopPhotosRepository;
import cn.wangdian.Repository.ShunShopPhotosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 25065 on 2016/9/18.
 */
@Service
public class ShunShopPhotosService {

    @Autowired
    private ShunShopPhotosRepository shunShopPhotosRepository;

    public void save(ShunShopPhotos shunShopPhotos){
        shunShopPhotosRepository.save(shunShopPhotos);
    }

    public void update(ShunShopPhotos shunShopPhotos){
        shunShopPhotosRepository.saveAndFlush(shunShopPhotos);
    }

    public List<ShunShopPhotos> findAllByIsDel0AndShopId(Integer shopId,Integer photoType){
        return shunShopPhotosRepository.findAllByIsDel0AndShopId(shopId,photoType);
    }

    public ShunShopPhotos findById(Integer id){
        return shunShopPhotosRepository.findById(id);
    }

    @Transactional
    public void deleteByPrimaryKey(Integer id){
        shunShopPhotosRepository.deleteByPrimaryKey(id);
    }
}
