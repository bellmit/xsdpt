package com.nju.sdpt.service;


import com.nju.sdpt.model.PubDxmbInfoModel;

import java.util.List;

public interface DxmbService {

    /**
     * 加载全部可用短信模板
     * @return
     * @param fybh
     *
     */
    List<PubDxmbInfoModel> loadTemplateList(String fybh);
    List<PubDxmbInfoModel> selectDxmbList(String fybh);
    List<PubDxmbInfoModel> selectAllDxmbList();
    Integer updateTemplate (PubDxmbInfoModel pubDxmbInfoModel);
    Integer addTemplate (PubDxmbInfoModel pubDxmbInfoModel);
}
