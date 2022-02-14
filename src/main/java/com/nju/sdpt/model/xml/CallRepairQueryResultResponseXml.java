package com.nju.sdpt.model.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @Author: Diao Lin
 * @Date: 2019/11/28 9:16
 * @Description: [请求] 内网数据交换 查询修复结果响应类
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CallRepairQueryResultResponseXml")
@XmlType(propOrder = {
        "yysdbh",
        "ssdrbh",
        "batchId",
        "sdpIdCard",
        "reapirBatchStatus",
        "reapirResult"
})
@Data
public class CallRepairQueryResultResponseXml {

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
     * 请求修复时返回的批次号
     */
    private String batchId;

    /**
     * 当事人身份证密文 SHA256
     */
    private String sdpIdCard;

    /**
     * 本批次修复状态,com.nju.sdpt.constant.SdptConstants.REPAIR_STATUS
     */
    private String reapirBatchStatus;

    private List<ReapirResult> reapirResult;

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getSdpIdCard() {
        return sdpIdCard;
    }

    public void setSdpIdCard(String sdpIdCard) {
        this.sdpIdCard = sdpIdCard;
    }

    public String getReapirBatchStatus() {
        return reapirBatchStatus;
    }

    public void setReapirBatchStatus(String reapirBatchStatus) {
        this.reapirBatchStatus = reapirBatchStatus;
    }

    public List<ReapirResult> getReapirResult() {
        return reapirResult;
    }

    public void setReapirResult(List<ReapirResult> reapirResult) {
        this.reapirResult = reapirResult;
    }

    public static class ReapirResult{

        /**
         * 号码类型 CUCC WCCMCC QXB
         */
        private String operatorType;

        /**
         * 号码唯一标识
         */
        private String repairNumber;

        /**
         * web显示号码  加密中间4位  如: 176****4038
         */
        private String tel;

        /**
         * 号码批次号
         */
        private String repairBatch;

        public String getOperatorType() {
            return operatorType;
        }

        public void setOperatorType(String operatorType) {
            this.operatorType = operatorType;
        }

        public String getRepairNumber() {
            return repairNumber;
        }

        public void setRepairNumber(String repairNumber) {
            this.repairNumber = repairNumber;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getRepairBatch() {
            return repairBatch;
        }

        public void setRepairBatch(String repairBatch) {
            this.repairBatch = repairBatch;
        }
    }

}
