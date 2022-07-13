package com.example.formulaone.models;

public class ConstructorStanding {
    String name;
    String points;
    String position;
    String link;

    public ConstructorStanding(String name, String points, String position, String link) {
        this.name = name;
        this.points = points;
        this.position = position;
        this.link = link;
    }

    public ConstructorStanding() {}


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String constructorName) {
        this.name = constructorName;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
