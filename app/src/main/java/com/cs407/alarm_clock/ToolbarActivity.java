package com.cs407.alarm_clock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
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

        if (itemId == R.id.action_my_alarms) {
            // Handle My Alarms
            startActivity(new Intent(this, alarm_overview.class));
            return true;
        } else if (itemId == R.id.action_create_question) {
            // Handle Create a Question
            return true;
        } else if (itemId == R.id.action_settings) {
            // Handle Settings
            startActivity(new Intent(this, SettingsScreen.class));
            return true;
        }
        return false;
    }
}
