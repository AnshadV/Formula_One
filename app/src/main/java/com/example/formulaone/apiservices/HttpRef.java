package com.example.formulaone.apiservices;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpRef {

    public final static String BASE_URL = "https://ergast.com/api/f1/";

    public final static String DRIVER_URL = "drivers.json";

   Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


}
