package com.example.formulaone;

import android.app.Application;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import io.branch.referral.Branch;

public class FormulaOneAndroidApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Branch.enableLogging();
        Branch.enableTestMode();
        Branch.getAutoInstance(this);

        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this, Integer.MAX_VALUE));
        Picasso built = builder.build();
        Picasso.setSingletonInstance(built);


    }

}
