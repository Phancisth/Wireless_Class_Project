
package com.example.wireless_class_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import android.widget.EditText;
import android.widget.Toast;


public class Database extends AppCompatActivity {

    private static final String TAG = "Database";

    DatabaseHelper mDatabaseHelper;
    private Button btnAdd, btnViewData;
    private String[] score = new String[22];
    private EditText ITCS175, ITCS200, ITCS320, ITCS125, ITCS208, ITCS211, ITCS159, ITCS210, ITCS222, ITCS231, ITCS306, ITCS241, ITCS323, ITCS335, ITCS343, ITCS381, ITCS361, ITCS371, ITCS414, ITCS420, ITCS443, ITCS451;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        ITCS175 = (EditText) findViewById(R.id.ITCS175_edit); score[0] = ITCS175.getText().toString();
        ITCS200 = (EditText) findViewById(R.id.ITCS200_edit); score[1] = ITCS175.getText().toString();
        ITCS320 = (EditText) findViewById(R.id.ITCS320_edit); score[2] = ITCS175.getText().toString();
        ITCS125 = (EditText) findViewById(R.id.ITCS125_edit); score[3] = ITCS175.getText().toString();
        ITCS208 = (EditText) findViewById(R.id.ITCS208_edit); score[4] = ITCS175.getText().toString();
        ITCS211 = (EditText) findViewById(R.id.ITCS211_edit); score[5] = ITCS175.getText().toString();
        ITCS159 = (EditText) findViewById(R.id.ITCS159_edit); score[6] = ITCS175.getText().toString();
        ITCS210 = (EditText) findViewById(R.id.ITCS210_edit); score[7] = ITCS175.getText().toString();
        ITCS222 = (EditText) findViewById(R.id.ITCS222_edit); score[8] = ITCS175.getText().toString();
        ITCS231 = (EditText) findViewById(R.id.ITCS231_edit); score[9] = ITCS175.getText().toString();
        ITCS306 = (EditText) findViewById(R.id.ITCS306_edit); score[10] = ITCS175.getText().toString();
        ITCS241 = (EditText) findViewById(R.id.ITCS241_edit); score[11] = ITCS175.getText().toString();
        ITCS323 = (EditText) findViewById(R.id.ITCS323_edit); score[12] = ITCS175.getText().toString();
        ITCS335 = (EditText) findViewById(R.id.ITCS335_edit); score[13] = ITCS175.getText().toString();
        ITCS343 = (EditText) findViewById(R.id.ITCS343_edit); score[14] = ITCS175.getText().toString();
        ITCS381 = (EditText) findViewById(R.id.ITCS381_edit); score[15] = ITCS175.getText().toString();
        ITCS361 = (EditText) findViewById(R.id.ITCS361_edit); score[16] = ITCS175.getText().toString();
        ITCS371 = (EditText) findViewById(R.id.ITCS371_edit); score[17] = ITCS175.getText().toString();
        ITCS414 = (EditText) findViewById(R.id.ITCS414_edit); score[18] = ITCS175.getText().toString();
        ITCS420 = (EditText) findViewById(R.id.ITCS420_edit); score[19] = ITCS175.getText().toString();
        ITCS443 = (EditText) findViewById(R.id.ITCS443_edit); score[20] = ITCS175.getText().toString();
        ITCS451 = (EditText) findViewById(R.id.ITCS451_edit); score[21] = ITCS175.getText().toString();


        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnViewData = (Button) findViewById(R.id.btnView);
        mDatabaseHelper = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean scoreCheck = true;

                for(int i = 0; i < 22; i++) {
                    if(score[i] == null) scoreCheck = false;
                }

                if (scoreCheck) {
                    AddData(score);
                    //editText.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }

            }
        });

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Database.this, ListDataActivity.class);
                startActivity(intent);
            }
        });

    }

    public void AddData(String[] newEntry) {
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}