package com.example.wireless_class_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent(); //?? Dont know why this is here
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

}
