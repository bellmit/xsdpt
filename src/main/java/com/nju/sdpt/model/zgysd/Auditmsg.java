package com.nju.sdpt.model.zgysd;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class Auditmsg implements Serializable {
    @JSONField(ordinal = 1)
    private String adtime;
    @JSONField(ordinal = 2)
    private String ip;
    @JSONField(ordinal = 3)
    private String fileurl;

    public Auditmsg() {
    }

    public Auditmsg(String adtime, String ip, String fileurl) {
        this.adtime = adtime;
        this.ip = ip;
        this.fileurl = fileurl;
    }

    public String getAdtime() {
        return adtime;
    }

    public void setAdtime(String adtime) {
        this.adtime = adtime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }
}
