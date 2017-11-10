package cn.wangdian.Service;

import cn.wangdian.Model.Orders;
import cn.wangdian.Model.Shop;
import cn.wangdian.Model.ShopCart;
import cn.wangdian.Model.User;
import cn.wangdian.Repository.OrdersRepository;
import cn.wangdian.Repository.ShopCartRepository;
import cn.wangdian.Repository.ShopRepository;
import cn.wangdian.Repository.UserRepository;
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
public class ShopCartService {

    @Autowired
    private ShopCartRepository shopCartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShopRepository shopRepository;


//    public Page<ShopCart> findAllByIsDel0(final String shopOrderId, final String shopName, final String shopKeeper, final String shopOrderMan, final String shopOrderTime, final Integer status, String orderField, String orderDirection, PageRequest pageRequest) throws Exception{
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
//                if (shopOrderId!=null&&!shopOrderId.equals("")){
//                    Path<String> stringPath=root.get("shopOrderId");
//                    predicate=criteriaBuilder.like(stringPath,"%"+shopOrderId+"%");
//                    predicateList.add(criteriaBuilder.and(predicate));
//                }
//
//                if (shopName!=null&&!shopName.equals("")){
//                    Path<String> stringPath=root.get("shopName");
//                    predicate=criteriaBuilder.like(stringPath,"%"+shopName+"%");
//                    predicateList.add(criteriaBuilder.and(predicate));
//                }
//
//                if (shopKeeper!=null&&!shopKeeper.equals("")){
//                    Path<String> stringPath=root.get("shopKeeper");
//                    predicate=criteriaBuilder.like(stringPath,"%"+shopKeeper+"%");
//                    predicateList.add(criteriaBuilder.and(predicate));
//                }
//
//                if (shopOrderMan!=null&&!shopOrderMan.equals("")){
//                    Path<String> stringPath=root.get("shopOrderMan");
//                    predicate=criteriaBuilder.like(stringPath,"%"+shopOrderMan+"%");
//                    predicateList.add(criteriaBuilder.and(predicate));
//                }
//
//                if (shopOrderTime!=null&&!shopOrderTime.equals("")){
//                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    String date1=shopOrderTime+" 00:00:00";
//                    String date2=shopOrderTime+" 23:59:59";
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
//                if (status!=null&&!status.equals("")){
//                    predicate=criteriaBuilder.equal(root.get("status"),status);
//                    predicateList.add(criteriaBuilder.and(predicate));
//                }
//                criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
//                return null;
//            }
//        };
//
//        Sort sort;
//        if (orderDirection!=null&&!orderDirection.equals("")&&orderField!=null&&!orderField.equals("")){
//            sort=new Sort(Sort.Direction.fromString(orderDirection),orderField);
//        }else {
//            sort=new Sort(Sort.Direction.DESC,"shopOrderTime");
//        }
//        //起始，长度
//        Pageable pageable=new PageRequest(pageRequest.getPageNumber(),pageRequest.getPageSize(),sort);
//
//        Page<Orders> ordersPage=null;
//        try {
//            ordersPage=ordersRepository.findAll(ordersSpecification,pageable);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ordersPage.getTotalElements()>0?ordersPage:null;
//    }

//    public List<ShopCart> findAllByIsDel0AndUsername(String username){
//        return shopCartRepository.findAllByIsDel0AndUsername(username);
//    }
//
    public List<ShopCart> findAllByIsDel0AndUserIdAndStatus1(Integer userId){
        return shopCartRepository.findAllByIsDel0AndUserIdAndStatus1(userId);
    }

//    public List<ShopCart> findAllByIsDel0AndOrderId(Integer orderId){
//        return shopCartRepository.findAllByIsDel0AndOrderId(orderId);
//    }

    public void update(ShopCart shopCart){
        shopCartRepository.saveAndFlush(shopCart);
    }

    public Integer save(ShopCart shopCart){
       return shopCartRepository.save(shopCart).getId();
    }

    public Integer saveAndReturnId(ShopCart shopCart){
        return  shopCartRepository.save(shopCart).getId();
    }

    public ShopCart findById(Integer id){
        return shopCartRepository.findById(id);
    }

    public void deleteRealByPrimaryKey(ShopCart shopCart){
        shopCartRepository.delete(shopCart);
    }


    public float findAllSecondCostById(Integer id){
        return shopCartRepository.findAllSecondCostById(id);
    }

    public float findAllProfitsById(Integer id){
        return shopCartRepository.findAllProfitsById(id);
    }

    @Transactional
    public void deleteByPrimaryKey(Integer id){
        shopCartRepository.deleteByPrimaryKey(id);
    }

//    @Transactional
//    public void updateOrderIdById(Integer orderId,Integer shopCartId){
//        shopCartRepository.updateOrderIdById(orderId,shopCartId);
//    }

    @Transactional
    public void updateStatus1ById(Integer shopCartId){
        int status=1;
        shopCartRepository.updateStatusById(status,shopCartId);
    }

    @Transactional
    public void updateStatus2ById(Integer shopCartId){
        int status=2;
        shopCartRepository.updateStatusById(status,shopCartId);
    }

    /**
     * 不同用户的不同售价 单价
     * @param shopCart
     * @return
     */
    public float getCostByShopCartId(ShopCart shopCart, Integer vip){
//        Integer userId=shopCart.getUserId();
        Integer shopId=shopCart.getShopId();
//        User user=userRepository.findById(userId);
//        Integer vip=user.getVip();
        //根据不同的 vip 来返回不同的售价
        if (vip==2){
            return  shopRepository.findShopkeeperPriceById(shopId);
        }else if (vip==1){
            return shopRepository.findVipPriceById(shopId);
        }else {
            return shopRepository.findSecondCostById(shopId);
        }

    }
}
