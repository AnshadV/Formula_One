package com.example.formulaone;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
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
import com.example.formulaone.models.BranchEvent;
import com.example.formulaone.models.Constructor;
import com.example.formulaone.models.Driver;
import com.example.formulaone.models.Race;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import org.json.JSONObject;

import java.io.Console;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.Branch;
import io.branch.referral.BranchError;
import io.branch.referral.util.BRANCH_STANDARD_EVENT;
import io.branch.referral.util.ContentMetadata;
import io.branch.referral.util.LinkProperties;
import io.branch.referral.validators.IntegrationValidator;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.firebase.ui.auth.AuthUI;


public class MainActivity extends AppCompatActivity {
    RecyclerView constructorList;
    RecyclerView.Adapter constructorAdapter;
    BottomNavigationView bottomNavigationView;
    NavController navController;
    DatabaseReference databaseRefecrence;

    AsyncTask<?, ?, ?> runningTask;
    private FirebaseAnalytics mFirebaseAnalytics;
    String FbInstanceId;
    BranchEvent branchEvent;

    private WeakReference<Context> contextWeakReference;
    //private BranchAdvertisingIDCallback branchAdvertisingIDCallback;



    @SuppressLint("HardwareIds")
    public String getAndroidID() {
        //ContentResolver contentResolver = contextWeakReference.get().getContentResolver();
        Log.e("android_id", Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID));
        return Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
    }



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

    private void initializeBranch() {
        if (FbInstanceId != null) {
            Branch.getInstance().setRequestMetadata("$firebase_app_instance_id", FbInstanceId);

            new io.branch.referral.util.BranchEvent(BRANCH_STANDARD_EVENT.START_TRIAL)
                    .logEvent(this);
        } else {
            Log.e("Error", "FbInstanceId is null");
        }
    }


    @Override public void onStart() {
        super.onStart();

        //IntegrationValidator.validate(this);

        //runningTask = new doHttpCalln();
        //runningTask.execute();


        //Branch.expectDelayedSessionInitialization(true);

        //Branch.getInstance().disableTracking(true);
        //Branch.getInstance().val

        getAndroidID();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Task<String> task = mFirebaseAnalytics.getAppInstanceId();
        task.addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String s) {
                Log.e("Instance id", s);

                // Set the FbInstanceId variable and initialize the Branch instance
                FbInstanceId = s;
                initializeBranch();
            }
        });



        Branch.sessionBuilder(this).withCallback(new Branch.BranchUniversalReferralInitListener() {
            @Override
            public void onInitFinished(BranchUniversalObject branchUniversalObject, LinkProperties linkProperties, BranchError error) {
                if (error != null) {
                    Log.e("BranchSDK_Tester", "branch init failed. Caused by -" + error.getMessage());
                } else {
                    Log.e("BranchSDK_Tester", "branch init complete!");
                    if (branchUniversalObject != null) {
                        Log.e("BranchSDK_Tester", "title " + branchUniversalObject.getTitle());
                        Log.e("BranchSDK_Tester", "CanonicalIdentifier " + branchUniversalObject.getCanonicalIdentifier());
                        Log.e("BranchSDK_Tester", "metadata " + branchUniversalObject.getContentMetadata().convertToJson());
                    }

                    if (linkProperties != null) {
                        Log.e("BranchSDK_Tester", "Channel " + linkProperties.getChannel());
                        Log.e("BranchSDK_Tester", "control params " + linkProperties.getControlParams());
                    }
                }
            }
        }).withData(this.getIntent().getData()).init();

        //integration test


    }



    //delete the cart value on activity destroy





    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                @Override
                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                    onSignInResult(result);
                }
            }
    );

    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.GoogleBuilder().build());

    Intent signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build();
    //signInLauncher.launch(signInIntent);

    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();
        if(result.getResultCode() == RESULT_OK) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        } else {
            //sign in failed
        }
    }


    private class doHttpCall extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            Response response = null;
                OkHttpClient client = new OkHttpClient();
                Log.i("Oncreate", "Start");
                Request request = new Request.Builder()
                        .url("https://api2.branch.io/v1/url?url=https://f6rwa.app.link/mobile/sso&branch_key=key_live_mjZS3VGlwUcl8Hk1uRlWUhegqvjRvEtJ")
                        .get()
                        .addHeader("accept", "application/json")
                        .build();

            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Log.i("executed from OnCreate", String.valueOf(response));
        }

        }

    private class doHttpCalln extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            Response response = null;
            OkHttpClient client = new OkHttpClient();
            Log.i("OnStart", "Start");
            Request request = new Request.Builder()
                    .url("https://api2.branch.io/v1/url?url=https://f6rwa.app.link/mobile/sso&branch_key=key_live_mjZS3VGlwUcl8Hk1uRlWUhegqvjRvEtJ")
                    .get()
                    .addHeader("accept", "application/json")
                    .build();

            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Log.i("executed from OnStart", String.valueOf(response));
        }

    }



}