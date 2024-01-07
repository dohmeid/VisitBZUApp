package com.example.visitbzu.features.healthCare;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.visitbzu.HomePage;
import com.example.visitbzu.R;
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

public class MedicalService extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    String language; //save the app language here

    ImageButton backBtn;
    ListView listView;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doha_health_care_medical_service);

        //get the selectedLanguage from SharedPreferences and print it to debug
        /*  SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        language = sh.getString("AppLanguage", "");
        Log.d(TAG, "--------------language " + language);
        */

        Intent intent = getIntent();

        //set the page title
        String serviceTitle = intent.getStringExtra("serviceTitle");
        title = findViewById(R.id.title);
        if (title != null) {
            title.setText(serviceTitle);
        }


        //set the list items/services
        ArrayList<String> titles = intent.getStringArrayListExtra("titlesArrayList");
        ArrayList<String> services = intent.getStringArrayListExtra("servicesArrayList");

        ArrayList<MedicalServiceItem> items = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            MedicalServiceItem item = new MedicalServiceItem(titles.get(i),services.get(i));
            items.add(item);
        }


        listView = findViewById(R.id.listView);
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, services);
       // ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.doha_health_care_medical_service_list_item, services);
        MedicalServiceAdapter adapter = new MedicalServiceAdapter(this, items);
        listView.setAdapter(adapter);


        //go back to HealthCare page
        backBtn = findViewById(R.id.backButton);
        backBtn.setOnClickListener(view -> {
            Intent i = new Intent(MedicalService.this, HealthCare.class);
            startActivity(i);
        });

    }
}