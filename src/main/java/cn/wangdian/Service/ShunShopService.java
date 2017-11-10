package cn.wangdian.Service;

import cn.wangdian.Model.ShunShop;
import cn.wangdian.Repository.ShunShopRepository;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 25065 on 2016/9/15.
 */
@Service
public class ShunShopService {

    @Autowired
    private ShunShopRepository shunShopRepository;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public Page<ShunShop> findAllByIsDel0(final String number, final String name, final String shopType, final String shopModel, final Integer status, String orderField, String orderDirection, PageRequest pageRequest) throws Exception{

        //特殊情况查询
        Specification<ShunShop> userSpecification=new Specification<ShunShop>() {
            @Override
            public Predicate toPredicate(Root<ShunShop> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
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

        Page<ShunShop> shunShopPage=null;
        try {
            shunShopPage=shunShopRepository.findAll(userSpecification,pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shunShopPage.getTotalElements()>0?shunShopPage:null;
    }

    public Integer countAllByIsDel0(final String number, final String name, final String shopType, final String shopModel, final Integer status) throws Exception{

        //特殊情况查询
        Specification<ShunShop> userSpecification=new Specification<ShunShop>() {
            @Override
            public Predicate toPredicate(Root<ShunShop> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
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
                criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
                return null;
            }
        };

        long count=0;
        try {
            count=shunShopRepository.count(userSpecification);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Long(count).intValue();
    }

    /**
     * 只查询那些还没有到期的瞬时秒杀商品
     * @param page
     * @param size
     * @return
     */
    public List<ShunShop> findAllByIsDel0Limit(Integer page, Integer size){
        Sort sort=new Sort(Sort.Direction.DESC,"inTime");
        Pageable pageable=new PageRequest(page,size,sort);
//        Date now = new Date();
        return shunShopRepository.findAllByIsDel0Limit(pageable);
    }

    public String checkNumber(String number){
        return shunShopRepository.checkNumber(number);
    }

    public String findNumberById(Integer id){
        return shunShopRepository.findNumberById(id);
    }

    public void update(ShunShop shop){
        shunShopRepository.saveAndFlush(shop);
    }

    public Integer save(ShunShop shop){
       return shunShopRepository.save(shop).getId();
    }

//    public User selectByUsername(String username){
//        return userRepository.selectByUsername(username);
//    }
//
    public ShunShop findById(Integer id){
        return shunShopRepository.findById(id);
    }

    @Transactional
    public void xiaJiaById(Integer id){
        shunShopRepository.shangXiaJia(id,1);
    }

    @Transactional
    public void shangJiaById(Integer id){
        shunShopRepository.shangXiaJia(id,0);
    }

    @Transactional
    public void deleteByPrimaryKey(Integer id){
        shunShopRepository.deleteByPrimaryKey(id);
    }

    /**
     * 通过秒杀商品 ShunShop id 去查找该秒杀商品的秒杀价格
     * @param id 需要被查询的秒杀商品的 id
     * @return 对应秒杀商品的秒杀价格
     */
    public Float findThirdById(Integer id) {
        return shunShopRepository.findThirdCostById(id);
    }

    /**
     * 通过秒杀商品 ShunShop id 去查找该秒杀商品的成本价价格
     * @param id 需要被查询的id
     * @return 对应秒杀商品的成本价
     */
    public Float findFirstCostById(Integer id) {
        return shunShopRepository.findFirstCostById(id);
    }

    /**
     * 查看某个瞬杀商品是否已经是删除了或者是已经过期了
     */
    public ShunShop findByIsDel0AndNoEndAndId(Integer id) {
        return shunShopRepository.findByIsDel0AndStatus0AndNoEndAndId(new Date(), id);
    }
}
