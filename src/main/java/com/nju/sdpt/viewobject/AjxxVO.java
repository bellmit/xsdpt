package com.nju.sdpt.viewobject;

import com.nju.sdpt.model.AjjbxxModel;

import java.util.List;

public class AjxxVO {
    private String fgmc;
    private List<AjjbxxModel> ajjbxxModels;

    public String getFgmc() {
        return fgmc;
    }

    public void setFgmc(String fgmc) {
        this.fgmc = fgmc;
    }

    public List<AjjbxxModel> getAjjbxxModels() {
        return ajjbxxModels;
    }

    public void setAjjbxxModels(List<AjjbxxModel> ajjbxxModels) {
        this.ajjbxxModels = ajjbxxModels;
    }
}
