package com.example.visitbzu.features.virtualTour;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;


import com.example.visitbzu.HomePage;
import com.example.visitbzu.R;
import com.example.visitbzu.features.faq.sararom_FAQs;
import com.example.visitbzu.helpers.HistoryAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class VirtualTour extends AppCompatActivity {

    private static final String TAG = "VirtualTour";
    private int lastClickedPosition = -1; //variable to store the last clicked item from the listView

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    String language; //save the app language here

    ListView tourList;
    ToggleButton descriptionBtn, backBtn;
    TextView descriptionText;
    ScrollView descriptionView;
    VrPanoramaView virtualView;

    //initialize lists
    ArrayList<Building> buildingList = new ArrayList<>();
    ListAdapter adapter;
    ArrayList<String> downloadUrls = new ArrayList<>();
    ArrayList<String> imageNames = new ArrayList<>(); //ArrayList to store image names
    boolean isSorted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doha_virtual_tour);

        //get the selectedLanguage from SharedPreferences and print it to debug
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        language = sh.getString("AppLanguage", "");
        Log.d(TAG, "--------------language " + language);


        virtualView = (VrPanoramaView) findViewById(R.id.vrView);
        descriptionView = findViewById(R.id.descriptionView);
        descriptionText = findViewById(R.id.description);
        backBtn = findViewById(R.id.backButton);
        descriptionBtn = findViewById(R.id.descriptionButton);
        tourList = findViewById(R.id.list);

        getImages(); //fetch the images from firebase cloud
        getData();   //fetch the data from firestore storage
        activateButtons();


        //initially load the first item content from the listView
        //loadItemContent(0);

        //click Listener for list items (adds the description text and loads the 360 image)
        tourList.setOnItemClickListener((new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //if the user clicked the same item  multiple times, don't load the same content again
                if (lastClickedPosition != i) {
                    loadItemContent(i);
                }
            }
        }));
    }

    //This function is used to load the item content from the listView
    private void loadItemContent(int i) {

        if (isSorted == false) {
            // Log.d(TAG, "Image Names: " + imageNames.toString());
            // Log.d(TAG, "downloadUrls : " + downloadUrls.toString());
            sortImagesByName();
            // Log.d(TAG, "-----after sort Image Names: " + imageNames.toString());
            // Log.d(TAG, "-----after sort downloadUrls : " + downloadUrls.toString());
            isSorted = true;
        }

        if (language.equals("ar")) {
            descriptionText.setText(buildingList.get(i).getDescriptionAR());
        } else {
            descriptionText.setText(buildingList.get(i).getDescriptionEN());
        }

        String selectedPhotoUrl = getSelectedPhotoUrl(i);
        new LoadPanoramaTask(virtualView).execute(selectedPhotoUrl); //loadPhotoSphere on the background thread
        lastClickedPosition = i;
    }


    //This function is used to activate all the tour settings buttons
    private void activateButtons() {

        //hide or show the building description
        descriptionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (descriptionBtn.isChecked()) {
                    descriptionView.setVisibility(View.VISIBLE); // Show the description view
                } else {
                    descriptionView.setVisibility(View.INVISIBLE); // Hide the description view
                }
            }
        });

        //go back to home page
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VirtualTour.this, HomePage.class);
                startActivity(i);
            }
        });
    }




    //function to get the data from Firestore Storage
    private void getData() {
        db.collection("VirtualTourData").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String code = document.getString("code");
                        //get english data
                        String buildingNameEN = document.getString("name_en");
                        String facultyNameEN = document.getString("faculty_name_en");
                        String descriptionTextEN = document.getString("description_en");
                        //get arabic data
                        String buildingNameAR = document.getString("name_ar");
                        String facultyNameAR = document.getString("faculty_name_ar");
                        String descriptionTextAR = document.getString("description_ar");
                        //create new building object
                        buildingList.add(new Building(code, buildingNameEN, facultyNameEN, descriptionTextEN, buildingNameAR, facultyNameAR, descriptionTextAR));

                        /*
                          //i can use this to fetch the data, but only if the attributes name are the same as firestore feilds
                          Building b = document.toObject(Building.class);
                          buildingList.add(b);
                        */
                    }
                    adapter = new ListAdapter(VirtualTour.this, buildingList);
                    tourList.setAdapter(adapter);
                } else {
                    // Handle errors
                    Log.d("VirtualTour", "Error getting documents: ", task.getException());
                }
            }
        });
    }


    //functions to get images from firebase storage
    private String getSelectedPhotoUrl(int position) {
        return downloadUrls.get(position);
    }

    private void getImages() {
        // Get a reference to the Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        // Specify the directory path where your 360 images are stored
        StorageReference imagesDirectoryRef = storageRef.child("virtualTour_360Images");

        // List all items (images) in the directory
        imagesDirectoryRef.listAll().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (StorageReference item : task.getResult().getItems()) {
                    //download and add the image url
                    //add the image url
                    item.getDownloadUrl().addOnSuccessListener(uri -> {
                        downloadUrls.add(uri.toString());
                        //add image name to the ArrayList - for sorting later
                        imageNames.add(item.getName());
                    }).addOnFailureListener(exception -> {
                        Log.e("Firebase", "Error downloading image: " + exception.getMessage());
                    });
                }
            } else {
                Log.e(TAG, "Error getting image names", task.getException());
            }
        });

    }

    //the images are loaded from firebase by the size, but I am using them by name
    public void sortImagesByName() {
        int n = imageNames.size();
        for (int j = 0; j < n; j++) {
            for (int i = j + 1; i < n; i++) {
                // comparing adjacent strings
                if ((imageNames.get(i)).compareTo(imageNames.get(j)) < 0) {
                    String temp = imageNames.get(j);
                    imageNames.set(j, imageNames.get(i));
                    imageNames.set(i, temp);

                    String temp2 = downloadUrls.get(j);
                    downloadUrls.set(j, downloadUrls.get(i));
                    downloadUrls.set(i, temp2);
                }
            }
        }
    }

}