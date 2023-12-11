package com.cs407.alarm_clock;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collections;

public class QuestionReader extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_reader);

        // Open connection
        QuestionDataSource questionDataSource = new QuestionDataSource(this);
        questionDataSource.open();

        // Generate random problem
        QuestionObject q = questionDataSource.getRandomQuestion();

        // Close connection
        questionDataSource.close();

        // Navigate
        goToPuzzle(q.getQuestion(), q.getAnswer(), q.getFalse1(), q.getFalse2(), q.getFalse3());
    }

    public void goToPuzzle(String q, String ans, String f1, String f2, String f3) {
        SharedPreferences sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        ArrayList<String> options = new ArrayList<String>();
        options.add("Button");

        boolean swipe = sharedPreferences.getBoolean("swipeOff", false);
        boolean rotate = sharedPreferences.getBoolean("rotationOff", false);
        boolean slide = sharedPreferences.getBoolean("slideOff", false);

        if(!swipe) {
            options.add("Swipe");
        }
        if(!rotate) {
            options.add("Rotate");
        }
        if(!slide) {
            options.add("Drag"); //
        }
        //shuffle the arraylist
        Collections.shuffle(options);

        if(options.get(0).equals("Button")) {
            Intent intent = new Intent(this, ButtonScreen.class);
            intent.putExtra("q", q);
            intent.putExtra("ans", ans);
            intent.putExtra("f1", f1);
            intent.putExtra("f2", f2);
            intent.putExtra("f3", f3);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else if(options.get(0).equals("Drag")) {
            Intent intent = new Intent(this, DragScreen.class);
            intent.putExtra("q", q);
            intent.putExtra("ans", ans);
            intent.putExtra("f1", f1);
            intent.putExtra("f2", f2);
            intent.putExtra("f3", f3);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else if(options.get(0).equals("Swipe")) {
            Intent intent = new Intent(this, SwipeScreen.class);
            intent.putExtra("q", q);
            intent.putExtra("ans", ans);
            intent.putExtra("f1", f1);
            intent.putExtra("f2", f2);
            intent.putExtra("f3", f3);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else if(options.get(0).equals("Rotate")) {
            Intent intent = new Intent(this, RotateScreen.class);
            intent.putExtra("q", q);
            intent.putExtra("ans", ans);
            intent.putExtra("f1", f1);
            intent.putExtra("f2", f2);
            intent.putExtra("f3", f3);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
