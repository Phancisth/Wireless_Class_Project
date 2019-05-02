/*Project By
5988023	Purit		Phanudom
5988053	Naruedon	Wattanakul
5988098	Tattiya		Sakulniwat
 */
package com.example.wireless_class_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class EditDataActivity extends AppCompatActivity {

    private static final String TAG = "EditDataActivity";
    //NameSpace
    private Button btnSave,btnDelete;
    private EditText editable_item;

    DatabaseHelper mDatabaseHelper;

    private String selectedName;
    private int selectedID;
    private int BackLogout = 2;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        //Initialize
        BackLogout = 2;
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        editable_item = (EditText) findViewById(R.id.editable_item);
        mDatabaseHelper = new DatabaseHelper(this);
        mAuth = FirebaseAuth.getInstance();
        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        selectedName = receivedIntent.getStringExtra("name");

        //set the text to show the current selected name
        editable_item.setHint(selectedName);
        //Saves the new Data to DB using Update
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editable_item.getText().toString();
                if(!item.equals("")){
                    if (Double.parseDouble(item) > 4 || Double.parseDouble(item) < 1) {
                        toastMessage("Grade Value Not Good Format");
                    }
                    else {
                        mDatabaseHelper.updateScore(item, selectedName);
                        finishAndRemoveTask();
                        Intent intent = new Intent(EditDataActivity.this, ListDataActivity.class);
                        startActivity(intent);
                    }
                }else{
                    toastMessage("You must enter a new Score");
                }
            }
        });
        //Delete Data from DB using Delete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteName(selectedName);
                editable_item.setText("");
                toastMessage("removed from database");
                finishAndRemoveTask();
                Intent intent = new Intent(EditDataActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });

    }
    /*@Override
    public void onBackPressed() {
        if (BackLogout <= 0) {
            Logout(this.findViewById(android.R.id.content));
        } else {
            toastMessage("Pressing Back "+BackLogout+" times will log out");
            BackLogout--;
        }
    }*/
    public void Logout(View view)
    {
        mAuth.signOut();
        finishAndRemoveTask();
        Intent intent   = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    //Toast Handler
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}