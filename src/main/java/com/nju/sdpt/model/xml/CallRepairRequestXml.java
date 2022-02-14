package com.nju.sdpt.model.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @Author: Diao Lin
 * @Date: 2019/11/28 9:16
 * @Description: [请求] 内网数据交换 提交修复请求类
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CallRepairRequestXml")
@XmlType(propOrder = {
        "yysdbh",
        "ssdrbh",
        "sdpName",
        "sdpIdCard",
        "showNumber"
})
@Data
public class CallRepairRequestXml {

    /**
     * 预约送达编号  即工单编号
     */
    private Integer yysdbh;

    /**
     * 受送达人编号
     */
    private Integer ssdrbh;

    public Integer getSsdrbh() {
        return ssdrbh;
    }

    public void setSsdrbh(Integer ssdrbh) {
        this.ssdrbh = ssdrbh;
    }

    /**
     * 当事人姓名
     */
    private String sdpName;

    /**
     * 当事人身份证密文 SHA256
     */
    private String sdpIdCard;

    /**
     * 外显号码
     */
    private String showNumber;

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }

    public String getSdpName() {
        return sdpName;
    }

    public void setSdpName(String sdpName) {
        this.sdpName = sdpName;
    }

    public String getSdpIdCard() {
        return sdpIdCard;
    }

    public void setSdpIdCard(String sdpIdCard) {
        this.sdpIdCard = sdpIdCard;
    }

    public String getShowNumber() {
        return showNumber;
    }

    public void setShowNumber(String showNumber) {
        this.showNumber = showNumber;
    }
}
