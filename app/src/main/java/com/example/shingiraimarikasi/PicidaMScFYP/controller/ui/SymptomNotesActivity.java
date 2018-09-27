package com.example.shingiraimarikasi.PicidaMScFYP.controller.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shingiraimarikasi.PicidaMScFYP.R;
import com.example.shingiraimarikasi.PicidaMScFYP.controller.dataModel.SymptomDataActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SymptomNotesActivity extends AppCompatActivity {


    private EditText symptomTitleEditText;
    private EditText symptomNotesEditText;
    private Button addSymptomButton, allSymptomButton;
    private TextView symptomFormTitle;
//    protected DatabaseReference ailmentRef = FirebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_notes);

        symptomTitleEditText = findViewById(R.id.symptomTitleEditText);
        symptomNotesEditText = findViewById(R.id.symptomNotesEditText);
        addSymptomButton = findViewById(R.id.addSymptomButton);
        allSymptomButton = findViewById(R.id.allSymptomButton);
        symptomFormTitle = findViewById(R.id.symptomFormTitle);


//        Typeface questrialFont = Typeface.createFromAsset(getAssets(), "fonts/Questrial-Regular.otf");
//        symptomFormTitle.setTypeface(questrialFont);



        addSymptomButton.setOnClickListener(v -> {
//            String symptomTitle, symptomNotes;
            String symptomTitle = symptomTitleEditText.getText().toString();
            String symptomNotes = symptomNotesEditText.getText().toString();

            //importing the data class that will act on the data sent across
            SymptomDataActivity symptom = new SymptomDataActivity(symptomTitle, symptomNotes);

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference dbRef = FirebaseDatabase
                    .getInstance()
                    .getReference(SymptomConstants.FIREBASE_CHILD_AILMENTS)
                    .child(uid);

            DatabaseReference pushRef = dbRef.push();
            String pushId = pushRef.getKey();
            symptom.setPushId(pushId);
            pushRef.setValue(symptom);

            Toast.makeText(this, "Save Successful!", Toast.LENGTH_SHORT).show();
            symptomTitleEditText.setText("");
            symptomNotesEditText.setText("");
        });


//        allSymptomButton.setOnClickListener(v -> {
//            Intent intent = new Intent(SymptomNotesActivity.this, SymptomListActivity.class);
//            startActivity(intent);
//        });
    }


/**TODO: commented out for now but need a static function that
 pulls the drawer out and static in the sense that it can be called from any class*/

//    public void navigationDrawer() {
//        ToggleButton toggle = ActionBarDrawerToggle(
//                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
//        drawer_layout.addDrawerListener(toggle)
//        toggle.syncState()
//
//        nav_view.setNavigationItemSelectedListener(this)
//    }
}