package com.example.formulaone.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.formulaone.MainActivity;
import com.example.formulaone.R;
import com.example.formulaone.adapter.ConstructorStandingAdapter;
import com.example.formulaone.adapter.DriverStandingAdapter;
import com.example.formulaone.adapter.RecentRaceAdapter;
import com.example.formulaone.apiservices.RaceDataService;
import com.example.formulaone.models.ConstructorStanding;
import com.example.formulaone.models.DriverStanding;
import com.example.formulaone.models.Race;
import com.example.formulaone.models.RecentRace;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    TextView raceName, date, monthYear;
    RecyclerView latestResultsRecyclerView, driverStandingsRecyclerView, constructorStandingRecyclerView;
    DatabaseReference mbase, mbase1, mbase2;
    RecentRaceAdapter recentRaceAdapter;
    DriverStandingAdapter driverStandingAdapter;
    ConstructorStandingAdapter constructorStandingAdapter;
    Query query;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        raceName = view.findViewById(R.id.raceName);
        latestResultsRecyclerView = view.findViewById(R.id.latestResultsRecyclerView);
        driverStandingsRecyclerView = view.findViewById(R.id.driverStandingsRecyclerView);
        constructorStandingRecyclerView = view.findViewById(R.id.constructorStandingRecyclerView);

        mbase = FirebaseDatabase.getInstance().getReference("recentResult");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        latestResultsRecyclerView.setLayoutManager(linearLayoutManager);
        FirebaseRecyclerOptions<RecentRace> options = new FirebaseRecyclerOptions.Builder<RecentRace>().setQuery(mbase, RecentRace.class).build();
        recentRaceAdapter = new RecentRaceAdapter(options);
        latestResultsRecyclerView.setAdapter(recentRaceAdapter);

        mbase1 = FirebaseDatabase.getInstance().getReference("DriverStandings");
        query = mbase1.orderByChild("position");



        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        driverStandingsRecyclerView.setLayoutManager(linearLayoutManager1);

        FirebaseRecyclerOptions<DriverStanding> options1 = new FirebaseRecyclerOptions.Builder<DriverStanding>().setQuery(query, DriverStanding.class).build();
        driverStandingAdapter = new DriverStandingAdapter(options1);
        driverStandingsRecyclerView.setAdapter(driverStandingAdapter);

        mbase2 = FirebaseDatabase.getInstance().getReference("ConstructorStandings");

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        constructorStandingRecyclerView.setLayoutManager(linearLayoutManager2);

        FirebaseRecyclerOptions<ConstructorStanding> options2 = new FirebaseRecyclerOptions.Builder<ConstructorStanding>().setQuery(mbase2, ConstructorStanding.class).build();
        constructorStandingAdapter = new ConstructorStandingAdapter(options2);
        constructorStandingRecyclerView.setAdapter(constructorStandingAdapter);










        raceName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RaceDataService raceDataService = new RaceDataService(getContext());

                raceDataService.RaceRecentResult(new RaceDataService.RaceResponseListener() {
                    @Override
                    public void onResponse(List<Race> races) {
                        //Toast.makeText(getContext(), "Succes call",Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getContext(),races.toString() ,Toast.LENGTH_SHORT).show();
                        raceName.setText(races.get(0).getRaceName());

                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        //set view to homeFragemnt
        //ViewGroup container = (ViewGroup) getActivity().findViewById(R.id.main_nav_host_fragment);
        //LayoutInflater.from(getParentFragment().getContext()).inflate(R.layout.fragment_home, container, true);
        super.onStart();
        recentRaceAdapter.startListening();
        driverStandingAdapter.startListening();
        constructorStandingAdapter.startListening();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onStop() {
        super.onStop();
        recentRaceAdapter.stopListening();
        driverStandingAdapter.stopListening();
        constructorStandingAdapter.stopListening();
    }
}