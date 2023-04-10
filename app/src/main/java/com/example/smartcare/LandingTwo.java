package com.example.smartcare;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class LandingTwo extends AppCompatActivity {

    ImageButton btnnexttwo,btnskiptwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_two);

        btnnexttwo = findViewById(R.id.btnNext);
        btnskiptwo = findViewById(R.id.btnSkip);
        btnnexttwo.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), LandingThree.class
            );
            startActivity(secondActivityIntent);
        });
        btnskiptwo.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), Login.class
            );
            startActivity(secondActivityIntent);
        });
    }
}