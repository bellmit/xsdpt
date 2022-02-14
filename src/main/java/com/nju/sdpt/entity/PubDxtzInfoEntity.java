package com.nju.sdpt.entity;

import java.util.Date;

public class PubDxtzInfoEntity {
    private Integer dxtzid;

    private Integer yysdbh;

    private Integer dsrbgh;

    private Integer hmbh;

    private String webtel;

    private String targettel;

    private String telbatchno;

    private String serialnumber;

    private String operatortype;

    private String shownumber;

    private Integer sendstate;

    private Integer templateid;

    private String templateno;

    private String msgcontent;

    private String paramjson;

    private String msgreceipt;

    private Date createtime;

    private Integer sdjg;

    private String flashmsgreceipt;

    private Integer flashsendstate;

    private Integer ssdrbh;

    private String fybh;

    private Integer ajxh;

    private Integer fgbh;

    private Integer fwzt;

    private String taskid;

    private Integer sdybh;

    private Date submittime;

    private Integer wsnum;

    public PubDxtzInfoEntity(Integer dxtzid, Integer yysdbh, Integer dsrbgh, Integer hmbh, String webtel, String targettel, String telbatchno, String serialnumber, String operatortype, String shownumber, Integer sendstate, Integer templateid, String templateno, String msgcontent, String paramjson, String msgreceipt, Date createtime, Integer sdjg, String flashmsgreceipt, Integer flashsendstate, Integer ssdrbh, String fybh, Integer ajxh, Integer fgbh, Integer fwzt, String taskid, Integer sdybh, Date submittime) {
        this.dxtzid = dxtzid;
        this.yysdbh = yysdbh;
        this.dsrbgh = dsrbgh;
        this.hmbh = hmbh;
        this.webtel = webtel;
        this.targettel = targettel;
        this.telbatchno = telbatchno;
        this.serialnumber = serialnumber;
        this.operatortype = operatortype;
        this.shownumber = shownumber;
        this.sendstate = sendstate;
        this.templateid = templateid;
        this.templateno = templateno;
        this.msgcontent = msgcontent;
        this.paramjson = paramjson;
        this.msgreceipt = msgreceipt;
        this.createtime = createtime;
        this.sdjg = sdjg;
        this.flashmsgreceipt = flashmsgreceipt;
        this.flashsendstate = flashsendstate;
        this.ssdrbh = ssdrbh;
        this.fybh = fybh;
        this.ajxh = ajxh;
        this.fgbh = fgbh;
        this.fwzt = fwzt;
        this.taskid = taskid;
        this.sdybh = sdybh;
        this.submittime = submittime;
    }

    public PubDxtzInfoEntity() {
        super();
    }

    public Integer getDxtzid() {
        return dxtzid;
    }

    public void setDxtzid(Integer dxtzid) {
        this.dxtzid = dxtzid;
    }

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }

    public Integer getDsrbgh() {
        return dsrbgh;
    }

    public void setDsrbgh(Integer dsrbgh) {
        this.dsrbgh = dsrbgh;
    }

    public Integer getHmbh() {
        return hmbh;
    }

    public void setHmbh(Integer hmbh) {
        this.hmbh = hmbh;
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

    public String getTelbatchno() {
        return telbatchno;
    }

    public void setTelbatchno(String telbatchno) {
        this.telbatchno = telbatchno == null ? null : telbatchno.trim();
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber == null ? null : serialnumber.trim();
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

    public Integer getSendstate() {
        return sendstate;
    }

    public void setSendstate(Integer sendstate) {
        this.sendstate = sendstate;
    }

    public Integer getTemplateid() {
        return templateid;
    }

    public void setTemplateid(Integer templateid) {
        this.templateid = templateid;
    }

    public String getTemplateno() {
        return templateno;
    }

    public void setTemplateno(String templateno) {
        this.templateno = templateno == null ? null : templateno.trim();
    }

    public String getMsgcontent() {
        return msgcontent;
    }

    public void setMsgcontent(String msgcontent) {
        this.msgcontent = msgcontent == null ? null : msgcontent.trim();
    }

    public String getParamjson() {
        return paramjson;
    }

    public void setParamjson(String paramjson) {
        this.paramjson = paramjson == null ? null : paramjson.trim();
    }

    public String getMsgreceipt() {
        return msgreceipt;
    }

    public void setMsgreceipt(String msgreceipt) {
        this.msgreceipt = msgreceipt == null ? null : msgreceipt.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getSdjg() {
        return sdjg;
    }

    public void setSdjg(Integer sdjg) {
        this.sdjg = sdjg;
    }

    public String getFlashmsgreceipt() {
        return flashmsgreceipt;
    }

    public void setFlashmsgreceipt(String flashmsgreceipt) {
        this.flashmsgreceipt = flashmsgreceipt == null ? null : flashmsgreceipt.trim();
    }

    public Integer getFlashsendstate() {
        return flashsendstate;
    }

    public void setFlashsendstate(Integer flashsendstate) {
        this.flashsendstate = flashsendstate;
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

    public Integer getWsnum() {
        return wsnum;
    }

    public void setWsnum(Integer wsnum) {
        this.wsnum = wsnum;
    }

    public void setFgbh(Integer fgbh) {
        this.fgbh = fgbh;
    }

    public Integer getFwzt() {
        return fwzt;
    }

    public void setFwzt(Integer fwzt) {
        this.fwzt = fwzt;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid == null ? null : taskid.trim();
    }

    public Integer getSdybh() {
        return sdybh;
    }

    public void setSdybh(Integer sdybh) {
        this.sdybh = sdybh;
    }

    public Date getSubmittime() {
        return submittime;
    }

    public void setSubmittime(Date submittime) {
        this.submittime = submittime;
    }

    public PubDxtzInfoEntity(Integer dxtzid, Integer yysdbh, Integer dsrbgh, Integer hmbh, String webtel, String targettel, String telbatchno, String serialnumber, String operatortype, String shownumber, Integer sendstate, Integer templateid, String templateno, String msgcontent, String paramjson, String msgreceipt, Date createtime, Integer sdjg, String flashmsgreceipt, Integer flashsendstate, Integer ssdrbh, String fybh, Integer ajxh, Integer fgbh, Integer fwzt, String taskid, Integer sdybh, Date submittime, Integer wsnum) {
        this.dxtzid = dxtzid;
        this.yysdbh = yysdbh;
        this.dsrbgh = dsrbgh;
        this.hmbh = hmbh;
        this.webtel = webtel;
        this.targettel = targettel;
        this.telbatchno = telbatchno;
        this.serialnumber = serialnumber;
        this.operatortype = operatortype;
        this.shownumber = shownumber;
        this.sendstate = sendstate;
        this.templateid = templateid;
        this.templateno = templateno;
        this.msgcontent = msgcontent;
        this.paramjson = paramjson;
        this.msgreceipt = msgreceipt;
        this.createtime = createtime;
        this.sdjg = sdjg;
        this.flashmsgreceipt = flashmsgreceipt;
        this.flashsendstate = flashsendstate;
        this.ssdrbh = ssdrbh;
        this.fybh = fybh;
        this.ajxh = ajxh;
        this.fgbh = fgbh;
        this.fwzt = fwzt;
        this.taskid = taskid;
        this.sdybh = sdybh;
        this.submittime = submittime;
        this.wsnum = wsnum;
    }
}