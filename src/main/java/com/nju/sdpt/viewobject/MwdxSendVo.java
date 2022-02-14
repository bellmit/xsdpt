package com.nju.sdpt.viewobject;

import com.nju.sdpt.entity.PubMwdxSendEntity;

import java.util.List;

public class MwdxSendVo extends PubMwdxSendEntity {
    private String[] urlLis;

    public String[] getUrlLis() {
        return urlLis;
    }

    public void setUrlLis(String[] urlLis) {
        this.urlLis = urlLis;
    }
}
