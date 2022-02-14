package com.nju.sdpt.model.zgysd;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class AjxxSpzz implements Serializable {
    @JSONField(ordinal = 1)
    private String cbr;
    @JSONField(ordinal = 2)
    private String cbrdh;
    @JSONField(ordinal = 3)
    private String sjy;
    @JSONField(ordinal = 4)
    private String sjydh;

    public AjxxSpzz() {
    }

    public AjxxSpzz(String cbr, String cbrdh, String sjy, String sjydh) {
        this.cbr = cbr;
        this.cbrdh = cbrdh;
        this.sjy = sjy;
        this.sjydh = sjydh;
    }

    public String getCbr() {
        return cbr;
    }

    public void setCbr(String cbr) {
        this.cbr = cbr;
    }

    public String getCbrdh() {
        return cbrdh;
    }

    public void setCbrdh(String cbrdh) {
        this.cbrdh = cbrdh;
    }

    public String getSjy() {
        return sjy;
    }

    public void setSjy(String sjy) {
        this.sjy = sjy;
    }

    public String getSjydh() {
        return sjydh;
    }

    public void setSjydh(String sjydh) {
        this.sjydh = sjydh;
    }
}
