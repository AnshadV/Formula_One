package com.example.formulaone.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.formulaone.MainActivity;
import com.example.formulaone.R;
import com.example.formulaone.adapter.DriverAdapter;
import com.example.formulaone.apiservices.DriverDataService;
import com.example.formulaone.models.Driver;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import io.branch.referral.Branch;


public class DriversFragment extends Fragment {
    DriverDataService driverDataService;
    RecyclerView recyclerView;
    DriverAdapter driverAdapter;
    ShimmerFrameLayout shimmerFrameLayout;


    public DriversFragment() {
        // Required empty public constructor
    }

    public static DriversFragment newInstance(String param1, String param2) {
        DriversFragment fragment = new DriversFragment();
        return fragment;
    }

    @Override
    public void onStart() {


        super.onStart();


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Branch branch = Branch.getAutoInstance(this.getContext());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drivers, container, false);
        recyclerView = view.findViewById(R.id.driverRecyclerView);


        driverDataService = new DriverDataService(getContext());

        driverDataService.driverByYear(2022, new DriverDataService.VolleyResponseListener() {

            @Override
            public void onError(String message) {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(List<Driver> drivers) {
                Log.i("TAG", "onResponse: " + drivers.size());

                //Toast.makeText(getContext(), drivers.toString(), Toast.LENGTH_SHORT).show();
                driverAdapter = new DriverAdapter((ArrayList<Driver>) drivers, getContext());
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(driverAdapter);


            }
        });

        return view;
    }
}