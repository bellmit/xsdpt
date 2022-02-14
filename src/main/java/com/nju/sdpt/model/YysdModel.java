package com.nju.sdpt.model;

import com.nju.sdpt.model.info.YysdInfoModel;
import com.nju.sdpt.util.DateUtil;

import java.util.Date;
import java.util.StringJoiner;

public class YysdModel {
    /**
     * 案件序号
     */
    private String ajxh;
    private String fybh;
    private String yysdbh;
    private String ah;
    private String ajmc;
    //预约人
    private String yyr;
    //预约时间
    private String yysj;
    //当事人名称
    private String dsrmc;
    //送达员名称
    private String sdymc;
    private String laay;
    //开庭时间
    private String ktsj;
    /**
     * 送达状态
     */
    private String sdzt;
    /**
     * 预约人电话
     */
    private String yyrdh;
    /**
     * 送达结果
     */
    private String sdjg;
    /**
     * 工单所属法院名称
     */
    private String fymc;
    /**
     * 委托任务数
     */
    private String wtrws;
    //是否生成文件
    private String sfscwj;
    //法官预约时备注
    private String yysdbz;
    //部门名称
    private String bmmc;

    /**
     * 是否标红
     */
    private String markRed;
    /**
     * 是否标黄
     */
    private String markYellow;

    private String sdsj;

    private String bz;

    public String getBmmc() {
        return bmmc;
    }

    public void setBmmc(String bmmc) {
        this.bmmc = bmmc;
    }

    public String getKtsj() {
        return ktsj;
    }

    public void setKtsj(String ktsj) {
        this.ktsj = ktsj;
    }

    public String getLaay() {
        return laay;
    }

    public void setLaay(String laay) {
        this.laay = laay;
    }


    public String getSfscwj() {
        return sfscwj;
    }

    public void setSfscwj(String sfscwj) {
        this.sfscwj = sfscwj;
    }

    public String getAjxh() {
        return ajxh;
    }

    public void setAjxh(String ajxh) {
        this.ajxh = ajxh;
    }

    public String getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(String yysdbh) {
        this.yysdbh = yysdbh;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public String getAjmc() {
        return ajmc;
    }

    public void setAjmc(String ajmc) {
        this.ajmc = ajmc;
    }

    public String getYyr() {
        return yyr;
    }

    public void setYyr(String yyr) {
        this.yyr = yyr;
    }

    public String getYysj() {
        return yysj;
    }

    public void setYysj(String yysj) {
        this.yysj = yysj;
    }

    public String getDsrmc() {
        return dsrmc;
    }

    public void setDsrmc(String dsrmc) {
        this.dsrmc = dsrmc;
    }

    public String getSdymc() {
        return sdymc;
    }

    public void setSdymc(String sdymc) {
        this.sdymc = sdymc;
    }

    public String getSdzt() {
        return sdzt;
    }

    public void setSdzt(String sdzt) {
        this.sdzt = sdzt;
    }

    public String getYyrdh() {
        return yyrdh;
    }

    public void setYyrdh(String yyrdh) {
        this.yyrdh = yyrdh;
    }

    public String getSdjg() {
        return sdjg;
    }

    public void setSdjg(String sdjg) {
        this.sdjg = sdjg;
    }

    public String getFymc() {
        return fymc;
    }

    public void setFymc(String fymc) {
        this.fymc = fymc;
    }

    public String getWtrws() {
        return wtrws;
    }

    public void setWtrws(String wtrws) {
        this.wtrws = wtrws;
    }

    public String getYysdbz() {
        return yysdbz;
    }

    public void setYysdbz(String yysdbz) {
        this.yysdbz = yysdbz;
    }

    public String getMarkRed() {
        return markRed;
    }

    public void setMarkRed(String markRed) {
        this.markRed = markRed;
    }

    public String getMarkYellow() {
        return markYellow;
    }

    public void setMarkYellow(String markYellow) {
        this.markYellow = markYellow;
    }

    public String getSdsj() {
        return sdsj;
    }

    public void setSdsj(String sdsj) {
        this.sdsj = sdsj;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public YysdModel() {
    }

    public YysdModel(String ajxh, String yysdbh, String ah, String ajmc, String yyr, String yysj, String dsrmc, String sdymc, String laay, String sdzt, String yyrdh, String sdjg, String fybh, String fymc, String wtrws) {
        this.ajxh = ajxh;
        this.yysdbh = yysdbh;
        this.ah = ah;
        this.ajmc = ajmc;
        this.yyr = yyr;
        this.yysj = yysj;
        this.dsrmc = dsrmc;
        this.sdymc = sdymc;
        this.laay = laay;
        this.sdzt = sdzt;
        this.yyrdh = yyrdh;
        this.sdjg = sdjg;
        this.fybh = fybh;
        this.fymc = fymc;
        this.wtrws = wtrws;
    }

    public YysdModel(String ajxh, String yysdbh, String ah, String ajmc, String yyr, String yysj, String dsrmc, String sdymc, String sdzt, String yyrdh, String sdjg, String fymc) {
        this.ajxh = ajxh;
        this.yysdbh = yysdbh;
        this.ah = ah;
        this.ajmc = ajmc;
        this.yyr = yyr;
        this.yysj = yysj;
        this.dsrmc = dsrmc;
        this.sdymc = sdymc;
        this.sdzt = sdzt;
        this.yyrdh = yyrdh;
        this.sdjg = sdjg;
        this.fymc = fymc;
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh;
    }

    public YysdModel(String ajxh, String yysdbh, String ah, String ajmc, String yyr, String yysj, String dsrmc, String sdymc, String sdzt, String yyrdh, String sdjg, String fybh, String fymc) {
        this.ajxh = ajxh;
        this.yysdbh = yysdbh;
        this.ah = ah;
        this.ajmc = ajmc;
        this.yyr = yyr;
        this.yysj = yysj;
        this.dsrmc = dsrmc;
        this.sdymc = sdymc;
        this.sdzt = sdzt;
        this.yyrdh = yyrdh;
        this.sdjg = sdjg;
        this.fybh = fybh;
        this.fymc = fymc;
    }


    public YysdModel(YysdInfoModel info){
        this.ajxh = info.getAjxh();
        this.yysdbh = info.getYysdbh();
        this.ah = info.getAh();
        this.ajmc = info.getAjmc();
        this.yyr = info.getYyr();
        this.yysj = info.getYysj();
        StringJoiner stringTokenizer = new StringJoiner(",");
        for (String xm:info.getDsrmc()){
            stringTokenizer.add(xm);
        }
        this.sdsj = info.getSdsj();
        this.dsrmc = stringTokenizer.toString();
        this.sdymc = info.getSdyxm();
        this.sdzt = info.getSdzt();
        this.yyrdh = info.getYyrdh();
        this.sdjg = info.getSdjg();
        this.fybh = info.getFybh();
        this.fymc = FYEnum.getFyByFybh(fybh)!=null?FYEnum.getFyByFybh(fybh).getJc():"";
        this.wtrws = info.getWtrws();
        this.ktsj = info.getKtsj();
        this.laay = info.getLaay();
        this.yysdbz = info.getYysdbz();
        this.laay = info.getLaay();
        this.bmmc = info.getBmmc();
        if(this.ktsj != null && !"".equals(this.ktsj)){
            long diffDays = DateUtil.getDiffDays(DateUtil.parse(this.ktsj, DateUtil.noSecondFormat), new Date());
            this.markRed = diffDays >= 0 && diffDays < 3  ? "y" : "n";
            this.markYellow = diffDays >= 0 && diffDays < 7 ? "y" : "n";
        }


    }
}
