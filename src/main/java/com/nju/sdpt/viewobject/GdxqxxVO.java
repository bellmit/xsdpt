package com.nju.sdpt.viewobject;

import com.nju.sdpt.entity.PubYysdJbEntity;
import com.nju.sdpt.entity.PubYysdSsdrQtsscyrEntity;
import com.nju.sdpt.entity.PubYysdWsEntity;
import com.nju.sdpt.model.FYEnum;
import com.nju.sdpt.model.PubYysdSsdrModel;

import java.util.List;

public class GdxqxxVO {
    PubYysdJbEntity jb;
    PubYysdWsEntity[] ws;
    /**
     * 受送达人集合
     */
    List<PubYysdSsdrModel> pubYysdSsdrModelList;

    String fymc;

    /**
     * 相关诉讼参与人集合
     */
    List<PubYysdSsdrQtsscyrEntity> qtsscyr;
    public List<PubYysdSsdrModel> getPubYysdSsdrModelList() {
        return pubYysdSsdrModelList;
    }

    public void setPubYysdSsdrModelList(List<PubYysdSsdrModel> pubYysdSsdrModelList) {
        this.pubYysdSsdrModelList = pubYysdSsdrModelList;
    }

    public GdxqxxVO() {
    }

    public GdxqxxVO(PubYysdJbEntity jb, PubYysdWsEntity[] ws) {
        this.jb = jb;
        this.ws = ws;
    }

    public GdxqxxVO(PubYysdJbEntity jb, PubYysdWsEntity[] ws, List<PubYysdSsdrModel> pubYysdSsdrModelList) {
        this.jb = jb;
        this.ws = ws;
        this.pubYysdSsdrModelList = pubYysdSsdrModelList;
        this.fymc = FYEnum.getFyByFybh(jb.getFybh()).getJc();

    }

    public PubYysdJbEntity getJb() {
        return jb;
    }

    public void setJb(PubYysdJbEntity jb) {
        this.jb = jb;
    }

    public PubYysdWsEntity[] getWs() {
        return ws;
    }

    public void setWs(PubYysdWsEntity[] ws) {
        this.ws = ws;
    }

    public String getFymc() {
        return fymc;
    }

    public void setFymc(String fymc) {
        this.fymc = fymc;
    }

    public List<PubYysdSsdrQtsscyrEntity> getQtsscyr() {
        return qtsscyr;
    }

    public void setQtsscyr(List<PubYysdSsdrQtsscyrEntity> qtsscyr) {
        this.qtsscyr = qtsscyr;
    }
}
