package com.cs407.alarm_clock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class SettingsScreen extends AppCompatActivity {
    String[] item = {"Math"}; //Need a function to read in other Question types
    AutoCompleteTextView autoCompleteTextview;
    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);

        autoCompleteTextview = findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, item);

        autoCompleteTextview.setAdapter(adapterItems);

        autoCompleteTextview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = adapterItems.getItem(position);
            }
        });


    }
    public void readQuestionTypes() {
        //put other question identifiers into items here
    }
    public void goToMain() {
        Intent intent = new Intent(this, MathGenerator.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}