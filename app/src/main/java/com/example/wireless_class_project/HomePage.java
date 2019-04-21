package com.example.wireless_class_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.os.ConfigurationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Locale;

public class HomePage extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DocumentReference docRef;
    private FirebaseFirestore db;
    private String TAG = "HomePage";
    private String name,id,year;
    private TextView Tname,Tid,Tyear;
    private int GradeEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //System.out.println("SOMETHINGHERE"+ ConfigurationCompat.getLocales(getResources().getConfiguration()).get(0));
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        docRef = db.collection("users").document(mAuth.getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        //System.out.println(document.get("StudentID"));
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        name = mAuth.getCurrentUser().getEmail();
                        id = document.get("StudentID").toString();
                        switch (id.substring(0,2))
                        {
                            case "58":
                                year = "4";
                                break;
                            case "59":
                                year = "3";
                                break;
                            case "60":
                                year = "2";
                                break;
                            case "61":
                                year = "1";
                                break;
                        }
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }

                Tname = findViewById(R.id.studentName);
                Tid = findViewById(R.id.studentID);
                Tyear = findViewById(R.id.studentYear);
                Tname.setText("Email: "+name);
                Tid.setText("ID: "+id);
                Tyear.setText("Year: "+year);


            }
        });
        //Intent intent = getIntent(); //?? Dont know why this is here

    }

    public void Logout(View view)
    {
        mAuth.signOut();
        finishAndRemoveTask();
        Intent intent   = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void GoToCompetency(View view)
    {
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        //System.out.println(document.get("StudentID"));
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        if(document.get("GradeEdit").toString().compareTo("0") == 0)
                        {
                            Toast.makeText(HomePage.this,"Please Input Your Grade First", Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            Intent intent   = new Intent(HomePage.this, Competency.class);
                            intent.putExtra("StudentID", id);
                            startActivity(intent);
                        }
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }
    public void GoToGrade(View view)
    {

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        //System.out.println(document.get("StudentID"));
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        if(document.get("GradeEdit").toString().compareTo("1") == 0)
                        {

                            GradeEdit = 1;
                        }
                        else
                        {
                            System.out.println("WHAT");
                            GradeEdit = 0;
                        }
                        if(GradeEdit == 0) {
                            Intent intent = new Intent(HomePage.this, Database.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Intent intent = new Intent(HomePage.this, ListDataActivity.class);
                            startActivity(intent);
                            Toast.makeText(HomePage.this,"You Have Already Input Grade Once", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
        System.out.println("THIS SHIT"+GradeEdit);

    }
    public void GoToQuestionare(View view)
    {
        Intent intent   = new Intent(this, Questionare.class);
        startActivity(intent);
    }
    public void GoToPreference(View view)
    {
        finishAndRemoveTask();
        Intent intent = new Intent(this, Preference.class);
        startActivity(intent);
    }

}
