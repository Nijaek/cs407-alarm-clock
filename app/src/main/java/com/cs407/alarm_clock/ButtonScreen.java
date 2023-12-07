package com.cs407.alarm_clock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class ButtonScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_screen);

        TextView questionSpace = (TextView) findViewById(R.id.buttonQuestion);
        Intent oldIntent = getIntent();
        String question = oldIntent.getStringExtra("q");
        questionSpace.setText(question);
        String answer = oldIntent.getStringExtra("ans");
        String fakeAnswer1 = oldIntent.getStringExtra("f1");
        String fakeAnswer2 = oldIntent.getStringExtra("f2");
        String fakeAnswer3 = oldIntent.getStringExtra("f3");

        ArrayList<String> options = new ArrayList<String>();
        options.add(answer);
        options.add(fakeAnswer1);
        options.add(fakeAnswer2);
        options.add(fakeAnswer3);
        //shuffle the arraylist
        Collections.shuffle(options);

        Button option1 = (Button) findViewById(R.id.option1);
        option1.setText(options.get(0));
        Button option2 = (Button) findViewById(R.id.option2);
        option2.setText(options.get(1));
        Button option3 = (Button) findViewById(R.id.option3);
        option3.setText(options.get(2));
        Button option4 = (Button) findViewById(R.id.option4);
        option4.setText(options.get(3));

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(options.get(0).equals(answer)) {
                    goToResults("Correct");
                } else {
                    goToResults("Incorrect\n" + question + " is\n" + answer);
                }
            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(options.get(1).equals(answer)) {
                    goToResults("Correct");
                } else {
                    goToResults("Incorrect\n" + question + " is\n" + answer);
                }
            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(options.get(2).equals(answer)) {
                    goToResults("Correct");
                } else {
                    goToResults("Incorrect\n" + question + " is\n" + answer);
                }
            }
        });
        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(options.get(3).equals(answer)) {
                    goToResults("Correct");
                } else {
                    goToResults("Incorrect\n" + question + " is\n" + answer);
                }
            }
        });

        Button skipButton = (Button) findViewById(R.id.skipButton);
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToResults("Skipped");
            }
        });

        Button switchButton = (Button) findViewById(R.id.switchButton);
        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToShake();
            }
        });


    }

    public void goToResults(String result) {
        Intent intent = new Intent(this, ResultScreen.class);
        intent.putExtra("result", result);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void goToShake() {
        Intent intent = new Intent(this, ShakeScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}