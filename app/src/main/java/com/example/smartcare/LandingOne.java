package com.example.smartcare;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class LandingOne extends AppCompatActivity {

    ImageButton btnnext,btnskip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_one);

        btnnext = findViewById(R.id.btnNext);
        btnskip = findViewById(R.id.btnSkip);
        btnnext.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), LandingTwo.class
            );
            startActivity(secondActivityIntent);
        });
        btnskip.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), Login.class
            );
            startActivity(secondActivityIntent);
        });
    }
}