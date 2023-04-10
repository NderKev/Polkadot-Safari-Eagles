package com.example.smartcare;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpDoctor extends AppCompatActivity {

    ImageButton btnsignupdoc, btnlogindoc, btnjoindoc, googleBtn;

    EditText edEmail, edPhone, edPassword, edCPassword, edName;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_doctor);

        btnlogindoc = findViewById(R.id.btnLogin);
        btnsignupdoc = findViewById(R.id.btnSignUp);
        btnjoindoc = findViewById(R.id.btnJoin);
        googleBtn = findViewById(R.id.btnGoogle);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        edEmail = findViewById(R.id.edtEmailAddress);
        edPhone = findViewById(R.id.edtPhone);
        edName = findViewById(R.id.edtUsername);
        edPassword = findViewById(R.id.edtPassword);
        edCPassword = findViewById(R.id.edtCPassword);

        btnlogindoc.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), Login.class
            );
            startActivity(secondActivityIntent);
        });
        btnsignupdoc.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), Login.class
            );
            startActivity(secondActivityIntent);
            if(TextUtils.isEmpty(edEmail.getText().toString()) || TextUtils.isEmpty(edPhone.getText())
                    || TextUtils.isEmpty(edPassword.getText())|| TextUtils.isEmpty(edCPassword.getText()) || TextUtils.isEmpty(edName.getText())){
                String message = "All inputs required ...";
                Toast.makeText(SignUpDoctor.this, message, Toast.LENGTH_LONG).show();
            }else{
                RegisterRequest registerRequest = new RegisterRequest();
                registerRequest.setPhone(edPhone.getText().toString());
                registerRequest.setEmail(edEmail.getText().toString());
                registerRequest.setPassword(edPassword.getText().toString());
                registerRequest.setPassword(edCPassword.getText().toString());
                registerRequest.setConfirmpassword(edName.getText().toString());
                registerUser(registerRequest);
            }
        });
        btnjoindoc.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), SignUpPatient.class
            );
            startActivity(secondActivityIntent);
        });
        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignIn();
            }
        });

    }
    public void registerUser(RegisterRequest registerRequest){
        Call<RegisterResponse> registerResponseCallCall = ApiClient.getService().registerUsers(registerRequest);
        registerResponseCallCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.isSuccessful()){
                    String message = "Successful ...";
                    Toast.makeText(SignUpDoctor.this, message, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(SignUpDoctor.this, Login.class) );
                }else{
                    String message = "An error occured try angain later ...";
                    Toast.makeText(SignUpDoctor.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(SignUpDoctor.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }
    void SignIn() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            }catch (ApiException e){
                Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }
    void navigateToSecondActivity() {
        finish();
        Intent intent = new Intent(SignUpDoctor.this, Home.class);
        startActivity(intent);
    }
}