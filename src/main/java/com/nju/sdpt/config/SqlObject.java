package com.nju.sdpt.config;

import java.io.Serializable;

public class
SqlObject implements Serializable {


    private static final long serialVersionUID = 3508173657083642474L;

    private String id;
    private long timeLength;
    private long currentTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(long timeLength) {
        this.timeLength = timeLength;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }
}
