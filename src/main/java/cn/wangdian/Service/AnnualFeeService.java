package cn.wangdian.Service;

import cn.wangdian.Model.AnnualFee;
import cn.wangdian.Repository.AnnualFeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 年费服务
 */
@Service
public class AnnualFeeService {
    @Autowired
    private AnnualFeeRepository annualFeeRepository;

    /**
     * 提取第一条年费记录
     * @return 如果不存在年费记录就返回 null
     * 否则返回第一条年费记录
     */
    public AnnualFee onlyOne() {
        List<AnnualFee> annualFees = annualFeeRepository.findAll();
        if (annualFees == null || annualFees.size() == 0) {
            return null;
        }else {
            return annualFees.get(0);
        }

    }

    /**
     * 保存新的记录
     * @param annualFee 需要被保存的年费记录
     */
    @Transactional
    public void save(AnnualFee annualFee) {
        annualFeeRepository.save(annualFee);
    }

    /**
     * 更新某条年费记录
     * @param annualFee 需要被更新的年费记录
     */
    @Transactional
    public void update(AnnualFee annualFee) {
        //id == 1
        annualFee.setId(1);
        annualFeeRepository.saveAndFlush(annualFee);
    }


}
