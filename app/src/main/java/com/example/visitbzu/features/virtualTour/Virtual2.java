package com.example.visitbzu.features.virtualTour;

import androidx.fragment.app.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.visitbzu.R;

public class Virtual2 extends Fragment {

    private static String imageURL = "https://www.google.com/maps/@31.9586766,35.1802866,3a,75y,28.46h,81.39t/data=!3m7!1e1!3m5!1sAF1QipN4KTKdUfH6iwpcvzWyjfF6bG6a0OOv4NpdE_W3!2e10!3e11!7i5376!8i2688?entry=ttu";
    ImageView imageView;
    WebView web;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.doha_virtual2, container, false);

        web = view.findViewById(R.id.web2);
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //Optimize WebView Settings to improve performance:
        // Enable hardware acceleration
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        // Enable zoom controls
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);

        // Enable local storage
        webSettings.setDomStorageEnabled(true);

        // Load the Street View link
        // String streetViewLink = "https://www.google.com/maps/@?q=Street+View+Location"; // Replace with the actual Street View URL
       // web.loadUrl(imageURL);

        // Ensure links open within the WebView
        //web.setWebViewClient(new WebViewClient());

        // Initialize the WebView in the background

        // Initialize the WebView in the background
        WebViewLoadTask webViewLoadTask = new WebViewLoadTask();
        webViewLoadTask.url = imageURL; // Set the URL here
        webViewLoadTask.execute();


     /*   web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("https://maps.app.goo.gl")) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        intent.setPackage("com.google.android.apps.maps"); // This specifies that you want to open it in Google Maps
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        // Google Maps app is not installed, open the URL in a web browser
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    }
                    return true;
                } else {
                    // Load standard HTTP/HTTPS URLs in the WebView
                    view.loadUrl(url);
                    return true;
                }
            }
        });*/

        return view;
    }

    // AsyncTask to load the WebView in the background
    private class WebViewLoadTask extends AsyncTask<Void, Void, Void>{
        public String url;

        @Override
        protected Void doInBackground(Void... params) {
            // Do background work here
            return null;
        }

        @Override
        public void onPostExecute(Void result) {
            // Load the URL in the WebView on the main thread
            web.loadUrl(url);
        }
    }



    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doha_virtual2);


        web = findViewById(R.id.web2);
        web.getSettings().setJavaScriptEnabled(true);
        //String streetWeb =
        //"<iframe src=\"https://www.google.com/maps/embed?pb=!4v1699745877485!6m8!1m7!1sCAoSLEFGMVFpcE40S1RLZFVmSDZpd3BjdnpXeWpmRjZiRzZhME9PdjROcGRFX1cz!2m2!1d31.95867656519368!2d35.18028661608696!3f28.458868928565188!4f-8.60676835969069!5f0.7820865974627469\" width=\"600\" height=\"450\" style=\"border:0;\" allowfullscreen loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>";
        //web.loadData(streetWeb, "text/html", null);


        web.getSettings().setJavaScriptEnabled(true);
       // web.getSettings().setLoadWithOverviewMode(true);
       // web.getSettings().setUseWideViewPort(true);
       // web.getSettings().setSupportZoom(false);

       // Uri uri = Uri.parse("https://maps.app.goo.gl/U6a5vLWCYUvUVr7V7");
        web.loadUrl(imageURL);
        // Ensure links open within the WebView
        web.setWebViewClient(new WebViewClient());



        // imageView = findViewById(R.id.imageView3);
       // Picasso.get().load(imageURL).into(imageView);
       /* Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream((InputStream)new URL(imageURL).getContent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        imageView.setImageBitmap(bitmap);
*/

    //}
}