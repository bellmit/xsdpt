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
public class JsonRootBean {

    private String ajid;
    private String ah;
    private List<Dsrlist> dsrlist;
    public void setAjid(String ajid) {
         this.ajid = ajid;
     }
     public String getAjid() {
         return ajid;
     }

    public void setAh(String ah) {
         this.ah = ah;
     }
     public String getAh() {
         return ah;
     }

    public void setDsrlist(List<Dsrlist> dsrlist) {
         this.dsrlist = dsrlist;
     }
     public List<Dsrlist> getDsrlist() {
         return dsrlist;
     }

}