package com.example.smartmedicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCanceledListener;

public class MainActivity extends AppCompatActivity {

    LoginResponce loginResponse;
    TextView username;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name, email;
    Button signOutBtn;

    ImageButton menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = getIntent();
        if(intent.getExtras() != null){
            loginResponse = (LoginResponce) intent.getSerializableExtra("data");
            username.setText(loginResponse.getUsername());
            Log.e("TAG", "=====>"+ loginResponse.getEmail());
        }
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        menu = findViewById(R.id.menu);
        username = findViewById(R.id.username);
        menu.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), HOmeTwo.class
            );
            startActivity(secondActivityIntent);
        });

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            name.setText(personName);
            email.setText(personEmail);
        }

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }

            void signOut() {
                gsc.signOut().addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        finish();
                        startActivity(new Intent(MainActivity.this, LoginPage.class));

                    }
                });
            }
        });
    }
}