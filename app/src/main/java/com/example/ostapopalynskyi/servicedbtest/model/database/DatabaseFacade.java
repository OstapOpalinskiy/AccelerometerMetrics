package com.example.ostapopalynskyi.servicedbtest.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseFacade {

    private static DatabaseFacade instance;

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private final Context context;
    private int openCounter = 0;


    public static DatabaseFacade instance(Context theContext) {
        if (instance == null) {
            instance = new DatabaseFacade(theContext);
        }
        return instance;
    }


    public static DatabaseFacade instance() {
        if (instance == null) {
            throw new IllegalStateException("Called method on uninitialized database facade");
        }

        return instance;
    }


    private DatabaseFacade(Context theContext) {
        this.context = theContext;
    }


    public void beginTransactions() {
        db.beginTransaction();
    }


    public void setTransactionSuccesfull() {
        db.setTransactionSuccessful();
    }


    public void endTransactions() {
        db.endTransaction();
    }


    public synchronized DatabaseFacade open()
            throws SQLException {

        if (openCounter == 0) {
            this.dbHelper = new DatabaseHelper(this.context);
            this.db = this.dbHelper.getWritableDatabase();
        }

        this.openCounter++;
        return this;
    }


    public synchronized void close() {
        if (openCounter == 1) {
            this.db.close();
            this.dbHelper.close();
        }

        openCounter--;
    }

    public long save(String theTable, ContentValues theValues) {
        return db.insert(theTable, null, theValues);
    }

    public Cursor getRowsCount(String query) {
        return db.query(query, null, null, null, null, null, null);
    }

}
