package com.cs407.alarm_clock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class DragScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_screen);

        TextView questionSpace = (TextView) findViewById(R.id.dragQuestion);
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

        TextView option1 = (TextView) findViewById(R.id.answer1text);
        option1.setText(options.get(0));
        TextView option2 = (TextView) findViewById(R.id.answer2text);
        option2.setText(options.get(1));
        TextView option3 = (TextView) findViewById(R.id.answer3text);
        option3.setText(options.get(2));
        TextView option4 = (TextView) findViewById(R.id.answer4text);
        option4.setText(options.get(3));

        SeekBar seek1 = (SeekBar) findViewById(R.id.seek1);
        SeekBar seek2 = (SeekBar) findViewById(R.id.seek2);
        SeekBar seek3 = (SeekBar) findViewById(R.id.seek3);
        SeekBar seek4 = (SeekBar) findViewById(R.id.seek4);

        seek1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress > 99) {
                    seek1.setProgress(0);
                    if(options.get(0).equals(answer)) {
                        goToResults("Correct");
                    } else {
                        goToResults("Incorrect\n" + question + " is\n" + answer);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seek2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress > 99) {
                    seek2.setProgress(0);
                    if(options.get(1).equals(answer)) {
                        goToResults("Correct");
                    } else {
                        goToResults("Incorrect\n" + question + " is\n" + answer);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seek3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress > 99) {
                    seek3.setProgress(0);
                    if(options.get(2).equals(answer)) {
                        goToResults("Correct");
                    } else {
                        goToResults("Incorrect\n" + question + " is\n" + answer);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seek4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress > 99) {
                    seek4.setProgress(0);
                    if(options.get(3).equals(answer)) {
                        goToResults("Correct");
                    } else {
                        goToResults("Incorrect\n" + question + " is\n" + answer);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button skipButton = (Button) findViewById(R.id.skipButton);
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToResults("Skipped");
            }
        });
    }
    public void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void goToResults(String result) {
        Intent intent = new Intent(this, ResultScreen.class);
        intent.putExtra("result", result);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}