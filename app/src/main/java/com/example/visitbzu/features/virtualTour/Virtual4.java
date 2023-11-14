package com.example.visitbzu.features.virtualTour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import com.example.visitbzu.R;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.InputStream;

public class Virtual4 extends AppCompatActivity {

    VrPanoramaView virtualView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doha_virtual4);

        virtualView = (VrPanoramaView) findViewById(R.id.vr);
        loadPhotoSphere();
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
        virtualView.loadImageFromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.doha_1), options);

        //To load image from internet - Set network image source
        //virtualView.loadImageFromByteArray();
    }

}