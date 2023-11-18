package com.example.visitbzu.features.virtualTour;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.visitbzu.R;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;


public class VirtualTour extends AppCompatActivity {

    String buildings[] = {"building1", "building2", "building3", "building4", "building5"};
    String text[] = {"building1", "building2", "building3", "building4", "building5 building5 building5 building5 building5 building5 building5 building5 building5 building5 building5 building5 building5 building5 building5 building5 building5 building5 building5 building5 building5 building5 building5 building5 building5 building5 building5 building5 building5 building5"};
    private static final int PHOTO_URL_1 = R.drawable.doha_1;
    private static final int PHOTO_URL_2 = R.drawable.doha_2;
    private static final int PHOTO_URL_3 = R.drawable.doha_2;
    private static final int PHOTO_URL_4 = R.drawable.doha_4;
    private static final int PHOTO_URL_5 = R.drawable.doha_6;

    ListView tourList;
    TextView descriptionText;
    ScrollView descriptionView;
    ToggleButton listBtn,descriptionBtn;
    VrPanoramaView virtualView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doha_virtual_tour);

        virtualView = (VrPanoramaView) findViewById(R.id.vrView);

        //set the description
        descriptionView = findViewById(R.id.descriptionView);
        descriptionText = findViewById(R.id.description);
        descriptionBtn = findViewById(R.id.toggleButton);
        descriptionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (descriptionBtn.isChecked()) {
                    descriptionView.setVisibility(View.VISIBLE); // Show the description view
                } else {
                    descriptionView.setVisibility(View.INVISIBLE); // Hide the description view
                }
            }
        });

        //set the tour list view
        listBtn = findViewById(R.id.toggleButton1);
        tourList = findViewById(R.id.list);
        ArrayAdapter<String> arr = new ArrayAdapter<String>(this, R.layout.doha_virtual_tour_list_item, R.id.textViewItem  ,buildings);
        tourList.setAdapter(arr);
        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listBtn.isChecked()) {
                    tourList.setVisibility(View.VISIBLE); // Show the description view
                } else {
                    tourList.setVisibility(View.INVISIBLE); // Hide the description view
                }
            }
        });

        //click Listener for list items
        tourList.setOnItemClickListener((new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int selectedPhotoUrl = getSelectedPhotoUrl(i);
                descriptionText.setText(text[i]);
                new LoadPanoramaTask(virtualView).execute(selectedPhotoUrl); //oadPhotoSphere on the background thread
            }
        }));
    }

    private int getSelectedPhotoUrl(int position) {
        switch (position) {
            case 0:
                return PHOTO_URL_1;
            case 1:
                return PHOTO_URL_2;
            case 2:
                return PHOTO_URL_3;
            case 3:
                return PHOTO_URL_4;
            case 4:
                return PHOTO_URL_5;
            default:
                return PHOTO_URL_1;
        }
    }


    /*
    //This could take a while. Should do on a background thread, but fine for current example
    private void loadPhotoSphere() {
        VrPanoramaView.Options options = new VrPanoramaView.Options();
        options.inputType = VrPanoramaView.Options.TYPE_MONO;

        //set the settings for the panorama view
        virtualView.setInfoButtonEnabled(false); //Set the button to hide the leftmost information
        //virtualView.setFullscreenButtonEnabled (false); //Hide full screen mode button
        //virtualView.setStereoModeButtonEnabled(false); //Set button to hide diorama
        //virtualView.setEventListener(new ActivityEventListener()); //Set up listening

        //Load local image source
         virtualView.loadImageFromBitmap(BitmapFactory.decodeResource(getResources(), current_photo), options);

        //To load image from internet - Set network image source
        //virtualView.loadImageFromByteArray();
    }*/
}