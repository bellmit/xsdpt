package com.nju.sdpt.entity;

public class PubYysdSsdrQtsscyrEntity {
    private Integer bh;

    private Integer yysdbh;

    private Integer ssdrbh;

    private String xm;

    private String lx;

    private String gzdw;

    private String dh;

    private String dz;

    private String sfzhm;

    public PubYysdSsdrQtsscyrEntity(Integer bh, Integer yysdbh, Integer ssdrbh, String xm, String lx, String gzdw, String dh, String dz, String sfzhm) {
        this.bh = bh;
        this.yysdbh = yysdbh;
        this.ssdrbh = ssdrbh;
        this.xm = xm;
        this.lx = lx;
        this.gzdw = gzdw;
        this.dh = dh;
        this.dz = dz;
        this.sfzhm = sfzhm;
    }




    public PubYysdSsdrQtsscyrEntity(PubQtsscyrEntity pubQtsscyrEntity, Integer yysdbh, Integer ssdrbh) {
        this.yysdbh = yysdbh;
        this.ssdrbh =ssdrbh;
        this.xm = pubQtsscyrEntity.getXm();
        this.lx = pubQtsscyrEntity.getQtsscyrlx();
        this.gzdw = pubQtsscyrEntity.getGzdw();
        this.dh = pubQtsscyrEntity.getDh();
        this.dz = pubQtsscyrEntity.getDz();
        this.sfzhm = pubQtsscyrEntity.getSfzhm();
    }


    public PubYysdSsdrQtsscyrEntity() {
        super();
    }

    public Integer getBh() {
        return bh;
    }

    public void setBh(Integer bh) {
        this.bh = bh;
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

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm == null ? null : xm.trim();
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx == null ? null : lx.trim();
    }

    public String getGzdw() {
        return gzdw;
    }

    public void setGzdw(String gzdw) {
        this.gzdw = gzdw == null ? null : gzdw.trim();
    }

    public String getDh() {
        return dh;
    }

    public void setDh(String dh) {
        this.dh = dh == null ? null : dh.trim();
    }

    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz == null ? null : dz.trim();
    }

    public String getSfzhm() {
        return sfzhm;
    }

    public void setSfzhm(String sfzhm) {
        this.sfzhm = sfzhm == null ? null : sfzhm.trim();
    }
}