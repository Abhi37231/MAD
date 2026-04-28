// AddGroupActivity.java (REPLACE FULL FILE)

package com.example.expensesplitter;

import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.example.expensesplitter.database.DBHelper;

public class AddGroupActivity extends AppCompatActivity {

    EditText groupName, membersInput;
    Button saveBtn;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        groupName = findViewById(R.id.groupName);
        membersInput = findViewById(R.id.members);
        saveBtn = findViewById(R.id.saveBtn);

        dbHelper = new DBHelper(this);

        saveBtn.setOnClickListener(v -> {

            String name = groupName.getText().toString().trim();
            String membersStr = membersInput.getText().toString().trim();

            if (name.isEmpty()) {
                Toast.makeText(this, "Enter group name", Toast.LENGTH_SHORT).show();
                return;
            }

            long groupId = dbHelper.addGroup(name);

            // Add members
            if (!membersStr.isEmpty()) {
                String[] memberArray = membersStr.split(",");
                for (String m : memberArray) {
                    String memberName = m.trim();
                    if (!memberName.isEmpty()) {
                        dbHelper.addMember((int) groupId, memberName);
                    }
                }
            }

            Toast.makeText(this, "Group and members added", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}