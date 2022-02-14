package com.nju.sdpt.service;

import com.nju.sdpt.viewobject.openApi.FzSsdrDataPushVo;

public interface HisSsdrDataInfoService {
    Boolean ssdr_info_push(FzSsdrDataPushVo pushVo);

    /**
     * 根据预约送达编号查询下属当事人历史基本信息并绑定
     * @param yysdbh
     * @return
     */
    Boolean bindSsdrHistoryData(Integer yysdbh);

}
