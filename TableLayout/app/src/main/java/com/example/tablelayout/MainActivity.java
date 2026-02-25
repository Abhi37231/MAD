package com.example.tablelayout;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etFirstName, etLastName;
    RatingBar ratingBar;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        ratingBar = findViewById(R.id.ratingBar);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                float rating = ratingBar.getRating();

                Toast.makeText(MainActivity.this,
                        "Name: " + firstName + " " + lastName +
                                "\nRating: " + rating,
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
