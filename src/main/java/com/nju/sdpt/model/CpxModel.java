package com.nju.sdpt.model;

/**
 * Created by XXT on 2019/5/24.
 */

import com.nju.sdpt.util.StringUtil;

import java.util.List;

/**
 * 构件的领域模型
 * @author byron
 *
 */
public class CpxModel {
    /**
     * 产品线编号
     */
    private long cpxbh;
    /**
     * 产品线名称
     */
    private String cpxmc;
    /**
     * 产品线简称
     */
    private String cpxjc;
    /**
     * 产品线类型
     * 1:立案
     * 2：审理
     */
    private String cpxlx;
    /**
     * 对应条件
     */
    private String dytj;
    /**
     * 产品线对应的构件列表
     */
    private List<GjModel> gjList;
    /**
     * 产品线构件关系列表
     */
    private List<CpxGjGxModel> gxList;


    public CpxModel() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CpxModel(long cpxbh, String cpxmc, String cpxjc, String cpxlx, String dytj,
                    List<GjModel> gjList, List<CpxGjGxModel> gxList) {
        super();
        this.cpxbh = cpxbh;
        this.cpxmc = cpxmc;
        this.cpxjc = cpxjc;
        this.cpxlx = cpxlx;
        this.dytj = dytj;
        this.gjList = gjList;
        this.gxList = gxList;
    }

    public long getCpxbh() {
        return cpxbh;
    }

    public void setCpxbh(long cpxbh) {
        this.cpxbh = cpxbh;
    }

    public String getCpxmc() {
        return cpxmc;
    }

    public void setCpxmc(String cpxmc) {
        this.cpxmc = cpxmc;
    }

    public String getCpxlx() {
        return cpxlx;
    }

    public void setCpxlx(String cpxlx) {
        this.cpxlx = cpxlx;
    }

    public String getDytj() {
        return dytj;
    }

    public void setDytj(String dytj) {
        this.dytj = dytj;
    }

    public List<GjModel> getGjList() {
        return gjList;
    }

    public void setGjList(List<GjModel> gjList) {
        this.gjList = gjList;
    }

    public List<CpxGjGxModel> getGxList() {
        return gxList;
    }

    public void setGxList(List<CpxGjGxModel> gxList) {
        this.gxList = gxList;
    }

    public String getCpxjc() {
        return cpxjc;
    }

    public void setCpxjc(String cpxjc) {
        this.cpxjc = cpxjc;
    }

//    public String toString(){
//        return ToStringBuilder.reflectionToString(this);
//    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof CpxModel))
            return false;

        CpxModel castOther = (CpxModel) other;

        boolean isEqual=
                (this.getCpxbh()==castOther.getCpxbh())
                        && StringUtil.equals(this.getCpxmc(), castOther.getCpxmc())
                        &&StringUtil.equals(this.getDytj(), castOther.getDytj())
                        &&StringUtil.equals(this.getCpxlx(), castOther.getCpxlx());

        return isEqual;
    }
}
