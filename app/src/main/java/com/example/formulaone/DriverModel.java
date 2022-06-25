package com.example.formulaone;

public class DriverModel {
    public String driverId;
    public String driverCode;
    public int permanentNumber;
    public String driverName;
    public String dateOfBirth;
    public String nationality;
    public String url;

    public DriverModel(String driverId, String driverCode, int permanentNumber, String driverName, String dateOfBirth, String nationality, String url) {
        this.driverId = driverId;
        this.driverCode = driverCode;
        this.permanentNumber = permanentNumber;
        this.driverName = driverName;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.url = url;
    }

    @Override
    public String toString() {
        return "DriverModel{" +
                "driverId='" + driverId + '\'' +
                ", driverCode='" + driverCode + '\'' +
                ", permanentNumber=" + permanentNumber +
                ", driverName='" + driverName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", nationality='" + nationality + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public DriverModel() {}

    public String getDriverId() {
        return driverId;
    }

    public String getDriverCode() {
        return driverCode;
    }

    public int getPermanentNumber() {
        return permanentNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public void setDriverCode(String driverCode) {
        this.driverCode = driverCode;
    }

    public void setPermanentNumber(int permanentNumber) {
        this.permanentNumber = permanentNumber;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getUrl() {
        return url;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
