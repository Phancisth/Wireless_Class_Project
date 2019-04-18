package com.example.wireless_class_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


public class DatabaseHelper extends SQLiteOpenHelper {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "user_id_grades";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    private static final String COL3 = "ITCS175";       //Advanced Math
    private static final String COL4 = "ITCS200";       //Fund. Program
    private static final String COL5 = "ITCS320";       //Discrete Structure
    private static final String COL6 = "ITCS125";       //Applied Stat
    private static final String COL7 = "ITCS208";       //Object Oriented Programming
    private static final String COL8 = "ITCS211";       //Intro. to Digital System
    private static final String COL9 = "ITCS159";       //Software Lab.
    private static final String COL10 = "ITCS210";      //Web Programming
    private static final String COL11 = "ITCS222";      //Computer Organization and Architect.
    private static final String COL12 = "ITCS231";      //Data Structure and Algorithm
    private static final String COL13 = "ITCS306";      //Numerical Method
    private static final String COL14 = "ITCS241";      //Database Management System
    private static final String COL15 = "ITCS323";      //Computer Data Comm.
    private static final String COL16 = "ITCS335";      //Intro to E-Biz.
    private static final String COL17 = "ITCS343";      //Principles of OS
    private static final String COL18 = "ITCS381";      //Intro to Multi.
    private static final String COL19 = "ITCS361";      //Management Info. System
    private static final String COL20 = "ITCS371";      //Intro to Soft. Eng.
    private static final String COL21 = "ITCS414";      //Info. Storage and Retriev.
    private static final String COL22 = "ITCS420";      //Computer Network
    private static final String COL23 = "ITCS443";      //Parallel and Distributed Sys.
    private static final String COL24 = "ITCS451";      //AI
    private static final String grade1 = "MM";

    private String UserID;



    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " TEXT, " +
                COL3 + " INTEGER, " +
                COL4 + " INTEGER, " +
                COL5 + " INTEGER, " +
                COL6 + " INTEGER, " +
                COL7 + " INTEGER, " +
                COL8 + " INTEGER, " +
                COL9 + " INTEGER, " +
                COL10 + " INTEGER, " +
                COL11 + " INTEGER, " +
                COL12 + " INTEGER, " +
                COL13 + " INTEGER, " +
                COL14 + " INTEGER, " +
                COL15 + " INTEGER, " +
                COL16 + " INTEGER, " +
                COL17 + " INTEGER, " +
                COL18 + " INTEGER, " +
                COL19 + " INTEGER, " +
                COL20 + " INTEGER, " +
                COL21 + " INTEGER, " +
                COL22 + " INTEGER, " +
                COL23 + " INTEGER, " +
                COL24 + " INTEGER)" ;


        db.execSQL(createTable);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        UserID = user.getUid();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String item) {
        //ADD USER ID AND STUFF HERE
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);

        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Returns only the ID that matches the name passed in
     * @param name
     * @return
     */
    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Updates the name field
     * @param newName
     * @param id
     * @param oldName
     */
    public void updateName(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + newName + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    /**
     * Delete from database
     * @param id
     * @param name
     */
    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

}