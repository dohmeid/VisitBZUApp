package com.example.visitbzu.features.faculties;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.visitbzu.R;

public class SaraIssaComputerScience extends AppCompatActivity {

    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sara_issa_activity_computer_science);

        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaraIssaComputerScience.this, SaraIssaFacultyOfInformationTechnology.class);
                startActivity(intent);
            }
        });
    }
}