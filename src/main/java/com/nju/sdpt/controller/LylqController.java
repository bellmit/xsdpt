package com.nju.sdpt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.entity.PubLylqInfoEntity;
import com.nju.sdpt.entity.PubLylqSdhzEntity;
import com.nju.sdpt.entity.PubYysdRyxxEntity;
import com.nju.sdpt.model.*;
import com.nju.sdpt.service.GdRyxxService;
import com.nju.sdpt.service.LogService;
import com.nju.sdpt.service.LylqService;
import com.nju.sdpt.service.XtyhService;
import com.nju.sdpt.util.DateUtil;
import com.nju.sdpt.util.FileUtil;
import com.nju.sdpt.util.MyZipUtils;
import com.nju.sdpt.util.RequestUtil;
import com.nju.sdpt.viewobject.LylqLoadListVo;
import com.nju.sdpt.viewobject.LylqUploadSdhzVo;
import com.nju.sdpt.viewobject.SsdrVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 来院领取相关业务 控制层
 */
@Controller
@RequestMapping("/lylq")
public class LylqController {

    @Autowired
    LylqService lylqService;
    @Autowired
    XtyhService xtyhService;
    @Autowired
    LogService logService;
    @Autowired
    GdRyxxService gdRyxxService;
    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;
    /**
     * 来院领取 - 上传送达回执 或者 编辑送达结果
     * @param request
     * @return
     */
    @RequestMapping(value = "/upload_sdhz.zf",method = RequestMethod.POST)
    @ResponseBody
    public RetObj uploadSdhz(HttpServletRequest request, @RequestBody LylqUploadSdhzVo lylqUploadSdhzVo){

        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        HttpSession session = request.getSession();
        lylqService.uploadSdhz(lylqUploadSdhzVo);
        RetObj obj = new RetObj();
        obj.setCode("1");
        obj.setMessage("succcess");
        obj.setSuccess("true");
        return obj;
    }

    /**
     * 来院领取 - 新增
     * @param request
     * @return
     */
    @RequestMapping(value = "/addLylq.zf",method = RequestMethod.POST)
    @ResponseBody
    public RetObj addLylq(HttpServletRequest request, @RequestBody LylqModel pubLylqInfoEntity){
        HttpSession session = request.getSession();
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdRyxxEntity pubYysdRyxxEntity = gdRyxxService.findByYhdm(gdRyxxService.getYhdm(request));
        lylqService.addLylq(pubLylqInfoEntity,pubYysdRyxxEntity);
        RetObj obj = new RetObj();
        obj.setCode("1");
        obj.setMessage("succcess");
        obj.setSuccess("true");
        return obj;
    }

    /**
     * 来院领取 - 新增(法官页面)
     * @param request
     * @return
     */
    @RequestMapping(value = "/addFgLylq.zf",method = RequestMethod.POST)
    @ResponseBody
    public RetObj addFgLylq(HttpServletRequest request, @RequestBody Map data) throws IOException, ParseException {
        int ajxh = (Integer) data.get("ajxh");
        String fybh = (String) data.get("fybh");
        HttpSession session = request.getSession();
        String yhm = (String)session.getAttribute("yhm");
        String yylqsj = (String) data.get("yylqsj");
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.noSecondFormat);
        Date sj =sdf.parse(yylqsj);
        String lylqaddress = (String) data.get("lylqaddress");
        FYEnum fyEnum = FYEnum.getFyByFybh(fybh);
        DynamicDataSource.router(fyEnum.getFydm());
        XtyhModel xtyhModel = xtyhService.getXtyhByYhm(yhm);
        String fgbh = String.valueOf(xtyhModel.getYhbh());
        String fgmc = xtyhModel.getYhmc();
        ObjectMapper objectMapper = new ObjectMapper();
//        DsrjbVO dsrjbVO = objectMapper.readValue(String.valueOf(data.get("dsr")),DsrjbVO.class);
        SsdrVO ssdrVO = objectMapper.readValue(String.valueOf(data.get("dsr")),SsdrVO.class);
        DynamicDataSource.router(SDPT_FYDM);
        PubLylqInfoEntity pubLylqInfoEntity = new PubLylqInfoEntity();
        pubLylqInfoEntity.setAjxh(ajxh);
        pubLylqInfoEntity.setSsdrbh(ssdrVO.getSsdrbh());
        pubLylqInfoEntity.setFybh(fybh);
        pubLylqInfoEntity.setFgbh(yhm);
        pubLylqInfoEntity.setYylqsj(sj);
        pubLylqInfoEntity.setLylqaddress(lylqaddress);
        lylqService.addFgLylq(pubLylqInfoEntity);
        logService.addLog(pubLylqInfoEntity.getAjxh(),pubLylqInfoEntity.getFybh(),ssdrVO.getSsdrmc(),15,"",fgmc,null);
        RetObj obj = new RetObj();
        obj.setCode("1");
        obj.setMessage("succcess");
        obj.setSuccess("true");
        return obj;
    }


    /**
     * 来院领取 -加载来院记录列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/load_list.zf",method = RequestMethod.POST)
    @ResponseBody
    public List<LylqModel> loadList(HttpServletRequest request, @RequestBody LylqLoadListVo lylqLoadListVo){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        HttpSession session = request.getSession();
//        String fybh = (String)session.getAttribute("fybh");
//        FYEnum fyEnum= FYEnum.getFyByFybh(fybh);
//        DynamicDataSource.router(fyEnum.getFydm());

        String yhm = (String)session.getAttribute("yhm");
        lylqLoadListVo.setGdryxm(yhm);
        Integer yhbh = gdRyxxService.getYhbhBySession(session);
        lylqLoadListVo.setSdybh(yhbh);
        //查询具体数据
        List<LylqModel> lylqModelList = lylqService.loadList(lylqLoadListVo);
        return lylqModelList;
    }


    /**
     * 来院领取 -加载来院记录列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/fgload_list.zf",method = RequestMethod.POST)
    @ResponseBody
    public List<LylqModel> fgLoadList(HttpServletRequest request,Integer sdjg){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        HttpSession session = request.getSession();
        String yhm = (String) session.getAttribute("yhm");
        String fybh= (String) session.getAttribute("fybh");
        //查询具体数据
        List<LylqModel> lylqModelList = lylqService.fgLoadList(yhm,fybh,sdjg);
        return lylqModelList;
    }

    @RequestMapping(value = "/get_ajmc.zf",method = RequestMethod.POST)
    @ResponseBody
    public String getAjmc(HttpServletRequest request,Integer yysdbh){ //加载数据环境
        HttpSession session = request.getSession();
        DynamicDataSource.router(SDPT_FYDM);
        String yhm = (String)session.getAttribute("yhm");
//        String fybh = (String)session.getAttribute("fybh");
//        FYEnum fyEnum= FYEnum.getFyByFybh(fybh);
//        DynamicDataSource.router(fyEnum.getFydm());
        String ajmc="";
        List<YysdModel> yysdModelList = lylqService.getYysdListByGdryxm(yhm);
        for(int i=0;i<yysdModelList.size();++i){
            if(yysdModelList.get(i).getYysdbh()==Integer.toString(yysdbh)){
                ajmc=yysdModelList.get(i).getAjmc();
            }
        }
        return ajmc;
    }

    @RequestMapping(value = "/get_yysd_list.zf",method = RequestMethod.POST)
    @ResponseBody
    public List<YysdModel> getYysdList(HttpServletRequest request){ //加载数据环境
        HttpSession session = request.getSession();
        DynamicDataSource.router(SDPT_FYDM);
        String yhm = (String)session.getAttribute("yhm");
//        String fybh = (String)session.getAttribute("fybh");
//        FYEnum fyEnum= FYEnum.getFyByFybh(fybh);
//        DynamicDataSource.router(fyEnum.getFydm());

        List<YysdModel> yysdModelList = lylqService.getYysdListByGdryxm(yhm);
        return yysdModelList;
    }


    @RequestMapping(value = "/get_ws_list.zf",method = RequestMethod.POST)
    @ResponseBody
    public String getWsList(HttpServletRequest request,Integer yysdbh,Integer ssdrbh){ //加载数据环境
        HttpSession session = request.getSession();
        DynamicDataSource.router(SDPT_FYDM);

        List<WsModel> wslb = lylqService.getYysdListByyysdbh(yysdbh,ssdrbh);
        String ws="";
        List<String> w=new ArrayList<>();
        for(int i=0;i<wslb.size();++i){
            w.add(wslb.get(i).getWSLB());
        }
        HashSet h=new HashSet(w);
        w.clear();
        w.addAll(h);
        for(int i=0;i<w.size();++i){
            if(i==w.size()-1){
                ws=ws+w.get(i);
            }
            else{
                ws=ws+w.get(i)+",";
            }
        }
        if(ws.equals("")){
            return "暂无文书";
        }
        return ws;
    }

    @RequestMapping(value = "/get_yysd.zf",method = RequestMethod.POST)
    @ResponseBody
    public String getYysdList(HttpServletRequest request,Integer yysdbh){ //加载数据环境
        HttpSession session = request.getSession();
        DynamicDataSource.router(SDPT_FYDM);
        String gh="";
        List<YysdModel> yysd = lylqService.getYysdList(yysdbh);
        return yysd.get(0).getAjmc();
    }

    @RequestMapping(value = "/get_laay.zf",method = RequestMethod.POST)
    @ResponseBody
    public String getLaay(HttpServletRequest request,Integer yysdbh){ //加载数据环境
        HttpSession session = request.getSession();
        DynamicDataSource.router(SDPT_FYDM);
        String gh="";
        List<YysdModel> yysd = lylqService.getYysdList(yysdbh);
        return yysd.get(0).getLaay();
    }



    /**
     * 图片预览 - 来院领取 回执
     * @param request
     * @param response
     * @param lylqId
     * @throws IOException
     */
    @RequestMapping(value = "/imgView_lylq_sdhz.zf",method = RequestMethod.GET)
    @ResponseBody
    public  void   downloadLylqSdhz(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("lylqId") Integer lylqId) throws IOException {
        DynamicDataSource.router(SDPT_FYDM);

        List<PubLylqSdhzEntity> pubLylqSdhzEntityByLylqid = lylqService.getPubLylqSdhzEntityByLylqid(lylqId);
        String folderPath = "";
        if(pubLylqSdhzEntityByLylqid != null && !pubLylqSdhzEntityByLylqid.isEmpty()){
            PubLylqSdhzEntity pubLylqSdhzEntity = pubLylqSdhzEntityByLylqid.get(0);
            folderPath = pubLylqSdhzEntity.getSdhzfolder();
            MyZipUtils.zip(folderPath+"/",folderPath+".zip");
            File zipFile = new File(folderPath+".zip");
            byte[] wsfile = FileUtil.file2byte(zipFile);
            sendFileToDownload(response, zipFile.getName(), wsfile);
        }

    }

    private void sendFileToDownload(HttpServletResponse response,
                                    String saveName, byte[] qzfile)
            throws UnsupportedEncodingException, IOException {
        saveName = URLEncoder.encode(saveName, "UTF-8");
        saveName = saveName.replaceAll("%28", "(");
        saveName = saveName.replaceAll("%29", ")");
        RequestUtil.openFile(qzfile, saveName, null, response);
    }


}
