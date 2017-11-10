package cn.wangdian.Service;

import cn.wangdian.Model.FirstPhoto;
import cn.wangdian.Repository.FirstPhotoRepository;
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
 * Created by 25065 on 2016/9/19.
 */
@Service
public class FirstPhotoService {

    @Autowired
    private FirstPhotoRepository firstPhotoRepository;

    public void save(FirstPhoto firstPhoto){
        firstPhotoRepository.save(firstPhoto);
    }

    public void update(FirstPhoto firstPhoto){
        firstPhotoRepository.saveAndFlush(firstPhoto);
    }

    public FirstPhoto findById(Integer id){
        return firstPhotoRepository.findById(id);
    }

    public Page<FirstPhoto> findAllByIsDel0( final String name, final String inTime, String orderField, String orderDirection, PageRequest pageRequest) throws Exception{

        //特殊情况查询
        Specification<FirstPhoto> ordersSpecification=new Specification<FirstPhoto>() {
            @Override
            public Predicate toPredicate(Root<FirstPhoto> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList= Lists.newArrayList();

                Predicate predicate=null;

                //匹配属性和属性对应的值
                predicate=criteriaBuilder.equal(root.get("isDel"),0);

                //添加
                predicateList.add(criteriaBuilder.and(predicate));

                if (name!=null&&!name.equals("")){
                    Path<String> stringPath=root.get("name");
                    predicate=criteriaBuilder.like(stringPath,"%"+name+"%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (inTime!=null&&!inTime.equals("")){
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String date1=inTime+" 00:00:00";
                    String date2=inTime+" 23:59:59";
                    Date date3= null;
                    Date date4= null;
                    try {
                        date3 = sdf.parse(date1);
                        date4 = sdf.parse(date2);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Expression<Date> stringPath=root.get("inTime");
                    predicate=criteriaBuilder.between(stringPath,date3,date4);
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
            sort=new Sort(Sort.Direction.DESC,"inTime");
        }
        //起始，长度
        Pageable pageable=new PageRequest(pageRequest.getPageNumber(),pageRequest.getPageSize(),sort);

        Page<FirstPhoto> firstPhotoPage=null;
        try {
            firstPhotoPage=firstPhotoRepository.findAll(ordersSpecification,pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return firstPhotoPage.getTotalElements()>0?firstPhotoPage:null;
    }

    public Integer countAllByIsDel0( final String name, final String inTime) throws Exception{

        //特殊情况查询
        Specification<FirstPhoto> ordersSpecification=new Specification<FirstPhoto>() {
            @Override
            public Predicate toPredicate(Root<FirstPhoto> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList= Lists.newArrayList();

                Predicate predicate=null;

                //匹配属性和属性对应的值
                predicate=criteriaBuilder.equal(root.get("isDel"),0);

                //添加
                predicateList.add(criteriaBuilder.and(predicate));

                if (name!=null&&!name.equals("")){
                    Path<String> stringPath=root.get("name");
                    predicate=criteriaBuilder.like(stringPath,"%"+name+"%");
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                if (inTime!=null&&!inTime.equals("")){
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String date1=inTime+" 00:00:00";
                    String date2=inTime+" 23:59:59";
                    Date date3= null;
                    Date date4= null;
                    try {
                        date3 = sdf.parse(date1);
                        date4 = sdf.parse(date2);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Expression<Date> stringPath=root.get("inTime");
                    predicate=criteriaBuilder.between(stringPath,date3,date4);
                    predicateList.add(criteriaBuilder.and(predicate));
                }

                criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
                return null;
            }
        };

        long count=0;
        try {
            count=firstPhotoRepository.count(ordersSpecification);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Long(count).intValue();
    }

    @Transactional
    public void deleteByPrimaryKey(Integer id){
        firstPhotoRepository.deleteByPrimaryKey(id);
    }
}
