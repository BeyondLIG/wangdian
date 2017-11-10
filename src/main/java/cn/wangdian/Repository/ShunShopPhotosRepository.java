package cn.wangdian.Repository;

import cn.wangdian.Model.ShopPhotos;
import cn.wangdian.Model.ShunShopPhotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by 25065 on 2016/9/18.
 */
public interface ShunShopPhotosRepository extends JpaRepository<ShunShopPhotos,String>,JpaSpecificationExecutor<ShunShopPhotos> {

    @Query("select o from ShunShopPhotos o where o.isDel=0 and o.shopId=:shopId and o.photoType=:photoType")
    public List<ShunShopPhotos> findAllByIsDel0AndShopId(@Param("shopId") Integer shopId, @Param("photoType") Integer photoType);

    @Query("select o from ShunShopPhotos o where o.id=:id ")
    public ShunShopPhotos findById(@Param("id") Integer id);

    @Modifying
    @Query("update ShunShopPhotos o set o.isDel=1 where o.id=:id ")
    public void deleteByPrimaryKey(@Param("id") Integer id);
}
