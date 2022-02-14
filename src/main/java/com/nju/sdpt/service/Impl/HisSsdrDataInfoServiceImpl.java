package com.nju.sdpt.service.Impl;

import com.nju.sdpt.constant.SdptConstants;
import com.nju.sdpt.entity.HisSsdrDataInfoEntity;
import com.nju.sdpt.entity.PubSsdrHmEntity;
import com.nju.sdpt.entity.PubYysdSsdrdzEntity;
import com.nju.sdpt.mapper.HisSsdrDataInfoEntityMapper;
import com.nju.sdpt.mapper.PubSsdrHmEntityMapper;
import com.nju.sdpt.mapper.PubYysdSsdrEntityMapper;
import com.nju.sdpt.mapper.PubYysdSsdrdzEntityMapper;
import com.nju.sdpt.model.PubYysdSsdrModel;
import com.nju.sdpt.service.HisSsdrDataInfoService;
import com.nju.sdpt.util.NumberUtil;
import com.nju.sdpt.util.StringUtil;
import com.nju.sdpt.viewobject.openApi.DataPushObj;
import com.nju.sdpt.viewobject.openApi.FzSsdrDataPushVo;
import exception.BusinessException;
import exception.HttpCode;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class HisSsdrDataInfoServiceImpl implements HisSsdrDataInfoService {
    @Resource
    HisSsdrDataInfoEntityMapper hisSsdrDataInfoEntityMapper;
    @Resource
    PubSsdrHmEntityMapper pubSsdrHmEntityMapper;
    @Resource
    PubYysdSsdrEntityMapper pubYysdSsdrEntityMapper;
    @Resource
    PubYysdSsdrdzEntityMapper pubYysdSsdrdzEntityMapper;
    @Resource
    SsdrServiceImpl ssdrService;

    @Override
    public Boolean ssdr_info_push(FzSsdrDataPushVo pushVo) {
        //1.校验参数
        String fybh = pushVo.getFybh();
        Integer ajxh = pushVo.getAjxh();
        Integer ssdrbh = pushVo.getSsdrbh();
        String ssdrmc = pushVo.getSsdrmc();
        String ssdrsfzhm = pushVo.getSsdrsfzhm();
        List<DataPushObj> telList = pushVo.getTelList();
        List<DataPushObj> addressList = pushVo.getAddressList();
        if (StringUtil.isBlank(fybh) || !NumberUtil.isIntNotNullAndGtZero(ajxh) || !NumberUtil.isIntNotNullAndGtZero(ssdrbh)
                || StringUtil.isBlank(ssdrmc) || StringUtil.isBlank(ssdrsfzhm)) {
            throw new BusinessException(HttpCode.CSERROR);
        }
        if (CollectionUtils.isEmpty(telList) && CollectionUtils.isEmpty(addressList)) {
            throw new BusinessException(HttpCode.DATA_ARR_ERROR);
        }

        //2.根据条件查询出当事人下所有相关数据
        HisSsdrDataInfoEntity entityWhere = new HisSsdrDataInfoEntity(){{
            setFybh(fybh);
            setAjxh(ajxh);
            setSsdrbh(ssdrbh);
            setSsdrmc(ssdrmc);
            setSsdrsfzhm(ssdrsfzhm);
        }};

        List<HisSsdrDataInfoEntity> entityList = hisSsdrDataInfoEntityMapper.selectByWhere(entityWhere);

        List<String> strList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(entityList)) {
            for (HisSsdrDataInfoEntity entity : entityList) {
                String ywlx = entity.getYwlx(); //业务类型  TEL:号码信息  ADDRESS:地址信息
                Integer type = entity.getType();//具体信息类型
                String content = entity.getContent();//具体数据内容
                //拼接
                String pj = ywlx + "_" + type + "_" + content;
                strList.add(pj);
            }
        }

        //3.过滤出需要插入的数据
        List<HisSsdrDataInfoEntity> saveEntityList = new ArrayList<>();
        loadSaveHisEntity(pushVo,saveEntityList,telList,strList,"TEL");
        loadSaveHisEntity(pushVo,saveEntityList,addressList,strList,"ADDRESS");

        if(CollectionUtils.isNotEmpty(saveEntityList)){
            for (HisSsdrDataInfoEntity ssdrDataInfoEntity : saveEntityList) {
                hisSsdrDataInfoEntityMapper.insertSelective(ssdrDataInfoEntity);
            }
            return true;
        }else {
            return false;
        }


    }

    @Override
    public Boolean bindSsdrHistoryData(Integer yysdbh) {
        if(!NumberUtil.isIntNotNullAndGtZero(yysdbh)){
            return false;
        }

        //查询工单下所有受送达人
        List<PubYysdSsdrModel> ssdrModelList = pubYysdSsdrEntityMapper.selectByYysdbh(yysdbh);
        if(CollectionUtils.isEmpty(ssdrModelList)){
            //没有数据 结束
            return false;
        }
        for (PubYysdSsdrModel ssdrModel : ssdrModelList) {
            String sfzhm = ssdrModel.getSfzhm();
            if(StringUtil.isBlank(sfzhm)){
                //不存在身份证号码  直接跳过 不进行操作
                continue;
            }
            Integer ssdrbh = ssdrModel.getSsdrbh();
            String ssdrmc = ssdrModel.getSsdrmc();
            //查询出当事人已经存在的号码 和 地址
            List<String> telList = pubSsdrHmEntityMapper.selectByYysdbhAndSsdrbh(yysdbh, ssdrbh);
            List<String> addressList = pubYysdSsdrdzEntityMapper.selectDzByyysdbhAndSsdrbh(yysdbh, ssdrbh);
            if(CollectionUtils.isEmpty(telList)) telList = new ArrayList<>();
            if(CollectionUtils.isEmpty(addressList)) addressList = new ArrayList<>();

            //根据身份证+姓名 查询出历史案件数据（号码+地址信息）
            HisSsdrDataInfoEntity entityWhere = new HisSsdrDataInfoEntity(){{
                setSsdrsfzhm(sfzhm);
                setSsdrmc(ssdrmc);
            }};
            List<HisSsdrDataInfoEntity> entityList = hisSsdrDataInfoEntityMapper.selectByWhere(entityWhere);

            //根据身份证+姓名 查询出历史工单数据（号码+地址信息）
            List<String> his_entry = pubSsdrHmEntityMapper.selectHisYYsdData_entry(ssdrmc,sfzhm,yysdbh); //历史明文号码
            List<PubSsdrHmEntity> his_repair = pubSsdrHmEntityMapper.selectHisYYsdData_repair(ssdrmc,sfzhm,yysdbh);//历史修复号码
            List<String> his_dz = pubYysdSsdrdzEntityMapper.selectHisYYsdData(ssdrmc,sfzhm,yysdbh);//历史地址


            //筛选出匹配的历史数据
            List<PubSsdrHmEntity> save_his_hm = new ArrayList<>();
            List<PubYysdSsdrdzEntity> save_his_dz = new ArrayList<>();
            if(CollectionUtils.isNotEmpty(his_entry)){
                for (String tel : his_entry) {
                    if(!telList.contains(tel)){
                        //不属于 当事人已经存在的号码集合中 需要新增为历史数据
                        PubSsdrHmEntity hisHm = new PubSsdrHmEntity(){{
                            setSdpName(ssdrModel.getSsdrmc());
                            setIdCard(sfzhm);
                            setOperatorType("ENTRY");
                            setShowTel(tel);
                            setOperatorTel(tel);
                            setCreateTime(new Date());
                            setSsdrbh(ssdrbh);
                            setYysdbh(yysdbh);
                            setHmly(SdptConstants.HIS_DATA_LY.HIS_YYSD); //历史工单
                        }};
                        telList.add(tel);
                        save_his_hm.add(hisHm);
                    }
                }
            }
            if(CollectionUtils.isNotEmpty(his_repair)){
                for (PubSsdrHmEntity pubSsdrHmEntity : his_repair) {
                    String tel = pubSsdrHmEntity.getShowTel();
                    if(!telList.contains(tel)){
                        //不属于 当事人已经存在的号码集合中 需要新增为历史数据
                        PubSsdrHmEntity hisHm = new PubSsdrHmEntity();
                        BeanUtils.copyProperties(pubSsdrHmEntity,hisHm);
                        hisHm.setCreateTime(new Date());
                        hisHm.setNewphonestatus(null);
                        hisHm.setIsconfirm(null);
                        hisHm.setSsdrbh(ssdrbh);
                        hisHm.setYysdbh(yysdbh);
                        hisHm.setSdpName(ssdrModel.getSsdrmc());
                        hisHm.setIdCard(ssdrModel.getSfzhm());
                        hisHm.setHmly(SdptConstants.HIS_DATA_LY.HIS_YYSD); //历史工单
                        save_his_hm.add(hisHm);
                    }
                }
            }
            if(CollectionUtils.isNotEmpty(his_dz)){
                for (String dz : his_dz) {
                    if(!addressList.contains(dz)){
                        //不属于 当事人已经存在的地址集合中 需要新增为历史数据
                        PubYysdSsdrdzEntity himDz = new PubYysdSsdrdzEntity(){{
                            setSdpName(ssdrmc);
                            setIdCard(sfzhm);
                            setLx(1);
                            setDz(dz);
                            setSsdrbh(ssdrbh);
                            setYysdbh(yysdbh);
                            setDzly(SdptConstants.HIS_DATA_LY.HIS_YYSD);//历史工单
                            setCreatetime(new Date());
                        }};
                        addressList.add(dz);
                        save_his_dz.add(himDz);
                    }
                }
            }
            if(CollectionUtils.isNotEmpty(entityList)){
                for (HisSsdrDataInfoEntity himEntity : entityList) {
                    String ywlx = himEntity.getYwlx(); //业务类型  TEL:号码信息  ADDRESS:地址信息
                    Integer type = himEntity.getType();//具体信息类型
                    String content = himEntity.getContent();//具体数据内容
                    if(Objects.equals(ywlx,"TEL")){
                        if(!telList.contains(content)){
                            //不属于 当事人已经存在的号码集合中 需要新增为历史数据
                            PubSsdrHmEntity hisHm = new PubSsdrHmEntity(){{
                                setSdpName(ssdrModel.getSsdrmc());
                                setIdCard(sfzhm);
                                setOperatorType("ENTRY");
                                setShowTel(content);
                                setOperatorTel(content);
                                setCreateTime(new Date());
                                setSsdrbh(ssdrbh);
                                setYysdbh(yysdbh);
                                setHmly(SdptConstants.HIS_DATA_LY.HIS_CASE); //历史案件
                            }};
                            telList.add(content);
                            save_his_hm.add(hisHm);
                        }
                    }else if(Objects.equals(ywlx,"ADDRESS")){
                        String dz = loadTypeDz(type) + content;
                        if(!addressList.contains(dz)){
                            //不属于 当事人已经存在的地址集合中 需要新增为历史数据
                            PubYysdSsdrdzEntity himDz = new PubYysdSsdrdzEntity(){{
                                setSdpName(ssdrmc);
                                setIdCard(sfzhm);
                                setLx(1);
                                setDz(dz);
                                setSsdrbh(ssdrbh);
                                setYysdbh(yysdbh);
                                setDzly(SdptConstants.HIS_DATA_LY.HIS_CASE);//历史案件
                                setCreatetime(new Date());
                            }};
                            addressList.add(dz);
                            save_his_dz.add(himDz);
                        }
                    }else {
                        //other
                    }
                }
            }

            //保存历史数据
            if(CollectionUtils.isNotEmpty(save_his_dz)){
                //历史待保存数据不为空执行
                for (PubYysdSsdrdzEntity ssdrdzEntity : save_his_dz) {
                    Integer integer = ssdrService.savePubYysdSsdrdzEntity(ssdrdzEntity);
                    System.out.println("========DZ===="+integer);
                }
            }
            if(CollectionUtils.isNotEmpty(save_his_hm)){
                //历史待保存数据不为空执行
                for (PubSsdrHmEntity pubSsdrHmEntity : save_his_hm) {
                    ssdrService.savePubSsdrHmEntity(pubSsdrHmEntity);
                }
            }

        }

        return true;
    }



    private String loadTypeDz(Integer type){
        if(NumberUtil.isIntNotNullAndGtZero(type)){
            //1.工作单位 2.经常居住地 3.户籍所在地 4.个人送达地址 5.单位地址 6.单位送达地址 7.机关地址 8.机关送达地址
            String[] typeCn = new String[]{"","工作单位:","经常居住地:","户籍所在地:","送达地址:","单位地址:","送达地址:","单位地址:","送达地址:"};
            if(type.compareTo(typeCn.length - 1) > 0){
                return "";
            }
            return typeCn[type];
        }
        return "";
    }
    private void loadSaveHisEntity(
            FzSsdrDataPushVo pushVo,
            List<HisSsdrDataInfoEntity> saveEntityList,
            List<DataPushObj> objList,
            List<String> strList,
            String ywlx){


        if(CollectionUtils.isEmpty(objList) || StringUtil.isBlank(ywlx)){
            //数据不完整  直接结束
            return;
        }
        if(null == saveEntityList){
            saveEntityList = new ArrayList<>();
        }
        if(null == strList){
            strList = new ArrayList<>();
        }

        String fybh = pushVo.getFybh();
        Integer ajxh = pushVo.getAjxh();
        Integer ssdrbh = pushVo.getSsdrbh();
        String ssdrmc = pushVo.getSsdrmc();
        String ssdrsfzhm = pushVo.getSsdrsfzhm();
        for (DataPushObj obj : objList) {
            Integer type = obj.getType();//具体信息类型
            String content = obj.getContent();//具体数据内容
            //拼接

            String pj = ywlx+"_" + type + "_" + content;
            //判断拼接字符串是否存在
            if(!strList.contains(pj)){
                //不存在 分装为插入数据
                HisSsdrDataInfoEntity saveEntity = new HisSsdrDataInfoEntity(){{
                    setFybh(fybh);
                    setAjxh(ajxh);
                    setSsdrbh(ssdrbh);
                    setSsdrmc(ssdrmc);
                    setSsdrsfzhm(ssdrsfzhm);
                    setYwlx(ywlx);
                    setType(type);
                    setContent(content);
                    setCreatetime(new Date());
                    setDsrssdw(pushVo.getDsrssdw());
                }};
                saveEntityList.add(saveEntity);
            }
        }
    }
}
