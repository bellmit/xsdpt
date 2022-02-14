package com.nju.sdpt.model;


import com.nju.sdpt.entity.AjJbEntity;
import com.nju.sdpt.util.DateUtil;
import com.nju.sdpt.util.StringUtil;

import java.util.Date;

/**
 * Created by XXT on 2019/5/24.
 */
public class AjjbxxModel {

    private Integer ajxh;
    private String ah;
    private String ajxz;
    private String ajly;
    private Date larq;
    private String larqStr;
    private String sx;
    private String fhcs;
    private String wdyj;
    private String ajmc;
    private String spdz;
    private String spcx;
    private String sycx;
    private String lar;
    private String spt;
    private String zdaj;
    private String jbaj;
    private String ajsj;
    private String lydd;
    private String ksjg;
    private String cbr;

    //add
    private String bycxdz;
    private String sfzscq;
    private String shwdfxpg;
    private String sfxess;
    private String swlx;
    private String sfys;
    private String ajsfys;
    private String gksjg;

    //add by wu (for ajys)
    private String jayydm;
    private String slqk;
    private String sslx;
    private String sfgs;
    private Date jarq;
    private String jarqStr;

    private Integer fjsx;
    private String ajzt;

    // added by gy
    private String jafs;
    private String modflag;

    //added by wangy
    private Date sxrq;
    private Date gdrq;
    private Date bjrq;
    private String psycy;
    private Date spzsqrq;
    private Date hythyrq;
    private Date tzsqrq;
    private Date yzsqrq;
    private Date swhjdrq;
    private Date yzqfrq;
    private String cbryj;
    private String hytyj;
    private String swhyj;

    //added by wu(提供只读信息，不能进行进行修改)
    private Date cbrbprq_read;
    private String fhcsyy_read;
    private String gpyy_read;
    private String jar_read;

    //added by yyf
    private String lybm;

    private String spcxdz;

    /**
     * add by zcz
     * 无结案文书原因
     */
    private String wjawsyy;

    //判决结果
    private String pjjg;

    private String zdsqmswfsdcp;//刑事二审 针对申请没收违法所得裁判
    private String jzdfdmsss;//刑事二审仅针对附带民事诉讼

    private String sjsx;//涉及事项

    private String sfzcfysx;//司法制裁复议事项
    private String sffcxx;//是否封存信息
    private String klhfksx; //扣留或罚款事项
    private String zdcpsx;//针对裁判事项
    private String syqx;//适用情形


    private String gxyylx;//管辖异议类型

    //added by dyw
    private String bafy;//办案法院

    private Date yssj;//移送日期
    private Date scktsj;//首次开庭时间
    private Date wssqsj;

    private String baspt;

    private int sptg;

    private String sfscwj;


    public AjJbEntity updateAjJbEntity(AjJbEntity ajjb){
        ajjb.setAh(ah);
        ajjb.setAjly(ajly);
        ajjb.setAjmc(ajmc);
        ajjb.setAjwsqk(ajsj);
        ajjb.setAjxh((int)ajxh);
        ajjb.setAjxz(ajxz);
        ajjb.setSffhcs(fhcs);
        ajjb.setSfjbaj(jbaj);
//		ajjb.setGksjg(ksjg);
        ajjb.setLar(lar);
        ajjb.setLarq(larq);
        ajjb.setLydq(StringUtil.trim(lydd));
        ajjb.setSpcx(spcx);

        ajjb.setBaspt(spt);
        ajjb.setSx(Integer.parseInt(sx));
        ajjb.setSycx(sycx);
        ajjb.setSfwdyj(wdyj);
        ajjb.setSfzdaj(zdaj);

        //add
        ajjb.setBycxdz(bycxdz);
        ajjb.setSfzscq(sfzscq);
        ajjb.setShwdfxpg(shwdfxpg);
        ajjb.setSfxess(sfxess);
        ajjb.setSwlx(swlx);
        ajjb.setSfys(sfys);
        ajjb.setAjsfys(ajsfys);
        ajjb.setGksjg(gksjg);

        //add by wu
        ajjb.setJayydm(jayydm);
        ajjb.setSlqk(slqk);
        ajjb.setSslx(sslx);
        ajjb.setSfgs(sfgs);
        ajjb.setJarq(jarq);
        ajjb.setFjsx(fjsx);
        ajjb.setAjzt(ajzt);

        // added by gy
        ajjb.setJafs(jafs);
        ajjb.setModflag(modflag);

        //added by yyf
        ajjb.setLybm(StringUtil.trim(lybm));


        if(sfgs != null)
            ajjb.setSxrq(sxrq);
        ajjb.setCbrbprq(bjrq);
        ajjb.setPsycy(psycy);

        ajjb.setSpzsqrq(spzsqrq);
        ajjb.setHythyrq(hythyrq);
        ajjb.setTzsqrq(tzsqrq);
        ajjb.setYzsqrq(yzsqrq);
        ajjb.setSwhjdrq(swhjdrq);
        ajjb.setYzqfrq(yzqfrq);

        ajjb.setCbryj(cbryj);
        ajjb.setHytyj(hytyj);
        ajjb.setSwhyj(swhyj);

        if(!StringUtil.isBlank(spcxdz)){
            ajjb.setSpcxdz(spcxdz);
        }else if(!StringUtil.isBlank(spdz)){
            ajjb.setSpcxdz(spdz);
        }
        ajjb.setWjawsyy(wjawsyy);
        ajjb.setPjjg(pjjg);
        ajjb.setSptg(sptg);
        ajjb.setBafy(bafy);
        return ajjb;
    }



    public String getJarqStr() {
        return jarqStr;
    }
    public void setJarqStr(String jarqstr) {
        this.jarqStr = jarqstr;
    }
    public Integer getAjxh() {
        return ajxh;
    }
    public void setAjxh(Integer ajxh) {
        this.ajxh = ajxh;
    }
    public String getAh() {
        return ah;
    }
    public void setAh(String ah) {
        this.ah = ah;
    }
    public String getAjxz() {
        return ajxz;
    }
    public void setAjxz(String ajxz) {
        this.ajxz = ajxz;
    }
    public String getAjly() {
        return ajly;
    }
    public void setAjly(String ajly) {
        this.ajly = ajly;
    }
    public Date getLarq() {
        return larq;
    }
    public void setLarq(Date larq) {
        this.larq = larq;
        this.larqStr= DateUtil.format(larq, DateUtil.newFormat);
    }

    public String getSfscwj() {
        return sfscwj;
    }

    public void setSfscwj(String sfscwj) {
        this.sfscwj = sfscwj;
    }

    public String getSx() {
        return sx;
    }
    public void setSx(String sx) {
        this.sx = sx;
    }
    public String getFhcs() {
        return fhcs;
    }
    public void setFhcs(String fhcs) {
        this.fhcs = fhcs;
    }
    public String getWdyj() {
        return wdyj;
    }
    public void setWdyj(String wdyj) {
        this.wdyj = wdyj;
    }
    public String getAjmc() {
        return ajmc;
    }
    public void setAjmc(String ajmc) {
        this.ajmc = ajmc;
    }
    public String getSpdz() {
        return spdz;
    }
    public void setSpdz(String spdz) {
        this.spdz = spdz;
    }
    public String getSpcx() {
        return spcx;
    }
    public void setSpcx(String spcx) {
        this.spcx = spcx;
    }
    public String getSycx() {
        return sycx;
    }
    public void setSycx(String sycx) {
        this.sycx = sycx;
    }
    public String getLar() {
        return lar;
    }
    public void setLar(String lar) {
        this.lar = lar;
    }
    public String getSpt() {
        return spt;
    }
    public void setSpt(String spt) {
        this.spt = spt;
    }
    public String getZdaj() {
        return zdaj;
    }
    public void setZdaj(String zdaj) {
        this.zdaj = zdaj;
    }
    public String getJbaj() {
        return jbaj;
    }
    public void setJbaj(String jbaj) {
        this.jbaj = jbaj;
    }
    public String getAjsj() {
        return ajsj;
    }
    public void setAjsj(String ajsj) {
        this.ajsj = ajsj;
    }
    public String getLydd() {
        return lydd;
    }
    public void setLydd(String lydd) {
        this.lydd = lydd;
    }
    public String getKsjg() {
        return ksjg;
    }
    public void setKsjg(String ksjg) {
        this.ksjg = ksjg;
    }

    public Date getSxrq() {
        return sxrq;
    }
    public Date getGdrq() {
        return gdrq;
    }
    public void setGdrq(Date gdrq) {
        this.gdrq = gdrq;
    }
    public void setSxrq(Date sxrq) {
        this.sxrq = sxrq;
    }
    public void setLarqStr(String larqStr) {
        this.larqStr = larqStr;
    }

    public String getCbr() {
        return cbr;
    }

    public void setCbr(String cbr) {
        this.cbr = cbr;
    }

    public AjjbxxModel(AjJbEntity ajjbDO) {
        super();
        this.ah = ajjbDO.getAh();
        this.ajly = StringUtil.trim(ajjbDO.getAjly());
        this.ajmc = ajjbDO.getAjmc();
        this.ajsj = StringUtil.trim(ajjbDO.getAjwsqk());
        this.ajxh = ajjbDO.getAjxh();
        this.ajxz = StringUtil.trim(ajjbDO.getAjxz());
        this.fhcs = StringUtil.trim(ajjbDO.getSffhcs());
        this.jbaj = StringUtil.trim(ajjbDO.getSfjbaj());
//		this.ksjg = ajjbDO.getGksjg();
        this.lar = ajjbDO.getLar();
        this.setLarq(ajjbDO.getLarq());
        this.lydd = StringUtil.trim(ajjbDO.getLydq());
        this.spcx = StringUtil.trim(ajjbDO.getSpcx());
        this.spdz = StringUtil.trim(ajjbDO.getSpcxdz());
        this.spt = StringUtil.trim(ajjbDO.getBaspt());
        if(ajjbDO.getSx() == null){
            this.sx = "";
        }else{
            this.sx = String.valueOf(ajjbDO.getSx());
        }
        this.sycx = StringUtil.trim(ajjbDO.getSycx());
        this.wdyj = StringUtil.trim(ajjbDO.getSfwdyj());
        this.zdaj = StringUtil.trim(ajjbDO.getSfzdaj());

        //add
        this.bycxdz = StringUtil.trim(ajjbDO.getBycxdz());
        this.sfzscq = StringUtil.trim(ajjbDO.getSfzscq());
        this.shwdfxpg = StringUtil.trim(ajjbDO.getShwdfxpg());
        this.sfxess = StringUtil.trim(ajjbDO.getSfxess());
        this.swlx = StringUtil.trim(ajjbDO.getSwlx());
        this.sfys = StringUtil.trim(ajjbDO.getSfys());
        this.ajsfys = StringUtil.trim(ajjbDO.getAjsfys());
        this.gksjg = StringUtil.trim(ajjbDO.getGksjg());

        this.jayydm = StringUtil.trim(ajjbDO.getJayydm());
        this.slqk = StringUtil.trim(ajjbDO.getSlqk());
        this.sslx = StringUtil.trim(ajjbDO.getSslx());
        this.sfgs = StringUtil.trim(ajjbDO.getSfgs());
        this.larqStr= DateUtil.format(ajjbDO.getLarq(), DateUtil.newFormat);
        this.jarq = ajjbDO.getJarq();
        this.fjsx = ajjbDO.getFjsx();
        this.ajzt = StringUtil.trim(ajjbDO.getAjzt());

        // added by gy
        this.jafs = StringUtil.trim(ajjbDO.getJafs());

        this.sxrq = ajjbDO.getSxrq();
        this.gdrq = ajjbDO.getGdrq();

        this.bjrq = ajjbDO.getCbrbprq();
        this.psycy = ajjbDO.getPsycy();

        this.spzsqrq = ajjbDO.getSpzsqrq();
        this.hythyrq = ajjbDO.getHythyrq();
        this.tzsqrq = ajjbDO.getTzsqrq();
        this.yzsqrq = ajjbDO.getYzsqrq();
        this.swhjdrq = ajjbDO.getSwhjdrq();
        this.yzqfrq = ajjbDO.getYzqfrq();

        this.cbryj = ajjbDO.getCbryj();
        this.hytyj = ajjbDO.getHytyj();
        this.swhyj = ajjbDO.getSwhyj();

        //added by yyf
        this.lybm = StringUtil.trim(ajjbDO.getLybm());

        //add by wu(only read)
        this.cbrbprq_read = ajjbDO.getCbrbprq();
        this.fhcsyy_read = ajjbDO.getFhcsyy();
        this.gpyy_read = ajjbDO.getGpyy();
        this.jar_read = ajjbDO.getJjar();

        this.spcxdz = ajjbDO.getSpcxdz();
        this.wjawsyy = ajjbDO.getWjawsyy();
        this.pjjg = ajjbDO.getPjjg();
        this.sffcxx = ajjbDO.getSffcxx();
        //added by dyw
        this.bafy = ajjbDO.getBafy();
        this.baspt = ajjbDO.getBaspt();
        if(ajjbDO.getSptg() != null)
            this.sptg = ajjbDO.getSptg();
    }

    public String getJar_read() {
        return jar_read;
    }


    public AjjbxxModel() {
        super();
    }

    /**
     * @param bycxdz the bycxdz to set
     */
    public void setBycxdz(String bycxdz) {
        this.bycxdz = bycxdz;
    }
    /**
     * @return the bycxdz
     */
    public String getBycxdz() {
        return bycxdz;
    }
    /**
     * @param sfzscq the sfzscq to set
     */
    public void setSfzscq(String sfzscq) {
        this.sfzscq = sfzscq;
    }
    /**
     * @return the sfzscq
     */
    public String getSfzscq() {
        return sfzscq;
    }
    /**
     * @param shwdfxpg the shwdString to set
     */
    public void setShwdfxpg(String shwdfxpg) {
        this.shwdfxpg = shwdfxpg;
    }
    /**
     * @return the shwdString
     */
    public String getShwdString() {
        return shwdfxpg;
    }
    /**
     * @param sfxess the sfxess to set
     */
    public void setSfxess(String sfxess) {
        this.sfxess = sfxess;
    }
    /**
     * @return the sfxess
     */
    public String getSfxess() {
        return sfxess;
    }
    /**
     * @param swlx the swlx to set
     */
    public void setSwlx(String swlx) {
        this.swlx = swlx;
    }
    /**
     * @return the swlx
     */
    public String getSwlx() {
        return swlx;
    }
    /**
     * @param sfys the sfys to set
     */
    public void setSfys(String sfys) {
        this.sfys = sfys;
    }
    /**
     * @return the sfys
     */
    public String getSfys() {
        return sfys;
    }
    /**
     * @param ajsfys the ansfys to set
     */
    public void setAjsfys(String ajsfys) {
        this.ajsfys = ajsfys;
    }
    /**
     * @return the ansfys
     */
    public String getAjsfys() {
        return ajsfys;
    }
    /**
     * @param gksjg the gksjg to set
     */
    public void setGksjg(String gksjg) {
        this.gksjg = gksjg;
    }
    /**
     * @return the gksjg
     */
    public String getGksjg() {
        return gksjg;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ah == null) ? 0 : ah.hashCode());
        result = prime * result + ((ajly == null) ? 0 : ajly.hashCode());
        result = prime * result + ((ajmc == null) ? 0 : ajmc.hashCode());
        result = prime * result + ((ajsfys == null) ? 0 : ajsfys.hashCode());
        result = prime * result + ((ajsj == null) ? 0 : ajsj.hashCode());
        result = prime * result + (int) (ajxh ^ (ajxh >>> 32));
        result = prime * result + ((ajxz == null) ? 0 : ajxz.hashCode());
        result = prime * result + ((ajzt == null) ? 0 : ajzt.hashCode());
        result = prime * result + ((bjrq == null) ? 0 : bjrq.hashCode());
        result = prime * result + ((bycxdz == null) ? 0 : bycxdz.hashCode());
        result = prime * result
                + ((cbrbprq_read == null) ? 0 : cbrbprq_read.hashCode());
        result = prime * result + ((cbryj == null) ? 0 : cbryj.hashCode());
        result = prime * result + ((fhcs == null) ? 0 : fhcs.hashCode());
        result = prime * result
                + ((fhcsyy_read == null) ? 0 : fhcsyy_read.hashCode());
        result = prime * result + ((fjsx == null) ? 0 : fjsx.hashCode());
        result = prime * result + ((gdrq == null) ? 0 : gdrq.hashCode());
        result = prime * result + ((gksjg == null) ? 0 : gksjg.hashCode());
        result = prime * result
                + ((gpyy_read == null) ? 0 : gpyy_read.hashCode());
        result = prime * result + ((hythyrq == null) ? 0 : hythyrq.hashCode());
        result = prime * result + ((hytyj == null) ? 0 : hytyj.hashCode());
        result = prime * result + ((jafs == null) ? 0 : jafs.hashCode());
        result = prime * result
                + ((jar_read == null) ? 0 : jar_read.hashCode());
        result = prime * result + ((jarq == null) ? 0 : jarq.hashCode());
        result = prime * result + ((jayydm == null) ? 0 : jayydm.hashCode());
        result = prime * result + ((jbaj == null) ? 0 : jbaj.hashCode());
        result = prime * result + ((ksjg == null) ? 0 : ksjg.hashCode());
        result = prime * result + ((lar == null) ? 0 : lar.hashCode());
        result = prime * result + ((larq == null) ? 0 : larq.hashCode());
        result = prime * result + ((larqStr == null) ? 0 : larqStr.hashCode());
        result = prime * result + ((jarqStr == null) ? 0 : jarqStr.hashCode());
        result = prime * result + ((lybm == null) ? 0 : lybm.hashCode());
        result = prime * result + ((lydd == null) ? 0 : lydd.hashCode());
        result = prime * result + ((modflag == null) ? 0 : modflag.hashCode());
        result = prime * result + ((psycy == null) ? 0 : psycy.hashCode());
        result = prime * result + ((sfgs == null) ? 0 : sfgs.hashCode());
        result = prime * result + ((sfxess == null) ? 0 : sfxess.hashCode());
        result = prime * result + ((sfys == null) ? 0 : sfys.hashCode());
        result = prime * result + ((sfzscq == null) ? 0 : sfzscq.hashCode());
        result = prime * result
                + ((shwdfxpg == null) ? 0 : shwdfxpg.hashCode());
        result = prime * result + ((slqk == null) ? 0 : slqk.hashCode());
        result = prime * result + ((spcx == null) ? 0 : spcx.hashCode());
        result = prime * result + ((spdz == null) ? 0 : spdz.hashCode());
        result = prime * result + ((spt == null) ? 0 : spt.hashCode());
        result = prime * result + ((spzsqrq == null) ? 0 : spzsqrq.hashCode());
        result = prime * result + ((sslx == null) ? 0 : sslx.hashCode());
        result = prime * result + ((swhjdrq == null) ? 0 : swhjdrq.hashCode());
        result = prime * result + ((swhyj == null) ? 0 : swhyj.hashCode());
        result = prime * result + ((swlx == null) ? 0 : swlx.hashCode());
        result = prime * result + ((sx == null) ? 0 : sx.hashCode());
        result = prime * result + ((sxrq == null) ? 0 : sxrq.hashCode());
        result = prime * result + ((sycx == null) ? 0 : sycx.hashCode());
        result = prime * result + ((tzsqrq == null) ? 0 : tzsqrq.hashCode());
        result = prime * result + ((wdyj == null) ? 0 : wdyj.hashCode());
        result = prime * result + ((yzqfrq == null) ? 0 : yzqfrq.hashCode());
        result = prime * result + ((yzsqrq == null) ? 0 : yzsqrq.hashCode());
        result = prime * result + ((zdaj == null) ? 0 : zdaj.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AjjbxxModel other = (AjjbxxModel) obj;
        if (ah == null) {
            if (other.ah != null)
                return false;
        } else if (!ah.equals(other.ah))
            return false;
        if (ajly == null) {
            if (other.ajly != null)
                return false;
        } else if (!ajly.equals(other.ajly))
            return false;
        if (ajmc == null) {
            if (other.ajmc != null)
                return false;
        } else if (!ajmc.equals(other.ajmc))
            return false;
        if (ajsfys == null) {
            if (other.ajsfys != null)
                return false;
        } else if (!ajsfys.equals(other.ajsfys))
            return false;
        if (ajsj == null) {
            if (other.ajsj != null)
                return false;
        } else if (!ajsj.equals(other.ajsj))
            return false;
        if (ajxh != other.ajxh)
            return false;
        if (ajxz == null) {
            if (other.ajxz != null)
                return false;
        } else if (!ajxz.equals(other.ajxz))
            return false;
        if (ajzt == null) {
            if (other.ajzt != null)
                return false;
        } else if (!ajzt.equals(other.ajzt))
            return false;
        if (bjrq == null) {
            if (other.bjrq != null)
                return false;
        } else if (!bjrq.equals(other.bjrq))
            return false;
        if (bycxdz == null) {
            if (other.bycxdz != null)
                return false;
        } else if (!bycxdz.equals(other.bycxdz))
            return false;
        if (cbrbprq_read == null) {
            if (other.cbrbprq_read != null)
                return false;
        } else if (!cbrbprq_read.equals(other.cbrbprq_read))
            return false;
        if (cbryj == null) {
            if (other.cbryj != null)
                return false;
        } else if (!cbryj.equals(other.cbryj))
            return false;
        if (fhcs == null) {
            if (other.fhcs != null)
                return false;
        } else if (!fhcs.equals(other.fhcs))
            return false;
        if (fhcsyy_read == null) {
            if (other.fhcsyy_read != null)
                return false;
        } else if (!fhcsyy_read.equals(other.fhcsyy_read))
            return false;
        if (fjsx == null) {
            if (other.fjsx != null)
                return false;
        } else if (!fjsx.equals(other.fjsx))
            return false;
        if (gdrq == null) {
            if (other.gdrq != null)
                return false;
        } else if (!gdrq.equals(other.gdrq))
            return false;
        if (gksjg == null) {
            if (other.gksjg != null)
                return false;
        } else if (!gksjg.equals(other.gksjg))
            return false;
        if (gpyy_read == null) {
            if (other.gpyy_read != null)
                return false;
        } else if (!gpyy_read.equals(other.gpyy_read))
            return false;
        if (hythyrq == null) {
            if (other.hythyrq != null)
                return false;
        } else if (!hythyrq.equals(other.hythyrq))
            return false;
        if (hytyj == null) {
            if (other.hytyj != null)
                return false;
        } else if (!hytyj.equals(other.hytyj))
            return false;
        if (jafs == null) {
            if (other.jafs != null)
                return false;
        } else if (!jafs.equals(other.jafs))
            return false;
        if (jar_read == null) {
            if (other.jar_read != null)
                return false;
        } else if (!jar_read.equals(other.jar_read))
            return false;
        if (jarq == null) {
            if (other.jarq != null)
                return false;
        } else if (!jarq.equals(other.jarq))
            return false;
        if (jayydm == null) {
            if (other.jayydm != null)
                return false;
        } else if (!jayydm.equals(other.jayydm))
            return false;
        if (jbaj == null) {
            if (other.jbaj != null)
                return false;
        } else if (!jbaj.equals(other.jbaj))
            return false;
        if (ksjg == null) {
            if (other.ksjg != null)
                return false;
        } else if (!ksjg.equals(other.ksjg))
            return false;
        if (lar == null) {
            if (other.lar != null)
                return false;
        } else if (!lar.equals(other.lar))
            return false;
        if (larq == null) {
            if (other.larq != null)
                return false;
        } else if (!larq.equals(other.larq))
            return false;
        if (larqStr == null) {
            if (other.larqStr != null)
                return false;
        } else if (!larqStr.equals(other.larqStr))
            return false;
        if (jarqStr == null) {
            if (other.jarqStr != null)
                return false;
        } else if (!jarqStr.equals(other.jarqStr))
            return false;
        if (lybm == null) {
            if (other.lybm != null)
                return false;
        } else if (!lybm.equals(other.lybm))
            return false;
        if (lydd == null) {
            if (other.lydd != null)
                return false;
        } else if (!lydd.equals(other.lydd))
            return false;
        if (modflag == null) {
            if (other.modflag != null)
                return false;
        } else if (!modflag.equals(other.modflag))
            return false;
        if (psycy == null) {
            if (other.psycy != null)
                return false;
        } else if (!psycy.equals(other.psycy))
            return false;
        if (sfgs == null) {
            if (other.sfgs != null)
                return false;
        } else if (!sfgs.equals(other.sfgs))
            return false;
        if (sfxess == null) {
            if (other.sfxess != null)
                return false;
        } else if (!sfxess.equals(other.sfxess))
            return false;
        if (sfys == null) {
            if (other.sfys != null)
                return false;
        } else if (!sfys.equals(other.sfys))
            return false;
        if (sfzscq == null) {
            if (other.sfzscq != null)
                return false;
        } else if (!sfzscq.equals(other.sfzscq))
            return false;
        if (shwdfxpg == null) {
            if (other.shwdfxpg != null)
                return false;
        } else if (!shwdfxpg.equals(other.shwdfxpg))
            return false;
        if (slqk == null) {
            if (other.slqk != null)
                return false;
        } else if (!slqk.equals(other.slqk))
            return false;
        if (spcx == null) {
            if (other.spcx != null)
                return false;
        } else if (!spcx.equals(other.spcx))
            return false;
        if (spdz == null) {
            if (other.spdz != null)
                return false;
        } else if (!spdz.equals(other.spdz))
            return false;
        if (spt == null) {
            if (other.spt != null)
                return false;
        } else if (!spt.equals(other.spt))
            return false;
        if (spzsqrq == null) {
            if (other.spzsqrq != null)
                return false;
        } else if (!spzsqrq.equals(other.spzsqrq))
            return false;
        if (sslx == null) {
            if (other.sslx != null)
                return false;
        } else if (!sslx.equals(other.sslx))
            return false;
        if (swhjdrq == null) {
            if (other.swhjdrq != null)
                return false;
        } else if (!swhjdrq.equals(other.swhjdrq))
            return false;
        if (swhyj == null) {
            if (other.swhyj != null)
                return false;
        } else if (!swhyj.equals(other.swhyj))
            return false;
        if (swlx == null) {
            if (other.swlx != null)
                return false;
        } else if (!swlx.equals(other.swlx))
            return false;
        if (sx == null) {
            if (other.sx != null)
                return false;
        } else if (!sx.equals(other.sx))
            return false;
        if (sxrq == null) {
            if (other.sxrq != null)
                return false;
        } else if (!sxrq.equals(other.sxrq))
            return false;
        if (sycx == null) {
            if (other.sycx != null)
                return false;
        } else if (!sycx.equals(other.sycx))
            return false;
        if (tzsqrq == null) {
            if (other.tzsqrq != null)
                return false;
        } else if (!tzsqrq.equals(other.tzsqrq))
            return false;
        if (wdyj == null) {
            if (other.wdyj != null)
                return false;
        } else if (!wdyj.equals(other.wdyj))
            return false;
        if (yzqfrq == null) {
            if (other.yzqfrq != null)
                return false;
        } else if (!yzqfrq.equals(other.yzqfrq))
            return false;
        if (yzsqrq == null) {
            if (other.yzsqrq != null)
                return false;
        } else if (!yzsqrq.equals(other.yzsqrq))
            return false;
        if (zdaj == null) {
            if (other.zdaj != null)
                return false;
        } else if (!zdaj.equals(other.zdaj))
            return false;
        return true;
    }
    /**
     * @param jayydm the jayydm to set
     */
    public void setJayydm(String jayydm) {
        this.jayydm = jayydm;
    }
    /**
     * @return the jayydm
     */
    public String getJayydm() {
        return jayydm;
    }
    /**
     * @param slqk the slqk to set
     */
    public void setSlqk(String slqk) {
        this.slqk = slqk;
    }
    /**
     * @return the slqk
     */
    public String getSlqk() {
        return slqk;
    }
    /**
     * @param sslx the sslx to set
     */
    public void setSslx(String sslx) {
        this.sslx = sslx;
    }
    /**
     * @return the sslx
     */
    public String getSslx() {
        return sslx;
    }
    public String getSfgs() {
        return sfgs;
    }
    public void setSfgs(String sfgs) {
        this.sfgs = sfgs;
    }
    /**
     * @param jarq the jarq to set
     */
    public void setJarq(Date jarq) {
        this.jarq = jarq;
        this.jarqStr=DateUtil.format(jarq, DateUtil.newFormat);
    }
    /**
     * @return the jarq
     */
    public Date getJarq() {
        return jarq;
    }

    public String getShwdfxpg() {
        return shwdfxpg;
    }
    public Integer getFjsx() {
        return fjsx;
    }
    public void setFjsx(Integer fjsx) {
        this.fjsx = fjsx;
    }
    /**
     * @param ajzt the ajzt to set
     */
    public void setAjzt(String ajzt) {
        this.ajzt = ajzt;
    }
    /**
     * @return the ajzt
     */
    public String getAjzt() {
        return ajzt;
    }
    public String getLarqStr() {
        return larqStr;
    }
    public String getJafs() {
        return jafs;
    }
    public void setJafs(String jafs) {
        this.jafs = jafs;
    }
    public Date getBjrq() {
        return bjrq;
    }
    public void setBjrq(Date bjrq) {
        this.bjrq = bjrq;
    }
    public String getPsycy() {
        return psycy;
    }
    public void setPsycy(String psycy) {
        this.psycy = psycy;
    }
    public Date getSpzsqrq() {
        return spzsqrq;
    }
    public void setSpzsqrq(Date spzsqrq) {
        this.spzsqrq = spzsqrq;
    }
    public Date getHythyrq() {
        return hythyrq;
    }
    public void setHythyrq(Date hythyrq) {
        this.hythyrq = hythyrq;
    }
    public Date getTzsqrq() {
        return tzsqrq;
    }
    public void setTzsqrq(Date tzsqrq) {
        this.tzsqrq = tzsqrq;
    }
    public Date getYssqrq() {
        return yzsqrq;
    }
    public void setYssqrq(Date yssqrq) {
        this.yzsqrq = yssqrq;
    }
    public Date getSwhjdrq() {
        return swhjdrq;
    }
    public void setSwhjdrq(Date swhjdrq) {
        this.swhjdrq = swhjdrq;
    }
    public Date getYzqfrq() {
        return yzqfrq;
    }
    public void setYzqfrq(Date yzqfrq) {
        this.yzqfrq = yzqfrq;
    }
    public String getCbryj() {
        return cbryj;
    }
    public void setCbryj(String cbryj) {
        this.cbryj = cbryj;
    }
    public String getHytyj() {
        return hytyj;
    }
    public void setHytyj(String hytyj) {
        this.hytyj = hytyj;
    }
    public String getSwhyj() {
        return swhyj;
    }
    public void setSwhyj(String swhyj) {
        this.swhyj = swhyj;
    }
    public String getModflag() {
        return modflag;
    }
    public void setModflag(String modflag) {
        this.modflag = modflag;
    }
    public Date getCbrbprq_read() {
        return cbrbprq_read;
    }

    public String getBaspt() {
        return baspt;
    }
    public void setBaspt(String baspt) {
        this.baspt = baspt;
    }

    public Date getYzsqrq() {
        return yzsqrq;
    }
    public void setYzsqrq(Date yzsqrq) {
        this.yzsqrq = yzsqrq;
    }

    public Date getYssj() {
        return yssj;
    }
    public void setYssj(Date yssj) {
        this.yssj = yssj;
    }

    public Date getScktsj() {
        return scktsj;
    }
    public void setScktsj(Date scktsj) {
        this.scktsj = scktsj;
    }

    public Date getWssqsj() {
        return wssqsj;
    }
    public void setWssqsj(Date wssqsj) {
        this.wssqsj = wssqsj;
    }

    public String getFhcsyy_read() {
        return fhcsyy_read;
    }
    public String getGpyy_read() {
        return gpyy_read;
    }
    public String getLybm() {
        return lybm;
    }
    public void setLybm(String lybm) {
        this.lybm = lybm;
    }
    public String getSpcxdz() {
        return spcxdz;
    }
    public void setSpcxdz(String spcxdz) {
        this.spcxdz = spcxdz;
    }
    public String getWjawsyy() {
        return wjawsyy;
    }
    public void setWjawsyy(String wjawsyy) {
        this.wjawsyy = wjawsyy;
    }
    public String getPjjg() {
        return pjjg;
    }
    public void setPjjg(String pjjg) {
        this.pjjg = pjjg;
    }
    public String getZdsqmswfsdcp() {
        return zdsqmswfsdcp;
    }
    public String getJzdfdmsss() {
        return jzdfdmsss;
    }
    public void setZdsqmswfsdcp(String zdsqmswfsdcp) {
        this.zdsqmswfsdcp = zdsqmswfsdcp;
    }
    public void setJzdfdmsss(String jzdfdmsss) {
        this.jzdfdmsss = jzdfdmsss;
    }
    public String getSjsx() {
        return sjsx;
    }
    public void setSjsx(String sjsx) {
        this.sjsx = sjsx;
    }
    public String getSfzcfysx() {
        return sfzcfysx;
    }
    public void setSfzcfysx(String sfzcfysx) {
        this.sfzcfysx = sfzcfysx;
    }
    public String getSffcxx() {
        return sffcxx;
    }
    public void setSffcxx(String sffcxx) {
        this.sffcxx = sffcxx;
    }

    public String getKlhfksx() {
        return klhfksx;
    }

    public void setKlhfksx(String klhfksx) {
        this.klhfksx = klhfksx;
    }

    public String getZdcpsx() {
        return zdcpsx;
    }
    public void setZdcpsx(String zdcpsx) {
        this.zdcpsx = zdcpsx;
    }

    public String getSyqx() {
        return syqx;
    }
    public void setSyqx(String syqx) {
        this.syqx = syqx;
    }
    public String getGxyylx() {
        return gxyylx;
    }

    public void setGxyylx(String gxyylx) {
        this.gxyylx = gxyylx;
    }
    public String getBafy() {
        return bafy;
    }
    public void setBafy(String bafy) {
        this.bafy = bafy;
    }

    public int getSptg() {
        return sptg;
    }

    public void setSptg(int sptg) {
        this.sptg = sptg;
    }
}
