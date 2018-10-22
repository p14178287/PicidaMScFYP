package com.example.shingiraimarikasi.PicidaMScFYP.controller.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.shingiraimarikasi.PicidaMScFYP.R;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends BaseActivity {


    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setContentView(R.layout.activity_navigation); //for getting the camera button take out once camera button is working

//        Button cameraBtn = findViewById(R.id.appointmentsButton); //checking to see if it can work


//        cameraBtn.setOnClickListener(view -> {
//            MainActivityPermissionsDispatcher.openCameraWithPermissionCheck(MainActivity.this);
//            Toast.makeText(this, "Opening camera", Toast.LENGTH_LONG).show();
//        });


//                 auth.getCurrentUser();
//            if (auth != null) {
//                supportActionBar?.setTitle("Welcome, " + user.displayName + "!")
//            } else {
//
//            }




        //TODO: attach the other button to a cllicklistener



//        onStart();
//        onStop();
    }

    //GET RID ONCE WORKING IN KOTLIN NAVIGATIONACTIVITY CLASS

//    /********* Permission request begins here ***********/
//
//    @NeedsPermission(Manifest.permission.CAMERA)
//    void openCamera() {
//
//        Toast.makeText(this, "Opening camera", Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
//    }
//
//    @OnShowRationale(Manifest.permission.CAMERA)
//    void show_PCIDA_rationaleRequestForCamera(PermissionRequest request) {
//        new AlertDialog.Builder(this).
//                setTitle("mhata").setMessage("This permissioon is ").
//                setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        request.proceed(); //if user okays permission
//                    }
//                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                request.cancel(); //where user doesn't agree
//            }
//        }).show();
//    }
//
//    @OnPermissionDenied(Manifest.permission.CAMERA)
//    void onPermissionDenied() {
//        Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show();
//    }
//
//    @OnNeverAskAgain(Manifest.permission.CAMERA)
//    void neverAskAgain() {
//        Toast.makeText(this, "Never asking again", Toast.LENGTH_LONG).show();
//    }
//
//    /********* Permission Handling Ends ***********/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authStateListener != null) {
            auth.removeAuthStateListener(authStateListener);
        }
    }


}

