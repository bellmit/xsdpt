package com.nju.sdpt.service;


import com.nju.sdpt.entity.*;
import com.nju.sdpt.model.*;
import com.nju.sdpt.viewobject.DsrjbVO;
import com.nju.sdpt.viewobject.WsInfo;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface YysdService {
    Map<Integer, List<WsInfo>> getWsInfosByAjxh(Integer ajxh);

    Integer save(PubYysdJbEntity pubYysdJbEntity);

    List<YysdModel> getAssignedRecord(String start,String end,String fybh);

    List<YysdModel> getUndistributedRecord(String start,String end,String fybh);

    List<PubYysdRyxxEntity> getSdry(String fybh);

    List<PubYysdRyxxEntity> getAllSdry();
    Integer saveYysdxxdistribution(DistributeYysdModel distributeYysdModel);


    void saveWs(PubYysdJbEntity pubYysdJbEntity, List<WsInfo> wsInfos) throws IOException;



    List<PubYysdJbEntity> getGdWclByGdryxm(String gdryxm, String way, Integer emsyysdbh);

    void updateSdcgByKey(Integer yysdbh, String sdWay);

    void updateSdsbByKey(Integer yysdbh, String sdWay);

    PubYysdJbEntity selectByPrimaryKey(Integer yysdbh);

    PubYysdJbEntity selectByAjxhAndFybh(Integer ajxh, String fybh);

    List<PubYysdJbEntity> getGdYclByGdryxm(String yhdm, String way, Integer emsyysdbh);

    PubYysdWsEntity[] selectByYysdbh(Integer yysdbh);

    PubYysdWsEntity selectByYysdbhAndBh(int yysdbh,int bh);


    void saveSsdrHm(PubSsdrHmEntity pubSsdrHmEntity);

    void saveSsdrDz(PubYysdSsdrdzEntity pubYysdSsdrdzEntity);

    void saveSsdr(PubYysdSsdrEntity pubYysdSsdrEntity);



    List<YysdModel> findByCbrAndCljg(String yhmc, Integer cljg,String fybh);

    List<YysdModel> findByGdryxmAndSdjg(String yhmc, Integer sdjg);
    List<YysdModel> findByGdryxmAndSdjg(String yhmc, Integer sdjg,String start,String end);

    List<ZsalModel> zsalSjtj();
    List<ZwhlModel> zwhlSjtj();
    List<ZxflModel> zxflSjtj();
    List<ZdxlModel> zdxlSjtj();

    void updateBzByYysdbh(Integer yysdbh, String bz);

    void updateKtsjByYysdbh(Integer yysdbh, String ktsj);

    void addAddress(Integer bh);
    //新增工单生成xml
    void addNewOrder(Integer yysdbh,Integer empid);

    //工单撤回
    void gdWithdrawByYysdbh(Integer yysdbh);

    List<YysdModel> mergeGd(List<YysdModel> yysdModels2, List<YysdModel> yysdModels3, List<YysdModel> yysdModels4, List<YysdModel> yysdModels5,List<YysdModel>yysdModels6,List<YysdModel>yysdModels7);


    void saveQtsscyr(PubQtsscyrEntity pubQtsscyrEntity,Integer yysdbh,Integer ssdrbh);

   Map<Integer,List<WsInfo>> getCaWsInfosByAjxh(String ajxhs);

    PubYysdSsdrEntity saveDsrjbVO(DsrjbVO dsrjbVO, int yysdbh, HashMap<Integer, List<String>> dsrDhMap);

    void saveCaxx(PubCaxxEntity pubCaxxEntity);

    void uploadSdjgMain(Integer yysdbh, String sdjg);

    Map<Integer, List<PubYysdSsdrEntity>> getSsdrByYysdbhs(String yysdbhs);

    List<PubYysdJbEntity> getGdByGdryxm(String yhm);

    String getYhmc(String yhm);

    Integer updateUser(PubYysdRyxxEntity pubDxmbInfoModel);

    Integer addUser(PubYysdRyxxEntity pubDxmbInfoModel);

    //获取登录用户名
    String getLoginYhm(HttpServletRequest request);

    public List<PubYysdWsEntity> getDsrwslb(Integer yysdbh, Integer ssdrbh);

    public PubYysdWsEntity getByYysdbhAndBh(Integer yysdbh, Integer bh);

    //检查没填写送达结果的记录
    List<String> checkSdjg(Integer yysdbh);

    //更新实体类
    int updateByPrimaryKeySelective(PubYysdJbEntity record);

}
