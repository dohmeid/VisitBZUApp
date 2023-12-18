package com.example.visitbzu.features.virtualTour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.visitbzu.R;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Building> {

    //list adapter constructor
    public ListAdapter(@NonNull Context context, ArrayList<Building> dataArrayList) {
        super(context, 0, dataArrayList);
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

        //english language data
        listName.setText(listData.getNameEN());
        listFaculty.setText(listData.getFacultyNameEN());

        //arabic language data
        //listName.setText(listData.getNameAR());
        //listFaculty.setText(listData.getFacultyNameAR());

        //return the new view to render on screen
        return view;
    }
}
