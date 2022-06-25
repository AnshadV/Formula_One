package com.example.formulaone.apiservices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DriverTable {

    @SerializedName("season")
    @Expose
    private String season;
    @SerializedName("Drivers")
    @Expose
    private List<Driver> drivers = null;

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    @Override
    public String toString() {
        return "DriverTable{" +
                "season='" + season + '\'' +
                ", drivers=" + drivers +
                '}';
    }
}
