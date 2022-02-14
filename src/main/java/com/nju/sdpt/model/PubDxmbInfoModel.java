package com.nju.sdpt.model;

import com.nju.sdpt.entity.PubDxmbInfoEntity;
import com.nju.sdpt.viewobject.ShortMsgParamObj;

import java.util.List;

public class PubDxmbInfoModel extends PubDxmbInfoEntity {
    private List<String> paramNameList;

    /**
     * 模板所属法院名称
     */
    private String fymc;

    public String getFymc() {
        return fymc;
    }

    public void setFymc(String fymc) {
        this.fymc = fymc;
    }

    public List<String> getParamNameList() {
        return paramNameList;
    }

    public void setParamNameList(List<String> paramNameList) {
        this.paramNameList = paramNameList;
    }
}
