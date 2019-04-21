/*Project By
5988023	Purit		Phanudom
5988053	Naruedon	Wattanakul
5988098	Tattiya		Sakulniwat
 */
package com.example.wireless_class_project;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.os.ConfigurationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Preference extends AppCompatActivity {
    //NameSpace
    private String currentLocal;
    DatabaseHelper mDatabaseHelper;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private DocumentReference docRef;
    private String TAG = "Preference";
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        //Initialize
        currentLocal = ConfigurationCompat.getLocales(getResources().getConfiguration()).get(0).toString();
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        mDatabaseHelper = new DatabaseHelper(this);
    }
    //Changes the language using localization NOT ALL ITEMS CAN BE CHANGED
    public void ChangeLanguage(View view) {
        MainActivity.temp.finishAndRemoveTask();
        if (currentLocal.contains("en")) {
            Locale myLocale = new Locale("th");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Locale.setDefault(myLocale);
            finishAndRemoveTask();

            Intent refresh = new Intent(this, HomePage.class);
            refresh.putExtra("Current_Language", "en");
            startActivity(refresh);

        } else if (currentLocal.contains("th")) {
            Locale myLocale = new Locale("en");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Locale.setDefault(myLocale);
            finishAndRemoveTask();
            Intent refresh = new Intent(this, HomePage.class);
            refresh.putExtra("Current_Language", "th");
            startActivity(refresh);


        }
    }
    //Delete all Gradeinput data in the DB for the current user
    public void ResetDB(View view)
    {
        MainActivity.temp.finishAndRemoveTask();
        docRef = db.collection("users").document(mAuth.getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());

                        FirebaseUser user = mAuth.getCurrentUser();
                        name = user.getUid();
                        Map<String, Object> data = new HashMap<>();
                        data.put("UID", name);
                        data.put("StudentID", document.get("StudentID").toString());
                        data.put("Recom_Track", document.get("Recom_Track").toString());
                        data.put("GradeEdit", "0");
                        db.collection("users").document(name).set(data);
                        mDatabaseHelper.deleteName(name);
                        Intent refresh = new Intent(Preference.this, HomePage.class);
                        refresh.putExtra("Current_Language", "th");
                        startActivity(refresh);
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }

            }
        });
    }
}
