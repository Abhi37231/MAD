package com.example.progressbarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView textPercent;
    Button btnDownload, btnPause;

    int progressStatus = 0;
    boolean isPaused = false;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        textPercent = findViewById(R.id.textPercent);
        btnDownload = findViewById(R.id.btnDownload);
        btnPause = findViewById(R.id.btnPause);

        btnDownload.setOnClickListener(v -> startDownload());

        btnPause.setOnClickListener(v -> isPaused = true);
    }

    private void startDownload() {

        isPaused = false;

        new Thread(() -> {
            while (progressStatus < 100 && !isPaused) {

                progressStatus++;

                handler.post(() -> {
                    progressBar.setProgress(progressStatus);
                    textPercent.setText(progressStatus + "%");
                });

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}