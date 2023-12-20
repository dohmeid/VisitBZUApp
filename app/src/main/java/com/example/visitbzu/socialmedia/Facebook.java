package com.example.visitbzu.socialmedia;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Facebook {

    // Method to open the Facebook profile in the Facebook app or browser
    public static void openFacebookProfile(Context context, String facebookProfileId) {
        try {
            // Try to open in the Facebook app
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/" + facebookProfileId));
            context.startActivity(intent);
        } catch (Exception e) {
            // Facebook app is not installed, open in browser
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/" + facebookProfileId));
            context.startActivity(intent);
        }
    }
}