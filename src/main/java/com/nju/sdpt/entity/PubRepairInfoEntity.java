package com.nju.sdpt.entity;

import java.util.Date;

public class PubRepairInfoEntity {
    private Integer id;

    private Integer yysdbh;

    private Integer ssdrbh;

    private String ah;

    private String ssdrmc;

    private String repairstatus;

    private String repairbatchno;

    private Date createtime;

    private Integer sdybh;

    public PubRepairInfoEntity(Integer id, Integer yysdbh, Integer ssdrbh, String ah, String ssdrmc, String repairstatus, String repairbatchno, Date createtime, Integer sdybh) {
        this.id = id;
        this.yysdbh = yysdbh;
        this.ssdrbh = ssdrbh;
        this.ah = ah;
        this.ssdrmc = ssdrmc;
        this.repairstatus = repairstatus;
        this.repairbatchno = repairbatchno;
        this.createtime = createtime;
        this.sdybh = sdybh;
    }

    public PubRepairInfoEntity() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }

    public Integer getSsdrbh() {
        return ssdrbh;
    }

    public void setSsdrbh(Integer ssdrbh) {
        this.ssdrbh = ssdrbh;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah == null ? null : ah.trim();
    }

    public String getSsdrmc() {
        return ssdrmc;
    }

    public void setSsdrmc(String ssdrmc) {
        this.ssdrmc = ssdrmc == null ? null : ssdrmc.trim();
    }

    public String getRepairstatus() {
        return repairstatus;
    }

    public void setRepairstatus(String repairstatus) {
        this.repairstatus = repairstatus == null ? null : repairstatus.trim();
    }

    public String getRepairbatchno() {
        return repairbatchno;
    }

    public void setRepairbatchno(String repairbatchno) {
        this.repairbatchno = repairbatchno == null ? null : repairbatchno.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getSdybh() {
        return sdybh;
    }

    public void setSdybh(Integer sdybh) {
        this.sdybh = sdybh;
    }
}