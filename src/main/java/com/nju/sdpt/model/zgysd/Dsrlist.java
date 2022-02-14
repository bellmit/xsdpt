/**
  * Copyright 2020 bejson.com 
  */
package com.nju.sdpt.model.zgysd;
import java.util.List;

/**
 * Auto-generated: 2020-07-27 15:43:26
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Dsrlist {

    private String dsrid;
    private String dsrmc;
    private String ssdw;
    private String fileurl;
    private List<Dzsd> dzsd;
    private List<Yjsd> yjsd;
    private List<Zjsd> zjsd;
    private List<Ggsd> ggsd;
    public void setDsrid(String dsrid) {
         this.dsrid = dsrid;
     }
     public String getDsrid() {
         return dsrid;
     }

    public void setDsrmc(String dsrmc) {
         this.dsrmc = dsrmc;
     }
     public String getDsrmc() {
         return dsrmc;
     }

    public void setSsdw(String ssdw) {
         this.ssdw = ssdw;
     }
     public String getSsdw() {
         return ssdw;
     }

    public void setFileurl(String fileurl) {
         this.fileurl = fileurl;
     }
     public String getFileurl() {
         return fileurl;
     }

    public void setDzsd(List<Dzsd> dzsd) {
         this.dzsd = dzsd;
     }
     public List<Dzsd> getDzsd() {
         return dzsd;
     }

    public void setYjsd(List<Yjsd> yjsd) {
         this.yjsd = yjsd;
     }
     public List<Yjsd> getYjsd() {
         return yjsd;
     }

    public void setZjsd(List<Zjsd> zjsd) {
         this.zjsd = zjsd;
     }
     public List<Zjsd> getZjsd() {
         return zjsd;
     }

    public void setGgsd(List<Ggsd> ggsd) {
         this.ggsd = ggsd;
     }
     public List<Ggsd> getGgsd() {
         return ggsd;
     }

}