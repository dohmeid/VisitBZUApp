package com.example.visitbzu.features.healthCare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.visitbzu.R;

import java.util.List;

public class MedicalServiceAdapter extends ArrayAdapter<MedicalServiceItem> {

    public MedicalServiceAdapter(Context context, List<MedicalServiceItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        MedicalServiceItem listItem = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.doha_health_care_medical_service_list_item, parent, false);
        }

        // Lookup view for data population
        TextView titleTextView = convertView.findViewById(R.id.titleTextView);
        TextView descriptionTextView = convertView.findViewById(R.id.descriptionTextView);

        // Populate the data into the template view using the data object
        titleTextView.setText(listItem.getTitle());
        descriptionTextView.setText(listItem.getDescription());

        // Return the completed view to render on screen
        return convertView;
    }
}