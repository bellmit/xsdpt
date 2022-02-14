package com.nju.sdpt.entity;

/**
 * Created by XXT on 2019/5/24.
 */
public class LaAyEntity implements java.io.Serializable {

    // Fields

    private Integer ajxh;
    private Integer laaybh;
    private String ay;
    private String laay;

    // Constructors

    /** default constructor */
    public LaAyEntity() {
    }





    public Integer getAjxh() {
        return ajxh;
    }

    public void setAjxh(Integer ajxh) {
        this.ajxh = ajxh;
    }

    public Integer getLaaybh() {
        return laaybh;
    }

    public void setLaaybh(Integer laaybh) {
        this.laaybh = laaybh;
    }

    public String getAy() {
        return ay;
    }

    public void setAy(String ay) {
        this.ay = ay;
    }

    public String getLaay() {
        return laay;
    }

    public void setLaay(String laay) {
        this.laay = laay;
    }
}
