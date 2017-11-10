package cn.wangdian.Repository;

import cn.wangdian.Model.ShopAttributes;
import cn.wangdian.Model.ShunShopAttributes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by 25065 on 2016/9/18.
 */
public interface ShunShopAttributesRepository extends JpaRepository<ShunShopAttributes,String>,JpaSpecificationExecutor<ShunShopAttributes> {

    @Query("select o.name from ShunShopAttributes o where o.isDel=0 and o.shopId=:shopId and o.name=:name order by o.name asc ")
    public String findNameByNameAndShopId(@Param("name") String name,@Param("shopId") Integer shopId);

    @Query("select o from ShunShopAttributes o where o.isDel=0 and o.shopId=:shopId and o.name=:name")
    public ShunShopAttributes  findByNameAndShopId(@Param("name") String name,@Param("shopId") Integer shopId);


    @Modifying
    @Query("update ShunShopAttributes o set o.isDel=1 where lower(o.id)=lower(:id) ")
    public void deleteByPrimaryKey(@Param("id") Integer id);

    @Query("select o from ShunShopAttributes o where o.isDel=0 and o.shopId=:shopId")
    public List<ShunShopAttributes> findAllByIsDel0AndShopId(@Param("shopId") Integer shopId);
}
