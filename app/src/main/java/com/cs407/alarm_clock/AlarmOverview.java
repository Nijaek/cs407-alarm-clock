package com.cs407.alarm_clock;

import java.util.List;

import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import com.cs407.alarm_clock.server.KeepAliveWallpaperServer;
import com.cs407.alarm_clock.server.LockServer;
import com.cs407.alarm_clock.utils.FloatPerUtils;

public class AlarmOverview extends ToolbarActivity {
    private Button buttonNewAlarm;

    private boolean isRunWallpaper = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_overview);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.FOREGROUND_SERVICE},100);

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

        // activate the lock screen service
        startService(new Intent(AlarmOverview.this, LockServer.class));

        // Handle new button click
        buttonNewAlarm = findViewById(R.id.buttonNewAlarm);
        buttonNewAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // tmp
                if (!isRunWallpaper){
                    isRunWallpaper = true;
                    KeepAliveWallpaperServer.setKeepAliveWallpaper(AlarmOverview.this);
                    return;
                }
                if (!FloatPerUtils.isFloatWindowSettingsOn(AlarmOverview.this)){
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                    startActivityForResult(intent,1001);
                    return;
                }
                Intent intent = new Intent(AlarmOverview.this, AlarmSelection.class);
                startActivity(intent);
            }
        });
    }
}