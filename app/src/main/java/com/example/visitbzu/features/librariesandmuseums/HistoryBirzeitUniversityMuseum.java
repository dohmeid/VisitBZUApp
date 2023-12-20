package com.example.visitbzu.features.librariesandmuseums;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.visitbzu.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class HistoryBirzeitUniversityMuseum extends AppCompatActivity {

    private FirebaseFirestore db;
    DocumentReference docRef;
    private TextView description;
    private Button btnBack;
    private Intent intent;
    private static final String TAG = "Read Data Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_birzeit_university_museum);

        db = FirebaseFirestore.getInstance();

        description = findViewById(R.id.description);
        btnBack = findViewById(R.id.btnBack);

        docRef = db.collection("InformationAboutBuildings").document("BirzeitUniversityMuseum");
        fetchData(docRef, description);

        btnBack.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                intent = new Intent(HistoryBirzeitUniversityMuseum.this, BirzeitUniversityMuseum.class);
                startActivity(intent);
            }
        });
    }

    private void fetchData(DocumentReference docRef, TextView description) {
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        description.setText(document.getString("description"));
                        Log.d(TAG, document.getString("description"));
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