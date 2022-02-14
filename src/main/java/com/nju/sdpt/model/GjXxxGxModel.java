package com.nju.sdpt.model;

/**
 * Created by XXT on 2019/5/24.
 */
public class GjXxxGxModel {
    /**
     * 关系id
     */
    private long gxid;
    /**
     * 信息项编号
     */
    private long xxxbh;
    /**
     * 构件编号
     */
    private long gjbh;
    /**
     * 法院编号
     */
    private long fybh;
    /**
     * 是否必填项
     */
    private String sfbtx;
    /**
     * 是否修改
     */
    private String sfxg;
    /**
     * 是否能引起信息项发生变化
     */
    private String keyColumn;
    /**
     * 信息项类型 1：共性 2：个性
     */
    private String type;

    // Constructors

    /** default constructor */
    public GjXxxGxModel() {
    }

    public GjXxxGxModel(long gxid, long xxxbh, long gjbh, long fybh, String sfbtx,
                        String sfxg, String type, String keyColumn) {
        super();
        this.gxid = gxid;
        this.xxxbh = xxxbh;
        this.gjbh = gjbh;
        this.fybh = fybh;
        this.sfbtx = sfbtx;
        this.sfxg = sfxg;
        this.type = type;
        this.keyColumn = keyColumn;
    }

    public long getGxid() {
        return gxid;
    }


    public void setGxid(long gxid) {
        this.gxid = gxid;
    }


    public long getXxxbh() {
        return xxxbh;
    }

    public void setXxxbh(long xxxbh) {
        this.xxxbh = xxxbh;
    }

    public long getGjbh() {
        return gjbh;
    }

    public void setGjbh(long gjbh) {
        this.gjbh = gjbh;
    }

    public String getSfbtx() {
        return sfbtx;
    }

    public void setSfbtx(String sfbtx) {
        this.sfbtx = sfbtx;
    }

    public String getSfxg() {
        return sfxg;
    }

    public void setSfxg(String sfxg) {
        this.sfxg = sfxg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getFybh() {
        return fybh;
    }

    public void setFybh(long fybh) {
        this.fybh = fybh;
    }

    public String getKeyColumn() {
        return keyColumn;
    }

    public void setKeyColumn(String keyColumn) {
        this.keyColumn = keyColumn;
    }

    @Override
    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof GjXxxGxModel))
            return false;

        GjXxxGxModel castOther = (GjXxxGxModel) other;

        boolean isEqual=
                (this.getGjbh()==castOther.getGjbh())
                        &&(this.getXxxbh()== castOther.getXxxbh())
                        &&isStringEqual(this.getSfbtx(), castOther.getSfbtx())
                        &&(this.getGxid()== castOther.getGxid());


        return isEqual;
    }

    private boolean isStringEqual(String str1, String str2){
        return ( (str1==str2) || (str1!=null && str2!=null && str1.equals(str2)) );
    }

//    public String toString(){
//        return ToStringBuilder.reflectionToString(this);
////    }

}
