package com.example.formulaone.apiservices;

import com.example.formulaone.models.Constructor;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {


    //Get all constructors by year
    @GET(HttpRef.domain + "{year}/constructors.json")
    Call<Constructor> getConstructors(@Path("year") int year);



}
