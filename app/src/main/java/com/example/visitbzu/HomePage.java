package com.example.visitbzu;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.visitbzu.features.faculties.SaraIssaFacultyOfInformationTechnology;
import com.example.visitbzu.features.faq.sararom_FAQs;
import com.example.visitbzu.features.libraries.SaraIssaLibraries;
import com.example.visitbzu.features.map.sararom_Map;
import com.example.visitbzu.features.prayers.SaraIssaPrayer;
import com.example.visitbzu.features.virtualTour.VirtualTour;
import com.example.visitbzu.helpers.HistoryAdapter;
import com.example.visitbzu.helpers.SuggestionsAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePage extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    //SearchView searchView;
    //ListView listView;

    Button mapBtn, virTourBtn, faqsBtn, prayersBtn, facultiesBtn, museumsLibrariesBtn;
    RecyclerView historyRV, suggestionsRV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doha_home_page);

        historyRV = findViewById(R.id.historyRV);
        historyRecycler();
        suggestionsRV = findViewById(R.id.suggestionsRV);
        suggestionsRecycler();

        activateButtons();
    }

    private void historyRecycler() {
        ArrayList<String> dataSource = new ArrayList<>();

        //get the data from Firestore Storage
        db.collection("HomePageData").document("HistoryRV").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot doc = task.getResult();
                Map<String, Object> allInformation = doc.getData();
                List keys = new ArrayList(allInformation.keySet()); //to display the information in-order, sort the map keys
                Collections.sort(keys);
                for (Object key : keys) {
                    dataSource.add(allInformation.get(key).toString());
                }
            } else {
                Log.w(TAG, "Error getting documents.", task.getException());
            }
        }).addOnFailureListener(e -> Log.w(TAG, e.getMessage()));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomePage.this, LinearLayoutManager.HORIZONTAL, false);
        HistoryAdapter myRvAdapter = new HistoryAdapter(dataSource);
        historyRV.setLayoutManager(linearLayoutManager);
        historyRV.setAdapter(myRvAdapter);
        //PagerSnapHelper helper = new PagerSnapHelper();//helper.attachToRecyclerView(historyRV);//historyRV.addItemDecoration(new CirclePagerIndicatorDecoration());
    }

    private void suggestionsRecycler() {
        ArrayList<String> titlesData = new ArrayList<>();
        ArrayList<String> descData = new ArrayList<>();
        ArrayList<Bitmap> imagesData = new ArrayList<>();

        // Create a Cloud Storage reference from the app
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        final long ONE_MEGABYTE = 1024 * 1024;

        StorageReference listRef = storageRef.child("SuggestionsRV_Images");

       /* listRef.listAll().addOnCompleteListener(new OnCompleteListener<ListResult>() {
            @Override
            public void onComplete(@NonNull Task<ListResult> task) {

                ListResult listResult = task.getResult();
                for (StorageReference file : listResult.getItems()) {

                    file.getBytes(ONE_MEGABYTE).addOnCompleteListener(new OnCompleteListener<byte[]>() {
                        @Override
                        public void onComplete(@NonNull Task<byte[]> task) {
                            byte[] bytes = task.getResult();
                            Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            imagesData.add(bmp);

                        }
                    });
                }
            }
        });
        */

        //get the data from Firestore Storage
        db.collection("HomePageData").document("SuggestionsRV").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    Map<String, Object> allSuggestions = document.getData();
                    List keys = new ArrayList(allSuggestions.keySet()); //to display the information in-order, sort the map keys
                    Collections.sort(keys);
                    for (Object key : keys) {
                        HashMap<String, Object> v = (HashMap<String, Object>) allSuggestions.get(key);
                        titlesData.add(v.get("title").toString());
                        descData.add(v.get("description").toString());

                        String httpsReference = v.get("imageRef").toString();
                        StorageReference file = storage.getReferenceFromUrl(httpsReference);
                        file.getBytes(ONE_MEGABYTE).addOnCompleteListener(new OnCompleteListener<byte[]>() {
                            @Override
                            public void onComplete(@NonNull Task<byte[]> task) {
                                byte[] bytes = task.getResult();
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                imagesData.add(bmp);
                            }
                        });
                    }
                }
            } else {
                Log.w(TAG, "Error getting documents.", task.getException());
            }
        }).addOnFailureListener(e -> Log.w(TAG, e.getMessage()));


        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(HomePage.this, LinearLayoutManager.HORIZONTAL, false);
        SuggestionsAdapter myRvAdapter2 = new SuggestionsAdapter(titlesData, descData, imagesData);
        suggestionsRV.setLayoutManager(linearLayoutManager2);
        suggestionsRV.setAdapter(myRvAdapter2);
    }


    //This method is used to activate all features on their buttons in home page
    private void activateButtons() {

        //--------------------Sara Isam features
        //Map feature
        mapBtn = findViewById(R.id.mapButton);
        mapBtn.setOnClickListener(view -> {
            Intent i = new Intent(HomePage.this, sararom_Map.class);
            startActivity(i);
            finish();
        });

        //FAQs feature
        faqsBtn = findViewById(R.id.faqsButton);
        faqsBtn.setOnClickListener(view -> {
            Intent i = new Intent(HomePage.this, sararom_FAQs.class);
            startActivity(i);
            finish();
        });

        //--------------------Doha Hmeid features
        //virtualTour feature
        virTourBtn = findViewById(R.id.virtualTourButton);
        virTourBtn.setOnClickListener(view -> {
            Intent i = new Intent(HomePage.this, VirtualTour.class);
            startActivity(i);
            finish();
        });

        //--------------------Sara Issa features
        //prayers feature
        //faculties feature
        facultiesBtn = findViewById(R.id.facultiesButton);
        facultiesBtn.setOnClickListener(view -> {
            Intent i = new Intent(HomePage.this, SaraIssaFacultyOfInformationTechnology.class);
            startActivity(i);
            finish();
        });

        //museums & Libraries feature
        museumsLibrariesBtn = findViewById(R.id.museumsLibrariesButton);
        museumsLibrariesBtn.setOnClickListener(view -> {
            Intent i = new Intent(HomePage.this, SaraIssaLibraries.class);
            startActivity(i);
            finish();
        });

        prayersBtn = findViewById(R.id.prayersButton);
        prayersBtn.setOnClickListener(view -> {
            Intent i = new Intent(HomePage.this, SaraIssaPrayer.class);
            startActivity(i);
            finish();
        });
    }

}