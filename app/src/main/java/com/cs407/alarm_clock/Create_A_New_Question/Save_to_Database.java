package com.cs407.alarm_clock.Create_A_New_Question;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cs407.alarm_clock.Creation_of_A_New_Question;
import com.cs407.alarm_clock.R;

public class Save_to_Database extends AppCompatActivity {
        private EditText questionEditText, rightAnswerEditText, falseAnswerAEditText, falseAnswerBEditText, falseAnswerCEditText;
        private Button saveButton, cancelButton, returnButton;

        // Assume you have a model class for Question and Answers
        private Creation_of_A_New_Question currentQuestion;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_create_a_new_question); // Ensure this layout matches the diagram

                questionEditText = findViewById(R.id.question);
                rightAnswerEditText = findViewById(R.id.right_answer);
                falseAnswerAEditText = findViewById(R.id.false_answer_a);
                falseAnswerBEditText = findViewById(R.id.false_answer_a);
                falseAnswerCEditText = findViewById(R.id.false_answer_a);
                saveButton = findViewById(R.id.save_button);
                cancelButton = findViewById(R.id.cancelButton);
                returnButton = findViewById(R.id.return_menu_button);

                // Load the current question if editing an existing one
                loadQuestion();

                saveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                saveQuestion();
                        }
                });

                cancelButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                // Clear all fields
                                questionEditText.setText("");
                                rightAnswerEditText.setText("");
                                falseAnswerAEditText.setText("");
                                falseAnswerBEditText.setText("");
                                falseAnswerCEditText.setText("");
                        }
                });

                returnButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                // Return to the previous menu
                                finish();
                        }
                });
        }

        private void loadQuestion() {
                // Load a question if necessary or prepare to create a new one
                currentQuestion = getQuestion(); // Replace with actual method to retrieve a question

                if (currentQuestion != null) {
                        questionEditText.setText(currentQuestion.getQuestionText());
                        // Set the right answer and false answers if they exist
                }
        }

        private void saveQuestion() {
                // Implement logic to save the question and answers
                // Validate inputs before saving
                if (validateInputs()) {
                        // Assuming your Creation_of_A_New_Question model has a method to set question and answers
                        currentQuestion.setQuestionText(questionEditText.getText().toString());
                        currentQuestion.setRightAnswer(rightAnswerEditText.getText().toString());
                        currentQuestion.setFalseAnswers(new String[]{
                                falseAnswerAEditText.getText().toString(),
                                falseAnswerBEditText.getText().toString(),
                                falseAnswerCEditText.getText().toString()
                        });

                        // Your DatabaseHelper should have a method to insert or update a question
                        DatabaseHelper dbHelper = new DatabaseHelper(this);
                        boolean isSaved = dbHelper.saveQuestion(currentQuestion);
                        if (isSaved) {
                                Toast.makeText(this, "Question saved successfully", Toast.LENGTH_SHORT).show();
                        } else {
                                Toast.makeText(this, "Failed to save question", Toast.LENGTH_SHORT).show();
                        }
                }
        }

        private boolean validateInputs() {
                // Check if any field is empty
                if (questionEditText.getText().toString().trim().isEmpty() ||
                        rightAnswerEditText.getText().toString().trim().isEmpty() ||
                        falseAnswerAEditText.getText().toString().trim().isEmpty() ||
                        falseAnswerBEditText.getText().toString().trim().isEmpty() ||
                        falseAnswerCEditText.getText().toString().trim().isEmpty()) {

                        Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                        return false;
                }
                return true;
        }

        private Creation_of_A_New_Question getQuestion() {
                // Stub method for getting a question
                // Replace with actual method to retrieve question
                // This method should return null if creating a new question
                return null;
        }
}

