package com.nju.sdpt.model.sdfk;

import com.nju.sdpt.util.DateUtil;

import java.util.Date;
import java.util.Objects;

public class ZjsdResultModel {
    /**
     * 直接送达地址
     */
    private String zjsdAddress;
    /**
     * 上门时间
     */
    private Date smlqsj;
    /**
     * 上门结果
     */
    private String smjg;
    /**
     * 送达结果
     */
    private String sdjg;

    public String getZjsdAddress() {
        return zjsdAddress;
    }

    public void setZjsdAddress(String zjsdAddress) {
        this.zjsdAddress = zjsdAddress;
    }

    public Date getSmlqsj() {
        return smlqsj;
    }

    public void setSmlqsj(Date smlqsj) {
        this.smlqsj = smlqsj;
    }

    public String getSmjg() {
        return smjg;
    }

    public void setSmjg(String smjg) {
        this.smjg = smjg;
    }

    public String getSdjg() {
        return sdjg;
    }

    public void setSdjg(String sdjg) {
        this.sdjg = sdjg;
    }

    /**
     * 送达反馈字符串拼接
     * @return
     */
    public String getSdfkStr(){
        String template = "上门时间：%s\n上门地址：%s\n上门结果：%s\n送达结果：%s\n";

        String sdjgStr = "暂无";
        if(Objects.equals(smjg,"1")){
            sdjgStr = "妥投";
        }else if(Objects.equals(smjg,"2")){
            sdjgStr = "拒收";
        }else if(Objects.equals(smjg,"3")){
            sdjgStr = "本人签收";
        }
        String result = String.format(template, DateUtil.format(smlqsj,"yyyy年MM月dd HH时mm分"),zjsdAddress,sdjgStr,sdjg);
        return result;
    }
}
