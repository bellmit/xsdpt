package com.nju.sdpt.model;

public class DownloadResult {
    String message;
    String content;
    Integer code;

    public DownloadResult(String message, String content, Integer code) {
        this.message = message;
        this.content = content;
        this.code = code;
    }

    public DownloadResult() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
