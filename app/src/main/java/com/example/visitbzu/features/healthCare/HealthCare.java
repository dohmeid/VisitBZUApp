package com.example.visitbzu.features.healthCare;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Toast;
import android.view.View;


import com.example.visitbzu.HomePage;
import com.example.visitbzu.R;
import com.example.visitbzu.features.virtualTour.VirtualTour;
import com.example.visitbzu.helpers.HistoryAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HealthCare extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    String language; //save the app language here
    HashMap<String, ArrayList<String>> englishServicesMap = new HashMap<>(); //maps the service title to its services
    HashMap<String, ArrayList<String>> englishTitlesMap = new HashMap<>();

    HashMap<String, ArrayList<String>> arabicServicesMap = new HashMap<>(); //maps the service title to its services
    HashMap<String, ArrayList<String>> arabicTitlesMap = new HashMap<>();



    ImageButton backBtn;
    RecyclerView aboutRV;
    Button medicineBtn, dentalBtn, ambulanceBtn, othersBtn, locationBtn, mailBtn, telephoneBtn, cellularBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doha_health_care);

        //get the selectedLanguage from SharedPreferences and print it to debug
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        language = sh.getString("AppLanguage", "");
        Log.d(TAG, "--------------language " + language);

        fetchDataFromFirestore();
        //the about section
        aboutRV = findViewById(R.id.aboutRV);
        aboutRecycler();

        //the provided Medical Services section
        medicineBtn = findViewById(R.id.medicineButton);
        dentalBtn = findViewById(R.id.dentalButton);
        ambulanceBtn = findViewById(R.id.ambulanceButton);
        othersBtn = findViewById(R.id.othersButton);

        //the Contact Info section
        locationBtn = findViewById(R.id.locationButton);
        mailBtn = findViewById(R.id.mailButton);
        telephoneBtn = findViewById(R.id.telephoneButton);
        cellularBtn = findViewById(R.id.cellularButton);

        activateButtons();
    }

    //activate all the buttons in this view (click listeners)
    private void activateButtons() {
        //go back to home page
        backBtn = findViewById(R.id.backButton);
        backBtn.setOnClickListener(view -> {
            Intent i = new Intent(HealthCare.this, HomePage.class);
            startActivity(i);
        });

        //the services buttons
        medicineBtn.setOnClickListener(view -> viewMedicalService(view));
        dentalBtn.setOnClickListener(view -> viewMedicalService(view));
        ambulanceBtn.setOnClickListener(view -> viewMedicalService(view));
        othersBtn.setOnClickListener(view -> viewMedicalService(view));
    }

    //add the selected service data and open a new intent
    private void viewMedicalService(View view) {
        Intent intent = new Intent(this, MedicalService.class);

        // Determine which button was clicked and pass an identifier
        int btn = view.getId();

        // Determine which button was clicked and pass an identifier
        if (language.equals("ar")) {
            if (btn == R.id.medicineButton) {
                intent.putExtra("serviceTitle", "عيادة الطب العام");
                intent.putStringArrayListExtra("servicesArrayList", arabicServicesMap.get("medicineButton"));
                intent.putStringArrayListExtra("titlesArrayList", arabicTitlesMap.get("medicineButton"));
            } else if (btn == R.id.dentalButton) {
                intent.putExtra("serviceTitle", "عيادة الأسنان");
                intent.putStringArrayListExtra("servicesArrayList", arabicServicesMap.get("dentalButton"));
                intent.putStringArrayListExtra("titlesArrayList", arabicTitlesMap.get("dentalButton"));
            } else if (btn == R.id.ambulanceButton) {
                intent.putExtra("serviceTitle", "الإسعاف والطوارئ");
                intent.putStringArrayListExtra("servicesArrayList", arabicServicesMap.get("ambulanceButton"));
                intent.putStringArrayListExtra("titlesArrayList", arabicTitlesMap.get("ambulanceButton"));
            } else {
                intent.putExtra("serviceTitle", "أخرى");
                intent.putStringArrayListExtra("servicesArrayList", arabicServicesMap.get("othersButton"));
                intent.putStringArrayListExtra("titlesArrayList", arabicTitlesMap.get("othersButton"));
            }
        } else {
            if (btn == R.id.medicineButton) {
                intent.putExtra("serviceTitle", "General Medicine Clinic");
                intent.putStringArrayListExtra("servicesArrayList", englishServicesMap.get("medicineButton"));
                intent.putStringArrayListExtra("titlesArrayList", englishTitlesMap.get("medicineButton"));
            } else if (btn == R.id.dentalButton) {
                intent.putExtra("serviceTitle", "Dental Clinic");
                intent.putStringArrayListExtra("servicesArrayList", englishServicesMap.get("dentalButton"));
                intent.putStringArrayListExtra("titlesArrayList", englishTitlesMap.get("dentalButton"));
            } else if (btn == R.id.ambulanceButton) {
                intent.putExtra("serviceTitle", "Ambulance and Emergency Services");
                intent.putStringArrayListExtra("servicesArrayList", englishServicesMap.get("ambulanceButton"));
                intent.putStringArrayListExtra("titlesArrayList", englishTitlesMap.get("ambulanceButton"));
            } else {
                intent.putExtra("serviceTitle", "Others");
                intent.putStringArrayListExtra("servicesArrayList", englishServicesMap.get("othersButton"));
                intent.putStringArrayListExtra("titlesArrayList", englishTitlesMap.get("othersButton"));
            }
        }

        startActivity(intent);
    }

    //fetch the about section data from firestore and add it to it's adapter
    private void aboutRecycler() {
        ArrayList<String> englishDataSource = new ArrayList<>();
        ArrayList<String> arabicDataSource = new ArrayList<>();

        //get the data from Firestore Storage
        db.collection("HealthCareData").document("EnglishAboutRV").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot doc = task.getResult();
                Map<String, Object> allInformation = doc.getData();
                List keys = new ArrayList(allInformation.keySet()); //to display the information in-order, sort the map keys
                Collections.sort(keys);
                for (Object key : keys) {
                    englishDataSource.add(allInformation.get(key).toString());
                }
            } else {
                Log.w(TAG, "Error getting documents.", task.getException());
            }
        }).addOnFailureListener(e -> Log.w(TAG, e.getMessage()));

        db.collection("HealthCareData").document("ArabicAboutRV").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot doc = task.getResult();
                Map<String, Object> allInformation = doc.getData();
                List keys = new ArrayList(allInformation.keySet()); //to display the information in-order, sort the map keys
                Collections.sort(keys);
                for (Object key : keys) {
                    arabicDataSource.add(allInformation.get(key).toString());
                }
            } else {
                Log.w(TAG, "Error getting documents.", task.getException());
            }
        }).addOnFailureListener(e -> Log.w(TAG, e.getMessage()));


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HealthCare.this, LinearLayoutManager.HORIZONTAL, false);
        AboutAdapter myRvAdapter;
        if (language.equals("ar")) {
            myRvAdapter = new AboutAdapter(arabicDataSource);
        } else {
            myRvAdapter = new AboutAdapter(englishDataSource);
        }

        aboutRV.setLayoutManager(linearLayoutManager);
        aboutRV.setAdapter(myRvAdapter);
    }

    private void fetchDataFromFirestore() {

        //get the data from Firestore Storage
        db.collection("HealthCareData").document("EnglishMedicalServices").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    Log.d(TAG, "1---------------------------heeeeeeeeeeeeeeeeeeeeeeeee");
                    if (document.exists()) {
                        Log.d(TAG, "3---------------------------heeeeeeeeeeeeeeeeeeeeeeeee");
                        Map<String, Object> allServices = document.getData();
                        List keys = new ArrayList(allServices.keySet());

                        for (Object key : keys) {
                            String title = key.toString();
                            String mapKey;
                            if (title.equals("dental_clinic")) {
                                mapKey = "dentalButton";
                            } else if (title.equals("medicine_clinic")) {
                                mapKey = "medicineButton";
                            } else if (title.equals("ambulance_and_emergency")) {
                                mapKey = "ambulanceButton";
                            } else {
                                mapKey = "othersButton";
                            }

                            ArrayList<String> services = new ArrayList<>();
                            ArrayList<String> titles = new ArrayList<>();
                            HashMap<String, Object> v = (HashMap<String, Object>) allServices.get(key);
                            List v2 = new ArrayList(v.keySet()); //to display the information in-order, sort the map keys
                            Collections.sort(v2);
                            for (Object value : v2) {
                                if(value.toString().contains("title"))
                                    titles.add(v.get(value).toString());
                                else
                                    services.add(v.get(value).toString());
                            }
                            englishServicesMap.put(mapKey, services);
                            englishTitlesMap.put(mapKey, titles);
                        }
                    }
                    for (Map.Entry<String, ArrayList<String>> entry : englishServicesMap.entrySet()) {
                        String mkey = entry.getKey();
                        ArrayList<String> values = entry.getValue();
                        Log.d(TAG, "keyy " + mkey);
                        Log.d(TAG, "Values: " + values);
                    }
                    for (Map.Entry<String, ArrayList<String>> entry : englishTitlesMap.entrySet()) {
                        String mkey = entry.getKey();
                        ArrayList<String> values = entry.getValue();
                        Log.d(TAG, "keyy " + mkey);
                        Log.d(TAG, "Values: " + values);
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        }).addOnFailureListener(e -> Log.w(TAG, e.getMessage()));

        db.collection("HealthCareData").document("ArabicMedicalServices").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String, Object> allServices = document.getData();
                        List keys = new ArrayList(allServices.keySet());

                        for (Object key : keys) {
                            String title = key.toString();
                            String mapKey;
                            if (title.equals("dental_clinic")) {
                                mapKey = "dentalButton";
                            } else if (title.equals("medicine_clinic")) {
                                mapKey = "medicineButton";
                            } else if (title.equals("ambulance_and_emergency")) {
                                mapKey = "ambulanceButton";
                            } else {
                                mapKey = "othersButton";
                            }

                            ArrayList<String> services = new ArrayList<>();
                            ArrayList<String> titles = new ArrayList<>();
                            HashMap<String, Object> v = (HashMap<String, Object>) allServices.get(key);
                            List v2 = new ArrayList(v.keySet()); //to display the information in-order, sort the map keys
                            Collections.sort(v2);
                            for (Object value : v2) {
                                if(value.toString().contains("title"))
                                    titles.add(v.get(value).toString());
                                else
                                    services.add(v.get(value).toString());
                            }
                            arabicServicesMap.put(mapKey, services);
                            arabicTitlesMap.put(mapKey, titles);
                        }
                    }
                    for (Map.Entry<String, ArrayList<String>> entry : arabicServicesMap.entrySet()) {
                        String mkey = entry.getKey();
                        ArrayList<String> values = entry.getValue();
                        Log.d(TAG, "keyy " + mkey);
                        Log.d(TAG, "Values: " + values);
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        }).addOnFailureListener(e -> Log.w(TAG, e.getMessage()));


    }

}

