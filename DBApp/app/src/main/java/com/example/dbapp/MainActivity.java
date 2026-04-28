package com.example.dbapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ContentValues;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    Button insertBtn, displayBtn;
    EditText editRoll, editName;
    TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Linking UI
        editRoll = findViewById(R.id.editText1);
        editName = findViewById(R.id.editText2);
        insertBtn = findViewById(R.id.button1);
        displayBtn = findViewById(R.id.button2);
        resultView = findViewById(R.id.textView3);

        // Create/Open Database
        db = openOrCreateDatabase("StudentDB", MODE_PRIVATE, null);

        // Create Table
        db.execSQL("CREATE TABLE IF NOT EXISTS Temp(id INTEGER, name TEXT)");

        // Insert Button
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = editRoll.getText().toString();
                String name = editName.getText().toString();

                if (id.isEmpty() || name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                ContentValues values = new ContentValues();
                values.put("id", id);
                values.put("name", name);

                long result = db.insert("Temp", null, values);

                if (result != -1) {
                    Toast.makeText(MainActivity.this, "Record Inserted", Toast.LENGTH_SHORT).show();
                    editRoll.setText("");
                    editName.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Insert Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Display Button
        displayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor c = db.rawQuery("SELECT * FROM Temp", null);

                if (c.getCount() == 0) {
                    resultView.setText("No Records Found");
                    return;
                }

                StringBuilder buffer = new StringBuilder();

                c.moveToFirst();
                while (!c.isAfterLast()) {
                    buffer.append("ID: ").append(c.getString(0))
                            .append("\nName: ").append(c.getString(1))
                            .append("\n\n");
                    c.moveToNext();
                }

                resultView.setText(buffer.toString());
                c.close();
            }
        });
    }
}