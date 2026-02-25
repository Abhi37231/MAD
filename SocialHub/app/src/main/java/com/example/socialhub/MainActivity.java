package com.example.socialhub;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button whatsapp, instagram, facebook, twitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        whatsapp = findViewById(R.id.btnWhatsApp);
        instagram = findViewById(R.id.btnInstagram);
        facebook = findViewById(R.id.btnFacebook);
        twitter = findViewById(R.id.btnTwitter);

        whatsapp.setOnClickListener(v -> openApp("com.whatsapp"));

        instagram.setOnClickListener(v -> openApp("com.instagram.android"));

        facebook.setOnClickListener(v -> openApp("com.facebook.katana"));

        twitter.setOnClickListener(v -> openApp("com.twitter.android"));
    }

    private void openApp(String packageName) {
        PackageManager pm = getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage(packageName);

        if (intent != null) {
            startActivity(intent);
        } else {
            // If app not installed, open Play Store
            try {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + packageName)));
            } catch (Exception e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
            }
            Toast.makeText(this, "App not installed", Toast.LENGTH_SHORT).show();
        }
    }
}
