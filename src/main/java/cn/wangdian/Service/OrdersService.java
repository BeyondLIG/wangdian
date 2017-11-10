package cn.wangdian.Service;

import cn.wangdian.Model.Orders;
import cn.wangdian.Model.Shop;
import cn.wangdian.Repository.OrdersRepository;
import cn.wangdian.Repository.ShopRepository;
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
 * Created by 25065 on 2016/9/15.
 */
@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;


    public Page<Orders> findAllByIsDel0(final String shopOrderId, final String shopName,final String shopKeeper, final String shopOrderMan,final String shopOrderTime, final Integer status, String orderField, String orderDirection, PageRequest pageRequest) throws Exception{

        //特殊情况查询
        Specification<Orders> ordersSpecification=new Specification<Orders>() {
            @Override
            public Predicate toPredicate(Root<Orders> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList= Lists.newArrayList();

                Predicate predicate=null;

                //匹配属性和属性对应的值
                predicate=criteriaBuilder.equal(root.get("isDel"),0);

                //添加
                predicateList.add(criteriaBuilder.and(predicate));

                if (shopOrderId!=null&&!shopOrderId.equals("")){
                    Path<String> stringPath=root.get("shopOrderId");
                    predicate=criteriaBuilder.like(stringPath,"%"+shopOrderId+"%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (shopName!=null&&!shopName.equals("")){
                    Path<String> stringPath=root.get("shopName");
                    predicate=criteriaBuilder.like(stringPath,"%"+shopName+"%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (shopKeeper!=null&&!shopKeeper.equals("")){
                    Path<String> stringPath=root.get("shopKeeper");
                    predicate=criteriaBuilder.like(stringPath,"%"+shopKeeper+"%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (shopOrderMan!=null&&!shopOrderMan.equals("")){
                    Path<String> stringPath=root.get("shopOrderMan");
                    predicate=criteriaBuilder.like(stringPath,"%"+shopOrderMan+"%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (shopOrderTime!=null&&!shopOrderTime.equals("")){
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String date1=shopOrderTime+" 00:00:00";
                    String date2=shopOrderTime+" 23:59:59";
                    Date date3= null;
                    Date date4= null;
                    try {
                        date3 = sdf.parse(date1);
                        date4 = sdf.parse(date2);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Expression<Date> stringPath=root.get("shopOrderTime");
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
            sort=new Sort(Sort.Direction.DESC,"shopOrderTime");
        }
        //起始，长度
        Pageable pageable=new PageRequest(pageRequest.getPageNumber(),pageRequest.getPageSize(),sort);

        Page<Orders> ordersPage=null;
        try {
            ordersPage=ordersRepository.findAll(ordersSpecification,pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ordersPage.getTotalElements()>0?ordersPage:null;
    }

    public Integer countAllByIsDel0(final String shopOrderId, final String shopName,final String shopKeeper, final String shopOrderMan,final String shopOrderTime, final Integer status) throws Exception{

        //特殊情况查询
        Specification<Orders> ordersSpecification=new Specification<Orders>() {
            @Override
            public Predicate toPredicate(Root<Orders> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList= Lists.newArrayList();

                Predicate predicate=null;

                //匹配属性和属性对应的值
                predicate=criteriaBuilder.equal(root.get("isDel"),0);

                //添加
                predicateList.add(criteriaBuilder.and(predicate));

                if (shopOrderId!=null&&!shopOrderId.equals("")){
                    Path<String> stringPath=root.get("shopOrderId");
                    predicate=criteriaBuilder.like(stringPath,"%"+shopOrderId+"%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (shopName!=null&&!shopName.equals("")){
                    Path<String> stringPath=root.get("shopName");
                    predicate=criteriaBuilder.like(stringPath,"%"+shopName+"%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (shopKeeper!=null&&!shopKeeper.equals("")){
                    Path<String> stringPath=root.get("shopKeeper");
                    predicate=criteriaBuilder.like(stringPath,"%"+shopKeeper+"%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (shopOrderMan!=null&&!shopOrderMan.equals("")){
                    Path<String> stringPath=root.get("shopOrderMan");
                    predicate=criteriaBuilder.like(stringPath,"%"+shopOrderMan+"%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (shopOrderTime!=null&&!shopOrderTime.equals("")){
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String date1=shopOrderTime+" 00:00:00";
                    String date2=shopOrderTime+" 23:59:59";
                    Date date3= null;
                    Date date4= null;
                    try {
                        date3 = sdf.parse(date1);
                        date4 = sdf.parse(date2);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Expression<Date> stringPath=root.get("shopOrderTime");
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
            count=ordersRepository.count(ordersSpecification);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Long(count).intValue();
    }

    public List<Orders> findAllByIsDel0AndIsDelFromUser0AndUsername(String username){
        return ordersRepository.findAllByIsDel0AndIsDelFromUser0AndUsername(username);
    }

    public List<Float> findAllByShopKeeperAndStatus3(String shopKeeper){
        return ordersRepository.findAllByShopKeeperAndStatus3(shopKeeper);
    }

    public void update(Orders orders){
        ordersRepository.saveAndFlush(orders);
    }


    public void save(Orders orders){
        ordersRepository.save(orders);
    }

    public Integer saveAndReturnId(Orders orders){
        return  ordersRepository.saveAndFlush(orders).getId();
    }

    public Orders findById(Integer id){
        return ordersRepository.findById(id);
    }

    public Orders findByShopOrderId(String shopOrderId){
        return ordersRepository.findByShopOrderId(shopOrderId);
    }

    @Transactional
    public void updateStatusByShopOrderId(Integer status,String shopOrderId){
        ordersRepository.updateStatusByShopOrderId(status,shopOrderId);
    }

    public int findStatusByByShopOrderId(String shopOrderId){
        return ordersRepository.findStatusByByShopOrderId(shopOrderId);
    }

    public Integer countAllByIsDel0AndToday(final String today){

        //特殊情况查询
        Specification<Orders> ordersSpecification=new Specification<Orders>() {
            @Override
            public Predicate toPredicate(Root<Orders> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList= Lists.newArrayList();

                Predicate predicate=null;

                //匹配属性和属性对应的值
                predicate=criteriaBuilder.equal(root.get("isDel"),0);

                //添加
                predicateList.add(criteriaBuilder.and(predicate));

                if (today!=null&&!today.equals("")){
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String date1=today+" 00:00:00";
                    String date2=today+" 23:59:59";
                    Date date3= null;
                    Date date4= null;
                    try {
                        date3 = sdf.parse(date1);
                        date4 = sdf.parse(date2);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Expression<Date> stringPath=root.get("shopOrderTime");
                    predicate=criteriaBuilder.between(stringPath,date3,date4);
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
                return null;
            }
        };

        long count=0;
        try {
            count=ordersRepository.count(ordersSpecification);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Long(count).intValue();
    }

    @Transactional
    public void deleteByPrimaryKey(Integer id){
        ordersRepository.deleteByPrimaryKey(id);
    }

    @Transactional
    public void deleteIsDelFromUser1ByPrimaryKey(Integer id){
        ordersRepository.deleteIsDelFromUser1ByPrimaryKey(id);
    }


    public long countAllByIsDel0(final String shopType, final String shopName,final String shopModel, final String shopNumber,final String shopKeeper,final String startTime,final String endTime,final Integer status) throws Exception{

        //特殊情况查询
        Specification<Orders> ordersSpecification=new Specification<Orders>() {
            @Override
            public Predicate toPredicate(Root<Orders> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList= Lists.newArrayList();

                Predicate predicate=null;

                //匹配属性和属性对应的值
                predicate=criteriaBuilder.equal(root.get("isDel"),0);

                //添加
                predicateList.add(criteriaBuilder.and(predicate));

                //实现一系列的模糊查询
                if (shopType!=null&&!shopType.equals("")){
                    Path<String> stringPath=root.get("shopType");
                    predicate=criteriaBuilder.like(stringPath,"%"+shopType+"%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (shopName!=null&&!shopName.equals("")){
                    Path<String> stringPath=root.get("shopName");
                    predicate=criteriaBuilder.like(stringPath,"%"+shopName+"%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (shopModel!=null&&!shopModel.equals("")){
                    Path<String> stringPath=root.get("shopModel");
                    predicate=criteriaBuilder.like(stringPath,"%"+shopModel+"%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (shopNumber!=null&&!shopNumber.equals("")){
                    Path<String> stringPath=root.get("shopNumber");
                    predicate=criteriaBuilder.like(stringPath,"%"+shopNumber+"%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (shopKeeper!=null&&!shopKeeper.equals("")){
                    Path<String> stringPath=root.get("shopKeeper");
                    predicate=criteriaBuilder.like(stringPath,"%"+shopKeeper+"%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }
                //查询在特定时间段中间的订单信息
                if (startTime!=null&&!startTime.equals("")){
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String date1=startTime+" 00:00:00";
                    String date2=endTime+" 00:00:00";
                    Date date3= null;
                    Date date4= null;
                    try {
                        date3 = sdf.parse(date1);
                        date4 = sdf.parse(date2);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Expression<Date> stringPath=root.get("shopOrderTime");
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
            count=ordersRepository.count(ordersSpecification);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 返回指定用户的所有待收货订单
     * @param user 查询的用户
     */
    public List<Orders> findAllByUserAndReceiving(Integer user) {
        return ordersRepository.findAllByUserAndReceiving(user);
    }


//    public long countShopType(final String shopType, final String shopName,final String shopModel, final String shopNumber,final String shopKeeper,final String startTime,final String endTime) throws Exception{
//
//        //特殊情况查询
//        Specification<Orders> ordersSpecification=new Specification<Orders>() {
//            @Override
//            public Predicate toPredicate(Root<Orders> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                List<Predicate> predicateList= Lists.newArrayList();
//
//                Predicate predicate=null;
//
//                //匹配属性和属性对应的值
//                predicate=criteriaBuilder.equal(root.get("isDel"),0);
//
//                //添加
//                predicateList.add(criteriaBuilder.and(predicate));
//
//                if (shopType!=null&&!shopType.equals("")){
//                    Path<String> stringPath=root.get("shopType");
//                    predicate=criteriaBuilder.like(stringPath,"%"+shopType+"%");
//                    predicateList.add(criteriaBuilder.and(predicate));
//                }
//
//                if (shopName!=null&&!shopName.equals("")){
//                    Path<String> stringPath=root.get("shopName");
//                    predicate=criteriaBuilder.like(stringPath,"%"+shopName+"%");
//                    predicateList.add(criteriaBuilder.and(predicate));
//                }
//
//                if (shopModel!=null&&!shopModel.equals("")){
//                    Path<String> stringPath=root.get("shopModel");
//                    predicate=criteriaBuilder.like(stringPath,"%"+shopModel+"%");
//                    predicateList.add(criteriaBuilder.and(predicate));
//                }
//
//                if (shopNumber!=null&&!shopNumber.equals("")){
//                    Path<String> stringPath=root.get("shopNumber");
//                    predicate=criteriaBuilder.like(stringPath,"%"+shopNumber+"%");
//                    predicateList.add(criteriaBuilder.and(predicate));
//                }
//
//                if (shopKeeper!=null&&!shopKeeper.equals("")){
//                    Path<String> stringPath=root.get("shopKeeper");
//                    predicate=criteriaBuilder.like(stringPath,"%"+shopKeeper+"%");
//                    predicateList.add(criteriaBuilder.and(predicate));
//                }
//
//                if (startTime!=null&&!startTime.equals("")){
//                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    String date1=startTime+" 00:00:00";
//                    String date2=endTime+" 00:00:00";
//                    Date date3= null;
//                    Date date4= null;
//                    try {
//                        date3 = sdf.parse(date1);
//                        date4 = sdf.parse(date2);
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                    Expression<Date> stringPath=root.get("shopOrderTime");
//                    predicate=criteriaBuilder.between(stringPath,date3,date4);
//                    predicateList.add(criteriaBuilder.and(predicate));
//                }
//
//
//                    predicate=criteriaBuilder.equal(root.get("status"),3);
//                    predicateList.add(criteriaBuilder.and(predicate));
//
//
//                criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
//                return null;
//            }
//        };
//
//        long count=0;
//        try {
//            count=ordersRepository.countShopType(ordersSpecification).size();
//            ordersRepository.
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return count;
//    }


    //获取订单的金额
    public float getOrdersCostByShopOrderId(String  id){
        return ordersRepository.getOrdersCostByShopOrderId(id);
    }
    //查看店主的总销售额

    public float findAllProfitByStatus3(){
        List<Float> profitList=ordersRepository.findAllProfitByStatus3();
        float allProfit=0;
        for(float profit:profitList){
            allProfit+=profit;
        }
        return allProfit;
    }
    //查看店主的总销售额
    public float findOrdersCostByStatus3(){
        List<Float> ordersCostList = ordersRepository.findOrdersCostByStatus3();
        float allCost=0;
        for (float cost:ordersCostList){
            allCost+=cost;
        }
        return allCost;
    }

}
