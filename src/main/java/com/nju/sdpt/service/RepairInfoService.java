package com.nju.sdpt.service;

import com.nju.sdpt.model.RepairInfoModel;
import com.nju.sdpt.model.sdfk.SdfkDataModel;
import com.nju.sdpt.viewobject.ConfirmSdfkVo;
import com.nju.sdpt.viewobject.RepairQueryVo;

import java.util.List;

/**
 * 修复记录业务
 */
public interface RepairInfoService {
    /**
     * 加载列表数据
     * @param queryVo
     * @return
     */
    List<RepairInfoModel> loadList(RepairQueryVo queryVo);

    void handle_old_data();

}
