package com.example.menuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layout);
    }

    // THIS CREATES THE 3 DOT MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // CLICK HANDLING
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.android) {
            layout.setBackgroundColor(Color.RED);
        } else if (item.getItemId() == R.id.java) {
            layout.setBackgroundColor(Color.GREEN);
        } else if (item.getItemId() == R.id.kotlin) {
            layout.setBackgroundColor(Color.BLUE);
        }

        return true;
    }
}