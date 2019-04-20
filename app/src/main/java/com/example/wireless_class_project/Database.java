
package com.example.wireless_class_project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Database extends AppCompatActivity {

    private static final String TAG = "Database";
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    DatabaseHelper mDatabaseHelper;
    private DocumentReference docRef;
    private Button btnAdd, btnViewData;
    private String[] score = new String[22];
    private String name;
    private EditText ITCS175, ITCS200, ITCS320, ITCS125, ITCS208, ITCS211, ITCS159, ITCS210, ITCS222, ITCS231, ITCS306, ITCS241, ITCS323, ITCS335, ITCS343, ITCS381, ITCS361, ITCS371, ITCS414, ITCS420, ITCS443, ITCS451;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        ITCS175 = (EditText) findViewById(R.id.ITCS175_edit);
        ITCS200 = (EditText) findViewById(R.id.ITCS200_edit);
        ITCS320 = (EditText) findViewById(R.id.ITCS320_edit);
        ITCS125 = (EditText) findViewById(R.id.ITCS125_edit);
        ITCS208 = (EditText) findViewById(R.id.ITCS208_edit);
        ITCS211 = (EditText) findViewById(R.id.ITCS211_edit);
        ITCS159 = (EditText) findViewById(R.id.ITCS159_edit);
        ITCS210 = (EditText) findViewById(R.id.ITCS210_edit);
        ITCS222 = (EditText) findViewById(R.id.ITCS222_edit);
        ITCS231 = (EditText) findViewById(R.id.ITCS231_edit);
        ITCS306 = (EditText) findViewById(R.id.ITCS306_edit);
        ITCS241 = (EditText) findViewById(R.id.ITCS241_edit);
        ITCS323 = (EditText) findViewById(R.id.ITCS323_edit);
        ITCS335 = (EditText) findViewById(R.id.ITCS335_edit);
        ITCS343 = (EditText) findViewById(R.id.ITCS343_edit);
        ITCS381 = (EditText) findViewById(R.id.ITCS381_edit);
        ITCS361 = (EditText) findViewById(R.id.ITCS361_edit);
        ITCS371 = (EditText) findViewById(R.id.ITCS371_edit);
        ITCS414 = (EditText) findViewById(R.id.ITCS414_edit);
        ITCS420 = (EditText) findViewById(R.id.ITCS420_edit);
        ITCS443 = (EditText) findViewById(R.id.ITCS443_edit);
        ITCS451 = (EditText) findViewById(R.id.ITCS451_edit);


        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnViewData = (Button) findViewById(R.id.btnView);
        mDatabaseHelper = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean scoreCheck = true;

                score[0] = ITCS175.getText().toString();
                score[1] = ITCS175.getText().toString();
                score[2] = ITCS175.getText().toString();
                score[3] = ITCS175.getText().toString();
                score[4] = ITCS175.getText().toString();
                score[5] = ITCS175.getText().toString();
                score[6] = ITCS175.getText().toString();
                score[7] = ITCS175.getText().toString();
                score[8] = ITCS175.getText().toString();
                score[9] = ITCS175.getText().toString();
                score[10] = ITCS175.getText().toString();
                score[11] = ITCS175.getText().toString();
                score[12] = ITCS175.getText().toString();
                score[13] = ITCS175.getText().toString();
                score[14] = ITCS175.getText().toString();
                score[15] = ITCS175.getText().toString();
                score[16] = ITCS175.getText().toString();
                score[17] = ITCS175.getText().toString();
                score[18] = ITCS175.getText().toString();
                score[19] = ITCS175.getText().toString();
                score[20] = ITCS175.getText().toString();
                score[21] = ITCS175.getText().toString();
                for(int i = 0; i < 22; i++) {
                    if(score[i] == null) scoreCheck = false;
                }

                if (scoreCheck) {
                    docRef = db.collection("users").document(mAuth.getUid());
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    Log.d(TAG, "DocumentSnapshot data: " + document.getData());

                                    FirebaseUser user = mAuth.getCurrentUser();
                                    name = user.getUid();
                                    Map<String, Object> data = new HashMap<>();
                                    data.put("UID", name);
                                    data.put("StudentID", document.get("StudentID").toString());
                                    data.put("Recom_Track", document.get("Recom_Track").toString());
                                    data.put("GradeEdit", "1");
                                    db.collection("users").document(name).set(data);

                                } else {
                                    Log.d(TAG, "No such document");
                                }
                            } else {
                                Log.d(TAG, "get failed with ", task.getException());
                            }

                        }
                    });
                    AddData(score);
                    //editText.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }

            }
        });

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Database.this, ListDataActivity.class);
                startActivity(intent);
            }
        });

    }

    public void AddData(String[] newEntry) {
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}