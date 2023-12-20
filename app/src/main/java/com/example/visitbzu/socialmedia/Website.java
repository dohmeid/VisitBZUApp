package com.example.visitbzu.socialmedia;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class Website {

    // Method to open a website link in a web browser
    public static void openWebsite(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        // Check if there is any application available to handle the Intent
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        } else {
            // Handle the case where no web browser is installed
            Toast.makeText(context, "Sorry, no web browser is installed!", Toast.LENGTH_SHORT).show();
        }
    }
}
