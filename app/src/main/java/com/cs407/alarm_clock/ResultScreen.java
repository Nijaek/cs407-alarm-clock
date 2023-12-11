package com.cs407.alarm_clock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResultScreen extends AppCompatActivity {
    int updatedNumQuest;
    private SoundPool soundPool;
    private int correct, wrong, congrats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);

        TextView resultView = (TextView) findViewById(R.id.resultView);
        Intent oldIntent = getIntent();
        String result = oldIntent.getStringExtra("result");
        resultView.setText(result);


        SharedPreferences sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        int numQuest = sharedPreferences.getInt("currentNumQuest", 10);

        if(result.equals("Correct")) {
            updatedNumQuest = numQuest - 1;
            sharedPreferences.edit().putInt("currentNumQuest", updatedNumQuest).apply();
        } else {
            updatedNumQuest = numQuest;
        }

        if(updatedNumQuest == 0) {
            result = "CONGRATULATIONS";
            resultView.setText(result);
        }

        if(result.equals("Correct")) {
            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.correct);
            mp.start();
        } else if(result.equals("CONGRATULATIONS")) {
            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.congrats);
            mp.start();
        } else if(result.equals("Skipped")){
            //do nothing
        } else {
            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
            mp.start();
        }

        TextView questionsLeft = (TextView) findViewById(R.id.questionsLeft);
        questionsLeft.setText("Questions Left: " + Integer.toString(updatedNumQuest));


        Button nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(updatedNumQuest == 0) {
                    exit();
                } else {
                    goToMain();
                }
            }
        });
    }
    public void goToMain() {
        Intent intent = new Intent(this, puzzleLooper.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    public void exit() {
        Intent intent = new Intent(this, alarm_overview.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}