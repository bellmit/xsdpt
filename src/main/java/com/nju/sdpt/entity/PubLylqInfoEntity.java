package com.nju.sdpt.entity;

import java.util.Date;

public class PubLylqInfoEntity {
    private Integer lylqid;

    private Integer yysdbh;

    private Date yylqsj;

    private String lylqaddress;

    private Integer lqstate;

    private Integer sdjg;

    private String ygno;

    private Date ygcjsj;

    private Date ygqjsj;

    private Integer ygqjzt;

    private Date createTime;

    private String createOperator;

    private Date updateTime;

    private String updateOperator;

    private Integer ssdrbh;

    private String fybh;

    private Integer ajxh;

    private String fgbh;

    private Integer sdybh;

    private String lqsj;

    private Date submittime;

    private byte[] sdhz;

    public PubLylqInfoEntity(Integer lylqid, Integer yysdbh, Date yylqsj, String lylqaddress, Integer lqstate, Integer sdjg, String ygno, Date ygcjsj, Date ygqjsj, Integer ygqjzt, Date createTime, String createOperator, Date updateTime, String updateOperator, Integer ssdrbh, String fybh, Integer ajxh, String fgbh, Integer sdybh, String lqsj, Date submittime) {
        this.lylqid = lylqid;
        this.yysdbh = yysdbh;
        this.yylqsj = yylqsj;
        this.lylqaddress = lylqaddress;
        this.lqstate = lqstate;
        this.sdjg = sdjg;
        this.ygno = ygno;
        this.ygcjsj = ygcjsj;
        this.ygqjsj = ygqjsj;
        this.ygqjzt = ygqjzt;
        this.createTime = createTime;
        this.createOperator = createOperator;
        this.updateTime = updateTime;
        this.updateOperator = updateOperator;
        this.ssdrbh = ssdrbh;
        this.fybh = fybh;
        this.ajxh = ajxh;
        this.fgbh = fgbh;
        this.sdybh = sdybh;
        this.lqsj = lqsj;
        this.submittime = submittime;
    }

    public PubLylqInfoEntity(Integer lylqid, Integer yysdbh, Date yylqsj, String lylqaddress, Integer lqstate, Integer sdjg, String ygno, Date ygcjsj, Date ygqjsj, Integer ygqjzt, Date createTime, String createOperator, Date updateTime, String updateOperator, Integer ssdrbh, String fybh, Integer ajxh, String fgbh, Integer sdybh, String lqsj, Date submittime, byte[] sdhz) {
        this.lylqid = lylqid;
        this.yysdbh = yysdbh;
        this.yylqsj = yylqsj;
        this.lylqaddress = lylqaddress;
        this.lqstate = lqstate;
        this.sdjg = sdjg;
        this.ygno = ygno;
        this.ygcjsj = ygcjsj;
        this.ygqjsj = ygqjsj;
        this.ygqjzt = ygqjzt;
        this.createTime = createTime;
        this.createOperator = createOperator;
        this.updateTime = updateTime;
        this.updateOperator = updateOperator;
        this.ssdrbh = ssdrbh;
        this.fybh = fybh;
        this.ajxh = ajxh;
        this.fgbh = fgbh;
        this.sdybh = sdybh;
        this.lqsj = lqsj;
        this.submittime = submittime;
        this.sdhz = sdhz;
    }

    public PubLylqInfoEntity() {
        super();
    }

    public Integer getLylqid() {
        return lylqid;
    }

    public void setLylqid(Integer lylqid) {
        this.lylqid = lylqid;
    }

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }

    public Date getYylqsj() {
        return yylqsj;
    }

    public void setYylqsj(Date yylqsj) {
        this.yylqsj = yylqsj;
    }

    public String getLylqaddress() {
        return lylqaddress;
    }

    public void setLylqaddress(String lylqaddress) {
        this.lylqaddress = lylqaddress == null ? null : lylqaddress.trim();
    }

    public Integer getLqstate() {
        return lqstate;
    }

    public void setLqstate(Integer lqstate) {
        this.lqstate = lqstate;
    }

    public Integer getSdjg() {
        return sdjg;
    }

    public void setSdjg(Integer sdjg) {
        this.sdjg = sdjg;
    }

    public String getYgno() {
        return ygno;
    }

    public void setYgno(String ygno) {
        this.ygno = ygno == null ? null : ygno.trim();
    }

    public Date getYgcjsj() {
        return ygcjsj;
    }

    public void setYgcjsj(Date ygcjsj) {
        this.ygcjsj = ygcjsj;
    }

    public Date getYgqjsj() {
        return ygqjsj;
    }

    public void setYgqjsj(Date ygqjsj) {
        this.ygqjsj = ygqjsj;
    }

    public Integer getYgqjzt() {
        return ygqjzt;
    }

    public void setYgqjzt(Integer ygqjzt) {
        this.ygqjzt = ygqjzt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateOperator() {
        return createOperator;
    }

    public void setCreateOperator(String createOperator) {
        this.createOperator = createOperator == null ? null : createOperator.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateOperator() {
        return updateOperator;
    }

    public void setUpdateOperator(String updateOperator) {
        this.updateOperator = updateOperator == null ? null : updateOperator.trim();
    }

    public Integer getSsdrbh() {
        return ssdrbh;
    }

    public void setSsdrbh(Integer ssdrbh) {
        this.ssdrbh = ssdrbh;
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

    public String getFgbh() {
        return fgbh;
    }

    public void setFgbh(String fgbh) {
        this.fgbh = fgbh == null ? null : fgbh.trim();
    }

    public Integer getSdybh() {
        return sdybh;
    }

    public void setSdybh(Integer sdybh) {
        this.sdybh = sdybh;
    }

    public String getLqsj() {
        return lqsj;
    }

    public void setLqsj(String lqsj) {
        this.lqsj = lqsj == null ? null : lqsj.trim();
    }

    public Date getSubmittime() {
        return submittime;
    }

    public void setSubmittime(Date submittime) {
        this.submittime = submittime;
    }

    public byte[] getSdhz() {
        return sdhz;
    }

    public void setSdhz(byte[] sdhz) {
        this.sdhz = sdhz;
    }
}