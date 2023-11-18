package com.example.visitbzu.features.virtualTour;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.visitbzu.R;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;

public class ApiVirtualTour extends AppCompatActivity implements OnStreetViewPanoramaReadyCallback {

    private SupportStreetViewPanoramaFragment streetViewPanoramaFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doha_api_virtual_tour);

        streetViewPanoramaFragment = (SupportStreetViewPanoramaFragment) getSupportFragmentManager().findFragmentById(R.id.streetViewMap);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);

     /*   Bundle streetViewBundle = null;
        if (savedInstanceState != null)
            streetViewBundle = savedInstanceState.getBundle(STREET_VIEW_BUNDLE);
        streetViewPanoramaFragment.onCreate(streetViewBundle);*/

      /*  StreetViewPanoramaFragment streetViewPanoramaFragment =
                (StreetViewPanoramaFragment) getFragmentManager()
                        .findFragmentById(R.id.streetViewMap);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);*/

    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
       /* this.streetViewPanorama = streetViewPanorama;
        this.streetViewPanorama.setPosition(new LatLng(-33.873398, 150.976744));
        this.streetViewPanorama.setOnStreetViewPanoramaChangeListener(streetViewPanoramaChangeListener);
        this.streetViewPanorama.setOnStreetViewPanoramaClickListener(streetViewPanoramaClickListener);*/

        streetViewPanorama.setPosition(new LatLng(46.4876132818091, 30.727231876007));
    }






}