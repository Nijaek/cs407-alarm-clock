package com.cs407.alarm_clock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
public class ToolbarActivity extends AppCompatActivity {
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        // Set up the Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        SharedPreferences sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        boolean activeAlarm = sharedPreferences.getBoolean("alarmActive", false);

        if (itemId == R.id.action_my_alarms) {
            // Handle My Alarms
            if(activeAlarm) {
                return true;
            }
            startActivity(new Intent(this, AlarmOverview.class));
            return true;
        } else if (itemId == R.id.action_puzzles) {
            // Handle Puzzles
            if(activeAlarm) {
                return true;
            }
            startActivity(new Intent(this, puzzleInitialize.class));
            return true;
        } else if (itemId == R.id.action_create_question) {
            // Handle Create a Question
            if(activeAlarm) {
                return true;
            }
            startActivity(new Intent(this, CustomQuestion.class));
            return true;
        } else if (itemId == R.id.action_settings) {
            // Handle Settings
            startActivity(new Intent(this, SettingsScreen.class));
            return true;
        }
        return false;
    }
}
