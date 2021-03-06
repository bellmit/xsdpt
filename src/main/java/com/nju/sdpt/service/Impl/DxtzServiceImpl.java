package com.nju.sdpt.service.Impl;

import com.alibaba.fastjson.JSON;
import com.nju.sdpt.entity.*;
import com.nju.sdpt.mapper.*;
import com.nju.sdpt.model.DxtzListModel;
import com.nju.sdpt.model.FYEnum;
import com.nju.sdpt.model.PubSsdrHmEntityModel;
import com.nju.sdpt.model.xml.PlaintextMsgRequestXml;
import com.nju.sdpt.model.xml.ShortMsgRequestXml;
import com.nju.sdpt.service.DxtzService;
import com.nju.sdpt.service.LogService;
import com.nju.sdpt.service.SsdrService;
import com.nju.sdpt.util.*;
import com.nju.sdpt.viewobject.*;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DxtzServiceImpl implements DxtzService {

    private final Logger logger = LoggerFactory.getLogger(DxtzServiceImpl.class);

    private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    PubYysdJbEntityMapper pubYysdJbEntityMapper;

    @Autowired
    PubDxtzInfoEntityMapper pubDxtzInfoEntityMapper;

    @Autowired
    PubDxmbInfoEntityMapper pubDxmbInfoEntityMapper;

    @Autowired
    PubMwdxSendEntityMapper pubMwdxSendEntityMapper;

    @Autowired
    PubSsdrHmEntityMapper pubSsdrHmEntityMapper;

    @Autowired
    PubYysdRyxxEntityMapper pubYysdRyxxEntityMapper;

    @Autowired
    PubYysdSsdrEntityMapper pubYysdSsdrEntityMapper;

    @Autowired
    LogService logService;
    @Autowired
    SsdrService ssdrService;

    @Autowired
    PubZybInfoEntityMapper zybInfoMapper;
    @Autowired
    PubXsmbInfoEntityMapper pubXsmbInfoEntityMapper;

    @Autowired
    private PubYysdSdwsEntityMapper pubYysdSdwsEntityMapper;

    @Autowired
    private PubYysdWsEntityMapper pubYysdWsEntityMapper;

    @Value("${xml.prefix}")
    String xmlPrefix;

    @Override
    public Boolean sendShortMsg(SendShortMsgVo sendShortMsgVo, String yhm) {

        //????????????
        PubYysdJbEntity pubYysdJbEntity = pubYysdJbEntityMapper.selectByPrimaryKey(sendShortMsgVo.getYysdbh());
        //???????????????
        //???????????????????????????????????????
        PubYysdSsdrEntityKey pubYysdSsdrKey = new PubYysdSsdrEntityKey();
        pubYysdSsdrKey.setYysdbh(sendShortMsgVo.getYysdbh());
        pubYysdSsdrKey.setSsdrbh(sendShortMsgVo.getSsdrbh());
        PubYysdSsdrEntity ssdrEntity = pubYysdSsdrEntityMapper.selectByPrimaryKey(pubYysdSsdrKey);
        //??????????????????
        PubDxmbInfoEntity pubDxmbInfoEntity = pubDxmbInfoEntityMapper.selectByPrimaryKey(sendShortMsgVo.getTemplateId());

        PubYysdRyxxEntity byYhdm = pubYysdRyxxEntityMapper.findByYhdm(yhm);
        //????????????????????????
        String writePath = "E:\\insdpt\\shortMsg";
        XMLObjUtil.isChartPathExit(writePath);

        List<Integer> targetTelBhs = sendShortMsgVo.getTargetTelBhs();
        //????????????
        String[] urlLis = sendShortMsgVo.getUrlLis();
        List<String> urlList = new ArrayList<>();
        if (null!=urlLis && urlLis.length>0){
            for (String urlLi : urlLis) {
                if (StringUtil.isNotBlank(urlLi)){
                    urlList.add(urlLi);
                }
            }
        }

        //??????????????????
        FYEnum fyEnum = FYEnum.getFyByFybh(byYhdm.getFybh());
        String fymc = "????????????";
        if(null != fyEnum){
            fymc = fyEnum.getName();
        }
        String yhmc = byYhdm.getYhmc();
        for (Integer targetTelBh : targetTelBhs) {
            //????????????
            PubSsdrHmEntity pubSsdrHmEntity = pubSsdrHmEntityMapper.selectByPrimaryKey(targetTelBh);


            //?????????????????????????????? ??????????????????
            PubDxtzInfoEntity pubDxtzInfoEntity = new PubDxtzInfoEntity();
            String msgContent = "";
            pubDxtzInfoEntity.setSdybh(byYhdm.getYhbh());
            pubDxtzInfoEntity.setYysdbh(sendShortMsgVo.getYysdbh());
            pubDxtzInfoEntity.setDsrbgh(ssdrEntity.getSsdrbh());
            pubDxtzInfoEntity.setHmbh(targetTelBh);
            //???????????? web tel
            pubDxtzInfoEntity.setWebtel(pubSsdrHmEntity.getShowTel());
            //?????????????????? targrt tel
            pubDxtzInfoEntity.setTargettel(pubSsdrHmEntity.getOperatorTel());
            //tel_batch_no
            pubDxtzInfoEntity.setTelbatchno(pubSsdrHmEntity.getTelBatchNo());
            //operator type
            pubDxtzInfoEntity.setOperatortype(pubSsdrHmEntity.getOperatorType());
            //show number
            pubDxtzInfoEntity.setShownumber("1069053926113");
            pubDxtzInfoEntity.setSendstate(0);
            pubDxtzInfoEntity.setTemplateid(sendShortMsgVo.getTemplateId());
            pubDxtzInfoEntity.setTemplateno(pubDxmbInfoEntity.getMbhsy());
            pubDxtzInfoEntity.setMsgcontent(msgContent);
            pubDxtzInfoEntity.setParamjson(JSON.toJSONString(sendShortMsgVo.getParamObjList()));
            pubDxtzInfoEntity.setCreatetime(new Date());
            pubDxtzInfoEntity.setSsdrbh(sendShortMsgVo.getSsdrbh());
            pubDxtzInfoEntity.setFybh(pubYysdJbEntity.getFybh());
            pubDxtzInfoEntity.setAjxh(pubYysdJbEntity.getAjxh());
            if (null!= urlList && urlList.size()>0){
                //0 ?????????  1 ?????????
                pubDxtzInfoEntity.setFwzt(0);
            }
            if(null != ssdrEntity.getWsnum()) {
                pubDxtzInfoEntity.setWsnum(ssdrEntity.getWsnum());
            }
            Integer i= savePubDxtzInfoEntity(pubDxtzInfoEntity);
            msgContent = pubDxmbInfoEntity.getMbnr(); //??????????????????
            List<ShortMsgParamObj> paramObjList = sendShortMsgVo.getParamObjList();
            boolean shortLink = false; //???????????????????????????
            if(null != paramObjList && paramObjList.size() > 0){
                for (ShortMsgParamObj paramObj : paramObjList) {
                    if (paramObj.getParamName().equals("????????????")){
                        String link="http://tjsd.tjcourt.gov.cn:8091/dxsd?uuid="+sendShortMsgVo.getYysdbh()+"_"+i;
                        paramObj.setParamValue(link);
                        shortLink = true;
                    }
                    msgContent = msgContent.replace("{{"+paramObj.getParamName()+"}}",paramObj.getParamValue());
                }
            }
            if(!shortLink){
                //??????????????????  ??????urlList
                urlList = new ArrayList<>();
            }


            // ??????????????????
//            List<String> dsrwslbArray = sendShortMsgVo.getDsrwslbArray();
//            for(String str : dsrwslbArray){
//                if(str.contains("_")){
//                    String[] yysdbhBh = str.split("_");
//                    PubYysdSdwsEntity pubYysdSdwsEntity = new PubYysdSdwsEntity(Integer.parseInt(yysdbhBh[0]), Integer.parseInt(yysdbhBh[1]),"dx",i);
//                    pubYysdSdwsEntityMapper.insert(pubYysdSdwsEntity);
//                }
//
//            }


            if (null!= urlList && urlList.size()>0){
                for (String s : urlList) {
                    PubZybInfoEntity zybInfoEntity = new PubZybInfoEntity();
                    //??????????????????
                    //????????????  1:????????????
                    zybInfoEntity.setYwlx(1);
                    //??????idi
                    zybInfoEntity.setYwid(String.valueOf(i));
//                    zybInfoEntity.setZymc(originalFilename);
                    zybInfoEntity.setZyurl(s);
                    savePubZybInfoEntity(zybInfoEntity);

                }
            }
            pubDxtzInfoEntity.setMsgcontent(msgContent);
            pubDxtzInfoEntityMapper.updateByPrimaryKey(pubDxtzInfoEntity);
            //??????xml?????? ??????ftp??????  ???????????????????????????
            ShortMsgRequestXml requestXml = new ShortMsgRequestXml();
            requestXml.setYysdbh(sendShortMsgVo.getYysdbh());
            requestXml.setDxtzId(pubDxtzInfoEntity.getDxtzid());
            requestXml.setTargetTel(pubSsdrHmEntity.getOperatorTel());
            requestXml.setTelBatchNo(pubSsdrHmEntity.getTelBatchNo());
            requestXml.setOperatorType(pubSsdrHmEntity.getOperatorType());
            requestXml.setTemplateNo(pubDxmbInfoEntity.getMbhsy());
            requestXml.setMsgContent(msgContent);
            requestXml.setParamJson(pubDxtzInfoEntity.getParamjson());
            requestXml.setUrlList(urlList);

            String sdpIdCard = ssdrEntity.getSfzhm();
            if (StringUtil.isNotBlank(sdpIdCard)) {
                sdpIdCard = SHA256Util.getSHA256(sdpIdCard);
            }
            requestXml.setSdpIdCard(sdpIdCard);

            String fybh = pubDxtzInfoEntity.getFybh();
            PubXsmbInfoEntity xsmbInfoEntity = pubXsmbInfoEntityMapper.selectOneByFybh(fybh);
            //index: 0 => ??????ID
            //index: 1 => ??????????????????
            //index: 1+ => ??????????????????
            List<String> templateValue = new ArrayList<>();
            if(null != xsmbInfoEntity){

                templateValue.add(xsmbInfoEntity.getMbid());
                templateValue.add(xsmbInfoEntity.getScalling());
                String mbnr = xsmbInfoEntity.getMbnr();
                if(StringUtil.isNotBlank(mbnr)){
                    if(mbnr.indexOf("#*#") >= 0){
                        templateValue.add(byYhdm.getYhmc());
                        templateValue.add(byYhdm.getLxdh());
                    }
                }
            }
            requestXml.setTemplateValue(templateValue);


            String wirteFilePath = writePath + "\\"+xmlPrefix+UUID.randomUUID().toString() + ".xml";
            XMLObjUtil.convertToXml(requestXml,wirteFilePath);
            logger.info("[????????????XML]dxtzId:{},XML?????????:{}",requestXml.getDxtzId(),wirteFilePath);
            //????????????  ????????????  ???????????????  ??????  ??????  ?????????
            logService.addLog(pubYysdJbEntity.getAjxh(),pubYysdJbEntity.getFybh(),ssdrEntity.getSsdrmc(),28,"",yhmc,sendShortMsgVo.getYysdbh());
            pubYysdJbEntityMapper.updateStatusByWayAndYysdbh("DXSD",sendShortMsgVo.getYysdbh());
        }
        return true;
    }

    @Override
    public List<DxtzListModel> loadList(DxtzLoadListVo loadListVo) {
        if(null == loadListVo){
            loadListVo = new DxtzLoadListVo();
        }
        List<DxtzListModel> result = new ArrayList<>();
        List<DxtzListModel> pubDxtzInfoEntityList = pubDxtzInfoEntityMapper.loadList(loadListVo);
        if(null != pubDxtzInfoEntityList && pubDxtzInfoEntityList.size() > 0){
            for (DxtzListModel dxtzInfoEntityWithBLOBs : pubDxtzInfoEntityList) {
                DxtzListModel model = new DxtzListModel();
                BeanUtils.copyProperties(dxtzInfoEntityWithBLOBs,model);
                //???????????????
                model.setCreatetimeStr(DateUtil.format(dxtzInfoEntityWithBLOBs.getCreatetime(),"yyyy-MM-dd HH:mm:ss"));
                result.add(model);
            }
        }
        return result;
    }
    @Override
    public List<DxtzListModel> loadList(DxtzLoadListVo loadListVo,String start,String end) {
        if(null == loadListVo){
            loadListVo = new DxtzLoadListVo();
        }
        List<DxtzListModel> result = new ArrayList<>();
        List<DxtzListModel> pubDxtzInfoEntityList = pubDxtzInfoEntityMapper.loadList1(loadListVo,start,end);
        if(null != pubDxtzInfoEntityList && pubDxtzInfoEntityList.size() > 0){
            for (PubDxtzInfoEntity dxtzInfoEntityWithBLOBs : pubDxtzInfoEntityList) {
                DxtzListModel model = new DxtzListModel();
                BeanUtils.copyProperties(dxtzInfoEntityWithBLOBs,model);
                //???????????????
                model.setCreatetimeStr(DateUtil.format(dxtzInfoEntityWithBLOBs.getCreatetime(),"yyyy-MM-dd HH:mm:ss"));
                result.add(model);
            }
        }
        return result;
    }

    @Override
    public List<PubMwdxSendEntity> fgLoadList(String yhm, String fybh,int status) {
        List<PubMwdxSendEntity> pubMwdxSendEntities = pubMwdxSendEntityMapper.fgLoadList(yhm,fybh,status);

        return pubMwdxSendEntities;
    }

    @Override
    public void editSdjg(DxtzEditSdjgVo dxtzEditSdjgVo) {
        String[] dxtzIds={};
        if(StringUtil.isNotBlank(dxtzEditSdjgVo.getDxtzIds())){
             dxtzIds=dxtzEditSdjgVo.getDxtzIds().split(",");
        }

        List<String> dxtzIdlist= Arrays.asList(dxtzIds);
        if(CollectionUtils.isEmpty(dxtzIdlist)){
            dxtzIdlist = new ArrayList<>();
        }
        Integer dxtzId = dxtzEditSdjgVo.getDxtzId();
        if(NumberUtil.isIntNotNullAndGtZero(dxtzId)){
            dxtzIdlist.add(dxtzId.toString());
        }
       for(int i=0;i<dxtzIdlist.size();i++){
           if(StringUtil.isBlank(dxtzIdlist.get(i))){
               continue;

           }
           PubDxtzInfoEntity entity = pubDxtzInfoEntityMapper.selectById(Integer.parseInt(dxtzIdlist.get(i)));
           if(null != entity){
               PubDxtzInfoEntity updateEntity = new PubDxtzInfoEntity();
               updateEntity.setDxtzid(Integer.parseInt(dxtzIdlist.get(i)));
               //?????????????????? ???????????? 0: ?????????  1???????????????  2???????????????  ??????0)
               updateEntity.setSdjg(dxtzEditSdjgVo.getSdjg());
                if(dxtzEditSdjgVo.getSdjg()!=null&&dxtzEditSdjgVo.getSdjg()>0){
                    updateEntity.setSubmittime(new Date());
                }
               //??????????????????
               PubYysdJbEntity pubYysdJbEntity = pubYysdJbEntityMapper.selectByPrimaryKey(entity.getYysdbh());

               if(null != pubYysdJbEntity){
                   //???????????????????????????
                   //???????????? -??? ???????????????????????????????????????
                   if(Objects.equals(1,dxtzEditSdjgVo.getSdjg())){
                       pubYysdJbEntity.setDxsd("Y");
//                    pubYysdJbEntity.setSdjg("Y");
                   }else {
                       pubYysdJbEntity.setDxsd("N");
                   }
                   pubYysdJbEntityMapper.updateByPrimaryKeySelective(pubYysdJbEntity);
               }
               pubDxtzInfoEntityMapper.updateByPrimaryKeySelective(updateEntity);

               //???????????????????????????
               ssdrService.loadSsdrSdjg(entity.getYysdbh(),entity.getSsdrbh(), entity.getFybh());

           }
       }
    }

    @Override
    public List<PubSsdrHmEntityModel> querySdpPhone(QuerySdpPhoneVo querySdpPhoneVo) {
        List<PubSsdrHmEntityModel> result = new ArrayList<>();
        if(null != querySdpPhoneVo){
            List<PubSsdrHmEntity> pubSsdrHmEntityList = pubSsdrHmEntityMapper.querySdpPhone(querySdpPhoneVo);
            if(null != pubSsdrHmEntityList && pubSsdrHmEntityList.size() > 0){
                for (PubSsdrHmEntity pubSsdrHmEntity : pubSsdrHmEntityList) {
                    PubSsdrHmEntityModel model = pubSsdrHmEntityMapper.selectQueryZt(pubSsdrHmEntity.getBh(),pubSsdrHmEntity.getOperatorTel());
                    if(null == model){
                        model = new PubSsdrHmEntityModel();
                    }
                    BeanUtils.copyProperties(pubSsdrHmEntity,model);
                    //?????? ?????????????????????????????????????????????
                    result.add(model);
                }
            }
        }
        return result;
    }

    @Override
    public Boolean sendPlaintext(MwdxSendVo mwdxSendVo, String yhm) {

        //????????????
        String sendphone = mwdxSendVo.getSendphone();
        String msgcontent = mwdxSendVo.getMsgcontent();
        Integer sendtype = mwdxSendVo.getSendtype();
        if(StringUtil.isBlank(sendphone) || sendphone.trim().length() == 0){
            //??????????????????
            return false;
        }
        //?????????????????????????????????
        Integer[] closeTypes = {1,4,5,6,7};
        if(null == sendtype || Arrays.asList(closeTypes).contains(sendtype)){
            //???????????? ?????? ????????????????????????????????????????????????
            return false;
        }
        //??????????????????????????????????????????
        if(StringUtil.isBlank(msgcontent)){
            //??????????????????
            return false;
        }
        //????????????????????????
        String regex = "^((1[3-8]))\\d{9}$";
        String phone = mwdxSendVo.getSendphone();
        if(phone.length() != 11){
            return false;
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        boolean isMatch = m.matches();
        if(!isMatch){
            return false;
        }
        if(msgcontent.indexOf("???") == 0 && msgcontent.indexOf("???") > 1){
            //????????????
        }else {
            //???????????? ??????????????????
            return false;
        }


        //????????????
        PubYysdJbEntity pubYysdJbEntity = pubYysdJbEntityMapper.selectByPrimaryKey(mwdxSendVo.getYysdbh());
        //????????????????????????
        String writePath = "E:\\N_ftp\\out\\plaintextMsg\\sendRequest";
        XMLObjUtil.isChartPathExit(writePath);
        //??????????????????
        PubMwdxSendEntity sendEntity = new PubMwdxSendEntity();
        sendEntity.setCratetime(new Date());
        sendEntity.setSendphone(mwdxSendVo.getSendphone());
        if (pubYysdJbEntity!=null){
        sendEntity.setYysdbh(pubYysdJbEntity.getYysdbh());}
        sendEntity.setMsgcontent(mwdxSendVo.getMsgcontent());
        sendEntity.setSendstatus(0);
        sendEntity.setSendtype(mwdxSendVo.getSendtype());
        sendEntity.setFybh(mwdxSendVo.getFybh());
        sendEntity.setAjxh(mwdxSendVo.getAjxh());
        sendEntity.setSsdrbh(mwdxSendVo.getSsdrbh());
        sendEntity.setFgbh(mwdxSendVo.getFgbh());
        //??????????????????
        String uid = UUID.randomUUID().toString();
        String[] urlLis = mwdxSendVo.getUrlLis();
        List<String> urlList = new ArrayList<>();
        if (null!=urlLis && urlLis.length>0){
            //?????????????????????
            sendEntity.setCusid(uid);
            for (String urlLi : urlLis) {
                if (StringUtil.isNotBlank(urlLi)){
                    urlList.add(urlLi);
                }
            }
        }
        if (null!= urlList && urlList.size()>0){
            //0 ?????????  1 ?????????
            sendEntity.setFwzt(0);
        }

        savePubMwdxSendEntity(sendEntity);

        //??????xml??????
        PlaintextMsgRequestXml requestXml = new PlaintextMsgRequestXml();
        requestXml.setMwdxId(sendEntity.getId());
        requestXml.setMsgContent(sendEntity.getMsgcontent());//????????????
        requestXml.setTargetTel(sendEntity.getSendphone());//????????????
        requestXml.setYysdbh(mwdxSendVo.getYysdbh());//????????????????????????
        requestXml.setUuid(uid);//????????????
        if (null!= urlList && urlList.size()>0){
            requestXml.setUrlList(urlList);
            for (String s : urlList) {
                PubZybInfoEntity zybInfoEntity = new PubZybInfoEntity();
                //??????????????????
                //????????????  1:????????????
                zybInfoEntity.setYwlx(1);
                //??????idi
                zybInfoEntity.setYwid(uid);
//                zybInfoEntity.setZymc(originalFilename);
                zybInfoEntity.setZyurl(s);
                savePubZybInfoEntity(zybInfoEntity);

            }
        }

        String wirteFilePath = writePath + "\\"+xmlPrefix+ UUID.randomUUID().toString() + ".xml";
        XMLObjUtil.convertToXml(requestXml,wirteFilePath);
        return true;
    }

    /**
     * ????????????????????????
     */
    @Override
    public PubMwdxSendEntity pubMwdxSendEntity(String cusid) {
            PubMwdxSendEntity pubMwdxSendEntity = pubMwdxSendEntityMapper.pubMwdxSendEntity(cusid);
            return pubMwdxSendEntity;
    }

    /**
     * ????????????
     * @param zybInfoEntity
     * @return
     */
    @Override
    public String uploadFile(MultipartFile file,PubZybInfoEntity zybInfoEntity) {
        List<String> result = new ArrayList<>();
        String url;
        String originalFilename;
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = format.format(date);
        String path = "E:\\insdpt\\uploadFile\\"+format1+"\\";
        XMLObjUtil.isChartPathExit(path);
        //?????????+??????
        originalFilename = file.getOriginalFilename();
        //????????????
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("." ));
        String s = UUID.randomUUID().toString();
        url = format1+ "\\" + s + suffix;
        File file1 = new File(path + s + suffix);
        try {
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //??????????????????enter????????????
        try {
            String path1 = "E:\\outsdpt\\uploadFile\\"+format1+"\\";
            XMLObjUtil.isChartPathExit(path1);
            InputStream inputStream = new FileInputStream(path + s + suffix);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            OutputStream out = new FileOutputStream(path1 + s + suffix);
            out.write(bytes);
            inputStream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;

//        for(String str : dsrwslbArray){
//            if(str.contains("_")){
//                String[] yysdbhBh = str.split("_");
//                PubYysdWsEntity pubYysdWsEntity = pubYysdWsEntityMapper.selectByYysdbhAndBh(Integer.valueOf(yysdbhBh[0]), Integer.valueOf(yysdbhBh[1]));
//                String filePath = new String(pubYysdWsEntity.getWsnr());
//                if(!"".equals(filePath)){
//                    File tempFile = new File(filePath);
//                    FileUtil.copyFile(filePath,path);
//                    FileUtil.copyFile(filePath,path1);
//                    result.add(format1 + "\\" + tempFile.getName());
//                }
//            }
//
//        }

//        return result;
    }

    @Override
    public Integer savePubDxtzInfoEntity(PubDxtzInfoEntity pubDxtzInfoEntity) {
       pubDxtzInfoEntityMapper.insert(pubDxtzInfoEntity);
       return pubDxtzInfoEntity.getDxtzid();
    }

    @Override
    public Integer savePubZybInfoEntity(PubZybInfoEntity pubZybInfoEntity) {
         zybInfoMapper.insert(pubZybInfoEntity);
        return pubZybInfoEntity.getId();
    }

    @Override
    public Integer savePubMwdxSendEntity(PubMwdxSendEntity pubMwdxSendEntity) {
        pubMwdxSendEntityMapper.insert(pubMwdxSendEntity);
        return pubMwdxSendEntity.getId();
    }

    @Override
    public void HandlerLinkStatus() {
        logger.error("???????????????????????????????????????");
        String LINSTATUSDIR="E:\\outsdpt\\shortLinkStatus";
        XMLObjUtil.isChartPathExit(LINSTATUSDIR);

        File[] filesList = XMLObjUtil.getCurFilesList(LINSTATUSDIR);
        if (null != filesList && filesList.length > 0) {
            for (File file : filesList) {
                try{
                    DxlinkStatuses dxlinkStatuses=(DxlinkStatuses)XMLObjUtil.convertXmlFileToObject(DxlinkStatuses.class,file.getPath());
                    if(dxlinkStatuses.getDXFWINFO()==null){
                        logger.error(file.getPath()+"?????????");
                        file.delete();
                        continue;
                    }
                    for(DxlinkStatus dxlinkStatus:dxlinkStatuses.getDXFWINFO()){
                        PubDxtzInfoEntity pubDxtzInfoEntity=pubDxtzInfoEntityMapper.selectById(Integer.valueOf(dxlinkStatus.getDxtzid()));
                        if(pubDxtzInfoEntity==null){
                            continue;
                        }
                        pubDxtzInfoEntity.setFwzt(1);
                        pubDxtzInfoEntityMapper.updateByPrimaryKey(pubDxtzInfoEntity);
                    }

                    //???????????????????????? ??????????????????
                    file.delete();
                }catch (Exception e){
                    logger.error(simpleDateFormat.format(new Date())+"??????"+file.getPath()+"????????????");
                }
            }
        }
    }

    @Override
    public void HandlerMsgStatus() {
        logger.error("???????????????????????????????????????");
        String MSGSTATUSDIR="E:\\outsdpt\\shortMsgStatus";
        XMLObjUtil.isChartPathExit(MSGSTATUSDIR);

        File[] filesList = XMLObjUtil.getCurFilesList(MSGSTATUSDIR);
        if (null != filesList && filesList.length > 0) {
            for (File file : filesList) {
                try{
                    DxStatuses dxStatuses=(DxStatuses)XMLObjUtil.convertXmlFileToObject(DxStatuses.class,file.getPath());
                    if(dxStatuses.getDXZTINFO()==null){
                        logger.error(file.getPath()+"?????????");
                        file.delete();
                        continue;
                    }
                    for(DxStatus dxStatus:dxStatuses.getDXZTINFO()){
                        PubDxtzInfoEntity pubDxtzInfoEntity=pubDxtzInfoEntityMapper.selectById(Integer.valueOf(dxStatus.getDxtzid()));
                        if(pubDxtzInfoEntity==null){
                            continue;
                        }
                        if("-1".equals(dxStatus.getCode())){
                            pubDxtzInfoEntity.setSendstate(2);
                        }
                        if("1".equals(dxStatus.getCode())){
                            pubDxtzInfoEntity.setSendstate(1);
                        }
                        pubDxtzInfoEntityMapper.updateByPrimaryKey(pubDxtzInfoEntity);
                    }

                    //???????????????????????? ??????????????????
                    file.delete();
                }catch (Exception e){
                    logger.error(simpleDateFormat.format(new Date())+"??????"+file.getPath()+"????????????");
                }
            }
        }
    }


}
