package cn.wangdian.Repository;

import cn.wangdian.Model.TiXianRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 店主提现记录的仓库
 */
public interface TiXianRecordRepository extends JpaRepository<TiXianRecord, String>, JpaSpecificationExecutor<TiXianRecord>{
    @Modifying
    @Query("update TiXianRecord o set o.status=:status where o.orderId=:orderId")
    void updateStatusByOrderId(@Param("status") int status, @Param("orderId") String orderId);
}
