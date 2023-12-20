package com.example.visitbzu.features.faculties.facultyOfInformationTechnology;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.visitbzu.R;
import com.example.visitbzu.features.faculties.SaraIssaDataModel;
import com.example.visitbzu.features.faculties.SaraIssaItemAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class SaraIssaFacultyOfInformationTechnology extends AppCompatActivity {

    private FirebaseFirestore db;
    DocumentReference docRef, docRef1, docRef2, docRef3, docRef4, docRef5, docRef6, docRef7;
    private TextView name, founder, description, title1, type1, title2, type2, title3, type3, title4, type4, title5, type5;
    private static final String TAG = "Read Data Activity";
    private ImageView programImageView1, programImageView2, programImageView3, programImageView4, programImageView5;
    private RecyclerView recyclerView, recyclerView2;
    private List<SaraIssaDataModel> mList;
    private List<SaraIssaDataModel> mList2;
    private SaraIssaItemAdapter adapter, adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sara_issa_activity_faculty_of_information_technology);

        db = FirebaseFirestore.getInstance();

        name = findViewById(R.id.title);
        founder = findViewById(R.id.founder);
        description = findViewById(R.id.description);

        docRef = db.collection("InformationAboutBuildings").document("FacultyOfInformationTechnology");
        fetchFacultyData(docRef, name, founder, description);

        title1 = findViewById(R.id.title1);
        type1 = findViewById(R.id.type1);
        title2 = findViewById(R.id.title2);
        type2 = findViewById(R.id.type2);
        title3 = findViewById(R.id.title3);
        type3 = findViewById(R.id.type3);
        title4 = findViewById(R.id.title4);
        type4 = findViewById(R.id.type4);
        title5 = findViewById(R.id.title5);
        type5 = findViewById(R.id.type5);

        docRef1 = db.collection("FacultyOfInformationTechnologyPrograms").document("ComputerEngineering");
        docRef2 = db.collection("FacultyOfInformationTechnologyPrograms").document("ElectricalEngineering");
        docRef3 = db.collection("FacultyOfInformationTechnologyPrograms").document("ComputerScience");
        docRef4 = db.collection("FacultyOfInformationTechnologyPrograms").document("CyberSecurity");
        docRef5 = db.collection("FacultyOfInformationTechnologyPrograms").document("ComputerEngineeringMaster");

        fetchProgramsData(docRef1, title1, type1);
        fetchProgramsData(docRef2, title2, type2);
        fetchProgramsData(docRef3, title3, type3);
        fetchProgramsData(docRef4, title4, type4);
        fetchProgramsData(docRef5, title5, type5);

        programImageView1 = findViewById(R.id.programImageView1);
        programImageView2 = findViewById(R.id.programImageView2);
        programImageView3 = findViewById(R.id.programImageView3);
        programImageView4 = findViewById(R.id.programImageView4);
        programImageView5 = findViewById(R.id.programImageView5);

        setImageViewClickListener();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mList = new ArrayList<>();

        // Department of Electrical and Computer Engineering List
        List<String> nestedList1 = new ArrayList<>();

        // Department of Computer Science List
        List<String> nestedList2 = new ArrayList<>();

        docRef6 = db.collection("Departments").document("DepartmentOfElectricalAndComputerEngineering");
        docRef7 = db.collection("Departments").document("DepartmentOfComputerScience");

        fetchDepartmentsData(docRef6, nestedList1);
        fetchDepartmentsData(docRef7, nestedList2);

        mList.add(new SaraIssaDataModel(nestedList1 , "Department of Electrical and Computer Engineering"));
        mList.add(new SaraIssaDataModel( nestedList2,"Department of Computer Science"));

        adapter = new SaraIssaItemAdapter(mList);
        recyclerView.setAdapter(adapter);

        recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        mList2 = new ArrayList<>();

        // First Hall List
        List<String> nestedList3 = new ArrayList<>();
        nestedList3.add("106");
        nestedList3.add("107");
        nestedList3.add("109");

        // Second Hall List
        List<String> nestedList4 = new ArrayList<>();
        nestedList4.add("202");
        nestedList4.add("203");
        nestedList4.add("206");

        // Third Hall List
        List<String> nestedList5 = new ArrayList<>();
        nestedList5.add("202");
        nestedList5.add("203");
        nestedList5.add("206");

        // Fourth Hall List
        List<String> nestedList6 = new ArrayList<>();
        nestedList6.add("202");
        nestedList6.add("203");
        nestedList6.add("206");

        // Fifth Hall List
        List<String> nestedList7 = new ArrayList<>();
        nestedList7.add("202");
        nestedList7.add("203");
        nestedList7.add("206");

        mList2.add(new SaraIssaDataModel(nestedList3 , "First Floor"));
        mList2.add(new SaraIssaDataModel( nestedList4,"Second Floor"));
        mList2.add(new SaraIssaDataModel(nestedList5, "Third Floor"));
        mList2.add(new SaraIssaDataModel( nestedList6,"Fourth Floor"));
        mList2.add(new SaraIssaDataModel(nestedList7, "Fifth Floor"));

        adapter2 = new SaraIssaItemAdapter(mList2);
        recyclerView2.setAdapter(adapter2);
    }

    private void fetchFacultyData(DocumentReference docRef, TextView name, TextView founder, TextView description) {
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        name.setText(document.getString("name"));
                        founder.setText(document.getString("founder"));
                        description.setText(document.getString("description"));
                        Log.d(TAG, document.getString("name"));
                        Log.d(TAG, document.getString("founder"));
                        Log.d(TAG, document.getString("description"));
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    private void fetchProgramsData(DocumentReference docRef, TextView titleTextView, TextView typeTextView) {
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        titleTextView.setText(document.getString("title"));
                        typeTextView.setText(document.getString("type"));
                        Log.d(TAG, document.getString("title"));
                        Log.d(TAG, document.getString("type"));
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    private void setImageViewClickListener() {
        programImageView1.setOnClickListener(createIntentClickListener(SaraIssaComputerEngineering.class));
        programImageView2.setOnClickListener(createIntentClickListener(SaraIssaElectricalEngineering.class));
        programImageView3.setOnClickListener(createIntentClickListener(SaraIssaComputerScience.class));
        programImageView4.setOnClickListener(createIntentClickListener(SaraIssaCyberSecurity.class));
        programImageView5.setOnClickListener(createIntentClickListener(SaraIssaComputerEngineeringMaster.class));
    }

    private View.OnClickListener createIntentClickListener(Class<?> targetClass) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaraIssaFacultyOfInformationTechnology.this, targetClass);
                startActivity(intent);
            }
        };
    }

    private void fetchDepartmentsData(DocumentReference docRef, List<String> targetList) {
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        targetList.add(document.getString("information1"));
                        targetList.add(document.getString("information2"));

                        if (document.contains("information3")) {
                            targetList.add(document.getString("information3"));
                        }

                        Log.d(TAG, document.getString("information1"));
                        Log.d(TAG, document.getString("information2"));

                        if (document.contains("information3")) {
                            Log.d(TAG, document.getString("information3"));
                        }
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