package com.nju.sdpt.model.report;

import com.nju.sdpt.util.NumberUtil;
import lombok.Data;

import java.util.List;

/**
 * 累计案件相关数据统计
 */
@Data
public class ChartMonthCaseModel {

    //月累计 案件量
    List<Integer> ljCaseList;

    //月累计 案件送达成功率
    List<String> ljSucCaseRateList;

}
