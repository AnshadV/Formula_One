package com.example.formulaone.models;

import com.google.gson.annotations.SerializedName;

public class ResultFastestLapAverageSpeed {
    @SerializedName("units")
    private String units = null;

    @SerializedName("speed")
    private String speed = null;

    public ResultFastestLapAverageSpeed(String units, String speed) {
        this.units = units;
        this.speed = speed;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }
}
