package com.example.wireless_class_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Questionare extends AppCompatActivity {


    RadioGroup radioGroup1,radioGroup2, radioGroup3, radioGroup4, radioGroup5;
    RadioButton radioButton;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionare);

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
        finishAffinity();
        Intent intent   = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void Next(View view) {

        //Go the to the next page in the questionnaire
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
