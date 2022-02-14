package com.nju.sdpt.entity;

public class PubGroupEntity {
    private Integer id;

    private Integer ryid;

    private String rymc;

    private String groupName;

    private String fybh;

    public PubGroupEntity(Integer id, Integer ryid, String rymc, String groupName) {
        this.id = id;
        this.ryid = ryid;
        this.rymc = rymc;
        this.groupName = groupName;
    }

    public PubGroupEntity(Integer id, Integer ryid, String rymc, String groupName, String fybh) {
        this.id = id;
        this.ryid = ryid;
        this.rymc = rymc;
        this.groupName = groupName;
        this.fybh = fybh;
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh;
    }

    public PubGroupEntity() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRyid() {
        return ryid;
    }

    public void setRyid(Integer ryid) {
        this.ryid = ryid;
    }

    public String getRymc() {
        return rymc;
    }

    public void setRymc(String rymc) {
        this.rymc = rymc == null ? null : rymc.trim();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }
}