package com.example.formulaone.apiservices;

import android.content.Context;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.formulaone.MySingleton;
import com.example.formulaone.models.Constructor;
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


public class ConstructorDataService {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Context context;
    String link="";

    public ConstructorDataService(Context context) {
        this.context = context;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public interface ConstructorResponseListener {
        void onResponse(List<Constructor> constructors);

        void onError(String error);
    }



    //Constructor constructor = new Constructor();
    //ConstructorStandingsByYearResponse response = constructorsApi.getConstructorStandingsByYear(2020, )



    public void constructorByYear(int year, ConstructorResponseListener constructorResponseListener) {
        String url = "https://ergast.com/api/f1/" + year + "/constructors.json";
        List<Constructor> constructors = new ArrayList<>();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONObject("MRData").getJSONObject("ConstructorTable").getJSONArray("Constructors");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject constructor = jsonArray.getJSONObject(i);
                        String constructorName = constructor.getString("name");
                        String constructorId = constructor.getString("constructorId");
                        String constructorUrl = constructor.getString("url");
                        String constructorNationality = constructor.getString("nationality");
                        DatabaseReference getImage = databaseReference.child(constructorId);
                        getImgLink(getImage);
                        Constructor constructorObject = new Constructor(constructorId, constructorUrl, constructorName, constructorNationality, link);
                        constructors.add(constructorObject);
                    }
                    constructorResponseListener.onResponse(constructors);
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

    public void getImgLink(DatabaseReference getImage) {
        getImage.addValueEventListener(new ValueEventListener() {

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
