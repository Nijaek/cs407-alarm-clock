package com.cs407.alarm_clock;

import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.POST_NOTIFICATIONS;
import static android.Manifest.permission.VIBRATE;
import static android.Manifest.permission.WAKE_LOCK;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.cs407.alarm_clock.Ring.RingService;

import java.util.Calendar;

public class RingActivity extends AppCompatActivity {

    private Button dismiss;
    private Button snooze;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring);
        dismiss = (Button) findViewById(R.id.dismiss);
        snooze = (Button) findViewById(R.id.snooze);

        // Ask permission
        if (ContextCompat.checkSelfPermission(this, POST_NOTIFICATIONS) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{POST_NOTIFICATIONS}, 1);
        }
        if (ContextCompat.checkSelfPermission(this, INTERNET) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{INTERNET}, 1);
        }
        if (ContextCompat.checkSelfPermission(this, VIBRATE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{VIBRATE}, 1);
        }
        if (ContextCompat.checkSelfPermission(this, WAKE_LOCK) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{WAKE_LOCK}, 1);

            //stop
            dismiss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentService = new Intent(getApplicationContext(), RingService.class);
                    getApplicationContext().stopService(intentService);
                    finish();
                }
            });
            // snooze for a while

            snooze.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.add(Calendar.MINUTE, 10);

                    Intent intentService = new Intent(getApplicationContext(), RingService.class);
                    getApplicationContext().stopService(intentService);
                    finish();
                }
            });
        }
    }
}
