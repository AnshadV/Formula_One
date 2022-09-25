package com.example.formulaone.apiservices;

import android.content.Context;
import android.provider.ContactsContract;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.formulaone.MySingleton;
import com.example.formulaone.models.Driver;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DriverDataService {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String link="";
    Context context;
    int year;

    public DriverDataService(Context context) {
        this.context = context;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public interface DriverDetailsResponseListener {
        void onResponse(Driver driver);

        void onError(String message);
    }

    public void driverById(String driverId, DriverDetailsResponseListener driverDetailsResponseListener) {
        String url = "https://ergast.com/api/f1/drivers/" + driverId + ".json";


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject = response.getJSONObject("MRData").getJSONObject("DriverTable").getJSONArray("Drivers").getJSONObject(0);
                    String driverId = jsonObject.getString("driverId");
                    String firstName = jsonObject.getString("givenName");
                    String driverCode = jsonObject.getString("code");
                    String lastName = jsonObject.getString("familyName");
                    int permanentNumber = jsonObject.getInt("permanentNumber");
                    String dateOfBirth = jsonObject.getString("dateOfBirth");
                    String nationality = jsonObject.getString("nationality");
                    //Toast.makeText(context, "On call" + nationality, Toast.LENGTH_SHORT).show();
                    String url = jsonObject.getString("url");
                    Driver driver = new Driver(driverId, driverCode, permanentNumber, firstName, lastName, dateOfBirth, nationality, url, null);
                    driverDetailsResponseListener.onResponse(driver);
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
        MySingleton.getInstance(context).addToRequestQueue(request);
    }



    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(List<Driver> drivers);
    }



    public void driverByYear(int year, VolleyResponseListener volleyResponseListener) {
        String url = "https://ergast.com/api/f1/" + year + "/drivers.json";
        List<Driver> drivers = new ArrayList<>();
        //set up volley request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //Toast.makeText(context, "Success level one", Toast.LENGTH_SHORT).show();
                    JSONArray jsonArray = response.getJSONObject("MRData").getJSONObject("DriverTable").getJSONArray("Drivers");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject driver = jsonArray.getJSONObject(i);
                        String driverName = driver.getString("givenName");
                        String LastName = driver.getString("familyName");
                        //Toast.makeText(context, driverName, Toast.LENGTH_SHORT).show();
                        String driverId = driver.getString("driverId");
                        String driverCode = driver.getString("code");
                        int permanentNumber = driver.getInt("permanentNumber");
                        String driverUrl = driver.getString("url");
                        String dateOfBirth = driver.getString("dateOfBirth");
                        String nationality = driver.getString("nationality");
                        String driverImage = driver.getString("driverId") + ".jpg";
                        DatabaseReference getImage = databaseReference.child(driverCode);
                        getImgLink(getImage);
                        String driverImageUrl = "https://www.formula1.com/content/fomula1/drivers/" + driverImage;
                        Driver driverObject = new Driver(driverId, driverCode, permanentNumber, driverName, LastName, dateOfBirth, nationality, driverUrl, link );
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

    public void getImgLink(DatabaseReference getImage) {
        getImage.addValueEventListener(new ValueEventListener() {

            //pass an json array to request body


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                link = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }
}
