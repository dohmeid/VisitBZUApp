package com.example.visitbzu.features.librariesandmuseums;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.visitbzu.R;

public class SaraIssaMuseums extends AppCompatActivity {
    private Button btn1, view_all, btnBack;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sara_issa_museums);

        btn1 = findViewById(R.id.btn1);
        btnBack = findViewById(R.id.btnBack);
        view_all = findViewById(R.id.view_all);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaraIssaMuseums.this, BirzeitUniversityMuseum.class);
                startActivity(intent);
            }
        });
        view_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaraIssaMuseums.this, SaraIssaLibrariesViewAll.class);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                intent = new Intent(SaraIssaMuseums.this, LibrariesAndMuseumsViewAll.class);
                startActivity(intent);
            }
        });
    }
}