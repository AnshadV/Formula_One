package com.example.formulaone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.formulaone.adapter.ConstructorAdapter;
import com.example.formulaone.apiservices.ConstructorDataService;
import com.example.formulaone.apiservices.DriverDataService;
import com.example.formulaone.apiservices.RaceDataService;
import com.example.formulaone.fragments.DriverProfileFragment;
import com.example.formulaone.models.Constructor;
import com.example.formulaone.models.Driver;
import com.example.formulaone.models.Race;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DatabaseReference;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.Branch;
import io.branch.referral.BranchError;
import io.branch.referral.util.LinkProperties;
import io.branch.referral.validators.IntegrationValidator;


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

        Branch.sessionBuilder(this).withCallback(new Branch.BranchReferralInitListener() {
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


        //integration test
        //IntegrationValidator.validate(this);

    }

    //delete the cart value on activity destroy


    public Branch.BranchUniversalReferralInitListener branchReferralInitListener = new Branch.BranchUniversalReferralInitListener() {
        @Override
        public void onInitFinished(@Nullable BranchUniversalObject branchUniversalObject, @Nullable LinkProperties linkProperties, @Nullable BranchError error) {
            if(branchUniversalObject == null) {
                Log.i("BranchSDK", "Branch Universal Object is null");
                Navigation.findNavController(MainActivity.this, R.id.main_nav_host_fragment).navigate(R.id.homeFragment);
            } else if(branchUniversalObject.getContentMetadata().getCustomMetadata().containsKey("$android_deeplink_path")){
                Log.i("BranchSDK", "Branch Universal Object does not contain deeplink path");

                String driverId = branchUniversalObject.getContentMetadata().getCustomMetadata().get("driverId");

                Log.i("BranchSDK", "Driver Code: " + branchUniversalObject.getContentMetadata().getCustomMetadata().get("driverId"));
                Log.i("BranchSDK", "Driver Code: " + driverId);
                Bundle bundle = new Bundle();
                bundle.putString("driverId", driverId);
                //Toast.makeText(MainActivity.this, driverId.toString(), Toast.LENGTH_SHORT).show();
                navController.popBackStack(R.id.homeFragment, false);
                Navigation.findNavController(MainActivity.this, R.id.main_nav_host_fragment).navigate(R.id.action_homeFragment_to_driverProfileFragment, bundle);


            }
        }
    };


}