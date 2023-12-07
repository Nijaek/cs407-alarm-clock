package com.cs407.alarm_clock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Display;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class RotateScreen extends AppCompatActivity {

    public String topContent;
    public String leftContent;
    public String rightContent;
    TextView topText;
    TextView leftText;
    TextView rightText;
    TextView bottomText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate_screen);

        topText = (TextView) findViewById(R.id.topText);
        leftText = (TextView) findViewById(R.id.leftText);
        rightText = (TextView) findViewById(R.id.rightText);
        bottomText = (TextView) findViewById(R.id.bottomText);

        TextView questionSpace = (TextView) findViewById(R.id.rotatePrompt);
        Intent oldIntent = getIntent();
        String question = oldIntent.getStringExtra("q");
        questionSpace.setText(question);
        String answer = oldIntent.getStringExtra("ans");
        String fakeAnswer1 = oldIntent.getStringExtra("f1");
        String fakeAnswer2 = oldIntent.getStringExtra("f2");

        ArrayList<String> options = new ArrayList<String>();
        options.add(answer);
        options.add(fakeAnswer1);
        options.add(fakeAnswer2);
        //shuffle the arraylist
        Collections.shuffle(options);

        topContent = options.get(0);
        rightContent = options.get(1);
        leftContent = options.get(2);



        OrientationEventListener mOrientationListener = new OrientationEventListener(this, SensorManager.SENSOR_DELAY_NORMAL) {
            @Override
            public void onOrientationChanged(int orientation) {
                if(orientation == OrientationEventListener.ORIENTATION_UNKNOWN){
                    return;
                }
                if(orientation > 65 && orientation < 115) {
                    //left is between 45 and 135
                    topText.setText(leftContent);
                    leftText.setText(" ");
                    rightText.setText(topContent);
                    bottomText.setText(rightContent);
                } else if(orientation > 245 && orientation < 295){
                    //right is between 225 and 315
                    topText.setText(rightContent);
                    leftText.setText(topContent);
                    rightText.setText(" ");
                    bottomText.setText(leftContent);
                }
            }
        };
        if(mOrientationListener.canDetectOrientation() == true) {
            mOrientationListener.enable();
        } else {
            mOrientationListener.disable();
        }

        Button inputButton = (Button) findViewById(R.id.inputButton);
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int orientation = display.getRotation();

                if(orientation == Surface.ROTATION_0) {
                    //up
                    if(options.get(0).equals(answer)) {
                        goToResults("Correct");
                    } else {
                        goToResults("Incorrect\n" + question + " is\n" + answer);
                    }
                } else if(orientation == Surface.ROTATION_90) {
                    //right
                    if(options.get(1).equals(answer)) {
                        goToResults("Correct");
                    } else {
                        goToResults("Incorrect\n" + question + " is\n" + answer);
                    }
                } else if(orientation == Surface.ROTATION_180) {
                    //unused (would be down)
                } else if(orientation == Surface.ROTATION_270) {
                    //left
                    if(options.get(2).equals(answer)) {
                        goToResults("Correct");
                    } else {
                        goToResults("Incorrect\n" + question + " is\n" + answer);
                    }
                }
                goToMain();
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

    public void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int orientation = display.getRotation();
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if(orientation == Surface.ROTATION_90) {
                topText.setText(rightContent);
                leftText.setText(topContent);
                rightText.setText(" ");
                bottomText.setText(leftContent);
            } else if(orientation == Surface.ROTATION_270) {
                topText.setText(leftContent);
                leftText.setText(" ");
                rightText.setText(topContent);
                bottomText.setText(rightContent);
            }
        } else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            topText.setText(topContent);
            leftText.setText(leftContent);
            rightText.setText(rightContent);
            bottomText.setText(" ");
        }
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