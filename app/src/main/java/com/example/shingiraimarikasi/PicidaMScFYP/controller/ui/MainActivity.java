package com.example.shingiraimarikasi.PicidaMScFYP.controller.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.shingiraimarikasi.PicidaMScFYP.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {



    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**TODO: figure out where the onCreateDrawer method id derived from*/
//        super.onCreateDrawer();
        Button symptomBtn = findViewById(R.id.symptomBtn);

//        Typeface questrialFont = Typeface.createFromAsset(getAssets(), "fonts/Questrial-Regular.otf");
        symptomBtn.setOnClickListener(v -> {
            System.out.println("yes"); //debugging print statement
            Intent intent = new Intent(MainActivity.this, SymptomNotesActivity.class);
            startActivity(intent);
        });

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
            }
        };

    }
}

//        /**TODO: create a utility class with static methods below for reuse rather than redefining the same methods*/
//        @Override
//        public boolean onCreateOptionsMenu (Menu menu){
//            MenuInflater inflater = getMenuInflater();
//            inflater.inflate(R.menu.menu_main, menu);
//            return super.onCreateOptionsMenu(menu);
//        }

//        @Override
//        public boolean onOptionsItemSelected (MenuItem item){
//            int id = item.getItemId();
//            if (id == R.id.action_logout) {
//                logout();
//                return true;
//            }
//            return super.onOptionsItemSelected(item);
//        }
//
//        private void logout () {
//            FirebaseAuth.getInstance().signOut();
//            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//            finish();
//        }
//
//        @Override
//        public void onStart () {
//            super.onStart();
//            mAuth.addAuthStateListener(mAuthListener);
//        }
//
//        @Override
//        public void onStop () {
//            super.onStop();
//            if (mAuthListener != null) {
//                mAuth.removeAuthStateListener(mAuthListener);
//            }
//        }

