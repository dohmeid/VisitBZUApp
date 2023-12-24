package com.example.visitbzu.features.virtualTour;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.PreferenceManager;

import com.example.visitbzu.R;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Building> {

    private Context context;
    String language; //save the app language here


    //list adapter constructor
    public ListAdapter(@NonNull Context context, ArrayList<Building> dataArrayList) {
        super(context, 0, dataArrayList);
        this.context = context;

        //get the selectedLanguage from SharedPreferences and print it to debug
        SharedPreferences sh= context.getSharedPreferences("MySharedPref", 0);
        language = sh.getString("AppLanguage", "");
        Log.d("ListAdapter", "--------------language " + language);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        //inflate the view if there is no view being reused
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.doha_virtual_tour_list_item, parent, false);
        }

        //get current item data from the list view
        Building listData = getItem(position);

        //get the listView components
        TextView listName = view.findViewById(R.id.nameTextView);
        TextView listFaculty = view.findViewById(R.id.facultyViewItem);
        TextView listCode = view.findViewById(R.id.codeTextView);

        //set the data to listView components
        listCode.setText(listData.getCode());

        if (language.equals("ar")) {
            //arabic language data
            listName.setText(listData.getNameAR());
            listFaculty.setText(listData.getFacultyNameAR());
        } else {
            //english language data
            listName.setText(listData.getNameEN());
            listFaculty.setText(listData.getFacultyNameEN());
        }

        //return the new view to render on screen
        return view;
    }
}
