package com.example.formulaone.apiservices;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.formulaone.MySingleton;

import java.util.HashMap;
import java.util.Map;

public class BranchDataService {
    Context context;

    public BranchDataService(Context context) {
        this.context = context;
    }

    public interface BranchResponseListener {
        void onResponse(String response);

        void onError(String message);
    }

    public void purchaseEvent(BranchResponseListener branchResponseListener) {
        String url = "";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Event logged: ", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Event logging error: ", error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers=new HashMap<String,String>();
                headers.put("Content-Type","application/json");
                return headers;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(postRequest);
    }
}
