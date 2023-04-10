package com.example.smartcare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class RecordProfile extends AppCompatActivity {

    ImageButton btnback, btnchat, btnprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_profile);

        btnback = findViewById(R.id.back);
        btnchat = findViewById(R.id.btnChat);
        btnprofile = findViewById(R.id.btnProfile);

        btnback.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), Home.class
            );
            startActivity(secondActivityIntent);
        });
        btnchat.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), Chat.class
            );
            startActivity(secondActivityIntent);
        });
        btnprofile.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), ProfilePatient.class
            );
            startActivity(secondActivityIntent);
        });

    }
}