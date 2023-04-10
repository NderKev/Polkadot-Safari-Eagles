package com.example.smartcare;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class LandingThree extends AppCompatActivity {

    ImageButton btnnextthree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_three);

        btnnextthree = findViewById(R.id.btnGetStarted);
        btnnextthree.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), Login.class
            );
            startActivity(secondActivityIntent);
        });
    }
}