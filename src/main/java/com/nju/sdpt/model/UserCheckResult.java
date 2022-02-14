package com.nju.sdpt.model;

/**
 * Created by XXT on 2019/5/8.
 */
import java.io.Serializable;


public class UserCheckResult implements Serializable{

    private static final long serialVersionUID = 530560250721561545L;

    boolean isSuccess;

    ResultCodeEnum resultCode;

    XtyhModel xtyh;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public ResultCodeEnum getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCodeEnum resultCode) {
        this.resultCode = resultCode;
    }

    public XtyhModel getXtyh() {
        return xtyh;
    }

    public void setXtyh(XtyhModel xtyh) {
        this.xtyh = xtyh;
    }

}

