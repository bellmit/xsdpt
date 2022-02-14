package com.nju.sdpt.service.Impl;


import com.nju.sdpt.entity.*;
import com.nju.sdpt.mapper.*;
import com.nju.sdpt.model.LylqModel;
import com.nju.sdpt.model.SdhzModel;
import com.nju.sdpt.model.YysdModel;
import com.nju.sdpt.model.WsModel;
import com.nju.sdpt.service.LogService;
import com.nju.sdpt.service.LylqService;
import com.nju.sdpt.service.SsdrService;
import com.nju.sdpt.util.BASE64DecodedMultipartFile;
import com.nju.sdpt.util.DateUtil;
import com.nju.sdpt.util.FileUtil;
import com.nju.sdpt.util.StringUtil;
import com.nju.sdpt.viewobject.LylqLoadListVo;
import com.nju.sdpt.viewobject.LylqUploadSdhzVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;

@Service
public class LylqServiceImpl implements LylqService {

    @Autowired
    PubLylqInfoEntityMapper pubLylqInfoEntityMapper;

    @Autowired
    PubYysdJbEntityMapper pubYysdJbEntityMapper;

    @Autowired
    PubYysdRyxxEntityMapper pubYysdRyxxEntityMapper;
    @Autowired
    LogService logService;
    @Autowired
    SsdrService ssdrService;

    @Autowired
    PubYysdSsdrEntityMapper pubYysdSsdrEntityMapper;

    @Autowired
    PubYysdSdwsEntityMapper pubYysdSdwsEntityMapper;

    @Autowired
    PubLylqSdhzEntityMapper pubLylqSdhzEntityMapper;


    @Override
    public List<LylqModel> loadList(LylqLoadListVo lylqLoadListVo) {
        if(null == lylqLoadListVo){
            lylqLoadListVo = new LylqLoadListVo();
        }
        List<LylqModel> lylqModelList = new ArrayList<>();
        if(lylqLoadListVo.getSdybh()==null){
            return lylqModelList;
        }
        lylqModelList = pubLylqInfoEntityMapper.loadList(lylqLoadListVo);
        if(null != lylqModelList && lylqModelList.size() > 0){
            for (LylqModel model : lylqModelList) {
                //格式化时间
                model.setYylqsjStr(DateUtil.format(model.getYylqsj(),"yyyy-MM-dd HH:mm"));
            }
        }
        return lylqModelList;
    }

    @Override
    public List<LylqModel> fgLoadList(String yhm, String fybh,int sdjg) {
        return pubLylqInfoEntityMapper.fgLoadList(yhm,fybh,sdjg);
    }

    @Override
    public Integer addLylq(LylqModel pubLylqInfoEntity, PubYysdRyxxEntity ryxxEntity) {
        String yhmc = ryxxEntity.getYhmc();
        PubYysdJbEntity pubYysdJbEntity = pubYysdJbEntityMapper.selectByPrimaryKey(pubLylqInfoEntity.getYysdbh());
        //查询受送达人信息
        PubYysdSsdrEntityKey pubYysdSsdrKey = new PubYysdSsdrEntityKey();
        pubYysdSsdrKey.setYysdbh(pubYysdJbEntity.getYysdbh());
        pubYysdSsdrKey.setSsdrbh(pubLylqInfoEntity.getSsdrbh());
        PubYysdSsdrEntity ssdrEntity = pubYysdSsdrEntityMapper.selectByPrimaryKey(pubYysdSsdrKey);

        pubLylqInfoEntity.setCreateTime(new Date());
        pubLylqInfoEntity.setUpdateTime(new Date());
        pubLylqInfoEntity.setCreateOperator(yhmc);
        pubLylqInfoEntity.setUpdateOperator(yhmc);
        pubLylqInfoEntity.setSdybh(ryxxEntity.getYhbh());
        pubLylqInfoEntity.setLqstate(0);
        pubLylqInfoEntity.setSdjg(0);
        pubLylqInfoEntity.setFybh(pubYysdJbEntity.getFybh());
        pubLylqInfoEntity.setAjxh(pubYysdJbEntity.getAjxh());
        pubLylqInfoEntity.setFybh(pubYysdJbEntity.getFybh());
        pubLylqInfoEntity.setAjxh(pubYysdJbEntity.getAjxh());
        Integer lylqid = savePubLylqInfoEntity(pubLylqInfoEntity);

        // 保存文书类型
        List<String> dsrwslbArray = pubLylqInfoEntity.getDsrwslbArray();
        for(String str : dsrwslbArray){
            if(str.contains("_")){
                String[] yysdbhBh = str.split("_");
                // 这里的 yysdbh 其实是 lylqid
                PubYysdSdwsEntity pubYysdSdwsEntity = new PubYysdSdwsEntity(Integer.parseInt(yysdbhBh[0]), Integer.parseInt(yysdbhBh[1]),"lylq",lylqid);
                pubYysdSdwsEntityMapper.insert(pubYysdSdwsEntity);
            }

        }

        //案件序号  法院编号  当事人姓名  类型  备注  创建人
        logService.addLog(pubYysdJbEntity.getAjxh(),pubYysdJbEntity.getFybh(),ssdrEntity.getSsdrmc(),25,"",yhmc,pubYysdJbEntity.getYysdbh());
        pubYysdJbEntityMapper.updateStatusByWayAndYysdbh("LYLQ",pubLylqInfoEntity.getYysdbh());
        return lylqid;
    }

    @Override
    public void addFgLylq(PubLylqInfoEntity pubLylqInfoEntity) {
        pubLylqInfoEntity.setCreateTime(new Date());
        pubLylqInfoEntity.setUpdateTime(new Date());
        pubLylqInfoEntity.setLqstate(0);
        pubLylqInfoEntity.setSdjg(0);
        savePubLylqInfoEntity(pubLylqInfoEntity);

    }

    @Override
    public List<YysdModel> getYysdListByGdryxm(String gdryxm) {
        return pubYysdJbEntityMapper.getYysdListByGdryxm(gdryxm);
    }

    @Override
    public List<WsModel> getYysdListByyysdbh(Integer yysdbh,Integer ssdrbh) {
        return pubYysdJbEntityMapper.getYysdListByyysdbh(yysdbh,ssdrbh);
    }


    @Override
    public List<YysdModel> getYysdList(Integer yysdbh){
        return pubYysdJbEntityMapper.getYysdList(yysdbh);
    }

    @Override
    public void uploadSdhz(LylqUploadSdhzVo lylqUploadSdhzVo) {

        Integer lylqId = lylqUploadSdhzVo.getLylqId();
        PubLylqInfoEntity pubLylqInfoEntity = pubLylqInfoEntityMapper.selectByPrimaryKey(lylqId);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] wsfile;
        if(StringUtil.isNotBlank(lylqUploadSdhzVo.getSdhzBase64Str())){
            try {
                MultipartFile wsnr = BASE64DecodedMultipartFile.base64ToMultipart(lylqUploadSdhzVo.getSdhzBase64Str());
                InputStream inputStream = wsnr.getInputStream();

                byte [] bytes = new byte[1024];
                int length = 0;
                while ((length=inputStream.read(bytes))!=-1){
                    byteArrayOutputStream.write(bytes);
                }
                // 这里保存个1，代表有数据，剩下存进磁盘中。
//                wsfile =byteArrayOutputStream.toByteArray();
                pubLylqInfoEntity.setSdhz("1".getBytes());
                pubLylqInfoEntity.setSdjg(1);
                pubLylqInfoEntity.setSubmittime(new Date());
                lylqUploadSdhzVo.setSdjg(1);
            }catch (Exception ex){
                System.out.println("异常");
            }
        }
        if(null != lylqUploadSdhzVo.getSdjg()){
            //维护送达结果 送达结果 0: 待送达  1：送达成功  2：送达失败  默认0)
            pubLylqInfoEntity.setSdjg(lylqUploadSdhzVo.getSdjg());
            if(lylqUploadSdhzVo.getSdjg()!=null&&lylqUploadSdhzVo.getSdjg()>0){
                pubLylqInfoEntity.setSubmittime(new Date());
            }

            //查询工单记录
            PubYysdJbEntity pubYysdJbEntity = pubYysdJbEntityMapper.selectByPrimaryKey(pubLylqInfoEntity.getYysdbh());

            if(null != pubYysdJbEntity){
                //维护来院送达结果时
                //送达成功 -》 更改工单的送达结果相关字段
                if(Objects.equals(1,lylqUploadSdhzVo.getSdjg())){
                    pubYysdJbEntity.setLylq("Y");
//                    pubYysdJbEntity.setSdjg("Y");

                    pubLylqInfoEntity.setLqstate(1);
                }else {
                    pubYysdJbEntity.setLylq("N");
                    pubLylqInfoEntity.setLqstate(2);
                }
                pubYysdJbEntityMapper.updateByPrimaryKeySelective(pubYysdJbEntity);
            }else {
                if(Objects.equals(1,lylqUploadSdhzVo.getSdjg())){
                    pubLylqInfoEntity.setLqstate(1);
                }else {
                    pubLylqInfoEntity.setLqstate(2);
                }
            }
        }
        pubLylqInfoEntityMapper.updateByPrimaryKeySelective(pubLylqInfoEntity);

        // 保存送达回执路径在PUB_LYLQ_SDHZ中
        String sdhzFolderName = pubLylqInfoEntity.getFybh()+"_"+pubLylqInfoEntity.getAjxh()+"_"+pubLylqInfoEntity.getLylqid();
        PubLylqSdhzEntity pubLylqSdhzEntity = new PubLylqSdhzEntity();
        pubLylqSdhzEntity.setLylqid(pubLylqInfoEntity.getLylqid());
        pubLylqSdhzEntity.setYysdbh(pubLylqInfoEntity.getYysdbh());
        String folderName = "E:\\lyhz\\"+sdhzFolderName;
        pubLylqSdhzEntity.setSdhzfolder(folderName);
        pubLylqSdhzEntity.setSdhzimage(lylqUploadSdhzVo.getImageName());
        pubLylqSdhzEntityMapper.insert(pubLylqSdhzEntity);
        try{
            File dir = new File(folderName);
            if (!dir.exists()) {
                dir.mkdir();
            }
            FileUtil.ByteToFile(byteArrayOutputStream.toByteArray(),pubLylqSdhzEntity.getSdhzfolder() + "/" + lylqUploadSdhzVo.getImageName());
        }catch (Exception e){
            e.printStackTrace();
        }

        //加载当事人送达结果
        ssdrService.loadSsdrSdjg(pubLylqInfoEntity.getYysdbh(),pubLylqInfoEntity.getSsdrbh(), pubLylqInfoEntity.getFybh());
    }

    @Override
    public PubLylqInfoEntity getById(Integer lylqId) {
        return pubLylqInfoEntityMapper.selectByPrimaryKey(lylqId);
    }

    @Override
    public Integer savePubLylqInfoEntity(PubLylqInfoEntity pubLylqInfoEntity) {
         pubLylqInfoEntityMapper.insert(pubLylqInfoEntity);
        return pubLylqInfoEntity.getLylqid();
    }

    @Override
    public List<PubLylqSdhzEntity> getPubLylqSdhzEntityByLylqid(Integer lylqId) {
        return pubLylqSdhzEntityMapper.findBylylqid(lylqId);
    }
}
