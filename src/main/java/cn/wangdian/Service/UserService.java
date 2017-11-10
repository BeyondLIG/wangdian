package cn.wangdian.Service;

import cn.wangdian.Model.Shop;
import cn.wangdian.Model.ShopKeeper;
import cn.wangdian.Model.User;
import cn.wangdian.Repository.ShopKeeperRepository;
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
import sun.misc.BASE64Decoder;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by 25065 on 2016/9/15.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShopRepository shopRepository;

    public Page<User> findAllByIsDel0(final String username, final String nickname, final Integer status, String orderField, String orderDirection, PageRequest pageRequest) throws Exception{

        //特殊情况查询
        Specification<User> userSpecification=new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList= Lists.newArrayList();

                Predicate predicate=null;

                //匹配属性和属性对应的值
                predicate=criteriaBuilder.equal(root.get("isDel"),0);

                //添加
                predicateList.add(criteriaBuilder.and(predicate));

                if (username!=null&&!username.equals("")){
                    Path<String> stringPath=root.get("username");
                    predicate=criteriaBuilder.like(stringPath,"%"+username+"%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (nickname!=null&&!nickname.equals("")){
                    Path<String> stringPath=root.get("nickname");
                    predicate=criteriaBuilder.like(stringPath,"%"+nickname+"%");
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
            //第一个参数：升序或降序 第二个参数：实体类的变量
            sort=new Sort(Sort.Direction.fromString(orderDirection),orderField);
        }else {
            //============排序方式，按id号升序排序==========//
            sort=new Sort(Sort.Direction.ASC,"id");
        }
        //============分页==========//
        //起始，长度
        Pageable pageable=new PageRequest(pageRequest.getPageNumber(),pageRequest.getPageSize(),sort);

        Page<User> shopKeeperPage=null;
        try {
            //==========查找所有符合查询规范的user=========//
            shopKeeperPage=userRepository.findAll(userSpecification,pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shopKeeperPage.getTotalElements()>0?shopKeeperPage:null;
    }

    public Integer countAllByIsDel0(final String username, final String nickname, final Integer status) throws Exception{

        //特殊情况查询
        Specification<User> userSpecification=new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList= Lists.newArrayList();

                Predicate predicate=null;

                //匹配属性和属性对应的值
                predicate=criteriaBuilder.equal(root.get("isDel"),0);

                //添加
                predicateList.add(criteriaBuilder.and(predicate));

                if (username!=null&&!username.equals("")){
                    Path<String> stringPath=root.get("username");
                    predicate=criteriaBuilder.like(stringPath,"%"+username+"%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (nickname!=null&&!nickname.equals("")){
                    Path<String> stringPath=root.get("nickname");
                    predicate=criteriaBuilder.like(stringPath,"%"+nickname+"%");
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
            //==========查询未被删除的即isDel=0的user的数量===========//
            count=userRepository.count(userSpecification);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Long(count).intValue();
    }

    //=======查询username，可用于判定username是否重复=========//
    public String checkUsername(String username){
        return userRepository.checkUsername(username);
    }
    //=======查询telephone，可用于判定telephone是否重复=========//
    public String checkTelePhone(String telephone){
        return userRepository.checkTelePhone(telephone);
    }

    public String checkEmail(String email){
        return userRepository.checkEmail(email);
    }

    public String findUsernameById(Integer id){
        return userRepository.findUsernameById(id);
    }

    @Transactional
    public void update(User user){
        userRepository.saveAndFlush(user);
    }

    @Transactional
    public void save(User user){
        userRepository.save(user);
    }

    public User selectByUsername(String username){
        return userRepository.selectByUsername(username);
    }

    public User selectByEmail(String email){
        return userRepository.selectByEmail(email);
    }

    public String selectPasswordById(Integer id){
        return userRepository.selectPasswordById(id);
    }

    public User selectByTelephone(String telephone){
        return userRepository.selectByTelephone(telephone);
    }

    public String selectPasswordByEmail(String email) throws Exception{
        return new String(new BASE64Decoder().decodeBuffer(userRepository.selectPasswordByEmail(email)));
    }

    @Transactional
    public void updatePasswordByEmail(String email,String password) throws Exception{
        userRepository.updatePasswordByEmail(email,password);
    }

    @Transactional
    public void updatePasswordById(String password,Integer id){
        userRepository.updatePasswordById(password,id);
    }

    @Transactional
    public void updateNicknameById(String nickname,Integer id){
        userRepository.updateNicknameById(nickname,id);
    }

    @Transactional
    public void updateEmailById(String email,Integer id){
        userRepository.updateEmailById(email,id);
    }

    @Transactional
    public void updateTelephoneById(String telephone,Integer id){
        userRepository.updateTelephoneById(telephone,id);
    }

    public User findById(Integer id){
        return userRepository.findById(id);
    }

    @Transactional
    public void suoDingById(Integer id){
        userRepository.suoJieDing(id,1);
    }

    @Transactional
    public void jieDingById(Integer id){
        userRepository.suoJieDing(id,0);
    }

    @Transactional
    public void deleteByPrimaryKey(Integer id){
        userRepository.deleteByPrimaryKey(id);
    }


    public Page<User> findAllUserByShopKeeperId(final Integer shopKeeperId, Integer status, String orderField, String orderDirection, PageRequest pageRequest) throws Exception{

        //特殊情况查询
        //=========创建查询规范===========//
        Specification<User> userSpecification=new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList= Lists.newArrayList();

                Predicate predicate=null;

                //匹配属性和属性对应的值
                predicate=criteriaBuilder.equal(root.get("isDel"),0);

                //添加
                predicateList.add(criteriaBuilder.and(predicate));

                if (status != null && !status.equals("")) {
                    predicate = criteriaBuilder.equal(root.get("status"), status);
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (shopKeeperId!=null&&!shopKeeperId.equals("")){
                    predicate=criteriaBuilder.equal(root.get("shopkeeper").get("id"),shopKeeperId);
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

        Page<User> shopKeeperPage=null;
        try {
            shopKeeperPage=userRepository.findAll(userSpecification,pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shopKeeperPage.getTotalElements()>0?shopKeeperPage:null;
    }

//    /**
//     * 查看店主的所有直接用户
//     * @param shopKeeperId
//     * @param page
//     * @return
//     */
//    public List<User> findAllByIsDel0AndShopkeeperId(Integer shopKeeperId,Integer page){
//        Sort sort=new Sort(Sort.Direction.ASC,"id");
//        Pageable pageable=new PageRequest(page,20,sort);
//        return userRepository.getAllUserByShopkeeperId(shopKeeperId);
//    }

    //2.查找普通用户和vip用户,根据传进来的vip的值可以查找普通用户和vip用户
    public Page<User> findAllByIsDel0AndVip(final Integer vip, Integer status, String orderField, String orderDirection, PageRequest pageRequest) throws Exception{

        //特殊情况查询
        //=========创建查询规范===========//
        Specification<User> userSpecification=new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList= Lists.newArrayList();

                Predicate predicate=null;

                //匹配属性和属性对应的值
                predicate=criteriaBuilder.equal(root.get("isDel"),0);

                //添加
                predicateList.add(criteriaBuilder.and(predicate));

                if (status != null && !status.equals("")) {
                    predicate = criteriaBuilder.equal(root.get("status"), status);
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (vip!=null&&!vip.equals("")){
                    predicate=criteriaBuilder.equal(root.get("vip"),vip);
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

        Page<User> shopKeeperPage=null;
        try {
            shopKeeperPage=userRepository.findAll(userSpecification,pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shopKeeperPage.getTotalElements()>0?shopKeeperPage:null;
    }


//    //============查询用户收藏的商品============//
//    public Page<Shop> findAllByIsDel0AndID(final Integer user_id ,String orderField, String orderDirection, PageRequest pageRequest) throws Exception {
//
//        //特殊情况查询
//        //创建查询规范
//        Specification<Shop> shopSpecification = new Specification<Shop>() {
//            @Override
//            public Predicate toPredicate(Root<Shop> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                List<Predicate> predicateList = Lists.newArrayList();
//
//                Predicate predicate = null;
//
//                //匹配属性和属性对应的值
//                predicate = criteriaBuilder.equal(root.get("isDel"), 0);
//
//                //添加
//                predicateList.add(criteriaBuilder.and(predicate));
//
//                if (user_id != null && !user_id.equals("")) {
//                    predicate = criteriaBuilder.equal(root.get("user_id"), user_id);
//                    predicateList.add(criteriaBuilder.and(predicate));
//                }
//                criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
//                return null;
//            }
//        };
//
//        Sort sort;
//        if (orderDirection != null && !orderDirection.equals("") && orderField != null && !orderField.equals("")) {
//            sort = new Sort(Sort.Direction.fromString(orderDirection), orderField);
//        } else {
//            sort = new Sort(Sort.Direction.ASC, "id");
//        }
//        //起始，长度
//        Pageable pageable = new PageRequest(pageRequest.getPageNumber(), pageRequest.getPageSize(), sort);
//
//        Page<Shop> shopPage = null;
//        try {
//            shopPage = shopRepository.findAll(shopSpecification, pageable);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return shopPage.getTotalElements() > 0 ? shopPage : null;
//    }



    //获取花费金额
    public float getPayById(Integer id){
        return userRepository.getPayById(id);
    }

    //更新花费金额
    @Transactional
    public void updatePayById(float pay,Integer id){
        userRepository.updatePayById(pay,id);
    }

    @Transactional
    public void updatePasswordByTelephone(String password, String telephone) {
        userRepository.updatePasswordByByTelephone(password, telephone);
    }

    @Transactional
    public void suoDingByUsername(String username) {
        userRepository.updateStatusByUsername(username, 1);
    }

    @Transactional
    public void jieSuoByUsername(String username) {
        userRepository.updateStatusByUsername(username, 0);
    }

    @Transactional
    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

    public User findByTelephone(String telephone) {
        return userRepository.findByTelephone(telephone);
    }

    @Transactional
    public void realDeleteById(Integer id) {
        userRepository.delete(id);
    }
}