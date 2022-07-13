package com.example.formulaone.models;

import com.google.gson.annotations.SerializedName;

public class ResultFastestLapTime {
    @SerializedName("time")
    private String time = null;

    public ResultFastestLapTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
