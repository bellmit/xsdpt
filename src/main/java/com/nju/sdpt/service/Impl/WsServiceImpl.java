package com.nju.sdpt.service.Impl;

import com.nju.sdpt.data.dynamicdDatabases.DataSourceEnum;
import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.entity.*;
import com.nju.sdpt.mapper.*;
import com.nju.sdpt.service.QzHolderService;
import com.nju.sdpt.service.SNPServiceV2Soap;
import com.nju.sdpt.service.WsService;
import com.nju.sdpt.util.Base64Util;
import com.nju.sdpt.util.FydmUtil;
import com.nju.sdpt.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class WsServiceImpl implements WsService {
    @Autowired
    PubBgscQzMapper pubBgscQzMapper;
    @Autowired
    PubWsJbEntityMapper pubWsJbEntityMapper;
    @Resource
    private QzHolderService qzHolderService;
    @Autowired
    private PubWsStampLogMapper pubWsStampLogMapper;
    @Autowired
    private PubYysdWsEntityMapper pubYysdWsEntityMapper;
    @Autowired
    private PubYysdJbEntityMapper pubYysdJbEntityMapper;

    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;
    @Override
    public PubBgscQzEntityWithBLOBs getBgscQzByPrimary(Integer ajxh, Integer wslybh, Integer qzbh) {
        return pubBgscQzMapper.selectByPrimaryKey(ajxh,wslybh.toString(),qzbh);
    }

    @Override
    public PubWsJbEntity getWsJbBykey(Integer ajxh, Integer wslybh) {
        return pubWsJbEntityMapper.selectByPrimaryKey(ajxh,wslybh);
    }

    @Override
    public byte[] fetchStampFile(PubBgscQzEntityWithBLOBs bgscQzEntity) throws Exception {

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        try{
            for (int i = 0; i < 10; i++) {
                //轮询10次，抓取文件
                String tokenID = getToken();
                StampFileResult result = getFileByType(tokenID,2, bgscQzEntity.getStampid(),false);
                cancelToken(tokenID);
                if (result.isSuccess()) {
                    byte[] qzfile = Base64Util.getFromBASE64(result.getDocBase64());
                    if(qzfile!=null){
                        bgscQzEntity.setQzfile(qzfile);
                    }
                    break;
                }
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        return bgscQzEntity.getQzfile();
    }


    @Override
    public PubWsStampLogEntity fetchStampFile(Integer fileType, String stampId) throws Exception {
        PubWsStampLogEntity pubWsStampLogEntity = new PubWsStampLogEntity();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        try{
            for (int i = 0; i < 10; i++) {
                //轮询10次，抓取文件
                String tokenID = getToken();
                StampFileResult result = getFileByType(tokenID,2, stampId,true);
                cancelToken(tokenID);
                if (result.isSuccess()) {

                    byte[] qzfile = Base64Util.getFromBASE64(result.getDocBase64());
                    pubWsStampLogEntity.setFilename(result.getFilename());
                    if(qzfile!=null){
                        pubWsStampLogEntity.setFile(qzfile);
                    }
                    break;
                }
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        return pubWsStampLogEntity;
    }
    @Override
    public PubWsStampLogEntity selectByAjxhAndWsjbbh(Integer ajxh, Integer wsjbbh) {
        List<PubWsStampLogEntity> pubWsStampLogEntities = pubWsStampLogMapper.selectByAjxhAndWsjbbh(ajxh,wsjbbh);
        return pubWsStampLogEntities==null?null:pubWsStampLogEntities.get(0);
    }


    @Override
    @Async
    public void yysdWsQz(PubYysdJbEntity pubYysdJbEntity) throws Exception {
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdWsEntity[]  pubYysdWsEntities = pubYysdWsEntityMapper.selectByYysdbh(pubYysdJbEntity.getYysdbh());
        DynamicDataSource.router(FydmUtil.getFydmByFybh(pubYysdJbEntity.getFybh()));

        for(int i = 0;i<pubYysdWsEntities.length;i++){
            //执行案件直接取文书基表的文件
            if(StringUtil.equals(pubYysdJbEntity.getAjxz(),"8")&&pubYysdWsEntities[i].getWsly()==1){
                PubWsJbEntity pubWsJbEntity = pubWsJbEntityMapper.selectByPrimaryKey(pubYysdJbEntity.getAjxh(), pubYysdWsEntities[i].getWslybh());
                String path = saveWs(pubWsJbEntity.getWsnr());
                pubYysdWsEntities[i].setWsnr(path.getBytes());
                pubYysdWsEntities[i].setWsmc(pubWsJbEntity.getWswjm());
                continue;
            }
            //其他案件的文书基表去stamplog里取
            if(pubYysdWsEntities[i].getWsly()==1){
                PubWsStampLogEntity pubWsStampLogEntity = pubWsStampLogMapper.selectByAjxhAndWsjbbh(pubYysdJbEntity.getAjxh(),pubYysdWsEntities[i].getWslybh()).get(0);
                pubWsStampLogEntity = fetchStampFile(2,pubWsStampLogEntity.getStampGuid());
                if(pubWsStampLogEntity.getFile()!=null){
                    String path = saveWs(pubWsStampLogEntity.getFile());
                    pubYysdWsEntities[i].setWsnr(path.getBytes());
                    pubYysdWsEntities[i].setStatus("签章成功");
                }else {
                    try {
                        String tokenId=getToken();
                        String status = getStampStatus(tokenId,pubWsStampLogEntity.getStampGuid(),true);
                        pubYysdWsEntities[i].setStatus(status);
                        pubYysdWsEntities[i].setFailnum(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if(pubYysdWsEntities[i].getWsly()==2){
                PubBgscQzEntityWithBLOBs pubBgscQzEntityWithBLOBs = pubBgscQzMapper.selectByPrimaryKey(pubYysdJbEntity.getAjxh(),pubYysdWsEntities[i].getWslybh().toString(),pubYysdWsEntities[i].getQzbh());
                if(pubBgscQzEntityWithBLOBs.getQzfile()!=null){
                    String path = saveWs(pubBgscQzEntityWithBLOBs.getQzfile());
                    pubYysdWsEntities[i].setWsnr(path.getBytes());
                    pubYysdWsEntities[i].setStatus("签章完成");
                }else {
                    try {
                        byte[] wsnr =  fetchStampFile(pubBgscQzEntityWithBLOBs);
                        String path = saveWs(wsnr);
                        if(wsnr!=null){
                            pubYysdWsEntities[i].setWsnr(path.getBytes());
                            pubYysdWsEntities[i].setStatus("签章完成");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        //如果签章失败,更新状态
                        if ( pubYysdWsEntities[i].getWsnr()==null){
                            try {
                                String tokenId=getToken();
                                String status = getStampStatus(tokenId,pubBgscQzEntityWithBLOBs.getStampid(),false);
                                pubYysdWsEntities[i].setStatus(status);
                                pubYysdWsEntities[i].setFailnum(1);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        DynamicDataSource.router(SDPT_FYDM);
        for(int i = 0;i<pubYysdWsEntities.length;i++){
            if(pubYysdWsEntities[i].getWsly()<3){
                pubYysdWsEntityMapper.updateByPrimaryKeyWithBLOBs(pubYysdWsEntities[i]);
            }
        }

    }

    @Override
    @Async
    public void yysdWsQzCa(PubYysdJbEntity pubYysdJbEntity) throws Exception {
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdWsEntity[]  pubYysdWsEntities = pubYysdWsEntityMapper.selectByYysdbh(pubYysdJbEntity.getYysdbh());
        DynamicDataSource.router(FydmUtil.getFydmByFybh(pubYysdJbEntity.getFybh()));
        for (PubYysdWsEntity pubYysdWsEntity:pubYysdWsEntities){
            if(pubYysdWsEntity.getWsly()==1){
                PubWsStampLogEntity pubWsStampLogEntity = pubWsStampLogMapper.selectByAjxhAndWsjbbh(pubYysdWsEntity.getAjxh(),pubYysdWsEntity.getWslybh()).get(0);
                pubWsStampLogEntity = fetchStampFile(2,pubWsStampLogEntity.getStampGuid());
                if(pubWsStampLogEntity.getFile()!=null){
                    String path = saveWs(pubWsStampLogEntity.getFile());
                    pubYysdWsEntity.setWsnr(path.getBytes());
                    pubYysdWsEntity.setStatus("签章成功");
                }else {
                    try {
                        String tokenId=getToken();
                        String status = getStampStatus(tokenId,pubWsStampLogEntity.getStampGuid(),true);
                        pubYysdWsEntity.setStatus(status);
                        pubYysdWsEntity.setFailnum(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            if(pubYysdWsEntity.getWsly()==2){
                PubBgscQzEntityWithBLOBs pubBgscQzEntityWithBLOBs = pubBgscQzMapper.selectByPrimaryKey(pubYysdWsEntity.getAjxh(),pubYysdWsEntity.getWslybh().toString(),pubYysdWsEntity.getQzbh());
                if(pubBgscQzEntityWithBLOBs.getQzfile()!=null){
                    String path= saveWs(pubBgscQzEntityWithBLOBs.getQzfile());
                    pubYysdWsEntity.setWsnr(path.getBytes());
                    pubYysdWsEntity.setStatus("签章完成");
                }else {
                    try {
                        byte[] wsnr =  fetchStampFile(pubBgscQzEntityWithBLOBs);
                        if(wsnr!=null){
                            String path = saveWs(wsnr);
                            pubYysdWsEntity.setWsnr(path.getBytes());
                            pubYysdWsEntity.setStatus("签章完成");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        //如果签章失败,更新状态
                        if ( pubYysdWsEntity.getWsnr()==null){
                            try {
                                String tokenId=getToken();
                                String status = getStampStatus(tokenId,pubBgscQzEntityWithBLOBs.getStampid(),false);
                                pubYysdWsEntity.setStatus(status);
                                pubYysdWsEntity.setFailnum(1);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        DynamicDataSource.router(SDPT_FYDM);
        for(int i = 0;i<pubYysdWsEntities.length;i++){
            if(pubYysdWsEntities[i].getWsly()<3){
                pubYysdWsEntityMapper.updateByPrimaryKeyWithBLOBs(pubYysdWsEntities[i]);
            }
        }

    }

    public String getToken() throws Exception {
        String xmlString = "<SNPAPI_REQUEST xmlns:xsd='http://www.w3.org/2001/XMLSchema' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'><API_VERSION>1.0.20151201</API_VERSION><CLI_NAME>法院审判系统</CLI_NAME><CLI_TYPE>1</CLI_TYPE><REQ_OPTION>TOKEN_LOGIN</REQ_OPTION><TOKEN_TIMEOUT>300</TOKEN_TIMEOUT><REQ_ACCOUNT>spxt</REQ_ACCOUNT><REQ_PASSWORD>123456</REQ_PASSWORD><STAMPJOBS /><STAMPJOBSTATUSS /><STAMPDOCS /><DICITEMS /></SNPAPI_REQUEST>";
        SNPServiceV2Soap portType = qzHolderService.getQzServiceIfOpen().getSNPServiceV2Soap();
        String tokenResult = portType.snpwebRequest(xmlString);// 获取的返回值
        String result_ok = dealWithTokenResult(tokenResult, "RESULT_OK");
        if("OK".equals(result_ok) || "OK" == result_ok){
            return dealWithTokenResult(tokenResult, "REQ_TOKEN");
        }else{
            throw new Exception("获取Token失败！");
        }
    }

    public static class StampFileResult{
        private boolean success;
        private String docBase64;
        private String filename;
        private String status;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getDocBase64() {
            return docBase64;
        }

        public void setDocBase64(String docBase64) {
            this.docBase64 = docBase64;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "StampFileResult{" +
                    "success=" + success +
                    ", docBase64='" + docBase64 + '\'' +
                    ", filename='" + filename + '\'' +
                    ", status='" + status + '\'' +
                    '}';
        }
    }

    public StampFileResult getFileByType(String tokenID, Integer fileType,
                                         String stamp_guid,boolean fromStampLog) {
        String xmlString = "<SNPAPI_REQUEST xmlns:xsd='http://www.w3.org/2001/XMLSchema' "
                + "xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'> "
                + "<API_VERSION>1.0.20151201</API_VERSION> <CLI_NAME>接口帮助系统</CLI_NAME> "
                + "<CLI_TYPE>1</CLI_TYPE> <REQ_OPTION>"
                + getReqByFileType(fileType)
                + "</REQ_OPTION> "
                + "<REQ_TOKEN>"
                + tokenID
                + "</REQ_TOKEN> <TOKEN_TIMEOUT>0</TOKEN_TIMEOUT> <STAMPJOBS />"
                + " <STAMPJOBSTATUSS /> <STAMPDOCS> <STAMPDOC> "
                + "<STAMP_GUID>"
                + stamp_guid
                + "</STAMP_GUID> <STATUS>0</STATUS> <FILETYPE>PDF</FILETYPE> <FILESIZE>-1</FILESIZE> </STAMPDOC> </STAMPDOCS> <DICITEMS /> </SNPAPI_REQUEST>";
        SNPServiceV2Soap portType = qzHolderService.getQzServiceIfOpen().getSNPServiceV2Soap();
        String stamp_apply = portType.snpwebRequest(xmlString);// 获取文件任务调用
        StampFileResult result = new StampFileResult();
        String statusDesc = dealWithTokenResult(stamp_apply, "STATUSDESC");
        if (statusDesc != null && StringUtil.equals(statusDesc, "打印完毕")) {
            String docBase64 = null;
            String filename = null;
            if(fromStampLog){
                docBase64 = dealWithTokenResult2(stamp_apply, "DOCBASE64");
                filename =   dealWithTokenResult2(stamp_apply, "FILENAME");
            }else {
                docBase64 = dealWithTokenResult(stamp_apply, "DOCBASE64");
                filename =   dealWithTokenResult(stamp_apply, "FILENAME");
            }
            result.setDocBase64(docBase64);
            result.setFilename(filename);
            result.setSuccess(true);
        }else{
            result.setSuccess(false);
            result.setStatus(statusDesc);
        }
        return result;
    }


    private String getReqByFileType(Integer fileType) {
        if (fileType == null)
            return null;
        switch (fileType) {
            case 1:
                return "STAMP_GETDOC_WORD";
            case 2:
                return "STAMP_GETDOC_PDF";
            case 3:
                return "STAMP_GETDOC_ALL";
            default:
                throw new RuntimeException("未知的签章文件类型！");
        }
    }

    public String cancelToken(String tokenID) {
        String xmlString = "<?xml version='1.0' encoding='utf-8'?> <SNPAPI_REQUEST xmlns:xsd='http://www.w3.org/2001/XMLSchema' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'> <API_VERSION>1.0.20151201</API_VERSION> <CLI_NAME>法院审判系统</CLI_NAME> <CLI_TYPE>1</CLI_TYPE> <REQ_OPTION>TOKEN_LOGOUT</REQ_OPTION> <REQ_TOKEN>"
                + tokenID
                + "</REQ_TOKEN> <TOKEN_TIMEOUT>0</TOKEN_TIMEOUT> <STAMPJOBS /> <STAMPJOBSTATUSS /> <STAMPDOCS /> <DICITEMS /> </SNPAPI_REQUEST>";
        SNPServiceV2Soap portType = qzHolderService.getQzServiceIfOpen().getSNPServiceV2Soap();
        return portType.snpwebRequest(xmlString);
    }


    /**
     * 获取标签内容
     * example:
     * <abc>hello</abc>
     * 获取hello内容
     * @param tokenResult
     * @param temp
     * @return
     */
    private String dealWithTokenResult(String tokenResult, String temp) {
        // 假设tokenResult是RESULT_OK,这是获取token成功或失败的返回值，REQ_TOKEN
        String position_start = "<" + temp + ">";
        String position_end = "</" + temp + ">";
        String result_ok = "";
        try {
            result_ok = getStringByPosition(tokenResult, position_start,
                    position_end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result_ok;
    }

    private String getStringByPosition(String token, String p1, String p2) {
        return token.substring(token.indexOf(p1) + p1.length(),
                token.indexOf(p2));
    }

    public String getStampStatus(String tokenID, String stamp_guid,boolean fromStampLog) {
        // 获取签章状态
        String xmlString = "<SNPAPI_REQUEST xmlns:xsd='http://www.w3.org/2001/XMLSchema' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'> "
                + "<API_VERSION>1.0.20151201</API_VERSION><REQ_OPTION>STAMP_GETSTATUS</REQ_OPTION> "
                + "<REQ_TOKEN>"
                + tokenID
                + "</REQ_TOKEN> "
                + "<TOKEN_TIMEOUT>0</TOKEN_TIMEOUT> "
                + "<STAMPJOBS /> <STAMPJOBSTATUSS> <STAMPJOBSTATUS> "
                + "<STAMP_GUID>"
                + stamp_guid
                + "</STAMP_GUID> "
                + "<STATUS>0</STATUS> </STAMPJOBSTATUS> </STAMPJOBSTATUSS> <STAMPDOCS /> <DICITEMS /> </SNPAPI_REQUEST>";
        SNPServiceV2Soap portType = qzHolderService.getQzServiceIfOpen().getSNPServiceV2Soap();
        String resultXml = portType.snpwebRequest(xmlString);
        System.out.println(resultXml);
        String status = null;
        if(fromStampLog){
            status =  dealWithTokenResult2(resultXml, "STATUS");
        }else {
            dealWithTokenResult(resultXml, "STATUS");
        }
        return dealStatus(status);
    }
    public String dealStatus(String status) {
        if (status == null) {
            return null;
        }
        if (StringUtil.equals(status, "0")) {
            return "等待处理";
        }
        if (StringUtil.equals(status, "1")) {
            return "正在处理";
        }
        if (StringUtil.equals(status, "2")) {
            return "处理完成";
        }
        if (StringUtil.equals(status, "3")) {
            return "处理出错";
        }
        if (StringUtil.equals(status, "4")) {
            return "拒绝处理";
        }
        if (StringUtil.equals(status, "5")) {
            return "其他情况";
        }
        return "未知情况";
    }


    @Override
    @Transactional
    public boolean saveOrInsertYysdWsBatch(List<PubYysdWsEntity> entities) {
        try {
            for (PubYysdWsEntity entity : entities) {
//                Integer yysdbh = entity.getYysdbh();
//                Integer ssdrbh = entity.getSsdrbh();
//                String wslb = entity.getWslb();
//                Integer bh = pubYysdWsMapper.existsSameTypeWs(yysdbh, ssdrbh, wslb);
//                if (bh != null && bh > 0) {
//                    entity.setBh(bh);
//                    pubYysdWsMapper.updateByPrimaryKeyWithBLOBs(entity);
//                } else {
                Integer bh = pubYysdWsEntityMapper.getBhByyYysdbh(entity.getYysdbh()) + 1;
                entity.setBh(bh);
                pubYysdWsEntityMapper.insert(entity);
//                }
            }
            return true;
        }catch (RuntimeException e){
            throw e;
        }
    }

    @Override
    public PubYysdWsEntity[] selectByYysdbh(Integer yysdbh) {
        return pubYysdWsEntityMapper.selectByYysdbh(yysdbh);
    }

    //获取第二个标签的内容
    private String dealWithTokenResult2(String tokenResult, String temp) {
        // 假设tokenResult是RESULT_OK,这是获取token成功或失败的返回值，REQ_TOKEN
        String position_start = "<" + temp + ">";
        String position_end = "</" + temp + ">";
        String result_ok = "";
        try {
            result_ok = getSecondStringByPosition(tokenResult, position_start,
                    position_end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result_ok;
    }

    private String getSecondStringByPosition(String token, String p1, String p2) {
        return token.substring(token.lastIndexOf(p1) + p1.length(),
                token.lastIndexOf(p2));
    }

    private static String saveWs(byte[] bytes){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String str=sdf.format(new Date());
        File file=new File("E:\\sdptwsnr\\"+str);
        if(!file.exists()){
            file.mkdir();
        }
        String path="E:\\sdptwsnr\\"+str+"\\"+UUID.randomUUID().toString()+".pdf";
        if(path.equals("")) return "";
        try{
            FileImageOutputStream imageOutPut=new FileImageOutputStream(new File(path));
            imageOutPut.write(bytes,0,bytes.length);
            imageOutPut.close();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return path;
    }

    public void reSaveWs(Integer yysdbh,Integer bh,byte[] bytes){
        String path = saveWs(bytes);
        DynamicDataSource.router(SDPT_FYDM);
        pubYysdWsEntityMapper.saveWsPath(yysdbh,bh,path.getBytes());
    }

    @Override
    public List<String> selectWslbByYysdbhAndSsdrbh(Integer yysdbh, Integer ssdrbh) {
        String currentDB = DynamicDataSource.getCurrentDB();
        DynamicDataSource.router(SDPT_FYDM);
        List<String> list = pubYysdWsEntityMapper.selectWslbByYysdbhAndSsdrbh(yysdbh, ssdrbh);
        DynamicDataSource.router(currentDB);
        return list;
    }


}
