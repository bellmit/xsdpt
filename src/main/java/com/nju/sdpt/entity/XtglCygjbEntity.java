package com.nju.sdpt.entity;

/**
 * Created by XXT on 2019/5/15.
 */

public class XtglCygjbEntity {

    // Fields

    private Integer cygjbh;
    private String gjmc;
    private Integer mrxssx;
    private Integer lx;
    private String lj;

    // Constructors
    public XtglCygjbEntity(Integer cygjbh, String gjmc, Integer mrxssx, Integer lx, String lj) {
        this.cygjbh = cygjbh;
        this.gjmc = gjmc;
        this.mrxssx = mrxssx;
        this.lx = lx;
        this.lj = lj;
    }

    public XtglCygjbEntity() {
    }

    public Integer getCygjbh() {
        return cygjbh;
    }

    public void setCygjbh(Integer cygjbh) {
        this.cygjbh = cygjbh;
    }

    public String getGjmc() {
        return gjmc;
    }

    public void setGjmc(String gjmc) {
        this.gjmc = gjmc;
    }

    public Integer getMrxssx() {
        return mrxssx;
    }

    public void setMrxssx(Integer mrxssx) {
        this.mrxssx = mrxssx;
    }

    public Integer getLx() {
        return lx;
    }

    public void setLx(Integer lx) {
        this.lx = lx;
    }

    public String getLj() {
        return lj;
    }

    public void setLj(String lj) {
        this.lj = lj;
    }
}
