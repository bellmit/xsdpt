package com.nju.sdpt.service;

public interface SpryService {

    //获取案件当前的负责人(最高院送达用)
    String getAuditorByAjxh(Integer ajxh);

    String getCbrByAjxh(Integer ajxh);

    String getSjyByAjxh(Integer ajxh, String fybh);

}
