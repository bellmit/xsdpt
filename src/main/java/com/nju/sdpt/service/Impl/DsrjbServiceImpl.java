package com.nju.sdpt.service.Impl;


import com.nju.sdpt.entity.*;
import com.nju.sdpt.mapper.*;
import com.nju.sdpt.service.DmService;
import com.nju.sdpt.service.DsrjbService;
import com.nju.sdpt.service.QtsscyrService;
import com.nju.sdpt.util.CheckUtil;
import com.nju.sdpt.util.StringUtil;
import com.nju.sdpt.viewobject.CaDsrjbVO;
import com.nju.sdpt.viewobject.DsrjbVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DsrjbServiceImpl implements DsrjbService {
    @Autowired
    DsrJbEntityMapper dsrJbEntityMapper;
    @Autowired
    DsrGrEntityMapper dsrGrEntityMapper;
    @Autowired
    DsrDwEntityMapper dsrDwEntityMapper;
    @Autowired
    DsrJgEntityMapper dsrJgEntityMapper;
    @Autowired
    DmService dmService;
    @Autowired
    QtsscyrService qtsscyrService;
    @Autowired
    AjJbMapper ajJbMapper;

    public List<DsrJbEntity> getDsrjblistByAjxh(Integer ajxh) {
        return dsrJbEntityMapper.getDsrjblistByAjxh(ajxh);
    }

    @Override
    public DsrJbEntity getDsrByAjxhAndDsrbh(Integer ajxh, Integer dsrbh) {
        return dsrJbEntityMapper.getDsrjblistByAjxhAndDsrbh(ajxh,dsrbh);
    }

    @Override
    public DsrGrEntity getDsrGrByAjxhAndDsrbh(Integer ajxh, Integer dsrbh) {
        return dsrGrEntityMapper.selectByPrimaryKey(ajxh,dsrbh);
    }

    @Override
    public DsrDwEntity getDsrDwByAjxhAndDsrbh(Integer ajxh, Integer dsrbh) {
        return dsrDwEntityMapper.selectByPrimaryKey(ajxh,dsrbh);
    }

    @Override
    public DsrJgEntity getDsrJgByAjxhAndDsrbh(Integer ajxh, Integer dsrbh) {
        return dsrJgEntityMapper.selectByPrimaryKey(ajxh, dsrbh);
    }

    @Override
    public DsrjbVO dsrEntityToDsrVO(DsrJbEntity dsrJbDO) {
        AjJbEntity ajjbxxEntity = ajJbMapper.findById(dsrJbDO.getAjxh());
        try {
            DsrjbVO dsrjbVO = new DsrjbVO();
            dsrjbVO.setAjxh(dsrJbDO.getAjxh());
            dsrjbVO.setDsrjc(dsrJbDO.getDsrjc());
            dsrjbVO.setSsdr(dsrJbDO.getSsdr());
            dsrjbVO.setDsrssdw(dmService.getDsrssdwByAjxzAndSpcxAndSpcxdz(dsrJbDO.getDsrssdw(), ajjbxxEntity.getAjxz(), ajjbxxEntity.getSpcx(), ajjbxxEntity.getSpcxdz(), ajjbxxEntity.getLarq()).getDmms());
            dsrjbVO.setDsrbh(dsrJbDO.getDsrbh());
            StringBuilder sddz= new StringBuilder();
            StringBuilder dh = new StringBuilder();
            String [] dsrlxList = {"","个人当事人","单位当事人","机关当事人"};
            for (int i=0; i<dsrlxList.length;i++){
                if(StringUtil.equals(dsrJbDO.getDsrlb(),i+"")){
                    dsrjbVO.setDsrlb(dsrlxList[i]);
                }
            }
            if(dsrJbDO.getSddz()!=null&&!StringUtil.equals(dsrJbDO.getSddz(),"")){
                sddz.append("送达地址:").append(dsrJbDO.getSddz()).append(";");
            }
            if(StringUtil.equals("1",dsrJbDO.getDsrlb())){
                DsrGrEntity dsrGrEntity = getDsrGrByAjxhAndDsrbh(dsrJbDO.getAjxh(),dsrJbDO.getDsrbh());

                String sfzhm = dsrGrEntity.getSfzhm();
                if(!StringUtil.isBlank(dsrGrEntity.getDh())){
                    dh.append(dsrGrEntity.getDh()).append(";");
                }
                if(sfzhm!=null){
                    if(CheckUtil.validate(sfzhm)){//过滤无效身份证号码
                        dsrjbVO.setSfzhm(sfzhm);
                    }
                }
                if(dsrGrEntity.getDz()!=null&&!StringUtil.equals(dsrGrEntity.getDz(),"")){
                    sddz.append("经常居住地:").append(dsrGrEntity.getDz()).append(";");
                }
                if(dsrGrEntity.getGzdw()!=null&&!StringUtil.equals(dsrGrEntity.getGzdw(),"")){
                    sddz.append("工作单位:").append(dsrGrEntity.getGzdw()).append(";");
                }
                if(dsrGrEntity.getZzd()!=null&&!StringUtil.equals(dsrGrEntity.getZzd(),"")){
                    sddz.append("户籍所在地:").append(dsrGrEntity.getZzd()).append(";");
                }
            }else if(StringUtil.equals("2",dsrJbDO.getDsrlb())){
                DsrDwEntity dsrDwEntity = getDsrDwByAjxhAndDsrbh(dsrJbDO.getAjxh(),dsrJbDO.getDsrbh());
                if(!StringUtil.isBlank(dsrDwEntity.getLxdh())){
                  dh.append(dsrDwEntity.getLxdh()).append(";");
                }
                if(StringUtil.isNotBlank(dsrDwEntity.getDh())){
                    dh.append(dsrDwEntity.getDh()).append(";");
                }
                if(dsrDwEntity.getDz()!=null&&!StringUtil.equals(dsrDwEntity.getDz(),"")){
                    sddz.append("单位地址:").append(dsrDwEntity.getDz()).append(";");
                }
            }else {
                DsrJgEntity dsrJgEntity = getDsrJgByAjxhAndDsrbh(dsrJbDO.getAjxh(),dsrJbDO.getDsrbh());
                    if(!StringUtil.isBlank(dsrJgEntity.getDh())){
                        dh.append(dsrJgEntity.getDh()).append(";");
                    }
                if(dsrJgEntity.getDz()!=null&&!StringUtil.equals(dsrJgEntity.getDz(),"")){
                    sddz.append("单位地址:").append(dsrJgEntity.getDz()).append(";");
                }
            }
            if(!StringUtil.isBlank(dh.toString())){
                String dhtemp= dh.substring(0,dh.length()-1);
              dsrjbVO.setDh(dhtemp);
            }
            if(!StringUtil.isBlank(sddz.toString())){
            dsrjbVO.setSddz(sddz.substring(0,sddz.length()-1).toString());
            }
            String daisrStr ="";
            List<PubQtsscyrEntity> pubQtsscyrEntities = qtsscyrService.getQtsscyr(ajjbxxEntity.getAjxh(),dsrJbDO.getDsrbh());
            for (PubQtsscyrEntity pubQtsscyrEntity:pubQtsscyrEntities){
                daisrStr+=pubQtsscyrEntity.getXm()+";";
            }
            if(daisrStr.length()!=0){
                daisrStr = daisrStr.substring(0,daisrStr.length()-1);
            }
            dsrjbVO.setDaisr(daisrStr);
            return dsrjbVO;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    @Override
    public List<CaDsrjbVO> getDsrVOByAjxhs(String ajxhs) {
        List<AjJbEntity> ajJbEntities = ajJbMapper.findByAjxhs(ajxhs);
        Map<Integer,AjJbEntity> ajMap = new LinkedHashMap<>();
        for (AjJbEntity ajJbEntity:ajJbEntities){
            ajMap.put(ajJbEntity.getAjxh(),ajJbEntity);
        }
          //先找到公共当事人,放到Map里
        Map<String,List<DsrjbVO>> dsrMap = new LinkedHashMap<>();
        List<DsrJbEntity> dsrJbEntities = dsrJbEntityMapper.getDsrjblistByAjxhs(ajxhs);
        for (DsrJbEntity dsrJbEntity:dsrJbEntities){
            DsrjbVO dsrjbVO = dsrEntityToDsrVO(dsrJbEntity);
            //将当事人编号重新进行排序
            dsrjbVO.setDsrbh(dsrJbEntities.indexOf(dsrJbEntity)+1);
            List<DsrjbVO> dsrJbVOList = dsrMap.get(dsrJbEntity.getDsrjc());
            if(dsrJbVOList==null){
                List<DsrjbVO> dsrjbVOLinkedList = new LinkedList<>();
                dsrjbVOLinkedList.add(dsrjbVO);
                dsrMap.put(dsrJbEntity.getDsrjc(),dsrjbVOLinkedList);
            }else {
                dsrJbVOList.add(dsrjbVO);
                dsrMap.put(dsrJbEntity.getDsrjc(),dsrJbVOList);
            }
        }
        List<CaDsrjbVO> caDsrjbVOS = new ArrayList<>();
        for (Map.Entry<String,List<DsrjbVO>> dsrList:dsrMap.entrySet()){
            DsrjbVO dsrjbVO = dsrList.getValue().get(0);
            CaDsrjbVO caDsrjbVO = new CaDsrjbVO(dsrjbVO);
            Integer[] ajxhArray = new Integer[dsrList.getValue().size()];
            String szah ="";
            for (int i=0;i<dsrList.getValue().size();i++){
                Integer ajxh = dsrList.getValue().get(i).getAjxh();
                ajxhArray[i]= ajxh;
                szah+=ajMap.get(ajxh).getAh()+";";
            }
            szah=szah.substring(0,szah.length()-1);
            caDsrjbVO.setAjxhs(ajxhArray);
            caDsrjbVO.setSzah(szah);
            caDsrjbVOS.add(caDsrjbVO);
        }
        return caDsrjbVOS;
    }

    @Override
    public List<DsrJbEntity> getDsrjblistByAjxhs(String ajxhs) {
        return dsrJbEntityMapper.getDsrjblistByAjxhs(ajxhs);
    }

    @Override
    public HashMap<Integer, List<String>> getDsrHmByAjxh(Integer ajxh) {
        HashMap<Integer, List<String>> dsrDhMap = new LinkedHashMap<>();
        List<DsrJbEntity> dsrJbEntities = dsrJbEntityMapper.getDsrjblistByAjxh(ajxh);
        for (DsrJbEntity dsrJbEntity:dsrJbEntities){
            ArrayList<String> dhs = new ArrayList<>();
            if(StringUtil.equals("1",dsrJbEntity.getDsrlb())){
                DsrGrEntity dsrGrEntity = getDsrGrByAjxhAndDsrbh(dsrJbEntity.getAjxh(),dsrJbEntity.getDsrbh());
                if(StringUtil.isNotBlank(dsrGrEntity.getDh()))
                    dhs.add(dsrGrEntity.getDh());
            }
            if(StringUtil.equals("2",dsrJbEntity.getDsrlb())){
                DsrDwEntity dsrDwEntity = getDsrDwByAjxhAndDsrbh(dsrJbEntity.getAjxh(),dsrJbEntity.getDsrbh());
                if(StringUtil.isNotBlank(dsrDwEntity.getDh()))
                    dhs.add(dsrDwEntity.getDh());
                if(StringUtil.isNotBlank(dsrDwEntity.getLxdh()))
                    dhs.add(dsrDwEntity.getLxdh());
            }
            if(StringUtil.equals("3",dsrJbEntity.getDsrlb())){
                DsrJgEntity dsrJgEntity = getDsrJgByAjxhAndDsrbh(dsrJbEntity.getAjxh(),dsrJbEntity.getDsrbh());
                if(StringUtil.isNotBlank(dsrJgEntity.getDh()))
                    dhs.add(dsrJgEntity.getDh());
            }

            dsrDhMap.put(dsrJbEntity.getDsrbh(),dhs);
        }
        return dsrDhMap;

    }
}
