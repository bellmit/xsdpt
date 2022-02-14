package com.nju.sdpt.model;

public class getParamjson {
    private String paramjson;
    private int yysdbh;
    private int ssdrbh;
    private int templateid;

    public String getParamjson() {
        return paramjson;
    }

    public void setParamjson(String paramjson) {
        this.paramjson = paramjson;
    }

    public int getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(int yysdbh) {
        this.yysdbh = yysdbh;
    }

    public int getSsdrbh() {
        return ssdrbh;
    }

    public void setSsdrbh(int ssdrbh) {
        this.ssdrbh = ssdrbh;
    }

    public int getTemplateid() {
        return templateid;
    }

    public void setTemplateid(int templateid) {
        this.templateid = templateid;
    }

    public getParamjson(int templateid) {
        this.templateid = templateid;
    }

    public getParamjson() {
    }
}
