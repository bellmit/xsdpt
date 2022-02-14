package com.nju.sdpt.model.zgysd;

import com.alibaba.fastjson.annotation.JSONField;
import com.nju.sdpt.viewobject.DsrjbVO;

import java.io.Serializable;
import java.util.List;

public class AjxxDsrItem implements Serializable {
    @JSONField(ordinal = 1)
    private String dsrid;
    @JSONField(ordinal = 2)
    private String ssdw;
    @JSONField(ordinal = 3)
    private String dsrmc;
    @JSONField(ordinal = 4)
    private String dsrlx;
    @JSONField(ordinal = 5)
    private String dzsd;
    @JSONField(ordinal = 6)
    private String sjhm;
    @JSONField(ordinal = 7)
    private String xxdz;
    @JSONField(ordinal = 8)
    private String sddz;
    @JSONField(ordinal = 9)
    private String zjzl;
    @JSONField(ordinal = 10)
    private String zjhm;
    @JSONField(ordinal = 11)
    private String zzjglx;
    @JSONField(ordinal = 12)
    private String zzdm;
    @JSONField(ordinal = 13)
    private String xb;
    @JSONField(ordinal = 14)
    private String yb;
    @JSONField(ordinal = 15)
    private String mz;
    @JSONField(ordinal = 16)
    private String splcgk;
    @JSONField(ordinal = 17)
    private List<AjxxDlrxx> dlrlist;

    public AjxxDsrItem() {
    }

    public AjxxDsrItem(String dsrid, String ssdw, String dsrmc, String dsrlx, String dzsd, String sjhm, String xxdz, String sddz, String zjzl, String zjhm, String zzjglx, String zzdm, String xb, String yb, String mz, String splcgk, List<AjxxDlrxx> dlrlist) {
        this.dsrid = dsrid;
        this.ssdw = ssdw;
        this.dsrmc = dsrmc;
        this.dsrlx = dsrlx;
        this.dzsd = dzsd;
        this.sjhm = sjhm;
        this.xxdz = xxdz;
        this.sddz = sddz;
        this.zjzl = zjzl;
        this.zjhm = zjhm;
        this.zzjglx = zzjglx;
        this.zzdm = zzdm;
        this.xb = xb;
        this.yb = yb;
        this.mz = mz;
        this.splcgk = splcgk;
        this.dlrlist = dlrlist;
    }

    public AjxxDsrItem(DsrjbVO dsrjbVO) {
        this.dsrid = dsrjbVO.getDsrbh().toString();
        this.ssdw = dsrjbVO.getDsrssdw();
        this.dsrmc = dsrjbVO.getDsrjc();
        this.dsrlx = dsrjbVO.getDsrlb();
        this.sjhm = dsrjbVO.getDh();
//        this.xxdz = dsrjbVO.getSddz();//对接地址失败,暂时取消
        this.zjhm = dsrjbVO.getSfzhm();
    }

    public String getDsrid() {
        return dsrid;
    }

    public void setDsrid(String dsrid) {
        this.dsrid = dsrid;
    }

    public String getSsdw() {
        return ssdw;
    }

    public void setSsdw(String ssdw) {
        this.ssdw = ssdw;
    }

    public String getDsrmc() {
        return dsrmc;
    }

    public void setDsrmc(String dsrmc) {
        this.dsrmc = dsrmc;
    }

    public String getDsrlx() {
        return dsrlx;
    }

    public void setDsrlx(String dsrlx) {
        this.dsrlx = dsrlx;
    }

    public String getDzsd() {
        return dzsd;
    }

    public void setDzsd(String dzsd) {
        this.dzsd = dzsd;
    }

    public String getSjhm() {
        return sjhm;
    }

    public void setSjhm(String sjhm) {
        this.sjhm = sjhm;
    }

    public String getXxdz() {
        return xxdz;
    }

    public void setXxdz(String xxdz) {
        this.xxdz = xxdz;
    }

    public String getSddz() {
        return sddz;
    }

    public void setSddz(String sddz) {
        this.sddz = sddz;
    }

    public String getZjzl() {
        return zjzl;
    }

    public void setZjzl(String zjzl) {
        this.zjzl = zjzl;
    }

    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    public String getZzjglx() {
        return zzjglx;
    }

    public void setZzjglx(String zzjglx) {
        this.zzjglx = zzjglx;
    }

    public String getZzdm() {
        return zzdm;
    }

    public void setZzdm(String zzdm) {
        this.zzdm = zzdm;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getYb() {
        return yb;
    }

    public void setYb(String yb) {
        this.yb = yb;
    }

    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }

    public String getSplcgk() {
        return splcgk;
    }

    public void setSplcgk(String splcgk) {
        this.splcgk = splcgk;
    }

    public List<AjxxDlrxx> getDlrlist() {
        return dlrlist;
    }

    public void setDlrlist(List<AjxxDlrxx> dlrlist) {
        this.dlrlist = dlrlist;
    }
}
