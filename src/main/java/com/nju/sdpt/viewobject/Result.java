package com.nju.sdpt.viewobject;

public class Result {
    Boolean isSuccess;
    String message;
    Object object;


    public static Result succeed = new Result(true,"成功",null);
    public static Result failed = new Result(false,"失败",null);

    public Result() {
    }

    public Result(Boolean isSuccess, String message, Object object) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.object = object;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
