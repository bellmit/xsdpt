package com.nju.sdpt.viewobject;



import java.util.List;

public class AjInfo {
    Integer ajxh;
    String fybh;
    String ajmc;
    String yhm;
    String fgmc;
    String ah;
    List<SsdrVO> ssdrVOS;

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public List<SsdrVO> getSsdrVOS() {
        return ssdrVOS;
    }

    public void setSsdrVOS(List<SsdrVO> ssdrVOS) {
        this.ssdrVOS = ssdrVOS;
    }

    public Integer getAjxh() {
        return ajxh;
    }

    public void setAjxh(Integer ajxh) {
        this.ajxh = ajxh;
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh;
    }

    public String getFgmc() {
        return fgmc;
    }

    public void setFgmc(String fgmc) {
        this.fgmc = fgmc;
    }

    public String getYhm() {
        return yhm;
    }

    public void setYhm(String yhm) {
        this.yhm = yhm;
    }

    public String getAjmc() {
        return ajmc;
    }

    public void setAjmc(String ajmc) {
        this.ajmc = ajmc;
    }
}
