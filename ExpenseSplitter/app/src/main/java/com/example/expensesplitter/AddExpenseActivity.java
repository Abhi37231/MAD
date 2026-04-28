// AddExpenseActivity.java (REPLACE FULL FILE)

package com.example.expensesplitter;

import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.example.expensesplitter.database.DBHelper;
import com.example.expensesplitter.models.Group;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.example.expensesplitter.models.Group;

public class AddExpenseActivity extends AppCompatActivity {

    EditText amount, paidBy;
    Spinner groupSpinner;
    Button saveExpense;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        amount = findViewById(R.id.amount);
        paidBy = findViewById(R.id.paidBy);
        groupSpinner = findViewById(R.id.groupSpinner);
        saveExpense = findViewById(R.id.saveExpense);

        dbHelper = new DBHelper(this);

        // Populate groups spinner
        ArrayList<Group> groups = dbHelper.getGroups();
        if (groups.isEmpty()) {
            Toast.makeText(this, "Create a group first", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        ArrayAdapter<Group> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, groups);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupSpinner.setAdapter(adapter);
        groupSpinner.setSelection(0);

        saveExpense.setOnClickListener(v -> {

            String amtStr = amount.getText().toString().trim();
            String payer = paidBy.getText().toString().trim();

            if (amtStr.isEmpty() || payer.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            double amt;
            try {
                amt = Double.parseDouble(amtStr);
            } catch (Exception e) {
                Toast.makeText(this, "Invalid amount", Toast.LENGTH_SHORT).show();
                return;
            }

            Group selectedGroup = (Group) groupSpinner.getSelectedItem();
            dbHelper.addExpense(selectedGroup.getId(), amt, payer);

            Toast.makeText(this, "Expense Added", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}