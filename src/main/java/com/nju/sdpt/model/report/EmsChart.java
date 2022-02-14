package com.nju.sdpt.model.report;

import lombok.Data;



@Data
public class EmsChart extends BaseChart{
    /**
     * EMS数量(次)
     */
    public Integer emsNum;
    /**
     * EMS数量(人)
     */
    public Integer emsPeoNum;
    /**
     * EMS在途数量
     */
    public Integer emsZtNum;

    /**
     * EMS成功数量
     */
    public Integer emsSucNum;
    /**
     * EMS失败数量
     */
    public Integer emsFailNum;

    public Integer emsSnNum;
    public Integer emsSwNum;
    /**
     * EMS成功率
     */
    public String emsSucRate;

    public Integer emsWsNum;


    public void loadSucRate() {
        emsSucRate = loadSucRateBase(true,emsSucNum,emsFailNum);
        emsZtNum = emsNum - (emsSucNum+emsFailNum);
    }
}
