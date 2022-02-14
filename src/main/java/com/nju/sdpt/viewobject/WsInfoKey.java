package com.nju.sdpt.viewobject;

public  class WsInfoKey{
    Integer ajxh;
    Integer dsrbh;

    public Integer getAjxh() {
        return ajxh;
    }

    public void setAjxh(Integer ajxh) {
        this.ajxh = ajxh;
    }

    public Integer getDsrbh() {
        return dsrbh;
    }

    public void setDsrbh(Integer dsrbh) {
        this.dsrbh = dsrbh;
    }

    public WsInfoKey(Integer ajxh, Integer dsrbh) {
        this.ajxh = ajxh;
        this.dsrbh = dsrbh;
    }
}
