package com.example.formulaone.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Constructor {

    @SerializedName("constructorId")
    @Expose
    private String constructorId;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nationality")
    @Expose
    private String nationality;

    private String conImageUrl;

    public Constructor() {}

    public Constructor(String constructorId, String url, String name, String nationality, String conImageUrl) {
        this.constructorId = constructorId;
        this.url = url;
        this.name = name;
        this.nationality = nationality;
        this.conImageUrl = conImageUrl;
    }


    public String getConstructorId() {
        return constructorId;
    }

    public String getConImageUrl() {
        return conImageUrl;
    }

    public void setConImageUrl(String conImageUrl) {
        this.conImageUrl = conImageUrl;
    }

    public void setConstructorId(String constructorId) {
        this.constructorId = constructorId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Constructor{" +
                "constructorId='" + constructorId + '\'' +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
