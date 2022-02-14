package com.nju.sdpt.service.Impl;

import com.nju.sdpt.constant.SDCategory;
import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.entity.*;
import com.nju.sdpt.mapper.*;
import com.nju.sdpt.model.PubYysdSsdrModel;
import com.nju.sdpt.model.RetObj;
import com.nju.sdpt.service.LogService;
import com.nju.sdpt.service.SsdrService;
import com.nju.sdpt.service.YysdService;
import com.nju.sdpt.util.NumberUtil;
import com.nju.sdpt.util.StringUtil;
import com.nju.sdpt.viewobject.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

import static com.nju.sdpt.constant.SdptConstants.INPUT_DATA_TYPE.INPUT_ADDRESS;
import static com.nju.sdpt.constant.SdptConstants.INPUT_DATA_TYPE.INPUT_ID_CARD;
import static com.nju.sdpt.constant.SdptConstants.INPUT_DATA_TYPE.INPUT_PHONE;
import static com.nju.sdpt.constant.SdptConstants.LOG_TYPE.LRLXDH;
import static com.nju.sdpt.constant.SdptConstants.LOG_TYPE.LRLXDZ;
import static com.nju.sdpt.constant.SdptConstants.LOG_TYPE.LRSFZ;

/**
 * 受送达人相关业务
 * @Author: Diao Lin
 * @Date: 2019/12/11 10:30
 * @Description:
 */
@Service
public class SsdrServiceImpl implements SsdrService {

    @Autowired
    PubYysdSsdrEntityMapper pubYysdSsdrEntityMapper;
    @Autowired
    PubYysdJbEntityMapper pubYysdJbEntityMapper;
    @Autowired
    PubYysdRyxxEntityMapper pubYysdRyxxEntityMapper;
    @Autowired
    PubSsdrHmEntityMapper pubSsdrHmEntityMapper;
    @Autowired
    PubYysdSsdrdzEntityMapper pubYysdSsdrdzEntityMapper;
    @Autowired
    PubWebCallInfoEntityMapper pubWebCallInfoEntityMapper;
    @Autowired
    PubDxtzInfoEntityMapper pubDxtzInfoEntityMapper;
    @Autowired
    PubLylqInfoEntityMapper pubLylqInfoEntityMapper;
    @Autowired
    PubZjsdInfoEntityMapper pubZjsdInfoEntityMapper;
    @Autowired
    SdjgMapper sdjgMapper;

    @Autowired
    LogService logService;
    @Autowired
    YysdService yysdService;
    @Autowired
    KdtdMapper kdtdMapper;
    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;

    @Override
    public List<PubYysdSsdrModel> querySsdrList(QuerySsdrListVo querySsdrListVo) {
        List<PubYysdSsdrModel> result = new ArrayList<>();
        if(null == querySsdrListVo){
            return result;
        }


        result = pubYysdSsdrEntityMapper.selectByYysdbh(querySsdrListVo.getYysdbh());
        return result;
    }

    @Override
    public List<PubYysdSsdrModel> findByYysdbh(Integer yysdbh) {
        return pubYysdSsdrEntityMapper.selectByYysdbh(yysdbh);
    }

    @Override
    public RetObj inputData(InputDataSsdrVo inputDataSsdrVo,String yhm) {
        RetObj obj = new RetObj();
        obj.setCode("200");
        obj.setMessage("接口调用成功");
        if(null == inputDataSsdrVo){
            obj.setCode("0");
            obj.setMessage("提交数据异常");
            return obj;
        }

        String content = inputDataSsdrVo.getContent();
        String inputDataType = inputDataSsdrVo.getInputDataType();
        Integer ssdrbh = inputDataSsdrVo.getSsdrbh();
        Integer yysdbh = inputDataSsdrVo.getYysdbh();

        //校验数据
        if(null == yysdbh || null == ssdrbh || StringUtil.isBlank(inputDataType) || StringUtil.isBlank(content)){
            obj.setCode("0");
            obj.setMessage("提交数据不完整，请检查后重试");
            return obj;
        }
        content = content.trim();


        //检验录入内容是否合法
        RetObj checkObj = checkInputDataContent(inputDataType,content);
        if(!Objects.equals(checkObj.getCode(),"200")){
            //检验不通过
            return checkObj;
        }


        //查询当事人信息
        PubYysdSsdrEntityKey ssdrKey = new PubYysdSsdrEntityKey();
        ssdrKey.setSsdrbh(ssdrbh);
        ssdrKey.setYysdbh(yysdbh);
        PubYysdSsdrEntity ssdrEntity = pubYysdSsdrEntityMapper.selectByPrimaryKey(ssdrKey);
        if(null == ssdrEntity){
            obj.setCode("0");
            obj.setMessage("未查询到当前受送达人信息，请检查后重试");
            return obj;
        }

        //查询工单信息
        PubYysdJbEntity jbEntity = pubYysdJbEntityMapper.selectByPrimaryKey(yysdbh);
        if(null == jbEntity){
            obj.setCode("0");
            obj.setMessage("未找到工单记录信息，请检查后重试");
            return obj;
        }

        //查询登陆人员信息
        PubYysdRyxxEntity ryxxEntity = pubYysdRyxxEntityMapper.findByYhdm(yhm);
        if(null == ryxxEntity){
            obj.setCode("0");
            obj.setMessage("登陆失效或用户不存在，请重新登陆后重试");
            return obj;
        }

        //插入数据
        int logTypeNum = 0;
        switch (inputDataType){
            case INPUT_ADDRESS:
                List<String> addressList = pubYysdSsdrdzEntityMapper.selectDzByyysdbhAndSsdrbh(yysdbh, ssdrbh);
                if(null == addressList){
                    addressList = new ArrayList<>();
                }
                if(addressList.contains(content)){
                    obj.setCode("0");
                    obj.setMessage("地址信息已存在，不可重复录入");
                }else {
                    inputData_address(inputDataSsdrVo,ssdrEntity);
                    logTypeNum = LRLXDZ.getTypeNum();
                }
                break;
            case INPUT_ID_CARD:
                if(StringUtil.isBlank(ssdrEntity.getSfzhm())){
                    inputData_idCard(inputDataSsdrVo,ssdrEntity);
                    logTypeNum = LRSFZ.getTypeNum();
                }else {
                    obj.setCode("0");
                    obj.setMessage("当事人身份证已存在，不可重复录入");
                }
                break;
            case INPUT_PHONE:
                List<String> phoneList = pubSsdrHmEntityMapper.selectByYysdbhAndSsdrbh(yysdbh, ssdrbh);
                if(null == phoneList){
                    phoneList = new ArrayList<>();
                }
                if(phoneList.contains(content)){
                    obj.setCode("0");
                    obj.setMessage("联系电话已存在，不可重复录入");
                }else {
                    inputData_phone(inputDataSsdrVo,ssdrEntity);
                    logTypeNum = LRLXDH.getTypeNum();
                }
                break;
        }

        //插入日志
        if(logTypeNum > 0){
            //案件序号  法院编号  当事人姓名  类型  备注  创建人
            logService.addLog(jbEntity.getAjxh(),jbEntity.getFybh(),ssdrEntity.getSsdrmc(),logTypeNum,content,ryxxEntity.getYhmc(),jbEntity.getYysdbh());
        }

        return obj;
    }

    @Override
    @Cacheable(value = "hisWebCall",key="#querySsdrHisDataVo.ssdrmc+'_'+#querySsdrHisDataVo.sfzhm")
    public List<PubWebCallInfoEntity> queryHisWebCall(QuerySsdrHisDataVo querySsdrHisDataVo) {
        return pubWebCallInfoEntityMapper.queryByssdrMcAndSfzhm(querySsdrHisDataVo.getSsdrmc(),querySsdrHisDataVo.getSfzhm());
    }

    @Override
    @Cacheable(value = "hisDxtz",key="#querySsdrHisDataVo.ssdrmc+'_'+#querySsdrHisDataVo.sfzhm")
    public List<PubDxtzInfoEntity> queryHisDxtz(QuerySsdrHisDataVo querySsdrHisDataVo) {
        return pubDxtzInfoEntityMapper.queryByssdrMcAndSfzhm(querySsdrHisDataVo.getSsdrmc(),querySsdrHisDataVo.getSfzhm());
    }

    @Override
    public List<PubLylqInfoEntity> queryHisLy(QuerySsdrHisDataVo querySsdrHisDataVo) {
        return pubLylqInfoEntityMapper.queryByssdrMcAndSfzhm(querySsdrHisDataVo.getSsdrmc(),querySsdrHisDataVo.getSfzhm());
    }

    @Override
    public List<PubZjsdInfoEntity> queryHisZj(QuerySsdrHisDataVo querySsdrHisDataVo) {
        return pubZjsdInfoEntityMapper.queryByssdrMcAndSfzhm(querySsdrHisDataVo.getSsdrmc(),querySsdrHisDataVo.getSfzhm());
    }

    @Override
    public String loadSsdrSdjg(Integer yysdbh, Integer ssdrbh, String fybh) {
        if(StringUtil.isEmpty(fybh)){
            DynamicDataSource.router(SDPT_FYDM);
            PubYysdJbEntity pubYysdJbEntity = pubYysdJbEntityMapper.selectByPrimaryKey(yysdbh);
            fybh = pubYysdJbEntity.getFybh();
        }
        //更改受送达人结果为 待送达 0 成功 1 失败 2 送达中 3 无 4
        String[] sdzt = {"待送达","送达成功","送达失败","送达中","无"};
        int index = 4;
        if(!NumberUtil.isIntNotNullAndGtZero(yysdbh) || !NumberUtil.isIntNotNullAndGtZero(ssdrbh)){
            //参数 不大于0 无效
            return sdzt[4];
        }
        try {
            DynamicDataSource.router(SDPT_FYDM);
            if(pubYysdSsdrEntityMapper.exists(yysdbh,ssdrbh)>0){
//                List<String> sdjgList = new ArrayList<String>(){{
//                    add(sdjgMapper.getExistSdjg(yysdbh, ssdrbh, SDCategory.WEB_CALL, true));
//                    add(sdjgMapper.getExistSdjg(yysdbh, ssdrbh, SDCategory.LYLQ, true));
//                    add(sdjgMapper.getExistSdjg(yysdbh, ssdrbh, SDCategory.DXTZ, true));
//                    add(sdjgMapper.getExistSdjg(yysdbh, ssdrbh, SDCategory.ZJSD, true));
//                }};
                //新增规则：短信通知列表短信状态发送成功+已访问即为送达成功，系统自动判定为当事人送达结果成功
                if(pubDxtzInfoEntityMapper.selectSdcgNumByYysdbhAndSsdrbh(yysdbh,ssdrbh)>0){
                    pubYysdSsdrEntityMapper.updateSdjgByYysdbhAndSsdrbh(yysdbh,ssdrbh,1);
                    return "送达成功";
                }
                List<String> sdjgList = new ArrayList<String>(){{
                    addAll(sdjgMapper.getSdjgList(yysdbh, ssdrbh, SDCategory.WEB_CALL));
                    addAll(sdjgMapper.getSdjgList(yysdbh, ssdrbh, SDCategory.LYLQ));
                    addAll(sdjgMapper.getSdjgList(yysdbh, ssdrbh, SDCategory.DXTZ));
                    addAll(sdjgMapper.getSdjgList(yysdbh, ssdrbh, SDCategory.ZJSD));
                }};
                DynamicDataSource.routerByFybh(fybh);
                sdjgList.addAll(sdjgMapper.getSdjgList(yysdbh, ssdrbh, SDCategory.EMSSD));
                //有一种方式成功视为成功
                //无成功则有一个失败即失败
                int wait = (int)sdjgList.stream().filter("-1"::equals).count();
                int success = (int)sdjgList.stream().filter("1"::equals).count();
                int failed = (int)sdjgList.stream().filter("2"::equals).count();
                success += (int)sdjgList.stream().filter(a->StringUtil.contains(a,"成功")).count();
                failed += (int)sdjgList.stream().filter(a->StringUtil.contains(a,"失败")).count();
                if(success>0){
                    index = 1;
                }else if(failed>0){
                    index = 2;
                }else {
                    index = 0;
                }
                if(index == 0 && success + failed + wait > 0){
                    index = 3;
                }
                DynamicDataSource.router(SDPT_FYDM);
                pubYysdSsdrEntityMapper.updateSdjgByYysdbhAndSsdrbh(yysdbh,ssdrbh,index);
            }

        }catch (Exception ex){
            //捕获异常
            ex.printStackTrace();
        }
        return sdzt[index];
    }

    @Override
    public PubYysdSsdrEntity findByYysdbhAndSsdrbh(Integer yysdbh, Integer ssdrbh) {
        String currentDB = DynamicDataSource.getCurrentDB();
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdSsdrEntity pubYysdSsdrEntity=pubYysdSsdrEntityMapper.selectByPrimaryKey(new PubYysdSsdrEntityKey(ssdrbh,yysdbh));
        DynamicDataSource.router(currentDB);
        return pubYysdSsdrEntity;
    }

    @Override
    public String readSdzt( Integer sdjg) {
        String[] sdzts = {"待送达","送达成功","送达失败","送达中","无"};

        return sdzts[sdjg];
    }

    @Override
    public void updateSdjgByYysdbhAndSsdrbh(Integer yysdbh, Integer ssdrbh, Integer sdjg) {
        pubYysdSsdrEntityMapper.updateSdjgByYysdbhAndSsdrbh(yysdbh,ssdrbh,sdjg);
    }

    @Override
    public  Integer savePubSsdrHmEntity(PubSsdrHmEntity pubSsdrHmEntity) {
         pubSsdrHmEntityMapper.insert(pubSsdrHmEntity);
         return pubSsdrHmEntity.getBh();
    }

    @Override
    public  Integer savePubYysdSsdrdzEntity(PubYysdSsdrdzEntity pubYysdSsdrdzEntity) {
        pubYysdSsdrdzEntityMapper.insert(pubYysdSsdrdzEntity);
        return pubYysdSsdrdzEntity.getBh();
    }

    /**
     * 录入地址
     * @param inputDataSsdrVo
     * @param ssdrEntity
     */
    private void inputData_address(InputDataSsdrVo inputDataSsdrVo, PubYysdSsdrEntity ssdrEntity) {
        PubYysdSsdrdzEntity ssdrdzEntity = new PubYysdSsdrdzEntity();
        ssdrdzEntity.setDz(inputDataSsdrVo.getContent());
        ssdrdzEntity.setLx(1);//1 代表录入地址
        ssdrdzEntity.setSdpName(ssdrEntity.getSsdrmc());
        ssdrdzEntity.setSsdrbh(ssdrEntity.getSsdrbh());
        ssdrdzEntity.setYysdbh(ssdrEntity.getYysdbh());
        ssdrdzEntity.setCreatetime(new Date());
        Integer integer = savePubYysdSsdrdzEntity(ssdrdzEntity);
        yysdService.addAddress(integer);
    }

    /**
     * 录入身份证
     * @param inputDataSsdrVo
     * @param ssdrEntity
     */
    private void inputData_idCard(InputDataSsdrVo inputDataSsdrVo, PubYysdSsdrEntity ssdrEntity) {

        if(StringUtil.isBlank(ssdrEntity.getSfzhm())){
            PubYysdSsdrEntity updateEntity = new PubYysdSsdrEntity();
            updateEntity.setSsdrbh(ssdrEntity.getSsdrbh());
            updateEntity.setYysdbh(ssdrEntity.getYysdbh());
            updateEntity.setSfzhm(inputDataSsdrVo.getContent());
            pubYysdSsdrEntityMapper.updateByPrimaryKeySelective(updateEntity);
        }
    }

    /**
     * 录入明文号码
     * @param inputDataSsdrVo
     * @param ssdrEntity
     */
    private void inputData_phone(InputDataSsdrVo inputDataSsdrVo, PubYysdSsdrEntity ssdrEntity) {
        PubSsdrHmEntity pubSsdrHmEntity = new PubSsdrHmEntity();
        pubSsdrHmEntity.setSdpName(ssdrEntity.getSsdrmc());
        pubSsdrHmEntity.setIdCard(ssdrEntity.getSfzhm());
        pubSsdrHmEntity.setOperatorType("ENTRY");
        pubSsdrHmEntity.setShowTel(inputDataSsdrVo.getContent());
        pubSsdrHmEntity.setOperatorTel(inputDataSsdrVo.getContent());
        pubSsdrHmEntity.setCreateTime(new Date());
        pubSsdrHmEntity.setYysdbh(inputDataSsdrVo.getYysdbh());
        pubSsdrHmEntity.setSsdrbh(inputDataSsdrVo.getSsdrbh());
        savePubSsdrHmEntity(pubSsdrHmEntity);
    }

    /**
     * 检验录入内容是否合法
     * @param inputDataType
     * @param content
     * @return code:200  =》校验通过  其他=》异常
     */
    private RetObj checkInputDataContent(String inputDataType, String content) {

        RetObj obj = new RetObj();
        obj.setCode("200"); //校验通过
        obj.setMessage("校验通过");

        String[] typeRule = {INPUT_ADDRESS,INPUT_ID_CARD,INPUT_PHONE};
        if(!Arrays.asList(typeRule).contains(inputDataType)){
            obj.setCode("0");
            obj.setMessage("录入类型错误，请检查后重试");
            return obj;
        }
        if(StringUtil.isBlank(content)){
            content = "";
        }
        content = content.trim();

        switch (inputDataType){
            case INPUT_ADDRESS:
                int length = content.length();
                if(length == 0 || length > 50){
                    obj.setCode("0");
                    obj.setMessage("地址信息1~50个字符");
                }
                break;
            case INPUT_ID_CARD:
                String idCardReg = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)";
                if(!Pattern.matches(idCardReg, content)){
                    obj.setCode("0");
                    obj.setMessage("身份证号码格式不正确");
                }
                break;
            case INPUT_PHONE:
                String phoneReg = "^\\d{10,12}$";
                if(!Pattern.matches(phoneReg, content)){
                    obj.setCode("0");
                    obj.setMessage("联系电话为10~12位纯数字");
                }
                break;
        }
        return obj;
    }


    /**
     * 获取受送达人信息
     * @param ajxh
     * @param fybh
     * @return
     */
    @Override
    public List<SsdrVO> getSsdrxxByAjxhAndFybhAndDsrbh(Integer ajxh, String fybh) {
        DynamicDataSource.router(SDPT_FYDM);
        return pubYysdSsdrEntityMapper.getSsdrxxListByAjxhAndFybh(ajxh, fybh);
    }

    @Override
    public int updateByPrimaryKey(PubYysdSsdrEntity pubYysdSsdrEntity) {
        return pubYysdSsdrEntityMapper.updateByPrimaryKey(pubYysdSsdrEntity);
    }

    @Override
    public void bindHisDsrxx(Integer ajxh, String fybh, List<DsrjbVO> dsrjbVOS) {
        for(DsrjbVO dsrjbVO:dsrjbVOS){
            List<String> dzs = pubYysdSsdrdzEntityMapper.getFgeditDsrdz(ajxh,fybh,dsrjbVO.getDsrbh());
            HashSet<String> dzSet = new HashSet<>(dzs);


            if(dsrjbVO.getSddz()!=null&&dsrjbVO.getSddz().length()>0){
                dsrjbVO.setSddz(dsrjbVO.getSddz()+";");
            }
            if(dsrjbVO.getDh()!=null&&dsrjbVO.getDh().length()>0){
                dsrjbVO.setDh(dsrjbVO.getDh()+";");
            }


            StringBuilder  sddz = new StringBuilder(dsrjbVO.getSddz()==null?"":dsrjbVO.getSddz());
           for (String str:dzSet){
               sddz.append("(历史预约信息)"+str+";");
           }

           List<String> dhs = pubSsdrHmEntityMapper.getFgeditDsrdh(ajxh,fybh,dsrjbVO.getDsrbh());
           HashSet<String> dhSet = new HashSet<>(dhs);

           StringBuilder dh = new StringBuilder(dsrjbVO.getDh()==null?"":dsrjbVO.getDh());
            for (String str:dhSet){
                dh.append("(历史预约信息)"+str+";");
            }
            if(!StringUtil.isBlank(dh.toString())){
                String dhtemp= dh.substring(0,dh.length()-1);
                dsrjbVO.setDh(dhtemp);
            }
            if(!StringUtil.isBlank(sddz.toString())){
                dsrjbVO.setSddz(sddz.substring(0,sddz.length()-1));
            }
        }
    }

    @Override
    public int deleteByPrimaryKey(PubYysdSsdrEntityKey key) {
        return pubYysdSsdrEntityMapper.deleteByPrimaryKey(key);
    }

    @Override
    public int insert(PubYysdSsdrEntity record) {
        return pubYysdSsdrEntityMapper.insert(record);
    }

    @Override
    public int insertSelective(PubYysdSsdrEntity record) {
        return pubYysdSsdrEntityMapper.insertSelective(record);
    }

    @Override
    public PubYysdSsdrEntity selectByPrimaryKey(PubYysdSsdrEntityKey key) {
        return pubYysdSsdrEntityMapper.selectByPrimaryKey(key);
    }

    @Override
    public int updateByPrimaryKeySelective(PubYysdSsdrEntity record) {
        return pubYysdSsdrEntityMapper.updateByPrimaryKeySelective(record);
    }
}
