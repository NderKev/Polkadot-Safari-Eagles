package com.example.smartcare;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class Home extends AppCompatActivity {

    ImageButton btnmenu, btnchat, btnprofile;
    Button records;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnmenu = findViewById(R.id.menu);
        btnchat = findViewById(R.id.btnChat);
        btnprofile = findViewById(R.id.btnProfile);
        records = findViewById(R.id.btnrecord);

        btnmenu.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), Settings.class
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
        records.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), RecordsQRScan.class
            );
            startActivity(secondActivityIntent);
        });
    }
}