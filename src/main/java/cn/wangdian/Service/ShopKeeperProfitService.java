package cn.wangdian.Service;

import cn.wangdian.Model.ShopKeeperProfit;
import cn.wangdian.Repository.ShopKeeperProfitRepository;
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
 * Created by 25065 on 2016/9/18.
 */
@Service
public class ShopKeeperProfitService {

    @Autowired
    private ShopKeeperProfitRepository shopKeeperProfitRepository;

    public void save(ShopKeeperProfit shopKeeperProfit){
        shopKeeperProfitRepository.save(shopKeeperProfit);
    }

    public void update(ShopKeeperProfit shopKeeperProfit){
        shopKeeperProfitRepository.saveAndFlush(shopKeeperProfit);
    }

    public Page<ShopKeeperProfit> findAllByIsDel0(final String shopKeeper, final String tiXianTime, final Integer status, String orderField, String orderDirection, PageRequest pageRequest) throws Exception{

        //特殊情况查询
        Specification<ShopKeeperProfit> userSpecification=new Specification<ShopKeeperProfit>() {
            @Override
            public Predicate toPredicate(Root<ShopKeeperProfit> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList= Lists.newArrayList();

                Predicate predicate=null;

                //匹配属性和属性对应的值
                predicate=criteriaBuilder.equal(root.get("isDel"),0);

                //添加
                predicateList.add(criteriaBuilder.and(predicate));

                if (shopKeeper!=null&&!shopKeeper.equals("")){
                    Path<String> stringPath=root.get("shopKeeper");
                    predicate=criteriaBuilder.like(stringPath,"%"+shopKeeper+"%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (tiXianTime!=null&&!tiXianTime.equals("")){
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String date1=tiXianTime+" 00:00:00";
                    String date2=tiXianTime+" 23:59:59";
                    Date date3= null;
                    Date date4= null;
                    try {
                        date3 = sdf.parse(date1);
                        date4 = sdf.parse(date2);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Expression<Date> stringPath=root.get("tiXianTime");
                    predicate=criteriaBuilder.between(stringPath,date3,date4);
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (status!=null&&!status.equals("")){
                    predicate=criteriaBuilder.equal(root.get("status"),status);
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
                return null;
            }
        };

        Sort sort;
        if (orderDirection!=null&&!orderDirection.equals("")&&orderField!=null&&!orderField.equals("")){
            sort=new Sort(Sort.Direction.fromString(orderDirection),orderField);
        }else {
            sort=new Sort(Sort.Direction.DESC,"tiXianTime");
        }
        //起始，长度
        Pageable pageable=new PageRequest(pageRequest.getPageNumber(),pageRequest.getPageSize(),sort);

        Page<ShopKeeperProfit> shopKeeperProfitPage=null;
        try {
            shopKeeperProfitPage=shopKeeperProfitRepository.findAll(userSpecification,pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shopKeeperProfitPage.getTotalElements()>0?shopKeeperProfitPage:null;
    }

    public Integer countAllByIsDel0(final String shopKeeper, final String tiXianTime, final Integer status) throws Exception{

        //特殊情况查询
        Specification<ShopKeeperProfit> userSpecification=new Specification<ShopKeeperProfit>() {
            @Override
            public Predicate toPredicate(Root<ShopKeeperProfit> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList= Lists.newArrayList();

                Predicate predicate=null;

                //匹配属性和属性对应的值
                predicate=criteriaBuilder.equal(root.get("isDel"),0);

                //添加
                predicateList.add(criteriaBuilder.and(predicate));

                if (shopKeeper!=null&&!shopKeeper.equals("")){
                    Path<String> stringPath=root.get("shopKeeper");
                    predicate=criteriaBuilder.like(stringPath,"%"+shopKeeper+"%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (tiXianTime!=null&&!tiXianTime.equals("")){
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String date1=tiXianTime+" 00:00:00";
                    String date2=tiXianTime+" 23:59:59";
                    Date date3= null;
                    Date date4= null;
                    try {
                        date3 = sdf.parse(date1);
                        date4 = sdf.parse(date2);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Expression<Date> stringPath=root.get("tiXianTime");
                    predicate=criteriaBuilder.between(stringPath,date3,date4);
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (status!=null&&!status.equals("")){
                    predicate=criteriaBuilder.equal(root.get("status"),status);
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
                return null;
            }
        };


        long count=0;
        try {
            count=shopKeeperProfitRepository.count(userSpecification);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Long(count).intValue();
    }

    @Transactional
    public void deleteByPrimaryKey(Integer id){
        shopKeeperProfitRepository.deleteByPrimaryKey(id);
    }

    @Transactional
    public void updateStatusOnRejectByPrimaryKey(Integer id){
        shopKeeperProfitRepository.updateStatusOnRejectByPrimaryKey(id);
    }

    @Transactional
    public void updateStatusOnOkByPrimaryKey(Integer id){
        shopKeeperProfitRepository.updateStatusOnOkByPrimaryKey(id);
    }

   public ShopKeeperProfit findById(Integer id){
       return shopKeeperProfitRepository.findById(id);
   }

}
