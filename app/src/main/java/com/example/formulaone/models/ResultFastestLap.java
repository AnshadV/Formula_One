package com.example.formulaone.models;

import com.google.gson.annotations.SerializedName;

public class ResultFastestLap {
    @SerializedName("rank")
    private String rank = null;

    @SerializedName("lap")
    private String lap = null;

    @SerializedName("Time")
    private ResultFastestLapTime time = null;

    @SerializedName("AverageSpeed")
    private ResultFastestLapAverageSpeed averageSpeed = null;

    public ResultFastestLap(String rank, String lap, ResultFastestLapTime time, ResultFastestLapAverageSpeed averageSpeed) {
        this.rank = rank;
        this.lap = lap;
        this.time = time;
        this.averageSpeed = averageSpeed;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getLap() {
        return lap;
    }

    public void setLap(String lap) {
        this.lap = lap;
    }

    public ResultFastestLapTime getTime() {
        return time;
    }

    public void setTime(ResultFastestLapTime time) {
        this.time = time;
    }

    public ResultFastestLapAverageSpeed getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(ResultFastestLapAverageSpeed averageSpeed) {
        this.averageSpeed = averageSpeed;
    }
}
