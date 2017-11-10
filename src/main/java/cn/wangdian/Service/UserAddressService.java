package cn.wangdian.Service;

import cn.wangdian.Model.UserAddress;
import cn.wangdian.Repository.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 25065 on 2016/9/18.
 */
@Service
public class UserAddressService {

    @Autowired
    private UserAddressRepository userAddressRepository;

    public List<UserAddress> findAllByIsDel0(Integer userId){
        return userAddressRepository.findAllByIsDel0(userId);
    }

    @Transactional
    public void deleteByPrimaryKey(Integer id){
        userAddressRepository.deleteByPrimaryKey(id);
    }

    public Integer findUserIdById(Integer id){
        return userAddressRepository.findUserIdById(id);
    }

    public UserAddress findById(Integer id){
        return userAddressRepository.findById(id);
    }

    public List<UserAddress> findAllByIsDel0AndUserId(Integer userId){
        return userAddressRepository.findAllByIsDel0AndUserId(userId);
    }

    @Transactional
    public void save(UserAddress userAddress){
        userAddressRepository.save(userAddress);
    }

    @Transactional
    public void update(UserAddress userAddress){
        userAddressRepository.saveAndFlush(userAddress);
    }
}
