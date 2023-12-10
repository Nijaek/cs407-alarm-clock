package com.cs407.alarm_clock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

public class SettingsScreen extends AppCompatActivity {
    String[] item = {"Math", "Custom"}; //Need a function to read in other Question types
    AutoCompleteTextView autoCompleteTextview;
    ArrayAdapter<String> adapterItems;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);
        loadSharedPref();

        EditText questionNumInput = (EditText) findViewById(R.id.questionNumberInput);

        autoCompleteTextview = findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, item);

        autoCompleteTextview.setAdapter(adapterItems);

        autoCompleteTextview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = adapterItems.getItem(position);
                sharedPreferences.edit().putString("questionType", item).apply();
            }
        });

        Switch swipeSwitch = (Switch) findViewById(R.id.swipeSwitch);
        swipeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    sharedPreferences.edit().putBoolean("swipeOff", true).apply();
                } else {
                    sharedPreferences.edit().putBoolean("swipeOff", false).apply();
                }
            }
        });

        Switch slideSwitch = (Switch) findViewById(R.id.slideSwitch);
        slideSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    sharedPreferences.edit().putBoolean("slideOff", true).apply();
                } else {
                    sharedPreferences.edit().putBoolean("slideOff", false).apply();
                }
            }
        });

        Switch rotationSwitch = (Switch) findViewById(R.id.rotationSwitch);
        rotationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    sharedPreferences.edit().putBoolean("rotationOff", true).apply();
                } else {
                    sharedPreferences.edit().putBoolean("rotationOff", false).apply();
                }
            }
        });

        Button saveButton = (Button) findViewById(R.id.saveSettingsButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int numQuest = Integer.parseInt(questionNumInput.getText().toString());
                    sharedPreferences.edit().putInt("questionAmount", numQuest).apply();
                    goToMain();
                } catch(NumberFormatException er){
                    makeToast("Invalid Question Number");
                }
            }
        });

        SeekBar mathDiff = (SeekBar) findViewById(R.id.mathSlider);

        mathDiff.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sharedPreferences.edit().putInt("mathDifficulty", progress).apply();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        boolean swipe = sharedPreferences.getBoolean("swipeOff", false);
        boolean rotate = sharedPreferences.getBoolean("rotationOff", false);
        boolean slide = sharedPreferences.getBoolean("slideOff", false);
        int math = sharedPreferences.getInt("mathDifficulty", 0);
        int numQ = sharedPreferences.getInt("questionAmount", 10);

        if(swipe) {
            swipeSwitch.setChecked(true);
        }
        if(rotate) {
            rotationSwitch.setChecked(true);
        }
        if(slide) {
            slideSwitch.setChecked(true);
        }
        mathDiff.setProgress(math);
        questionNumInput.setText(Integer.toString(numQ));
    }
    public void readQuestionTypes() {
        //put other question identifiers into items here
    }
    public void makeToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
    }
    public void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void loadSharedPref() {
        sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
    }
}