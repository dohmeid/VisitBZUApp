package com.example.visitbzu.features.virtualTour;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.InputStream;
import java.net.URL;

//This class is used to upload the 360 images on the background thread (since this action could take a while)
public class LoadPanoramaTask extends AsyncTask<String, Void, Bitmap> {
    private static final String TAG = "LoadPanoramaTask";
    private final VrPanoramaView vrPanoramaView;

    public LoadPanoramaTask(VrPanoramaView vrPanoramaView) {
        this.vrPanoramaView = vrPanoramaView;
    }

    //override methods from AsyncTask
    @Override
    protected Bitmap doInBackground(String... urls) {
        String imageUrl = urls[0];
        try {
            InputStream inputStream = new URL(imageUrl).openStream();
            return BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            Log.e(TAG, "Error loading panorama image: " + e.getMessage());
            return null;
        }
    }

    protected void onPostExecute(Bitmap result) {
        if (result != null) {
            VrPanoramaView.Options options = new VrPanoramaView.Options();
            options.inputType = VrPanoramaView.Options.TYPE_MONO;

            //set the settings for the panorama view
            vrPanoramaView.setInfoButtonEnabled(false);       //Set the button to hide the leftmost information
            vrPanoramaView.setStereoModeButtonEnabled(false); //Set button to hide diorama
            //vrPanoramaView.setFullscreenButtonEnabled (false); //Hide full screen mode button

            //load the image into VrPanoramaView
            vrPanoramaView.loadImageFromBitmap(result, options);
        } else {
            Toast.makeText(vrPanoramaView.getContext(), "Failed to load image", Toast.LENGTH_SHORT).show();
        }
    }
}

