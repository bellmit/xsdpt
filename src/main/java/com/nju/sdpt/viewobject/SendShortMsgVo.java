package com.nju.sdpt.viewobject;

import com.nju.sdpt.entity.PubLylqInfoEntity;

import java.util.List;

/**
 * 短信通知 - 下发短信 - VO
 */
public class SendShortMsgVo {
    String[] urlLis;

    public String[] getUrlLis() {
        return urlLis;
    }

    public void setUrlLis(String[] urlLis) {
        this.urlLis = urlLis;
    }

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 号码编号 集合
     */
    List<Integer> targetTelBhs;

    /**
     * 短信模板ID
     */
    private Integer templateId;
    private Integer ssdrbh;

    public Integer getSsdrbh() {
        return ssdrbh;
    }

    public void setSsdrbh(Integer ssdrbh) {
        this.ssdrbh = ssdrbh;
    }

    /**
     * 工单编号
     */
    private Integer yysdbh;

    /**
     * 发送参数集合
     */
    private List<ShortMsgParamObj> paramObjList;

    /**
     * 当事人文书类别
     */
    List<String> dsrwslbArray;

    public List<Integer> getTargetTelBhs() {
        return targetTelBhs;
    }

    public void setTargetTelBhs(List<Integer> targetTelBhs) {
        this.targetTelBhs = targetTelBhs;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }

    public List<ShortMsgParamObj> getParamObjList() {
        return paramObjList;
    }

    public void setParamObjList(List<ShortMsgParamObj> paramObjList) {
        this.paramObjList = paramObjList;
    }

    public List<String> getDsrwslbArray() {
        return dsrwslbArray;
    }

    public void setDsrwslbArray(List<String> dsrwslbArray) {
        this.dsrwslbArray = dsrwslbArray;
    }
}
