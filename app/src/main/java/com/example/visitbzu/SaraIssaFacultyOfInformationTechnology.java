package com.example.visitbzu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class SaraIssaFacultyOfInformationTechnology extends AppCompatActivity {

    private ImageView programImageView1;
    private ImageView programImageView2;
    private ImageView programImageView3;
    private ImageView programImageView4;
    private ImageView programImageView5;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private List<SaraIssaDataModel> mList;
    private List<SaraIssaDataModel> mList2;
    private SaraIssaItemAdapter adapter;
    private SaraIssaItemAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_of_information_technology);

        programImageView1 = findViewById(R.id.programImageView1);
        programImageView2 = findViewById(R.id.programImageView2);
        programImageView3 = findViewById(R.id.programImageView3);
        programImageView4 = findViewById(R.id.programImageView4);
        programImageView5 = findViewById(R.id.programImageView5);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mList = new ArrayList<>();

        // Department of Electrical and Computer Engineering List
        List<String> nestedList1 = new ArrayList<>();
        nestedList1.add("Bachelor Program in Electrical Engineering");
        nestedList1.add("Bachelor Program in Computer Systems Engineering");
        nestedList1.add("Master Program in Computer Engineering");

        // Department of Computer Science List
        List<String> nestedList2 = new ArrayList<>();
        nestedList2.add("Bachelor Program in Computer Science");
        nestedList2.add("Bachelor Program in Cyber Security");

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

        programImageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaraIssaFacultyOfInformationTechnology.this, SaraIssaComputerEngineering.class);
                startActivity(intent);
            }
        });

        programImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaraIssaFacultyOfInformationTechnology.this, SaraIssaElectricalEngineering.class);
                startActivity(intent);
            }
        });

        programImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaraIssaFacultyOfInformationTechnology.this, SaraIssaComputerScience.class);
                startActivity(intent);
            }
        });

        programImageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaraIssaFacultyOfInformationTechnology.this, SaraIssaCyberSecurity.class);
                startActivity(intent);
            }
        });

        programImageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaraIssaFacultyOfInformationTechnology.this, SaraIssaComputerEngineeringMaster.class);
                startActivity(intent);
            }
        });
    }
}