package com.example.formulaone.models;

public class DriverStanding {

    String position;
    String firstName;
    String lastName;
    String points;
    String link;
    String constructor;

    public DriverStanding() {
    }

    public DriverStanding(String position, String firstName, String lastName, String points, String link, String constructor) {
        this.position = position;
        this.firstName = firstName;
        this.lastName = lastName;
        this.points = points;
        this.link = link;
        this.constructor = constructor;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getConstructor() {
        return constructor;
    }

    public void setConstructor(String constructor) {
        this.constructor = constructor;
    }
}
