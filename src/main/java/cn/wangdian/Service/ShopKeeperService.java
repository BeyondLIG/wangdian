package cn.wangdian.Service;

import cn.wangdian.Model.ShopKeeper;
import cn.wangdian.Model.User;
import cn.wangdian.Repository.ShopKeeperRepository;
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
import java.util.Date;
import java.util.List;

/**
 * Created by 25065 on 2016/9/15.
 */
@Service
public class ShopKeeperService {

    @Autowired
    private ShopKeeperRepository shopKeeperRepository;
    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private ShopRepository shopRepository;


    //==========分页查找所有未被删除的店主===========//
    public Page<ShopKeeper> findAllByIsDel0(final String username, final String nickname, final Integer status, String orderField, String orderDirection, PageRequest pageRequest) throws Exception {

        //特殊情况查询
        Specification<ShopKeeper> shopKeeperSpecification = new Specification<ShopKeeper>() {
            @Override
            public Predicate toPredicate(Root<ShopKeeper> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = Lists.newArrayList();

                Predicate predicate = null;

                //匹配属性和属性对应的值
                predicate = criteriaBuilder.equal(root.get("isDel"), 0);

                //添加
                predicateList.add(criteriaBuilder.and(predicate));

                if (username != null && !username.equals("")) {
                    Path<String> stringPath = root.get("username");
                    predicate = criteriaBuilder.like(stringPath, "%" + username + "%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (nickname != null && !nickname.equals("")) {
                    Path<String> stringPath = root.get("nickname");
                    predicate = criteriaBuilder.like(stringPath, "%" + nickname + "%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (status != null && !status.equals("")) {
                    predicate = criteriaBuilder.equal(root.get("status"), status);
                    predicateList.add(criteriaBuilder.and(predicate));
                }
                criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
                return null;
            }
        };

        Sort sort;
        if (orderDirection != null && !orderDirection.equals("") && orderField != null && !orderField.equals("")) {
            sort = new Sort(Sort.Direction.fromString(orderDirection), orderField);
        } else {
            sort = new Sort(Sort.Direction.ASC, "id");
        }
        //起始，长度
        Pageable pageable = new PageRequest(pageRequest.getPageNumber(), pageRequest.getPageSize(), sort);

        Page<ShopKeeper> shopKeeperPage = null;
        try {
            shopKeeperPage = shopKeeperRepository.findAll(shopKeeperSpecification, pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shopKeeperPage.getTotalElements() > 0 ? shopKeeperPage : null;
    }

    public Integer countAllByIsDel0(final String username, final String nickname, final Integer status) throws Exception {

        //特殊情况查询
        Specification<ShopKeeper> shopKeeperSpecification = new Specification<ShopKeeper>() {
            @Override
            public Predicate toPredicate(Root<ShopKeeper> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = Lists.newArrayList();

                Predicate predicate = null;

                //匹配属性和属性对应的值
                predicate = criteriaBuilder.equal(root.get("isDel"), 0);

                //添加
                predicateList.add(criteriaBuilder.and(predicate));

                if (username != null && !username.equals("")) {
                    Path<String> stringPath = root.get("username");
                    predicate = criteriaBuilder.like(stringPath, "%" + username + "%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (nickname != null && !nickname.equals("")) {
                    Path<String> stringPath = root.get("nickname");
                    predicate = criteriaBuilder.like(stringPath, "%" + nickname + "%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (status != null && !status.equals("")) {
                    predicate = criteriaBuilder.equal(root.get("status"), status);
                    predicateList.add(criteriaBuilder.and(predicate));
                }
                criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
                return null;
            }
        };

        long count = 0;
        try {
            count = shopKeeperRepository.count(shopKeeperSpecification);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Long(count).intValue();
    }


    public String checkUsername(String username) {
        return shopKeeperRepository.checkUsername(username);
    }

    public String findUsernameById(Integer id) {
        return shopKeeperRepository.findUsernameById(id);
    }

    @Transactional
    public void update(ShopKeeper shopKeeper) {
        shopKeeperRepository.saveAndFlush(shopKeeper);
    }

    @Transactional
    public void updateAllProfitById(float allProfit, Integer id) {
        shopKeeperRepository.updateAllProfitById(allProfit, id);
    }

    @Transactional
    public void save(ShopKeeper shopKeeper) {
        shopKeeperRepository.save(shopKeeper);
    }

    public ShopKeeper selectByUsername(String username) {
        return shopKeeperRepository.selectByUsername(username);
    }

    public ShopKeeper findById(Integer id) {
        return shopKeeperRepository.findById(id);
    }

    public String findWebUrlBySecondDomain(String secondDomain) {
        return shopKeeperRepository.findWebUrlBySecondDomain(secondDomain);
    }

    @Transactional
    public void suoDingById(Integer id) {
        shopKeeperRepository.suoJieDing(id, 1);
    }

    @Transactional
    public void jieDingById(Integer id) {
        shopKeeperRepository.suoJieDing(id, 0);
    }

    @Transactional
    public void deleteByPrimaryKey(Integer id) {
        shopKeeperRepository.deleteByPrimaryKey(id);
    }

    //TODO 还没通过测试

    /**
     * 删除店主
     * @param id 将要被删除的店主的 id
     */
    //1.删除店主前店主的所有用户归给店主的上一级店主
    @Transactional
    public void deleteShopKeeperById(Integer id) {
        //上级店主的总用户数目不变
        ShopKeeper shopKeeperLow = findById(id);
        //获取删除的店主的上一级店主的id号
        //获取上一级店主id号
        Integer belong = shopKeeperLow.getBelong();
        if (belong != 0) {
            ShopKeeper shopKeeperUp = shopKeeperRepository.findById(belong);
            //将删除的店主的所有用户与上一级店主关联
            List<User> userList = shopKeeperLow.getUserList();
            for (User user : userList) {
                //能够自动级联，同步修改 user.shopkeeper_id 为 belong
                shopKeeperUp.addUserList(user);
//            userRepository.updateUserShopkeeperIdById(belong,user.getId());
            }

            //2.1改变店主直接用户和总用户
            Integer direct_user_delete = shopKeeperRepository.findDirectUserNumberById(id);//删除的店主的直接用户数
            Integer direct_user_belong = shopKeeperRepository.findDirectUserNumberById(belong);//上一级店主的直接用户数
            Integer directUserNumber = direct_user_belong + direct_user_delete;//删除的店主的上一级店主的新总直接用户数
            shopKeeperRepository.updateDirectUserNumberById(directUserNumber,belong);

            //2.2改变上级店主的直接店主数和总店主数
            Integer directShopKeeperNumber_belong=shopKeeperRepository.findDirectShopkeeperNumberById(belong);//上一级店主的直接店主数
            Integer directShopKeeperNumber_delete=shopKeeperRepository.findDirectShopkeeperNumberById(id);//删除的店主的直接店主数
            Integer directShopKeeperNumber=directShopKeeperNumber_belong + directShopKeeperNumber_delete - 1;
            shopKeeperRepository.updateDirectShopkeeperNumberById(directShopKeeperNumber,belong);//直接店主数-1
            //上级店主的总店主数-1
            Integer allShopKeeperNumber, level;
            while(belong!=0){
                allShopKeeperNumber=shopKeeperRepository.findAllShopkeeperNumberById(belong) - 1;
                shopKeeperRepository.updateAllShopkeeperNumberById(allShopKeeperNumber,belong);
                //重新设置用户的等级
                directShopKeeperNumber = shopKeeperRepository.findDirectShopkeeperNumberById(belong);
                level = 0;
                /**
                 * TODO 需要更改数字大小
                 * 原本应该的数据量
                 * 5 36
                 * 20 666
                 * 现在为了测试，更改数据量改为
                 * 3    5
                 * 5    8
                 */
                if (directShopKeeperNumber >= 5 && allShopKeeperNumber >= 36){
                    level = 1;
                } else if (directShopKeeperNumber >= 20 && allShopKeeperNumber >= 666) {
                    level = 2;
                }
//                if (directShopKeeperNumber >= 3 && allShopKeeperNumber >= 5){
//                    level = 1;
//                } else if (directShopKeeperNumber >= 5 && allShopKeeperNumber >= 8) {
//                    level = 2;
//                }
                shopKeeperRepository.updateLevelById(level, belong);
                belong=shopKeeperRepository.findBelongById(belong);
            }

        }else {
            //TODO 该店主上一级已经没有任何店主，他手下的所用用户和店主将会变成游离 belong = 0
            Integer user_id,shopkeeper_id;
            for (User user : shopKeeperLow.getUserList()) {
                    user_id=user.getId();
                    userRepository.updateUserVipById(user_id);//设置vip=0
            }
            for(ShopKeeper shopKeeper:shopKeeperRepository.findAllDirectShopKeeperById(id)){
                shopkeeper_id=shopKeeper.getId();
                shopKeeperRepository.updateBelongById(shopkeeper_id);
            }
        }

        //删除店主，即把isDel号设置为1
        shopKeeperRepository.deleteByPrimaryKey(id);
        //同时belong属性更改为0
        shopKeeperRepository.updateBelongById(id);
        //店主的直接店主、所有店主、直接用户、所有用户都需要置为 0
        shopKeeperRepository.updateNumber0ById(id);
    }

    @Transactional
    public void updateMessageByPrimaryKey(String message, Integer id) {
        shopKeeperRepository.updateMessageByPrimaryKey(message, id);
    }

    @Transactional
    public void updateMessageAndYiTiXianById(String message, float money, Integer id) {
        shopKeeperRepository.updateMessageAndYiTiXianById(message, money, id);
    }

    @Transactional
    public void updateMessageById(String message, Integer id) {
        shopKeeperRepository.updateMessageById(message, id);
    }


    //------------------NEW------------------------//


    /**
     * 店主查看直接下属店主
     * @param belong
     * @param orderField
     * @param orderDirection
     * @param pageRequest
     * @return
     * @throws Exception
     */
    public Page<ShopKeeper> findAllByIsDel0AndBelong(final Integer belong, Integer status, String orderField, String orderDirection, PageRequest pageRequest) throws Exception {

        //特殊情况查询
        //============创建查询规范=============//
        Specification<ShopKeeper> shopKeeperSpecification = new Specification<ShopKeeper>() {
            @Override
            public Predicate toPredicate(Root<ShopKeeper> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = Lists.newArrayList();

                Predicate predicate = null;

                //匹配属性和属性对应的值
                predicate = criteriaBuilder.equal(root.get("isDel"), 0);

                //添加
                predicateList.add(criteriaBuilder.and(predicate));

                if (status != null && !status.equals("")) {
                    predicate = criteriaBuilder.equal(root.get("status"), status);
                    predicateList.add(criteriaBuilder.and(predicate));
                }
                if (belong != null && !belong.equals("")) {
                    predicate = criteriaBuilder.equal(root.get("belong"), belong);
                    predicateList.add(criteriaBuilder.and(predicate));
                }
                //=========查找shopkeeper表中belong字段=传进来belong参数的店主==========//
                criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
                return null;
            }
        };

        Sort sort;
        if (orderDirection != null && !orderDirection.equals("") && orderField != null && !orderField.equals("")) {
            sort = new Sort(Sort.Direction.fromString(orderDirection), orderField);
        } else {
            sort = new Sort(Sort.Direction.ASC, "id");
        }
        //起始，长度
        Pageable pageable = new PageRequest(pageRequest.getPageNumber(), pageRequest.getPageSize(), sort);

        Page<ShopKeeper> shopKeeperPage = null;
        try {
            shopKeeperPage = shopKeeperRepository.findAll(shopKeeperSpecification, pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shopKeeperPage.getTotalElements() > 0 ? shopKeeperPage : null;
    }

    //功能：查询高级店主的直接店主数量 //
    public Integer findDirectShopkeeperNumberById(Integer id) {
        return shopKeeperRepository.findDirectShopkeeperNumberById(id);
    }

    //功能：查询高级店主的所有店主数量
    public Integer findAllShopkeeperNumberById(Integer id) {
        return shopKeeperRepository.findAllShopkeeperNumberById(id);
    }


    //获取店主的下属店主数量
    public Integer getAllShopKeeperNumberById(Integer id) {
        return shopKeeperRepository.findAllShopkeeperNumberById(id);
    }


    /**
     * 查看不同等级的店主
     * @param level
     * @param orderField
     * @param orderDirection
     * @param pageRequest
     * @return
     * @throws Exception
     */
    public Page<ShopKeeper> findAllByIsDel0AndLevel(final Integer level, Integer status,String orderField, String orderDirection, PageRequest pageRequest) throws Exception {

        //特殊情况查询
        //创建查询规范
        Specification<ShopKeeper> shopKeeperSpecification = new Specification<ShopKeeper>() {
            @Override
            public Predicate toPredicate(Root<ShopKeeper> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = Lists.newArrayList();

                Predicate predicate = null;

                //匹配属性和属性对应的值
                predicate = criteriaBuilder.equal(root.get("isDel"), 0);

                //添加
                predicateList.add(criteriaBuilder.and(predicate));
                if (status != null && !status.equals("")) {
                    predicate = criteriaBuilder.equal(root.get("status"), status);
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (level != null && !level.equals("")) {
                    predicate = criteriaBuilder.equal(root.get("level"), level);
                    predicateList.add(criteriaBuilder.and(predicate));
                }
                criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
                return null;
            }
        };

        Sort sort;
        if (orderDirection != null && !orderDirection.equals("") && orderField != null && !orderField.equals("")) {
            sort = new Sort(Sort.Direction.fromString(orderDirection), orderField);
        } else {
            sort = new Sort(Sort.Direction.ASC, "id");
        }
        //起始，长度
        Pageable pageable = new PageRequest(pageRequest.getPageNumber(), pageRequest.getPageSize(), sort);

        Page<ShopKeeper> shopKeeperPage = null;
        try {
            shopKeeperPage = shopKeeperRepository.findAll(shopKeeperSpecification, pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shopKeeperPage.getTotalElements() > 0 ? shopKeeperPage : null;
    }




    //---------------------NEW--------------------//

    //====1.店主A添加店主B时，更改店主A的直接用户数量和总用户数=====//
    //                       同时更改高级店主的直接店主数量
    @Transactional
    public void updateNumberByAddShopkeeper(Integer shopKeeperAId) {
//            //1.更改店主A的直接用户数量
//            Integer directUserNumber=shopKeeperRepository.findDirectUserNumberById(shopKeeperAId);
        //            shopKeepeepository.updateDirectUserNumberById(directUserNumber+1,shopKeeperAId);rR
        //更改高级店主的直接店主数量
        Integer directShopkeeperNumber = shopKeeperRepository.findDirectShopkeeperNumberById(shopKeeperAId) + 1;
        shopKeeperRepository.updateDirectShopkeeperNumberById(directShopkeeperNumber, shopKeeperAId);

        int belong = shopKeeperAId;
        int number;
        while (belong != 0) {
            number = shopKeeperRepository.findAllShopkeeperNumberById(belong) + 1;//店主A上一级店主C的原本总店主数量
            shopKeeperRepository.updateAllShopkeeperNumberById(number, belong);//更新店主C的总用户数量
            //判断有无可能升级
            int directShopKeeperNum = shopKeeperRepository.findDirectShopkeeperNumberById(belong);
            /**
             * TODO 需要更改数字大小
             * 原本应该的数据量
             * 5 36
             * 20 666
             * 现在为了测试，更改数据量改为
             * 3    5
             * 5    8
             */
            if (directShopKeeperNum >= 5 && number >= 36) {
                shopKeeperRepository.updateLevelById(1, belong);
            }
            if (directShopKeeperNum >= 20 && number >= 666) {
                shopKeeperRepository.updateLevelById(2, belong);
            }
//            if (directShopKeeperNum >= 3 && number >= 5) {
//                shopKeeperRepository.updateLevelById(1, belong);
//            }
//            if (directShopKeeperNum >= 5 && number >= 8) {
//                shopKeeperRepository.updateLevelById(2, belong);
//            }
            belong = shopKeeperRepository.findBelongById(belong);//查找店主C的上一级店主D的id号
        }

    }

    //========2.店主添加用户=======//
    @Transactional
    public void updateNumberByAddUser(Integer shopkeeperId) {
        Integer direct = shopKeeperRepository.findDirectUserNumberById(shopkeeperId) + 1;
        shopKeeperRepository.updateDirectUserNumberById(direct, shopkeeperId);
        Integer number = shopKeeperRepository.findAllUserNumberById(shopkeeperId) + 1;//店主A的总用户数
        shopKeeperRepository.updateAllUserNumberById(number, shopkeeperId);//店主A的总用户数+1
        Integer belong = shopKeeperRepository.findBelongById(shopkeeperId);//查找店主A上一级店主C的id号
        while (belong != 0) {
            number = shopKeeperRepository.findAllUserNumberById(belong) + 1;//店主A上一级店主C的总用户数量
            shopKeeperRepository.updateAllUserNumberById(number, belong);//店主C的总用户数+1
            belong = shopKeeperRepository.findBelongById(belong);//查找店主C的上一级店主D的id号
        }
    }


    /**
     * 通过 username 查询对应店主的 id
     *
     * @return 对应店主的 id
     */
    public Integer findIdByUsername(String username) {
        return shopKeeperRepository.findIdByUsername(username);
    }


    //查询注册时间
    public Date findRegisterTimeById(Integer id) {
        return shopKeeperRepository.findRegisterTimeById(id);
    }

    //查询过期时间
    public Date findDeathTimeById(Integer id) {
        return shopKeeperRepository.findDeathTimeById(id);
    }

    @Transactional
    public void updateDeathTimeById(Date time, Integer id) {
        shopKeeperRepository.updateDeathTimeById(time, id);
    }

    public ShopKeeper findByUsername(String username) {
        return shopKeeperRepository.findByUsername(username);
    }





    /**
     * 通过店主的 id allProfit += delta
     * @param delta 总利润的增量
     * @param id 需要更新店主的 id
     */
    @Transactional
    public void addAllProfitById(float delta, Integer id) {
        shopKeeperRepository.addAllProfitById(delta, id);
    }

    /**
     * 通过手机号码查询店主信息
     */
    public ShopKeeper findByTelephone(String telephone) {
        return shopKeeperRepository.findByTelephone(telephone);
    }

    @Transactional
    public void updatePasswordByTelephone(String password, String telephone) {
        shopKeeperRepository.updatePasswordByTelephone(password, telephone);
    }

    /**
     * 查找所有某一个等级的用户
     */
//    public List<ShopKeeper> findAllByLevel(Integer level) {
//        Sort sort = new Sort(Sort.Direction.DESC, "")
//        Pageable pageable = new PageRequest()
//    }

    /**
     * 通过手机号码，查找店主的等级
     */
    public Integer findLevelByTelephone(String telephone) {
        return shopKeeperRepository.findLevelByTelephone(telephone);
    }

    @Transactional
    public void updateTelephoneByUsername(String username, String newTelephone) {
        shopKeeperRepository.updateTelephoneUsername(username, newTelephone);
    }

    //查看店主的总利润
    public float getAllProfitByShopKeeper(String shopKeeper){
        return shopKeeperRepository.findAllProfitByShopKeeper(shopKeeper);
    }

    public  String  findShopKeeperNameByBelong(Integer belong){
        return shopKeeperRepository.findShopKeeperNameByBelong(belong);
    }

    /**
     * vip 用户升级为管理员后，把相应的店主的直接用户 - 1, 所有用户 - 1, 该店主的所有上级店主的所有用户数目 - 1
     * @param id id 为 id 的店主的一个 vip 用户升级为了管理员
     */
    @Transactional
    public void decreaseDirectUserNumberAndAllUserNumber(Integer id) {
        //直接用户数目 -1
        shopKeeperRepository.decreaseDirectUserNumberById(id);
        //所有用户数目 -1
        shopKeeperRepository.decreaseAllUserNumberById(id);
        Integer belong = shopKeeperRepository.findBelongById(id);
        //存在上级店主
        while (belong != 0) {
            shopKeeperRepository.decreaseAllUserNumberById(belong);
            belong = shopKeeperRepository.findBelongById(belong);
        }
    }

    /**
     * 从数据库中删除该店主的信息，以便释放店主的 username 和 telephone 信息
     * @param id
     */
    @Transactional
    public void realDeleteById(Integer id) {
        shopKeeperRepository.delete(id);
    }
}

