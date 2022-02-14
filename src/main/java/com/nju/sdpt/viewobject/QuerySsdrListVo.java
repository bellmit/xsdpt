package com.nju.sdpt.viewobject;

import lombok.Data;

/**
 *
 *根据工单加载当事人list请求类
 */
@Data
public class QuerySsdrListVo {

    /**
     * 预约送达编号 即工单编号
     */
    private Integer yysdbh;

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }
}
