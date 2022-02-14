package com.nju.sdpt.viewobject;

import com.nju.sdpt.constant.SdptConstants;
import com.nju.sdpt.entity.PubLogEntity;

import java.util.Date;

public class LogVO {
    String creater;
    Date creattime;
    String type;
    String targetname;

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTargetname() {
        return targetname;
    }

    public void setTargetname(String targetname) {
        this.targetname = targetname;
    }

    public LogVO(PubLogEntity pubLogEntity){
        this.creater = pubLogEntity.getCreater();
        this.creattime = pubLogEntity.getCreattime();
        this.type = SdptConstants.LOG_TYPE.getLogTypeByTypeNum(pubLogEntity.getLogtype()).getContent();
        this.targetname = pubLogEntity.getTargetname();
    }
}
