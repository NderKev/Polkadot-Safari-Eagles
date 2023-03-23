package com.example.smartmedicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class SignUpDoctor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_doctor);

        ImageButton buttonSecondActivity = findViewById(
                R.id.btnSignUp
        );
        ImageButton buttontologin = findViewById(
                R.id.btnlogin
        );
        ImageButton buttontojoin = findViewById(
                R.id.btnJoin
        );
        buttonSecondActivity.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), LoginPage.class
            );
            startActivity(secondActivityIntent);
        });
        buttontologin.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), LoginPage.class
            );
            startActivity(secondActivityIntent);
        });
        buttontojoin.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), SignUpPatient.class
            );
            startActivity(secondActivityIntent);
        });
    }
}