package cn.wangdian.Repository;

import cn.wangdian.Model.AnnualFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 年费仓库
 * 写法参考 YunFeiRepository
 */
public interface AnnualFeeRepository extends JpaRepository<AnnualFee, String>, JpaSpecificationExecutor<AnnualFee> {

}
