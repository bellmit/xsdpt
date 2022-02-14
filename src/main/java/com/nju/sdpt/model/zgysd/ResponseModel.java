package com.nju.sdpt.model.zgysd;

/**
 * 最高院送达通信Response实体类
 */
public class ResponseModel {

    private String code;
    private String msg;

    public ResponseModel() {
    }

    public ResponseModel(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
