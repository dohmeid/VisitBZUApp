package com.example.visitbzu.features.faculties;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.visitbzu.R;
import com.example.visitbzu.features.faculties.facultyOfInformationTechnology.SaraIssaFacultyOfInformationTechnology;

import java.util.ArrayList;

public class SaraIssaFacultiesViewAll extends AppCompatActivity {

    ListView lsvView;
    Button btnBack;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sara_issa_faculties_view_all);

        lsvView = findViewById(R.id.lsvView);
        btnBack = findViewById(R.id.btnBack);

        ArrayList<String> arr = new ArrayList<>();

        arr.add("Faculty of Information Technology");
        arr.add("Faculty of Education Building");
        arr.add("Faulty of Law and Public Administration");
        arr.add("Faculty of Science and Mathematics Wing");
        arr.add("Faculty of Business and Economics");
        arr.add("Faculty of Pharmacy, Nursing and Health Professions");
        arr.add("Faculty of Arts");
        arr.add("Faculty of Women's Studies");
        arr.add("Faculty of Graduate Studies");
        arr.add("Faculty of Business and Economics");
        arr.add("Faculty of Engineering");
        arr.add("Faculty of Science Building");

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), R.layout.sara_issa_view_item, arr);
        lsvView.setAdapter(adapter);

        lsvView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0: intent = new Intent(SaraIssaFacultiesViewAll.this, SaraIssaFacultyOfInformationTechnology.class);
                            startActivity(intent);
                            break;
                }
            }
        });

        btnBack.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                intent = new Intent(SaraIssaFacultiesViewAll.this, SaraIssaFaculties.class);
                startActivity(intent);
            }
        });
    }
}