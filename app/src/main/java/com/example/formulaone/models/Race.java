package com.example.formulaone.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Race {

    @SerializedName("season")
    private String season = null;

    @SerializedName("round")
    private String round = null;

    @SerializedName("url")
    private String url = null;

    @SerializedName("raceName")
    private String raceName = null;

    @SerializedName("Circuit")
    private Circuit circuit = null;

    @SerializedName("date")
    private Date date = null;

    @SerializedName("time")
    private String time = null;

    @SerializedName("Results")
    private List<Result> results = null;

    public Race(String season, String round, String url, String raceName, Circuit circuit, Date date, String time, List<Result> results) {
        this.season = season;
        this.round = round;
        this.url = url;
        this.raceName = raceName;
        this.circuit = circuit;
        this.date = date;
        this.time = time;
        this.results = results;
    }


    @Override
    public String toString() {
        return "Race{" +
                "season='" + season + '\'' +
                ", round='" + round + '\'' +
                ", url='" + url + '\'' +
                ", raceName='" + raceName + '\'' +
                ", circuit=" + circuit +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", results=" + results +
                '}';
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
