package com.example.visitbzu.features.healthCare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.visitbzu.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HealthCare extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter adapter;
    List<String> expandableTitleList;
    HashMap<String, List<String>> expandableDetailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doha_health_care);

        expandableListView = findViewById(R.id.expandableListView);

        expandableDetailList = ExpandableListDataItems.getData();
        expandableTitleList = new ArrayList<String>(expandableDetailList.keySet());
        adapter = new CustomizedExpandableListAdapter(this, expandableTitleList, expandableDetailList);
        expandableListView.setAdapter(adapter);

    }
}

// This method is called when the group is expanded
     /*   expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(), expandableTitleList.get(groupPosition) + " List Expanded.", Toast.LENGTH_SHORT).show();
            }
        });
*/

// This method is called when the group is collapsed
     /*   expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(), expandableTitleList.get(groupPosition) + " List Collapsed.", Toast.LENGTH_SHORT).show();
            }
        });
*/

// This method is called when the child in any group is clicked
 /*       expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getApplicationContext(), expandableTitleList.get(groupPosition)
                        + " -> "
                        + expandableDetailList.get(
                        expandableTitleList.get(groupPosition)).get(
                        childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });
        */