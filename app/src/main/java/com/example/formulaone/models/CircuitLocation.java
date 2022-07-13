package com.example.formulaone.models;

import com.google.gson.annotations.SerializedName;

public class CircuitLocation {
    @SerializedName("lat")
    private String lat = null;

    @SerializedName("long")
    private String _long = null;

    @SerializedName("locality")
    private String locality = null;

    @SerializedName("country")
    private String country = null;

    public CircuitLocation(String lat, String _long, String locality, String country) {
        this.lat = lat;
        this._long = _long;
        this.locality = locality;
        this.country = country;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String get_long() {
        return _long;
    }

    public void set_long(String _long) {
        this._long = _long;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
