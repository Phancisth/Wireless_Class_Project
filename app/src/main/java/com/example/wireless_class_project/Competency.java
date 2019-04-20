package com.example.wireless_class_project;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

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
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;


public class Competency extends AppCompatActivity {
    private static final float MAX =16, MIN =1f;
    private static final int TRACKS = 8;
    private RadarChart chart;
    private FirebaseAuth mAuth;
    DatabaseHelper mDatabaseHelper;
    private String[] Subjects = {"ITCS175","ITCS200","ITCS320","ITCS125","ITCS208","ITCS211","ITCS159","ITCS210","ITCS222","ITCS231","ITCS306","ITCS241","ITCS323","ITCS335","ITCS343","ITCS381","ITCS361","ITCS371","ITCS414","ITCS420","ITCS443","ITCS451"};
    private final String[] tracks = new String[]{"CN","CS","DB","EB","HT","MM","MS","SE"};
    private int TrackScore[] = new int[8];
    private String StudentID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competency);
        chart = findViewById(R.id.RadarChart);
        mDatabaseHelper = new DatabaseHelper(this);
        StudentID = getIntent().getStringExtra("StudentID");

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();


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
        HashMap<String,Double> result = new HashMap<>();
        Double[] temporary = new Double[24];
        int g = 0;

        while(datas.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            for(int i=0;i<22;i++) {
                result.put(Subjects[i],Double.parseDouble(datas.getString(i)));
            }

        }
        for(String temp:Subjects)
        {
            temporary[g] = result.get(temp);
            System.out.println(result.get(temp));
            g++;
        }
        for(int i=0;i<TRACKS;i++)//{"CN","CS","DB","EB","HT","MM","MS","SE"}
        {
            float val = (int)(Math.random()*MAX)+MIN;
            user.add(new RadarEntry((val)));
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

