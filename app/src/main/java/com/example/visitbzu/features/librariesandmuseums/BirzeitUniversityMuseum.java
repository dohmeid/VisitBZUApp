package com.example.visitbzu.features.librariesandmuseums;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.visitbzu.R;
import com.example.visitbzu.features.map.sararom_Map;
import com.example.visitbzu.socialmedia.Facebook;
import com.example.visitbzu.socialmedia.Instagram;
import com.example.visitbzu.socialmedia.Twitter;
import com.example.visitbzu.socialmedia.Youtube;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class BirzeitUniversityMuseum extends AppCompatActivity {

    private FirebaseFirestore db;
    DocumentReference docRef;
    private Button btnBack, historyBtn, aboutBtn, facebookBtn, youtubeBtn, instagramBtn, twitterBtn;
    private Intent intent;
    private static final String TAG = "Read Data Activity";

    private String facebook_username, youtube_channel_id, instagram_username, twitter_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birzeit_university_museum);

        db = FirebaseFirestore.getInstance();
        docRef = db.collection("InformationAboutBuildings").document("BirzeitUniversityMuseum");

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        facebook_username = document.getString("facebook_username");
                        youtube_channel_id = document.getString("youtube_channel_id");
                        instagram_username = document.getString("instagram_username");
                        twitter_username = document.getString("twitter_username");
                        Log.d("TAG", document.getString("facebook_username"));
                        Log.d("TAG", document.getString("youtube_channel_id"));
                        Log.d("TAG", document.getString("instagram_username"));
                        Log.d("TAG", document.getString("twitter_username"));
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        historyBtn = findViewById(R.id.historyBtn);
        aboutBtn = findViewById(R.id.aboutBtn);
        facebookBtn = findViewById(R.id.facebookBtn);
        youtubeBtn = findViewById(R.id.youtubeBtn);
        instagramBtn = findViewById(R.id.instagramBtn);
        twitterBtn = findViewById(R.id.twitterBtn);
        btnBack = findViewById(R.id.btnBack);

        historyBtn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                intent = new Intent(BirzeitUniversityMuseum.this, HistoryBirzeitUniversityMuseum.class);
                startActivity(intent);
            }
        });

        aboutBtn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                intent = new Intent(BirzeitUniversityMuseum.this, AboutBirzeitUniversityMuseum.class);
                startActivity(intent);
            }
        });

        facebookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Facebook.openFacebookProfile(BirzeitUniversityMuseum.this, facebook_username);
            }
        });
        youtubeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Youtube.openYoutubeChannel(BirzeitUniversityMuseum.this, youtube_channel_id);
            }
        });
        instagramBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Instagram.openInstagramAccount(BirzeitUniversityMuseum.this, instagram_username);
            }
        });
        twitterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Twitter.openTwitterAccount(BirzeitUniversityMuseum.this, twitter_username);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BirzeitUniversityMuseum.this, SaraIssaMuseums.class);
                startActivity(intent);
            }
        });
    }
}