package cn.wangdian.Repository;

import cn.wangdian.Model.AnnualFeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 年费仓库记录
 */
public interface AnnualFeeOrderRepository extends JpaRepository<AnnualFeeOrder, String>, JpaSpecificationExecutor<AnnualFeeOrder> {

    @Query("select o from AnnualFeeOrder o where o.id=:id")
    AnnualFeeOrder findById(@Param("id") Integer id);

    @Query("select o from AnnualFeeOrder o where o.orderId=:annualFeeOrderId ")
    AnnualFeeOrder findByOrOrderId(@Param("annualFeeOrderId") String annualFeeOrderId);

    @Query("select o.status from AnnualFeeOrder o where o.orderId=:annualFeeOrderId ")
    int findStatusByOrderId(@Param("annualFeeOrderId") String annualFeeOrderId);

    @Modifying
    @Query("update AnnualFeeOrder o set o.status=:status where o.orderId=:annualFeeOrderId ")
    void updateStatusByOrderId(@Param("status") Integer status, @Param("annualFeeOrderId") String annualFeeOrderId);

    // warning: 这里有可能会抛出异常 ==> 实际测试中，并没有抛出异常，而且在 mysql 以下语句可以正常执行
    // 使用模糊查询
    @Query("select count(o) from AnnualFeeOrder o where o.submitTime like concat(:today, '%') ")
    int countTodayOrder(@Param("today") String today);

    @Query("select count(o) from AnnualFeeOrder o where o.status=:status")
    Integer countAllByStatus(@Param("status") Integer status);

    @Query("select sum(o.fee) from AnnualFeeOrder o where o.status=1")
    float countAllAnnualFeeIncome();

}
