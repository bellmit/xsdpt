package com.nju.sdpt.viewobject;

import com.nju.sdpt.entity.PubYysdWsEntity;
import org.springframework.web.multipart.MultipartFile;

public class WsInfo {
    Integer yysdbh;
    String wslb;
    Integer wsly;//文书来源 1.文书基表 2.表格生成签章 3.线上传输 4.线下传输
    Integer wslybh;
    String wsnr;
    Integer qzbh;
    Integer dsrbh;
    String stampId;//传到前端方便法官直接下载
    String wsmc;//自行上传的文件用
    Integer ajxh;
    String bz;//备注
    String ext;//文件扩展名
    MultipartFile wsFile;

    public String getWslb() {
        return wslb;
    }

    public void setWslb(String wslb) {
        this.wslb = wslb;
    }

    public Integer getWsly() {
        return wsly;
    }

    public void setWsly(Integer wsly) {
        this.wsly = wsly;
    }

    public Integer getWslybh() {
        return wslybh;
    }

    public void setWslybh(Integer wslybh) {
        this.wslybh = wslybh;
    }

    public String getWsnr() {
        return wsnr;
    }

    public void setWsnr(String wsnr) {
        this.wsnr = wsnr;
    }

    public Integer getQzbh() {
        return qzbh;
    }

    public void setQzbh(Integer qzbh) {
        this.qzbh = qzbh;
    }

    public Integer getDsrbh() {
        return dsrbh;
    }

    public void setDsrbh(Integer dsrbh) {
        this.dsrbh = dsrbh;
    }

    public String getStampId() {
        return stampId;
    }

    public void setStampId(String stampId) {
        this.stampId = stampId;
    }

    public String getWsmc() {
        return wsmc;
    }

    public void setWsmc(String wsmc) {
        this.wsmc = wsmc;
    }

    public Integer getAjxh() {
        return ajxh;
    }

    public void setAjxh(Integer ajxh) {
        this.ajxh = ajxh;
    }

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public MultipartFile getWsFile() {
        return wsFile;
    }

    public void setWsFile(MultipartFile wsFile) {
        this.wsFile = wsFile;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public WsInfo() {
    }

    public WsInfo(String wslb, Integer wsly, Integer wslybh, String wsnr, Integer qzbh, Integer dsrbh, String stampId,String bz) {
        this.wslb = wslb;
        this.wsly = wsly;
        this.wslybh = wslybh;
        this.wsnr = wsnr;
        this.qzbh = qzbh;
        this.dsrbh = dsrbh;
        this.stampId = stampId;
        this.bz = bz;
    }




    public WsInfo(String wslb, Integer wsly, Integer wslybh, String wsnr, Integer qzbh, Integer dsrbh, String stampId, String wsmc, Integer ajxh,String bz) {
        this.wslb = wslb;
        this.wsly = wsly;
        this.wslybh = wslybh;
        this.wsnr = wsnr;
        this.qzbh = qzbh;
        this.dsrbh = dsrbh;
        this.stampId = stampId;
        this.wsmc = wsmc;
        this.ajxh = ajxh;
        this.bz = bz;
    }


    public WsInfo(Integer yysdbh, String wslb, Integer wsly, Integer wslybh, String wsnr, Integer qzbh, Integer dsrbh, String stampId, String wsmc, Integer ajxh, String bz) {
        this.yysdbh = yysdbh;
        this.wslb = wslb;
        this.wsly = wsly;
        this.wslybh = wslybh;
        this.wsnr = wsnr;
        this.qzbh = qzbh;
        this.dsrbh = dsrbh;
        this.stampId = stampId;
        this.wsmc = wsmc;
        this.ajxh = ajxh;
        this.bz = bz;
    }


    public PubYysdWsEntity getWsEntity(){
        PubYysdWsEntity pubYysdWsEntity = new PubYysdWsEntity();
        pubYysdWsEntity.setYysdbh(yysdbh);
        pubYysdWsEntity.setWslb(wslb);
        pubYysdWsEntity.setWsly(wsly);
        pubYysdWsEntity.setSsdrbh(dsrbh);
        pubYysdWsEntity.setWsmc(wsmc);
        return pubYysdWsEntity;
    }
}
