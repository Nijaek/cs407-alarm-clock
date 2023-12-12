package com.cs407.alarm_clock;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AlarmOverview extends ToolbarActivity {
    private Button buttonNewAlarm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_overview);

        //Remove the active alarm flag
        SharedPreferences sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("alarmActive", false).apply();

        // Get alarms
        List<Alarm> alarms;
        AlarmDataSource alarmDataSource = new AlarmDataSource(AlarmOverview.this);
        alarmDataSource.open();
        alarms = alarmDataSource.getAllAlarms();
        alarmDataSource.close();

        // Set up container
        RecyclerView recyclerView = findViewById(R.id.recyclerViewAlarms);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        AlarmAdapter adapter = new AlarmAdapter(this, alarms);
        recyclerView.setAdapter(adapter);

        // Handle new button click
        buttonNewAlarm = findViewById(R.id.buttonNewAlarm);
        buttonNewAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlarmOverview.this, AlarmSelection.class);
                startActivity(intent);
            }
        });
    }
}