package com.cs407.alarm_clock;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ToggleButton;

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
                // Open database connection
                AlarmDataSource alarmDataSource = new AlarmDataSource(alarm_selection.this);
                alarmDataSource.open(); // Open the database connection

                // Save alarm
                TimePicker timePicker = findViewById(R.id.timePicker);
                EditText editTextNote = findViewById(R.id.editTextNote);
                RadioButton radioButtonYes = findViewById(R.id.radioButtonYes);
                ToggleButton toggleButtonMon = findViewById(R.id.toggleButtonMon);
                ToggleButton toggleButtonTue = findViewById(R.id.toggleButtonTue);
                ToggleButton toggleButtonWed = findViewById(R.id.toggleButtonWed);
                ToggleButton toggleButtonThu = findViewById(R.id.toggleButtonThu);
                ToggleButton toggleButtonFri = findViewById(R.id.toggleButtonFri);
                ToggleButton toggleButtonSat = findViewById(R.id.toggleButtonSat);
                ToggleButton toggleButtonSun = findViewById(R.id.toggleButtonSun);

                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();
                String note = editTextNote.getText().toString();
                boolean isRepeatable = radioButtonYes.isChecked();
                List<Integer> repeatDays = new ArrayList<>();
                boolean isAM;

                // Update hour, isAM
                if (hour >= 0 && hour < 12) {
                    isAM = true;
                } else {
                    isAM = false;
                    if (hour > 12) {
                        hour -= 12;
                    }
                }

                // Update repeat days
                if (isRepeatable) {
                    if (toggleButtonMon.isChecked())
                        repeatDays.add(1);
                    if (toggleButtonTue.isChecked())
                        repeatDays.add(2);
                    if (toggleButtonWed.isChecked())
                        repeatDays.add(3);
                    if (toggleButtonThu.isChecked())
                        repeatDays.add(4);
                    if (toggleButtonFri.isChecked())
                        repeatDays.add(5);
                    if (toggleButtonSat.isChecked())
                        repeatDays.add(6);
                    if (toggleButtonSun.isChecked())
                        repeatDays.add(7);
                }

                // Insert
                Alarm alarm = new Alarm(0, hour, minute, note, isRepeatable, repeatDays, isAM);
                long id = alarmDataSource.createAlarm(alarm);
                Log.d("Id", String.valueOf(id));

                // Close database connection
                alarmDataSource.close();

                // Return to alarm overview
                Intent intent = new Intent(alarm_selection.this, alarm_overview.class);
                startActivity(intent);
            }
        });
    }
}