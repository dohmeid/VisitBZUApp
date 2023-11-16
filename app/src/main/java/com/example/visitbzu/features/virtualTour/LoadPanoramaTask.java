package com.example.visitbzu.features.virtualTour;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoadPanoramaTask extends AsyncTask<String, Void, Bitmap> {

    private static final String TAG = "LoadPanoramaTask";
    private VrPanoramaView vrPanoramaView;

    public LoadPanoramaTask(VrPanoramaView vrPanoramaView) {
        this.vrPanoramaView = vrPanoramaView;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String imageUrl = params[0];
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            Log.e(TAG, "Error loading panorama image: " + e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        if (result != null) {
            VrPanoramaView.Options options = new VrPanoramaView.Options();
            options.inputType = VrPanoramaView.Options.TYPE_MONO;
            vrPanoramaView.loadImageFromBitmap(result, options);
        } else {
            Toast.makeText(vrPanoramaView.getContext(), "Failed to load image", Toast.LENGTH_SHORT).show();
        }
    }

}
