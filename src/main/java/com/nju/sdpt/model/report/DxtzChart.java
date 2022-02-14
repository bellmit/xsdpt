package com.nju.sdpt.model.report;

import com.nju.sdpt.util.NumberUtil;
import lombok.Data;
import org.apache.poi.ss.formula.functions.Rate;

import java.math.BigDecimal;

/**
 * 短信相关数据
 */
@Data
public class DxtzChart extends BaseChart {

    /**
     * 下发数量
     */
    public Integer dxtzNum;
    /**
     * 纯文字下发数量
     */
    public Integer cwzDxtzNum;
    /**
     * 带链接下发数量
     */
    public Integer dljDxtzNum;
    /**
     * 带链接下发访问数量
     */
    public Integer dljDxtzFwNum;

    /**
     * 访问率
     */
    public String dxtzFwRate;
    /**
     * 成功送达数量
     */
    public Integer dxtzSucNum;
    /**
     * 失败送达数量
     */
    public Integer dxtzFailNum;
   /**
     * 访问率
     */
    public String dxtzSucRate;

    public Integer dxtzWsfs;


    public void loadSucRate(boolean bfh) {
        dxtzFwRate = loadSucRateBase(true,dljDxtzFwNum,dljDxtzNum==null?null:(dljDxtzFwNum==null?null:dljDxtzNum-dljDxtzFwNum));
        dxtzSucRate = loadSucRateBase(bfh,dxtzSucNum,dxtzFailNum);
    }
}
