package com.example.formulaone.apiservices;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.formulaone.MySingleton;
import com.example.formulaone.models.RaceData;
import com.google.gson.Gson;

import org.json.JSONObject;

public class rRaceDataService {
    private Context context;

    public rRaceDataService(Context context) {
        this.context = context;
    }

    public interface RaceResponseListener {
        void onResponse(RaceData raceData);

        void onError(String message);
    }

    public void getRaceData(RaceResponseListener raceResponseListener) {
        String url = "https://ergast.com/api/f1/current/last/results.json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Gson gson = new Gson();
                    RaceData raceData = gson.fromJson(response.toString(), RaceData.class);
                    Log.d("RaceDataService", "JSON Response: " + new Gson().toJson(raceData));
                    raceResponseListener.onResponse(raceData);
                } catch (Exception e) {
                    e.printStackTrace();
                    raceResponseListener.onError("Error parsing JSON");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                raceResponseListener.onError(error.getMessage());
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}

