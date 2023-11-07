package com.example.visitbzu;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.visitbzu.features.faculties.SaraIssaFacultyOfInformationTechnology;
import com.example.visitbzu.features.faq.sararom_FAQs;
import com.example.visitbzu.features.libraries.SaraIssaLibraries;
import com.example.visitbzu.features.map.sararom_Map;
import com.example.visitbzu.features.prayers.SaraIssaPrayer;
import com.example.visitbzu.helpers.HistoryAdapter;
import com.example.visitbzu.helpers.SuggestionsAdapter;
import com.example.visitbzu.features.virtualTour.VirtualTour;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class HomePage extends AppCompatActivity {

    private FirebaseFirestore db;
    private FirebaseStorage storage;
    private StorageReference storageRef;
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
        db = FirebaseFirestore.getInstance();
        db.collection("HomePageData").document("HistoryRV").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    Map<String, Object> allInformation = doc.getData();
                    //to display the information in-order, sort the map keys
                    List keys = new ArrayList(allInformation.keySet());
                    Collections.sort(keys);
                    for (Object key : keys) {
                        dataSource.add(allInformation.get(key).toString());
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //print e.message();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomePage.this, LinearLayoutManager.HORIZONTAL, false);
        HistoryAdapter myRvAdapter = new HistoryAdapter(dataSource);
        historyRV.setLayoutManager(linearLayoutManager);
        historyRV.setAdapter(myRvAdapter);
        //PagerSnapHelper helper = new PagerSnapHelper();
        //helper.attachToRecyclerView(historyRV);
        //historyRV.addItemDecoration(new CirclePagerIndicatorDecoration());
    }

    private void suggestionsRecycler() {
        ArrayList<String> titlesData = new ArrayList<>();
        ArrayList<String> descData = new ArrayList<>();
        //ArrayList<Bitmap> imagesData = new ArrayList<>();
        ArrayList<String> imagesData = new ArrayList<>();

        // Create a Cloud Storage reference from the app
        storage = FirebaseStorage.getInstance();
        final long ONE_MEGABYTE = 1024 * 1024;

        db = FirebaseFirestore.getInstance();
        db.collection("HomePageData").document("SuggestionsRV").collection("Suggestions").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<String> list = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        list.add(document.getId());
                        titlesData.add(document.getString("title"));
                        descData.add(document.getString("description"));

                        String httpsReference = document.getString("imageRef");
                        imagesData.add(httpsReference);
                    /*    StorageReference file = storage.getReferenceFromUrl(httpsReference);
                        file.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                imagesData.add(bmp);
                            }
                        });
                        */

                    }
                    Log.d(TAG, list.toString());
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(HomePage.this, LinearLayoutManager.HORIZONTAL, false);
        SuggestionsAdapter myRvAdapter2 = new SuggestionsAdapter(titlesData, descData, imagesData);
        suggestionsRV.setLayoutManager(linearLayoutManager2);
        suggestionsRV.setAdapter(myRvAdapter2);
    }

    private void activateButtons() {

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

        //prayers feature
        prayersBtn = findViewById(R.id.prayersButton);
        prayersBtn.setOnClickListener(view -> {
            Intent i = new Intent(HomePage.this, SaraIssaPrayer.class);
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

        //faculties feature
        facultiesBtn = findViewById(R.id.facultiesButton);
        facultiesBtn.setOnClickListener(view -> {
            Intent i = new Intent(HomePage.this, SaraIssaFacultyOfInformationTechnology.class);
            startActivity(i);
            finish();
        });

        //virtualTour feature
        virTourBtn = findViewById(R.id.virtualTourButton);
        virTourBtn.setOnClickListener(view -> {
            Intent i = new Intent(HomePage.this, VirtualTour.class);
            startActivity(i);
            finish();
        });
    }

}