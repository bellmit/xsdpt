package com.nju.sdpt.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author jiaweiq
 * @date 2021/11/8 10:52
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "DXFWINFO")
@XmlType(propOrder = {
        "yysdbh",
        "dxtzid"
})
public class DxlinkStatus {
    private String yysdbh;
    private String dxtzid;

    public DxlinkStatus() {
    }

    public DxlinkStatus(String yysdbh, String dxtzid) {
        this.yysdbh = yysdbh;
        this.dxtzid = dxtzid;
    }

    public String getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(String yysdbh) {
        this.yysdbh = yysdbh;
    }

    public String getDxtzid() {
        return dxtzid;
    }

    public void setDxtzid(String dxtzid) {
        this.dxtzid = dxtzid;
    }
}
