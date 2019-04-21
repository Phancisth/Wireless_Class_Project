package com.example.wireless_class_project;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.util.Locale;

import static android.graphics.ColorSpace.Model.XYZ;

public class MainActivity extends AppCompatActivity {
    private EditText LoginUsername;
    private EditText LoginPassword;
    private TextView Language;
    private String  Email;
    private String Password;
    private FirebaseAuth mAuth;
    private String TAG = "LoginPage";
    private int Bypassincrement = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        LoginUsername = findViewById(R.id.Login_Username);
        LoginPassword = findViewById(R.id.Login_Password);



        /*if(getIntent().getStringExtra("Current_Language") == null)
        {
            Locale.setDefault(new Locale("en"));
        }
        else {
            Locale.setDefault(new Locale(getIntent().getStringExtra("Current_Language")));
        }*/
    }

    public void Login(View view)
    {
        Email = LoginUsername.getText().toString();
        Password = LoginPassword.getText().toString();
        System.out.println(Email +" "+ Password);
        if(Email.isEmpty() || Password.isEmpty())
        {
            Toast.makeText(MainActivity.this,"Please Enter Email and Password", Toast.LENGTH_SHORT).show();

        }
        else {
            mAuth.signInWithEmailAndPassword(Email, Password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                finishAndRemoveTask();
                                Intent intent = new Intent(MainActivity.this, HomePage.class);
                                startActivity(intent);


                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity.this, "Login Failed",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }

    }
    public void Signup(View view)
    {
        Intent intent   = new Intent(this, SignUp.class);
        startActivity(intent);
    }
    /*public void ChangeLanguage(View view)
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
    }*/
    public void Preference(View view)
    {
        Intent intent = new Intent(MainActivity.this,Preference.class);
        startActivity(intent);
    }
    public void Bypassing(View view)
    {
    Bypassincrement--;
        Toast.makeText(MainActivity.this, "Bypassing "+Bypassincrement,
                Toast.LENGTH_SHORT).show();
    if(Bypassincrement == 0) {
        mAuth.signInWithEmailAndPassword("wirelessproject@wireless.com", "wirelessproject")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            finishAndRemoveTask();
                            Intent intent = new Intent(MainActivity.this, HomePage.class);
                            startActivity(intent);


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Login Failed",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    }

}
