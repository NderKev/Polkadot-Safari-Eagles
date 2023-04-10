package com.example.smartcare;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

public class PersonalData extends AppCompatActivity {

    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);
        back = findViewById(R.id.back);

        back.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), Settings.class
            );
            startActivity(secondActivityIntent);
        });
    }
}