package com.nju.sdpt.model.zgysd;

import com.alibaba.fastjson.annotation.JSONField;
import com.nju.sdpt.entity.PubQtsscyrEntity;

import java.io.Serializable;

public class AjxxDlrxx implements Serializable {
    @JSONField(ordinal = 1)
    private String dlrxm;
    @JSONField(ordinal = 2)
    private String dlrsjh;
    @JSONField(ordinal = 3)
    private String dlrzjh;
    @JSONField(ordinal = 4)
    private String dlrlxdz;

    public AjxxDlrxx() {
    }

    public AjxxDlrxx(String dlrxm, String dlrsjh, String dlrzjh, String dlrlxdz) {
        this.dlrxm = dlrxm;
        this.dlrsjh = dlrsjh;
        this.dlrzjh = dlrzjh;
        this.dlrlxdz = dlrlxdz;
    }

    public AjxxDlrxx(PubQtsscyrEntity pubQtsscyrEntity) {
        this.dlrxm = pubQtsscyrEntity.getXm();
        this.dlrsjh = pubQtsscyrEntity.getDh();
        this.dlrzjh = pubQtsscyrEntity.getSfzhm();
        this.dlrlxdz = pubQtsscyrEntity.getDz();
    }

    public String getDlrxm() {
        return dlrxm;
    }

    public void setDlrxm(String dlrxm) {
        this.dlrxm = dlrxm;
    }

    public String getDlrsjh() {
        return dlrsjh;
    }

    public void setDlrsjh(String dlrsjh) {
        this.dlrsjh = dlrsjh;
    }

    public String getDlrzjh() {
        return dlrzjh;
    }

    public void setDlrzjh(String dlrzjh) {
        this.dlrzjh = dlrzjh;
    }

    public String getDlrlxdz() {
        return dlrlxdz;
    }

    public void setDlrlxdz(String dlrlxdz) {
        this.dlrlxdz = dlrlxdz;
    }
}
