package com.nju.sdpt.entity;

import java.util.Date;

public class PubSsdrHmEntity {
    private Integer bh;

    private String sdpName;

    private String idCard;

    private String operatorType;

    private String showTel;

    private String operatorTel;

    private String telBatchNo;

    private Date endTime;

    private Date createTime;

    private Integer newphonestatus;

    private Integer isconfirm;

    private Integer yysdbh;

    private Integer ssdrbh;

    private String hmly;

    private String label;

    public PubSsdrHmEntity(Integer bh, String sdpName, String idCard, String operatorType, String showTel, String operatorTel, String telBatchNo, Date endTime, Date createTime, Integer newphonestatus, Integer isconfirm, Integer yysdbh, Integer ssdrbh, String hmly, String label) {
        this.bh = bh;
        this.sdpName = sdpName;
        this.idCard = idCard;
        this.operatorType = operatorType;
        this.showTel = showTel;
        this.operatorTel = operatorTel;
        this.telBatchNo = telBatchNo;
        this.endTime = endTime;
        this.createTime = createTime;
        this.newphonestatus = newphonestatus;
        this.isconfirm = isconfirm;
        this.yysdbh = yysdbh;
        this.ssdrbh = ssdrbh;
        this.hmly = hmly;
        this.label = label;
    }

    public PubSsdrHmEntity() {
        super();
    }

    public Integer getBh() {
        return bh;
    }

    public void setBh(Integer bh) {
        this.bh = bh;
    }

    public String getSdpName() {
        return sdpName;
    }

    public void setSdpName(String sdpName) {
        this.sdpName = sdpName == null ? null : sdpName.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType == null ? null : operatorType.trim();
    }

    public String getShowTel() {
        return showTel;
    }

    public void setShowTel(String showTel) {
        this.showTel = showTel == null ? null : showTel.trim();
    }

    public String getOperatorTel() {
        return operatorTel;
    }

    public void setOperatorTel(String operatorTel) {
        this.operatorTel = operatorTel == null ? null : operatorTel.trim();
    }

    public String getTelBatchNo() {
        return telBatchNo;
    }

    public void setTelBatchNo(String telBatchNo) {
        this.telBatchNo = telBatchNo == null ? null : telBatchNo.trim();
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getNewphonestatus() {
        return newphonestatus;
    }

    public void setNewphonestatus(Integer newphonestatus) {
        this.newphonestatus = newphonestatus;
    }

    public Integer getIsconfirm() {
        return isconfirm;
    }

    public void setIsconfirm(Integer isconfirm) {
        this.isconfirm = isconfirm;
    }

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }

    public Integer getSsdrbh() {
        return ssdrbh;
    }

    public void setSsdrbh(Integer ssdrbh) {
        this.ssdrbh = ssdrbh;
    }

    public String getHmly() {
        return hmly;
    }

    public void setHmly(String hmly) {
        this.hmly = hmly == null ? null : hmly.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }
}