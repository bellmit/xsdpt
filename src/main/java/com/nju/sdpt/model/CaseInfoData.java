package com.nju.sdpt.model;

import java.util.List;

public class CaseInfoData {
    /**
     * 案件信息
     */
    private List<CaseInfoAjxx> ajxx;
    /**
     * 文书信息
     */
    private List<CaseInfoWsxx> wsxx;
    /**
     * 当事人信息
     */
    private List<CaseInfoDsrxx> dsrxx;


    public List<CaseInfoAjxx> getAjxx() {
        return ajxx;
    }

    public void setAjxx(List<CaseInfoAjxx> ajxx) {
        this.ajxx = ajxx;
    }

    public List<CaseInfoWsxx> getWsxx() {
        return wsxx;
    }

    public void setWsxx(List<CaseInfoWsxx> wsxx) {
        this.wsxx = wsxx;
    }

    public List<CaseInfoDsrxx> getDsrxx() {
        return dsrxx;
    }

    public void setDsrxx(List<CaseInfoDsrxx> dsrxx) {
        this.dsrxx = dsrxx;
    }
}
