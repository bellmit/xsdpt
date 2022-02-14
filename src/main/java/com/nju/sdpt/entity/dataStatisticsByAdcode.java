package com.nju.sdpt.entity;

/**
 * @author jiaweiq
 * @date 2021/12/27 15:58
 */
public class dataStatisticsByAdcode {

    private String Adcode;
    private String fybh;
    private int ljgdsl;
    private int ljgdwcsl;
    private int ljsasl;

    private int jrgdsl;
    private int jrgdwcsl;
    private int jrsasl;

    public String getAdcode() {
        return Adcode;
    }

    public void setAdcode(String adcode) {
        Adcode = adcode;
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh;
    }

    public int getLjgdsl() {
        return ljgdsl;
    }

    public void setLjgdsl(int ljgdsl) {
        this.ljgdsl = ljgdsl;
    }

    public int getLjgdwcsl() {
        return ljgdwcsl;
    }

    public void setLjgdwcsl(int ljgdwcsl) {
        this.ljgdwcsl = ljgdwcsl;
    }

    public int getLjsasl() {
        return ljsasl;
    }

    public void setLjsasl(int ljsasl) {
        this.ljsasl = ljsasl;
    }

    public int getJrgdsl() {
        return jrgdsl;
    }

    public void setJrgdsl(int jrgdsl) {
        this.jrgdsl = jrgdsl;
    }

    public int getJrgdwcsl() {
        return jrgdwcsl;
    }

    public void setJrgdwcsl(int jrgdwcsl) {
        this.jrgdwcsl = jrgdwcsl;
    }

    public int getJrsasl() {
        return jrsasl;
    }

    public void setJrsasl(int jrsasl) {
        this.jrsasl = jrsasl;
    }
}
