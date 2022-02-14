package com.nju.sdpt.model.sdfk;

import com.nju.sdpt.util.DateUtil;

import java.util.Date;
import java.util.Objects;

/**
 * 送达反馈表 - 来院领取记录
 */
public class LylqResultModel extends BaseModel {

    /**
     * 预约领取时间
     */
    private Date yylqsj;

    /**
     * 预约领取地址
     */
    private String lylqAddress;

    /**
     * 送达结果
     */
    private Integer sdjg;


    /**
     * 送达反馈字符串拼接
     * @return
     */
    public String getSdfkStr(){
        String template = "时间：%s\n领取地点：%s\n送达结果：%s\n";

        String sdjgStr = "暂无送达结果";
        if(Objects.equals(sdjg,1)){
            sdjgStr = "送达成功";
        }else if(Objects.equals(sdjg,2)){
            sdjgStr = "送达失败";
        }
        String result = String.format(template,DateUtil.format(yylqsj,"yyyy年MM月dd HH时mm分"),lylqAddress,sdjgStr);
        return result;
    }

    public Date getYylqsj() {
        return yylqsj;
    }

    public void setYylqsj(Date yylqsj) {
        this.yylqsj = yylqsj;
    }

    public String getLylqAddress() {
        return lylqAddress;
    }

    public void setLylqAddress(String lylqAddress) {
        this.lylqAddress = lylqAddress;
    }

    public Integer getSdjg() {
        return sdjg;
    }

    public void setSdjg(Integer sdjg) {
        this.sdjg = sdjg;
    }
}
