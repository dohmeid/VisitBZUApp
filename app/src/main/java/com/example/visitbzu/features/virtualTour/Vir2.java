package com.example.visitbzu.features.virtualTour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.visitbzu.R;

public class Vir2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doha_vir2);

        // Create a new StreetViewFragment and add it to the container
        Virtual2 streetViewFragment = new Virtual2();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, streetViewFragment).commit();

    }
}