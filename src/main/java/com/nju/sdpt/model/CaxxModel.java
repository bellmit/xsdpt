package com.nju.sdpt.model;


import com.nju.sdpt.entity.AjJbEntity;
import com.nju.sdpt.util.DateUtil;
import com.nju.sdpt.util.StringUtil;

/**
 * Created by XXT on 2019/5/24.
 */
public class CaxxModel {
    private long ajxh;
    private String ah;
    private String ajmc;
    private String larq;
    private String jarq;
    private String cbr;
    private String sjy;
    private String cabz;
    private String xsbh;
    private String jafs;
    private String sxrq;
    private String gdrq;
    private String jaay;
    public CaxxModel(){}
    public CaxxModel(AjJbEntity ajjbDO){
        this.setAjxh(ajjbDO.getAjxh());
        this.setAh(ajjbDO.getAh());
        this.setAjmc(ajjbDO.getAjmc());
        this.setLarq(DateUtil.format(ajjbDO.getLarq(), DateUtil.webFormat));
        this.setJarq(DateUtil.format(ajjbDO.getJarq(), DateUtil.webFormat));
        this.setCabz(StringUtil.trim(ajjbDO.getJayydm()));
        this.setSxrq(DateUtil.format(ajjbDO.getSxrq(), DateUtil.webFormat));
        this.setGdrq(DateUtil.format(ajjbDO.getGdrq(), DateUtil.webFormat));
    }
    public CaxxModel(long ajxh, String ah, String ajmc, String larq,
                     String jarq, String cbr, String cabz, String xsbh) {
        super();
        this.ajxh = ajxh;
        this.ah = ah;
        this.ajmc = ajmc;
        this.larq = larq;
        this.jarq = jarq;
        this.cbr = cbr;
        this.cabz = cabz;
        this.xsbh = xsbh;
    }
    public long getAjxh() {
        return ajxh;
    }
    public void setAjxh(long ajxh) {
        this.ajxh = ajxh;
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
    public String getLarq() {
        return larq;
    }
    public void setLarq(String larq) {
        this.larq = larq;
    }
    public String getJarq() {
        return jarq;
    }
    public void setJarq(String jarq) {
        this.jarq = jarq;
    }
    public String getCbr() {
        return cbr;
    }
    public void setCbr(String cbr) {
        this.cbr = cbr;
    }
    public String getCabz() {
        return cabz;
    }
    public void setCabz(String cabz) {
        this.cabz = cabz;
    }
    public String getXsbh() {
        return xsbh;
    }
    public void setXsbh(String xsbh) {
        this.xsbh = xsbh;
    }
    public String getSjy() {
        return sjy;
    }
    public void setSjy(String sjy) {
        this.sjy = sjy;
    }
    public String getJafs() {
        return jafs;
    }
    public void setJafs(String jafs) {
        this.jafs = jafs;
    }
    public String getSxrq() {
        return sxrq;
    }
    public void setSxrq(String sxrq) {
        this.sxrq = sxrq;
    }
    public String getGdrq() {
        return gdrq;
    }
    public void setGdrq(String gdrq) {
        this.gdrq = gdrq;
    }
    public String getJaay() {
        return jaay;
    }
    public void setJaay(String jaay) {
        this.jaay = jaay;
    }


}
