package com.nju.sdpt.entity;

import java.util.Date;

public class PubYysdSsdrdzEntity {
    private Integer bh;

    private String sdpName;

    private String idCard;

    private Integer lx;

    private String dz;

    private Integer yysdbh;

    private Integer ssdrbh;

    private String dzly;

    private Date createtime;

    private String label;

    public PubYysdSsdrdzEntity(Integer bh, String sdpName, String idCard, Integer lx, String dz, Integer yysdbh, Integer ssdrbh, String dzly, Date createtime, String label) {
        this.bh = bh;
        this.sdpName = sdpName;
        this.idCard = idCard;
        this.lx = lx;
        this.dz = dz;
        this.yysdbh = yysdbh;
        this.ssdrbh = ssdrbh;
        this.dzly = dzly;
        this.createtime = createtime;
        this.label = label;
    }

    public PubYysdSsdrdzEntity() {
        super();
    }

    public Integer getBh() {
        return bh;
    }

    public void setBh(Integer bh) {
        this.bh = bh;
    }

    public String getSdpName() {
        return sdpName;
    }

    public void setSdpName(String sdpName) {
        this.sdpName = sdpName == null ? null : sdpName.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public Integer getLx() {
        return lx;
    }

    public void setLx(Integer lx) {
        this.lx = lx;
    }

    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz == null ? null : dz.trim();
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

    public String getDzly() {
        return dzly;
    }

    public void setDzly(String dzly) {
        this.dzly = dzly == null ? null : dzly.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }
}