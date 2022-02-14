package com.nju.sdpt.entity;

import java.util.Date;

public class PubBgscQzEntityWithBLOBs extends PubBgscQzEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUB_BGSC_QZ.FILENR
     *
     * @mbggenerated
     */
    private byte[] filenr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUB_BGSC_QZ.QZFILE
     *
     * @mbggenerated
     */
    private byte[] qzfile;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_BGSC_QZ
     *
     * @mbggenerated
     */
    public PubBgscQzEntityWithBLOBs(Integer ajxh, String dmbh, Integer bh, String bgmc, String fileid, String stampid, String result, String bz, String qzfilename, String source, Date qztime, String sfdrdzjz, String wslxmc, Integer yhbh, String wslx, String ah, String sfwcdy, String ml, Integer dsrbh, byte[] filenr, byte[] qzfile) {
        super(ajxh, dmbh, bh, bgmc, fileid, stampid, result, bz, qzfilename, source, qztime, sfdrdzjz, wslxmc, yhbh, wslx, ah, sfwcdy, ml, dsrbh);
        this.filenr = filenr;
        this.qzfile = qzfile;
    }

    public PubBgscQzEntityWithBLOBs(byte[] filenr, byte[] qzfile) {
        this.filenr = filenr;
        this.qzfile = qzfile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_BGSC_QZ
     *
     * @mbggenerated
     */
    public PubBgscQzEntityWithBLOBs() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUB_BGSC_QZ.FILENR
     *
     * @return the value of PUB_BGSC_QZ.FILENR
     *
     * @mbggenerated
     */
    public byte[] getFilenr() {
        return filenr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUB_BGSC_QZ.FILENR
     *
     * @param filenr the value for PUB_BGSC_QZ.FILENR
     *
     * @mbggenerated
     */
    public void setFilenr(byte[] filenr) {
        this.filenr = filenr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUB_BGSC_QZ.QZFILE
     *
     * @return the value of PUB_BGSC_QZ.QZFILE
     *
     * @mbggenerated
     */
    public byte[] getQzfile() {
        return qzfile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUB_BGSC_QZ.QZFILE
     *
     * @param qzfile the value for PUB_BGSC_QZ.QZFILE
     *
     * @mbggenerated
     */
    public void setQzfile(byte[] qzfile) {
        this.qzfile = qzfile;
    }
}