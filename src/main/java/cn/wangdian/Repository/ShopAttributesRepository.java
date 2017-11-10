package cn.wangdian.Repository;

import cn.wangdian.Model.ShopAttributes;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by 25065 on 2016/9/18.
 */
public interface ShopAttributesRepository extends JpaRepository<ShopAttributes,String>,JpaSpecificationExecutor<ShopAttributes> {

    @Modifying
    @Query("update ShopAttributes o set o.isDel=1 where o.id=:id ")
    public void deleteByPrimaryKey(@Param("id") Integer id);

    @Query("select o from ShopAttributes o where o.isDel=0 and o.shopId=:shopId order by o.name asc ")
    public List<ShopAttributes> findAllByIsDel0AndShopId(@Param("shopId") Integer shopId);

    @Query("select o.name from ShopAttributes o where o.isDel=0 and o.shopId=:shopId and o.name=:name")
    public String  findNameByNameAndShopId(@Param("name") String name,@Param("shopId") Integer shopId);

    @Query("select o from ShopAttributes o where o.isDel=0 and o.shopId=:shopId and o.name=:name")
    public List<ShopAttributes> findByNameAndShopId(@Param("name") String name, @Param("shopId") Integer shopId, Pageable pageable);

    @Modifying
    @Query("update ShopAttributes o set o.name=:name where o.id=:id ")
    public void updateNameById(@Param("name") String name,@Param("id") Integer id);
}
