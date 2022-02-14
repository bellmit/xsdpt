package com.nju.sdpt.model;

/**
 * Created by XXT on 2019/5/24.
 */
/**
 * 审限规则领域模型
 * @author lc
 *
 */
public class SxgzModel {

    /**
     * 规则编号
     */
    private long gzbh;
    /**
     * 法院编号
     */
    private long fybh;
    /**
     * 审限类型
     */
    private String sxlx;
    /**
     * 案件属性
     */
    private String ajsx;
    /**
     * 审限值
     */
    private long sxz;
    /**
     * 是否有效
     */
    private String sfyx;
    /**
     * 起算时间
     */
    private String qssj;
    /**
     * 立案期限
     */
    private long laqx;
    /**
     * 规则名称
     */
    private String sxmc;
    /**
     * 审限描述
     */
    private String sxms;

    /**
     * 规则校验范围
     */
    private String gzjyajfw;

    public long getGzbh() {
        return gzbh;
    }
    public void setGzbh(long gzbh) {
        this.gzbh = gzbh;
    }
    public long getFybh() {
        return fybh;
    }
    public void setFybh(long fybh) {
        this.fybh = fybh;
    }
    public String getSxlx() {
        return sxlx;
    }
    public void setSxlx(String sxlx) {
        this.sxlx = sxlx;
    }
    public String getAjsx() {
        return ajsx;
    }
    public void setAjsx(String ajsx) {
        this.ajsx = ajsx;
    }
    public long getSxz() {
        return sxz;
    }
    public void setSxz(long sxz) {
        this.sxz = sxz;
    }
    public String getSfyx() {
        return sfyx;
    }
    public void setSfyx(String sfyx) {
        this.sfyx = sfyx;
    }

    public String getQssj() {
        return qssj;
    }

    public void setQssj(String qssj) {
        this.qssj = qssj;
    }

    public long getLaqx() {
        return laqx;
    }
    public void setLaqx(long laqx) {
        this.laqx = laqx;
    }
    public String getSxmc() {
        return sxmc;
    }
    public void setSxmc(String sxmc) {
        this.sxmc = sxmc;
    }
    public String getSxms() {
        return sxms;
    }
    public void setSxms(String sxms) {
        this.sxms = sxms;
    }

    public String getGzjyajfw() {
        return gzjyajfw;
    }
    public void setGzjyajfw(String gzjyajfw) {
        this.gzjyajfw = gzjyajfw;
    }
//    public String toString(){
//        return ToStringBuilder.reflectionToString(this);
////    }
}
