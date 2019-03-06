package com.example.wireless_class_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText LoginUsername;
    private EditText LoginPassword;
    private Button LoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginUsername = findViewById(R.id.Login_Username);
        LoginPassword = findViewById(R.id.Login_Password);
        LoginButton = findViewById(R.id.Login_Button);

    }
}
