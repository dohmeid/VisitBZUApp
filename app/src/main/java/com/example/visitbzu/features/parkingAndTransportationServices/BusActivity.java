package com.example.visitbzu.features.parkingAndTransportationServices;

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

public class BusActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    DocumentReference docRef1, docRef2, docRef3, docRef4;
    private static final String TAG = "Read Data Activity";
    private Button btnBack;
    private Intent intent;

    // An array of IDs
    int[] descriptionIds = { R.id.descriptionValue1, R.id.descriptionValue2, R.id.descriptionValue3, R.id.descriptionValue4 };

    int[] travelTimeIds = { R.id.travelTimeValue1, R.id.travelTimeValue2, R.id.travelTimeValue3, R.id.travelTimeValue4 };

    int[] costIds = { R.id.CostValue1, R.id.CostValue2, R.id.CostValue3, R.id.CostValue4 };

    TextView[] descriptions =  new TextView[4];
    TextView[] travelTimes =  new TextView[4];
    TextView[] costs =  new TextView[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        db = FirebaseFirestore.getInstance();

        docRef1 = db.collection("Parking").document("FromRamallahToTheUniversity");
        docRef2 = db.collection("Parking").document("FromTheUniversityToBirzeit");
        docRef3 = db.collection("Parking").document("FromTheUniversityToRamallah");
        docRef4 = db.collection("Parking").document("FromTheUniversityToRamallah1");

        btnBack = findViewById(R.id.btnBack);

        for (int i = 0; i < descriptions.length; i++) {
            descriptions[i] = findViewById(descriptionIds[i]);
        }

        for (int i = 0; i < travelTimes.length; i++) {
            travelTimes[i] = findViewById(travelTimeIds[i]);
        }

        for (int i = 0; i < costs.length; i++) {
            costs[i] = findViewById(costIds[i]);
        }

        fetchData(docRef1, descriptions[0], travelTimes[0], costs[0]);
        fetchData(docRef2, descriptions[1], travelTimes[1], costs[1]);
        fetchData(docRef3, descriptions[2], travelTimes[2], costs[2]);
        fetchData(docRef4, descriptions[3], travelTimes[3], costs[3]);

        btnBack.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                intent = new Intent(BusActivity.this, ParkingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void fetchData(DocumentReference docRef, TextView descriptionTextView, TextView travelTimeTextView, TextView costTextView) {
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        descriptionTextView.setText(document.getString("Description"));
                        travelTimeTextView.setText(document.getString("TravelTime"));
                        costTextView.setText(document.getString("Cost"));
                        Log.d(TAG, document.getString("Description"));
                        Log.d(TAG, document.getString("TravelTime"));
                        Log.d(TAG, document.getString("Cost"));
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