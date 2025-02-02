package com.example.besafe_letsexplore.dummy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "register.db";
    public static final String TABLE_NAME = "registerFullName";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "FullName";
    public static final String COL_3 = "Password";



    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(" CREATE TABLE registeruser (ID INTEGER PRIMARY KEY AUTOINCREMENT,  FullName TEXT, Password TEXT) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public long addUser(String user, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Full Name", user);
        contentValues.put("Password", password);
        long res = db.insert("registerFullName", null, contentValues);
        db.close();
        return res;

    }

    public boolean checkUser(String FullName, String Password) {
        String[] columns = { COL_1};
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + "and" + COL_3 + "=?";
        String[] selectionArgs =  { FullName, Password };
        Cursor cursor = db.query(TABLE_NAME, columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();


        if(count>0)
            return  true;
        else
            return  false;


    }

}
