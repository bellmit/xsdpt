package com.nju.sdpt.model;

public class UploadResult {
    String message;
    String path;
    Integer code;

    public UploadResult() {
    }

    public UploadResult(String message, String path, Integer code) {
        this.message = message;
        this.path = path;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
