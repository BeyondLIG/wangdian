package cn.wangdian.Repository;

import cn.wangdian.Model.ShopAttributes;
import cn.wangdian.Model.ShopAttributesValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by 25065 on 2016/9/18.
 */
public interface ShopAttributesValueRepository extends JpaRepository<ShopAttributesValue,String>,JpaSpecificationExecutor<ShopAttributesValue> {

    @Modifying
    @Query("update ShopAttributesValue o set o.isDel=1 where o.id=:id ")
    public void deleteByPrimaryKey(@Param("id") Integer id);


//    @Query("select o.attributesValue from ShopAttributesValue o where o.isDel=0 and o.shopAttributesId=:attributesId ")
//    public String  findValueByAttributesId(@Param("attributesId") Integer attributesId);

    @Modifying
    @Query("update ShopAttributesValue o set o.attributesValue=:name where o.id=:id ")
    public void updateNameById(@Param("name") String name, @Param("id") Integer id);
}
