package com.nju.sdpt.model.xml.batch;

import com.nju.sdpt.model.xml.CallRepairQueryResultResponseXml;
import com.nju.sdpt.model.xml.ShortMsgQueryStatusResponseXml;
import com.nju.sdpt.model.xml.ShortMsgSxQueryStatusResponseXml;
import com.nju.sdpt.model.xml.WebCallRecordResponseXml;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @Author: Diao Lin
 * @Date: 2020/3/9 17:53
 * @Description: 外网->内网响应合并XML
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "BatchResponseXml")
@XmlType(propOrder = {
        "callRepairQueryResultResponseXmlList",
        "shortMsgQueryStatusResponseXmlList",
        "plaintextMsgQueryStatusResponseXmlList",
        "webCallRecordResponseXmlList",
        "shortMsgSxQueryStatusResponseXmlList",
})

@Data
public class BatchResponseXml {

    /**
     * 查询修复结果XML list
     */
    private List<CallRepairQueryResultResponseXml> callRepairQueryResultResponseXmlList;
    /**
     * 查询工单短信短信下发状态XML list
     */
    private List<ShortMsgQueryStatusResponseXml> shortMsgQueryStatusResponseXmlList;
    /**
     *
     * 查询法官端明文短信下发状态XML list
     * 因为参数一致 直接使用List<ShortMsgQueryStatusResponseXml> 重命名
     */
    private List<ShortMsgQueryStatusResponseXml> plaintextMsgQueryStatusResponseXmlList;
    /**
     * 查询通话记录XML list
     */
    private List<WebCallRecordResponseXml> webCallRecordResponseXmlList;
    /**
     * 查询闪信下发状态XML list
     */
    private List<ShortMsgSxQueryStatusResponseXml> shortMsgSxQueryStatusResponseXmlList;
}
