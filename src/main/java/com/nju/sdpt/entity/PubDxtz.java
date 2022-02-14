package com.nju.sdpt.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (PubDxtz)实体类
 *
 * @author makejava
 * @since 2021-10-26 09:12:14
 */
public class PubDxtz implements Serializable {
    private static final long serialVersionUID = 223235172198125959L;
    
    private Integer dxtzid;
    
    private Integer yysdbh;
    
    private Integer ssdrbh;
    
    private Integer sdybh;
    
    private String targettel;
    
    private String content;
    
    private Integer sendstate;
    
    private Date createtime;
    
    private String uuid;
    
    private Integer visited;


    public Integer getDxtzid() {
        return dxtzid;
    }

    public void setDxtzid(Integer dxtzid) {
        this.dxtzid = dxtzid;
    }

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }

    public Integer getSsdrbh() {
        return ssdrbh;
    }

    public void setSsdrbh(Integer ssdrbh) {
        this.ssdrbh = ssdrbh;
    }

    public Integer getSdybh() {
        return sdybh;
    }

    public void setSdybh(Integer sdybh) {
        this.sdybh = sdybh;
    }

    public String getTargettel() {
        return targettel;
    }

    public void setTargettel(String targettel) {
        this.targettel = targettel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSendstate() {
        return sendstate;
    }

    public void setSendstate(Integer sendstate) {
        this.sendstate = sendstate;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getVisited() {
        return visited;
    }

    public void setVisited(Integer visited) {
        this.visited = visited;
    }

}

