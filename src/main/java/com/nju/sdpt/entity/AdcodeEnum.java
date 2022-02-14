package com.nju.sdpt.entity;

/**
 * @author jiaweiq
 * @date 2021/12/27 15:28
 */
public enum AdcodeEnum {

    HPQ("53","120101","和平区"),
    HDQ("63","120102","河东区"),
    HXQ("64","120103","河西区"),
    NKQ("54","120104","南开区"),
    HBQ("55","120105","河北区"),
    HQQ("56","120106","红桥区"),
    DLQ("68","120110","东丽区"),
    XQQ("57","120111","西青区"),
    JNQ("69","120112","津南区"),
    BCQ("58","120113","北辰区"),
    WQQ("59","120114","武清区"),
    BDQ("61","120115","宝坻区"),
    BHXQ("74","120116","滨海新区"),
    NHQ("70","120117","宁河区"),
    JHQ("60","120118","静海区"),
    JZQ("73","120119","蓟州区");

    private String fybh;
    private String adcode;
    private String fymc;

    AdcodeEnum() {
    }

    AdcodeEnum(String fybh, String adcode, String fymc) {
        this.fybh = fybh;
        this.adcode = adcode;
        this.fymc = fymc;
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getFymc() {
        return fymc;
    }

    public void setFymc(String fymc) {
        this.fymc = fymc;
    }
}
