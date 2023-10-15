package com.example.formulaone.models;

import java.util.List;
public class DriverStanding {
    private int position;
    private String driverName;
    private String constructor;
    private int points;
    private int wins;

    public DriverStanding(int position, String driverName, String constructor, int points, int wins) {
        this.position = position;
        this.driverName = driverName;
        this.points = points;
        this.wins = wins;
        this.constructor = constructor;
    }

    public int getPosition() {
        return position;
    }

    public String getDriverName() {
        return driverName;
    }

    public int getPoints() {
        return points;
    }

    public int getWins() {
        return wins;
    }

    public String getConstructor() {
        return constructor;
    }

    public void setConstructor(String constructor) {
        this.constructor = constructor;
    }
}
