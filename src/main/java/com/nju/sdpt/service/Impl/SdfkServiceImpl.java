package com.nju.sdpt.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.nju.sdpt.constant.SdptConstants;
import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.entity.*;
import com.nju.sdpt.mapper.*;
import com.nju.sdpt.model.GddcModel;
import com.nju.sdpt.model.PubYysdSsdrModel;
import com.nju.sdpt.model.SsdrGddcModel;
import com.nju.sdpt.model.sdfk.*;
import com.nju.sdpt.service.DxtzService;
import com.nju.sdpt.service.EmsService;
import com.nju.sdpt.service.LogService;
import com.nju.sdpt.service.SdfkService;
import com.nju.sdpt.util.DateUtil;
import com.nju.sdpt.util.FydmUtil;
import com.nju.sdpt.util.HttpUtil;
import com.nju.sdpt.util.StringUtil;
import com.nju.sdpt.util.convertor.TagConvertorUtil;
import com.nju.sdpt.viewobject.ConfirmSdfkVo;
import com.nju.sdpt.viewobject.KdcxVO;
import com.nju.sdpt.viewobject.MwdxSendVo;
import io.swagger.models.auth.In;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@Service
public class SdfkServiceImpl implements SdfkService {

    @Autowired
    private PubYysdJbEntityMapper pubYysdJbEntityMapper;
    @Autowired
    private PubYysdSsdrEntityMapper pubYysdSsdrEntityMapper;

    @Autowired
    private PubYysdWsEntityMapper pubYysdWsEntityMapper;
    @Autowired
    private PubSsdrHmEntityMapper pubSsdrHmEntityMapper;
    @Autowired
    private PubYysdSsdrdzEntityMapper pubYysdSsdrdzEntityMapper;

    @Autowired
    private PubWebCallInfoEntityMapper pubWebCallInfoEntityMapper;
    @Autowired
    private PubDxtzInfoEntityMapper pubDxtzInfoEntityMapper;
    @Autowired
    private PubLylqInfoEntityMapper pubLylqInfoEntityMapper;
    @Autowired
    private KdtdMapper kdtdMapper;
    @Autowired
    private PubYysdRyxxEntityMapper pubYysdRyxxEntityMapper;
    @Autowired
    private PubZjsdInfoEntityMapper pubZjsdInfoEntityMapper;

    @Autowired
    private DxtzService dxtzService;
    @Autowired
    LogService logService;
    @Resource
    EmsService emsService;
    @Resource
    PubLylqSdhzEntityMapper lylqSdhzEntityMapper;
    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;

    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public List<SdfkDataModel> getSdfkData(Integer yysdbh,List<Integer> ssdrbhs) {

        DynamicDataSource.router(SDPT_FYDM);
        List<SdfkDataModel> sdfkDataModelList = new ArrayList<>();

        //??????????????????
        PubYysdJbEntity jbEntity = pubYysdJbEntityMapper.selectByPrimaryKey(yysdbh);
        if(jbEntity.getLaay() == null) {
            jbEntity.setLaay("");
        }
        if(jbEntity.getCbr() == null) {
            jbEntity.setCbr("");
        }
        String sdcgfs = "";

        String zasdjg = "";
        if(jbEntity.getSdjg() != null && jbEntity.getSdjg().equals("Y")) {
            zasdjg = "????????????";
        } else if(jbEntity.getSdjg() != null && jbEntity.getSdjg().equals("N")) {
            zasdjg = "????????????";
        }
        int count = 0;
        int countFail = 0;
        if(null != jbEntity){
            String fybh = jbEntity.getFybh();
            String sdjg = jbEntity.getSdjg();
            String sdjgStr;
            if(Objects.equals(sdjg,"Y") || Objects.equals(sdjg, "FY")){
                sdjgStr = "????????????";
            }else if(Objects.equals(sdjg,"N") || Objects.equals(sdjg, "FN")){
                sdjgStr = "????????????";
            }else {
                //?????????????????? - ???????????????
//                return sdfkDataModelList;
                sdjgStr = "?????????";
            }
            //??????????????????
            String gdryxm = jbEntity.getGdryxm();
            PubYysdRyxxEntity ryxxEntity = pubYysdRyxxEntityMapper.findByYhdm(gdryxm);
            String sdzy = "";
            if(null != ryxxEntity){
                sdzy =  ryxxEntity.getYhmc() + "???" + ryxxEntity.getLxdh();
            }
            //?????????????????????
            List<PubYysdSsdrModel> pubYysdSsdrModelList = new LinkedList<>();
            if(ssdrbhs==null||ssdrbhs.size()==0){
                pubYysdSsdrModelList = pubYysdSsdrEntityMapper.selectByYysdbh(yysdbh);
            }else {
                for (Integer ssdrbh : ssdrbhs){
                    PubYysdSsdrModel pubYysdSsdrModel = pubYysdSsdrEntityMapper.findByPrimaryKey(yysdbh,ssdrbh);
                    if(pubYysdSsdrModel!=null){
                        pubYysdSsdrModelList.add(pubYysdSsdrModel);
                    }
                }
            }

            if(null != pubYysdSsdrModelList && pubYysdSsdrModelList.size() > 0){
                for (PubYysdSsdrModel ssdrModel : pubYysdSsdrModelList) {
                    DynamicDataSource.router(SDPT_FYDM);
                    Integer ssdrbh = ssdrModel.getSsdrbh();
                    SdfkDataModel dataModel = new SdfkDataModel();
                    String ssdrsdjg = "";
                    dataModel.setFkzt(jbEntity.getFkzt());
                    Integer ssdrModelSdjg = ssdrModel.getSdjg();
                    if (Objects.equals(ssdrModelSdjg,1)){
                        ++ count;
                        ssdrsdjg = "??????";
                        if(count == 1) {
                            zasdjg += "(" + ssdrModel.getSsdrmc() + ":" + "?????? ;";
                        } else {
                            zasdjg += ssdrModel.getSsdrmc() + ":" + "?????? ;";
                        }
                    } else  if (Objects.equals(ssdrModelSdjg,2)){
                        ++ countFail;
                        if(count == 0) {
                            zasdjg += "(" + ssdrModel.getSsdrmc() + ":" + "?????? ;";
                        } else {
                            zasdjg +=  ssdrModel.getSsdrmc() + ":" + "?????? ;";
                        }
                        ssdrsdjg = "??????";
                    } else if (Objects.equals(ssdrModelSdjg,3)){
                        if(count == 0) {
                            zasdjg += "(" + ssdrModel.getSsdrmc() + ":" + "????????? ;";
                        } else {
                            zasdjg +=  ssdrModel.getSsdrmc() + ":" + "????????? ;";
                        }
                        ssdrsdjg = "?????????";
                    }else {
                        if(count == 0) {
                            zasdjg += "(" + ssdrModel.getSsdrmc() + ":" + "????????? ;";
                        } else {
                            zasdjg +=  ssdrModel.getSsdrmc() + ":" + "????????? ;";
                        }
                        ssdrsdjg = "?????????";
                    }
                    dataModel.setSsdrSdjg(ssdrsdjg);
                    dataModel.setYysdbz(jbEntity.getYysdbz()==null?"":jbEntity.getYysdbz());
                    dataModel.setYysdbh(yysdbh);
                    dataModel.setSsdrbh(ssdrbh);
                    dataModel.setAnyou(jbEntity.getLaay());
                    dataModel.setAh(jbEntity.getAh());
                    dataModel.setCbfgmc(jbEntity.getCbr());
                    dataModel.setYyrxm(jbEntity.getYyrxm());
                    dataModel.setRemark(jbEntity.getBz());
                    dataModel.setBmmc(jbEntity.getBmmc());
                    dataModel.setSsdrMc(ssdrModel.getSsdrmc());
                    dataModel.setSfzHm(ssdrModel.getSfzhm());
                    dataModel.setSdjg(sdjgStr);
                    dataModel.setSdzyName(sdzy);
                    dataModel.setSsdw(ssdrModel.getSsdrssdw());
                    dataModel.setYysj(DateUtil.format(jbEntity.getYysj(),DateUtil.datetimeFormat));
                    dataModel.setFksj(jbEntity.getSdsj()==null?"":DateUtil.format(jbEntity.getSdsj(),DateUtil.datetimeFormat));
                    dataModel.setKtsj(jbEntity.getKtsj()==null?"":jbEntity.getKtsj());
                    dataModel.setSdzq(jbEntity.getSdsj()==null?null:Integer.parseInt((DateUtil.getDiffDays(jbEntity.getSdsj(),jbEntity.getYysj())+1)+"") );
                    //TODO: ????????????
                    List<String> wsList = pubYysdWsEntityMapper.selectWslbByyysdbhAndSrdrbh(yysdbh, dataModel.getSsdrbh());
                    if(null != wsList){
                        dataModel.setSdwsMcs(StringUtil.splitJoint(wsList,"???"));
                    }
                    //TODO: ????????????
                    List<HashMap<String, String>> showTelMapList = pubSsdrHmEntityMapper.selectDhListMapByYysdbhAndSsdrbh(yysdbh, ssdrbh);
                    dataModel.setWebTels(TagConvertorUtil.addTag(showTelMapList,"tel"));
                    List<HashMap<String, String>> showQrelMapList = pubSsdrHmEntityMapper.selectQrDhListMapByYysdbhAndSsdrbh(yysdbh, ssdrbh);
                    dataModel.setSdqrWebTels(TagConvertorUtil.addTag(showQrelMapList,"tel"));
                    //TODO???????????????
                    List<HashMap<String,String>> dzMapList = pubYysdSsdrdzEntityMapper.selectDzMapListByYysdbhAndSsdrbh(yysdbh,ssdrbh);
                    dataModel.setAddress(TagConvertorUtil.addTag(dzMapList,"dz"));
                    //TODO: ????????????
                    dataModel.setAddressQrs(ssdrModel.getSfqssddzqrs()==null?"??????":(ssdrModel.getSfqssddzqrs()==1?"???":"???"));
                    dataModel.setDzshQr(ssdrModel.getSftydzsd()==null?"??????":(ssdrModel.getSftydzsd()==1?"???":"???"));
                    //TODO ???????????????????????? ?????????????????????????????????
                    List<PubZjsdInfoEntity> zjsdInfoEntityList = pubZjsdInfoEntityMapper.selectListByYysdbhAndSsdrbh(yysdbh,ssdrbh);
                    List<ZjsdResultModel> zjsdResultModels = new ArrayList<>();
                    if(null != zjsdInfoEntityList && zjsdInfoEntityList.size() > 0){
                        for (PubZjsdInfoEntity pubZjsdInfoEntity : zjsdInfoEntityList) {
                            ZjsdResultModel resultModel = new ZjsdResultModel();
                            String zjsdSdjgStr = "??????????????????";
                            if(Objects.equals(pubZjsdInfoEntity.getZjsdjg(),1)){
                                zjsdSdjgStr = "????????????";
                            }else if(Objects.equals(pubZjsdInfoEntity.getZjsdjg(),2)){
                                zjsdSdjgStr = "????????????";
                            }
                            resultModel.setSdjg(zjsdSdjgStr);
                            resultModel.setSmlqsj(pubZjsdInfoEntity.getSmsj());
                            resultModel.setZjsdAddress(pubZjsdInfoEntity.getSddz());
                            resultModel.setSmjg(pubZjsdInfoEntity.getQszt()==null?"":pubZjsdInfoEntity.getQszt().toString());
                            zjsdResultModels.add(resultModel);
                        }
                    }
                    dataModel.setZjsdResultModels(zjsdResultModels);
                    dataModel.setDhsdResultModelList(getSdfkDhsdResult(yysdbh,ssdrbh));
                    dataModel.setDzsdResultModelList(getDzsdResult(yysdbh,ssdrbh));
                    dataModel.setLylqResultModelList(getLylqResult(yysdbh,ssdrbh));
                    for(DhsdResultModel dhsdResultModel : dataModel.getDhsdResultModelList()) {
                        if(("??????".equals(dhsdResultModel.getSdjg()))) {
                            sdcgfs = "??????????????????;";
                            break;
                        }
                    }
                    for(DzsdResultModel dzsdResultModel : dataModel.getDzsdResultModelList()) {
                        if((dzsdResultModel.getSdjg()!= null && dzsdResultModel.getSdjg() == 1) ||(dzsdResultModel.getFwState() != null && dzsdResultModel.getFwState() == 1)) {
                            sdcgfs += "??????????????????;";
                            break;
                        }
                    }
                    for(ZjsdResultModel zjsdResultModel : dataModel.getZjsdResultModels()) {
                        if(("????????????".equals(zjsdResultModel.getSdjg()))) {
                            sdcgfs += "??????????????????;";
                            break;
                        }
                    }
                    for(LylqResultModel lylqResultModel : dataModel.getLylqResultModelList()) {
                        if(lylqResultModel.getSdjg()!= null && lylqResultModel.getSdjg() == 1) {
                            sdcgfs += "??????????????????;";
                            break;
                        }
                    }
                    dataModel.setSdcgfs(sdcgfs);
                    sdfkDataModelList.add(dataModel);
                    sdcgfs = "";
                }
                //??????????????? TODO ??????EMS??????
                try{
                    DynamicDataSource.router(FydmUtil.getFydmByFybh(fybh));
                    List<PubKdtdEntity> kdtdEntityList = kdtdMapper.findGdByYysdbhAndyysj(jbEntity.getYysdbh(),simpleDateFormat.format(jbEntity.getYysj()));

                    if(null != kdtdEntityList){
                        Map<String,KdcxVO[]> wljlMap = new HashMap<>();
                        for (PubKdtdEntity pubKdtdEntity : kdtdEntityList) {
                            String kddh = pubKdtdEntity.getKddh();
                            if(!" ".equals(kddh)){
                                String targetUrl = "http://130.1.1.150:18088/kdcx.aj?kddh="+kddh+"&fybh="+fybh;
                                String result = HttpUtil.doGet(targetUrl);
                                if(StringUtil.isNotBlank(result)){
                                    try {
                                        ObjectMapper objectMapper1= new ObjectMapper();
                                        KdcxVO[] kdcxVOS = objectMapper1.readValue(result,KdcxVO[].class);
                                        wljlMap.put(kddh,kdcxVOS);
                                    }catch (Exception ex){
                                        ex.printStackTrace();
                                    }
                                }
                            }
                        }


                        for (SdfkDataModel dataModel : sdfkDataModelList) {
                            List<EmsResultModel> emsResultModelList = new ArrayList<>();
                            for (PubKdtdEntity kdtdEntity : kdtdEntityList) {
                                if(null == kdtdEntity){
                                    continue;
                                }
                                if(Objects.equals(kdtdEntity.getDsrbh(),dataModel.getSsdrbh())||kdtdEntity.getSjrxm().trim().contains(dataModel.getSsdrMc().trim())){
                                    EmsResultModel model = new EmsResultModel();
                                    model.setJsrdz(kdtdEntity.getSjrdz());
                                    model.setKddh(kdtdEntity.getKddh());
                                    model.setYjsj(kdtdEntity.getScrq());
                                    model.setSdjg(kdtdEntity.getSdjg());
                                    model.setKdcxVO(wljlMap.get(kdtdEntity.getKddh()));
                                    emsResultModelList.add(model);
                                }
                            }
                            dataModel.setEmsResultModelList(emsResultModelList);
                            for(EmsResultModel emsResultModel : emsResultModelList) {
                                if(("??????".equals(emsResultModel.getSdjg()))) {
                                    dataModel.setSdcgfs(dataModel.getSdcgfs() + "ems????????????");
                                    break;
                                }
                            }
                        }
                    }
                }catch (Exception ex){
                   ex.printStackTrace();
                }
            }
            if(count == pubYysdSsdrModelList.size()) {
                zasdjg = zasdjg.substring(0,4);
            }
            else if(countFail == pubYysdSsdrModelList.size()) {
                zasdjg = zasdjg.substring(0,4);
            }
            else {
                zasdjg += ")";
            }
            for (SdfkDataModel sdfkDataModel : sdfkDataModelList) {
                sdfkDataModel.setZasdjg(zasdjg);

            }
        }
        return sdfkDataModelList;
    }

    @Override
    public void confirmSdfk(ConfirmSdfkVo confirmSdfkVo) {
        if(null != confirmSdfkVo){

            Boolean confirmState = confirmSdfkVo.getConfirmState();
            Integer yysdbh = confirmSdfkVo.getYysdbh();
            if(null == confirmState || null == yysdbh){
                return;
            }

            PubYysdJbEntity jbEntity = pubYysdJbEntityMapper.selectByPrimaryKey(yysdbh);
            if(null == jbEntity){
                return;
            }

            PubYysdJbEntity updateJb = new PubYysdJbEntity();
            updateJb.setYysdbh(yysdbh);
            String shortMsgContent;
            Integer sendType;
            Integer logtype;
            if(confirmState){
                //???????????????????????????
                updateJb.setFkzt(1); //?????????-?????????
                shortMsgContent = SdptConstants.SYSTEM_MWDX_DXMB.SDFK_QRMB;
                sendType = SdptConstants.MWDX_SEND_TYPE.SDFK_QR;
                logtype = SdptConstants.LOG_TYPE.SDFKQR.getTypeNum();
            }else {
                //????????????????????????
                //?????????????????????????????? ??????
                updateJb.setFkzt(2); //?????????-?????????
                updateJb.setSdjg(null);
                shortMsgContent = SdptConstants.SYSTEM_MWDX_DXMB.SDFK_THMB;
                sendType = SdptConstants.MWDX_SEND_TYPE.SDFK_TH;
                logtype = SdptConstants.LOG_TYPE.SDFKTH.getTypeNum();
            }
            pubYysdJbEntityMapper.updateYysdJbOfSdfk(updateJb);

            //????????????????????????
            //????????????????????????
            String gdryxm = jbEntity.getGdryxm();
            PubYysdRyxxEntity ryxxEntity = pubYysdRyxxEntityMapper.findByYhdm(gdryxm);
            //????????????????????????
            String msgPhone = ryxxEntity.getLxdh();
            if(StringUtil.isNotBlank(msgPhone) && msgPhone.indexOf("1") == 0 && msgPhone.length() == 11){
                shortMsgContent = String.format(shortMsgContent,jbEntity.getAh(),yysdbh);

                MwdxSendVo mwdxSendVo = new MwdxSendVo();
                mwdxSendVo.setSendtype(sendType);
                mwdxSendVo.setMsgcontent(shortMsgContent);
                mwdxSendVo.setAjxh(jbEntity.getAjxh());
                mwdxSendVo.setFybh(jbEntity.getFybh());
                mwdxSendVo.setSendphone(msgPhone);
                mwdxSendVo.setYysdbh(yysdbh);
                dxtzService.sendPlaintext(mwdxSendVo, "");
            }
            logService.addLog(jbEntity.getAjxh(),jbEntity.getFybh(),"",logtype,"",jbEntity.getYyrxm(),jbEntity.getYysdbh());


        }
    }


    @Override
    public void addEmsmd(List<String> paths, List<String> fileNames,Integer yysdbh,Integer ssdrbh) {
        DynamicDataSource.routerToSdpt();
        PubYysdJbEntity jb = pubYysdJbEntityMapper.selectByPrimaryKey(yysdbh);
        DynamicDataSource.routerByFybh(jb.getFybh());
        List<PubKdtdEntity> pubKdtdEntities = kdtdMapper.selectByYysdbhAndDsrbh(yysdbh,ssdrbh);
        for (PubKdtdEntity pubKdtdEntity:pubKdtdEntities){
            String filepath = emsService.downloadEmsmd(pubKdtdEntity.getKdid(),jb.getFybh(),jb.getAjxh());
            if(filepath.equals("")) {
                continue;
            }
            File file = new File(filepath);
            String filename = file.getName();
            paths.add(filepath);
            fileNames.add(filename);
        }
        DynamicDataSource.routerToSdpt();
    }

    @Override
    public void addLyhz(List<String> paths, List<String> fileNames, Integer yysdbh, Integer ssdrbh) {
        List<PubLylqInfoEntity> pubLylqInfoEntities = null;
        try {
            pubLylqInfoEntities = pubLylqInfoEntityMapper.findByYysdbhAndSsdrbh(yysdbh, ssdrbh);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(pubLylqInfoEntities == null || pubLylqInfoEntities.size() == 0) {
            return;
        }
        for (PubLylqInfoEntity pubLylqInfoEntity : pubLylqInfoEntities){
            List<PubLylqSdhzEntity> pubLylqSdhzEntities = lylqSdhzEntityMapper.findBylylqid(pubLylqInfoEntity.getLylqid());
            for (PubLylqSdhzEntity pubLylqSdhzEntity : pubLylqSdhzEntities){
                paths.add(pubLylqSdhzEntity.getSdhzfolder() + "\\" + pubLylqSdhzEntity.getSdhzimage());
                fileNames.add(yysdbh+"_"+ssdrbh+"_"+pubLylqInfoEntity.getLylqid()+pubLylqSdhzEntity.getSdhzimage());
            }
        }
    }

    @Override
    public void addSdfkb(List<String> paths, List<String> fileNames, Integer yysdbh, List<Integer> ssdrs, Integer ssdrbh) throws IOException, DocumentException {
        //???????????????
        if(ssdrs == null) {
            ssdrs = new ArrayList<>();
            ssdrs.add(ssdrbh);
        }
        List<SdfkDataModel> dataList = getSdfkData(yysdbh,ssdrs);
        if(dataList==null||dataList.size()==0)
            return;
        // ??????pdf ??????
        String pdfFolder = "E:\\sdfkb\\";
        File pdfFile = new File(pdfFolder);
        String pdf = pdfFolder + UUID.randomUUID() + ".pdf";
        // 1.??????documnet??????
        Document doc = new Document(PageSize.A4, 0, 0, 25, 25);//SUPPRESS
        FileOutputStream fos = new FileOutputStream(pdf);
        PdfWriter.getInstance(doc, fos);
        // ????????????
        BaseFont baseFont = BaseFont.createFont( "STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
        // ??????????????????
        Font font = new Font(baseFont, 10, Font.NORMAL);//SUPPRESS
        Font bigFont = new Font(baseFont, 12, Font.NORMAL);//SUPPRESS
        /**
         *  ??????4?????????
         */
        doc.open();
        for(int i=0;i<dataList.size();++i) {
            PdfPTable table = new PdfPTable(4);//SUPPRESS
            table.setSplitLate(false);
            // ??????????????????
            table.setTotalWidth(new float[]{100, 100, 100, 200});//SUPPRESS
            // ??????????????????
            table.addCell(mergeCol("???????????????", font, 4, "center"));
            //?????????
            table.addCell(mergeCol("??????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getAh(), font, 3, "center"));
            //?????????
            table.addCell(mergeCol("????????????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getCbfgmc(), font, 3, "center"));

            table.addCell(mergeCol("??????????????????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getYysdbz(), font, 3, "center"));
            //?????????
            table.addCell(mergeCol("??????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getAnyou(), font, 3, "center"));

            table.addCell(mergeCol("??????????????????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getZasdjg(), font, 3, "center"));
            //?????????
            table.addCell(mergeCol("????????????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getSsdrMc(), font, 1, "left"));

            table.addCell(mergeCol("???????????????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getSfzHm(), font, 3, "center"));
            //?????????
            table.addCell(mergeCol("????????????????????????", bigFont, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getSsdrSdjg(), font, 1, "left"));
            table.addCell(mergeCol("??????????????????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getSdcgfs(), font, 3, "center"));
            //?????????
            table.addCell(mergeCol("????????????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getSdwsMcs(), font, 3, "center"));
            //?????????
            table.addCell(mergeCol("????????????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getWebTels(), font, 1, "left"));
            table.addCell(mergeCol("????????????????????????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getSdqrWebTels(), font, 3, "center"));
            //?????????
            table.addCell(mergeCol("????????????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getAddress(), font, 3, "left"));
            //?????????
            table.addCell(mergeCol("?????????????????????????????????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getAddressQrs(), font, 1, "left"));
            //?????????
            table.addCell(mergeCol("????????????????????????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getDzshQr(), font, 1, "left"));
            //?????????
            List<DhsdResultModel> dhsdResultModelList = dataList.get(i).getDhsdResultModelList();
            if (CollectionUtils.isNotEmpty(dhsdResultModelList)){
                table.addCell(mergeColRow("??????????????????", font, 1, "center",dhsdResultModelList.size()));
                for (DhsdResultModel dhsdResultModel : dhsdResultModelList) {
                    table.addCell(mergeCol(dhsdResultModel.getSdfkStr(), font, 3, "left"));
                }

            }
            //????????????
            List<DzsdResultModel> dzsdResultModelList = dataList.get(i).getDzsdResultModelList();
            if (CollectionUtils.isNotEmpty(dzsdResultModelList)){
                table.addCell(mergeColRow("??????????????????", font, 1, "center",dzsdResultModelList.size()));
                for (DzsdResultModel dzsdResultModel : dzsdResultModelList) {
                    table.addCell(mergeCol(dzsdResultModel.getSdfkStr(), font, 3, "left"));
                }
            }
            //????????????
            List<LylqResultModel> lylqResultModelList = dataList.get(i).getLylqResultModelList();
            if (CollectionUtils.isNotEmpty(lylqResultModelList)){
                table.addCell(mergeColRow("??????????????????", font, 1, "center",lylqResultModelList.size()));
                for (LylqResultModel lylqResultModel : lylqResultModelList) {
                    table.addCell(mergeCol(lylqResultModel.getSdfkStr(), font, 3, "left"));
                }
            }
            //????????????
            List<EmsResultModel> emsResultModelList = dataList.get(i).getEmsResultModelList();
            if (CollectionUtils.isNotEmpty(emsResultModelList)){
                table.addCell(mergeColRow("EMS????????????", font, 1, "center",emsResultModelList.size()));
                for (EmsResultModel emsResultModel : emsResultModelList) {
                    table.addCell(mergeCol(emsResultModel.getSdfkStr(), font, 3, "left"));
                }
            }
            //????????????
            List<ZjsdResultModel> zjsdResultModels = dataList.get(i).getZjsdResultModels();
            if (CollectionUtils.isNotEmpty(zjsdResultModels)){
                table.addCell(mergeColRow("??????????????????", font, 1, "center",zjsdResultModels.size()));
                for (ZjsdResultModel zjsdResultModel : zjsdResultModels) {
                    table.addCell(mergeCol(zjsdResultModel.getSdfkStr(), font, 3, "left"));
                }
            }
            table.addCell(mergeCol("??????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getRemark(), font, 3, "left"));
//            table.addCell(mergeCol("??????????????????", bigFont, 1, "center"));
//            table.addCell(mergeCol(dataList.get(i).getSdjg(), bigFont, 1, "left"));
            table.addCell(mergeCol("????????????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getSdzyName(), font, 3, "left"));
            try {
                doc.add(table);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            doc.newPage();
        }
        doc.close();
        paths.add(pdf);
        if(ssdrbh == null) {
            fileNames.add(yysdbh+"_"+"fkb.pdf");
        }else {
            fileNames.add(yysdbh+"_"+ssdrbh+"_"+"fkb.pdf");
        }
    }



   public PdfPCell mergeCol(String s, Font font, int i, String align) {
        PdfPCell pdfPCell = new PdfPCell(new Paragraph(s,font));
        if (Objects.equals(align,"center")){
            pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);//??????
        }
        if (Objects.equals(align,"left")){
            pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);//?????????
        }
        if (Objects.equals(align,"right")){
            pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);//?????????
        }
        pdfPCell.setColspan(i);
        pdfPCell.setUseAscender(true);
        return pdfPCell;
    }
    public PdfPCell mergeColRow(String s, Font font, int i, String align, int row) {
        PdfPCell pdfPCell = new PdfPCell(new Paragraph(s,font));
        if (Objects.equals(align,"center")){
            pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);//??????
        }
        if (Objects.equals(align,"left")){
            pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);//?????????
        }
        if (Objects.equals(align,"right")){
            pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);//?????????
        }
        pdfPCell.setColspan(i);
        pdfPCell.setRowspan(row);
        return pdfPCell;
    }



    @Override
    public String gdxqxxdc(Integer yysdbh) throws DocumentException, IOException {
        //???????????????
        List<SdfkDataModel> dataList = getSdfkData(yysdbh,null);
        // ??????pdf ??????
        String pdfFolder = "E:\\gdxq\\";
        String pdf = pdfFolder + UUID.randomUUID() + ".pdf";
        // 1.??????documnet??????
        Document doc = new Document(PageSize.A4, 0, 0, 50, 0);//SUPPRESS
        FileOutputStream fos = new FileOutputStream(pdf);
        PdfWriter.getInstance(doc, fos);
        // ????????????
        BaseFont baseFont = BaseFont.createFont( "STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
        // ??????????????????
        Font font = new Font(baseFont, 10, Font.NORMAL);//SUPPRESS
        Font bigFont = new Font(baseFont, 12, Font.NORMAL);//SUPPRESS
        //  ??????4?????????
        doc.open();
        for(int i=0;i<dataList.size();++i) {
            PdfPTable table = new PdfPTable(4);//SUPPRESS
            table.setSplitLate(false);
            // ??????????????????
            table.setTotalWidth(new float[]{100, 100, 100, 200});//SUPPRESS
            // ??????????????????
            table.addCell(mergeCol("???????????????", font, 4, "center"));

            table.addCell(mergeCol("?????????", font, 1, "center"));
            table.addCell(mergeCol(yysdbh+"", font, 3, "center"));

            table.addCell(mergeCol("??????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getAh(), font, 3, "center"));

            table.addCell(mergeCol("???????????????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getSsdrMc(), font, 3, "center"));

            table.addCell(mergeCol("????????????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getSsdw(), font, 3, "center"));

            table.addCell(mergeCol("????????????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getSdwsMcs().split("???").length+"", font, 3, "center"));

            table.addCell(mergeCol("????????????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getSdwsMcs(), font, 3, "center"));


            table.addCell(mergeCol("??????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getAnyou(), font, 3, "center"));

            table.addCell(mergeCol("??????????????????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getSdjg(), font, 3, "center"));

            table.addCell(mergeCol("????????????????????????", bigFont, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getSsdrSdjg(), bigFont, 3, "center"));

            table.addCell(mergeCol("??????????????????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getYysj(), font, 3, "center"));

            table.addCell(mergeCol("????????????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getFksj()==null?"":dataList.get(i).getFksj(), font, 3, "center"));

            table.addCell(mergeCol("????????????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getSdzq()+"", font, 3, "center"));

            table.addCell(mergeCol("?????????????????????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getBmmc(), font, 3, "center"));

            table.addCell(mergeCol("???????????????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getYyrxm(), font, 3, "center"));

            table.addCell(mergeCol("??????????????????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getYysdbz(), font, 3, "center"));

            table.addCell(mergeCol("???????????????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getWebTels(), font, 3, "center"));

            List<LylqResultModel> lylqResultModelList = dataList.get(i).getLylqResultModelList();
            if (CollectionUtils.isNotEmpty(lylqResultModelList)){
                table.addCell(mergeColRow("??????????????????", font, 1, "center",lylqResultModelList.size()));
                for (LylqResultModel lylqResultModel : lylqResultModelList) {
                    table.addCell(mergeCol(lylqResultModel.getSdfkStr(), font, 3, "left"));
                }
            }
            //????????????
            List<EmsResultModel> emsResultModelList = dataList.get(i).getEmsResultModelList();
            if (CollectionUtils.isNotEmpty(emsResultModelList)){
                table.addCell(mergeColRow("EMS????????????", font, 1, "center",emsResultModelList.size()));
                for (EmsResultModel emsResultModel : emsResultModelList) {
                    table.addCell(mergeCol(emsResultModel.getSdfkStr(), font, 3, "left"));
                }
            }

            table.addCell(mergeCol("????????????????????????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getDzshQr(), font, 3, "center"));

            table.addCell(mergeCol("????????????", font, 1, "center"));
            table.addCell(mergeCol(dataList.get(i).getKtsj(), font, 3, "center"));

            doc.add(table);
            doc.newPage();
        }
        doc.close();
        return pdf;
    }

    private List<LylqResultModel> getLylqResult(Integer yysdbh, Integer ssdrbh) {

        List<LylqResultModel> result = new ArrayList<>();
        List<PubLylqInfoEntity> lylqInfoEntityList = pubLylqInfoEntityMapper.findByYysdbhAndSsdrbh(yysdbh, ssdrbh);

        if(null != lylqInfoEntityList){
            for (PubLylqInfoEntity entity : lylqInfoEntityList) {
                LylqResultModel model = new LylqResultModel();

                model.setYylqsj(entity.getYylqsj());
                model.setLylqAddress(entity.getLylqaddress());
                model.setSdjg(entity.getSdjg());

                result.add(model);
            }
        }

        return result;
    }

    private List<DzsdResultModel> getDzsdResult(Integer yysdbh, Integer ssdrbh) {
        List<PubDxtzInfoEntity> dxtzInfoEntityList = pubDxtzInfoEntityMapper.findByYysdbhAndSsdrbhWithBLOBs(yysdbh, ssdrbh);
        List<DzsdResultModel> result = new ArrayList<>();
        if(null != dxtzInfoEntityList){
            for (PubDxtzInfoEntity entity : dxtzInfoEntityList) {
                DzsdResultModel model = new DzsdResultModel();

                model.setCreateTime(entity.getCreatetime());
                model.setShowTel(entity.getWebtel());
                model.setShortMsg(entity.getMsgcontent());
                model.setSendState(entity.getSendstate());
                model.setFwState(entity.getFwzt());
                model.setSdjg(entity.getSdjg());

                result.add(model);
            }
        }
        return result;
    }

    /**
     * ?????????-????????????
     * @param yysdbh
     * @param ssdrbh
     * @return
     */
    private List<DhsdResultModel> getSdfkDhsdResult(Integer yysdbh, Integer ssdrbh) {
        String[] phoneStatus = {"", "??????-??????", "??????-?????????", "??????-??????", "??????-??????", "??????-?????????", "??????-?????????", "??????-??????", "??????", "????????????", "??????-??????","???????????????","??????????????????"};
        List<PubWebCallInfoEntity> entityList = pubWebCallInfoEntityMapper.findByYysdbhAndSsdrbh(yysdbh, ssdrbh);
        List<DhsdResultModel> resultModelList = new ArrayList<>();
        if(null != entityList){
            for (PubWebCallInfoEntity entity : entityList) {
                DhsdResultModel resultModel = new DhsdResultModel();
                resultModel.setCallTime(entity.getCreatetime());
                resultModel.setCallDuration(entity.getCallduration());
                Integer callstate = entity.getCallstate();
                if(null == callstate){
                    callstate = 0;
                }
                resultModel.setDhzt(phoneStatus[callstate]);
                String sdjg = "";
                if (Objects.equals(resultModel.getSdjg(),"??????")){
                    sdjg = "??????";
                }else if (Objects.equals(resultModel.getSdjg(),"??????")){
                    sdjg = "??????";
                }else {
                    sdjg = "??????";
                }
                resultModel.setSdjg(sdjg);
                resultModel.setSeatNumber(entity.getSeatnumber());
                resultModel.setWebTel(entity.getWebtel());
                String remarks = entity.getRemarks();
                if(StringUtil.isBlank(remarks)){
                    remarks = "??????";
                }
                resultModel.setRemarks(remarks);
                resultModelList.add(resultModel);
            }
        }
        return resultModelList;
    }
}
