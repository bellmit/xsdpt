package com.nju.sdpt.model.report;

import com.nju.sdpt.util.NumberUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 失联触达相关表格数据
 */
@Data
public class ChartslcdTableModel {

    /**
     * 提交查询数量
     */
    private Integer repairNum;

    /**
     * 查询成功数量
     */
    private Integer repairSucNum;
    /**
     * 查询成功率
     */
    private String repairSucRate;

    /**
     * 修复外呼相关数据
     */
    private WebCallChart repairWebCallChart;

    /**
     * 明文外呼相关数据
     */
    private WebCallChart entryWebCallChart;


    /**
     * 明文短信相关数据
     */
    private DxtzChart entryDxtzChart;

    /**
     * 修复短信相关数据
     */
    private DxtzChart repairDxtzChart;

    private ZjsdChart zjsdChart;

    private LylqChart lylqChart;

    private EmsChart emsChart;
    /**
     * 诉讼活动通知总量
     */
    private Integer sshdtzzl;

    public Integer getSshdtzzl() {
        return sshdtzzl;
    }

    public void setSshdtzzl(Integer sshdtzzl) {
        this.sshdtzzl = sshdtzzl;
    }

    public void loadSucRate(Boolean bfh) {
        if(NumberUtil.isIntNotNullAndGtZero(repairNum) && NumberUtil.isIntNotNullAndGtZero(repairSucNum)){
            repairSucRate = String.format("%.2f", ((double)repairSucNum / repairNum) * 100);
            if(new BigDecimal(repairSucRate).compareTo(new BigDecimal(100)) > 0){
                repairSucRate = "100.00";
            }
        }else {
            repairSucRate = "0.00";
        }

        if(bfh){
            repairSucRate += "%";
        }
    }
    public void loadSucRate() {
        loadSucRate(true);
    }

}
