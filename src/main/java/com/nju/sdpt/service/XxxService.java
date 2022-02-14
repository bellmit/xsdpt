package com.nju.sdpt.service;

/**
 * Created by XXT on 2019/5/24.
 */

/**
 * 信息项服务
 * @author byron
 *
 */
public interface XxxService {

    /**
     * 根据表名和列名活动类别编号
     * @param szb
     * @param szl
     * @param fybh
     */
    String getLbbh2016ByTableAndColumn(String szb, String szl, long fybh);

    /**
     * 根据表名和列名活动类别编号
     * @param szb 所在表
     * @param szl 所在列
     * @param fybh 法院编号
     * @return String
     */
    public String getLbbhByTableAndColumn(String szb, String szl, long fybh);






}
