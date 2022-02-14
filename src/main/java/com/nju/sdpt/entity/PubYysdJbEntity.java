package com.nju.sdpt.entity;

import java.util.Date;

public class PubYysdJbEntity {
    private Integer yysdbh;

    private Integer ajxh;

    private String ajxz;

    private String cbr = "";

    private Date yysj;

    private String yyrxm;

    private String fybh;

    private String bmmc;

    private String bgdh;

    private String sjhm;

    private String gdryxm;

    private Date sdsj;

    private String sdjg;

    private String dhsd;

    private String dzsd;

    private String emssd;

    private String ggsd;

    private String zjsd;

    private String lylq;

    private String wtsd;

    private String zhjsd;

    private String dxsd;

    private String laay ="";

    private String bz;

    private Integer fkzt;

    private String yysdbz;

    private Integer caxh;

    private String ajmc;

    private String ah;

    private String bmbh;

    private String ktsj;

    private String slcx;

    private Date cdsj;

    private Integer wsqs;


    public PubYysdJbEntity(Integer ajxh, String ah, String ajmc, String ajxz, String laay, String cbr, String yyrxm, String fybh, String bmmc, String bgdh, String sjhm, String yysdbz,String slcx) {
        this.ajxh = ajxh;
        this.ah = ah;
        this.ajmc = ajmc;
        this.laay = laay;
        this.ajxz = ajxz;
        this.cbr = cbr;
        this.yyrxm = yyrxm;
        this.fybh = fybh;
        this.bmmc = bmmc;
        this.bgdh = bgdh;
        this.sjhm = sjhm;
        this.yysdbz = yysdbz;
        this.slcx = slcx;
    }
    public PubYysdJbEntity(Integer ajxh, String ah, String ajmc, String ajxz, String laay, String cbr, String yyrxm, String fybh, String bmmc, String bgdh, String sjhm, String yysdbz,String ktsj,String slcx) {
        this.ajxh = ajxh;
        this.ah = ah;
        this.ajmc = ajmc;
        this.laay = laay;
        this.ajxz = ajxz;
        this.cbr = cbr;
        this.yyrxm = yyrxm;
        this.fybh = fybh;
        this.bmmc = bmmc;
        this.bgdh = bgdh;
        this.sjhm = sjhm;
        this.yysdbz = yysdbz;
        this.ktsj=ktsj;
        this.slcx = slcx;
    }
    public PubYysdJbEntity(Integer yysdbh, Integer ajxh, String ajxz, String cbr, Date yysj, String yyrxm, String fybh, String bmmc, String bgdh, String sjhm, String gdryxm, Date sdsj, String sdjg, String dhsd, String dzsd, String emssd, String ggsd, String zjsd, String lylq, String wtsd, String zhjsd, String dxsd, String laay, String bz, Integer fkzt, String yysdbz, Integer caxh, String ajmc, String ah, String bmbh, String ktsj, String slcx, Date cdsj) {
        this.yysdbh = yysdbh;
        this.ajxh = ajxh;
        this.ajxz = ajxz;
        this.cbr = cbr;
        this.yysj = yysj;
        this.yyrxm = yyrxm;
        this.fybh = fybh;
        this.bmmc = bmmc;
        this.bgdh = bgdh;
        this.sjhm = sjhm;
        this.gdryxm = gdryxm;
        this.sdsj = sdsj;
        this.sdjg = sdjg;
        this.dhsd = dhsd;
        this.dzsd = dzsd;
        this.emssd = emssd;
        this.ggsd = ggsd;
        this.zjsd = zjsd;
        this.lylq = lylq;
        this.wtsd = wtsd;
        this.zhjsd = zhjsd;
        this.dxsd = dxsd;
        this.laay = laay;
        this.bz = bz;
        this.fkzt = fkzt;
        this.yysdbz = yysdbz;
        this.caxh = caxh;
        this.ajmc = ajmc;
        this.ah = ah;
        this.bmbh = bmbh;
        this.ktsj = ktsj;
        this.slcx = slcx;
        this.cdsj = cdsj;
    }

    public PubYysdJbEntity(Integer yysdbh, Integer ajxh, String ajxz, String cbr, Date yysj, String yyrxm, String fybh, String bmmc, String bgdh, String sjhm, String gdryxm, Date sdsj, String sdjg, String dhsd, String dzsd, String emssd, String ggsd, String zjsd, String lylq, String wtsd, String zhjsd, String dxsd, String laay, String bz, Integer fkzt, String yysdbz, Integer caxh, String ajmc, String ah, String bmbh, String ktsj, String slcx, Date cdsj, Integer wsqs) {
        this.yysdbh = yysdbh;
        this.ajxh = ajxh;
        this.ajxz = ajxz;
        this.cbr = cbr;
        this.yysj = yysj;
        this.yyrxm = yyrxm;
        this.fybh = fybh;
        this.bmmc = bmmc;
        this.bgdh = bgdh;
        this.sjhm = sjhm;
        this.gdryxm = gdryxm;
        this.sdsj = sdsj;
        this.sdjg = sdjg;
        this.dhsd = dhsd;
        this.dzsd = dzsd;
        this.emssd = emssd;
        this.ggsd = ggsd;
        this.zjsd = zjsd;
        this.lylq = lylq;
        this.wtsd = wtsd;
        this.zhjsd = zhjsd;
        this.dxsd = dxsd;
        this.laay = laay;
        this.bz = bz;
        this.fkzt = fkzt;
        this.yysdbz = yysdbz;
        this.caxh = caxh;
        this.ajmc = ajmc;
        this.ah = ah;
        this.bmbh = bmbh;
        this.ktsj = ktsj;
        this.slcx = slcx;
        this.cdsj = cdsj;
        this.wsqs = wsqs;
    }


    public Integer getWsqs() {
        return wsqs;
    }

    public void setWsqs(Integer wsqs) {
        this.wsqs = wsqs;
    }

    public PubYysdJbEntity() {
        super();
    }

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }

    public Integer getAjxh() {
        return ajxh;
    }

    public void setAjxh(Integer ajxh) {
        this.ajxh = ajxh;
    }

    public String getAjxz() {
        return ajxz;
    }

    public void setAjxz(String ajxz) {
        this.ajxz = ajxz == null ? null : ajxz.trim();
    }

    public String getCbr() {
        return cbr;
    }

    public void setCbr(String cbr) {
        this.cbr = cbr == null ? null : cbr.trim();
    }

    public Date getYysj() {
        return yysj;
    }

    public void setYysj(Date yysj) {
        this.yysj = yysj;
    }

    public String getYyrxm() {
        return yyrxm;
    }

    public void setYyrxm(String yyrxm) {
        this.yyrxm = yyrxm == null ? null : yyrxm.trim();
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh == null ? null : fybh.trim();
    }

    public String getBmmc() {
        return bmmc;
    }

    public void setBmmc(String bmmc) {
        this.bmmc = bmmc == null ? null : bmmc.trim();
    }

    public String getBgdh() {
        return bgdh;
    }

    public void setBgdh(String bgdh) {
        this.bgdh = bgdh == null ? null : bgdh.trim();
    }

    public String getSjhm() {
        return sjhm;
    }

    public void setSjhm(String sjhm) {
        this.sjhm = sjhm == null ? null : sjhm.trim();
    }

    public String getGdryxm() {
        return gdryxm;
    }

    public void setGdryxm(String gdryxm) {
        this.gdryxm = gdryxm == null ? null : gdryxm.trim();
    }

    public Date getSdsj() {
        return sdsj;
    }

    public void setSdsj(Date sdsj) {
        this.sdsj = sdsj;
    }

    public String getSdjg() {
        return sdjg;
    }

    public void setSdjg(String sdjg) {
        this.sdjg = sdjg == null ? null : sdjg.trim();
    }

    public String getDhsd() {
        return dhsd;
    }

    public void setDhsd(String dhsd) {
        this.dhsd = dhsd == null ? null : dhsd.trim();
    }

    public String getDzsd() {
        return dzsd;
    }

    public void setDzsd(String dzsd) {
        this.dzsd = dzsd == null ? null : dzsd.trim();
    }

    public String getEmssd() {
        return emssd;
    }

    public void setEmssd(String emssd) {
        this.emssd = emssd == null ? null : emssd.trim();
    }

    public String getGgsd() {
        return ggsd;
    }

    public void setGgsd(String ggsd) {
        this.ggsd = ggsd == null ? null : ggsd.trim();
    }

    public String getZjsd() {
        return zjsd;
    }

    public void setZjsd(String zjsd) {
        this.zjsd = zjsd == null ? null : zjsd.trim();
    }

    public String getLylq() {
        return lylq;
    }

    public void setLylq(String lylq) {
        this.lylq = lylq == null ? null : lylq.trim();
    }

    public String getWtsd() {
        return wtsd;
    }

    public void setWtsd(String wtsd) {
        this.wtsd = wtsd == null ? null : wtsd.trim();
    }

    public String getZhjsd() {
        return zhjsd;
    }

    public void setZhjsd(String zhjsd) {
        this.zhjsd = zhjsd == null ? null : zhjsd.trim();
    }

    public String getDxsd() {
        return dxsd;
    }

    public void setDxsd(String dxsd) {
        this.dxsd = dxsd == null ? null : dxsd.trim();
    }

    public String getLaay() {
        return laay;
    }

    public void setLaay(String laay) {
        this.laay = laay == null ? null : laay.trim();
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }

    public Integer getFkzt() {
        return fkzt;
    }

    public void setFkzt(Integer fkzt) {
        this.fkzt = fkzt;
    }

    public String getYysdbz() {
        return yysdbz;
    }

    public void setYysdbz(String yysdbz) {
        this.yysdbz = yysdbz == null ? null : yysdbz.trim();
    }

    public Integer getCaxh() {
        return caxh;
    }

    public void setCaxh(Integer caxh) {
        this.caxh = caxh;
    }

    public String getAjmc() {
        return ajmc;
    }

    public void setAjmc(String ajmc) {
        this.ajmc = ajmc == null ? null : ajmc.trim();
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah == null ? null : ah.trim();
    }

    public String getBmbh() {
        return bmbh;
    }

    public void setBmbh(String bmbh) {
        this.bmbh = bmbh == null ? null : bmbh.trim();
    }

    public String getKtsj() {
        return ktsj;
    }

    public void setKtsj(String ktsj) {
        this.ktsj = ktsj == null ? null : ktsj.trim();
    }

    public String getSlcx() {
        return slcx;
    }

    public void setSlcx(String slcx) {
        this.slcx = slcx == null ? null : slcx.trim();
    }

    public Date getCdsj() {
        return cdsj;
    }

    public void setCdsj(Date cdsj) {
        this.cdsj = cdsj;
    }
}