package com.nju.sdpt.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @author jiaweiq
 * @date 2021/11/4 16:49
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "AchieveDataModel",
})
public class ZjsdXml {
    private List<ZjsdResponseModel> AchieveDataModel;

    public ZjsdXml() {
    }

    public ZjsdXml(List<ZjsdResponseModel> achieveDataModel) {
        AchieveDataModel = achieveDataModel;
    }

    public List<ZjsdResponseModel> getAchieveDataModel() {
        return AchieveDataModel;
    }

    public void setAchieveDataModel(List<ZjsdResponseModel> achieveDataModel) {
        AchieveDataModel = achieveDataModel;
    }
}
