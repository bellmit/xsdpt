package com.nju.sdpt.model;

import java.io.Serializable;

/**
 * Created by XXT on 2019/5/9.
 */
public class Mediation implements Serializable {
    protected String id;

    protected String fybh;

    protected Integer mediationid;

    protected String mediationcaseno;

    protected Integer state;

    protected String casetype;

    protected Integer type;

    protected String caseno;

    protected Integer organizationid;

    protected Integer mediatorid;



    protected static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh;
    }

    public Integer getMediationid() {
        return mediationid;
    }

    public void setMediationid(Integer mediationid) {
        this.mediationid = mediationid;
    }

    public String getMediationcaseno() {
        return mediationcaseno;
    }

    public void setMediationcaseno(String mediationcaseno) {
        this.mediationcaseno = mediationcaseno;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCasetype() {
        return casetype;
    }

    public void setCasetype(String casetype) {
        this.casetype = casetype;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCaseno() {
        return caseno;
    }

    public void setCaseno(String caseno) {
        this.caseno = caseno;
    }

    public Integer getOrganizationid() {
        return organizationid;
    }

    public void setOrganizationid(Integer organizationid) {
        this.organizationid = organizationid;
    }

    public Integer getMediatorid() {
        return mediatorid;
    }

    public void setMediatorid(Integer mediatorid) {
        this.mediatorid = mediatorid;
    }
}
