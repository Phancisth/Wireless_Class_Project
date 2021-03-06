/*Project By
5988023	Purit		Phanudom
5988053	Naruedon	Wattanakul
5988098	Tattiya		Sakulniwat
 */
package com.example.wireless_class_project;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class ListDataActivity extends AppCompatActivity {

    //NameSpace
    private static final String TAG = "ListDataActivity";
    private int BackLogout = 2;
    private FirebaseAuth mAuth;

    DatabaseHelper mDatabaseHelper;

    private ListView mListView;

    private String[] Subjects = {
            "Advanced Math for Computer Science",
            "C Programming",
            "Discrete Structure",
            "Applied Statistics for Computing",
            "Java Programming",
            "Introduction to Digital System",
            "Software Lab",
            "Web Programming",
            "Computer Organization and Architecture",
            "Data Structure and Algorithm Analysis",
            "Numerical Methods",
            "Database Management System",
            "Computer Data Communication",
            "Introduction to E-business",
            "Principle of Operating System",
            "Introduction to Multimedia",
            "Management Information System",
            "Introduction to Software Engineering",
            "Information Storage and Retrieval",
            "Computer Networks",
            "Parallel and Distributed System",
            "Artificial Intelligence"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        //Initialize
        BackLogout = 2;
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        mListView = (ListView) findViewById(R.id.listView);


        mDatabaseHelper = new DatabaseHelper(this);

        populateListView();
    }
    //adds data to the list view for users to select
    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        //get the data and append to a list
        Cursor data = mDatabaseHelper.getScoreID(mAuth.getUid());

        List<HashMap<String,String>> listData = new ArrayList<>();
        HashMap<String,String> result = new HashMap<>();
        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            for(int i=0;i<22;i++) {
                result.put(Subjects[i],data.getString(i));
            }

        }
        SimpleAdapter adapter = new SimpleAdapter(this, listData, R.layout.list_item,
                new String[]{"First Line=|=", "Second Line"},
                new int[]{R.id.text1, R.id.text2});
        //create the list adapter and set the adapter
        Iterator it = result.entrySet().iterator();
        while (it.hasNext())
        {
            HashMap<String, String> resultsMap = new HashMap<>();
            Map.Entry pair = (Map.Entry)it.next();
            resultsMap.put("First Line=|=", pair.getKey().toString());
            resultsMap.put("Second Line", pair.getValue().toString());
            listData.add(resultsMap);
        }

        mListView.setAdapter(adapter);


        //set an onItemClickListener to the ListView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                name = name.substring(name.indexOf("=|=")+1);
                name.trim();
                name = name.replace("=","");
                name = name.replace("|","");
                name = name.replace("}","");
                //Remove bad characters
                Log.d(TAG, "onItemClick: You Clicked on " + name);

                Cursor data = mDatabaseHelper.getItemID(name); //get the id associated with that name
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    finishAndRemoveTask();
                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
                    Intent editScreenIntent = new Intent(ListDataActivity.this, EditDataActivity.class);
                    editScreenIntent.putExtra("id",itemID);
                    editScreenIntent.putExtra("name",name);
                    startActivity(editScreenIntent);
                }
                else{
                    toastMessage("No ID associated with that name");
                }
            }
        });
    }
   /* @Override
    public void onBackPressed() {
        if (BackLogout <= 0) {
            Logout(this.findViewById(android.R.id.content));
        } else {
            toastMessage("Pressing Back "+BackLogout+" times will log out");
            BackLogout--;
        }
    }*/
    //Toast Handler
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