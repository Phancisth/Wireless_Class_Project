/*Project By
5988023	Purit		Phanudom
5988053	Naruedon	Wattanakul
5988098	Tattiya		Sakulniwat
 */
package com.example.wireless_class_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

public class HomePage extends AppCompatActivity {
    //NameSpace
    private FirebaseAuth mAuth;
    private DocumentReference docRef;
    private FirebaseFirestore db;
    private String TAG = "HomePage";
    private String name,id,year;
    private TextView Tname,Tid,Tyear;
    private int GradeEdit;
    private int BackLogout = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Initialize
        BackLogout = 2;
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        //Connects to FireBase DB
        docRef = db.collection("users").document(mAuth.getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
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

    }
    //Logout of the App
    public void Logout(View view)
    {
        mAuth.signOut();
        finishAndRemoveTask();
        Intent intent   = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    /*@Override
    public void onBackPressed() {
        if (BackLogout <= 0) {
            Logout(this.findViewById(android.R.id.content));
        } else {
            toastMessage("Pressing Back "+BackLogout+" times will log out");
            BackLogout--;
        }
    }*/
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
    //Directs the user to the Competency Page
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
    //Directs the user to the GradeEdit Page
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
                            Toast.makeText(HomePage.this,"Edit Mode, Delete is in Preference", Toast.LENGTH_SHORT).show();
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
    //Directs the user to the Questionaire Page
    public void GoToQuestionare(View view)
    {
        Intent intent   = new Intent(this, Questionare.class);
        startActivity(intent);
    }
    //Directs the user to the Preference Page
    public void GoToPreference(View view)
    {
        Intent intent = new Intent(this, Preference.class);
        startActivity(intent);
    }

}
