package com.nju.sdpt.service.Impl;

import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.entity.*;
import com.nju.sdpt.mapper.*;
import com.nju.sdpt.model.*;
import com.nju.sdpt.service.SdxxService;
import com.nju.sdpt.util.DateUtil;
import com.nju.sdpt.util.FydmUtil;
import com.nju.sdpt.viewobject.DxsdInfoVO;
import com.nju.sdpt.viewobject.SdDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SdxxServiceImpl implements SdxxService {

    @Autowired
    DjsdxxMapper djsdxxMapper;

    @Autowired
    AjJbMapper ajJbMapper;

    @Autowired
    KdtdMapper kdtdMapper;

    @Autowired
    GgxxMapper ggxxMapper;

    @Autowired
    SpryMapper spryMapper;

    @Autowired
    XtglYhbMapper xtglYhbMapper;
    @Autowired
    PubWebCallInfoEntityMapper pubWebCallInfoEntityMapper;
    @Autowired
    PubLylqInfoEntityMapper pubLylqInfoEntityMapper;
    @Autowired
    PubMwdxSendEntityMapper pubMwdxSendEntityMapper;
    @Autowired
    PubDxtzInfoEntityMapper pubDxtzInfoEntityMapper;
    @Autowired
    DsrJbEntityMapper dsrJbEntityMapper;
    @Autowired
    PubCaxxEntityMapper pubCaxxEntityMapper;
    @Autowired
    PubZjsdInfoEntityMapper pubZjsdInfoEntityMapper;
    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;

    @Autowired
    PubYysdJbEntityMapper pubYysdJbEntityMapper;
    /**
     * 获取电话送达信息
     *
     * @param ajxh 案件序号
     * @param fybh 法院编号
     * @return List<DzsdModel>
     */
    @Override
    public List<PubWebCallInfoModel> getDhsdInfo(Integer ajxh, String fybh) {
        List<PubWebCallInfoModel> pubWebCallInfoModelList = pubWebCallInfoEntityMapper.findByAjxhAndFybh(ajxh,fybh);
        List<PubCaxxEntity> caxxEntityList = pubCaxxEntityMapper.selectByAjxhAndFybh(ajxh,fybh);
        for (PubCaxxEntity pubCaxxEntity:caxxEntityList){
            List<PubWebCallInfoModel> result = pubWebCallInfoEntityMapper.findByYysdbh(pubCaxxEntity.getYysdbh());
            pubWebCallInfoModelList.addAll(result);
        }
        for(PubWebCallInfoModel pubWebCallInfoModel : pubWebCallInfoModelList){
            pubWebCallInfoModel.setWhTime(DateUtil.format(pubWebCallInfoModel.getCreatetime(),"yyyy-MM-dd HH:mm:ss"));
        }
        return  pubWebCallInfoModelList ;
    }

    /**
     * 获取电子送达信息
     *
     * @param ajxh 案件序号
     * @param fybh 法院编号
     * @return List<DzsdModel>
     */
    @Override
    public List<DzsdModel> getDzsdInfo(Integer ajxh, String fybh) {
        DynamicDataSource.router(FydmUtil.getFydmByFybh(fybh));
        List<DzsdModel> dzsdModels = new ArrayList<>();
        List<PubDjSdxxEntity> pubDjSdxxEntities = djsdxxMapper.getDzsdInfo(ajxh,fybh);
        for (PubDjSdxxEntity pubDjSdxxEntity : pubDjSdxxEntities) {
            DzsdModel dzsdModel = new DzsdModel(pubDjSdxxEntity);
            dzsdModels.add(dzsdModel);
        }
        return dzsdModels;
    }

    /**
     * 获取EMS送达信息
     *
     * @param ajxh 案件序号
     * @param fybh 法院编号
     * @return List<EmssdModel>
     */
    @Override
    public List<EmssdModel> getEmssdInfo(Integer ajxh, String fybh) {
        DynamicDataSource.router(FydmUtil.getFydmByFybh(fybh));
        List<EmssdModel> emssdModels = new ArrayList<>();
        AjJbEntity ajJbDO = ajJbMapper.findById(ajxh);
        List<PubKdtdEntity> pubKdtdEntities = kdtdMapper.findByAh(ajJbDO.getAh());
        DynamicDataSource.router(SDPT_FYDM);
        List<PubCaxxEntity> caxxEntityList = pubCaxxEntityMapper.selectByAjxhAndFybh(ajxh,fybh);
        for (PubCaxxEntity pubCaxxEntity:caxxEntityList){
            DynamicDataSource.routerByFybh(fybh);
            List<PubKdtdEntity> result = kdtdMapper.findGdByYysdbh(pubCaxxEntity.getYysdbh());
            for (PubKdtdEntity caKd:result){
                Boolean hasAdded = false;
                for (PubKdtdEntity kdtd:pubKdtdEntities){
                    if(kdtd.getKdid()==caKd.getKdid()){
                        hasAdded = true;
                        break;
                    }
                }
                if(!hasAdded){
                    pubKdtdEntities.add(caKd);
                }
            }

        }
        for (PubKdtdEntity pubKdtdEntity : pubKdtdEntities) {
            EmssdModel emssdModel = new EmssdModel(pubKdtdEntity);
            emssdModels.add(emssdModel);
        }
        return emssdModels;
    }

    /**
     * 获取公告送达信息
     *
     * @param ajxh 案件序号
     * @param fybh 法院编号
     * @return List<GgsdModel>
     */
    @Override
    public List<GgsdModel> getGgsdInfo(Integer ajxh, String fybh) {
        DynamicDataSource.router("gonggao");
        List<GgsdModel> ggsdModels = new ArrayList<>();
        List<PubGgxxEntity> pubGgxxEntities=ggxxMapper.findByAjxh(ajxh, FydmUtil.getFydmByFybh(fybh));
        if(pubGgxxEntities==null){
            return null;
        }
        for (PubGgxxEntity pubGgxxEntity : pubGgxxEntities) {
            GgsdModel ggsdModel = new GgsdModel(pubGgxxEntity);
            ggsdModel.setGgshzt(getGgshzt(pubGgxxEntity, ggsdModel));
            ggsdModels.add(ggsdModel);
        }
        return ggsdModels;
    }

    /**
     * 获取来院领取信息
     *
     * @param ajxh 案件序号
     * @param fybh 法院编号
     * @return List<DzsdModel>
     */
    @Override
    public List<LylqModel> getLylqInfo(Integer ajxh, String fybh) {
       ;
        List<LylqModel> fgLylqModels = pubLylqInfoEntityMapper.findFgLylqModelsByAjxhAndFybh(ajxh,fybh);
        List<LylqModel> gdLylqModels = pubLylqInfoEntityMapper.findGdLylqModelsByAjxhAndFybh(ajxh,fybh);
        DynamicDataSource.router(FydmUtil.getFydmByFybh(fybh));
        for(LylqModel lylqModel:fgLylqModels){
            lylqModel.setSsdrmc(dsrJbEntityMapper.getDsrjblistByAjxhAndDsrbh(ajxh,lylqModel.getSsdrbh()).getDsrjc());
            lylqModel.setFqr(xtglYhbMapper.findByYhdm(lylqModel.getFgbh()).getYhmc());
        }
        DynamicDataSource.router(SDPT_FYDM);
        fgLylqModels.addAll(gdLylqModels);
        List<PubCaxxEntity> caxxEntityList = pubCaxxEntityMapper.selectByAjxhAndFybh(ajxh,fybh);
        for (PubCaxxEntity pubCaxxEntity:caxxEntityList){
            List<LylqModel> result = pubLylqInfoEntityMapper.findByYysdbh(pubCaxxEntity.getYysdbh());
            fgLylqModels.addAll(result);
        }
        for(LylqModel lylqModel : fgLylqModels){
            lylqModel.setYylqsjStr((DateUtil.format(lylqModel.getYylqsj(),"yyyy-MM-dd HH:mm:ss")));
        }
        return  fgLylqModels ;
    }

    @Override
    public List<DxsdInfoVO> getDxsdInfo(Integer ajxh, String fybh) {
        List<DxsdInfoVO> dxsdInfoVOS = new ArrayList<DxsdInfoVO>();
        List<PubMwdxSendEntity> pubMwdxSendEntities = pubMwdxSendEntityMapper.findByAjxhAndFybh(ajxh,fybh);
        DynamicDataSource.router(FydmUtil.getFydmByFybh(fybh));
        for (PubMwdxSendEntity ms:pubMwdxSendEntities){
            String time = DateUtil.format(ms.getCratetime(),DateUtil.noSecondFormat);
            String dsrmc = dsrJbEntityMapper.getDsrjblistByAjxhAndDsrbh(ms.getAjxh(),ms.getSsdrbh()).getDsrjc();
            String fgmc = xtglYhbMapper.findByYhdm(ms.getFgbh()).getYhmc();
            DxsdInfoVO dxsdInfoVO = new DxsdInfoVO(ms.getId(),fgmc,ms.getSendphone(),time,ms.getMsgcontent(),ms.getSendstatus(),ms.getFwzt(),ms.getCusid(),dsrmc);
            dxsdInfoVOS.add(dxsdInfoVO);
        }
        DynamicDataSource.router(SDPT_FYDM);
        List<DxtzListModel> dxtzInfoEntities = pubDxtzInfoEntityMapper.findByAjxhAndFybh(ajxh,fybh);
        List<PubCaxxEntity> caxxEntityList = pubCaxxEntityMapper.selectByAjxhAndFybh(ajxh,fybh);
        for (PubCaxxEntity pubCaxxEntity:caxxEntityList){
           DynamicDataSource.router(SDPT_FYDM);
           List<DxtzListModel> result = pubDxtzInfoEntityMapper.findByYysdbh(pubCaxxEntity.getYysdbh());
           DynamicDataSource.routerByFybh(fybh);
           String dsrmc = dsrJbEntityMapper.getDsrjblistByAjxhAndDsrbh(pubCaxxEntity.getAjxh(),pubCaxxEntity.getDsrbh()).getDsrjc();
           for (DxtzListModel dxtzListModel:result){
               dxtzListModel.setDsrmc(dsrmc);
           }
            dxtzInfoEntities.addAll(result);
        }
        for(DxtzListModel di : dxtzInfoEntities){
            String time = DateUtil.format(di.getCreatetime(),DateUtil.noSecondFormat);
            DxsdInfoVO dxsdInfoVO = new DxsdInfoVO(di.getDxtzid(),"送达中心",di.getWebtel(),time,di.getMsgcontent(),di.getSendstate(),di.getFwzt(),di.getDxtzid().toString(),di.getDsrmc());
            dxsdInfoVOS.add(dxsdInfoVO);
        }
        return dxsdInfoVOS;
    }

    @Override
    public PubKdtdEntity getKdtdByKdid(Integer kdid) {
        return kdtdMapper.findByKdid(kdid);
    }

    /**
     * 删除送达回执
     * @param kdid
     * @return
     */
    @Override
    public boolean deleteKdhzByKdid(Integer kdid){
        return kdtdMapper.deleteKdhzBykdid(kdid);
    }

    @Override
    public List<EmssdModel> getEmssdGdInfo(Integer yysdbh, String fybh) {
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdJbEntity pubYysdJbEntity=pubYysdJbEntityMapper.selectByPrimaryKey(yysdbh);
        List<EmssdModel> emssdModels = new ArrayList<>();
        DynamicDataSource.router(FydmUtil.getFydmByFybh(fybh));
        List<PubKdtdEntity> pubKdtdEntities = null;
        try{
            pubKdtdEntities=kdtdMapper.findGdByYysdbh(yysdbh);
            for (PubKdtdEntity pubKdtdEntity : pubKdtdEntities) {
                if(pubKdtdEntity.getDyrq()!=null&&pubKdtdEntity.getDyrq().compareTo(pubYysdJbEntity.getYysj())>=0) {
                    EmssdModel emssdModel = new EmssdModel(pubKdtdEntity);
                    emssdModels.add(emssdModel);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        DynamicDataSource.router(SDPT_FYDM);
        return emssdModels;
    }

    @Override
    public List<ZjsdModel> getZjsdGdInfo(Integer yysdbh) {
        List<ZjsdModel> zjsdModels = new ArrayList<>();
        try{
            zjsdModels = pubZjsdInfoEntityMapper.findByYysdbh(yysdbh);
        }catch (Exception e){
            e.printStackTrace();
        }

        
        return zjsdModels;
    }

    @Override
    public List<EmssdModel> getEmssdGdInfo(String ssdrmc, String sfzhm, String fybh) {

        List<EmssdModel> emssdModels = new ArrayList<>();
        DynamicDataSource.router(FydmUtil.getFydmByFybh(fybh));
        List<PubKdtdEntity> pubKdtdEntities = kdtdMapper.findGdBySsdrmcAndSfzhm(ssdrmc, sfzhm);
        for (PubKdtdEntity pubKdtdEntity : pubKdtdEntities) {
            EmssdModel emssdModel = new EmssdModel(pubKdtdEntity);
            emssdModels.add(emssdModel);
        }
        DynamicDataSource.router(SDPT_FYDM);
        return emssdModels;
    }

    @Override
    public List<ZjsdModel> getZjsdInfo(Integer ajxh, String fybh) {
        List<ZjsdModel> zjsdModelList = pubZjsdInfoEntityMapper.findByAjxhAndFybh(String.valueOf(ajxh),fybh);
        List<PubCaxxEntity> caxxEntityList = pubCaxxEntityMapper.selectByAjxhAndFybh(ajxh,fybh);
        for (PubCaxxEntity pubCaxxEntity:caxxEntityList){
            List<ZjsdModel> result = pubZjsdInfoEntityMapper.findByYysdbh(pubCaxxEntity.getYysdbh());
            zjsdModelList.addAll(result);
        }
        for(ZjsdModel zjsdModel : zjsdModelList){
            zjsdModel.setSmsjStr(DateUtil.format(zjsdModel.getSmsj(),"yyyy-MM-dd"));
        }
        return zjsdModelList;
    }


    private String getGgshzt(PubGgxxEntity pubGgxxEntity, GgsdModel ggsdModel) {

        int ywtStatus = pubGgxxEntity.getYwtstatus();
        int cwStatus = pubGgxxEntity.getCwstatus();
        int glyStatus = pubGgxxEntity.getGlystatus();
        int gyStatus = pubGgxxEntity.getGystatus();

        if (gyStatus == 3) {
            return "已导出";
        }
        if (glyStatus == 1) {
            return "管理员已审批";
        }
        if (cwStatus == 1) {
            return "财务已审批";
        }
        if (ywtStatus == 1) {
            return "业务庭已审批";
        }
        return "待审批";
    }

    /**
     * 获取该法官的全部送达信息
     *
     * @param fybh 法院编号
     * @param yhm  用户名
     * @return SdDataVO
     */
    @Override
    public SdDataVO getAllSdData(String fybh, String yhm) {

        DynamicDataSource.router(FydmUtil.getFydmByFybh(fybh));
        SdDataVO sdDataVO = new SdDataVO();
        XtglYhbEntity byYhdm = xtglYhbMapper.findByYhdm(yhm);

        // todo 电话送达人数
        List<Integer> dhsdAjxhList = new ArrayList<>();
        sdDataVO.setDhsdrs(dhsdAjxhList.size());

        // 电子送达人数
        List<Integer> dzsdAjxhList = djsdxxMapper.getTargetJudgeAllAjxh(byYhdm.getYhmc());
        sdDataVO.setDzsdrs(dzsdAjxhList.size());

        // EMS送达人数
        List<String> emssdAjxhList = kdtdMapper.getTargetJudgeAllAh(byYhdm.getYhmc());
        sdDataVO.setEmssdrs(emssdAjxhList.size());


        // 对电话送达人数去重得到电话送达案件数
        Set<Integer> dhsdSet = new HashSet<>(dhsdAjxhList);
        sdDataVO.setDzsdaj(dhsdSet.size());

        // 对电子送达人数去重得到电子送达案件数
        Set<Integer> dzsdSet = new HashSet<>(dzsdAjxhList);
        sdDataVO.setDzsdaj(dzsdSet.size());

        // 对EMS送达人数去重得到EMS送达案件数
        Set<String> emssdsSet = new HashSet<>(emssdAjxhList);
        sdDataVO.setEmssdaj(emssdsSet.size());


        // 公告送达人数 切换数据源
        DynamicDataSource.router("gonggao");

        String fydmByFybh = FydmUtil.getFydmByFybh(fybh);
        List<Integer> ggsdAjxhList = ggxxMapper.findByDjr(byYhdm.getYhmc(),fydmByFybh);
        sdDataVO.setGgsdrs(ggsdAjxhList.size());

        // 对公告送达人数去重得到公告送达案件数
        Set<Integer> ggsdsSet = new HashSet<>(ggsdAjxhList);
        sdDataVO.setGgsdaj(ggsdsSet.size());

        return sdDataVO;

    }


}
