package com.nju.sdpt.entity;

import java.util.Date;

public class PubBgscQzEntity extends PubBgscQzKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUB_BGSC_QZ.BGMC
     *
     * @mbggenerated
     */
    private String bgmc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUB_BGSC_QZ.FILEID
     *
     * @mbggenerated
     */
    private String fileid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUB_BGSC_QZ.STAMPID
     *
     * @mbggenerated
     */
    private String stampid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUB_BGSC_QZ.RESULT
     *
     * @mbggenerated
     */
    private String result;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUB_BGSC_QZ.BZ
     *
     * @mbggenerated
     */
    private String bz;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUB_BGSC_QZ.QZFILENAME
     *
     * @mbggenerated
     */
    private String qzfilename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUB_BGSC_QZ.SOURCE
     *
     * @mbggenerated
     */
    private String source;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUB_BGSC_QZ.QZTIME
     *
     * @mbggenerated
     */
    private Date qztime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUB_BGSC_QZ.SFDRDZJZ
     *
     * @mbggenerated
     */
    private String sfdrdzjz;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUB_BGSC_QZ.WSLXMC
     *
     * @mbggenerated
     */
    private String wslxmc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUB_BGSC_QZ.YHBH
     *
     * @mbggenerated
     */
    private Integer yhbh;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUB_BGSC_QZ.WSLX
     *
     * @mbggenerated
     */
    private String wslx;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUB_BGSC_QZ.AH
     *
     * @mbggenerated
     */
    private String ah;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUB_BGSC_QZ.SFWCDY
     *
     * @mbggenerated
     */
    private String sfwcdy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUB_BGSC_QZ.ML
     *
     * @mbggenerated
     */
    private String ml;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUB_BGSC_QZ.DSRBH
     *
     * @mbggenerated
     */
    private Integer dsrbh;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_BGSC_QZ
     *
     * @mbggenerated
     */
    public PubBgscQzEntity(Integer ajxh, String dmbh, Integer bh, String bgmc, String fileid, String stampid, String result, String bz, String qzfilename, String source, Date qztime, String sfdrdzjz, String wslxmc, Integer yhbh, String wslx, String ah, String sfwcdy, String ml, Integer dsrbh) {
        super(ajxh, dmbh, bh);
        this.bgmc = bgmc;
        this.fileid = fileid;
        this.stampid = stampid;
        this.result = result;
        this.bz = bz;
        this.qzfilename = qzfilename;
        this.source = source;
        this.qztime = qztime;
        this.sfdrdzjz = sfdrdzjz;
        this.wslxmc = wslxmc;
        this.yhbh = yhbh;
        this.wslx = wslx;
        this.ah = ah;
        this.sfwcdy = sfwcdy;
        this.ml = ml;
        this.dsrbh = dsrbh;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_BGSC_QZ
     *
     * @mbggenerated
     */
    public PubBgscQzEntity() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUB_BGSC_QZ.BGMC
     *
     * @return the value of PUB_BGSC_QZ.BGMC
     *
     * @mbggenerated
     */
    public String getBgmc() {
        return bgmc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUB_BGSC_QZ.BGMC
     *
     * @param bgmc the value for PUB_BGSC_QZ.BGMC
     *
     * @mbggenerated
     */
    public void setBgmc(String bgmc) {
        this.bgmc = bgmc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUB_BGSC_QZ.FILEID
     *
     * @return the value of PUB_BGSC_QZ.FILEID
     *
     * @mbggenerated
     */
    public String getFileid() {
        return fileid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUB_BGSC_QZ.FILEID
     *
     * @param fileid the value for PUB_BGSC_QZ.FILEID
     *
     * @mbggenerated
     */
    public void setFileid(String fileid) {
        this.fileid = fileid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUB_BGSC_QZ.STAMPID
     *
     * @return the value of PUB_BGSC_QZ.STAMPID
     *
     * @mbggenerated
     */
    public String getStampid() {
        return stampid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUB_BGSC_QZ.STAMPID
     *
     * @param stampid the value for PUB_BGSC_QZ.STAMPID
     *
     * @mbggenerated
     */
    public void setStampid(String stampid) {
        this.stampid = stampid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUB_BGSC_QZ.RESULT
     *
     * @return the value of PUB_BGSC_QZ.RESULT
     *
     * @mbggenerated
     */
    public String getResult() {
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUB_BGSC_QZ.RESULT
     *
     * @param result the value for PUB_BGSC_QZ.RESULT
     *
     * @mbggenerated
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUB_BGSC_QZ.BZ
     *
     * @return the value of PUB_BGSC_QZ.BZ
     *
     * @mbggenerated
     */
    public String getBz() {
        return bz;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUB_BGSC_QZ.BZ
     *
     * @param bz the value for PUB_BGSC_QZ.BZ
     *
     * @mbggenerated
     */
    public void setBz(String bz) {
        this.bz = bz;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUB_BGSC_QZ.QZFILENAME
     *
     * @return the value of PUB_BGSC_QZ.QZFILENAME
     *
     * @mbggenerated
     */
    public String getQzfilename() {
        return qzfilename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUB_BGSC_QZ.QZFILENAME
     *
     * @param qzfilename the value for PUB_BGSC_QZ.QZFILENAME
     *
     * @mbggenerated
     */
    public void setQzfilename(String qzfilename) {
        this.qzfilename = qzfilename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUB_BGSC_QZ.SOURCE
     *
     * @return the value of PUB_BGSC_QZ.SOURCE
     *
     * @mbggenerated
     */
    public String getSource() {
        return source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUB_BGSC_QZ.SOURCE
     *
     * @param source the value for PUB_BGSC_QZ.SOURCE
     *
     * @mbggenerated
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUB_BGSC_QZ.QZTIME
     *
     * @return the value of PUB_BGSC_QZ.QZTIME
     *
     * @mbggenerated
     */
    public Date getQztime() {
        return qztime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUB_BGSC_QZ.QZTIME
     *
     * @param qztime the value for PUB_BGSC_QZ.QZTIME
     *
     * @mbggenerated
     */
    public void setQztime(Date qztime) {
        this.qztime = qztime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUB_BGSC_QZ.SFDRDZJZ
     *
     * @return the value of PUB_BGSC_QZ.SFDRDZJZ
     *
     * @mbggenerated
     */
    public String getSfdrdzjz() {
        return sfdrdzjz;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUB_BGSC_QZ.SFDRDZJZ
     *
     * @param sfdrdzjz the value for PUB_BGSC_QZ.SFDRDZJZ
     *
     * @mbggenerated
     */
    public void setSfdrdzjz(String sfdrdzjz) {
        this.sfdrdzjz = sfdrdzjz;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUB_BGSC_QZ.WSLXMC
     *
     * @return the value of PUB_BGSC_QZ.WSLXMC
     *
     * @mbggenerated
     */
    public String getWslxmc() {
        return wslxmc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUB_BGSC_QZ.WSLXMC
     *
     * @param wslxmc the value for PUB_BGSC_QZ.WSLXMC
     *
     * @mbggenerated
     */
    public void setWslxmc(String wslxmc) {
        this.wslxmc = wslxmc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUB_BGSC_QZ.YHBH
     *
     * @return the value of PUB_BGSC_QZ.YHBH
     *
     * @mbggenerated
     */
    public Integer getYhbh() {
        return yhbh;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUB_BGSC_QZ.YHBH
     *
     * @param yhbh the value for PUB_BGSC_QZ.YHBH
     *
     * @mbggenerated
     */
    public void setYhbh(Integer yhbh) {
        this.yhbh = yhbh;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUB_BGSC_QZ.WSLX
     *
     * @return the value of PUB_BGSC_QZ.WSLX
     *
     * @mbggenerated
     */
    public String getWslx() {
        return wslx;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUB_BGSC_QZ.WSLX
     *
     * @param wslx the value for PUB_BGSC_QZ.WSLX
     *
     * @mbggenerated
     */
    public void setWslx(String wslx) {
        this.wslx = wslx;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUB_BGSC_QZ.AH
     *
     * @return the value of PUB_BGSC_QZ.AH
     *
     * @mbggenerated
     */
    public String getAh() {
        return ah;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUB_BGSC_QZ.AH
     *
     * @param ah the value for PUB_BGSC_QZ.AH
     *
     * @mbggenerated
     */
    public void setAh(String ah) {
        this.ah = ah;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUB_BGSC_QZ.SFWCDY
     *
     * @return the value of PUB_BGSC_QZ.SFWCDY
     *
     * @mbggenerated
     */
    public String getSfwcdy() {
        return sfwcdy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUB_BGSC_QZ.SFWCDY
     *
     * @param sfwcdy the value for PUB_BGSC_QZ.SFWCDY
     *
     * @mbggenerated
     */
    public void setSfwcdy(String sfwcdy) {
        this.sfwcdy = sfwcdy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUB_BGSC_QZ.ML
     *
     * @return the value of PUB_BGSC_QZ.ML
     *
     * @mbggenerated
     */
    public String getMl() {
        return ml;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUB_BGSC_QZ.ML
     *
     * @param ml the value for PUB_BGSC_QZ.ML
     *
     * @mbggenerated
     */
    public void setMl(String ml) {
        this.ml = ml;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUB_BGSC_QZ.DSRBH
     *
     * @return the value of PUB_BGSC_QZ.DSRBH
     *
     * @mbggenerated
     */
    public Integer getDsrbh() {
        return dsrbh;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUB_BGSC_QZ.DSRBH
     *
     * @param dsrbh the value for PUB_BGSC_QZ.DSRBH
     *
     * @mbggenerated
     */
    public void setDsrbh(Integer dsrbh) {
        this.dsrbh = dsrbh;
    }
}