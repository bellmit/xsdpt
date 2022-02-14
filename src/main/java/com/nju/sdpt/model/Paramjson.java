package com.nju.sdpt.model;

import java.util.List;

public class Paramjson {
    private int bh;
    private List<String> paramjson;

    public int getBh() {
        return bh;
    }

    public void setBh(int bh) {
        this.bh = bh;
    }

    public List<String> getParamjson() {
        return paramjson;
    }

    public void setParamjson(List<String> paramjson) {
        this.paramjson = paramjson;
    }

    public Paramjson(int bh, List<String> paramjson) {
        this.bh = bh;
        this.paramjson = paramjson;
    }

    public Paramjson() {
    }
}
