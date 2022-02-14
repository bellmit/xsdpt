package com.nju.sdpt.model.zgysd;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class AjxxWsxx implements Serializable {
    @JSONField(ordinal = 1)
    private String wsid;
    @JSONField(ordinal = 2)
    private String wslx;
    @JSONField(ordinal = 3)
    private String wsmc;
    @JSONField(ordinal = 4)
    private String wsurl;

    public AjxxWsxx() {
    }

    public AjxxWsxx(String wsid, String wslx, String wsmc, String wsurl) {
        this.wsid = wsid;
        this.wslx = wslx;
        this.wsmc = wsmc;
        this.wsurl = wsurl;
    }

    public String getWsid() {
        return wsid;
    }

    public void setWsid(String wsid) {
        this.wsid = wsid;
    }

    public String getWslx() {
        return wslx;
    }

    public void setWslx(String wslx) {
        this.wslx = wslx;
    }

    public String getWsmc() {
        return wsmc;
    }

    public void setWsmc(String wsmc) {
        this.wsmc = wsmc;
    }

    public String getWsurl() {
        return wsurl;
    }

    public void setWsurl(String wsurl) {
        this.wsurl = wsurl;
    }
}
