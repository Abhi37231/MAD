package com.example.studentregistration;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etName, etRoll, etCourse;
    Button btnRegister;
    ListView listView;

    ArrayList<String> studentList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etRoll = findViewById(R.id.etRoll);
        etCourse = findViewById(R.id.etCourse);
        btnRegister = findViewById(R.id.btnRegister);
        listView = findViewById(R.id.listViewStudents);

        studentList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                studentList);

        listView.setAdapter(adapter);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString();
                String roll = etRoll.getText().toString();
                String course = etCourse.getText().toString();

                if(name.isEmpty() || roll.isEmpty() || course.isEmpty()){
                    Toast.makeText(MainActivity.this,
                            "Please fill all fields",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    String studentData =
                            "Name: " + name +
                                    "\nRoll: " + roll +
                                    "\nCourse: " + course;

                    studentList.add(studentData);
                    adapter.notifyDataSetChanged();

                    // Clear fields
                    etName.setText("");
                    etRoll.setText("");
                    etCourse.setText("");
                }
            }
        });
    }
}
