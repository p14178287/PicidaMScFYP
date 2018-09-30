package com.example.shingiraimarikasi.PicidaMScFYP.controller.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.shingiraimarikasi.PicidaMScFYP.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

//    private val mAuthListener: FirebaseAuth.AuthStateListener? = null
//    private val mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        //listen for changes in user and display their name once they are logged in
        val mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                supportActionBar?.setTitle("Welcome, " + user.displayName + "!")
            } else {

            }
        }


        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

    }
//TODO: DTERMINE WHICH CLASS THE SYMPTOM BUTTON LISTERNER NEEDS TO GO
////        val activityLayout = findViewById<ConstraintLayout>(R.id.activity_main)
////        Typeface questrialFont = Typeface.createFromAsset(getAssets(), "fonts/Questrial-Regular.otf");
//        activityLayout.symptomBtn.symptomBtn.setOnClickListener { _ ->
//            System.out.println("yes") //debugging print statement
//            val intent = Intent(this@NavigationActivity, SymptomNotesActivity::class.java)
//            startActivity(intent)
//        }
//    }


        override fun onBackPressed() {
            val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START)
            } else {
                super.onBackPressed()
            }
        }

        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            // Inflate the menu; this adds items to the action bar if it is present.
            menuInflater.inflate(R.menu.navigation, menu)
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            val id = item.itemId


            return if (id == R.id.action_settings) {
                true
            } else super.onOptionsItemSelected(item)

        }

        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            val auth = FirebaseAuth.getInstance() //
            // Handle navigation view item clicks here.
            when (item.itemId) {
                R.id.nav_camera -> {
                    // Handle the camera action
                }
                R.id.pass_reset -> {

                }
                R.id.nav_slideshow -> {

                }
                R.id.sign_out -> { //sign out and assure user successful sign out
                    auth.signOut()
                    startActivity(Intent(this, LoginActivity::class.java)) // not doing anything this line need to find out more
                    Toast.makeText(this, "You have successfully Logged Out", Toast.LENGTH_LONG).show()
                    finish()

                }
                R.id.nav_share -> {

                }
                R.id.nav_send -> {

                }
            }

            drawer_layout.closeDrawer(GravityCompat.START)
            return true
        }

}

//TODO: NOT IN THE THER CLASS

//    override fun onStart() {
//        super.onStart()
//        mAuthListener?.let { mAuth?.addAuthStateListener(it) }
//    }
//
//    override fun onStop() {
//        super.onStop()
//        if (mAuthListener != null) mAuth?.removeAuthStateListener(mAuthListener)
//    }

