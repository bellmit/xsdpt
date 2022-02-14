package com.nju.sdpt.model.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "AddNewOrderRequestXml")
@XmlType(propOrder = {
        "yysdbh",
        "ah",
        "ssdrList",
        "zfEmployeeId"
})
@Data
public class AddNewOrderRequestXml {

    /**
     * 预约送达编号  即工单编号
     */
    private Integer yysdbh;
    /**
     * 案号
     */
    private String ah;
    /**
     * 受送达人集合
     */
    private List<SdpList> ssdrList;

    /**
     * 关联智法账户id
     * @return
     */
    private Integer zfEmployeeId;

    public Integer getZfEmployeeId() {
        return zfEmployeeId;
    }

    public void setZfEmployeeId(Integer zfEmployeeId) {
        this.zfEmployeeId = zfEmployeeId;
    }

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public List<SdpList> getSsdrList() {
        return ssdrList;
    }

    public void setSsdrList(List<SdpList> ssdrList) {
        this.ssdrList = ssdrList;
    }
    public static class SdpList{
        //受送达人姓名
        private String sdpName;
        //受送达人编号
        private Integer ssdrbh;
        //地址信息集合
        private List<String> addressList;

        public List<String> getAddressList() {
            return addressList;
        }

        public void setAddressList(List<String> addressList) {
            this.addressList = addressList;
        }

        public String getSdpName() {
            return sdpName;
        }

        public void setSdpName(String sdpName) {
            this.sdpName = sdpName;
        }

        public Integer getSsdrbh() {
            return ssdrbh;
        }

        public void setSsdrbh(Integer ssdrbh) {
            this.ssdrbh = ssdrbh;
        }
    }
}
