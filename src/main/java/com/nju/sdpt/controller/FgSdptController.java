package com.nju.sdpt.controller;

import com.nju.sdpt.constant.SdptConstants;
import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.entity.DsrJbEntity;
import com.nju.sdpt.entity.PubYysdJbEntity;
import com.nju.sdpt.entity.PubYysdRyxxEntity;
import com.nju.sdpt.entity.XtglCygjbEntity;
import com.nju.sdpt.mapper.DmbMapper;
import com.nju.sdpt.model.*;
import com.nju.sdpt.service.*;
import com.nju.sdpt.util.Base64Util;
import com.nju.sdpt.util.StringUtil;
import com.nju.sdpt.viewobject.*;
import io.swagger.annotations.Api;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 法官送达平台
 *
 * @author zzy
 */
@Controller
@Api("法官送达平台")
public class FgSdptController {
    @Autowired
    YysdService yysdService;
    @Autowired
    DsrjbService dsrjbService;
    @Autowired
    DmService dmService;
    @Autowired
    AjjbxxService ajjbxxService;
    @Autowired
    LaayService laayService;
    @Autowired
    XtyhService xtyhService;
    @Autowired
    LogService logService;
    @Autowired
    SsdrQrxxService ssdrQrxxService;
    @Autowired
    GdRyxxService gdRyxxService;
    @Autowired
    DxtzService dxtzService;
    @Autowired
    SdxxService sdxxService;
    @Autowired
    CygjService cygjService;

    @Autowired
    SsdrService ssdrService;
    @Autowired
    DmbMapper dmbMapper;
    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;


    /**
     * 获取工单列表
     */
    @PostMapping("/getYysdInfo")
    @ResponseBody
    public PubYysdJbEntity getYysdInfo(Integer yysdbh, HttpServletRequest request) {
        DynamicDataSource.router(SDPT_FYDM);
        final PubYysdJbEntity yysdJbEntity = yysdService.selectByPrimaryKey(yysdbh);
        return yysdJbEntity;
    }


    @RequestMapping(value = "/getYysdByCbr.do", method = RequestMethod.POST)
    @ResponseBody
    public List<YysdModel> getYysdByCbr(HttpServletRequest request, Integer sdjg) {
        HttpSession session = request.getSession();
        String yhm = (String) session.getAttribute("yhm");
        String fybh = (String) session.getAttribute("fybh");
        FYEnum fyEnum = FYEnum.getFyByFybh(fybh);
        DynamicDataSource.router(fyEnum.getFydm());
        XtyhModel xtyhModel = xtyhService.getXtyhByYhm(yhm);
        String yhmc = xtyhModel.getYhmc();
        DynamicDataSource.router(SDPT_FYDM);
        List<YysdModel> yysdModels = new ArrayList<YysdModel>();
        if (sdjg != 1) {
            yysdModels = yysdService.findByCbrAndCljg(yhmc, sdjg, fybh);
        } else {

            yysdModels = yysdService.mergeGd(yysdService.findByCbrAndCljg(yhmc, 2, fybh)
                    , yysdService.findByCbrAndCljg(yhmc, 3, fybh)
                    , yysdService.findByCbrAndCljg(yhmc, 4, fybh)
                    , yysdService.findByCbrAndCljg(yhmc, 5, fybh)
                    , yysdService.findByCbrAndCljg(yhmc, 6, fybh), new ArrayList<>());
        }

        return yysdModels;
    }


    @RequestMapping(value = "/getYysdByCbrCount.do", method = RequestMethod.POST)
    @ResponseBody
    public int[] getYysdByCbrCount(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String yhm = (String) session.getAttribute("yhm");
        String fybh = (String) session.getAttribute("fybh");
        FYEnum fyEnum = FYEnum.getFyByFybh(fybh);
        DynamicDataSource.router(fyEnum.getFydm());
        XtyhModel xtyhModel = xtyhService.getXtyhByYhm(yhm);
        String yhmc = xtyhModel.getYhmc();
        DynamicDataSource.router(SDPT_FYDM);
        int[] counts = new int[6];
        for (int i = 1; i < 7; i++) {
            List<YysdModel> yysdModels = yysdService.findByCbrAndCljg(yhmc, i, fybh);
            counts[i - 1] = yysdModels.size();
        }
        return counts;
    }


    /**
     * 获取目标法官的承办案件
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getTargetJudgeAjInfos.do", method = RequestMethod.POST)
    @ResponseBody
    public List<AjjbxxModel> getAjInfos(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String fybh = (String) session.getAttribute("fybh");
        FYEnum fyEnum = FYEnum.getFyByFybh(fybh);
        DynamicDataSource.router(fyEnum.getFydm());
        String yhm = (String) session.getAttribute("yhm");
        String fgmc = xtyhService.getFgmcByYhm(yhm);
        return ajjbxxService.getCbajlb(fgmc);
    }


    /**
     * 修改是否签署送达地址确认书(法官)
     *
     * @param request
     * @param
     * @param
     */
    @RequestMapping(value = "/qsqrs_update_fg.aj", method = RequestMethod.POST)
    @ResponseBody
    public Result qsqrs_update_fg(HttpServletRequest request, @RequestBody PubSsdrQrxxEntityVO pubSsdrQrxxEntityVO) {
        DynamicDataSource.router(FYEnum.getFyByFybh(pubSsdrQrxxEntityVO.getFybh()).getFydm());
        String dsrmc = dsrjbService.getDsrByAjxhAndDsrbh(pubSsdrQrxxEntityVO.getAjxh(), pubSsdrQrxxEntityVO.getDsrbh()).getDsrjc();
        HttpSession session = request.getSession();
        String yhm = (String) session.getAttribute("yhm");
        String yhmc = xtyhService.getFgmcByYhm(yhm);
        DynamicDataSource.router(SDPT_FYDM);
        ssdrQrxxService.qsqrsUpdate(pubSsdrQrxxEntityVO);
        logService.addLog(pubSsdrQrxxEntityVO.getAjxh(), pubSsdrQrxxEntityVO.getFybh(), dsrmc, SdptConstants.LOG_TYPE.getLogTypeByJc("QSQRS").getTypeNum(), "", yhmc, null);
        return new Result(true, "成功", null);
    }

    /**
     * 修改是否签署送达地址确认书(法官)
     *
     * @param request
     * @param
     * @param
     */
    @RequestMapping(value = "/tydzsd_update_fg.aj", method = RequestMethod.POST)
    @ResponseBody
    public Result tydzsd_update_fg(HttpServletRequest request, @RequestBody PubSsdrQrxxEntityVO pubSsdrQrxxEntityVO) {
        DynamicDataSource.router(FYEnum.getFyByFybh(pubSsdrQrxxEntityVO.getFybh()).getFydm());
        HttpSession session = request.getSession();
        String yhm = (String) session.getAttribute("yhm");
        String yhmc = xtyhService.getFgmcByYhm(yhm);
        String dsrmc = dsrjbService.getDsrByAjxhAndDsrbh(pubSsdrQrxxEntityVO.getAjxh(), pubSsdrQrxxEntityVO.getDsrbh()).getDsrjc();
        DynamicDataSource.router(SDPT_FYDM);
        ssdrQrxxService.tydzsdUpdate(pubSsdrQrxxEntityVO);
        logService.addLog(pubSsdrQrxxEntityVO.getAjxh(), pubSsdrQrxxEntityVO.getFybh(), dsrmc, SdptConstants.LOG_TYPE.getLogTypeByJc("TYDZSD").getTypeNum(), "", yhmc, null);
        return new Result(true, "成功", null);
    }


    /**
     * 工单撤回
     */
    @RequestMapping(value = "/gdch.aj", method = RequestMethod.POST)
    @ResponseBody
    public Result gdch(HttpServletRequest request, Integer yysdbh) {
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdJbEntity yysdJbEntity = yysdService.selectByPrimaryKey(yysdbh);
        String fybh = yysdJbEntity.getFybh();
        Integer ajxh = yysdJbEntity.getAjxh();
        yysdService.gdWithdrawByYysdbh(yysdbh);
        logService.addLog(ajxh, fybh, "", 8, "", yysdJbEntity.getYyrxm(), yysdbh);
        return new Result(true, "撤回成功", null);

    }


    /**
     * 获取案件当事人基本信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getAjDsrInfos.do", method = RequestMethod.POST)
    @ResponseBody
    public AjInfo getAjDsrInfos(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer ajxh = (Integer) session.getAttribute("ajxh");
        String fybh = (String) session.getAttribute("fybh");
        FYEnum fyEnum = FYEnum.getFyByFybh(fybh);
        DynamicDataSource.router(fyEnum.getFydm());
        String yhm = (String) session.getAttribute("yhm");
        String fgmc = xtyhService.getFgmcByYhm(yhm);
        AjjbxxModel ajjbxxModel = ajjbxxService.getAjjbxxByAjxh(ajxh);
        List<DsrJbEntity> dsrJbDOS = dsrjbService.getDsrjblistByAjxh(ajxh);
        List<SsdrVO> ssdrVOSTemp = ssdrService.getSsdrxxByAjxhAndFybhAndDsrbh(ajxh, fybh);
        List<SsdrVO> ssdrVOSRet = new ArrayList<>();
        DynamicDataSource.router(fyEnum.getFydm());
        for (DsrJbEntity dsrJbDO : dsrJbDOS) {
            Integer dsrbh = dsrJbDO.getDsrbh();
            SsdrVO temp = new SsdrVO();
            List<SsdrVO> ssdrVOList = ssdrVOSTemp.stream().filter(x -> dsrbh.equals(x.getSsdrbh())).collect(Collectors.toList());
            if (ssdrVOList.size() > 1) {
                List<String> sdztList = Arrays.asList("待送达","送达中","送达失败","送达成功");
                int index = 0;
                for (int i = 0; i < ssdrVOList.size(); i++) {
                    int newIndex = sdztList.indexOf(ssdrVOList.get(i).getSdzt());
                    if(newIndex>index){
                        index = newIndex;
                        temp = ssdrVOList.get(i);
                    }
                }
            } else if (ssdrVOList.size() == 1) {
                temp = ssdrVOList.get(0);
            }
            if(StringUtil.isEmpty(temp.getSdzt())){
                temp.setSdzt("待送达");
            }
            temp.setSsdrbh(dsrJbDO.getDsrbh());
            temp.setSsdrmc(dsrJbDO.getDsrjc());
            temp.setSsdrbh(dsrbh);
            String dsrssdw = dmService.getDsrssdwByAjxzAndSpcxAndSpcxdz(dsrJbDO.getDsrssdw(), ajjbxxModel.getAjxz(), ajjbxxModel.getSpcx(), ajjbxxModel.getSpcxdz(), ajjbxxModel.getLarq()).getDmms();
            temp.setSsdw(dsrssdw);
            DsrjbVO dsrjbVO = dsrjbService.dsrEntityToDsrVO(dsrJbDO);
            temp.setDh(dsrjbVO.getDh());
            ssdrVOSRet.add(temp);
        }
        AjInfo ajInfo = new AjInfo();
        ajInfo.setAjxh(ajxh);
        ajInfo.setFybh(fybh);
        ajInfo.setFgmc(fgmc);
        ajInfo.setYhm(yhm);
        ajInfo.setAh(ajjbxxModel.getAh());
        ajInfo.setSsdrVOS(ssdrVOSRet);
        ajInfo.setAjmc(ajjbxxModel.getAjmc());
        return ajInfo;
    }

    /**
     * 获取某案件电话送达记录
     *
     * @param request
     * @return List<PubWebCallInfoModel>
     */
    @RequestMapping(value = "getDhsdInfo.do", method = RequestMethod.POST)
    @ResponseBody
    public List<PubWebCallInfoModel> getDhsdInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer ajxh = (Integer) session.getAttribute("ajxh");
        String fybh = (String) session.getAttribute("fybh");
        DynamicDataSource.router(SDPT_FYDM);
        return sdxxService.getDhsdInfo(ajxh, fybh);
    }

    /**
     * 获取某案件短信送达记录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "getDxsdInfo.do", method = RequestMethod.POST)
    @ResponseBody
    public List<DxsdInfoVO> getDxsdInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer ajxh = (Integer) session.getAttribute("ajxh");
        String fybh = (String) session.getAttribute("fybh");
        DynamicDataSource.router(SDPT_FYDM);
        return sdxxService.getDxsdInfo(ajxh, fybh);

    }

    /**
     * 获取某案件电子送达记录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "getDzsdInfo.do", method = RequestMethod.POST)
    @ResponseBody
    public List<DzsdModel> getDzsdInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer ajxh = (Integer) session.getAttribute("ajxh");
        String fybh = (String) session.getAttribute("fybh");
        return sdxxService.getDzsdInfo(ajxh, fybh);

    }

    /**
     * 获取某案件EMS送达记录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "getEmssdInfo.do", method = RequestMethod.POST)
    @ResponseBody
    public List<EmssdModel> getEmssdInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer ajxh = (Integer) session.getAttribute("ajxh");
        String fybh = (String) session.getAttribute("fybh");
        return sdxxService.getEmssdInfo(ajxh, fybh);
    }

    @RequestMapping(value = "getGgsdInfo.do", method = RequestMethod.POST)
    @ResponseBody
    public List<GgsdModel> getGgssdInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer ajxh = (Integer) session.getAttribute("ajxh");
        String fybh = (String) session.getAttribute("fybh");
        return sdxxService.getGgsdInfo(ajxh, fybh);

    }

    /**
     * 获取某案件来院领取送达记录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "getLylqInfo.do", method = RequestMethod.POST)
    @ResponseBody
    public List<LylqModel> getLylqInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer ajxh = (Integer) session.getAttribute("ajxh");
        String fybh = (String) session.getAttribute("fybh");
        DynamicDataSource.router(SDPT_FYDM);
        return sdxxService.getLylqInfo(ajxh, fybh);

    }

    @RequestMapping(value = "getZjsdInfo.do", method = RequestMethod.POST)
    @ResponseBody
    public List<ZjsdModel> getZjsdInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer ajxh = (Integer) session.getAttribute("ajxh");
        String fybh = (String) session.getAttribute("fybh");
        DynamicDataSource.router(SDPT_FYDM);
        return sdxxService.getZjsdInfo(ajxh, fybh);

    }

    /**
     * 获取法官页面送达地址
     *
     * @param request
     * @param sdWay
     * @param
     */
    @RequestMapping(value = "/getFgSdUrl.aj", method = RequestMethod.POST)
    @ResponseBody
    public Result getSdUrl(HttpServletRequest request, String sdWay, Integer ajxh) {
        HttpSession session = request.getSession();
        String fybh = (String) session.getAttribute("fybh");
        FYEnum fyEnum = FYEnum.getFyByFybh(fybh);
        DynamicDataSource.router(fyEnum.getFydm());
        String yhm = (String) session.getAttribute("yhm");
        String fydm = fyEnum.getFydm();
        AjjbxxModel ajjbxxModel = ajjbxxService.getAjjbxxByAjxh(ajxh);
        String ajxz = ajjbxxModel.getAjxz();
        String url = new String();
        if (StringUtil.equals(sdWay, "DHSD")) {
            String baseSdurl = "http://130.39.111.182:8080/znsdgj/ndlogin?";
            url = baseSdurl + "ajID=" + ajxh + "&nAjlb=" + ajxz + "&nFyid=" + fybh + "&loginId=" + yhm;
        }
        if (StringUtil.equals(sdWay, "DZSD")) {
            NpForDzfyService dsfyService = new NpForDzfyService();
            NpForDzfyServicePortType dsfyInterface = dsfyService.getNpForDzfyServiceHttpPort();
            url = dsfyInterface.createDzsd(Integer.toString(ajxh), yhm, FYEnum.getFyByFybh(fybh).getFydm());
        }
        if (StringUtil.equals(sdWay, "EMSSD")) {
            XtyhModel xtyhModel = xtyhService.getXtyhByYhm(yhm);
            XtglCygjbEntity xtglCygjbEntity = cygjService.getCygjByName("EMS打印管理系统");
            String server = xtglCygjbEntity.getLj();
            String xt = "/emsdyxt/";
            String method = "emsgateway.do";
            String service = "sqkdd";
            String parameters = "?username=" + yhm + "&password=" + xtyhModel.getYhkl() + "&service=" + service + "&param=ajxh_" + ajxh + ";isspxt_1";
            url = server + xt + method + parameters;
        }
        if (StringUtil.equals(sdWay, "GGSD")) {
            XtyhModel xtyhModel = xtyhService.getXtyhByYhm(yhm);
            DmbModel dmb = dmService.getDmByLbbhAndDmbh("USR_GGXT", "01");
            String doName = StringUtil.trim(dmb.getXgdm());
            url = doName += "?ajxh=" + ajxh + "&&fydm=" + fyEnum.getFydm() + "&&yhbh=" + xtyhModel.getYhbh();
        }
        Result result = new Result(true, "成功", url);
        String fgmc = xtyhService.getFgmcByYhm(yhm);
        DynamicDataSource.router(SDPT_FYDM);
        return result;
    }

    /**
     * 获取该工单派送员电话
     *
     * @param request
     * @param
     * @param
     */
    @RequestMapping(value = "/getRyxx.aj", method = RequestMethod.POST)
    @ResponseBody
    public Result getRyxx(HttpServletRequest request, Integer yysdbh) {
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdJbEntity pubYysdJbEntity = yysdService.selectByPrimaryKey(yysdbh);
        if (pubYysdJbEntity.getGdryxm() == null) {
            return new Result(false, "该工单尚未分配", null);
        }
        PubYysdRyxxEntity pubYysdRyxxEntity = gdRyxxService.findByYhdm(pubYysdJbEntity.getGdryxm());
        String dh = pubYysdRyxxEntity.getLxdh();
        return new Result(true, "成功", dh);
    }


    @RequestMapping("/uploadEmsmd.fg")
    @ResponseBody
    public Result uploadfilefg(@RequestParam("file") MultipartFile file, HttpServletRequest request)
            throws IllegalStateException, IOException {
        HttpSession session = request.getSession();
        Integer ajxh = (Integer) session.getAttribute("ajxh");
        String fybh = (String) session.getAttribute("fybh");
        FYEnum fyEnum = FYEnum.getFyByFybh(fybh);
        DynamicDataSource.router(fyEnum.getFydm());
        String serverUrl = dmbMapper.findXtcygjbUrlByGjmc("电子卷宗");
        if (serverUrl == null) {
            return new Result(false, "上传失败", null);
        }
        String root = Base64Util.encode("正卷".getBytes("utf8"));
        String folder = Base64Util.encode("EMS面单".getBytes(Charset.forName("UTF-8")));
        String filename = "ems面单" + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
        filename = Base64Util.encode(filename.getBytes(Charset.forName("UTF-8")));
//        serverUrl = "http://130.1.67.18:8081/";测试
        String targetUrl = serverUrl + "gatewayUploadMul.do";
        HttpPost post = new HttpPost(targetUrl);
        HttpEntity entity = MultipartEntityBuilder.create()
                .addTextBody("ajxh", String.valueOf(ajxh))
                .addTextBody("yhbh", String.valueOf(1))
                .addTextBody("wjmc", filename)
                .addTextBody("root", root)
                .addTextBody("folder", folder)
                .addPart("file", new ByteArrayBody(file.getBytes(), filename))
                .build();
        post.setEntity(entity);

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(2000)
                .setConnectTimeout(5000)
                .build();
        post.setConfig(requestConfig);

        String result;

        try (
                CloseableHttpClient httpClient = HttpClients.createDefault();
                CloseableHttpResponse httpResponse = httpClient.execute(post)
        ) {
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            result = EntityUtils.toString(httpResponse.getEntity());
            if (statusCode == HttpStatus.SC_OK) {
                return new Result(true, "上传成功", null);

            } else {
                return new Result(false, "上传失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "上传失败", null);
        }

    }


}
