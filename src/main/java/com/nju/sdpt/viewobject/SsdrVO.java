package com.nju.sdpt.viewobject;

public class SsdrVO {
    int yysdbh;
    int ssdrbh;
    String ssdrmc;//受送达人名称
    String ssdw;//诉讼地位
    String sdzt;//送达状态
    String sfqsqrs;//是否签署送达地址确认书
    String sftydzsd;//是否同意电子送达
    String sfzhm;//身份证号码
    String dh;
    Integer authority;//操作权限 0没有,1有
    String repairstatus;//修复状态

    public String getDh() {
        return dh;
    }

    public void setDh(String dh) {
        this.dh = dh;
    }

    public String getSfzhm() {
        return sfzhm;
    }

    public void setSfzhm(String sfzhm) {
        this.sfzhm = sfzhm;
    }

    public String getSsdrmc() {
        return ssdrmc;
    }

    public void setSsdrmc(String ssdrmc) {
        this.ssdrmc = ssdrmc;
    }

    public String getSsdw() {
        return ssdw;
    }

    public void setSsdw(String ssdw) {
        this.ssdw = ssdw;
    }

    public String getSdzt() {
        return sdzt;
    }

    public void setSdzt(String sdzt) {
        this.sdzt = sdzt;
    }

    public String getSfqsqrs() {
        return sfqsqrs;
    }

    public void setSfqsqrs(String sfqsqrs) {
        this.sfqsqrs = sfqsqrs;
    }

    public String getSftydzsd() {
        return sftydzsd;
    }

    public void setSftydzsd(String sftydzsd) {
        this.sftydzsd = sftydzsd;
    }

    public int getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(int yysdbh) {
        this.yysdbh = yysdbh;
    }

    public int getSsdrbh() {
        return ssdrbh;
    }

    public void setSsdrbh(int ssdrbh) {
        this.ssdrbh = ssdrbh;
    }

    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
    }

    public String getRepairstatus() {
        return repairstatus;
    }

    public void setRepairstatus(String repairstatus) {
        this.repairstatus = repairstatus;
    }
}
