package com.nju.sdpt.service;

import com.nju.sdpt.model.RepairInfoModel;
import com.nju.sdpt.model.report.*;
import com.nju.sdpt.viewobject.RepairQueryVo;
import com.nju.sdpt.viewobject.ReportQueryVo;

import java.util.List;
import java.util.Map;

/**
 * 修复记录业务
 */
public interface ReportService {

    List<ReportLjModel> report_lj(ReportQueryVo queryVo);

    List<ReportRepairModel> report_repair(ReportQueryVo queryVo);

    ChartCaseModel chart_case(ReportQueryVo queryVo);

    ChartMonthCaseModel chart_month_case(ReportQueryVo queryVo);

    ChartslcdTableModel chart_slcd_table(ReportQueryVo queryVo);

    ChartCassLaayModel chart_case_laay(ReportQueryVo queryVo);

    Map<String, Integer> chart_yxbr(ReportQueryVo queryVo);

    Map<String, Integer> chart_sjfb(ReportQueryVo queryVo);

    Map<String, Double> chart_pjsdzq(ReportQueryVo queryVo);

    Map<String, Double> getSdwsfb(ReportQueryVo queryVo);

    List<String> getBmmcListByfybhList(List<String> fybhList);

    List<String> getSsdrlxListByfybhList(List<String> fybhList);
}
