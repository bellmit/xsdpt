package com.nju.sdpt.service.Impl;

import com.nju.sdpt.entity.PubDxmbInfoEntity;
import com.nju.sdpt.mapper.PubDxmbInfoEntityMapper;
import com.nju.sdpt.mapper.PubYysdRyxxEntityMapper;
import com.nju.sdpt.model.FYEnum;
import com.nju.sdpt.model.PubDxmbInfoModel;
import com.nju.sdpt.service.DxmbService;
import com.nju.sdpt.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DxmbServiceImpl implements DxmbService {
    @Autowired
    PubDxmbInfoEntityMapper pubDxmbInfoEntityMapper;
    @Autowired
    PubYysdRyxxEntityMapper pubYysdRyxxEntityMapper;
    @Override
    public List<PubDxmbInfoModel> loadTemplateList(String fybh) {
        List<PubDxmbInfoEntity> pubDxmbInfoEntityList = pubDxmbInfoEntityMapper.selectEnablesList(fybh);
        List<PubDxmbInfoModel> result = new ArrayList<>();
        if(null != pubDxmbInfoEntityList && pubDxmbInfoEntityList.size() > 0){
            for (PubDxmbInfoEntity infoEntity : pubDxmbInfoEntityList) {
                PubDxmbInfoModel model = new PubDxmbInfoModel();
                BeanUtils.copyProperties(infoEntity,model);
                //加载模板参数
                List<String> paramNameList = new ArrayList<>();
                String content = infoEntity.getMbnr();
                if(StringUtil.isNotBlank(content)){
                    for (Integer i = 0; i < content.length(); i++) {
                        Integer beginIndex = find(content,"{{",i);
                        Integer endIndex = find(content,"}}",i);

                        if(beginIndex >=0 && endIndex >= 0){
                            paramNameList.add(content.substring(beginIndex+2,endIndex));
                        }else {
                            break;
                        }
                    }
                }
                model.setParamNameList(paramNameList);

                //记载模板所属法院名称
                FYEnum fyEnum = FYEnum.getFyByFybh(infoEntity.getFybh());
                if(null != fyEnum){
                    model.setFymc(fyEnum.getName());
                }
                result.add(model);
            }
        }
        return result;
    }

    @Override
    public List<PubDxmbInfoModel> selectDxmbList(String fybh) {
        return pubDxmbInfoEntityMapper.selectDxmbList(fybh);
    }

    @Override
    public List<PubDxmbInfoModel> selectAllDxmbList() {
        return pubDxmbInfoEntityMapper.selectAllDxmbList();
    }

    @Override
    public Integer updateTemplate(PubDxmbInfoModel pubDxmbInfoModel) {
        int i = pubDxmbInfoEntityMapper.updateByPrimaryKeySelective(pubDxmbInfoModel);
        return i;
    }

    @Override
    public Integer addTemplate(PubDxmbInfoModel pubDxmbInfoModel) {
        pubDxmbInfoModel.setCjsj(new Date());
        pubDxmbInfoEntityMapper.insert(pubDxmbInfoModel);
        return pubDxmbInfoModel.getBh();
    }

    private Integer find(String str,String cha,Integer num){
        Integer index = str.indexOf(cha);
        for (Integer i = 0; i < num; i++) {
            index = str.indexOf(cha,index+1);
        }
        return index;
    }

}
