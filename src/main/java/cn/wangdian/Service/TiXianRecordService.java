package cn.wangdian.Service;

import cn.wangdian.Model.TiXianRecord;
import cn.wangdian.Repository.TiXianRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TiXianRecordService {
    @Autowired
    private TiXianRecordRepository tiXianRecordRepository;

    /**
     * 持久化一个 TiXianRecord 对象
     */
    public void save(TiXianRecord tiXianRecord) {
        tiXianRecordRepository.save(tiXianRecord);
    }

    /**
     * 成功执行提现，更新提现 order 状态
     */
    public void orderFinished(String orderId) {
       updateStatusByOrderId(1, orderId);
    }

    /**
     * 更改数据库中一条 orderId 的 status 状态
     */
    @Transactional
    private void updateStatusByOrderId(int status, String orderId) {
        tiXianRecordRepository.updateStatusByOrderId(status, orderId);
    }
}
