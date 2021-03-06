package com.example.formulaone.apiservices;

import com.example.formulaone.utils.Print0;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpRef {

    public static final String domain = "https://ergast.com/api/f1";

    public static OkHttpClient okClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();

            // Request customization: add request headers
            Request.Builder requestBuilder = original.newBuilder().method(original.method(), original.body());

            Request request = requestBuilder.build();
            Print0.log("GET CALLS : " + chain.request().url().toString());
            // System.out.println("Request__: "+request.headers().toString()+" :
            // "+request.body().toString()+" : " +chain.request().headers().toString()+" :
            // "+chain.request().body().toString());
            return chain.proceed(request);
        }
    }).connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).build();

    private ApiService apiService;

    public static Retrofit builder = new Retrofit.Builder().baseUrl(domain).client(okClient)
            .addConverterFactory(GsonConverterFactory.create()).client(okClient).build();

    public static ApiService createService() {
        return builder.create(ApiService.class);
    }

    public ApiService getRetrofitInstance() {
        if(apiService == null) {
            apiService = createService();
        }
        return apiService;
    }
}
