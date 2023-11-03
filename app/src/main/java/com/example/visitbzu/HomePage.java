package com.example.visitbzu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.visitbzu.features.faculties.SaraIssaFacultyOfInformationTechnology;
import com.example.visitbzu.features.faq.sararom_FAQs;
import com.example.visitbzu.features.libraries.SaraIssaLibraries;
import com.example.visitbzu.features.map.sararom_Map;
import com.example.visitbzu.features.prayers.SaraIssaPrayer;
import com.example.visitbzu.helpers.HistoryAdapter;
import com.example.visitbzu.helpers.SuggestionsAdapter;
import com.example.visitbzu.features.virtualTour.VirtualTour;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

    FirebaseFirestore db;
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

/*
    private void readDatabase() {  //READ DATA 1.using the firebase console 2.using get() method
        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });
    }
*/

    private void historyRecycler() {
        ArrayList<String> dataSource;

        //Setting the data source
        dataSource = new ArrayList<>();
        dataSource.add("Over a century, what began as a small girls’ school in Birzeit town has become the most prestigious Palestinian university, transforming Palestinian higher education through its impact on community awareness, culture, and resistance."
                + "\n"
                + "Birzeit University has been a thorn in the side of the occupation, insisting on playing its role of enlightenment and creating a multicultural Palestinian society on the campus grounds.");

        db = FirebaseFirestore.getInstance();
        db.collection("HomePageData").document("/HistoryRV").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                //City city = documentSnapshot.toObject(City.class);
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

        ArrayList<String> titlesData;
        ArrayList<String> descData;
        ArrayList<Integer> imagesData = new ArrayList<>();
        imagesData.add(R.drawable.doha_photo_pal_museum);
        imagesData.add(R.drawable.doha_photo_najjad_zeenni);
        imagesData.add(R.drawable.doha_photo_students_break);

        //Setting the data source
        titlesData = new ArrayList<>();
        titlesData.add("The Palestinian Museum");
        titlesData.add("Najjad Zeenni IT of excellence building");
        titlesData.add("Students Break Area");

        descData = new ArrayList<>();
        descData.add("A Non-Governmental Association promotes Palestinian culture, presents new narratives, and provides creative spaces for educational programs and innovative research, fostering a vibrant local and international community.");
        descData.add("Also known as the (Blue Dome) building. It is an innovation space within Birzeit University designed to nurture ideas and creativity and provide an environment which is conducive to creating new businesses or developing existing ones by providing a unique and highly flexible combination of business development processes, people, academia and infrastructure.");
        descData.add("A Non-Governmental Association promotes Palestinian culture, presents new narratives, and provides creative spaces for educational programs and innovative research, fostering a vibrant local and international community.");

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