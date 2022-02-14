package com.nju.sdpt.model;

public class TjxxModel {
    String name;
    double value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public TjxxModel() {
    }

    public TjxxModel(String name, double value) {
        this.name = name;
        this.value = value;
    }
}
