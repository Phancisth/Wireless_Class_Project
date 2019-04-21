package com.example.wireless_class_project;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;



public class Competency extends AppCompatActivity {
    private static final float MAX =16, MIN =1f;
    private static final int TRACKS = 8;
    private RadarChart chart;
    private FirebaseAuth mAuth;
    private DocumentReference docRef;
    private String TAG = "Competency";
    DatabaseHelper mDatabaseHelper;
    private String[] Subjects = {"ITCS175","ITCS200","ITCS320","ITCS125","ITCS208","ITCS211","ITCS159","ITCS210","ITCS222","ITCS231","ITCS306","ITCS241","ITCS323","ITCS335","ITCS343","ITCS381","ITCS361","ITCS371","ITCS414","ITCS420","ITCS443","ITCS451"};
    private final String[] tracks = new String[]{"CN","CS","DB","EB","HT","MM","MS","SE"};
    private float TrackScore[] = new float[8];
    private String StudentID;
    private TextView[] T1 = new TextView[8];
    private TextView T2;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competency);
        chart = findViewById(R.id.RadarChart);
        mDatabaseHelper = new DatabaseHelper(this);
        StudentID = getIntent().getStringExtra("StudentID");

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        T1[7] = findViewById(R.id.TrackNo1);
        T1[6] = findViewById(R.id.TrackNo2);
        T1[5] = findViewById(R.id.TrackNo3);
        T1[4] = findViewById(R.id.TrackNo4);
        T1[3] = findViewById(R.id.TrackNo5);
        T1[2] = findViewById(R.id.TrackNo6);
        T1[1] = findViewById(R.id.TrackNo7);
        T1[0] = findViewById(R.id.TrackNo8);
        T2 = findViewById(R.id.trackname);

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        docRef = db.collection("users").document(mAuth.getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        //System.out.println(document.get("StudentID"));
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        T2.setText(document.get("Recom_Track").toString());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        chart.setBackgroundColor(Color.rgb(245,245,245));
        chart.getDescription().setEnabled(false);
        chart.setWebLineWidth(1f);

        setData();

        chart.animateXY(1000,1000, Easing.EaseInOutQuad,Easing.EaseInOutQuad);

        XAxis xAxis = chart.getXAxis();
        xAxis.setTextSize(10f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new ValueFormatter(){

            @Override
            public String getFormattedValue(float value)
            {
                return tracks[(int) value % tracks.length];
            }
        });

        xAxis.setTextColor(Color.rgb(130,145,155));

        YAxis yAxis = chart.getYAxis();
        yAxis.setTextSize(10f);
        yAxis.setLabelCount(TRACKS, false);
        yAxis.setAxisMinimum(MIN);
        yAxis.setAxisMaximum(MAX);
        yAxis.setDrawLabels(false);

        Legend l = chart.getLegend();
        l.setTextSize(5f);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
        l.setTextColor(Color.rgb(130,145,155));


    }
    public void setData()
    {
        Cursor datas = mDatabaseHelper.getScoreID(mAuth.getUid());
        ArrayList<RadarEntry> user = new ArrayList<>();
        HashMap<String,Float> result = new HashMap<>();     //Hash map of <subject, grade>
        float[] temporary = new float[22];      //Grade for each subject
        int g = 0;

        while(datas.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            for(int i=0;i<22;i++) {
                result.put(Subjects[i],Float.parseFloat(datas.getString(i)));   //Putting data into hash map
            }

        }
        for(String temp:Subjects)
        {
            //For each iteration, temp becomes the subject name in Subjects[]
            //Use temp as input for 'get' hash function to retrieve the grade of that subject
            temporary[g] = result.get(temp);
            System.out.println(temporary[g]);
            g++;
        }

       /**
        * List of subjects that effect the rank of each track
        *
        * CS:
        * 	ITCS443,ITCS343,ITCS451,ITCS306,ITCS222,ITCS200,ITCS175,ITCS211,ITCS125
        * SE:
        * 	ITCS371,ITCS159,ITCS208,ITCS200,ITCS125,
        * DB:
        * 	ITCS241,ITCS414,ITCS231,ITCS320,ITCS200,ITCS208,ITCS125
        * CN:
        * 	ITCS420,ITCS323,ITCS200,ITCS208,ITCS211,ITCS125,
        * HT:
        * 	ITCS241,ITCS371,ITCS200
        * MM:
        * 	ITCS381,ITCS320,ITCS200,ITCS208
        * MS:
        * 	ITCS361,ITCS371,ITCS231,ITCS208
        * EB:
        * 	ITCS335,ITCS200,ITCS208,ITCS231
        * **/

        TrackScore[0] = (temporary[19]+temporary[12]+temporary[1]+temporary[4]+temporary[5]+temporary[3])/1.5f;
        TrackScore[1] = (temporary[20]+temporary[14]+temporary[21]+temporary[10]+temporary[8]+temporary[1]+temporary[0]+temporary[5]+temporary[3])/1.7f;
        TrackScore[2] = (temporary[11]+temporary[18]+temporary[9]+temporary[2]+temporary[1]+temporary[4]+temporary[3])/1.5f;
        TrackScore[3] = (temporary[13]+temporary[1]+temporary[4]+temporary[9])*1.1f;
        TrackScore[4] = (temporary[11]+temporary[17]+temporary[1])*1.5f;
        TrackScore[5] = (temporary[15]+temporary[3]+temporary[1]+temporary[4])*1.1f;
        TrackScore[6] = (temporary[16]+temporary[17]+temporary[9]+temporary[4])*1.1f;
        TrackScore[7] = (temporary[17]+temporary[6]+temporary[4]+temporary[1]+temporary[3])/1.1f;

        for(int i=0;i<TRACKS;i++)//{"CN","CS","DB","EB","HT","MM","MS","SE"}
        {
            float val = TrackScore[i];
            if(val > 18)
            {
                val = 18;
            }
            user.add(new RadarEntry((val)));
        }
        String[] tempptracks = new String[8];
        float max = 0;
        int choose = 0;
        int T1Num = 7;
        for(int j=0;j<8;j++) {
            for (int i = 0; i < 8; i++) {
                if (TrackScore[i] > max) {
                    max = TrackScore[i];
                    T1[T1Num].setText(tracks[i]);
                    choose = i;

                }
            }
            System.out.println("ITEMS : ");
            System.out.println(TrackScore[5]);

            T1Num--;
            TrackScore[choose] = -1;
            max = 0;
        }

        RadarDataSet set = new RadarDataSet(user,"ID: "+StudentID);
        set.setColor(Color.rgb(45,186,225));
        set.setFillColor(Color.rgb(45,186,225));
        set.setDrawFilled(true);
        set.setFillAlpha(180);
        set.setLineWidth(2f);
        set.setDrawHighlightIndicators(false);
        set.setDrawHighlightCircleEnabled(true);

        ArrayList<IRadarDataSet> sets = new ArrayList<>();
        sets.add(set);
        RadarData data = new RadarData(sets);
        data.setValueTextSize(8f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.rgb(130,145,155));

        chart.setData(data);

        chart.invalidate();


    }
    public void GodTemp(View view)
    {
//        chart.getXAxis().setEnabled(!chart.getXAxis().isEnabled());
//        chart.notifyDataSetChanged();

        chart.invalidate();
    }
    public void Logout(View view)
    {
        finishAndRemoveTask();
        Intent intent   = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}

