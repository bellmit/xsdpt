package com.nju.sdpt.model.report;

import com.nju.sdpt.util.NumberUtil;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 累计案件相关数据统计
 */
@Data
public class ChartCaseModel {

    //累计收案量
    private Integer ljCase;

    //累计送达成功案件量
    private Integer ljSucCase;

    //累计送达完成案件量
    private Integer ljEndCase;

    //(新增)送达中案件量
    private Integer sdzCase;

    //案件送达成功率
    private String caseSucRate;

    //累计受送达人数
    private Integer ljSddr;

    //累计送达成功人数
    private Integer ljSucSddr;

    //累计送达完成人数
    private Integer ljEndSddr;

    //送达中人数
    private Integer sdzSddr;

    //受送达人成功率
    private String ssdrSucRate;

    //短信通知成功人数

    private Integer dxtzSucNum;

    //短信通知成功率
    private String dxtzSucRate;

    //外呼次数
    private Integer ljWebCallNum;
    //短信次数
    private Integer ljDxtzNum;
    //来院记录次数
    private Integer ljLylqNum;
    //直接送达次数
    private Integer ljZjsdNum;
    //邮寄次数
    private Integer ljEmsNum;

    //外呼成功次数
    private Integer ljWebCallSucNum;
    //短信成功次数
    private Integer ljDxtzSucNum;
    //来院记录成功次数
    private Integer ljLylqSucNum;
    //直接送达成功次数
    private Integer ljZjsdSucNum;
    //邮寄成功次数
    private Integer ljEmsSucNum;

    //外呼失败次数
    private Integer ljWebCallFailNum;
    //短信失败次数
    private Integer ljDxtzFailNum;
    //来院记录失败次数
    private Integer ljLylqFailNum;
    //直接送达失败次数
    private Integer ljZjsdFailNum;
    //邮寄失败次数
    private Integer ljEmsFailNum;

    public void loadSucRate(Boolean bfh) {
        if(NumberUtil.isIntNotNullAndGtZero(ljEndCase) && NumberUtil.isIntNotNullAndGtZero(ljSucCase)){
            caseSucRate = String.format("%.2f", ((double)ljSucCase / ljEndCase) * 100);
            if(new BigDecimal(caseSucRate).compareTo(new BigDecimal(100)) > 0){
                caseSucRate = "100.00";
            }
        }else {
            caseSucRate = "0.00";
        }
        if(NumberUtil.isIntNotNullAndGtZero(ljEndSddr) && NumberUtil.isIntNotNullAndGtZero(ljSucSddr)){
            ssdrSucRate = String.format("%.2f", ((double)ljSucSddr / ljEndSddr) * 100);
            if(new BigDecimal(ssdrSucRate).compareTo(new BigDecimal(100)) > 0){
                ssdrSucRate = "100.00";
            }
        }else {
            ssdrSucRate = "0.00";
        }

        if(bfh){
            ssdrSucRate+="%";
            caseSucRate+="%";
        }
    }
    public void loadSucRate() {
        loadSucRate(true);
    }

}
