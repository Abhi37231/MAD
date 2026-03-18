package com.example.menuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.graphics.Color;
import android.view.*;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    RelativeLayout layout;
    TextView text;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layout);
        text = findViewById(R.id.text);
        btn = findViewById(R.id.btn);

        // CONTEXT MENU
        registerForContextMenu(text);

        // POPUP MENU
        btn.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(MainActivity.this, btn);
            popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

            popup.setOnMenuItemClickListener(item -> {
                Toast.makeText(MainActivity.this,
                        item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            });

            popup.show();
        });
    }

    // OPTION MENU (3 dots)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.red)
            layout.setBackgroundColor(Color.RED);
        else if (item.getItemId() == R.id.green)
            layout.setBackgroundColor(Color.GREEN);
        else if (item.getItemId() == R.id.blue)
            layout.setBackgroundColor(Color.BLUE);

        return true;
    }

    // CONTEXT MENU
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Choose Color");
        menu.add(0, v.getId(), 0, "Yellow");
        menu.add(0, v.getId(), 0, "Gray");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle().equals("Yellow"))
            layout.setBackgroundColor(Color.YELLOW);
        else
            layout.setBackgroundColor(Color.GRAY);

        return true;
    }
}