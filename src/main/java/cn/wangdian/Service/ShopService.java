package cn.wangdian.Service;

import cn.wangdian.Model.Shop;
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
import java.util.List;

/**
 * Created by 25065 on 2016/9/15.
 */
@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;


    public Page<Shop> findAllByIsDel0(final String number,final String name, final String shopType,final String shopModel, final Integer status,final Integer isRecommend, String orderField, String orderDirection, PageRequest pageRequest) throws Exception{

        //特殊情况查询
        Specification<Shop> userSpecification=new Specification<Shop>() {
            @Override
            public Predicate toPredicate(Root<Shop> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList= Lists.newArrayList();

                Predicate predicate=null;

                //匹配属性和属性对应的值
                predicate=criteriaBuilder.equal(root.get("isDel"),0);

                //添加
                predicateList.add(criteriaBuilder.and(predicate));

                if (number!=null&&!number.equals("")){
                    Path<String> stringPath=root.get("number");
                    predicate=criteriaBuilder.like(stringPath,"%"+number+"%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (name!=null&&!name.equals("")){
                    Path<String> stringPath=root.get("name");
                    predicate=criteriaBuilder.like(stringPath,"%"+name+"%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (shopType!=null&&!shopType.equals("")){
                    Path<String> stringPath=root.get("shopTypeName");
                    predicate=criteriaBuilder.like(stringPath,"%"+shopType+"%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (shopModel!=null&&!shopModel.equals("")){
                    Path<String> stringPath=root.get("shopModel");
                    predicate=criteriaBuilder.like(stringPath,"%"+shopModel+"%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (status!=null&&!status.equals("")){
                    predicate=criteriaBuilder.equal(root.get("status"),status);
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (isRecommend!=null&&!isRecommend.equals("")){
                    predicate=criteriaBuilder.equal(root.get("isRecommend"),isRecommend);
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
            sort=new Sort(Sort.Direction.ASC,"id");
        }
        //起始，长度
        Pageable pageable=new PageRequest(pageRequest.getPageNumber(),pageRequest.getPageSize(),sort);

        Page<Shop> shopPage=null;
        try {
            shopPage=shopRepository.findAll(userSpecification,pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shopPage.getTotalElements()>0?shopPage:null;
    }

    public Integer countAllByIsDel0(final String number,final String name, final String shopType,final String shopModel, final Integer status,final Integer isRecommend) throws Exception{

        //特殊情况查询
        Specification<Shop> userSpecification=new Specification<Shop>() {
            @Override
            public Predicate toPredicate(Root<Shop> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList= Lists.newArrayList();

                Predicate predicate=null;

                //匹配属性和属性对应的值
                predicate=criteriaBuilder.equal(root.get("isDel"),0);

                //添加
                predicateList.add(criteriaBuilder.and(predicate));

                if (number!=null&&!number.equals("")){
                    Path<String> stringPath=root.get("number");
                    predicate=criteriaBuilder.like(stringPath,"%"+number+"%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (name!=null&&!name.equals("")){
                    Path<String> stringPath=root.get("name");
                    predicate=criteriaBuilder.like(stringPath,"%"+name+"%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (shopType!=null&&!shopType.equals("")){
                    Path<String> stringPath=root.get("shopTypeName");
                    predicate=criteriaBuilder.like(stringPath,"%"+shopType+"%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (shopModel!=null&&!shopModel.equals("")){
                    Path<String> stringPath=root.get("shopModel");
                    predicate=criteriaBuilder.like(stringPath,"%"+shopModel+"%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (status!=null&&!status.equals("")){
                    predicate=criteriaBuilder.equal(root.get("status"),status);
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (isRecommend!=null&&!isRecommend.equals("")){
                    predicate=criteriaBuilder.equal(root.get("isRecommend"),isRecommend);
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
                return null;
            }
        };

        long count=0;
        try {
            count=shopRepository.count(userSpecification);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Long(count).intValue();
    }

    public List<Shop> findAllByIsDel0(){
        return shopRepository.findAllByIsDel0();
    }

    public List<Shop> findAllByIsDel0AndShopName(String shopName,Integer page){
        Sort sort=new Sort(Sort.Direction.DESC,"inTime");
        Pageable pageable=new PageRequest(page,20,sort);
        return shopRepository.findAllByIsDel0AndShopName(shopName,pageable);
    }

    public List<Shop> findAllByIsDel0AndIsRecommend1Limit(Integer page, Integer size){
        Sort sort = new Sort(Sort.Direction.DESC, "inTime");
        Pageable pageable = new PageRequest(page, size, sort);
        return shopRepository.findAllByIsDel0AndIsRecommend1Limit(pageable);
    }

    public List<String> findAllShopTypeByIsDel0(){
        return shopRepository.findAllShopTypeByIsDel0();
    }

    public List<String> findAllShopModelByIsDel0AndType(String shopType){
        return shopRepository.findAllShopModelByIsDel0AndType(shopType);
    }

    public List<Shop> findAllByIsDel0AndShopTypeAndShopModel(String shopType,Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "inTime");
        Pageable pageable = new PageRequest(page, size, sort);
        return shopRepository.findAllByIsDel0AndShopTypeAndShopModel(shopType, pageable);
    }


    public String checkNumber(String number){
        return shopRepository.checkNumber(number);
    }

    public String findNumberById(Integer id){
        return shopRepository.findNumberById(id);
    }

    public void update(Shop shop){
        shopRepository.saveAndFlush(shop);
    }

    public Integer save(Shop shop){
        Integer id=shopRepository.save(shop).getId();
        return id;
    }

//    public User selectByUsername(String username){
//        return userRepository.selectByUsername(username);
//    }
//
    public Shop findById(Integer id){
        return shopRepository.findById(id);
    }

    @Transactional
    public void xiaJiaById(Integer id){
        shopRepository.shangXiaJia(id,1);
    }

    @Transactional
    public void shangJiaById(Integer id){
        shopRepository.shangXiaJia(id,0);
    }

    @Transactional
    public void deleteByPrimaryKey(Integer id){
        shopRepository.deleteByPrimaryKey(id);
    }


    //==================NEW=================//
    //通过vip的值确定商品的价格，vip=0 普通用户价格 vip=1 vip用户价格 vip=2 店主价格
    public float getPriceById(Integer id,Integer vip){
        if(vip==0){
            return shopRepository.findSecondCostById(id);
        }else if (vip==1){
            return shopRepository.findVipPriceById(id);
        }else {
            return shopRepository.findShopkeeperPriceById(id);
        }
    }

    public List<Shop> findAllByIsDel0OrShopNameOrShopTypeOrShopModel(String keyWord,int page, int size){
        Sort sort=new Sort(Sort.Direction.DESC,"inTime");
        Pageable pageable=new PageRequest(page,size,sort);
        return shopRepository.findAllByIsDel0OrShopNameOrShopTypeOrShopModel(keyWord,pageable);
    }

    /**
     * 通过普通商品的 id 来查询商品的成本价
     * @param id 普通商品的 id
     * @return 返回相应的普通商品的成本价
     */
    public float findFirstCostById(Integer id) {
        return shopRepository.findFirstCostById(id);
    }

    /**
     * 通过 id 查看某个商品是否已经过期了
     */
    public Shop findIsDelById(Integer id) {
        return shopRepository.findByIsDel0AndStatus0AndId(id);
    }
}
