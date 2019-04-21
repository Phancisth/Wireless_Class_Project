/*Project By
5988023	Purit		Phanudom
5988053	Naruedon	Wattanakul
5988098	Tattiya		Sakulniwat
 */
package com.example.wireless_class_project;

import android.app.Activity;
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
    //NameSpace
    private EditText LoginUsername;
    private EditText LoginPassword;
    private String  Email;
    private String Password;
    private FirebaseAuth mAuth;
    private String TAG = "LoginPage";
    private int Bypassincrement = 5;
    public static Activity temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);
        //Initialize
        setContentView(R.layout.activity_main);
        temp = this;
        mAuth = FirebaseAuth.getInstance();
        LoginUsername = findViewById(R.id.Login_Username);
        LoginPassword = findViewById(R.id.Login_Password);
    }

    //Login to the Application
    public void Login(View view)
    {
        Email = LoginUsername.getText().toString();
        Password = LoginPassword.getText().toString();

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
    //Sends user to Signup page
    public void Signup(View view)
    {
        Intent intent   = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    //Users can use the example account to see the system and how it is used
    public void Bypassing(View view)
    {
    Bypassincrement--;
    if(Bypassincrement > 0) {
        Toast.makeText(MainActivity.this, "Example Login " + Bypassincrement, Toast.LENGTH_SHORT).show();
    }
    else
    {
        Toast.makeText(MainActivity.this, "Example Login Success ", Toast.LENGTH_SHORT).show();
    }
    if(Bypassincrement == 0) {
        mAuth.signInWithEmailAndPassword("wireless@wireless.com", "wireless")
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

                    }
                });
    }

    }

}
