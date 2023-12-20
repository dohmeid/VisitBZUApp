package com.example.visitbzu.socialmedia;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Instagram {

    // Method to open an Instagram account by its username
    public static void openInstagramAccount(Context context, String username) {
        try {
            // Try to open in the Instagram app
            context.getPackageManager().getPackageInfo("com.instagram.android", 0);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/_u/" + username));
            intent.setPackage("com.instagram.android");
            context.startActivity(intent);
        } catch (Exception e) {
            // Instagram app is not installed, open in browser
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/" + username));
            context.startActivity(intent);
        }
    }
}
