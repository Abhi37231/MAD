package com.example.inputcontrol2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.os.Bundle;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    EditText editName;
    RadioGroup radioGroup;
    CheckBox checkJava, checkAndroid;
    RatingBar ratingBar;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editName);
        radioGroup = findViewById(R.id.radioGroup);
        checkJava = findViewById(R.id.checkJava);
        checkAndroid = findViewById(R.id.checkAndroid);
        ratingBar = findViewById(R.id.ratingBar);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {

            String name = editName.getText().toString();

            int selectedId = radioGroup.getCheckedRadioButtonId();
            String gender = "";

            if(selectedId != -1){
                RadioButton rb = findViewById(selectedId);
                gender = rb.getText().toString();
            }

            String skills = "";

            if(checkJava.isChecked())
                skills += "Java ";

            if(checkAndroid.isChecked())
                skills += "Android ";

            float rating = ratingBar.getRating();

            String result =
                    "Name: " + name +
                            "\nGender: " + gender +
                            "\nSkills: " + skills +
                            "\nRating: " + rating;

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Registration Info")
                    .setMessage(result)
                    .setPositiveButton("OK", null)
                    .show();
        });
    }
}