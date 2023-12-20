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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class CarParkingActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    DocumentReference docRef1, docRef2, docRef3, docRef4, docRef5, docRef6, docRef7, docRef8, docRef9, docRef10, docRef11, docRef12, docRef13, docRef14, docRef15, docRef16, docRef17, docRef18, docRef19, docRef20, docRef21, docRef22, docRef23, docRef24, docRef25, docRef26, docRef27;
    private static final String TAG = "Read Data Activity";
    private Button btnBack;
    private TextView total, total1;
    private Intent intent;

    // An array of IDs
    int[] numIds = {
            R.id.num1, R.id.num2, R.id.num3, R.id.num4, R.id.num5,
            R.id.num6, R.id.num7, R.id.num8, R.id.num9, R.id.num10,
            R.id.num11, R.id.num12, R.id.num13, R.id.num14, R.id.num15,
            R.id.num16, R.id.num17, R.id.num18, R.id.num19, R.id.num20,
            R.id.num21, R.id.num22, R.id.num23, R.id.num24, R.id.num25
    };

    int[] locationIds = {
            R.id.location1, R.id.location2, R.id.location3, R.id.location4, R.id.location5,
            R.id.location6, R.id.location7, R.id.location8, R.id.location9, R.id.location10,
            R.id.location11, R.id.location12, R.id.location13, R.id.location14, R.id.location15,
            R.id.location16, R.id.location17, R.id.location18, R.id.location19, R.id.location20,
            R.id.location21, R.id.location22, R.id.location23, R.id.location24, R.id.location25
    };

    TextView[] numbers =  new TextView[25];
    TextView[] locations =  new TextView[25];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_parking);

        db = FirebaseFirestore.getInstance();

        docRef1 = db.collection("Parking").document("P1");
        docRef2 = db.collection("Parking").document("P2");
        docRef3 = db.collection("Parking").document("P3");
        docRef4 = db.collection("Parking").document("P4");
        docRef5 = db.collection("Parking").document("P5");
        docRef6 = db.collection("Parking").document("P6");
        docRef7 = db.collection("Parking").document("P7");
        docRef8 = db.collection("Parking").document("P8");
        docRef9 = db.collection("Parking").document("P9");
        docRef10 = db.collection("Parking").document("P10");
        docRef11 = db.collection("Parking").document("P11");
        docRef12 = db.collection("Parking").document("P12");
        docRef13 = db.collection("Parking").document("P13");
        docRef14 = db.collection("Parking").document("P14");
        docRef15 = db.collection("Parking").document("P15");
        docRef16 = db.collection("Parking").document("P16");
        docRef17 = db.collection("Parking").document("P17");
        docRef18 = db.collection("Parking").document("P18");
        docRef19 = db.collection("Parking").document("P19");
        docRef20 = db.collection("Parking").document("P20");
        docRef21 = db.collection("Parking").document("P21");
        docRef22 = db.collection("Parking").document("P22");
        docRef23 = db.collection("Parking").document("P23");
        docRef24 = db.collection("Parking").document("P24");
        docRef25 = db.collection("Parking").document("P25");

        docRef26 = db.collection("Parking").document("GrandTotal");
        docRef27 = db.collection("Parking").document("GrandTotal");

        btnBack = findViewById(R.id.btnBack);

        total = findViewById(R.id.total);
        total1 = findViewById(R.id.total1);

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = findViewById(numIds[i]);
        }

        for (int i = 0; i < locations.length; i++) {
            locations[i] = findViewById(locationIds[i]);
        }

        fetchData(docRef1, numbers[0], locations[0]);
        fetchData(docRef2, numbers[1], locations[1]);
        fetchData(docRef3, numbers[2], locations[2]);
        fetchData(docRef4, numbers[3], locations[3]);
        fetchData(docRef5, numbers[4], locations[4]);
        fetchData(docRef6, numbers[5], locations[5]);
        fetchData(docRef7, numbers[6], locations[6]);
        fetchData(docRef8, numbers[7], locations[7]);
        fetchData(docRef9, numbers[8], locations[8]);
        fetchData(docRef10, numbers[9], locations[9]);
        fetchData(docRef11, numbers[10], locations[10]);
        fetchData(docRef12, numbers[11], locations[11]);
        fetchData(docRef13, numbers[12], locations[12]);
        fetchData(docRef14, numbers[13], locations[13]);
        fetchData(docRef15, numbers[14], locations[14]);
        fetchData(docRef16, numbers[15], locations[15]);
        fetchData(docRef17, numbers[16], locations[16]);
        fetchData(docRef18, numbers[17], locations[17]);
        fetchData(docRef19, numbers[18], locations[18]);
        fetchData(docRef20, numbers[19], locations[19]);
        fetchData(docRef21, numbers[20], locations[20]);
        fetchData(docRef22, numbers[21], locations[21]);
        fetchData(docRef23, numbers[22], locations[22]);
        fetchData(docRef24, numbers[23], locations[23]);
        fetchData(docRef25, numbers[24], locations[24]);

        fetchTotalNum(docRef26, total, "PrivateValue");
        fetchTotalNum(docRef27, total1, "PublicValue");

        btnBack.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                intent = new Intent(CarParkingActivity.this, ParkingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void fetchData(DocumentReference docRef, TextView titleTextView, TextView typeTextView) {
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        titleTextView.setText(document.getString("NumberOfCars"));
                        typeTextView.setText(document.getString("Location"));
                        Log.d(TAG, document.getString("NumberOfCars"));
                        Log.d(TAG, document.getString("Location"));
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    private void fetchTotalNum(DocumentReference docRef, TextView textView, String s) {
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        textView.setText(document.getString(s));
                        Log.d(TAG, document.getString(s));
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