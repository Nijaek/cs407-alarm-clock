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

        TextView questionSpace = (TextView) findViewById(R.id.dragQuestion);
        Intent oldIntent = getIntent();
        String question = oldIntent.getStringExtra("q");
        questionSpace.setText(question);
        int answer = Integer.parseInt(oldIntent.getStringExtra("ans"));
        int fakeAnswer1 = Integer.parseInt(oldIntent.getStringExtra("f1"));
        int fakeAnswer2 = Integer.parseInt(oldIntent.getStringExtra("f2"));
        int fakeAnswer3 = Integer.parseInt(oldIntent.getStringExtra("f3"));

        ArrayList<String> options = new ArrayList<String>();
        options.add(Integer.toString(answer));
        options.add(Integer.toString(fakeAnswer1));
        options.add(Integer.toString(fakeAnswer2));
        options.add(Integer.toString(fakeAnswer3));
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
                if(Integer.parseInt(options.get(0)) == answer) {
                    goToResults("Correct");
                } else {
                    goToResults("Incorrect\n" + question + " is\n" + Integer.toString(answer));
                }
            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(options.get(1)) == answer) {
                    goToResults("Correct");
                } else {
                    goToResults("Incorrect\n" + question + " is\n" + Integer.toString(answer));
                }
            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(options.get(2)) == answer) {
                    goToResults("Correct");
                } else {
                    goToResults("Incorrect\n" + question + " is\n" + Integer.toString(answer));
                }
            }
        });
        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(options.get(3)) == answer) {
                    goToResults("Correct");
                } else {
                    goToResults("Incorrect\n" + question + " is\n" + Integer.toString(answer));
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