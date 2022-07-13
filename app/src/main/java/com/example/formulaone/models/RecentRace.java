package com.example.formulaone.models;

public class RecentRace {

    public String position;
    public String firstName;
    public String lastName;
    public String time;

    public RecentRace() {
    }

    public RecentRace(String position, String firstName, String lastName, String time) {
        this.position = position;
        this.firstName = firstName;
        this.lastName = lastName;
        this.time = time;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
