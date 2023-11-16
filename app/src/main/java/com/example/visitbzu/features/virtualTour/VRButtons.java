package com.example.visitbzu.features.virtualTour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import com.example.visitbzu.HomePage;
import com.example.visitbzu.R;
import com.example.visitbzu.WelcomeScreen;

public class VRButtons extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4,btn5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doha_vr_buttons);

        btn1 = findViewById(R.id.button1);
        btn1.setOnClickListener(view -> {
            Intent i = new Intent(VRButtons.this, VirtualTour.class);
            startActivity(i);
            finish();
        });

        btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(view -> {
            Intent i = new Intent(VRButtons.this, Vir2.class);
            startActivity(i);
            finish();
        });

        btn3 = findViewById(R.id.button3);
        btn3.setOnClickListener(view -> {
            Intent i = new Intent(VRButtons.this, Virtual3.class);
            startActivity(i);
            finish();
        });

        btn4 = findViewById(R.id.button4);
        btn4.setOnClickListener(view -> {
            Intent i = new Intent(VRButtons.this, Virtual4.class);
            startActivity(i);
            finish();
        });

        btn5 = findViewById(R.id.button5);
        btn5.setOnClickListener(view -> {
            Intent i = new Intent(VRButtons.this, menu.class);
            startActivity(i);
            finish();
        });
    }
}