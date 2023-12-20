package com.example.visitbzu.socialmedia;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Twitter {

    // Method to open a Twitter account by its username
    public static void openTwitterAccount(Context context, String username) {
        try {
            // Try to open in the Twitter app
            context.getPackageManager().getPackageInfo("com.twitter.android", 0);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + username));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            // Twitter app is not installed, open in browser
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/" + username));
            context.startActivity(intent);
        }
    }
}
