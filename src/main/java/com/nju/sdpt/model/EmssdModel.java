package com.nju.sdpt.model;

import com.nju.sdpt.entity.PubKdtdEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EmssdModel {
    /**
     * 邮寄地址
     */
    private String yjdz;
    /**
     * 邮寄时间
     */
    private String yjsj;
    /**
     * 邮寄单号
     */
    private String yjdh;
    /**
     * 物流详情
     */
    private String wlxq;
    /**
     * 邮寄结果
     */
    private String yjjg;
    /**
     * 送达回执
     */
    private String sdhz;
    /**
     * 收件人姓名
     */
    private String sjrxm;

    /**
     * 快递id
     */
    private int kdid;

    /**
     * 快递单号
     */
    private  String kddh;
    /**
     * 寄件人姓名
     */
    private  String jjrxm;

    private String sfscmd;

    /**
     * 工单号
     */
    private Integer yysdbh;

    /**
     * 上传日期
     */
    private Date scrq;

    private String ah;

    private String fymc;

    private String fybh;
    /**
     * 送达结果
     */
    private String sdjg;

    private Integer ssdrbh;

    public String getFymc() {
        return fymc;
    }

    public void setFymc(String fymc) {
        this.fymc = fymc;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public Date getScrq() {
        return scrq;
    }

    public void setScrq(Date scrq) {
        this.scrq = scrq;
    }

    public String getJjrxm() {
        return jjrxm;
    }

    public void setJjrxm(String jjrxm) {
        this.jjrxm = jjrxm;
    }

    public String getKddh() {
        return kddh;
    }

    public void setKddh(String kddh) {
        this.kddh = kddh;
    }

    public int getKdid() {
        return kdid;
    }

    public void setKdid(int kdid) {
        this.kdid = kdid;
    }

    public String getSfscmd() {
        return sfscmd;
    }

    public void setSfscmd(String sfscmd) {
        this.sfscmd = sfscmd;
    }

    public EmssdModel() {
    }

    public EmssdModel(PubKdtdEntity pubKdtdEntity) {
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
        this.yjdz = pubKdtdEntity.getSjrdz();
        this.yjsj = sdf.format(pubKdtdEntity.getScrq());
        this.yjdh = pubKdtdEntity.getDjh();
        this.wlxq = "";
        this.yjjg = "";
        this.sjrxm = pubKdtdEntity.getSjrxm();
        this.kdid = pubKdtdEntity.getKdid();
        if(pubKdtdEntity.getKdhz()!=null){
            this.sdhz="";
        }
        this.kddh = pubKdtdEntity.getKddh();
        this.jjrxm = pubKdtdEntity.getJjrxm();
        this.sfscmd = pubKdtdEntity.getSfscmd();
        this.yysdbh = pubKdtdEntity.getYysdbh();
        this.scrq = pubKdtdEntity.getScrq();
        this.ah = pubKdtdEntity.getAh();
        this.sdjg = pubKdtdEntity.getSdjg();
        this.ssdrbh = pubKdtdEntity.getDsrbh();
    }

    public EmssdModel(String yjdz, String yjsj, String yjdh, String wlxq, String yjjg, String sdhz, String sjrxm, int kdid, String kddh, String jjrxm) {
        this.yjdz = yjdz;
        this.yjsj = yjsj;
        this.yjdh = yjdh;
        this.wlxq = wlxq;
        this.yjjg = yjjg;
        this.sdhz = sdhz;
        this.sjrxm = sjrxm;
        this.kdid = kdid;
        this.kddh = kddh;
        this.jjrxm = jjrxm;
    }

    public String getYjdz() {
        return yjdz;
    }

    public void setYjdz(String yjdz) {
        this.yjdz = yjdz;
    }

    public String getYjsj() {
        return yjsj;
    }

    public void setYjsj(String yjsj) {
        this.yjsj = yjsj;
    }

    public String getYjdh() {
        return yjdh;
    }

    public void setYjdh(String yjdh) {
        this.yjdh = yjdh;
    }

    public String getWlxq() {
        return wlxq;
    }

    public void setWlxq(String wlxq) {
        this.wlxq = wlxq;
    }

    public String getYjjg() {
        return yjjg;
    }

    public void setYjjg(String yjjg) {
        this.yjjg = yjjg;
    }

    public String getSdhz() {
        return sdhz;
    }

    public void setSdhz(String sdhz) {
        this.sdhz = sdhz;
    }

    public String getSjrxm() {
        return sjrxm;
    }

    public void setSjrxm(String sjrxm) {
        this.sjrxm = sjrxm;
    }

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh;
    }

    public String getSdjg() {
        return sdjg;
    }

    public void setSdjg(String sdjg) {
        this.sdjg = sdjg;
    }

    public Integer getSsdrbh() {
        return ssdrbh;
    }

    public void setSsdrbh(Integer ssdrbh) {
        this.ssdrbh = ssdrbh;
    }
}
