package com.nju.sdpt.model;

/**
 * Created by XXT on 2019/5/24.
 */

import java.util.List;

/**
 * 构件的领域模型
 * @author byron
 *
 */
public class GjModel {
    /**
     * 构件编号
     */
    private long gjbh;
    /**
     * 构件名称
     */
    private String gjmc;
    /**
     * 构件简称
     */
    private String gjjc;
    /**
     * 构件模型
     */
    private String gjmx;
    /**
     * 构件显示对象
     */
    private String gjxsdx;
    /**
     * 构件对应表
     */
    private String gjdyb;
    /**
     * 构件对应主键
     */
    private String gjdyzj;
    /**
     * 与案件关系
     * 1:
     * 0:
     */
    private String yajgx;
    /**
     * 释放有快捷方式
     */
    private String sfykjfs;
    /**
     * 是否显示在树形结构
     */
    private String sfxszsxjg;
    /**
     * 快捷方式显示样式
     */
    private String kjfsxsys;
    /**
     * 父构件编号
     */
    private long fgjbh;
    /**
     * 法院编号
     */
    private long fybh;
    /**
     * 构件对应的信息项列表
     */
    private List<XxxModel> xxxList;
    /**
     * 构件信息项关系列表
     */
    private List<GjXxxGxModel> gxList;
    /**
     * 对应ProcDef的ID
     */
    private long procdefid;
    /**
     * 显示顺序
     */
    private Integer xssx;
    /**
     * 构件对应的jsp
     */
    private String gjurl;

    public GjModel(long gjbh, String gjmc, String gjjc, String gjmx,
                   String gjxsdx, String gjdyb, String gjdyzj, String yajgx, String sfykjfs, String sfxszsxjg,
                   String kjfsxsys, long fgjbh, long fybh,
                   List<XxxModel> xxxList, List<GjXxxGxModel> gxList, long procdefid, Integer xssx, String gjurl) {
        super();
        this.gjbh = gjbh;
        this.gjmc = gjmc;
        this.gjjc = gjjc;
        this.gjmx = gjmx;
        this.gjxsdx = gjxsdx;
        this.gjdyb = gjdyb;
        this.gjdyzj = gjdyzj;
        this.yajgx = yajgx;
        this.sfykjfs = sfykjfs;
        this.sfxszsxjg = sfxszsxjg;
        this.kjfsxsys = kjfsxsys;
        this.fgjbh = fgjbh;
        this.fybh = fybh;
        this.xxxList = xxxList;
        this.gxList = gxList;
        this.procdefid = procdefid;
        this.xssx = xssx;
        this.gjurl = gjurl;
    }

    public GjModel() {
        super();
    }

    public long getGjbh() {
        return gjbh;
    }
    public void setGjbh(long gjbh) {
        this.gjbh = gjbh;
    }
    public String getGjmc() {
        return gjmc;
    }
    public void setGjmc(String gjmc) {
        this.gjmc = gjmc;
    }
    public String getGjjc() {
        return gjjc;
    }
    public void setGjjc(String gjjc) {
        this.gjjc = gjjc;
    }
    public String getGjmx() {
        return gjmx;
    }
    public void setGjmx(String gjmx) {
        this.gjmx = gjmx;
    }
    public String getGjxsdx() {
        return gjxsdx;
    }
    public void setGjxsdx(String gjxsdx) {
        this.gjxsdx = gjxsdx;
    }

    public String getGjdyb() {
        return gjdyb;
    }

    public void setGjdyb(String gjdyb) {
        this.gjdyb = gjdyb;
    }

    public String getGjdyzj() {
        return gjdyzj;
    }

    public void setGjdyzj(String gjdyzj) {
        this.gjdyzj = gjdyzj;
    }

    public String getYajgx() {
        return yajgx;
    }
    public void setYajgx(String yajgx) {
        this.yajgx = yajgx;
    }
    public String getSfykjfs() {
        return sfykjfs;
    }
    public void setSfykjfs(String sfykjfs) {
        this.sfykjfs = sfykjfs;
    }
    public String getSfxszsxjg() {
        return sfxszsxjg;
    }
    public void setSfxszsxjg(String sfxszsxjg) {
        this.sfxszsxjg = sfxszsxjg;
    }
    public String getKjfsxsys() {
        return kjfsxsys;
    }
    public void setKjfsxsys(String kjfsxsys) {
        this.kjfsxsys = kjfsxsys;
    }
    public long getFgjbh() {
        return fgjbh;
    }
    public void setFgjbh(long fgjbh) {
        this.fgjbh = fgjbh;
    }
    public long getFybh() {
        return fybh;
    }
    public void setFybh(long fybh) {
        this.fybh = fybh;
    }
    public List<XxxModel> getXxxList() {
        return xxxList;
    }
    public void setXxxList(List<XxxModel> xxxList) {
        this.xxxList = xxxList;
    }
    public List<GjXxxGxModel> getGxList() {
        return gxList;
    }
    public void setGxList(List<GjXxxGxModel> gxList) {
        this.gxList = gxList;
    }

    public long getProcdefid() {
        return procdefid;
    }

    public void setProcdefid(long procdefid) {
        this.procdefid = procdefid;
    }

    public Integer getXssx() {
        return xssx;
    }

    public void setXssx(Integer xssx) {
        this.xssx = xssx;
    }

    public String getGjurl() {
        return gjurl;
    }

    public void setGjurl(String gjurl) {
        this.gjurl = gjurl;
    }

    @Override
    public String toString() {
        return "GjModel [fgjbh=" + fgjbh + ", fybh=" + fybh + ", gjbh=" + gjbh
                + ", gjdyb=" + gjdyb + ", gjdyzj=" + gjdyzj + ", gjjc=" + gjjc
                + ", gjmc=" + gjmc + ", gjmx=" + gjmx + ", gjurl=" + gjurl
                + ", gjxsdx=" + gjxsdx + ", gxList=" + gxList + ", kjfsxsys="
                + kjfsxsys + ", procdefid=" + procdefid + ", sfxszsxjg="
                + sfxszsxjg + ", sfykjfs=" + sfykjfs + ", xssx=" + xssx
                + ", xxxList=" + xxxList + ", yajgx=" + yajgx + "]";
    }
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof GjModel))
            return false;

        GjModel castOther = (GjModel) other;

        boolean isEqual=
                (this.getGjbh()==castOther.getGjbh())
                        &&isStringEqual(this.getGjmc(), castOther.getGjmc())
                        &&isStringEqual(this.getGjjc(), castOther.getGjjc())
                        &&isStringEqual(this.getGjmx(), castOther.getGjmx())
                        &&isStringEqual(this.getGjxsdx(), castOther.getGjxsdx())
                        &&isStringEqual(this.getYajgx(), castOther.getYajgx())
                        &&isStringEqual(this.getSfykjfs(), castOther.getSfykjfs())
                        &&isStringEqual(this.getSfxszsxjg(), castOther.getSfxszsxjg())
                        &&isStringEqual(this.getKjfsxsys(), castOther.getKjfsxsys())
                        &&this.getFgjbh()==castOther.getFgjbh()
                        &&this.getFybh()==castOther.getFybh();

        return isEqual;
    }

    private boolean isStringEqual(String str1, String str2){
        return ( (str1==str2) || (str1!=null && str2!=null && str1.equals(str2)) );
    }

}
