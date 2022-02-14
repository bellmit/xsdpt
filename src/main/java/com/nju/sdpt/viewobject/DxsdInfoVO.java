package com.nju.sdpt.viewobject;

import org.apache.ibatis.annotations.Select;

public class DxsdInfoVO {
    Integer id;
    String dxly;//短信来源
    String phone;
    String time;
    String content;
    String status;
    String fwzt;
    String dxtzid;
    String ssdrmc;


    public DxsdInfoVO() {
    }

    public DxsdInfoVO(Integer id, String dxly, String phone, String time, String content,Integer status, Integer fwzt, String dxtzid,String ssdrmc) {
        this.id = id;
        this.dxly = dxly;
        this.phone = phone;
        this.time = time;
        this.content = content;
        if(status == 1){
            this.status = "发送成功";
        }else if(status == 2){
            this.status = "发送失败";
        }else {
            this.status = "发送中";
        }
        if(fwzt==null){
            this.fwzt = "暂无";
        }else   if(fwzt == 1){
            this.fwzt = "已访问";
        }else  if(fwzt == 0){
            this.fwzt = "未访问";
        }
        this.dxtzid = dxtzid;
        this.ssdrmc = ssdrmc;
    }

    public String getFwzt() {
        return fwzt;
    }

    public void setFwzt(String fwzt) {
        this.fwzt = fwzt;
    }

    public String getDxtzid() {
        return dxtzid;
    }

    public void setDxtzid(String dxtzid) {
        this.dxtzid = dxtzid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDxly() {
        return dxly;
    }

    public void setDxly(String dxly) {
        this.dxly = dxly;
    }

    public String getSsdrmc() {
        return ssdrmc;
    }

    public void setSsdrmc(String ssdrmc) {
        this.ssdrmc = ssdrmc;
    }
}
