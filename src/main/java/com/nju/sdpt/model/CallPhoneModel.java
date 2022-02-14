package com.nju.sdpt.model;

/**
 * @author jiaweiq
 * @date 2021/10/25 10:37
 */
public class CallPhoneModel {

    private String uuid;
    private String create;
    private String target;
    private String gh;
    private String wx;

    public CallPhoneModel() {
    }

    public CallPhoneModel(String create, String target, String gh, String wx) {
        this.create = create;
        this.target = target;
        this.gh = gh;
        this.wx = wx;
    }

    public CallPhoneModel(String uuid, String create, String target, String gh, String wx) {
        this.uuid = uuid;
        this.create = create;
        this.target = target;
        this.gh = gh;
        this.wx = wx;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getGh() {
        return gh;
    }

    public void setGh(String gh) {
        this.gh = gh;
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }
}
