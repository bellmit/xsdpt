package com.nju.sdpt.model;

/**
 * Created by XXT on 2019/5/8.
 */


import com.nju.sdpt.entity.XtglYhbEntity;

/**
 *
 * 系统用户的领域模型
 *
 *
 */
public class XtyhModel {
    /**
     * 用户编号
     */
    private long yhbh;
    /**
     * 法院编号
     */
    private long fybh;
    /**
     * 用户代码
     */
    private String yhdm;
    /**
     * 用户名称
     */
    private String yhmc;
    /**
     * 用户口令
     */
    private String yhkl;
    /**
     * 用户部门
     */
    private String yhbm;
    /**
     * 用户身份
     */
    private String yhsf;
    /**
     * 用户状态
     */
    private boolean yhzt;

    /**
     * 是否普通登录
     */
    private String sfptdl;

    private String phone;

    private String sfjagly; //是否结案管理员

    private String tel;//办公电话



    public void setYhbh(long yhbh) {
        this.yhbh = yhbh;
    }

    public long getYhbh() {
        return yhbh;
    }

    public void setFybh(long fybh) {
        this.fybh = fybh;
    }

    public long getFybh() {
        return fybh;
    }

    public void setYhdm(String yhdm) {
        this.yhdm = yhdm;
    }

    public String getYhdm() {
        return yhdm;
    }

    public void setYhmc(String yhmc) {
        this.yhmc = yhmc;
    }

    public String getYhmc() {
        return yhmc;
    }

    public void setYhkl(String yhkl) {
        this.yhkl = yhkl;
    }

    public String getYhkl() {
        return yhkl;
    }

    public void setYhbm(String yhbm) {
        this.yhbm = yhbm;
    }

    public String getYhbm() {
        return yhbm;
    }

    public void setYhsf(String yhsf) {
        this.yhsf = yhsf;
    }

    public String getYhsf() {
        return yhsf;
    }

    public void setYhzt(boolean yhzt) {
        this.yhzt = yhzt;
    }

    public boolean getYhzt() {
        return yhzt;
    }

    public void setSfptdl(String sfptdl) { this.sfptdl = sfptdl; }

    public String getSfptdl() { return sfptdl; }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSfjagly() {
        return sfjagly;
    }

    public void setSfjagly(String sfjagly) {
        this.sfjagly = sfjagly;
    }

    public boolean isYhzt() {
        return yhzt;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public XtglYhbEntity updateXtglYhbDO(XtglYhbEntity xtglyhDO){
        xtglyhDO.setYhbm(yhbm);
        xtglyhDO.setYhkl(yhkl);
        xtglyhDO.setYhmc(yhmc);
        xtglyhDO.setYhsf(yhsf);
        xtglyhDO.setYhdm(yhdm);
        xtglyhDO.setFybh((int)fybh);
        xtglyhDO.setYhzt(yhzt == true ? 1 : 0);
        return xtglyhDO;

    }

    public XtyhModel(XtglYhbEntity xtglyhDO){
        this.yhbh = xtglyhDO.getYhbh();
        this.fybh = xtglyhDO.getFybh();
        this.yhdm = xtglyhDO.getYhdm();
        this.yhmc = xtglyhDO.getYhmc();
        this.yhkl = xtglyhDO.getYhkl();
        this.yhbm = xtglyhDO.getYhbm();
        this.yhsf = xtglyhDO.getYhsf();
//        this.yhzt = xtglyhDO.getYhzt();
        this.sfptdl = xtglyhDO.getSfptdl();
        this.phone = xtglyhDO.getPhone();
        this.sfjagly = xtglyhDO.getSfjagly();
        if(xtglyhDO.getTel()!= null){
            this.tel = xtglyhDO.getTel();
        }
    }

    @Override
    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof XtyhModel))
            return false;
        XtyhModel castOther=(XtyhModel)other;
        boolean isEqual=
                ((this.getYhbh() == castOther.getYhbh()))
                        &&((this.getFybh() == castOther.getFybh()))
                        &&((this.getYhdm() == castOther.getYhdm()) || (this.getYhdm() != null && castOther.getYhdm() != null && this.getYhdm().equals(castOther.getYhdm())))
                        &&((this.getYhbm() == castOther.getYhbm()))
                        &&((this.getYhkl() == castOther.getYhkl()) || (this.getYhkl() != null && castOther.getYhkl() != null && this.getYhkl().equals(castOther.getYhkl())))
                        &&((this.getYhmc() == castOther.getYhmc()) || (this.getYhmc() != null && castOther.getYhmc() != null && this.getYhmc().equals(castOther.getYhmc())))
                        &&((this.getYhsf() == castOther.getYhsf()) || (this.getYhsf() != null && castOther.getYhsf() != null && this.getYhsf().equals(castOther.getYhsf())))
                        &&((this.getYhzt() == castOther.getYhzt()))
                ;
        return isEqual;
    }

}
