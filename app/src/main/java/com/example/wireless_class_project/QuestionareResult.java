package com.example.wireless_class_project;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class QuestionareResult extends AppCompatActivity {
    private int Score[] = new int[8];
    private TextView ShowTrack;
    private FirebaseFirestore db;
    private String name;
    private FirebaseAuth mAuth;
    private ImageView ResultImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionare_result);
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

        name = user.getUid();
        Map<String, Object> data = new HashMap<>();
        data.put("Recom_Track", ShowTrack.getText().toString());



        db.collection("users").document(name).set(data);

    }

    public void Prev(View view) {
        //Go to the previous page in the questionnaire Nahhhh Reset this whole thing
        //Make it a button that links to a page where the users can check the past results and other shit
        finishAndRemoveTask();
        Intent restart = new Intent(this,Questionare.class);
        startActivity(restart);
    }
    public void BacktoMain(View view)
    {
        finishAndRemoveTask();;
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
