package com.nju.sdpt.service;

import com.nju.sdpt.entity.PubYysdRwlzEntity;
import com.nju.sdpt.viewobject.RwlzVO;

/**
 * 任务流转
 */
public interface RwlzService {
    int deleteByPrimaryKey(Integer bh);

    void insert(RwlzVO rwlzVO);

    int insertSelective(PubYysdRwlzEntity record);

    PubYysdRwlzEntity selectByPrimaryKey(Integer bh);

    int updateByPrimaryKeySelective(PubYysdRwlzEntity record);

    int updateByPrimaryKey(PubYysdRwlzEntity record);
}
