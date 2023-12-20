package com.example.visitbzu.features.librariesandmuseums;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.visitbzu.R;
import com.example.visitbzu.features.map.sararom_Map;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AboutBirzeitUniversityMuseum extends AppCompatActivity {

    private FirebaseFirestore db;
    DocumentReference docRef;
    private TextView name, description, description1;
    private Button btnBack, location;
    private Intent intent;
    private static final String TAG = "Read Data Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_birzeit_university_museum);

        db = FirebaseFirestore.getInstance();

        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        description1 = findViewById(R.id.description1);
        btnBack = findViewById(R.id.btnBack);
        location = findViewById(R.id.location);

        docRef = db.collection("InformationAboutBuildings").document("BirzeitUniversityMuseum");
        fetchData(docRef, name, description, description1);

        location.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                intent = new Intent(AboutBirzeitUniversityMuseum.this, sararom_Map.class);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                intent = new Intent(AboutBirzeitUniversityMuseum.this, BirzeitUniversityMuseum.class);
                startActivity(intent);
            }
        });
    }

    private void fetchData(DocumentReference docRef, TextView name, TextView description, TextView description1) {
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        name.setText(document.getString("name"));
                        description.setText(document.getString("description"));
                        description1.setText(document.getString("description1"));
                        Log.d(TAG, document.getString("name"));
                        Log.d(TAG, document.getString("description"));
                        Log.d(TAG, document.getString("description1"));
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
}