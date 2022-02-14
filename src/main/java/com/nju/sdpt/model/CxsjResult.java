package com.nju.sdpt.model;

import javax.xml.crypto.Data;
import java.util.Date;

public class CxsjResult {
    private Boolean success;
    private Date cxsj;
    private String msg;
    private String data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Date getCxsj() {
        return cxsj;
    }

    public void setCxsj(Date cxsj) {
        this.cxsj = cxsj;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
