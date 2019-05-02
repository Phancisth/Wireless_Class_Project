/*Project By
5988023	Purit		Phanudom
5988053	Naruedon	Wattanakul
5988098	Tattiya		Sakulniwat
 */
package com.example.wireless_class_project;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class QuestionareResult extends AppCompatActivity {
    private int BackLogout = 2;
    //nameSpace
    private int Score[] = new int[8];
    private TextView ShowTrack;
    private FirebaseFirestore db;
    private String name;
    private FirebaseAuth mAuth;
    private ImageView ResultImage;
    private DocumentReference docRef;
    private String TAG = "QPage";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionare_result);
        //Initialize
        BackLogout = 2;
        Score = getIntent().getIntArrayExtra("Score");
        int max = 0;
        int trackchosen = 0;
        ShowTrack = findViewById(R.id.TrackResult);
        ResultImage = findViewById(R.id.ResultIMG);
        for(int i=0;i<Score.length;i++)
        {
            if(Score[i] > max)
            {
                max = Score[i];
                trackchosen = i;
            }
        }

        switch(trackchosen)
        {//0=CN,1=CS,2=DB,3=EB,4=HT,5=MM,6=MS,7=SE
            case 0:
                ShowTrack.setText("CN");
                ResultImage.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.cn, null));
                break;
            case 1:
                ShowTrack.setText("CS");
                ResultImage.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.cs, null));
                break;
            case 2:
                ShowTrack.setText("DB");
                ResultImage.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.db, null));
                break;
            case 3:
                ShowTrack.setText("EB");
                ResultImage.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.eb, null));
                break;
            case 4:
                ShowTrack.setText("HT");
                ResultImage.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ht, null));
                break;
            case 5:
                ShowTrack.setText("MM");
                ResultImage.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.mm, null));
                break;
            case 6:
                ShowTrack.setText("MS");
                ResultImage.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ms, null));
                break;
            case 7:
                ShowTrack.setText("SE");
                ResultImage.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.se, null));
                break;

        }
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        //Adds the information to Firebase DB
        name = user.getUid();
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
                        data.put("Recom_Track", ShowTrack.getText().toString());
                        data.put("GradeEdit", "1");
                        db.collection("users").document(name).set(data);

                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }

            }
        });



    }
    //Retake the questionaire
    public void Prev(View view) {
        finishAndRemoveTask();
        Intent restart = new Intent(this,Questionare.class);
        startActivity(restart);
    }
    @Override
    public void onBackPressed() {
        if (BackLogout <= 0) {
            Logout(this.findViewById(android.R.id.content));
        } else {
            toastMessage("Pressing Back "+BackLogout+" times will log out");
            BackLogout--;
        }
    }
    //Back to menu
    public void BacktoMain(View view)
    {
        finishAndRemoveTask();;
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
    public void Logout(View view)
    {
        mAuth.signOut();
        finishAndRemoveTask();
        Intent intent   = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
