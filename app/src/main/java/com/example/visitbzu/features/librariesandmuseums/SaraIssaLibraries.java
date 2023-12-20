package com.example.visitbzu.features.librariesandmuseums;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.visitbzu.R;

public class SaraIssaLibraries extends AppCompatActivity {
    private Button btn1, btn2, btn3, view_all, btnBack;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sara_issa_activity_libraries);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btnBack = findViewById(R.id.btnBack);
        view_all = findViewById(R.id.view_all);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaraIssaLibraries.this, YusufAhmadAlGhanimLibrary.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaraIssaLibraries.this, SaidKhouryLibraryForDevelopmentStudies.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaraIssaLibraries.this, TheInstituteOfLawLibrary.class);
                startActivity(intent);
            }
        });

        view_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaraIssaLibraries.this, SaraIssaLibrariesViewAll.class);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                intent = new Intent(SaraIssaLibraries.this, LibrariesAndMuseumsViewAll.class);
                startActivity(intent);
            }
        });
    }
}