package com.nju.sdpt.service;

/**
 * Created by XXT on 2019/5/24.
 */

import com.nju.sdpt.model.BaseCaseModel;
import com.nju.sdpt.model.CaseContextModel;

/**
 *
 * 案件基本信息服务
 *
 *
 */
public interface BaseCaseService {



    /**
     * 根据案件序号获得案件上下文信息
     *
     * @param ajxh
     *            案件序号
     * @return CaseContextModel 案件的上下文
     */
    CaseContextModel getCaseContextByAjxh(long ajxh);

}
