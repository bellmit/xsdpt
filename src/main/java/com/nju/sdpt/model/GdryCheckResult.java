package com.nju.sdpt.model;

import com.nju.sdpt.entity.PubYysdRyxxEntity;

public class GdryCheckResult {
    boolean isSuccess;
    PubYysdRyxxEntity pubYysdRyxxEntity;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public PubYysdRyxxEntity getPubYysdRyxxEntity() {
        return pubYysdRyxxEntity;
    }

    public void setPubYysdRyxxEntity(PubYysdRyxxEntity pubYysdRyxxEntity) {
        this.pubYysdRyxxEntity = pubYysdRyxxEntity;
    }
}
