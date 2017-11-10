package cn.wangdian.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by dushang on 2016/9/26.
 */
@Entity
@Table(name = "wd_yunfei")
public class YunFei {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer yunFei;//基本运费

    private Integer mianYunFei;//免运费的总销售价

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYunFei() {
        return yunFei;
    }

    public void setYunFei(Integer yunFei) {
        this.yunFei = yunFei;
    }

    public Integer getMianYunFei() {
        return mianYunFei;
    }

    public void setMianYunFei(Integer mianYunFei) {
        this.mianYunFei = mianYunFei;
    }
}
