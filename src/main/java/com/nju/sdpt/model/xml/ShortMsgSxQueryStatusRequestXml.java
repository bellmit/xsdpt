package com.nju.sdpt.model.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @Author: Diao Lin
 * @Date: 2020/06/02 17:06
 * @Description: [请求] 内网数据交换 闪信查询下发状态请求XML
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ShortMsgSxQueryStatusRequestXml")
@XmlType(propOrder = {
        "taskId"
})
@Data
public class ShortMsgSxQueryStatusRequestXml {

    /**
     * 闪信记录流水号
     */
    private String taskId;
}
