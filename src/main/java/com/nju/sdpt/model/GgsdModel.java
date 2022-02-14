package com.nju.sdpt.model;

import com.nju.sdpt.entity.PubGgxxEntity;

import java.text.SimpleDateFormat;

public class GgsdModel {
    /**
     * 提交公告日期
     */
    private String tjggrq;
    /**
     * 公告内容
     */
    private String ggnr;
    /**
     * 公告审核状态
     */
    private String ggshzt;
    /**
     * 导出时间
     */
    private String dcsj;

    /**
     * 送达人
     */
    private String sdr;

    public GgsdModel() {
    }

    public GgsdModel(PubGgxxEntity pubGgxxEntity) {
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
        this.tjggrq = sdf.format(pubGgxxEntity.getKdrq());
        this.ggnr = pubGgxxEntity.getGgnr();
        if(pubGgxxEntity.getDcrq()!=null){
            this.dcsj = sdf.format(pubGgxxEntity.getDcrq());
        }
        this.sdr = pubGgxxEntity.getSdr();
    }


    public GgsdModel(String tjggrq, String ggnr, String ggshzt, String dcsj, String sdr) {
        this.tjggrq = tjggrq;
        this.ggnr = ggnr;
        this.ggshzt = ggshzt;
        this.dcsj = dcsj;
        this.sdr = sdr;
    }

    public String getTjggrq() {
        return tjggrq;
    }

    public void setTjggrq(String tjggrq) {
        this.tjggrq = tjggrq;
    }

    public String getGgnr() {
        return ggnr;
    }

    public void setGgnr(String ggnr) {
        this.ggnr = ggnr;
    }

    public String getGgshzt() {
        return ggshzt;
    }

    public void setGgshzt(String ggshzt) {
        this.ggshzt = ggshzt;
    }

    public String getDcsj() {
        return dcsj;
    }

    public void setDcsj(String dcsj) {
        this.dcsj = dcsj;
    }

    public String getSdr() {
        return sdr;
    }

    public void setSdr(String sdr) {
        this.sdr = sdr;
    }
}
