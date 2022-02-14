package com.nju.sdpt.service.Impl;

import com.nju.sdpt.constant.SdptConstants;
import com.nju.sdpt.mapper.*;
import com.nju.sdpt.model.FYEnum;
import com.nju.sdpt.model.SdzqModel;
import com.nju.sdpt.model.TjxxModel;
import com.nju.sdpt.model.report.*;
import com.nju.sdpt.service.ReportService;
import com.nju.sdpt.util.DateUtil;
import com.nju.sdpt.util.StringUtil;
import com.nju.sdpt.viewobject.ReportQueryVo;
import io.swagger.models.auth.In;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

@Service
public class ReportServiceImpl implements ReportService {

    private final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);
    @Autowired
    PubYysdJbEntityMapper pubYysdJbEntityMapper;
    @Autowired
    PubWebCallInfoEntityMapper pubWebCallInfoEntityMapper;
    @Autowired
    PubDxtzInfoEntityMapper pubDxtzInfoEntityMapper;
    @Autowired
    PubLylqInfoEntityMapper pubLylqInfoEntityMapper;
    @Autowired
    PubZjsdInfoEntityMapper pubZjsdInfoEntityMapper;
    @Autowired
    StatisticsMapper statisticsMapper;
    @Autowired
    ReportMapper reportMapper;
    @Override
    public List<ReportLjModel> report_lj(ReportQueryVo queryVo) {


        //查询
        //     * 累计推送案件+累计撤回+当日推送
        //     * 累计推送涉及人数+累计撤回涉及人数+当日推送涉及人数
        //     * 累计反馈送达成功的工单量+累计反馈送达失败的工单量
        List<ReportLjModel> ljfkList = pubYysdJbEntityMapper.report_lj(queryVo);

        //封装法院名称
        if(CollectionUtils.isNotEmpty(ljfkList)){
            for (ReportLjModel reportLjModel : ljfkList) {
                String fybh = reportLjModel.getFybh();
                if(StringUtil.isNotBlank(fybh)){
                    FYEnum fyEnum = FYEnum.getFyByFybh(fybh);
                    if(null != fyEnum){
                        reportLjModel.setFymc(fyEnum.getName());
                    }
                }
            }
        }


        return ljfkList;
    }

    @Override
    public List<ReportRepairModel> report_repair(ReportQueryVo queryVo) {
        List<ReportRepairModel> ljfkList = pubYysdJbEntityMapper.report_repair(queryVo);
        //封装法院名称
        if(CollectionUtils.isNotEmpty(ljfkList)){
            for (ReportRepairModel reportLjModel : ljfkList) {
                String fybh = reportLjModel.getFybh();
                if(StringUtil.isNotBlank(fybh)){
                    FYEnum fyEnum = FYEnum.getFyByFybh(fybh);
                    if(null != fyEnum){
                        reportLjModel.setFymc(fyEnum.getName());
                    }
                }
            }
        }
        return ljfkList;
    }

    @Override
    public ChartCaseModel chart_case(ReportQueryVo queryVo) {

        String endTime = queryVo.getEndTime();
        if(StringUtil.isNotBlank(queryVo.getStartTime()) && StringUtil.isNotBlank(queryVo.getEndTime())){
            //处理结束时间 加1天
            endTime = DateUtil.format(DateUtil.addDays(DateUtil.parse(endTime,DateUtil.webFormat),1),DateUtil.webFormat);
            queryVo.setEndTime(endTime);
        }

        //累计收案
        Integer ljCase = pubYysdJbEntityMapper.lj_case(queryVo);
        Integer ljSucCase = pubYysdJbEntityMapper.lj_suc_case(queryVo);
        Integer ljEndCase = pubYysdJbEntityMapper.lj_end_case(queryVo);
        Integer ljDsdCase =pubYysdJbEntityMapper.lj_dsd_case(queryVo);
        List<Map<String, Integer>> mapList = pubYysdJbEntityMapper.lj_ssdr(queryVo);
        Integer ljSddr = 0,ljSucSddr = 0,ljEndSddr = 0,dxtzSucNum = 0;
        if(CollectionUtils.isNotEmpty(mapList)){
            Map<String, Integer> integerMap = mapList.get(0);
            ljSddr = Integer.valueOf(String.valueOf(integerMap.get("ljSddr")));
            ljSucSddr = Integer.valueOf(String.valueOf(integerMap.get("ljSucSddr")));
            ljEndSddr = Integer.valueOf(String.valueOf(integerMap.get("ljEndSddr")));
        }
        dxtzSucNum =  pubDxtzInfoEntityMapper.ljDxtzSucPeoNum(queryVo);


        queryVo.setReportCusType(SdptConstants.REPORT_CUS_TYPE.PEO);

        List<Map<String,Integer>> webCallMap = reportMapper.loadWebCallData(queryVo,"ENTRY");
        WebCallChart entryWebCallChart = new WebCallChart();
        if(CollectionUtils.isNotEmpty(webCallMap)){
            entryWebCallChart.loadNum(webCallMap);
            entryWebCallChart.loadSucRate();
        }
        List<Map<String,Integer>> dxtzMap = reportMapper.loadDxtzData(queryVo,"ENTRY");
        DxtzChart dxtzChart = new DxtzChart();
        if(CollectionUtils.isNotEmpty(dxtzMap)){
            dxtzChart.loadNum(dxtzMap);
            dxtzChart.loadSucRate(true);
        }

        List<Map<String,Integer>> lylqMap = reportMapper.loadLylqData(queryVo);
        LylqChart lylqChart = new LylqChart();
        if(CollectionUtils.isNotEmpty(lylqMap)){
            lylqChart.loadNum(lylqMap);
            lylqChart.loadSucRate();
        }

        List<Map<String,Integer>> zjsdMap = reportMapper.loadZjsdData(queryVo);
        ZjsdChart zjsdChart = new ZjsdChart();
        if(CollectionUtils.isNotEmpty(zjsdMap)){
            zjsdChart.loadNum(zjsdMap);
            zjsdChart.loadSucRate();
        }


        Integer ljWebCallNum = pubWebCallInfoEntityMapper.ljWebcallNum(queryVo);
        Integer ljDxtzNum = pubDxtzInfoEntityMapper.ljDxtzNum(queryVo);
        Integer ljLylqNum = pubLylqInfoEntityMapper.ljNum(queryVo);
        Integer ljZjsdNum = pubZjsdInfoEntityMapper.ljNum(queryVo);
        List<Map<String,Integer>> emsMap = reportMapper.loadEmsData(queryVo);
        EmsChart emsChart = new EmsChart();
        if(CollectionUtils.isNotEmpty(emsMap)){
            emsChart.loadNum(emsMap);
            emsChart.loadSucRate();
        }
        Integer emsNum = emsChart.getEmsNum();
        Integer finalLjSddr = ljSddr;
        Integer finalLjSucSddr = ljSucSddr;
        Integer finalDxtzSucNum = dxtzSucNum;
        Integer finalLjEndSddr = ljEndSddr;
        return new ChartCaseModel(){{
            setLjCase(ljCase);
            setLjSucCase(ljSucCase);
            setLjEndCase(ljEndCase);
            setLjSddr(finalLjSddr);
            setLjSucSddr(finalLjSucSddr);
            setLjEndSddr(finalLjEndSddr);
            setLjWebCallNum(ljWebCallNum);
            setLjDxtzNum(ljDxtzNum);
            setLjLylqNum(ljLylqNum);
            setLjZjsdNum(ljZjsdNum);
            setLjEmsNum(emsNum);
            setSdzCase(ljCase-ljEndCase - ljDsdCase);
            setSdzSddr(finalLjSddr-finalLjEndSddr);
            setLjWebCallSucNum(entryWebCallChart.webCallSucNum);
            setLjDxtzSucNum(dxtzChart.dljDxtzFwNum);
            setLjLylqSucNum(lylqChart.lylqSucNum);
            setLjZjsdSucNum(zjsdChart.zjsdSucNum);
            setLjEmsSucNum(emsChart.emsSucNum);
            setDxtzSucRate(dxtzChart.dxtzSucRate);
            setLjWebCallFailNum(entryWebCallChart.webCallFailNum);
            setLjDxtzFailNum(dxtzChart.dljDxtzNum - dxtzChart.dljDxtzFwNum);
            setLjLylqFailNum(lylqChart.lylqFailNum);
            setLjZjsdFailNum(zjsdChart.zjsdFailNum);
            setLjEmsFailNum(emsChart.emsFailNum);
            loadSucRate();
        }};
    }

    @Override
    public ChartMonthCaseModel chart_month_case(ReportQueryVo queryVo) {
        if(null == queryVo){
            queryVo = new ReportQueryVo();
        }
        Calendar nowYear = Calendar.getInstance();
        int nowMon = nowYear.get(Calendar.MONTH);
        nowYear.set(Calendar.MONTH,0);
        nowYear.set(Calendar.DATE,1);

        List<Integer>  ljCaseList = new ArrayList<>();
        List<String>  ljSucCaseRateList = new ArrayList<>();
        for (int i = 0; i <= nowMon; i++) {
            //计算开始日期于结束日期
            String startTime = DateUtil.format(nowYear.getTime(),"yyyy-MM-dd");
            nowYear.add(Calendar.MONTH,1);
            String endTime = DateUtil.format(nowYear.getTime(),"yyyy-MM-dd");
            queryVo.setStartTime(startTime);
            queryVo.setEndTime(endTime);
            //累计收案
            Integer ljCase = pubYysdJbEntityMapper.lj_case(queryVo);
            Integer ljSucCase = pubYysdJbEntityMapper.lj_suc_case(queryVo);
            Integer ljEndCase = pubYysdJbEntityMapper.lj_end_case(queryVo);
            ChartCaseModel chartCaseModel = new ChartCaseModel() {{
                setLjCase(ljCase);
                setLjSucCase(ljSucCase);
                setLjEndCase(ljEndCase);
                loadSucRate(false);
            }};
            ljCaseList.add(ljCase);
            ljSucCaseRateList.add(chartCaseModel.getCaseSucRate());

        }
        return new ChartMonthCaseModel(){{
            setLjCaseList(ljCaseList);
            setLjSucCaseRateList(ljSucCaseRateList);
        }};
    }

    @Override
    public ChartslcdTableModel chart_slcd_table(ReportQueryVo queryVo) {
        if(null == queryVo){
            queryVo = new ReportQueryVo();
        }
        String reportCusType = queryVo.getReportCusType();
        String[] ruleArr = {SdptConstants.REPORT_CUS_TYPE.COUNT, SdptConstants.REPORT_CUS_TYPE.PEO};
        if(!Arrays.asList(ruleArr).contains(reportCusType)){
            //不符合规则 默认
            reportCusType = SdptConstants.REPORT_CUS_TYPE.COUNT;
        }
        String endTime = queryVo.getEndTime();
        if(StringUtil.isNotBlank(queryVo.getStartTime()) && StringUtil.isNotBlank(queryVo.getEndTime())){
            //处理结束时间 加1天
            endTime = DateUtil.format(DateUtil.addDays(DateUtil.parse(endTime,DateUtil.webFormat),1),DateUtil.webFormat);
            queryVo.setEndTime(endTime);
        }
        queryVo.setReportCusType(reportCusType);

        //号码修复相关
        List<Map<String,Integer>> slcdMap = reportMapper.loadSlcdData(queryVo);
        Integer repairNum = 0,repairSucNum = 0;
        if(CollectionUtils.isNotEmpty(slcdMap)){
            Map<String, Integer> integerMap = slcdMap.get(0);
            repairNum = Integer.valueOf(String.valueOf(integerMap.get("repairNum")));
            repairSucNum = Integer.valueOf(String.valueOf(integerMap.get("repairSucNum")));
        }

        List<Map<String,Integer>> webCallMap = reportMapper.loadWebCallData(queryVo,"ENTRY");
        WebCallChart entryWebCallChat = new WebCallChart();
        if(CollectionUtils.isNotEmpty(webCallMap)){
            entryWebCallChat.loadNum(webCallMap);
            entryWebCallChat.loadSucRate();
        }
        List<Map<String,Integer>> repairWebCallMap = reportMapper.loadWebCallData(queryVo,"REPAIR");
        WebCallChart repairWebCallChat = new WebCallChart();
        if(CollectionUtils.isNotEmpty(repairWebCallMap)){
            repairWebCallChat.loadNum(repairWebCallMap);
            repairWebCallChat.loadSucRate();
        }

        List<Map<String,Integer>> dxtzMap = reportMapper.loadDxtzData(queryVo,"ENTRY");
        DxtzChart dxtzChart = new DxtzChart();
        if(CollectionUtils.isNotEmpty(dxtzMap)){
            dxtzChart.loadNum(dxtzMap);
            dxtzChart.loadSucRate(true);
        }

        List<Map<String,Integer>> repairDxtzMap = reportMapper.loadDxtzData(queryVo,"REPAIR");
        DxtzChart reapirDxtzChart = new DxtzChart();
        if(CollectionUtils.isNotEmpty(repairDxtzMap)){
            reapirDxtzChart.loadNum(repairDxtzMap);
            reapirDxtzChart.loadSucRate(true);
        }
        Integer lylqNum = 0;
        List<Map<String,Integer>> lylqMap = reportMapper.loadLylqData(queryVo);
        LylqChart lylqChart = new LylqChart();
        if(CollectionUtils.isNotEmpty(lylqMap)){
            lylqChart.loadNum(lylqMap);
            lylqChart.loadSucRate();
        }
        if(Objects.equals(reportCusType, SdptConstants.REPORT_CUS_TYPE.COUNT)){
            lylqNum = lylqChart.getLylqNum();
        }else {
            lylqNum = lylqChart.getLylqPeoNum();
        }

        Integer zjsdNum = 0;
        List<Map<String,Integer>> zjsdMap = reportMapper.loadZjsdData(queryVo);
        ZjsdChart zjsdChart = new ZjsdChart();
        if(CollectionUtils.isNotEmpty(zjsdMap)){
            zjsdChart.loadNum(zjsdMap);
            zjsdChart.loadSucRate();
        }
        if(Objects.equals(reportCusType, SdptConstants.REPORT_CUS_TYPE.COUNT)){
            zjsdNum = zjsdChart.getZjsdNum();
        }else {
            zjsdNum = zjsdChart.getZjsdPeoNum();
        }

        //查询EMS相关
        List<Map<String,Integer>> emsMap = reportMapper.loadEmsData(queryVo);
        EmsChart emsChart = new EmsChart();
        if(CollectionUtils.isNotEmpty(emsMap)){
            emsChart.loadNum(emsMap);
            emsChart.loadSucRate();
            emsChart.setEmsSwNum(emsChart.getEmsNum() - emsChart.getEmsSnNum());
        }
        Integer emsNum= emsChart.getEmsPeoNum();

        Integer finalRepairSucNum = repairSucNum;
        Integer finalRepairNum = repairNum;
        Integer sshdtzzl =dxtzChart.getDljDxtzNum()+reapirDxtzChart.getDljDxtzNum()+entryWebCallChat.getWebCallNum()+repairWebCallChat.getWebCallNum()+lylqNum+zjsdNum+emsNum;
        return new ChartslcdTableModel(){{
            setRepairNum(finalRepairNum);
            setRepairSucNum(finalRepairSucNum);
            setEntryWebCallChart(entryWebCallChat);
            setRepairWebCallChart(repairWebCallChat);
            setEntryDxtzChart(dxtzChart);
            setRepairDxtzChart(reapirDxtzChart);
            setLylqChart(lylqChart);
            setZjsdChart(zjsdChart);
            setEmsChart(emsChart);
            setSshdtzzl(sshdtzzl);
            loadSucRate();
        }};
    }

    @Override
    public ChartCassLaayModel chart_case_laay(ReportQueryVo queryVo) {
        if(null == queryVo){
            queryVo = new ReportQueryVo();
        }
        String endTime = queryVo.getEndTime();
        if(StringUtil.isNotBlank(queryVo.getStartTime()) && StringUtil.isNotBlank(queryVo.getEndTime())){
            //处理结束时间 加1天
            endTime = DateUtil.format(DateUtil.addDays(DateUtil.parse(endTime,DateUtil.webFormat),1),DateUtil.webFormat);
            queryVo.setEndTime(endTime);
        }

        int total = reportMapper.loadLaayTotal(queryVo);
        int totalSuc = reportMapper.loadLaaySuc(queryVo);
        List<Map<String,Object>> laayCaseData = reportMapper.loadLaayCaseData(queryVo,"TOTAL");
        List<Map<String,Object>> laayCaseSucData = reportMapper.loadLaayCaseData(queryVo,"SUC");
        int count =0;
        for(Map<String, Object> laay : laayCaseData) {
            String name = (String) laay.get("name");
            Integer value = Integer.valueOf(laay.get("value").toString());
            count += value;
                for(Map<String, Object> laaySuc : laayCaseSucData) {
                    if(laaySuc.get("name").equals(name)) {
                        if(laay.get("value").equals(null) || (Long) laay.get("value") == 0) {
                            continue;
                        }
                        Object o = laaySuc.get("value");
                        Integer success = Integer.valueOf(o.toString());
                        StringBuilder sb = new StringBuilder();
                        sb.append("案件量：");
                        sb.append(value);
                        sb.append("(成功率：");
                        sb.append(success * 100 / value);
                        sb.append("%");
                        sb.append(")");
                        sb.append("(案由占比：");
                        sb.append(value * 100 / total);
                        sb.append("%");
                        sb.append(")");
                        laay.put("suc", success * 100 / value);
                        laay.put("zb", value * 100 / total);
                    }
                }
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "其他");
        map.put("value", total - count);
        map.put("suc",totalSuc * 100 / (total - count));
        map.put("zb", (total - count) * 100 / total);
        laayCaseData.add(map);
        return new ChartCassLaayModel(){{
            setLaayCaseData(laayCaseData);
            setLaayCaseSucData(laayCaseSucData);
        }};
    }

    @Override
    public Map<String, Integer> chart_yxbr(ReportQueryVo queryVo) {
        if(null == queryVo){
            queryVo = new ReportQueryVo();
        }
        String endTime = queryVo.getEndTime();
        if(StringUtil.isNotBlank(queryVo.getStartTime()) && StringUtil.isNotBlank(queryVo.getEndTime())){
            //处理结束时间 加1天
            endTime = DateUtil.format(DateUtil.addDays(DateUtil.parse(endTime,DateUtil.webFormat),1),DateUtil.webFormat);
            queryVo.setEndTime(endTime);
        }

        List<Map<String, Integer>> dataList = reportMapper.loadYxbrData(queryVo);

        return dataList.get(0);
    }

    @Override
    public Map<String, Integer> chart_sjfb(ReportQueryVo queryVo) {
        if(null == queryVo){
            queryVo = new ReportQueryVo();
        }
        String endTime = queryVo.getEndTime();
        if(StringUtil.isNotBlank(queryVo.getStartTime()) && StringUtil.isNotBlank(queryVo.getEndTime())){
            //处理结束时间 加1天
            endTime = DateUtil.format(DateUtil.addDays(DateUtil.parse(endTime,DateUtil.webFormat),1),DateUtil.webFormat);
            queryVo.setEndTime(endTime);
        }

        List<Map<String, Integer>> dataList = reportMapper.loadSjfbData(queryVo);
        Map<String, Integer> datamap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> data:dataList.get(0).entrySet() ){
            if(StringUtil.equals("p1",data.getKey())){
                datamap.put("9:00-11:00",data.getValue());
            }
            if(StringUtil.equals("p2",data.getKey())){
                datamap.put("11:00-13:00",data.getValue());
            }
            if(StringUtil.equals("p3",data.getKey())){
                datamap.put("13:00-15:00",data.getValue());
            }
            if(StringUtil.equals("p4",data.getKey())){
                datamap.put("15:00-18:00",data.getValue());
            }
        }
        return datamap;
    }

    @Override
    public Map<String, Double> chart_pjsdzq(ReportQueryVo queryVo) {
        Map<String,Double> pjsdzqMap = new HashMap<>();
        pjsdzqMap.put("案件平均送达周期",reportMapper.loadGdzq(queryVo));
        Double dh = reportMapper.loadDhsdzq(queryVo);
        dh = dh==null?0:dh;
        pjsdzqMap.put("电话平均送达周期",(double)Math.round(dh*100000/(24*3600))/100000);
        pjsdzqMap.put("短信平均送达周期",dealSdzqList(reportMapper.loadDxsdzq(queryVo)));
        pjsdzqMap.put("来院平均送达周期", dealSdzqList(reportMapper.loadLysdzq(queryVo)));
        pjsdzqMap.put("邮寄平均送达周期",dealSdzqList(reportMapper.loadEmssdzq(queryVo)));
        pjsdzqMap.put("直接平均送达周期", dealSdzqList(reportMapper.loadZjsdzq(queryVo)));
        return pjsdzqMap;
    }

    @Override
    @Cacheable(value = "getSdwsfb",key="#queryVo.fybhSet+'_'+#queryVo.startTime+'_'+#queryVo.endTime")
    public Map<String, Double> getSdwsfb(ReportQueryVo queryVo) {
        Map<String,Double> sdwsfbMap = new HashMap<>();
        Double sdwsAll = reportMapper.getSdwsfbAll(queryVo);
        List<TjxxModel> sdwsList = reportMapper.getSdwsfb(queryVo);
        for (TjxxModel tjxxModel:sdwsList){
            sdwsfbMap.put(tjxxModel.getName(),tjxxModel.getValue());
            sdwsAll -= tjxxModel.getValue();
        }
        if(sdwsAll>0){
           sdwsfbMap.put("其他",sdwsAll);
        }
        return sdwsfbMap;
    }

    @Override
    public List<String> getBmmcListByfybhList(List<String> fybhList) {
        return reportMapper.getBmmcListByfybhList(fybhList);
    }

    @Override
    public List<String> getSsdrlxListByfybhList(List<String> fybhList) {
        return reportMapper.getSsdrlxListByfybhList(fybhList);
    }


    //处理送达周期
    private Double dealSdzqList(List<SdzqModel> sdzqList) {
        long sdCreSum = 0;
        long sdSubSum = 0;
        for (SdzqModel sdzqModel : sdzqList){
            sdCreSum += sdzqModel.getCreatetime().getTime();
            sdSubSum += sdzqModel.getSubmittime().getTime();
        }
        if(sdzqList.size()==0){
            return 0.0;
        }
        return  (double)Math.round((sdSubSum-sdCreSum)/sdzqList.size()/(24*3600))/1000;
    }

}
