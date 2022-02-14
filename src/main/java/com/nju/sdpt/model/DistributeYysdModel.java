package com.nju.sdpt.model;

import java.util.List;

public class DistributeYysdModel {
    private String fybh;
    private Integer sdrybh;
    private List<Integer> selectedYysdbh;


    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh;
    }

    public Integer getSdrybh() {
        return sdrybh;
    }

    public void setSdrybh(Integer sdrybh) {
        this.sdrybh = sdrybh;
    }

    public List<Integer> getSelectedYysdbh() {
        return selectedYysdbh;
    }

    public void setSelectedYysdbh(List<Integer> selectedYysdbh) {
        this.selectedYysdbh = selectedYysdbh;
    }
}
