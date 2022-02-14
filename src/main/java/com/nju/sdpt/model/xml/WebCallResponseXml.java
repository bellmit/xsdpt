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
@XmlRootElement(name = "WebCallResponseXml")
@XmlType(propOrder = {
        "callId",
        "uuid"
})
public class WebCallResponseXml {


    /**
     * 内网电话记录标识
     */
    private Integer callId;

    /**
     * 外呼唯一标识
     */
    private String uuid;

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

}
