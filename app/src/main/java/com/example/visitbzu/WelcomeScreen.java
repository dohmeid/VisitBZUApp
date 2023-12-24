package com.example.visitbzu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import com.example.visitbzu.helpers.LocaleHelper;
import com.google.android.material.button.MaterialButton;

import java.util.Locale;

public class WelcomeScreen extends AppCompatActivity {

    MaterialButton arabicBtn, englishBtn;
    Button nextBtn;
    String selectedLanguage = "";
    Context context;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doha_welcome_screen);

        nextBtn = findViewById(R.id.nextButton);
        arabicBtn = findViewById(R.id.arButton);
        englishBtn = findViewById(R.id.enButton);

        //by default, arabic language is selected
        //arabicBtn.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#2B581C"))); //selected button

        arabicBtn.setOnClickListener(view -> {
            //  setAppLanguage(this,"ar");
            context = LocaleHelper.setLocale(WelcomeScreen.this, "ar");
            resources = context.getResources();
            saveLanguageInSharedPreference("ar");

            arabicBtn.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#2B581C"))); //selected button
            englishBtn.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#66A8CC9B"))); //not selected button
        });

        englishBtn.setOnClickListener(view -> {
            context = LocaleHelper.setLocale(WelcomeScreen.this, "en");
            resources = context.getResources();

            saveLanguageInSharedPreference("en");


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

    public void saveLanguageInSharedPreference(String selectedLanguage){
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        //write the selectedLanguage by the user in SharedPreference and apply
        myEdit.putString("AppLanguage", selectedLanguage);
        myEdit.apply();
    }

    public void setAppLanguage(Activity activity, String language){
        Locale locale = new Locale(language);
        Resources resources = activity.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration,resources.getDisplayMetrics());
    }
}