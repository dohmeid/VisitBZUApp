package com.example.visitbzu.features.virtualTour;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.InputStream;


public class LoadPanoramaTask extends AsyncTask<Integer, Void, Bitmap> {
    private static final String TAG = "LoadPanoramaTask";
    private VrPanoramaView vrPanoramaView;

    public LoadPanoramaTask(VrPanoramaView vrPanoramaView) {
        this.vrPanoramaView = vrPanoramaView;
    }

    @Override
    protected Bitmap doInBackground(Integer... params) {
        int resourceId = params[0];
        try {
            InputStream inputStream = vrPanoramaView.getContext().getResources().openRawResource(resourceId);
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
            vrPanoramaView.setInfoButtonEnabled(false); //Set the button to hide the leftmost information
            vrPanoramaView.setStereoModeButtonEnabled(false); //Set button to hide diorama
            vrPanoramaView.loadImageFromBitmap(result, options);
        } else {
            Toast.makeText(vrPanoramaView.getContext(), "Failed to load image", Toast.LENGTH_SHORT).show();
        }
    }

}
