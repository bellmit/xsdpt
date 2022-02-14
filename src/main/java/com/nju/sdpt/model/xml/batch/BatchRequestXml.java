package com.nju.sdpt.model.xml.batch;

import com.nju.sdpt.model.xml.*;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @Author: Diao Lin
 * @Date: 2020/03/09 17:16
 * @Description: 内网->外网请求合并XML
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "BatchRequestXml")
@XmlType(propOrder = {
        "callRepairQueryResultRequestXmlList",
        "shortMsgQueryStatusRequestXmlList",
        "plaintextMsgQueryStatusRequestXmlList",
        "webCallRecordRequestXmlList",
        "shortMsgSxQueryStatusRequestXmlList",
})
@Data
public class BatchRequestXml {

    /**
     * 查询修复结果XML list
     */
    private List<CallRepairQueryResultRequestXml> callRepairQueryResultRequestXmlList;
    /**
     * 查询工单短信短信下发状态XML list
     */
    private List<ShortMsgQueryStatusRequestXml> shortMsgQueryStatusRequestXmlList;
    /**
     * 查询法官端明文短信下发状态XML list
     */
    private List<PlaintextMsgQueryStatusRequestXml> plaintextMsgQueryStatusRequestXmlList;
    /**
     * 查询通话记录XML list
     */
    private List<WebCallRecordRequestXml> webCallRecordRequestXmlList;
    /**
     * 查询闪信状态 XML list
     */
    private List<ShortMsgSxQueryStatusRequestXml> shortMsgSxQueryStatusRequestXmlList;
}
