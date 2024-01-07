package com.example.visitbzu.features.healthCare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.visitbzu.R;
import com.example.visitbzu.helpers.HistoryAdapter;

import java.util.ArrayList;

public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.AboutHolder> {

    ArrayList<String> data;

    public AboutAdapter(ArrayList<String> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public AboutAdapter.AboutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doha_health_care_about_item, parent, false);
        return new AboutAdapter.AboutHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AboutAdapter.AboutHolder holder, int position) {
        holder.text.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    static class AboutHolder extends RecyclerView.ViewHolder {
        TextView text;

        public AboutHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.title);
        }
    }

}