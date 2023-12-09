package com.cs407.randomly_select_tasks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        Button firstFragmentButton = findViewById(R.id.llCenter);
        firstFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(R.id.llCenter, llTop.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("showing First")
                        .commit();
            }
        });

        Button secondFragmentButoon = findViewById(R.id.llTop);
        secondFragmentButoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(R.id.llCenter, llCenter.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("showing First")
                        .commit();
            }
        });
    }
}