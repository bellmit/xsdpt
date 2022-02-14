package com.nju.sdpt.model;

/**
 * Created by XXT on 2019/5/24.
 */

import com.nju.sdpt.util.DateUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 案件的基本信息模型
 *
 * @author zym
 *
 */
//案件涉及 <--> 案件五涉情况
public class BaseCaseModel implements Serializable{

    private static final long serialVersionUID = -5443697228593879089L;

    /**
     * 案件序号
     */
    private long ajxh;
    /**
     * 案号
     */
    private String ah;
    /**
     * 案件性质
     */
    private String ajxz;
    /**
     * 案件名称
     */
    private String ajmc;
    /**
     * 审判程序
     */
    private String spcx;
    /**
     * 审判程序代字
     */
    private String spcxdz;
    /**
     * 本院程序代字
     */
    private String bycxdz;
    /**
     * 案件来源
     */
    private String ajly;
    /**
     * 立案人
     */
    private String lar;
    /**
     * 立案日期
     */
    private Date larq;
    /**
     * 法院编号
     */
    private long fybh;
    /**
     * 办案审判庭名称
     */
    private String basptmc;
    /**
     * 办案审判庭编号
     */
    private String basptbh;
    /**
     * 办案审判庭
     */
    private String spt;
    /**
     * 适用程序
     */
    private String sycx;
    /**
     * 是否一审
     */
    private String sfys;
    /**
     * 是否发回重审
     */
    private String sffhcs;
    /**
     * 是否外地移交
     */
    private String sfwdyj;
    /**
     * 公抗诉机关
     */
    private String gksjg;
    /**
     * 是否公诉
     */
    private String sfgs;
    /**
     * 上诉类型
     */
    private String sslx;
    /**
     * 来源地区
     */
    private String lydq;
    /**
     * 审限
     */
    private Integer sx;
    /**
     * 是否重大案件
     */
    private String sfzdaj;
    /**
     * 是否交办案件
     */
    private String sfjbaj;
    /**
     * 立案受理费
     */
    private BigDecimal laslf;
    /**
     * 立案案由
     */
    private String laay;
    /**
     * 案件涉及
     */
    private String ajsj;
    /**
     * 涉及国别
     */
    private String sjgb;
    /**
     **立案标的金额
     */
    private BigDecimal labdje;
    /**
     * 立案审判庭
     */
    private String laspt;
    /**
     * 是否知识产权
     */
    private String sfzscq;

    /**
     * 移送日期
     */
    private Date ysrq;
    /**
     * 移送排期日期
     */
    private Date yspqrq;
    /**
     * 结案日期
     */
    private Date jarq;
    /**
     * 结案方式
     */
    private String jafs;

    /**
     * 责任承担人
     */
    private String zrcdr;
    /**
     * 不予受理原因
     */
    private String byslyy;
    /**
     * 驳回起诉原因
     */
    private String bhqsyy;
    /**
     * 按自动撤诉处理原因
     */
    private String azdcsclyy;
    /**
     * 终结诉讼原因
     */
    private String zjssyy;
    /**
     * 结案主案由
     */
    private Integer jazay;
    /**
     * 结案主案由名称
     */
    private String jazaymc;
    /**
     * 结案标的
     */
    private BigDecimal jabd;
    /**
     * 是否串案
     * */
    private String sfca;
    /**
     * 发回重审原因
     */
    private String fhcsyy;
    /**
     * 改判原因
     */
    private String gpyy;
    /**
     * 承办人报批日期
     */
    private Date cbrbprq;
    /**
     * 生效日期
     */
    private Date sxrq;

    /**
     * 是否小额诉讼
     */
    private String sfxess;

    /**
     * 承办人
     */
    private String xm;
    /**
     * 法官助理
     * */
    private String fgzl;
    /**
     * 审判人员
     */
    private String spry;
    /**
     * 办案法院
     */
    private String bafy;
    /**
     * 承办人编号
     */
    private Integer cbrbh;
    private String shwdfxpg;

    // added
    /**
     * 简要案情
     */
    private String jyaq;
    /**
     * 结案原因代码
     */
    private String jayydm;

    private String bz;
    /**
     * 归档标志
     */
    private String gdbz;
    /**
     * 案件状态
     */
    private String ajzt;
    /**
     * 附加审限
     */
    private Integer fjsx;
    /**
     * 实际审限
     */
    private Integer sjsx;
    private String slqk;
    /**
     * 剩余审理天数
     */
    private Long syslts;
    /**
     * 书记员
     */
    private String sjy;
    /**
     * 结案人
     */
    private String jar;
    /**
     * 结案案由
     */
    private String jaay;
    /**
     * 是否当庭宣判
     */
    private String sfdtxp;
    /**
     * 电子档案审核状态
     */
    private String shzt;

    //归档日期
    private Date gdrq;
    private String gdrqString;
    // added by typ for GJ-xsspjdjaxx
    String cbryj;
    String hytyj;
    String tzhyj;
    String yzhyj;
    String swhyj;
    String jayy;
    Date spzsqrq;
    Date hythyrq;
    Date tzsqrq;
    Date yzsqrq;
    Date swhjdrq;
    Date yzqfrq;
    String psycy;
    String sffcxx;
    String sfcqyscs;

    //是否速裁
    private String sfsc;
    //案件是否移送
    private String ajsfys;

    //	结案退回原因
    private String jathyy;

    //审判长
    private String spz;

    //专门用于铁路法院的管辖类型，用于案件基本已无其他字段可添。
    private int sptg;

    //专门审判代字
    private String zmspdz;

//    /**
//     * @author typ
//     * @param vo
//     * @return
//     */
//    public static BaseCaseModel getBaseCaseModel(XsspjdjaxxVO vo) {
//        if (vo == null) {
//            return new BaseCaseModel();
//        }
//        BaseCaseModel baseCaseModel = new BaseCaseModel();
//        baseCaseModel.setAjxh(vo.getAjxh());
//        baseCaseModel.setAjxz(vo.getAjxz());
//        baseCaseModel.setCbryj(vo.getCbryj());
//        baseCaseModel.setHytyj(vo.getHytyj());
//        baseCaseModel.setTzhyj(vo.getTzhyj());
//        baseCaseModel.setYzhyj(vo.getYzhyj());
//        baseCaseModel.setSwhyj(vo.getSwhyj());
//        baseCaseModel.setJarq(DateUtil.parse(vo.getJarq(), DateUtil.webFormat));
//        baseCaseModel.setJayy(vo.getJayy());
//        baseCaseModel.setJayydm(vo.getJayydm());
//        baseCaseModel.setBz(vo.getBz());
//        baseCaseModel.setSxrq(DateUtil.parse(vo.getSxrq(), DateUtil.webFormat));
//        baseCaseModel.setJyaq(vo.getJyaq());
//        baseCaseModel.setJafs(vo.getJafs());
//        baseCaseModel.setCbrbprq(DateUtil.parse(vo.getCbrbprq(),
//                DateUtil.webFormat));
//        baseCaseModel.setSpzsqrq(DateUtil.parse(vo.getSpzsqrq(),
//                DateUtil.webFormat));
//        baseCaseModel.setHythyrq(DateUtil.parse(vo.getHythyrq(),
//                DateUtil.webFormat));
//        baseCaseModel.setTzsqrq(DateUtil.parse(vo.getTzsqrq(),
//                DateUtil.webFormat));
//        baseCaseModel.setYzsqrq(DateUtil.parse(vo.getYzsqrq(),
//                DateUtil.webFormat));
//        baseCaseModel.setSwhjdrq(DateUtil.parse(vo.getSwhjdrq(),
//                DateUtil.webFormat));
//        baseCaseModel.setYzqfrq(DateUtil.parse(vo.getYzqfrq(),
//                DateUtil.webFormat));
//        baseCaseModel.setSycx(vo.getSycx());
//        baseCaseModel.setPsycy(vo.getPsycy());
//        baseCaseModel.setShwdfxpg(vo.getShwdfxpg());
//        baseCaseModel.setSffcxx(vo.getSffcxx());
//        baseCaseModel.setSfcqyscs(vo.getSfcqyscs());
//        return baseCaseModel;
//    }

    public Date getGdrq() {
        return gdrq;
    }

    public void setGdrq(Date gdrq) {
        this.gdrq = gdrq;
        this.gdrqString= DateUtil.format(gdrq, DateUtil.webFormat);
    }

    public String getSfxess() {
        return sfxess;
    }

    public void setSfxess(String sfxess) {
        this.sfxess = sfxess;
    }


    public BigDecimal getJabd() {
        return jabd;
    }

    public void setJabd(BigDecimal jabd) {
        this.jabd = jabd;
    }

    public Date getSxrq() {
        return sxrq;
    }

    public void setSxrq(Date sxrq) {
        this.sxrq = sxrq;
    }

    public Date getCbrbprq() {
        return cbrbprq;
    }

    public void setCbrbprq(Date cbrbprq) {
        this.cbrbprq = cbrbprq;
    }

    public Integer getJazay() {
        return jazay;
    }

    public void setJazay(Integer jazay) {
        this.jazay = jazay;
    }

    public String getJazaymc() {
        return jazaymc;
    }

    public void setJazaymc(String jazaymc) {
        this.jazaymc = jazaymc;
    }

    public BigDecimal getLaslf() {
        return laslf;
    }

    public void setLaslf(BigDecimal laslf) {
        this.laslf = laslf;
    }

    public String getLaay() {
        return laay;
    }

    public void setLaay(String laay) {
        this.laay = laay;
    }

    public String getAjsj() {
        return ajsj;
    }

    public void setAjsj(String ajsj) {
        this.ajsj = ajsj;
    }

    public String getSjgb() {
        return sjgb;
    }

    public void setSjgb(String sjgb) {
        this.sjgb = sjgb;
    }

    public BigDecimal getLabdje() {
        return labdje;
    }

    public void setLabdje(BigDecimal labdje) {
        this.labdje = labdje;
    }




    public Date getJarq() {
        return jarq;
    }

    public void setJarq(Date jarq) {
        this.jarq = jarq;
    }

    public String getJafs() {
        return jafs;
    }

    public void setJafs(String jafs) {
        this.jafs = jafs;
    }

    public String getSfca() {
        return sfca;
    }

    public void setSfca(String sfca) {
        this.sfca = sfca;
    }

    public String getFgzl() {
        return fgzl;
    }

    public void setFgzl(String fgzl) {
        this.fgzl = fgzl;
    }

    public BaseCaseModel() {
        super();
    }


    public String getBasptbh() {
        return basptbh;
    }

    public void setBasptbh(String basptbh) {
        this.basptbh = basptbh;
    }

    public BaseCaseModel(long ajxh, String ah, String ajxz, String spcx,
                         String spcxdz, String bycxdz, String ajly, String lar, Date larq,
                         long fybh, String sycx, String sfys, String sffhcs, String sfwdyj,
                         String gksjg, String sfgs, String sslx, String lydq, Integer sx, Integer fjsx,
                         String sfzdaj, String sfjbaj, BigDecimal laslf, String laay,
                         Date sxrq, BigDecimal jabd, String basptbh,
                         String ajsj, String sjgb, BigDecimal labdje, String laspt,
                         String sfzscq, String zrcdr, String byslyy, String bhqsyy,
                         String azdcsclyy, String zjssyy, Date jarq, String jafs, Date cbrbprq, String ajmc, String basptmc, String slqk) {

        super();
        this.ajxh = ajxh;
        this.ah = ah;
        this.ajxz = ajxz;
        this.spcx = spcx;
        this.spcxdz = spcxdz;
        this.bycxdz = bycxdz;
        this.ajly = ajly;
        this.lar = lar;
        this.larq = larq;
        this.fybh = fybh;
        this.sycx = sycx;
        this.sfys = sfys;
        this.sffhcs = sffhcs;
        this.sfwdyj = sfwdyj;
        this.gksjg = gksjg;
        this.sfgs = sfgs;
        this.sslx = sslx;
        this.lydq = lydq;
        this.sx = sx;
        this.fjsx = fjsx;
        this.sfzdaj = sfzdaj;
        this.sfjbaj = sfjbaj;
        this.laslf = laslf;
        this.laay = laay;
        this.ajsj = ajsj;
        this.sjgb = sjgb;
        this.labdje = labdje;
        this.laspt = laspt;
        this.sfzscq = sfzscq;

        this.jarq = jarq;
        this.jafs = jafs;

        this.zrcdr = zrcdr;
        this.byslyy = byslyy;
        this.bhqsyy = bhqsyy;
        this.azdcsclyy = azdcsclyy;
        this.zjssyy = zjssyy;
        this.cbrbprq = cbrbprq;
        this.ajmc = ajmc;
        this.basptmc = basptmc;
        this.basptbh = basptbh;

        this.jabd = jabd;
        this.sxrq = sxrq;
        this.slqk = slqk;
    }

    public long getAjxh() {
        return ajxh;
    }

    public void setAjxh(long ajxh) {
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

    public String getSpcx() {
        return spcx;
    }

    public void setSpcx(String spcx) {
        this.spcx = spcx;
    }

    public String getSpcxdz() {
        return spcxdz;
    }

    public void setSpcxdz(String spcxdz) {
        this.spcxdz = spcxdz;
    }

    public String getBycxdz() {
        return bycxdz;
    }

    public void setBycxdz(String bycxdz) {
        this.bycxdz = bycxdz;
    }

    public String getAjly() {
        return ajly;
    }

    public void setAjly(String ajly) {
        this.ajly = ajly;
    }

    public String getLar() {
        return lar;
    }

    public void setLar(String lar) {
        this.lar = lar;
    }

    public Date getLarq() {
        return larq;
    }

    public void setLarq(Date larq) {
        this.larq = larq;
    }

    public long getFybh() {
        return fybh;
    }

    public void setFybh(long fybh) {
        this.fybh = fybh;
    }

    public String getSycx() {
        return sycx;
    }

    public void setSycx(String sycx) {
        this.sycx = sycx;
    }

    public String getSfys() {
        return sfys;
    }

    public void setSfys(String sfys) {
        this.sfys = sfys;
    }

    public String getSfgs() {
        return sfgs;
    }

    public void setSfgs(String sfgs) {
        this.sfgs = sfgs;
    }

    public String getLydq() {
        return lydq;
    }

    public void setLydq(String lydq) {
        this.lydq = lydq;
    }

    public Integer getSx() {
        return sx;
    }

    public void setSx(Integer sx) {
        this.sx = sx;
    }

    public String getSfzdaj() {
        return sfzdaj;
    }

    public void setSfzdaj(String sfzdaj) {
        this.sfzdaj = sfzdaj;
    }

    public String getSfjbaj() {
        return sfjbaj;
    }

    public void setSfjbaj(String sfjbaj) {
        this.sfjbaj = sfjbaj;
    }

    public String getSffhcs() {
        return sffhcs;
    }

    public void setSffhcs(String sffhcs) {
        this.sffhcs = sffhcs;
    }

    public String getSfwdyj() {
        return sfwdyj;
    }

    public void setSfwdyj(String sfwdyj) {
        this.sfwdyj = sfwdyj;
    }

    public String getGksjg() {
        return gksjg;
    }

    public void setGksjg(String gksjg) {
        this.gksjg = gksjg;
    }

    public String getSslx() {
        return sslx;
    }

    public void setSslx(String sslx) {
        this.sslx = sslx;
    }



    public String getSfzscq() {
        return sfzscq;
    }

    public void setSfzscq(String sfzscq) {
        this.sfzscq = sfzscq;
    }

    public String getLaspt() {
        return laspt;
    }

    public void setLaspt(String laspt) {
        this.laspt = laspt;
    }

    public String getZrcdr() {
        return zrcdr;
    }

    public void setZrcdr(String zrcdr) {
        this.zrcdr = zrcdr;
    }

    public String getByslyy() {
        return byslyy;
    }

    public void setByslyy(String byslyy) {
        this.byslyy = byslyy;
    }

    public String getBhqsyy() {
        return bhqsyy;
    }

    public void setBhqsyy(String bhqsyy) {
        this.bhqsyy = bhqsyy;
    }

    public String getAzdcsclyy() {
        return azdcsclyy;
    }

    public void setAzdcsclyy(String azdcsclyy) {
        this.azdcsclyy = azdcsclyy;
    }

    public String getZjssyy() {
        return zjssyy;
    }

    public void setZjssyy(String zjssyy) {
        this.zjssyy = zjssyy;
    }

    public String getFhcsyy() {
        return fhcsyy;
    }

    public void setFhcsyy(String fhcsyy) {
        this.fhcsyy = fhcsyy;
    }

    public String getGpyy() {
        return gpyy;
    }

    public void setGpyy(String gpyy) {
        this.gpyy = gpyy;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getAjmc() {
        return ajmc;
    }

    public void setAjmc(String ajmc) {
        this.ajmc = ajmc;
    }

    public String getBasptmc() {
        return basptmc;
    }

    public void setBasptmc(String basptmc) {
        this.basptmc = basptmc;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getBafy() {
        return bafy;
    }

    public void setBafy(String bafy) {
        this.bafy = bafy;
    }

    public String getShwdfxpg() {
        return shwdfxpg;
    }

    public void setShwdfxpg(String shwdfxpg) {
        this.shwdfxpg = shwdfxpg;
    }



    public String getJyaq() {
        return jyaq;
    }

    public void setJyaq(String jyaq) {
        this.jyaq = jyaq;
    }

    public String getJayydm() {
        return jayydm;
    }

    public void setJayydm(String jayydm) {
        this.jayydm = jayydm;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getGdbz() {
        return gdbz;
    }

    public void setGdbz(String gdbz) {
        this.gdbz = gdbz;
    }

    public String getAjzt() {
        return ajzt;
    }

    public void setAjzt(String ajzt) {
        this.ajzt = ajzt;
    }

    public String getSlqk() {
        return slqk;
    }

    public void setSlqk(String slqk) {
        this.slqk = slqk;
    }
    public Integer getFjsx() {
        return fjsx;
    }

    public void setFjsx(Integer fjsx) {
        this.fjsx = fjsx;
    }


    public Integer getSjsx() {
        return sjsx;
    }

    public void setSjsx(Integer sjsx) {
        this.sjsx = sjsx;
    }


//    public String toString() {
//        return ToStringBuilder.reflectionToString(this);
//    }

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

    public String getTzhyj() {
        return tzhyj;
    }

    public void setTzhyj(String tzhyj) {
        this.tzhyj = tzhyj;
    }

    public String getYzhyj() {
        return yzhyj;
    }

    public void setYzhyj(String yzhyj) {
        this.yzhyj = yzhyj;
    }

    public String getSwhyj() {
        return swhyj;
    }

    public void setSwhyj(String swhyj) {
        this.swhyj = swhyj;
    }

    public String getJayy() {
        return jayy;
    }

    public void setJayy(String jayy) {
        this.jayy = jayy;
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

    public Date getYzsqrq() {
        return yzsqrq;
    }

    public void setYzsqrq(Date yzsqrq) {
        this.yzsqrq = yzsqrq;
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

    public String getPsycy() {
        return psycy;
    }

    public void setPsycy(String psycy) {
        this.psycy = psycy;
    }

    public String getSffcxx() {
        return sffcxx;
    }

    public void setSffcxx(String sffcxx) {
        this.sffcxx = sffcxx;
    }

    public String getSfcqyscs() {
        return sfcqyscs;
    }

    public void setSfcqyscs(String sfcqyscs) {
        this.sfcqyscs = sfcqyscs;
    }

    public String getSjy() {
        return sjy;
    }

    public void setSjy(String sjy) {
        this.sjy = sjy;
    }

    public String getSpry() {
        return spry;
    }

    public void setSpry(String spry) {
        this.spry = spry;
    }

    public Integer getCbrbh() {
        return cbrbh;
    }

    public void setCbrbh(Integer cbrbh) {
        this.cbrbh = cbrbh;
    }

    public String getJaay() {
        return jaay;
    }

    public void setJaay(String jaay) {
        this.jaay = jaay;
    }

    public String getSfdtxp() {
        return sfdtxp;
    }

    public void setSfdtxp(String sfdtxp) {
        this.sfdtxp = sfdtxp;
    }

    public String getShzt() {
        return shzt;
    }

    public void setShzt(String shzt) {
        this.shzt = shzt;
    }

    public Date getYsrq() {
        return ysrq;
    }

    public void setYsrq(Date ysrq) {
        this.ysrq = ysrq;
    }

    public Date getYspqrq() {
        return yspqrq;
    }

    public void setYspqrq(Date yspqrq) {
        this.yspqrq = yspqrq;
    }

    public String getSpt() {
        return spt;
    }

    public void setSpt(String spt) {
        this.spt = spt;
    }

    public String getGdrqString() {
        return gdrqString;
    }

    public String getJar() {
        return jar;
    }

    public void setGdrqString(String gdrqString) {
        this.gdrqString = gdrqString;
    }


    public void setJar(String jar) {
        this.jar = jar;
    }

    public Long getSyslts() {
        return syslts;
    }

    public void setSyslts(Long syslts) {
        this.syslts = syslts;
    }

    public String getSfsc() {
        return sfsc;
    }

    public void setSfsc(String sfsc) {
        this.sfsc = sfsc;
    }

    public void setAjsfys(String ajsfys) {
        this.ajsfys = ajsfys;
    }

    public String getAjsfys() {
        return ajsfys;
    }

    public String getJathyy() {
        return jathyy;
    }

    public void setJathyy(String jathyy) {
        this.jathyy = jathyy;
    }

    public String getSpz() {
        return spz;
    }

    public void setSpz(String spz) {
        this.spz = spz;
    }

    public int getSptg() {
        return sptg;
    }

    public void setSptg(int sptg) {
        this.sptg = sptg;
    }

    public String getZmspdz() { return zmspdz; }

    public void setZmspdz(String zmspdz) { this.zmspdz = zmspdz; }
}
