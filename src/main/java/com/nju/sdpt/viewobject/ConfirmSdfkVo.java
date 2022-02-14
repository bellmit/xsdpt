package com.nju.sdpt.viewobject;

/**
 * 法官-确认送达反馈请求类
 */
public class ConfirmSdfkVo {

    /**
     * 工单编号
     */
    private Integer yysdbh;

    /**
     * 确认状态
     * true: 确认接收
     * false： 退回
     */
    private Boolean confirmState;

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }

    public Boolean getConfirmState() {
        return confirmState;
    }

    public void setConfirmState(Boolean confirmState) {
        this.confirmState = confirmState;
    }
}
