package com.example.smartcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class RecordsQRScan extends AppCompatActivity {

    ImageButton btnback, btnchat, btnprofile, btnscanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records_qrscan);

        btnback = findViewById(R.id.back);
        btnchat = findViewById(R.id.btnChat);
        btnprofile = findViewById(R.id.btnProfile);
        btnscanner = findViewById(R.id.backgroundn);

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
        btnscanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(RecordsQRScan.this);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setPrompt("Scan a QR Code");
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                intentIntegrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(intentResult != null){
            String contents = intentResult.getContents();
            if(contents != null){
//                textView.setText(intentResult.getContents());
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
}