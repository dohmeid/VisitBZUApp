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

public class YusufAhmadAlGhanimLibrary extends AppCompatActivity {

    private FirebaseFirestore db;
    DocumentReference docRef;
    private TextView name, founder, description, description1, description2, description3, description4;
    private Button location;
    private Intent intent;
    private static final String TAG = "Read Data Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yusuf_ahmad_alghanim_library);

        db = FirebaseFirestore.getInstance();

        name = findViewById(R.id.title);
        founder = findViewById(R.id.founder);
        description = findViewById(R.id.description);
        description1 = findViewById(R.id.description1);
        description2 = findViewById(R.id.description2);
        description3 = findViewById(R.id.description3);
        description4 = findViewById(R.id.description4);
        location = findViewById(R.id.location);

        docRef = db.collection("InformationAboutBuildings").document("YusufAhmadAlGhanimLibrary");
        fetchLibraryData(docRef, name, founder, description, description1, description2, description3, description4);

        location.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                intent = new Intent(YusufAhmadAlGhanimLibrary.this, sararom_Map.class);
                startActivity(intent);
            }
        });
    }

    private void fetchLibraryData(DocumentReference docRef, TextView name, TextView founder, TextView description, TextView description1,
    TextView description2, TextView description3, TextView description4) {
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        name.setText(document.getString("name"));
                        founder.setText(document.getString("founder"));
                        description.setText(document.getString("description"));
                        description1.setText(document.getString("description1"));
                        description2.setText(document.getString("description2"));
                        description3.setText(document.getString("description3"));
                        description4.setText(document.getString("description4"));
                        Log.d(TAG, document.getString("name"));
                        Log.d(TAG, document.getString("founder"));
                        Log.d(TAG, document.getString("description"));
                        Log.d(TAG, document.getString("description1"));
                        Log.d(TAG, document.getString("description2"));
                        Log.d(TAG, document.getString("description3"));
                        Log.d(TAG, document.getString("description4"));
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