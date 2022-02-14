package com.nju.sdpt.model;

/**
 * @author jiaweiq
 * @date 2022/1/8 13:56
 */
public class ResultObj {
    private Integer code;
    private Object data;
    private String message;

    public ResultObj(String message){
        this.code=400;
        this.message=message;
    }

    public ResultObj(Object data) {
        this.code=200;
        this.data = data;
    }

    public ResultObj() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
