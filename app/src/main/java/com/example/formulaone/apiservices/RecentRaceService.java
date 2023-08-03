package com.example.formulaone.apiservices;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.formulaone.MySingleton;
import com.example.formulaone.models.RecentRace;

import org.json.JSONArray;
import org.json.JSONObject;

public class RecentRaceService {

    public interface RecentRaceResponseListener{

        void onResponse(RecentRace recentRace);

        void onError(String error);

    }

    public void RecentRace(RecentRaceResponseListener recentRaceResponseListener) {
        String url = "http://ergast.com/api/f1/current/last/results.json";

        RecentRace recentRaceObj;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //JSONArray jsonArray = response.getJSONObject("MRData").getJSONObject("RaceTable").getJSONArray("Races");
                    //String season = jsonArray.getString("season");
                    //String round = jsonArray.getString("round");
                    //String

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        //MySingleton.getInstance(context).addToRequestQueue(request);

    }

}
