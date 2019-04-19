package com.example.wireless_class_project;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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

import java.util.ArrayList;


public class Competency extends AppCompatActivity {
    private static final float MAX =16, MIN =1f;
    private static final int TRACKS = 8;
    private RadarChart chart;
    private String StudentID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competency);
        chart = findViewById(R.id.RadarChart);

        StudentID = getIntent().getStringExtra("StudentID");

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
            private final String[] tracks = new String[]{"CN","CS","DB","EB","HT","MM","MS","SE"};
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
        ArrayList<RadarEntry> user = new ArrayList<>();

        for(int i=0;i<TRACKS;i++)
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

