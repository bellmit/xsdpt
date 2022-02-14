package com.nju.sdpt.entity;

public class PubLogEntityKey {
    private Integer logbh;

    private Integer ajxh;

    private String fybh;

    public PubLogEntityKey(Integer logbh, Integer ajxh, String fybh) {
        this.logbh = logbh;
        this.ajxh = ajxh;
        this.fybh = fybh;
    }

    public PubLogEntityKey() {
        super();
    }

    public Integer getLogbh() {
        return logbh;
    }

    public void setLogbh(Integer logbh) {
        this.logbh = logbh;
    }

    public Integer getAjxh() {
        return ajxh;
    }

    public void setAjxh(Integer ajxh) {
        this.ajxh = ajxh;
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh == null ? null : fybh.trim();
    }
}