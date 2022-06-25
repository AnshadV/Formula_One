package com.example.formulaone;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DriverDataService {

    Context context;
    int year;

    public DriverDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(List<DriverModel> drivers);
    }

    public void driverByYear(int year, VolleyResponseListener volleyResponseListener) {
        Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show();
        String url = "https://ergast.com/api/f1/" + year + "/drivers.json";
        List<DriverModel> drivers = new ArrayList<>();
        //set up volley request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Toast.makeText(context, "Success level one", Toast.LENGTH_SHORT).show();
                    JSONArray jsonArray = response.getJSONObject("MRData").getJSONObject("DriverTable").getJSONArray("Drivers");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject driver = jsonArray.getJSONObject(i);
                        String driverName = driver.getString("givenName") + " " + driver.getString("familyName");
                        //Toast.makeText(context, driverName, Toast.LENGTH_SHORT).show();
                        String driverId = driver.getString("driverId");
                        String driverCode = driver.getString("code");
                        int permanentNumber = driver.getInt("permanentNumber");
                        String driverUrl = driver.getString("url");
                        String dateOfBirth = driver.getString("dateOfBirth");
                        String nationality = driver.getString("nationality");
                        String driverImage = driver.getString("driverId") + ".jpg";
                        //String driverImageUrl = "https://www.formula1.com/content/fomula1/drivers/" + driverImage;
                        DriverModel driverObject = new DriverModel(driverId, driverCode, permanentNumber, driverName, dateOfBirth, nationality, driverUrl );
                        //Toast.makeText(context, driverObject.toString(),Toast.LENGTH_SHORT).show();
                        drivers.add(driverObject);
                        //Toast.makeText(context, "Success level two", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(context, drivers.get(i).toString(), Toast.LENGTH_SHORT).show();

                    }
                    volleyResponseListener.onResponse(drivers);
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
        //add request to queue
        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}
