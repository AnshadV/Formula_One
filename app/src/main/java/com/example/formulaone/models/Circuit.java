package com.example.formulaone.models;

import com.google.gson.annotations.SerializedName;

public class Circuit {

    @SerializedName("circuitId")
    private String circuitId = null;

    @SerializedName("round")
    private String round = null;

    @SerializedName("url")
    private String url = null;

    @SerializedName("circuitName")
    private String circuitName = null;

    @SerializedName("Location")
    private CircuitLocation location = null;

    public Circuit(String circuitId, String round, String url, String circuitName, CircuitLocation location) {
        this.circuitId = circuitId;
        this.round = round;
        this.url = url;
        this.circuitName = circuitName;
        this.location = location;
    }

    public String getCircuitId() {
        return circuitId;
    }

    public void setCircuitId(String circuitId) {
        this.circuitId = circuitId;
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

    public String getCircuitName() {
        return circuitName;
    }

    public void setCircuitName(String circuitName) {
        this.circuitName = circuitName;
    }

    public CircuitLocation getLocation() {
        return location;
    }

    public void setLocation(CircuitLocation location) {
        this.location = location;
    }
}
