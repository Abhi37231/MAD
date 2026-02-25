package com.example.regestration2;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etName, etEmail, etPassword, etConfirmPassword;
    Button btnSubmit, btnReset;
    RadioGroup rgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link Views
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnReset = findViewById(R.id.btnReset);
        rgGender = findViewById(R.id.rgGender);

        // Submit Button
        btnSubmit.setOnClickListener(v -> {

            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirm = etConfirmPassword.getText().toString().trim();

            // Validation
            if (name.isEmpty()) {
                etName.setError("Enter Name");
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.setError("Enter valid Email");
                return;
            }

            if (password.length() < 6) {
                etPassword.setError("Minimum 6 characters");
                return;
            }

            if (!password.equals(confirm)) {
                etConfirmPassword.setError("Passwords do not match");
                return;
            }

            if (rgGender.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();
                return;
            }

            // Success
            Toast.makeText(this, "Registration Successful", Toast.LENGTH_LONG).show();
        });

        // Reset Button
        btnReset.setOnClickListener(v -> {
            etName.setText("");
            etEmail.setText("");
            etPassword.setText("");
            etConfirmPassword.setText("");
            rgGender.clearCheck();
        });
    }
}
