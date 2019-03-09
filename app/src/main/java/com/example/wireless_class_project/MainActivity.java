package com.example.wireless_class_project;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;

import org.w3c.dom.Text;

import java.util.Locale;

import static android.graphics.ColorSpace.Model.XYZ;

public class MainActivity extends AppCompatActivity {
    private EditText LoginUsername;
    private EditText LoginPassword;
    private TextView Language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginUsername = findViewById(R.id.Login_Username);
        LoginPassword = findViewById(R.id.Login_Password);

        Language = findViewById(R.id.Language);
        if(getIntent().getStringExtra("Current_Language") == null)
        {
            Language.setText("TH");
        }
        else {
            Language.setText(getIntent().getStringExtra("Current_Language"));
        }
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
        String currentLocal = Locale.getDefault().getLanguage();
        if(currentLocal == "en") {
            Locale myLocale = new Locale("th");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            refresh.putExtra("Current_Language","EN");
            startActivity(refresh);
            finish();
        }
        else if(currentLocal == "th")
        {
            Locale myLocale = new Locale("en");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            refresh.putExtra("Current_Language","TH");
            startActivity(refresh);
            finish();
        }
    }

}
