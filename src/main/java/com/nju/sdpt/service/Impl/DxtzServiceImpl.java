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

        //查询工单
        PubYysdJbEntity pubYysdJbEntity = pubYysdJbEntityMapper.selectByPrimaryKey(sendShortMsgVo.getYysdbh());
        //查询当事人
        //查询受送达人信息和号码信息
        PubYysdSsdrEntityKey pubYysdSsdrKey = new PubYysdSsdrEntityKey();
        pubYysdSsdrKey.setYysdbh(sendShortMsgVo.getYysdbh());
        pubYysdSsdrKey.setSsdrbh(sendShortMsgVo.getSsdrbh());
        PubYysdSsdrEntity ssdrEntity = pubYysdSsdrEntityMapper.selectByPrimaryKey(pubYysdSsdrKey);
        //查询短信模板
        PubDxmbInfoEntity pubDxmbInfoEntity = pubDxmbInfoEntityMapper.selectByPrimaryKey(sendShortMsgVo.getTemplateId());

        PubYysdRyxxEntity byYhdm = pubYysdRyxxEntityMapper.findByYhdm(yhm);
        //定义文件写入目录
        String writePath = "E:\\insdpt\\shortMsg";
        XMLObjUtil.isChartPathExit(writePath);

        List<Integer> targetTelBhs = sendShortMsgVo.getTargetTelBhs();
        //获取路径
        String[] urlLis = sendShortMsgVo.getUrlLis();
        List<String> urlList = new ArrayList<>();
        if (null!=urlLis && urlLis.length>0){
            for (String urlLi : urlLis) {
                if (StringUtil.isNotBlank(urlLi)){
                    urlList.add(urlLi);
                }
            }
        }

        //获取法院名称
        FYEnum fyEnum = FYEnum.getFyByFybh(byYhdm.getFybh());
        String fymc = "天津法院";
        if(null != fyEnum){
            fymc = fyEnum.getName();
        }
        String yhmc = byYhdm.getYhmc();
        for (Integer targetTelBh : targetTelBhs) {
            //查询号码
            PubSsdrHmEntity pubSsdrHmEntity = pubSsdrHmEntityMapper.selectByPrimaryKey(targetTelBh);


            //封装短信通知记录数据 并保存数据库
            PubDxtzInfoEntity pubDxtzInfoEntity = new PubDxtzInfoEntity();
            String msgContent = "";
            pubDxtzInfoEntity.setSdybh(byYhdm.getYhbh());
            pubDxtzInfoEntity.setYysdbh(sendShortMsgVo.getYysdbh());
            pubDxtzInfoEntity.setDsrbgh(ssdrEntity.getSsdrbh());
            pubDxtzInfoEntity.setHmbh(targetTelBh);
            //目标号码 web tel
            pubDxtzInfoEntity.setWebtel(pubSsdrHmEntity.getShowTel());
            //实际接收号码 targrt tel
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
                //0 未访问  1 已访问
                pubDxtzInfoEntity.setFwzt(0);
            }
            if(null != ssdrEntity.getWsnum()) {
                pubDxtzInfoEntity.setWsnum(ssdrEntity.getWsnum());
            }
            Integer i= savePubDxtzInfoEntity(pubDxtzInfoEntity);
            msgContent = pubDxmbInfoEntity.getMbnr(); //封装短信内容
            List<ShortMsgParamObj> paramObjList = sendShortMsgVo.getParamObjList();
            boolean shortLink = false; //是否存在短链接参数
            if(null != paramObjList && paramObjList.size() > 0){
                for (ShortMsgParamObj paramObj : paramObjList) {
                    if (paramObj.getParamName().equals("文书详情")){
                        String link="http://tjsd.tjcourt.gov.cn:8091/dxsd?uuid="+sendShortMsgVo.getYysdbh()+"_"+i;
                        paramObj.setParamValue(link);
                        shortLink = true;
                    }
                    msgContent = msgContent.replace("{{"+paramObj.getParamName()+"}}",paramObj.getParamValue());
                }
            }
            if(!shortLink){
                //不存在短链接  置空urlList
                urlList = new ArrayList<>();
            }


            // 保存文书类型
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
                    //保存到资源表
                    //业务类型  1:短信通知
                    zybInfoEntity.setYwlx(1);
                    //业务idi
                    zybInfoEntity.setYwid(String.valueOf(i));
//                    zybInfoEntity.setZymc(originalFilename);
                    zybInfoEntity.setZyurl(s);
                    savePubZybInfoEntity(zybInfoEntity);

                }
            }
            pubDxtzInfoEntity.setMsgcontent(msgContent);
            pubDxtzInfoEntityMapper.updateByPrimaryKey(pubDxtzInfoEntity);
            //生成xml文件 放入ftp目录  进行内外网数据交互
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
            //index: 0 => 模板ID
            //index: 1 => 模板绑定固话
            //index: 1+ => 其他具体参数
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
            logger.info("[短信下发XML]dxtzId:{},XML文件名:{}",requestXml.getDxtzId(),wirteFilePath);
            //案件序号  法院编号  当事人姓名  类型  备注  创建人
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
                //格式化时间
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
                //格式化时间
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
               //维护送达结果 送达结果 0: 待送达  1：送达成功  2：送达失败  默认0)
               updateEntity.setSdjg(dxtzEditSdjgVo.getSdjg());
                if(dxtzEditSdjgVo.getSdjg()!=null&&dxtzEditSdjgVo.getSdjg()>0){
                    updateEntity.setSubmittime(new Date());
                }
               //查询工单记录
               PubYysdJbEntity pubYysdJbEntity = pubYysdJbEntityMapper.selectByPrimaryKey(entity.getYysdbh());

               if(null != pubYysdJbEntity){
                   //维护来院送达结果时
                   //送达成功 -》 更改工单的送达结果相关字段
                   if(Objects.equals(1,dxtzEditSdjgVo.getSdjg())){
                       pubYysdJbEntity.setDxsd("Y");
//                    pubYysdJbEntity.setSdjg("Y");
                   }else {
                       pubYysdJbEntity.setDxsd("N");
                   }
                   pubYysdJbEntityMapper.updateByPrimaryKeySelective(pubYysdJbEntity);
               }
               pubDxtzInfoEntityMapper.updateByPrimaryKeySelective(updateEntity);

               //加载当事人送达结果
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
                    //查询 最新短信下发状态和最新访问状态
                    result.add(model);
                }
            }
        }
        return result;
    }

    @Override
    public Boolean sendPlaintext(MwdxSendVo mwdxSendVo, String yhm) {

        //校验数据
        String sendphone = mwdxSendVo.getSendphone();
        String msgcontent = mwdxSendVo.getMsgcontent();
        Integer sendtype = mwdxSendVo.getSendtype();
        if(StringUtil.isBlank(sendphone) || sendphone.trim().length() == 0){
            //下发号码异常
            return false;
        }
        //关闭指定类型通知类短信
        Integer[] closeTypes = {1,4,5,6,7};
        if(null == sendtype || Arrays.asList(closeTypes).contains(sendtype)){
            //类型为空 或者 指定类型短信通知限制，不进行发送
            return false;
        }
        //校验下发内容是否存在短信签名
        if(StringUtil.isBlank(msgcontent)){
            //下发内容为空
            return false;
        }
        //校验号码是否正确
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
        if(msgcontent.indexOf("【") == 0 && msgcontent.indexOf("】") > 1){
            //校验通过
        }else {
            //下发内容 缺少短信签名
            return false;
        }


        //查询工单
        PubYysdJbEntity pubYysdJbEntity = pubYysdJbEntityMapper.selectByPrimaryKey(mwdxSendVo.getYysdbh());
        //定义文件写入目录
        String writePath = "E:\\N_ftp\\out\\plaintextMsg\\sendRequest";
        XMLObjUtil.isChartPathExit(writePath);
        //保存短信记录
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
        //获取路径数组
        String uid = UUID.randomUUID().toString();
        String[] urlLis = mwdxSendVo.getUrlLis();
        List<String> urlList = new ArrayList<>();
        if (null!=urlLis && urlLis.length>0){
            //自定义唯一标识
            sendEntity.setCusid(uid);
            for (String urlLi : urlLis) {
                if (StringUtil.isNotBlank(urlLi)){
                    urlList.add(urlLi);
                }
            }
        }
        if (null!= urlList && urlList.size()>0){
            //0 未访问  1 已访问
            sendEntity.setFwzt(0);
        }

        savePubMwdxSendEntity(sendEntity);

        //生成xml文件
        PlaintextMsgRequestXml requestXml = new PlaintextMsgRequestXml();
        requestXml.setMwdxId(sendEntity.getId());
        requestXml.setMsgContent(sendEntity.getMsgcontent());//短信内容
        requestXml.setTargetTel(sendEntity.getSendphone());//接收号码
        requestXml.setYysdbh(mwdxSendVo.getYysdbh());//预约送达人员编号
        requestXml.setUuid(uid);//唯一标识
        if (null!= urlList && urlList.size()>0){
            requestXml.setUrlList(urlList);
            for (String s : urlList) {
                PubZybInfoEntity zybInfoEntity = new PubZybInfoEntity();
                //保存到资源表
                //业务类型  1:短信通知
                zybInfoEntity.setYwlx(1);
                //业务idi
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
     * 查询短信发送状态
     */
    @Override
    public PubMwdxSendEntity pubMwdxSendEntity(String cusid) {
            PubMwdxSendEntity pubMwdxSendEntity = pubMwdxSendEntityMapper.pubMwdxSendEntity(cusid);
            return pubMwdxSendEntity;
    }

    /**
     * 上传文件
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
        //文件名+后缀
        originalFilename = file.getOriginalFilename();
        //获取后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("." ));
        String s = UUID.randomUUID().toString();
        url = format1+ "\\" + s + suffix;
        File file1 = new File(path + s + suffix);
        try {
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //将文件复制到enter文件夹中
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
        logger.error("开始处理短信链接访问状态！");
        String LINSTATUSDIR="E:\\outsdpt\\shortLinkStatus";
        XMLObjUtil.isChartPathExit(LINSTATUSDIR);

        File[] filesList = XMLObjUtil.getCurFilesList(LINSTATUSDIR);
        if (null != filesList && filesList.length > 0) {
            for (File file : filesList) {
                try{
                    DxlinkStatuses dxlinkStatuses=(DxlinkStatuses)XMLObjUtil.convertXmlFileToObject(DxlinkStatuses.class,file.getPath());
                    if(dxlinkStatuses.getDXFWINFO()==null){
                        logger.error(file.getPath()+"无信息");
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

                    //最后需要删除文件 避免多次读取
                    file.delete();
                }catch (Exception e){
                    logger.error(simpleDateFormat.format(new Date())+"文件"+file.getPath()+"解析失败");
                }
            }
        }
    }

    @Override
    public void HandlerMsgStatus() {
        logger.error("开始处理短信链接访问状态！");
        String MSGSTATUSDIR="E:\\outsdpt\\shortMsgStatus";
        XMLObjUtil.isChartPathExit(MSGSTATUSDIR);

        File[] filesList = XMLObjUtil.getCurFilesList(MSGSTATUSDIR);
        if (null != filesList && filesList.length > 0) {
            for (File file : filesList) {
                try{
                    DxStatuses dxStatuses=(DxStatuses)XMLObjUtil.convertXmlFileToObject(DxStatuses.class,file.getPath());
                    if(dxStatuses.getDXZTINFO()==null){
                        logger.error(file.getPath()+"无信息");
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

                    //最后需要删除文件 避免多次读取
                    file.delete();
                }catch (Exception e){
                    logger.error(simpleDateFormat.format(new Date())+"文件"+file.getPath()+"解析失败");
                }
            }
        }
    }


}
