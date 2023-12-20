package com.example.visitbzu.features.librariesandmuseums;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.visitbzu.R;

import java.util.ArrayList;

public class SaraIssaLibrariesViewAll extends AppCompatActivity {
    ListView lsvView;
    Button btnBack;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sara_issa_libraries_view_all);
        lsvView = findViewById(R.id.lsvView);
        btnBack = findViewById(R.id.btnBack);

        ArrayList<String> arr = new ArrayList<>();

        arr.add("Yusuf Ahmad Al Ghanim Library");
        arr.add("Said Khoury Library For Development Studies");
        arr.add("The Institute Of Law Library");

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), R.layout.sara_issa_view_item, arr);
        lsvView.setAdapter(adapter);

        lsvView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0: intent = new Intent(SaraIssaLibrariesViewAll.this, YusufAhmadAlGhanimLibrary.class);
                        startActivity(intent);
                        break;
                    case 1: intent = new Intent(SaraIssaLibrariesViewAll.this, SaidKhouryLibraryForDevelopmentStudies.class);
                        startActivity(intent);
                        break;
                    case 2: intent = new Intent(SaraIssaLibrariesViewAll.this, TheInstituteOfLawLibrary.class);
                        startActivity(intent);
                        break;
                }
            }
        });

        btnBack.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                intent = new Intent(SaraIssaLibrariesViewAll.this, SaraIssaLibraries.class);
                startActivity(intent);
            }
        });
    }
}