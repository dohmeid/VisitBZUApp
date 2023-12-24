package com.example.visitbzu.features.faq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.visitbzu.HomePage;
import com.example.visitbzu.R;

import java.util.ArrayList;
import java.util.List;

public class sararom_FAQs extends AppCompatActivity {
    RecyclerView recyclerView;
    List<sararom_Question> questionList;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sararom_faq);
        recyclerView = findViewById(R.id.sararom_question);
        back = findViewById(R.id.sararom_arrow_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(sararom_FAQs.this, HomePage.class));
            }
        });
        initData();
        setRecyclerView();

    }

    private void setRecyclerView() {
        sararom_QuestionsAdapter questionsAdapter = new sararom_QuestionsAdapter(getBaseContext(),questionList);
        recyclerView.setAdapter(questionsAdapter);
        recyclerView.setHasFixedSize(true);
    }

    private void initData() {
        questionList = new ArrayList<>();
        questionList.add(new sararom_Question("Why should I visit a college?","Lorem ipsum dolor sit amet consectetur." +
                " Ornare arcu quam vel mauris fermentum vulputate " +
                "gravida ac. Vel fringilla turpis lobortis nam aenean sit. Eget faucibus et leo at velit lectus molestie." +
                " Hendrerit curabitur mattis elit maecenas platea quam egestas."));
        questionList.add(new sararom_Question("When is the best time to visit?","Lorem ipsum dolor sit amet consectetur." +
                " Ornare arcu quam vel mauris fermentum vulputate gravida ac. " +
                "Vel fringilla turpis lobortis nam aenean sit. Eget faucibus et leo at velit lectus molestie. " +
                "Hendrerit curabitur mattis elit maecenas platea quam egestas."));
        questionList.add(new sararom_Question("Is there wireless internet on campus?","Lorem ipsum dolor sit amet consectetur." +
                " Ornare arcu quam vel mauris fermentum vulputate gravida ac. Vel fringilla turpis lobortis nam aenean sit. " +
                "Eget faucibus et leo at velit lectus molestie." +
                " Hendrerit curabitur mattis elit maecenas platea quam egestas."));
        questionList.add(new sararom_Question("Should I tour with a group, or on my own?","Lorem ipsum dolor sit amet consectetur." +
                " Ornare arcu quam vel mauris fermentum vulputate gravida ac. Vel fringilla turpis lobortis nam aenean sit." +
                " Eget faucibus et leo at velit lectus molestie." +
                " Hendrerit curabitur mattis elit maecenas platea quam egestas."));
        questionList.add(new sararom_Question("How long does the tour take?","Lorem ipsum dolor sit amet consectetur." +
                " Ornare arcu quam vel mauris fermentum vulputate gravida ac. Vel fringilla turpis lobortis nam aenean sit." +
                " Eget faucibus et leo at velit lectus molestie. Hendrerit curabitur mattis elit maecenas platea quam egestas."));


    }

}
