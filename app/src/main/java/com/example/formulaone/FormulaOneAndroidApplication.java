package com.example.formulaone;

import android.app.Application;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import io.branch.referral.Branch;

public class FormulaOneAndroidApplication extends Application {
    FirebaseAnalytics mFirebaseAnalytics;



    @Override
    public void onCreate() {
        super.onCreate();



        Branch.enableLogging();
        Branch.getAutoInstance(this);

        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this, Integer.MAX_VALUE));
        Picasso built = builder.build();
        Picasso.setSingletonInstance(built);


    }

}
