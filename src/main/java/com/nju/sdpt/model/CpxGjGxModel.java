package com.nju.sdpt.model;

import com.nju.sdpt.util.StringUtil;

/**
 * Created by XXT on 2019/5/24.
 */
public class CpxGjGxModel {
    /**
     * 产品线构件关系主键
     */
    private long cpxgjgxid;
    /**
     * 产品线编号
     */
    private long cpxbh;
    /**
     * 构件编号
     */
    private long gjbh;
    /**
     * 法院编号
     */
    private long fybh;
    /**
     * 构件是否必须
     */
    private String sfbx;
    /**
     * 权限
     */
    private String qx;

    public CpxGjGxModel(long cpxgjgxid, long cpxbh, long gjbh, long fybh,
                        String sfbx, String qx) {
        super();
        this.cpxgjgxid = cpxgjgxid;
        this.cpxbh = cpxbh;
        this.gjbh = gjbh;
        this.fybh = fybh;
        this.sfbx = sfbx;
        this.qx = qx;
    }

    public CpxGjGxModel() {
        super();
        // TODO Auto-generated constructor stub
    }

    public long getCpxgjgxid() {
        return cpxgjgxid;
    }

    public void setCpxgjgxid(long cpxgjgxid) {
        this.cpxgjgxid = cpxgjgxid;
    }

    public long getCpxbh() {
        return cpxbh;
    }

    public void setCpxbh(long cpxbh) {
        this.cpxbh = cpxbh;
    }

    public long getGjbh() {
        return gjbh;
    }

    public void setGjbh(long gjbh) {
        this.gjbh = gjbh;
    }

    public long getFybh() {
        return fybh;
    }

    public void setFybh(long fybh) {
        this.fybh = fybh;
    }

    public String getSfbx() {
        return sfbx;
    }

    public void setSfbx(String sfbx) {
        this.sfbx = sfbx;
    }

    public String getQx() {
        return qx;
    }

    public void setQx(String qx) {
        this.qx = qx;
    }

//    public String toString(){
//        return ToStringBuilder.reflectionToString(this);
//    }

    @Override
    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof GjXxxGxModel))
            return false;

        CpxGjGxModel castOther = (CpxGjGxModel) other;

        boolean isEqual=
                (this.getGjbh()==castOther.getGjbh())
                        &&(this.getCpxbh()== castOther.getCpxbh())
                        &&(this.getFybh()== castOther.getFybh())
                        && StringUtil.equals(this.getSfbx(), castOther.getSfbx())
                        &&(this.getCpxgjgxid()== castOther.getCpxgjgxid());

        return isEqual;
    }
}
