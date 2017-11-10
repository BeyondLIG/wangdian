package cn.wangdian.Repository;

import cn.wangdian.Model.ShopType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 商品分类的仓库
 */
public interface ShopTypeRepository extends JpaRepository<ShopType, String>, JpaSpecificationExecutor<ShopType> {
    @Query("select distinct (o.name) from ShopType o")
    List<String> findAllName();

    @Query("select o from ShopType o where o.name=:name")
    ShopType findByShopTypeName(@Param("name") String name);


}
