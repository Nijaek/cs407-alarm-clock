package com.cs407.alarm_clock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class alarm_selection extends AppCompatActivity {
    private Button buttonSaveAlarm;
    private RadioGroup radioGroupRepeat;
    private LinearLayout gridLayoutDaysOfWeek;
    private TextView textViewDaysOfWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_selection);

        radioGroupRepeat = findViewById(R.id.radioGroupRepeat);
        gridLayoutDaysOfWeek = findViewById(R.id.gridLayoutDaysOfWeek);
        textViewDaysOfWeek = findViewById(R.id.textViewDaysOfWeek);
        buttonSaveAlarm = findViewById(R.id.buttonSaveAlarm);

        radioGroupRepeat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButtonYes) {
                    gridLayoutDaysOfWeek.setVisibility(View.VISIBLE);
                    textViewDaysOfWeek.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.radioButtonNo) {
                    gridLayoutDaysOfWeek.setVisibility(View.GONE);
                    textViewDaysOfWeek.setVisibility(View.GONE);
                }
            }
        });

        buttonSaveAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(alarm_selection.this, alarm_overview.class);

                startActivity(intent);
            }
        });
    }
}