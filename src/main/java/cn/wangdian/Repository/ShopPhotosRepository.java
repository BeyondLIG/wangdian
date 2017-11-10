package cn.wangdian.Repository;

import cn.wangdian.Model.ShopPhotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by 25065 on 2016/9/18.
 */
public interface ShopPhotosRepository extends JpaRepository<ShopPhotos,String>,JpaSpecificationExecutor<ShopPhotos> {

    @Query("select o from ShopPhotos o where o.isDel=0 and o.shopId=:shopId and o.photoType=:photoType")
    public List<ShopPhotos> findAllByIsDel0AndShopId(@Param("shopId") Integer shopId,@Param("photoType") Integer photoType);

    @Query("select o from ShopPhotos o where o.id=:id ")
    public ShopPhotos findById(@Param("id") Integer id);

    @Query("select o.urlPath from ShopPhotos o where o.id=:id ")
    public String findUrlPathById(@Param("id") Integer id);

    @Modifying
    @Query("update ShopPhotos o set o.isDel=1 where o.id=:id ")
    public void deleteByPrimaryKey(@Param("id") Integer id);
}
