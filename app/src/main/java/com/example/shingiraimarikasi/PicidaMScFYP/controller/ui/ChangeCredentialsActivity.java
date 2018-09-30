package com.example.shingiraimarikasi.PicidaMScFYP.controller.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shingiraimarikasi.PicidaMScFYP.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangeCredentialsActivity extends BaseActivity {

    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //converted fields into local variables as accessed internally
        Button btnChangeEmail, btnChangePassword,
                btnSendEmail, btnDelAccount, btnSignOut;
        EditText oldEmail, newEmail, password, newPassword;


        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_change_credentials);








        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_change_credentials, null, false);
        drawerLayout.addView(contentView, 0);










        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = firebaseAuth -> {
            if (user == null) {
                startActivity(new Intent(ChangeCredentialsActivity.this, LoginActivity.class));
                finish();
            }
        };


        btnChangeEmail = findViewById(R.id.btnChangeEmail);
        btnChangePassword = findViewById(R.id.btnChangePassword);
        btnSendEmail = findViewById(R.id.send_details_btn);
        btnDelAccount = findViewById(R.id.del_acc_btn);
        btnSignOut = findViewById(R.id.sign_out);

        oldEmail = findViewById(R.id.old_email);
        newEmail = findViewById(R.id.new_email);
        password = findViewById(R.id.old_password);
        newPassword = findViewById(R.id.new_password);

//        oldEmail.setVisibility(View.GONE); //temporarily disabled to see visibility in the layout, rem: to change and make them visible again
//        newEmail.setVisibility(View.GONE);
//        password.setVisibility(View.GONE);
//        newPassword.setVisibility(View.GONE);

//        changeEmail.setVisibility(View.GONE);
//        changePassword.setVisibility(View.GONE);
//        btnSendEmail.setVisibility(View.GONE);
//        btnDelAccount.setVisibility(View.GONE);

//        progressBar = (ProgressBar) findViewById(R.id.progressBar);

//        if (progressBar != null) {
//            progressBar.setVisibility(View.GONE);
//        }

        btnChangeEmail.setOnClickListener(v -> {
            oldEmail.setVisibility(View.GONE);
            newEmail.setVisibility(View.VISIBLE);
            password.setVisibility(View.GONE);
            newPassword.setVisibility(View.GONE);
//            changeEmail.setVisibility(View.VISIBLE);
//            changePassword.setVisibility(View.GONE);
            btnSendEmail.setVisibility(View.GONE);
            btnDelAccount.setVisibility(View.GONE);

        });

        btnChangeEmail.setOnClickListener(v -> {
//            progressBar.setVisibility(View.VISIBLE);
            if (user != null && !newEmail.getText().toString().trim().equals("")) {
                user.updateEmail(newEmail.getText().toString().trim())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(ChangeCredentialsActivity.this, "Email address is updated. Please sign in with new email id!", Toast.LENGTH_LONG).show();
                                signOut();
//                                progressBar.setVisibility(View.GONE);
                            } else {
                                Toast.makeText(ChangeCredentialsActivity.this, "Failed to update email!", Toast.LENGTH_LONG).show();
//                                progressBar.setVisibility(View.GONE);
                            }

                        });
            } else if (newEmail.getText().toString().trim().equals("")) {
                newEmail.setError("Enter email");
//                progressBar.setVisibility(View.GONE);
            }

        });

        btnChangePassword.setOnClickListener(v -> {
            oldEmail.setVisibility(View.GONE);
            newEmail.setVisibility(View.GONE);
            password.setVisibility(View.GONE);
            newPassword.setVisibility(View.VISIBLE);
//            changeEmail.setVisibility(View.GONE);
//            changePassword.setVisibility(View.VISIBLE);
            btnSendEmail.setVisibility(View.GONE);
            btnDelAccount.setVisibility(View.GONE);

        });

        btnChangePassword.setOnClickListener(v -> {
//            progressBar.setVisibility(View.VISIBLE);
            if (user != null && !newPassword.getText().toString().trim().equals("")) {
                if (newPassword.getText().toString().trim().length() < 6) {
                    newPassword.setError("Password too short, enter minimum 6 characters");
//                    progressBar.setVisibility(View.GONE);
                } else {
                    user.updatePassword(newPassword.getText().toString().trim())
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ChangeCredentialsActivity.this, "Password is updated, sign in with new password!", Toast.LENGTH_SHORT).show();
                                    signOut();
//                                    progressBar.setVisibility(View.GONE);
                                } else {
                                    Toast.makeText(ChangeCredentialsActivity.this, "Failed to update password!", Toast.LENGTH_SHORT).show();
//                                    progressBar.setVisibility(View.GONE);
                                }

                            });
                }
            } else if (newPassword.getText().toString().trim().equals("")) {
                newPassword.setError("Enter password");
//                progressBar.setVisibility(View.GONE);
            }

        });

        btnSendEmail.setOnClickListener(v -> {
            oldEmail.setVisibility(View.VISIBLE);
            newEmail.setVisibility(View.GONE);
            password.setVisibility(View.GONE);
            newPassword.setVisibility(View.GONE);
//            changeEmail.setVisibility(View.GONE);
//            changePassword.setVisibility(View.GONE);
            btnSendEmail.setVisibility(View.VISIBLE);
            btnDelAccount.setVisibility(View.GONE);

        });

        btnSendEmail.setOnClickListener(v -> {
//            progressBar.setVisibility(View.VISIBLE);
            if (!oldEmail.getText().toString().trim().equals("")) {
                auth.sendPasswordResetEmail(oldEmail.getText().toString().trim())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(ChangeCredentialsActivity.this, "Reset password email is sent!", Toast.LENGTH_SHORT).show();
//                                progressBar.setVisibility(View.GONE);
                            } else {
                                Toast.makeText(ChangeCredentialsActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
//                                progressBar.setVisibility(View.GONE);
                            }
                        });
            } else {
                oldEmail.setError("Enter email");
//                progressBar.setVisibility(View.GONE);
            }

        });

        btnDelAccount.setOnClickListener(v -> {
//                progressBar.setVisibility(View.VISIBLE);
            if (user != null) {
                user.delete()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(ChangeCredentialsActivity.this, "Your profile is deleted:( Create a account now!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ChangeCredentialsActivity.this, SignupActivity.class));
                                finish();
//                                progressBar.setVisibility(View.GONE);
                            } else {
                                Toast.makeText(ChangeCredentialsActivity.this, "Failed to delete your account!", Toast.LENGTH_SHORT).show();
//                                progressBar.setVisibility(View.GONE);
                            }

                        });

            }
        });

        btnSignOut.setOnClickListener(v -> {
            if (v.getId() == R.id.sign_out) {
                signOut();
                startActivity(new Intent(this, LoginActivity.class)); // not doing anything this line need to find out more
                finish();
            }
        });
    }

    //sign out method
    public void signOut() {
        auth.signOut();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }
}
