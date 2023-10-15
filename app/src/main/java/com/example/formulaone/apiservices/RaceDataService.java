package com.example.formulaone.apiservices;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.formulaone.MainActivity;
import com.example.formulaone.MySingleton;
import com.example.formulaone.models.Circuit;
import com.example.formulaone.models.CircuitLocation;
import com.example.formulaone.models.Constructor;
import com.example.formulaone.models.Driver;
import com.example.formulaone.models.Race;
import com.example.formulaone.models.Result;
import com.example.formulaone.models.ResultFastestLap;
import com.example.formulaone.models.ResultFastestLapAverageSpeed;
import com.example.formulaone.models.ResultFastestLapTime;
import com.example.formulaone.models.ResultTime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RaceDataService {
    Date formattedRaceDate;
    Context context;
    public RaceDataService(Context context) {
        this.context = context;
        ;
    }

    public interface RaceResponseListener {
        void onResponse(List<Race> races);

        void onError(String error);

    }

    public void RaceRecentResult(RaceResponseListener raceResponseListener) {

        String url = "https://ergast.com/api/f1/current/last/results.json";
        List<Race> raceList = new ArrayList<>();
        List<Circuit> circuitList = new ArrayList<>();
        List<CircuitLocation> circuitLocationList = new ArrayList<>();
        List<Driver> driverList = new ArrayList<>();
        List<Constructor> constructorList = new ArrayList<>();
        List<ResultTime> resultTimeList = new ArrayList<>();
        List<ResultFastestLapTime> resultFastestLapTimeList = new ArrayList<>();
        List<ResultFastestLapAverageSpeed> resultFastestLapAverageSpeedList = new ArrayList<>();
        List<ResultFastestLap> resultFastestLapList = new ArrayList<>();
        List<Result> resultList = new ArrayList<>();


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONObject("MRData").getJSONObject("RaceTable").getJSONArray("Races");
                    String len = String.valueOf(jsonArray.length());
                    Toast.makeText(context, len.toString() , Toast.LENGTH_SHORT);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject races = jsonArray.getJSONObject(i);
                        String season = races.getString("season");
                        String round = races.getString("round");
                        String url = races.getString("url");
                        String raceName = races.getString("raceName");


                        JSONObject circuit = races.getJSONObject("Circuit");
                        String circuitId = circuit.getString("circuitId");
                        String circuitUrl = circuit.getString("url");
                        String circuitName = circuit.getString("circuitName");

                        JSONObject location = circuit.getJSONObject("Location");
                        String circuitLat = location.getString("lat");
                        String circuitLong = location.getString("long");
                        String country = location.getString("country");
                        String locality = location.getString("locality");

                        CircuitLocation circuitLocation = new CircuitLocation(circuitLat, circuitLong, country, locality);

                        Circuit mCircuit = new Circuit(circuitId, round, circuitUrl, circuitName, circuitLocation);
                        circuitList.add(mCircuit);
                        circuitLocationList.add(circuitLocation);

                        String raceDate = races.getString("date");
                        String raceTime = races.getString("time");

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            formattedRaceDate = sdf.parse(raceDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }



                        JSONArray results = races.getJSONArray("Results");
                        //
                        // Toast.makeText(context, results.toString(), Toast.LENGTH_SHORT).show();
                        Driver mDriver;
                        for (i = 1; i <= 20; i++) {
                            JSONObject resultItem = results.getJSONObject(i);
                            String number = resultItem.getString("number");
                            String position = resultItem.getString("position");
                            String positionText = resultItem.getString("positionText");
                            String points = resultItem.getString("points");
                            //Toast.makeText(context, resultItem.toString(), Toast.LENGTH_SHORT).show();

                            JSONObject driver = resultItem.getJSONObject("Driver");
                            String driverId = driver.getString("driverId");
                            int permanentNumber = driver.getInt("permanentNumber");
                            String driverUrl = driver.getString("url");
                            String driverGivenName = driver.getString("givenName");
                            String lastName = driver.getString("familyName");
                            String driverFamilyName = driver.getString("familyName");
                            String driverNationality = driver.getString("nationality");
                            String driverCode = driver.getString("code");
                            String dateOfBirth = driver.getString("dateOfBirth");

                            mDriver = new Driver(driverId, driverCode, permanentNumber, driverGivenName, lastName,  dateOfBirth, driverNationality, url, null);
                            driverList.add(mDriver);

                            JSONObject constructor = resultItem.getJSONObject("Constructor");
                            String constructorId = constructor.getString("constructorId");
                            String conUrl = constructor.getString("url");
                            String conName = constructor.getString("name");
                            String conNationality = constructor.getString("nationality");

                            Constructor mConstructor = new Constructor(constructorId, conUrl, conName, conNationality, "null");
                            constructorList.add(mConstructor);


                            String grid = resultItem.getString("grid");
                            //Toast.makeText(context, grid, Toast.LENGTH_SHORT).show();
                            String laps = resultItem.getString("laps");
                            String status = resultItem.getString("status");

                            JSONObject ObjTime = resultItem.getJSONObject("Time");
                            String millis = ObjTime.getString("millis");
                            String timeText = ObjTime.getString("time");

                            ResultTime mTime = new ResultTime(millis, timeText);
                            resultTimeList.add(mTime);

                            JSONObject fastestLap = resultItem.getJSONObject("FastestLap");
                            String rank = fastestLap.getString("rank");
                            String lap = fastestLap.getString("lap");

                            JSONObject fTime = fastestLap.getJSONObject("Time");
                            String fLapTime = fTime.getString("time");
                            ResultFastestLapTime mfLapTime = new ResultFastestLapTime(fLapTime);
                            resultFastestLapTimeList.add(mfLapTime);

                            JSONObject averageSpeed = fastestLap.getJSONObject("AverageSpeed");
                            String units = averageSpeed.getString("units");
                            String speed = averageSpeed.getString("speed");

                            ResultFastestLapAverageSpeed fLapAvg = new ResultFastestLapAverageSpeed(units, speed);
                            resultFastestLapAverageSpeedList.add(fLapAvg);

                            ResultFastestLap mFastestLap = new ResultFastestLap(rank, lap, mfLapTime, fLapAvg);
                            resultFastestLapList.add(mFastestLap);

                            Result result = new Result(number, position, positionText, points, mDriver, mConstructor, grid, laps, status, mTime, mFastestLap);
                            resultList.add(result);
                            //Toast.makeText(context, races.toString(), Toast.LENGTH_SHORT);
                            //Toast.makeText(context, resultList.get(0).toString(), Toast.LENGTH_SHORT).show(); //working

                        }
                        //Toast.makeText(context, "outside of for loop", Toast.LENGTH_SHORT).show(); //working

                        Race race = new Race(season, round, url, raceName, mCircuit, formattedRaceDate, raceTime, resultList);
                        raceList.add(race);
                        //Toast.makeText(context, race.toString(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(context, raceList.get(0).toString(), Toast.LENGTH_SHORT).show();
                    }

                    raceResponseListener.onResponse(raceList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}
