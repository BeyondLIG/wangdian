package cn.wangdian.Repository;

import cn.wangdian.Model.Benefit;
import cn.wangdian.Model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by 1192126986 on 2017/4/23.
 * 分润系统接口
 */
public interface BenefitRepository extends JpaRepository<Benefit,String>,JpaSpecificationExecutor<Benefit> {
    //店主分润系数
    @Query("select o.a from Benefit o where o.id=:id")
    public float findAById(@Param("id")Integer id);

    //直系主管分润系数
    @Query("select o.b from Benefit o where o.id=:id")
    public float findBById(@Param("id")Integer id);

    //直系经理分润系数
    @Query("select o.c from Benefit o where o.id=:id")
    public float findCById(@Param("id")Integer id);

    //更改分润系数
    //1.店主
    @Modifying
    @Query("update Benefit o set o.a=:a")
    public void updateA(@Param("a")Float a);

    //2.主管
    @Modifying
    @Query("update Benefit o set o.b=:b")
    public void updateB(@Param("b")Float b);

    //3.经理
    @Modifying
    @Query("update Benefit o set o.c=:c")
    public void updateC(@Param("c")Float c);

    //查看商品分润系数
    @Query("select o from Benefit o where o.id=1")
    public Benefit findGoodsShareBenefitParam();

    //查看年费分润系数
    @Query("select o from Benefit o where o.id=2")
    public Benefit findAnnualFeeBenefitParam();
}
