package com.example.visitbzu.helpers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.visitbzu.R;

import java.util.ArrayList;

public class SuggestionsAdapter extends RecyclerView.Adapter<SuggestionsAdapter.SuggestionsHolder> {

    ArrayList<String> titles;
    ArrayList<String> descriptions;
    ArrayList<Integer> images;

    public SuggestionsAdapter(ArrayList<String> data1, ArrayList<String> data2, ArrayList<Integer> data3) {
        this.titles = data1;
        this.descriptions = data2;
        this.images = data3;
    }

    @NonNull
    @Override
    public SuggestionsAdapter.SuggestionsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_page_suggestion_item, parent, false);
        return new SuggestionsAdapter.SuggestionsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestionsAdapter.SuggestionsHolder holder, int position) {
        holder.title.setText(titles.get(position));
        holder.description.setText(descriptions.get(position));
        holder.image.setImageResource(images.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }


    class SuggestionsHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        ImageView image;

        public SuggestionsHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.image);
        }
    }
}
