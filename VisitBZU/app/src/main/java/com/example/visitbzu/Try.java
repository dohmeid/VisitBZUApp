package com.example.visitbzu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Try extends AppCompatActivity {

    RecyclerView rv;
    ArrayList<String> dataSource;
    LinearLayoutManager linearLayoutManager;
    MyRvAdapter myRvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try);

        rv = findViewById(R.id.horizontalRv);
        //Setting the data source
        dataSource = new ArrayList<>();
        dataSource.add("Over a century, what began as a small girls’ school in Birzeit town has become the most prestigious Palestinian university," +
                "transforming Palestinian higher education through its impact on community awareness, culture and resistance.");
        dataSource.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam in scelerisque sem. Mauris\n" +
                "  volutpat, dolor id interdum ullamcorper, risus dolor egestas lectus, sit amet mattis purus\n" +
                "  dui nec risus. Maecenas non sodales nisi, vel dictum dolor. Class aptent taciti sociosqu ad");
        dataSource.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam in scelerisque sem. Mauris\n" +
                " volutpat, dolor id interdum ullamcorper, risus dolor egestas lectus, sit amet mattis purus\n" +
                " dui nec risus. Maecenas non sodales nisi, vel dictum dolor. Class aptent taciti sociosqu ad");
        dataSource.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam in scelerisque sem. Mauris\n" +
                " volutpat, dolor id interdum ullamcorper, risus dolor egestas lectus, sit amet mattis purus\n" +
                " dui nec risus. Maecenas non sodales nisi, vel dictum dolor. Class aptent taciti sociosqu ad");




        linearLayoutManager = new LinearLayoutManager(Try.this, LinearLayoutManager.HORIZONTAL, false);
        myRvAdapter = new MyRvAdapter(dataSource);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(myRvAdapter);
    }

    class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyHolder> {
        ArrayList<String> data;

        public MyRvAdapter(ArrayList<String> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(Try.this).inflate(R.layout.rv_item, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.tvTitle.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
            TextView tvTitle;

            public MyHolder(@NonNull View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.title);
            }
        }

    }
}