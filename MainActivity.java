package com.example.offlinebusinessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;
    ArrayList<Entry> entryList;
    EntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        entryList = db.getAllEntries();

        ListView listView = findViewById(R.id.listView);
        adapter = new EntryAdapter(this, entryList);
        listView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddEntryActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        entryList.clear();
        entryList.addAll(db.getAllEntries());
        adapter.notifyDataSetChanged();
    }
}
