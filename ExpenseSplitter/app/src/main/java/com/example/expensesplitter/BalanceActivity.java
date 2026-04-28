// BalanceActivity.java - Improved

package com.example.expensesplitter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.expensesplitter.database.DBHelper;

import java.util.HashMap;
import java.util.Map;

public class BalanceActivity extends AppCompatActivity {

    TextView balanceText;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);

        balanceText = findViewById(R.id.balanceText);
        dbHelper = new DBHelper(this);

        showBalances();
    }

    private void showBalances() {
        int groupId = getIntent().getIntExtra("group_id", -1);
        HashMap<String, Double> map;
        String title = "Balances";

        if (groupId == -1) {
            map = dbHelper.getBalances();  // Global
            title = "Global Balances";
        } else {
            map = dbHelper.getBalances();  // Use global for now
            title = "Balances";
        }

        StringBuilder result = new StringBuilder(title + ":\n\n");

        boolean hasBalance = false;
        for (String person : map.keySet()) {
            double amt = map.get(person);
            if (Math.abs(amt) > 0.01) {
                hasBalance = true;
                if (amt > 0) {
                    result.append(person)
                            .append(" is owed ₹")
                            .append(String.format("%.2f", amt))
                            .append("\n\n");
                } else {
                    result.append(person)
                            .append(" owes ₹")
                            .append(String.format("%.2f", -amt))
                            .append("\n\n");
                }
            }
        }

        if (!hasBalance) {
            result.append("Everything is settled! 🎉");
        }

        balanceText.setText(result.toString());
    }
}

