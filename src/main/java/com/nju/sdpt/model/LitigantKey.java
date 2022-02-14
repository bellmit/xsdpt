package com.nju.sdpt.model;

import java.io.Serializable;

/**
 * Created by XXT on 2019/5/9.
 */
public class LitigantKey implements Serializable {
    private String mediationid;

    private Integer dsrbh;

    private static final long serialVersionUID = 1L;

    public String getMediationid() {
        return mediationid;
    }

    public void setMediationid(String mediationid) {
        this.mediationid = mediationid;
    }

    public Integer getDsrbh() {
        return dsrbh;
    }

    public void setDsrbh(Integer dsrbh) {
        this.dsrbh = dsrbh;
    }
}
