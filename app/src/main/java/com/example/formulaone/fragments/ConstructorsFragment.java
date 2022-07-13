package com.example.formulaone.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.formulaone.MainActivity;
import com.example.formulaone.R;
import com.example.formulaone.adapter.ConstructorAdapter;
import com.example.formulaone.apiservices.ConstructorDataService;
import com.example.formulaone.models.Constructor;

import java.util.ArrayList;
import java.util.List;


public class ConstructorsFragment extends Fragment {
    RecyclerView constructorRecyclerView;
    ConstructorDataService constructorDataService;
    ConstructorAdapter constructorAdapter;

    public ConstructorsFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_constructors, container, false);
        constructorRecyclerView = view.findViewById(R.id.constructorRecyclerView);
        constructorDataService = new ConstructorDataService(getContext());

        constructorDataService.constructorByYear(2022, new ConstructorDataService.ConstructorResponseListener() {
            @Override
            public void onResponse(List<Constructor> constructors) {
                constructorAdapter = new ConstructorAdapter((ArrayList<Constructor>) constructors, getContext());
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                constructorRecyclerView.setLayoutManager(manager);
                constructorRecyclerView.setAdapter(constructorAdapter);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}