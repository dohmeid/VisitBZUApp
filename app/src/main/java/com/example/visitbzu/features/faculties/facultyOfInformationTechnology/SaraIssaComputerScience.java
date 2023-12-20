package com.example.visitbzu.features.faculties.facultyOfInformationTechnology;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.visitbzu.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SaraIssaComputerScience extends AppCompatActivity {

    private FirebaseFirestore db;
    private TextView title, description, type, department, hours;
    private Button btnBack;
    private static final String TAG = "Read Data Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sara_issa_activity_computer_science);

        db = FirebaseFirestore.getInstance();

        title = findViewById(R.id.titleValue);
        description = findViewById(R.id.descriptionValue);
        type = findViewById(R.id.typeValue);
        department = findViewById(R.id.departmentValue);
        hours = findViewById(R.id.hoursValue);

        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaraIssaComputerScience.this, SaraIssaFacultyOfInformationTechnology.class);
                startActivity(intent);
            }
        });

        DocumentReference docRef = db.collection("FacultyOfInformationTechnologyPrograms").document("ComputerScience");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        title.setText(document.getString("title"));
                        description.setText(document.getString("description"));
                        type.setText(document.getString("type"));
                        department.setText(document.getString("department"));
                        hours.setText(document.getString("hours"));
                        Log.d("TAG", document.getString("title")); //Print the title
                        Log.d("TAG", document.getString("description")); //Print the description
                        Log.d("TAG", document.getString("type")); //Print the type
                        Log.d("TAG", document.getString("department")); //Print the department
                        Log.d("TAG", document.getString("hours")); //Print the hours
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