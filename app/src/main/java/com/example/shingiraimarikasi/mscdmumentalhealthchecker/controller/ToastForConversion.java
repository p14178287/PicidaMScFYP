package com.example.shingiraimarikasi.mscdmumentalhealthchecker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shingiraimarikasi.mscdmumentalhealthchecker.R;


public class ToastForConversion extends AppCompatActivity implements View.OnClickListener {

    EditText editTextUsername, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
//        findViewById(R.id.signUpNewUser).setOnClickListener(this);

    }

    private void registerUser() {
        String newEmail = editTextUsername.getText().toString().trim();
        String newPassword = editTextPassword.getText().toString().trim();

        /**
         * FOR USER INPUT VALIDATION
         * 6 cases to cater for for: when user enters nothing, < 6 characters,
         * non-alphanumeric, invalid email format etc.
         * Consider using when() in Kotlin
         */
        if (newEmail.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(newPassword)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (newPassword.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signUpNewUser: //what happens after pressing the sign up button
                registerUser();
                break;
            case R.id.alreadyHaveAccount:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}
