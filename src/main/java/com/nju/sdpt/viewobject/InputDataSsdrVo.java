package com.nju.sdpt.viewobject;

/**
 * 受送达人 - 录入信息请求类
 * 联系电话/地址/身份证号码
 */
public class InputDataSsdrVo {

    /**
     * 预约送达编号 - 工单号
     */
    private Integer yysdbh;

    /**
     * 受送达人编号
     */
    private Integer ssdrbh;

    /**
     * 录入类型
     * 参数规则： com.nju.sdpt.constant.SdptConstants.INPUT_DATA_TYPE
     */
    private String inputDataType;

    /**
     * 录入内容
     * 电话/地址/身份证
     */
    private String content;

    public InputDataSsdrVo() {
    }

    public InputDataSsdrVo(Integer yysdbh, Integer ssdrbh, String inputDataType, String content) {
        this.yysdbh = yysdbh;
        this.ssdrbh = ssdrbh;
        this.inputDataType = inputDataType;
        this.content = content;
    }

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }

    public Integer getSsdrbh() {
        return ssdrbh;
    }

    public void setSsdrbh(Integer ssdrbh) {
        this.ssdrbh = ssdrbh;
    }

    public String getInputDataType() {
        return inputDataType;
    }

    public void setInputDataType(String inputDataType) {
        this.inputDataType = inputDataType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
