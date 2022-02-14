package com.nju.sdpt.entity;

/**
 * @author jiaweiq
 * @date 2021/10/25 15:47
 */
public class PreviewModel {
    private String fymc;
    private String ay;
    private String uuid;

    public PreviewModel() {
    }

    public PreviewModel(String fymc, String ay, String uuid) {
        this.fymc = fymc;
        this.ay = ay;
        this.uuid = uuid;
    }

    public String getFymc() {
        return fymc;
    }

    public void setFymc(String fymc) {
        this.fymc = fymc;
    }

    public String getAy() {
        return ay;
    }

    public void setAy(String ay) {
        this.ay = ay;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
