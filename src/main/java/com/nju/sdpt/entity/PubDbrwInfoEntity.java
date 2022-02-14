package com.nju.sdpt.entity;

import java.util.Date;

public class PubDbrwInfoEntity {
    private Integer id;

    private String serviceno;

    private String casenumber;

    private String orderpeople;

    private String bsdpeople;

    private String xzsdfs;

    private String xzsdfsxq;

    private String clzt;

    private String createpeople;

    private Date creaetetime;

    private Integer ssdrbh;

    private Integer yhbh;

    private Integer yysdbh;

    public PubDbrwInfoEntity(Integer id, String serviceno, String casenumber, String orderpeople, String bsdpeople, String xzsdfs, String xzsdfsxq, String clzt, String createpeople, Date creaetetime, Integer ssdrbh, Integer yhbh, Integer yysdbh) {
        this.id = id;
        this.serviceno = serviceno;
        this.casenumber = casenumber;
        this.orderpeople = orderpeople;
        this.bsdpeople = bsdpeople;
        this.xzsdfs = xzsdfs;
        this.xzsdfsxq = xzsdfsxq;
        this.clzt = clzt;
        this.createpeople = createpeople;
        this.creaetetime = creaetetime;
        this.ssdrbh = ssdrbh;
        this.yhbh = yhbh;
        this.yysdbh = yysdbh;
    }

    public PubDbrwInfoEntity() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceno() {
        return serviceno;
    }

    public void setServiceno(String serviceno) {
        this.serviceno = serviceno == null ? null : serviceno.trim();
    }

    public String getCasenumber() {
        return casenumber;
    }

    public void setCasenumber(String casenumber) {
        this.casenumber = casenumber == null ? null : casenumber.trim();
    }

    public String getOrderpeople() {
        return orderpeople;
    }

    public void setOrderpeople(String orderpeople) {
        this.orderpeople = orderpeople == null ? null : orderpeople.trim();
    }

    public String getBsdpeople() {
        return bsdpeople;
    }

    public void setBsdpeople(String bsdpeople) {
        this.bsdpeople = bsdpeople == null ? null : bsdpeople.trim();
    }

    public String getXzsdfs() {
        return xzsdfs;
    }

    public void setXzsdfs(String xzsdfs) {
        this.xzsdfs = xzsdfs == null ? null : xzsdfs.trim();
    }

    public String getXzsdfsxq() {
        return xzsdfsxq;
    }

    public void setXzsdfsxq(String xzsdfsxq) {
        this.xzsdfsxq = xzsdfsxq == null ? null : xzsdfsxq.trim();
    }

    public String getClzt() {
        return clzt;
    }

    public void setClzt(String clzt) {
        this.clzt = clzt == null ? null : clzt.trim();
    }

    public String getCreatepeople() {
        return createpeople;
    }

    public void setCreatepeople(String createpeople) {
        this.createpeople = createpeople == null ? null : createpeople.trim();
    }

    public Date getCreaetetime() {
        return creaetetime;
    }

    public void setCreaetetime(Date creaetetime) {
        this.creaetetime = creaetetime;
    }

    public Integer getSsdrbh() {
        return ssdrbh;
    }

    public void setSsdrbh(Integer ssdrbh) {
        this.ssdrbh = ssdrbh;
    }

    public Integer getYhbh() {
        return yhbh;
    }

    public void setYhbh(Integer yhbh) {
        this.yhbh = yhbh;
    }

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }
}