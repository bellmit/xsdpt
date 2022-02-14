package com.nju.sdpt.entity;

import java.util.Date;

public class HisSsdrDataInfoEntity {
    private Integer id;

    private String fybh;

    private Integer ajxh;

    private Integer ssdrbh;

    private String ssdrmc;

    private String ssdrsfzhm;

    private String ywlx;

    private Integer type;

    private String content;

    private Date createtime;

    private String dsrssdw;

    public HisSsdrDataInfoEntity(Integer id, String fybh, Integer ajxh, Integer ssdrbh, String ssdrmc, String ssdrsfzhm, String ywlx, Integer type, String content, Date createtime, String dsrssdw) {
        this.id = id;
        this.fybh = fybh;
        this.ajxh = ajxh;
        this.ssdrbh = ssdrbh;
        this.ssdrmc = ssdrmc;
        this.ssdrsfzhm = ssdrsfzhm;
        this.ywlx = ywlx;
        this.type = type;
        this.content = content;
        this.createtime = createtime;
        this.dsrssdw = dsrssdw;
    }

    public HisSsdrDataInfoEntity() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh == null ? null : fybh.trim();
    }

    public Integer getAjxh() {
        return ajxh;
    }

    public void setAjxh(Integer ajxh) {
        this.ajxh = ajxh;
    }

    public Integer getSsdrbh() {
        return ssdrbh;
    }

    public void setSsdrbh(Integer ssdrbh) {
        this.ssdrbh = ssdrbh;
    }

    public String getSsdrmc() {
        return ssdrmc;
    }

    public void setSsdrmc(String ssdrmc) {
        this.ssdrmc = ssdrmc == null ? null : ssdrmc.trim();
    }

    public String getSsdrsfzhm() {
        return ssdrsfzhm;
    }

    public void setSsdrsfzhm(String ssdrsfzhm) {
        this.ssdrsfzhm = ssdrsfzhm == null ? null : ssdrsfzhm.trim();
    }

    public String getYwlx() {
        return ywlx;
    }

    public void setYwlx(String ywlx) {
        this.ywlx = ywlx == null ? null : ywlx.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getDsrssdw() {
        return dsrssdw;
    }

    public void setDsrssdw(String dsrssdw) {
        this.dsrssdw = dsrssdw == null ? null : dsrssdw.trim();
    }
}