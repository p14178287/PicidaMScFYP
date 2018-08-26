package com.example.shingiraimarikasi.mscdmumentalhealthchecker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shingiraimarikasi.mscdmumentalhealthchecker.R;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        EditText loginEmail, loginPassword;
        Button signupBtn, loginBtn, passResetBtn; //variables made local as not accessed elsewhere

        super.onCreate(savedInstanceState); //Get Firebase auth instance
        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {

            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        setContentView(R.layout.activity_login);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        signupBtn = findViewById(R.id.signup_newuser);
        loginBtn = findViewById(R.id.login_btn);
        passResetBtn = findViewById(R.id.forgot_pass_btn);




        signupBtn.setOnClickListener( v ->
                startActivity(new Intent(LoginActivity.this, SignupActivity.class)));

        passResetBtn.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class)));

        loginBtn.setOnClickListener(v -> {
                String email = loginEmail.getText().toString();
                final String password = loginPassword.getText().toString();

                //validate user input
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }


            //authenticate user.
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, task -> {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
//                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        loginPassword.setError(getString(R.string.error_password_short));
                                    } else {
                                        Toast.makeText(LoginActivity.this, getString(R.string.failed_auth), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }

                        });

        });
    }
}