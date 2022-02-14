package com.nju.sdpt.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * (DhsdHjmx)实体类
 *
 * @author makejava
 * @since 2021-10-22 13:57:20
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "DHSDINFO")
@XmlType(propOrder = {
        "id",
        "sessionId",
        "uuid",
        "Starttime",
        "Endtime",
        "Agentid",
        "Agentname",
        "Agentdn",
        "Skillid",
        "Skillname",
        "Calltype",
        "Localurl",
        "Remoteurl",
        "Ivrduration",
        "Alertduration",
        "Alterdurationagent",
        "Queueduration",
        "Talkduration",
        "Acwduration",
        "EndType",
})
public class DhsdHjmx implements Serializable {
    private static final long serialVersionUID = -10732220068734596L;

    private Integer id;
    /**
     * 与lyjl表中的sessionId对应
     */
    private String sessionId;
    /**
     * 与uuid对应
     */
    private String uuid;
    /**
     * 开始时间（毫秒）
     */
    private String Starttime;
    /**
     * 结束时间（毫秒）
     */
    private String Endtime;
    /**
     * 坐席ID
     */
    private String Agentid;
    /**
     * 坐席姓名
     */
    private String Agentname;
    /**
     * 坐席分机号
     */
    private String Agentdn;
    /**
     * 技能组ID
     */
    private String Skillid;
    /**
     * 技能组名称
     */
    private String Skillname;
    /**
     * 呼叫类型
     */
    private Integer Calltype;

    private String Localurl;
    /**
     * 客户号码
     */
    private String Remoteurl;
    /**
     * ivr时长
     */
    private Integer Ivrduration;
    /**
     * 呼入时表示坐席振铃时长，呼出时表示客户振铃时长
     */
    private Integer Alertduration;
    /**
     * 呼出时表示坐席振铃时长
     */
    private Integer Alterdurationagent;
    /**
     * 排队时长
     */
    private Integer Queueduration;
    /**
     * 通话时长
     */
    private Integer Talkduration;
    /**
     * 事后处理时长
     */
    private Integer Acwduration;
    /**
     * 结束类型
     */
    private String EndType;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getStarttime() {
        return Starttime;
    }

    public void setStarttime(String starttime) {
        Starttime = starttime;
    }

    public String getEndtime() {
        return Endtime;
    }

    public void setEndtime(String endtime) {
        Endtime = endtime;
    }

    public String getAgentid() {
        return Agentid;
    }

    public void setAgentid(String agentid) {
        Agentid = agentid;
    }

    public String getAgentname() {
        return Agentname;
    }

    public void setAgentname(String agentname) {
        Agentname = agentname;
    }

    public String getAgentdn() {
        return Agentdn;
    }

    public void setAgentdn(String agentdn) {
        Agentdn = agentdn;
    }

    public String getSkillid() {
        return Skillid;
    }

    public void setSkillid(String skillid) {
        Skillid = skillid;
    }

    public String getSkillname() {
        return Skillname;
    }

    public void setSkillname(String skillname) {
        Skillname = skillname;
    }

    public Integer getCalltype() {
        return Calltype;
    }

    public void setCalltype(Integer calltype) {
        Calltype = calltype;
    }

    public String getLocalurl() {
        return Localurl;
    }

    public void setLocalurl(String localurl) {
        Localurl = localurl;
    }

    public String getRemoteurl() {
        return Remoteurl;
    }

    public void setRemoteurl(String remoteurl) {
        Remoteurl = remoteurl;
    }

    public Integer getIvrduration() {
        return Ivrduration;
    }

    public void setIvrduration(Integer ivrduration) {
        Ivrduration = ivrduration;
    }

    public Integer getAlertduration() {
        return Alertduration;
    }

    public void setAlertduration(Integer alertduration) {
        Alertduration = alertduration;
    }

    public Integer getAlterdurationagent() {
        return Alterdurationagent;
    }

    public void setAlterdurationagent(Integer alterdurationagent) {
        Alterdurationagent = alterdurationagent;
    }

    public Integer getQueueduration() {
        return Queueduration;
    }

    public void setQueueduration(Integer queueduration) {
        Queueduration = queueduration;
    }

    public Integer getTalkduration() {
        return Talkduration;
    }

    public void setTalkduration(Integer talkduration) {
        Talkduration = talkduration;
    }

    public Integer getAcwduration() {
        return Acwduration;
    }

    public void setAcwduration(Integer acwduration) {
        Acwduration = acwduration;
    }

    public String getEndType() {
        return EndType;
    }

    public void setEndType(String endType) {
        EndType = endType;
    }
}
