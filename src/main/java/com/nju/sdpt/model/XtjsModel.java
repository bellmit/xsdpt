package com.nju.sdpt.model;

/**
 * Created by XXT on 2019/5/7.
 */

import java.util.Date;

/**
 *
 * 系统角色的领域模型
 *
 */
public class XtjsModel {

    /**
     * 角色编号
     */
    private long jsbh;

    /**
     * 法院编号
     */
    private long fybh;
    /**
     * 角色名称
     */
    private String jsmc;
    /**
     * 涉及部门
     */
    private String sjbm;
    /**
     * 创建时间
     */
    private Date cjsj;


//    public String toString() {
//        return ToStringBuilder.reflectionToString(this);
//    }

    public void setJsbh(long jsbh) {
        this.jsbh = jsbh;
    }

    public long getJsbh() {
        return jsbh;
    }

    public void setFybh(long fybh) {
        this.fybh = fybh;
    }

    public long getFybh() {
        return fybh;
    }

    public void setJsmc(String jsmc) {
        this.jsmc = jsmc;
    }

    public String getJsmc() {
        return jsmc;
    }

    public void setSjbm(String sjbm) {
        this.sjbm = sjbm;
    }

    public String getSjbm() {
        return sjbm;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof XtjsModel))
            return false;
        XtjsModel castOther = (XtjsModel) other;
        boolean isEqual = ((this.getJsbh() == castOther.getJsbh()))
                && ((this.getFybh() == castOther.getFybh()))
                && ((this.getJsmc()) == castOther.getJsmc())
                || (this.getJsmc() != null && castOther.getJsmc() != null && this
                .getJsmc().equals(castOther.getJsmc

                        ()))
                && ((this.getSjbm() == castOther.getSjbm()))
                && ((this.getCjsj().toGMTString() == castOther.getCjsj()
                .toGMTString()) || (this.getCjsj().toGMTString() != null
                && castOther.getCjsj

                ().toGMTString() != null && this.getCjsj()
                .toGMTString()
                .equals(castOther.getCjsj().toGMTString())));
        return isEqual;
    }
}
