package com.juan.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransacModel {

    @JsonProperty("time")
    private long time;

    @JsonProperty("value")
    private double value;

    public TransacModel() {
    }

    public TransacModel(final long time, final double value) {
        this.time = time;
        this.value = value;
    }

    public long getTime() {
        return time;
    }

    public void setTime(final long time) {
        this.time = time;
    }

    public double getValue() {
        return value;
    }

    public void setValue(final double value) {
        this.value = value;
    }
}
