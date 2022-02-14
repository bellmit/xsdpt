package com.nju.sdpt.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.entity.PubSsdrHmEntity;
import com.nju.sdpt.entity.PubYysdJbEntity;
import com.nju.sdpt.entity.PubYysdRyxxEntity;
import com.nju.sdpt.entity.PubYysdWsEntity;
import com.nju.sdpt.mapper.PubLylqInfoEntityMapper;
import com.nju.sdpt.mapper.PubSsdrHmEntityMapper;
import com.nju.sdpt.mapper.PubYysdSsdrdzEntityMapper;
import com.nju.sdpt.model.*;
import com.nju.sdpt.service.*;
import com.nju.sdpt.util.StringUtil;
import com.nju.sdpt.viewobject.MwdxSendVo;
import com.nju.sdpt.viewobject.QuerySdpPhoneVo;
import com.nju.sdpt.viewobject.Result;
import com.nju.sdpt.viewobject.RwlzVO;
import exception.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 工单系统
 *
 * @author zzy
 */
@Controller
@Slf4j
@ApiIgnore
public class GdfpController {

    @Autowired
    YysdService yysdService;

    @Autowired
    GdRyxxService gdRyxxService;

    @Autowired
    LogService logService;

    @Resource
    private PubSsdrHmEntityMapper pubSsdrHmEntityMapper;

    @Autowired
    DxtzService dxtzService;
    @Resource
    SsdrService ssdrService;

    @Resource
    PubLylqInfoEntityMapper pubLylqInfoEntityMapper;
    @Resource
    private SdxxService sdxxService;

    @Resource
    PubYysdSsdrdzEntityMapper pubYysdSsdrdzEntityMapper;
    @Resource
    GdController gdController;

    @Resource
    RwlzService rwlzService;
    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;
    /**
     * 获取已分配的任务列表
     * @param  start 开始时间 end 结束时间
     * @return
     */
    @RequestMapping(value = "/getAssignedRecord.do", method = RequestMethod.POST)
    @ResponseBody
    public List<YysdModel> getAssignedRecord(HttpServletRequest request, @RequestParam String start, @RequestParam String end) {
        // 暂时在测试库，以后会在指定的法院
        DynamicDataSource.router(SDPT_FYDM);
        String yhdm = gdRyxxService.getYhdm(request);
        String fybh = gdRyxxService.findByYhdm(yhdm).getFybh();
        return yysdService.getAssignedRecord(start, end, fybh);
    }

    @RequestMapping(value = "/download_rwfp_excel.do", method = RequestMethod.GET)
    @ResponseBody
    public void getYysdInfosAndDownload(HttpServletRequest request, HttpServletResponse response, @RequestParam Integer sdjg, @RequestParam String start, @RequestParam String end) {
        DynamicDataSource.router(SDPT_FYDM);
        String yhdm = gdRyxxService.getYhdm(request);
        String fybh = gdRyxxService.findByYhdm(yhdm).getFybh();
        List<YysdModel> yysdModels = yysdService.getAssignedRecord(start, end, fybh);
        List<SdxxData> sdxxDatas = new ArrayList<>();
        for (YysdModel yysdModel : yysdModels) {
            List<PubYysdSsdrModel> yysdSsdrModelList = ssdrService.findByYysdbh(Integer.valueOf(yysdModel.getYysdbh()));
            for (PubYysdSsdrModel pubYysdSsdrModel : yysdSsdrModelList) {
                SdxxData sdxxData = new SdxxData();
                sdxxData.setYysdbh(pubYysdSsdrModel.getYysdbh());
                sdxxData.setAh(yysdModel.getAh());
                sdxxData.setDsrxm(pubYysdSsdrModel.getSsdrmc());
                sdxxData.setSsdw(pubYysdSsdrModel.getSsdrssdw());
                PubYysdWsEntity[] pubYysdWsEntities = yysdService.selectByYysdbh(pubYysdSsdrModel.getYysdbh());
                StringBuilder wslx = new StringBuilder();
                int count = 0;
                for (int i = 0; i < pubYysdWsEntities.length; ++i) {
                    if(pubYysdWsEntities[i].getSsdrbh() != pubYysdSsdrModel.getSsdrbh()) {
                        continue;
                    }
                    if (i == pubYysdWsEntities.length - 1) {
                        wslx.append(pubYysdWsEntities[i].getWslb());
                        ++ count;
                    } else {
                        wslx.append(pubYysdWsEntities[i].getWslb() + ",");
                        ++count;
                    }
                }
                sdxxData.setWssl(count);
                sdxxData.setWslx(wslx.toString());
                if (yysdModel.getLaay() != null) {
                    sdxxData.setAy(yysdModel.getLaay());
                }
//                String jg = "暂无送达结果";
//                if (null != yysdModel.getSdsj()) {
//                    if ("Y".equals(yysdModel.getSdjg())) {
//                        jg = "送达成功";
//                    }
//                    if ("N".equals(yysdModel.getSdjg())) {
//                        jg = "送达失败";
//                    }
//                    if ("X".equals(yysdModel.getSdjg())) {
//                        jg = "撤回";
//                    }
//                }

                sdxxData.setSdjg(yysdModel.getSdjg());
                if(null != pubYysdSsdrModel.getSdjg()) {
                    sdxxData.setDsrsdjg(ssdrService.readSdzt(pubYysdSsdrModel.getSdjg()));
                }
                if (null != yysdModel.getYysj()) {
                    sdxxData.setYysj(yysdModel.getYysj());
                }
                if (null !=yysdModel.getSdsj()) {
                    sdxxData.setFksj(yysdModel.getSdsj());
                }
                if (null !=yysdModel.getYysj() && null != yysdModel.getSdsj()) {
                    SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date yysj = sm.parse(yysdModel.getYysj());
                        Date sdsj = sm.parse(yysdModel.getSdsj());
                        long diff = sdsj.getTime() - yysj.getTime();
                        int day = (int) diff / (1000 * 60 * 60 * 24);
                        sdxxData.setSdzq(day);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if (null !=yysdModel.getBmmc()) {
                    sdxxData.setTs(yysdModel.getBmmc());
                }
                sdxxData.setYysxm(yysdModel.getYyr());
                if (null !=yysdModel.getBz()) {
                    sdxxData.setBz(yysdModel.getYysdbz());
                }
                QuerySdpPhoneVo querySdpPhoneVo = new QuerySdpPhoneVo();
                querySdpPhoneVo.setYysdBh(pubYysdSsdrModel.getYysdbh());
                querySdpPhoneVo.setSsdrbh(pubYysdSsdrModel.getSsdrbh());
                List<PubSsdrHmEntity> pubSsdrHmEntitys = pubSsdrHmEntityMapper.querySdpPhone(querySdpPhoneVo);
                String xfhm = "无";
                StringBuilder mwhm = new StringBuilder();
                for (int i = 0; i < pubSsdrHmEntitys.size(); ++i) {
                    if (pubSsdrHmEntitys.get(i).getOperatorType().equals("CUCC")) {
                        xfhm = "有";
                        continue;
                    }
                    mwhm.append(pubSsdrHmEntitys.get(i).getShowTel()).append("/");
                }
                sdxxData.setMwhm(mwhm.toString());
                sdxxData.setXfhm(xfhm);

                List<EmssdModel> emsInfo = sdxxService.getEmssdGdInfo(Integer.parseInt(yysdModel.getYysdbh()), yysdModel.getFybh());
                String rq = "";
                String emsjg = "无";
                if (null !=emsInfo) {
                    for (EmssdModel emssdModel : emsInfo) {
                        if (emssdModel.getSjrxm().trim().equals(pubYysdSsdrModel.getSsdrmc().trim())) {
                            if (null !=emssdModel.getYjsj()) {
                                rq = emssdModel.getYjsj();
                            }
                            if (null !=emssdModel.getSdjg() && (emsjg.equals("无") || emsjg.equals("失败"))) {
                                emsjg = emssdModel.getSdjg();
                            }
                        }
                    }
                    sdxxData.setEmsrq(rq);
                    sdxxData.setEmsjg(emsjg);
                }
                String lylqsj = "";
                List<LylqModel> byYysdbh = pubLylqInfoEntityMapper.findByYysdbh(Integer.parseInt(yysdModel.getYysdbh()));
                if (null !=byYysdbh) {
                    for (LylqModel lylqModel : byYysdbh) {
                        if (lylqModel.getSsdrbh().equals(pubYysdSsdrModel.getSsdrbh())) {
                            lylqsj = lylqModel.getLqsj();
                        }
                    }
                    sdxxData.setLylqsj(lylqsj);
                }
                if (null !=yysdModel.getKtsj()) {
                    sdxxData.setKtsj(yysdModel.getKtsj());
                }
                if (null !=pubYysdSsdrModel.getSftydzsd()) {
                    sdxxData.setSftydzsd(pubYysdSsdrModel.getSftydzsd().equals(1) ? "是" : "否");
                }
                sdxxDatas.add(sdxxData);
            }
        }
        String fileName = "E:\\" + UUID.randomUUID() + "送达信息.xlsx";
        EasyExcel.write(fileName, SdxxData.class).sheet().doWrite(sdxxDatas);
        File file = new File(fileName);
        InputStream fis = null;
        byte[] buffer = null;
        try {
            fis = new BufferedInputStream(new FileInputStream(fileName));
            buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            gdController.downFileExcel(fileName, buffer, response);
            File f = new File(fileName);
            if(f.exists()) {
                f.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取未分配的任务列表
     * @param start 开始时间 end 结束时间
     * @return
     */
    @RequestMapping(value = "/getUndistributedRecord.do", method = RequestMethod.POST)
    @ResponseBody
    public List<YysdModel> getUndistributedRecord(HttpServletRequest request, @RequestParam String start, @RequestParam String end) {
        // 暂时在测试库，以后会在指定的法院
        DynamicDataSource.router(SDPT_FYDM);
        String yhdm = gdRyxxService.getYhdm(request);
        String fybh = gdRyxxService.findByYhdm(yhdm).getFybh();
        return yysdService.getUndistributedRecord(start, end, fybh);
    }

    /**
     * 工单分配
     * @param request
     * @param distributeYysdModel
     * @return
     */
    @RequestMapping(value = "/saveSdryAssigned.do", method = RequestMethod.POST)
    @ResponseBody
    public Result saveSdryAssigned(HttpServletRequest request, @RequestBody DistributeYysdModel distributeYysdModel) {
        log.error("sdpt操作"+request.getRemoteAddr()+":工单流转");
        // 暂时在测试库，以后会在指定的法院
        DynamicDataSource.router(SDPT_FYDM);
        UserContextModel userContext = (UserContextModel) request.getSession()
                .getAttribute("userContext");
        for (Integer yysdbh : distributeYysdModel.getSelectedYysdbh()) {
            PubYysdJbEntity jb = yysdService.selectByPrimaryKey(yysdbh);
            StringBuilder msg = new StringBuilder();
            msg.append("【天津法院】您好！收到新的送达任务").append("：（工单号:")
                    .append(yysdbh).append("，案号:").append(jb.getAh()).append("，请及时登录系统查看，如有问题请及时联系承办法官。");
            MwdxSendVo mwdxSendVo = new MwdxSendVo();
            PubYysdRyxxEntity pubYysdRyxxEntity=gdRyxxService.selectByPrimaryKey(distributeYysdModel.getSdrybh());
            if(pubYysdRyxxEntity!=null){
                mwdxSendVo.setSendphone(pubYysdRyxxEntity.getLxdh());
                mwdxSendVo.setMsgcontent(msg.toString());
                mwdxSendVo.setSendtype(7);
                mwdxSendVo.setYysdbh(yysdbh);
//                dxtzService.sendPlaintext(mwdxSendVo, null);
            }
            log.error("sdpt操作工单流转,工单号："+yysdbh);
//            yysdService.addNewOrder(yysdbh,pubYysdRyxxEntity.getWwempid());
        }
        RwlzVO rwlzVO = new RwlzVO();
        rwlzVO.setYysdbhList(distributeYysdModel.getSelectedYysdbh());
        rwlzVO.setCreater(Integer.parseInt(userContext.getYhbh()+""));
        rwlzVO.setTarget(distributeYysdModel.getSdrybh());
        rwlzService.insert(rwlzVO);

        return Result.succeed;
    }

    /**
     * 获取所有的送达人员
     *
     * @return List<PubYysdRyxxEntity>
     */
    @RequestMapping(value = "/getSdry.do", method = RequestMethod.POST)
    @ResponseBody
    public List<PubYysdRyxxEntity> getSdry(HttpServletRequest request) {
        // 暂时在测试库，以后会在指定的法院
        DynamicDataSource.router(SDPT_FYDM);
        String yhdm = gdRyxxService.getYhdm(request);
        if(yhdm==null||"".equals(yhdm)){
            return null;
        }
        if(gdRyxxService.findByYhdm(yhdm)==null){
            return null;
        }
        String fybh = gdRyxxService.findByYhdm(yhdm).getFybh();
        if("-1".equals(fybh)){
            return yysdService.getAllSdry();
        }
        return yysdService.getSdry(fybh);
    }

    /**
     * 获取法院相关代码及对应法院名称
     */

    @RequestMapping(value = "/getFyInfos.do", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray getFyInfos(HttpServletRequest request, Model model) {
        FYEnum[] fyEnums = FYEnum.values();
        JSONArray jsonArray = new JSONArray();

        for (FYEnum fyEnum : fyEnums) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("jc", fyEnum.getName());
            jsonObject.put("fybh", fyEnum.getFybh());
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }

    /**
     * 获取法院相关代码及对应法院名称
     */

    @RequestMapping(value = "/getFyInfos_cx.do", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray getFyInfosCx(HttpServletRequest request, Model model) {
        FYEnum[] fyEnums = FYEnum.values();
        JSONArray jsonArray = new JSONArray();

        HttpSession session = request.getSession();
        UserContextModel userContext = (UserContextModel) session.getAttribute("userContext");
        if (StringUtil.equals(userContext.getFybh(),"-1")){
            for (FYEnum fyEnum : fyEnums) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("jc", fyEnum.getName());
                jsonObject.put("fybh", fyEnum.getFybh());
                jsonArray.add(jsonObject);
            }
        }else {
            FYEnum fyEnum = FYEnum.getFyByFybh(userContext.getFybh());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("jc", fyEnum.getName());
            jsonObject.put("fybh", fyEnum.getFybh());
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }

    /**
     * 获取特定法院的送达人员
     *
     * @return
     */
    @RequestMapping(value = "/getFySdry.do", method = RequestMethod.POST)
    @ResponseBody
    public List<PubYysdRyxxEntity> getFySdry(String fybh) {
        // 暂时在测试库，以后会在指定的法院
        DynamicDataSource.router(SDPT_FYDM);
        return yysdService.getSdry(fybh);
    }


    /**
     * 获取特定法院的送达人员列表
     *
     * @return
     */
    @RequestMapping(value = "/getUserList.do", method = RequestMethod.POST)
    @ResponseBody
    public List<PubYysdRyxxEntity> getUserList(HttpServletRequest request) {
        UserContextModel userContextModel = (UserContextModel) request.getSession().getAttribute("userContext");
        // 暂时在测试库，以后会在指定的法院
        List<PubYysdRyxxEntity> sdry = new ArrayList<>();
        DynamicDataSource.router(SDPT_FYDM);
        String fybh = userContextModel.getFybh();
        if (Objects.equals(fybh,"-1")){
            sdry = yysdService.getAllSdry();
        }else {
            sdry = yysdService.getSdry(fybh);
        }
        return sdry;
    }


    @RequestMapping(value = "/update_user.zf",method = RequestMethod.POST)
    @ResponseBody
    public RetObj updateUser(HttpServletRequest request, @RequestBody PubYysdRyxxEntity pubDxmbInfoModel){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        HttpSession session = request.getSession();

        yysdService.updateUser(pubDxmbInfoModel);
        RetObj obj = new RetObj();
        obj.setCode("1");
        obj.setMessage("succcess");
        obj.setSuccess("true");
        return obj;
    }
    @RequestMapping(value = "/add_user.zf",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addUser(HttpServletRequest request, @RequestBody PubYysdRyxxEntity pubDxmbInfoModel){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        HttpSession session = request.getSession();

        yysdService.addUser(pubDxmbInfoModel);
        return ResultUtils.wrapSuccess();
    }

}
