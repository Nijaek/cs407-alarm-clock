package com.cs407.alarm_clock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResultScreen extends AppCompatActivity {
    int updatedNumQuest;

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
        }

        TextView questionsLeft = (TextView) findViewById(R.id.questionsLeft);
        questionsLeft.setText("Questions Left: " + Integer.toString(updatedNumQuest));


        Button nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
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
}