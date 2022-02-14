package com.nju.sdpt.model;

import java.io.Serializable;

/**
 * Created by XXT on 2019/5/9.
 */
public class Litigant extends LitigantKey implements Serializable {
    private String dsrssdw;

    private String dsrlb;

    private String jc;

    private static final long serialVersionUID = 1L;

    public String getDsrssdw() {
        return dsrssdw;
    }

    public void setDsrssdw(String dsrssdw) {
        this.dsrssdw = dsrssdw;
    }

    public String getDsrlb() {
        return dsrlb;
    }

    public void setDsrlb(String dsrlb) {
        this.dsrlb = dsrlb;
    }

    public String getJc() {
        return jc;
    }

    public void setJc(String jc) {
        this.jc = jc;
    }
}
