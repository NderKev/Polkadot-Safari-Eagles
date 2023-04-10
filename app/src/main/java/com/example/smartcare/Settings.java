package com.example.smartcare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {

    ImageButton btnback, btnchat, btnprofile, myaccountpersonaldata, otherpersonaldata, myaccountenp, otherenp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btnback = findViewById(R.id.back);
        btnchat = findViewById(R.id.btnChat);
        btnprofile = findViewById(R.id.btnProfile);

        myaccountpersonaldata = findViewById(R.id.btnpersonaldataback);
        otherpersonaldata = findViewById(R.id.btnothersettingsback);
        myaccountenp = findViewById(R.id.btnemailpaymentback);
        otherenp = findViewById(R.id.btnemailpaymentbackothersettings);

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

        myaccountpersonaldata.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), PersonalData.class
            );
            startActivity(secondActivityIntent);
        });
        otherpersonaldata.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), PersonalData.class
            );
            startActivity(secondActivityIntent);
        });
        myaccountenp.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), EmailnPayment.class
            );
            startActivity(secondActivityIntent);
        });
        otherenp.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), EmailnPayment.class
            );
            startActivity(secondActivityIntent);
        });
    }
}