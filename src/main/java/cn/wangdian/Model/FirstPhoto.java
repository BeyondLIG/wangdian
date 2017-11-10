package cn.wangdian.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 25065 on 2016/9/19.
 */
@Entity
@Table(name = "wd_firstphoto")
public class FirstPhoto {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String photo;

    private Date inTime;

    private Integer isDel;

    public FirstPhoto() {
    }

    public FirstPhoto(String name, String photo, Date inTime) {
        this.name = name;
        this.photo = photo;
        this.inTime = inTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public String inTimeToString(){
        if (inTime!=null){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.format(inTime);
        }else {
            return null;
        }

    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}
