package com.example.visitbzu.features.virtualTour;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.visitbzu.R;

public class menu extends AppCompatActivity {

    ListView myList;
    String buildings[]  = { "building1", "building2", "building3", "building4", "building5",
                            "building6", "building7", "building8"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doha_menu);

        myList = findViewById(R.id.list);
        ArrayAdapter<String> arr = new ArrayAdapter<String>( this,  android.R.layout.simple_list_item_1, buildings);
        // android.R.layout.simple_list_item_1 - default textview design - i can chnage this
        myList.setAdapter(arr);

        //click Listener for list items
        myList.setOnItemClickListener((new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String select = myList.getItemAtPosition(i).toString();
                Toast.makeText(menu.this,""+select,Toast.LENGTH_SHORT).show();
            }

        }));

    }
}