package com.nju.sdpt.model;

import com.nju.sdpt.entity.PubWebCallInfoEntity;
import lombok.Data;

import java.util.Date;

@Data
public class PubWebCallInfoModel extends PubWebCallInfoEntity {

    private String whTime;

    /**
     * 受送达人名称
     */
    private String ssdrmc;
    /**
     * 案号
     */
    private String ah;

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public String getSsdrmc() {
        return ssdrmc;
    }

    public void setSsdrmc(String ssdrmc) {
        this.ssdrmc = ssdrmc;
    }

    public String getWhTime() {
        return whTime;
    }

    public void setWhTime(String whTime) {
        this.whTime = whTime;
    }


}
