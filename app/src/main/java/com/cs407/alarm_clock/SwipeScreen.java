package com.cs407.alarm_clock;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class SwipeScreen extends AppCompatActivity {

    LinearLayout relativeLayout;
    TextView swipeText;
    SwipeListener swipeListener;
    String answer;
    String leftText;
    boolean left = false;
    String rightText;
    boolean right = false;
    String topText;
    boolean up = false;
    String bottomText;
    boolean bottom = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_screen);

        TextView questionSpace = (TextView) findViewById(R.id.dragQuestion);
        Intent oldIntent = getIntent();
        String question = oldIntent.getStringExtra("q");
        questionSpace.setText(question);
        answer = oldIntent.getStringExtra("ans");
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

        leftText = options.get(0);
        rightText = options.get(1);
        topText = options.get(2);
        bottomText =options.get(3);

        relativeLayout = findViewById(R.id.relativeLayout);
        swipeText = findViewById(R.id.swipeText);

        swipeListener = new SwipeListener(relativeLayout);

        Button submitButton = (Button) findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(left) {
                    if(options.get(0).equals(answer)) {
                        goToResults("Correct");
                    } else {
                        goToResults("Incorrect\n" + question + " is\n" + answer);
                    }
                } else if(right) {
                    if(options.get(1).equals(answer)) {
                        goToResults("Correct");
                    } else {
                        goToResults("Incorrect\n" + question + " is\n" + answer);
                    }
                } else if(up) {
                    if(options.get(2).equals(answer)) {
                        goToResults("Correct");
                    } else {
                        goToResults("Incorrect\n" + question + " is\n" + answer);
                    }
                } else if(bottom) {
                    if(options.get(3).equals(answer)) {
                        goToResults("Correct");
                    } else {
                        goToResults("Incorrect\n" + question + " is\n" + answer);
                    }
                } else {
                    //Nothing selected
                    goToResults("Nothing Inputted");
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

    private class SwipeListener implements View.OnTouchListener {

        GestureDetector gestureDetector;
        SwipeListener(View view) {
            int threshold = 100;
            int velocity_threshold = 100;
            GestureDetector.SimpleOnGestureListener listener =
                    new GestureDetector.SimpleOnGestureListener() {
                        @Override
                        public boolean onDown(@NonNull MotionEvent e) {
                            return true;
                        }

                        @Override
                        public boolean onFling(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
                            float xDiff = e2.getX() - e1.getX();
                            float yDiff = e2.getY() - e1.getY();
                            try {
                                if(Math.abs(xDiff) > Math.abs(yDiff)) {
                                    if(Math.abs(xDiff) > threshold && Math.abs(velocityX) > velocity_threshold) {
                                        if(xDiff > 0) {
                                            swipeText.setText(rightText);
                                            right = true;
                                            left = false;
                                            up = false;
                                            bottom = false;
                                        } else {
                                            swipeText.setText(leftText);
                                            right = false;
                                            left = true;
                                            up = false;
                                            bottom = false;
                                        }
                                        return true;
                                    }
                                } else {
                                    if(Math.abs(yDiff) > threshold && Math.abs(velocityY) > velocity_threshold) {
                                        if(yDiff > 0) {
                                            swipeText.setText(bottomText);
                                            right = false;
                                            left = false;
                                            up = false;
                                            bottom = true;
                                        } else {
                                            swipeText.setText(topText);
                                            right = false;
                                            left = false;
                                            up = true;
                                            bottom = false;
                                        }
                                        return true;
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return false;
                        }
                    };
            gestureDetector = new GestureDetector(listener);
            view.setOnTouchListener(this);
        }
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }
    }
}