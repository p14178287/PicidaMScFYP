package com.example.shingiraimarikasi.PicidaMScFYP.controller.ui


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.shingiraimarikasi.PicidaMScFYP.R
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity(), StringValidationInterface {

//    private var signupEmail: EditText? = null // 1.consider making variable local and immutable
//    private var signupPassword: EditText? = null
//    private var auth: FirebaseAuth? = null
//    private var registerBtn  : Button? = null
//    private var forgotPassBtn  : Button? = null
//    private var alreadyHaveAccBtn  : Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val auth: FirebaseAuth = FirebaseAuth.getInstance()

        val signupEmail: EditText = findViewById(R.id.editTextNewEmail)
        val signupPassword: EditText = findViewById(R.id.editTextNewPasswd)
        val registerBtn: Button = findViewById(R.id.register_newuser_btn) //2. see 1 above
//        val forgotPassBtn: Button = findViewById(R.id.signup_forgot_pass_btn)
//        val alreadyHaveAccBtn: Button = findViewById(R.id.signup_already_haveAcc_btn)

        registerBtn.setOnClickListener { _ ->
            /**
             * Fetch the ext from the the email and password editTextfields
             */
            val newEmail = signupEmail.text.toString().trim { it <= ' ' }
            val newPassword = signupPassword.text.toString().trim { it <= ' ' }

            /**
             * FOR USER INPUT VALIDATION
             * 6 cases to cater for: when user enters nothing, < 6 characters,
             * non-alphanumeric, invalid email format etc.
             * Consider using when() in Kotlin
             */
            if (newEmail.isEmpty()) {
                Toast.makeText(applicationContext, "Enter email address!", Toast.LENGTH_SHORT).show()

            }

            if (TextUtils.isEmpty(newPassword)) {
                Toast.makeText(applicationContext, "Enter password!", Toast.LENGTH_SHORT).show()

            }

            if (newPassword.length < 6) {
                Toast.makeText(applicationContext, "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show()

            }

            auth.createUserWithEmailAndPassword(newEmail, newPassword)
                    .addOnCompleteListener(this@SignupActivity) { task ->
                        Toast.makeText(this@SignupActivity, "createUserWithEmail:onComplete:" + task.isSuccessful, Toast.LENGTH_SHORT).show()
//                        progressBar.setVisibility(View.GONE)
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful) {
                            Toast.makeText(this@SignupActivity, "Authentication failed." + task.exception!!,
                                    Toast.LENGTH_SHORT).show()
                        } else {
                            startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
                            finish()
                        }
                    }


        }

    }
}
