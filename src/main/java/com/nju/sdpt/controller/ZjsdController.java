package com.nju.sdpt.controller;

import com.alibaba.fastjson.JSON;
import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.entity.*;
import com.nju.sdpt.mapper.*;
import com.nju.sdpt.model.FYEnum;
import com.nju.sdpt.model.RetObj;
import com.nju.sdpt.model.XtyhModel;
import com.nju.sdpt.model.ZjsdModel;
import com.nju.sdpt.model.zgysd.Zjsd;
import com.nju.sdpt.service.*;
import com.nju.sdpt.util.*;
import com.nju.sdpt.viewobject.Result;
import com.nju.sdpt.viewobject.ZjsdLoadListVo;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 直接送达 控制层
 */
@Slf4j
@Controller
@RequestMapping("/zjsd")
public class ZjsdController {
    @Autowired
    PubZjsdInfoEntityMapper pubZjsdInfoEntityMapper;
    @Autowired
    private ZjsdService zjsdService;
    @Autowired
    private SsdrService ssdrService;
    @Autowired
    private YysdService yysdService;
    @Autowired
    private XtyhService xtyhService;
    @Autowired
    private PubYysdJbEntityMapper pubYysdJbEntityMapper;
    @Autowired
    private CaxxService caxxService;
    @Autowired
    private PubYysdSsdrEntityMapper pubYysdSsdrEntityMapper;
    @Autowired
    private PubYysdRyxxEntityMapper pubYysdRyxxEntityMapper;

    private static String zjsdURL="http://130.1.19.244:8096/uploadData";
    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;



    @RequestMapping(value = "/zjsdList" , method = RequestMethod.POST)
    @ResponseBody
    public List<ZjsdModel> getZjsdList(HttpServletRequest request, @RequestBody ZjsdLoadListVo zjsdLoadListVo){
        DynamicDataSource.router(SDPT_FYDM);
        HttpSession session = request.getSession();
        String yhm = (String)session.getAttribute("yhm");
        zjsdLoadListVo.setGdryxm(yhm);
        List<ZjsdModel> pubZjsdInfoEntityList = pubZjsdInfoEntityMapper.loadList(zjsdLoadListVo);
        if(null != pubZjsdInfoEntityList && pubZjsdInfoEntityList.size() > 0){
            for (ZjsdModel model : pubZjsdInfoEntityList) {
                //格式化时间
                model.setSmsjStr(DateUtil.format(model.getSmsj(),"yyyy-MM-dd"));
            }
        }
        return pubZjsdInfoEntityList;
    }

    @RequestMapping(value = "/downloadZjsdWj.do", method = RequestMethod.GET)
    @ResponseBody
    public void downloadZjsdWj(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam("yysdbh") Integer yysdbh,
                               @RequestParam("zjsdbh") Integer zjsdbh,
                               @RequestParam("wjid") String wjid,
                               @RequestParam("wjmc") String wjmc) throws IOException {
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdJbEntity pubYysdJbEntity = yysdService.selectByPrimaryKey(yysdbh);
        DynamicDataSource.routerByFybh(pubYysdJbEntity.getFybh());
        String filepath=zjsdService.downloadZjsdWj(yysdbh,zjsdbh,pubYysdJbEntity.getFybh(),wjid,wjmc);
        File file = new File(filepath);
        String filename =file.getName();
        DownloadUtils.downloadFile(filepath,filename,response,request);
    }

    @RequestMapping(value = "/updateByZjsdbh",method = RequestMethod.POST)
    @ResponseBody
    public RetObj uploadSdhz(HttpServletRequest request, @RequestBody ZjsdLoadListVo zjsdLoadListVo){

        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        HttpSession session = request.getSession();
        pubZjsdInfoEntityMapper.updateZjsdById(zjsdLoadListVo.getZjsdjg(),zjsdLoadListVo.getZjsdbh());

        //加载当事人送达结果
        PubZjsdInfoEntity zjsdInfoEntity = pubZjsdInfoEntityMapper.selectByPrimaryKey(zjsdLoadListVo.getZjsdbh());
        if(null != zjsdInfoEntity){
            ssdrService.loadSsdrSdjg(zjsdInfoEntity.getYysdbh(),zjsdInfoEntity.getSsdrbh(), null);
        }
        RetObj obj = new RetObj();
        obj.setCode("1");
        obj.setMessage("succcess");
        obj.setSuccess("true");
        return obj;
    }


    //上传文件
    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
    @ResponseBody
    public boolean uploadFile(HttpServletRequest request,@RequestParam("file") MultipartFile file, Integer zjsdbh){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        HttpSession session = request.getSession();
        zjsdService.uploadZjsd(file,zjsdbh);
        return true;
    }

    //根据路径显示图片
    @RequestMapping(value = "/showSdhz",method = RequestMethod.GET)
    public void showZy(HttpServletResponse response, @RequestParam("zjsdbh") Integer zjsdbh) throws IOException {
        DynamicDataSource.router(SDPT_FYDM);
        PubZjsdInfoEntity zjsdInfoEntity = pubZjsdInfoEntityMapper.selectByPrimaryKey(zjsdbh);
        String url = "E:\\insdpt\\uploadFile\\"+zjsdInfoEntity.getSdhz();
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

    @RequestMapping(value = "/showZjsdjl",method = RequestMethod.GET)
    public String getDbrwList_fg(HttpServletRequest request,@RequestParam("id") String id) {
        HttpSession session = request.getSession();
        session.setAttribute("way", id);
        return "gd/showZjsdjl";
    }

    //根据id查询资源集合
    @RequestMapping(value = "/showzjsdList",method = RequestMethod.POST)
    @ResponseBody
    public List<String> showZyList(@RequestBody String ywid){
        DynamicDataSource.router(SDPT_FYDM);
        String replace = ywid.replace("=", "");
        PubZjsdInfoEntity zjsdInfoEntity = pubZjsdInfoEntityMapper.selectByPrimaryKey(Integer.valueOf(replace));
        String sdgcjl = zjsdInfoEntity.getSdgcjl();
        String replace1 = sdgcjl.replace("[", "");
        String replace2 = replace1.replace("]", "");
        String replace3 = replace2.replace("\"", "");
        String[] split = replace3.split(",");
        List<String> urlList = new ArrayList<>();
        for (String s : split) {
            urlList.add(s);
        }

        return urlList;
    }

    @RequestMapping(value = "/showZjsdjlImage",method = RequestMethod.GET)
    public void showZjsdjlImage( HttpServletResponse response,@RequestParam("url") String path) throws IOException {
        DynamicDataSource.router(SDPT_FYDM);
        String url = "E:\\outsdpt\\sdzx\\img\\"+path;
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

    //下载
    @RequestMapping(value = "/downloadFile",method = RequestMethod.GET)
    public void downloadFile( HttpServletResponse response,@RequestParam("url") String path) throws IOException {
        DynamicDataSource.router(SDPT_FYDM);
        String url = "E:\\insdpt\\uploadFile\\"+path;
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

    @RequestMapping(value = "/uploadZjsdWj.do",method = RequestMethod.POST)
    @ResponseBody
    public Result uploadWj(@RequestParam("file") MultipartFile file, @RequestParam("yysdbh") Integer yysdbh, @RequestParam("zjsdbh") Integer zjsdbh, HttpServletRequest request)
            throws IllegalStateException, IOException {
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdJbEntity pubYysdJbEntity = yysdService.selectByPrimaryKey(yysdbh);
        DynamicDataSource.router(FydmUtil.getFydmByFybh(pubYysdJbEntity.getFybh()));
        XtyhModel xtyhModel = xtyhService.getXtyhByYhm("sdzx");
        String yhbh = xtyhModel.getYhbh()+"";
        if(pubYysdJbEntity.getAjxh()>0){
            Boolean isSuccess = zjsdService.uploadWj(pubYysdJbEntity.getAjxh(),file,yhbh,yysdbh,zjsdbh,pubYysdJbEntity.getFybh());
            return isSuccess?new Result(true,"上传成功","上传成功"):new Result(false,"上传失败","上传失败");
        }else {
            if(pubYysdJbEntity.getAjxh()==-1){
                DynamicDataSource.router(SDPT_FYDM);
                List<PubCaxxEntity> pubCaxxEntities = caxxService.getCaxxByYysdbh(yysdbh);
                DynamicDataSource.router(FydmUtil.getFydmByFybh(pubYysdJbEntity.getFybh()));
                for (PubCaxxEntity pubCaxxEntity:pubCaxxEntities){
                    Boolean result = zjsdService.uploadWj(pubCaxxEntity.getAjxh(),file,yhbh,yysdbh,zjsdbh,pubYysdJbEntity.getFybh());
                    if (!result){
                        return new Result(false,"上传成功",null);
                    }
                }
                return new Result(true,"上传成功",null);
            }
        }
        return null;
    }

    @RequestMapping(value = "/zjsd.aj",method = RequestMethod.POST)
    @ResponseBody
    public Result sendzjsd(@RequestBody Map data,HttpServletRequest request){
        DynamicDataSource.router(SDPT_FYDM);
        int yysdbh=Integer.parseInt(data.get("yysdbh").toString());
        int ssdrbh=Integer.parseInt(data.get("ssdrbh").toString());
//        int yhbh= pubYysdRyxxEntityMapper.findByYhdm(data.get("yhm").toString()).getYhbh();
        int yhbh= Integer.valueOf(data.get("yhm").toString());
        String wjmc=data.get("wjmc").toString();
        String sddz=data.get("sddz").toString();

        PubYysdJbEntity pubYysdJbEntity=pubYysdJbEntityMapper.selectByPrimaryKey(yysdbh);
        PubYysdSsdrEntity pubYysdSsdrEntity=pubYysdSsdrEntityMapper.findByPrimaryKey(yysdbh,ssdrbh);

        PubZjsdInfoEntity pubZjsdInfoEntity=new PubZjsdInfoEntity();
        pubZjsdInfoEntity.setYysdbh(yysdbh);
        pubZjsdInfoEntity.setSsdrbh(ssdrbh);
        pubZjsdInfoEntity.setFybh(pubYysdJbEntity.getFybh());
        pubZjsdInfoEntity.setAjxh(pubYysdJbEntity.getAjxh().toString());
        pubZjsdInfoEntity.setSddz(sddz);
        pubZjsdInfoEntity.setCjsj(new Date());
        pubZjsdInfoEntity.setCjr(yhbh);
        pubZjsdInfoEntity.setWjmc(wjmc);
        pubZjsdInfoEntityMapper.insert(pubZjsdInfoEntity);
        int zjsdbh=pubZjsdInfoEntityMapper.selectMaxNumber(yysdbh,ssdrbh);
        List<SendZjsdModel> sendZjsdModels=new ArrayList<>();
        SendZjsdModel zjsdModel=new SendZjsdModel();
        zjsdModel.setZjsdbh((long) zjsdbh);
        zjsdModel.setFybh(pubYysdJbEntity.getFybh());
        zjsdModel.setAjxh(Long.valueOf(pubYysdJbEntity.getAjxh()));
        zjsdModel.setDsrid(Long.valueOf(ssdrbh));
        zjsdModel.setAh(pubYysdJbEntity.getAh());
        zjsdModel.setDsrmc(pubYysdSsdrEntity.getSsdrmc());
        zjsdModel.setDsrdz(sddz);
        zjsdModel.setYhbh(yhbh);
        zjsdModel.setYhdm(pubYysdRyxxEntityMapper.selectByPrimaryKey(yhbh).getYhdm());
        zjsdModel.setJzrq(new Date());
        sendZjsdModels.add(zjsdModel);

        try{
            String res=HttpUtil.sendHttpPost(JSON.toJSONString(sendZjsdModels),zjsdURL);
            Map zjsdRes=(Map)JSON.parse(res);
            if(!"success".equals(zjsdRes.get("status").toString())){
                return new Result(false,"发送失败","发送失败");
            }
        } catch (IOException e) {
            log.error("http发送失败");
            return new Result(false,"发送失败","发送失败");
        }
        return new Result(true,"发送成功","发送成功");
    }
}
