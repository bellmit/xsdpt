package com.nju.sdpt.model.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "AddZjsdDataResponseXml")
@XmlType(propOrder = {
        "yysdbh",
        "ssdrbh",
        "sdAddress",
        "dwAddress",
        "comeDoorTime",
        "directServicePictureList",
        "receiptPic",
        "remark",
        "zfObjectId",
        "zfEmployeeId",
        "receiptResult"
})
@Data
public class AddZjsdDataResponseXml {

    /**
     * 预约送达编号  即工单编号
     */
    private Integer yysdbh;
    /**
     * 受送达人编号
     */
    private Integer ssdrbh;
    /**
     * 送达地址
     */
    private String sdAddress;
    /**
     * 定位地址
     */
    private String dwAddress;
    /**
     * 上门时间
     */
    private Date comeDoorTime;
    /**
     * 直接送达过程记录图片
     */
    private List<String> directServicePictureList;
    /**
     * 送达回执
     */
    private String receiptPic;
    /**
     * 备注
     */
    private String remark;
    /**
     * 智法小程序id
     */
    private Integer zfEmployeeId;
    /**
     * 创建人id  智法账户id
     */
    private String zfObjectId;
    /**
     * 签收结果
     */
    private Integer receiptResult;

    public Integer getReceiptResult() {
        return receiptResult;
    }

    public void setReceiptResult(Integer receiptResult) {
        this.receiptResult = receiptResult;
    }

    public String getZfObjectId() {
        return zfObjectId;
    }

    public void setZfObjectId(String zfObjectId) {
        this.zfObjectId = zfObjectId;
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

    public String getSdAddress() {
        return sdAddress;
    }

    public void setSdAddress(String sdAddress) {
        this.sdAddress = sdAddress;
    }

    public String getDwAddress() {
        return dwAddress;
    }

    public void setDwAddress(String dwAddress) {
        this.dwAddress = dwAddress;
    }

    public Date getComeDoorTime() {
        return comeDoorTime;
    }

    public void setComeDoorTime(Date comeDoorTime) {
        this.comeDoorTime = comeDoorTime;
    }

    public List<String> getDirectServicePictureList() {
        return directServicePictureList;
    }

    public void setDirectServicePictureList(List<String> directServicePictureList) {
        this.directServicePictureList = directServicePictureList;
    }

    public String getReceiptPic() {
        return receiptPic;
    }

    public void setReceiptPic(String receiptPic) {
        this.receiptPic = receiptPic;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getZfEmployeeId() {
        return zfEmployeeId;
    }

    public void setZfEmployeeId(Integer zfEmployeeId) {
        this.zfEmployeeId = zfEmployeeId;
    }
}
