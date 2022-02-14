package com.nju.sdpt.service.Impl;

import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.entity.PubKdtdEntity;
import com.nju.sdpt.entity.PubYysdSsdrEntity;
import com.nju.sdpt.entity.RpoEmsInfoEntity;
import com.nju.sdpt.mapper.*;
import com.nju.sdpt.model.FYEnum;
import com.nju.sdpt.model.PubYysdSsdrModel;
import com.nju.sdpt.model.TjxxModel;
import com.nju.sdpt.service.StatisticsService;
import com.nju.sdpt.util.DateUtil;
import com.nju.sdpt.util.NumberUtil;
import com.nju.sdpt.util.StringUtil;
import com.nju.sdpt.viewobject.ReportQueryVo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StatisticsServiceImpl implements StatisticsService {


    private final Logger logger = LoggerFactory.getLogger(StatisticsServiceImpl.class);
    @Autowired
    StatisticsMapper statisticsMapper;
    @Autowired
    PubYysdTjxxEntityMapper pubYysdTjxxEntityMapper;
    @Autowired
    KdtdMapper kdtdMapper;
    @Autowired
    RpoEmsInfoEntityMapper rpoEmsInfoEntityMapper;

    @Autowired
    PubYysdSsdrEntityMapper pubYysdSsdrEntityMapper;
    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;
    /**
     * 获取累计送达案件数
     * @return
     */
    @Override
    @Cacheable(cacheNames = {"ljsdajs"})
    public int findLjsdajs() {

        return statisticsMapper.findLjsdajs();
    }

    /**
     * 获取累计委托人次
     * @return
     */
    @Override
    @Cacheable(cacheNames = {"ljwtrc"})
    public int findLjwtrc() {
        return statisticsMapper.findLjwtrc();
    }

    /**
     * 获取累计送达人数
     * @return
     */
    @Override
    @Cacheable(cacheNames = {"ljsdrc"})
    public int findLjsdrc() {
       return statisticsMapper.getLjsdrc();
    }


    @Override
    @Cacheable(cacheNames = {"ljsdcgl"})
    public double findSdcgl() {

        int sdcgs = statisticsMapper.findLjsdAjsBySdjg("Y");
        int sdsbs = statisticsMapper.findLjsdAjsSdsb();
        return (double) sdcgs/(double)(sdcgs+sdsbs);
    }

    @Override
    public LinkedList<Integer> findYysdrws(Date date) {
        List<TjxxModel> tjxx = statisticsMapper.findYysdrws(date);
        return generateResult(tjxx);
    }

    private  LinkedList<Integer> generateResult(List<TjxxModel> tjxx) {
        LinkedList<Integer> result = new LinkedList<>();
        for(FYEnum fyEnum:FYEnum.values()){
            int count = 0;
            for (TjxxModel tjxxModel:tjxx){
                if(StringUtil.equals(tjxxModel.getName(),fyEnum.getFybh())){
                    count = (int)tjxxModel.getValue();
                    break;
                }
            }
            result.add(count);
        }
        return result;
    }

    @Override
    public LinkedList<Integer> findSdzrws(Date date) {
        List<TjxxModel> tjxx = statisticsMapper.findSdzrws(date);
        return generateResult(tjxx);
    }

    @Override
    public LinkedList<Integer> findWcsdrws(Date date) {
        LinkedList<TjxxModel> tjxx = statisticsMapper.findWcsdrws(date);
        return generateResult(tjxx);
    }

    @Override
    @Cacheable(value = "getSdfsfb")
    public List<TjxxModel> findSdfsfb() {
        return statisticsMapper.findSdfsfb();
    }

    @Override
    public void updateSdfsfb() {
        int dhsdCount = statisticsMapper.getDhsdCount();
        int dxsdCount = statisticsMapper.getDxsdCount();
        int emsCount = 0;
        int lylqCount = statisticsMapper.getLylqCount();
        int zhijsdCount = statisticsMapper.getZjsdCount();
//        int ggsdCount = 0;
        for(FYEnum fyEnum:FYEnum.values()){
            DynamicDataSource.routerByFybh(fyEnum.getFybh());
            emsCount+=statisticsMapper.getEmsCount();
        }
        DynamicDataSource.router(SDPT_FYDM);
        statisticsMapper.updateCountByLxAndSjwd("特定送达方式占比","电话送达",new Date(),dhsdCount);
        statisticsMapper.updateCountByLxAndSjwd("特定送达方式占比","短信送达",new Date(),dxsdCount);
        statisticsMapper.updateCountByLxAndSjwd("特定送达方式占比","邮寄送达",new Date(),emsCount);
        statisticsMapper.updateCountByLxAndSjwd("特定送达方式占比","来院领取",new Date(),lylqCount);
        statisticsMapper.updateCountByLxAndSjwd("特定送达方式占比","直接送达",new Date(),zhijsdCount);
//        statisticsMapper.updateCountByLxAndSjwd("特定送达方式占比","公告送达",new Date(),ggsdCount);

    }

    @Override
    public void updateSENDSTATE() {
        statisticsMapper.updateSENDSTATE();

    }

    @Override
    public List<TjxxModel> getSjfypm(Date date) {
        return dealSjfypm(statisticsMapper.findYysdrws(date));
    }
    @Override
    public List<TjxxModel> getSjfypmSd(Date date) {
        return dealSjfypm(statisticsMapper.findWcsdrws(date));
    }

    @Override
    public int findLjsdAjcgs() {
        return statisticsMapper.findLjsdAjsBySdjg("Y");
    }

    @Override
    public void scheduledUpdateEmsCount(ReportQueryVo queryVo) {

        //查询EMS 泛型数据  YYSDBH ， DSRBH ， SCRQ ,KDID

        List<RpoEmsInfoEntity> emsInfoEntityList = new ArrayList<>();
        for(FYEnum fyEnum:FYEnum.values()) {
            try {
                DynamicDataSource.routerByFybh(fyEnum.getFybh());
                List<PubKdtdEntity> emsInfo = kdtdMapper.getEmsInfo(queryVo);
                System.out.println(fyEnum.getFybh()+"开始");
                if (CollectionUtils.isNotEmpty(emsInfo)) {
                    for (PubKdtdEntity kdtdEntity : emsInfo) {
                        DynamicDataSource.routerByFybh(fyEnum.getFybh());
                        RpoEmsInfoEntity emsInfoEntity = new RpoEmsInfoEntity();
                        emsInfoEntity.setDsrbh(NumberUtil.isIntNotNullAndGtZero(kdtdEntity.getDsrbh()) ? kdtdEntity.getDsrbh() : 0);
                        emsInfoEntity.setScrq(kdtdEntity.getScrq());
                        emsInfoEntity.setYysdbh(NumberUtil.isIntNotNullAndGtZero(kdtdEntity.getYysdbh()) ? kdtdEntity.getYysdbh() : -kdtdEntity.getKdid());
                        emsInfoEntity.setKdid(kdtdEntity.getKdid());
                        emsInfoEntity.setSdybh(kdtdEntity.getSdybh());
                        emsInfoEntity.setSdjg(emsInfoEntity.getSdjg());
                        emsInfoEntity.setSddz(kdtdEntity.getSjrdz());
                        DynamicDataSource.router(SDPT_FYDM);
                        PubYysdSsdrModel pubYysdSsdrModel = null;
                        if(null != kdtdEntity.getYysdbh() && null != kdtdEntity.getDsrbh()) {
                            pubYysdSsdrModel = pubYysdSsdrEntityMapper.findByPrimaryKey(kdtdEntity.getYysdbh(), kdtdEntity.getDsrbh());
                        }
                        if(null != pubYysdSsdrModel && null != pubYysdSsdrModel.getWsnum()) {
                            emsInfoEntity.setWsnum(pubYysdSsdrModel.getWsnum());
                        }
                        if(null != kdtdEntity.getKddh() && kdtdEntity.getKddh().length() > 0) {
                            emsInfoEntity.setKddh(kdtdEntity.getKddh());
                        }
                        emsInfoEntityList.add(emsInfoEntity);
                    }
                }

            } catch (Exception ex) {
                logger.error(ex.getMessage());
            } finally {
                if (CollectionUtils.isNotEmpty(emsInfoEntityList)) {
                    DynamicDataSource.router(SDPT_FYDM);
                    for (RpoEmsInfoEntity emsInfoEntity : emsInfoEntityList) {
                        int count = rpoEmsInfoEntityMapper.selectCountByYYsdbhAndKdid(emsInfoEntity.getYysdbh(), emsInfoEntity.getKdid());
                        if (count == 0) {
                            rpoEmsInfoEntityMapper.insertSelective(emsInfoEntity);
                        } else {
                            List<Integer> bhs = rpoEmsInfoEntityMapper.selectBh(emsInfoEntity.getYysdbh(), emsInfoEntity.getKdid());
                            for(int bh : bhs) {
                                emsInfoEntity.setBh(bh);
                                rpoEmsInfoEntityMapper.updateByPrimaryKeySelective(emsInfoEntity);
                            }
                        }
                    }
                }
            }
        }
        System.out.println("全院完成");
    }


    @Override
    public List<TjxxModel> getSjfypmCgl(Date date) {
        List<TjxxModel> sdcgsList = statisticsMapper.sdcgsList(date);//送达成功数
        List<TjxxModel> sdsList = statisticsMapper.sdsList(date);//送达总数
        List<TjxxModel> cgl = new LinkedList<>();
        for (FYEnum fyEnum:FYEnum.values()){
            double cgs = 0;//成功数
            double zs = 0;//总数
            for(TjxxModel tjxxModel:sdcgsList){
                if(StringUtil.equals(tjxxModel.getName(),fyEnum.getFybh())){
                    cgs = tjxxModel.getValue();
                    break;
                }
            }
            for(TjxxModel tjxxModel:sdsList){
                if(StringUtil.equals(tjxxModel.getName(),fyEnum.getFybh())){
                    zs = tjxxModel.getValue();
                    break;
                }
            }
            if(zs == 0){
                cgl.add(new TjxxModel(fyEnum.getFybh(),0.00));
                continue;
            }
            cgl.add(new TjxxModel(fyEnum.getFybh(),(cgs/zs)));
        }
        cgl = dealSjfypm(cgl);
        for(int i = 0 ; i < cgl.size() ; i++){
            if(cgl.get(i).getValue() == 0){
               for (int j = i ; j < 10 ; j ++){
                   cgl.remove(i);
               }
               break;
            }
        }
        return cgl;
    }


    private static  List<TjxxModel> dealSjfypm(List<TjxxModel> tjxx){
        Collections.sort(tjxx, new Comparator<TjxxModel>() {
            @Override
            public int compare(TjxxModel o1, TjxxModel o2) {
                return (int)(o2.getValue()*1000-o1.getValue()*1000);
            }
        });
        List<TjxxModel> tjxxModels = new LinkedList<>();
        for (int i=0 ;i<10;i++){
            if(i<=tjxx.size()-1){
                TjxxModel tjxxModel = tjxx.get(i);
                tjxxModel.setName(FYEnum.getFyByFybh(tjxxModel.getName()).getJc());
                tjxxModels.add(tjxxModel);
            }

        }
        return tjxxModels;
    }

    @Override
    public List<Integer> findSlrs() {
        List<Integer> slrs = new ArrayList<>();
        List<Date> months = DateUtil.getHistoryMonth(new Date());

        for(Date date:months){
            Integer rs = statisticsMapper.findSlrs(date,DateUtil.addMonths(date,1));
            slrs.add(rs);
        }
        return slrs;
    }

    @Override
    public List<Integer> findCdrs() {
        List<Integer> slrs = new ArrayList<>();
        List<Date> months = DateUtil.getHistoryMonth(new Date());
        for(Date date:months){
            Integer rs = statisticsMapper.findCdrs(date,DateUtil.addMonths(date,1));
            slrs.add(rs);
        }
        return slrs;
    }

    @Override
    @Cacheable(cacheNames = "sdcglList")
    public List<String> findSdcglList() {
        Date date = new Date(0,0,0);
        List<TjxxModel> sdcgsList = statisticsMapper.sdcgsList(date);//送达成功数
        List<TjxxModel> sdsList = statisticsMapper.sdsList(date);//送达总数
        List<String> sdcglList = new LinkedList<>();//送达成功率
        for (FYEnum fyEnum:FYEnum.values()){
            double cgs = 0;//成功数
            double zs = 0;//总数
            for(TjxxModel tjxxModel:sdcgsList){
                if(StringUtil.equals(tjxxModel.getName(),fyEnum.getFybh())){
                    cgs = tjxxModel.getValue();
                    break;
                }
            }
            for(TjxxModel tjxxModel:sdsList){
                if(StringUtil.equals(tjxxModel.getName(),fyEnum.getFybh())){
                    zs = tjxxModel.getValue();
                    break;
                }
            }
            if(zs == 0){
                sdcglList.add("0.00");
                continue;
            }
            sdcglList.add(String.format("%.2f", (cgs/zs)*100));
        }
        return sdcglList;
    }



}
