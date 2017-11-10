package cn.wangdian.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by 1192126986 on 2017/4/23.
 * 分润系统，用于实现等级分润
 */

@Entity
@Table(name="wd_benefit")
public class Benefit {
    @Id
    @GeneratedValue
    Integer id;
    private  float a;//店主分润系数
    private float b;//直系主管分润系数
    private float c;//直系经理分润系数

    public Benefit(){

    }

    public Benefit(float a,float b,float c){
        this.a=a;
        this.b=b;
        this.c=c;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public float getC() {
        return c;
    }

    public void setC(float c) {
        this.c = c;
    }
}
