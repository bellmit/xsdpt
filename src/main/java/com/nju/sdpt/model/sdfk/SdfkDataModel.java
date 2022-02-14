package com.nju.sdpt.model.sdfk;

import com.nju.sdpt.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 送达反馈数据信息类
 */
public class SdfkDataModel {

    /**
     * 预约送达编号 - 工单编号
     */
    private Integer yysdbh;

    /**
     * 受送达人编号
     */
    private Integer ssdrbh;

    /**
     * 送达反馈状态 - 工单 FKZT  1=>代表已确认
     */
    private Integer fkzt;

    /**
     * 案号
     */
    private String ah = "";

    /**
     * 承办法官名称
     */
    private String cbfgmc = "";

    //部门名称
    private String bmmc;

    //预约时间
    private String yysj;

    //反馈时间
    private String fksj;

    //送达周期
    private Integer sdzq;

    private String yyrxm;
    /**
     * 法官预约备注
     */
    private String yysdbz = "";

    /**
     * 案由
     */
    private String anyou = "";

    /**
     * 受送达人名称
     */
    private String ssdrMc = "";
    //诉讼地位
    private String ssdw;
    /**
     * 受送达人送达结果
     */
    private String ssdrSdjg = "";

    /**
     * 受送达人身份证号码
     */
    private String sfzHm = "";

    /**
     * 送达文书名称（多个逗号分割）
     */
    private String sdwsMcs = "";

    /**
     * 联系电话（多个逗号分隔）
     */
    private String webTels = "";

    private String sdqrWebTels = "";

    /**
     * 联系地址（多个逗号分隔）
     */
    private String address = "暂无";

    /**
     * 是否签署送达地址确认书
     */
    private String addressQrs = "暂无";

    /**
     * 是否同意电子送达
     */
    private String dzshQr = "暂无";

    private String ktsj = "";

    private String zasdjg = "";
    private String sdcgfs = "";
    /**
     * 电话送达结果集合
     */
    private List<DhsdResultModel> dhsdResultModelList = new ArrayList<>();

    /**
     * 电子送达结果集
     */
    private List<DzsdResultModel> dzsdResultModelList = new ArrayList<>();

    /**
     * 来院领取结果集合
     */
    private List<LylqResultModel> lylqResultModelList = new ArrayList<>();
    /**
     * EMS结果集合
     */
    private List<EmsResultModel> emsResultModelList = new ArrayList<>();

    /**
     * 直接送达结果 只显示最新一条数据
     * 模板：  上门时间 ，上门地址，送达结果
     *         2019.12.18，天津市河东区河东路100号101室，送达结果：送达失败
     */
    private List<ZjsdResultModel> zjsdResultModels;

    public List<ZjsdResultModel> getZjsdResultModels() {
        return zjsdResultModels;
    }

    public void setZjsdResultModels(List<ZjsdResultModel> zjsdResultModels) {
        this.zjsdResultModels = zjsdResultModels;
    }

    /**
     * 备注
     */
    private String remark = "";

    /**
     * 送达结果
     */
    private String sdjg = "暂无";

    /**
     * 送达专员名称（外包人员）
     *
     */
    private String sdzyName = "";

    public String getDhsdStr(){
        String result = "";
        if(null != dhsdResultModelList){
            for (DhsdResultModel model : dhsdResultModelList) {
                result += model.getSdfkStr();
            }
        }

        return result;
    }
    public String getDzsdStr(){
        String result = "";
        if(null != dzsdResultModelList){
            for (DzsdResultModel model : dzsdResultModelList) {
                result += model.getSdfkStr();
            }
        }
        return result;
    }
    public String getLylqStr(){
        String result = "";
        if(null != lylqResultModelList){
            for (LylqResultModel model : lylqResultModelList) {
                result += model.getSdfkStr();
            }
        }
        return result;
    }
    public String getEmsStr(){
        String result = "";
        if(null != emsResultModelList){
            for (EmsResultModel model : emsResultModelList) {
                result += model.getSdfkStr();
            }
        }
        return result;
    }

    public Integer getFkzt() {
        return fkzt;
    }

    public void setFkzt(Integer fkzt) {
        this.fkzt = fkzt;
    }

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }


    public Integer getSsdrbh() {
        return ssdrbh;
    }

    public void setSsdrbh(Integer ssdrbh) {
        this.ssdrbh = ssdrbh;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public String getCbfgmc() {
        return cbfgmc;
    }

    public void setCbfgmc(String cbfgmc) {
        this.cbfgmc = cbfgmc;
    }

    public String getSsdrSdjg() {
        return ssdrSdjg;
    }

    public void setSsdrSdjg(String ssdrSdjg) {
        this.ssdrSdjg = ssdrSdjg;
    }

    public String getAnyou() {
        return anyou;
    }

    public void setAnyou(String anyou) {
        this.anyou = anyou;
    }

    public String getSsdrMc() {
        return ssdrMc;
    }

    public void setSsdrMc(String ssdrMc) {
        this.ssdrMc = ssdrMc;
    }

    public String getSfzHm() {
        if(null == sfzHm){
            return "暂无";
        }
        return sfzHm;
    }

    public void setSfzHm(String sfzHm) {
        this.sfzHm = sfzHm;
    }

    public String getSdwsMcs() {
        return sdwsMcs;
    }

    public void setSdwsMcs(String sdwsMcs) {
        this.sdwsMcs = sdwsMcs;
    }

    public String getWebTels() {
        return webTels;
    }

    public void setWebTels(String webTels) {
        this.webTels = webTels;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressQrs() {
        return addressQrs;
    }

    public void setAddressQrs(String addressQrs) {
        this.addressQrs = addressQrs;
    }

    public String getDzshQr() {
        return dzshQr;
    }

    public void setDzshQr(String dzshQr) {
        this.dzshQr = dzshQr;
    }

    public List<DhsdResultModel> getDhsdResultModelList() {
        return dhsdResultModelList;
    }

    public void setDhsdResultModelList(List<DhsdResultModel> dhsdResultModelList) {
        this.dhsdResultModelList = dhsdResultModelList;
    }

    public List<DzsdResultModel> getDzsdResultModelList() {
        return dzsdResultModelList;
    }

    public void setDzsdResultModelList(List<DzsdResultModel> dzsdResultModelList) {
        this.dzsdResultModelList = dzsdResultModelList;
    }

    public List<LylqResultModel> getLylqResultModelList() {
        return lylqResultModelList;
    }

    public void setLylqResultModelList(List<LylqResultModel> lylqResultModelList) {
        this.lylqResultModelList = lylqResultModelList;
    }

    public List<EmsResultModel> getEmsResultModelList() {
        return emsResultModelList;
    }

    public void setEmsResultModelList(List<EmsResultModel> emsResultModelList) {
        this.emsResultModelList = emsResultModelList;
    }

    public String getRemark() {
        if(null == remark){
            return "暂无";
        }
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSdjg() {
        return sdjg;
    }

    public void setSdjg(String sdjg) {
        this.sdjg = sdjg;
    }

    public String getSdzyName() {
        return sdzyName;
    }

    public void setSdzyName(String sdzyName) {
        this.sdzyName = sdzyName;
    }

    public String getYysdbz() {
        return yysdbz;
    }

    public void setYysdbz(String yysdbz) {
        this.yysdbz = yysdbz;
    }

    public String getBmmc() {
        return bmmc;
    }

    public void setBmmc(String bmmc) {
        this.bmmc = bmmc;
    }

    public String getYysj() {
        return yysj;
    }

    public void setYysj(String yysj) {
        this.yysj = yysj;
    }

    public String getFksj() {
        return fksj;
    }

    public void setFksj(String fksj) {
        this.fksj = fksj;
    }

    public Integer getSdzq() {
        return sdzq;
    }

    public void setSdzq(Integer sdzq) {
        this.sdzq = sdzq;
    }

    public String getSsdw() {
        return ssdw;
    }

    public void setSsdw(String ssdw) {
        this.ssdw = ssdw;
    }

    public String getYyrxm() {
        return yyrxm;
    }

    public void setYyrxm(String yyrxm) {
        this.yyrxm = yyrxm;
    }

    public String getKtsj() {
        return ktsj;
    }

    public void setKtsj(String ktsj) {
        this.ktsj = ktsj;
    }

    public String getZasdjg() {
        return zasdjg;
    }

    public void setZasdjg(String zasdjg) {
        this.zasdjg = zasdjg;
    }

    public String getSdqrWebTels() {
        return sdqrWebTels;
    }

    public void setSdqrWebTels(String sdqrWebTels) {
        this.sdqrWebTels = sdqrWebTels;
    }

    public String getSdcgfs() {
        return sdcgfs;
    }

    public void setSdcgfs(String sdcgfs) {
        this.sdcgfs = sdcgfs;
    }
}
