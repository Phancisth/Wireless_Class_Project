package com.example.wireless_class_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
    }

    public void Logout(View view)
    {
        finishAffinity();
        Intent intent   = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
