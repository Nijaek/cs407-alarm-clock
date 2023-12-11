package com.cs407.alarm_clock;

import android.util.Log;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class CustomQuestion extends ToolbarActivity {
    private EditText questionEditText, rightAnswerEditText, falseAnswerAEditText, falseAnswerBEditText, falseAnswerCEditText;
    private Button saveButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_question);

        // Initialize field inputs
        questionEditText = findViewById(R.id.question);
        rightAnswerEditText = findViewById(R.id.right_answer);
        falseAnswerAEditText = findViewById(R.id.false_answer_a);
        falseAnswerBEditText = findViewById(R.id.false_answer_b);
        falseAnswerCEditText = findViewById(R.id.false_answer_c);

        // Strings
        String question = questionEditText.getText().toString();
        String rightAnswer = rightAnswerEditText.getText().toString();
        String falseAnswerA = falseAnswerAEditText.getText().toString();
        String falseAnswerB = falseAnswerBEditText.getText().toString();
        String falseAnswerC = falseAnswerCEditText.getText().toString();

        // Initialize Buttons
        saveButton = findViewById(R.id.save_button);
        cancelButton = findViewById(R.id.cancel_button);

        // Set up listeners for the buttons
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create data source and initialize
                QuestionDataSource questionDataSource = new QuestionDataSource(CustomQuestion.this);
                questionDataSource.open();

                // Save question
                QuestionObject questionObj = new QuestionObject(0, question, rightAnswer, falseAnswerA, falseAnswerB, falseAnswerC);
                long id = questionDataSource.createQuestion(questionObj);

                // Release memory
                questionDataSource.close();

                // Clear fields
                questionEditText.setText("");
                rightAnswerEditText.setText("");
                falseAnswerAEditText.setText("");
                falseAnswerBEditText.setText("");
                falseAnswerCEditText.setText("");

                // Notify
                showConfirmationDialog();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle cancel action
                startActivity(new Intent(CustomQuestion.this, AlarmOverview.class));
            }
        });
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Success");
        builder.setMessage("Your question has been saved successfully.");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                startActivity(new Intent(CustomQuestion.this, AlarmOverview.class));
            }
        });

        // Create and show the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}


