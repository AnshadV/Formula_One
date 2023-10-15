package com.example.formulaone.apiservices;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.formulaone.MySingleton;
import com.example.formulaone.models.DriverStanding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DriverStandingDataService {
    Context context;

    public DriverStandingDataService(Context context) {
        this.context = context;
    }

    public interface DriverStandingResponseListener {
        void onResponse(List<DriverStanding> driverStandings);

        void onError(String message);
    }

    public void driverStandings(DriverStandingResponseListener driverStandingResponseListener) {
        String url = "https://ergast.com/api/f1/current/driverStandings.json";
        List<DriverStanding> driverStandings = new ArrayList<>();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                List<DriverStanding> driverStandings = new ArrayList<>();
                try {
                    JSONArray standingsLists = response.getJSONObject("MRData")
                            .getJSONObject("StandingsTable")
                            .getJSONArray("StandingsLists");

                    if (standingsLists.length() > 0) {
                        JSONArray standings = standingsLists.getJSONObject(0)
                                .getJSONArray("DriverStandings");

                        for (int i = 0; i < standings.length(); i++) {
                            JSONObject driverStanding = standings.getJSONObject(i);

                            int position = driverStanding.getInt("position");
                            int points = driverStanding.getInt("points");
                            String driverName = driverStanding.getJSONObject("Driver")
                                    .getString("givenName") + " " + driverStanding.getJSONObject("Driver")
                                    .getString("familyName");
                            int wins = driverStanding.getInt("wins");
                            String constructor = driverStanding.getJSONArray("Constructors").getJSONObject(0).getString("name");
                            Log.d("constructor", constructor.toString());

                            DriverStanding driverStandingObject = new DriverStanding(position,driverName, constructor, points, wins);
                            driverStandings.add(driverStandingObject);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                driverStandingResponseListener.onResponse(driverStandings);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                driverStandingResponseListener.onError(error.getMessage());
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}