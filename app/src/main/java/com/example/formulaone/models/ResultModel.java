package com.example.formulaone.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultModel {
    @SerializedName("season")
    private String season = null;

    @SerializedName("round")
    private String round = null;

    @SerializedName("Races")
    private List<Race> races = null;

    public ResultModel(String season, String round, List<Race> races) {
        this.season = season;
        this.round = round;
        this.races = races;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public List<Race> getRaces() {
        return races;
    }

    public void setRaces(List<Race> races) {
        this.races = races;
    }
}


