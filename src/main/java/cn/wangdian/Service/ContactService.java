package cn.wangdian.Service;

import cn.wangdian.Model.Contact;
import cn.wangdian.Model.YunFei;
import cn.wangdian.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 25065 on 2016/9/18.
 */
@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Contact onlyOne(){
        List<Contact> contactList=contactRepository.findAll();
        if (contactList==null||contactList.size()==0){
            return null;
        }else {
            return contactList.get(0);
        }
    }

    public void save(Contact contact){
        contactRepository.save(contact);
    }

    public void update(Contact contact){
        contactRepository.saveAndFlush(contact);
    }
}
