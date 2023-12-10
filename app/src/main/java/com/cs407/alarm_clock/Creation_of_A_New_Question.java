package com.cs407.alarm_clock;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


    public class Creation_of_A_New_Question extends AppCompatActivity {
        private EditText questionEditText, rightAnswerEditText, falseAnswerAEditText, falseAnswerBEditText, falseAnswerCEditText;
        private Button saveButton, cancelButton, returnMenuButton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_create_a_new_question);

            // Initialize EditTexts with correct IDs
            questionEditText = findViewById(R.id.question);
            rightAnswerEditText = findViewById(R.id.right_answer);
            falseAnswerAEditText = findViewById(R.id.false_answer_a);
            falseAnswerBEditText = findViewById(R.id.false_answer_b);
            falseAnswerCEditText = findViewById(R.id.false_answer_c);

            // Initialize Buttons with correct IDs
            saveButton = findViewById(R.id.save_button);
            cancelButton = findViewById(R.id.cancel_button);
            returnMenuButton = findViewById(R.id.return_menu_button);

            // Set up listeners for the buttons
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveQuestion();
                }
            });

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle cancel action
                    // For example: clear the EditText fields or close the activity
                }
            });

            returnMenuButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle return to menu action
                    // For example: Intent to start a new Activity or close this Activity
                }
            });
        }

        private void saveQuestion() {
            // Get the text from the EditTexts
            String question = questionEditText.getText().toString();
            String rightAnswer = rightAnswerEditText.getText().toString();
            String falseAnswerA = falseAnswerAEditText.getText().toString();
            String falseAnswerB = falseAnswerBEditText.getText().toString();
            String falseAnswerC = falseAnswerCEditText.getText().toString();

        }
    }

