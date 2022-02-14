package com.nju.sdpt.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @Author: Diao Lin
 * @Date: 2019/11/28 9:16
 * @Description: [请求] 内网数据交换 查询通话记录响应类
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "WebCallRecordResponseXml")
@XmlType(propOrder = {
        "callId",
        "uuid",
        "callDuration",
        "callRadioRelativePath"
})
public class WebCallRecordResponseXml {


    /**
     * 内网电话记录标识
     */
    private Integer callId;

    /**
     * 外呼唯一标识
     */
    private String uuid;

    /**
     * 通话时长 单位秒
     */
    private Integer callDuration;

    /**
     * 录音文件 相对路径
     * webCall/webRadio/uuid.mp3
     */
    private String callRadioRelativePath;

    public Integer getCallId() {
        return callId;
    }

    public void setCallId(Integer callId) {
        this.callId = callId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(Integer callDuration) {
        this.callDuration = callDuration;
    }

    public String getCallRadioRelativePath() {
        return callRadioRelativePath;
    }

    public void setCallRadioRelativePath(String callRadioRelativePath) {
        this.callRadioRelativePath = callRadioRelativePath;
    }
}
