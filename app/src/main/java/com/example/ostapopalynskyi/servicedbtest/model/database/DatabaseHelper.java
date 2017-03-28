package com.example.ostapopalynskyi.servicedbtest.model.database;


import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ostapopalynskyi.servicedbtest.R;


public class DatabaseHelper
        extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "main_db";

    private static final int DATABASE_VERSION = 1;

    private final Resources appResources;

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.appResources = context.getResources();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(appResources.getString(R.string.create_table_accelerometer_metrics));
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO: handle data migration here
    }
}
