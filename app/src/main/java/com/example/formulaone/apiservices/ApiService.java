package com.example.formulaone.apiservices;

import com.example.formulaone.DriverModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("drivers.json")
    Call<List<Driver>> getDrivers();
}
