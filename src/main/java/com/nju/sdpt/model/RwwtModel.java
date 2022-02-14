package com.nju.sdpt.model;

import com.nju.sdpt.entity.PubRwwtEntity;
import com.nju.sdpt.util.DateUtil;
import com.nju.sdpt.util.StringUtil;

public class RwwtModel {

    private Integer rwwtid;

    private Integer yysdbh;

    private String wtsj;

    private Integer ssdrbh;

    private String ssdrmc;

    private String wtrmc;

    private String clrmc;

    private String wtfs;

    private String clsj;

    private String cljg;

    private String ah;

    private String ajmc;

    private String fybh;

    private String fymc;

    public RwwtModel() {
    }

    public RwwtModel(PubRwwtEntity entity) {
        this.rwwtid = entity.getRwwtid();
        this.yysdbh = entity.getYysdbh();
        if(entity.getWtsj()!=null){
             this.wtsj = DateUtil.format(entity.getWtsj(),"yyyy-MM-dd HH:mm");
        }
        this.ssdrbh = entity.getSsdrbh();
        this.ssdrmc = entity.getSsdrmc();
        this.wtrmc = entity.getWtrmc();
        this.clrmc = entity.getClrmc();
        this.wtfs = generateWtfs(entity.getWtfs());
        if(entity.getWtsj()!=null){
            this.clsj = DateUtil.format(entity.getClsj(),"yyyy-MM-dd HH:mm");
        }
        this.cljg = generateCljg(entity.getCljg());

    }

    private String generateCljg(String cljg) {
        if(StringUtil.equals(cljg,"Y")){
            return "成功";
        }else  if(StringUtil.equals(cljg,"N")){
            return "失败";
        }else {
            return "暂无";
        }
    }

    private String generateWtfs(String wtfs) {
        String[] wtfsCN = {"电话送达","短信送达","邮寄送达","来院领取","直接送达"};
        String[] wtfsEng = {"DHSD","DXSD","EMS","LYLQ","ZZSD"};
        for (int i=0;i<wtfsEng.length;i++){
            if (StringUtil.equals(wtfs,wtfsEng[i])){
                return wtfsCN[i];
            }
        }
        return "未知送达";
    }


    public Integer getRwwtid() {
        return rwwtid;
    }

    public void setRwwtid(Integer rwwtid) {
        this.rwwtid = rwwtid;
    }

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }

    public String getWtsj() {
        return wtsj;
    }

    public void setWtsj(String wtsj) {
        this.wtsj = wtsj;
    }

    public Integer getSsdrbh() {
        return ssdrbh;
    }

    public void setSsdrbh(Integer ssdrbh) {
        this.ssdrbh = ssdrbh;
    }

    public String getSsdrmc() {
        return ssdrmc;
    }

    public void setSsdrmc(String ssdrmc) {
        this.ssdrmc = ssdrmc;
    }

    public String getWtrmc() {
        return wtrmc;
    }

    public void setWtrmc(String wtrmc) {
        this.wtrmc = wtrmc;
    }

    public String getClrmc() {
        return clrmc;
    }

    public void setClrmc(String clrmc) {
        this.clrmc = clrmc;
    }

    public String getWtfs() {
        return wtfs;
    }

    public void setWtfs(String wtfs) {
        this.wtfs = wtfs;
    }

    public String getClsj() {
        return clsj;
    }

    public void setClsj(String clsj) {
        this.clsj = clsj;
    }

    public String getCljg() {
        return cljg;
    }

    public void setCljg(String cljg) {
        this.cljg = cljg;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public String getAjmc() {
        return ajmc;
    }

    public void setAjmc(String ajmc) {
        this.ajmc = ajmc;
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh;
    }

    public String getFymc() {
        return fymc;
    }

    public void setFymc(String fymc) {
        this.fymc = fymc;
    }
}
