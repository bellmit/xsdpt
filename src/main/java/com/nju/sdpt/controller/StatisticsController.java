package com.nju.sdpt.controller;

import com.alibaba.fastjson.JSONObject;
import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.entity.AdcodeEnum;
import com.nju.sdpt.entity.StatisticsFgModel1;
import com.nju.sdpt.entity.dataStatistics;
import com.nju.sdpt.entity.dataStatisticsByAdcode;
import com.nju.sdpt.mapper.StatisticsMapper;
import com.nju.sdpt.model.*;
import com.nju.sdpt.model.report.ChartCaseModel;
import com.nju.sdpt.service.GdRyxxService;
import com.nju.sdpt.service.ReportService;
import com.nju.sdpt.service.StatisticsService;
import com.nju.sdpt.util.DateUtil;
import com.nju.sdpt.util.StringUtil;
import com.nju.sdpt.viewobject.ReportQueryVo;
import exception.Result;
import exception.ResultUtils;
import org.apache.commons.net.ntp.TimeStamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.*;

@Controller
public class StatisticsController {
    @Autowired
    StatisticsService statisticsService;
    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;
    @Resource
    GdRyxxService ryxxService;
    @Resource
    ReportService reportService;
    @Autowired
    StatisticsMapper statisticsMapper;

    /**
     * 获取实时送达数据
     *
     * @return
     */
    @RequestMapping(value = "/getTitle.aj", method = RequestMethod.POST)
    @ResponseBody
    @Cacheable(value = "getTitle")
    public JSONObject getTitle() {
        JSONObject jsonObject = new JSONObject();
        DynamicDataSource.router(SDPT_FYDM);
        int ljsdajs = statisticsService.findLjsdajs();//累计送达案件
        int ljwtrc = statisticsService.findLjwtrc();//累计委托人次
        int ljsdrc = statisticsService.findLjsdrc();//累计送达人数
        int ljsdcs = statisticsService.findLjsdAjcgs();//累计送达案件成功数
        double sdcgl = statisticsService.findSdcgl();//送达成功率
        jsonObject.put("ljsdajs", ljsdajs);
        jsonObject.put("ljwtrc", ljwtrc);
        jsonObject.put("ljsdrc", ljsdrc);
        jsonObject.put("ljsdcs", ljsdcs);
        DecimalFormat df = new DecimalFormat("0.00%");
        jsonObject.put("sdcgl", df.format(sdcgl));
        return jsonObject;
    }

    /**
     * 获取实时送达数据
     * lx:时间类型(日/周/月/年)
     *
     * @return
     */
    @RequestMapping(value = "/getSssdsj.aj", method = RequestMethod.POST)
    @ResponseBody
//    @Cacheable(value = "getSssdsj",key = "#lx")
    public JSONObject getSssdsj(String lx) {
        Date date = dealDateLx(lx);
        JSONObject jsonObject = new JSONObject();
        DynamicDataSource.router(SDPT_FYDM);
        List<String> fymcList = new LinkedList<>();
        for (FYEnum fyEnum : FYEnum.values()) {
            fymcList.add(fyEnum.getJjc());
        }
        LinkedList<Integer> yysdrws = statisticsService.findYysdrws(date);//预约送达任务数
        LinkedList<Integer> sdzrws = statisticsService.findSdzrws(date);//送达中任务数
        LinkedList<Integer> wcsdrws = statisticsService.findWcsdrws(date);//完成任务数
//        List<String> sdcgl = statisticsService.findSdcglList(); //各法院送达成功率
        jsonObject.put("fyjc", fymcList);
        jsonObject.put("yysdrws", yysdrws);
        jsonObject.put("sdzrws", sdzrws);
        jsonObject.put("wcsdrws", wcsdrws);
//        jsonObject.put("sdcgl",sdcgl);
        return jsonObject;
    }

    /**
     * 获取失联人触达统计
     *
     * @return
     */
    @RequestMapping(value = "/getSlrcd.aj", method = RequestMethod.POST)
    @ResponseBody
    @Cacheable(value = "getSlrcd")
    public JSONObject getSlrcd() {
        JSONObject jsonObject = new JSONObject();
        DynamicDataSource.router(SDPT_FYDM);
        List<Integer> slrs = statisticsService.findSlrs();//失联人数
        List<Integer> cdrs = statisticsService.findCdrs();//触达人数
        List<String> cdl = new ArrayList<>();
        for (int i = 0; i < slrs.size(); i++) {
            if (slrs.get(i) == 0) {
                cdl.add("0");
                continue;
            }
            cdl.add(String.format("%.2f", ((double) (cdrs.get(i)) / (double) (slrs.get(i)) * 100)));
        }
        int slrsSum = 0;
        for (Integer sl : slrs) {
            slrsSum += sl;
        }
        int cdrsSum = 0;
        for (Integer cd : cdrs) {
            cdrsSum += cd;
        }
        double cdlSum = (double) cdrsSum / (double) slrsSum;
        jsonObject.put("slrs", slrs);
        jsonObject.put("cdrs", cdrs);

        jsonObject.put("slrsSum", slrsSum);
        jsonObject.put("cdrsSum", cdrsSum);
        jsonObject.put("cdl", cdl);
        DecimalFormat df = new DecimalFormat("0.00%");
        jsonObject.put("cdlSum", df.format(cdlSum));
        return jsonObject;
    }

    /**
     * 获取送达方式分布
     *
     * @return
     */
    @RequestMapping(value = "/getSdfsfb.aj", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result<Object>> getSdfsfb(@RequestBody ReportQueryVo queryVo) {
        //加载数据环境
        queryVo.setLoginYhm("hzzf_lhm");
        queryVo.setYhbh(ryxxService.findByYhdm(queryVo.getLoginYhm()).getYhbh());
        List<String> fybhList = new ArrayList<>();
        for (FYEnum fyEnum : FYEnum.values()) {
            fybhList.add(fyEnum.getFybh());
        }
        queryVo.setFybhSet(fybhList);
        //查询具体数据

        DynamicDataSource.router(SDPT_FYDM);
        queryVo.setGdOrAhType("AJ");
        ChartCaseModel result = reportService.chart_case(queryVo);
        return ResultUtils.wrapSuccess(result);
    }

    /**
     * 获取三级法院排名
     *
     * @return
     */
    @RequestMapping(value = "/getSjfypm.aj", method = RequestMethod.POST)
    @ResponseBody
    @Cacheable(value = "getSjfypm", key = "#time+'_'+#type")
    public JSONObject getSjfypm(String time, String type) {
        Date date = dealDateLx(time);
        JSONObject jsonObject = new JSONObject();
        DynamicDataSource.router(SDPT_FYDM);
        List<TjxxModel> tjxxModels = new ArrayList<>();
        if (StringUtil.equals(type, "委托数")) {
            tjxxModels = statisticsService.getSjfypm(date);//委托数
        } else if (StringUtil.equals(type, "送达数")) {
            tjxxModels = statisticsService.getSjfypmSd(date);//送达数
        } else {
            tjxxModels = statisticsService.getSjfypmCgl(date);//成功率
        }
        jsonObject.put("sjfypm", tjxxModels);
        return jsonObject;
    }


    private static Date dealDateLx(String lx) {
        Date date = new Date();
        if (StringUtil.equals(lx, "日")) {
            date = DateUtil.today();
        }
        if (StringUtil.equals(lx, "周")) {
            date = DateUtil.getThisWeekMonday();
        }
        if (StringUtil.equals(lx, "月")) {
            date = DateUtil.getDefaultMonthBeginTime();
        }
        if (StringUtil.equals(lx, "年")) {
            date = DateUtil.getDefaultYearBeginTime();
        }
        return date;
    }

    /**
     * 获取实时送达统计数据
     *
     * @return
     */
    @RequestMapping(value = "/getstatistics.aj", method = RequestMethod.POST)
    @ResponseBody
//    @Cacheable(value = "getstatistics")
    public dataStatistics getstatistics(@RequestBody Map data) {
        String mode = data.get("mode").toString();
        String lx = data.get("lx").toString();
        String fybh = data.get("fybh").toString();

        String startTime = "";
        String endTime = "";
        if ("1".equals(lx)) {
            startTime = DateUtil.getInitDateByDay();
            endTime = DateUtil.getEndDateByDay();
        }
        if ("2".equals(lx)) {
            startTime = DateUtil.get7DaysAgo();
            endTime = DateUtil.getNow();
        }
        if ("3".equals(lx)) {
            startTime = DateUtil.get30DaysAgo();
            endTime = DateUtil.getNow();
        }
        if ("4".equals(lx)) {
            startTime = "2021-11-01 00:00:00";
            endTime = DateUtil.getNow();
        }

        Timestamp start= Timestamp.valueOf(startTime);
        Timestamp end=Timestamp.valueOf(endTime);

        DecimalFormat df=new DecimalFormat("0.0000");
        DynamicDataSource.router(SDPT_FYDM);
        dataStatistics dataStatistics = new dataStatistics();

        int gdl=statisticsMapper.getGdl(start, end, fybh);
        int cggdl=statisticsMapper.getCgGdl(start, end, fybh);
        int sbgdl=statisticsMapper.getSbGdl(start, end, fybh);
        dataStatistics.setYygdsl(gdl);
        dataStatistics.setSdcgsl(cggdl);
        // 送达成功率 规整化
        double gsdcgl;
        try {
            gsdcgl =(double) cggdl / gdl;
            gsdcgl=Double.valueOf(df.format(gsdcgl*100));
        } catch (Exception e){
            gsdcgl=0;
        }
        dataStatistics.setGdsdcgl(gsdcgl);

        int sal=statisticsMapper.getLjSa(start, end, fybh);
        int cgsal=statisticsMapper.getCgAj(start, end, fybh);
        int sbsal=statisticsMapper.getSbAj(start, end, fybh);
        dataStatistics.setLjsasl(sal);
        dataStatistics.setAjsdsl(cgsal);
        double ajsdcgl;
        try {
            ajsdcgl =(double) cgsal / sal;
            ajsdcgl=Double.valueOf(df.format(ajsdcgl*100));
        } catch (Exception e){
            ajsdcgl=0;
        }
        dataStatistics.setAjsdcgl(ajsdcgl);

        int ssdrsl=statisticsMapper.getLjSsdr(start, end, fybh);
        int cgssdrsl=statisticsMapper.getCgSsdr(start, end, fybh);
        int sbssdrsl=statisticsMapper.getSbSsdr(start, end, fybh);
        dataStatistics.setSsdrsl(ssdrsl);
        dataStatistics.setWcsdrs(cgssdrsl);
        double sdrxcgl;
        try {
            sdrxcgl =(double) cgssdrsl / ssdrsl;
            sdrxcgl=Double.valueOf(df.format(sdrxcgl*100));
        } catch (Exception e){
            sdrxcgl=0;
        }
        dataStatistics.setSsdrcgl(sdrxcgl);

        dataStatistics.setSdzgdsl(gdl-cggdl-sbgdl);
        dataStatistics.setSdzajsl(sal-cgsal-sbsal);
        dataStatistics.setSdzrs(ssdrsl-cgssdrsl-sbssdrsl);

        dataStatistics.setAjpjsdzq(statisticsMapper.getAjpjSdzq(start, end, fybh));
        dataStatistics.setDhdzpjsdzq(statisticsMapper.getDzpjSdzq(start, end, fybh));
        Double Yjpjsdzq=statisticsMapper.getYjpjSdzq(start, end, fybh);
        dataStatistics.setYjpjsdzq(Yjpjsdzq==null?0.0:Yjpjsdzq);
        Double Lypjsdzq=statisticsMapper.getLypjSdzq(start, end, fybh);
        dataStatistics.setLypjsdzq(Lypjsdzq==null?0.0:Lypjsdzq);
        Double Zjpjsdzq=statisticsMapper.getZjpjSdzq(start, end, fybh);
        dataStatistics.setZjpjsdzq(Zjpjsdzq==null?0.0:Zjpjsdzq);

        dataStatistics.setDxxfzs(statisticsMapper.getDxxf(start, end, fybh));
        dataStatistics.setDxljfwl(statisticsMapper.getDxljfwl(start, end, fybh));
        dataStatistics.setDhwhbacs(statisticsMapper.getDhwhbdcs(start, end, fybh));
        dataStatistics.setDhwhjtcs(statisticsMapper.getJts(start, end, fybh));
        dataStatistics.setYjjs(statisticsMapper.getYjjs(start, end, fybh));
        dataStatistics.setWszl(statisticsMapper.getWssl(start, end, fybh));
        dataStatistics.setXfzs(statisticsMapper.getXfs(start, end, fybh));
        dataStatistics.setXfcgs(statisticsMapper.getXfcgs(start, end, fybh));
        return dataStatistics;
    }

    @RequestMapping(value = "/getstatisticsByAdcode.aj", method = RequestMethod.POST)
    @ResponseBody
    public List<dataStatisticsByAdcode> getstatisticsByAdcode(@RequestBody Map data) {
        String fybh = data.get("fybh").toString();
        DynamicDataSource.router(SDPT_FYDM);

        List<dataStatisticsByAdcode> dataStatisticsByAdcodes=new ArrayList<>();
        if("-1".equals(fybh)||"52".equals(fybh)||"62".equals(fybh)||"5015".equals(fybh)||"24".equals(fybh)||"5047".equals(fybh)){
            String startTime = "2021-11-01 00:00:00";
            String endTime = DateUtil.getNow();
            Timestamp start= Timestamp.valueOf(startTime);
            Timestamp end=Timestamp.valueOf(endTime);
            dataStatisticsByAdcode dataStatistic=new dataStatisticsByAdcode();
            dataStatistic.setAdcode("");
            dataStatistic.setFybh(fybh);
            dataStatistic.setLjgdsl(statisticsMapper.getGdl(start, end, fybh));
            dataStatistic.setLjgdwcsl(statisticsMapper.getCgGdl(start, end, fybh));
            dataStatistic.setLjsasl(statisticsMapper.getLjSa(start, end, fybh));

            startTime = DateUtil.getInitDateByDay();
            endTime = DateUtil.getEndDateByDay();
            start= Timestamp.valueOf(startTime);
            end=Timestamp.valueOf(endTime);
            dataStatistic.setJrgdsl(statisticsMapper.getGdl(start, end, fybh));
            dataStatistic.setJrgdwcsl(statisticsMapper.getCgGdl(start, end, fybh));
            dataStatistic.setJrsasl(statisticsMapper.getLjSa(start, end, fybh));

            dataStatisticsByAdcodes.add(dataStatistic);
        }

        for(AdcodeEnum adcodeEnum:AdcodeEnum.values()){
            String startTime = "2021-11-01 00:00:00";
            String endTime = DateUtil.getNow();
            Timestamp start= Timestamp.valueOf(startTime);
            Timestamp end=Timestamp.valueOf(endTime);
            dataStatisticsByAdcode dataStatistic=new dataStatisticsByAdcode();
            dataStatistic.setAdcode(adcodeEnum.getAdcode());
            dataStatistic.setFybh(adcodeEnum.getFybh());
            dataStatistic.setLjgdsl(statisticsMapper.getGdl(start, end, adcodeEnum.getFybh()));
            dataStatistic.setLjgdwcsl(statisticsMapper.getCgGdl(start, end, adcodeEnum.getFybh()));
            dataStatistic.setLjsasl(statisticsMapper.getLjSa(start, end, adcodeEnum.getFybh()));

            startTime = DateUtil.getInitDateByDay();
            endTime = DateUtil.getEndDateByDay();
            start= Timestamp.valueOf(startTime);
            end=Timestamp.valueOf(endTime);
            dataStatistic.setJrgdsl(statisticsMapper.getGdl(start, end, adcodeEnum.getFybh()));
            dataStatistic.setJrgdwcsl(statisticsMapper.getCgGdl(start, end, adcodeEnum.getFybh()));
            dataStatistic.setJrsasl(statisticsMapper.getLjSa(start, end, adcodeEnum.getFybh()));

            dataStatisticsByAdcodes.add(dataStatistic);
        }

        return dataStatisticsByAdcodes;
    }

    @RequestMapping(value = "/getStatisticsByFg.aj", method = RequestMethod.POST)
    @ResponseBody
    public ResultObj getStatisticsByFg(@RequestBody Map data){
        String lx = data.get("lx").toString();
        String fybh = data.get("fybh").toString();

        String startTime = "";
        String endTime = "";
        if ("1".equals(lx)) {
            startTime = DateUtil.getInitDateByDay();
            endTime = DateUtil.getEndDateByDay();
        }
        if ("2".equals(lx)) {
            startTime = DateUtil.get7DaysAgo();
            endTime = DateUtil.getNow();
        }
        if ("3".equals(lx)) {
            startTime = DateUtil.get30DaysAgo();
            endTime = DateUtil.getNow();
        }
        if ("4".equals(lx)) {
            startTime = "2021-11-01 00:00:00";
            endTime = DateUtil.getNow();
        }
        if("5".equals(lx)){
            startTime=data.get("start").toString()+" 00:00:00";
            endTime=data.get("end").toString()+" 23:59:59";
        }

        Timestamp start= Timestamp.valueOf(startTime);
        Timestamp end=Timestamp.valueOf(endTime);

        DecimalFormat df=new DecimalFormat("0.0000");
        DynamicDataSource.router(SDPT_FYDM);

        List<StatisticsFgModel> statisticsFgModels=statisticsMapper.getStatisticsFgModel(start,end,fybh);

        StatisticsFgModel1 total=new StatisticsFgModel1();
        for(StatisticsFgModel statisticsFgModel:statisticsFgModels){
            total.setSdcgsl(total.getSdcgsl()+statisticsFgModel.getSdcgsl());
            total.setWcsdsl(total.getWcsdsl()+statisticsFgModel.getWcsdsl());
            total.setWcsdrs(total.getWcsdrs()+statisticsFgModel.getWcsdrs());
            total.setSdcgrs(total.getSdcgrs()+statisticsFgModel.getSdcgrs());
            total.setFsnum(total.getFsnum()+statisticsFgModel.getFsnum());
            total.setCgnum(total.getCgnum()+statisticsFgModel.getCgnum());
            total.setTsgdds(statisticsMapper.getTsggsl(start,end,fybh));
            total.setWcgdds(statisticsMapper.getWcggsl(start,end,fybh));
            total.setZjsdcs(statisticsMapper.getFqzjsd(start,end,fybh));
            total.setWczjsdcs(statisticsMapper.getWczjsd(start,end,fybh));
            statisticsFgModel.setDzsdcgl(Double.valueOf(df.format(statisticsFgModel.getDzsdcgl()*100)));
        }
        total.setDzsdcgl(Double.valueOf(df.format(((double)total.getCgnum()/total.getFsnum())*100)));

        StatisticsFg statisticsFg=new StatisticsFg();
        statisticsFg.setTotal(total);
        statisticsFg.setDetails(statisticsFgModels);
        
        return new ResultObj(statisticsFg);
    }
}
