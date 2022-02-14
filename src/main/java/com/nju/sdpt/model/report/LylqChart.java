package com.nju.sdpt.model.report;

import com.nju.sdpt.util.NumberUtil;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LylqChart extends BaseChart{
    /**
     * 来院领取数量(次)
     */
    public Integer lylqNum;
    /**
     * 来院领取数量(人)
     */
    public Integer lylqPeoNum;

    /**
     * 来院领取成功数量
     */
    public Integer lylqSucNum;
    /**
     * 来院领取失败数量
     */
    public Integer lylqFailNum;
    /**
     * 来院领取成功率
     */
    public String lylqSucRate;


    public void loadSucRate() {
        lylqSucRate = loadSucRateBase(true,lylqSucNum,lylqFailNum);
    }
}
