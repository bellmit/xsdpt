package com.nju.sdpt.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 向日志平台发送日志的实体类
 */
public class NewLogModel {

    String ip;
    String id; //bgbapt
    String time;
    String yhdm;
    String fydm;
    String message; //类型
    String bz; //补充内容

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getYhdm() {
        return yhdm;
    }

    public void setYhdm(String yhdm) {
        this.yhdm = yhdm;
    }

    public String getFydm() {
        return fydm;
    }

    public void setFydm(String fydm) {
        this.fydm = fydm;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public NewLogModel(String ip, String id, Date time, String yhdm, String fydm, String message, String bz) {
        this.ip = ip;
        this.id = id;
        SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString =formatter.format(time);

        this.time = dateString;
        this.yhdm = yhdm;

        this.fydm = fydm;
        this.message = message;
        this.bz = bz;
    }

    public NewLogModel() {
    }



}
