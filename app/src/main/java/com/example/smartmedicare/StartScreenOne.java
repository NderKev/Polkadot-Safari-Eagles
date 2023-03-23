package com.example.smartmedicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class StartScreenOne extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen_one);

        ImageButton buttonSecondActivity = findViewById(
                R.id.btnNext
        );
        buttonSecondActivity.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), StartScreenTwo.class
            );
            startActivity(secondActivityIntent);
        });
    }
}