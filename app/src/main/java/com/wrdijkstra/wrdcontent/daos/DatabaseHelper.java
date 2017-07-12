package com.wrdijkstra.wrdcontent.daos;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by WDijkstra on 27-Jun-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private String databaseName;

    public static final int DATABASE_VERSION = 2;
    public static final String TABLE = "Counters";
    public static final String _ID = "_id";
    public static final String LABEL = "label";
    public static final String COUNT = "count";
    public static final String LOCKED = "locked";

    // A string that defines the SQL statement for creating a table
    private static final String SQL_CREATE_DB = "CREATE TABLE " +
            TABLE +                    // Table's name
            " ( " +                    // The columns in the table
            _ID + " INTEGER PRIMARY KEY, " +
            LABEL + " TEXT, " +
            COUNT + " INTEGER, " +
            LOCKED + " INTEGER )";

    public DatabaseHelper (Context context, String databaseName) {
        super(context, databaseName, null, DATABASE_VERSION);
        this.context = context;
        this.databaseName = databaseName;
    }

    public void onCreate(SQLiteDatabase db) {

        // Creates the main table
        db.execSQL(SQL_CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // For now, on upgrade, delete database and create new one
        context.deleteDatabase(databaseName);
        onCreate(db);
    }
}
