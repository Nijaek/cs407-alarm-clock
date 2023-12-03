package com.cs407.alarm_clock;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class alarm_overview extends AppCompatActivity {
    private Button buttonNewAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_overview);

        buttonNewAlarm = findViewById(R.id.buttonNewAlarm);
        buttonNewAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(alarm_overview.this, alarm_selection.class);

                startActivity(intent);
            }
        });
    }
}