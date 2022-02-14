package com.nju.sdpt.viewobject;

import java.util.List;
import java.util.Set;

/**
 * 报错电话状态请求类
 */
public class SubmitWebCallVo {

    private Integer webCallId;

    /**
     * 本次通话记录状态
     */
    private Integer callstate;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 确认电话集合
     */
    private Set<String> confirmNumberSet;

    /**
     * 确认地址集合
     */
    private Set<String> confirmAddressSet;
    /**
     * 是否同意电子送达
     */
    private Integer electronSend;
    /**
     * 送达方式
     */
    private String sendType;

    /**
     * 预约时间加地点-来院领取
     */
    private String order;
    /**
     * 邮寄送达地址
     */
    private String mailAddress;
    /**
     * 短信送达目标号码
     */
    private String callPhone;

    /**
     * 当事人姓名
     */
    private String sdpName;
    /**
     * 案号
     */
    private String caseNo;

    /**
     * 工单id
     * @return
     */
    private Integer yysdBh;
    /**
     * 创建人
     */
    private String createPeople;
    /**
     * 账号名
     * @return
     */
    private String yhdm;
    /**
     * 受送达人编号
     */
    private Integer ssdrBh;
    /**
     * 案件编号
     */
    private Integer ajxh;
    /**
     * 法院编号
     */
    private String fybh;
    /**
     * 送达结果 1成功 2失败 其他不处理
     */
    private Integer sdjg;

    /**
     * 委托方式
     */
    private String wtfs;

    /**
     * 委托处理人编号
     */
    private Integer wtclrbh;

    /**
     * 送达员编号
     * @return
     */
    private  Integer sdybh;

    /**
     * 当事人文书类别
     */
    List<String> dsrwslbArray;

    public Integer getSdybh() {
        return sdybh;
    }

    public void setSdybh(Integer sdybh) {
        this.sdybh = sdybh;
    }

    public Integer getSdjg() {
        return sdjg;
    }

    public void setSdjg(Integer sdjg) {
        this.sdjg = sdjg;
    }

    public Integer getWebCallId() {
        return webCallId;
    }

    public void setWebCallId(Integer webCallId) {
        this.webCallId = webCallId;
    }

    public Integer getCallstate() {
        return callstate;
    }

    public void setCallstate(Integer callstate) {
        this.callstate = callstate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Set<String> getConfirmNumberSet() {
        return confirmNumberSet;
    }

    public void setConfirmNumberSet(Set<String> confirmNumberSet) {
        this.confirmNumberSet = confirmNumberSet;
    }

    public Set<String> getConfirmAddressSet() {
        return confirmAddressSet;
    }

    public void setConfirmAddressSet(Set<String> confirmAddressSet) {
        this.confirmAddressSet = confirmAddressSet;
    }

    public Integer getElectronSend() {
        return electronSend;
    }

    public void setElectronSend(Integer electronSend) {
        this.electronSend = electronSend;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getCallPhone() {
        return callPhone;
    }

    public void setCallPhone(String callPhone) {
        this.callPhone = callPhone;
    }

    public String getSdpName() {
        return sdpName;
    }

    public void setSdpName(String sdpName) {
        this.sdpName = sdpName;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public Integer getYysdBh() {
        return yysdBh;
    }

    public void setYysdBh(Integer yysdBh) {
        this.yysdBh = yysdBh;
    }

    public String getCreatePeople() {
        return createPeople;
    }

    public void setCreatePeople(String createPeople) {
        this.createPeople = createPeople;
    }

    public String getYhdm() {
        return yhdm;
    }

    public void setYhdm(String yhdm) {
        this.yhdm = yhdm;
    }

    public Integer getSsdrBh() {
        return ssdrBh;
    }

    public void setSsdrBh(Integer ssdrBh) {
        this.ssdrBh = ssdrBh;
    }

    public Integer getAjxh() {
        return ajxh;
    }

    public void setAjxh(Integer ajxh) {
        this.ajxh = ajxh;
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh;
    }

    public String getWtfs() {
        return wtfs;
    }

    public void setWtfs(String wtfs) {
        this.wtfs = wtfs;
    }

    public Integer getWtclrbh() {
        return wtclrbh;
    }

    public void setWtclrbh(Integer wtclrbh) {
        this.wtclrbh = wtclrbh;
    }

    public List<String> getDsrwslbArray() {
        return dsrwslbArray;
    }

    public void setDsrwslbArray(List<String> dsrwslbArray) {
        this.dsrwslbArray = dsrwslbArray;
    }
}
