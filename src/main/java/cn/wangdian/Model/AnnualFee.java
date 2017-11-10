package cn.wangdian.Model;

import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 年费
 */
@Entity
@Table(name = "wd_annualfee")
public class AnnualFee {
    @Id
    @GeneratedValue
    private Integer id;

    private Float fee;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getFee() {
        return fee;
    }

    public void setFee(Float fee) {
        this.fee = fee;
    }
}
