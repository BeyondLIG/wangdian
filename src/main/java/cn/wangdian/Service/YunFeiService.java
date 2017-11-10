package cn.wangdian.Service;

import cn.wangdian.Model.YunFei;
import cn.wangdian.Repository.YunFeiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 25065 on 2016/9/18.
 */
@Service
public class YunFeiService {

    @Autowired
    private YunFeiRepository yuFeiRepository;

    public YunFei onlyOne(){
        List<YunFei> yunFeiList=yuFeiRepository.findAll();
        if (yunFeiList==null||yunFeiList.size()==0){
            return null;
        }else {
            return yunFeiList.get(0);
        }
    }

    @Transactional
    public void save(YunFei yunFei){
        yuFeiRepository.save(yunFei);
    }

    @Transactional
    public void update(YunFei yunFei){
        yuFeiRepository.saveAndFlush(yunFei);
    }
}
