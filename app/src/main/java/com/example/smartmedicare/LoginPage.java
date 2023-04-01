package com.example.smartmedicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPage extends AppCompatActivity {

    EditText edEmail, edPassword;

    ImageButton SignUp, Login;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    ImageView googleBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        SignUp = findViewById(R.id.btnSignUp);
        Login = findViewById(R.id.btnLogin);

        edEmail = findViewById(R.id.edtEmailAddress);
        edPassword = findViewById(R.id.edtPassword);

        googleBtn = findViewById(R.id.btnGoogle);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondActivityIntent = new Intent(
                        getApplicationContext(), MainActivity.class
                );
                startActivity(secondActivityIntent);
                SignIn();
            }
        });

        Login.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), HomeOne.class
            );
            startActivity(secondActivityIntent);
            if(TextUtils.isEmpty(edEmail.getText().toString()) || TextUtils.isEmpty(edPassword.getText())){
                String message = "All inputs required ...";
                Toast.makeText(LoginPage.this, message, Toast.LENGTH_LONG).show();
            }else{
                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setEmail(edEmail.getText().toString());
                loginRequest.setPassword(edPassword.getText().toString());
                loginUser(loginRequest);
            }
        });
        SignUp.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), SignUpPatient.class
            );
            startActivity(secondActivityIntent);

        });
    }

    void SignIn() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
        String message = "You have logged in with your google account ...";
        Toast.makeText(LoginPage.this, message, Toast.LENGTH_LONG).show();

    }
    public void loginUser(LoginRequest loginRequest ){
        Call<LoginResponce> loginResponseCall = ApiClient.getService().loginUser(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponce>() {
            @Override
            public void onResponse(Call<LoginResponce> call, Response<LoginResponce> response) {
                if(response.isSuccessful()){
                    LoginResponce loginResponce = response.body();
                    startActivity(new Intent(LoginPage.this, HomeOne.class).putExtra("data", loginResponce));
                    finish();
                    String message = "Successful ...";
                    Toast.makeText(LoginPage.this, message, Toast.LENGTH_LONG).show();
                }else{
                    String message = "An error occupied please try again later ...";
                    Toast.makeText(LoginPage.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponce> call, Throwable t) {

            }
        });
    }
}