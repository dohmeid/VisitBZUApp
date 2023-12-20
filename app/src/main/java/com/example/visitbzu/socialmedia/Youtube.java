package com.example.visitbzu.socialmedia;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Youtube {

    // Method to open a YouTube channel by its channel ID or username
    public static void openYoutubeChannel(Context context, String channelId) {
        try {
            // Try to open in the YouTube app
            context.getPackageManager().getPackageInfo("com.google.android.youtube", 0);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/" + channelId));
            context.startActivity(intent);
        } catch (Exception e) {
            // YouTube app is not installed, open in browser
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/" + channelId));
            context.startActivity(intent);
        }
    }
}
