package cn.wangdian.Service;

import cn.wangdian.Model.Admin;
import cn.wangdian.Repository.AdminRepository;
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
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin selectByUsername(String username){
        return adminRepository.selectByUsername(username);
    }

    public Admin findById(Integer id){
        return adminRepository.findById(id);
    }

    public void save(Admin admin){
        adminRepository.save(admin);
    }

    public Page<Admin> findAllByIsDel0(final String username, final String nickname, final Integer status, String orderField, String orderDirection, PageRequest pageRequest) throws Exception{

        //特殊情况查询
        Specification<Admin> adminSpecification=new Specification<Admin>() {
            @Override
            public Predicate toPredicate(Root<Admin> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
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
           sort=new Sort(Sort.Direction.fromString(orderDirection),orderField);
        }else {
            sort=new Sort(Sort.Direction.ASC,"loginTime");
        }
        //起始，长度
        Pageable pageable=new PageRequest(pageRequest.getPageNumber(),pageRequest.getPageSize(),sort);

        Page<Admin> adminPage=null;
        try {
            adminPage=adminRepository.findAll(adminSpecification,pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return adminPage.getTotalElements()>0?adminPage:null;
    }

    public Integer countAllByIsDel0(final String username, final String nickname, final Integer status) throws Exception{

        //特殊情况查询
        Specification<Admin> adminSpecification=new Specification<Admin>() {
            @Override
            public Predicate toPredicate(Root<Admin> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
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
            count=adminRepository.count(adminSpecification);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Long(count).intValue();
    }

    public String checkUsername(String username){
        return adminRepository.checkUsername(username);
    }

    public String findUsernameById(Integer id){
        return adminRepository.findUsernameById(id);
    }

    public void update(Admin admin){
        adminRepository.saveAndFlush(admin);
    }

    @Transactional
    public void suoDingById(Integer id){
        adminRepository.suoJieDing(id,1);
    }

    @Transactional
    public void jieDingById(Integer id){
        adminRepository.suoJieDing(id,0);
    }

    @Transactional
    public void deleteByPrimaryKey(Integer id){
        adminRepository.deleteByPrimaryKey(id);
    }

}
