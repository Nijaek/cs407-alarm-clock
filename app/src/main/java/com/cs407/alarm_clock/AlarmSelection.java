package com.cs407.alarm_clock;

import android.util.Log;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.cs407.alarm_clock.server.KeepAliveWallpaperServer;

public class AlarmSelection extends AppCompatActivity {
    private Button buttonSaveAlarm;
    private RadioGroup radioGroupRepeat;
    private LinearLayout gridLayoutDaysOfWeek;
    private TextView textViewDaysOfWeek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_selection);

        // Extract intents
        Intent intent = getIntent();
        long alarmId = intent.getLongExtra("alarm_id", -1);
        int alarmHour = intent.getIntExtra("alarm_hour", -1);
        int alarmMinute = intent.getIntExtra("alarm_minute", -1);
        String alarmNote = intent.getStringExtra("alarm_note");
        Boolean alarmIsRepeatable = intent.getBooleanExtra("alarm_repeatable", false);
        String alarmRepeatDays = intent.getStringExtra("alarm_repeat_days");

        // Interface functionality
        radioGroupRepeat = findViewById(R.id.radioGroupRepeat);
        gridLayoutDaysOfWeek = findViewById(R.id.gridLayoutDaysOfWeek);
        textViewDaysOfWeek = findViewById(R.id.textViewDaysOfWeek);
        buttonSaveAlarm = findViewById(R.id.buttonSaveAlarm);
        TimePicker timePicker = findViewById(R.id.timePicker);
        EditText editTextNote = findViewById(R.id.editTextNote);
        RadioButton radioButtonYes = findViewById(R.id.radioButtonYes);
        RadioButton radioButtonNo = findViewById(R.id.radioButtonNo);
        ToggleButton toggleButtonMon = findViewById(R.id.toggleButtonMon);
        ToggleButton toggleButtonTue = findViewById(R.id.toggleButtonTue);
        ToggleButton toggleButtonWed = findViewById(R.id.toggleButtonWed);
        ToggleButton toggleButtonThu = findViewById(R.id.toggleButtonThu);
        ToggleButton toggleButtonFri = findViewById(R.id.toggleButtonFri);
        ToggleButton toggleButtonSat = findViewById(R.id.toggleButtonSat);
        ToggleButton toggleButtonSun = findViewById(R.id.toggleButtonSun);

        // Update inputs based on alarm being edited
        if (alarmId != -1) {
            timePicker.setHour(alarmHour);
            timePicker.setMinute(alarmMinute);
            editTextNote.setText(alarmNote);
            radioButtonYes.setChecked(alarmIsRepeatable);
            radioButtonNo.setChecked(!alarmIsRepeatable);

            if (alarmRepeatDays.contains("1"))
                toggleButtonMon.setChecked(true);
            if (alarmRepeatDays.contains("2"))
                toggleButtonTue.setChecked(true);
            if (alarmRepeatDays.contains("3"))
                toggleButtonWed.setChecked(true);
            if (alarmRepeatDays.contains("4"))
                toggleButtonThu.setChecked(true);
            if (alarmRepeatDays.contains("5"))
                toggleButtonFri.setChecked(true);
            if (alarmRepeatDays.contains("6"))
                toggleButtonSat.setChecked(true);
            if (alarmRepeatDays.contains("7"))
                toggleButtonSun.setChecked(true);

            if (alarmIsRepeatable) {
                gridLayoutDaysOfWeek.setVisibility(View.VISIBLE);
                textViewDaysOfWeek.setVisibility(View.VISIBLE);
            }
        }

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
                AlarmDataSource alarmDataSource = new AlarmDataSource(AlarmSelection.this);
                alarmDataSource.open(); // Open the database connection

                Calendar instance = Calendar.getInstance();

                // Save alarm
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();
                String note = editTextNote.getText().toString();
                boolean isRepeatable = radioButtonYes.isChecked();
                List<Integer> repeatDays = new ArrayList<>();
                boolean isAM;

                instance.set(Calendar.HOUR_OF_DAY, hour);
                instance.set(Calendar.MINUTE, minute);

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

                // Save alarm
                if (alarmId == -1) { // Insert
                    Alarm alarm = new Alarm(0, hour, minute, note, isRepeatable, repeatDays, isAM);
                    long id = alarmDataSource.createAlarm(alarm);
                    Bundle bundle = new Bundle();
                    bundle.putString("TITLE", note);
                    bundle.putInt("id", (int) id);
                    AlarmUtils.addSingleAlarm(AlarmSelection.this, instance.getTimeInMillis(), bundle);
                    Log.d("Id", String.valueOf(id));
                } else { // Edit
                    alarmDataSource.updateAlarm(new Alarm(alarmId, hour, minute, note, isRepeatable, repeatDays, isAM));
                }

                // Close database connection
                alarmDataSource.close();

                // Return to alarm overview
                Intent intent = new Intent(AlarmSelection.this, AlarmOverview.class);
                startActivity(intent);
            }
        });
    }
}