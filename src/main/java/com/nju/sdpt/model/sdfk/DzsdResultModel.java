package com.nju.sdpt.model.sdfk;

import com.nju.sdpt.util.DateUtil;

import java.util.Date;
import java.util.Objects;

/**
 * 送达反馈表 - 电子送达结果 （工单短信通知）
 */
public class DzsdResultModel extends BaseModel {

    /**
     * 短信下发时间 创建时间
     */
    private Date createTime;

    /**
     * 下发号码
     */
    private String showTel;

    /**
     * 短信下发内容
     */
    private String shortMsg;

    /**
     * 短信发送状态
     */
    private Integer sendState;

    /**
     * 访问状态
     */
    private Integer fwState;

    /**
     * 送达结果
     */
    private Integer sdjg;

    /**
     * 送达反馈字符串拼接
     * @return
     */
    public String getSdfkStr(){
        String template = "%s\n接收号码：%s\n"
                +"发送状态：%s\n阅览状态：%s\n"
                +"送达结果：%s\n"
                +"短信内容：%s\n";
        //格式化外呼时长
        String sendStateStr = "发送中";
        if(Objects.equals(sendState,1)){
            sendStateStr = "发送成功";
        }else if(Objects.equals(sendState,2)){
            sendStateStr = "发送失败";
        }

        String fwStateStr = "暂无";
        if(Objects.equals(fwState,1)){
            fwStateStr = "已访问";
        }else if(Objects.equals(fwState,0)){
            fwStateStr = "未访问";
        }

        String sdjgStr = "暂无";
        if(Objects.equals(sdjg,1)){
            sdjgStr = "送达成功";
        }else if(Objects.equals(sdjg,2)){
            sdjgStr = "送达失败";
        }
        String result = String.format(template,DateUtil.format(createTime,"yyyy.MM.dd"),showTel,sendStateStr,fwStateStr,sdjgStr,shortMsg);
        return result;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getShowTel() {
        return showTel;
    }

    public void setShowTel(String showTel) {
        this.showTel = showTel;
    }

    public String getShortMsg() {
        return shortMsg;
    }

    public void setShortMsg(String shortMsg) {
        this.shortMsg = shortMsg;
    }

    public Integer getSendState() {
        return sendState;
    }

    public void setSendState(Integer sendState) {
        this.sendState = sendState;
    }

    public Integer getFwState() {
        return fwState;
    }

    public void setFwState(Integer fwState) {
        this.fwState = fwState;
    }

    public Integer getSdjg() {
        return sdjg;
    }

    public void setSdjg(Integer sdjg) {
        this.sdjg = sdjg;
    }
}
