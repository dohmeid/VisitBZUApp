package com.example.visitbzu.features.virtualTour;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.example.visitbzu.R;

public class VirtualTour extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.virtual_tour);

        WebView myWebView = (WebView) findViewById(R.id.website);
        setContentView(myWebView);
        myWebView.loadUrl("https://www.birzeit.edu/sites/all/themes/vrt/");


    }
}