package com.example.assignment2.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.assignment2.helper.DBContract.*;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "database.db";
    public static final int DATABASE_VERSION = 1;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_BIKELIST_TABLE = "CREATE TABLE " +
                BikeEntry.TABLE_NAME + " (" +
                BikeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                BikeEntry.COLUMN_BRAND + " TEXT NOT NULL, " +
                BikeEntry.COLUMN_MODEL + " TEXT NOT NULL, " +
                BikeEntry.COLUMN_DOP + " NUMERIC NOT NULL, " +
                BikeEntry.COLUMN_PICID + " TEXT " +
                ");";


        db.execSQL(SQL_CREATE_BIKELIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
