package com.example.wireless_class_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomePage extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DocumentReference docRef;
    private FirebaseFirestore db;
    private String TAG = "HomePage";
    private String name,id,year;
    private TextView Tname,Tid,Tyear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
        Intent intent   = new Intent(this, Competency.class);
        intent.putExtra("StudentID", id);
        startActivity(intent);
    }
    public void GoToGrade(View view)
    {
        Intent intent   = new Intent(this, GradeInput.class);
        startActivity(intent);
    }
    public void GoToQuestionare(View view)
    {
        Intent intent   = new Intent(this, Questionare.class);
        startActivity(intent);
    }
    public void GoToPreference(View view)
    {
        Intent intent = new Intent(this, Preference.class);
        startActivity(intent);
    }

}
