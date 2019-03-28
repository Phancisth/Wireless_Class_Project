package com.example.wireless_class_project;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Questionare extends AppCompatActivity {


    RadioGroup radioGroup1,radioGroup2, radioGroup3, radioGroup4, radioGroup5;
    RadioButton radioButton;
    TextView textView;
    private int[] Score = new int[8];//CS,CN,SE,DB,HT,MM,EB,MS
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionare);

        switch(page)
        {
            case 1:

                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }

        radioGroup1 = findViewById(R.id.radioGroup1);
        radioGroup2 = findViewById(R.id.radioGroup2);
        radioGroup3 = findViewById(R.id.radioGroup3);
        radioGroup4 = findViewById(R.id.radioGroup4);
        radioGroup5 = findViewById(R.id.radioGroup5);

        Button applyButtonGroup1 = findViewById(R.id.radioButton11);
        Button applyButtonGroup2 = findViewById(R.id.radioButton21);
        Button applyButtonGroup3 = findViewById(R.id.radioButton31);
        Button applyButtonGroup4 = findViewById(R.id.radioButton41);
        Button applyButtonGroup5 = findViewById(R.id.radioButton51);

        applyButtonGroup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioID = radioGroup1.getCheckedRadioButtonId();
                radioButton = findViewById(radioID);
            }
        });

        applyButtonGroup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioID = radioGroup1.getCheckedRadioButtonId();
                radioButton = findViewById(radioID);
            }
        });

        applyButtonGroup3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioID = radioGroup1.getCheckedRadioButtonId();
                radioButton = findViewById(radioID);
            }
        });

        applyButtonGroup4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioID = radioGroup1.getCheckedRadioButtonId();
                radioButton = findViewById(radioID);
            }
        });

        applyButtonGroup5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioID = radioGroup1.getCheckedRadioButtonId();
                radioButton = findViewById(radioID);
            }
        });
    }
    public void Logout(View view)
    {
        finishAndRemoveTask();
        Intent intent   = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void Next(View view) {

        //Get some score here

        //Go the to the next page in the questionnaire
        Intent nextpage = new Intent(this, Questionare.class);
        nextpage.putExtra("Score",Score);
        nextpage.putExtra("Page", page);

    }

    public void Prev(View view) {
        //Go to the previous page in the questionnaire
    }

    public CharSequence checkButton (View view) {

        int radioID = radioGroup1.getCheckedRadioButtonId();
        radioButton = findViewById(radioID);
        CharSequence radioButtonText = radioButton.getText();

        return radioButtonText;

    }
}
