package com.nju.sdpt.model.zgysd;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

public class CaseItem implements Serializable {
    @JSONField(ordinal = 1)
    private AjxxJbxx jbxx;
    @JSONField(ordinal = 2)
    private AjxxSpzz spzz;
    @JSONField(ordinal = 3)
    private List<AjxxDsrItem> dsrlist;
    @JSONField(ordinal = 4)
    private List<AjxxWsxx> wsxxlist;

    public CaseItem() {
    }

    public CaseItem(AjxxJbxx jbxx, AjxxSpzz spzz, List<AjxxDsrItem> dsrlist, List<AjxxWsxx> wsxxlist) {
        this.jbxx = jbxx;
        this.spzz = spzz;
        this.dsrlist = dsrlist;
        this.wsxxlist = wsxxlist;
    }

    public AjxxJbxx getJbxx() {
        return jbxx;
    }

    public void setJbxx(AjxxJbxx jbxx) {
        this.jbxx = jbxx;
    }

    public AjxxSpzz getSpzz() {
        return spzz;
    }

    public void setSpzz(AjxxSpzz spzz) {
        this.spzz = spzz;
    }

    public List<AjxxDsrItem> getDsrlist() {
        return dsrlist;
    }

    public void setDsrlist(List<AjxxDsrItem> dsrlist) {
        this.dsrlist = dsrlist;
    }

    public List<AjxxWsxx> getWsxxlist() {
        return wsxxlist;
    }

    public void setWsxxlist(List<AjxxWsxx> wsxxlist) {
        this.wsxxlist = wsxxlist;
    }
}
