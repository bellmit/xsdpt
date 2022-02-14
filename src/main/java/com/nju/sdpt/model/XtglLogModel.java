package com.nju.sdpt.model;



import com.nju.sdpt.entity.XtglLogEntity;

import java.util.Date;

/**
 * Created by XXT on 2019/5/24.
 */
public class XtglLogModel {
    private String yhmc;
    private Integer bh;
    private Integer ajxh;
    private String ah;
    private String cz;
    private Date sj;
    private String bz;

    public XtglLogEntity getXtglLogEntity()
    {
        return update(new XtglLogEntity());
    }

    public XtglLogEntity update(XtglLogEntity xtglLogDO)
    {
        xtglLogDO.setYhmc(this.yhmc);
        xtglLogDO.setBh(this.bh);
        xtglLogDO.setAjxh(this.ajxh);
        xtglLogDO.setAh(this.ah);
        xtglLogDO.setCz(this.cz);
        xtglLogDO.setSj(this.sj);
        xtglLogDO.setBz(this.bz);
        return xtglLogDO;
    }

    public XtglLogModel() {
        super();
        // TODO Auto-generated constructor stub
    }

    public XtglLogModel(String yhmc, Integer bh, Integer ajxh, String ah,
                        String cz, Date sj, String bz) {
        super();
        this.yhmc = yhmc;
        this.bh = bh;
        this.ajxh = ajxh;
        this.ah = ah;
        this.cz = cz;
        this.sj = sj;
        this.bz = bz;
    }

    public String getYhmc() {
        return yhmc;
    }
    public void setYhmc(String yhmc) {
        this.yhmc = yhmc;
    }

    public Integer getAjxh() {
        return ajxh;
    }

    public void setAjxh(Integer ajxh) {
        this.ajxh = ajxh;
    }

    public Integer getBh() {
        return bh;
    }
    public void setBh(Integer bh) {
        this.bh = bh;
    }
    public String getAh() {
        return ah;
    }
    public void setAh(String ah) {
        this.ah = ah;
    }
    public String getCz() {
        return cz;
    }
    public void setCz(String cz) {
        this.cz = cz;
    }
    public Date getSj() {
        return sj;
    }
    public void setSj(Date sj) {
        this.sj = sj;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

}

