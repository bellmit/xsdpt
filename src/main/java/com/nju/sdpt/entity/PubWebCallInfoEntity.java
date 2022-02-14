package com.nju.sdpt.entity;

import java.util.Date;

public class PubWebCallInfoEntity {
    private Integer webcallid;

    private Integer yysdbh;

    private Integer hmbh;

    private String telbatchno;

    private String uuid;

    private String operatortype;

    private String shownumber;

    private String seatnumber;

    private String webtel;

    private String targettel;

    private String callradio;

    private Integer callduration;

    private Integer querystate;

    private Integer callstate;

    private Date createtime;

    private String remarks;

    private String confirmnumber;

    private String confirmaddress;

    private Integer electronsend;

    private String sdtype;

    private Integer ssdrbh;

    private String fybh;

    private Integer ajxh;

    private Integer fgbh;

    private String seatid;

    private String sdfscontent;

    private Integer sdjg;

    private Integer sdybh;

    public PubWebCallInfoEntity(Integer webcallid, Integer yysdbh, Integer hmbh, String telbatchno, String uuid, String operatortype, String shownumber, String seatnumber, String webtel, String targettel, String callradio, Integer callduration, Integer querystate, Integer callstate, Date createtime, String remarks, String confirmnumber, String confirmaddress, Integer electronsend, String sdtype, Integer ssdrbh, String fybh, Integer ajxh, Integer fgbh, String seatid, String sdfscontent, Integer sdjg, Integer sdybh) {
        this.webcallid = webcallid;
        this.yysdbh = yysdbh;
        this.hmbh = hmbh;
        this.telbatchno = telbatchno;
        this.uuid = uuid;
        this.operatortype = operatortype;
        this.shownumber = shownumber;
        this.seatnumber = seatnumber;
        this.webtel = webtel;
        this.targettel = targettel;
        this.callradio = callradio;
        this.callduration = callduration;
        this.querystate = querystate;
        this.callstate = callstate;
        this.createtime = createtime;
        this.remarks = remarks;
        this.confirmnumber = confirmnumber;
        this.confirmaddress = confirmaddress;
        this.electronsend = electronsend;
        this.sdtype = sdtype;
        this.ssdrbh = ssdrbh;
        this.fybh = fybh;
        this.ajxh = ajxh;
        this.fgbh = fgbh;
        this.seatid = seatid;
        this.sdfscontent = sdfscontent;
        this.sdjg = sdjg;
        this.sdybh = sdybh;
    }

    public PubWebCallInfoEntity() {
        super();
    }

    public Integer getWebcallid() {
        return webcallid;
    }

    public void setWebcallid(Integer webcallid) {
        this.webcallid = webcallid;
    }

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }

    public Integer getHmbh() {
        return hmbh;
    }

    public void setHmbh(Integer hmbh) {
        this.hmbh = hmbh;
    }

    public String getTelbatchno() {
        return telbatchno;
    }

    public void setTelbatchno(String telbatchno) {
        this.telbatchno = telbatchno == null ? null : telbatchno.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getOperatortype() {
        return operatortype;
    }

    public void setOperatortype(String operatortype) {
        this.operatortype = operatortype == null ? null : operatortype.trim();
    }

    public String getShownumber() {
        return shownumber;
    }

    public void setShownumber(String shownumber) {
        this.shownumber = shownumber == null ? null : shownumber.trim();
    }

    public String getSeatnumber() {
        return seatnumber;
    }

    public void setSeatnumber(String seatnumber) {
        this.seatnumber = seatnumber == null ? null : seatnumber.trim();
    }

    public String getWebtel() {
        return webtel;
    }

    public void setWebtel(String webtel) {
        this.webtel = webtel == null ? null : webtel.trim();
    }

    public String getTargettel() {
        return targettel;
    }

    public void setTargettel(String targettel) {
        this.targettel = targettel == null ? null : targettel.trim();
    }

    public String getCallradio() {
        return callradio;
    }

    public void setCallradio(String callradio) {
        this.callradio = callradio == null ? null : callradio.trim();
    }

    public Integer getCallduration() {
        return callduration;
    }

    public void setCallduration(Integer callduration) {
        this.callduration = callduration;
    }

    public Integer getQuerystate() {
        return querystate;
    }

    public void setQuerystate(Integer querystate) {
        this.querystate = querystate;
    }

    public Integer getCallstate() {
        return callstate;
    }

    public void setCallstate(Integer callstate) {
        this.callstate = callstate;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getConfirmnumber() {
        return confirmnumber;
    }

    public void setConfirmnumber(String confirmnumber) {
        this.confirmnumber = confirmnumber == null ? null : confirmnumber.trim();
    }

    public String getConfirmaddress() {
        return confirmaddress;
    }

    public void setConfirmaddress(String confirmaddress) {
        this.confirmaddress = confirmaddress == null ? null : confirmaddress.trim();
    }

    public Integer getElectronsend() {
        return electronsend;
    }

    public void setElectronsend(Integer electronsend) {
        this.electronsend = electronsend;
    }

    public String getSdtype() {
        return sdtype;
    }

    public void setSdtype(String sdtype) {
        this.sdtype = sdtype == null ? null : sdtype.trim();
    }

    public Integer getSsdrbh() {
        return ssdrbh;
    }

    public void setSsdrbh(Integer ssdrbh) {
        this.ssdrbh = ssdrbh;
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh == null ? null : fybh.trim();
    }

    public Integer getAjxh() {
        return ajxh;
    }

    public void setAjxh(Integer ajxh) {
        this.ajxh = ajxh;
    }

    public Integer getFgbh() {
        return fgbh;
    }

    public void setFgbh(Integer fgbh) {
        this.fgbh = fgbh;
    }

    public String getSeatid() {
        return seatid;
    }

    public void setSeatid(String seatid) {
        this.seatid = seatid == null ? null : seatid.trim();
    }

    public String getSdfscontent() {
        return sdfscontent;
    }

    public void setSdfscontent(String sdfscontent) {
        this.sdfscontent = sdfscontent == null ? null : sdfscontent.trim();
    }

    public Integer getSdjg() {
        return sdjg;
    }

    public void setSdjg(Integer sdjg) {
        this.sdjg = sdjg;
    }

    public Integer getSdybh() {
        return sdybh;
    }

    public void setSdybh(Integer sdybh) {
        this.sdybh = sdybh;
    }
}