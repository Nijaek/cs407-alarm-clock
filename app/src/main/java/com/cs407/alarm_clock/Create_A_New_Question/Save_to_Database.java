package com.cs407.alarm_clock.Create_A_New_Question;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cs407.alarm_clock.Creation_of_A_New_Question;
import com.cs407.alarm_clock.R;

public class Save_to_Database extends AppCompatActivity {
private TextView questionTextView;
private RadioGroup answersRadioGroup;
private Button saveButton;

// Assume you have a model class for Question and Answer
private Creation_of_A_New_Question currentQuestion;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_a_new_question);

        questionTextView = findViewById(R.id.question);
        answersRadioGroup = findViewById(R.id.right_answer);
        saveButton = findViewById(R.id.save_button);

        // Load the current question
        loadQuestion();

        saveButton.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        int selectedAnswerId = answersRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedAnswerId);
        if (selectedRadioButton != null) {
        saveAnswer(currentQuestion, selectedRadioButton.getText().toString());
        } else {
        // No answer was selected
        Toast.makeText(Save_to_Database.this, "Please select an answer", Toast.LENGTH_SHORT).show();
        }
        }
        });
        }

private void loadQuestion() {
        // This would be retrieved from your database or API
        // For demonstration, let's assume you have a method to get the current question
        currentQuestion = getQuestion();

        questionTextView.setText(currentQuestion.getQuestionText());
        // Dynamically create radio buttons for answers
        for (String answer : currentQuestion.getAnswers()) {
        RadioButton radioButton = new RadioButton(this);
        radioButton.setText(answer);
        answersRadioGroup.addView(radioButton);
        }
   }

private void saveAnswer(Creation_of_A_New_Question question, String answer) {
        // Here you would implement the logic to save the answer to the database
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        boolean isInserted = dbHelper.insertAnswer(question.getId(), answer);
        if (isInserted) {
        Toast.makeText(this, "Answer saved successfully", Toast.LENGTH_SHORT).show();
        } else {
        Toast.makeText(this, "Failed to save answer", Toast.LENGTH_SHORT).show();
        }
        }

    private Creation_of_A_New_Question getQuestion() {
        // Stub method for getting a question
        return new Creation_of_A_New_Question(); // Replace with actual method to retrieve question
    }
}
