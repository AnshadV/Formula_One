package com.example.formulaone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button button;
    ListView driverList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        driverList = findViewById(R.id.driverlist);
        DriverDataService driverDataService = new DriverDataService(MainActivity.this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                driverDataService.driverByYear(2022, new DriverDataService.VolleyResponseListener() {

                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<DriverModel> drivers) {
                        Toast.makeText(MainActivity.this, "Success call", Toast.LENGTH_SHORT).show();
                        Log.i("TAG", "onResponse: " + drivers.size());
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, drivers);
                        driverList.setAdapter(arrayAdapter);

                    }
                });
            }

        });

    }
}