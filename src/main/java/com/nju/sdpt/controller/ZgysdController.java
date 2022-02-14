package com.nju.sdpt.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.entity.*;
import com.nju.sdpt.mapper.PubYysdJbEntityMapper;
import com.nju.sdpt.mapper.PubYysdRyxxEntityMapper;
import com.nju.sdpt.mapper.PubYysdSsdrEntityMapper;
import com.nju.sdpt.mapper.PubYysdWsEntityMapper;
import com.nju.sdpt.model.AjjbxxModel;
import com.nju.sdpt.model.FYEnum;
import com.nju.sdpt.model.XtyhModel;
import com.nju.sdpt.model.ZgysdModel;
import com.nju.sdpt.model.zgysd.*;
import com.nju.sdpt.service.*;
import com.nju.sdpt.service.Impl.DxtzServiceImpl;
import com.nju.sdpt.util.AESUtil;
import com.nju.sdpt.util.Base64Util;
import com.nju.sdpt.util.HttpUtil;
import com.nju.sdpt.util.StringUtil;
import com.nju.sdpt.viewobject.BSConventer;
import com.nju.sdpt.viewobject.DsrjbVO;
import com.nju.sdpt.viewobject.Result;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 最高院送达
 */
@RestController
@RequestMapping("/dzsd")
@Api(value = "最高院送达相关")
@Slf4j
public class ZgysdController {


    @Autowired
    AjjbxxService ajjbxxService;
    @Autowired
    SdSddjService sdSddjService;
    @Autowired
    DmService dmService;
    @Autowired
    LaayService laayService;
    @Autowired
    SpryService spryService;
    @Autowired
    XtyhService xtyhService;
    @Autowired
    DsrjbService dsrjbService;
    @Autowired
    QtsscyrService qtsscyrService;
    @Autowired
    YysdService yysdService;
    @Autowired
    ZgysdService zgysdService;
    @Autowired
    PubYysdJbEntityMapper pubYysdJbEntityMapper;
    @Autowired
    PubYysdRyxxEntityMapper pubYysdRyxxEntityMapper;
    @Autowired
    PubYysdWsEntityMapper pubYysdWsEntityMapper;
    @Autowired
    PubYysdSsdrEntityMapper pubYysdSsdrEntityMapper;
    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;

    static String ADDRESS="http://localhost:9080/zgydwfy";

    public String convertToNewCzrid(String czrid_old, String fybh) {
        return fybh + "_" + czrid_old;
    }

    public String convertToOldCzrid(String czrid_new) {
        return czrid_new.split("_")[1];
    }

    /**
     * 微法院送达
     */
    @PostMapping("/wfysd")
    public Result wfysd(@RequestBody Map data,HttpServletRequest request){
        HttpSession session=request.getSession();
//        Integer yysdbh = Integer.valueOf(session.getAttribute("yysdbh").toString());
        Integer yysdbh = Integer.valueOf(data.get("yysdbh").toString());
        Integer ssdrbh = Integer.valueOf(data.get("ssdrbh").toString());
        String yhm=data.get("fsrmc").toString();
//        String wses=session.getAttribute("wses").toString();
        String sddz=data.get("sddz").toString();
        String lxdh=data.get("lxdh").toString();
//        String[] ws=wses.split(",");

        DynamicDataSource.router(SDPT_FYDM);
        PubYysdJbEntity pubYysdJbEntity=pubYysdJbEntityMapper.selectByPrimaryKey(yysdbh);
        Integer sdybh=pubYysdRyxxEntityMapper.findByYhdm(yhm).getYhbh();
        if(pubYysdJbEntity.getAjxh()==-1){
            return new Result(false,"串案不能推送微法院送达",null);
        }
        PubYysdRyxxEntity pubYysdRyxxEntity=pubYysdRyxxEntityMapper.selectByPrimaryKey(sdybh);
        PostWfyModel postWfyModel=new PostWfyModel();
        postWfyModel.setBmdh(1);
        postWfyModel.setYysdbh(yysdbh);
        postWfyModel.setSsdrbh(ssdrbh);
        postWfyModel.setWsbh(1);
        postWfyModel.setAjxh(pubYysdJbEntity.getAjxh());
        postWfyModel.setFybh(pubYysdJbEntity.getFybh());
        postWfyModel.setAh(pubYysdJbEntity.getAh());
        postWfyModel.setAy(pubYysdJbEntity.getAjmc());
        postWfyModel.setFsrid(sdybh.toString());
        postWfyModel.setFsrxm(pubYysdRyxxEntity.getYhmc());

        List<Wsxx> wsxxList=new ArrayList<>();
        List<PubYysdWsEntity> pubYysdWsEntities=pubYysdWsEntityMapper.findByYysdbhAndSsdrbh(yysdbh,ssdrbh);

        for(PubYysdWsEntity wsinfo:pubYysdWsEntities){
            PubYysdWsEntityKey pubYysdWsEntityKey=new PubYysdWsEntityKey();
            pubYysdWsEntityKey.setBh(wsinfo.getBh());
            pubYysdWsEntityKey.setYysdbh(yysdbh);
            PubYysdWsEntity pubYysdWsEntity=pubYysdWsEntityMapper.selectByPrimaryKey(pubYysdWsEntityKey);

            Wsxx wsxx=new Wsxx();
            wsxx.setSdwsmc(pubYysdWsEntity.getWslb());
            wsxx.setSdwsid(wsinfo.getBh().toString());
            wsxx.setSdwsgs("pdf");
            wsxx.setSdwslxmc(pubYysdWsEntity.getWslb());
            wsxxList.add(wsxx);
        }
        postWfyModel.setWsxx(wsxxList);

        List<Ssdrxx> ssdrxxList=new ArrayList<>();

        PubYysdSsdrEntityKey pubYysdSsdrEntityKey=new PubYysdSsdrEntityKey();
        pubYysdSsdrEntityKey.setSsdrbh(ssdrbh);
        pubYysdSsdrEntityKey.setYysdbh(yysdbh);
        PubYysdSsdrEntity pubYysdSsdrEntity=pubYysdSsdrEntityMapper.selectByPrimaryKey(pubYysdSsdrEntityKey);
        Ssdrxx ssdrxx=new Ssdrxx();
        ssdrxx.setXm(pubYysdSsdrEntity.getSsdrmc());
        ssdrxx.setLxdh(lxdh);
        ssdrxx.setZjhm(pubYysdSsdrEntity.getSfzhm());
        ssdrxx.setSddz(sddz);
        ssdrxxList.add(ssdrxx);

        postWfyModel.setSsdrxx(ssdrxxList);

        try{
            String response= HttpUtil.sendHttpPost(JSONObject.toJSONString(postWfyModel),ADDRESS);
//            String response="{code:200}";
            Map map=(Map)JSON.parse(response);
            String code=map.get("code").toString();
            if("200".equals(code)){
                return new Result(true,null,"微法院发送成功");
            }
        }catch (Exception e){
            log.error("请求失败");
        }
        return new Result(false,"请求失败","请求失败");
    }

    /**
     * 最高院送达跳转
     *
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/zgysdLogin")
    public Result zgysdLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer yysdbh = Integer.valueOf(request.getParameter("yysdbh"));
        DynamicDataSource.router(SDPT_FYDM);
        HttpSession session = request.getSession();
        String yhmc = (String) session.getAttribute("yhm");
        String yhm = yysdService.getYhmc(yhmc);
        PubYysdJbEntity pubYysdJbEntity = yysdService.selectByPrimaryKey(yysdbh);
        String url = "http://130.39.117.109/zuigaofa-api-code/nei-html/index.html";
        URIBuilder uriBuilder = new URIBuilder(url);
        uriBuilder.addParameter("czrname", yhm);
        URIBuilder url1 = generateZgysdUrl(pubYysdJbEntity, uriBuilder);
//        System.out.println(url1.build().toString());
        return new Result(true, "", url1.build().toString());
    }

    @PostMapping("/zgysdLoginforFz")
    public Result zgysdLoginforFz(@RequestBody ZgysdModel zgysdModel, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String czrname=request.getParameter("czrname");
//        String fybh=request.getParameter("fybh");
//        String rybh=request.getParameter("rybh");
//        String bmmc=request.getParameter("bmmc");
//        String sjhm=request.getParameter("sjhm");
        String czrname=zgysdModel.getCzrname();
        String fybh=zgysdModel.getFybh();
        String rybh=zgysdModel.getRybh();
        String bmmc=zgysdModel.getBmmc();
        String sjhm=zgysdModel.getSjhm();
        String url = "http://130.39.117.109/zuigaofa-api-code/nei-html/index.html";
        URIBuilder uriBuilder = new URIBuilder(url);
        String uuid = "d3cd2ea1-b8e7-4e05-a64d-dcd578219bb0";
        String key = "xinshiyun19tj%&*";
        String token = new Token(uuid).generate(key);
        String ajid = zgysdService.insertForFz(Integer.valueOf(fybh), Integer.valueOf(zgysdModel.getAjid()));
//        logger.error("参数 ssbm:"+bmmc +"ssfy:"+fybh +"lxdh:"+sjhm +"ajid:"+ajid +"token:"+token);
        uriBuilder.addParameter("czrid",
                convertToNewCzrid(String.valueOf(rybh), fybh))
                .addParameter("ssbm", bmmc)
                .addParameter("ssfy", fybh)
                .addParameter("lxdh", sjhm)
                .addParameter("ajid", ajid)
                .addParameter("token", token)
                .addParameter("kysd", "1")
                .addParameter("czrname", czrname);
        return new Result(true, "", uriBuilder.build().toString());
    }

    private URIBuilder generateZgysdUrl(PubYysdJbEntity pubYysdJbEntity, URIBuilder uriBuilder) throws Exception {
        String uuid = "d3cd2ea1-b8e7-4e05-a64d-dcd578219bb0";
        String key = "xinshiyun19tj%&*";
        DynamicDataSource.routerByFybh(pubYysdJbEntity.getFybh());
        XtyhModel xtyhModel = xtyhService.getXtyhByYhmc(pubYysdJbEntity.getYyrxm());
        long yhbh = xtyhModel.getYhbh();
        String bmmc = pubYysdJbEntity.getBmmc();
        String fybh = pubYysdJbEntity.getFybh();
        String sjhm = pubYysdJbEntity.getSjhm();
        DynamicDataSource.router(SDPT_FYDM);
        String ajid = zgysdService.insert(pubYysdJbEntity);
        String token = new Token(uuid).generate(key);
        uriBuilder.addParameter("czrid",
                convertToNewCzrid(String.valueOf(yhbh), fybh))
                .addParameter("ssbm", bmmc)
                .addParameter("ssfy", fybh)
                .addParameter("lxdh", sjhm)
                .addParameter("ajid", ajid)
                .addParameter("token", token)
                .addParameter("kysd", "1");
        return uriBuilder;
    }


    class Token {
        private long timestamp;
        private String uuid;

        public Token(String uuid) {
            this.uuid = uuid;
            this.timestamp = System.currentTimeMillis();
        }

        public long getTimestamp() {
            return timestamp;
        }


        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }

        public String generate(String key) throws Exception {
            return Base64Util.encode(AESUtil.encrypt(toString(), key.getBytes()));
        }
    }


    /**
     * 权限检验
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/check/*")
    public void check(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("新视云正在验证的操作人ID为：" + request.getParameter("czrid"));
        System.out.println("操作人身份验证通过");
        List<String> ajList = new ArrayList<>();
        ajList.add(request.getParameter("ajid"));

        RequestModel requestModel = new RequestModel();
        requestModel.setCzrid(request.getParameter("czrid"));
        requestModel.setAjid(ajList);
        requestModel.setToken(request.getParameter("token"));

        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode("1001");
        responseModel.setMsg("验证通过");
        String res = JSONObject.toJSONString(responseModel);
//            response.setStatus(1001);
        response.setCharacterEncoding("GBK");
        PrintWriter out = response.getWriter();
        out.append(res);
        out.close();

    }

    /**
     * 案件信息获取
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/caseshow/*")
    @ResponseBody
    public CaseList caseshow(HttpServletRequest request, HttpServletResponse response) {
        String ajxx = request.getParameter("ajidlist");
        try {
            return ajxxConvert(ajxx);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public CaseList ajxxConvert(String ajxx) throws Exception {

        AjList ajList = JSON.parseObject(ajxx, AjList.class);
        List<String> list = ajList.getAjidlist();
        Map<String, List<String>> map = new HashMap<>();
        List<String> tmpList = new ArrayList<>();
        for (String ajid : list) {
            DynamicDataSource.router(SDPT_FYDM);
            PubYysdJbEntity pubYysdJbEntity = zgysdService.getYysdByAjid(ajid);
            String fybh = pubYysdJbEntity.getFybh();
            String fydm = FYEnum.getFyByFybh(fybh).getFydm();
            Integer ajxh = pubYysdJbEntity.getAjxh();
            tmpList.add(ajxh.toString());
            //System.out.println(tmpList.size());
            map.put(fydm, tmpList);
//            tmpList.clear();
        }
        Set<String> keys = map.keySet();
        List<CaseItem> ajxxList = new ArrayList<>();
        for (String key : keys) {
            List<String> ajxhList = map.get(key);
            if (ajxhList != null) {
                System.out.println("正在转换的案件列表长度：" + ajxhList.size());
            } else {
                System.out.println("里面没数据");
            }
//            System.out.println("法院代码："+key);
            List<CaseItem> result = convertAjxx(key, ajxhList);
            for (int i = 0; i < result.size(); i++) {
                result.get(i).getJbxx().setAjid(list.get(i));
            }
            ajxxList.addAll(result);
        }
//        JSONObject jsonObject=new JSONObject(true);
        CaseList caseList = new CaseList();
        caseList.setCaselist(ajxxList);
//        String str = JSON.toJSONString(caseList, SerializerFeature.SortField);
        System.out.println("案件信息成功返回，接口调用成功");
        return caseList;
    }

    private List<CaseItem> convertAjxx(String fydm, List<String> ajxhList) throws Exception {
        List<CaseItem> ajxxList = new ArrayList<>();
        for (String ajxh : ajxhList) {
            System.out.println("正在转换的ajxh:" + ajxh);
//            Ajxx ajxx = new Ajxx();
//            ajxx.setAjxh(ajxh);
            CaseItem caseItem = convertAjxx(fydm, ajxh);
            ajxxList.add(caseItem);
        }
        return ajxxList;
    }

    private CaseItem convertAjxx(String fydm, String ajxh) throws Exception {
        DynamicDataSource.router(fydm);
        CaseItem caseItem = new CaseItem();
        caseItem.setJbxx(convertJbxx(fydm, ajxh));
        caseItem.setSpzz(convertSpzzxx(fydm, ajxh));
        caseItem.setDsrlist(convertDsrList(fydm, ajxh));
        caseItem.setWsxxlist(null);
        return caseItem;
    }

    private AjxxJbxx convertJbxx(String fydm, String ajxh) {

        AjjbxxModel ajjbxxModel = ajjbxxService.getAjjbxxByAjxh(Integer.parseInt(ajxh));
        AjxxJbxx jbxx = new AjxxJbxx();
        jbxx.setAh(ajjbxxModel.getAh());
        jbxx.setAjid(BSConventer.convertToAJBS(Long.parseLong(ajxh), FYEnum.getFybhByFydm(fydm), fydm));
        jbxx.setAjlx(dmService.getDmByLbbhAndDmbh("FBS0021-97", ajjbxxModel.getAjxz()).getDmms());
        String fybh = FYEnum.getFybhByFydm(fydm);
        jbxx.setAy(laayService.getLaayByAjxh(Integer.parseInt(ajxh), fybh));
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        jbxx.setLarq(sm.format(ajjbxxModel.getLarq()));
        jbxx.setCbbm(dmService.getDmByLbbhAndDmbh("USR206-99", ajjbxxModel.getBaspt()).getDmms());
        jbxx.setJzdz(FYEnum.getFyByFydm(fydm).getName());
        jbxx.setFycode(FYEnum.getFybhByFydm(fydm));
        jbxx.setFydz(dmService.getDmByLbbhAndDmbh("系统缺省", "本院地址").getDmms());
        jbxx.setAjzt("在审");//不知道填充什么
        jbxx.setAuditinformation(convertAuditinformation(fydm, ajxh));
        jbxx.setAjsltzs_dir("");//后续修改
        return jbxx;
    }


    //填充当事人信息
    private List<AjxxDsrItem> convertDsrList(String fydm, String ajxh) {
        List<DsrJbEntity> dsrJbDOS = dsrjbService.getDsrjblistByAjxh(Integer.parseInt(ajxh));
        List<DsrjbVO> dsrjbVOS = new ArrayList<>();
        for (DsrJbEntity dsrJbDO : dsrJbDOS) {
            DsrjbVO dsrjbVO = dsrjbService.dsrEntityToDsrVO(dsrJbDO);
            dsrjbVOS.add(dsrjbVO);
        }
        List<AjxxDsrItem> dsrItemList = new ArrayList<>();
        for (DsrjbVO dsrjbVO : dsrjbVOS) {
            AjxxDsrItem dsrItem = new AjxxDsrItem(dsrjbVO);
            List<AjxxDlrxx> ajxxDlrxxList = new LinkedList<>();
            List<PubQtsscyrEntity> pubQtsscyrEntities = qtsscyrService.getQtsscyr(Integer.parseInt(ajxh), dsrjbVO.getDsrbh());
            for (PubQtsscyrEntity pubQtsscyrEntity : pubQtsscyrEntities) {
                ajxxDlrxxList.add(new AjxxDlrxx(pubQtsscyrEntity));
            }
            dsrItem.setDlrlist(ajxxDlrxxList);
            dsrItemList.add(dsrItem);
        }
        return dsrItemList;
    }


    //填充审判组织信息
    private AjxxSpzz convertSpzzxx(String fydm, String ajxh) {

        AjxxSpzz ajxxSpzz = new AjxxSpzz();
//        DataSourceRouter.routerTo(fydm);
        String cbr = spryService.getCbrByAjxh(Integer.parseInt(ajxh));
        if (StringUtil.isNotBlank(cbr)) {
            ajxxSpzz.setCbr(cbr);
            XtyhModel cbrYh = xtyhService.getXtyhByYhmc(cbr);
            ajxxSpzz.setCbrdh(cbrYh.getPhone());
        }

        String sjy = spryService.getSjyByAjxh(Integer.parseInt(ajxh), fydm);
        if (StringUtil.isNotBlank(sjy)) {
            ajxxSpzz.setSjy(sjy);
            XtyhModel sjyYh = xtyhService.getXtyhByYhmc(sjy);
            ajxxSpzz.setSjy(sjyYh.getPhone());
        }
        return ajxxSpzz;
    }

    //生成审核人信息
    private Auditinformation convertAuditinformation(String fydm, String ajxh) {
        DynamicDataSource.router(fydm);
        Auditinformation auditinformation = new Auditinformation();
        auditinformation.setAuditstatus("1001");
        String auditor = spryService.getAuditorByAjxh(Integer.parseInt(ajxh));
        auditinformation.setAuditor(auditor);
        XtyhModel xtyhModel = xtyhService.getXtyhByYhmc(auditor);
        auditinformation.setAuditorlev(xtyhModel.getYhsf());
        Auditmsg auditmsg = new Auditmsg();
//        auditmsg.setIp(OperatorIpMap.getIp(Integer.parseInt(ajxh),fydm));//不知道填啥
        auditmsg.setIp("130.1.67.27");
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        auditmsg.setAdtime(sm.format(new Date()));
        auditmsg.setFileurl("");//后续添加
        auditinformation.setAuditmsg(auditmsg);
        return auditinformation;
//        Auditinformation auditinformation = new Auditinformation();
//        auditinformation.setAuditstatus("1001");
//        auditinformation.setAuditor("33");
//        auditinformation.setAuditorlev("科长");
//        Auditmsg auditmsg = new Auditmsg();
//        auditmsg.setIp("130.1.67.27");
//        Date date = new Date();
//        auditmsg.setAdtime("2020-03-20");
//        auditmsg.setFileurl("http://130.1.67.27:26666/dzsd//download/123.pdf");
//        auditinformation.setAuditmsg(auditmsg);
//        return auditinformation;
    }


    /**
     * 最高院送达回执
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @PostMapping("/zgysdhz")
    @ResponseBody
    public ResponseModel zgysdcx(HttpServletRequest request, HttpServletResponse response, @RequestBody JsonRootBean jsonRootBean) {
        try {

            Integer ajid = Integer.parseInt(jsonRootBean.getAjid());
            List<Dsrlist> dsrlists = jsonRootBean.getDsrlist();
            for (Dsrlist dsrlist : dsrlists) {
                for (Yjsd yjsd : dsrlist.getYjsd()) {
                    PubZgysdInfoEntity pubZgysdInfoEntity = new PubZgysdInfoEntity(yjsd);
                    pubZgysdInfoEntity.setHzurl(dsrlist.getFileurl());
                    pubZgysdInfoEntity.setLogbh(ajid);
                    zgysdService.insert(pubZgysdInfoEntity);
                }
            }
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode("1001");
            responseModel.setMsg("成功");
            return responseModel;

        } catch (Exception e) {
            e.printStackTrace();
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode("1002");
            responseModel.setMsg("失败");
            return responseModel;
        }

    }

}
