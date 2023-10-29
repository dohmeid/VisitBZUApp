package com.example.visitbzu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.visitbzu.helpers.HistoryAdapter;
import com.example.visitbzu.helpers.SuggestionsAdapter;
import com.example.visitbzu.features.virtualTour.VirtualTour;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

    SearchView searchView;
    ListView listView;

    Button virTour;
    RecyclerView historyRV;
    RecyclerView suggestionsRV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        virTour = findViewById(R.id.virtualTourButton);
        virTour.setOnClickListener(view -> {
            Intent i = new Intent(HomePage.this, VirtualTour.class);
            startActivity(i);
            finish();
        });


        historyRV = findViewById(R.id.historyRV);
        historyRecycler();

        suggestionsRV = findViewById(R.id.suggestionsRV);
        suggestionsRecycler();
    }


    private void historyRecycler() {
        ArrayList<String> dataSource;

        //Setting the data source
        dataSource = new ArrayList<>();
        dataSource.add("Over a century, what began as a small girls’ school in Birzeit town has become the most prestigious Palestinian university, transforming Palestinian higher education through its impact on community awareness, culture, and resistance."
                + "\n"
                + "Birzeit University has been a thorn in the side of the occupation, insisting on playing its role of enlightenment and creating a multicultural Palestinian society on the campus grounds.");


        dataSource.add("Birzeit University was initially established as a school in 1924 by Ms. Nabiha. Then in 1953, it started offering first-year university courses in arts and sciences. It later expanded to second-year university classes in 1961 and associate degrees in 1992 enabling students to transfer to other universities in the Arab world and elsewhere to complete their bachelor’s degrees.");

        dataSource.add("In 1967, the Israeli military occupation of the West Bank and Gaza Strip led to travel restrictions for Palestinians, prompting Birzeit to gradually eliminate its high school program and concentrate on a higher education.");

        dataSource.add("In 1975, the name, Birzeit University was officially adopted, and Birzeit became the first Arab university to be established in Palestine. The first graduation ceremony was held on July 11, 1976. \n" +
                "The university established faculties of Commerce and Economics in 1978, followed by Engineering in 1979. Graduate programs were introduced in 1977, and the Faculty of Graduate Studies was formally established in 1996.\n");

        dataSource.add("In 1973, just as Birzeit’s development into a full-fledged university approached completion, Israel closed the campus by military order for two weeks. The university faced repression and closures by Israeli military authorities multiple times 1979-1992.\n" +
                "Despite closures, the university continued to operate underground with small study groups outside the campus, causing students to take longer to graduate.\n");

        dataSource.add("Despite facing repressive measures, Birzeit University continues to thrive with over 900 faculty members and around 10,103 students, with 64% female and 36% male. \n" +
                "The university aims to prepare the young Palestinian generation for responsible leadership and citizenship, emphasizing social awareness and national commitment.\n");


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomePage.this, LinearLayoutManager.HORIZONTAL, false);
        HistoryAdapter myRvAdapter = new HistoryAdapter(dataSource);
        historyRV.setLayoutManager(linearLayoutManager);
        historyRV.setAdapter(myRvAdapter);

        //PagerSnapHelper helper = new PagerSnapHelper();
        //helper.attachToRecyclerView(historyRV);
        //historyRV.addItemDecoration(new CirclePagerIndicatorDecoration());
    }

    private void suggestionsRecycler() {

        ArrayList<String> titlesData;
        ArrayList<String> descData;
        ArrayList<Integer> imagesData = new ArrayList<>();
        imagesData.add(R.drawable.photo_pal_museum);
        imagesData.add(R.drawable.photo_najjad_zeenni);
        imagesData.add(R.drawable.photo_students_break);

        //Setting the data source
        titlesData = new ArrayList<>();
        titlesData.add("The Palestinian Museum");
        titlesData.add("Najjad Zeenni IT of excellence building");
        titlesData.add("Students Break Area");

        descData = new ArrayList<>();
        descData.add("A Non-Governmental Association promotes Palestinian culture, presents new narratives, and provides creative spaces for educational programs and innovative research, fostering a vibrant local and international community.");
        descData.add("Also known as the (Blue Dome) building. It is an innovation space within Birzeit University designed to nurture ideas and creativity and provide an environment which is conducive to creating new businesses or developing existing ones by providing a unique and highly flexible combination of business development processes, people, academia and infrastructure.");
        descData.add("A Non-Governmental Association promotes Palestinian culture, presents new narratives, and provides creative spaces for educational programs and innovative research, fostering a vibrant local and international community.");

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(HomePage.this, LinearLayoutManager.HORIZONTAL, false);
        SuggestionsAdapter myRvAdapter2 = new SuggestionsAdapter(titlesData, descData,imagesData);
        suggestionsRV.setLayoutManager(linearLayoutManager2);
        suggestionsRV.setAdapter(myRvAdapter2);
    }

}