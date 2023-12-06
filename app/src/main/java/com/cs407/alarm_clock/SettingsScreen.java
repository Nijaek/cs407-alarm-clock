package com.cs407.alarm_clock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsScreen extends AppCompatActivity {
    String[] item = {"Math"}; //Need a function to read in other Question types
    AutoCompleteTextView autoCompleteTextview;
    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);

        EditText questionNumInput = (EditText) findViewById(R.id.questionNumberInput);

        autoCompleteTextview = findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, item);

        autoCompleteTextview.setAdapter(adapterItems);

        autoCompleteTextview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = adapterItems.getItem(position);
            }
        });

        Button saveButton = (Button) findViewById(R.id.saveSettingsButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int numQuest = Integer.parseInt(questionNumInput.getText().toString());
                    goToMain();
                } catch(NumberFormatException er){
                    makeToast("Invalid Question Number");
                }
            }
        });
    }
    public void readQuestionTypes() {
        //put other question identifiers into items here
    }
    public void makeToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
    }
    public void goToMain() {
        Intent intent = new Intent(this, MathGenerator.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}