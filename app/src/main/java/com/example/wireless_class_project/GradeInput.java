package com.example.wireless_class_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GradeInput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_input);
    }
    public void Logout(View view)
    {
        finishAffinity();
        Intent intent   = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
