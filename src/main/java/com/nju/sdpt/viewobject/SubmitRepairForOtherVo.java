package com.nju.sdpt.viewobject;

import java.util.List;

public class SubmitRepairForOtherVo {
    String yysdbh;
    List<SsdrVO> ssdrVOS;

    public SubmitRepairForOtherVo() {
    }

    public SubmitRepairForOtherVo(String yysdbh, List<SsdrVO> ssdrVOS) {
        this.yysdbh = yysdbh;
        this.ssdrVOS = ssdrVOS;
    }

    public String getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(String yysdbh) {
        this.yysdbh = yysdbh;
    }

    public List<SsdrVO> getSsdrVOS() {
        return ssdrVOS;
    }

    public void setSsdrVOS(List<SsdrVO> ssdrVOS) {
        this.ssdrVOS = ssdrVOS;
    }
}
