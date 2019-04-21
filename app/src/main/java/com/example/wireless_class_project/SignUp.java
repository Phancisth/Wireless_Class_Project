/*Project By
5988023	Purit		Phanudom
5988053	Naruedon	Wattanakul
5988098	Tattiya		Sakulniwat
 */
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
    //namespace
    private EditText namein, emailin, passwordin, studentIDin,Confirmin;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private String name, email, password, studentID,Confirm;
    private static final String TAG = "EmailPassword";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //initialize
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

    }
    //signs user out of the system
    public void Logout(View view)
    {
        mAuth.signOut();
        finishAndRemoveTask();
        Intent intent   = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    //Signup with Firebase Authenticator
    public void SignupButton(final View view)
    {
        emailin = findViewById(R.id.input_email);
        passwordin = findViewById(R.id.input_password);
        studentIDin = findViewById(R.id.input_studentID);
        Confirmin = findViewById(R.id.input_password2);
        email = emailin.getText().toString();
        password = passwordin.getText().toString();
        studentID = studentIDin.getText().toString();
        Confirm = Confirmin.getText().toString();
        if(email.isEmpty() || password.isEmpty() || studentID.isEmpty())
        {
            Toast.makeText(SignUp.this,"Please Enter all the needed information", Toast.LENGTH_SHORT).show();

        }
        else {
            if(password.compareTo(Confirm) == 0) {
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
                            data.put("Recom_Track", "NONE");
                            data.put("GradeEdit", "0");


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
                            Toast.makeText(SignUp.this, "Sign Up Failed, Check your input",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                    }
                });
            }
            else
            {
                Toast.makeText(SignUp.this, "Password Does Not Match",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

}