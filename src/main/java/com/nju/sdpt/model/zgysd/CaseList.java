package com.nju.sdpt.model.zgysd;

import java.io.Serializable;
import java.util.List;

public class CaseList implements Serializable {
    private List<CaseItem> caselist;

    public CaseList() {
    }

    public CaseList(List<CaseItem> caselist) {
        this.caselist = caselist;
    }

    public List<CaseItem> getCaselist() {
        return caselist;
    }

    public void setCaselist(List<CaseItem> caselist) {
        this.caselist = caselist;
    }
}
