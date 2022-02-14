package com.nju.sdpt.model.zgysd;

import java.util.List;

/**
 * 最高院送达通信Request实体类
 */
public class RequestModel {
    private String czrid;
    private List<String> ajid;
    private String token;

    public RequestModel() {
    }

    public RequestModel(String czrid, List<String> ajid, String token) {
        this.czrid = czrid;
        this.ajid = ajid;
        this.token = token;
    }

    public String getCzrid() {
        return czrid;
    }

    public void setCzrid(String czrid) {
        this.czrid = czrid;
    }

    public List<String> getAjid() {
        return ajid;
    }

    public void setAjid(List<String> ajid) {
        this.ajid = ajid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "RequestModel{" +
                "czrid='" + czrid + '\'' +
                ", ajid=" + ajid +
                ", token='" + token + '\'' +
                '}';
    }
}
