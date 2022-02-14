package com.nju.sdpt;

public class DownloadRequestModel {

    private String id;
    private String path;
    private boolean base64;

    public DownloadRequestModel(String id, String path, boolean base64) {
        this.id = id;
        this.path = path;
        this.base64 = base64;
    }

    public DownloadRequestModel() {
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

    public boolean isBase64() {
        return base64;
    }

    public void setBase64(boolean base64) {
        this.base64 = base64;
    }
}
