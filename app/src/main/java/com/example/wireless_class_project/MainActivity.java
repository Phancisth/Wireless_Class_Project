package com.example.wireless_class_project;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;

import org.w3c.dom.Text;

import java.util.Locale;

import static android.graphics.ColorSpace.Model.XYZ;

public class MainActivity extends AppCompatActivity {
    private EditText LoginUsername;
    private EditText LoginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
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
    public void Signup(View view)
    {
        Intent intent   = new Intent(this, SignUp.class);
        startActivity(intent);
    }
    public void ChangeLanguage(View view)
    {
        String languageToLoad  = "th-rTH";
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getResources().updateConfiguration(config,this.getResources().getDisplayMetrics());

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
