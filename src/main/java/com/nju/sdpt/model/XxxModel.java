package com.nju.sdpt.model;

/**
 * Created by XXT on 2019/5/24.
 */

import com.nju.sdpt.util.StringUtil;

/**
 * 信息项的领域模型
 * @author byron
 *
 */
public class XxxModel {
    /**
     * 信息项编号
     */
    private long xxxbh;
    /**
     * 信息项名称
     */
    private String name;

    /**
     * 信息项数据类型
     */
    private DataTypeEnum datatype;
    /**
     * 信息项简称
     */
    private String xxxjc;
    /**
     * 信息项涉及选项
     */
    private String sjxx;
    /**
     * 信息项涉及选项2016
     */
    private String sjxx2016;
    /**
     * 法院编号
     */
    private long fybh;
    /**
     * 所在列
     */
    private String szl;
    /**
     * 所在表
     */
    private String szb;

    public XxxModel(long xxxbh, String name, DataTypeEnum datatype,
                    String xxxjc, String sjxx,String sjxx2016, long fybh, String szl, String szb) {
        super();
        this.xxxbh = xxxbh;
        this.name = name;
        this.datatype = datatype;
        this.xxxjc = xxxjc;
        this.sjxx = sjxx;
        this.fybh = fybh;
        this.szl = szl;
        this.szb = szb;
        this.sjxx2016=sjxx2016;
    }

    public XxxModel() {
        super();
    }

    public long getXxxbh() {
        return xxxbh;
    }
    public void setXxxbh(long xxxbh) {
        this.xxxbh = xxxbh;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public DataTypeEnum getDatatype() {
        return datatype;
    }
    public void setDatatype(DataTypeEnum datatype) {
        this.datatype = datatype;
    }
    public String getXxxjc() {
        return xxxjc;
    }
    public void setXxxjc(String xxxjc) {
        this.xxxjc = xxxjc;
    }
    public String getSjxx() {
        return sjxx;
    }
    public void setSjxx(String sjxx) {
        this.sjxx = sjxx;
    }

    public long getFybh() {
        return fybh;
    }
    public void setFybh(long fybh) {
        this.fybh = fybh;
    }

    public String getSzl() {
        return szl;
    }
    public void setSzl(String szl) {
        this.szl = szl;
    }
    public String getSzb() {
        return szb;
    }
    public void setSzb(String szb) {
        this.szb = szb;
    }
//    public String toString(){
//        return ToStringBuilder.reflectionToString(this);
//    }



    public String getSjxx2016() {
        return sjxx2016;
    }

    public void setSjxx2016(String sjxx2016) {
        this.sjxx2016 = sjxx2016;
    }
    @Override
    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof XxxModel))
            return false;
        XxxModel castOther = (XxxModel) other;
        return (StringUtil.equals(this.getName(), castOther.getName()))
                && (StringUtil.equals(this.getSzb(), castOther.getSzb()))
                && (StringUtil.equals(this.getSzl(), castOther.getSzl()))
                && (StringUtil.equals(this.getSzl(), castOther.getSzl()))
                && (StringUtil.equals(this.getSjxx(), castOther.getSjxx()))
                && (StringUtil.equals(this.getXxxjc(), castOther.getXxxjc()))
                && (StringUtil.equals(this.getSjxx2016(), castOther.getSjxx2016()))
                && (this.getFybh() == castOther.getFybh())
                && (this.getXxxbh() == castOther.getXxxbh());
    }
}
