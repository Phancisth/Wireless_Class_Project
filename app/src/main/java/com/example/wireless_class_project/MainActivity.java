package com.example.wireless_class_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private EditText LoginUsername;
    private EditText LoginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginUsername = findViewById(R.id.Login_Username);
        LoginPassword = findViewById(R.id.Login_Password);

    }

    public void Login(View view)
    {
        Intent intent   = new Intent(this, HomePage.class);
        startActivity(intent);
    }
}
