package com.example.smartmedicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class HOmeTwo extends AppCompatActivity {

    ImageButton myaccountpersonaldata, myaccountenp, otherpersonaldata, otheren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_two);

        myaccountpersonaldata = findViewById(R.id.btnpersonaldataback);
        myaccountenp = findViewById(R.id.btnemailpaymentback);
        otherpersonaldata = findViewById(R.id.btnothersettingsback);
        otheren = findViewById(R.id.btnemailpaymentbackothersettings);

        myaccountpersonaldata.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), PersonalData.class
            );
            startActivity(secondActivityIntent);
        });
        myaccountenp.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), EmailPayment.class
            );
            startActivity(secondActivityIntent);
        });
        otherpersonaldata.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), PersonalData.class
            );
            startActivity(secondActivityIntent);
        });
        otheren.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), EmailPayment.class
            );
            startActivity(secondActivityIntent);
        });
    }
}