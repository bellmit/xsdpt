package com.nju.sdpt.entity;

import java.util.Date;

/**
 * DzjzWdWjJzDO entity. @author MyEclipse Persistence Tools
 */
public class DzjzWdWjDO implements java.io.Serializable {

	// Fields

	private String wjid;
	private String wjssml;
	private Integer ajxh;
	private String ah;
	private String wjmc;
	private String wjlx;
	private Integer wjsx;
	private Integer wjzt;
	private String wjlj;
	private Integer sfgd;
	private String sfkfy;
	private String cjr;
	private Date cjsj;
	private String shr;
	private Date shsj;
	private String shyj;
	private String scr;
	private Date scsj;
	private String scyy;
	private String ladjh;
	private String djxh;

	// Constructors

	/** default constructor */
	public DzjzWdWjDO() {
	}

	/** full constructor */
	public DzjzWdWjDO(String wjid,String wjssml, Integer ajxh, String ah, String wjmc,
			String wjlx, Integer wjsx, Integer wjzt, String wjlj, Integer sfgd,
			String sfkfy, String cjr, Date cjsj, String shr, Date shsj,
			String shyj, String scr, Date scsj, String scyy,String ladjh,String djxh) {
		this.wjid = wjid;
		this.wjssml = wjssml;
		this.ajxh = ajxh;
		this.ah = ah;
		this.wjmc = wjmc;
		this.wjlx = wjlx;
		this.wjsx = wjsx;
		this.wjzt = wjzt;
		this.wjlj = wjlj;
		this.sfgd = sfgd;
		this.sfkfy = sfkfy;
		this.cjr = cjr;
		this.cjsj = cjsj;
		this.shr = shr;
		this.shsj = shsj;
		this.shyj = shyj;
		this.scr = scr;
		this.scsj = scsj;
		this.scyy = scyy;
		this.ladjh = ladjh;
		this.djxh = djxh;
	}

//	// Property accessors
//	@Id
//	@Column(name = "WJID", unique = true, length = 40)
	public String getWjid() {
		return this.wjid;
	}

	public void setWjid(String wjid) {
		this.wjid = wjid;
	}

//	@Column(name = "WJSSML", length = 100)
	public String getWjssml() {
		return this.wjssml;
	}

	public void setWjssml(String wjssml) {
		this.wjssml = wjssml;
	}

//	@Column(name = "AJXH")
	public Integer getAjxh() {
		return this.ajxh;
	}

	public void setAjxh(Integer ajxh) {
		this.ajxh = ajxh;
	}

//	@Column(name = "AH")
	public String getAh() {
		return this.ah;
	}

	public void setAh(String ah) {
		this.ah = ah;
	}

//	@Column(name = "WJMC")
	public String getWjmc() {
		return this.wjmc;
	}

	public void setWjmc(String wjmc) {
		this.wjmc = wjmc;
	}

//	@Column(name = "WJLX")
	public String getWjlx() {
		return this.wjlx;
	}

	public void setWjlx(String wjlx) {
		this.wjlx = wjlx;
	}

//	@Column(name = "WJSX")
	public Integer getWjsx() {
		return this.wjsx;
	}

	public void setWjsx(Integer wjsx) {
		this.wjsx = wjsx;
	}

//	@Column(name = "WJZT")
	public Integer getWjzt() {
		return this.wjzt;
	}

	public void setWjzt(Integer wjzt) {
		this.wjzt = wjzt;
	}

//	@Column(name = "WJLJ", length = 200)
	public String getWjlj() {
		return this.wjlj;
	}

	public void setWjlj(String wjlj) {
		this.wjlj = wjlj;
	}

//	@Column(name = "SFGD")
	public Integer getSfgd() {
		return this.sfgd;
	}

	public void setSfgd(Integer sfgd) {
		this.sfgd = sfgd;
	}

//	@Column(name = "SFKFY",  length = 10)
	public String getSfkfy() {
		return this.sfkfy;
	}

	public void setSfkfy(String sfkfy) {
		this.sfkfy = sfkfy;
	}

//	@Column(name = "CJR")
	public String getCjr() {
		return this.cjr;
	}

	public void setCjr(String cjr) {
		this.cjr = cjr;
	}

//	@Column(name = "CJSJ")
	public Date getCjsj() {
		return this.cjsj;
	}

	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}

//	@Column(name = "SHR",  length = 20)
	public String getShr() {
		return this.shr;
	}

	public void setShr(String shr) {
		this.shr = shr;
	}

//	@Column(name = "SHSJ",  length = 23)
	public Date getShsj() {
		return this.shsj;
	}

	public void setShsj(Date shsj) {
		this.shsj = shsj;
	}

//	@Column(name = "SHYJ",  length = 100)
	public String getShyj() {
		return this.shyj;
	}

	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

//	@Column(name = "SCR", length = 20)
	public String getScr() {
		return this.scr;
	}

	public void setScr(String scr) {
		this.scr = scr;
	}

//	@Column(name = "SCSJ", length = 23)
	public Date getScsj() {
		return this.scsj;
	}

	public void setScsj(Date scsj) {
		this.scsj = scsj;
	}

//	@Column(name = "SCYY",  length = 100)
	public String getScyy() {
		return this.scyy;
	}

	public void setScyy(String scyy) {
		this.scyy = scyy;
	}
	
//	@Column(name = "LADJH")
	public String getLadjh() {
		return ladjh;
	}
	public void setLadjh(String ladjh) {
		this.ladjh = ladjh;
	}
	
//	@Column(name = "DJXH")
	public String getDjxh() {
		return djxh;
	}

	public void setDjxh(String djxh) {
		this.djxh = djxh;
	}
	public void setDjxh(int djxh) {
		this.djxh = djxh+"";
	}
	
	public DzjzWdWjDO(String wjid, Integer ajxh, String ah, String wjmc, String wjlx, Date cjsj, String cjr, String wjlj) {
		this.wjid = wjid;
		this.ajxh = ajxh;
		this.ah = ah;
		this.wjmc = wjmc;
		this.wjlx = wjlx;
		this.cjsj = cjsj;
		this.cjr = cjr;
		this.wjlj = wjlj;
	}
}