package com.example.expensesplitter;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensesplitter.adapter.GroupAdapter;
import com.example.expensesplitter.database.DBHelper;
import com.example.expensesplitter.models.Group;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayout emptyState;
    FloatingActionButton fabAddGroup;
    DBHelper dbHelper;
    ArrayList<Group> groupList;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        emptyState = findViewById(R.id.emptyState);
        fabAddGroup = findViewById(R.id.fabAddGroup);

        dbHelper = new DBHelper(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadGroups();

        fabAddGroup.setOnClickListener(v -> 
                startActivity(new Intent(this, AddGroupActivity.class)));

        findViewById(R.id.emptyFab).setOnClickListener(v -> 
                startActivity(new Intent(this, AddGroupActivity.class)));

        findViewById(R.id.fabBalance).setOnClickListener(v -> 
                startActivity(new Intent(this, BalanceActivity.class))); // Global balance
    }

    private void loadGroups() {
        groupList = dbHelper.getGroups();
        recyclerView.setAdapter(new GroupAdapter(groupList));
        
        if (groupList.isEmpty()) {
            emptyState.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            emptyState.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadGroups();
    }
}
