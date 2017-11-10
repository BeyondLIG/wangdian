package cn.wangdian.Repository;

import cn.wangdian.Model.Contact;
import cn.wangdian.Model.YunFei;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by 25065 on 2016/9/18.
 */
public interface ContactRepository extends JpaRepository<Contact,String>,JpaSpecificationExecutor<Contact> {


}
