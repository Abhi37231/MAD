package com.example.inputcontrol1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ToggleButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    ToggleButton toggleButton;
    Button alertBtn;

    String[] languages = {"Java", "Android", "Python", "C++"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        toggleButton = findViewById(R.id.toggleButton);
        alertBtn = findViewById(R.id.alertBtn);

        // Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                languages
        );

        spinner.setAdapter(adapter);

        // Toggle Button
        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(MainActivity.this, "Toggle ON", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Toggle OFF", Toast.LENGTH_SHORT).show();
            }
        });

        // Alert Dialog
        alertBtn.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Alert Dialog");
            builder.setMessage("Do you want to exit?");

            builder.setPositiveButton("Yes", (dialog, which) -> {
                Toast.makeText(MainActivity.this, "App Closed", Toast.LENGTH_SHORT).show();
                finish();
            });

            builder.setNegativeButton("No", (dialog, which) -> {
                dialog.dismiss();
            });

            builder.show();
        });
    }
}