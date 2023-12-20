package com.example.visitbzu.features.parkingAndTransportationServices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.visitbzu.HomePage;
import com.example.visitbzu.R;
import com.example.visitbzu.socialmedia.Phone;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class TaxiActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    DocumentReference docRef1, docRef2, docRef3;
    private static final String TAG = "Read Data Activity";
    private Button btnBack, callBtn1, callBtn2, callBtn3;
    private TextView nameValue1, nameValue2, nameValue3, addressValue1 ,addressValue2 ,addressValue3;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxi);

        db = FirebaseFirestore.getInstance();

        docRef1 = db.collection("Parking").document("Taxi1");
        docRef2 = db.collection("Parking").document("Taxi2");
        docRef3 = db.collection("Parking").document("Taxi3");

        btnBack = findViewById(R.id.btnBack);

        nameValue1 = findViewById(R.id.nameValue1);
        nameValue2 = findViewById(R.id.nameValue2);
        nameValue3 = findViewById(R.id.nameValue3);

        addressValue1 = findViewById(R.id.addressValue1);
        addressValue2 = findViewById(R.id.addressValue2);
        addressValue3 = findViewById(R.id.addressValue3);

        callBtn1 = findViewById(R.id.callBtn1);
        callBtn2 = findViewById(R.id.callBtn2);
        callBtn3 = findViewById(R.id.callBtn3);

        String[] stringNumber1 = new String[1];
        fetchData(docRef1, nameValue1, addressValue1, stringNumber1);

        String[] stringNumber2 = new String[1];
        fetchData(docRef2, nameValue2, addressValue2, stringNumber2);

        String[] stringNumber3 = new String[1];
        fetchData(docRef3, nameValue3, addressValue3, stringNumber3);

        // Back Button
        btnBack.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                intent = new Intent(TaxiActivity.this, ParkingActivity.class);
                startActivity(intent);
            }
        });

        // Call Button
        callBtn1.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Phone.makePhoneCall(TaxiActivity.this, stringNumber1[0]);
            }
        });
        callBtn2.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Phone.makePhoneCall(TaxiActivity.this, stringNumber2[0]);
            }
        });
        callBtn3.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Phone.makePhoneCall(TaxiActivity.this, stringNumber2[0]);
            }
        });
    }

    private void fetchData(DocumentReference docRef, TextView nameTextView, TextView addressTextView, String[] phoneNumber) {
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        nameTextView.setText(document.getString("Name"));
                        addressTextView.setText(document.getString("Address"));
                        phoneNumber[0] = document.getString("PhoneNumber");
                        Log.d(TAG, document.getString("Name"));
                        Log.d(TAG, document.getString("Address"));
                        Log.d(TAG, document.getString("PhoneNumber"));
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