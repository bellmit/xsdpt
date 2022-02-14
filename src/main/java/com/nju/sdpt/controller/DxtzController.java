package com.nju.sdpt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nju.sdpt.constant.SdptConstants;
import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.entity.*;
import com.nju.sdpt.mapper.*;
import com.nju.sdpt.model.*;
import com.nju.sdpt.service.*;
import com.nju.sdpt.util.DateUtil;
import com.nju.sdpt.util.DownloadUtils;
import com.nju.sdpt.viewobject.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 短信通知相关业务 控制层
 */
@Controller
@RequestMapping("/dxtz")
public class DxtzController {

    @Autowired
    private SsdrService ssdrService;
    @Autowired
    private WsService wsService;
    @Autowired
    private PubYysdJbEntityMapper pubYysdJbEntityMapper;
    @Autowired
    private DxtzService dxtzService;
    @Autowired
    private DxmbService dxmbService;
    @Autowired
    private PubDbrwInfoEntityMapper dbrwInfoMapper;
    @Autowired
    LogService logService;
    @Autowired
    YysdService yysdService;
    @Autowired
    PubDxmbInfoEntityMapper pubDxmbInfoEntityMapper;
    @Autowired
    XtyhService xtyhService;
    @Autowired
    AjjbxxService ajjbxxService;
    @Autowired
    DsrjbService dsrjbService;
    @Autowired
    PubZybInfoEntityMapper pubZybInfoEntityMapper;
    @Autowired
    GdRyxxService gdRyxxService;
    @Autowired
    private PubDxtzDao pubDxtzDao;
    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;

    public static final String SHORTMSGDIR="E:\\Q_ftp\\ShortMsg";
    /**
     * 短信通知 - 编辑送达结果
     * @param request
     * @return
     */
    @RequestMapping(value = "/edit_sdjg.zf",method = RequestMethod.POST)
    @ResponseBody
    public RetObj editSdjg(HttpServletRequest request, @RequestBody DxtzEditSdjgVo dxtzEditSdjgVo){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        HttpSession session = request.getSession();

        dxtzService.editSdjg(dxtzEditSdjgVo);
        RetObj obj = new RetObj();
        obj.setCode("1");
        obj.setMessage("succcess");
        obj.setSuccess("true");
        return obj;
    }
    /**
     * 短信通知 - 加载短信通知列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/load_list.zf",method = RequestMethod.POST)
    @ResponseBody
    public List<DxtzListModel> loadList(HttpServletRequest request, @RequestBody DxtzLoadListVo loadListVo){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        HttpSession session = request.getSession();
        Integer yhbh = gdRyxxService.getYhbhBySession(session);
        loadListVo.setSdybh(yhbh);
        //查询具体数据
        List<DxtzListModel> result = dxtzService.loadList(loadListVo);
        return result;
    }
    @RequestMapping(value = "/load_listByTime.zf",method = RequestMethod.POST)
    @ResponseBody
    public List<DxtzListModel> loadList(HttpServletRequest request, @RequestBody DxtzLoadListVo loadListVo,@RequestParam String start, @RequestParam String end){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        HttpSession session = request.getSession();
        Integer yhbh = gdRyxxService.getYhbhBySession(session);
        loadListVo.setSdybh(yhbh);
        //查询具体数据
        List<DxtzListModel> result = dxtzService.loadList(loadListVo,start,end);
        return result;
    }

    /**
     * 法官-短信通知 - 加载短信通知列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/fg_load_list.zf",method = RequestMethod.POST)
    @ResponseBody
    public  List<DxtzMwdxVO>  fgloadList(HttpServletRequest request,Integer status){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        HttpSession session = request.getSession();
        String yhm;
        String fybh = (String)session.getAttribute("fybh");
        //查询具体数据
        List<PubMwdxSendEntity> result = dxtzService.fgLoadList(gdRyxxService.getYhdm(request),fybh,status);
        List<DxtzMwdxVO> dxtzMwdxVOS = new ArrayList<DxtzMwdxVO>();
        DynamicDataSource.router(FYEnum.getFyByFybh(fybh).getFydm());
        for(PubMwdxSendEntity pubMwdxSendEntity: result){
            AjjbxxModel ajjbxxModel = ajjbxxService.getAjjbxxByAjxh(pubMwdxSendEntity.getAjxh());
            String ah = ajjbxxModel.getAh();
            DsrJbEntity dsrJbEntity = dsrjbService.getDsrByAjxhAndDsrbh(pubMwdxSendEntity.getAjxh(),pubMwdxSendEntity.getSsdrbh());
            String dsrmc= dsrJbEntity.getDsrjc();
            String creattime = DateUtil.format(pubMwdxSendEntity.getCratetime(),DateUtil.noSecondFormat);
            DxtzMwdxVO dxtzMwdxVO = new DxtzMwdxVO(ah,dsrmc,pubMwdxSendEntity.getSendphone(),creattime,pubMwdxSendEntity.getMsgcontent(),pubMwdxSendEntity.getSendstatus(),pubMwdxSendEntity.getFwzt(),pubMwdxSendEntity.getCusid());
            dxtzMwdxVOS.add(dxtzMwdxVO);
        }
        return dxtzMwdxVOS;
    }

    /**
     * 短信送达
     * @param request
     * @param sendShortMsgVo 短信VO
     * @return
     */
    @RequestMapping(value = "/send_short_msg.zf",method = RequestMethod.POST)
    @ResponseBody
    public RetObj sendShortMsg(HttpServletRequest request, @RequestBody SendShortMsgVo sendShortMsgVo){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        if(null != sendShortMsgVo.getId()){
            dbrwInfoMapper.updatestatusById(sendShortMsgVo.getId());
        }
        String yhm = gdRyxxService.getYhdm(request);
        //查询具体数据
        Boolean result = dxtzService.sendShortMsg(sendShortMsgVo,yhm);

        RetObj obj = new RetObj();
        obj.setCode("1");
        obj.setMessage("succcess");
        obj.setSuccess("true");
        return obj;
    }

    /**
     * 短信下发时 - 加载短信模板
     * @param request
     * @return
     */
    @RequestMapping(value = "/load_template_list.zf",method = RequestMethod.POST)
    @ResponseBody
    public List<PubDxmbInfoModel> loadTemplateList(HttpServletRequest request,String fybh){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        List<PubDxmbInfoModel> result = dxmbService.loadTemplateList(fybh);
        return result;
    }
    /**
     * 短信模板列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/get_template_list.zf",method = RequestMethod.POST)
    @ResponseBody
    public List<PubDxmbInfoModel> getTemplateList(HttpServletRequest request){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        UserContextModel userContextModel = (UserContextModel) request.getSession().getAttribute("userContext");
        List<PubDxmbInfoModel> result = new ArrayList<>();
        if (Objects.equals(userContextModel.getFybh(),"-1")){
            result = dxmbService.selectAllDxmbList();
        }else {
            result = dxmbService.selectDxmbList(userContextModel.getFybh());
        }
        return result;
    }

    @RequestMapping(value = "/get_paramjson.zf",method = RequestMethod.POST)
    @ResponseBody
    public List<Paramjson> getParamjson(HttpServletRequest request,Integer ssdrbh,Integer yysdbh){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        List<getParamjson>result= pubDxmbInfoEntityMapper.get_paramjson(ssdrbh,yysdbh);
        List<Paramjson>res=new ArrayList<>();
        List<Paramjson> re=new ArrayList<>();
        if(result==null){
            return res;
        }
        int count=0;
        int s1=0;
        int s2=0;
        for(int j=0;j<result.size();++j) {
            List<String>list=new ArrayList<>();
            for (int i = 0; i <result.get(j).getParamjson().length(); ++i) {
                if (result.get(j).getParamjson().charAt(i) == '"') ++count;
                if (count == 7) {
                    s1 = i;
                    ++count;
                }
                if (count == 9) {
                    count = 0;
                    s2 = i;
                    String s = result.get(j).getParamjson().substring(s1 + 1, s2);
                    list.add(s);
                }
            }
            re.add(new Paramjson(result.get(j).getTemplateid(),list));
        }
        return re;
    }

    @RequestMapping(value = "/query_sdp_phone.zf",method = RequestMethod.POST)
    @ResponseBody
    public List<PubSsdrHmEntityModel> querySdpPhone(HttpServletRequest request, @RequestBody QuerySdpPhoneVo querySdpPhoneVo){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        //查询具体数据
        List<PubSsdrHmEntityModel> result = dxtzService.querySdpPhone(querySdpPhoneVo);
        return result;
    }

    //发送明文短信
    @RequestMapping(value = "/send_plaintext",method = RequestMethod.POST)
    @ResponseBody
    public RetObj sendPlaintext(HttpServletRequest request, @RequestBody MwdxSendVo mwdxSendVo){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        //查询具体数据
        Boolean aBoolean = dxtzService.sendPlaintext(mwdxSendVo, gdRyxxService.getYhdm(request));
        PubYysdJbEntity jb = new PubYysdJbEntity();
        if(mwdxSendVo.getYysdbh()!=null){
            jb =  yysdService.selectByPrimaryKey(mwdxSendVo.getYysdbh());
        }
        int logtype = -1;
        String creater = "";
        if(Objects.equals(mwdxSendVo.getSendtype(), SdptConstants.MWDX_SEND_TYPE.FGCD)){//法官催单
            logtype = 4;
            creater = jb.getYyrxm();
            jb.setCdsj(new Date());
            yysdService.updateByPrimaryKeySelective(jb);
        }
        if(Objects.equals(mwdxSendVo.getSendtype(), SdptConstants.MWDX_SEND_TYPE.SDRYFK)){//送达人员反馈
            logtype = 5;
            creater = jb.getGdryxm();
        }

        if(!Objects.equals(mwdxSendVo.getSendtype(), SdptConstants.MWDX_SEND_TYPE.FGDXTZ)){//法官在上一个方法里记录日志
        logService.addLog(jb.getAjxh(),jb.getFybh(),"",logtype,"",creater,mwdxSendVo.getYysdbh());
        }
        RetObj obj = new RetObj();
        obj.setCode("1");
        obj.setMessage("success");
        obj.setSuccess("true");
        return obj;
    }
    //查询短信状态
    @RequestMapping(value = "/query_status",method = RequestMethod.POST)
    @ResponseBody
    public PubMwdxSendEntity queryStatus(@RequestBody String cusid){
        PubMwdxSendEntity pubMwdxSendEntity = dxtzService.pubMwdxSendEntity(cusid);
        return pubMwdxSendEntity;
    }

    //法官发送短信
    @RequestMapping(value = "/fg_send_message.aj",method = RequestMethod.POST)
    @ResponseBody
    public RetObj getMsgContent(@RequestBody Map data, HttpServletRequest request) throws IOException {
        int ajxh = (Integer) data.get("ajxh");
        String fybh = (String) data.get("fybh");
        String yhm = (String) data.get("yhm");
        FYEnum fyEnum = FYEnum.getFyByFybh(fybh);
        DynamicDataSource.router(fyEnum.getFydm());
        XtyhModel xtyhModel = xtyhService.getXtyhByYhm(yhm);
        String fgbh = String.valueOf(xtyhModel.getYhbh());
        String fgmc = xtyhModel.getYhmc();
        ObjectMapper objectMapper = new ObjectMapper();
        SsdrVO ssdrVO = objectMapper.readValue(String.valueOf(data.get("dsr")),SsdrVO.class);
        String[] dh = objectMapper.readValue(String.valueOf(data.get("dh")),String[].class);
//        DsrjbVO dsrjbVO = objectMapper.readValue(String.valueOf(data.get("dsr")),DsrjbVO.class);
        int templateId= Integer.parseInt((String) data.get("templateId"));
        ObjectMapper objectMapper1 = new ObjectMapper();
        ShortMsgParamObj[] paramObjList = objectMapper1.readValue(String.valueOf(data.get("paramObjList")), ShortMsgParamObj[].class);
        ObjectMapper objectMapper2 = new ObjectMapper();
        String[] urlList = objectMapper2.readValue(String.valueOf(data.get("urlList")),String[].class);
        DynamicDataSource.router(SDPT_FYDM);
        PubDxmbInfoEntity pubDxmbInfoEntity = pubDxmbInfoEntityMapper.selectByPrimaryKey(templateId);
        String msgContent = pubDxmbInfoEntity.getMbnr(); //封装短信内容
        if(null != paramObjList && paramObjList.length > 0){
            for (ShortMsgParamObj paramObj : paramObjList) {
                msgContent = msgContent.replace("{{"+paramObj.getParamName()+"}}",paramObj.getParamValue());
            }
        }
        RetObj obj = new RetObj();
        for(int i = 0 ; i < dh.length ; i++) {
            MwdxSendVo mwdxSendVo = new MwdxSendVo();
            mwdxSendVo.setAjxh(ajxh);
            mwdxSendVo.setSendtype(3);
            mwdxSendVo.setFybh(fybh);
            mwdxSendVo.setSsdrbh(ssdrVO.getSsdrbh());
//       pubMwdxSendEntity.setSendphone(dsrjbVO.getDh());
//        mwdxSendVo.setSendphone("13181748338");
            mwdxSendVo.setSendphone(dh[i]);
            mwdxSendVo.setMsgcontent(msgContent);
            mwdxSendVo.setFgbh(yhm);
            mwdxSendVo.setUrlLis(urlList);
            obj = this.sendPlaintext(request, mwdxSendVo);
            logService.addLog(ajxh, fybh, "", 18, "当事人电话"+dh[i], fgmc,templateId);
        }
        return obj;
    }
    //上传文件
    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
    @ResponseBody
    public RetObj uploadFile(HttpServletRequest request,@RequestParam("file")MultipartFile file,PubZybInfoEntity pubZybInfoEntity){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        RetObj obj = new RetObj();
        HttpSession session = request.getSession();
        String url = dxtzService.uploadFile(file, pubZybInfoEntity);
        obj.setData(url);
        obj.setCode("1");
        obj.setMessage("success");
        obj.setSuccess("true");
        return obj;
    }


    //根据短信id查询资源集合
    @RequestMapping(value = "/showZyList",method = RequestMethod.POST)
    @ResponseBody
    public List<PubZybInfoEntity> showZyList(@RequestParam String ywid){
        DynamicDataSource.router(SDPT_FYDM);
        List<PubZybInfoEntity> zybInfoEntityList = pubZybInfoEntityMapper.selectZybList(ywid);
        return zybInfoEntityList;
    }
    //根据资源id查询文书详情
    @RequestMapping(value = "/showZy",method = RequestMethod.GET)
    public void showZy( HttpServletResponse response,@RequestParam("id") Integer id) throws IOException {
        DynamicDataSource.router(SDPT_FYDM);
        PubZybInfoEntity zybInfoEntity = pubZybInfoEntityMapper.selectZyb(id);
        String url = "E:\\outsdpt\\uploadFile\\"+zybInfoEntity.getZyurl();
        File file = null;
        FileInputStream inputStream = null;
        try {
            file = new File(url);
            if (!file.exists()){
                return;
            }
            inputStream = new FileInputStream(file);
            final byte[] bytes = new byte[1024];
            while (inputStream.read(bytes)>0){
                response.getOutputStream().write(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream!=null){
                inputStream.close();
            }
        }
    }
    //下载文书
    @RequestMapping(value = "/downloadFile",method = RequestMethod.GET)
    public void downloadFile( HttpServletResponse response,@RequestParam("id") Integer id) throws IOException {
        DynamicDataSource.router(SDPT_FYDM);
        PubZybInfoEntity zybInfoEntity = pubZybInfoEntityMapper.selectZyb(id);
        String url = "E:\\outsdpt\\uploadFile\\"+zybInfoEntity.getZyurl();
        String fileName = url.substring(url.lastIndexOf("\\")+1);
        //读到流中
        InputStream inputStream = new FileInputStream(url);
        //设置输出格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition","attachment; fileName=\"" + fileName + "\"");
        //循环取出流中的数据
        byte[] bytes = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(bytes))>0) {
                response.getOutputStream().write(bytes,0,len);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/showImages")
    public String getDbrwList(HttpServletRequest request,@RequestParam("id") Integer id) {
        HttpSession session = request.getSession();
        session.setAttribute("way", id);
        session.removeAttribute("emsyysdbh");
        return "gd/showImages";
    }

    @RequestMapping(value = "/showImages_fg",method = RequestMethod.GET)
    public String getDbrwList_fg(HttpServletRequest request,@RequestParam("id") String id) {
        HttpSession session = request.getSession();
        session.setAttribute("way", id);
        session.removeAttribute("emsyysdbh");
        return "gd/showImages";
    }

    @RequestMapping(value = "/update_template.zf",method = RequestMethod.POST)
    @ResponseBody
    public RetObj updateTemplate(HttpServletRequest request, @RequestBody PubDxmbInfoModel pubDxmbInfoModel){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        HttpSession session = request.getSession();

        dxmbService.updateTemplate(pubDxmbInfoModel);
        RetObj obj = new RetObj();
        obj.setCode("1");
        obj.setMessage("succcess");
        obj.setSuccess("true");
        return obj;
    }

    @RequestMapping(value = "/add_template.zf",method = RequestMethod.POST)
    @ResponseBody
    public RetObj addTemplate(HttpServletRequest request, @RequestBody PubDxmbInfoModel pubDxmbInfoModel){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        HttpSession session = request.getSession();
        dxmbService.addTemplate(pubDxmbInfoModel);
        RetObj obj = new RetObj();
        obj.setCode("1");
        obj.setMessage("succcess");
        obj.setSuccess("true");
        return obj;
    }

    // 生成预览的接口
    @RequestMapping(value = "/preview.zf",method = RequestMethod.POST)
    @ResponseBody
    public PreviewModel preview(HttpServletRequest request){
        Integer yysdbh=Integer.parseInt(request.getParameter("yysdbh"));
        PubYysdJbEntity pubYysdJbEntity=pubYysdJbEntityMapper.selectByPrimaryKey(yysdbh);
        PreviewModel previewModel=new PreviewModel();
        previewModel.setAy(pubYysdJbEntity.getAjmc());
        previewModel.setFymc(FYEnum.getNameByFYDM(FYEnum.getFYDMByFybh(pubYysdJbEntity.getFybh())));
        String uuid=UUID.randomUUID().toString().replaceAll("-","");
        previewModel.setUuid(uuid);
        return previewModel;
    }

    // 发送短信
    @RequestMapping(value = "/fsdx.zf",method = RequestMethod.POST)
    @ResponseBody
    public void fsdx(HttpServletRequest request,HttpServletResponse response){
        // 获取需要传输到外部的信息
        Integer yysdbh=Integer.parseInt(request.getParameter("yysdbh"));
        String uuid=request.getParameter("uuid");
        Integer ssdrbh=Integer.parseInt(request.getParameter("ssdrbh"));
        Integer sdybh=Integer.parseInt(request.getParameter("sdybh"));
        String targettel=request.getParameter("targettel");
        String content=request.getParameter("content");
        if(uuid==null||"".equals(uuid)){
            uuid=UUID.randomUUID().toString().replaceAll("-","");
        }

        /**
         * 获取需要送达文件，根据yysdbh和ssdrbh获取到需要送达的文书，然后下载打包之后，将文书放到固定的地方。
         */
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
        String fileName = uuid + ".zip";
//        for(int i = 0 ; i < paths.size(); ++ i) {
//
//            DownloadUtils.downloadFile(paths.get(i),childName.get(i),response,request);
//        }
        DownloadUtils.downloadZipToLocal(paths,childName,fileName,SHORTMSGDIR,response,request);

        PubDxtz pubDxtz=new PubDxtz();
        pubDxtz.setYysdbh(yysdbh);
        pubDxtz.setSsdrbh(ssdrbh);
        pubDxtz.setSdybh(sdybh==null?0:sdybh);
        pubDxtz.setTargettel(targettel==null?"":targettel);
        pubDxtz.setContent(content);
        pubDxtz.setCreatetime(new Date());
        pubDxtz.setSendstate(0);
        pubDxtz.setUuid(uuid);
        pubDxtz.setVisited(0);
        pubDxtzDao.insert(pubDxtz);
    }
}
