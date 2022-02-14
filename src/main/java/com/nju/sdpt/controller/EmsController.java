package com.nju.sdpt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.entity.*;
import com.nju.sdpt.mapper.*;
import com.nju.sdpt.model.EmssdModel;
import com.nju.sdpt.model.FYEnum;
import com.nju.sdpt.model.XtyhModel;
import com.nju.sdpt.service.*;
import com.nju.sdpt.util.*;
import com.nju.sdpt.viewobject.EmsVO;
import com.nju.sdpt.viewobject.KdcxVO;
import com.nju.sdpt.viewobject.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * EMS控制层
 */
@RestController
@Api("EMS送达")
public class EmsController {
    @Autowired
    YysdService yysdService;
    @Autowired
    EmsService emsService;
    @Autowired
    KdtdMapper kdtdMapper;
    @Autowired
    CaxxService caxxService;
    @Autowired
    XtyhService xtyhService;
    @Autowired
    CygjService cygjService;
    @Autowired
    SsdrService ssdrService;
    @Autowired
    GdRyxxService gdRyxxService;
    @Autowired
    PubYysdJbEntityMapper pubYysdJbEntityMapper;
    @Resource
    RpoEmsInfoService rpoEmsInfoService;
    @Autowired
    LogService logService;
    @Autowired
    PubYysdSsdrEntityMapper pubYysdSsdrEntityMapper;
    @Autowired
    private AjJbMapper ajJbMapper;
    @Autowired
    PubYysdRyxxEntityMapper pubYysdRyxxEntityMapper;
    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;

    /**
     * 获取工单EMS列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getEmsGd.aj", method = RequestMethod.POST)
    public List<EmssdModel> getEmsGd(HttpServletRequest request) {
        DynamicDataSource.router(SDPT_FYDM);
        HttpSession session = request.getSession();
        String yhm = (String) session.getAttribute("yhm");
        List<EmssdModel> emssdModels = new LinkedList<>();
        List<PubYysdJbEntity> pubYysdJbEntityList = yysdService.getGdByGdryxm(yhm);
        HashMap<String, List<Integer>> jbMap = new LinkedHashMap<>();//法院编号-工单号map
        for (PubYysdJbEntity jb : pubYysdJbEntityList) {
            List<Integer> jbbhs = jbMap.get(jb.getFybh());
            if (jbbhs == null) {
                List<Integer> newJbbhs = new LinkedList<>();
                newJbbhs.add(jb.getYysdbh());
                jbMap.put(jb.getFybh(), newJbbhs);
            } else {
                jbbhs.add(jb.getYysdbh());
                jbMap.put(jb.getFybh(), jbbhs);
            }
        }
        for (Map.Entry<String, List<Integer>> jbbhs : jbMap.entrySet()) {
            List<EmssdModel> models = emsService.getEmsModelByYysdbhList(jbbhs.getKey(), jbbhs.getValue());
            emssdModels.addAll(models);
        }
        emssdModels.sort(Comparator.comparing(EmssdModel::getScrq));
        Collections.reverse(emssdModels);
        return emssdModels;
    }

    @RequestMapping("/uploadKddh.aj")
    public Result uploadKddh(Integer yysdbh, Integer kdid, String kddh) {
        kddh = StringUtil.trim(kddh);
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdJbEntity pubYysdJbEntity = yysdService.selectByPrimaryKey(yysdbh);
        String fybh = pubYysdJbEntity.getFybh();
        DynamicDataSource.routerByFybh(fybh);
        //上传快递单号到ftp服务器
        String targetUrl = "http://130.1.1.150:18088/uploadKddh.aj?kddh=" + kddh + "&fybh=" + fybh;
//        String targetUrl = "http://localhost:18088/uploadKddh.aj?kddh="+kddh+"&fybh="+fybh;
//        String targetUrl = server+"/kdcx.aj?kddh="+kddh;
        String result = "";
        try {
            result = HttpUtil.doGet(targetUrl);
            if (StringUtil.equals(result, "ok")) {
                kdtdMapper.uploadKddhBykdid(kdid, kddh);
                return new Result(true, "提交成功", null);
            } else {
                return new Result(false, "提交失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "提交失败", null);
        }

    }

    /**
     * 上传面单
     *
     * @param file
     * @param yysdbh
     * @param kdid
     * @param request
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @RequestMapping("/uploadEmsmd.do")
    public Result uploadfile(@RequestParam("file") MultipartFile file, @RequestParam("yysdbh") Integer yysdbh, @RequestParam("kdid") Integer kdid, HttpServletRequest request)
            throws IllegalStateException, IOException {

        DynamicDataSource.router(SDPT_FYDM);
        PubYysdJbEntity pubYysdJbEntity = yysdService.selectByPrimaryKey(yysdbh);
        DynamicDataSource.router(FydmUtil.getFydmByFybh(pubYysdJbEntity.getFybh()));
        if(pubYysdJbEntity.getAjxh() == -2){
            int ajxh=ajJbMapper.getAjxhByAh(pubYysdJbEntity.getAh());
            pubYysdJbEntity.setAjxh(ajxh);
        }
        XtyhModel xtyhModel = xtyhService.getXtyhByYhm("sdzx");
        String yhbh = xtyhModel.getYhbh() + "";
        if (pubYysdJbEntity.getAjxh() > 0) {
            Boolean isSuccess = emsService.uploadEmsmd(pubYysdJbEntity.getAjxh(), file, yhbh, kdid, pubYysdJbEntity.getFybh());
            return isSuccess ? new Result(true, "上传成功", "上传成功") : new Result(false, "上传失败", "上传失败");
        } else {
            if (pubYysdJbEntity.getAjxh() == -1) {
                DynamicDataSource.router(SDPT_FYDM);
                List<PubCaxxEntity> pubCaxxEntities = caxxService.getCaxxByYysdbh(yysdbh);
                DynamicDataSource.router(FydmUtil.getFydmByFybh(pubYysdJbEntity.getFybh()));
                for (PubCaxxEntity pubCaxxEntity : pubCaxxEntities) {
                    Boolean result = emsService.uploadEmsmd(pubCaxxEntity.getAjxh(), file, yhbh, kdid, pubYysdJbEntity.getFybh());
                    if (!result) {
                        return new Result(false, "上传失败", null);
                    }
                }
                return new Result(true, "上传成功", null);
            }
        }
        return null;
    }

    /**
     * 下载快递面单
     *
     * @param
     */
    @RequestMapping(value = "/downloadEmsmd.do", method = RequestMethod.GET)
    public void downloadEmsmd(HttpServletRequest request, HttpServletResponse response, @RequestParam("yysdbh") Integer yysdbh, @RequestParam("kdid") Integer kdid) throws IOException {
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdJbEntity pubYysdJbEntity = yysdService.selectByPrimaryKey(yysdbh);
        DynamicDataSource.routerByFybh(pubYysdJbEntity.getFybh());
        String filepath = emsService.downloadEmsmd(kdid, pubYysdJbEntity.getFybh(),pubYysdJbEntity.getAjxh());
        File file = new File(filepath);
        String filename = file.getName();
        DownloadUtils.downloadFile(filepath, filename, response, request);
    }

    @RequestMapping(value = "/deleteEmsmd.do", method = RequestMethod.POST)
    public Result deleteEmsmd(HttpServletRequest request, HttpServletResponse response, @RequestParam("yysdbh") Integer yysdbh, @RequestParam("kdid") Integer kdid) {
        Result ret = new Result(false, null, null);
        DynamicDataSource.router(SDPT_FYDM);
        try {
            PubYysdJbEntity pubYysdJbEntity = yysdService.selectByPrimaryKey(yysdbh);
            if (pubYysdJbEntity != null) {
                String fybh = pubYysdJbEntity.getFybh();
                DynamicDataSource.router(FydmUtil.getFydmByFybh(fybh));
                XtyhModel xtyhModel = xtyhService.getXtyhByYhm("sdzx");
                String yhbh = String.valueOf(xtyhModel.getYhbh());
                Boolean isSuccess = emsService.deleteEmsmd(kdid, fybh, yhbh,pubYysdJbEntity.getAjxh());
                ret = isSuccess ? new Result(true, "删除成功", "删除成功") : new Result(false, "删除失败", "删除失败");
            }
        } catch (Exception e) {
            ret = new Result(false, "删除失败", "删除失败");
        } finally {
            DynamicDataSource.router(SDPT_FYDM);
        }
        return ret;
    }


    /**
     * 新增ems
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/add_emsGd.aj", method = RequestMethod.POST)
    public Result getEmsGd(HttpServletRequest request, @RequestBody EmsVO emsVO) {
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdJbEntity yysdJbEntity = yysdService.selectByPrimaryKey(emsVO.getYysdbh());
        String fybh = yysdJbEntity.getFybh();
        Integer ajxh = yysdJbEntity.getAjxh();
        if (ajxh == -1) {
            List<PubCaxxEntity> caxxEntityList = caxxService.getCaxxByYysdbh(emsVO.getYysdbh());
            ajxh = caxxEntityList.get(0).getAjxh();
        }
        if(ajxh==-2){
            DynamicDataSource.router(FYEnum.getFYDMByFybh(fybh));
            ajxh=ajJbMapper.getAjxhByAh(yysdJbEntity.getAh());
            DynamicDataSource.router(SDPT_FYDM);
        }
        // 在jb中进行修改
        yysdJbEntity.setEmssd("ING");
        yysdService.updateByPrimaryKeySelective(yysdJbEntity);
        HttpSession session = request.getSession();
        PubYysdRyxxEntity pubYysdRyxxEntity = gdRyxxService.getYhBySession(session);
        Integer sdybh = pubYysdRyxxEntity.getYhbh();
        PubYysdSsdrEntity ssdrEntity = ssdrService.findByYysdbhAndSsdrbh(emsVO.getYysdbh(), emsVO.getSsdrbh());
        DynamicDataSource.routerByFybh(fybh);

        // 对编号进行操作，查看是否为当事人
        if(!fybh.equals("74")&&!ssdrEntity.getSsdrmc().equals(pubYysdSsdrEntityMapper.getDsrJc(ajxh,ssdrEntity.getSsdrbh()))){
            List<DsrJbEntity> dsrJbEntities=pubYysdSsdrEntityMapper.getDsrJbByAjxh(ajxh);
            boolean isFound=false;
            for(DsrJbEntity dsrJbEntity:dsrJbEntities){
                if(ssdrEntity.getSsdrmc().equals(dsrJbEntity.getDsrjc())){
                    ssdrEntity.setSsdrbh(dsrJbEntity.getDsrbh());
                    isFound=true;
                    break;
                }
            }
            if(!isFound){
                return new Result(false, "未在系统内找到相应人员", "未在系统内找到相应人员");
            }
        }
        String yhm = "sdzx";
        String password = xtyhService.getYhklByYhm(yhm);
        XtglCygjbEntity xtglCygjbEntity = cygjService.getCygjByName("EMS打印管理系统");
        String server = null;
        server = xtglCygjbEntity.getLj();
//        server = "http://localhost:8080";
        //地址类型截掉
        if (emsVO.getDz() != null && (emsVO.getDz().contains("送达地址") || emsVO.getDz().contains("送达地址") || emsVO.getDz().contains("工作单位") || emsVO.getDz().contains("工作单位") || emsVO.getDz().contains("户籍所在地"))) {
            emsVO.setDz(emsVO.getDz().substring(emsVO.getDz().indexOf(':') + 1));
        }
        String lxfs = yysdJbEntity.getBgdh() != null ? yysdJbEntity.getBgdh() : " ";
        String yddh = "";
        String zzdh = "";
        String bgdh = "";
        if(emsVO.getDh()!=null&&emsVO.getDh().size()>0){
            yddh = emsVO.getDh().get(0);
            zzdh = emsVO.getDh().size()>1? emsVO.getDh().get(1):"";
            bgdh = emsVO.getDh().size()>2? emsVO.getDh().get(2):"";
        }
        String dz = emsVO.getDz() == null || emsVO.getDz().equals("0") ? "" : emsVO.getDz();
        String xt = "/emsdyxt";
//        xt = "";
        String url = "/emsForSdpt.do";
        StringBuilder dh = new StringBuilder();
        if(yddh != "") {
            dh.append(yddh);
        }
        if(zzdh != "") {
            if(dh.length() > 0) {
                dh.append("/");
            }
            dh.append(zzdh);
        }
        if(bgdh != "") {
            if(dh.length() > 0) {
                dh.append("/");
            }
            dh.append(bgdh);
        }
        String parameters = "";
        if("74".equals(fybh)) {
//            parameters = "?username=" + yhm + "&password=" + password + "&ajxh=" + ajxh + "&jbr=" +UrlUtil.getURLEncoderString(yysdJbEntity.getYyrxm())  + "&yysdbh=" + emsVO.getYysdbh() + "&dsrbh=" + ssdrEntity.getSsdrbh() + "&dh=" + yddh+ "&dz=" + UrlUtil.getURLEncoderString(dz) + "&jjrdh=" + lxfs + "&sdybh=" + sdybh;
            parameters = "?username=" + yhm + "&password=" + password + "&ajxh=" + ajxh + "&jbr=" +UrlUtil.getURLEncoderString(yysdJbEntity.getYyrxm())  + "&yysdbh=" + emsVO.getYysdbh() + "&dsrbh=" + ssdrEntity.getSsdrbh() + "&yddh=" + yddh+ "&zzdh=" + zzdh+"&bgdh=" + bgdh+ "&dz=" + UrlUtil.getURLEncoderString(dz) + "&jjrdh=" + lxfs + "&sdybh=" + sdybh;
        }else {
            parameters = "?username=" + yhm + "&password=" + password + "&ajxh=" + ajxh + "&jbr=" +UrlUtil.getURLEncoderString(yysdJbEntity.getYyrxm())  + "&yysdbh=" + emsVO.getYysdbh() + "&dsrbh=" + ssdrEntity.getSsdrbh() + "&dh=" + yddh+ "&dz=" + UrlUtil.getURLEncoderString(dz) + "&jjrdh=" + lxfs + "&sdybh=" + sdybh;
        }
//        String parameters = "?username=" + yhm + "&password=" + password + "&ajxh=" + ajxh + "&jbr=" +UrlUtil.getURLEncoderString(yysdJbEntity.getYyrxm())  + "&yysdbh=" + emsVO.getYysdbh() + "&dsrbh=" + ssdrEntity.getSsdrbh() + "&yddh=" + yddh+"&zzdh="+zzdh+"&bgdh="+bgdh + "&dz=" + UrlUtil.getURLEncoderString(dz) + "&jjrdh=" + lxfs + "&sdybh=" + sdybh;
        String target = server + xt + url +parameters;
        return new Result(true, "成功", target);
    }

    /**
     * 新增ems
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/kdcx.aj", method = RequestMethod.POST)
    public Result kdcx(HttpServletRequest request, String kddh, String fybh) throws IOException {
        DynamicDataSource.routerByFybh(fybh);
        XtglCygjbEntity xtglCygjbEntity = cygjService.getCygjByName("EMS打印管理系统");
        String server = xtglCygjbEntity.getLj();
        String targetUrl = "http://130.1.1.150:18088/kdcx.aj?kddh=" + kddh + "&fybh=" + fybh;
//        String targetUrl = server+"/kdcx.aj?kddh="+kddh;
        String result = "";

        try {
            result = HttpUtil.doGet(targetUrl);
            ObjectMapper objectMapper1 = new ObjectMapper();
            KdcxVO[] kdcxVOS = objectMapper1.readValue(result, KdcxVO[].class);
            return new Result(true, null, kdcxVOS);
        } catch (Exception e) {
            return new Result(false, null, null);
        }
    }

    /**
     * TODO
     * @param kdid
     * @param fybh
     * @return
     */
    @RequestMapping(value = "/emsLogInterface.aj", method = RequestMethod.GET)
    public Result emsLogInterface(Integer kdid, String fybh) {
        if ("65".equals(fybh) || "66".equals(fybh) || "67".equals(fybh) || "71".equals(fybh)) {
            fybh = "74";
        }
        if(StringUtil.isEmpty(fybh)||FydmUtil.getFydmByFybh(fybh)==null){
            return Result.failed;
        }
        DynamicDataSource.router(FydmUtil.getFydmByFybh(fybh));
        PubKdtdEntity pubKdtdEntity =kdtdMapper.findByKdid(kdid);
        DynamicDataSource.router(SDPT_FYDM);
        pubYysdJbEntityMapper.updateStatusByWayAndYysdbh("EMSSD", pubKdtdEntity.getYysdbh());
        if(pubKdtdEntity.getSdybh()==null){
            return new Result(false,"送达员编号为空",null);
        }
        PubYysdJbEntity pubYysdJbEntity = yysdService.selectByPrimaryKey(pubKdtdEntity.getYysdbh());
        PubYysdRyxxEntity pubYysdRyxxEntity = gdRyxxService.selectByPrimaryKey(pubKdtdEntity.getSdybh());
        ssdrService.loadSsdrSdjg(pubKdtdEntity.getYysdbh(),pubKdtdEntity.getDsrbh(),fybh);
        if (pubYysdJbEntity.getAjxh() == -1) {
            List<PubCaxxEntity> caxxEntityList = caxxService.getCaxxByYysdbh(pubYysdJbEntity.getYysdbh());
            for (PubCaxxEntity pubCaxxEntity : caxxEntityList) {
                logService.addLog(pubCaxxEntity.getAjxh(), fybh, pubKdtdEntity.getSjrxm(), 22, "", pubYysdRyxxEntity.getYhmc(), pubCaxxEntity.getYysdbh());
            }
        } else {
            logService.addLog(pubYysdJbEntity.getAjxh(), fybh, pubKdtdEntity.getSjrxm(), 22, "", pubYysdRyxxEntity.getYhmc(), pubYysdJbEntity.getYysdbh());
        }
        return null;
    }


    @RequestMapping("/upload_ems_kdhz.do")
    public Result uploadSdhz(@RequestParam("file") MultipartFile file, @RequestParam("yysdbh") Integer yysdbh, @RequestParam("kdid") Integer kdid, HttpServletRequest request)
            throws IllegalStateException, IOException {
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdJbEntity pubYysdJbEntity = yysdService.selectByPrimaryKey(yysdbh);
        DynamicDataSource.router(FydmUtil.getFydmByFybh(pubYysdJbEntity.getFybh()));
        kdtdMapper.uploadKdhzBykdid(kdid, file.getBytes());
        return new Result(true, "上传成功", null);
    }

    @RequestMapping("/upload_emsSdjg.do")
    public Result upload_emsSdjg(@RequestParam("fybh") String fybh, @RequestParam("kdid") Integer kdid, @RequestParam("sdjg") String sdjg)
            throws IllegalStateException, IOException {
        DynamicDataSource.routerByFybh(fybh);
        kdtdMapper.uploadSdjgByKdid(kdid, sdjg, new Date());
        PubKdtdEntity pubKdtdEntity = kdtdMapper.findByKdid(kdid);
        DynamicDataSource.router(SDPT_FYDM);
        rpoEmsInfoService.uploadSdjg(pubKdtdEntity);
        return new Result(true, "上传成功", null);
    }


    @RequestMapping("/delete_EmsRecord.do")
    public Result delete_EmsRecord(@RequestParam("yysdbh") Integer yysdbh, @RequestParam("kdid") Integer kdid, @RequestParam("fybh") String fybh) {
        Result result = Result.failed;
        boolean stage1 = false,stage2 =false;
        try {
            if(emsService.existsEmsInfoByKdid(yysdbh, kdid)){
                stage1 = emsService.deleteEmsInfo(yysdbh, kdid);
            }else {
                stage1 = true;
            }
            if (stage1) {
                stage2 = emsService.deleteKdtdByKdid(kdid, fybh);
            }
        } catch (Exception e) {

        }
        if(!stage1){
            result.setMessage("删除Ems信息失败");
        }
        if(!stage2){
            result.setMessage("删除Ems系统的信息失败");
        }
        if(stage1 && stage2){
            return Result.succeed;
        }
        return result;
    }

    /**
     * 根据快递单号获取相关的快递信息
     * @param data
     * @return
     */
    @RequestMapping(value = "/getEmsInfoByKddh")
    public List<PubKdtdEntity> getEmsInfoByKddh(@RequestBody Map data){
        String kddh=data.get("kddh").toString();
        String yhm=data.get("yhm").toString();
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdRyxxEntity pubYysdRyxxEntity=pubYysdRyxxEntityMapper.findByYhdm(yhm);
        if(!"-1".equals(pubYysdRyxxEntity.getFybh())){
            DynamicDataSource.router(FYEnum.getFYDMByFybh(pubYysdRyxxEntity.getFybh()));
        }else{
            DynamicDataSource.router(FYEnum.TJGY.getFydm());
        }
        return emsService.getEmsInfoByKddh(kddh);
    }

    /**
     * 关联快递单号
     * @param data
     * @return
     */
    @RequestMapping(value = "/glkddh")
    public Result glkddh(@RequestBody Map data){
        String kddh=data.get("kddh").toString();
        String yhm=data.get("yhm").toString();
        Integer yysdbh=Integer.valueOf(data.get("yysdbh").toString());
        Integer ssdrbh=Integer.valueOf(data.get("ssdrbh").toString());
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdRyxxEntity pubYysdRyxxEntity=pubYysdRyxxEntityMapper.findByYhdm(yhm);
        PubYysdJbEntity pubYysdJbEntity=pubYysdJbEntityMapper.selectByPrimaryKey(yysdbh);
        if(!"-1".equals(pubYysdRyxxEntity.getFybh())){
            DynamicDataSource.router(FYEnum.getFYDMByFybh(pubYysdRyxxEntity.getFybh()));
        }else{
            DynamicDataSource.router(FYEnum.TJGY.getFydm());
        }
        PubKdtdEntity pubKdtdEntity=emsService.getGlEmsInfoByKddh(kddh);
        if(pubKdtdEntity==null){
            return new Result(false,"关联失败",null);
        }
        int kdid=kdtdMapper.MaxKdid();
//        PubKdtdEntity newPubKdtdEntity=new PubKdtdEntity();
        pubKdtdEntity.setDsrbh(ssdrbh);
        pubKdtdEntity.setAh(pubYysdJbEntity.getAh());
        pubKdtdEntity.setYysdbh(yysdbh);
        pubKdtdEntity.setJjrdh(pubYysdJbEntity.getBgdh());
        pubKdtdEntity.setJjrbm(pubYysdJbEntity.getBmmc());
        pubKdtdEntity.setSdybh(pubYysdRyxxEntity.getYhbh());
        pubKdtdEntity.setKdid(kdid+1);

        kdtdMapper.insertKdtd(pubKdtdEntity);
        return new Result(true,"关联成功",null);
    }
}



