package com.nju.sdpt.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @Author: Diao Lin
 * @Date: 2019/11/28 9:16
 * @Description: [请求] 内网数据交换 闪信查询状态响应类
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ShortMsgSxQueryStatusResponseXml")
@XmlType(propOrder = {
        "taskId",
        "code",
})
public class ShortMsgSxQueryStatusResponseXml {

    /**
     * 短信记录流水号
     */
    private String taskId;

    /**
     * 短信下发状态 = DELIVRD 代表成功  其余全部为失败
     */
    private String code;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
