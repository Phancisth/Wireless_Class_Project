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
    private static final String COL3 = "ITCS175";       //1Advanced Math
    private static final String COL4 = "ITCS200";       //2Fund. Program
    private static final String COL5 = "ITCS320";       //3Discrete Structure
    private static final String COL6 = "ITCS125";       //4Applied Stat
    private static final String COL7 = "ITCS208";       //5Object Oriented Programming
    private static final String COL8 = "ITCS211";       //6Intro. to Digital System
    private static final String COL9 = "ITCS159";       //7Software Lab.
    private static final String COL10 = "ITCS210";      //8Web Programming
    private static final String COL11 = "ITCS222";      //9Computer Organization and Architect.
    private static final String COL12 = "ITCS231";      //10Data Structure and Algorithm
    private static final String COL13 = "ITCS306";      //11Numerical Method
    private static final String COL14 = "ITCS241";      //12Database Management System
    private static final String COL15 = "ITCS323";      //13Computer Data Comm.
    private static final String COL16 = "ITCS335";      //14Intro to E-Biz.
    private static final String COL17 = "ITCS343";      //15Principles of OS
    private static final String COL18 = "ITCS381";      //16Intro to Multi.
    private static final String COL19 = "ITCS361";      //17Management Info. System
    private static final String COL20 = "ITCS371";      //18Intro to Soft. Eng.
    private static final String COL21 = "ITCS414";      //19Info. Storage and Retriev.
    private static final String COL22 = "ITCS420";      //20Computer Network
    private static final String COL23 = "ITCS443";      //21Parallel and Distributed Sys.
    private static final String COL24 = "ITCS451";      //22AI

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

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String[] item) {
        //ADD USER ID AND STUFF HERE
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        UserID = user.getUid();

        contentValues.put(COL2, UserID);
        contentValues.put(COL3, item[0]); contentValues.put(COL4, item[1]); contentValues.put(COL5, item[2]);
        contentValues.put(COL6, item[3]); contentValues.put(COL7, item[4]); contentValues.put(COL8, item[5]);
        contentValues.put(COL9, item[6]); contentValues.put(COL10, item[7]); contentValues.put(COL11, item[8]);
        contentValues.put(COL12, item[9]); contentValues.put(COL13, item[10]); contentValues.put(COL14, item[11]);
        contentValues.put(COL15, item[12]); contentValues.put(COL16, item[13]); contentValues.put(COL17, item[14]);
        contentValues.put(COL18, item[15]); contentValues.put(COL19, item[16]); contentValues.put(COL20, item[17]);
        contentValues.put(COL21, item[18]); contentValues.put(COL22, item[19]); contentValues.put(COL23, item[20]);
        contentValues.put(COL24, item[21]);


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
        String query = "SELECT * FROM " + TABLE_NAME ;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Returns only the ID that matches the name passed in
     * @param name
     * @return
     */
    public Cursor getItemID(String name){
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        UserID = user.getUid();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE "
               +COL2+" = '"+UserID+"'";

        Cursor data = db.rawQuery(query, null);

            return data;
    }
    /*public boolean updateQuestion (int setNO, int questionFlag, Question question){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("problem", question.getQuestionName());
        contentValues.put("answer1", question.getAnswers()[0]);
        contentValues.put("answer2", question.getAnswers()[1]);
        contentValues.put("answer3", question.getAnswers()[2]);
        contentValues.put("answer4", question.getAnswers()[3]);
        contentValues.put("correctans", question.getCorrectAnswer());
        db.update("question", contentValues, "setNO= ? AND questionNo = ? AND questionFlag = ?", new String[]{ Integer.toString(setNO),Integer.toString(question.getQuestionNO()), Integer.toString(questionFlag)});
        return true;
    }*/
    /**
     * Returns the scores of the ID that matches the name passed in
     *
     * **/
    public Cursor getScoreID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "
                + COL3 + ", "+ COL4 + ", "+ COL5 + ", "
                + COL6 + ", "+ COL7 + ", "+ COL8 + ", "
                + COL9 + ", "+ COL10 + ", "+ COL11 + ", "
                + COL12 + ", "+ COL13 + ", "+ COL14 + ", "
                + COL15 + ", "+ COL16 + ", "+ COL17 + ", "
                + COL18 + ", "+ COL19 + ", "+ COL20 + ", "
                + COL21 + ", "+ COL22 + ", "+ COL23 + ", "
                + COL24
                +" FROM " + TABLE_NAME + " WHERE " + COL2 + " = '" + name + "'";

        Cursor data = db.rawQuery(query, null);
        return data;
    }


    /**
     * Updates the name field
     * @param newName
     * @param
     * @param
     */
//    public void updateName(String newName, String COLName){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "UPDATE " + TABLE_NAME + " SET " + COLName + " = '" + newName + "' WHERE " + COL1 + " = '" + id + "'" + " AND " + COL2 + " = '" + oldName + "'";
//        Log.d(TAG, "updateName: query: " + query);
//        Log.d(TAG, "updateName: Setting name to " + newName);
//        db.execSQL(query);
//    }

    /**
     * Update the score value of a subject of a user
     *
     * @param subjectID (this is exactly the same as column name)
     * @param newScore
     * @param
     */
    public void updateScore(String newScore, String subjectID) {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        UserID = user.getUid();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + subjectID + " = '" + newScore + "' WHERE " + COL2 + "= '" + UserID+ "'";
        Log.d(TAG, "updateScore: query: " + query);
        Log.d(TAG, "updateScore: setting score to " + newScore);
        db.execSQL(query);
    }

    /**
     * Delete from database
     * @param
     * @param name
     */
    public void deleteName(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

}