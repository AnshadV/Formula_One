package com.example.formulaone;

import static com.android.volley.toolbox.Volley.newRequestQueue;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

public class MySingleton {
    private static MySingleton instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private MySingleton(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public static synchronized MySingleton getInstance(Context context) {
        if (instance == null) {
            instance = new MySingleton(context);
        }

        return instance;
    }
}
