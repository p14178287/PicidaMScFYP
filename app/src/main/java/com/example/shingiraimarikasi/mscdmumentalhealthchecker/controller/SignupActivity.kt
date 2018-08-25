package com.example.shingiraimarikasi.mscdmumentalhealthchecker.controller


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.shingiraimarikasi.mscdmumentalhealthchecker.R

class SignupActivity : AppCompatActivity(), View.OnClickListener {
    private var editTextUsername: EditText? = null
    private var editTextPassword: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        //        findViewById(R.id.signUpNewUser).setOnClickListener(this);

    }

    private fun registerUser() {
        val newEmail = editTextUsername!!.text.toString().trim { it <= ' ' }
        val newPassword = editTextPassword!!.text.toString().trim { it <= ' ' }

        /**
         * FOR USER INPUT VALIDATION
         * 6 cases to cater for for: when user enters nothing, < 6 characters,
         * non-alphanumeric, invalid email format etc.
         * Consider using when() in Kotlin
         */
        if (newEmail.isEmpty()) {
            Toast.makeText(applicationContext, "Enter email address!", Toast.LENGTH_SHORT).show()
            return
        }

        if (TextUtils.isEmpty(newPassword)) {
            Toast.makeText(applicationContext, "Enter password!", Toast.LENGTH_SHORT).show()
            return
        }

        if (newPassword.length < 6) {
            Toast.makeText(applicationContext, "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show()
            return
        }


    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.register_newuser_btn //what happens after pressing the sign up button
            -> registerUser()
            R.id.alreadyHaveAccount -> startActivity(Intent(this, MainActivity::class.java))
        }
    }
}

