package com.nju.sdpt.viewobject;

/**
 * 外呼请求类
 */
public class WebCallVo {

    /**
     * 工单编号
     */
    private Integer yysdbh;

    /**
     * 受送达人编号
     */
    private Integer ssdrbh;

    /**
     * 号码编号 即id
     */
    private Integer telBh;

    /**
     * 登陆用户名
     */
    private String yhm;

    public Integer getSsdrbh() {
        return ssdrbh;
    }

    public void setSsdrbh(Integer ssdrbh) {
        this.ssdrbh = ssdrbh;
    }

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }

    public Integer getTelBh() {
        return telBh;
    }

    public void setTelBh(Integer telBh) {
        this.telBh = telBh;
    }

    public String getYhm() {
        return yhm;
    }

    public void setYhm(String yhm) {
        this.yhm = yhm;
    }
}
