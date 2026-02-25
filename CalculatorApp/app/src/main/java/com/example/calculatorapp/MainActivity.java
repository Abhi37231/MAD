package com.example.calculatorapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText num1, num2;
    Button btnAdd, btnSub, btnMul, btnDiv;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);

        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);

        result = findViewById(R.id.result);

        btnAdd.setOnClickListener(v -> calculate('+'));
        btnSub.setOnClickListener(v -> calculate('-'));
        btnMul.setOnClickListener(v -> calculate('*'));
        btnDiv.setOnClickListener(v -> calculate('/'));
    }

    private void calculate(char op) {
        String s1 = num1.getText().toString();
        String s2 = num2.getText().toString();

        if (s1.isEmpty() || s2.isEmpty()) {
            result.setText("Enter both numbers");
            return;
        }

        double a = Double.parseDouble(s1);
        double b = Double.parseDouble(s2);
        double res = 0;

        switch (op) {
            case '+':
                res = a + b;
                break;
            case '-':
                res = a - b;
                break;
            case '*':
                res = a * b;
                break;
            case '/':
                if (b == 0) {
                    result.setText("Cannot divide by zero");
                    return;
                }
                res = a / b;
                break;
        }

        result.setText("Result: " + res);
    }
}
