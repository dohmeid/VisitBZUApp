package com.example.visitbzu.features.virtualTour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.visitbzu.R;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.InputStream;

public class Virtual4 extends AppCompatActivity {

    ListView myList;
    String buildings[]  = { "building1", "building2", "building3", "building4", "building5"};
    VrPanoramaView virtualView;
    VrPanoramaView.Options options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doha_virtual4);

        virtualView = (VrPanoramaView) findViewById(R.id.vr);
        options = new VrPanoramaView.Options();
        options.inputType = VrPanoramaView.Options.TYPE_MONO;
        virtualView.setInfoButtonEnabled(false); //Set the button to hide the leftmost information

        myList = findViewById(R.id.list2);
        ArrayAdapter<String> arr = new ArrayAdapter<String>( this,  android.R.layout.simple_list_item_1, buildings);
        myList.setAdapter(arr);


        //click Listener for list items
        myList.setOnItemClickListener((new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                loadPhoto(i);
            }
        }));


    }

    private void loadPhoto(int position) {

        int current_photo = R.drawable.doha_1;
        if(position==0)
            current_photo = R.drawable.doha_1;
        if(position==1)
            current_photo = R.drawable.doha_2;
        else if(position==2)
            current_photo = R.drawable.doha_3;
        else if(position==3)
            current_photo = R.drawable.doha_4;
        else if(position==4)
            current_photo = R.drawable.doha_5;

        virtualView.loadImageFromBitmap(BitmapFactory.decodeResource(getResources(), current_photo), options);
    }


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
      //  virtualView.loadImageFromBitmap(BitmapFactory.decodeResource(getResources(), current_photo), options);

        //To load image from internet - Set network image source
        //virtualView.loadImageFromByteArray();
    }

}