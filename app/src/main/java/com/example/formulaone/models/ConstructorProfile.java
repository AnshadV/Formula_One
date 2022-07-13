package com.example.formulaone.models;

public class ConstructorProfile {
    String teamName;
    String base;
    String chassis;
    String fastestLaps;
    String polePositions;
    String teamChief;
    String technicalChief;
    String worldChampionships;

    public ConstructorProfile() {
    }

    public ConstructorProfile(String teamName, String base, String chassis, String fastestLaps, String polePositions, String teamChief, String technicalChief, String worldChampionships) {
        this.teamName = teamName;
        this.base = base;
        this.chassis = chassis;
        this.fastestLaps = fastestLaps;
        this.polePositions = polePositions;
        this.teamChief = teamChief;
        this.technicalChief = technicalChief;
        this.worldChampionships = worldChampionships;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public String getFastestLaps() {
        return fastestLaps;
    }

    public void setFastestLaps(String fastestLaps) {
        this.fastestLaps = fastestLaps;
    }

    public String getPolePositions() {
        return polePositions;
    }

    public void setPolePositions(String polePositions) {
        this.polePositions = polePositions;
    }

    public String getTeamChief() {
        return teamChief;
    }

    public void setTeamChief(String teamChief) {
        this.teamChief = teamChief;
    }

    public String getTechnicalChief() {
        return technicalChief;
    }

    public void setTechnicalChief(String technicalChief) {
        this.technicalChief = technicalChief;
    }

    public String getWorldChampionships() {
        return worldChampionships;
    }

    public void setWorldChampionships(String worldChampionships) {
        this.worldChampionships = worldChampionships;
    }
}
