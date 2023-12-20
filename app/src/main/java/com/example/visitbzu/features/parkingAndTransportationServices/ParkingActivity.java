package com.example.visitbzu.features.parkingAndTransportationServices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.visitbzu.HomePage;
import com.example.visitbzu.R;
import com.example.visitbzu.features.librariesandmuseums.BirzeitUniversityMuseum;
import com.example.visitbzu.features.librariesandmuseums.HistoryBirzeitUniversityMuseum;
import com.example.visitbzu.features.map.sararom_Map;

public class ParkingActivity extends AppCompatActivity {
    private Button btnBack, parkingBtn, busBtn, taxiBtn, mapBtn;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);

        btnBack = findViewById(R.id.btnBack);
        parkingBtn = findViewById(R.id.parkingBtn);
        busBtn = findViewById(R.id.busBtn);
        taxiBtn = findViewById(R.id.taxiBtn);
        mapBtn = findViewById(R.id.mapBtn);

        // Back Button
        btnBack.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                intent = new Intent(ParkingActivity.this, HomePage.class);
                startActivity(intent);
            }
        });

        // Parking Button
        parkingBtn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                intent = new Intent(ParkingActivity.this, CarParkingActivity.class);
                startActivity(intent);
            }
        });

        // Bus Button
        busBtn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                intent = new Intent(ParkingActivity.this, BusActivity.class);
                startActivity(intent);
            }
        });

        // Taxi Button
        taxiBtn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                intent = new Intent(ParkingActivity.this, TaxiActivity.class);
                startActivity(intent);
            }
        });

        // Map Button
        mapBtn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                intent = new Intent(ParkingActivity.this, sararom_Map.class);
                startActivity(intent);
            }
        });
    }
}