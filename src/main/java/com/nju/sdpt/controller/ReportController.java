package com.nju.sdpt.controller;

import com.alibaba.fastjson.JSONObject;
import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.model.TjxxModel;
import com.nju.sdpt.model.report.*;
import com.nju.sdpt.service.GdRyxxService;
import com.nju.sdpt.service.ReportService;
import com.nju.sdpt.viewobject.ReportQueryVo;
import exception.Result;
import exception.ResultUtils;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 修复记录相关
 */
@Controller
@RequestMapping("/report")
@Api(value = "统计相关")
public class ReportController extends BaseController {

    @Resource
    ReportService reportService;
    @Resource
    GdRyxxService ryxxService;

    /**
     * 累计数据统计 维度支持：  庭室 或 法官
     * @return
     */
    @RequestMapping(value = "/report_lj.zf",method = RequestMethod.POST)
    @ResponseBody
    public List<ReportLjModel> report_lj(@RequestBody ReportQueryVo queryVo){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        //查询具体数据
        return  reportService.report_lj(queryVo);
    }

    /**
     * 累计数据统计 修复 外呼
     * @return
     */
    @RequestMapping(value = "/report_repair.zf",method = RequestMethod.POST)
    @ResponseBody
    public List<ReportRepairModel> report_repair(@RequestBody ReportQueryVo queryVo){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        //查询具体数据
        return reportService.report_repair(queryVo);
    }

    /**
     * 累计数据统计 收案
     * @return
     */
    @RequestMapping(value = "/chart_case.zf",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result<Object>> chart_case(@RequestBody ReportQueryVo queryVo){
        //加载数据环境
        queryVo.setLoginYhm(getLoginYhm());
        queryVo.setYhbh(ryxxService.findByYhdm(queryVo.getLoginYhm()).getYhbh());
        queryVo.setFybhSet(getLoginYhmFybhSet());
        queryVo = loadReportQueryVoOffg(queryVo);
        //查询具体数据

        DynamicDataSource.router(SDPT_FYDM);
        ChartCaseModel result = reportService.chart_case(queryVo);
        return ResultUtils.wrapSuccess(result);
    }

    /**
     * 月累计数据统计 收案
     * @return
     */
    @RequestMapping(value = "/chart_month_case.zf",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result<Object>> chart_month_case(@RequestBody ReportQueryVo queryVo){
        //加载数据环境
        queryVo.setLoginYhm(getLoginYhm());
        queryVo.setYhbh(ryxxService.findByYhdm(queryVo.getLoginYhm()).getYhbh());
        queryVo.setFybhSet(getLoginYhmFybhSet());
        queryVo = loadReportQueryVoOffg(queryVo);
        DynamicDataSource.router(SDPT_FYDM);
        //查询具体数据
        ChartMonthCaseModel result = reportService.chart_month_case(queryVo);
        return ResultUtils.wrapSuccess(result);
    }

    /**
     * 失联触达相关数据
     * @return
     */
    @RequestMapping(value = "/chart_slcd_table.zf",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result<Object>> chart_slcd_table(@RequestBody ReportQueryVo queryVo){
        //加载数据环境
        queryVo.setLoginYhm(getLoginYhm());
        queryVo.setYhbh(ryxxService.findByYhdm(queryVo.getLoginYhm()).getYhbh());
        queryVo.setFybhSet(getLoginYhmFybhSet());
        queryVo = loadReportQueryVoOffg(queryVo);
        DynamicDataSource.router(SDPT_FYDM);
        //查询具体数据
        ChartslcdTableModel result = reportService.chart_slcd_table(queryVo);
        return ResultUtils.wrapSuccess(result);
    }

    /**
     * 案由 案件关系统计
     * @return
     */
    @RequestMapping(value = "/chart_case_laay.zf",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result<Object>> chart_case_laay(@RequestBody ReportQueryVo queryVo){
        //加载数据环境
        queryVo.setLoginYhm(getLoginYhm());
        if(ryxxService.findByYhdm(queryVo.getLoginYhm())==null){
            return ResultUtils.wrapFail("失败");
        }
        queryVo.setYhbh(ryxxService.findByYhdm(queryVo.getLoginYhm()).getYhbh());
        queryVo.setFybhSet(getLoginYhmFybhSet());
        queryVo = loadReportQueryVoOffg(queryVo);
        DynamicDataSource.router(SDPT_FYDM);
        //查询具体数据
        ChartCassLaayModel result = reportService.chart_case_laay(queryVo);
        return ResultUtils.wrapSuccess(result);
    }

    /**
     * 有效本人转化率
     * @return
     */
    @RequestMapping(value = "/chart_yxbr.zf",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result<Object>> chart_yxbr(@RequestBody ReportQueryVo queryVo){
        //加载数据环境
        queryVo.setLoginYhm(getLoginYhm());
        queryVo.setYhbh(ryxxService.findByYhdm(queryVo.getLoginYhm()).getYhbh());
        queryVo.setFybhSet(getLoginYhmFybhSet());
        queryVo = loadReportQueryVoOffg(queryVo);
        DynamicDataSource.router(SDPT_FYDM);
        //查询具体数据
        Map<String,Integer> result = reportService.chart_yxbr(queryVo);
        return ResultUtils.wrapSuccess(result);
    }
    /**
     * 时间分布表
     * @return
     */
    @RequestMapping(value = "/chart_sjfb.zf",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result<Object>> chart_sjfb(@RequestBody ReportQueryVo queryVo){
        //加载数据环境
        queryVo.setLoginYhm(getLoginYhm());
        queryVo.setYhbh(ryxxService.findByYhdm(queryVo.getLoginYhm()).getYhbh());
        queryVo.setFybhSet(getLoginYhmFybhSet());
        queryVo = loadReportQueryVoOffg(queryVo);
        DynamicDataSource.router(SDPT_FYDM);
        //查询具体数据
        Map<String,Integer> result = reportService.chart_sjfb(queryVo);
        return ResultUtils.wrapSuccess(result);
    }

    /**
     * 平均送达周期
     * @return
     */
    @RequestMapping(value = "/chart_pjsdzq.zf",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result<Object>> chart_pjsdzq(@RequestBody ReportQueryVo queryVo){
        //加载数据环境
        queryVo.setLoginYhm(getLoginYhm());
        queryVo.setYhbh(ryxxService.findByYhdm(queryVo.getLoginYhm()).getYhbh());
        queryVo.setFybhSet(getLoginYhmFybhSet());
        queryVo = loadReportQueryVoOffg(queryVo);
        DynamicDataSource.router(SDPT_FYDM);
        //查询具体数据
        Map<String,Double> result = reportService.chart_pjsdzq(queryVo);
        return ResultUtils.wrapSuccess(result);
    }

    /**
     * 获取送达文书分布
     * @return
     */
    @RequestMapping(value = "/getSdwsfb.aj", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result<Object>> getSdwsfb(@RequestBody ReportQueryVo queryVo){
        queryVo.setLoginYhm(getLoginYhm());
        queryVo.setYhbh(ryxxService.findByYhdm(queryVo.getLoginYhm()).getYhbh());
        queryVo.setFybhSet(getLoginYhmFybhSet());
        queryVo = loadReportQueryVoOffg(queryVo);
        queryVo.setStartTime(queryVo.getStartTime()==null?"2000-01-01":queryVo.getStartTime());
        queryVo.setEndTime(queryVo.getEndTime()==null?"3030-01-01":queryVo.getEndTime());
        DynamicDataSource.router(SDPT_FYDM);
        Map<String,Double> result = reportService.getSdwsfb(queryVo);
        return ResultUtils.wrapSuccess(result);
    }



    /**
     * 获取部门名称
     * @return
     */
    @RequestMapping(value = "/getBmmc.aj",method = RequestMethod.GET)
    @ResponseBody
    public List<String> getBmmc(){
       List<String> fybhList = getLoginYhmFybhSet();
        //查询具体数据
        List<String> bmmcList = reportService.getBmmcListByfybhList(fybhList);
        return bmmcList;
    }

    /**
     * 获取部门名称
     * @return
     */
    @RequestMapping(value = "/getSsdrlx.aj",method = RequestMethod.GET)
    @ResponseBody
    public List<String> getSsdrlx(){
        List<String> fybhList = getLoginYhmFybhSet();
        //查询具体数据
        List<String> ssdrlxList = reportService.getSsdrlxListByfybhList(fybhList);
        return ssdrlxList;
    }


}
