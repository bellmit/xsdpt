package com.nju.sdpt.model.report;

import com.nju.sdpt.util.NumberUtil;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ZjsdChart extends BaseChart{
    /**
     * 直接送达数量(次)
     */
    public Integer zjsdNum;
    /**
     * 直接送达数量(人)
     */
    public Integer zjsdPeoNum;
    /**
     * 直接送达成功数量
     */
    public Integer zjsdSucNum;
    /**
     * 直接送达失败数量
     */
    public Integer zjsdFailNum;
    /**
     * 直接送达成功率
     */
    public String zjsdSucRate;


    public void loadSucRate() {
        zjsdSucRate = loadSucRateBase(true,zjsdSucNum,zjsdFailNum);
    }
}
