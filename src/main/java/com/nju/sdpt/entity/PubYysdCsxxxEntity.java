package com.nju.sdpt.entity;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * @author jiaweiq
 * @date 2021/7/26 14:08
 */
public class PubYysdCsxxxEntity {
    private Integer bh;
    // 预约送达编号，是这个表内，外联别的表的唯一字段
    private Integer yysdbh;
    // 预约时间
    private Timestamp yysj;
    // 电话超审限最后期限
    private Timestamp dhcsxddl;
    // 邮寄超审限最后期限
    private Timestamp yjcsxddl;
    // 超审限期限
    private Timestamp csxddl;
    // 用户代码
    private String yhdm;
    // 送达时间
    private Timestamp jssj;
    // 送达结果
    private String sdjg;
    // 是否电话送达
    private String sfdhsd;
    // 电话送达时间
    private Timestamp webcallcreatetime;
    // 是否邮寄送达
    private String sfyjsd;
    // ems送达时间
    private String emssubmittime;
    // 是否电话超审限
    private String sfdhcsx;
    // 是否邮寄超审限
    private String sfyjcsx;
    // 是否超审限
    private String sfcsx;

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

    public Timestamp getYysj() {
        return yysj;
    }

    public void setYysj(Timestamp yysj) {
        this.yysj = yysj;
    }

    public Timestamp getDhcsxddl() {
        return dhcsxddl;
    }

    public void setDhcsxddl(Timestamp dhcsxddl) {
        this.dhcsxddl = dhcsxddl;
    }

    public Timestamp getYjcsxddl() {
        return yjcsxddl;
    }

    public void setYjcsxddl(Timestamp yjcsxddl) {
        this.yjcsxddl = yjcsxddl;
    }

    public Timestamp getCsxddl() {
        return csxddl;
    }

    public void setCsxddl(Timestamp csxddl) {
        this.csxddl = csxddl;
    }

    public String getYhdm() {
        return yhdm;
    }

    public void setYhdm(String yhdm) {
        this.yhdm = yhdm;
    }

    public Timestamp getJssj() {
        return jssj;
    }

    public void setJssj(Timestamp jssj) {
        this.jssj = jssj;
    }

    public String getSdjg() {
        return sdjg;
    }

    public void setSdjg(String sdjg) {
        this.sdjg = sdjg;
    }

    public String getSfdhsd() {
        return sfdhsd;
    }

    public void setSfdhsd(String sfdhsd) {
        this.sfdhsd = sfdhsd;
    }

    public Timestamp getWebcallcreatetime() {
        return webcallcreatetime;
    }

    public void setWebcallcreatetime(Timestamp webcallcreatetime) {
        this.webcallcreatetime = webcallcreatetime;
    }

    public String getSfyjsd() {
        return sfyjsd;
    }

    public void setSfyjsd(String sfyjsd) {
        this.sfyjsd = sfyjsd;
    }

    public String getEmssubmittime() {
        return emssubmittime;
    }

    public void setEmssubmittime(String emssubmittime) {
        this.emssubmittime = emssubmittime;
    }

    public String getSfdhcsx() {
        return sfdhcsx;
    }

    public void setSfdhcsx(String sfdhcsx) {
        this.sfdhcsx = sfdhcsx;
    }

    public String getSfyjcsx() {
        return sfyjcsx;
    }

    public void setSfyjcsx(String sfyjcsx) {
        this.sfyjcsx = sfyjcsx;
    }

    public String getsfCsx() {
        return sfcsx;
    }

    public void setSfcsx(String sfcsx) {
        this.sfcsx = sfcsx;
    }
}
