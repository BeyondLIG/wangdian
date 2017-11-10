package cn.wangdian.Service;

import cn.wangdian.Model.Admin;
import cn.wangdian.Model.AdminLogin;
import cn.wangdian.Repository.AdminLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by 25065 on 2016/9/15.
 */
@Service
public class AdminLoginService {

    @Autowired
    private AdminLoginRepository adminLoginRepository;

    public void save(AdminLogin adminLogin){
        adminLoginRepository.save(adminLogin);
    }

    public Date findLastLoginTimeByAdminId(Integer adminId){
        return adminLoginRepository.findLastLoginTimeByAdminId(adminId);
    }

    public Integer findCountByAdminId(Integer adminId){
        return adminLoginRepository.findCountByAdminId(adminId);
    }
}
