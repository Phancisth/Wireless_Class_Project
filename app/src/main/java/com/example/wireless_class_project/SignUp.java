package com.example.wireless_class_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    private EditText namein, emailin, passwordin, studentIDin;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private String name, email, password, studentID;
    private static final String TAG = "EmailPassword";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


    }
    public void Logout(View view)
    {
        mAuth.signOut();
        finishAndRemoveTask();
        Intent intent   = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void SignupButton(final View view)
    {
        emailin = findViewById(R.id.input_email);
        passwordin = findViewById(R.id.input_password);
        studentIDin = findViewById(R.id.input_studentID);
        email = emailin.getText().toString();
        password = passwordin.getText().toString();
        studentID = studentIDin.getText().toString();
        if(email.isEmpty() || password.isEmpty() || studentID.isEmpty())
        {
            Toast.makeText(SignUp.this,"Please Enter all the needed information", Toast.LENGTH_SHORT).show();

        }
        else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        //updateUI(user);

                        // Create a new user with a first and last name
                        name = user.getUid();
                        Map<String, Object> data = new HashMap<>();
                        data.put("UID", name);
                        data.put("StudentID", studentID);


                        db.collection("users").document(name).set(data);

                        Toast.makeText(SignUp.this, "Succesfully Created an Account",
                                Toast.LENGTH_SHORT).show();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Logout(view);
                            }
                        }, 3000);

// Add a new document with a generated ID


                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(SignUp.this, "Sign Up Failed",
                                Toast.LENGTH_SHORT).show();
                        //updateUI(null);
                    }

                    // ...
                }
            });
        }
    }

}