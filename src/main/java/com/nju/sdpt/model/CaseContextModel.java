package com.nju.sdpt.model;

/**
 * Created by XXT on 2019/5/24.
 */


import com.nju.sdpt.entity.AjJbEntity;
import com.nju.sdpt.util.StringUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * 表明某个业务规则对应的案件情况。
 * 每个属性都是以代码编号为值。
 *
 *
 * @author zym
 *
 */
public class CaseContextModel implements Serializable {

    private static final long serialVersionUID = 1532462108975018732L;
    /**
     * 案件性质;以代码编号为值   ;
     */
    private String ajxz;
    /**
     * 审判程序;以代码编号为值
     */
    private String spcx;
    /**
     * 案件来源；以代码编号为值
     */
    private String ajly;

    /**
     * 适用程序;以代码编号为值
     */
    private String sycx;

    /**
     * 案由;以案由代码为值
     */
    private String ah;

    /**
     * 案件序号
     */
    private long ajxh;
    /**
     * 是否公诉
     */
    private String sfgs;
    /**
     * 是否一审
     */
    private String sfys;

    /**
     * 流程实例id
     */
    private long procInsId;

    private String slqk;

    private String sslx;

    private Date larq;

    private String sfxess;


    private String spcxdz;

    private String bz;

    private String jayydm;

    private String sfxssc;

    public CaseContextModel(String ajxz, String spcx, String ajly, String sycx,
                            String ah, long ajxh, long procInsId) {
        super();
        this.ajxz = ajxz;
        this.spcx = spcx;
        this.ajly = ajly;
        this.sycx = sycx;
        this.ah = ah;
        this.ajxh = ajxh;
        this.procInsId = procInsId;
    }

    public CaseContextModel(AjJbEntity dataobj){
        super();
        this.ajxz = StringUtil.trim(dataobj.getAjxz());
        this.spcx = StringUtil.trim(dataobj.getSpcx());
        this.ajly = StringUtil.trim(dataobj.getAjly());
        this.sycx = StringUtil.trim(dataobj.getSycx());
        this.ah = StringUtil.trim(dataobj.getAh());
        this.ajxh = dataobj.getAjxh();
        this.sfgs = StringUtil.trim(dataobj.getSfgs());
        this.sfys = StringUtil.trim(dataobj.getSfys());
        this.sslx = StringUtil.trim(dataobj.getSslx());
        this.slqk = StringUtil.trim(dataobj.getSlqk());
        this.larq = dataobj.getLarq();
        this.sfxess = StringUtil.trim(dataobj.getSfxess());
        this.spcxdz = StringUtil.trim(dataobj.getSpcxdz());
        this.jayydm = dataobj.getJayydm();
        this.sfxssc = dataobj.getSfxssc();
    }

    public CaseContextModel(BaseCaseModel baseCase){
        super();
        this.ajxz = StringUtil.trim(baseCase.getAjxz());
        this.spcx = StringUtil.trim(baseCase.getSpcx());
        this.ajly = StringUtil.trim(baseCase.getAjly());
        this.sycx = StringUtil.trim(baseCase.getSycx());
        this.ah = StringUtil.trim(baseCase.getAh());
        this.ajxh = baseCase.getAjxh();
        this.sfgs = StringUtil.trim(baseCase.getSfgs());
        this.sfys = StringUtil.trim(baseCase.getSfys());
        this.sslx = StringUtil.trim(baseCase.getSslx());
        this.slqk = StringUtil.trim(baseCase.getSlqk());
        this.larq = baseCase.getLarq();
        this.sfxess = StringUtil.trim(baseCase.getSfxess());
        this.spcxdz = StringUtil.trim(baseCase.getSpcxdz());
        this.bz=StringUtil.trim(baseCase.getBz());
        this.jayydm = baseCase.getJayydm();
    }




    public CaseContextModel() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getAjxz() {
        return ajxz;
    }

    public void setAjxz(String ajxz) {
        this.ajxz = ajxz;
    }

    public String getSpcx() {
        return spcx;
    }

    public void setSpcx(String spcx) {
        this.spcx = spcx;
    }

    public String getAjly() {
        return ajly;
    }

    public void setAjly(String ajly) {
        this.ajly = ajly;
    }

    public String getSycx() {
        return sycx;
    }

    public void setSycx(String sycx) {
        this.sycx = sycx;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }



    public long getAjxh() {
        return ajxh;
    }

    public void setAjxh(long ajxh) {
        this.ajxh = ajxh;
    }

    public long getProcInsId() {
        return procInsId;
    }

    public void setProcInsId(long procInsId) {
        this.procInsId = procInsId;
    }

    public String getSfgs() {
        return sfgs;
    }

    public void setSfgs(String sfgs) {
        this.sfgs = sfgs;
    }


    public String getSfys() {
        return sfys;
    }

    public void setSfys(String sfys) {
        this.sfys = sfys;
    }

    public String getSfxssc() {
        return sfxssc;
    }

    public void setSfxssc(String sfxssc) {
        this.sfxssc = sfxssc;
    }

    public String getSlqk() {
        if(StringUtil.isBlank(slqk)){
            return "1";
        }
        return slqk;
    }

    public void setSlqk(String slqk) {

        this.slqk = slqk;
    }

    public String getSslx() {
        if(StringUtil.isBlank(sslx)){
            return "4";
        }
        return sslx;
    }

    public void setSslx(String sslx) {
        this.sslx = sslx;
    }

    public boolean isCa() {
        return !StringUtil.isBlank(this.jayydm);
    }

    public String dytj() {
        String ajxz = this.getAjxz();
        String spcx = this.getSpcx();
        String sfys = this.getSfys();
        String sfgs = this.getSfgs();
        String ajly = this.getAjly();
        String spcxdz = this.getSpcxdz();

        if (ajxz.startsWith("0")) {
            ajxz = ajxz.substring(1);
        }
        if (spcx.startsWith("0")) {
            spcx = spcx.substring(1);
        }
        if(sfys!=null && sfys.indexOf('Y')>=0){
            sfys = "Y";
        }else
            sfys = "N";
        if(sfgs!=null && sfgs.indexOf('Y')>=0){
            sfgs = "Y";
        }else
            sfgs = "N";

        if(ajly == null)
            ajly="%";
        if(StringUtil.isEmpty(sycx)){
            sycx="1";
        }

        //再审案件
        if(spcx.equals("3")&&(ajxz.equals("6")||ajxz.equals("1")||ajxz.equals("2"))){
            if(spcxdz!=null&&spcxdz.equals("4")){
                sfgs="4";
            }
        }
        //赔偿行政赔偿再审
        if(spcx.equals("B")&&ajxz.equals("7")&&spcxdz.equals("6")){
            sfgs="6";
        }

        //赔偿行政赔偿申请再审(行赔申)  审限是180
        if(spcx.equals("B")&&ajxz.equals("7")&&spcxdz.equals("4")){
            sfgs="7_B_4";
        }

        //刑事上诉案件和抗诉案件的审限为2个月，不管是否公诉。
        if (StringUtil.equals(ajxz, "1") && StringUtil.equals(spcx, "2")
                && StringUtil.contains("15_1,15_2,15_3", ajly)){
            sfgs = "15_1_2_3";
        }

        //行申字案件，审限是0
        if(StringUtil.equals(ajxz, "6") && StringUtil.equals(spcx, "3") &&
                StringUtil.equals(spcxdz, "2"))
            sfgs = "6_3_2";
        String dytj = ajxz + ";"+spcx+";"+sfgs+";"+sfys+";"+ajly+";"+sycx+";";
        return dytj;
    }

    public String cpxDytj(){
        String ajxz = this.getAjxz();
        String spcx = this.getSpcx();
        String sfys = this.getSfys();
        String sfgs = this.getSfgs();
        String sycx = this.getSycx();
        String slqk = this.getSlqk();
        String eslx = this.getSslx();
        String spcxdz = this.getSpcxdz();
        //ajxz
        if(StringUtil.isEmpty(ajxz)){
            ajxz="?";
        }else{
            if (ajxz.startsWith("0")) {
                ajxz = ajxz.substring(1);//从第二位开始取出所有
            }
            if(ajxz.equals("3")||ajxz.equals("4")||ajxz.equals("5"))
                ajxz = "2";
        }

        //spcx
        if(StringUtil.isEmpty(ajxz)){
            spcx="?";
        }else if (spcx.startsWith("0")) {
            spcx = spcx.substring(1);
        }

        //sfyx
        if(sfys!=null && sfys.indexOf('Y')>=0){
            sfys = "Y";
        }else
            sfys = "N";
        //sfgs
        if(sfgs!=null && sfgs.indexOf('Y')>=0){
            sfgs = "Y";
        }else
            sfgs = "N";

        //sycx
        if(StringUtil.isBlank(sycx)){
            sycx="2";
        }

        //slqk
        if(StringUtil.isBlank(slqk)){
            slqk="1";
        }

        //eslx
        if(StringUtil.isBlank(eslx)){
            eslx="4";
        }
//		赔偿案件 行政案件 一审二审再审其他案件区分
        if(spcx.equals("B")&&ajxz.equals("7")){
            if(spcxdz!=null&&spcxdz.equals("2")){
                sfys = "2";
            }else if(spcxdz!=null&&spcxdz.equals("6")){
                sfys = "6";
            }else if(spcxdz!=null&&!spcxdz.equals("1")){
                sfys = "4";//其他案件
            }
        }
//		行政 审判监督 申请再审审查:使用申诉复查产品线

        if(spcx.equals("3")&&(ajxz.equals("6")||ajxz.equals("1")||ajxz.equals("2"))){
            if(spcxdz!=null&&spcxdz.equals("2")){
                sfys="Y";
            }
        }
        String dytj = ajxz + ";"+spcx+";"+sfys+";"+sfgs+";"+sycx+";"+slqk+";"+eslx;

        return dytj;
    }

    public void setLarq(Date larq) {
        this.larq = larq;
    }

    public Date getLarq() {
        return larq;
    }

    public String getSfxess() {
        return sfxess;
    }

    public void setSfxess(String sfxess) {
        this.sfxess = sfxess;
    }

    public String getSpcxdz() {
        return spcxdz;
    }

    public void setSpcxdz(String spcxdz) {
        this.spcxdz = spcxdz;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getJayydm() {
        return jayydm;
    }

    public void setJayydm(String jayydm) {
        this.jayydm = jayydm;
    }
}
