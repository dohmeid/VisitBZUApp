package com.example.visitbzu.socialmedia;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class Phone {

    // Method to initiate a phone call
    public static void makePhoneCall(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));

        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        } else {
            // Handle the case where no phone app is installed
            Toast.makeText(context, "Sorry, no phone app is installed!", Toast.LENGTH_SHORT).show();
        }
    }
}
