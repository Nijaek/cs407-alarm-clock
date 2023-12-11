package com.cs407.alarm_clock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;

public class puzzleInitialize extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_initialize);

        SharedPreferences sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        int numQuest = sharedPreferences.getInt("questionAmount", 10);
        sharedPreferences.edit().putInt("currentNumQuest", numQuest).apply();

        boolean alarmActive = true;
        sharedPreferences.edit().putBoolean("alarmActive", alarmActive).apply();

        Intent intent = new Intent(this, puzzleLooper.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}