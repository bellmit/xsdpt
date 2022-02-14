package com.nju.sdpt.model;

public class UploadRequestModel {
    private String id;
    private String path;
    private boolean override;
    private boolean base64;
    private String content;

    public UploadRequestModel() {
    }

    public UploadRequestModel(String id, String path, boolean override, boolean base64, String content) {
        this.id = id;
        this.path = path;
        this.override = override;
        this.base64 = base64;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isOverride() {
        return override;
    }

    public void setOverride(boolean override) {
        this.override = override;
    }

    public boolean isBase64() {
        return base64;
    }

    public void setBase64(boolean base64) {
        this.base64 = base64;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
