package com.example.visitbzu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;

public class WelcomeScreen extends AppCompatActivity {

    MaterialButton arabicBtn, englishBtn;
    Button nextBtn;
    String selectedLanguage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doha_welcome_screen);

        nextBtn = findViewById(R.id.nextButton);
        arabicBtn = findViewById(R.id.arButton);
        englishBtn = findViewById(R.id.enButton);

        //by default, arabic language is selected
        arabicBtn.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#2B581C"))); //selected button

        arabicBtn.setOnClickListener(view -> {
            selectedLanguage = "ar";
            arabicBtn.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#2B581C"))); //selected button
            englishBtn.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#66A8CC9B"))); //not selected button
        });

        englishBtn.setOnClickListener(view -> {
            selectedLanguage = "en";
            englishBtn.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#2B581C"))); //selected button
            arabicBtn.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#66A8CC9B"))); //not selected button
        });

        nextBtn.setOnClickListener(view -> {
            // Intent i = new Intent(WelcomeScreen.this, HomePage.class);
            Intent i = new Intent(WelcomeScreen.this, HomePage.class);
            startActivity(i);
            finish();
        });
    }
}