package com.nju.sdpt.service;


import com.nju.sdpt.entity.DsrDwEntity;
import com.nju.sdpt.entity.DsrGrEntity;
import com.nju.sdpt.entity.DsrJbEntity;
import com.nju.sdpt.entity.DsrJgEntity;
import com.nju.sdpt.viewobject.CaDsrjbVO;
import com.nju.sdpt.viewobject.DsrjbVO;

import java.util.HashMap;
import java.util.List;

public interface DsrjbService  {
    /**
     * 查看该案件所有当事人
     */
    List<DsrJbEntity> getDsrjblistByAjxh(Integer ajxh);

    DsrJbEntity getDsrByAjxhAndDsrbh(Integer ajxh, Integer dsrbh);

    DsrGrEntity getDsrGrByAjxhAndDsrbh(Integer ajxh, Integer dsrbh);


    DsrDwEntity getDsrDwByAjxhAndDsrbh(Integer ajxh, Integer dsrbh);

    DsrJgEntity getDsrJgByAjxhAndDsrbh(Integer ajxh, Integer dsrbh);

    DsrjbVO dsrEntityToDsrVO(DsrJbEntity dsrJbDO);

    List<CaDsrjbVO> getDsrVOByAjxhs(String ajxhs);

    List<DsrJbEntity> getDsrjblistByAjxhs(String ajxhs);

    HashMap<Integer, List<String>> getDsrHmByAjxh(Integer ajxh);
}
