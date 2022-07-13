package com.example.formulaone.models;

import com.google.gson.annotations.SerializedName;

public class ResultTime {
    @SerializedName("millis")
    private String millis = null;

    @SerializedName("time")
    private String time = null;

    public ResultTime(String millis, String time) {
        this.millis = millis;
        this.time = time;
    }

    public String getMillis() {
        return millis;
    }

    public void setMillis(String millis) {
        this.millis = millis;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
