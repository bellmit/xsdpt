package com.nju.sdpt.model;

import java.util.Date;

public class RyzxModel {
    int bh;
    //送达员名称
    String sdzy;
    //法院编号
    int fybh;
    //工单反馈总量
    int gdfkzl;
    //工单送达成功量
    int gdsdcgl;
    //工单送达失败量
    int gdsdsbl;
    //工单送达成功率
    String gdsdcgRate;
    //送达超审限案件
    int sdcsxaj;
    //电话送达超审限案件
    int dhsdcsxaj;
    //邮寄送达超审限案件
    int yjsdcsxaj;
    //平均送达时常
    int pjsdsc;
    //接受委托任务流转后响应时间
    int jswtrwlzhxysj;
    //送达总人数
    int sdzrs;
    //送达成功人数
    int sdcgrs;
    //送达失败人数
    int sdsbrs;
    //同意电子送达人数
    int tydzsdrs;
    //拨打电话量按次
    int bddhlac;
    //拨打电话量按人
    int bddhlar;
    //通话总时长
    int thzsc;
    //平均通话时长
    float pjthsc;
    //电话接通率按人
    String dhjtlarRate;
    //电话接通率按次
    double dhjtlacrate;
    //短信发送量
    int dxfsl;
    //带链接短信总量
    int dljdxzl;
    //邮寄次数
    int yjcs;
    //来院领取次数
    int lylqcs;
    //统计维度 1当日 2当周 3当月 4全部
    int scope;
    //统计时间
    String tjsj;
    // 送达专员在人员信息表中的yhdm
    String yhdm;



    public String getYhdm() {
        return yhdm;
    }

    public void setYhdm(String yhdm) {
        this.yhdm = yhdm;
    }

    public RyzxModel() {
    }


    public int getBh() {
        return bh;
    }

    public void setBh(int bh) {
        this.bh = bh;
    }

    public String getSdzy() {
        return sdzy;
    }

    public void setSdzy(String sdzy) {
        this.sdzy = sdzy;
    }

    public int getFybh() {
        return fybh;
    }

    public void setFybh(int fybh) {
        this.fybh = fybh;
    }

    public int getGdfkzl() {
        return gdfkzl;
    }

    public void setGdfkzl(int gdfkzl) {
        this.gdfkzl = gdfkzl;
    }

    public int getGdsdcgl() {
        return gdsdcgl;
    }

    public void setGdsdcgl(int gdsdcgl) {
        this.gdsdcgl = gdsdcgl;
    }

    public int getGdsdsbl() {
        return gdsdsbl;
    }

    public void setGdsdsbl(int gdsdsbl) {
        this.gdsdsbl = gdsdsbl;
    }

    public String getGdsdcgRate() {
        return gdsdcgRate;
    }

    public void setGdsdcgRate(String gdsdcgRate) {
        this.gdsdcgRate = gdsdcgRate;
    }

    public int getSdcsxaj() {
        return sdcsxaj;
    }

    public void setSdcsxaj(int sdcsxaj) {
        this.sdcsxaj = sdcsxaj;
    }

    public int getDhsdcsxaj() {
        return dhsdcsxaj;
    }

    public void setDhsdcsxaj(int dhsdcsxaj) {
        this.dhsdcsxaj = dhsdcsxaj;
    }

    public int getYjsdcsxaj() {
        return yjsdcsxaj;
    }

    public void setYjsdcsxaj(int yjsdcsxaj) {
        this.yjsdcsxaj = yjsdcsxaj;
    }

    public int getPjsdsc() {
        return pjsdsc;
    }

    public void setPjsdsc(int pjsdsc) {
        this.pjsdsc = pjsdsc;
    }

    public int getJswtrwlzhxysj() {
        return jswtrwlzhxysj;
    }

    public void setJswtrwlzhxysj(int jswtrwlzhxysj) {
        this.jswtrwlzhxysj = jswtrwlzhxysj;
    }

    public int getSdzrs() {
        return sdzrs;
    }

    public void setSdzrs(int sdzrs) {
        this.sdzrs = sdzrs;
    }

    public int getSdcgrs() {
        return sdcgrs;
    }

    public void setSdcgrs(int sdcgrs) {
        this.sdcgrs = sdcgrs;
    }

    public int getSdsbrs() {
        return sdsbrs;
    }

    public void setSdsbrs(int sdsbrs) {
        this.sdsbrs = sdsbrs;
    }

    public int getTydzsdrs() {
        return tydzsdrs;
    }

    public void setTydzsdrs(int tydzsdrs) {
        this.tydzsdrs = tydzsdrs;
    }

    public int getBddhlac() {
        return bddhlac;
    }

    public void setBddhlac(int bddhlac) {
        this.bddhlac = bddhlac;
    }

    public int getBddhlar() {
        return bddhlar;
    }

    public void setBddhlar(int bddhlar) {
        this.bddhlar = bddhlar;
    }

    public int getThzsc() {
        return thzsc;
    }

    public void setThzsc(int thzsc) {
        this.thzsc = thzsc;
    }

    public float getPjthsc() {
        return pjthsc;
    }

    public void setPjthsc(float pjthsc) {
        this.pjthsc = pjthsc;
    }

    public String getDhjtlarRate() {
        return dhjtlarRate;
    }

    public void setDhjtlarRate(String dhjtlarRate) {
        this.dhjtlarRate = dhjtlarRate;
    }

    public double getDhjtlacRate() {
        return dhjtlacrate;
    }

    public void setDhjtlacRate(double dhjtlacRate) {
        this.dhjtlacrate = dhjtlacRate;
    }

    public int getDxfsl() {
        return dxfsl;
    }

    public void setDxfsl(int dxfsl) {
        this.dxfsl = dxfsl;
    }

    public int getDljdxzl() {
        return dljdxzl;
    }

    public void setDljdxzl(int dljdxzl) {
        this.dljdxzl = dljdxzl;
    }

    public int getYjcs() {
        return yjcs;
    }

    public void setYjcs(int yjcs) {
        this.yjcs = yjcs;
    }

    public int getLylqcs() {
        return lylqcs;
    }

    public void setLylqcs(int lylqcs) {
        this.lylqcs = lylqcs;
    }

    public int getScope() {
        return scope;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

    public String getTjsj() {
        return tjsj;
    }

    public void setTjsj(String tjsj) {
        this.tjsj = tjsj;
    }
}
