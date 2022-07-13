package com.example.formulaone.models;

import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("number")
    private String number = null;

    @SerializedName("position")
    private String position = null;

    @SerializedName("positionText")
    private String positionText = null;

    @SerializedName("points")
    private String points = null;

    @SerializedName("Driver")
    private Driver driver = null;

    @SerializedName("Constructor")
    private Constructor constructor = null;

    @SerializedName("grid")
    private String grid = null;

    @SerializedName("laps")
    private String laps = null;

    @SerializedName("status")
    private String status = null;

    @SerializedName("Time")
    private ResultTime time = null;

    @SerializedName("FastestLap")
    private ResultFastestLap fastestLap = null;

    @Override
    public String toString() {
        return "Result{" +
                "number='" + number + '\'' +
                ", position='" + position + '\'' +
                ", positionText='" + positionText + '\'' +
                ", points='" + points + '\'' +
                ", driver=" + driver +
                ", constructor=" + constructor +
                ", grid='" + grid + '\'' +
                ", laps='" + laps + '\'' +
                ", status='" + status + '\'' +
                ", time=" + time +
                ", fastestLap=" + fastestLap +
                '}';
    }

    public Result(String number, String position, String positionText, String points, Driver driver, Constructor constructor, String grid, String laps, String status, ResultTime time, ResultFastestLap fastestLap) {
        this.number = number;
        this.position = position;
        this.positionText = positionText;
        this.points = points;
        this.driver = driver;
        this.constructor = constructor;
        this.grid = grid;
        this.laps = laps;
        this.status = status;
        this.time = time;
        this.fastestLap = fastestLap;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPositionText() {
        return positionText;
    }

    public void setPositionText(String positionText) {
        this.positionText = positionText;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Constructor getConstructor() {
        return constructor;
    }

    public void setConstructor(Constructor constructor) {
        this.constructor = constructor;
    }

    public String getGrid() {
        return grid;
    }

    public void setGrid(String grid) {
        this.grid = grid;
    }

    public String getLaps() {
        return laps;
    }

    public void setLaps(String laps) {
        this.laps = laps;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ResultTime getTime() {
        return time;
    }

    public void setTime(ResultTime time) {
        this.time = time;
    }

    public ResultFastestLap getFastestLap() {
        return fastestLap;
    }

    public void setFastestLap(ResultFastestLap fastestLap) {
        this.fastestLap = fastestLap;
    }
}
