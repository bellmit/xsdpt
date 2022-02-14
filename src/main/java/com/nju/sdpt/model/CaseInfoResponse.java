package com.nju.sdpt.model;

public class CaseInfoResponse {

    private String message;
    private String code;
    private String  success;
    private CaseInfoData data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public CaseInfoData getData() {
        return data;
    }

    public void setData(CaseInfoData data) {
        this.data = data;
    }
}
