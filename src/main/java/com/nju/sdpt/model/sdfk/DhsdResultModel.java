package com.nju.sdpt.model.sdfk;

import com.nju.sdpt.util.DateUtil;

import java.util.Date;

/**
 * 送达反馈表 - 电话送达结果
 */
public class DhsdResultModel extends BaseModel{

    /**
     * 拨打时间
     */
    private Date callTime;

    /**
     * 被叫号码
     */
    private String webTel;

    /**
     * 主叫送达专员电话
     */
    private String seatNumber;

    /**
     * 电话状态
     */
    private String dhzt;

    /**
     * 通话时长（单位秒）
     */
    private Integer callDuration;

    /**
     * 送达结果
     */
    private String sdjg;

    /**
     * 外呼备注
     */
    private String remarks;

    /**
     * 送达反馈字符串拼接
     * @return
     */
    public String getSdfkStr(){
        String template = "%s\n被叫号码：%s\n主叫号码：%s\n"
                +"电话状态：%s\n外呼时长：%s\n送达结果：%s\n备注：%s\n"
               ;
        //格式化外呼时长
        String unitFormat = DateUtil.secToTime(callDuration);
        String result = String.format(template,DateUtil.format(callTime,"yyyy.MM.dd"),webTel,seatNumber,dhzt,unitFormat,sdjg,remarks);
        return result;
    }

    public Date getCallTime() {
        return callTime;
    }

    public void setCallTime(Date callTime) {
        this.callTime = callTime;
    }

    public String getWebTel() {
        return webTel;
    }

    public void setWebTel(String webTel) {
        this.webTel = webTel;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getDhzt() {
        return dhzt;
    }

    public void setDhzt(String dhzt) {
        this.dhzt = dhzt;
    }

    public Integer getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(Integer callDuration) {
        this.callDuration = callDuration;
    }

    public String getSdjg() {
        return sdjg;
    }

    public void setSdjg(String sdjg) {
        this.sdjg = sdjg;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
