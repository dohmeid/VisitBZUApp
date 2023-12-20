package com.example.visitbzu.features.faculties;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.visitbzu.HomePage;
import com.example.visitbzu.R;
import com.example.visitbzu.features.faculties.facultyOfInformationTechnology.SaraIssaFacultyOfInformationTechnology;

public class SaraIssaFaculties extends AppCompatActivity {

    private Button btn1, view_all, btnBack;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sara_issa_faculties);

        btn1 = findViewById(R.id.btn1);
        btnBack = findViewById(R.id.btnBack);
        view_all = findViewById(R.id.view_all);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SaraIssaFaculties.this, SaraIssaFacultyOfInformationTechnology.class);
                startActivity(intent);
            }
        });
        view_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SaraIssaFaculties.this, SaraIssaFacultiesViewAll.class);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                intent = new Intent(SaraIssaFaculties.this, HomePage.class);
                startActivity(intent);
            }
        });
    }
}