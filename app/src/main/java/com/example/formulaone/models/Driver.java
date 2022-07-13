package com.example.formulaone.models;

import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.util.ContentMetadata;

public class Driver {
    public String driverId;
    public String driverCode;
    public int permanentNumber;
    public String driverName;
    public String lastName;
    public String dateOfBirth;
    public String nationality;
    public String url;
    public String driverImageUrl;

    public Driver(String driverId, String driverCode, int permanentNumber, String driverName, String lastName, String dateOfBirth, String nationality, String url, String driverImageUrl) {
        this.driverId = driverId;
        this.driverCode = driverCode;
        this.permanentNumber = permanentNumber;
        this.driverName = driverName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.url = url;
        this.driverImageUrl = driverImageUrl;
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

    public Driver() {}

    public String getDriverImageUrl() {
        return driverImageUrl;
    }

    public void setDriverImageUrl(String driverImageUrl) {
        this.driverImageUrl = driverImageUrl;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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

    public BranchUniversalObject getDriverObj() {
        return new BranchUniversalObject()
                .setTitle(getDriverName() + " " + getLastName())
                .setContentDescription("Check out this driver")
                .setContentImageUrl(getDriverImageUrl())
                .setContentMetadata(new ContentMetadata()
                        .addCustomMetadata("driverId", getDriverId())
                        .addCustomMetadata("driverCode", getDriverCode())
                        .addCustomMetadata("permanentNumber", String.valueOf(getPermanentNumber()))
                        .addCustomMetadata("driverName", getDriverName())
                        .addCustomMetadata("lastName", getLastName())
                        .addCustomMetadata("dateOfBirth", getDateOfBirth())
                        .addCustomMetadata("nationality", getNationality())
                );

    }


}
