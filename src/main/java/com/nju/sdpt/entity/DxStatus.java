package com.nju.sdpt.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author jiaweiq
 * @date 2021/11/10 11:06
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "DXZTINFO")
@XmlType(propOrder = {
        "yysdbh",
        "dxtzid",
        "code",
        "msg"
})
public class DxStatus {
    private String yysdbh;
    private String dxtzid;
    private String code;
    private String msg;

    public DxStatus(String yysdbh, String dxtzid, String code, String msg) {
        this.yysdbh = yysdbh;
        this.dxtzid = dxtzid;
        this.code = code;
        this.msg = msg;
    }

    public DxStatus() {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
