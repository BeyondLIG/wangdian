package cn.wangdian.Service;

import cn.wangdian.Model.AnnualFeeOrder;
import cn.wangdian.Repository.AnnualFeeOrderRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 年费的服务类
 */
@Service
public class AnnualFeeOrderService {
    @Autowired
    private AnnualFeeOrderRepository annualFeeOrderRepository;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 通过订单 id 更新订单付款状态
     * @param status 订单更新后的状态 0 ==> 未付款 1 ==> 已付款
     * @param orderId 订单 id
     */
    private void updateStatusByOrderId(Integer status,String orderId){
        annualFeeOrderRepository.updateStatusByOrderId(status, orderId);
    }

    /**
     * 更新订单状态未已付款
     * @param orderId 订单id
     */
    @Transactional
    public void updateAlreadyPaidByOrderId(String orderId) {
        updateStatusByOrderId(1, orderId);
    }

    /**
     * 通过数据库id来查找年费订单信息
     * @param id 数据库中的id
     * @return 所查询的订单
     */
    public AnnualFeeOrder findById(Integer id) {
        return annualFeeOrderRepository.findById(id);
    }

    /**
     * 通过订单id来查询订单
     * @param orderId 订单 id
     * @return 所查询的订单
     */
    public AnnualFeeOrder findByOrderId(String orderId) {
        return annualFeeOrderRepository.findByOrOrderId(orderId);
    }

    /**
     * 通过订单id来查询该订单的状态
     * @param orderId 订单id
     * @return 所查询的订单
     */
    public int findStatusByOrderId(String orderId) {
        return annualFeeOrderRepository.findStatusByOrderId(orderId);
    }

    /**
     * 查询今天所有提交年费的单子
     * @param today yyyyMMdd 字符串形式
     * @return yyyyMMdd 目前有多少但店主发起提交年费
     */
    public Integer countToday(final String today) {
        return annualFeeOrderRepository.countTodayOrder(today);
    }

    /**
     * 存储新的 AnnualFeeOrder 到数据库中
     * @param annualFeeOrder 需要被存储到数据库中的 AnnualFeeOrder
     */
    @Transactional
    public void save(AnnualFeeOrder annualFeeOrder) {
        annualFeeOrderRepository.save(annualFeeOrder);
    }


    /**
     * 统计所有已经交付了年费订单的数目
     * @return
     */
    public Integer countAllAnnualFeeOrdersWhichHaveBeenPaid() {
        return annualFeeOrderRepository.countAllByStatus(1);
    }

    /**
     * 查看尚未付款的年费订单数目
     *
     * @return
     */
    public Integer countAllAnnualFeeOrdersWhichNotPaid() {
        return annualFeeOrderRepository.countAllByStatus(0);
    }

    /**
     * 得到所有年费的收入
     */
    public float countAllAnnualFeeIncome() {
        return annualFeeOrderRepository.countAllAnnualFeeIncome();
    }

    /**
     * 统计所有符合条件的年费订单的数量
     * @param orderId 年费订单号
     * @param shopKeeper 提交年费的店主
     * @param submitTime 提交年费的时间
     * @param status 年费订单是否已经付款
     * @return
     */
    public Integer countAllByCondition(String orderId, String shopKeeper, String submitTime, Integer status) {
        Specification<AnnualFeeOrder> annualFeeOrderSpecification = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = Lists.newArrayList();
            Predicate predicate ;

            try {

                if (orderId != null && !orderId.equals("")) {
                    Path<String> stringPath = root.get("orderId");
                    predicate = criteriaBuilder.like(stringPath, orderId);
                    predicateList.add(predicate);
                }

                if (shopKeeper != null && !shopKeeper.equals("")) {
                    Path<String> stringPath = root.get("shopKeeper");
                    predicate = criteriaBuilder.like(stringPath, shopKeeper);
                    predicateList.add(predicate);
                }

                if (submitTime != null && !submitTime.equals("")) {
                    Date startDate = sdf.parse(submitTime + " 00:00:00");
                    Date endDate = sdf.parse(submitTime + " 23:59:59");
                    Expression<Date> datePath = root.get("submitTime");
                    predicate = criteriaBuilder.between(datePath, startDate, endDate);
                    predicateList.add(predicate);
                }

                if (status != null) {
                    predicate = criteriaBuilder.equal(root.get("status"), status);
                    predicateList.add(predicate);
                }

                criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        };
        long count = annualFeeOrderRepository.count(annualFeeOrderSpecification);
        return new Long(count).intValue();
    }


    /**
     * 通过条件来查找符合条件的年费订单
     *
     * @param orderId
     * @param shopKeeper
     * @param submitTime
     * @param status
     * @return
     */
    public Page<AnnualFeeOrder> findAllByCondition(String orderId, String shopKeeper, String submitTime, Integer status, String orderField, String orderDirection, PageRequest pageRequest) {
        Specification<AnnualFeeOrder> annualFeeOrderSpecification = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = Lists.newArrayList();
            Predicate predicate;

            if (orderId != null && !orderId.equals("")) {
                Path<String> stringPath = root.get("orderId");
                predicate = criteriaBuilder.like(stringPath, "%" + orderId + "%");
                predicateList.add(predicate);
            }

            if (shopKeeper != null && !shopKeeper.equals("")) {
                Path<String> stringPath = root.get("shopKeeper");
                predicate = criteriaBuilder.equal(stringPath, shopKeeper);
                predicateList.add(predicate);
            }

            if (submitTime != null && !submitTime.equals("")) {
                try {
                    Date startDate = sdf.parse(submitTime + " 00::00:00");
                    Date endDate = sdf.parse(submitTime + " 23:59:59");
                    Expression<Date> datePath = root.get("submitTime");
                    predicate = criteriaBuilder.between(datePath, startDate, endDate);
                    predicateList.add(predicate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            if (status != null) {
                predicate = criteriaBuilder.equal(root.get("status"), status);
                predicateList.add(predicate);
            }

            criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
            return null;
        };

        Sort sort;
        if (orderDirection != null && !orderDirection.equals("") && orderField != null && !orderField.equals("")){
            sort=new Sort(Sort.Direction.fromString(orderDirection),orderField);
        }else {
            sort=new Sort(Sort.Direction.DESC,"submitTime");
        }

        Pageable pageable = new PageRequest(pageRequest.getPageNumber(), pageRequest.getPageSize(), sort);

        return annualFeeOrderRepository.findAll(annualFeeOrderSpecification, pageable);
    }

}
