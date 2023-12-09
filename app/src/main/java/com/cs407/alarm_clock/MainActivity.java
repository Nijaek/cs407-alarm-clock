package com.cs407.alarm_clock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);

        boolean swipe = sharedPreferences.getBoolean("swipeOff", false);
        boolean rotate = sharedPreferences.getBoolean("rotationOff", false);
        boolean slide = sharedPreferences.getBoolean("slideOff", false);
        int math = sharedPreferences.getInt("mathDifficulty", 0);
        int numQ = sharedPreferences.getInt("questionAmount", 10);

        Button buttonS = findViewById(R.id.buttonS);
        buttonS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSet();
            }
        });
        Button button = findViewById(R.id.buttonM);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMain();
            }
        });
    }
    public void goToMain() {
        Intent intent = new Intent(this, MathGenerator.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    public void goToSet() {
        Intent intent = new Intent(this, SettingsScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}