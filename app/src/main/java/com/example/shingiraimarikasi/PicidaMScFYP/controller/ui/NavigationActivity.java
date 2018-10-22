package com.example.shingiraimarikasi.PicidaMScFYP.controller.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.shingiraimarikasi.PicidaMScFYP.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions //permissionsDispatcher annotation
public class NavigationActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth auth; //never used because got rid of the profile name reader and display function
    private FirebaseAuth.AuthStateListener authListener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view ->
                /**TODO:
                 * insert a dialogue here and ascertain if
                 * user wants to send email or share profile or something!
                 * */
                Snackbar.make(view, "Sending Email", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show());


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        /*** ATTACH CLICK LISTENERS TO SOME BUTTONS ****/
        //switch to symptom notes when this Button is pressed
        Button symptomBtn = findViewById(R.id.symptomBtn);
        symptomBtn.setOnClickListener(v -> {
            Intent intent = new Intent(NavigationActivity.this, SymptomNotesActivity.class);
            startActivity(intent);
        });

        /*** APPOINTMENTS BUTTON ****/

        Button appBtn = findViewById(R.id.appointmentsButton);
        appBtn.setOnClickListener(v -> {
            Intent intent = new Intent(NavigationActivity.this, ApptActivity.class);
            startActivity(intent);
        });


        new AlertDialog.Builder(this)
                .setTitle("PCIDA Permission Conxtext for this Application")
                .setMessage(retrievePermissions().toString()) // calling retrieve permissions here
                .setPositiveButton("okay", (dialogue, which) -> {

                    dialogue.dismiss();
                }).show();
    }

    /***************** PCIDA PERMISSION HANDLING *********************/

    @NeedsPermission(Manifest.permission.CAMERA) //permissionsDispatcher annotation
    public void openCamera() {
        Toast.makeText(this, "opening camera", Toast.LENGTH_LONG).show();
    }

    @NeedsPermission(Manifest.permission.READ_CALENDAR)
    public void readCalendar() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        NavigationActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    /**
     * Invoked when user has previously denied permission but
     * tries to access camera again. A PCIDA rationale dialog
     * would help explain to the user why the permission is needed
     *
     * @param request
     */
    @OnShowRationale(Manifest.permission.CAMERA)
    public void PCIDA_showRationaleRequestForCamera(PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setTitle("Permission needed")
                .setMessage("This permission is needed for accessing the camera and without it, you may not be able to upload a picture for your profile")
                .setPositiveButton("okay", (dialogue, which) -> {
                    request.proceed(); //when the user clicks the Okay button, acknowledging they have understood the rationale
                }).setNegativeButton("Cancel", ((dialog, which) -> {
            request.cancel();
        })).show();
    }


    /**
     * Invoked when user denies permission. Intention is clear
     * therefore no need to introduce a dialog
     */
    @OnPermissionDenied(Manifest.permission.CAMERA)
    public void PCIDA_onCameraPermissionDenied() {
        Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show();
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    public void PCIDA_onNeverAskAgain() {
        Toast.makeText(this, "Never asking again", Toast.LENGTH_LONG).show();
    }

    /********* PCIDA PERMISSION HANDLING ENDS ***********/


    /********* PCIDA READING CONTEXT OF ALL PERMISSIONS LIKELY OT BE REQUESTED **********/


//    public boolean getContextOfPermissions(String permission) {
//        Context context;
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS);
//            if (info.requestedPermissions != null) {
//                for (String p : info.requestedPermissions) {
//                    if (p.equals(permission)) {
//                        return true;
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
    public StringBuilder retrievePermissions() {
//        try {
//            return context
//                    .getPackageManager()
//                    .getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS)
//                    .requestedPermissions;
//        } catch (PackageManager.NameNotFoundException e) {
//            throw new RuntimeException("This should have never happened.", e);
//        }


        StringBuilder appNameAndPermissions = new StringBuilder();
        PackageManager packageManager = getPackageManager();
        List<ApplicationInfo> packages = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo applicationInfo : packages) {
            Log.d("test", "App: " + applicationInfo.name + " Package: " + applicationInfo.packageName);
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(applicationInfo.packageName, PackageManager.GET_PERMISSIONS);
                appNameAndPermissions.append(packageInfo.packageName + "*******:\n");


                //Get Permissions
                String[] requestedPermissions = packageInfo.requestedPermissions;
                if (requestedPermissions != null) {
                    for (int i = 0; i < requestedPermissions.length; i++) {
                        Log.d("test", requestedPermissions[i]);
                        appNameAndPermissions.append(requestedPermissions[i] + "\n");
                    }
                    appNameAndPermissions.append("\n");
                }

            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
return appNameAndPermissions;

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            /**
             * This is where the openCamera() function would be called
             * but now its called in the NavigationActivityPermissionDispatcher
             * which is autogenerated at compile time.
             */
            NavigationActivityPermissionsDispatcher.openCameraWithPermissionCheck(NavigationActivity.this);

        } else if (id == R.id.pass_reset) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.sign_out) {
            auth = FirebaseAuth.getInstance();
            auth.signOut();
            startActivity(new Intent(this, LoginActivity.class));
            Toast.makeText(this, "You have successfully Logged Out", Toast.LENGTH_LONG).show();
            finish();
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
