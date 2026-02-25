package com.example.intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnSms, btnCall, btnSettings, btnNetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSms = findViewById(R.id.btnSms);
        btnCall = findViewById(R.id.btnCall);
        btnSettings = findViewById(R.id.btnSettings);
        btnNetwork = findViewById(R.id.btnNetwork);

        // 1. Send SMS
        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setData(Uri.parse("sms:9876543210"));
                smsIntent.putExtra("sms_body", "Hello from Implicit Intent App");
                startActivity(smsIntent);
            }
        });

        // 2. Make Call (opens dialer)
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:9876543210"));
                startActivity(callIntent);
            }
        });

        // 3. Open General Settings
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingsIntent = new Intent(Settings.ACTION_SETTINGS);
                startActivity(settingsIntent);
            }
        });

        // 4. Open Network Settings
        btnNetwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent networkIntent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                startActivity(networkIntent);
            }
        });
    }
}
