package com.cs407.alarm_clock;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import kotlinx.coroutines.flow.SharedFlow;

public class MathGenerator extends AppCompatActivity {

    public int answer;
    public String question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_generator);

        answer = 0;
        int fakeAnswer1 = 0;
        int fakeAnswer2 = 0;
        int fakeAnswer3 = 0;
        question = null;

        Random r = new Random();
        //use a random int to choose the type of math problem
        int mathOptions = r.nextInt(5);
        //Change the range to account for difficulties

        if(mathOptions == 0) {
            //addition
            int problemPart1 = r.nextInt(12);
            int problemPart2 = r.nextInt(12);

            answer = problemPart1 + problemPart2;
            question = Integer.toString(problemPart1) + " + " + Integer.toString(problemPart2);
            fakeAnswer1 = answer + r.nextInt(5) + 1;
            fakeAnswer2 = answer - r.nextInt(5) - 1;

            //having the 3rd fake answer be above the answer, and making sure it isn't a duplicate
            int whereDoes3Go = r.nextInt(2);
            if(whereDoes3Go == 0) {
                fakeAnswer3 = answer + r.nextInt(10) + 1;
            } else {
                fakeAnswer3 = answer - r.nextInt(10) - 1;
            }
            while(fakeAnswer3 == fakeAnswer1 || fakeAnswer3 == fakeAnswer2) {
                if(whereDoes3Go == 0) {
                    fakeAnswer3 = answer + r.nextInt(10)+ 1;
                } else {
                    fakeAnswer3 = answer - r.nextInt(10) - 1;
                }
            }
        } else if(mathOptions == 1) {
            //subtraction
            int problemPart1 = r.nextInt(12);
            int problemPart2 = r.nextInt(12);

            answer = problemPart1 - problemPart2;
            question = Integer.toString(problemPart1) + " - " + Integer.toString(problemPart2);
            fakeAnswer1 = answer + r.nextInt(5) + 1;
            fakeAnswer2 = answer - r.nextInt(5) - 1;

            //having the 3rd fake answer be above the answer, and making sure it isn't a duplicate
            int whereDoes3Go = r.nextInt(2);
            if(whereDoes3Go == 0) {
                fakeAnswer3 = answer + r.nextInt(10)+ 1;
            } else {
                fakeAnswer3 = answer - r.nextInt(10) - 1;
            }
            while(fakeAnswer3 == fakeAnswer1 || fakeAnswer3 == fakeAnswer2) {
                if(whereDoes3Go == 0) {
                    fakeAnswer3 = answer + r.nextInt(10)+ 1;
                } else {
                    fakeAnswer3 = answer - r.nextInt(10) - 1;
                }
            }
        } else if(mathOptions == 2) {
            //multiplication
            int problemPart1 = r.nextInt(12);
            int problemPart2 = r.nextInt(12);

            answer = problemPart1 * problemPart2;
            question = Integer.toString(problemPart1) + " x " + Integer.toString(problemPart2);
            fakeAnswer1 = answer + r.nextInt(20) + 1;
            fakeAnswer2 = answer - r.nextInt(20) - 1;

            //having the 3rd fake answer be above the answer, and making sure it isn't a duplicate
            int whereDoes3Go = r.nextInt(2);
            if(whereDoes3Go == 0) {
                fakeAnswer3 = answer + r.nextInt(30)+ 1;
            } else {
                fakeAnswer3 = answer - r.nextInt(30) - 1;
            }
            while(fakeAnswer3 == fakeAnswer1 || fakeAnswer3 == fakeAnswer2 || fakeAnswer3 == 0) {
                if(whereDoes3Go == 0) {
                    fakeAnswer3 = answer + r.nextInt(30)+ 1;
                } else {
                    fakeAnswer3 = answer - r.nextInt(30) - 1;
                }
            }
        } else if(mathOptions == 3) {
            //powers
            int problemPart1 = r.nextInt(4) + 2;
            int problemPart2 = r.nextInt(6);
            //need to convert to doubles
            double pow1 = problemPart1;
            double pow2 = problemPart2;

            answer = (int)Math.pow(pow1, pow2);
            question = Integer.toString(problemPart1) + "^" + Integer.toString(problemPart2);
            fakeAnswer1 = (int)Math.pow(pow1, pow2 + 1);
            fakeAnswer2 = (int)Math.pow(pow1, pow2 - 1);

            //having the 3rd fake answer be above the answer, and making sure it isn't a duplicate
            int whereDoes3Go = r.nextInt(2);
            if(whereDoes3Go == 0) {
                fakeAnswer3 = answer + r.nextInt(10)+ 1;
            } else {
                fakeAnswer3 = answer - r.nextInt(10) - 1;
            }
            while(fakeAnswer3 == fakeAnswer1 || fakeAnswer3 == fakeAnswer2) {
                if(whereDoes3Go == 0) {
                    fakeAnswer3 = answer + r.nextInt(10)+ 1;
                } else {
                    fakeAnswer3 = answer - r.nextInt(10) - 1;
                }
            }
        }else if(mathOptions == 4) {
            //Algebra
            int problemPart1 = r.nextInt(12) + 1;
            answer = r.nextInt(12);
            int problemPart2 = problemPart1 * answer;


            question = Integer.toString(problemPart1) + "X = " + Integer.toString(problemPart2) + "\nWhat is X?";
            fakeAnswer1 = answer + r.nextInt(5) + 1;
            fakeAnswer2 = answer - r.nextInt(5) - 1;

            //having the 3rd fake answer be above the answer, and making sure it isn't a duplicate
            int whereDoes3Go = r.nextInt(2);
            if(whereDoes3Go == 0) {
                fakeAnswer3 = answer + r.nextInt(10)+ 1;
            } else {
                fakeAnswer3 = answer - r.nextInt(10) - 1;
            }
            while(fakeAnswer3 == fakeAnswer1 || fakeAnswer3 == fakeAnswer2) {
                if(whereDoes3Go == 0) {
                    fakeAnswer3 = answer + r.nextInt(10)+ 1;
                } else {
                    fakeAnswer3 = answer - r.nextInt(10) - 1;
                }
            }
        }else {
            Log.d(TAG ,"Options not generated properly");
            //division could be implemented later
        }

        //Passing the values to the puzzle
        String ans = Integer.toString(answer);
        String fake1 = Integer.toString(fakeAnswer1);
        String fake2 = Integer.toString(fakeAnswer2);
        String fake3 = Integer.toString(fakeAnswer3);
        goToPuzzle(question, ans, fake1, fake2, fake3);
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