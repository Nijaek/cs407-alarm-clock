package com.cs407.alarm_clock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class puzzleLooper extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_looper);

        SharedPreferences sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        String qType = sharedPreferences.getString("questionType", "Math");

        if(qType.equals("Math")) {
            Intent intent = new Intent(this, MathGenerator.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            //insert custom question reader here.

        }
    }
}