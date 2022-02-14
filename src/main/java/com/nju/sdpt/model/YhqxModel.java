package com.nju.sdpt.model;
/**
 * 系统用户权限实体类
 * Created by zzy on 2020/05/27.
 */
public class YhqxModel {
    String yhdm;//用户代码
    String yhmc;//用户名称
    String fybh;//法官编号
    String bmbh;//部门编号
    String bmmc;//部门名称
    Integer authority;//权限 1-院长(全院)  2-非审判部门(全院) 3-审判部门负责人(本部门) 4-部门成员(本人)

    public String getYhmc() {
        return yhmc;
    }

    public void setYhmc(String yhmc) {
        this.yhmc = yhmc;
    }

    public String getYhdm() {
        return yhdm;
    }

    public void setYhdm(String yhdm) {
        this.yhdm = yhdm;
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh;
    }

    public String getBmbh() {
        return bmbh;
    }

    public void setBmbh(String bmbh) {
        this.bmbh = bmbh;
    }

    public String getBmmc() {
        return bmmc;
    }

    public void setBmmc(String bmmc) {
        this.bmmc = bmmc;
    }

    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
    }

    public YhqxModel() {
    }

    public YhqxModel(String yhdm, String fybh, String bmbh, String bmmc, Integer authority) {
        this.yhdm = yhdm;
        this.fybh = fybh;
        this.bmbh = bmbh;
        this.bmmc = bmmc;
        this.authority = authority;
    }
}
