package com.nju.sdpt.viewobject;

/**
 * 查询当事人手机号列表请求类
 */
public class QuerySdpPhoneVo {

    /**
     * 当事人姓名
     */
    private String sdpName;

    /**
     * 身份证号码
     */
    private String sdpIdCard;
    /**
     * 工单id
     */
    private Integer yysdBh;

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

    public Integer getYysdBh() {
        return yysdBh;
    }

    public void setYysdBh(Integer yysdBh) {
        this.yysdBh = yysdBh;
    }

    public String getSdpName() {
        return sdpName;
    }

    public void setSdpName(String sdpName) {
        this.sdpName = sdpName;
    }

    public String getSdpIdCard() {
        return sdpIdCard;
    }

    public void setSdpIdCard(String sdpIdCard) {
        this.sdpIdCard = sdpIdCard;
    }
}
