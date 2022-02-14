package com.nju.sdpt.service;

import com.nju.sdpt.entity.PubYysdSsdrdzEntity;
import com.nju.sdpt.model.PubYysdSsdrdzModel;
import com.nju.sdpt.viewobject.SelectSsdrdzVo;

import java.util.List;

public interface SsdrDzService {
    /**
     * 根据条件查询受送达人地址信息
     *
     * @param selectSsdrdzVo
     * @return
     */
    List<PubYysdSsdrdzModel> selectSsdrdz(SelectSsdrdzVo selectSsdrdzVo);

    int deleteByPrimaryKey(Integer bh);

    int insert(PubYysdSsdrdzEntity record);

    int insertSelective(PubYysdSsdrdzEntity record);

    PubYysdSsdrdzEntity selectByPrimaryKey(Integer bh);

    int updateByPrimaryKeySelective(PubYysdSsdrdzEntity record);

    int updateByPrimaryKey(PubYysdSsdrdzEntity record);
}
