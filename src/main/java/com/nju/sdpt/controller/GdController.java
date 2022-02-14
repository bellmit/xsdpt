package com.nju.sdpt.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nju.sdpt.constant.SdptConstants;
import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.entity.*;
import com.nju.sdpt.mapper.*;
import com.nju.sdpt.model.*;
import com.nju.sdpt.service.*;
import com.nju.sdpt.util.*;
import com.nju.sdpt.viewobject.*;
import exception.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.*;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * 工单系统
 *
 * @author zzy
 */
@Controller
@Slf4j
@ApiIgnore
public class GdController {

    @Resource
    PubDxtzInfoEntityMapper pubDxtzInfoEntityMapper;
    @Resource
    YysdService yysdService;
    @Resource
    DsrjbService dsrjbService;
    @Resource
    WsService wsService;
    @Resource
    CygjService cygjService;
    @Resource
    XtyhService xtyhService;
    @Resource
    DmService dmService;
    @Resource
    GdRyxxService gdRyxxService;
    @Resource
    SsdrService ssdrService;
    @Resource
    DmbMapper dmbMapper;
    @Resource
    private PubSsdrHmEntityMapper pubSsdrHmEntityMapper;
    @Resource
    private PubYysdSsdrdzEntityMapper pubYysdSsdrdzEntityMapper;
    @Resource
    private LogService logService;
    @Resource
    private SsdrQrxxService ssdrQrxxService;
    @Resource
    private PubBgscQzMapper bgscQzMapper;
    @Resource
    private GdcxService gdcxService;
    @Resource
    private KdtdMapper kdtdMapper;
    @Resource
    private SdxxService sdxxService;
    @Resource
    PubYysdSsdrdzEntityMapper pubYysdSsdrdzyMapper;
    @Resource
    PubZjsdInfoEntityMapper pubZjsdInfoEntityMapper;
    @Resource
    PubWebCallInfoEntityMapper pubWebCallInfoEntityMapper;
    @Resource
    PubLylqInfoEntityMapper pubLylqInfoEntityMapper;
    @Resource
    QtsscyrService qtsscyrService;
    @Resource
    EmsService emsService;
    @Resource
    CaxxService caxxService;
    @Resource
    RwwtService rwwtService;
    @Resource
    private LylqService lylqService;
    @Resource
    private NewLogService newLogService;
    @Resource
    private RwlzService rwlzService;




    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;


    /**
     * 登录
     */
    @PostMapping("/login.do")
    @ResponseBody
    public LoginModel login(HttpServletRequest request, String username, String password) {
        DynamicDataSource.router(SDPT_FYDM);
        HttpSession session = request.getSession();
        GdryCheckResult gdryCheckResult = gdRyxxService.checkUserLoginResult(username, password);
        LoginModel loginModel=new LoginModel();
        if (gdryCheckResult.isSuccess()) {
            PubYysdRyxxEntity pubYysdRyxxEntity = gdryCheckResult.getPubYysdRyxxEntity();
            // 把人员信息放入session
            UserContextModel userContextModel = new UserContextModel();
            userContextModel.setYhbh(pubYysdRyxxEntity.getYhbh());
            userContextModel.setYhmc(pubYysdRyxxEntity.getYhmc());
            userContextModel.setYhjs(pubYysdRyxxEntity.getYhjs());
            userContextModel.setYhdm(pubYysdRyxxEntity.getYhdm());
            userContextModel.setFybh(pubYysdRyxxEntity.getFybh());
            session.setAttribute("isSuccess","Y");
            session.setAttribute("userContext", userContextModel);
            session.setAttribute("yhm", pubYysdRyxxEntity.getYhdm());
//            session.setAttribute("mc",pubYysdRyxxEntity.getYhmc());
            newLogService.addNewModel(new NewLogModel(request.getRemoteAddr(), "sdpt", new Date(), userContextModel.getYhdm(), "", "工单登录", ""));
            if ("admin".equals(pubYysdRyxxEntity.getYhjs())) {
                loginModel.setNr("wbryIndex");
                loginModel.setYhmc(pubYysdRyxxEntity.getYhmc());
                loginModel.setFybh(pubYysdRyxxEntity.getFybh());
                // 外部管理员使用的页面
                return loginModel;
            }
            if ("user".equals(pubYysdRyxxEntity.getYhjs())) {
                loginModel.setNr("gdIndex");
                loginModel.setYhmc(pubYysdRyxxEntity.getYhmc());
                loginModel.setFybh(pubYysdRyxxEntity.getFybh());
                // 送达员使用的页面
                return loginModel;
            }
            if ("zjzy".equals(pubYysdRyxxEntity.getYhjs())) {
                loginModel.setNr("zjzyIndex");
                loginModel.setYhmc(pubYysdRyxxEntity.getYhmc());
                loginModel.setFybh(pubYysdRyxxEntity.getFybh());
                return loginModel;
            }
        }else{
            session.setAttribute("isSuccess","N");
        }
        return loginModel;
    }

    @RequestMapping(value = "/chromeDownload.do", method = RequestMethod.GET)
    public void chromeDownload(HttpServletResponse response, Integer version) throws IOException {
        String path = "E:\\chrome\\chrome_64.exe";
        File file = new File(path);
        String filename = file.getName();
        // 取得文件的后缀名。
        String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
        // 以流的形式下载文件。
        InputStream fis = new BufferedInputStream(new FileInputStream(path));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        // 清空response
        response.reset();
        // 设置response的Header
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
        response.addHeader("Content-Length", "" + file.length());
        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        toClient.write(buffer);
        toClient.flush();
        toClient.close();

    }

    @RequestMapping(value = "/tbsj.aj", method = RequestMethod.GET)
    @ResponseBody
    public String tbsj(String fybh, String ah) throws IOException {
        HttpClient client = new DefaultHttpClient();
        String url = null;
        if(fybh.equals("74")) {
            url = "http://130.1.1.105:8081/Zxjkgl-bhxqPG/updateOneAjxx.do";
        } else {
            url = "http://130.1.1.105:8080/Zxjkgl/updateOneAjxx.do";
        }
        HttpPost httpPost = new HttpPost(url);
        List<BasicNameValuePair> paramsList = new ArrayList<BasicNameValuePair>();
        paramsList.add(new BasicNameValuePair("fydm", FYEnum.getFYDMByFybh(fybh)));
        paramsList.add(new BasicNameValuePair("ah", ah));
        paramsList.add(new BasicNameValuePair("lx", "SA"));
        paramsList.add(new BasicNameValuePair("fw", "aj"));
        UrlEncodedFormEntity uefEntity = null;
        try {
            uefEntity = new UrlEncodedFormEntity(paramsList, "UTF-8");
//					uefEntity.setContentType("application/json");
            httpPost.setEntity(uefEntity);
//					httpPost.setHeader("contentType", "application/json");
            System.out.println("executing request:" + httpPost.getURI());
            CloseableHttpResponse response = (CloseableHttpResponse) client.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String str = EntityUtils.toString(entity, "GBK");
                    System.out.println("---------------" + response.getStatusLine());
                    System.out.println("Response content:" + str);
                    System.out.println("---------------");
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return"gd/yysd";
    }


//
//    /**
//     * 送达员使用的页面
//     *
//     * @param request request
//     * @return gdIndex.html
//     */
//    @RequestMapping(value = "/gdIndex")
//    public String newIndex(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        session.setAttribute("yhmc", "测试账号");
//        session.setAttribute("yhdm", "test");
//        return "gdIndex";
//    }


    /**
     * 获取工单列表
     *
     * @param sdWay 送达方式
     * @param yhdm  用户代码
     * @param cljg  处理结果
     */
    @RequestMapping(value = "/getGd.aj", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<GdVO> getGd(HttpServletRequest request, String sdWay, String yhdm, Boolean cljg) {
        DynamicDataSource.router(SDPT_FYDM);
        HttpSession session = request.getSession();
        Integer emsyysdbh = (Integer) session.getAttribute("emsyysdbh");
        String way = StringUtil.toUpperCase(sdWay);
        List<PubYysdJbEntity> pubYysdJbEntities = null;
        if (!cljg) {
            pubYysdJbEntities = yysdService.getGdWclByGdryxm(yhdm, way, emsyysdbh);

        } else {
            pubYysdJbEntities = yysdService.getGdYclByGdryxm(yhdm, way, emsyysdbh);
        }
        Map<Integer, List<PubYysdSsdrEntity>> listMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(pubYysdJbEntities)) {
            StringBuilder sql = new StringBuilder();
            for (PubYysdJbEntity pubYysdJbEntity : pubYysdJbEntities) {
                sql.append(pubYysdJbEntity.getYysdbh()).append(",");
            }
            String sqlstr = sql.toString();
            String yysdbhs = sqlstr.substring(0, sqlstr.length() - 1);
            listMap = yysdService.getSsdrByYysdbhs(yysdbhs);
        }

        ArrayList<GdVO> gdVOS = new ArrayList<GdVO>();
        for (PubYysdJbEntity pubYysdJbEntity : pubYysdJbEntities) {
            FYEnum fyEnum = FYEnum.getFyByFybh(pubYysdJbEntity.getFybh());
            GdVO gdVO = new GdVO();
            gdVO.setAh(pubYysdJbEntity.getAh());

            List<PubYysdSsdrEntity> pubYysdSsdrEntityList = listMap.get(pubYysdJbEntity.getYysdbh());
            if (null != pubYysdSsdrEntityList) {
                List<String> mcList = new ArrayList<>();
                for (PubYysdSsdrEntity ssdrModel : pubYysdSsdrEntityList) {
                    mcList.add(ssdrModel.getSsdrmc());
                }

                gdVO.setBsdr(StringUtil.splitJoint(mcList, ","));
            }
//            gdVO.setBsdr(pubYysdJbEntity.getDsrmc());
            gdVO.setYyr(pubYysdJbEntity.getYyrxm());
            String yysj = DateUtil.format(pubYysdJbEntity.getYysj(), "yyyy-MM-dd HH:mm");
            gdVO.setYysj(yysj);
            gdVO.setAjxh(pubYysdJbEntity.getAjxh());
            gdVO.setFybh(pubYysdJbEntity.getFybh());
            gdVO.setAjxz(pubYysdJbEntity.getAjxz());
            gdVO.setYysdbh(pubYysdJbEntity.getYysdbh());
//            gdVO.setSfzhm(pubYysdJbEntity.getSfzhm());
            gdVOS.add(gdVO);
        }
        return gdVOS;
    }

    @RequestMapping(value = "/uploadSdjg.aj", method = RequestMethod.POST)
    @ResponseBody
    public void uploadSdjg(HttpServletRequest request, String sdWay, Integer sdjg, Integer yysdbh) {
        DynamicDataSource.router(SDPT_FYDM);
        //送达成功
        PubYysdJbEntity pubYysdJbEntity = yysdService.selectByPrimaryKey(yysdbh);
        HttpSession session = request.getSession();
        String yhm = (String) session.getAttribute("yhm");
        String yhmc = gdRyxxService.findByYhdm(yhm).getYhmc();
        String content = SdptConstants.LOG_TYPE.getLogTypeByJc(sdWay).getContent();
        if (sdjg == 1) {
            yysdService.updateSdcgByKey(yysdbh, sdWay);
            content += "成功";
            logService.addLog(pubYysdJbEntity.getAjxh(), pubYysdJbEntity.getFybh(), "", SdptConstants.LOG_TYPE.getLogTypeByJc(sdWay).getTypeNum(), content, yhmc, yysdbh);
        }
        if (sdjg == 0) {
            yysdService.updateSdsbByKey(yysdbh, sdWay);
            content += "失败";
            logService.addLog(pubYysdJbEntity.getAjxh(), pubYysdJbEntity.getFybh(), "", SdptConstants.LOG_TYPE.getLogTypeByJc(sdWay).getTypeNum(), content, yhmc, yysdbh);
        }
    }

    /**
     * 上传送达结果
     *
     * @param sdjg   送达结果
     * @param yysdbh 工单号
     * @param bz     备注
     */
    @RequestMapping(value = "/uploadSdjgMain.aj", method = RequestMethod.POST)
    @ResponseBody
    public void uploadSdjgMain(HttpServletRequest request, Integer sdjg, Integer yysdbh, String bz) {
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdJbEntity pubYysdJbEntity = yysdService.selectByPrimaryKey(yysdbh);
        HttpSession session = request.getSession();
        String yhm = (String) session.getAttribute("yhm");
        String yhmc = gdRyxxService.findByYhdm(yhm).getYhmc();
        yysdService.updateBzByYysdbh(yysdbh, bz);
        //送达成功
        if (sdjg == 1) {
            yysdService.uploadSdjgMain(yysdbh, "Y");
            logService.addLog(pubYysdJbEntity.getAjxh(), pubYysdJbEntity.getFybh(), "", 3, "反馈送达完成", yhmc, yysdbh);

        }
        if (sdjg == 0) {
            yysdService.uploadSdjgMain(yysdbh, "N");
            logService.addLog(pubYysdJbEntity.getAjxh(), pubYysdJbEntity.getFybh(), "", 3, "反馈送达失败", yhmc, yysdbh);
        }
    }

    @RequestMapping(value = "/downloadFromDzjz.do", method = RequestMethod.POST)
    @ResponseBody
    public byte[] downloadfrondzjz(HttpServletRequest request, String wjid, String fybh) {
        DynamicDataSource.routerByFybh(fybh);
        String serverUrl = dmbMapper.findXtcygjbUrlByGjmc("电子卷宗");
        String url = serverUrl + "getFile/" + wjid;
//        url="http://130.23.0.102:8081/dzjzBhxq/getFile/C6ED5B41-DB6A-4366-B7E8-346DD9AC2FFA";
        byte[] wj = DownloadUtils.dowloadWebFile(url);
        return wj;
    }

    @RequestMapping(value = "/gatewayFindByMlmc.do", method = RequestMethod.POST)
    @ResponseBody
    public List<DataResult> dzjzcx(HttpServletRequest request, String ah, String mlmc, String fybh) throws UnsupportedEncodingException {
        CxsjResult cxsjResult = emsService.findByMlmc(ah,mlmc,fybh);
        List<DataResult> dataResults = new ArrayList<>();
        if(cxsjResult.getSuccess()) {
//            System.out.println(new String(Base64Util.getFromBASE64("W3sid2ppZCI6IkY0MjQ0RUIxLTc2MjItNDQ0Mi1BMjdGLTBEQjg0ODU5OEE0NSIsIndqc3NtbCI6IuawkeS6i+ijgeWumuebuOWFs+adkOaWmSIsIndqbWMiOiJpbWFnZTk4ODkuanBnIiwiaW5kZXgiOjF9LHsid2ppZCI6IjEyODlEOThCLTVDOTUtNDE4RC1CQUI3LTg2QTlFMzI5OTI2RiIsIndqc3NtbCI6IuawkeS6i+ijgeWumuebuOWFs+adkOaWmSIsIndqbWMiOiJpbWFnZTk4OTAuanBnIiwiaW5kZXgiOjJ9XQ=="),"UTF-8"));
//            System.out.println(new String(Base64Util.getFromBASE64(cxsjResult.getData()),"UTF-8"));
            dataResults = JSON.parseArray(new String(Base64Util.getFromBASE64(cxsjResult.getData()),"UTF-8"),DataResult.class);
        }
//        dataResults.add(new DataResult("67ACCADB-F757-4D73-BE67-38220157A7B6","委托人身份材料","陈倩代理律师执业证.jpg"));
//        dataResults.add(new DataResult("4B634772-B376-4117-946C-E5B5E8C97FA7","当事人身份证明","郝鹏飞当事人身份证明.jpg"));
        return dataResults;
    }

    /**
     * 检查送达结果
     *
     * @param yysdbh 工单号
     */
    @RequestMapping(value = "/checkSdjg.aj", method = RequestMethod.POST)
    @ResponseBody
    public Result checkSdjg(HttpServletRequest request, Integer yysdbh) {
        DynamicDataSource.router(SDPT_FYDM);
        List<String> nonResultWay = yysdService.checkSdjg(yysdbh);
        if (nonResultWay.size() == 0) {
            return Result.succeed;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (String way : nonResultWay) {
                stringBuilder.append(way).append(",");
            }
            String message = stringBuilder.substring(0, stringBuilder.length() - 1);
            message = "存在" + message + "记录未提交送达结果，是否继续?";
            return new Result(false, message, null);
        }
    }

    /**
     * 批量检查送达结果
     */
    @RequestMapping(value = "/checkSdjgList.aj", method = RequestMethod.POST)
    @ResponseBody
    public Result checkSdjg(HttpServletRequest request, Integer[] yysdbhList) {
        List<Integer> nonResultWayList = new LinkedList<>();
        for (Integer yysdbh : yysdbhList) {
            DynamicDataSource.router(SDPT_FYDM);
            List<String> nonResultWay = yysdService.checkSdjg(yysdbh);
            if (nonResultWay.size() > 0) {
                nonResultWayList.add(yysdbh);
            }
        }
        if (nonResultWayList.size() == 0) {
            return Result.succeed;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (Integer yysdbh : nonResultWayList) {
                stringBuilder.append(yysdbh).append(",");
            }
            String message = stringBuilder.substring(0, stringBuilder.length() - 1);
            message = "存在" + message + "号工单未提交送达结果，是否继续？";
            return new Result(false, message, null);
        }
    }

    /**
     * 批量工单流转
     */
    @RequestMapping(value = "/uploadRwlzList.aj", method = RequestMethod.POST)
    @ResponseBody
    public Result uploadRwlzList(HttpServletRequest request, RwlzVO rwlzVO) {
        log.error("sdpt操作"+request.getRemoteAddr()+":批量工单流转");
        DynamicDataSource.router(SDPT_FYDM);
        UserContextModel userContextModel = (UserContextModel) request.getSession().getAttribute("userContext");
        if (userContextModel != null) {
            Integer yhbh = Integer.parseInt(userContextModel.getYhbh() + "");
            rwlzVO.setCreater(yhbh);
            rwlzService.insert(rwlzVO);
            return Result.succeed;
        } else {
            return Result.failed;
        }
    }

    /**
     * 获取送达地址
     */
    @RequestMapping(value = "/getSdUrl.aj", method = RequestMethod.POST)
    @ResponseBody
    public Result getSdUrl(HttpServletRequest request, String sdWay, Integer yysdbh) {
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdJbEntity pubYysdJbEntity = yysdService.selectByPrimaryKey(yysdbh);
        int ajxh = pubYysdJbEntity.getAjxh();
        String fybh = pubYysdJbEntity.getFybh();
        String ajxz = pubYysdJbEntity.getAjxz();
        String url = "";
        if (StringUtil.equals(sdWay, "DHSD")) {
            String baseSdurl = "http://130.39.111.182:8080/znsdgj/ndlogin?";
            url = baseSdurl + "ajID=" + ajxh + "&nAjlb=" + ajxz + "&nFyid=" + fybh + "&loginId=sdzx";
        }
        if (StringUtil.equals(sdWay, "DZSD")) {
            NpForDzfyService dsfyService = new NpForDzfyService();
            NpForDzfyServicePortType dsfyInterface = dsfyService.getNpForDzfyServiceHttpPort();
            url = dsfyInterface.createDzsd(Integer.toString(ajxh), "sdzx", FYEnum.getFyByFybh(fybh).getFydm());
        }
        if (StringUtil.equals(sdWay, "EMSSD")) {
            DynamicDataSource.router(FYEnum.getFyByFybh(pubYysdJbEntity.getFybh()).getFydm());
            XtyhModel xtyhModel = xtyhService.getXtyhByYhm("sdzx");
            XtglCygjbEntity xtglCygjbEntity = cygjService.getCygjByName("EMS打印管理系统");
            String server = xtglCygjbEntity.getLj();
            String xt = "/emsdyxt/";
            String method = "emsgateway.do";
            String service = "sqkdd";
            String parameters = "?username=" + "sdzx" + "&password=" + xtyhModel.getYhkl() + "&service=" + service + "&param=ajxh_" + ajxh + ";isspxt_1";
            url = server + xt + method + parameters;
        }
        if (StringUtil.equals(sdWay, "GGSD")) {
            FYEnum fyEnum = FYEnum.getFyByFybh(pubYysdJbEntity.getFybh());
            DynamicDataSource.routerByFybh(pubYysdJbEntity.getFybh());
            XtyhModel xtyhModel = xtyhService.getXtyhByYhm("sdzx");
            DmbModel dmb = dmService.getDmByLbbhAndDmbh("USR_GGXT", "01");
            String doName = StringUtil.trim(dmb.getXgdm());
            url = doName += "?ajxh=" + ajxh + "&&fydm=" + fyEnum.getFydm() + "&&yhbh=" + xtyhModel.getYhbh();
        }
        return new Result(true, "成功", url);
    }

    /**
     * 获取工单详情
     */
    @RequestMapping(value = "/getGdxqxx.aj", method = RequestMethod.POST)
    @ResponseBody
    public Result getGdxqxx(HttpServletRequest request, Integer yysdbh) {
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdJbEntity pubYysdJbEntity = yysdService.selectByPrimaryKey(yysdbh);
        PubYysdWsEntity[] pubYysdWsEntities = yysdService.selectByYysdbh(yysdbh);
        for (PubYysdWsEntity pubYysdWsEntity : pubYysdWsEntities) {
            pubYysdWsEntity.setWsnr(null);//去掉文书内容
        }

        //查询工单当事人信息
        List<PubYysdSsdrModel> pubYysdSsdrModelList = ssdrService.findByYysdbh(yysdbh);
        if (null != pubYysdSsdrModelList && pubYysdSsdrModelList.size() > 0) {
            for (PubYysdSsdrModel ssdrModel : pubYysdSsdrModelList) {
                //查询当事人下号码信息
                QuerySdpPhoneVo querySdpPhoneVo = new QuerySdpPhoneVo();
                querySdpPhoneVo.setYysdBh(ssdrModel.getYysdbh());
                querySdpPhoneVo.setSsdrbh(ssdrModel.getSsdrbh());
                List<PubSsdrHmEntity> pubSsdrHmEntitys = pubSsdrHmEntityMapper.querySdpPhone(querySdpPhoneVo);
                ssdrModel.setPubSsdrHmEntityList(pubSsdrHmEntitys);
                //查询当事人下地址信息
                List<PubYysdSsdrdzEntity> ssdrdzEntityList = pubYysdSsdrdzEntityMapper.selectByyysdbhAndSsdrbh(ssdrModel.getYysdbh(), ssdrModel.getSsdrbh());
                ssdrModel.setPubYysdSsdrdzEntityList(ssdrdzEntityList);
            }

        }
        List<PubYysdSsdrQtsscyrEntity> pubYysdSsdrQtsscyrEntities = qtsscyrService.getpubYysdSsdrQtsscyrEntitiesByYysdbh(yysdbh);
        GdxqxxVO gdxqxxVO = new GdxqxxVO(pubYysdJbEntity, pubYysdWsEntities, pubYysdSsdrModelList);
        gdxqxxVO.setQtsscyr(pubYysdSsdrQtsscyrEntities);
        return new Result(true, "成功", gdxqxxVO);
    }


    /**
     * 查询缴款码缴费状态
     */
    @RequestMapping(value = "/getJkmStatus.aj", method = RequestMethod.POST)
    @ResponseBody
    public Result getJkmStatus(String fybh, String jkm) throws Exception {
        if (StringUtil.isBlank(jkm))
            return new Result(false, "该文书未绑定缴款码", null);
        DynamicDataSource.routerByFybh(fybh);
        PayCode payCode = new PayCode(fybh, jkm);
        String jsonObject = JSONObject.toJSONString(payCode);
//        String targetUrl = "http://"+dmbMapper.getDmByLbbhAndDmbh("系统缺省","新系统地址").getDmms().split("/")[0]+"/getJkmStatus.aj";
        String targetUrl = "http://130.39.110.238:18088/getJkmStatus.do";
//        String targetUrl = "http://localhost:18088/getJkmStatus.do";
        String result = "";
        try {
            result = HttpUtil.sendHttpPost(jsonObject, targetUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, "获取缴款码失败", null);
        }
        JSONObject returnMsg = JSON.parseObject(result);
        String message = returnMsg.getString("message");
        return new Result(true, message, null);

    }

    /**
     * 下载文书
     */
    @RequestMapping(value = "/downloadWs.do", method = RequestMethod.GET)
    @ResponseBody
    public void downloadWs(HttpServletRequest request, HttpServletResponse response, @RequestParam("yysdbh") Integer yysdbh, @RequestParam("bh") Integer bh) throws Exception {
        DynamicDataSource.router(SDPT_FYDM);
        //法综内文书名称统一使用案号+当事人名称+文书类别的拼接
        String fileName = "";
        PubYysdJbEntity pubYysdJbEntity = yysdService.selectByPrimaryKey(yysdbh);
        fileName += String.valueOf(pubYysdJbEntity.getYysdbh());
        PubYysdWsEntity ws = yysdService.selectByYysdbhAndBh(yysdbh, bh);
        String wsmc = ws.getWsmc();
        if (ws.getSsdrbh() != null) {
            PubYysdSsdrEntity pubYysdSsdrEntity = ssdrService.findByYysdbhAndSsdrbh(yysdbh, ws.getSsdrbh());
            fileName += "-当事人:" + pubYysdSsdrEntity.getSsdrmc();
            String regEx = "[\n`~!@#$%^&*()+=|{}';',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；”“’。， 、？]";
            fileName = fileName.replaceAll(regEx, "");//去掉当事人里的特殊字符
        }
        String ext = (wsmc == null || wsmc.lastIndexOf(".") < 0) ? ".pdf" : wsmc.substring(wsmc.lastIndexOf("."));
        fileName += "-" + ws.getWslb() + ext;
        if (StringUtil.equals(pubYysdJbEntity.getAjxz(), "8") && ws.getWsly() == 1 ) {
            fileName = ws.getWsmc();
            if(fileName == null || ws.getWsnr() == null) {
                String fybh = pubYysdJbEntity.getFybh();
                DynamicDataSource.routerByFybh(fybh);
                PubWsJbEntity pubWsJbEntity = wsService.getWsJbBykey(pubYysdJbEntity.getAjxh(), ws.getWslybh());
                downFile(pubWsJbEntity.getWswjm(), pubWsJbEntity.getWsnr(), response);
                return;
            }
            String path = new String(ws.getWsnr());
            if (path.contains("E:\\sdptwsnr")) {
                DownloadUtils.downloadFile(path, fileName, response, request);
                return;
            }
            downFile(fileName, ws.getWsnr(), response);
            return;
        }
        if (ws.getWsly() == 3 || ws.getWsly() == 5) {
            //自己上传的文件
            fileName = ws.getWsmc();
            String path = new String(ws.getWsnr());
            if (path.contains("E:\\sdptwsnr")) {
                DownloadUtils.downloadFile(path, fileName, response, request);
                return;
            } else {
                downFile(fileName, ws.getWsnr(), response);
            }

        } else {
            String fybh = pubYysdJbEntity.getFybh();
            //去目标法院拿文书
            DynamicDataSource.routerByFybh(fybh);
            if (ws.getWsly() == 1) {
                //wsStampLog
                PubWsJbEntity pubWsJbEntity=null;
                pubWsJbEntity = wsService.getWsJbBykey(pubYysdJbEntity.getAjxh(), ws.getWslybh());
                if(pubWsJbEntity==null) {
                    pubWsJbEntity = wsService.getWsJbBykey(ws.getAjxh(), ws.getWslybh());
                }
                PubWsStampLogEntity pubWsStampLogEntity = wsService.selectByAjxhAndWsjbbh(pubWsJbEntity.getAjxh(), pubWsJbEntity.getWsjbbh());
                PubWsStampLogEntity wsStampLogEntity = wsService.fetchStampFile(2, pubWsStampLogEntity.getStampGuid());//获取签章文件
                wsService.reSaveWs(yysdbh, ws.getBh(), wsStampLogEntity.getFile());
                downFile(fileName, wsStampLogEntity.getFile(), response);
            }
            if (ws.getWsly() == 2) {
                byte[] bytes = null;
                PubBgscQzEntityWithBLOBs bgscQzEntity=null;
                if (ws.getWsnr() == null) {
                     bgscQzEntity = wsService.getBgscQzByPrimary(pubYysdJbEntity.getAjxh(), ws.getWslybh(), ws.getQzbh());
                    if(bgscQzEntity==null){
                        bgscQzEntity = wsService.getBgscQzByPrimary(ws.getAjxh(), ws.getWslybh(), ws.getQzbh());
                    }
                    bytes = bgscQzEntity.getQzfile();
                    if (bgscQzEntity.getQzfile() == null) {
                        //取最后一次签章成功的文件
                        PubBgscQzEntityWithBLOBs pubBgscQzEntityWithBLOBsNew = bgscQzMapper.getByAjxhAndFileId(bgscQzEntity.getAjxh(), bgscQzEntity.getFileid());
                        bytes = wsService.fetchStampFile(pubBgscQzEntityWithBLOBsNew);//获取签章文件
                        DynamicDataSource.router(SDPT_FYDM);
                        wsService.reSaveWs(yysdbh, ws.getBh(), bytes);
                    } else {
                        fileName = bgscQzEntity.getQzfilename();
                    }
                } else {
                    String path = new String(ws.getWsnr());

                    if (path.contains("E:\\sdptwsnr")) {
                        DownloadUtils.downloadFile(path, fileName, response, request);
                        return;
                    } else {
                        bytes = ws.getWsnr();
                    }

                }
                downFile(fileName, bytes, response);
            }
        }
        return;
    }

    public static void downFile(String fileName, byte[] buffer, HttpServletResponse response) throws IOException {
        String filePath = "E:\\workspace\\";

        File file = new File(filePath + fileName);
        OutputStream output = new FileOutputStream(file);
        BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
        bufferedOutput.write(buffer);
        bufferedOutput.flush();
        bufferedOutput.close();
        FileInputStream fileInputStream = new FileInputStream(new File(filePath + fileName));
        byte[] bytes = new byte[fileInputStream.available()];
        fileInputStream.read(bytes);

        String saveName = URLEncoder.encode(fileName, "UTF-8");
        saveName = saveName.replaceAll("%28", "(");
        saveName = saveName.replaceAll("%29", ")");
        String header = "attachment;filename=" + saveName;
        response.setHeader("Content-Disposition", header);
        ServletOutputStream sout = response.getOutputStream();
        int length = buffer.length;
        sout.write(buffer, 0, length);
        sout.flush();
        sout.close();
    }



    @RequestMapping(value = "/uploadFile_Offline.aj", method = RequestMethod.POST)
    @ResponseBody
    public Result uploadFile_Offline(@RequestBody WsInfo[] wsInfos) {
        Result result = Result.failed;
        if (wsInfos == null) {
            return null;
        }
        try {
            String targetDir = "E:\\sdptwsnr\\" + DateFormatUtils.format(new Date(), "yyyy-MM-dd");
            File dirFile = FileUtils.getFile(targetDir);
            if (!dirFile.exists()) {
                FileUtils.forceMkdir(dirFile);
            }
            List<PubYysdWsEntity> entities = new ArrayList<>(wsInfos.length);
            for (WsInfo wsInfo : wsInfos) {
                PubYysdWsEntity wsEntity = wsInfo.getWsEntity();
                MultipartFile wsnr = BASE64DecodedMultipartFile.base64ToMultipart(wsInfo.getWsnr());
                if (wsnr != null) {
                    String wsmc = wsEntity.getWsmc();
                    String filepath = targetDir + "\\" + UUID.randomUUID() + wsmc.substring(wsmc.lastIndexOf("."));
                    FileUtils.copyInputStreamToFile(wsnr.getInputStream(), new File(filepath));
                    wsEntity.setWsnr(filepath.getBytes());
                }
                entities.add(wsEntity);
            }
            wsService.saveOrInsertYysdWsBatch(entities);
            result = Result.succeed;
        } catch (Exception e) {
            log.error("文书上传失败!");
        }
        return result;
    }


    /**
     * 获取要给法官发送反馈短信的短信信息
     */

    @RequestMapping(value = "/feedback.aj", method = RequestMethod.POST)
    @ResponseBody
    public DxtzVO feedback(@RequestParam("yysdbh") Integer yysdbh) {
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdJbEntity yysdJbEntity = yysdService.selectByPrimaryKey(yysdbh);
        String yyrxm = yysdJbEntity.getYyrxm();
        String yyrdh = yysdJbEntity.getSjhm();
        String sdyxm = yysdJbEntity.getGdryxm();
        String sdjg = yysdJbEntity.getSdjg();
        PubYysdRyxxEntity ryxxEntity = gdRyxxService.findByYhdm(sdyxm);
        String sdydh = ryxxEntity.getLxdh();
        DxtzVO dxtzVO = new DxtzVO();
        dxtzVO.setYysdbh(yysdJbEntity.getYysdbh());
        dxtzVO.setAh(yysdJbEntity.getAh());
        dxtzVO.setYyrxm(yyrxm);
        dxtzVO.setYyrdh(yyrdh);
        dxtzVO.setSdyxm(sdyxm);
        dxtzVO.setSdydh(sdydh);
        if (Objects.equals(sdjg, "Y")) {
            dxtzVO.setSdjg("送达成功");
        } else if (Objects.equals(sdjg, "N")) {
            dxtzVO.setSdjg("送达失败");
        } else {
            dxtzVO.setSdjg("");
        }
        return dxtzVO;

    }

    /**
     * 修改是否签署送达地址确认书(工单)
     */
    @RequestMapping(value = "/qsqrs_update_gd.aj", method = RequestMethod.POST)
    @ResponseBody
    public Result qsqrs_update_gd(@RequestBody Map data,HttpServletRequest request, @Param("yysdbh") Integer yysdbh, @Param("dsrbh")Integer dsrbh, @Param("sfqssddzqrs")Integer sfqssddzqrs) {
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdJbEntity jb = yysdService.selectByPrimaryKey((Integer)data.get("yysdbh"));
        PubYysdSsdrEntity ssdrEntity = ssdrService.selectByPrimaryKey(new PubYysdSsdrEntityKey((Integer)data.get("dsrbh"), (Integer)data.get("yysdbh")));
        ssdrEntity.setSfqssddzqrs((Integer)data.get("sfqssddzqrs"));
        ssdrService.updateByPrimaryKeySelective(ssdrEntity);
        HttpSession session = request.getSession();
        String yhm = (String) session.getAttribute("yhm");
        String yhmc = gdRyxxService.findByYhdm(yhm).getYhmc();
        logService.addLog(jb.getAjxh(), jb.getFybh(), ssdrEntity.getSsdrmc(), SdptConstants.LOG_TYPE.getLogTypeByJc("QSQRS").getTypeNum(), "", yhmc, (Integer) data.get("yysdbh"));
        return new Result(true, "成功", null);
    }

    /**
     * 修改是否签署送达地址确认书(工单)
     *
     * @param request
     * @param
     * @param
     */
    @RequestMapping(value = "/tydzsd_update_gd.aj", method = RequestMethod.POST)
    @ResponseBody
    public Result tydzsd_update_gd(@RequestBody Map data, HttpServletRequest request, Integer yysdbh, Integer dsrbh, Integer sftydzsd) {
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdJbEntity jb = yysdService.selectByPrimaryKey((Integer) data.get("yysdbh"));
        PubYysdSsdrEntity ssdrEntity = ssdrService.selectByPrimaryKey(new PubYysdSsdrEntityKey((Integer) data.get("dsrbh"), (Integer) data.get("yysdbh")));
        ssdrEntity.setSftydzsd((Integer) data.get("sftydzsd"));
        ssdrService.updateByPrimaryKeySelective(ssdrEntity);
        HttpSession session = request.getSession();
        String yhm = (String) session.getAttribute("yhm");
        String yhmc = gdRyxxService.findByYhdm(yhm).getYhmc();
        logService.addLog(jb.getAjxh(), jb.getFybh(), ssdrEntity.getSsdrmc(), SdptConstants.LOG_TYPE.getLogTypeByJc("TYDZSD").getTypeNum(), "", yhmc, (Integer) data.get("yysdbh"));
        return new Result(true, "成功", null);
    }


    /**
     * 全市工单查询
     * <p>
     * data(工单号,状态,受送达人,法院,预约人,时间,案号,案件名称)
     */
    @RequestMapping(value = "/gdcx.aj", method = RequestMethod.POST)
    @ResponseBody
    public List<YysdModel> qsgdcx(@RequestBody Map data, HttpServletRequest request) {
        DynamicDataSource.router(SDPT_FYDM);
        Integer yysdbh = null;
        if (!StringUtil.isBlank((String) data.get("yysdbh"))) {
            yysdbh = Integer.parseInt((String) data.get("yysdbh"));
        }
        Integer sdzt = null;
        if (!StringUtil.isBlank((String) data.get("sdzt"))) {
            sdzt = Integer.parseInt((String) data.get("sdzt"));
        }
        GdcxModel gdcxModel = new GdcxModel((String) data.get("fybh"), yysdbh, (String) data.get("ah"), (String) data.get("ajmc"),
                (String) data.get("ssdr"), (String) data.get("yyr"), sdzt, (String) data.get("startTime"), (String) data.get("endTime"), (String) data.get("sdr"));
        if (StringUtil.isBlank(gdcxModel.getFybh())) {
            HttpSession session = request.getSession();
            UserContextModel userContext = (UserContextModel) session.getAttribute("userContext");
            gdcxModel.setFybh(userContext.getFybh());
        }
        if (StringUtil.isNotBlank(gdcxModel.getSdr())) {
            List<PubYysdRyxxEntity> pubYysdRyxxEntity = gdRyxxService.findByYhmc(gdcxModel.getSdr());
            if (pubYysdRyxxEntity == null || pubYysdRyxxEntity.size() == 0) {
                gdcxModel.setSdr("未知");
            } else {
                gdcxModel.setSdr(pubYysdRyxxEntity.get(0).getYhdm());
            }
        }
//        List<YysdModel> last=new ArrayList<>();
//        last=gdcxService.getGdcxResult(gdcxModel);
        return gdcxService.getGdcxResult(gdcxModel);//送达状态:0-未送达,1-送达中,2-已送达
    }

    @RequestMapping(value = "/tssdgdcx.aj", method = RequestMethod.POST)
    @ResponseBody
    public List<YysdModel> tssdgdcx(@RequestBody Map data, HttpServletRequest request) {
        DynamicDataSource.router(SDPT_FYDM);
        Integer yysdbh = null;
        if (!StringUtil.isBlank((String) data.get("yysdbh"))) {
            yysdbh = Integer.parseInt((String) data.get("yysdbh"));
        }
        Integer sdzt = null;
        if (!StringUtil.isBlank((String) data.get("sdzt"))) {
            sdzt = Integer.parseInt((String) data.get("sdzt"));
        }
        GdcxModel gdcxModel = new GdcxModel((String) data.get("fybh"), yysdbh, (String) data.get("ah"), (String) data.get("ajmc"),
                (String) data.get("ssdr"), (String) data.get("yyr"), sdzt, (String) data.get("startTime"), (String) data.get("endTime"), (String) data.get("sdr"));
        if (StringUtil.isBlank(gdcxModel.getFybh())) {
            HttpSession session = request.getSession();
            UserContextModel userContext = (UserContextModel) session.getAttribute("userContext");
            gdcxModel.setFybh(userContext.getFybh());
        }
        if (StringUtil.isNotBlank(gdcxModel.getSdr())) {
            List<PubYysdRyxxEntity> pubYysdRyxxEntity = gdRyxxService.findByYhmc(gdcxModel.getSdr());
            if (pubYysdRyxxEntity == null || pubYysdRyxxEntity.size() == 0) {
                gdcxModel.setSdr("未知");
            } else {
                gdcxModel.setSdr(pubYysdRyxxEntity.get(0).getYhdm());
            }
        }
        return gdcxService.getTsyysdResult(gdcxModel);//送达状态:0-未送达,1-送达中,2-已送达
    }


    /**
     * 获取外部人员送达显示列表，暂时复制法官我的送达页面
     */
    @RequestMapping(value = "/getwbrysdInfo.do", method = RequestMethod.POST)
    @ResponseBody
    public WbrywdsdVO getwbrysdInfo(HttpServletRequest request, @RequestParam String wbryshow, @RequestParam(required = false) String queryType) {

        boolean queryWebCall = false, queryDxtz = false, queryZjsd = false, queryLylq = false, queryGg = false;
        if (Objects.isNull(queryType)) {
            queryWebCall = true;
            queryDxtz = true;
            queryZjsd = true;
            queryLylq = true;
            queryGg = true;
        }
        if (Objects.equals(queryType, "DXTZ")) {
            queryDxtz = true;
        } else if (Objects.equals(queryType, "DHSD")) {
            queryWebCall = true;
        } else if (Objects.equals(queryType, "LYLQ")) {
            queryLylq = true;
        } else if (Objects.equals(queryType, "ZJSD")) {
            queryZjsd = true;
        }

        DynamicDataSource.router(SDPT_FYDM);
        PubYysdJbEntity pubYysdJbEntity = yysdService.selectByPrimaryKey(Integer.parseInt(wbryshow));
        WbrywdsdVO wbrywdsdVO = new WbrywdsdVO();
        wbrywdsdVO.setAh(pubYysdJbEntity.getAh());
        wbrywdsdVO.setPubYysdJbEntity(pubYysdJbEntity);
        wbrywdsdVO.setLaay(wbrywdsdVO.getPubYysdJbEntity().getLaay());
//        List<EmssdModel> emssdModelList = sdxxService.getEmssdGdInfo(pubYysdJbEntity.getYysdbh(), pubYysdJbEntity.getFybh());
        //电子送达弃用
//        List<DzsdModel> dzsdModelList = sdxxService.getDzsdInfo(pubYysdJbEntity.getAjxh(), pubYysdJbEntity.getFybh());
        // 暂时在测试库，以后会在指定的法院
//        DynamicDataSource.router(SDPT_FYDM);
        List<PubWebCallInfoModel> pubWebCallInfoModelList = new ArrayList<>();
        if (queryWebCall) {
            pubWebCallInfoModelList = pubWebCallInfoEntityMapper.findByYysdbh(Integer.parseInt(wbryshow));
            for (PubWebCallInfoModel pubWebCallInfoModel : pubWebCallInfoModelList) {
                pubWebCallInfoModel.setWhTime(DateUtil.format(pubWebCallInfoModel.getCreatetime(), "yyyy-MM-dd HH:mm:ss"));
            }
        }
        List<DxtzListModel> dxtzListModels = new ArrayList<>();
        if (queryDxtz) {
            dxtzListModels = pubDxtzInfoEntityMapper.findByYysdbh(Integer.parseInt(wbryshow));
            for (DxtzListModel dxtzListModel : dxtzListModels) {
                dxtzListModel.setCreatetimeStr(DateUtil.format(dxtzListModel.getCreatetime(), "yyyy-MM-dd HH:mm:ss"));
            }
        }
        List<LylqModel> byYysdbh = new ArrayList<>();
        if (queryLylq) {
            byYysdbh = pubLylqInfoEntityMapper.findByYysdbh(Integer.parseInt(wbryshow));
            for (LylqModel lylqModel : byYysdbh) {
                lylqModel.setYylqsjStr((DateUtil.format(lylqModel.getYylqsj(), "yyyy-MM-dd HH:mm:ss")));
            }
        }
        List<ZjsdModel> zjsdModelList = new ArrayList<>();
        if (queryZjsd) {
            zjsdModelList = pubZjsdInfoEntityMapper.selectListByYysdbh(Integer.parseInt(wbryshow));
            for (ZjsdModel zjsdModel : zjsdModelList) {
                zjsdModel.setSmsjStr(DateUtil.format(zjsdModel.getSmsj(), "yyyy-MM-dd"));
            }
        }
        //公告送达弃用
//        List<GgsdModel> ggsdModelList = new ArrayList<>();
//        if(queryGg){
//            ggsdModelList = sdxxService.getGgsdInfo(pubYysdJbEntity.getAjxh(), pubYysdJbEntity.getFybh());
//        }

//        DynamicDataSource.router(SDPT_FYDM);
//        QuerySsdrListVo querySsdrListVo = new QuerySsdrListVo();
//        querySsdrListVo.setYysdbh(Integer.parseInt(wbryshow));
//        List<PubYysdSsdrModel> yysdSsdrModelList =ssdrService.querySsdrList(querySsdrListVo);
//        for(PubYysdSsdrModel pubYysdSsdrModel:yysdSsdrModelList){
//            Integer yysdbh = pubYysdSsdrModel.getYysdbh();
//            Integer ssdrbh = pubYysdSsdrModel.getSsdrbh();
//            List<PubYysdSsdrdzEntity> pubYysdSsdrdzEntities = pubYysdSsdrdzEntityMapper.selectByyysdbhAndSsdrbh(yysdbh,ssdrbh);
//            String dz = "";
//            for(int i = 0;i<pubYysdSsdrdzEntities.size();i++){
//                if(i!=pubYysdSsdrdzEntities.size()-1){
//                    dz+=pubYysdSsdrdzEntities.get(i).getDz()+",";
//                }else
//                    dz+=pubYysdSsdrdzEntities.get(i).getDz();
//            }
//            pubYysdSsdrModel.setSsdrdz(dz);
//            List<PubSsdrHmEntity> pubSsdrHmEntitys = pubSsdrHmEntityMapper.selectByYysdbhAndSsdrbh(yysdbh,ssdrbh);
//            String hm = "";
//            if(pubSsdrHmEntitys!=null){
//            for(int i = 0;i<pubSsdrHmEntitys.size();i++){
//                if(i!=pubYysdSsdrdzEntities.size()-1){
//                    hm+=pubSsdrHmEntitys.get(i).getShowTel()+",";
//                }else{
//                    hm+=pubSsdrHmEntitys.get(i).getShowTel();
//                 }
//            }}
//            pubYysdSsdrModel.setSsdrdh(hm);
//        }
//        wbrywdsdVO.setEmssdModel(emssdModelList);
//        wbrywdsdVO.setGgsdModel(ggsdModelList);
//        wbrywdsdVO.setDzsdModel(dzsdModelList);
        wbrywdsdVO.setPubWebCallInfoModels(pubWebCallInfoModelList);
        wbrywdsdVO.setDxtzListModels(dxtzListModels);
        wbrywdsdVO.setLylqModels(byYysdbh);
        wbrywdsdVO.setZjsdModels(zjsdModelList);
        return wbrywdsdVO;
    }


    /**
     * 获取ems记录
     */
    @RequestMapping(value = "/getGdEmssdInfos.do", method = RequestMethod.POST)
    @ResponseBody
    public List<EmssdModel> getGdEmssdInfos(HttpServletRequest request, @RequestParam Integer yysdbh, @RequestParam String fybh) {
        return sdxxService.getEmssdGdInfo(yysdbh, fybh);
    }

    @RequestMapping(value = "/getGdZjsdInfos.do", method = RequestMethod.POST)
    @ResponseBody
    public List<ZjsdModel> getGdZjsdInfos(HttpServletRequest request, @RequestParam Integer yysdbh) {
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdJbEntity pubYysdJbEntity = yysdService.selectByPrimaryKey(yysdbh);
        return sdxxService.getZjsdGdInfo(pubYysdJbEntity.getYysdbh());
    }


    /**
     * 获取受送达人列表
     */
    @RequestMapping(value = "/getSsdrInfos.do", method = RequestMethod.POST)
    @ResponseBody
    public List<SsdrVO> getSsdrInfos(HttpServletRequest request, @RequestParam Integer yysdbh) {

        DynamicDataSource.router(SDPT_FYDM);
        List<PubYysdSsdrModel> yysdSsdrModelList = ssdrService.findByYysdbh(yysdbh);
        PubYysdJbEntity yysdJbEntity = yysdService.selectByPrimaryKey(yysdbh);
        for(PubYysdSsdrModel yysdSsdrModel : yysdSsdrModelList) {
            if(null != yysdSsdrModel.getSdjg() && (yysdSsdrModel.getSdjg() == 1 || yysdSsdrModel.getSdjg() == 2)) {
                continue;
            }
            ssdrService.loadSsdrSdjg(yysdbh, yysdSsdrModel.getSsdrbh(), yysdJbEntity.getFybh());
        }
        yysdSsdrModelList = ssdrService.findByYysdbh(yysdbh);
        List<SsdrVO> ssdrVOS = new ArrayList<>();
        int authority = 1;//1有操作权限,0没有
        UserContextModel userContextModel = (UserContextModel) request.getSession().getAttribute("userContext");
//        authority = StringUtil.equals(userContextModel.getYhjs(), "fg") ? 0 : authority;//法官无法在此页面送达
//        PubYysdJbEntity pubYysdJbEntity = yysdService.selectByPrimaryKey(yysdbh);
        authority = StringUtil.isNotBlank(yysdJbEntity.getSdjg()) ? 0 : authority;//有送达结果的无法送达
        authority = StringUtil.isBlank(yysdJbEntity.getGdryxm()) ? 0 : authority;//未分配的无法送达
        for (PubYysdSsdrModel pubYysdSsdrModel : yysdSsdrModelList) {
//            ssdrService.loadSsdrSdjg(pubYysdSsdrModel.getYysdbh(),pubYysdSsdrModel.getSsdrbh());
            SsdrVO ssdrVO = new SsdrVO();
            ssdrVO.setSsdrbh(pubYysdSsdrModel.getSsdrbh());
            ssdrVO.setSsdrmc(pubYysdSsdrModel.getSsdrmc());
            ssdrVO.setSsdw(pubYysdSsdrModel.getSsdrssdw());
            ssdrVO.setSfzhm(pubYysdSsdrModel.getSfzhm());
            ssdrVO.setYysdbh(pubYysdSsdrModel.getYysdbh());
            ssdrVO.setRepairstatus(pubYysdSsdrModel.getRepairstatus());
            ssdrVO.setSdzt(ssdrService.readSdzt(pubYysdSsdrModel.getSdjg()));
            if (pubYysdSsdrModel.getSfqssddzqrs() == null) {
                ssdrVO.setSfqsqrs("暂无");
            } else {
                ssdrVO.setSfqsqrs(pubYysdSsdrModel.getSfqssddzqrs() == 1 ? "是" : "否");
            }
            if (pubYysdSsdrModel.getSftydzsd() == null) {
                ssdrVO.setSftydzsd("暂无");
            } else {
                ssdrVO.setSftydzsd(pubYysdSsdrModel.getSftydzsd() == 1 ? "是" : "否");
            }
            ssdrVO.setAuthority(authority);
            ssdrVOS.add(ssdrVO);
        }
        ssdrVOS.sort(new Comparator<SsdrVO>() {
            @Override
            public int compare(SsdrVO o1, SsdrVO o2) {
                return o1.getSsdrbh() - o2.getSsdrbh();
            }
        });
        return ssdrVOS;
    }


    /**
     * ems查看送达回执 测试地址:http://localhost:8088/sdLogin.aj?fybh=64&ajxh=169804&yhm=super
     */
    @RequestMapping(value = "/ems_sdhz_show.do", method = RequestMethod.GET)
    @ResponseBody
    public void getEmssSdhz(HttpServletRequest request, HttpServletResponse response, Integer kdid, String fybh) throws IOException {
        DynamicDataSource.routerByFybh(fybh);
        PubKdtdEntity pubKdtdEntity = sdxxService.getKdtdByKdid(kdid);
        byte[] wsfile = pubKdtdEntity.getKdhz();
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(wsfile);
        outputStream.flush();
        outputStream.close();
    }

    @RequestMapping(value = "/ems_sdhz_delete.do", method = RequestMethod.POST)
    @ResponseBody
    public String deleteEmsSdhz(HttpServletRequest request, HttpServletResponse response, Integer kdid, String fybh) {
        DynamicDataSource.routerByFybh(fybh);
        try {
            sdxxService.deleteKdhzByKdid(kdid);
        } catch (Exception e) {
            return "false";
        }
        return "true";
    }

    @RequestMapping(value = "/getYysdInfos.do", method = RequestMethod.POST)
    @ResponseBody
    public List<YysdModel> getYysdInfos(HttpServletRequest request, Integer sdjg) {
        DynamicDataSource.router(SDPT_FYDM);
        String yhmc = (String) request.getSession().getAttribute("yhm");
        return yysdService.findByGdryxmAndSdjg(yhmc, sdjg);
    }

    /**
     * 获取当前用户工单
     *
     * @param request
     * @param sdjg
     * @param start
     * @param end
     * @return
     */
    @RequestMapping(value = "/getYysdInfosByTime.do", method = RequestMethod.POST)
    @ResponseBody
    public List<YysdModel> getYysdInfos(HttpServletRequest request, Integer sdjg, @RequestParam String start, @RequestParam String end) {
        DynamicDataSource.router(SDPT_FYDM);
        String yhmc = (String) request.getSession().getAttribute("yhm");
        return yysdService.findByGdryxmAndSdjg(yhmc, sdjg, start, end);
    }

    @RequestMapping(value = "/download_sd_excel.do", method = RequestMethod.GET)
    @ResponseBody
    public void getYysdInfosAndDownload(HttpServletRequest request, HttpServletResponse response, @RequestParam Integer sdjg, @RequestParam String start, @RequestParam String end) {
        DynamicDataSource.router(SDPT_FYDM);
        String yhmc = (String) request.getSession().getAttribute("yhm");
        List<YysdModel> yysdModels = yysdService.findByGdryxmAndSdjg(yhmc, sdjg, start, end);
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
                String jg = "暂无送达结果";
                if (null != yysdModel.getSdsj()) {
                    if ("Y".equals(yysdModel.getSdjg())) {
                        jg = "送达成功";
                    }
                    if ("N".equals(yysdModel.getSdjg())) {
                        jg = "送达失败";
                    }
                    if ("X".equals(yysdModel.getSdjg())) {
                        jg = "撤回";
                    }
                }

                sdxxData.setSdjg(jg);
                sdxxData.setDsrsdjg(ssdrService.readSdzt(pubYysdSsdrModel.getSdjg()));
                if (null != yysdModel.getYysj()) {
                    sdxxData.setYysj(yysdModel.getYysj());
                }
                if (null !=yysdModel.getSdsj()) {
                    sdxxData.setFksj(yysdModel.getSdsj());
                }
                if (null !=yysdModel.getYysj() && null != yysdModel.getSdsj()) {
                    SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
            downFileExcel(fileName, buffer, response);
            File f = new File(fileName);
            if(f.exists()) {
                f.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void downFileExcel(String fileName, byte[] buffer, HttpServletResponse response) throws IOException {
        File file = new File(fileName);
        OutputStream output = new FileOutputStream(file);
        BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
        bufferedOutput.write(buffer);
        bufferedOutput.flush();
        bufferedOutput.close();
        FileInputStream fileInputStream = new FileInputStream(new File(fileName));
        byte[] bytes = new byte[fileInputStream.available()];
        fileInputStream.read(bytes);
        String saveName = URLEncoder.encode(fileName, "UTF-8");
        saveName = saveName.replaceAll("%28", "(");
        saveName = saveName.replaceAll("%29", ")");
        String header = "attachment;filename=" + saveName;
        response.setHeader("Content-Disposition", header);
        ServletOutputStream sout = response.getOutputStream();
        int length = buffer.length;
        sout.write(buffer, 0, length);
        sout.flush();
        sout.close();
        fileInputStream.close();
        file.delete();
    }


    /**
     * 上传分送达方式的送达结果
     */
    @RequestMapping(value = "/upload_sdjg.aj", method = RequestMethod.POST)
    @ResponseBody
    public Result uploadSdjg(HttpServletRequest request, String type, Integer yysdbh, Integer id, Integer sdjg, String wtfs, Integer wtclrbh) {
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdRyxxEntity wtr = gdRyxxService.getYhBySession(request.getSession());
        PubYysdJbEntity jb = yysdService.selectByPrimaryKey(yysdbh);
        String fybh = jb.getFybh();
        Integer ssdrbh = -1;
        if (StringUtil.equals(type, "DXSD")) {
            PubDxtzInfoEntity pubDxtzInfoEntity = pubDxtzInfoEntityMapper.selectByPrimaryKey(id);
            ssdrbh = pubDxtzInfoEntity.getSsdrbh();
            pubDxtzInfoEntityMapper.updateSdjgByDxtzId(id, sdjg);
            if(sdjg == 1){
                pubDxtzInfoEntityMapper.updateShortUrlStatus1(yysdbh,ssdrbh);
            }
        }
        if (StringUtil.equals(type, "EMS")) {
            DynamicDataSource.routerByFybh(jb.getFybh());
            PubKdtdEntity pubKdtdEntity = kdtdMapper.findByKdid(id);
            ssdrbh = pubKdtdEntity.getDsrbh();
            String[] results = {"","成功","失败","未寄出"};
            String jg = results[sdjg];
            kdtdMapper.uploadSdjgByKdid(id, jg, new Date());
            DynamicDataSource.router(SDPT_FYDM);
            if(jg.equals("成功")) {
                pubDxtzInfoEntityMapper.updateShortUrlStatus1(yysdbh,ssdrbh);
            }
        }
        if (StringUtil.equals(type, "LYLQ")) {
            PubLylqInfoEntity pubLylqInfoEntity = pubLylqInfoEntityMapper.selectByPrimaryKey(id);
            ssdrbh = pubLylqInfoEntity.getSsdrbh();
            LylqUploadSdhzVo lylqUploadSdhzVo = new LylqUploadSdhzVo();
            lylqUploadSdhzVo.setLylqId(id);
            lylqUploadSdhzVo.setSdjg(sdjg);
            lylqService.uploadSdhz(lylqUploadSdhzVo);
            if(sdjg == 1){
                pubDxtzInfoEntityMapper.updateShortUrlStatus1(yysdbh,ssdrbh);
            }
        }
        ssdrService.loadSsdrSdjg(yysdbh, ssdrbh, fybh);
        if(wtclrbh!=null&&wtfs!=null){
            rwwtService.addNewRwwt(yysdbh, ssdrbh, wtfs, wtr.getYhbh(), wtclrbh);
        }
        return new Result(null, "上传成功", null);
    }


    /**
     * 上传分送达方式的送达结果
     */
    @RequestMapping(value = "/upload_sdjg_pl.aj", method = RequestMethod.POST)
    @ResponseBody
    public Result uploadSdjgpl(HttpServletRequest request, String type, String yysdbh, String id, Integer sdjg, String wtfs, Integer wtclrbh) {
        DynamicDataSource.router(SDPT_FYDM);
        String yysdbhcl=yysdbh.substring(1);
        String idcl=id.substring(1);
         String[] yysdbhs =yysdbhcl.split(",");
         String[] ids =idcl.split(",");
       for(int i=0;i<ids.length;i++){
           uploadSdjg(request,type,Integer.valueOf(yysdbhs[i]),Integer.valueOf(ids[i]),sdjg,wtfs,wtclrbh);
       }
        return new Result(null, "上传成功", null);
    }

    /**
     * 撤销送达结果
     */
    @RequestMapping(value = "/delete_sdjg.aj", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteSdjg(Integer yysdbh, Integer kdid) {
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdJbEntity jb = yysdService.selectByPrimaryKey(yysdbh);
        String fybh = jb.getFybh();
        DynamicDataSource.routerByFybh(fybh);
        PubKdtdEntity pubKdtdEntity = kdtdMapper.findByKdid(kdid);
        Integer ssdrbh = pubKdtdEntity.getDsrbh();
        String oldSdjg = pubKdtdEntity.getSdjg();
        Date oldDate = pubKdtdEntity.getSdrq();
        boolean stage1;
        try {
            stage1 = kdtdMapper.clearSdjgByKdid(kdid);
        }catch (Exception e){
            stage1 = false;
            e.printStackTrace();
        }
        try {
            if(stage1){
                ssdrService.loadSsdrSdjg(yysdbh,ssdrbh, fybh);
            }else {
                emsService.updateEmsSdjg(kdid,fybh,oldSdjg,oldDate);
                return new Result(false,"撤销失败",null);
            }
        }catch (Exception e){
            emsService.updateEmsSdjg(kdid,fybh,oldSdjg,oldDate);
            return new Result(false,"更新送达状态失败",null);
        }
        return new Result(true, "撤销成功", null);
    }



    /**
     * 修改送达状态
     * @param request
     * @param sdzt
     * @param yysdbh
     * @return
     */
    @RequestMapping(value = "/editSdzt.aj", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getGd(HttpServletRequest request, String sdzt, Integer yysdbh) {
        log.error("sdpt操作"+request.getRemoteAddr()+":操作了修改状态,修改工单号为："+yysdbh);
        DynamicDataSource.router(SDPT_FYDM);
        Boolean aBoolean = gdcxService.editSdzt(sdzt, yysdbh);
        return ResultUtils.wrapSuccess(aBoolean);
    }

    /**
     * 修改外呼信息
     * @param request
     * @param webcallid
     * @param callState
     * @param electronsend
     * @param remarks
     * @return
     */
    @RequestMapping(value = "/editCallData.aj", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getGd(HttpServletRequest request, Integer webcallid, String callState, String electronsend, String remarks, String phone, String address, Integer yysdbh, Integer dsrbh) {
        DynamicDataSource.router(SDPT_FYDM);
        Boolean aBoolean = gdcxService.editCallData(webcallid, callState,electronsend,remarks,phone,address);
        PubYysdSsdrEntity ssdrEntity = ssdrService.selectByPrimaryKey(new PubYysdSsdrEntityKey( dsrbh, yysdbh));
        if(! electronsend.equals("")) {
            ssdrEntity.setSftydzsd(electronsend.equals("1") ? 0 : 1);
            ssdrService.updateByPrimaryKeySelective(ssdrEntity);
        }
        return ResultUtils.wrapSuccess(aBoolean);
}

    @RequestMapping(value = "/deleteMsg.aj", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity deleteMsg(HttpServletRequest request, Integer dxtzid) {
        DynamicDataSource.router(SDPT_FYDM);
        int i = pubDxtzInfoEntityMapper.deleteByPrimaryKey(dxtzid);
        return ResultUtils.wrapSuccess(i);
    }

    /**
     * 删除录入号码
     * @param request
     * @param bh
     * @return
     */
    @RequestMapping(value = "/deletePhone.aj", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity deletePhone(HttpServletRequest request, Integer bh) {
        DynamicDataSource.router(SDPT_FYDM);
        int i = pubSsdrHmEntityMapper.deleteByPrimaryKey(bh);
        return ResultUtils.wrapSuccess(i);
    }
    /**
     * 删除录入地址
     * @param request
     * @param bh
     * @return
     */
    @RequestMapping(value = "/deleteAddress.aj", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity deleteAddress(HttpServletRequest request, Integer bh) {
        DynamicDataSource.router(SDPT_FYDM);
        int i = pubYysdSsdrdzyMapper.deleteByPrimaryKey(bh);
        return ResultUtils.wrapSuccess(i);
    }

    /**
     * 删除来院领取记录
     * @param request
     * @param lylqid
     * @return
     */
    @RequestMapping(value = "/deleteLylq.aj", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity deleteLylq(HttpServletRequest request, Integer lylqid) {
        DynamicDataSource.router(SDPT_FYDM);
        int i = pubLylqInfoEntityMapper.deleteByPrimaryKey(lylqid);
        return ResultUtils.wrapSuccess(i);
    }

    @RequestMapping(value = "/editLylqData.aj", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity editLylqData(HttpServletRequest request, Integer lylqid, Integer lylqsSate) {
        DynamicDataSource.router(SDPT_FYDM);
        int i = 0;
        PubLylqInfoEntity pubLylqInfoEntity = pubLylqInfoEntityMapper.selectByPrimaryKey(lylqid);
        if (null!=pubLylqInfoEntity){
            pubLylqInfoEntity.setLqstate(lylqsSate);
            i = pubLylqInfoEntityMapper.updateByPrimaryKey(pubLylqInfoEntity);
        }

        return ResultUtils.wrapSuccess(i);
    }

    @RequestMapping(value = "/editSdztData.aj", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity editSdztData(HttpServletRequest request, Integer yysdbh,Integer ssdrbh, Integer sdjg) {
        DynamicDataSource.router(SDPT_FYDM);
        int i = 0;
        PubYysdSsdrEntity pubYysdSsdrEntity = ssdrService.findByYysdbhAndSsdrbh(yysdbh, ssdrbh);
        if (pubYysdSsdrEntity!=null){
            pubYysdSsdrEntity.setSdjg(sdjg);
            if (Objects.equals(sdjg,4)){
                pubYysdSsdrEntity.setSdjg(null);
            }
            i = ssdrService.updateByPrimaryKey(pubYysdSsdrEntity);
        }

        return ResultUtils.wrapSuccess(i);
    }
    /**
     * 批量文书签章确认
     */
    @RequestMapping(value = "/plwsqzqr.aj", method = RequestMethod.GET)
    @ResponseBody
    public Result plwsqzqr(HttpServletRequest request, HttpServletResponse response, @RequestParam("yysdbh") Integer yysdbh) throws Exception {
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdWsEntity[] yysdWsEntities = wsService.selectByYysdbh(yysdbh);
        for (PubYysdWsEntity yysdWsEntity : yysdWsEntities) {
            if (yysdWsEntity.getWsly() < 3) {
                if (yysdWsEntity.getWsnr() == null) {
                    String ssdrmc = "";
                    PubYysdSsdrEntity byYysdbhAndSsdrbh = ssdrService.findByYysdbhAndSsdrbh(yysdbh, yysdWsEntity.getSsdrbh());
                    if(byYysdbhAndSsdrbh != null) {
                        ssdrmc = byYysdbhAndSsdrbh.getSsdrmc();
                    }
                    return new Result(false, ssdrmc + "的" + yysdWsEntity.getWslb() + "尚未签章，请先单独下载", null);
                }
            }
        }
        return Result.succeed;
    }

    /**
     * 批量下载文书
     * http://localhost:8088/plxzws.do?yysdbh=74647
     */
    @RequestMapping(value = "/plxzws.do", method = RequestMethod.GET)
    @ResponseBody
    public void plxzws(HttpServletRequest request, HttpServletResponse response, @RequestParam("yysdbh") Integer yysdbh, @RequestParam("ssdrbh") Integer ssdrbh) throws Exception {
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdWsEntity[] yysdWsEntities = wsService.selectByYysdbh(yysdbh);
        List<PubYysdSsdrModel> ssdrModels = ssdrService.findByYysdbh(yysdbh);
        HashMap<Integer,String> ssdrMap = new HashMap<>();
        for (PubYysdSsdrModel ssdrModel:ssdrModels){
            ssdrMap.put(ssdrModel.getSsdrbh(),ssdrModel.getSsdrmc());
        }
        List<String> paths = new LinkedList<>();
        List<String> childName = new LinkedList<>();
        int count = 1;
        for (int i = 0 ; i < yysdWsEntities.length ; i ++){
            if(yysdWsEntities[i].getSsdrbh() != ssdrbh) {
                continue;
            }
            if(yysdWsEntities[i].getWsly()!=4){
                paths.add(new String(yysdWsEntities[i].getWsnr()));
                if(yysdWsEntities[i].getWsly()<3) {
                    String wsmc = yysdWsEntities[i].getWsmc();
                    String ext = (wsmc==null|| wsmc.lastIndexOf(".")<0)?".pdf":wsmc.substring(wsmc.lastIndexOf("."));
                    childName.add((count ++) + "._"+yysdbh+"_" + ssdrMap.get(yysdWsEntities[i].getSsdrbh()) + "_" + yysdWsEntities[i].getWslb()+ext);
                }else {
                    childName.add((count ++) + "._"+yysdbh+"_" + ssdrMap.get(yysdWsEntities[i].getSsdrbh()) + "_"+ yysdWsEntities[i].getWsmc());
                }
            }
        }
        String fileName = yysdbh+"_"+ssdrMap.get(ssdrbh) + ".zip";
//        for(int i = 0 ; i < paths.size(); ++ i) {
//
//            DownloadUtils.downloadFile(paths.get(i),childName.get(i),response,request);
//        }
        DownloadUtils.downloadZip(paths,childName,fileName,response,request);
    }

    /**
     * 批量下载文书
     * http://localhost:8088/plxzws.do?yysdbh=74647
     */
    @RequestMapping(value = "/xzAllws.do", method = RequestMethod.GET)
    @ResponseBody
    public void xzAllws(HttpServletRequest request, HttpServletResponse response, @RequestParam("yysdbh") Integer yysdbh, @RequestParam("ssdrbh") Integer ssdrbh) throws Exception {
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdWsEntity[] yysdWsEntities = wsService.selectByYysdbh(yysdbh);
        List<PubYysdSsdrModel> ssdrModels = ssdrService.findByYysdbh(yysdbh);
        HashMap<Integer,String> ssdrMap = new HashMap<>();
        for (PubYysdSsdrModel ssdrModel:ssdrModels){
            ssdrMap.put(ssdrModel.getSsdrbh(),ssdrModel.getSsdrmc());
        }
        List<String> paths = new LinkedList<>();
        List<String> childName = new LinkedList<>();
        for (int i = 0 ; i < yysdWsEntities.length ; i ++){
            if(yysdWsEntities[i].getWsly()<4){
                paths.add(new String(yysdWsEntities[i].getWsnr()));
                if(yysdWsEntities[i].getWsly()<3) {
                    String wsmc = yysdWsEntities[i].getWsmc();
                    String ext = (wsmc==null|| wsmc.lastIndexOf(".")<0)?".pdf":wsmc.substring(wsmc.lastIndexOf("."));
                    childName.add((i+1) + "._"+yysdbh+"_" + ssdrMap.get(yysdWsEntities[i].getSsdrbh()) + "_" + yysdWsEntities[i].getWslb()+ext);
                }else {
                    childName.add((i+1) + "._"+yysdbh+"_" + ssdrMap.get(yysdWsEntities[i].getSsdrbh()) + "_"+ yysdWsEntities[i].getWsmc());
                }
            }
        }
        String fileName = yysdbh+ ".zip";
//        for(int i = 0 ; i < paths.size(); ++ i) {
//
//            DownloadUtils.downloadFile(paths.get(i),childName.get(i),response,request);
//        }
        DownloadUtils.downloadZip(paths,childName,fileName,response,request);
    }


    /**
     * 批量下载文书
     * http://localhost:8088/plxzws.do?yysdbh=74647
     */
    @RequestMapping(value = "/look_dsr_ws.aj", method = RequestMethod.POST)
    @ResponseBody
    public Result lookDsrWs(HttpServletRequest request, HttpServletResponse response, @RequestParam("yysdbh") Integer yysdbh, @RequestParam("ssdrbh") Integer ssdrbh) throws Exception{
        DynamicDataSource.routerToSdpt();
        List<String> wslbList = wsService.selectWslbByYysdbhAndSsdrbh(yysdbh,ssdrbh);
        return new Result(true,"",wslbList);
    }

}
