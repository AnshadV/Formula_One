package com.example.formulaone.models;

import java.util.List;

public class RaceData {
    private MRData MRData;

    public MRData getMRData() {
        return MRData;
    }

    public static class MRData {
        private RaceTable RaceTable;

        public RaceTable getRaceTable() {
            return RaceTable;
        }
    }

    public static class RaceTable {
        private String season;
        private String round;
        private List<Race> Races;

        public String getSeason() {
            return season;
        }

        public String getRound() {
            return round;
        }

        public List<Race> getRaces() {
            return Races;
        }
    }

    public static class Race {
        private String season;
        private String round;
        private String url;
        private String raceName;
        private Circuit Circuit;
        private String date;
        private String time;
        private List<Result> Results;

        public String getSeason() {
            return season;
        }

        public String getRound() {
            return round;
        }

        public String getUrl() {
            return url;
        }

        public String getRaceName() {
            return raceName;
        }

        public Circuit getCircuit() {
            return Circuit;
        }

        public String getDate() {
            return date;
        }

        public String getTime() {
            return time;
        }

        public List<Result> getResults() {
            return Results;
        }
    }

    public static class Circuit {
        private String circuitId;
        private String url;
        private String circuitName;
        private Location Location;

        public String getCircuitId() {
            return circuitId;
        }

        public String getUrl() {
            return url;
        }

        public String getCircuitName() {
            return circuitName;
        }

        public Location getLocation() {
            return Location;
        }
    }

    public static class Location {
        private String lat;
        private String a;
        private String locality;
        private String country;

        public String getLat() {
            return lat;
        }

        public String getA() {
            return a;
        }

        public String getLocality() {
            return locality;
        }

        public String getCountry() {
            return country;
        }
    }

    public static class Result {
        private String number;
        private String position;
        private String positionText;
        private String points;
        private Driver Driver;
        private Constructor Constructor;
        private String grid;
        private String laps;
        private String status;
        private Time Time;
        private FastestLap FastestLap;

        public String getNumber() {
            return number;
        }

        public String getPosition() {
            return position;
        }

        public String getPositionText() {
            return positionText;
        }

        public String getPoints() {
            return points;
        }

        public Driver getDriver() {
            return Driver;
        }

        public Constructor getConstructor() {
            return Constructor;
        }

        public String getGrid() {
            return grid;
        }

        public String getLaps() {
            return laps;
        }

        public String getStatus() {
            return status;
        }

        public Time getTime() {
            return Time;
        }

        public FastestLap getFastestLap() {
            return FastestLap;
        }
    }

    public static class Driver {
        private String driverId;
        private String permanentNumber;
        private String code;
        private String url;
        private String givenName;
        private String familyName;
        private String dateOfBirth;
        private String nationality;

        public String getDriverId() {
            return driverId;
        }

        public String getPermanentNumber() {
            return permanentNumber;
        }

        public String getCode() {
            return code;
        }

        public String getUrl() {
            return url;
        }

        public String getGivenName() {
            return givenName;
        }

        public String getFamilyName() {
            return familyName;
        }

        public String getDateOfBirth() {
            return dateOfBirth;
        }

        public String getNationality() {
            return nationality;
        }
    }

    public static class Constructor {
        private String constructorId;
        private String url;
        private String name;
        private String nationality;

        public String getConstructorId() {
            return constructorId;
        }

        public String getUrl() {
            return url;
        }

        public String getName() {
            return name;
        }

        public String getNationality() {
            return nationality;
        }
    }

    public static class Time {
        private String millis;
        private String time;

        public String getMillis() {
            return millis;
        }

        public String getTime() {
            return time;
        }
    }

    public static class FastestLap {
        private String rank;
        private String lap;
        private Time Time;
        private AverageSpeed AverageSpeed;

        public String getRank() {
            return rank;
        }

        public String getLap() {
            return lap;
        }

        public Time getTime() {
            return Time;
        }

        public AverageSpeed getAverageSpeed() {
            return AverageSpeed;
        }
    }

    public static class AverageSpeed {
        private String units;
        private String speed;

        public String getUnits() {
            return units;
        }

        public String getSpeed() {
            return speed;
        }
    }
}

