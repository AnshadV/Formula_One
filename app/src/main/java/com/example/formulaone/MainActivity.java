package com.example.formulaone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formulaone.apiservices.ConstructorDataService;
import com.example.formulaone.apiservices.DriverDataService;
import com.example.formulaone.apiservices.RaceDataService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;

import org.json.JSONObject;

import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.Branch;
import io.branch.referral.BranchError;
import io.branch.referral.util.LinkProperties;


public class MainActivity extends AppCompatActivity {
    RecyclerView constructorList;
    RecyclerView.Adapter constructorAdapter;
    BottomNavigationView bottomNavigationView;
    NavController navController;
    DatabaseReference databaseRefecrence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navController = Navigation.findNavController(this, R.id.main_nav_host_fragment);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        DriverDataService driverDataService = new DriverDataService(MainActivity.this);
        ConstructorDataService constructorDataService = new ConstructorDataService(MainActivity.this);
        RaceDataService raceDataService = new RaceDataService(MainActivity.this);










    }



    @Override public void onStart() {
        super.onStart();


        Branch.sessionBuilder(this).withCallback(new Branch.BranchUniversalReferralInitListener(){

            @Override
            public void onInitFinished(@Nullable BranchUniversalObject branchUniversalObject, @Nullable LinkProperties linkProperties, @Nullable BranchError error) {
                if(error != null) {
                    Log.e("BranchSDK_Tester", "branch init failed" + error.getMessage());
                } else {
                    Log.i("BranchSDK_Tester", "branch init complete!");
                    if(branchUniversalObject!=null) {
                        Log.e("BranchSDK_Tester" ,"title"+ branchUniversalObject.getTitle());
                    }

                    if(linkProperties!= null) {
                        Log.i("Branch SDK Tester", "Channel" + linkProperties.getChannel());
                    }
                }
            }
        }).withData(this.getIntent().getData()).init();



        //Branch.getInstance().initSession(branchReferralInitListener, this.getIntent().getData(), this);

        /*Branch.sessionBuilder(this).withCallback(new Branch.BranchReferralInitListener() {
            @Override
            public void onInitFinished(@Nullable JSONObject referringParams, @Nullable BranchError error) {
                if(error == null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("$referring_link", referringParams.toString());

                } else {
                    Log.i("BranchSDK", "Branch Referral Error: " + error.getMessage());
                }
            }
        }).withData(this.getIntent().getData()).init();
        */

        //integration test
        //IntegrationValidator.validate(this);

    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.setIntent(intent);
        if (intent != null && intent.hasExtra("branch_force_new_session") && intent.getBooleanExtra("branch_force_new_session",false)) {
            Branch.sessionBuilder(this).withCallback(new Branch.BranchReferralInitListener() {
                @Override
                public void onInitFinished(JSONObject referringParams, BranchError error) {
                    if (error != null) {
                        Log.e("BranchSDK_Tester", error.getMessage());
                    } else if (referringParams != null) {
                        Log.i("BranchSDK_Tester", referringParams.toString());
                    }
                }
            }).reInit();
        }
    }

    //delete the cart value on activity destroy







}